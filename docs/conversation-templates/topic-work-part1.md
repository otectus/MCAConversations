# Topic: work

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `work` |
| Opened from | question `conversations.cat.profession`, button `work` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.profession` |
| Ages that can reach it | child, teen, adult |
| Stance families it must offer | `encouragement`, `curiosity`, `challenge`, `dismissal`, `humor`, `self_disclosure`, `candor`, `exit` |
| Narrative arc | `work`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.profession`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.profession.work
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.profession
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.profession.work   [31 chars]
    en  Do you actually like your work?
    >>  ............................................
    pt  Você gosta mesmo do seu trabalho?
    >>  ............................................
```

---

**Parts of this conversation:** [part 1](topic-work-part1.md) · [part 2](topic-work-part2.md) · [part 3](topic-work-part3.md) · [part 4](topic-work-part4.md)


## Nodes in this file

- [`conversations.arc.work.resume.followup`](#conversations-arc-work-resume-followup)
- [`conversations.arc.work.resume.respond`](#conversations-arc-work-resume-respond)
- [`conversations.topic.work.again.respond`](#conversations-topic-work-again-respond)
- [`conversations.topic.work.hate.followup`](#conversations-topic-work-hate-followup)
- [`conversations.topic.work.hate.respond`](#conversations-topic-work-hate-respond)
- [`conversations.topic.work.pride.followup`](#conversations-topic-work-pride-followup)
- [`conversations.topic.work.pride.respond`](#conversations-topic-work-pride-respond)
- [`conversations.topic.work.young.respond`](#conversations-topic-work-young-respond)
- [`conversations.work`](#conversations-work)

---

## `conversations.arc.work.resume.followup`

**Reached from 3 route(s):** `conversations.arc.work.resume.respond` / `ask_next_step`; `conversations.arc.work.resume.respond` / `offer_help`; `conversations.arc.work.resume.respond` / `doubt`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.resume.ask_next_step` — e.g. "Somebody with more authority than me saying yes. That's the whole of the next step."
- `conversations.work.resume.doubt` — e.g. "I did. And I'll say it next time, which is either persistence or the other thing."
- `conversations.work.resume.offer_help` — e.g. "...Say that again in a month and I'll believe you're not being polite."


```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.work.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.work.resume.followup   [36 chars]
    en  And that's the whole of it, for now.
    >>  ............................................
    pt  E é tudo, por enquanto.
    >>  ............................................
```


### Button `hold_you_to_it` — "I'll ask again, and I'll remember the answer."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.resume.ask_next_step`, `work.resume.offer_help`, `work.resume.doubt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.resume.hold_you_to_it` — accepted phrasings: "i will ask again and remember the answer"; "i will hold you to it"; "i will check back on this"
  - the message must contain one of: `remember`
  - scored words: `again`(0.6), `remember`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.followup.hold_you_to_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.followup.hold_you_to_it   [45 chars]
    en  I'll ask again, and I'll remember the answer.
    >>  ............................................
    pt  Vou perguntar de novo, e vou lembrar da resposta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.resume.hold`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.resume.hold_you_to_it`)_
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.resume.hold_you_to_it
WHO    VILLAGER — what the player reads after pressing "I'll ask again, and I'll remember the answer."
       spoken on: conversations.arc.work.resume.followup, button `hold_you_to_it`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.hold_you_to_it`: the villager accepts. Subject `work.aspiration`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.resume.hold_you_to_it/1   [76 chars]
    en  Then I'd better have a better one. That's more use to me than encouragement.
    >>  ............................................
    pt  Então é bom eu ter uma resposta melhor. Isso me serve mais que incentivo.
    >>  ............................................
  dialogue.conversations.work.resume.hold_you_to_it/2   [56 chars]
    en  Do. Half of why nothing moves is that nobody asks twice.
    >>  ............................................
    pt  Pergunte. Metade do motivo de nada andar é que ninguém pergunta duas vezes.
    >>  ............................................
  dialogue.conversations.work.resume.hold_you_to_it/3   [80 chars]
    en  Right. I'll not enjoy it and I'll be glad of it, which is how these things work.
    >>  ............................................
    pt  Certo. Não vou gostar e vou agradecer, que é como essas coisas funcionam.
    >>  ............................................
```


### Button `no_hurry` — "There's no hurry in it."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.resume.ask_next_step`, `work.resume.offer_help`, `work.resume.doubt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.resume.no_hurry` — accepted phrasings: "there is no hurry"; "take your time with it"; "no need to rush it"
  - the message must contain one of: `hurry`, `rush`
  - scored words: `hurry`(1.5), `rush`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.followup.no_hurry
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.followup.no_hurry   [23 chars]
    en  There's no hurry in it.
    >>  ............................................
    pt  Não há pressa nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.resume.patience`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `work.resume.no_hurry`)_
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.resume.no_hurry
WHO    VILLAGER — what the player reads after pressing "There's no hurry in it."
       spoken on: conversations.arc.work.resume.followup, button `no_hurry`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.no_hurry`: the villager accepts. Subject `work.aspiration`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.resume.no_hurry/1   [57 chars]
    en  There is, a little. But thank you for saying there isn't.
    >>  ............................................
    pt  Há, um pouco. Mas obrigado por dizer que não.
    >>  ............................................
  dialogue.conversations.work.resume.no_hurry/2   [56 chars]
    en  That's kind and it's wrong, and I'll take the kind half.
    >>  ............................................
    pt  É gentil e é errado, e eu fico com a metade gentil.
    >>  ............................................
  dialogue.conversations.work.resume.no_hurry/3   [74 chars]
    en  No. There isn't. I keep forgetting that and then working through a Sunday.
    >>  ............................................
    pt  Não. Não há. Vivo esquecendo isso e depois trabalhando num domingo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.resume.ask_next_step`, `work.resume.offer_help`, `work.resume.doubt` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.followup.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.arc.work.resume.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.leave`: the villager accepts. Subject `work.aspiration`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.work.resume.respond / leave
```

```text
  dialogue.conversations.work.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.work.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.work.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.work.resume.respond`

**Reached from 37 route(s):** `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work` …and 25 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.adventurer.resume` — e.g. "I've been pricing roofs. Not buying one. Pricing one, which is further than I've got before."
- `conversations.work.archer.resume` — e.g. "I put the second-archer request in writing. It's on the mayor's table under three other things."
- `conversations.work.armorer.resume` — e.g. "I started the suit. It's in pieces and I've already remade the left pauldron twice."
- `conversations.work.butcher.resume` — e.g. "I walked the ground for the new pens. There's a spot that would work if the smith agrees."
- `conversations.work.cartographer.resume` — e.g. "I've a fourth description of the coast now. It contradicts the other three differently."
- `conversations.work.cleric.resume` — e.g. "I've a fourth candidate. She sat through an afternoon without asking once about the recipes."
- `conversations.work.cultist.resume` — e.g. "I've tested a fourth. He reached line nineteen and asked what it meant, which disqualifies him."
- `conversations.work.delightchef.resume` — e.g. "I've a second pair of hands two days a week. December is now merely difficult."
- `conversations.work.delightcook.resume` — e.g. "The pot has a fund now. Small, and it is a fund, and thinning is about barley again."
- `conversations.work.enderian.resume` — e.g. "I showed the catalogue to the librarian. She turned three pages and asked good questions."
- `conversations.work.engineer.resume` — e.g. "The race is surveyed. Digging it is a matter of hands and a summer, which is to say money."
- `conversations.work.farmer.resume` — e.g. "I got three weeks of only the field. Three weeks, and I slept like a stone."
- `conversations.work.fisherman.resume` — e.g. "I got as far as the headland. Not the stretch itself, but I've seen it now."
- `conversations.work.fletcher.resume` — e.g. "I've a stave seasoning. It'll be two years before it's a bow, and that's the trade."
- …and 23 more pools


```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.work.resume.respond   [32 chars]
    en  So that's where the plan stands.
    >>  ............................................
    pt  Então é aí que o plano está.
    >>  ............................................
```


### Button `ask_next_step` — "What's the next step?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.resume.opener.adventurer`, `work.resume.opener.archer`, `work.resume.opener.armorer`, `work.resume.opener.butcher`, `work.resume.opener.cartographer`, `work.resume.opener.cleric`, `work.resume.opener.cultist`, `work.resume.opener.delightchef`, `work.resume.opener.delightcook`, `work.resume.opener.enderian`, `work.resume.opener.engineer`, `work.resume.opener.farmer`, `work.resume.opener.fisherman`, `work.resume.opener.fletcher`, `work.resume.opener.florist`, `work.resume.opener.guard`, `work.resume.opener.hunter`, `work.resume.opener.hunter_expert`, `work.resume.opener.leatherworker`, `work.resume.opener.librarian`, `work.resume.opener.mason`, `work.resume.opener.mercenary`, `work.resume.opener.miner`, `work.resume.opener.netherian`, `work.resume.opener.nitwit`, `work.resume.opener.none`, `work.resume.opener.oceanographer`, `work.resume.opener.outlaw`, `work.resume.opener.priest`, `work.resume.opener.scribe`, `work.resume.opener.shady_wizard`, `work.resume.opener.shepherd`, `work.resume.opener.toolsmith`, `work.resume.opener.vampire_expert`, `work.resume.opener.weaponsmith`, `work.resume.opener.werewolf_expert`, `work.resume.opener.woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.resume.ask_next_step` — accepted phrasings: "what is the next step"; "what comes next for it"; "what happens next with it"
  - the message must contain one of: `step`
  - scored words: `comes`(0.8), `step`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.respond.ask_next_step
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.respond.ask_next_step   [21 chars]
    en  What's the next step?
    >>  ............................................
    pt  Qual é o próximo passo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.work.resume.followup`
- …where the player's next choices will be: "I'll ask again, and I'll remember the answer." | "There's no hurry in it." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.resume.ask_next_step
WHO    VILLAGER — what the player reads after pressing "What's the next step?"
       spoken on: conversations.arc.work.resume.respond, button `ask_next_step`
       leaves the player on: conversations.arc.work.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.ask_next_step`: the villager explains. Subject `work.aspiration`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.resume.ask_next_step/1   [83 chars]
    en  Somebody with more authority than me saying yes. That's the whole of the next step.
    >>  ............................................
    pt  Alguém com mais autoridade que eu dizendo sim. É esse o próximo passo inteiro.
    >>  ............................................
  dialogue.conversations.work.resume.ask_next_step/2   [76 chars]
    en  Money, or a season with nothing urgent in it. I've never had either at once.
    >>  ............................................
    pt  Dinheiro, ou uma estação sem nada urgente. Nunca tive os dois ao mesmo tempo.
    >>  ............................................
  dialogue.conversations.work.resume.ask_next_step/3   [81 chars]
    en  Deciding it's allowed to matter. I keep putting that off and calling it prudence.
    >>  ............................................
    pt  Decidir que pode importar. Vivo adiando isso e chamando de prudência.
    >>  ............................................
```


### Button `offer_help` — "Tell me what would move it."

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `work.resume.opener.adventurer`, `work.resume.opener.archer`, `work.resume.opener.armorer`, `work.resume.opener.butcher`, `work.resume.opener.cartographer`, `work.resume.opener.cleric`, `work.resume.opener.cultist`, `work.resume.opener.delightchef`, `work.resume.opener.delightcook`, `work.resume.opener.enderian`, `work.resume.opener.engineer`, `work.resume.opener.farmer`, `work.resume.opener.fisherman`, `work.resume.opener.fletcher`, `work.resume.opener.florist`, `work.resume.opener.guard`, `work.resume.opener.hunter`, `work.resume.opener.hunter_expert`, `work.resume.opener.leatherworker`, `work.resume.opener.librarian`, `work.resume.opener.mason`, `work.resume.opener.mercenary`, `work.resume.opener.miner`, `work.resume.opener.netherian`, `work.resume.opener.nitwit`, `work.resume.opener.none`, `work.resume.opener.oceanographer`, `work.resume.opener.outlaw`, `work.resume.opener.priest`, `work.resume.opener.scribe`, `work.resume.opener.shady_wizard`, `work.resume.opener.shepherd`, `work.resume.opener.toolsmith`, `work.resume.opener.vampire_expert`, `work.resume.opener.weaponsmith`, `work.resume.opener.werewolf_expert`, `work.resume.opener.woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.resume.offer_help` — accepted phrasings: "tell me what would move it"; "what would help it along"; "what do you need to make it happen"
  - the message must contain one of: `move`
  - scored words: `move`(1.2), `what`(0.3)

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.respond.offer_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.respond.offer_help   [27 chars]
    en  Tell me what would move it.
    >>  ............................................
    pt  Me diga o que faria isso andar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.work.resume.followup`
- …where the player's next choices will be: "I'll ask again, and I'll remember the answer." | "There's no hurry in it." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.resume.offer_help
WHO    VILLAGER — what the player reads after pressing "Tell me what would move it."
       spoken on: conversations.arc.work.resume.respond, button `offer_help`
       leaves the player on: conversations.arc.work.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.offer_help`: the villager request_helps. Subject `work.aspiration`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.resume.offer_help/1   [70 chars]
    en  ...Say that again in a month and I'll believe you're not being polite.
    >>  ............................................
    pt  ...Diga isso de novo em um mês e eu acredito que não é só educação.
    >>  ............................................
  dialogue.conversations.work.resume.offer_help/2   [84 chars]
    en  Two hands and a day. That's all it is, and I've not asked anyone for a day in years.
    >>  ............................................
    pt  Duas mãos e um dia. É só isso, e faz anos que não peço um dia a ninguém.
    >>  ............................................
  dialogue.conversations.work.resume.offer_help/3   [96 chars]
    en  You could ask about it in front of somebody who decides things. That's worth more than the work.
    >>  ............................................
    pt  Você podia perguntar sobre isso na frente de quem decide. Vale mais que o trabalho.
    >>  ............................................
```


### Button `doubt` — "You said the same thing last time."

*stance family `challenge` · tone `blunt` · outcome `qualified` · answers the beat(s) `work.resume.opener.adventurer`, `work.resume.opener.archer`, `work.resume.opener.armorer`, `work.resume.opener.butcher`, `work.resume.opener.cartographer`, `work.resume.opener.cleric`, `work.resume.opener.cultist`, `work.resume.opener.delightchef`, `work.resume.opener.delightcook`, `work.resume.opener.enderian`, `work.resume.opener.engineer`, `work.resume.opener.farmer`, `work.resume.opener.fisherman`, `work.resume.opener.fletcher`, `work.resume.opener.florist`, `work.resume.opener.guard`, `work.resume.opener.hunter`, `work.resume.opener.hunter_expert`, `work.resume.opener.leatherworker`, `work.resume.opener.librarian`, `work.resume.opener.mason`, `work.resume.opener.mercenary`, `work.resume.opener.miner`, `work.resume.opener.netherian`, `work.resume.opener.nitwit`, `work.resume.opener.none`, `work.resume.opener.oceanographer`, `work.resume.opener.outlaw`, `work.resume.opener.priest`, `work.resume.opener.scribe`, `work.resume.opener.shady_wizard`, `work.resume.opener.shepherd`, `work.resume.opener.toolsmith`, `work.resume.opener.vampire_expert`, `work.resume.opener.weaponsmith`, `work.resume.opener.werewolf_expert`, `work.resume.opener.woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.resume.doubt` — accepted phrasings: "you said the same thing last time"; "you said that before"; "that is what you said last time"
  - scored words: `last`(0.8), `said`(0.6), `time`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.respond.doubt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.respond.doubt   [34 chars]
    en  You said the same thing last time.
    >>  ............................................
    pt  Você disse a mesma coisa da última vez.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.work.resume.followup`
- …where the player's next choices will be: "I'll ask again, and I'll remember the answer." | "There's no hurry in it." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.resume.doubt
WHO    VILLAGER — what the player reads after pressing "You said the same thing last time."
       spoken on: conversations.arc.work.resume.respond, button `doubt`
       leaves the player on: conversations.arc.work.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.doubt`: the villager qualifys. Subject `work.aspiration`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.resume.doubt/1   [81 chars]
    en  I did. And I'll say it next time, which is either persistence or the other thing.
    >>  ............................................
    pt  Eu disse. E vou dizer da próxima vez, o que é ou persistência ou a outra coisa.
    >>  ............................................
  dialogue.conversations.work.resume.doubt/2   [70 chars]
    en  That's fair, and I'd rather be told than let it become a story I tell.
    >>  ............................................
    pt  É justo, e prefiro que me digam a deixar virar uma história que eu conto.
    >>  ............................................
  dialogue.conversations.work.resume.doubt/3   [77 chars]
    en  So I did. Ask me again in a season and if the answer's the same, stop asking.
    >>  ............................................
    pt  Disse mesmo. Pergunte de novo numa estação e, se a resposta for igual, pare de perguntar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.resume.opener.adventurer`, `work.resume.opener.archer`, `work.resume.opener.armorer`, `work.resume.opener.butcher`, `work.resume.opener.cartographer`, `work.resume.opener.cleric`, `work.resume.opener.cultist`, `work.resume.opener.delightchef`, `work.resume.opener.delightcook`, `work.resume.opener.enderian`, `work.resume.opener.engineer`, `work.resume.opener.farmer`, `work.resume.opener.fisherman`, `work.resume.opener.fletcher`, `work.resume.opener.florist`, `work.resume.opener.guard`, `work.resume.opener.hunter`, `work.resume.opener.hunter_expert`, `work.resume.opener.leatherworker`, `work.resume.opener.librarian`, `work.resume.opener.mason`, `work.resume.opener.mercenary`, `work.resume.opener.miner`, `work.resume.opener.netherian`, `work.resume.opener.nitwit`, `work.resume.opener.none`, `work.resume.opener.oceanographer`, `work.resume.opener.outlaw`, `work.resume.opener.priest`, `work.resume.opener.scribe`, `work.resume.opener.shady_wizard`, `work.resume.opener.shepherd`, `work.resume.opener.toolsmith`, `work.resume.opener.vampire_expert`, `work.resume.opener.weaponsmith`, `work.resume.opener.werewolf_expert`, `work.resume.opener.woodworker` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.work.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.work.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.work.resume.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.arc.work.resume.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.leave`: the villager accepts. Subject `work.aspiration`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.work.resume.followup / leave
```

> Written out in full under **`conversations.arc.work.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.again.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.again` — e.g. "We covered my work already. It hasn't gotten more interesting since."


```text
POOL   dialogue key: dialogue.conversations.topic.work.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.again.respond   [27 chars]
    en  We covered my work already.
    >>  ............................................
    pt  Já falamos do meu trabalho.
    >>  ............................................
```


### Button `apologize` — "Sorry — you've told me."

*stance family `candor` · tone `gentle` · answers the beat(s) `work.again.to.work.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.again.apologize` — accepted phrasings: "sorry, you have told me"; "sorry, i already asked"; "my mistake, you told me"
  - the message must contain one of: `told`, `sorry`, `already`
  - scored words: `told`(1.5), `sorry`(1.2), `already`(1.0), `work`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.again.respond.apologize   [23 chars]
    en  Sorry — you've told me.
    >>  ............................................
    pt  Desculpa — você já me contou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -2  _(recorded under topic `work.again.apologize`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — you've told me."
       spoken on: conversations.topic.work.again.respond, button `apologize`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.again.apologize.terminal`: the villager accepts. Subject `work.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.again.apologize/1   [52 chars]
    en  No harm. I like talking about it more than I let on.
    >>  ............................................
    pt  Sem problema. Gosto mais de falar disso do que demonstro.
    >>  ............................................
  dialogue.conversations.work.again.apologize/2   [34 chars]
    en  You did ask, aye. My answer keeps.
    >>  ............................................
    pt  Você perguntou, é. Minha resposta se conserva.
    >>  ............................................
  dialogue.conversations.work.again.apologize/3   [56 chars]
    en  It's alright, %1$s. It's the only subject I'm expert in.
    >>  ............................................
    pt  Tudo bem, %1$s. É o único assunto em que sou especialista.
    >>  ............................................
```


### Button `press` — "Tell me again anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `work.again.to.work.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.again.press` — accepted phrasings: "tell me again anyway"; "once more"; "go on, again"
  - the message must contain one of: `again`, `anyway`, `once`
  - scored words: `again`(1.5), `anyway`(1.2), `once`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.again.respond.press   [21 chars]
    en  Tell me again anyway.
    >>  ............................................
    pt  Me conta de novo mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `work.again.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `work.again.press`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.work.again.respond, button `press`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.again.press.terminal`: the villager resists. Subject `work.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.again.press/1   [39 chars]
    en  It's the same trade it was an hour ago.
    >>  ............................................
    pt  É o mesmo ofício de uma hora atrás.
    >>  ............................................
  dialogue.conversations.work.again.press/2   [57 chars]
    en  Twice? The work hasn't changed, %1$s, and neither have I.
    >>  ............................................
    pt  Duas vezes? O trabalho não mudou, %1$s, e nem eu.
    >>  ............................................
  dialogue.conversations.work.again.press/3   [43 chars]
    en  ...Fine. Same as before. Now let me get on.
    >>  ............................................
    pt  ...Tá. O mesmo de antes. Agora deixa eu trabalhar.
    >>  ............................................
```


### Button `leave` — "Fair. Another time."

*stance family `exit` · tone `plain` · answers the beat(s) `work.again.to.work.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.again.respond.leave   [19 chars]
    en  Fair. Another time.
    >>  ............................................
    pt  Justo. Outra hora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another time."
       spoken on: conversations.topic.work.again.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.again.leave.terminal`: the villager accepts. Subject `work.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.again.leave/1   [51 chars]
    en  Aye. Come back when I've something new to show you.
    >>  ............................................
    pt  Tá. Volte quando eu tiver algo novo para mostrar.
    >>  ............................................
  dialogue.conversations.work.again.leave/2   [10 chars]
    en  Go safely.
    >>  ............................................
    pt  Vá com cuidado.
    >>  ............................................
  dialogue.conversations.work.again.leave/3   [15 chars]
    en  Leave it there.
    >>  ............................................
    pt  Deixe por aí.
    >>  ............................................
```

---


## `conversations.topic.work.hate.followup`

**Reached from 3 route(s):** `conversations.topic.work.hate.respond` / `ask_why_stay`; `conversations.topic.work.hate.respond` / `sympathise`; `conversations.topic.work.hate.respond` / `keep_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.hate.keep_it` — e.g. "It had better. Half the folk here would have an opinion and none of them useful."
- `conversations.work.hate.sympathise` — e.g. "...It is. Nobody's called it heavy before. They call it lucky, mostly."
- `conversations.work.hate.why_stay` — e.g. "Because the alternative is a winter I'd not survive. That's the whole romance of it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hate.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hate.followup   [29 chars]
    en  That's the shape of it, then.
    >>  ............................................
    pt  É esse o formato da coisa, então.
    >>  ............................................
```


### Button `offer_idea` — "Have you tried it the other way?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.hate.ask_why_stay`, `work.hate.keep_it`, `work.hate.sympathise` · offered only once the villager has actually said `feeling:trapped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hate.offer_idea` — accepted phrasings: "have you tried it the other way"; "have you tried something different"; "try it another way"
  - the message must contain one of: `tried`, `other`, `different`
  - scored words: `tried`(1.5), `other`(1.0), `different`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.followup.offer_idea
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.followup.offer_idea   [32 chars]
    en  Have you tried it the other way?
    >>  ............................................
    pt  Você já tentou de outro jeito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.followup.offer_idea`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +1  _(recorded under topic `work.followup.offer_idea`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.followup.offer_idea
WHO    VILLAGER — what the player reads after pressing "Have you tried it the other way?"
       spoken on: conversations.topic.work.hate.followup, button `offer_idea`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.idea`: the villager accepts. Subject `work.any.dislike`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.followup.offer_idea/1   [54 chars]
    en  ...Huh. That'd work. Where were you three winters ago?
    >>  ............................................
    pt  ...Hm. Isso funcionaria. Onde você estava três invernos atrás?
    >>  ............................................
  dialogue.conversations.work.followup.offer_idea/2   [67 chars]
    en  I've done it my way so long I stopped looking. Say it again slowly.
    >>  ............................................
    pt  Faço do meu jeito há tanto tempo que parei de olhar. Repete devagar.
    >>  ............................................
  dialogue.conversations.work.followup.offer_idea/3   [59 chars]
    en  Not bad. I'll try it and blame you loudly if it goes wrong.
    >>  ............................................
    pt  Nada mal. Vou tentar e culpar você em voz alta se der errado.
    >>  ............................................
```


### Button `hear_burnout` — "It sounds like it's wearing you down."

*stance family `empathy` · tone `gentle` · outcome `appreciated`/`rebuffed` · answers the beat(s) `work.hate.ask_why_stay`, `work.hate.keep_it`, `work.hate.sympathise` · offered only once the villager has actually said `feeling:trapped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hate.burnout` — accepted phrasings: "it sounds like it is wearing you down"; "that sounds tiring"; "you sound worn out"
  - the message must contain one of: `wearing`, `tiring`, `worn`
  - scored words: `wearing`(1.5), `tiring`(1.5), `worn`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.followup.hear_burnout
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.followup.hear_burnout   [37 chars]
    en  It sounds like it's wearing you down.
    >>  ............................................
    pt  Parece que isso está te desgastando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `work.hear_burnout` lands on tier **crit** (axis respect, difficulty 35, stance curiosity)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `work.hear_burnout.crit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +6, trust +3  _(recorded under topic `work.hear_burnout`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hear_burnout.crit
WHO    VILLAGER — what the player reads after pressing "It sounds like it's wearing you down."
       spoken on: conversations.topic.work.hate.followup, button `hear_burnout`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.burnout.crit`: the villager discloses. Subject `work.any.dislike`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hear_burnout.crit/1   [90 chars]
    en  ...It is. Nobody asks the second question, and you just did. Sit down, I've things to say.
    >>  ............................................
    pt  ...É. Ninguém faz a segunda pergunta, e você acabou de fazer. Senta, tenho coisas a dizer.
    >>  ............................................
  dialogue.conversations.work.hear_burnout.crit/2   [80 chars]
    en  You've been watching me and not the work, %1$s. That's rarer than a good hammer.
    >>  ............................................
    pt  Você olhou pra mim e não pro trabalho, %1$s. Isso é mais raro que um bom martelo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did, and I'd stopped hoping anyone would.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer, e eu tinha parado de esperar.
    >>  ............................................
  anxious.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Sit down. I'll need a moment before I start and then I'll not stop for a while.
    >>  ............................................
    pt  É. Sente-se. Vou precisar de um momento antes de começar e depois não vou parar tão cedo.
    >>  ............................................
  athletic.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down; this isn't a short answer.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se; não é uma resposta curta.
    >>  ............................................
  athletic.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Second question. Take a seat — it's taken years to be worth asking and it'll take a while to answer.
    >>  ............................................
    pt  É. Segunda pergunta. Sente — levou anos pra valer a pena perguntar e vai levar um tempo pra responder.
    >>  ............................................
  confident.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer. Sente-se.
    >>  ............................................
  confident.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. That's the second question and I've been waiting years for somebody to ask it.
    >>  ............................................
    pt  É. Essa é a segunda pergunta e eu esperei anos por alguém que a fizesse.
    >>  ............................................
  crabby.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer. Sente-se.
    >>  ............................................
  crabby.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. That's the second question and I've been waiting years for somebody to ask it.
    >>  ............................................
    pt  É. Essa é a segunda pergunta e eu esperei anos por alguém que a fizesse.
    >>  ............................................
  extroverted.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, %1$s, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, %1$s, e você acabou de fazer. Sente-se.
    >>  ............................................
  extroverted.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one. That's why I'm about to tell you more than I planned.
    >>  ............................................
    pt  É. Você fez a segunda. É por isso que eu vou te contar mais do que eu planejava.
    >>  ............................................
  flirty.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, %1$s, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, %1$s, e você acabou de fazer. Sente-se.
    >>  ............................................
  flirty.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one. That's why I'm about to tell you more than I planned.
    >>  ............................................
    pt  É. Você fez a segunda. É por isso que eu vou te contar mais do que eu planejava.
    >>  ............................................
  friendly.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, %1$s, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, %1$s, e você acabou de fazer. Sente-se.
    >>  ............................................
  friendly.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one. That's why I'm about to tell you more than I planned.
    >>  ............................................
    pt  É. Você fez a segunda. É por isso que eu vou te contar mais do que eu planejava.
    >>  ............................................
  gloomy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did, and I'd stopped hoping anyone would.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer, e eu tinha parado de esperar.
    >>  ............................................
  gloomy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Sit down. I'll need a moment before I start and then I'll not stop for a while.
    >>  ............................................
    pt  É. Sente-se. Vou precisar de um momento antes de começar e depois não vou parar tão cedo.
    >>  ............................................
  greedy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer. Sente-se.
    >>  ............................................
  greedy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. That's the second question and I've been waiting years for somebody to ask it.
    >>  ............................................
    pt  É. Essa é a segunda pergunta e eu esperei anos por alguém que a fizesse.
    >>  ............................................
  grumpy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer. Sente-se.
    >>  ............................................
  grumpy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. That's the second question and I've been waiting years for somebody to ask it.
    >>  ............................................
    pt  É. Essa é a segunda pergunta e eu esperei anos por alguém que a fizesse.
    >>  ............................................
  introverted.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se.
    >>  ............................................
  introverted.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one.
    >>  ............................................
    pt  É. Você fez a segunda.
    >>  ............................................
  lazy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down; this isn't a short answer.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se; não é uma resposta curta.
    >>  ............................................
  lazy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Second question. Take a seat — it's taken years to be worth asking and it'll take a while to answer.
    >>  ............................................
    pt  É. Segunda pergunta. Sente — levou anos pra valer a pena perguntar e vai levar um tempo pra responder.
    >>  ............................................
  odd.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se.
    >>  ............................................
  odd.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one.
    >>  ............................................
    pt  É. Você fez a segunda.
    >>  ............................................
  peaceful.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down; this isn't a short answer.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se; não é uma resposta curta.
    >>  ............................................
  peaceful.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Second question. Take a seat — it's taken years to be worth asking and it'll take a while to answer.
    >>  ............................................
    pt  É. Segunda pergunta. Sente — levou anos pra valer a pena perguntar e vai levar um tempo pra responder.
    >>  ............................................
  peppy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is! Nobody asks the second question and you just did. Sit down, I've things to say.
    >>  ............................................
    pt  É! Ninguém faz a segunda pergunta e você acabou de fazer. Sente-se, eu tenho coisas a dizer.
    >>  ............................................
  peppy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. The second question! Nobody asks it. Pull up something to sit on.
    >>  ............................................
    pt  É. A segunda pergunta! Ninguém faz. Puxe algo pra sentar.
    >>  ............................................
  playful.dialogue.conversations.work.hear_burnout.crit/1
    en  It is! Nobody asks the second question and you just did. Sit down, I've things to say.
    >>  ............................................
    pt  É! Ninguém faz a segunda pergunta e você acabou de fazer. Sente-se, eu tenho coisas a dizer.
    >>  ............................................
  playful.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. The second question! Nobody asks it. Pull up something to sit on.
    >>  ............................................
    pt  É. A segunda pergunta! Ninguém faz. Puxe algo pra sentar.
    >>  ............................................
  relaxed.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down; this isn't a short answer.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se; não é uma resposta curta.
    >>  ............................................
  relaxed.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Second question. Take a seat — it's taken years to be worth asking and it'll take a while to answer.
    >>  ............................................
    pt  É. Segunda pergunta. Sente — levou anos pra valer a pena perguntar e vai levar um tempo pra responder.
    >>  ............................................
  sensitive.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question, and you just did, and I'd stopped hoping anyone would.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta, e você acabou de fazer, e eu tinha parado de esperar.
    >>  ............................................
  sensitive.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. Sit down. I'll need a moment before I start and then I'll not stop for a while.
    >>  ............................................
    pt  É. Sente-se. Vou precisar de um momento antes de começar e depois não vou parar tão cedo.
    >>  ............................................
  shy.dialogue.conversations.work.hear_burnout.crit/1
    en  It is. Nobody asks the second question. Sit down.
    >>  ............................................
    pt  É. Ninguém faz a segunda pergunta. Sente-se.
    >>  ............................................
  shy.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. You asked the second one.
    >>  ............................................
    pt  É. Você fez a segunda.
    >>  ............................................
  upbeat.dialogue.conversations.work.hear_burnout.crit/1
    en  It is! Nobody asks the second question and you just did. Sit down, I've things to say.
    >>  ............................................
    pt  É! Ninguém faz a segunda pergunta e você acabou de fazer. Sente-se, eu tenho coisas a dizer.
    >>  ............................................
  upbeat.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. The second question! Nobody asks it. Pull up something to sit on.
    >>  ............................................
    pt  É. A segunda pergunta! Ninguém faz. Puxe algo pra sentar.
    >>  ............................................
  witty.dialogue.conversations.work.hear_burnout.crit/1
    en  It is! Nobody asks the second question and you just did. Sit down, I've things to say.
    >>  ............................................
    pt  É! Ninguém faz a segunda pergunta e você acabou de fazer. Sente-se, eu tenho coisas a dizer.
    >>  ............................................
  witty.dialogue.conversations.work.hear_burnout.crit/2
    en  Aye. The second question! Nobody asks it. Pull up something to sit on.
    >>  ............................................
    pt  É. A segunda pergunta! Ninguém faz. Puxe algo pra sentar.
    >>  ............................................
```

</details>


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `work.hear_burnout` lands on tier **success** (axis respect, difficulty 35, stance curiosity)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `work.hear_burnout.success`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.hear_burnout`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hear_burnout.success
WHO    VILLAGER — what the player reads after pressing "It sounds like it's wearing you down."
       spoken on: conversations.topic.work.hate.followup, button `hear_burnout`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.burnout.success`: the villager accepts. Subject `work.any.dislike`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hear_burnout.success/1   [59 chars]
    en  ...Aye. It is. That's the first time I've said so out loud.
    >>  ............................................
    pt  ...É. É sim. É a primeira vez que eu digo em voz alta.
    >>  ............................................
  dialogue.conversations.work.hear_burnout.success/2   [57 chars]
    en  It wears. You noticed. Right — back to it, but thank you.
    >>  ............................................
    pt  Desgasta. Você reparou. Certo — de volta ao trabalho, mas obrigado.
    >>  ............................................
```


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `work.hear_burnout` lands on tier **partial** (axis respect, difficulty 35, stance curiosity)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `work.hear_burnout.partial`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +1  _(recorded under topic `work.hear_burnout`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hear_burnout.partial
WHO    VILLAGER — what the player reads after pressing "It sounds like it's wearing you down."
       spoken on: conversations.topic.work.hate.followup, button `hear_burnout`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.burnout.partial`: the villager qualifys. Subject `work.any.dislike`, polarity `neutral`, ends conversation, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hear_burnout.partial/1   [68 chars]
    en  Everything wears somebody down. That's not an insight, that's a job.
    >>  ............................................
    pt  Tudo desgasta alguém. Isso não é percepção, é emprego.
    >>  ............................................
  dialogue.conversations.work.hear_burnout.partial/2   [48 chars]
    en  Mm. It's work, %1$s. It's supposed to be tiring.
    >>  ............................................
    pt  Hm. É trabalho, %1$s. É para cansar mesmo.
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `work.hear_burnout` lands on tier **rebuff** (axis respect, difficulty 35, stance curiosity)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `work.hear_burnout.rebuff`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +4, respect -2  _(recorded under topic `work.hear_burnout`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hear_burnout.rebuff
WHO    VILLAGER — what the player reads after pressing "It sounds like it's wearing you down."
       spoken on: conversations.topic.work.hate.followup, button `hear_burnout`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.burnout.rebuff`: the villager refuses. Subject `work.any.dislike`, polarity `negative`, ends conversation, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hear_burnout.rebuff/1   [92 chars]
    en  Don't diagnose me across a workbench. I've been doing this longer than you've been watching.
    >>  ............................................
    pt  Não me diagnostique por cima da bancada. Faço isso há mais tempo do que você observa.
    >>  ............................................
  dialogue.conversations.work.hear_burnout.rebuff/2   [53 chars]
    en  Wearing me down. Right. Was that meant to be helpful?
    >>  ............................................
    pt  Me desgastando. Certo. Isso era para ajudar?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. If you say it out loud I'll have to look at it, %1$s.
    >>  ............................................
    pt  Não. Se você disser em voz alta eu vou ter que olhar pra isso, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Please don't name it. I've been managing it by not naming it.
    >>  ............................................
    pt  Por favor não dê nome. Eu venho lidando com isso justamente por não dar nome.
    >>  ............................................
  athletic.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. It's a season, not a state. It'll turn like the rest of them.
    >>  ............................................
    pt  Não. É uma estação, não um estado. Vai virar como as outras.
    >>  ............................................
  athletic.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I've been tired before and I'll be tired again. It passes.
    >>  ............................................
    pt  Não. Já estive cansado antes e vou estar de novo. Passa.
    >>  ............................................
  confident.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't diagnose me across a workbench. I've been doing this longer than you've been watching.
    >>  ............................................
    pt  Não me diagnostique por cima da bancada. Eu faço isso há mais tempo do que você observa.
    >>  ............................................
  confident.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I'm tired, not finished, and those are not the same word.
    >>  ............................................
    pt  Não. Eu estou cansado, não acabado, e não são a mesma palavra.
    >>  ............................................
  crabby.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't diagnose me across a workbench. I've been doing this longer than you've been watching.
    >>  ............................................
    pt  Não me diagnostique por cima da bancada. Eu faço isso há mais tempo do que você observa.
    >>  ............................................
  crabby.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I'm tired, not finished, and those are not the same word.
    >>  ............................................
    pt  Não. Eu estou cansado, não acabado, e não são a mesma palavra.
    >>  ............................................
  extroverted.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't, %1$s. I told you it was a hard week; I didn't ask to be looked at.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu disse que foi uma semana dura; não pedi pra ser examinado.
    >>  ............................................
  extroverted.dialogue.conversations.work.hear_burnout.rebuff/2
    en  That's kindly meant and it's too much. I'd rather you just sat a while.
    >>  ............................................
    pt  É bem-intencionado e é demais. Eu preferia que você só sentasse um pouco.
    >>  ............................................
  flirty.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't, %1$s. I told you it was a hard week; I didn't ask to be looked at.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu disse que foi uma semana dura; não pedi pra ser examinado.
    >>  ............................................
  flirty.dialogue.conversations.work.hear_burnout.rebuff/2
    en  That's kindly meant and it's too much. I'd rather you just sat a while.
    >>  ............................................
    pt  É bem-intencionado e é demais. Eu preferia que você só sentasse um pouco.
    >>  ............................................
  friendly.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't, %1$s. I told you it was a hard week; I didn't ask to be looked at.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu disse que foi uma semana dura; não pedi pra ser examinado.
    >>  ............................................
  friendly.dialogue.conversations.work.hear_burnout.rebuff/2
    en  That's kindly meant and it's too much. I'd rather you just sat a while.
    >>  ............................................
    pt  É bem-intencionado e é demais. Eu preferia que você só sentasse um pouco.
    >>  ............................................
  gloomy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. If you say it out loud I'll have to look at it, %1$s.
    >>  ............................................
    pt  Não. Se você disser em voz alta eu vou ter que olhar pra isso, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Please don't name it. I've been managing it by not naming it.
    >>  ............................................
    pt  Por favor não dê nome. Eu venho lidando com isso justamente por não dar nome.
    >>  ............................................
  greedy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't diagnose me across a workbench. I've been doing this longer than you've been watching.
    >>  ............................................
    pt  Não me diagnostique por cima da bancada. Eu faço isso há mais tempo do que você observa.
    >>  ............................................
  greedy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I'm tired, not finished, and those are not the same word.
    >>  ............................................
    pt  Não. Eu estou cansado, não acabado, e não são a mesma palavra.
    >>  ............................................
  grumpy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't diagnose me across a workbench. I've been doing this longer than you've been watching.
    >>  ............................................
    pt  Não me diagnostique por cima da bancada. Eu faço isso há mais tempo do que você observa.
    >>  ............................................
  grumpy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I'm tired, not finished, and those are not the same word.
    >>  ............................................
    pt  Não. Eu estou cansado, não acabado, e não são a mesma palavra.
    >>  ............................................
  introverted.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. Not across a workbench.
    >>  ............................................
    pt  Não. Não por cima da bancada.
    >>  ............................................
  introverted.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I said it was a hard week. That's all I said.
    >>  ............................................
    pt  Não. Eu disse que foi uma semana dura. Foi só isso.
    >>  ............................................
  lazy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. It's a season, not a state. It'll turn like the rest of them.
    >>  ............................................
    pt  Não. É uma estação, não um estado. Vai virar como as outras.
    >>  ............................................
  lazy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I've been tired before and I'll be tired again. It passes.
    >>  ............................................
    pt  Não. Já estive cansado antes e vou estar de novo. Passa.
    >>  ............................................
  odd.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. Not across a workbench.
    >>  ............................................
    pt  Não. Não por cima da bancada.
    >>  ............................................
  odd.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I said it was a hard week. That's all I said.
    >>  ............................................
    pt  Não. Eu disse que foi uma semana dura. Foi só isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. It's a season, not a state. It'll turn like the rest of them.
    >>  ............................................
    pt  Não. É uma estação, não um estado. Vai virar como as outras.
    >>  ............................................
  peaceful.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I've been tired before and I'll be tired again. It passes.
    >>  ............................................
    pt  Não. Já estive cansado antes e vou estar de novo. Passa.
    >>  ............................................
  peppy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Ah — no. One long week and you've written my obituary, %1$s.
    >>  ............................................
    pt  Ah — não. Uma semana longa e você já escreveu meu obituário, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Right, stop there. I'm tired on a Thursday. It happens to trades.
    >>  ............................................
    pt  Certo, pare aí. Estou cansado numa quinta. Acontece com ofícios.
    >>  ............................................
  playful.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Ah — no. One long week and you've written my obituary, %1$s.
    >>  ............................................
    pt  Ah — não. Uma semana longa e você já escreveu meu obituário, %1$s.
    >>  ............................................
  playful.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Right, stop there. I'm tired on a Thursday. It happens to trades.
    >>  ............................................
    pt  Certo, pare aí. Estou cansado numa quinta. Acontece com ofícios.
    >>  ............................................
  relaxed.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. It's a season, not a state. It'll turn like the rest of them.
    >>  ............................................
    pt  Não. É uma estação, não um estado. Vai virar como as outras.
    >>  ............................................
  relaxed.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I've been tired before and I'll be tired again. It passes.
    >>  ............................................
    pt  Não. Já estive cansado antes e vou estar de novo. Passa.
    >>  ............................................
  sensitive.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. If you say it out loud I'll have to look at it, %1$s.
    >>  ............................................
    pt  Não. Se você disser em voz alta eu vou ter que olhar pra isso, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Please don't name it. I've been managing it by not naming it.
    >>  ............................................
    pt  Por favor não dê nome. Eu venho lidando com isso justamente por não dar nome.
    >>  ............................................
  shy.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Don't. Not across a workbench.
    >>  ............................................
    pt  Não. Não por cima da bancada.
    >>  ............................................
  shy.dialogue.conversations.work.hear_burnout.rebuff/2
    en  No. I said it was a hard week. That's all I said.
    >>  ............................................
    pt  Não. Eu disse que foi uma semana dura. Foi só isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Ah — no. One long week and you've written my obituary, %1$s.
    >>  ............................................
    pt  Ah — não. Uma semana longa e você já escreveu meu obituário, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Right, stop there. I'm tired on a Thursday. It happens to trades.
    >>  ............................................
    pt  Certo, pare aí. Estou cansado numa quinta. Acontece com ofícios.
    >>  ............................................
  witty.dialogue.conversations.work.hear_burnout.rebuff/1
    en  Ah — no. One long week and you've written my obituary, %1$s.
    >>  ............................................
    pt  Ah — não. Uma semana longa e você já escreveu meu obituário, %1$s.
    >>  ............................................
  witty.dialogue.conversations.work.hear_burnout.rebuff/2
    en  Right, stop there. I'm tired on a Thursday. It happens to trades.
    >>  ............................................
    pt  Certo, pare aí. Estou cansado numa quinta. Acontece com ofícios.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +2** — decision id `work.followup.hear_burnout`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +5, warmth +2  _(recorded under topic `work.followup.hear_burnout`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.followup.hear_burnout
WHO    VILLAGER — what the player reads after pressing "It sounds like it's wearing you down."
       spoken on: conversations.topic.work.hate.followup, button `hear_burnout`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.burnout`: the villager discloses. Subject `work.any.dislike`, polarity `mixed`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.followup.hear_burnout/1   [80 chars]
    en  ...It is. Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  ...É. Hm. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  dialogue.conversations.work.followup.hear_burnout/2   [69 chars]
    en  Wearing me down. Aye, that's the word for it. I'd not found the word.
    >>  ............................................
    pt  Me desgastando. É, essa é a palavra. Eu não tinha achado a palavra.
    >>  ............................................
  dialogue.conversations.work.followup.hear_burnout/3   [79 chars]
    en  You've been watching, then. It's the work that gets seen, not the one doing it.
    >>  ............................................
    pt  Você andou observando, então. É o trabalho que se vê, não quem o faz.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Everyone else asks when it'll be finished, %1$s.
    >>  ............................................
    pt  ...É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. I'd not let myself say so until somebody else said it first.
    >>  ............................................
    pt  É. Eu não me deixava dizer até alguém dizer primeiro.
    >>  ............................................
  anxious.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye, it is. Give me a moment. That was closer to the bone than you meant.
    >>  ............................................
    pt  ...É, sim. Me dê um momento. Isso pegou mais fundo do que você queria.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.hear_burnout/2
    en  Aye, it is. It has been for a while, and it'll pass, and it's good to have it said.
    >>  ............................................
    pt  É, sim. Faz um tempo, e vai passar, e é bom ter sido dito.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.hear_burnout/3
    en  It is. Seasons do that. Thank you for noticing which one this is.
    >>  ............................................
    pt  É. Estações fazem isso. Obrigado por reparar em qual é esta.
    >>  ............................................
  confident.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  confident.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody has put it that way before. Go on.
    >>  ............................................
    pt  É. Ninguém tinha colocado assim antes. Continue.
    >>  ............................................
  confident.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye, it is. That's the first useful thing anybody's said about it.
    >>  ............................................
    pt  É, sim. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody has put it that way before. Go on.
    >>  ............................................
    pt  É. Ninguém tinha colocado assim antes. Continue.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye, it is. That's the first useful thing anybody's said about it.
    >>  ............................................
    pt  É, sim. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished, %1$s. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s. Certo — onde a gente estava.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. You asked the second question and nobody asks the second question.
    >>  ............................................
    pt  É. Você fez a segunda pergunta e ninguém faz a segunda pergunta.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Thank you for that. Sit down a minute, if you've got one.
    >>  ............................................
    pt  É. Obrigado por isso. Sente um minuto, se você tiver um.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished, %1$s. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s. Certo — onde a gente estava.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. You asked the second question and nobody asks the second question.
    >>  ............................................
    pt  É. Você fez a segunda pergunta e ninguém faz a segunda pergunta.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Thank you for that. Sit down a minute, if you've got one.
    >>  ............................................
    pt  É. Obrigado por isso. Sente um minuto, se você tiver um.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished, %1$s. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s. Certo — onde a gente estava.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. You asked the second question and nobody asks the second question.
    >>  ............................................
    pt  É. Você fez a segunda pergunta e ninguém faz a segunda pergunta.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Thank you for that. Sit down a minute, if you've got one.
    >>  ............................................
    pt  É. Obrigado por isso. Sente um minuto, se você tiver um.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Everyone else asks when it'll be finished, %1$s.
    >>  ............................................
    pt  ...É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. I'd not let myself say so until somebody else said it first.
    >>  ............................................
    pt  É. Eu não me deixava dizer até alguém dizer primeiro.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye, it is. Give me a moment. That was closer to the bone than you meant.
    >>  ............................................
    pt  ...É, sim. Me dê um momento. Isso pegou mais fundo do que você queria.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody has put it that way before. Go on.
    >>  ............................................
    pt  É. Ninguém tinha colocado assim antes. Continue.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye, it is. That's the first useful thing anybody's said about it.
    >>  ............................................
    pt  É, sim. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody has put it that way before. Go on.
    >>  ............................................
    pt  É. Ninguém tinha colocado assim antes. Continue.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye, it is. That's the first useful thing anybody's said about it.
    >>  ............................................
    pt  É, sim. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Right — where were we.
    >>  ............................................
    pt  ...É. Huh. Certo — onde a gente estava.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's asked that.
    >>  ............................................
    pt  É. Ninguém perguntou isso.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye. Right.
    >>  ............................................
    pt  ...É. Certo.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.hear_burnout/2
    en  Aye, it is. It has been for a while, and it'll pass, and it's good to have it said.
    >>  ............................................
    pt  É, sim. Faz um tempo, e vai passar, e é bom ter sido dito.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.hear_burnout/3
    en  It is. Seasons do that. Thank you for noticing which one this is.
    >>  ............................................
    pt  É. Estações fazem isso. Obrigado por reparar em qual é esta.
    >>  ............................................
  odd.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Right — where were we.
    >>  ............................................
    pt  ...É. Huh. Certo — onde a gente estava.
    >>  ............................................
  odd.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's asked that.
    >>  ............................................
    pt  É. Ninguém perguntou isso.
    >>  ............................................
  odd.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye. Right.
    >>  ............................................
    pt  ...É. Certo.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.hear_burnout/2
    en  Aye, it is. It has been for a while, and it'll pass, and it's good to have it said.
    >>  ............................................
    pt  É, sim. Faz um tempo, e vai passar, e é bom ter sido dito.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.hear_burnout/3
    en  It is. Seasons do that. Thank you for noticing which one this is.
    >>  ............................................
    pt  É. Estações fazem isso. Obrigado por reparar em qual é esta.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.hear_burnout/1
    en  It is! Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É! Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's said that. Everyone asks about the deadline, which is not the same question.
    >>  ............................................
    pt  É. Ninguém disse isso. Todos perguntam do prazo, que não é a mesma pergunta.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Huh. That landed somewhere. Give me a moment and we'll carry on.
    >>  ............................................
    pt  É. Huh. Isso pegou em algum lugar. Me dê um momento e a gente continua.
    >>  ............................................
  playful.dialogue.conversations.work.followup.hear_burnout/1
    en  It is! Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É! Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  playful.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's said that. Everyone asks about the deadline, which is not the same question.
    >>  ............................................
    pt  É. Ninguém disse isso. Todos perguntam do prazo, que não é a mesma pergunta.
    >>  ............................................
  playful.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Huh. That landed somewhere. Give me a moment and we'll carry on.
    >>  ............................................
    pt  É. Huh. Isso pegou em algum lugar. Me dê um momento e a gente continua.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.hear_burnout/1
    en  It is. Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É. Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.hear_burnout/2
    en  Aye, it is. It has been for a while, and it'll pass, and it's good to have it said.
    >>  ............................................
    pt  É, sim. Faz um tempo, e vai passar, e é bom ter sido dito.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.hear_burnout/3
    en  It is. Seasons do that. Thank you for noticing which one this is.
    >>  ............................................
    pt  É. Estações fazem isso. Obrigado por reparar em qual é esta.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Everyone else asks when it'll be finished, %1$s.
    >>  ............................................
    pt  ...É. Huh. Todo mundo pergunta quando vai ficar pronto, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. I'd not let myself say so until somebody else said it first.
    >>  ............................................
    pt  É. Eu não me deixava dizer até alguém dizer primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye, it is. Give me a moment. That was closer to the bone than you meant.
    >>  ............................................
    pt  ...É, sim. Me dê um momento. Isso pegou mais fundo do que você queria.
    >>  ............................................
  shy.dialogue.conversations.work.followup.hear_burnout/1
    en  ...It is. Huh. Right — where were we.
    >>  ............................................
    pt  ...É. Huh. Certo — onde a gente estava.
    >>  ............................................
  shy.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's asked that.
    >>  ............................................
    pt  É. Ninguém perguntou isso.
    >>  ............................................
  shy.dialogue.conversations.work.followup.hear_burnout/3
    en  ...Aye. Right.
    >>  ............................................
    pt  ...É. Certo.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.hear_burnout/1
    en  It is! Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É! Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's said that. Everyone asks about the deadline, which is not the same question.
    >>  ............................................
    pt  É. Ninguém disse isso. Todos perguntam do prazo, que não é a mesma pergunta.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Huh. That landed somewhere. Give me a moment and we'll carry on.
    >>  ............................................
    pt  É. Huh. Isso pegou em algum lugar. Me dê um momento e a gente continua.
    >>  ............................................
  witty.dialogue.conversations.work.followup.hear_burnout/1
    en  It is! Huh. Everyone else asks when it'll be finished. Right — where were we.
    >>  ............................................
    pt  É! Huh. Todo mundo pergunta quando vai ficar pronto. Certo — onde a gente estava.
    >>  ............................................
  witty.dialogue.conversations.work.followup.hear_burnout/2
    en  It is. Nobody's said that. Everyone asks about the deadline, which is not the same question.
    >>  ............................................
    pt  É. Ninguém disse isso. Todos perguntam do prazo, que não é a mesma pergunta.
    >>  ............................................
  witty.dialogue.conversations.work.followup.hear_burnout/3
    en  Aye. Huh. That landed somewhere. Give me a moment and we'll carry on.
    >>  ............................................
    pt  É. Huh. Isso pegou em algum lugar. Me dê um momento e a gente continua.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you work."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hate.ask_why_stay`, `work.hate.keep_it`, `work.hate.sympathise` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.followup.leave   [18 chars]
    en  I'll let you work.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hate.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you work."
       spoken on: conversations.topic.work.hate.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.left`: the villager accepts. Subject `work.any.dislike`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.hate.respond / leave
```

```text
  dialogue.conversations.work.hate.leave/1   [16 chars]
    en  Aye. Off you go.
    >>  ............................................
    pt  É. Pode ir.
    >>  ............................................
  dialogue.conversations.work.hate.leave/2   [16 chars]
    en  Safe home, %1$s.
    >>  ............................................
    pt  Volte bem, %1$s.
    >>  ............................................
  dialogue.conversations.work.hate.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.work.hate.respond`

**Reached from 1 route(s):** `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.hate` — e.g. "Between us? Some mornings I stare at the ceiling and think about just... walking."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hate.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hate.respond   [40 chars]
    en  ...I've never said that out loud before.
    >>  ............................................
    pt  ...Eu nunca disse isso em voz alta antes.
    >>  ............................................
```


### Button `ask_why_stay` — "Then why stay at it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hate.open` · offered only once the villager has actually said `feeling:trapped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hate.ask_why_stay` — accepted phrasings: "then why stay at it"; "why not quit"; "why do you stay"
  - the message must contain one of: `stay`, `quit`
  - scored words: `stay`(1.5), `quit`(1.2), `leave`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.respond.ask_why_stay
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.respond.ask_why_stay   [20 chars]
    en  Then why stay at it?
    >>  ............................................
    pt  Então por que continuar nisso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +2  _(recorded under topic `work.hate.ask_why_stay`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hate.followup`
- …where the player's next choices will be: "Have you tried it the other way?" | "It sounds like it's wearing you down." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.hate.why_stay
WHO    VILLAGER — what the player reads after pressing "Then why stay at it?"
       spoken on: conversations.topic.work.hate.respond, button `ask_why_stay`
       leaves the player on: conversations.topic.work.hate.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.ask_why_stay`: the villager explains. Subject `work.any.dislike`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:any`, `feeling:trapped` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hate.why_stay/1   [84 chars]
    en  Because the alternative is a winter I'd not survive. That's the whole romance of it.
    >>  ............................................
    pt  Porque a alternativa é um inverno que eu não sobreviveria. É todo o romantismo da coisa.
    >>  ............................................
  dialogue.conversations.work.hate.why_stay/2   [64 chars]
    en  Where would I go, %1$s? A trade is a door you walk through once.
    >>  ............................................
    pt  Pra onde eu iria, %1$s? Um ofício é uma porta que você atravessa uma vez só.
    >>  ............................................
  dialogue.conversations.work.hate.why_stay/3   [66 chars]
    en  Habit. Habit, and being good at it, which is its own kind of trap.
    >>  ............................................
    pt  Costume. Costume, e ser bom nisso, que é outro tipo de armadilha.
    >>  ............................................
```


### Button `sympathise` — "That's a heavy thing to carry every morning."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hate.open` · offered only once the villager has actually said `feeling:trapped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hate.sympathise` — accepted phrasings: "that is a heavy thing to carry every morning"; "that sounds heavy to carry"; "that is a lot to carry"
  - the message must contain one of: `heavy`, `carry`, `morning`
  - scored words: `heavy`(1.5), `carry`(1.2), `morning`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.respond.sympathise   [44 chars]
    en  That's a heavy thing to carry every morning.
    >>  ............................................
    pt  É uma coisa pesada de carregar toda manhã.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hate.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +4  _(recorded under topic `work.hate.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hate.followup`
- …where the player's next choices will be: "Have you tried it the other way?" | "It sounds like it's wearing you down." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.hate.sympathise
WHO    VILLAGER — what the player reads after pressing "That's a heavy thing to carry every morning."
       spoken on: conversations.topic.work.hate.respond, button `sympathise`
       leaves the player on: conversations.topic.work.hate.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.sympathise`: the villager accepts. Subject `work.any.dislike`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:any`, `feeling:trapped` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hate.sympathise/1   [70 chars]
    en  ...It is. Nobody's called it heavy before. They call it lucky, mostly.
    >>  ............................................
    pt  ...É. Ninguém nunca chamou de pesado antes. Costumam chamar de sorte.
    >>  ............................................
  dialogue.conversations.work.hate.sympathise/2   [91 chars]
    en  Aye. And I carry it, and I'll carry it tomorrow. Thank you for calling it what it is, %1$s.
    >>  ............................................
    pt  É. E eu carrego, e vou carregar amanhã. Obrigado por chamar do que é, %1$s.
    >>  ............................................
  dialogue.conversations.work.hate.sympathise/3   [66 chars]
    en  Heavy. That is the word I have been avoiding for about four years.
    >>  ............................................
    pt  Pesado. É a palavra que eu venho evitando faz uns quatro anos.
    >>  ............................................
```


### Button `keep_it` — "That stays between us."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hate.open` · offered only once the villager has actually said `feeling:trapped`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hate.keep_it` — accepted phrasings: "that stays between us"; "i will not repeat it"; "your secret is safe"
  - the message must contain one of: `between`, `secret`, `repeat`
  - scored words: `between`(1.5), `secret`(1.2), `repeat`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.respond.keep_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.respond.keep_it   [22 chars]
    en  That stays between us.
    >>  ............................................
    pt  Isso fica entre nós.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hate.keep_it`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +5  _(recorded under topic `work.hate.keep_it`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hate.followup`
- …where the player's next choices will be: "Have you tried it the other way?" | "It sounds like it's wearing you down." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.hate.keep_it
WHO    VILLAGER — what the player reads after pressing "That stays between us."
       spoken on: conversations.topic.work.hate.respond, button `keep_it`
       leaves the player on: conversations.topic.work.hate.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.keep_it`: the villager accepts. Subject `work.any.dislike`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:any`, `feeling:trapped` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hate.keep_it/1   [80 chars]
    en  It had better. Half the folk here would have an opinion and none of them useful.
    >>  ............................................
    pt  É bom que fique. Metade do vilarejo teria opinião e nenhuma útil.
    >>  ............................................
  dialogue.conversations.work.hate.keep_it/2   [65 chars]
    en  Thank you. I'd rather be a grumbler than a cautionary tale, %1$s.
    >>  ............................................
    pt  Obrigado. Prefiro ser um resmungão a virar exemplo do que não fazer, %1$s.
    >>  ............................................
  dialogue.conversations.work.hate.keep_it/3   [75 chars]
    en  Good. I have watched what this place does with a man who says he wants out.
    >>  ............................................
    pt  Bom. Já vi o que este lugar faz com um homem que diz que quer sair.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hate.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hate.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hate.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hate.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hate.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hate.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.left`: the villager accepts. Subject `work.any.dislike`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.hate.followup / leave
```

> Written out in full under **`conversations.topic.work.hate.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.pride.followup`

**Reached from 6 route(s):** `conversations.topic.work.pride.respond` / `praise`; `conversations.topic.work.pride.respond` / `praise`; `conversations.topic.work.pride.respond` / `praise`; `conversations.topic.work.pride.respond` / `ask_why`; `conversations.topic.work.pride.respond` / `ask_why`; `conversations.topic.work.pride.respond` / `blunt`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.ask_why.harvest` — e.g. "Why? In %2$s you don't ask why. You ask whether it's in yet, and it isn't."
- `conversations.work.blunt` — e.g. "...I would, wouldn't I. It's what you say about your own trade. ...Ask me again and I'll try harder."
- `conversations.work.praise.high` — e.g. "Ha! You've caught me on a good day and I'll take every word of that."
- `conversations.work.praise.storm` — e.g. "Kind of you to say it standing out in this. Get under something."
- `conversations.work.respond.ask_why` — e.g. "Because somebody has to, and because it's mine. That's most of it."
- `conversations.work.respond.praise` — e.g. "...Am I? Twenty years and nobody's said it in those words. Thank you."


```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.pride.followup   [26 chars]
    en  So that's how the days go.
    >>  ............................................
    pt  É assim que os dias passam.
    >>  ............................................
```


### Button `belittle` — "Anyone could do that job."

*stance family `dismissal` · tone `hostile` · outcome `rebuffed` · answers the beat(s) `work.pride.conceded`, `work.pride.praised`, `work.pride.praised.high`, `work.pride.praised.storm`, `work.pride.reason`, `work.pride.reason.harvest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.pride.belittle` — accepted phrasings: "anyone could do that"; "that looks easy"; "that is simple enough"
  - the message must contain one of: `anyone`, `easy`, `simple`
  - scored words: `anyone`(1.5), `easy`(1.2), `simple`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.followup.belittle
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.followup.belittle   [25 chars]
    en  Anyone could do that job.
    >>  ............................................
    pt  Qualquer um faria esse trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `work.followup.belittle`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -5, tension +6, warmth -2  _(recorded under topic `work.followup.belittle`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.followup.belittle
WHO    VILLAGER — what the player reads after pressing "Anyone could do that job."
       spoken on: conversations.topic.work.pride.followup, button `belittle`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.belittled`: the villager refuses. Subject `work.any.pride`, polarity `negative`, ends conversation, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.followup.belittle/1   [54 chars]
    en  Anyone could. Nobody does. There's a difference, %1$s.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. Tem diferença, %1$s.
    >>  ............................................
  dialogue.conversations.work.followup.belittle/2   [39 chars]
    en  Then take the tools. Go on. I'll watch.
    >>  ............................................
    pt  Então pega as ferramentas. Vai. Eu assisto.
    >>  ............................................
  dialogue.conversations.work.followup.belittle/3   [65 chars]
    en  ...Right. I'll remember that next time you need something mended.
    >>  ............................................
    pt  ...Certo. Vou lembrar disso da próxima vez que precisar consertar algo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does. It's the only argument I've got, %1$s.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz. É o único argumento que eu tenho, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.followup.belittle/2
    en  Right. Yes. It's small work. I've been told that most of my life.
    >>  ............................................
    pt  Certo. Sim. É trabalho pequeno. Me dizem isso quase a vida toda.
    >>  ............................................
  anxious.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not talk about it again. It's easier that way.
    >>  ............................................
    pt  ...Não falo mais disso. É mais fácil assim.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. It's been that way a long while.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. É assim faz tempo.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.belittle/2
    en  ...Aye, it's not clever work. It's the work that's there and somebody does it.
    >>  ............................................
    pt  ...É, não é trabalho esperto. É o trabalho que existe e alguém faz.
    >>  ............................................
  athletic.dialogue.conversations.work.followup.belittle/3
    en  Right. It'll still want doing tomorrow whatever we call it.
    >>  ............................................
    pt  Certo. Vai continuar precisando ser feito amanhã, chamemos como chamarmos.
    >>  ............................................
  confident.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. There's a difference.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. Tem diferença.
    >>  ............................................
  confident.dialogue.conversations.work.followup.belittle/2
    en  Right. Then you do it for a season and tell me how it went.
    >>  ............................................
    pt  Certo. Então faça você por uma estação e me diga como foi.
    >>  ............................................
  confident.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not defend my trade to somebody who's never held the tool.
    >>  ............................................
    pt  ...Não vou defender meu ofício pra quem nunca segurou a ferramenta.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. There's a difference.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. Tem diferença.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.belittle/2
    en  Right. Then you do it for a season and tell me how it went.
    >>  ............................................
    pt  Certo. Então faça você por uma estação e me diga como foi.
    >>  ............................................
  crabby.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not defend my trade to somebody who's never held the tool.
    >>  ............................................
    pt  ...Não vou defender meu ofício pra quem nunca segurou a ferramenta.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does, %1$s. That's the whole difference.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz, %1$s. É toda a diferença.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.belittle/2
    en  I'd show you what's actually in it, if you'd rather ask than decide.
    >>  ............................................
    pt  Eu te mostraria o que tem de verdade nisso, se você preferisse perguntar a decidir.
    >>  ............................................
  extroverted.dialogue.conversations.work.followup.belittle/3
    en  ...Right. I'll not go on about it. It matters to me, mind.
    >>  ............................................
    pt  ...Certo. Não vou insistir. Mas me importa, veja bem.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does, %1$s. That's the whole difference.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz, %1$s. É toda a diferença.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.belittle/2
    en  I'd show you what's actually in it, if you'd rather ask than decide.
    >>  ............................................
    pt  Eu te mostraria o que tem de verdade nisso, se você preferisse perguntar a decidir.
    >>  ............................................
  flirty.dialogue.conversations.work.followup.belittle/3
    en  ...Right. I'll not go on about it. It matters to me, mind.
    >>  ............................................
    pt  ...Certo. Não vou insistir. Mas me importa, veja bem.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does, %1$s. That's the whole difference.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz, %1$s. É toda a diferença.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.belittle/2
    en  I'd show you what's actually in it, if you'd rather ask than decide.
    >>  ............................................
    pt  Eu te mostraria o que tem de verdade nisso, se você preferisse perguntar a decidir.
    >>  ............................................
  friendly.dialogue.conversations.work.followup.belittle/3
    en  ...Right. I'll not go on about it. It matters to me, mind.
    >>  ............................................
    pt  ...Certo. Não vou insistir. Mas me importa, veja bem.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does. It's the only argument I've got, %1$s.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz. É o único argumento que eu tenho, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.belittle/2
    en  Right. Yes. It's small work. I've been told that most of my life.
    >>  ............................................
    pt  Certo. Sim. É trabalho pequeno. Me dizem isso quase a vida toda.
    >>  ............................................
  gloomy.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not talk about it again. It's easier that way.
    >>  ............................................
    pt  ...Não falo mais disso. É mais fácil assim.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. There's a difference.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. Tem diferença.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.belittle/2
    en  Right. Then you do it for a season and tell me how it went.
    >>  ............................................
    pt  Certo. Então faça você por uma estação e me diga como foi.
    >>  ............................................
  greedy.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not defend my trade to somebody who's never held the tool.
    >>  ............................................
    pt  ...Não vou defender meu ofício pra quem nunca segurou a ferramenta.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. There's a difference.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. Tem diferença.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.belittle/2
    en  Right. Then you do it for a season and tell me how it went.
    >>  ............................................
    pt  Certo. Então faça você por uma estação e me diga como foi.
    >>  ............................................
  grumpy.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not defend my trade to somebody who's never held the tool.
    >>  ............................................
    pt  ...Não vou defender meu ofício pra quem nunca segurou a ferramenta.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.belittle/2
    en  That's not the same thing.
    >>  ............................................
    pt  Não é a mesma coisa.
    >>  ............................................
  introverted.dialogue.conversations.work.followup.belittle/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. It's been that way a long while.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. É assim faz tempo.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.belittle/2
    en  ...Aye, it's not clever work. It's the work that's there and somebody does it.
    >>  ............................................
    pt  ...É, não é trabalho esperto. É o trabalho que existe e alguém faz.
    >>  ............................................
  lazy.dialogue.conversations.work.followup.belittle/3
    en  Right. It'll still want doing tomorrow whatever we call it.
    >>  ............................................
    pt  Certo. Vai continuar precisando ser feito amanhã, chamemos como chamarmos.
    >>  ............................................
  odd.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz.
    >>  ............................................
  odd.dialogue.conversations.work.followup.belittle/2
    en  That's not the same thing.
    >>  ............................................
    pt  Não é a mesma coisa.
    >>  ............................................
  odd.dialogue.conversations.work.followup.belittle/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. It's been that way a long while.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. É assim faz tempo.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.belittle/2
    en  ...Aye, it's not clever work. It's the work that's there and somebody does it.
    >>  ............................................
    pt  ...É, não é trabalho esperto. É o trabalho que existe e alguém faz.
    >>  ............................................
  peaceful.dialogue.conversations.work.followup.belittle/3
    en  Right. It'll still want doing tomorrow whatever we call it.
    >>  ............................................
    pt  Certo. Vai continuar precisando ser feito amanhã, chamemos como chamarmos.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.belittle/1
    en  Anyone could! Nobody does. That's my whole business model, %1$s.
    >>  ............................................
    pt  Qualquer um poderia! Ninguém faz. É todo o meu modelo de negócio, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.belittle/2
    en  Right! Easy. Come and do a Tuesday with me and we'll compare notes.
    >>  ............................................
    pt  Certo! Fácil. Venha fazer uma terça comigo e a gente compara.
    >>  ............................................
  peppy.dialogue.conversations.work.followup.belittle/3
    en  ...Ha. Yes. Anyone could. And yet here I am, doing it.
    >>  ............................................
    pt  ...Ha. Sim. Qualquer um poderia. E no entanto aqui estou eu, fazendo.
    >>  ............................................
  playful.dialogue.conversations.work.followup.belittle/1
    en  Anyone could! Nobody does. That's my whole business model, %1$s.
    >>  ............................................
    pt  Qualquer um poderia! Ninguém faz. É todo o meu modelo de negócio, %1$s.
    >>  ............................................
  playful.dialogue.conversations.work.followup.belittle/2
    en  Right! Easy. Come and do a Tuesday with me and we'll compare notes.
    >>  ............................................
    pt  Certo! Fácil. Venha fazer uma terça comigo e a gente compara.
    >>  ............................................
  playful.dialogue.conversations.work.followup.belittle/3
    en  ...Ha. Yes. Anyone could. And yet here I am, doing it.
    >>  ............................................
    pt  ...Ha. Sim. Qualquer um poderia. E no entanto aqui estou eu, fazendo.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.belittle/1
    en  Anyone could. Nobody does. It's been that way a long while.
    >>  ............................................
    pt  Qualquer um poderia. Ninguém faz. É assim faz tempo.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.belittle/2
    en  ...Aye, it's not clever work. It's the work that's there and somebody does it.
    >>  ............................................
    pt  ...É, não é trabalho esperto. É o trabalho que existe e alguém faz.
    >>  ............................................
  relaxed.dialogue.conversations.work.followup.belittle/3
    en  Right. It'll still want doing tomorrow whatever we call it.
    >>  ............................................
    pt  Certo. Vai continuar precisando ser feito amanhã, chamemos como chamarmos.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does. It's the only argument I've got, %1$s.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz. É o único argumento que eu tenho, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.belittle/2
    en  Right. Yes. It's small work. I've been told that most of my life.
    >>  ............................................
    pt  Certo. Sim. É trabalho pequeno. Me dizem isso quase a vida toda.
    >>  ............................................
  sensitive.dialogue.conversations.work.followup.belittle/3
    en  ...I'll not talk about it again. It's easier that way.
    >>  ............................................
    pt  ...Não falo mais disso. É mais fácil assim.
    >>  ............................................
  shy.dialogue.conversations.work.followup.belittle/1
    en  ...Anyone could. Nobody does.
    >>  ............................................
    pt  ...Qualquer um poderia. Ninguém faz.
    >>  ............................................
  shy.dialogue.conversations.work.followup.belittle/2
    en  That's not the same thing.
    >>  ............................................
    pt  Não é a mesma coisa.
    >>  ............................................
  shy.dialogue.conversations.work.followup.belittle/3
    en  ...Right. Leave it.
    >>  ............................................
    pt  ...Certo. Deixe.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.belittle/1
    en  Anyone could! Nobody does. That's my whole business model, %1$s.
    >>  ............................................
    pt  Qualquer um poderia! Ninguém faz. É todo o meu modelo de negócio, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.belittle/2
    en  Right! Easy. Come and do a Tuesday with me and we'll compare notes.
    >>  ............................................
    pt  Certo! Fácil. Venha fazer uma terça comigo e a gente compara.
    >>  ............................................
  upbeat.dialogue.conversations.work.followup.belittle/3
    en  ...Ha. Yes. Anyone could. And yet here I am, doing it.
    >>  ............................................
    pt  ...Ha. Sim. Qualquer um poderia. E no entanto aqui estou eu, fazendo.
    >>  ............................................
  witty.dialogue.conversations.work.followup.belittle/1
    en  Anyone could! Nobody does. That's my whole business model, %1$s.
    >>  ............................................
    pt  Qualquer um poderia! Ninguém faz. É todo o meu modelo de negócio, %1$s.
    >>  ............................................
  witty.dialogue.conversations.work.followup.belittle/2
    en  Right! Easy. Come and do a Tuesday with me and we'll compare notes.
    >>  ............................................
    pt  Certo! Fácil. Venha fazer uma terça comigo e a gente compara.
    >>  ............................................
  witty.dialogue.conversations.work.followup.belittle/3
    en  ...Ha. Yes. Anyone could. And yet here I am, doing it.
    >>  ............................................
    pt  ...Ha. Sim. Qualquer um poderia. E no entanto aqui estou eu, fazendo.
    >>  ............................................
```

</details>


### Button `joke` — "So you're the reason it all still works."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `work.pride.conceded`, `work.pride.praised`, `work.pride.praised.high`, `work.pride.praised.storm`, `work.pride.reason`, `work.pride.reason.harvest` · offered only once the villager has actually said `work:any`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.pride.joke` — accepted phrasings: "so you are the reason it all still works"; "you are why it still works"; "the whole thing works because of you"
  - the message must contain one of: `reason`, `works`
  - scored words: `reason`(1.5), `works`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.followup.joke   [40 chars]
    en  So you're the reason it all still works.
    >>  ............................................
    pt  Então é você que faz tudo continuar funcionando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.followup.joke`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, tension -2  _(recorded under topic `work.followup.joke`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.followup.joke
WHO    VILLAGER — what the player reads after pressing "So you're the reason it all still works."
       spoken on: conversations.topic.work.pride.followup, button `joke`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.joked`: the villager accepts. Subject `work.any.pride`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.followup.joke/1   [72 chars]
    en  Ha. Don't tell the others. They think it holds itself up out of respect.
    >>  ............................................
    pt  Ha. Não conta para os outros. Eles acham que se sustenta sozinho por respeito.
    >>  ............................................
  dialogue.conversations.work.followup.joke/2   [72 chars]
    en  I am, and nobody will notice until the day I stop. That's the job, %1$s.
    >>  ............................................
    pt  Sou, e ninguém vai reparar até o dia em que eu parar. É esse o trabalho, %1$s.
    >>  ............................................
  dialogue.conversations.work.followup.joke/3   [61 chars]
    en  The reason, aye. Also the reason it breaks. Both true, sadly.
    >>  ............................................
    pt  O motivo, é. E também o motivo de quebrar. As duas coisas, infelizmente.
    >>  ............................................
```


### Button `leave` — "I'll let you work."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.pride.conceded`, `work.pride.praised`, `work.pride.praised.high`, `work.pride.praised.storm`, `work.pride.reason`, `work.pride.reason.harvest` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.followup.leave   [18 chars]
    en  I'll let you work.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you work."
       spoken on: conversations.topic.work.pride.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.left`: the villager accepts. Subject `work.any.pride`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.pride.respond / leave
```

```text
  dialogue.conversations.work.respond.leave/1   [24 chars]
    en  Aye. It won't do itself.
    >>  ............................................
    pt  É. Não vai se fazer sozinho.
    >>  ............................................
  dialogue.conversations.work.respond.leave/2   [42 chars]
    en  Right you are. Mind the tools by the door.
    >>  ............................................
    pt  Isso mesmo. Cuidado com as ferramentas perto da porta.
    >>  ............................................
  dialogue.conversations.work.respond.leave/3   [21 chars]
    en  Leave it there, %1$s.
    >>  ............................................
    pt  Deixe por aí, %1$s.
    >>  ............................................
```

---


## `conversations.topic.work.pride.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.generic` — e.g. "Being a %2$s isn't glamorous, but it's mine, and I'm better at it than most."
- `conversations.work.like` — e.g. "I do, most days. There's a rhythm to it. You stop noticing the blisters."


```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.pride.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.pride.respond   [25 chars]
    en  That's the trade, anyway.
    >>  ............................................
    pt  É o ofício, enfim.
    >>  ............................................
```


### Button `praise` — "You're good at what you do."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.pride.likes_it`, `work.pride.templated` · offered only once the villager has actually said `work:any`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.pride.praise` — accepted phrasings: "you are good at what you do"; "you know your craft"; "you are skilled at it"
  - the message must contain one of: `skilled`, `craft`, `master`
  - scored words: `good`(0.8), `skilled`(1.5), `craft`(1.5), `master`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.respond.praise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.respond.praise   [27 chars]
    en  You're good at what you do.
    >>  ............................................
    pt  Você é bom no que faz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the sky is `storm`
- Fires when: RULED OUT when the `world` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `work.respond.praise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, warmth +1  _(recorded under topic `work.respond.praise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.praise.storm
WHO    VILLAGER — what the player reads after pressing "You're good at what you do."
       spoken on: conversations.topic.work.pride.respond, button `praise`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.praised.storm`: the villager accepts. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.praise.storm/1   [64 chars]
    en  Kind of you to say it standing out in this. Get under something.
    >>  ............................................
    pt  Gentil da sua parte dizer isso parado nessa chuva. Vá se abrigar.
    >>  ............................................
  dialogue.conversations.work.praise.storm/2   [56 chars]
    en  Praise in a downpour counts double, %1$s. Now go inside.
    >>  ............................................
    pt  Elogio em temporal vale em dobro, %1$s. Agora entre.
    >>  ............................................
  dialogue.conversations.work.praise.storm/3   [71 chars]
    en  Ha! You've picked a filthy afternoon to be complimentary. I'll take it.
    >>  ............................................
    pt  Ha! Você escolheu uma tarde imunda para ser gentil. Eu aceito.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: RULED OUT when the sky is `storm`  _(chance -2000)_
- Fires when: weighted +100 when the mood is `overjoyed`
- Does: **hearts +2** — decision id `work.respond.praise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, warmth +1  _(recorded under topic `work.respond.praise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.praise.high
WHO    VILLAGER — what the player reads after pressing "You're good at what you do."
       spoken on: conversations.topic.work.pride.respond, button `praise`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.praised.high`: the villager celebrates. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.praise.high/1   [68 chars]
    en  Ha! You've caught me on a good day and I'll take every word of that.
    >>  ............................................
    pt  Ha! Você me pegou num dia bom e eu aceito cada palavra.
    >>  ............................................
  dialogue.conversations.work.praise.high/2   [87 chars]
    en  It has gone well, hasn't it. I'd not have said so first, but since you did, %1$s — aye!
    >>  ............................................
    pt  Correu bem mesmo, né. Eu não teria dito primeiro, mas já que você disse, %1$s — isso!
    >>  ............................................
  dialogue.conversations.work.praise.high/3   [82 chars]
    en  Right you are, and don't stop there. Days like this I can take a great deal of it.
    >>  ............................................
    pt  Isso mesmo, e não pare aí. Em dias assim eu aguento bastante disso.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `overjoyed`  _(chance -2000)_
- Does: **hearts +2** — decision id `work.respond.praise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, warmth +1  _(recorded under topic `work.respond.praise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.respond.praise
WHO    VILLAGER — what the player reads after pressing "You're good at what you do."
       spoken on: conversations.topic.work.pride.respond, button `praise`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.praised`: the villager accepts. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.respond.praise/1   [69 chars]
    en  ...Am I? Twenty years and nobody's said it in those words. Thank you.
    >>  ............................................
    pt  ...Sou? Vinte anos e ninguém disse com essas palavras. Obrigado.
    >>  ............................................
  dialogue.conversations.work.respond.praise/2   [81 chars]
    en  You'd be the first to notice. It's the joint people see, never the fitting of it.
    >>  ............................................
    pt  Você seria o primeiro a reparar. As pessoas veem o encaixe, nunca o encaixar.
    >>  ............................................
  dialogue.conversations.work.respond.praise/3   [64 chars]
    en  I am, as it happens. It's good to be told once in a while, %1$s.
    >>  ............................................
    pt  Eu sou, por acaso. É bom ouvir isso de vez em quando, %1$s.
    >>  ............................................
```


### Button `ask_why` — "Why does it matter to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.pride.likes_it`, `work.pride.templated` · offered only once the villager has actually said `work:any`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.pride.ask_why` — accepted phrasings: "why does it matter to you"; "what does it mean to you"; "why do you do it"
  - the message must contain one of: `matter`, `means`
  - scored words: `matter`(1.5), `why`(0.8), `means`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.respond.ask_why
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.respond.ask_why   [26 chars]
    en  Why does it matter to you?
    >>  ............................................
    pt  Por que isso importa pra você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the season is `autumn`
- Fires when: RULED OUT when the `seasons` feature is OFF  _(chance -2000)_
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.respond.ask_why`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.ask_why.harvest
WHO    VILLAGER — what the player reads after pressing "Why does it matter to you?"
       spoken on: conversations.topic.work.pride.respond, button `ask_why`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = season
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.reason.harvest`: the villager explains. Subject `work.any.pride`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.ask_why.harvest/1   [74 chars]
    en  Why? In %2$s you don't ask why. You ask whether it's in yet, and it isn't.
    >>  ............................................
    pt  Por quê? No %2$s a gente não pergunta por quê. Pergunta se já entrou, e não entrou.
    >>  ............................................
  dialogue.conversations.work.ask_why.harvest/2   [93 chars]
    en  Ask me that in a quieter season, %1$s. Right now the answer is 'because it'll rot otherwise'.
    >>  ............................................
    pt  Me pergunte numa estação mais calma, %1$s. Agora a resposta é 'porque senão apodrece'.
    >>  ............................................
  dialogue.conversations.work.ask_why.harvest/3   [64 chars]
    en  This time of year the work does the asking and I just answer it.
    >>  ............................................
    pt  Nesta época do ano o trabalho é que pergunta e eu só respondo.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the season is `autumn`  _(chance -2000)_
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.respond.ask_why`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.respond.ask_why
WHO    VILLAGER — what the player reads after pressing "Why does it matter to you?"
       spoken on: conversations.topic.work.pride.respond, button `ask_why`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.reason`: the villager discloses. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.respond.ask_why/1   [66 chars]
    en  Because somebody has to, and because it's mine. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo.
    >>  ............................................
  dialogue.conversations.work.respond.ask_why/2   [88 chars]
    en  Why. Hm. ...Because when it's done right it lasts longer than I will. Don't repeat that.
    >>  ............................................
    pt  Por quê. Hm. ...Porque quando é bem feito dura mais do que eu. Não repete isso.
    >>  ............................................
  dialogue.conversations.work.respond.ask_why/3   [58 chars]
    en  It's the one thing in the day nobody argues with me about.
    >>  ............................................
    pt  É a única coisa do dia sobre a qual ninguém discute comigo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's most of what I've got, %1$s.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo que eu tenho, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing. Some weeks that's the only sentence keeping me at the bench.
    >>  ............................................
    pt  Porque precisa ser feito. Em algumas semanas é a única frase que me mantém na bancada.
    >>  ............................................
  anxious.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I'd not know who I was without it and that frightens me a little.
    >>  ............................................
    pt  Porque é meu. Eu não saberia quem eu sou sem isso e isso me assusta um pouco.
    >>  ............................................
  athletic.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's been enough for a long while.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. Basta faz muito tempo.
    >>  ............................................
  athletic.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and it'll need doing tomorrow, and I'll be here.
    >>  ............................................
    pt  Porque precisa ser feito, e vai precisar amanhã, e eu vou estar aqui.
    >>  ............................................
  athletic.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Reasons like that wear better than clever ones.
    >>  ............................................
    pt  Porque é meu. Razões assim se conservam melhor que as espertas.
    >>  ............................................
  confident.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo.
    >>  ............................................
  confident.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm the one here. That's the whole answer.
    >>  ............................................
    pt  Porque precisa ser feito e eu sou quem está aqui. É toda a resposta.
    >>  ............................................
  confident.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I've never needed a better reason than that.
    >>  ............................................
    pt  Porque é meu. Nunca precisei de razão melhor.
    >>  ............................................
  crabby.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo.
    >>  ............................................
  crabby.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm the one here. That's the whole answer.
    >>  ............................................
    pt  Porque precisa ser feito e eu sou quem está aqui. É toda a resposta.
    >>  ............................................
  crabby.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I've never needed a better reason than that.
    >>  ............................................
    pt  Porque é meu. Nunca precisei de razão melhor.
    >>  ............................................
  extroverted.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine, %1$s. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu, %1$s. É quase tudo.
    >>  ............................................
  extroverted.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and because the people who need it are people I know.
    >>  ............................................
    pt  Porque precisa ser feito, e porque quem precisa é gente que eu conheço.
    >>  ............................................
  extroverted.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. And because you asked, which is a better reason than I usually get.
    >>  ............................................
    pt  Porque é meu. E porque você perguntou, que é razão melhor do que eu costumo ter.
    >>  ............................................
  flirty.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine, %1$s. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu, %1$s. É quase tudo.
    >>  ............................................
  flirty.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and because the people who need it are people I know.
    >>  ............................................
    pt  Porque precisa ser feito, e porque quem precisa é gente que eu conheço.
    >>  ............................................
  flirty.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. And because you asked, which is a better reason than I usually get.
    >>  ............................................
    pt  Porque é meu. E porque você perguntou, que é razão melhor do que eu costumo ter.
    >>  ............................................
  friendly.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine, %1$s. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu, %1$s. É quase tudo.
    >>  ............................................
  friendly.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and because the people who need it are people I know.
    >>  ............................................
    pt  Porque precisa ser feito, e porque quem precisa é gente que eu conheço.
    >>  ............................................
  friendly.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. And because you asked, which is a better reason than I usually get.
    >>  ............................................
    pt  Porque é meu. E porque você perguntou, que é razão melhor do que eu costumo ter.
    >>  ............................................
  gloomy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's most of what I've got, %1$s.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo que eu tenho, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing. Some weeks that's the only sentence keeping me at the bench.
    >>  ............................................
    pt  Porque precisa ser feito. Em algumas semanas é a única frase que me mantém na bancada.
    >>  ............................................
  gloomy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I'd not know who I was without it and that frightens me a little.
    >>  ............................................
    pt  Porque é meu. Eu não saberia quem eu sou sem isso e isso me assusta um pouco.
    >>  ............................................
  greedy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo.
    >>  ............................................
  greedy.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm the one here. That's the whole answer.
    >>  ............................................
    pt  Porque precisa ser feito e eu sou quem está aqui. É toda a resposta.
    >>  ............................................
  greedy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I've never needed a better reason than that.
    >>  ............................................
    pt  Porque é meu. Nunca precisei de razão melhor.
    >>  ............................................
  grumpy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. That's most of it.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo.
    >>  ............................................
  grumpy.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm the one here. That's the whole answer.
    >>  ............................................
    pt  Porque precisa ser feito e eu sou quem está aqui. É toda a resposta.
    >>  ............................................
  grumpy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I've never needed a better reason than that.
    >>  ............................................
    pt  Porque é meu. Nunca precisei de razão melhor.
    >>  ............................................
  introverted.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu.
    >>  ............................................
  introverted.dialogue.conversations.work.respond.ask_why/2
    en  It needs doing. I'm here.
    >>  ............................................
    pt  Precisa ser feito. Eu estou aqui.
    >>  ............................................
  introverted.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. That's all.
    >>  ............................................
    pt  Porque é meu. Só isso.
    >>  ............................................
  lazy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's been enough for a long while.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. Basta faz muito tempo.
    >>  ............................................
  lazy.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and it'll need doing tomorrow, and I'll be here.
    >>  ............................................
    pt  Porque precisa ser feito, e vai precisar amanhã, e eu vou estar aqui.
    >>  ............................................
  lazy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Reasons like that wear better than clever ones.
    >>  ............................................
    pt  Porque é meu. Razões assim se conservam melhor que as espertas.
    >>  ............................................
  odd.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu.
    >>  ............................................
  odd.dialogue.conversations.work.respond.ask_why/2
    en  It needs doing. I'm here.
    >>  ............................................
    pt  Precisa ser feito. Eu estou aqui.
    >>  ............................................
  odd.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. That's all.
    >>  ............................................
    pt  Porque é meu. Só isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's been enough for a long while.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. Basta faz muito tempo.
    >>  ............................................
  peaceful.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and it'll need doing tomorrow, and I'll be here.
    >>  ............................................
    pt  Porque precisa ser feito, e vai precisar amanhã, e eu vou estar aqui.
    >>  ............................................
  peaceful.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Reasons like that wear better than clever ones.
    >>  ............................................
    pt  Porque é meu. Razões assim se conservam melhor que as espertas.
    >>  ............................................
  peppy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine! That's most of it and it's enough.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu! É quase tudo e basta.
    >>  ............................................
  peppy.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm here. Those two facts have made my whole life.
    >>  ............................................
    pt  Porque precisa ser feito e eu estou aqui. Esses dois fatos fizeram a minha vida inteira.
    >>  ............................................
  peppy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Everything else is decoration on that one word.
    >>  ............................................
    pt  Porque é meu. Todo o resto é enfeite em cima dessa palavra.
    >>  ............................................
  playful.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine! That's most of it and it's enough.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu! É quase tudo e basta.
    >>  ............................................
  playful.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm here. Those two facts have made my whole life.
    >>  ............................................
    pt  Porque precisa ser feito e eu estou aqui. Esses dois fatos fizeram a minha vida inteira.
    >>  ............................................
  playful.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Everything else is decoration on that one word.
    >>  ............................................
    pt  Porque é meu. Todo o resto é enfeite em cima dessa palavra.
    >>  ............................................
  relaxed.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's been enough for a long while.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. Basta faz muito tempo.
    >>  ............................................
  relaxed.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing, and it'll need doing tomorrow, and I'll be here.
    >>  ............................................
    pt  Porque precisa ser feito, e vai precisar amanhã, e eu vou estar aqui.
    >>  ............................................
  relaxed.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Reasons like that wear better than clever ones.
    >>  ............................................
    pt  Porque é meu. Razões assim se conservam melhor que as espertas.
    >>  ............................................
  sensitive.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine. It's most of what I've got, %1$s.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu. É quase tudo que eu tenho, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing. Some weeks that's the only sentence keeping me at the bench.
    >>  ............................................
    pt  Porque precisa ser feito. Em algumas semanas é a única frase que me mantém na bancada.
    >>  ............................................
  sensitive.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. I'd not know who I was without it and that frightens me a little.
    >>  ............................................
    pt  Porque é meu. Eu não saberia quem eu sou sem isso e isso me assusta um pouco.
    >>  ............................................
  shy.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu.
    >>  ............................................
  shy.dialogue.conversations.work.respond.ask_why/2
    en  It needs doing. I'm here.
    >>  ............................................
    pt  Precisa ser feito. Eu estou aqui.
    >>  ............................................
  shy.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. That's all.
    >>  ............................................
    pt  Porque é meu. Só isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine! That's most of it and it's enough.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu! É quase tudo e basta.
    >>  ............................................
  upbeat.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm here. Those two facts have made my whole life.
    >>  ............................................
    pt  Porque precisa ser feito e eu estou aqui. Esses dois fatos fizeram a minha vida inteira.
    >>  ............................................
  upbeat.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Everything else is decoration on that one word.
    >>  ............................................
    pt  Porque é meu. Todo o resto é enfeite em cima dessa palavra.
    >>  ............................................
  witty.dialogue.conversations.work.respond.ask_why/1
    en  Because somebody has to, and because it's mine! That's most of it and it's enough.
    >>  ............................................
    pt  Porque alguém tem que fazer, e porque é meu! É quase tudo e basta.
    >>  ............................................
  witty.dialogue.conversations.work.respond.ask_why/2
    en  Because it needs doing and I'm here. Those two facts have made my whole life.
    >>  ............................................
    pt  Porque precisa ser feito e eu estou aqui. Esses dois fatos fizeram a minha vida inteira.
    >>  ............................................
  witty.dialogue.conversations.work.respond.ask_why/3
    en  Because it's mine. Everything else is decoration on that one word.
    >>  ............................................
    pt  Porque é meu. Todo o resto é enfeite em cima dessa palavra.
    >>  ............................................
```

</details>


### Button `blunt` — "You'd say that whether it was true or not."

*stance family `challenge` · tone `blunt` · outcome `qualified` · answers the beat(s) `work.pride.likes_it`, `work.pride.templated` · offered only once the villager has actually said `feeling:content`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.pride.blunt` — accepted phrasings: "you would say that whether it was true or not"; "you would say that anyway"; "you would say that either way"
  - the message must contain one of: `true`, `whether`
  - scored words: `true`(1.5), `whether`(1.5), `anyway`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.respond.blunt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.respond.blunt   [42 chars]
    en  You'd say that whether it was true or not.
    >>  ............................................
    pt  Você diria isso sendo verdade ou não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.blunt`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, tension +2  _(recorded under topic `work.blunt`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.pride.followup`
- …where the player's next choices will be: "Anyone could do that job." | "So you're the reason it all still works." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.blunt
WHO    VILLAGER — what the player reads after pressing "You'd say that whether it was true or not."
       spoken on: conversations.topic.work.pride.respond, button `blunt`
       leaves the player on: conversations.topic.work.pride.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.conceded`: the villager qualifys. Subject `work.any.pride`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:any` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.blunt/1   [100 chars]
    en  ...I would, wouldn't I. It's what you say about your own trade. ...Ask me again and I'll try harder.
    >>  ............................................
    pt  ...Eu diria, né. É o que se diz do próprio ofício. ...Pergunte de novo e eu me esforço mais.
    >>  ............................................
  dialogue.conversations.work.blunt/2   [44 chars]
    en  That's a rude thing to be right about, %1$s.
    >>  ............................................
    pt  É grosseiro estar certo sobre isso, %1$s.
    >>  ............................................
  dialogue.conversations.work.blunt/3   [83 chars]
    en  Hm. Fair. Half of what I say about the work is habit and the other half is defence.
    >>  ............................................
    pt  Hm. Justo. Metade do que eu digo do trabalho é hábito e a outra metade é defesa.
    >>  ............................................
```


### Button `leave` — "I'll let you work."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.pride.likes_it`, `work.pride.templated` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.pride.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.pride.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.pride.respond.leave   [18 chars]
    en  I'll let you work.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.respond.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you work."
       spoken on: conversations.topic.work.pride.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.left`: the villager accepts. Subject `work.any.pride`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.pride.followup / leave
```

> Written out in full under **`conversations.topic.work.pride.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.young.respond`

**Reached from 2 route(s):** `conversations.cat.profession` / `work`; `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.child` — e.g. "My job is being a kid and I'm SO good at it."
- `conversations.work.teen` — e.g. "Bit of everything — whatever the grown-ups don't want to do, apparently."


```text
POOL   dialogue key: dialogue.conversations.topic.work.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.young.respond   [17 chars]
    en  That's what I do!
    >>  ............................................
    pt  É isso que eu faço!
    >>  ............................................
```


### Button `encourage` — "That's real work, that is."

*stance family `encouragement` · tone `plain` · answers the beat(s) `work.child.to.work.young`, `work.teen.to.work.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.young.encourage` — accepted phrasings: "that is real work"; "that is proper work"; "that counts as work"
  - the message must contain one of: `real`, `proper`, `counts`
  - scored words: `real`(1.5), `proper`(1.2), `counts`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.young.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.young.respond.encourage   [26 chars]
    en  That's real work, that is.
    >>  ............................................
    pt  Isso é trabalho de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.young.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.young.encourage`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.young.encourage
WHO    VILLAGER — what the player reads after pressing "That's real work, that is."
       spoken on: conversations.topic.work.young.respond, button `encourage`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.young.encourage.terminal`: the villager accepts. Subject `work.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.young.encourage/1   [34 chars]
    en  It IS! Nobody counts it but it is.
    >>  ............................................
    pt  É MESMO! Ninguém conta mas é.
    >>  ............................................
  dialogue.conversations.work.young.encourage/2   [48 chars]
    en  ...Thanks. Everyone calls it helping. It's work.
    >>  ............................................
    pt  ...Valeu. Todo mundo chama de ajudar. É trabalho.
    >>  ............................................
  dialogue.conversations.work.young.encourage/3   [36 chars]
    en  You get it. Grown-ups usually don't.
    >>  ............................................
    pt  Você entendeu. Os adultos geralmente não.
    >>  ............................................
```


### Button `ask` — "Who taught you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `work.child.to.work.young`, `work.teen.to.work.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.young.ask` — accepted phrasings: "who taught you"; "how did you learn"; "who showed you"
  - the message must contain one of: `taught`, `learn`, `who`
  - scored words: `taught`(1.5), `learn`(1.2), `who`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.young.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.young.respond.ask   [15 chars]
    en  Who taught you?
    >>  ............................................
    pt  Quem te ensinou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.young.ask`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.young.ask
WHO    VILLAGER — what the player reads after pressing "Who taught you?"
       spoken on: conversations.topic.work.young.respond, button `ask`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.young.ask.terminal`: the villager asks. Subject `work.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.young.ask/1   [55 chars]
    en  My mother, mostly. And a lot of getting it wrong first.
    >>  ............................................
    pt  Minha mãe, principalmente. E muito errar primeiro.
    >>  ............................................
  dialogue.conversations.work.young.ask/2   [40 chars]
    en  Nobody! I watched until I worked it out.
    >>  ............................................
    pt  Ninguém! Eu olhei até descobrir.
    >>  ............................................
  dialogue.conversations.work.young.ask/3   [65 chars]
    en  The smith lets me hold things. That counts as teaching, I reckon.
    >>  ............................................
    pt  O ferreiro me deixa segurar as coisas. Isso conta como ensinar, eu acho.
    >>  ............................................
```


### Button `dismiss` — "That's not a real job."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `work.child.to.work.young`, `work.teen.to.work.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.young.dismiss` — accepted phrasings: "you are just playing"; "that is pretend work"; "those are only chores"
  - the message must contain one of: `playing`, `pretend`, `chores`
  - scored words: `playing`(1.5), `pretend`(1.5), `chores`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.young.respond.dismiss   [22 chars]
    en  That's not a real job.
    >>  ............................................
    pt  Isso não é um trabalho de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `work.young.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +3  _(recorded under topic `work.young.dismiss`)_
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.young.dismiss
WHO    VILLAGER — what the player reads after pressing "That's not a real job."
       spoken on: conversations.topic.work.young.respond, button `dismiss`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.young.dismiss.terminal`: the villager dismisss. Subject `work.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.young.dismiss/1   [15 chars]
    en  ...It is to me.
    >>  ............................................
    pt  ...Para mim é.
    >>  ............................................
  dialogue.conversations.work.young.dismiss/2   [43 chars]
    en  Everyone says that until they need it done.
    >>  ............................................
    pt  Todo mundo diz isso até precisar que seja feito.
    >>  ............................................
  dialogue.conversations.work.young.dismiss/3   [28 chars]
    en  Fine. Forget I mentioned it.
    >>  ............................................
    pt  Tá. Esquece que eu falei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me, %1$s. It's most of what I've got.
    >>  ............................................
    pt  ...Pra mim é, %1$s. É quase tudo que eu tenho.
    >>  ............................................
  anxious.dialogue.conversations.work.young.dismiss/2
    en  Right. Yes. Small. I know how it looks from outside.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu sei como parece de fora.
    >>  ............................................
  anxious.dialogue.conversations.work.young.dismiss/3
    en  ...I'll not tell you about it again.
    >>  ............................................
    pt  ...Não te conto de novo.
    >>  ............................................
  athletic.dialogue.conversations.work.young.dismiss/1
    en  It is to me. That's usually enough for a trade.
    >>  ............................................
    pt  Pra mim é. Pra um ofício costuma bastar.
    >>  ............................................
  athletic.dialogue.conversations.work.young.dismiss/2
    en  ...Aye, it's quiet work. Quiet work suits some of us.
    >>  ............................................
    pt  ...É, é trabalho quieto. Trabalho quieto serve a alguns de nós.
    >>  ............................................
  athletic.dialogue.conversations.work.young.dismiss/3
    en  Right you are. It'll still be there tomorrow, being small.
    >>  ............................................
    pt  Você tem razão. Vai estar lá amanhã, sendo pequeno.
    >>  ............................................
  confident.dialogue.conversations.work.young.dismiss/1
    en  It is to me.
    >>  ............................................
    pt  Pra mim é.
    >>  ............................................
  confident.dialogue.conversations.work.young.dismiss/2
    en  Right. Then don't ask me about it.
    >>  ............................................
    pt  Certo. Então não me pergunte sobre isso.
    >>  ............................................
  confident.dialogue.conversations.work.young.dismiss/3
    en  ...It's what I do all day. That makes it something.
    >>  ............................................
    pt  ...É o que eu faço o dia todo. Isso faz ser alguma coisa.
    >>  ............................................
  crabby.dialogue.conversations.work.young.dismiss/1
    en  It is to me.
    >>  ............................................
    pt  Pra mim é.
    >>  ............................................
  crabby.dialogue.conversations.work.young.dismiss/2
    en  Right. Then don't ask me about it.
    >>  ............................................
    pt  Certo. Então não me pergunte sobre isso.
    >>  ............................................
  crabby.dialogue.conversations.work.young.dismiss/3
    en  ...It's what I do all day. That makes it something.
    >>  ............................................
    pt  ...É o que eu faço o dia todo. Isso faz ser alguma coisa.
    >>  ............................................
  extroverted.dialogue.conversations.work.young.dismiss/1
    en  It is to me, %1$s. I'd show you the good bit if you wanted.
    >>  ............................................
    pt  Pra mim é, %1$s. Eu te mostraria a parte boa se você quisesse.
    >>  ............................................
  extroverted.dialogue.conversations.work.young.dismiss/2
    en  You'd like it if somebody showed you properly. Most people would.
    >>  ............................................
    pt  Você gostaria se alguém te mostrasse direito. Quase todo mundo gostaria.
    >>  ............................................
  extroverted.dialogue.conversations.work.young.dismiss/3
    en  ...Right. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais disso.
    >>  ............................................
  flirty.dialogue.conversations.work.young.dismiss/1
    en  It is to me, %1$s. I'd show you the good bit if you wanted.
    >>  ............................................
    pt  Pra mim é, %1$s. Eu te mostraria a parte boa se você quisesse.
    >>  ............................................
  flirty.dialogue.conversations.work.young.dismiss/2
    en  You'd like it if somebody showed you properly. Most people would.
    >>  ............................................
    pt  Você gostaria se alguém te mostrasse direito. Quase todo mundo gostaria.
    >>  ............................................
  flirty.dialogue.conversations.work.young.dismiss/3
    en  ...Right. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais disso.
    >>  ............................................
  friendly.dialogue.conversations.work.young.dismiss/1
    en  It is to me, %1$s. I'd show you the good bit if you wanted.
    >>  ............................................
    pt  Pra mim é, %1$s. Eu te mostraria a parte boa se você quisesse.
    >>  ............................................
  friendly.dialogue.conversations.work.young.dismiss/2
    en  You'd like it if somebody showed you properly. Most people would.
    >>  ............................................
    pt  Você gostaria se alguém te mostrasse direito. Quase todo mundo gostaria.
    >>  ............................................
  friendly.dialogue.conversations.work.young.dismiss/3
    en  ...Right. I'll not go on about it, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais disso.
    >>  ............................................
  gloomy.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me, %1$s. It's most of what I've got.
    >>  ............................................
    pt  ...Pra mim é, %1$s. É quase tudo que eu tenho.
    >>  ............................................
  gloomy.dialogue.conversations.work.young.dismiss/2
    en  Right. Yes. Small. I know how it looks from outside.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu sei como parece de fora.
    >>  ............................................
  gloomy.dialogue.conversations.work.young.dismiss/3
    en  ...I'll not tell you about it again.
    >>  ............................................
    pt  ...Não te conto de novo.
    >>  ............................................
  greedy.dialogue.conversations.work.young.dismiss/1
    en  It is to me.
    >>  ............................................
    pt  Pra mim é.
    >>  ............................................
  greedy.dialogue.conversations.work.young.dismiss/2
    en  Right. Then don't ask me about it.
    >>  ............................................
    pt  Certo. Então não me pergunte sobre isso.
    >>  ............................................
  greedy.dialogue.conversations.work.young.dismiss/3
    en  ...It's what I do all day. That makes it something.
    >>  ............................................
    pt  ...É o que eu faço o dia todo. Isso faz ser alguma coisa.
    >>  ............................................
  grumpy.dialogue.conversations.work.young.dismiss/1
    en  It is to me.
    >>  ............................................
    pt  Pra mim é.
    >>  ............................................
  grumpy.dialogue.conversations.work.young.dismiss/2
    en  Right. Then don't ask me about it.
    >>  ............................................
    pt  Certo. Então não me pergunte sobre isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.young.dismiss/3
    en  ...It's what I do all day. That makes it something.
    >>  ............................................
    pt  ...É o que eu faço o dia todo. Isso faz ser alguma coisa.
    >>  ............................................
  introverted.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me.
    >>  ............................................
    pt  ...Pra mim é.
    >>  ............................................
  introverted.dialogue.conversations.work.young.dismiss/2
    en  That's all right. It's mine.
    >>  ............................................
    pt  Tudo bem. É meu.
    >>  ............................................
  introverted.dialogue.conversations.work.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  lazy.dialogue.conversations.work.young.dismiss/1
    en  It is to me. That's usually enough for a trade.
    >>  ............................................
    pt  Pra mim é. Pra um ofício costuma bastar.
    >>  ............................................
  lazy.dialogue.conversations.work.young.dismiss/2
    en  ...Aye, it's quiet work. Quiet work suits some of us.
    >>  ............................................
    pt  ...É, é trabalho quieto. Trabalho quieto serve a alguns de nós.
    >>  ............................................
  lazy.dialogue.conversations.work.young.dismiss/3
    en  Right you are. It'll still be there tomorrow, being small.
    >>  ............................................
    pt  Você tem razão. Vai estar lá amanhã, sendo pequeno.
    >>  ............................................
  odd.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me.
    >>  ............................................
    pt  ...Pra mim é.
    >>  ............................................
  odd.dialogue.conversations.work.young.dismiss/2
    en  That's all right. It's mine.
    >>  ............................................
    pt  Tudo bem. É meu.
    >>  ............................................
  odd.dialogue.conversations.work.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  peaceful.dialogue.conversations.work.young.dismiss/1
    en  It is to me. That's usually enough for a trade.
    >>  ............................................
    pt  Pra mim é. Pra um ofício costuma bastar.
    >>  ............................................
  peaceful.dialogue.conversations.work.young.dismiss/2
    en  ...Aye, it's quiet work. Quiet work suits some of us.
    >>  ............................................
    pt  ...É, é trabalho quieto. Trabalho quieto serve a alguns de nós.
    >>  ............................................
  peaceful.dialogue.conversations.work.young.dismiss/3
    en  Right you are. It'll still be there tomorrow, being small.
    >>  ............................................
    pt  Você tem razão. Vai estar lá amanhã, sendo pequeno.
    >>  ............................................
  peppy.dialogue.conversations.work.young.dismiss/1
    en  It is to me! And I'm the one doing it, so I win.
    >>  ............................................
    pt  Pra mim é! E eu é que faço, então eu ganho.
    >>  ............................................
  peppy.dialogue.conversations.work.young.dismiss/2
    en  Right! Boring. Come and try it and then we'll talk, %1$s.
    >>  ............................................
    pt  Certo! Chato. Venha tentar e aí a gente conversa, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.work.young.dismiss/3
    en  ...Ha. Fine. You'd be surprised.
    >>  ............................................
    pt  ...Ha. Tudo bem. Você ficaria surpreso.
    >>  ............................................
  playful.dialogue.conversations.work.young.dismiss/1
    en  It is to me! And I'm the one doing it, so I win.
    >>  ............................................
    pt  Pra mim é! E eu é que faço, então eu ganho.
    >>  ............................................
  playful.dialogue.conversations.work.young.dismiss/2
    en  Right! Boring. Come and try it and then we'll talk, %1$s.
    >>  ............................................
    pt  Certo! Chato. Venha tentar e aí a gente conversa, %1$s.
    >>  ............................................
  playful.dialogue.conversations.work.young.dismiss/3
    en  ...Ha. Fine. You'd be surprised.
    >>  ............................................
    pt  ...Ha. Tudo bem. Você ficaria surpreso.
    >>  ............................................
  relaxed.dialogue.conversations.work.young.dismiss/1
    en  It is to me. That's usually enough for a trade.
    >>  ............................................
    pt  Pra mim é. Pra um ofício costuma bastar.
    >>  ............................................
  relaxed.dialogue.conversations.work.young.dismiss/2
    en  ...Aye, it's quiet work. Quiet work suits some of us.
    >>  ............................................
    pt  ...É, é trabalho quieto. Trabalho quieto serve a alguns de nós.
    >>  ............................................
  relaxed.dialogue.conversations.work.young.dismiss/3
    en  Right you are. It'll still be there tomorrow, being small.
    >>  ............................................
    pt  Você tem razão. Vai estar lá amanhã, sendo pequeno.
    >>  ............................................
  sensitive.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me, %1$s. It's most of what I've got.
    >>  ............................................
    pt  ...Pra mim é, %1$s. É quase tudo que eu tenho.
    >>  ............................................
  sensitive.dialogue.conversations.work.young.dismiss/2
    en  Right. Yes. Small. I know how it looks from outside.
    >>  ............................................
    pt  Certo. Sim. Pequeno. Eu sei como parece de fora.
    >>  ............................................
  sensitive.dialogue.conversations.work.young.dismiss/3
    en  ...I'll not tell you about it again.
    >>  ............................................
    pt  ...Não te conto de novo.
    >>  ............................................
  shy.dialogue.conversations.work.young.dismiss/1
    en  ...It is to me.
    >>  ............................................
    pt  ...Pra mim é.
    >>  ............................................
  shy.dialogue.conversations.work.young.dismiss/2
    en  That's all right. It's mine.
    >>  ............................................
    pt  Tudo bem. É meu.
    >>  ............................................
  shy.dialogue.conversations.work.young.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  upbeat.dialogue.conversations.work.young.dismiss/1
    en  It is to me! And I'm the one doing it, so I win.
    >>  ............................................
    pt  Pra mim é! E eu é que faço, então eu ganho.
    >>  ............................................
  upbeat.dialogue.conversations.work.young.dismiss/2
    en  Right! Boring. Come and try it and then we'll talk, %1$s.
    >>  ............................................
    pt  Certo! Chato. Venha tentar e aí a gente conversa, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.work.young.dismiss/3
    en  ...Ha. Fine. You'd be surprised.
    >>  ............................................
    pt  ...Ha. Tudo bem. Você ficaria surpreso.
    >>  ............................................
  witty.dialogue.conversations.work.young.dismiss/1
    en  It is to me! And I'm the one doing it, so I win.
    >>  ............................................
    pt  Pra mim é! E eu é que faço, então eu ganho.
    >>  ............................................
  witty.dialogue.conversations.work.young.dismiss/2
    en  Right! Boring. Come and try it and then we'll talk, %1$s.
    >>  ............................................
    pt  Certo! Chato. Venha tentar e aí a gente conversa, %1$s.
    >>  ............................................
  witty.dialogue.conversations.work.young.dismiss/3
    en  ...Ha. Fine. You'd be surprised.
    >>  ............................................
    pt  ...Ha. Tudo bem. Você ficaria surpreso.
    >>  ............................................
```

</details>


### Button `leave` — "Back to it, then."

*stance family `exit` · tone `plain` · answers the beat(s) `work.child.to.work.young`, `work.teen.to.work.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.young.respond.leave   [17 chars]
    en  Back to it, then.
    >>  ............................................
    pt  De volta ao trabalho, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.young.leave
WHO    VILLAGER — what the player reads after pressing "Back to it, then."
       spoken on: conversations.topic.work.young.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.young.leave.terminal`: the villager accepts. Subject `work.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.work.young.leave/1   [4 chars]
    en  Bye!
    >>  ............................................
    pt  Tchau!
    >>  ............................................
  dialogue.conversations.work.young.leave/2   [14 chars]
    en  See you, %1$s.
    >>  ............................................
    pt  Até mais, %1$s.
    >>  ............................................
  dialogue.conversations.work.young.leave/3   [32 chars]
    en  Okay. I've got things to finish.
    >>  ............................................
    pt  Tá. Tenho coisas para terminar.
    >>  ............................................
```

---


## `conversations.work`

Question flags: `auto`, `silent`. An `auto` node shows no buttons — it plays its one answer straight through.

**Reached from 1 route(s):** `conversations.cat.profession` / `work`


```text
POOL   dialogue key: dialogue.conversations.work
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.work
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.work   [31 chars]
    en  Do you actually like your work?
    >>  ............................................
    pt  Você gosta mesmo do seu trabalho?
    >>  ............................................
```


### Button `(auto)` — "(no label — this is an auto answer)"

```text
POOL   dialogue key: dialogue.conversations.work.(auto)
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.work
ARGS   none — button labels take no substitutions; write plain text
SIZE   0 lines in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

> **No English line ships under this key.** Write one, and its `pt_br` twin.

```text
  dialogue.conversations.work.(auto)
    en  (missing)
    >>  ............................................
    pt  (missing)
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.respond`
- …where the player's next choices will be: "What do the weeds actually cost you?" | "The village eats because you get up first." | "Anyone can put a seed in the ground." | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.identity`: the villager explains. Subject `work.farmer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.farmer/1   [90 chars]
    en  Farming's honest: you plant, you pray, you pull weeds. The weeds always negotiate hardest.
    >>  ............................................
    pt  A lavoura é honesta: você planta, você reza, você arranca o mato. O mato sempre negocia mais duro.
    >>  ............................................
  dialogue.conversations.work.prof.farmer/2   [75 chars]
    en  I talk to the crops, %1$s. The wheat listens. The beetroot is a lost cause.
    >>  ............................................
    pt  Eu converso com a plantação, %1$s. O trigo escuta. A beterraba é caso perdido.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.farmer/1
    en  I farm. One bad August and it doesn't matter how carefully I did every other month.
    >>  ............................................
    pt  Eu planto. Um agosto ruim e não importa o quanto eu fui cuidadoso nos outros meses.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.farmer/2
    en  Farming. You spend a year on something the weather can take in an afternoon, and then you do it again.
    >>  ............................................
    pt  Lavoura. Você passa um ano em algo que o tempo leva numa tarde, e aí faz de novo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer/1
    en  I farm. The year tells me what the day is, and I've stopped arguing with it.
    >>  ............................................
    pt  Eu planto. O ano me diz o que é o dia, e eu parei de discutir com ele.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer/2
    en  Fields. Nothing in this trade can be hurried, which is most of why I'm still in it.
    >>  ............................................
    pt  Campos. Nada neste ofício pode ser apressado, e é quase toda a razão de eu continuar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer/1
    en  I farm. Plant, weed, harvest, and the field does not care how I felt about any of it.
    >>  ............................................
    pt  Eu planto. Semear, capinar, colher, e o campo não liga pro que eu senti em nada disso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer/2
    en  Farming. It's the only trade where being right in April is proved wrong in August.
    >>  ............................................
    pt  Lavoura. É o único ofício em que estar certo em abril é desmentido em agosto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer/1
    en  I farm. Plant, weed, harvest, and the field does not care how I felt about any of it.
    >>  ............................................
    pt  Eu planto. Semear, capinar, colher, e o campo não liga pro que eu senti em nada disso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer/2
    en  Farming. It's the only trade where being right in April is proved wrong in August.
    >>  ............................................
    pt  Lavoura. É o único ofício em que estar certo em abril é desmentido em agosto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer/1
    en  I farm. Which means half this village has eaten out of my field and I know which half.
    >>  ............................................
    pt  Eu planto. O que significa que metade deste vilarejo comeu do meu campo e eu sei qual metade.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer/2
    en  Farming. Come at harvest — everybody ends up in somebody's field and it's the best week of the year.
    >>  ............................................
    pt  Lavoura. Venha na colheita — todo mundo acaba no campo de alguém e é a melhor semana do ano.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer/1
    en  I farm. Which means half this village has eaten out of my field and I know which half.
    >>  ............................................
    pt  Eu planto. O que significa que metade deste vilarejo comeu do meu campo e eu sei qual metade.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer/2
    en  Farming. Come at harvest — everybody ends up in somebody's field and it's the best week of the year.
    >>  ............................................
    pt  Lavoura. Venha na colheita — todo mundo acaba no campo de alguém e é a melhor semana do ano.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer/1
    en  I farm. Which means half this village has eaten out of my field and I know which half.
    >>  ............................................
    pt  Eu planto. O que significa que metade deste vilarejo comeu do meu campo e eu sei qual metade.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer/2
    en  Farming. Come at harvest — everybody ends up in somebody's field and it's the best week of the year.
    >>  ............................................
    pt  Lavoura. Venha na colheita — todo mundo acaba no campo de alguém e é a melhor semana do ano.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer/1
    en  I farm. One bad August and it doesn't matter how carefully I did every other month.
    >>  ............................................
    pt  Eu planto. Um agosto ruim e não importa o quanto eu fui cuidadoso nos outros meses.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer/2
    en  Farming. You spend a year on something the weather can take in an afternoon, and then you do it again.
    >>  ............................................
    pt  Lavoura. Você passa um ano em algo que o tempo leva numa tarde, e aí faz de novo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer/1
    en  I farm. Plant, weed, harvest, and the field does not care how I felt about any of it.
    >>  ............................................
    pt  Eu planto. Semear, capinar, colher, e o campo não liga pro que eu senti em nada disso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer/2
    en  Farming. It's the only trade where being right in April is proved wrong in August.
    >>  ............................................
    pt  Lavoura. É o único ofício em que estar certo em abril é desmentido em agosto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer/1
    en  I farm. Plant, weed, harvest, and the field does not care how I felt about any of it.
    >>  ............................................
    pt  Eu planto. Semear, capinar, colher, e o campo não liga pro que eu senti em nada disso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer/2
    en  Farming. It's the only trade where being right in April is proved wrong in August.
    >>  ............................................
    pt  Lavoura. É o único ofício em que estar certo em abril é desmentido em agosto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer/1
    en  I farm. Today it's the low corner that floods, which nobody has ever wanted to hear about.
    >>  ............................................
    pt  Eu planto. Hoje é o canto baixo que alaga, que ninguém nunca quis ouvir sobre.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer/2
    en  Fields. There's a row I planted crooked eleven years ago and I still see it from the gate.
    >>  ............................................
    pt  Campos. Tem uma fileira que eu plantei torta onze anos atrás e eu ainda vejo do portão.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer/1
    en  I farm. The year tells me what the day is, and I've stopped arguing with it.
    >>  ............................................
    pt  Eu planto. O ano me diz o que é o dia, e eu parei de discutir com ele.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer/2
    en  Fields. Nothing in this trade can be hurried, which is most of why I'm still in it.
    >>  ............................................
    pt  Campos. Nada neste ofício pode ser apressado, e é quase toda a razão de eu continuar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer/1
    en  I farm. Today it's the low corner that floods, which nobody has ever wanted to hear about.
    >>  ............................................
    pt  Eu planto. Hoje é o canto baixo que alaga, que ninguém nunca quis ouvir sobre.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer/2
    en  Fields. There's a row I planted crooked eleven years ago and I still see it from the gate.
    >>  ............................................
    pt  Campos. Tem uma fileira que eu plantei torta onze anos atrás e eu ainda vejo do portão.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer/1
    en  I farm. The year tells me what the day is, and I've stopped arguing with it.
    >>  ............................................
    pt  Eu planto. O ano me diz o que é o dia, e eu parei de discutir com ele.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer/2
    en  Fields. Nothing in this trade can be hurried, which is most of why I'm still in it.
    >>  ............................................
    pt  Campos. Nada neste ofício pode ser apressado, e é quase toda a razão de eu continuar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer/1
    en  I grow things! Mostly on purpose. The weeds are a hobby I didn't ask for.
    >>  ............................................
    pt  Eu planto coisas! Quase sempre de propósito. O mato é um passatempo que eu não pedi.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer/2
    en  Farming. You put a small thing in the ground and later there's dinner. Never stops being good.
    >>  ............................................
    pt  Lavoura. Você põe uma coisinha no chão e depois tem janta. Nunca deixa de ser bom.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer/1
    en  I grow things! Mostly on purpose. The weeds are a hobby I didn't ask for.
    >>  ............................................
    pt  Eu planto coisas! Quase sempre de propósito. O mato é um passatempo que eu não pedi.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer/2
    en  Farming. You put a small thing in the ground and later there's dinner. Never stops being good.
    >>  ............................................
    pt  Lavoura. Você põe uma coisinha no chão e depois tem janta. Nunca deixa de ser bom.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer/1
    en  I farm. The year tells me what the day is, and I've stopped arguing with it.
    >>  ............................................
    pt  Eu planto. O ano me diz o que é o dia, e eu parei de discutir com ele.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer/2
    en  Fields. Nothing in this trade can be hurried, which is most of why I'm still in it.
    >>  ............................................
    pt  Campos. Nada neste ofício pode ser apressado, e é quase toda a razão de eu continuar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer/1
    en  I farm. One bad August and it doesn't matter how carefully I did every other month.
    >>  ............................................
    pt  Eu planto. Um agosto ruim e não importa o quanto eu fui cuidadoso nos outros meses.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer/2
    en  Farming. You spend a year on something the weather can take in an afternoon, and then you do it again.
    >>  ............................................
    pt  Lavoura. Você passa um ano em algo que o tempo leva numa tarde, e aí faz de novo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer/1
    en  I farm. Today it's the low corner that floods, which nobody has ever wanted to hear about.
    >>  ............................................
    pt  Eu planto. Hoje é o canto baixo que alaga, que ninguém nunca quis ouvir sobre.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer/2
    en  Fields. There's a row I planted crooked eleven years ago and I still see it from the gate.
    >>  ............................................
    pt  Campos. Tem uma fileira que eu plantei torta onze anos atrás e eu ainda vejo do portão.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer/1
    en  I grow things! Mostly on purpose. The weeds are a hobby I didn't ask for.
    >>  ............................................
    pt  Eu planto coisas! Quase sempre de propósito. O mato é um passatempo que eu não pedi.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer/2
    en  Farming. You put a small thing in the ground and later there's dinner. Never stops being good.
    >>  ............................................
    pt  Lavoura. Você põe uma coisinha no chão e depois tem janta. Nunca deixa de ser bom.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer/1
    en  I grow things! Mostly on purpose. The weeds are a hobby I didn't ask for.
    >>  ............................................
    pt  Eu planto coisas! Quase sempre de propósito. O mato é um passatempo que eu não pedi.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer/2
    en  Farming. You put a small thing in the ground and later there's dinner. Never stops being good.
    >>  ............................................
    pt  Lavoura. Você põe uma coisinha no chão e depois tem janta. Nunca deixa de ser bom.
    >>  ............................................
```

</details>


**Outcome 2 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.respond`
- …where the player's next choices will be: "What does the heron actually take?" | "Half the village's supper comes off that dock." | "Sitting still isn't a trade." | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.identity`: the villager explains. Subject `work.fisherman.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.fisherman/1   [101 chars]
    en  The fish and I have an understanding: I wait, they mock me, occasionally one apologizes into the net.
    >>  ............................................
    pt  Os peixes e eu temos um acordo: eu espero, eles zombam de mim, e de vez em quando um pede desculpa dentro da rede.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman/2   [85 chars]
    en  Best office in the village — the dock at dawn. Worst coworker — the heron. He steals.
    >>  ............................................
    pt  Melhor escritório do vilarejo — o cais ao amanhecer. Pior colega — a garça. Ela rouba.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The river is lower than it was when I was young, and I'm the only one counting.
    >>  ............................................
    pt  Eu pesco. O rio está mais baixo do que quando eu era jovem, e eu sou o único contando.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Some weeks the net comes up empty and I don't say so, because people worry.
    >>  ............................................
    pt  Pesca. Em algumas semanas a rede vem vazia e eu não digo, porque as pessoas se preocupam.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The water decides the day and I've never known it to be in a hurry.
    >>  ............................................
    pt  Eu pesco. A água decide o dia e eu nunca a vi com pressa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Same bend, same hour, thirty years. It has never once felt like the same morning.
    >>  ............................................
    pt  Pesca. Mesma curva, mesma hora, trinta anos. Nunca pareceu a mesma manhã.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman/1
    en  I fish. Out before light, back when there's something to come back with. No mystery in it.
    >>  ............................................
    pt  Eu pesco. Saio antes da luz, volto quando tem algo pra trazer. Sem mistério.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Reading water is the trade; the rod is just what you hold while you do it.
    >>  ............................................
    pt  Pesca. Ler a água é o ofício; a vara é só o que você segura enquanto lê.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman/1
    en  I fish. Out before light, back when there's something to come back with. No mystery in it.
    >>  ............................................
    pt  Eu pesco. Saio antes da luz, volto quando tem algo pra trazer. Sem mistério.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Reading water is the trade; the rod is just what you hold while you do it.
    >>  ............................................
    pt  Pesca. Ler a água é o ofício; a vara é só o que você segura enquanto lê.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman/1
    en  I fish. And I bring the second-best of it to whoever's had a hard week. That's most of the trade.
    >>  ............................................
    pt  Eu pesco. E levo o segundo melhor pra quem teve uma semana dura. É quase todo o ofício.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Come out with me one morning — it's four hours of quiet and then a great deal of talking.
    >>  ............................................
    pt  Pesca. Venha comigo numa manhã — são quatro horas de silêncio e depois muita conversa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman/1
    en  I fish. And I bring the second-best of it to whoever's had a hard week. That's most of the trade.
    >>  ............................................
    pt  Eu pesco. E levo o segundo melhor pra quem teve uma semana dura. É quase todo o ofício.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Come out with me one morning — it's four hours of quiet and then a great deal of talking.
    >>  ............................................
    pt  Pesca. Venha comigo numa manhã — são quatro horas de silêncio e depois muita conversa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman/1
    en  I fish. And I bring the second-best of it to whoever's had a hard week. That's most of the trade.
    >>  ............................................
    pt  Eu pesco. E levo o segundo melhor pra quem teve uma semana dura. É quase todo o ofício.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Come out with me one morning — it's four hours of quiet and then a great deal of talking.
    >>  ............................................
    pt  Pesca. Venha comigo numa manhã — são quatro horas de silêncio e depois muita conversa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The river is lower than it was when I was young, and I'm the only one counting.
    >>  ............................................
    pt  Eu pesco. O rio está mais baixo do que quando eu era jovem, e eu sou o único contando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Some weeks the net comes up empty and I don't say so, because people worry.
    >>  ............................................
    pt  Pesca. Em algumas semanas a rede vem vazia e eu não digo, porque as pessoas se preocupam.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman/1
    en  I fish. Out before light, back when there's something to come back with. No mystery in it.
    >>  ............................................
    pt  Eu pesco. Saio antes da luz, volto quando tem algo pra trazer. Sem mistério.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Reading water is the trade; the rod is just what you hold while you do it.
    >>  ............................................
    pt  Pesca. Ler a água é o ofício; a vara é só o que você segura enquanto lê.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman/1
    en  I fish. Out before light, back when there's something to come back with. No mystery in it.
    >>  ............................................
    pt  Eu pesco. Saio antes da luz, volto quando tem algo pra trazer. Sem mistério.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Reading water is the trade; the rod is just what you hold while you do it.
    >>  ............................................
    pt  Pesca. Ler a água é o ofício; a vara é só o que você segura enquanto lê.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman/1
    en  I fish. There's a place under the second willow that I have told nobody about.
    >>  ............................................
    pt  Eu pesco. Tem um lugar sob o segundo salgueiro sobre o qual eu não contei a ninguém.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Mostly it's sitting still, and I was going to be doing that anyway.
    >>  ............................................
    pt  Pesca. É quase só ficar parado, e eu ia fazer isso de qualquer jeito.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The water decides the day and I've never known it to be in a hurry.
    >>  ............................................
    pt  Eu pesco. A água decide o dia e eu nunca a vi com pressa.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Same bend, same hour, thirty years. It has never once felt like the same morning.
    >>  ............................................
    pt  Pesca. Mesma curva, mesma hora, trinta anos. Nunca pareceu a mesma manhã.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman/1
    en  I fish. There's a place under the second willow that I have told nobody about.
    >>  ............................................
    pt  Eu pesco. Tem um lugar sob o segundo salgueiro sobre o qual eu não contei a ninguém.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Mostly it's sitting still, and I was going to be doing that anyway.
    >>  ............................................
    pt  Pesca. É quase só ficar parado, e eu ia fazer isso de qualquer jeito.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The water decides the day and I've never known it to be in a hurry.
    >>  ............................................
    pt  Eu pesco. A água decide o dia e eu nunca a vi com pressa.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Same bend, same hour, thirty years. It has never once felt like the same morning.
    >>  ............................................
    pt  Pesca. Mesma curva, mesma hora, trinta anos. Nunca pareceu a mesma manhã.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman/1
    en  I fish! Some days it's a trade and some days it's an excuse to sit by water. Both are fine.
    >>  ............................................
    pt  Eu pesco! Uns dias é ofício, outros é desculpa pra sentar perto da água. Os dois servem.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. The fish win most mornings, and I keep turning up, which is the sport of it.
    >>  ............................................
    pt  Pesca. Os peixes ganham quase toda manhã, e eu continuo aparecendo, que é a graça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman/1
    en  I fish! Some days it's a trade and some days it's an excuse to sit by water. Both are fine.
    >>  ............................................
    pt  Eu pesco! Uns dias é ofício, outros é desculpa pra sentar perto da água. Os dois servem.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. The fish win most mornings, and I keep turning up, which is the sport of it.
    >>  ............................................
    pt  Pesca. Os peixes ganham quase toda manhã, e eu continuo aparecendo, que é a graça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The water decides the day and I've never known it to be in a hurry.
    >>  ............................................
    pt  Eu pesco. A água decide o dia e eu nunca a vi com pressa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Same bend, same hour, thirty years. It has never once felt like the same morning.
    >>  ............................................
    pt  Pesca. Mesma curva, mesma hora, trinta anos. Nunca pareceu a mesma manhã.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman/1
    en  I fish. The river is lower than it was when I was young, and I'm the only one counting.
    >>  ............................................
    pt  Eu pesco. O rio está mais baixo do que quando eu era jovem, e eu sou o único contando.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Some weeks the net comes up empty and I don't say so, because people worry.
    >>  ............................................
    pt  Pesca. Em algumas semanas a rede vem vazia e eu não digo, porque as pessoas se preocupam.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman/1
    en  I fish. There's a place under the second willow that I have told nobody about.
    >>  ............................................
    pt  Eu pesco. Tem um lugar sob o segundo salgueiro sobre o qual eu não contei a ninguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. Mostly it's sitting still, and I was going to be doing that anyway.
    >>  ............................................
    pt  Pesca. É quase só ficar parado, e eu ia fazer isso de qualquer jeito.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman/1
    en  I fish! Some days it's a trade and some days it's an excuse to sit by water. Both are fine.
    >>  ............................................
    pt  Eu pesco! Uns dias é ofício, outros é desculpa pra sentar perto da água. Os dois servem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. The fish win most mornings, and I keep turning up, which is the sport of it.
    >>  ............................................
    pt  Pesca. Os peixes ganham quase toda manhã, e eu continuo aparecendo, que é a graça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman/1
    en  I fish! Some days it's a trade and some days it's an excuse to sit by water. Both are fine.
    >>  ............................................
    pt  Eu pesco! Uns dias é ofício, outros é desculpa pra sentar perto da água. Os dois servem.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman/2
    en  Fishing. The fish win most mornings, and I keep turning up, which is the sport of it.
    >>  ............................................
    pt  Pesca. Os peixes ganham quase toda manhã, e eu continuo aparecendo, que é a graça.
    >>  ............................................
```

</details>


**Outcome 3 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.respond`
- …where the player's next choices will be: "What do you lose them to?" | "Everyone here wears something you made." | "They mostly look after themselves." | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.identity`: the villager explains. Subject `work.shepherd.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.shepherd/1   [72 chars]
    en  The flock knows me better than most people do. Sheep don't gossip. Much.
    >>  ............................................
    pt  O rebanho me conhece melhor que a maioria das pessoas. Ovelha não fofoca. Muito.
    >>  ............................................
  dialogue.conversations.work.prof.shepherd/2   [70 chars]
    en  Shearing season is one long argument I have with forty woolly lawyers.
    >>  ............................................
    pt  Época de tosquia é uma discussão longa que eu tenho com quarenta advogados de lã.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Wolves take one and unsettle forty, and the counting after is the worst hour of the year.
    >>  ............................................
    pt  Eu cuido de ovelhas. Lobos levam uma e desassossegam quarenta, e a contagem depois é a pior hora do ano.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. I name them, which is a foolish habit that makes the good years better and the bad ones much worse.
    >>  ............................................
    pt  Pastor. Eu dou nome a elas, um hábito bobo que faz os bons anos melhores e os ruins muito piores.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Up the hill, down the hill, and the hill has never once needed me to hurry.
    >>  ............................................
    pt  Eu cuido de ovelhas. Morro acima, morro abaixo, e o morro nunca precisou que eu tivesse pressa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Fourteen years of the same forty animals doing the same forty foolish things.
    >>  ............................................
    pt  Pastor. Catorze anos dos mesmos quarenta bichos fazendo as mesmas quarenta bobagens.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Forty of them, counted three times a day, and I get three different numbers.
    >>  ............................................
    pt  Eu cuido de ovelhas. Quarenta, contadas três vezes por dia, e eu dou três números diferentes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing, lambing and walking the line before dark. Nothing about it is complicated and none of it is easy.
    >>  ............................................
    pt  Pastor. Tosquia, parição e andar a linha antes de escurecer. Nada é complicado e nada é fácil.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Forty of them, counted three times a day, and I get three different numbers.
    >>  ............................................
    pt  Eu cuido de ovelhas. Quarenta, contadas três vezes por dia, e eu dou três números diferentes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing, lambing and walking the line before dark. Nothing about it is complicated and none of it is easy.
    >>  ............................................
    pt  Pastor. Tosquia, parição e andar a linha antes de escurecer. Nada é complicado e nada é fácil.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Every blanket and every child's first jumper in this place walked up that hill first.
    >>  ............................................
    pt  Eu cuido de ovelhas. Todo cobertor e o primeiro suéter de cada criança daqui subiu aquele morro antes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. The weaver and I have an arrangement older than either of us and neither of us wrote it down.
    >>  ............................................
    pt  Pastor. A tecelã e eu temos um acordo mais velho que nós dois e nenhum de nós anotou.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Every blanket and every child's first jumper in this place walked up that hill first.
    >>  ............................................
    pt  Eu cuido de ovelhas. Todo cobertor e o primeiro suéter de cada criança daqui subiu aquele morro antes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. The weaver and I have an arrangement older than either of us and neither of us wrote it down.
    >>  ............................................
    pt  Pastor. A tecelã e eu temos um acordo mais velho que nós dois e nenhum de nós anotou.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Every blanket and every child's first jumper in this place walked up that hill first.
    >>  ............................................
    pt  Eu cuido de ovelhas. Todo cobertor e o primeiro suéter de cada criança daqui subiu aquele morro antes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. The weaver and I have an arrangement older than either of us and neither of us wrote it down.
    >>  ............................................
    pt  Pastor. A tecelã e eu temos um acordo mais velho que nós dois e nenhum de nós anotou.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Wolves take one and unsettle forty, and the counting after is the worst hour of the year.
    >>  ............................................
    pt  Eu cuido de ovelhas. Lobos levam uma e desassossegam quarenta, e a contagem depois é a pior hora do ano.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. I name them, which is a foolish habit that makes the good years better and the bad ones much worse.
    >>  ............................................
    pt  Pastor. Eu dou nome a elas, um hábito bobo que faz os bons anos melhores e os ruins muito piores.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Forty of them, counted three times a day, and I get three different numbers.
    >>  ............................................
    pt  Eu cuido de ovelhas. Quarenta, contadas três vezes por dia, e eu dou três números diferentes.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing, lambing and walking the line before dark. Nothing about it is complicated and none of it is easy.
    >>  ............................................
    pt  Pastor. Tosquia, parição e andar a linha antes de escurecer. Nada é complicado e nada é fácil.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Forty of them, counted three times a day, and I get three different numbers.
    >>  ............................................
    pt  Eu cuido de ovelhas. Quarenta, contadas três vezes por dia, e eu dou três números diferentes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing, lambing and walking the line before dark. Nothing about it is complicated and none of it is easy.
    >>  ............................................
    pt  Pastor. Tosquia, parição e andar a linha antes de escurecer. Nada é complicado e nada é fácil.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. The flock knows me better than most people do, and that suits me exactly.
    >>  ............................................
    pt  Eu cuido de ovelhas. O rebanho me conhece melhor que quase todo mundo, e isso me serve perfeitamente.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing you learn from somebody's hands over yours. It does not go on paper.
    >>  ............................................
    pt  Pastor. Tosquia se aprende com as mãos de alguém sobre as suas. Não vai pro papel.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Up the hill, down the hill, and the hill has never once needed me to hurry.
    >>  ............................................
    pt  Eu cuido de ovelhas. Morro acima, morro abaixo, e o morro nunca precisou que eu tivesse pressa.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Fourteen years of the same forty animals doing the same forty foolish things.
    >>  ............................................
    pt  Pastor. Catorze anos dos mesmos quarenta bichos fazendo as mesmas quarenta bobagens.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. The flock knows me better than most people do, and that suits me exactly.
    >>  ............................................
    pt  Eu cuido de ovelhas. O rebanho me conhece melhor que quase todo mundo, e isso me serve perfeitamente.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing you learn from somebody's hands over yours. It does not go on paper.
    >>  ............................................
    pt  Pastor. Tosquia se aprende com as mãos de alguém sobre as suas. Não vai pro papel.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Up the hill, down the hill, and the hill has never once needed me to hurry.
    >>  ............................................
    pt  Eu cuido de ovelhas. Morro acima, morro abaixo, e o morro nunca precisou que eu tivesse pressa.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Fourteen years of the same forty animals doing the same forty foolish things.
    >>  ............................................
    pt  Pastor. Catorze anos dos mesmos quarenta bichos fazendo as mesmas quarenta bobagens.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd/1
    en  Sheep! They have one idea a day and defend it to the death. I admire the commitment.
    >>  ............................................
    pt  Ovelhas! Elas têm uma ideia por dia e defendem até a morte. Eu admiro o compromisso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shepherd/2
    en  I keep the flock. They don't gossip. Much. Certainly less than the square does.
    >>  ............................................
    pt  Cuido do rebanho. Elas não fofocam. Muito. Certamente menos que a praça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd/1
    en  Sheep! They have one idea a day and defend it to the death. I admire the commitment.
    >>  ............................................
    pt  Ovelhas! Elas têm uma ideia por dia e defendem até a morte. Eu admiro o compromisso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shepherd/2
    en  I keep the flock. They don't gossip. Much. Certainly less than the square does.
    >>  ............................................
    pt  Cuido do rebanho. Elas não fofocam. Muito. Certamente menos que a praça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Up the hill, down the hill, and the hill has never once needed me to hurry.
    >>  ............................................
    pt  Eu cuido de ovelhas. Morro acima, morro abaixo, e o morro nunca precisou que eu tivesse pressa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Fourteen years of the same forty animals doing the same forty foolish things.
    >>  ............................................
    pt  Pastor. Catorze anos dos mesmos quarenta bichos fazendo as mesmas quarenta bobagens.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. Wolves take one and unsettle forty, and the counting after is the worst hour of the year.
    >>  ............................................
    pt  Eu cuido de ovelhas. Lobos levam uma e desassossegam quarenta, e a contagem depois é a pior hora do ano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. I name them, which is a foolish habit that makes the good years better and the bad ones much worse.
    >>  ............................................
    pt  Pastor. Eu dou nome a elas, um hábito bobo que faz os bons anos melhores e os ruins muito piores.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd/1
    en  I keep sheep. The flock knows me better than most people do, and that suits me exactly.
    >>  ............................................
    pt  Eu cuido de ovelhas. O rebanho me conhece melhor que quase todo mundo, e isso me serve perfeitamente.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shepherd/2
    en  Shepherd. Shearing you learn from somebody's hands over yours. It does not go on paper.
    >>  ............................................
    pt  Pastor. Tosquia se aprende com as mãos de alguém sobre as suas. Não vai pro papel.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd/1
    en  Sheep! They have one idea a day and defend it to the death. I admire the commitment.
    >>  ............................................
    pt  Ovelhas! Elas têm uma ideia por dia e defendem até a morte. Eu admiro o compromisso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shepherd/2
    en  I keep the flock. They don't gossip. Much. Certainly less than the square does.
    >>  ............................................
    pt  Cuido do rebanho. Elas não fofocam. Muito. Certamente menos que a praça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd/1
    en  Sheep! They have one idea a day and defend it to the death. I admire the commitment.
    >>  ............................................
    pt  Ovelhas! Elas têm uma ideia por dia e defendem até a morte. Eu admiro o compromisso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shepherd/2
    en  I keep the flock. They don't gossip. Much. Certainly less than the square does.
    >>  ............................................
    pt  Cuido do rebanho. Elas não fofocam. Muito. Certamente menos que a praça.
    >>  ............................................
```

</details>


**Outcome 4 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.respond`
- …where the player's next choices will be: "What makes one go wrong?" | "The guards trust their lives to those." | "It's sticks and feathers." | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.identity`: the villager explains. Subject `work.fletcher.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.fletcher/1   [76 chars]
    en  Every arrow that flies true is a small promise kept. I keep hundreds a week.
    >>  ............................................
    pt  Cada flecha que voa certo é uma pequena promessa cumprida. Cumpro centenas por semana.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher/2   [93 chars]
    en  Feathers, shafts, patience. Mostly patience. The guard breaks them faster than I fletch them.
    >>  ............................................
    pt  Penas, hastes, paciência. Principalmente paciência. O guarda quebra mais rápido do que eu monto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Every one goes somewhere I'll never see, and I think about that more than I say.
    >>  ............................................
    pt  Eu faço flechas. Cada uma vai pra um lugar que eu nunca vou ver, e eu penso nisso mais do que digo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. A rushed batch is the dangerous one. Nobody dies of a slow arrow; they die of a hurried one.
    >>  ............................................
    pt  Flecheiro. Um lote apressado é o perigoso. Ninguém morre de flecha lenta; morre de flecha apressada.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher/1
    en  Arrows. Twelve years and the watch has never run short. That's the whole of what I've got to show.
    >>  ............................................
    pt  Flechas. Doze anos e a guarda nunca ficou sem. É tudo que eu tenho pra mostrar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows. Slowly, in order, and the order matters more than the speed ever has.
    >>  ............................................
    pt  Eu faço flechas. Devagar, em ordem, e a ordem sempre importou mais que a velocidade.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Sixty a week for the watch and every one of them straight, or it doesn't leave the bench.
    >>  ............................................
    pt  Eu faço flechas. Sessenta por semana pra guarda e todas retas, ou não saem da bancada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Feathers, shafts and heads. Get the feather wrong and the arrow goes somewhere you didn't look.
    >>  ............................................
    pt  Flecheiro. Penas, hastes e pontas. Erre a pena e a flecha vai pra onde você não olhou.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Sixty a week for the watch and every one of them straight, or it doesn't leave the bench.
    >>  ............................................
    pt  Eu faço flechas. Sessenta por semana pra guarda e todas retas, ou não saem da bancada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Feathers, shafts and heads. Get the feather wrong and the arrow goes somewhere you didn't look.
    >>  ............................................
    pt  Flecheiro. Penas, hastes e pontas. Erre a pena e a flecha vai pra onde você não olhou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. The archer brings back the broken ones and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Eu faço flechas. A arqueira traz as quebradas e me conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Nobody thanks a fletcher — they thank the archer, and the archer knows better, which is enough.
    >>  ............................................
    pt  Flecheiro. Ninguém agradece um flecheiro — agradecem a arqueira, e ela sabe da verdade, o que basta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. The archer brings back the broken ones and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Eu faço flechas. A arqueira traz as quebradas e me conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Nobody thanks a fletcher — they thank the archer, and the archer knows better, which is enough.
    >>  ............................................
    pt  Flecheiro. Ninguém agradece um flecheiro — agradecem a arqueira, e ela sabe da verdade, o que basta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. The archer brings back the broken ones and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Eu faço flechas. A arqueira traz as quebradas e me conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Nobody thanks a fletcher — they thank the archer, and the archer knows better, which is enough.
    >>  ............................................
    pt  Flecheiro. Ninguém agradece um flecheiro — agradecem a arqueira, e ela sabe da verdade, o que basta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Every one goes somewhere I'll never see, and I think about that more than I say.
    >>  ............................................
    pt  Eu faço flechas. Cada uma vai pra um lugar que eu nunca vou ver, e eu penso nisso mais do que digo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. A rushed batch is the dangerous one. Nobody dies of a slow arrow; they die of a hurried one.
    >>  ............................................
    pt  Flecheiro. Um lote apressado é o perigoso. Ninguém morre de flecha lenta; morre de flecha apressada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Sixty a week for the watch and every one of them straight, or it doesn't leave the bench.
    >>  ............................................
    pt  Eu faço flechas. Sessenta por semana pra guarda e todas retas, ou não saem da bancada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Feathers, shafts and heads. Get the feather wrong and the arrow goes somewhere you didn't look.
    >>  ............................................
    pt  Flecheiro. Penas, hastes e pontas. Erre a pena e a flecha vai pra onde você não olhou.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Sixty a week for the watch and every one of them straight, or it doesn't leave the bench.
    >>  ............................................
    pt  Eu faço flechas. Sessenta por semana pra guarda e todas retas, ou não saem da bancada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. Feathers, shafts and heads. Get the feather wrong and the arrow goes somewhere you didn't look.
    >>  ............................................
    pt  Flecheiro. Penas, hastes e pontas. Erre a pena e a flecha vai pra onde você não olhou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher/1
    en  I sort feathers. It looks like nothing and it decides whether any of them fly straight.
    >>  ............................................
    pt  Eu separo penas. Parece nada e decide se alguma delas voa reto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. There's a wall behind the workshop with a great many holes in it. That's my testing method.
    >>  ............................................
    pt  Flecheiro. Tem uma parede atrás da oficina com muitos furos. É o meu método de teste.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher/1
    en  Arrows. Twelve years and the watch has never run short. That's the whole of what I've got to show.
    >>  ............................................
    pt  Flechas. Doze anos e a guarda nunca ficou sem. É tudo que eu tenho pra mostrar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows. Slowly, in order, and the order matters more than the speed ever has.
    >>  ............................................
    pt  Eu faço flechas. Devagar, em ordem, e a ordem sempre importou mais que a velocidade.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher/1
    en  I sort feathers. It looks like nothing and it decides whether any of them fly straight.
    >>  ............................................
    pt  Eu separo penas. Parece nada e decide se alguma delas voa reto.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. There's a wall behind the workshop with a great many holes in it. That's my testing method.
    >>  ............................................
    pt  Flecheiro. Tem uma parede atrás da oficina com muitos furos. É o meu método de teste.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher/1
    en  Arrows. Twelve years and the watch has never run short. That's the whole of what I've got to show.
    >>  ............................................
    pt  Flechas. Doze anos e a guarda nunca ficou sem. É tudo que eu tenho pra mostrar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows. Slowly, in order, and the order matters more than the speed ever has.
    >>  ............................................
    pt  Eu faço flechas. Devagar, em ordem, e a ordem sempre importou mais que a velocidade.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher/1
    en  Sticks and feathers, they call it. Sticks and feathers that have kept this place standing, mind.
    >>  ............................................
    pt  Gravetos e penas, é como chamam. Gravetos e penas que mantiveram este lugar de pé, veja bem.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows! It's the most satisfying counting job in the village. Sixty, every week, all of them mine.
    >>  ............................................
    pt  Eu faço flechas! É o serviço de contar mais satisfatório do vilarejo. Sessenta, toda semana, todas minhas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher/1
    en  Sticks and feathers, they call it. Sticks and feathers that have kept this place standing, mind.
    >>  ............................................
    pt  Gravetos e penas, é como chamam. Gravetos e penas que mantiveram este lugar de pé, veja bem.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows! It's the most satisfying counting job in the village. Sixty, every week, all of them mine.
    >>  ............................................
    pt  Eu faço flechas! É o serviço de contar mais satisfatório do vilarejo. Sessenta, toda semana, todas minhas.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher/1
    en  Arrows. Twelve years and the watch has never run short. That's the whole of what I've got to show.
    >>  ............................................
    pt  Flechas. Doze anos e a guarda nunca ficou sem. É tudo que eu tenho pra mostrar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows. Slowly, in order, and the order matters more than the speed ever has.
    >>  ............................................
    pt  Eu faço flechas. Devagar, em ordem, e a ordem sempre importou mais que a velocidade.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher/1
    en  I make arrows. Every one goes somewhere I'll never see, and I think about that more than I say.
    >>  ............................................
    pt  Eu faço flechas. Cada uma vai pra um lugar que eu nunca vou ver, e eu penso nisso mais do que digo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. A rushed batch is the dangerous one. Nobody dies of a slow arrow; they die of a hurried one.
    >>  ............................................
    pt  Flecheiro. Um lote apressado é o perigoso. Ninguém morre de flecha lenta; morre de flecha apressada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher/1
    en  I sort feathers. It looks like nothing and it decides whether any of them fly straight.
    >>  ............................................
    pt  Eu separo penas. Parece nada e decide se alguma delas voa reto.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher/2
    en  Fletcher. There's a wall behind the workshop with a great many holes in it. That's my testing method.
    >>  ............................................
    pt  Flecheiro. Tem uma parede atrás da oficina com muitos furos. É o meu método de teste.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher/1
    en  Sticks and feathers, they call it. Sticks and feathers that have kept this place standing, mind.
    >>  ............................................
    pt  Gravetos e penas, é como chamam. Gravetos e penas que mantiveram este lugar de pé, veja bem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows! It's the most satisfying counting job in the village. Sixty, every week, all of them mine.
    >>  ............................................
    pt  Eu faço flechas! É o serviço de contar mais satisfatório do vilarejo. Sessenta, toda semana, todas minhas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher/1
    en  Sticks and feathers, they call it. Sticks and feathers that have kept this place standing, mind.
    >>  ............................................
    pt  Gravetos e penas, é como chamam. Gravetos e penas que mantiveram este lugar de pé, veja bem.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher/2
    en  I make arrows! It's the most satisfying counting job in the village. Sixty, every week, all of them mine.
    >>  ............................................
    pt  Eu faço flechas! É o serviço de contar mais satisfatório do vilarejo. Sessenta, toda semana, todas minhas.
    >>  ............................................
```

</details>


**Outcome 5 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.respond`
- …where the player's next choices will be: "What's the worst thing that happens to a book?" | "We would forget ourselves without you." | "You sit indoors and read." | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.identity`: the villager explains. Subject `work.librarian.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.librarian/1   [94 chars]
    en  I mind the books. They mind me back. Quieter company than the tavern and twice as opinionated.
    >>  ............................................
    pt  Eu cuido dos livros. Eles cuidam de mim. Companhia mais quieta que a taverna e duas vezes mais opinativa.
    >>  ............................................
  dialogue.conversations.work.prof.librarian/2   [95 chars]
    en  People think it's dull work. Those people have never smelled a new-bound book, and I pity them.
    >>  ............................................
    pt  As pessoas acham que é trabalho chato. Essas pessoas nunca sentiram o cheiro de um livro recém-encadernado, e eu tenho pena delas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Damp took four pages of the births ledger last winter and four families lost a date.
    >>  ............................................
    pt  Eu cuido dos livros. A umidade levou quatro páginas do registro no inverno e quatro famílias perderam uma data.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Every name in that ground has a line in my ledger, and the roof is not mine to fix.
    >>  ............................................
    pt  Bibliotecário. Todo nome naquele chão tem uma linha no meu registro, e o telhado não é meu pra consertar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. The ledger is ninety years old and has outlived four librarians. It will outlive a fifth.
    >>  ............................................
    pt  Eu cuido dos livros. O registro tem noventa anos e sobreviveu a quatro bibliotecários. Vai sobreviver a um quinto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Copying pages is slow and there is no faster version of it that is worth having.
    >>  ............................................
    pt  Bibliotecário. Copiar páginas é lento e não existe versão mais rápida que valha.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian/1
    en  I mind the books and the ledgers. When two families argue about a boundary, I am the one who settles it.
    >>  ............................................
    pt  Eu cuido dos livros e dos registros. Quando duas famílias brigam por divisa, sou eu que resolvo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Cataloguing is the trade. Anyone can shelve; knowing where a thing will be looked for is the skill.
    >>  ............................................
    pt  Bibliotecário. Catalogar é o ofício. Qualquer um põe na prateleira; saber onde vão procurar é a habilidade.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian/1
    en  I mind the books and the ledgers. When two families argue about a boundary, I am the one who settles it.
    >>  ............................................
    pt  Eu cuido dos livros e dos registros. Quando duas famílias brigam por divisa, sou eu que resolvo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Cataloguing is the trade. Anyone can shelve; knowing where a thing will be looked for is the skill.
    >>  ............................................
    pt  Bibliotecário. Catalogar é o ofício. Qualquer um põe na prateleira; saber onde vão procurar é a habilidade.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Half this village can read because I stayed open on winter evenings.
    >>  ............................................
    pt  Eu cuido dos livros. Metade deste vilarejo lê porque eu ficava aberto nas noites de inverno.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Come in and stay past the first hour — most people don't, and the ones who do change my year.
    >>  ............................................
    pt  Bibliotecário. Entre e fique além da primeira hora — a maioria não fica, e quem fica muda o meu ano.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Half this village can read because I stayed open on winter evenings.
    >>  ............................................
    pt  Eu cuido dos livros. Metade deste vilarejo lê porque eu ficava aberto nas noites de inverno.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Come in and stay past the first hour — most people don't, and the ones who do change my year.
    >>  ............................................
    pt  Bibliotecário. Entre e fique além da primeira hora — a maioria não fica, e quem fica muda o meu ano.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Half this village can read because I stayed open on winter evenings.
    >>  ............................................
    pt  Eu cuido dos livros. Metade deste vilarejo lê porque eu ficava aberto nas noites de inverno.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Come in and stay past the first hour — most people don't, and the ones who do change my year.
    >>  ............................................
    pt  Bibliotecário. Entre e fique além da primeira hora — a maioria não fica, e quem fica muda o meu ano.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Damp took four pages of the births ledger last winter and four families lost a date.
    >>  ............................................
    pt  Eu cuido dos livros. A umidade levou quatro páginas do registro no inverno e quatro famílias perderam uma data.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Every name in that ground has a line in my ledger, and the roof is not mine to fix.
    >>  ............................................
    pt  Bibliotecário. Todo nome naquele chão tem uma linha no meu registro, e o telhado não é meu pra consertar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian/1
    en  I mind the books and the ledgers. When two families argue about a boundary, I am the one who settles it.
    >>  ............................................
    pt  Eu cuido dos livros e dos registros. Quando duas famílias brigam por divisa, sou eu que resolvo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Cataloguing is the trade. Anyone can shelve; knowing where a thing will be looked for is the skill.
    >>  ............................................
    pt  Bibliotecário. Catalogar é o ofício. Qualquer um põe na prateleira; saber onde vão procurar é a habilidade.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian/1
    en  I mind the books and the ledgers. When two families argue about a boundary, I am the one who settles it.
    >>  ............................................
    pt  Eu cuido dos livros e dos registros. Quando duas famílias brigam por divisa, sou eu que resolvo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Cataloguing is the trade. Anyone can shelve; knowing where a thing will be looked for is the skill.
    >>  ............................................
    pt  Bibliotecário. Catalogar é o ofício. Qualquer um põe na prateleira; saber onde vão procurar é a habilidade.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. Today it's a spine that gave out in somebody's bag, and I have said nothing about whose.
    >>  ............................................
    pt  Eu cuido dos livros. Hoje é uma lombada que se soltou na bolsa de alguém, e eu não disse de quem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian/2
    en  Librarian. I taught myself binding from a book about binding, which is either clever or ridiculous.
    >>  ............................................
    pt  Bibliotecário. Aprendi encadernação num livro sobre encadernação, o que é genial ou ridículo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. The ledger is ninety years old and has outlived four librarians. It will outlive a fifth.
    >>  ............................................
    pt  Eu cuido dos livros. O registro tem noventa anos e sobreviveu a quatro bibliotecários. Vai sobreviver a um quinto.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Copying pages is slow and there is no faster version of it that is worth having.
    >>  ............................................
    pt  Bibliotecário. Copiar páginas é lento e não existe versão mais rápida que valha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. Today it's a spine that gave out in somebody's bag, and I have said nothing about whose.
    >>  ............................................
    pt  Eu cuido dos livros. Hoje é uma lombada que se soltou na bolsa de alguém, e eu não disse de quem.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian/2
    en  Librarian. I taught myself binding from a book about binding, which is either clever or ridiculous.
    >>  ............................................
    pt  Bibliotecário. Aprendi encadernação num livro sobre encadernação, o que é genial ou ridículo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. The ledger is ninety years old and has outlived four librarians. It will outlive a fifth.
    >>  ............................................
    pt  Eu cuido dos livros. O registro tem noventa anos e sobreviveu a quatro bibliotecários. Vai sobreviver a um quinto.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Copying pages is slow and there is no faster version of it that is worth having.
    >>  ............................................
    pt  Bibliotecário. Copiar páginas é lento e não existe versão mais rápida que valha.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian/1
    en  Books! Quieter company than the tavern and about twice as opinionated.
    >>  ............................................
    pt  Livros! Companhia mais quieta que a taverna e umas duas vezes mais opinativa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian/2
    en  I mind the library. It's the only job where sitting down all day counts as diligence.
    >>  ............................................
    pt  Cuido da biblioteca. É o único trabalho em que ficar sentado o dia todo conta como diligência.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian/1
    en  Books! Quieter company than the tavern and about twice as opinionated.
    >>  ............................................
    pt  Livros! Companhia mais quieta que a taverna e umas duas vezes mais opinativa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian/2
    en  I mind the library. It's the only job where sitting down all day counts as diligence.
    >>  ............................................
    pt  Cuido da biblioteca. É o único trabalho em que ficar sentado o dia todo conta como diligência.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. The ledger is ninety years old and has outlived four librarians. It will outlive a fifth.
    >>  ............................................
    pt  Eu cuido dos livros. O registro tem noventa anos e sobreviveu a quatro bibliotecários. Vai sobreviver a um quinto.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Copying pages is slow and there is no faster version of it that is worth having.
    >>  ............................................
    pt  Bibliotecário. Copiar páginas é lento e não existe versão mais rápida que valha.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian/1
    en  I keep the books. Damp took four pages of the births ledger last winter and four families lost a date.
    >>  ............................................
    pt  Eu cuido dos livros. A umidade levou quatro páginas do registro no inverno e quatro famílias perderam uma data.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian/2
    en  Librarian. Every name in that ground has a line in my ledger, and the roof is not mine to fix.
    >>  ............................................
    pt  Bibliotecário. Todo nome naquele chão tem uma linha no meu registro, e o telhado não é meu pra consertar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian/1
    en  I mind the books. Today it's a spine that gave out in somebody's bag, and I have said nothing about whose.
    >>  ............................................
    pt  Eu cuido dos livros. Hoje é uma lombada que se soltou na bolsa de alguém, e eu não disse de quem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian/2
    en  Librarian. I taught myself binding from a book about binding, which is either clever or ridiculous.
    >>  ............................................
    pt  Bibliotecário. Aprendi encadernação num livro sobre encadernação, o que é genial ou ridículo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian/1
    en  Books! Quieter company than the tavern and about twice as opinionated.
    >>  ............................................
    pt  Livros! Companhia mais quieta que a taverna e umas duas vezes mais opinativa.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian/2
    en  I mind the library. It's the only job where sitting down all day counts as diligence.
    >>  ............................................
    pt  Cuido da biblioteca. É o único trabalho em que ficar sentado o dia todo conta como diligência.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian/1
    en  Books! Quieter company than the tavern and about twice as opinionated.
    >>  ............................................
    pt  Livros! Companhia mais quieta que a taverna e umas duas vezes mais opinativa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian/2
    en  I mind the library. It's the only job where sitting down all day counts as diligence.
    >>  ............................................
    pt  Cuido da biblioteca. É o único trabalho em que ficar sentado o dia todo conta como diligência.
    >>  ............................................
```

</details>


**Outcome 6 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.respond`
- …where the player's next choices will be: "What happens when a map is wrong?" | "Every traveller who arrives owes you something." | "You draw lines you've never walked." | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.identity`: the villager explains. Subject `work.cartographer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.cartographer/1   [85 chars]
    en  I draw the world so nobody gets lost the way I once did. Every map is a small rescue.
    >>  ............................................
    pt  Eu desenho o mundo pra ninguém se perder do jeito que eu me perdi. Cada mapa é um pequeno resgate.
    >>  ............................................
  dialogue.conversations.work.prof.cartographer/2   [83 chars]
    en  The edges of the map are my favorite part. That's where the excuses to travel live.
    >>  ............................................
    pt  As bordas do mapa são a minha parte favorita. É ali que moram as desculpas pra viajar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. People trust paper more than they should, and I lie awake about that.
    >>  ............................................
    pt  Eu desenho mapas. As pessoas confiam demais no papel, e eu perco sono com isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cartographer/2
    en  Maps. The ford moved in the spring and mine was wrong for a season. Nobody drowned. It was close.
    >>  ............................................
    pt  Mapas. O vau se moveu na primavera e o meu ficou errado uma estação. Ninguém se afogou. Foi por pouco.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer/1
    en  Maps. It takes a year to do a valley properly, and there's no version that takes less.
    >>  ............................................
    pt  Mapas. Leva um ano pra fazer um vale direito, e não existe versão que leve menos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps. Same route, same hour, same ink. The land doesn't hurry and neither do I.
    >>  ............................................
    pt  Eu desenho mapas. Mesma rota, mesma hora, mesma tinta. A terra não tem pressa e eu também não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. Mine are right, which is rarer than you would like it to be.
    >>  ............................................
    pt  Eu desenho mapas. Os meus estão certos, o que é mais raro do que você gostaria.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cartographer/2
    en  Maps. Pacing, ink and no guessing. A wrong map is worse than no map at all.
    >>  ............................................
    pt  Mapas. Passos, tinta e nenhum chute. Um mapa errado é pior que nenhum mapa.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. Mine are right, which is rarer than you would like it to be.
    >>  ............................................
    pt  Eu desenho mapas. Os meus estão certos, o que é mais raro do que você gostaria.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cartographer/2
    en  Maps. Pacing, ink and no guessing. A wrong map is worse than no map at all.
    >>  ............................................
    pt  Mapas. Passos, tinta e nenhum chute. Um mapa errado é pior que nenhum mapa.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer/1
    en  Maps. Three families arrived here because a sheet of mine reached a town four valleys over.
    >>  ............................................
    pt  Mapas. Três famílias vieram pra cá porque uma folha minha chegou a uma cidade a quatro vales.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps — and then I get to hear where everybody went with them, which is the better half.
    >>  ............................................
    pt  Eu desenho mapas — e depois escuto pra onde todos foram com eles, que é a metade melhor.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer/1
    en  Maps. Three families arrived here because a sheet of mine reached a town four valleys over.
    >>  ............................................
    pt  Mapas. Três famílias vieram pra cá porque uma folha minha chegou a uma cidade a quatro vales.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps — and then I get to hear where everybody went with them, which is the better half.
    >>  ............................................
    pt  Eu desenho mapas — e depois escuto pra onde todos foram com eles, que é a metade melhor.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer/1
    en  Maps. Three families arrived here because a sheet of mine reached a town four valleys over.
    >>  ............................................
    pt  Mapas. Três famílias vieram pra cá porque uma folha minha chegou a uma cidade a quatro vales.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps — and then I get to hear where everybody went with them, which is the better half.
    >>  ............................................
    pt  Eu desenho mapas — e depois escuto pra onde todos foram com eles, que é a metade melhor.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. People trust paper more than they should, and I lie awake about that.
    >>  ............................................
    pt  Eu desenho mapas. As pessoas confiam demais no papel, e eu perco sono com isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cartographer/2
    en  Maps. The ford moved in the spring and mine was wrong for a season. Nobody drowned. It was close.
    >>  ............................................
    pt  Mapas. O vau se moveu na primavera e o meu ficou errado uma estação. Ninguém se afogou. Foi por pouco.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. Mine are right, which is rarer than you would like it to be.
    >>  ............................................
    pt  Eu desenho mapas. Os meus estão certos, o que é mais raro do que você gostaria.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cartographer/2
    en  Maps. Pacing, ink and no guessing. A wrong map is worse than no map at all.
    >>  ............................................
    pt  Mapas. Passos, tinta e nenhum chute. Um mapa errado é pior que nenhum mapa.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. Mine are right, which is rarer than you would like it to be.
    >>  ............................................
    pt  Eu desenho mapas. Os meus estão certos, o que é mais raro do que você gostaria.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cartographer/2
    en  Maps. Pacing, ink and no guessing. A wrong map is worse than no map at all.
    >>  ............................................
    pt  Mapas. Passos, tinta e nenhum chute. Um mapa errado é pior que nenhum mapa.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer/1
    en  I make maps. Half my sheets have a dotted line on them, which is where I stop pretending.
    >>  ............................................
    pt  Eu faço mapas. Metade das minhas folhas tem linha pontilhada, que é onde eu paro de fingir.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cartographer/2
    en  Maps. I count my own paces, all day, out loud, and people have learned not to interrupt.
    >>  ............................................
    pt  Mapas. Eu conto meus próprios passos, o dia todo, em voz alta, e aprenderam a não me interromper.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer/1
    en  Maps. It takes a year to do a valley properly, and there's no version that takes less.
    >>  ............................................
    pt  Mapas. Leva um ano pra fazer um vale direito, e não existe versão que leve menos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps. Same route, same hour, same ink. The land doesn't hurry and neither do I.
    >>  ............................................
    pt  Eu desenho mapas. Mesma rota, mesma hora, mesma tinta. A terra não tem pressa e eu também não.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer/1
    en  I make maps. Half my sheets have a dotted line on them, which is where I stop pretending.
    >>  ............................................
    pt  Eu faço mapas. Metade das minhas folhas tem linha pontilhada, que é onde eu paro de fingir.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cartographer/2
    en  Maps. I count my own paces, all day, out loud, and people have learned not to interrupt.
    >>  ............................................
    pt  Mapas. Eu conto meus próprios passos, o dia todo, em voz alta, e aprenderam a não me interromper.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer/1
    en  Maps. It takes a year to do a valley properly, and there's no version that takes less.
    >>  ............................................
    pt  Mapas. Leva um ano pra fazer um vale direito, e não existe versão que leve menos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps. Same route, same hour, same ink. The land doesn't hurry and neither do I.
    >>  ............................................
    pt  Eu desenho mapas. Mesma rota, mesma hora, mesma tinta. A terra não tem pressa e eu também não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer/1
    en  Maps! The best part of the job is the blank bit at the edge. That's an invitation, that is.
    >>  ............................................
    pt  Mapas! A melhor parte do serviço é o pedaço em branco na borda. Aquilo é um convite.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cartographer/2
    en  I draw the world. Slowly, badly at first, and then one day somebody finds a river where I said.
    >>  ............................................
    pt  Eu desenho o mundo. Devagar, mal no começo, e um dia alguém acha um rio onde eu disse.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer/1
    en  Maps! The best part of the job is the blank bit at the edge. That's an invitation, that is.
    >>  ............................................
    pt  Mapas! A melhor parte do serviço é o pedaço em branco na borda. Aquilo é um convite.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cartographer/2
    en  I draw the world. Slowly, badly at first, and then one day somebody finds a river where I said.
    >>  ............................................
    pt  Eu desenho o mundo. Devagar, mal no começo, e um dia alguém acha um rio onde eu disse.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer/1
    en  Maps. It takes a year to do a valley properly, and there's no version that takes less.
    >>  ............................................
    pt  Mapas. Leva um ano pra fazer um vale direito, e não existe versão que leve menos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cartographer/2
    en  I draw maps. Same route, same hour, same ink. The land doesn't hurry and neither do I.
    >>  ............................................
    pt  Eu desenho mapas. Mesma rota, mesma hora, mesma tinta. A terra não tem pressa e eu também não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer/1
    en  I draw maps. People trust paper more than they should, and I lie awake about that.
    >>  ............................................
    pt  Eu desenho mapas. As pessoas confiam demais no papel, e eu perco sono com isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cartographer/2
    en  Maps. The ford moved in the spring and mine was wrong for a season. Nobody drowned. It was close.
    >>  ............................................
    pt  Mapas. O vau se moveu na primavera e o meu ficou errado uma estação. Ninguém se afogou. Foi por pouco.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer/1
    en  I make maps. Half my sheets have a dotted line on them, which is where I stop pretending.
    >>  ............................................
    pt  Eu faço mapas. Metade das minhas folhas tem linha pontilhada, que é onde eu paro de fingir.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cartographer/2
    en  Maps. I count my own paces, all day, out loud, and people have learned not to interrupt.
    >>  ............................................
    pt  Mapas. Eu conto meus próprios passos, o dia todo, em voz alta, e aprenderam a não me interromper.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer/1
    en  Maps! The best part of the job is the blank bit at the edge. That's an invitation, that is.
    >>  ............................................
    pt  Mapas! A melhor parte do serviço é o pedaço em branco na borda. Aquilo é um convite.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cartographer/2
    en  I draw the world. Slowly, badly at first, and then one day somebody finds a river where I said.
    >>  ............................................
    pt  Eu desenho o mundo. Devagar, mal no começo, e um dia alguém acha um rio onde eu disse.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer/1
    en  Maps! The best part of the job is the blank bit at the edge. That's an invitation, that is.
    >>  ............................................
    pt  Mapas! A melhor parte do serviço é o pedaço em branco na borda. Aquilo é um convite.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cartographer/2
    en  I draw the world. Slowly, badly at first, and then one day somebody finds a river where I said.
    >>  ............................................
    pt  Eu desenho o mundo. Devagar, mal no começo, e um dia alguém acha um rio onde eu disse.
    >>  ............................................
```

</details>


**Outcome 7 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.respond`
- …where the player's next choices will be: "And when it cannot be mended?" | "Half the folk here have cried in front of you." | "Most of what you brew is comfort, not cure." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.identity`: the villager explains. Subject `work.cleric.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.cleric/1   [94 chars]
    en  I tend souls and brew what mends them. Half my job is listening. You'd be surprised who talks.
    >>  ............................................
    pt  Eu cuido de almas e preparo o que as remenda. Metade do meu ofício é escutar. Você se surpreenderia com quem fala.
    >>  ............................................
  dialogue.conversations.work.prof.cleric/2   [102 chars]
    en  Between the candles and the confessions, I hear this village's whole heart. It's a good heart. Mostly.
    >>  ............................................
    pt  Entre as velas e as confissões, eu escuto o coração inteiro desse vilarejo. É um bom coração. Na maior parte.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. There are things I cannot mend and I have to say so out loud, to a face.
    >>  ............................................
    pt  Sou a clériga. Tem coisas que eu não curo e eu tenho que dizer em voz alta, a um rosto.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cleric/2
    en  I tend the ill. Nobody in this village has died alone in eleven years. It is the only number I keep.
    >>  ............................................
    pt  Eu cuido dos doentes. Ninguém neste vilarejo morreu sozinho em onze anos. É o único número que eu guardo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Fevers, bones and long afternoons. Most of it is waiting well.
    >>  ............................................
    pt  Clérigo. Febres, ossos e tardes longas. Quase tudo é esperar bem.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cleric/2
    en  I mend what mends. The rest gets time, and time does more of the work than the bottles do.
    >>  ............................................
    pt  Eu curo o que tem cura. O resto ganha tempo, e o tempo faz mais serviço que os frascos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric/1
    en  I brew what mends people and I sit with the ones it won't. Both are the job.
    >>  ............................................
    pt  Eu preparo o que cura e sento com quem não tem cura. As duas coisas são o serviço.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Bottles, bones and bad news, delivered plainly because every soft version made it worse.
    >>  ............................................
    pt  Clérigo. Frascos, ossos e más notícias, ditos sem rodeio porque toda versão suave piorou.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric/1
    en  I brew what mends people and I sit with the ones it won't. Both are the job.
    >>  ............................................
    pt  Eu preparo o que cura e sento com quem não tem cura. As duas coisas são o serviço.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Bottles, bones and bad news, delivered plainly because every soft version made it worse.
    >>  ............................................
    pt  Clérigo. Frascos, ossos e más notícias, ditos sem rodeio porque toda versão suave piorou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. Which means I've sat in nearly every kitchen in this place at three in the morning.
    >>  ............................................
    pt  Sou a clériga. O que significa que eu já sentei em quase toda cozinha daqui às três da manhã.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cleric/2
    en  I tend people. Come by even when nothing's wrong — half of what I do is being somewhere to come by.
    >>  ............................................
    pt  Eu cuido de gente. Apareça mesmo sem nada errado — metade do que eu faço é estar num lugar pra se aparecer.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. Which means I've sat in nearly every kitchen in this place at three in the morning.
    >>  ............................................
    pt  Sou a clériga. O que significa que eu já sentei em quase toda cozinha daqui às três da manhã.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cleric/2
    en  I tend people. Come by even when nothing's wrong — half of what I do is being somewhere to come by.
    >>  ............................................
    pt  Eu cuido de gente. Apareça mesmo sem nada errado — metade do que eu faço é estar num lugar pra se aparecer.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. Which means I've sat in nearly every kitchen in this place at three in the morning.
    >>  ............................................
    pt  Sou a clériga. O que significa que eu já sentei em quase toda cozinha daqui às três da manhã.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cleric/2
    en  I tend people. Come by even when nothing's wrong — half of what I do is being somewhere to come by.
    >>  ............................................
    pt  Eu cuido de gente. Apareça mesmo sem nada errado — metade do que eu faço é estar num lugar pra se aparecer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. There are things I cannot mend and I have to say so out loud, to a face.
    >>  ............................................
    pt  Sou a clériga. Tem coisas que eu não curo e eu tenho que dizer em voz alta, a um rosto.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cleric/2
    en  I tend the ill. Nobody in this village has died alone in eleven years. It is the only number I keep.
    >>  ............................................
    pt  Eu cuido dos doentes. Ninguém neste vilarejo morreu sozinho em onze anos. É o único número que eu guardo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric/1
    en  I brew what mends people and I sit with the ones it won't. Both are the job.
    >>  ............................................
    pt  Eu preparo o que cura e sento com quem não tem cura. As duas coisas são o serviço.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Bottles, bones and bad news, delivered plainly because every soft version made it worse.
    >>  ............................................
    pt  Clérigo. Frascos, ossos e más notícias, ditos sem rodeio porque toda versão suave piorou.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric/1
    en  I brew what mends people and I sit with the ones it won't. Both are the job.
    >>  ............................................
    pt  Eu preparo o que cura e sento com quem não tem cura. As duas coisas são o serviço.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Bottles, bones and bad news, delivered plainly because every soft version made it worse.
    >>  ............................................
    pt  Clérigo. Frascos, ossos e más notícias, ditos sem rodeio porque toda versão suave piorou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Three bottles for the miller's cough and an afternoon sitting with somebody. That's a day.
    >>  ............................................
    pt  Clériga. Três frascos pra tosse do moleiro e uma tarde sentada com alguém. É um dia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cleric/2
    en  I brew, and I listen. The listening is the difficult one and it looks like doing nothing.
    >>  ............................................
    pt  Eu preparo, e eu escuto. Escutar é o difícil e parece não fazer nada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Fevers, bones and long afternoons. Most of it is waiting well.
    >>  ............................................
    pt  Clérigo. Febres, ossos e tardes longas. Quase tudo é esperar bem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cleric/2
    en  I mend what mends. The rest gets time, and time does more of the work than the bottles do.
    >>  ............................................
    pt  Eu curo o que tem cura. O resto ganha tempo, e o tempo faz mais serviço que os frascos.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Three bottles for the miller's cough and an afternoon sitting with somebody. That's a day.
    >>  ............................................
    pt  Clériga. Três frascos pra tosse do moleiro e uma tarde sentada com alguém. É um dia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cleric/2
    en  I brew, and I listen. The listening is the difficult one and it looks like doing nothing.
    >>  ............................................
    pt  Eu preparo, e eu escuto. Escutar é o difícil e parece não fazer nada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Fevers, bones and long afternoons. Most of it is waiting well.
    >>  ............................................
    pt  Clérigo. Febres, ossos e tardes longas. Quase tudo é esperar bem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cleric/2
    en  I mend what mends. The rest gets time, and time does more of the work than the bottles do.
    >>  ............................................
    pt  Eu curo o que tem cura. O resto ganha tempo, e o tempo faz mais serviço que os frascos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric/1
    en  I mend people. Mostly with tea and stubbornness, occasionally with something stronger.
    >>  ............................................
    pt  Eu conserto gente. Quase sempre com chá e teimosia, às vezes com algo mais forte.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Half medicine, half company, and the company is the part that actually works.
    >>  ............................................
    pt  Clérigo. Metade remédio, metade companhia, e a companhia é a parte que funciona de verdade.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric/1
    en  I mend people. Mostly with tea and stubbornness, occasionally with something stronger.
    >>  ............................................
    pt  Eu conserto gente. Quase sempre com chá e teimosia, às vezes com algo mais forte.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Half medicine, half company, and the company is the part that actually works.
    >>  ............................................
    pt  Clérigo. Metade remédio, metade companhia, e a companhia é a parte que funciona de verdade.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Fevers, bones and long afternoons. Most of it is waiting well.
    >>  ............................................
    pt  Clérigo. Febres, ossos e tardes longas. Quase tudo é esperar bem.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cleric/2
    en  I mend what mends. The rest gets time, and time does more of the work than the bottles do.
    >>  ............................................
    pt  Eu curo o que tem cura. O resto ganha tempo, e o tempo faz mais serviço que os frascos.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric/1
    en  I'm the cleric. There are things I cannot mend and I have to say so out loud, to a face.
    >>  ............................................
    pt  Sou a clériga. Tem coisas que eu não curo e eu tenho que dizer em voz alta, a um rosto.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cleric/2
    en  I tend the ill. Nobody in this village has died alone in eleven years. It is the only number I keep.
    >>  ............................................
    pt  Eu cuido dos doentes. Ninguém neste vilarejo morreu sozinho em onze anos. É o único número que eu guardo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric/1
    en  Cleric. Three bottles for the miller's cough and an afternoon sitting with somebody. That's a day.
    >>  ............................................
    pt  Clériga. Três frascos pra tosse do moleiro e uma tarde sentada com alguém. É um dia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cleric/2
    en  I brew, and I listen. The listening is the difficult one and it looks like doing nothing.
    >>  ............................................
    pt  Eu preparo, e eu escuto. Escutar é o difícil e parece não fazer nada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric/1
    en  I mend people. Mostly with tea and stubbornness, occasionally with something stronger.
    >>  ............................................
    pt  Eu conserto gente. Quase sempre com chá e teimosia, às vezes com algo mais forte.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Half medicine, half company, and the company is the part that actually works.
    >>  ............................................
    pt  Clérigo. Metade remédio, metade companhia, e a companhia é a parte que funciona de verdade.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric/1
    en  I mend people. Mostly with tea and stubbornness, occasionally with something stronger.
    >>  ............................................
    pt  Eu conserto gente. Quase sempre com chá e teimosia, às vezes com algo mais forte.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cleric/2
    en  Cleric. Half medicine, half company, and the company is the part that actually works.
    >>  ............................................
    pt  Clérigo. Metade remédio, metade companhia, e a companhia é a parte que funciona de verdade.
    >>  ............................................
```

</details>


**Outcome 8 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.respond`
- …where the player's next choices will be: "What keeps you up about it?" | "Nobody's died in armour you made." | "It's bashing metal until it fits." | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.identity`: the villager explains. Subject `work.armorer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.armorer/1   [94 chars]
    en  I make the steel that stands between folk and their bad luck. Heavy work. Sleeps well, though.
    >>  ............................................
    pt  Eu faço o aço que fica entre as pessoas e o azar delas. Trabalho pesado. Mas eu durmo bem.
    >>  ............................................
  dialogue.conversations.work.prof.armorer/2   [89 chars]
    en  Every dent in a breastplate I made is a funeral that didn't happen. I count them proudly.
    >>  ............................................
    pt  Cada amassado num peitoral que eu fiz é um enterro que não aconteceu. Conto todos com orgulho.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.armorer/1
    en  I make armour, and I find out whether I got it right only when somebody comes home.
    >>  ............................................
    pt  Eu faço armadura, e só descubro se acertei quando alguém volta pra casa.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.armorer/2
    en  Steel, for the watch. Every piece is a bet I've made on somebody else's evening.
    >>  ............................................
    pt  Aço, pra guarda. Cada peça é uma aposta que eu fiz na noite de outra pessoa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer/1
    en  Armour. Nineteen years of it, and the fire is the same fire every morning.
    >>  ............................................
    pt  Armadura. Dezenove anos disso, e o fogo é o mesmo fogo toda manhã.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.armorer/2
    en  I beat steel into shapes that keep people whole. It's slow, and slow is the point of it.
    >>  ............................................
    pt  Eu bato aço em formas que mantêm gente inteira. É lento, e o lento é a questão.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer/1
    en  Plate. Made properly, fitted to one man, and it comes back dented instead of through.
    >>  ............................................
    pt  Placa. Bem feita, ajustada a um homem, e volta amassada em vez de furada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.armorer/2
    en  I work steel. Anyone can beat a sheet flat; making it fit a shoulder is the trade.
    >>  ............................................
    pt  Eu trabalho aço. Qualquer um achata uma chapa; fazer servir num ombro é o ofício.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer/1
    en  Plate. Made properly, fitted to one man, and it comes back dented instead of through.
    >>  ............................................
    pt  Placa. Bem feita, ajustada a um homem, e volta amassada em vez de furada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.armorer/2
    en  I work steel. Anyone can beat a sheet flat; making it fit a shoulder is the trade.
    >>  ............................................
    pt  Eu trabalho aço. Qualquer um achata uma chapa; fazer servir num ombro é o ofício.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer/1
    en  I fit armour. Which means I know the shape of everyone on that watch, and their mothers.
    >>  ............................................
    pt  Eu ajusto armadura. O que significa que eu sei o formato de toda a guarda, e das mães delas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.armorer/2
    en  Armour. Come by when the guard's here — the fittings are the best conversation in the village.
    >>  ............................................
    pt  Armadura. Apareça quando o guarda estiver aqui — os ajustes são a melhor conversa do vilarejo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer/1
    en  I fit armour. Which means I know the shape of everyone on that watch, and their mothers.
    >>  ............................................
    pt  Eu ajusto armadura. O que significa que eu sei o formato de toda a guarda, e das mães delas.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.armorer/2
    en  Armour. Come by when the guard's here — the fittings are the best conversation in the village.
    >>  ............................................
    pt  Armadura. Apareça quando o guarda estiver aqui — os ajustes são a melhor conversa do vilarejo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer/1
    en  I fit armour. Which means I know the shape of everyone on that watch, and their mothers.
    >>  ............................................
    pt  Eu ajusto armadura. O que significa que eu sei o formato de toda a guarda, e das mães delas.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.armorer/2
    en  Armour. Come by when the guard's here — the fittings are the best conversation in the village.
    >>  ............................................
    pt  Armadura. Apareça quando o guarda estiver aqui — os ajustes são a melhor conversa do vilarejo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer/1
    en  I make armour, and I find out whether I got it right only when somebody comes home.
    >>  ............................................
    pt  Eu faço armadura, e só descubro se acertei quando alguém volta pra casa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.armorer/2
    en  Steel, for the watch. Every piece is a bet I've made on somebody else's evening.
    >>  ............................................
    pt  Aço, pra guarda. Cada peça é uma aposta que eu fiz na noite de outra pessoa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer/1
    en  Plate. Made properly, fitted to one man, and it comes back dented instead of through.
    >>  ............................................
    pt  Placa. Bem feita, ajustada a um homem, e volta amassada em vez de furada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.armorer/2
    en  I work steel. Anyone can beat a sheet flat; making it fit a shoulder is the trade.
    >>  ............................................
    pt  Eu trabalho aço. Qualquer um achata uma chapa; fazer servir num ombro é o ofício.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer/1
    en  Plate. Made properly, fitted to one man, and it comes back dented instead of through.
    >>  ............................................
    pt  Placa. Bem feita, ajustada a um homem, e volta amassada em vez de furada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.armorer/2
    en  I work steel. Anyone can beat a sheet flat; making it fit a shoulder is the trade.
    >>  ............................................
    pt  Eu trabalho aço. Qualquer um achata uma chapa; fazer servir num ombro é o ofício.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer/1
    en  Plate, mail, rivets. Mostly rivets, if I'm honest about how the days go.
    >>  ............................................
    pt  Placa, malha, rebites. Rebites, principalmente, se eu for honesto sobre como os dias vão.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.armorer/2
    en  I make armour. There's a breastplate on the third stand that took me nine weeks and nobody's asked.
    >>  ............................................
    pt  Eu faço armadura. Tem um peitoral no terceiro suporte que levou nove semanas e ninguém perguntou.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer/1
    en  Armour. Nineteen years of it, and the fire is the same fire every morning.
    >>  ............................................
    pt  Armadura. Dezenove anos disso, e o fogo é o mesmo fogo toda manhã.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.armorer/2
    en  I beat steel into shapes that keep people whole. It's slow, and slow is the point of it.
    >>  ............................................
    pt  Eu bato aço em formas que mantêm gente inteira. É lento, e o lento é a questão.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer/1
    en  Plate, mail, rivets. Mostly rivets, if I'm honest about how the days go.
    >>  ............................................
    pt  Placa, malha, rebites. Rebites, principalmente, se eu for honesto sobre como os dias vão.
    >>  ............................................
  odd.dialogue.conversations.work.prof.armorer/2
    en  I make armour. There's a breastplate on the third stand that took me nine weeks and nobody's asked.
    >>  ............................................
    pt  Eu faço armadura. Tem um peitoral no terceiro suporte que levou nove semanas e ninguém perguntou.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer/1
    en  Armour. Nineteen years of it, and the fire is the same fire every morning.
    >>  ............................................
    pt  Armadura. Dezenove anos disso, e o fogo é o mesmo fogo toda manhã.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.armorer/2
    en  I beat steel into shapes that keep people whole. It's slow, and slow is the point of it.
    >>  ............................................
    pt  Eu bato aço em formas que mantêm gente inteira. É lento, e o lento é a questão.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer/1
    en  I make the shiny things that keep people un-punctured. Very satisfying trade, that.
    >>  ............................................
    pt  Eu faço as coisas brilhantes que mantêm as pessoas sem furos. Ofício muito satisfatório.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.armorer/2
    en  Hot, loud, and every so often something walks out of here looking magnificent. I'll take it.
    >>  ............................................
    pt  Quente, barulhento, e de vez em quando algo sai daqui parecendo magnífico. Eu aceito.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer/1
    en  I make the shiny things that keep people un-punctured. Very satisfying trade, that.
    >>  ............................................
    pt  Eu faço as coisas brilhantes que mantêm as pessoas sem furos. Ofício muito satisfatório.
    >>  ............................................
  playful.dialogue.conversations.work.prof.armorer/2
    en  Hot, loud, and every so often something walks out of here looking magnificent. I'll take it.
    >>  ............................................
    pt  Quente, barulhento, e de vez em quando algo sai daqui parecendo magnífico. Eu aceito.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer/1
    en  Armour. Nineteen years of it, and the fire is the same fire every morning.
    >>  ............................................
    pt  Armadura. Dezenove anos disso, e o fogo é o mesmo fogo toda manhã.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.armorer/2
    en  I beat steel into shapes that keep people whole. It's slow, and slow is the point of it.
    >>  ............................................
    pt  Eu bato aço em formas que mantêm gente inteira. É lento, e o lento é a questão.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer/1
    en  I make armour, and I find out whether I got it right only when somebody comes home.
    >>  ............................................
    pt  Eu faço armadura, e só descubro se acertei quando alguém volta pra casa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.armorer/2
    en  Steel, for the watch. Every piece is a bet I've made on somebody else's evening.
    >>  ............................................
    pt  Aço, pra guarda. Cada peça é uma aposta que eu fiz na noite de outra pessoa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer/1
    en  Plate, mail, rivets. Mostly rivets, if I'm honest about how the days go.
    >>  ............................................
    pt  Placa, malha, rebites. Rebites, principalmente, se eu for honesto sobre como os dias vão.
    >>  ............................................
  shy.dialogue.conversations.work.prof.armorer/2
    en  I make armour. There's a breastplate on the third stand that took me nine weeks and nobody's asked.
    >>  ............................................
    pt  Eu faço armadura. Tem um peitoral no terceiro suporte que levou nove semanas e ninguém perguntou.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer/1
    en  I make the shiny things that keep people un-punctured. Very satisfying trade, that.
    >>  ............................................
    pt  Eu faço as coisas brilhantes que mantêm as pessoas sem furos. Ofício muito satisfatório.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.armorer/2
    en  Hot, loud, and every so often something walks out of here looking magnificent. I'll take it.
    >>  ............................................
    pt  Quente, barulhento, e de vez em quando algo sai daqui parecendo magnífico. Eu aceito.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer/1
    en  I make the shiny things that keep people un-punctured. Very satisfying trade, that.
    >>  ............................................
    pt  Eu faço as coisas brilhantes que mantêm as pessoas sem furos. Ofício muito satisfatório.
    >>  ............................................
  witty.dialogue.conversations.work.prof.armorer/2
    en  Hot, loud, and every so often something walks out of here looking magnificent. I'll take it.
    >>  ............................................
    pt  Quente, barulhento, e de vez em quando algo sai daqui parecendo magnífico. Eu aceito.
    >>  ............................................
```

</details>


**Outcome 9 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.respond`
- …where the player's next choices will be: "How do you choose the hands?" | "The guards would be holding sticks without you." | "You make things for hurting people." | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.identity`: the villager explains. Subject `work.weaponsmith.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.weaponsmith/1   [98 chars]
    en  Blades are honest tools — they only ever do what the hand asks. I try to like the hands I sell to.
    >>  ............................................
    pt  Lâmina é ferramenta honesta — só faz o que a mão pede. Eu tento gostar das mãos pra quem eu vendo.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith/2   [82 chars]
    en  The forge doesn't care about your feelings, which is exactly what I like about it.
    >>  ............................................
    pt  A forja não liga pros seus sentimentos, que é exatamente o que eu gosto nela.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Everything I sell can be used badly and I have no say after the coin changes hands.
    >>  ............................................
    pt  Eu forjo lâminas. Tudo que eu vendo pode ser usado mal e eu não tenho voz depois que a moeda troca de mão.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. There's a blade of mine somewhere I think about. I sold it to a man I should not have.
    >>  ............................................
    pt  Armeiro. Tem uma lâmina minha em algum lugar em que eu penso. Vendi a um homem a quem eu não devia.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Steel has opinions and the fire changes them temporarily. There's no rushing that.
    >>  ............................................
    pt  Eu forjo lâminas. O aço tem opinião e o fogo muda ela temporariamente. Não dá pra apressar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Twenty years at one anvil. I've turned down more work each year than the year before.
    >>  ............................................
    pt  Armeiro. Vinte anos numa bigorna. Recuso mais serviço a cada ano que passa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Temper is the whole of it — anyone can shape steel, almost nobody can make it stay shaped.
    >>  ............................................
    pt  Eu forjo lâminas. Têmpera é tudo — qualquer um dá forma ao aço, quase ninguém faz ele manter a forma.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I ask what it's for and I watch how long they take to answer. That is my whole method.
    >>  ............................................
    pt  Armeiro. Pergunto pra que serve e observo quanto tempo levam pra responder. É todo o meu método.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Temper is the whole of it — anyone can shape steel, almost nobody can make it stay shaped.
    >>  ............................................
    pt  Eu forjo lâminas. Têmpera é tudo — qualquer um dá forma ao aço, quase ninguém faz ele manter a forma.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I ask what it's for and I watch how long they take to answer. That is my whole method.
    >>  ............................................
    pt  Armeiro. Pergunto pra que serve e observo quanto tempo levam pra responder. É todo o meu método.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. I made the one the guard carried the night of the raid, and nobody has mentioned it to me.
    >>  ............................................
    pt  Eu forjo lâminas. Fiz a que o guarda carregou na noite do ataque, e ninguém mencionou pra mim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Work the bellows for me some afternoon — steady and slow, and I'll talk your ear off.
    >>  ............................................
    pt  Armeiro. Puxe o fole pra mim numa tarde — firme e devagar, e eu falo até você cansar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. I made the one the guard carried the night of the raid, and nobody has mentioned it to me.
    >>  ............................................
    pt  Eu forjo lâminas. Fiz a que o guarda carregou na noite do ataque, e ninguém mencionou pra mim.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Work the bellows for me some afternoon — steady and slow, and I'll talk your ear off.
    >>  ............................................
    pt  Armeiro. Puxe o fole pra mim numa tarde — firme e devagar, e eu falo até você cansar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. I made the one the guard carried the night of the raid, and nobody has mentioned it to me.
    >>  ............................................
    pt  Eu forjo lâminas. Fiz a que o guarda carregou na noite do ataque, e ninguém mencionou pra mim.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Work the bellows for me some afternoon — steady and slow, and I'll talk your ear off.
    >>  ............................................
    pt  Armeiro. Puxe o fole pra mim numa tarde — firme e devagar, e eu falo até você cansar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Everything I sell can be used badly and I have no say after the coin changes hands.
    >>  ............................................
    pt  Eu forjo lâminas. Tudo que eu vendo pode ser usado mal e eu não tenho voz depois que a moeda troca de mão.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. There's a blade of mine somewhere I think about. I sold it to a man I should not have.
    >>  ............................................
    pt  Armeiro. Tem uma lâmina minha em algum lugar em que eu penso. Vendi a um homem a quem eu não devia.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Temper is the whole of it — anyone can shape steel, almost nobody can make it stay shaped.
    >>  ............................................
    pt  Eu forjo lâminas. Têmpera é tudo — qualquer um dá forma ao aço, quase ninguém faz ele manter a forma.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I ask what it's for and I watch how long they take to answer. That is my whole method.
    >>  ............................................
    pt  Armeiro. Pergunto pra que serve e observo quanto tempo levam pra responder. É todo o meu método.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Temper is the whole of it — anyone can shape steel, almost nobody can make it stay shaped.
    >>  ............................................
    pt  Eu forjo lâminas. Têmpera é tudo — qualquer um dá forma ao aço, quase ninguém faz ele manter a forma.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I ask what it's for and I watch how long they take to answer. That is my whole method.
    >>  ............................................
    pt  Armeiro. Pergunto pra que serve e observo quanto tempo levam pra responder. É todo o meu método.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge. Today it's re-tempering a blade somebody left in a damp scabbard. It is salvageable. Barely.
    >>  ............................................
    pt  Eu forjo. Hoje é retemperar uma lâmina que alguém deixou numa bainha úmida. Dá pra salvar. Por pouco.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I learned edges from a man who never explained anything. I watched for four years.
    >>  ............................................
    pt  Armeiro. Aprendi fios com um homem que nunca explicou nada. Observei por quatro anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Steel has opinions and the fire changes them temporarily. There's no rushing that.
    >>  ............................................
    pt  Eu forjo lâminas. O aço tem opinião e o fogo muda ela temporariamente. Não dá pra apressar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Twenty years at one anvil. I've turned down more work each year than the year before.
    >>  ............................................
    pt  Armeiro. Vinte anos numa bigorna. Recuso mais serviço a cada ano que passa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge. Today it's re-tempering a blade somebody left in a damp scabbard. It is salvageable. Barely.
    >>  ............................................
    pt  Eu forjo. Hoje é retemperar uma lâmina que alguém deixou numa bainha úmida. Dá pra salvar. Por pouco.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I learned edges from a man who never explained anything. I watched for four years.
    >>  ............................................
    pt  Armeiro. Aprendi fios com um homem que nunca explicou nada. Observei por quatro anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Steel has opinions and the fire changes them temporarily. There's no rushing that.
    >>  ............................................
    pt  Eu forjo lâminas. O aço tem opinião e o fogo muda ela temporariamente. Não dá pra apressar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Twenty years at one anvil. I've turned down more work each year than the year before.
    >>  ............................................
    pt  Armeiro. Vinte anos numa bigorna. Recuso mais serviço a cada ano que passa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith/1
    en  Blades are honest tools. They only ever do what the hand asks, which is the interesting problem.
    >>  ............................................
    pt  Lâminas são ferramentas honestas. Só fazem o que a mão pede, que é o problema interessante.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith/2
    en  I forge. Hot, loud, and every so often a thing leaves this shop that I'm quietly delighted with.
    >>  ............................................
    pt  Eu forjo. Quente, barulhento, e de vez em quando sai daqui algo com que eu fico secretamente encantado.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith/1
    en  Blades are honest tools. They only ever do what the hand asks, which is the interesting problem.
    >>  ............................................
    pt  Lâminas são ferramentas honestas. Só fazem o que a mão pede, que é o problema interessante.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith/2
    en  I forge. Hot, loud, and every so often a thing leaves this shop that I'm quietly delighted with.
    >>  ............................................
    pt  Eu forjo. Quente, barulhento, e de vez em quando sai daqui algo com que eu fico secretamente encantado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Steel has opinions and the fire changes them temporarily. There's no rushing that.
    >>  ............................................
    pt  Eu forjo lâminas. O aço tem opinião e o fogo muda ela temporariamente. Não dá pra apressar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. Twenty years at one anvil. I've turned down more work each year than the year before.
    >>  ............................................
    pt  Armeiro. Vinte anos numa bigorna. Recuso mais serviço a cada ano que passa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge blades. Everything I sell can be used badly and I have no say after the coin changes hands.
    >>  ............................................
    pt  Eu forjo lâminas. Tudo que eu vendo pode ser usado mal e eu não tenho voz depois que a moeda troca de mão.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. There's a blade of mine somewhere I think about. I sold it to a man I should not have.
    >>  ............................................
    pt  Armeiro. Tem uma lâmina minha em algum lugar em que eu penso. Vendi a um homem a quem eu não devia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith/1
    en  I forge. Today it's re-tempering a blade somebody left in a damp scabbard. It is salvageable. Barely.
    >>  ............................................
    pt  Eu forjo. Hoje é retemperar uma lâmina que alguém deixou numa bainha úmida. Dá pra salvar. Por pouco.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith/2
    en  Weaponsmith. I learned edges from a man who never explained anything. I watched for four years.
    >>  ............................................
    pt  Armeiro. Aprendi fios com um homem que nunca explicou nada. Observei por quatro anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith/1
    en  Blades are honest tools. They only ever do what the hand asks, which is the interesting problem.
    >>  ............................................
    pt  Lâminas são ferramentas honestas. Só fazem o que a mão pede, que é o problema interessante.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith/2
    en  I forge. Hot, loud, and every so often a thing leaves this shop that I'm quietly delighted with.
    >>  ............................................
    pt  Eu forjo. Quente, barulhento, e de vez em quando sai daqui algo com que eu fico secretamente encantado.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith/1
    en  Blades are honest tools. They only ever do what the hand asks, which is the interesting problem.
    >>  ............................................
    pt  Lâminas são ferramentas honestas. Só fazem o que a mão pede, que é o problema interessante.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith/2
    en  I forge. Hot, loud, and every so often a thing leaves this shop that I'm quietly delighted with.
    >>  ............................................
    pt  Eu forjo. Quente, barulhento, e de vez em quando sai daqui algo com que eu fico secretamente encantado.
    >>  ............................................
```

</details>


**Outcome 10 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.respond`
- …where the player's next choices will be: "What comes back most often?" | "Nothing in this village gets built without you." | "It's the dull end of smithing." | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.identity`: the villager explains. Subject `work.toolsmith.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.toolsmith/1   [103 chars]
    en  Shovels, hoes, picks — unglamorous things that build everything. Somebody has to make the quiet heroes.
    >>  ............................................
    pt  Pás, enxadas, picaretas — coisas sem glamour que constroem tudo. Alguém tem que fazer os heróis silenciosos.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith/2   [87 chars]
    en  A good tool outlives its maker. I'm leaving this village a hundred small immortalities.
    >>  ............................................
    pt  Uma boa ferramenta sobrevive a quem a fez. Estou deixando pra esse vilarejo umas cem pequenas imortalidades.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. A tool that fails does it at the worst moment, because that's when it was being used hardest.
    >>  ............................................
    pt  Eu faço ferramentas. Uma ferramenta falha no pior momento, porque é quando estava sendo mais usada.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Nobody dies of a bad hoe. They do lose a finger, and I have seen it, and I made that hoe.
    >>  ............................................
    pt  Ferramenteiro. Ninguém morre de enxada ruim. Mas perde um dedo, e eu já vi, e eu fiz aquela enxada.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. The queue never ends, and on the day it does something has gone wrong somewhere.
    >>  ............................................
    pt  Eu faço ferramentas. A fila nunca acaba, e no dia em que acabar algo deu errado em algum lugar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Same nine tools, same farms, same seasons. There is a great deal of comfort in that.
    >>  ............................................
    pt  Ferramenteiro. Mesmas nove ferramentas, mesmas fazendas, mesmas estações. Tem muito conforto nisso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith/1
    en  I make the tools everything else is built with. Nine hoes on the bench today, all blunted the same way.
    >>  ............................................
    pt  Faço as ferramentas com que tudo é construído. Nove enxadas na bancada hoje, todas cegas do mesmo jeito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. A good handle is the whole tool. The head is the part people look at and the least of the problem.
    >>  ............................................
    pt  Ferramenteiro. Um bom cabo é a ferramenta inteira. A cabeça é a parte que olham e o menor dos problemas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith/1
    en  I make the tools everything else is built with. Nine hoes on the bench today, all blunted the same way.
    >>  ............................................
    pt  Faço as ferramentas com que tudo é construído. Nove enxadas na bancada hoje, todas cegas do mesmo jeito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. A good handle is the whole tool. The head is the part people look at and the least of the problem.
    >>  ............................................
    pt  Ferramenteiro. Um bom cabo é a ferramenta inteira. A cabeça é a parte que olham e o menor dos problemas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Every child in this place gets their first real one from me, and I don't charge for those.
    >>  ............................................
    pt  Eu faço ferramentas. Toda criança daqui ganha a primeira de verdade comigo, e essas eu não cobro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Bring me a worn tool and I'll tell you how its owner stands. I'm right more than I'm not.
    >>  ............................................
    pt  Ferramenteiro. Traga uma ferramenta gasta e eu digo como o dono se posiciona. Acerto mais que erro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Every child in this place gets their first real one from me, and I don't charge for those.
    >>  ............................................
    pt  Eu faço ferramentas. Toda criança daqui ganha a primeira de verdade comigo, e essas eu não cobro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Bring me a worn tool and I'll tell you how its owner stands. I'm right more than I'm not.
    >>  ............................................
    pt  Ferramenteiro. Traga uma ferramenta gasta e eu digo como o dono se posiciona. Acerto mais que erro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Every child in this place gets their first real one from me, and I don't charge for those.
    >>  ............................................
    pt  Eu faço ferramentas. Toda criança daqui ganha a primeira de verdade comigo, e essas eu não cobro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Bring me a worn tool and I'll tell you how its owner stands. I'm right more than I'm not.
    >>  ............................................
    pt  Ferramenteiro. Traga uma ferramenta gasta e eu digo como o dono se posiciona. Acerto mais que erro.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. A tool that fails does it at the worst moment, because that's when it was being used hardest.
    >>  ............................................
    pt  Eu faço ferramentas. Uma ferramenta falha no pior momento, porque é quando estava sendo mais usada.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Nobody dies of a bad hoe. They do lose a finger, and I have seen it, and I made that hoe.
    >>  ............................................
    pt  Ferramenteiro. Ninguém morre de enxada ruim. Mas perde um dedo, e eu já vi, e eu fiz aquela enxada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith/1
    en  I make the tools everything else is built with. Nine hoes on the bench today, all blunted the same way.
    >>  ............................................
    pt  Faço as ferramentas com que tudo é construído. Nove enxadas na bancada hoje, todas cegas do mesmo jeito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. A good handle is the whole tool. The head is the part people look at and the least of the problem.
    >>  ............................................
    pt  Ferramenteiro. Um bom cabo é a ferramenta inteira. A cabeça é a parte que olham e o menor dos problemas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith/1
    en  I make the tools everything else is built with. Nine hoes on the bench today, all blunted the same way.
    >>  ............................................
    pt  Faço as ferramentas com que tudo é construído. Nove enxadas na bancada hoje, todas cegas do mesmo jeito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. A good handle is the whole tool. The head is the part people look at and the least of the problem.
    >>  ............................................
    pt  Ferramenteiro. Um bom cabo é a ferramenta inteira. A cabeça é a parte que olham e o menor dos problemas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Ash, seasoned two years, and no varnish. Varnish is a lie you can feel through your palm.
    >>  ............................................
    pt  Eu faço ferramentas. Freixo, curado dois anos, e sem verniz. Verniz é mentira que se sente na palma.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. I read wear. Twenty years of the same nine tools coming back teaches anyone that.
    >>  ............................................
    pt  Ferramenteiro. Eu leio desgaste. Vinte anos das mesmas nove ferramentas voltando ensinam qualquer um.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. The queue never ends, and on the day it does something has gone wrong somewhere.
    >>  ............................................
    pt  Eu faço ferramentas. A fila nunca acaba, e no dia em que acabar algo deu errado em algum lugar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Same nine tools, same farms, same seasons. There is a great deal of comfort in that.
    >>  ............................................
    pt  Ferramenteiro. Mesmas nove ferramentas, mesmas fazendas, mesmas estações. Tem muito conforto nisso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Ash, seasoned two years, and no varnish. Varnish is a lie you can feel through your palm.
    >>  ............................................
    pt  Eu faço ferramentas. Freixo, curado dois anos, e sem verniz. Verniz é mentira que se sente na palma.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. I read wear. Twenty years of the same nine tools coming back teaches anyone that.
    >>  ............................................
    pt  Ferramenteiro. Eu leio desgaste. Vinte anos das mesmas nove ferramentas voltando ensinam qualquer um.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. The queue never ends, and on the day it does something has gone wrong somewhere.
    >>  ............................................
    pt  Eu faço ferramentas. A fila nunca acaba, e no dia em que acabar algo deu errado em algum lugar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Same nine tools, same farms, same seasons. There is a great deal of comfort in that.
    >>  ............................................
    pt  Ferramenteiro. Mesmas nove ferramentas, mesmas fazendas, mesmas estações. Tem muito conforto nisso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith/1
    en  Shovels, hoes, picks — the unglamorous things that build everything. Somebody has to make the quiet heroes.
    >>  ............................................
    pt  Pás, enxadas, picaretas — as coisas sem glamour que constroem tudo. Alguém tem que fazer os heróis quietos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith/2
    en  I make tools! Nobody ever writes a song about a hoe and yet here we all are, eating.
    >>  ............................................
    pt  Eu faço ferramentas! Ninguém escreve música sobre enxada e no entanto aqui estamos todos, comendo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith/1
    en  Shovels, hoes, picks — the unglamorous things that build everything. Somebody has to make the quiet heroes.
    >>  ............................................
    pt  Pás, enxadas, picaretas — as coisas sem glamour que constroem tudo. Alguém tem que fazer os heróis quietos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith/2
    en  I make tools! Nobody ever writes a song about a hoe and yet here we all are, eating.
    >>  ............................................
    pt  Eu faço ferramentas! Ninguém escreve música sobre enxada e no entanto aqui estamos todos, comendo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. The queue never ends, and on the day it does something has gone wrong somewhere.
    >>  ............................................
    pt  Eu faço ferramentas. A fila nunca acaba, e no dia em que acabar algo deu errado em algum lugar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Same nine tools, same farms, same seasons. There is a great deal of comfort in that.
    >>  ............................................
    pt  Ferramenteiro. Mesmas nove ferramentas, mesmas fazendas, mesmas estações. Tem muito conforto nisso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. A tool that fails does it at the worst moment, because that's when it was being used hardest.
    >>  ............................................
    pt  Eu faço ferramentas. Uma ferramenta falha no pior momento, porque é quando estava sendo mais usada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. Nobody dies of a bad hoe. They do lose a finger, and I have seen it, and I made that hoe.
    >>  ............................................
    pt  Ferramenteiro. Ninguém morre de enxada ruim. Mas perde um dedo, e eu já vi, e eu fiz aquela enxada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith/1
    en  I make tools. Ash, seasoned two years, and no varnish. Varnish is a lie you can feel through your palm.
    >>  ............................................
    pt  Eu faço ferramentas. Freixo, curado dois anos, e sem verniz. Verniz é mentira que se sente na palma.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith/2
    en  Toolsmith. I read wear. Twenty years of the same nine tools coming back teaches anyone that.
    >>  ............................................
    pt  Ferramenteiro. Eu leio desgaste. Vinte anos das mesmas nove ferramentas voltando ensinam qualquer um.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith/1
    en  Shovels, hoes, picks — the unglamorous things that build everything. Somebody has to make the quiet heroes.
    >>  ............................................
    pt  Pás, enxadas, picaretas — as coisas sem glamour que constroem tudo. Alguém tem que fazer os heróis quietos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith/2
    en  I make tools! Nobody ever writes a song about a hoe and yet here we all are, eating.
    >>  ............................................
    pt  Eu faço ferramentas! Ninguém escreve música sobre enxada e no entanto aqui estamos todos, comendo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith/1
    en  Shovels, hoes, picks — the unglamorous things that build everything. Somebody has to make the quiet heroes.
    >>  ............................................
    pt  Pás, enxadas, picaretas — as coisas sem glamour que constroem tudo. Alguém tem que fazer os heróis quietos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith/2
    en  I make tools! Nobody ever writes a song about a hoe and yet here we all are, eating.
    >>  ............................................
    pt  Eu faço ferramentas! Ninguém escreve música sobre enxada e no entanto aqui estamos todos, comendo.
    >>  ............................................
```

</details>


**Outcome 11 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.respond`
- …where the player's next choices will be: "What's the part you don't like?" | "Nobody in this village goes hungry." | "It's a cruel way to make a living." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.identity`: the villager explains. Subject `work.butcher.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.butcher/1   [99 chars]
    en  It's blunt work, but nobody in this village goes hungry on my watch. That's the whole ethics of it.
    >>  ............................................
    pt  É trabalho bruto, mas ninguém nesse vilarejo passa fome no meu turno. É essa a ética toda.
    >>  ............................................
  dialogue.conversations.work.prof.butcher/2   [93 chars]
    en  The cleaver and I feed the village. The vegetarian glares at me across the square. We manage.
    >>  ............................................
    pt  O cutelo e eu alimentamos o vilarejo. O vegetariano me encara do outro lado da praça. A gente se entende.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. I feed the animals I'll kill, and I name none of them, and that is a rule.
    >>  ............................................
    pt  Sou o açougueiro. Alimento os bichos que vou matar, e não dou nome a nenhum, e é uma regra.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Somebody has to, and I'd rather it were somebody who minds. I do mind.
    >>  ............................................
    pt  Açougue. Alguém tem que fazer, e prefiro que seja alguém que se importe. Eu me importo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Same block, same season, same salt. The year tells me what to do.
    >>  ............................................
    pt  Sou o açougueiro. Mesmo cepo, mesma estação, mesmo sal. O ano me diz o que fazer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.butcher/2
    en  Butchery. It's an old rhythm — kill in autumn, salt in autumn, eat all winter. Nothing hurried.
    >>  ............................................
    pt  Açougue. É um ritmo antigo — abater no outono, salgar no outono, comer o inverno todo. Nada apressado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher/1
    en  I break down carcasses and I salt what keeps. Nobody here goes hungry in February.
    >>  ............................................
    pt  Eu desmancho carcaça e salgo o que dura. Ninguém aqui passa fome em fevereiro.
    >>  ............................................
  confident.dialogue.conversations.work.prof.butcher/2
    en  Butcher. It's honest, it's unpleasant, and everybody who complains still eats.
    >>  ............................................
    pt  Açougueiro. É honesto, é desagradável, e todo mundo que reclama come mesmo assim.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher/1
    en  I break down carcasses and I salt what keeps. Nobody here goes hungry in February.
    >>  ............................................
    pt  Eu desmancho carcaça e salgo o que dura. Ninguém aqui passa fome em fevereiro.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.butcher/2
    en  Butcher. It's honest, it's unpleasant, and everybody who complains still eats.
    >>  ............................................
    pt  Açougueiro. É honesto, é desagradável, e todo mundo que reclama come mesmo assim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Which means I know what every household in this place eats, and when they stop.
    >>  ............................................
    pt  Sou o açougueiro. O que significa que eu sei o que cada casa daqui come, e quando param.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Half of it is the block and half is knowing which family to put the good cut aside for.
    >>  ............................................
    pt  Açougue. Metade é o cepo e metade é saber pra qual família guardar o corte bom.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Which means I know what every household in this place eats, and when they stop.
    >>  ............................................
    pt  Sou o açougueiro. O que significa que eu sei o que cada casa daqui come, e quando param.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Half of it is the block and half is knowing which family to put the good cut aside for.
    >>  ............................................
    pt  Açougue. Metade é o cepo e metade é saber pra qual família guardar o corte bom.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Which means I know what every household in this place eats, and when they stop.
    >>  ............................................
    pt  Sou o açougueiro. O que significa que eu sei o que cada casa daqui come, e quando param.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Half of it is the block and half is knowing which family to put the good cut aside for.
    >>  ............................................
    pt  Açougue. Metade é o cepo e metade é saber pra qual família guardar o corte bom.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. I feed the animals I'll kill, and I name none of them, and that is a rule.
    >>  ............................................
    pt  Sou o açougueiro. Alimento os bichos que vou matar, e não dou nome a nenhum, e é uma regra.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Somebody has to, and I'd rather it were somebody who minds. I do mind.
    >>  ............................................
    pt  Açougue. Alguém tem que fazer, e prefiro que seja alguém que se importe. Eu me importo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher/1
    en  I break down carcasses and I salt what keeps. Nobody here goes hungry in February.
    >>  ............................................
    pt  Eu desmancho carcaça e salgo o que dura. Ninguém aqui passa fome em fevereiro.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.butcher/2
    en  Butcher. It's honest, it's unpleasant, and everybody who complains still eats.
    >>  ............................................
    pt  Açougueiro. É honesto, é desagradável, e todo mundo que reclama come mesmo assim.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher/1
    en  I break down carcasses and I salt what keeps. Nobody here goes hungry in February.
    >>  ............................................
    pt  Eu desmancho carcaça e salgo o que dura. Ninguém aqui passa fome em fevereiro.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.butcher/2
    en  Butcher. It's honest, it's unpleasant, and everybody who complains still eats.
    >>  ............................................
    pt  Açougueiro. É honesto, é desagradável, e todo mundo que reclama come mesmo assim.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher/1
    en  I keep the block. It's quiet work if you don't count the pens, and I try not to.
    >>  ............................................
    pt  Eu cuido do cepo. É trabalho quieto se você não contar os currais, e eu tento não contar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.butcher/2
    en  Butcher. Bone to the broth, fat to the candles, hide to the tanner. Almost nothing wasted.
    >>  ............................................
    pt  Açougueiro. Osso pro caldo, gordura pras velas, couro pro curtidor. Quase nada se perde.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Same block, same season, same salt. The year tells me what to do.
    >>  ............................................
    pt  Sou o açougueiro. Mesmo cepo, mesma estação, mesmo sal. O ano me diz o que fazer.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.butcher/2
    en  Butchery. It's an old rhythm — kill in autumn, salt in autumn, eat all winter. Nothing hurried.
    >>  ............................................
    pt  Açougue. É um ritmo antigo — abater no outono, salgar no outono, comer o inverno todo. Nada apressado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher/1
    en  I keep the block. It's quiet work if you don't count the pens, and I try not to.
    >>  ............................................
    pt  Eu cuido do cepo. É trabalho quieto se você não contar os currais, e eu tento não contar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.butcher/2
    en  Butcher. Bone to the broth, fat to the candles, hide to the tanner. Almost nothing wasted.
    >>  ............................................
    pt  Açougueiro. Osso pro caldo, gordura pras velas, couro pro curtidor. Quase nada se perde.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Same block, same season, same salt. The year tells me what to do.
    >>  ............................................
    pt  Sou o açougueiro. Mesmo cepo, mesma estação, mesmo sal. O ano me diz o que fazer.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.butcher/2
    en  Butchery. It's an old rhythm — kill in autumn, salt in autumn, eat all winter. Nothing hurried.
    >>  ............................................
    pt  Açougue. É um ritmo antigo — abater no outono, salgar no outono, comer o inverno todo. Nada apressado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher/1
    en  I'm the reason dinner has anything interesting in it. Terrible job, excellent results.
    >>  ............................................
    pt  Sou o motivo do jantar ter algo interessante. Trabalho horrível, resultado excelente.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.butcher/2
    en  Butcher! Yes, the apron. No, don't ask what the stain is. Come back Thursday, there'll be sausage.
    >>  ............................................
    pt  Açougueiro! Sim, o avental. Não, não pergunte o que é a mancha. Volte quinta, vai ter linguiça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher/1
    en  I'm the reason dinner has anything interesting in it. Terrible job, excellent results.
    >>  ............................................
    pt  Sou o motivo do jantar ter algo interessante. Trabalho horrível, resultado excelente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.butcher/2
    en  Butcher! Yes, the apron. No, don't ask what the stain is. Come back Thursday, there'll be sausage.
    >>  ............................................
    pt  Açougueiro! Sim, o avental. Não, não pergunte o que é a mancha. Volte quinta, vai ter linguiça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. Same block, same season, same salt. The year tells me what to do.
    >>  ............................................
    pt  Sou o açougueiro. Mesmo cepo, mesma estação, mesmo sal. O ano me diz o que fazer.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.butcher/2
    en  Butchery. It's an old rhythm — kill in autumn, salt in autumn, eat all winter. Nothing hurried.
    >>  ............................................
    pt  Açougue. É um ritmo antigo — abater no outono, salgar no outono, comer o inverno todo. Nada apressado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher/1
    en  I'm the butcher. I feed the animals I'll kill, and I name none of them, and that is a rule.
    >>  ............................................
    pt  Sou o açougueiro. Alimento os bichos que vou matar, e não dou nome a nenhum, e é uma regra.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.butcher/2
    en  Butchery. Somebody has to, and I'd rather it were somebody who minds. I do mind.
    >>  ............................................
    pt  Açougue. Alguém tem que fazer, e prefiro que seja alguém que se importe. Eu me importo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher/1
    en  I keep the block. It's quiet work if you don't count the pens, and I try not to.
    >>  ............................................
    pt  Eu cuido do cepo. É trabalho quieto se você não contar os currais, e eu tento não contar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.butcher/2
    en  Butcher. Bone to the broth, fat to the candles, hide to the tanner. Almost nothing wasted.
    >>  ............................................
    pt  Açougueiro. Osso pro caldo, gordura pras velas, couro pro curtidor. Quase nada se perde.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher/1
    en  I'm the reason dinner has anything interesting in it. Terrible job, excellent results.
    >>  ............................................
    pt  Sou o motivo do jantar ter algo interessante. Trabalho horrível, resultado excelente.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.butcher/2
    en  Butcher! Yes, the apron. No, don't ask what the stain is. Come back Thursday, there'll be sausage.
    >>  ............................................
    pt  Açougueiro! Sim, o avental. Não, não pergunte o que é a mancha. Volte quinta, vai ter linguiça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher/1
    en  I'm the reason dinner has anything interesting in it. Terrible job, excellent results.
    >>  ............................................
    pt  Sou o motivo do jantar ter algo interessante. Trabalho horrível, resultado excelente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.butcher/2
    en  Butcher! Yes, the apron. No, don't ask what the stain is. Come back Thursday, there'll be sausage.
    >>  ............................................
    pt  Açougueiro! Sim, o avental. Não, não pergunte o que é a mancha. Volte quinta, vai ter linguiça.
    >>  ............................................
```

</details>


**Outcome 12 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.respond`
- …where the player's next choices will be: "Does the smell ever get to you?" | "Half of what people own passed through your hands." | "It's a filthy job." | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.identity`: the villager explains. Subject `work.leatherworker.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.leatherworker/1   [96 chars]
    en  Hides come in stiff and ornery, leave soft and useful. There's a sermon in that if you want one.
    >>  ............................................
    pt  O couro chega duro e emburrado, sai macio e útil. Tem um sermão nisso, se você quiser um.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker/2   [70 chars]
    en  Everything I make gets better with age and wear. I aspire to the same.
    >>  ............................................
    pt  Tudo que eu faço melhora com o tempo e o uso. Aspiro ao mesmo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. The pits are poison and everyone's boots walk past them, so I check the fence daily.
    >>  ............................................
    pt  Eu trabalho com couro. Os tanques são veneno e as botas de todos passam perto, então confiro a cerca todo dia.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. I make two saddles a year and I lose sleep over both of them, every year.
    >>  ............................................
    pt  Couro. Faço duas selas por ano e perco sono com as duas, todo ano.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker/1
    en  Leather. Ash seasoned two years, hides turned for months. Nothing about it can be hurried.
    >>  ............................................
    pt  Couro. Freixo curado dois anos, couros virados por meses. Nada nisso pode ser apressado.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker/2
    en  I tan and I stitch. Twenty years of it, and going slowly is not a habit — it's the method.
    >>  ............................................
    pt  Eu curto e eu costuro. Vinte anos disso, e ir devagar não é hábito — é o método.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker/1
    en  I tan hides and cut leather. Thirty new pairs of boots a year and ninety mended.
    >>  ............................................
    pt  Eu curto couros e corto couro. Trinta pares novos de bota por ano e noventa consertados.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. A bad hole is forever; a bad stitch you pull out. That's the whole of what I know.
    >>  ............................................
    pt  Couro. Um furo ruim é pra sempre; um ponto ruim se desfaz. É tudo que eu sei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker/1
    en  I tan hides and cut leather. Thirty new pairs of boots a year and ninety mended.
    >>  ............................................
    pt  Eu curto couros e corto couro. Trinta pares novos de bota por ano e noventa consertados.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. A bad hole is forever; a bad stitch you pull out. That's the whole of what I know.
    >>  ............................................
    pt  Couro. Um furo ruim é pra sempre; um ponto ruim se desfaz. É tudo que eu sei.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. Everyone in this place walks on something I made, which I think about often.
    >>  ............................................
    pt  Eu trabalho com couro. Todo mundo aqui pisa em algo que eu fiz, e eu penso nisso sempre.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. The butcher brings me his hides at dawn and we've had eleven years of the same joke about it.
    >>  ............................................
    pt  Couro. O açougueiro traz os couros ao amanhecer e temos onze anos da mesma piada sobre isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. Everyone in this place walks on something I made, which I think about often.
    >>  ............................................
    pt  Eu trabalho com couro. Todo mundo aqui pisa em algo que eu fiz, e eu penso nisso sempre.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. The butcher brings me his hides at dawn and we've had eleven years of the same joke about it.
    >>  ............................................
    pt  Couro. O açougueiro traz os couros ao amanhecer e temos onze anos da mesma piada sobre isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. Everyone in this place walks on something I made, which I think about often.
    >>  ............................................
    pt  Eu trabalho com couro. Todo mundo aqui pisa em algo que eu fiz, e eu penso nisso sempre.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. The butcher brings me his hides at dawn and we've had eleven years of the same joke about it.
    >>  ............................................
    pt  Couro. O açougueiro traz os couros ao amanhecer e temos onze anos da mesma piada sobre isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. The pits are poison and everyone's boots walk past them, so I check the fence daily.
    >>  ............................................
    pt  Eu trabalho com couro. Os tanques são veneno e as botas de todos passam perto, então confiro a cerca todo dia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. I make two saddles a year and I lose sleep over both of them, every year.
    >>  ............................................
    pt  Couro. Faço duas selas por ano e perco sono com as duas, todo ano.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker/1
    en  I tan hides and cut leather. Thirty new pairs of boots a year and ninety mended.
    >>  ............................................
    pt  Eu curto couros e corto couro. Trinta pares novos de bota por ano e noventa consertados.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. A bad hole is forever; a bad stitch you pull out. That's the whole of what I know.
    >>  ............................................
    pt  Couro. Um furo ruim é pra sempre; um ponto ruim se desfaz. É tudo que eu sei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker/1
    en  I tan hides and cut leather. Thirty new pairs of boots a year and ninety mended.
    >>  ............................................
    pt  Eu curto couros e corto couro. Trinta pares novos de bota por ano e noventa consertados.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. A bad hole is forever; a bad stitch you pull out. That's the whole of what I know.
    >>  ............................................
    pt  Couro. Um furo ruim é pra sempre; um ponto ruim se desfaz. É tudo que eu sei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker/1
    en  I turn hides in the pits. Corner to corner, count them, and start again if the count is wrong.
    >>  ............................................
    pt  Eu viro couros nos tanques. Canto a canto, conte, e recomece se a conta der errado.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Couro. Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker/1
    en  Leather. Ash seasoned two years, hides turned for months. Nothing about it can be hurried.
    >>  ............................................
    pt  Couro. Freixo curado dois anos, couros virados por meses. Nada nisso pode ser apressado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker/2
    en  I tan and I stitch. Twenty years of it, and going slowly is not a habit — it's the method.
    >>  ............................................
    pt  Eu curto e eu costuro. Vinte anos disso, e ir devagar não é hábito — é o método.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker/1
    en  I turn hides in the pits. Corner to corner, count them, and start again if the count is wrong.
    >>  ............................................
    pt  Eu viro couros nos tanques. Canto a canto, conte, e recomece se a conta der errado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Couro. Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker/1
    en  Leather. Ash seasoned two years, hides turned for months. Nothing about it can be hurried.
    >>  ............................................
    pt  Couro. Freixo curado dois anos, couros virados por meses. Nada nisso pode ser apressado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker/2
    en  I tan and I stitch. Twenty years of it, and going slowly is not a habit — it's the method.
    >>  ............................................
    pt  Eu curto e eu costuro. Vinte anos disso, e ir devagar não é hábito — é o método.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker/1
    en  I make leather. Yes, that is the smell. No, it doesn't come out. Yes, I've tried everything.
    >>  ............................................
    pt  Eu faço couro. Sim, é esse o cheiro. Não, não sai. Sim, eu já tentei de tudo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker/2
    en  Hides come in stiff and ornery and leave soft and useful. There's a sermon in that if you want one.
    >>  ............................................
    pt  Os couros chegam duros e teimosos e saem macios e úteis. Tem um sermão nisso, se você quiser.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker/1
    en  I make leather. Yes, that is the smell. No, it doesn't come out. Yes, I've tried everything.
    >>  ............................................
    pt  Eu faço couro. Sim, é esse o cheiro. Não, não sai. Sim, eu já tentei de tudo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker/2
    en  Hides come in stiff and ornery and leave soft and useful. There's a sermon in that if you want one.
    >>  ............................................
    pt  Os couros chegam duros e teimosos e saem macios e úteis. Tem um sermão nisso, se você quiser.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker/1
    en  Leather. Ash seasoned two years, hides turned for months. Nothing about it can be hurried.
    >>  ............................................
    pt  Couro. Freixo curado dois anos, couros virados por meses. Nada nisso pode ser apressado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker/2
    en  I tan and I stitch. Twenty years of it, and going slowly is not a habit — it's the method.
    >>  ............................................
    pt  Eu curto e eu costuro. Vinte anos disso, e ir devagar não é hábito — é o método.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker/1
    en  I work leather. The pits are poison and everyone's boots walk past them, so I check the fence daily.
    >>  ............................................
    pt  Eu trabalho com couro. Os tanques são veneno e as botas de todos passam perto, então confiro a cerca todo dia.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. I make two saddles a year and I lose sleep over both of them, every year.
    >>  ............................................
    pt  Couro. Faço duas selas por ano e perco sono com as duas, todo ano.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker/1
    en  I turn hides in the pits. Corner to corner, count them, and start again if the count is wrong.
    >>  ............................................
    pt  Eu viro couros nos tanques. Canto a canto, conte, e recomece se a conta der errado.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker/2
    en  Leather. Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Couro. Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker/1
    en  I make leather. Yes, that is the smell. No, it doesn't come out. Yes, I've tried everything.
    >>  ............................................
    pt  Eu faço couro. Sim, é esse o cheiro. Não, não sai. Sim, eu já tentei de tudo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker/2
    en  Hides come in stiff and ornery and leave soft and useful. There's a sermon in that if you want one.
    >>  ............................................
    pt  Os couros chegam duros e teimosos e saem macios e úteis. Tem um sermão nisso, se você quiser.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker/1
    en  I make leather. Yes, that is the smell. No, it doesn't come out. Yes, I've tried everything.
    >>  ............................................
    pt  Eu faço couro. Sim, é esse o cheiro. Não, não sai. Sim, eu já tentei de tudo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker/2
    en  Hides come in stiff and ornery and leave soft and useful. There's a sermon in that if you want one.
    >>  ............................................
    pt  Os couros chegam duros e teimosos e saem macios e úteis. Tem um sermão nisso, se você quiser.
    >>  ............................................
```

</details>


**Outcome 13 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.respond`
- …where the player's next choices will be: "What do you find when you look at a wall?" | "Half these houses are standing because of you." | "Stacking rocks isn't a craft." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.identity`: the villager explains. Subject `work.mason.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.mason/1   [97 chars]
    en  Stone is patient and so am I. The wall I built at twenty will outlive my grandchildren's grudges.
    >>  ............................................
    pt  A pedra é paciente e eu também. O muro que levantei aos vinte vai durar mais que as brigas dos meus netos.
    >>  ............................................
  dialogue.conversations.work.prof.mason/2   [85 chars]
    en  People see rock. I see the corner of a house where somebody's whole life will happen.
    >>  ............................................
    pt  As pessoas veem rocha. Eu vejo o canto de uma casa onde a vida inteira de alguém vai acontecer.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mason/1
    en  I build. Everything I build outlives me, and so does everything I get wrong, and that is not a comfortable pair.
    >>  ............................................
    pt  Eu construo. Tudo que eu construo me sobrevive, e tudo que eu erro também, e não é um par confortável.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mason/2
    en  Mason. A wall came down in the next valley last year. Not mine. I went and looked at it anyway.
    >>  ............................................
    pt  Pedreiro. Uma parede caiu no vale vizinho ano passado. Não minha. Eu fui olhar mesmo assim.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The wall I built at twenty will outlive my grandchildren's grudges.
    >>  ............................................
    pt  Eu corto pedra. A parede que eu fiz aos vinte vai sobreviver às mágoas dos meus netos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason/2
    en  Mason. Stone is patient and so am I, and neither of us has ever been improved by hurry.
    >>  ............................................
    pt  Pedreiro. A pedra é paciente e eu também, e nenhum de nós melhorou com pressa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The well, the church footing, four chimneys and the bridge. That is my account.
    >>  ............................................
    pt  Eu corto pedra. O poço, a fundação da igreja, quatro chaminés e a ponte. É o meu balanço.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason/2
    en  Mason. Identical is harder than beautiful, and identical is what a row of sills has to be.
    >>  ............................................
    pt  Pedreiro. Idêntico é mais difícil que bonito, e idêntico é o que uma fileira de peitoris tem que ser.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The well, the church footing, four chimneys and the bridge. That is my account.
    >>  ............................................
    pt  Eu corto pedra. O poço, a fundação da igreja, quatro chaminés e a ponte. É o meu balanço.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason/2
    en  Mason. Identical is harder than beautiful, and identical is what a row of sills has to be.
    >>  ............................................
    pt  Pedreiro. Idêntico é mais difícil que bonito, e idêntico é o que uma fileira de peitoris tem que ser.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason/1
    en  I cut stone. I also cut the names on the graves, so every name in that ground came through my hands twice.
    >>  ............................................
    pt  Eu corto pedra. Também corto os nomes nos túmulos, então todo nome naquele chão passou por minhas mãos duas vezes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason/2
    en  Mason. Half the walls you've leaned on today are mine, and I'd be glad to tell you which.
    >>  ............................................
    pt  Pedreiro. Metade das paredes em que você se encostou hoje são minhas, e eu adoraria dizer quais.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason/1
    en  I cut stone. I also cut the names on the graves, so every name in that ground came through my hands twice.
    >>  ............................................
    pt  Eu corto pedra. Também corto os nomes nos túmulos, então todo nome naquele chão passou por minhas mãos duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason/2
    en  Mason. Half the walls you've leaned on today are mine, and I'd be glad to tell you which.
    >>  ............................................
    pt  Pedreiro. Metade das paredes em que você se encostou hoje são minhas, e eu adoraria dizer quais.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason/1
    en  I cut stone. I also cut the names on the graves, so every name in that ground came through my hands twice.
    >>  ............................................
    pt  Eu corto pedra. Também corto os nomes nos túmulos, então todo nome naquele chão passou por minhas mãos duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason/2
    en  Mason. Half the walls you've leaned on today are mine, and I'd be glad to tell you which.
    >>  ............................................
    pt  Pedreiro. Metade das paredes em que você se encostou hoje são minhas, e eu adoraria dizer quais.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason/1
    en  I build. Everything I build outlives me, and so does everything I get wrong, and that is not a comfortable pair.
    >>  ............................................
    pt  Eu construo. Tudo que eu construo me sobrevive, e tudo que eu erro também, e não é um par confortável.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason/2
    en  Mason. A wall came down in the next valley last year. Not mine. I went and looked at it anyway.
    >>  ............................................
    pt  Pedreiro. Uma parede caiu no vale vizinho ano passado. Não minha. Eu fui olhar mesmo assim.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The well, the church footing, four chimneys and the bridge. That is my account.
    >>  ............................................
    pt  Eu corto pedra. O poço, a fundação da igreja, quatro chaminés e a ponte. É o meu balanço.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason/2
    en  Mason. Identical is harder than beautiful, and identical is what a row of sills has to be.
    >>  ............................................
    pt  Pedreiro. Idêntico é mais difícil que bonito, e idêntico é o que uma fileira de peitoris tem que ser.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The well, the church footing, four chimneys and the bridge. That is my account.
    >>  ............................................
    pt  Eu corto pedra. O poço, a fundação da igreja, quatro chaminés e a ponte. É o meu balanço.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason/2
    en  Mason. Identical is harder than beautiful, and identical is what a row of sills has to be.
    >>  ............................................
    pt  Pedreiro. Idêntico é mais difícil que bonito, e idêntico é o que uma fileira de peitoris tem que ser.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason/1
    en  I cut stone. It tells you where it wants to split if you tap it and stay quiet long enough.
    >>  ............................................
    pt  Eu corto pedra. Ela diz onde quer rachar se você bater e ficar quieto o bastante.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason/2
    en  Mason. There are marks on my yard wall for every stone I've got wrong. Twice a season, twenty years.
    >>  ............................................
    pt  Pedreiro. Tem marcas na parede do meu pátio pra cada pedra que eu errei. Duas por estação, vinte anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The wall I built at twenty will outlive my grandchildren's grudges.
    >>  ............................................
    pt  Eu corto pedra. A parede que eu fiz aos vinte vai sobreviver às mágoas dos meus netos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason/2
    en  Mason. Stone is patient and so am I, and neither of us has ever been improved by hurry.
    >>  ............................................
    pt  Pedreiro. A pedra é paciente e eu também, e nenhum de nós melhorou com pressa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason/1
    en  I cut stone. It tells you where it wants to split if you tap it and stay quiet long enough.
    >>  ............................................
    pt  Eu corto pedra. Ela diz onde quer rachar se você bater e ficar quieto o bastante.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason/2
    en  Mason. There are marks on my yard wall for every stone I've got wrong. Twice a season, twenty years.
    >>  ............................................
    pt  Pedreiro. Tem marcas na parede do meu pátio pra cada pedra que eu errei. Duas por estação, vinte anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The wall I built at twenty will outlive my grandchildren's grudges.
    >>  ............................................
    pt  Eu corto pedra. A parede que eu fiz aos vinte vai sobreviver às mágoas dos meus netos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason/2
    en  Mason. Stone is patient and so am I, and neither of us has ever been improved by hurry.
    >>  ............................................
    pt  Pedreiro. A pedra é paciente e eu também, e nenhum de nós melhorou com pressa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason/1
    en  Stone! It's heavy, it's slow, and every single thing I make outlasts the argument about it.
    >>  ............................................
    pt  Pedra! É pesada, é lenta, e cada coisa que eu faço sobrevive à discussão sobre ela.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason/2
    en  I'm the mason. Ask me about arches and I will not stop, so pick your moment.
    >>  ............................................
    pt  Sou o pedreiro. Me pergunte sobre arcos e eu não paro, então escolha a hora.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason/1
    en  Stone! It's heavy, it's slow, and every single thing I make outlasts the argument about it.
    >>  ............................................
    pt  Pedra! É pesada, é lenta, e cada coisa que eu faço sobrevive à discussão sobre ela.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason/2
    en  I'm the mason. Ask me about arches and I will not stop, so pick your moment.
    >>  ............................................
    pt  Sou o pedreiro. Me pergunte sobre arcos e eu não paro, então escolha a hora.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason/1
    en  I cut stone. The wall I built at twenty will outlive my grandchildren's grudges.
    >>  ............................................
    pt  Eu corto pedra. A parede que eu fiz aos vinte vai sobreviver às mágoas dos meus netos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason/2
    en  Mason. Stone is patient and so am I, and neither of us has ever been improved by hurry.
    >>  ............................................
    pt  Pedreiro. A pedra é paciente e eu também, e nenhum de nós melhorou com pressa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason/1
    en  I build. Everything I build outlives me, and so does everything I get wrong, and that is not a comfortable pair.
    >>  ............................................
    pt  Eu construo. Tudo que eu construo me sobrevive, e tudo que eu erro também, e não é um par confortável.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason/2
    en  Mason. A wall came down in the next valley last year. Not mine. I went and looked at it anyway.
    >>  ............................................
    pt  Pedreiro. Uma parede caiu no vale vizinho ano passado. Não minha. Eu fui olhar mesmo assim.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason/1
    en  I cut stone. It tells you where it wants to split if you tap it and stay quiet long enough.
    >>  ............................................
    pt  Eu corto pedra. Ela diz onde quer rachar se você bater e ficar quieto o bastante.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason/2
    en  Mason. There are marks on my yard wall for every stone I've got wrong. Twice a season, twenty years.
    >>  ............................................
    pt  Pedreiro. Tem marcas na parede do meu pátio pra cada pedra que eu errei. Duas por estação, vinte anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason/1
    en  Stone! It's heavy, it's slow, and every single thing I make outlasts the argument about it.
    >>  ............................................
    pt  Pedra! É pesada, é lenta, e cada coisa que eu faço sobrevive à discussão sobre ela.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason/2
    en  I'm the mason. Ask me about arches and I will not stop, so pick your moment.
    >>  ............................................
    pt  Sou o pedreiro. Me pergunte sobre arcos e eu não paro, então escolha a hora.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason/1
    en  Stone! It's heavy, it's slow, and every single thing I make outlasts the argument about it.
    >>  ............................................
    pt  Pedra! É pesada, é lenta, e cada coisa que eu faço sobrevive à discussão sobre ela.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason/2
    en  I'm the mason. Ask me about arches and I will not stop, so pick your moment.
    >>  ............................................
    pt  Sou o pedreiro. Me pergunte sobre arcos e eu não paro, então escolha a hora.
    >>  ............................................
```

</details>


**Outcome 14 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.respond`
- …where the player's next choices will be: "Does the joke wear thin?" | "You notice more than the busy people do." | "You could learn a trade if you wanted." | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.identity`: the villager explains. Subject `work.nitwit.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.nitwit/1   [86 chars]
    en  Work? Oh, I supervise. The clouds, mostly. Somebody has to make sure they keep moving.
    >>  ............................................
    pt  Trabalho? Ah, eu supervisiono. As nuvens, principalmente. Alguém tem que garantir que elas continuem andando.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit/2   [102 chars]
    en  I tried a job once. Terrible fit. My true calling is being a cautionary tale, and business is BOOMING.
    >>  ............................................
    pt  Tentei um emprego uma vez. Não combinou nada. Minha verdadeira vocação é ser exemplo do que não fazer, e os negócios vão de VENTO EM POPA.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.nitwit/1
    en  No trade. People say things in front of me they'd not say in front of anyone, and I carry all of it.
    >>  ............................................
    pt  Sem ofício. As pessoas dizem na minha frente o que não diriam na de mais ninguém, e eu carrego tudo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. The risk is being here in forty years having been asked nothing at all.
    >>  ............................................
    pt  Eu olho a estrada. O risco é estar aqui em quarenta anos sem ninguém ter me perguntado nada.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit/1
    en  No trade to speak of. I watch the road, and the road has never once needed me to hurry.
    >>  ............................................
    pt  Nenhum ofício, pra falar a verdade. Eu olho a estrada, e a estrada nunca precisou que eu tivesse pressa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit/2
    en  They decided I was simple and I let them. It has been a quieter life than the alternative.
    >>  ............................................
    pt  Decidiram que eu era simples e eu deixei. Foi uma vida mais quieta que a alternativa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit/1
    en  Nobody's given me a trade. So I watch the road, and I know more about who comes and goes than the mayor does.
    >>  ............................................
    pt  Ninguém me deu um ofício. Então eu olho a estrada, e sei mais de quem entra e sai que o prefeito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit/2
    en  No trade. That was decided about me a long time ago and never revisited, and I have my own opinion of it.
    >>  ............................................
    pt  Sem ofício. Decidiram isso sobre mim há muito tempo e nunca revisaram, e eu tenho minha opinião.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit/1
    en  Nobody's given me a trade. So I watch the road, and I know more about who comes and goes than the mayor does.
    >>  ............................................
    pt  Ninguém me deu um ofício. Então eu olho a estrada, e sei mais de quem entra e sai que o prefeito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit/2
    en  No trade. That was decided about me a long time ago and never revisited, and I have my own opinion of it.
    >>  ............................................
    pt  Sem ofício. Decidiram isso sobre mim há muito tempo e nunca revisaram, e eu tenho minha opinião.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit/1
    en  No trade. But I can tell you the name of every person here and who they're not speaking to this month.
    >>  ............................................
    pt  Sem ofício. Mas eu sei o nome de cada pessoa daqui e com quem não estão se falando este mês.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. If you ever want to know who came in before noon, I'm the one to ask, and nobody asks.
    >>  ............................................
    pt  Eu olho a estrada. Se quiser saber quem entrou antes do meio-dia, sou eu que sei, e ninguém pergunta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit/1
    en  No trade. But I can tell you the name of every person here and who they're not speaking to this month.
    >>  ............................................
    pt  Sem ofício. Mas eu sei o nome de cada pessoa daqui e com quem não estão se falando este mês.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. If you ever want to know who came in before noon, I'm the one to ask, and nobody asks.
    >>  ............................................
    pt  Eu olho a estrada. Se quiser saber quem entrou antes do meio-dia, sou eu que sei, e ninguém pergunta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit/1
    en  No trade. But I can tell you the name of every person here and who they're not speaking to this month.
    >>  ............................................
    pt  Sem ofício. Mas eu sei o nome de cada pessoa daqui e com quem não estão se falando este mês.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. If you ever want to know who came in before noon, I'm the one to ask, and nobody asks.
    >>  ............................................
    pt  Eu olho a estrada. Se quiser saber quem entrou antes do meio-dia, sou eu que sei, e ninguém pergunta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit/1
    en  No trade. People say things in front of me they'd not say in front of anyone, and I carry all of it.
    >>  ............................................
    pt  Sem ofício. As pessoas dizem na minha frente o que não diriam na de mais ninguém, e eu carrego tudo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. The risk is being here in forty years having been asked nothing at all.
    >>  ............................................
    pt  Eu olho a estrada. O risco é estar aqui em quarenta anos sem ninguém ter me perguntado nada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit/1
    en  Nobody's given me a trade. So I watch the road, and I know more about who comes and goes than the mayor does.
    >>  ............................................
    pt  Ninguém me deu um ofício. Então eu olho a estrada, e sei mais de quem entra e sai que o prefeito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit/2
    en  No trade. That was decided about me a long time ago and never revisited, and I have my own opinion of it.
    >>  ............................................
    pt  Sem ofício. Decidiram isso sobre mim há muito tempo e nunca revisaram, e eu tenho minha opinião.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit/1
    en  Nobody's given me a trade. So I watch the road, and I know more about who comes and goes than the mayor does.
    >>  ............................................
    pt  Ninguém me deu um ofício. Então eu olho a estrada, e sei mais de quem entra e sai que o prefeito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit/2
    en  No trade. That was decided about me a long time ago and never revisited, and I have my own opinion of it.
    >>  ............................................
    pt  Sem ofício. Decidiram isso sobre mim há muito tempo e nunca revisaram, e eu tenho minha opinião.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit/1
    en  I watch the road. I know every path out of this valley and which ones flood. Nobody taught me that.
    >>  ............................................
    pt  Eu olho a estrada. Conheço cada caminho pra fora deste vale e quais alagam. Ninguém me ensinou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit/2
    en  No trade. I notice things instead. It is not a job and it fills the day entirely.
    >>  ............................................
    pt  Sem ofício. Eu reparo nas coisas. Não é um trabalho e enche o dia inteiro.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit/1
    en  No trade to speak of. I watch the road, and the road has never once needed me to hurry.
    >>  ............................................
    pt  Nenhum ofício, pra falar a verdade. Eu olho a estrada, e a estrada nunca precisou que eu tivesse pressa.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit/2
    en  They decided I was simple and I let them. It has been a quieter life than the alternative.
    >>  ............................................
    pt  Decidiram que eu era simples e eu deixei. Foi uma vida mais quieta que a alternativa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit/1
    en  I watch the road. I know every path out of this valley and which ones flood. Nobody taught me that.
    >>  ............................................
    pt  Eu olho a estrada. Conheço cada caminho pra fora deste vale e quais alagam. Ninguém me ensinou.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit/2
    en  No trade. I notice things instead. It is not a job and it fills the day entirely.
    >>  ............................................
    pt  Sem ofício. Eu reparo nas coisas. Não é um trabalho e enche o dia inteiro.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit/1
    en  No trade to speak of. I watch the road, and the road has never once needed me to hurry.
    >>  ............................................
    pt  Nenhum ofício, pra falar a verdade. Eu olho a estrada, e a estrada nunca precisou que eu tivesse pressa.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit/2
    en  They decided I was simple and I let them. It has been a quieter life than the alternative.
    >>  ............................................
    pt  Decidiram que eu era simples e eu deixei. Foi uma vida mais quieta que a alternativa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit/1
    en  I supervise! The clouds, mostly. Somebody has to make sure they keep moving.
    >>  ............................................
    pt  Eu supervisiono! As nuvens, principalmente. Alguém tem que garantir que continuem andando.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit/2
    en  No trade, but I've got the best appointments — the gate, the weather, and everybody's comings and goings.
    >>  ............................................
    pt  Sem ofício, mas eu tenho os melhores compromissos — o portão, o tempo, e as idas e vindas de todos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit/1
    en  I supervise! The clouds, mostly. Somebody has to make sure they keep moving.
    >>  ............................................
    pt  Eu supervisiono! As nuvens, principalmente. Alguém tem que garantir que continuem andando.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit/2
    en  No trade, but I've got the best appointments — the gate, the weather, and everybody's comings and goings.
    >>  ............................................
    pt  Sem ofício, mas eu tenho os melhores compromissos — o portão, o tempo, e as idas e vindas de todos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit/1
    en  No trade to speak of. I watch the road, and the road has never once needed me to hurry.
    >>  ............................................
    pt  Nenhum ofício, pra falar a verdade. Eu olho a estrada, e a estrada nunca precisou que eu tivesse pressa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit/2
    en  They decided I was simple and I let them. It has been a quieter life than the alternative.
    >>  ............................................
    pt  Decidiram que eu era simples e eu deixei. Foi uma vida mais quieta que a alternativa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit/1
    en  No trade. People say things in front of me they'd not say in front of anyone, and I carry all of it.
    >>  ............................................
    pt  Sem ofício. As pessoas dizem na minha frente o que não diriam na de mais ninguém, e eu carrego tudo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit/2
    en  I watch the road. The risk is being here in forty years having been asked nothing at all.
    >>  ............................................
    pt  Eu olho a estrada. O risco é estar aqui em quarenta anos sem ninguém ter me perguntado nada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit/1
    en  I watch the road. I know every path out of this valley and which ones flood. Nobody taught me that.
    >>  ............................................
    pt  Eu olho a estrada. Conheço cada caminho pra fora deste vale e quais alagam. Ninguém me ensinou.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit/2
    en  No trade. I notice things instead. It is not a job and it fills the day entirely.
    >>  ............................................
    pt  Sem ofício. Eu reparo nas coisas. Não é um trabalho e enche o dia inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit/1
    en  I supervise! The clouds, mostly. Somebody has to make sure they keep moving.
    >>  ............................................
    pt  Eu supervisiono! As nuvens, principalmente. Alguém tem que garantir que continuem andando.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit/2
    en  No trade, but I've got the best appointments — the gate, the weather, and everybody's comings and goings.
    >>  ............................................
    pt  Sem ofício, mas eu tenho os melhores compromissos — o portão, o tempo, e as idas e vindas de todos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit/1
    en  I supervise! The clouds, mostly. Somebody has to make sure they keep moving.
    >>  ............................................
    pt  Eu supervisiono! As nuvens, principalmente. Alguém tem que garantir que continuem andando.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit/2
    en  No trade, but I've got the best appointments — the gate, the weather, and everybody's comings and goings.
    >>  ............................................
    pt  Sem ofício, mas eu tenho os melhores compromissos — o portão, o tempo, e as idas e vindas de todos.
    >>  ............................................
```

</details>


**Outcome 15 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.respond`
- …where the player's next choices will be: "Is it as free as it sounds?" | "There's time in that most people never get." | "You'll have to choose something eventually." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.identity`: the villager explains. Subject `work.none.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.none/1   [83 chars]
    en  No trade yet — I'm between destinies. The lean season of a life, my gran called it.
    >>  ............................................
    pt  Sem ofício ainda — estou entre destinos. A estação magra de uma vida, como dizia minha avó.
    >>  ............................................
  dialogue.conversations.work.prof.none/2   [89 chars]
    en  No master, no bell, no bench. Poor as dirt and free as weather. Ask me again next season.
    >>  ............................................
    pt  Sem mestre, sem sino, sem bancada. Pobre feito o pó e livre feito o tempo. Me pergunte na próxima estação.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.none/1
    en  No trade. A bad winter and I'm the one nobody has a reason to feed, and I know where I sit in that queue.
    >>  ............................................
    pt  Sem ofício. Um inverno ruim e eu sou quem ninguém tem motivo pra alimentar, e sei onde eu fico na fila.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.none/2
    en  Nothing yet. Everyone else has a trade to fall back on and I have a reputation for being nearby.
    >>  ............................................
    pt  Nada ainda. Todo mundo tem um ofício pra cair e eu tenho fama de estar por perto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none/1
    en  No trade. It'll come or it won't, and worrying at it has never once made it come faster.
    >>  ............................................
    pt  Sem ofício. Vem ou não vem, e me preocupar nunca fez vir mais rápido.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none/2
    en  Nothing settled. I've been useful for years without a title and the years passed pleasantly enough.
    >>  ............................................
    pt  Nada certo. Fui útil por anos sem título e os anos passaram bem o bastante.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none/1
    en  No trade. I turn up where hands are short, and I'm short of hands nowhere.
    >>  ............................................
    pt  Sem ofício. Apareço onde falta mão, e não falto em lugar nenhum.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none/2
    en  Nothing with a name to it. I've half of six trades and the confidence of none of them.
    >>  ............................................
    pt  Nada com nome. Tenho metade de seis ofícios e a confiança de nenhum.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none/1
    en  No trade. I turn up where hands are short, and I'm short of hands nowhere.
    >>  ............................................
    pt  Sem ofício. Apareço onde falta mão, e não falto em lugar nenhum.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none/2
    en  Nothing with a name to it. I've half of six trades and the confidence of none of them.
    >>  ............................................
    pt  Nada com nome. Tenho metade de seis ofícios e a confiança de nenhum.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none/1
    en  No trade. What I'm actually good at is knowing who needs what before they ask.
    >>  ............................................
    pt  Sem ofício. No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none/2
    en  Nothing settled yet. If you're going anywhere that wants a second pair of hands, say so.
    >>  ............................................
    pt  Nada certo ainda. Se você vai a algum lugar que precise de um segundo par de mãos, diga.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none/1
    en  No trade. What I'm actually good at is knowing who needs what before they ask.
    >>  ............................................
    pt  Sem ofício. No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none/2
    en  Nothing settled yet. If you're going anywhere that wants a second pair of hands, say so.
    >>  ............................................
    pt  Nada certo ainda. Se você vai a algum lugar que precise de um segundo par de mãos, diga.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none/1
    en  No trade. What I'm actually good at is knowing who needs what before they ask.
    >>  ............................................
    pt  Sem ofício. No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none/2
    en  Nothing settled yet. If you're going anywhere that wants a second pair of hands, say so.
    >>  ............................................
    pt  Nada certo ainda. Se você vai a algum lugar que precise de um segundo par de mãos, diga.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none/1
    en  No trade. A bad winter and I'm the one nobody has a reason to feed, and I know where I sit in that queue.
    >>  ............................................
    pt  Sem ofício. Um inverno ruim e eu sou quem ninguém tem motivo pra alimentar, e sei onde eu fico na fila.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none/2
    en  Nothing yet. Everyone else has a trade to fall back on and I have a reputation for being nearby.
    >>  ............................................
    pt  Nada ainda. Todo mundo tem um ofício pra cair e eu tenho fama de estar por perto.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none/1
    en  No trade. I turn up where hands are short, and I'm short of hands nowhere.
    >>  ............................................
    pt  Sem ofício. Apareço onde falta mão, e não falto em lugar nenhum.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none/2
    en  Nothing with a name to it. I've half of six trades and the confidence of none of them.
    >>  ............................................
    pt  Nada com nome. Tenho metade de seis ofícios e a confiança de nenhum.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none/1
    en  No trade. I turn up where hands are short, and I'm short of hands nowhere.
    >>  ............................................
    pt  Sem ofício. Apareço onde falta mão, e não falto em lugar nenhum.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none/2
    en  Nothing with a name to it. I've half of six trades and the confidence of none of them.
    >>  ............................................
    pt  Nada com nome. Tenho metade de seis ofícios e a confiança de nenhum.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none/1
    en  No trade. This morning it was the mill's sacks; this afternoon nobody has decided yet.
    >>  ............................................
    pt  Sem ofício. De manhã foram os sacos do moinho; à tarde ninguém decidiu ainda.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none/2
    en  Nothing with a title. Every trade here has had a week of me in it and nobody adds those weeks up.
    >>  ............................................
    pt  Nada com título. Todo ofício daqui teve uma semana minha e ninguém soma essas semanas.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none/1
    en  No trade. It'll come or it won't, and worrying at it has never once made it come faster.
    >>  ............................................
    pt  Sem ofício. Vem ou não vem, e me preocupar nunca fez vir mais rápido.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none/2
    en  Nothing settled. I've been useful for years without a title and the years passed pleasantly enough.
    >>  ............................................
    pt  Nada certo. Fui útil por anos sem título e os anos passaram bem o bastante.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none/1
    en  No trade. This morning it was the mill's sacks; this afternoon nobody has decided yet.
    >>  ............................................
    pt  Sem ofício. De manhã foram os sacos do moinho; à tarde ninguém decidiu ainda.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none/2
    en  Nothing with a title. Every trade here has had a week of me in it and nobody adds those weeks up.
    >>  ............................................
    pt  Nada com título. Todo ofício daqui teve uma semana minha e ninguém soma essas semanas.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none/1
    en  No trade. It'll come or it won't, and worrying at it has never once made it come faster.
    >>  ............................................
    pt  Sem ofício. Vem ou não vem, e me preocupar nunca fez vir mais rápido.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none/2
    en  Nothing settled. I've been useful for years without a title and the years passed pleasantly enough.
    >>  ............................................
    pt  Nada certo. Fui útil por anos sem título e os anos passaram bem o bastante.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none/1
    en  Between destinies! The lean season of a life, my gran called it. She was being kind.
    >>  ............................................
    pt  Entre destinos! A estação magra de uma vida, minha avó chamava. Ela estava sendo gentil.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none/2
    en  No trade yet. Which means every day is a surprise and some of the surprises are quite good.
    >>  ............................................
    pt  Sem ofício ainda. O que significa que todo dia é surpresa e algumas surpresas são boas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none/1
    en  Between destinies! The lean season of a life, my gran called it. She was being kind.
    >>  ............................................
    pt  Entre destinos! A estação magra de uma vida, minha avó chamava. Ela estava sendo gentil.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none/2
    en  No trade yet. Which means every day is a surprise and some of the surprises are quite good.
    >>  ............................................
    pt  Sem ofício ainda. O que significa que todo dia é surpresa e algumas surpresas são boas.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none/1
    en  No trade. It'll come or it won't, and worrying at it has never once made it come faster.
    >>  ............................................
    pt  Sem ofício. Vem ou não vem, e me preocupar nunca fez vir mais rápido.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none/2
    en  Nothing settled. I've been useful for years without a title and the years passed pleasantly enough.
    >>  ............................................
    pt  Nada certo. Fui útil por anos sem título e os anos passaram bem o bastante.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none/1
    en  No trade. A bad winter and I'm the one nobody has a reason to feed, and I know where I sit in that queue.
    >>  ............................................
    pt  Sem ofício. Um inverno ruim e eu sou quem ninguém tem motivo pra alimentar, e sei onde eu fico na fila.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none/2
    en  Nothing yet. Everyone else has a trade to fall back on and I have a reputation for being nearby.
    >>  ............................................
    pt  Nada ainda. Todo mundo tem um ofício pra cair e eu tenho fama de estar por perto.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none/1
    en  No trade. This morning it was the mill's sacks; this afternoon nobody has decided yet.
    >>  ............................................
    pt  Sem ofício. De manhã foram os sacos do moinho; à tarde ninguém decidiu ainda.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none/2
    en  Nothing with a title. Every trade here has had a week of me in it and nobody adds those weeks up.
    >>  ............................................
    pt  Nada com título. Todo ofício daqui teve uma semana minha e ninguém soma essas semanas.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none/1
    en  Between destinies! The lean season of a life, my gran called it. She was being kind.
    >>  ............................................
    pt  Entre destinos! A estação magra de uma vida, minha avó chamava. Ela estava sendo gentil.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none/2
    en  No trade yet. Which means every day is a surprise and some of the surprises are quite good.
    >>  ............................................
    pt  Sem ofício ainda. O que significa que todo dia é surpresa e algumas surpresas são boas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none/1
    en  Between destinies! The lean season of a life, my gran called it. She was being kind.
    >>  ............................................
    pt  Entre destinos! A estação magra de uma vida, minha avó chamava. Ela estava sendo gentil.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none/2
    en  No trade yet. Which means every day is a surprise and some of the surprises are quite good.
    >>  ............................................
    pt  Sem ofício ainda. O que significa que todo dia é surpresa e algumas surpresas são boas.
    >>  ............................................
```

</details>


**Outcome 16 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.respond`
- …where the player's next choices will be: "What's the worst part of a quiet shift?" | "Everyone here sleeps because you don't." | "Nothing ever happens here." | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.identity`: the villager explains. Subject `work.guard.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.guard/1   [87 chars]
    en  I hold the wall so everyone else gets to complain about smaller things. It's good work.
    >>  ............................................
    pt  Eu seguro o muro pra que todo mundo possa reclamar de coisas menores. É um bom trabalho.
    >>  ............................................
  dialogue.conversations.work.prof.guard/2   [89 chars]
    en  Quiet shifts are the victory, %1$s. Nobody thanks you for the monster that never arrived.
    >>  ............................................
    pt  Turno tranquilo é a vitória, %1$s. Ninguém te agradece pelo monstro que nunca chegou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years ago I was at the wrong gate, and I have never been at the wrong gate since.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos atrás eu estava no portão errado, e nunca mais estive.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.guard/2
    en  Guard. Everyone here is somebody's. If I'm slow, that lands on a family and not on me.
    >>  ............................................
    pt  Guarda. Todo mundo aqui é de alguém. Se eu for lento, isso cai numa família, não em mim.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. It's mostly weather and walking, and both suit me fine.
    >>  ............................................
    pt  Eu guardo a muralha. É quase só tempo e caminhada, e os dois me servem bem.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard/2
    en  Guard. Same round, same hour, and the sameness is the instrument. Vary it and you see nothing.
    >>  ............................................
    pt  Guarda. Mesma ronda, mesma hora, e a igualdade é o instrumento. Varie e você não vê nada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years without a second raid, and I do not get to claim that.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos sem um segundo ataque, e eu não posso reivindicar isso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard/2
    en  Guard. Standing here is the whole job and it is harder than it looks by about hour six.
    >>  ............................................
    pt  Guarda. Ficar aqui é todo o serviço e é mais difícil do que parece lá pela sexta hora.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years without a second raid, and I do not get to claim that.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos sem um segundo ataque, e eu não posso reivindicar isso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard/2
    en  Guard. Standing here is the whole job and it is harder than it looks by about hour six.
    >>  ............................................
    pt  Guarda. Ficar aqui é todo o serviço e é mais difícil do que parece lá pela sexta hora.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. Walk the perimeter with me one evening — company on a round is a rare offer.
    >>  ............................................
    pt  Eu guardo a muralha. Ande o perímetro comigo numa noite — companhia numa ronda é oferta rara.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard/2
    en  Guard. I know every door here, and which children are getting through which gate this week.
    >>  ............................................
    pt  Guarda. Conheço cada porta daqui, e quais crianças estão passando por qual portão esta semana.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. Walk the perimeter with me one evening — company on a round is a rare offer.
    >>  ............................................
    pt  Eu guardo a muralha. Ande o perímetro comigo numa noite — companhia numa ronda é oferta rara.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard/2
    en  Guard. I know every door here, and which children are getting through which gate this week.
    >>  ............................................
    pt  Guarda. Conheço cada porta daqui, e quais crianças estão passando por qual portão esta semana.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. Walk the perimeter with me one evening — company on a round is a rare offer.
    >>  ............................................
    pt  Eu guardo a muralha. Ande o perímetro comigo numa noite — companhia numa ronda é oferta rara.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard/2
    en  Guard. I know every door here, and which children are getting through which gate this week.
    >>  ............................................
    pt  Guarda. Conheço cada porta daqui, e quais crianças estão passando por qual portão esta semana.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years ago I was at the wrong gate, and I have never been at the wrong gate since.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos atrás eu estava no portão errado, e nunca mais estive.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard/2
    en  Guard. Everyone here is somebody's. If I'm slow, that lands on a family and not on me.
    >>  ............................................
    pt  Guarda. Todo mundo aqui é de alguém. Se eu for lento, isso cai numa família, não em mim.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years without a second raid, and I do not get to claim that.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos sem um segundo ataque, e eu não posso reivindicar isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard/2
    en  Guard. Standing here is the whole job and it is harder than it looks by about hour six.
    >>  ............................................
    pt  Guarda. Ficar aqui é todo o serviço e é mais difícil do que parece lá pela sexta hora.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years without a second raid, and I do not get to claim that.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos sem um segundo ataque, e eu não posso reivindicar isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard/2
    en  Guard. Standing here is the whole job and it is harder than it looks by about hour six.
    >>  ............................................
    pt  Guarda. Ficar aqui é todo o serviço e é mais difícil do que parece lá pela sexta hora.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard/1
    en  I watch. The skill is noticing what's different, and it takes years before there's anything to notice.
    >>  ............................................
    pt  Eu observo. A habilidade é reparar no que mudou, e leva anos até ter algo pra reparar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard/2
    en  Guard. Six doors in this place don't lock. I walk past those six twice a night and say nothing.
    >>  ............................................
    pt  Guarda. Seis portas daqui não trancam. Passo por elas duas vezes por noite e não digo nada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. It's mostly weather and walking, and both suit me fine.
    >>  ............................................
    pt  Eu guardo a muralha. É quase só tempo e caminhada, e os dois me servem bem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard/2
    en  Guard. Same round, same hour, and the sameness is the instrument. Vary it and you see nothing.
    >>  ............................................
    pt  Guarda. Mesma ronda, mesma hora, e a igualdade é o instrumento. Varie e você não vê nada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard/1
    en  I watch. The skill is noticing what's different, and it takes years before there's anything to notice.
    >>  ............................................
    pt  Eu observo. A habilidade é reparar no que mudou, e leva anos até ter algo pra reparar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard/2
    en  Guard. Six doors in this place don't lock. I walk past those six twice a night and say nothing.
    >>  ............................................
    pt  Guarda. Seis portas daqui não trancam. Passo por elas duas vezes por noite e não digo nada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. It's mostly weather and walking, and both suit me fine.
    >>  ............................................
    pt  Eu guardo a muralha. É quase só tempo e caminhada, e os dois me servem bem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard/2
    en  Guard. Same round, same hour, and the sameness is the instrument. Vary it and you see nothing.
    >>  ............................................
    pt  Guarda. Mesma ronda, mesma hora, e a igualdade é o instrumento. Varie e você não vê nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard/1
    en  I stand about looking solid so everyone else can get on with the interesting problems.
    >>  ............................................
    pt  Fico por aqui parecendo sólido pra todo mundo poder cuidar dos problemas interessantes.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard/2
    en  Guard! Best-informed person in the village and legally required to keep it to myself. Tragic.
    >>  ............................................
    pt  Guarda! A pessoa mais informada do vilarejo e obrigado a guardar pra mim. Trágico.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard/1
    en  I stand about looking solid so everyone else can get on with the interesting problems.
    >>  ............................................
    pt  Fico por aqui parecendo sólido pra todo mundo poder cuidar dos problemas interessantes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard/2
    en  Guard! Best-informed person in the village and legally required to keep it to myself. Tragic.
    >>  ............................................
    pt  Guarda! A pessoa mais informada do vilarejo e obrigado a guardar pra mim. Trágico.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard/1
    en  I keep the wall. It's mostly weather and walking, and both suit me fine.
    >>  ............................................
    pt  Eu guardo a muralha. É quase só tempo e caminhada, e os dois me servem bem.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard/2
    en  Guard. Same round, same hour, and the sameness is the instrument. Vary it and you see nothing.
    >>  ............................................
    pt  Guarda. Mesma ronda, mesma hora, e a igualdade é o instrumento. Varie e você não vê nada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard/1
    en  I hold the wall. Four years ago I was at the wrong gate, and I have never been at the wrong gate since.
    >>  ............................................
    pt  Eu guardo a muralha. Quatro anos atrás eu estava no portão errado, e nunca mais estive.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard/2
    en  Guard. Everyone here is somebody's. If I'm slow, that lands on a family and not on me.
    >>  ............................................
    pt  Guarda. Todo mundo aqui é de alguém. Se eu for lento, isso cai numa família, não em mim.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard/1
    en  I watch. The skill is noticing what's different, and it takes years before there's anything to notice.
    >>  ............................................
    pt  Eu observo. A habilidade é reparar no que mudou, e leva anos até ter algo pra reparar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard/2
    en  Guard. Six doors in this place don't lock. I walk past those six twice a night and say nothing.
    >>  ............................................
    pt  Guarda. Seis portas daqui não trancam. Passo por elas duas vezes por noite e não digo nada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard/1
    en  I stand about looking solid so everyone else can get on with the interesting problems.
    >>  ............................................
    pt  Fico por aqui parecendo sólido pra todo mundo poder cuidar dos problemas interessantes.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard/2
    en  Guard! Best-informed person in the village and legally required to keep it to myself. Tragic.
    >>  ............................................
    pt  Guarda! A pessoa mais informada do vilarejo e obrigado a guardar pra mim. Trágico.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard/1
    en  I stand about looking solid so everyone else can get on with the interesting problems.
    >>  ............................................
    pt  Fico por aqui parecendo sólido pra todo mundo poder cuidar dos problemas interessantes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard/2
    en  Guard! Best-informed person in the village and legally required to keep it to myself. Tragic.
    >>  ............................................
    pt  Guarda! A pessoa mais informada do vilarejo e obrigado a guardar pra mim. Trágico.
    >>  ............................................
```

</details>


**Outcome 17 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.respond`
- …where the player's next choices will be: "What makes you hold the shot?" | "You see this place more clearly than anyone." | "You just stand up there all day." | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.identity`: the villager explains. Subject `work.archer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.archer/1   [102 chars]
    en  From the tower I see everything — who's brave, who's sneaking, whose roof needs mending. Mostly roofs.
    >>  ............................................
    pt  Da torre eu vejo tudo — quem é corajoso, quem está de esgueirada, qual telhado precisa de conserto. Telhado, principalmente.
    >>  ............................................
  dialogue.conversations.work.prof.archer/2   [89 chars]
    en  One arrow, one warning. I haven't needed a second in years, and I practice so I never do.
    >>  ............................................
    pt  Uma flecha, um aviso. Faz anos que não preciso de uma segunda, e eu treino pra nunca precisar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.archer/1
    en  I keep the wall. Every quiet night is one I spent waiting for the other kind.
    >>  ............................................
    pt  Eu guardo a muralha. Toda noite calma é uma que eu passei esperando a outra.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.archer/2
    en  I'm the one on the tower. Somebody has to be, and I've never got used to being first seen.
    >>  ............................................
    pt  Sou eu na torre. Alguém tem que ser, e eu nunca me acostumei a ser vista primeiro.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer/1
    en  I'm on the wall. It's mostly weather and waiting, and I've made my peace with both.
    >>  ............................................
    pt  Eu fico na muralha. É quase só tempo e espera, e eu fiz as pazes com os dois.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer/2
    en  Six years on that tower. It suits me — nothing up there ever needs deciding in a hurry.
    >>  ............................................
    pt  Seis anos naquela torre. Me serve — nada lá em cima precisa ser decidido com pressa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer/1
    en  I hold the tower. Nothing has come over that wall in six years and that is not luck.
    >>  ............................................
    pt  Eu guardo a torre. Nada passou aquela muralha em seis anos e isso não é sorte.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer/2
    en  I shoot. Two hundred draws a morning is why the answer is yes when it matters.
    >>  ............................................
    pt  Eu atiro. Duzentos puxões por manhã é por que a resposta é sim quando importa.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer/1
    en  I hold the tower. Nothing has come over that wall in six years and that is not luck.
    >>  ............................................
    pt  Eu guardo a torre. Nada passou aquela muralha em seis anos e isso não é sorte.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer/2
    en  I shoot. Two hundred draws a morning is why the answer is yes when it matters.
    >>  ............................................
    pt  Eu atiro. Duzentos puxões por manhã é por que a resposta é sim quando importa.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer/1
    en  I keep the tower. Come up sometime — everyone looks better from that height, you included.
    >>  ............................................
    pt  Eu cuido da torre. Suba um dia — todo mundo fica melhor daquela altura, você inclusive.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer/2
    en  I watch over the place. It's less lonely than it sounds; half the village waves on the way past.
    >>  ............................................
    pt  Eu vigio o lugar. É menos solitário do que parece; metade do vilarejo acena ao passar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer/1
    en  I keep the tower. Come up sometime — everyone looks better from that height, you included.
    >>  ............................................
    pt  Eu cuido da torre. Suba um dia — todo mundo fica melhor daquela altura, você inclusive.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer/2
    en  I watch over the place. It's less lonely than it sounds; half the village waves on the way past.
    >>  ............................................
    pt  Eu vigio o lugar. É menos solitário do que parece; metade do vilarejo acena ao passar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer/1
    en  I keep the tower. Come up sometime — everyone looks better from that height, you included.
    >>  ............................................
    pt  Eu cuido da torre. Suba um dia — todo mundo fica melhor daquela altura, você inclusive.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer/2
    en  I watch over the place. It's less lonely than it sounds; half the village waves on the way past.
    >>  ............................................
    pt  Eu vigio o lugar. É menos solitário do que parece; metade do vilarejo acena ao passar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer/1
    en  I keep the wall. Every quiet night is one I spent waiting for the other kind.
    >>  ............................................
    pt  Eu guardo a muralha. Toda noite calma é uma que eu passei esperando a outra.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer/2
    en  I'm the one on the tower. Somebody has to be, and I've never got used to being first seen.
    >>  ............................................
    pt  Sou eu na torre. Alguém tem que ser, e eu nunca me acostumei a ser vista primeiro.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer/1
    en  I hold the tower. Nothing has come over that wall in six years and that is not luck.
    >>  ............................................
    pt  Eu guardo a torre. Nada passou aquela muralha em seis anos e isso não é sorte.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer/2
    en  I shoot. Two hundred draws a morning is why the answer is yes when it matters.
    >>  ............................................
    pt  Eu atiro. Duzentos puxões por manhã é por que a resposta é sim quando importa.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer/1
    en  I hold the tower. Nothing has come over that wall in six years and that is not luck.
    >>  ............................................
    pt  Eu guardo a torre. Nada passou aquela muralha em seis anos e isso não é sorte.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer/2
    en  I shoot. Two hundred draws a morning is why the answer is yes when it matters.
    >>  ............................................
    pt  Eu atiro. Duzentos puxões por manhã é por que a resposta é sim quando importa.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer/1
    en  The tower. It's a good place for somebody who'd rather look than talk.
    >>  ............................................
    pt  A torre. É um bom lugar pra quem prefere olhar a falar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer/2
    en  I draw a bow two hundred times before noon. After that the day is quiet and mine.
    >>  ............................................
    pt  Puxo o arco duzentas vezes antes do meio-dia. Depois o dia é quieto e meu.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer/1
    en  I'm on the wall. It's mostly weather and waiting, and I've made my peace with both.
    >>  ............................................
    pt  Eu fico na muralha. É quase só tempo e espera, e eu fiz as pazes com os dois.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer/2
    en  Six years on that tower. It suits me — nothing up there ever needs deciding in a hurry.
    >>  ............................................
    pt  Seis anos naquela torre. Me serve — nada lá em cima precisa ser decidido com pressa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer/1
    en  The tower. It's a good place for somebody who'd rather look than talk.
    >>  ............................................
    pt  A torre. É um bom lugar pra quem prefere olhar a falar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer/2
    en  I draw a bow two hundred times before noon. After that the day is quiet and mine.
    >>  ............................................
    pt  Puxo o arco duzentas vezes antes do meio-dia. Depois o dia é quieto e meu.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer/1
    en  I'm on the wall. It's mostly weather and waiting, and I've made my peace with both.
    >>  ............................................
    pt  Eu fico na muralha. É quase só tempo e espera, e eu fiz as pazes com os dois.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer/2
    en  Six years on that tower. It suits me — nothing up there ever needs deciding in a hurry.
    >>  ............................................
    pt  Seis anos naquela torre. Me serve — nada lá em cima precisa ser decidido com pressa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer/1
    en  Best seat in the village and a bow to go with it. I'd not trade the view for a house.
    >>  ............................................
    pt  O melhor lugar do vilarejo e um arco de brinde. Não trocaria a vista por uma casa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer/2
    en  I watch the whole place from up there. Half of it is guarding and half is very good gossip.
    >>  ............................................
    pt  Eu vejo o lugar inteiro lá de cima. Metade é guarda e metade é uma fofoca excelente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer/1
    en  Best seat in the village and a bow to go with it. I'd not trade the view for a house.
    >>  ............................................
    pt  O melhor lugar do vilarejo e um arco de brinde. Não trocaria a vista por uma casa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer/2
    en  I watch the whole place from up there. Half of it is guarding and half is very good gossip.
    >>  ............................................
    pt  Eu vejo o lugar inteiro lá de cima. Metade é guarda e metade é uma fofoca excelente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer/1
    en  I'm on the wall. It's mostly weather and waiting, and I've made my peace with both.
    >>  ............................................
    pt  Eu fico na muralha. É quase só tempo e espera, e eu fiz as pazes com os dois.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer/2
    en  Six years on that tower. It suits me — nothing up there ever needs deciding in a hurry.
    >>  ............................................
    pt  Seis anos naquela torre. Me serve — nada lá em cima precisa ser decidido com pressa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer/1
    en  I keep the wall. Every quiet night is one I spent waiting for the other kind.
    >>  ............................................
    pt  Eu guardo a muralha. Toda noite calma é uma que eu passei esperando a outra.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer/2
    en  I'm the one on the tower. Somebody has to be, and I've never got used to being first seen.
    >>  ............................................
    pt  Sou eu na torre. Alguém tem que ser, e eu nunca me acostumei a ser vista primeiro.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer/1
    en  The tower. It's a good place for somebody who'd rather look than talk.
    >>  ............................................
    pt  A torre. É um bom lugar pra quem prefere olhar a falar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer/2
    en  I draw a bow two hundred times before noon. After that the day is quiet and mine.
    >>  ............................................
    pt  Puxo o arco duzentas vezes antes do meio-dia. Depois o dia é quieto e meu.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer/1
    en  Best seat in the village and a bow to go with it. I'd not trade the view for a house.
    >>  ............................................
    pt  O melhor lugar do vilarejo e um arco de brinde. Não trocaria a vista por uma casa.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer/2
    en  I watch the whole place from up there. Half of it is guarding and half is very good gossip.
    >>  ............................................
    pt  Eu vejo o lugar inteiro lá de cima. Metade é guarda e metade é uma fofoca excelente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer/1
    en  Best seat in the village and a bow to go with it. I'd not trade the view for a house.
    >>  ............................................
    pt  O melhor lugar do vilarejo e um arco de brinde. Não trocaria a vista por uma casa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer/2
    en  I watch the whole place from up there. Half of it is guarding and half is very good gossip.
    >>  ............................................
    pt  Eu vejo o lugar inteiro lá de cima. Metade é guarda e metade é uma fofoca excelente.
    >>  ............................................
```

</details>


**Outcome 18 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.respond`
- …where the player's next choices will be: "What actually goes wrong out there?" | "The stories you bring back are worth something here." | "Half of that's invented." | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.identity`: the villager explains. Subject `work.adventurer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.adventurer/1   [103 chars]
    en  The road's my trade. I rest here between chapters — villages are where adventurers footnote themselves.
    >>  ............................................
    pt  A estrada é o meu ofício. Descanso aqui entre capítulos — vilarejos são onde os aventureiros viram nota de rodapé.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer/2   [100 chars]
    en  I've seen the nether's ceiling and the ocean's floor. The scariest thing is still an empty food bag.
    >>  ............................................
    pt  Já vi o teto do Nether e o fundo do oceano. A coisa mais assustadora ainda é uma bolsa de comida vazia.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.adventurer/1
    en  I travel. It sounds better than it is — you arrive somewhere and nobody was waiting.
    >>  ............................................
    pt  Eu viajo. Soa melhor do que é — você chega em algum lugar e ninguém estava esperando.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.adventurer/2
    en  The road. I keep going out because the staying still is the part I've never learned.
    >>  ............................................
    pt  A estrada. Continuo saindo porque ficar parado é a parte que eu nunca aprendi.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer/1
    en  I travel, when the season suits. There's no hurry in it that I've ever found.
    >>  ............................................
    pt  Eu viajo, quando a estação permite. Nunca achei pressa nisso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer/2
    en  The road, and a long stop at the end of it. The stops are getting longer every year.
    >>  ............................................
    pt  A estrada, e uma parada longa no fim. As paradas ficam mais longas todo ano.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer/1
    en  The road. I'm good at it, I'm paid badly for it, and I'd not swap.
    >>  ............................................
    pt  A estrada. Eu sou bom nisso, me pagam mal por isso, e eu não trocaria.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer/2
    en  I go where the map stops and I come back. That's the trade, start to finish.
    >>  ............................................
    pt  Vou aonde o mapa acaba e volto. É o ofício, do começo ao fim.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer/1
    en  The road. I'm good at it, I'm paid badly for it, and I'd not swap.
    >>  ............................................
    pt  A estrada. Eu sou bom nisso, me pagam mal por isso, e eu não trocaria.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer/2
    en  I go where the map stops and I come back. That's the trade, start to finish.
    >>  ............................................
    pt  Vou aonde o mapa acaba e volto. É o ofício, do começo ao fim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer/1
    en  The road, mostly. But it's the coming back that I'd talk about, if you've an hour.
    >>  ............................................
    pt  A estrada, principalmente. Mas é a volta que eu falaria sobre, se você tiver uma hora.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer/2
    en  I travel. What I actually do is carry news between people who'd otherwise never hear of each other.
    >>  ............................................
    pt  Eu viajo. O que eu faço mesmo é levar notícia entre gente que nunca saberia uma da outra.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer/1
    en  The road, mostly. But it's the coming back that I'd talk about, if you've an hour.
    >>  ............................................
    pt  A estrada, principalmente. Mas é a volta que eu falaria sobre, se você tiver uma hora.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer/2
    en  I travel. What I actually do is carry news between people who'd otherwise never hear of each other.
    >>  ............................................
    pt  Eu viajo. O que eu faço mesmo é levar notícia entre gente que nunca saberia uma da outra.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer/1
    en  The road, mostly. But it's the coming back that I'd talk about, if you've an hour.
    >>  ............................................
    pt  A estrada, principalmente. Mas é a volta que eu falaria sobre, se você tiver uma hora.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer/2
    en  I travel. What I actually do is carry news between people who'd otherwise never hear of each other.
    >>  ............................................
    pt  Eu viajo. O que eu faço mesmo é levar notícia entre gente que nunca saberia uma da outra.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer/1
    en  I travel. It sounds better than it is — you arrive somewhere and nobody was waiting.
    >>  ............................................
    pt  Eu viajo. Soa melhor do que é — você chega em algum lugar e ninguém estava esperando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer/2
    en  The road. I keep going out because the staying still is the part I've never learned.
    >>  ............................................
    pt  A estrada. Continuo saindo porque ficar parado é a parte que eu nunca aprendi.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer/1
    en  The road. I'm good at it, I'm paid badly for it, and I'd not swap.
    >>  ............................................
    pt  A estrada. Eu sou bom nisso, me pagam mal por isso, e eu não trocaria.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer/2
    en  I go where the map stops and I come back. That's the trade, start to finish.
    >>  ............................................
    pt  Vou aonde o mapa acaba e volto. É o ofício, do começo ao fim.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer/1
    en  The road. I'm good at it, I'm paid badly for it, and I'd not swap.
    >>  ............................................
    pt  A estrada. Eu sou bom nisso, me pagam mal por isso, e eu não trocaria.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer/2
    en  I go where the map stops and I come back. That's the trade, start to finish.
    >>  ............................................
    pt  Vou aonde o mapa acaba e volto. É o ofício, do começo ao fim.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer/1
    en  I walk. Then I sit down somewhere and mend a strap, and that's most of it.
    >>  ............................................
    pt  Eu caminho. Aí sento em algum lugar e conserto uma correia, e é quase tudo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer/2
    en  There's a milestone four days east with my initials on it. That's the whole of my trade, really.
    >>  ............................................
    pt  Tem um marco a quatro dias a leste com minhas iniciais. É todo o meu ofício, na verdade.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer/1
    en  I travel, when the season suits. There's no hurry in it that I've ever found.
    >>  ............................................
    pt  Eu viajo, quando a estação permite. Nunca achei pressa nisso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer/2
    en  The road, and a long stop at the end of it. The stops are getting longer every year.
    >>  ............................................
    pt  A estrada, e uma parada longa no fim. As paradas ficam mais longas todo ano.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer/1
    en  I walk. Then I sit down somewhere and mend a strap, and that's most of it.
    >>  ............................................
    pt  Eu caminho. Aí sento em algum lugar e conserto uma correia, e é quase tudo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer/2
    en  There's a milestone four days east with my initials on it. That's the whole of my trade, really.
    >>  ............................................
    pt  Tem um marco a quatro dias a leste com minhas iniciais. É todo o meu ofício, na verdade.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer/1
    en  I travel, when the season suits. There's no hurry in it that I've ever found.
    >>  ............................................
    pt  Eu viajo, quando a estação permite. Nunca achei pressa nisso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer/2
    en  The road, and a long stop at the end of it. The stops are getting longer every year.
    >>  ............................................
    pt  A estrada, e uma parada longa no fim. As paradas ficam mais longas todo ano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer/1
    en  The road! Best trade there is — new hills, new inns, and nobody knows your old mistakes.
    >>  ............................................
    pt  A estrada! O melhor ofício que existe — morros novos, estalagens novas, e ninguém sabe dos seus erros antigos.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer/2
    en  I collect places the way other folk collect plates. Fewer shelves needed, better stories.
    >>  ............................................
    pt  Coleciono lugares como os outros colecionam pratos. Menos prateleira, histórias melhores.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer/1
    en  The road! Best trade there is — new hills, new inns, and nobody knows your old mistakes.
    >>  ............................................
    pt  A estrada! O melhor ofício que existe — morros novos, estalagens novas, e ninguém sabe dos seus erros antigos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer/2
    en  I collect places the way other folk collect plates. Fewer shelves needed, better stories.
    >>  ............................................
    pt  Coleciono lugares como os outros colecionam pratos. Menos prateleira, histórias melhores.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer/1
    en  I travel, when the season suits. There's no hurry in it that I've ever found.
    >>  ............................................
    pt  Eu viajo, quando a estação permite. Nunca achei pressa nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer/2
    en  The road, and a long stop at the end of it. The stops are getting longer every year.
    >>  ............................................
    pt  A estrada, e uma parada longa no fim. As paradas ficam mais longas todo ano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer/1
    en  I travel. It sounds better than it is — you arrive somewhere and nobody was waiting.
    >>  ............................................
    pt  Eu viajo. Soa melhor do que é — você chega em algum lugar e ninguém estava esperando.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer/2
    en  The road. I keep going out because the staying still is the part I've never learned.
    >>  ............................................
    pt  A estrada. Continuo saindo porque ficar parado é a parte que eu nunca aprendi.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer/1
    en  I walk. Then I sit down somewhere and mend a strap, and that's most of it.
    >>  ............................................
    pt  Eu caminho. Aí sento em algum lugar e conserto uma correia, e é quase tudo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer/2
    en  There's a milestone four days east with my initials on it. That's the whole of my trade, really.
    >>  ............................................
    pt  Tem um marco a quatro dias a leste com minhas iniciais. É todo o meu ofício, na verdade.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer/1
    en  The road! Best trade there is — new hills, new inns, and nobody knows your old mistakes.
    >>  ............................................
    pt  A estrada! O melhor ofício que existe — morros novos, estalagens novas, e ninguém sabe dos seus erros antigos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer/2
    en  I collect places the way other folk collect plates. Fewer shelves needed, better stories.
    >>  ............................................
    pt  Coleciono lugares como os outros colecionam pratos. Menos prateleira, histórias melhores.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer/1
    en  The road! Best trade there is — new hills, new inns, and nobody knows your old mistakes.
    >>  ............................................
    pt  A estrada! O melhor ofício que existe — morros novos, estalagens novas, e ninguém sabe dos seus erros antigos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer/2
    en  I collect places the way other folk collect plates. Fewer shelves needed, better stories.
    >>  ............................................
    pt  Coleciono lugares como os outros colecionam pratos. Menos prateleira, histórias melhores.
    >>  ............................................
```

</details>


**Outcome 19 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.respond`
- …where the player's next choices will be: "Where's the line you won't cross?" | "You've stayed here longer than a contract explains." | "You fight for whoever pays." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.identity`: the villager explains. Subject `work.mercenary.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.mercenary/1   [93 chars]
    en  Coin for steel, steel for coin. I keep my contracts and my blade clean. One of those is hard.
    >>  ............................................
    pt  Moeda por aço, aço por moeda. Mantenho meus contratos e minha lâmina limpos. Um desses é difícil.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary/2   [77 chars]
    en  Don't romanticize it, %1$s. It's a job with worse weather and better stories.
    >>  ............................................
    pt  Não romantiza isso, %1$s. É um emprego com tempo pior e histórias melhores.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mercenary/1
    en  I fight for pay. The risk isn't dying — it's the year you're hungry enough to take the one you'd refuse.
    >>  ............................................
    pt  Eu luto por pagamento. O risco não é morrer — é o ano em que a fome basta pra você aceitar o que recusaria.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. People here are civil and none would leave me alone with their children. Both are fair.
    >>  ............................................
    pt  Mercenário. As pessoas aqui são civis e nenhuma me deixaria sozinho com os filhos. As duas coisas são justas.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel, when the work is honest. I'm in no hurry to find out what the other kind pays.
    >>  ............................................
    pt  Moeda por aço, quando o serviço é honesto. Não tenho pressa de descobrir quanto paga o outro tipo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nineteen years in a company of forty. Eleven of us are alive. I take my time now.
    >>  ............................................
    pt  Mercenário. Dezenove anos numa companhia de quarenta. Onze estamos vivos. Agora eu vou no meu tempo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I keep my contracts and I refuse the ones that won't say who is on the other side.
    >>  ............................................
    pt  Moeda por aço. Cumpro meus contratos e recuso os que não dizem quem está do outro lado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Half the trade is fighting and half is knowing which work to turn down. The second half took longer.
    >>  ............................................
    pt  Mercenário. Metade do ofício é lutar e metade é saber o que recusar. A segunda metade levou mais tempo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I keep my contracts and I refuse the ones that won't say who is on the other side.
    >>  ............................................
    pt  Moeda por aço. Cumpro meus contratos e recuso os que não dizem quem está do outro lado.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Half the trade is fighting and half is knowing which work to turn down. The second half took longer.
    >>  ............................................
    pt  Mercenário. Metade do ofício é lutar e metade é saber o que recusar. A segunda metade levou mais tempo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary/1
    en  I hire out my sword. And I'd rather have a wage and a name here than a purse and a road.
    >>  ............................................
    pt  Alugo minha espada. E prefiro salário e nome aqui do que bolsa e estrada.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. If there's harvest labour going, say so plainly — I'd take it and not be too proud.
    >>  ............................................
    pt  Mercenário. Se tem trabalho de colheita, diga direto — eu aceitaria e não seria orgulhoso demais.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary/1
    en  I hire out my sword. And I'd rather have a wage and a name here than a purse and a road.
    >>  ............................................
    pt  Alugo minha espada. E prefiro salário e nome aqui do que bolsa e estrada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. If there's harvest labour going, say so plainly — I'd take it and not be too proud.
    >>  ............................................
    pt  Mercenário. Se tem trabalho de colheita, diga direto — eu aceitaria e não seria orgulhoso demais.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary/1
    en  I hire out my sword. And I'd rather have a wage and a name here than a purse and a road.
    >>  ............................................
    pt  Alugo minha espada. E prefiro salário e nome aqui do que bolsa e estrada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. If there's harvest labour going, say so plainly — I'd take it and not be too proud.
    >>  ............................................
    pt  Mercenário. Se tem trabalho de colheita, diga direto — eu aceitaria e não seria orgulhoso demais.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary/1
    en  I fight for pay. The risk isn't dying — it's the year you're hungry enough to take the one you'd refuse.
    >>  ............................................
    pt  Eu luto por pagamento. O risco não é morrer — é o ano em que a fome basta pra você aceitar o que recusaria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. People here are civil and none would leave me alone with their children. Both are fair.
    >>  ............................................
    pt  Mercenário. As pessoas aqui são civis e nenhuma me deixaria sozinho com os filhos. As duas coisas são justas.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I keep my contracts and I refuse the ones that won't say who is on the other side.
    >>  ............................................
    pt  Moeda por aço. Cumpro meus contratos e recuso os que não dizem quem está do outro lado.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Half the trade is fighting and half is knowing which work to turn down. The second half took longer.
    >>  ............................................
    pt  Mercenário. Metade do ofício é lutar e metade é saber o que recusar. A segunda metade levou mais tempo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I keep my contracts and I refuse the ones that won't say who is on the other side.
    >>  ............................................
    pt  Moeda por aço. Cumpro meus contratos e recuso os que não dizem quem está do outro lado.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Half the trade is fighting and half is knowing which work to turn down. The second half took longer.
    >>  ............................................
    pt  Mercenário. Metade do ofício é lutar e metade é saber o que recusar. A segunda metade levou mais tempo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I clean kit nobody has paid me to use this month, because that's the habit.
    >>  ............................................
    pt  Moeda por aço. Limpo equipamento que ninguém pagou pra eu usar este mês, porque é o hábito.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. I read a contract twice when the pay is high for the work described. It usually isn't the work.
    >>  ............................................
    pt  Mercenário. Leio um contrato duas vezes quando o pagamento é alto pro serviço descrito. Normalmente não é o serviço.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel, when the work is honest. I'm in no hurry to find out what the other kind pays.
    >>  ............................................
    pt  Moeda por aço, quando o serviço é honesto. Não tenho pressa de descobrir quanto paga o outro tipo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nineteen years in a company of forty. Eleven of us are alive. I take my time now.
    >>  ............................................
    pt  Mercenário. Dezenove anos numa companhia de quarenta. Onze estamos vivos. Agora eu vou no meu tempo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I clean kit nobody has paid me to use this month, because that's the habit.
    >>  ............................................
    pt  Moeda por aço. Limpo equipamento que ninguém pagou pra eu usar este mês, porque é o hábito.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. I read a contract twice when the pay is high for the work described. It usually isn't the work.
    >>  ............................................
    pt  Mercenário. Leio um contrato duas vezes quando o pagamento é alto pro serviço descrito. Normalmente não é o serviço.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel, when the work is honest. I'm in no hurry to find out what the other kind pays.
    >>  ............................................
    pt  Moeda por aço, quando o serviço é honesto. Não tenho pressa de descobrir quanto paga o outro tipo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nineteen years in a company of forty. Eleven of us are alive. I take my time now.
    >>  ............................................
    pt  Mercenário. Dezenove anos numa companhia de quarenta. Onze estamos vivos. Agora eu vou no meu tempo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary/1
    en  Sword for hire, and currently very available. Escort work preferred — it's dull and nobody's family gets a visit.
    >>  ............................................
    pt  Espada de aluguel, e no momento muito disponível. Prefiro escolta — é maçante e nenhuma família recebe visita.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nine days between contracts, which is nine days of learning to enjoy mending straps.
    >>  ............................................
    pt  Mercenário. Nove dias entre contratos, que são nove dias aprendendo a gostar de consertar correias.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary/1
    en  Sword for hire, and currently very available. Escort work preferred — it's dull and nobody's family gets a visit.
    >>  ............................................
    pt  Espada de aluguel, e no momento muito disponível. Prefiro escolta — é maçante e nenhuma família recebe visita.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nine days between contracts, which is nine days of learning to enjoy mending straps.
    >>  ............................................
    pt  Mercenário. Nove dias entre contratos, que são nove dias aprendendo a gostar de consertar correias.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel, when the work is honest. I'm in no hurry to find out what the other kind pays.
    >>  ............................................
    pt  Moeda por aço, quando o serviço é honesto. Não tenho pressa de descobrir quanto paga o outro tipo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nineteen years in a company of forty. Eleven of us are alive. I take my time now.
    >>  ............................................
    pt  Mercenário. Dezenove anos numa companhia de quarenta. Onze estamos vivos. Agora eu vou no meu tempo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary/1
    en  I fight for pay. The risk isn't dying — it's the year you're hungry enough to take the one you'd refuse.
    >>  ............................................
    pt  Eu luto por pagamento. O risco não é morrer — é o ano em que a fome basta pra você aceitar o que recusaria.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. People here are civil and none would leave me alone with their children. Both are fair.
    >>  ............................................
    pt  Mercenário. As pessoas aqui são civis e nenhuma me deixaria sozinho com os filhos. As duas coisas são justas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary/1
    en  Coin for steel. I clean kit nobody has paid me to use this month, because that's the habit.
    >>  ............................................
    pt  Moeda por aço. Limpo equipamento que ninguém pagou pra eu usar este mês, porque é o hábito.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. I read a contract twice when the pay is high for the work described. It usually isn't the work.
    >>  ............................................
    pt  Mercenário. Leio um contrato duas vezes quando o pagamento é alto pro serviço descrito. Normalmente não é o serviço.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary/1
    en  Sword for hire, and currently very available. Escort work preferred — it's dull and nobody's family gets a visit.
    >>  ............................................
    pt  Espada de aluguel, e no momento muito disponível. Prefiro escolta — é maçante e nenhuma família recebe visita.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nine days between contracts, which is nine days of learning to enjoy mending straps.
    >>  ............................................
    pt  Mercenário. Nove dias entre contratos, que são nove dias aprendendo a gostar de consertar correias.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary/1
    en  Sword for hire, and currently very available. Escort work preferred — it's dull and nobody's family gets a visit.
    >>  ............................................
    pt  Espada de aluguel, e no momento muito disponível. Prefiro escolta — é maçante e nenhuma família recebe visita.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary/2
    en  Mercenary. Nine days between contracts, which is nine days of learning to enjoy mending straps.
    >>  ............................................
    pt  Mercenário. Nove dias entre contratos, que são nove dias aprendendo a gostar de consertar correias.
    >>  ............................................
```

</details>


**Outcome 20 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.respond`
- …where the player's next choices will be: "What do the neighbours actually think?" | "You've never actually harmed anyone here." | "You don't believe a word of it." | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.identity`: the villager explains. Subject `work.cultist.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.cultist/1   [97 chars]
    en  We are a BOOK CLUB. A perfectly ordinary book club. The chanting is... enthusiasm for literature.
    >>  ............................................
    pt  Nós somos um CLUBE DO LIVRO. Um clube do livro perfeitamente comum. O cântico é... entusiasmo pela literatura.
    >>  ............................................
  dialogue.conversations.work.prof.cultist/2   [101 chars]
    en  The stars whisper such things, %1$s. Would you like a pamphlet? Everyone eventually takes a pamphlet.
    >>  ............................................
    pt  As estrelas sussurram tais coisas, %1$s. Aceita um panfleto? Todo mundo acaba aceitando um panfleto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cultist/1
    en  We keep an old rite. If I am wrong about all of it, I have given forty years to a lamp.
    >>  ............................................
    pt  Mantemos um rito antigo. Se eu estiver errado sobre tudo, dei quarenta anos a uma lamparina.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cultist/2
    en  I keep the names of everyone who has died here for ninety years. Somebody has to, and it weighs.
    >>  ............................................
    pt  Guardo os nomes de todos que morreram aqui por noventa anos. Alguém tem que guardar, e pesa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist/1
    en  An old observance. It was here before me and it will be here after, and that suits me.
    >>  ............................................
    pt  Uma observância antiga. Estava aqui antes de mim e estará depois, e isso me serve.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist/2
    en  We keep a rite. Nothing about it is urgent, which is most of why it has lasted.
    >>  ............................................
    pt  Mantemos um rito. Nada nele é urgente, e é quase toda a razão de ter durado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. The village finds it strange and I have never asked it not to.
    >>  ............................................
    pt  Mantemos uma observância antiga. O vilarejo acha estranho e eu nunca pedi que não achasse.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist/2
    en  I keep the lamp and forty lines of names. That is the whole of it and I will not decorate it.
    >>  ............................................
    pt  Cuido da lamparina e de quarenta linhas de nomes. É tudo e eu não vou enfeitar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. The village finds it strange and I have never asked it not to.
    >>  ............................................
    pt  Mantemos uma observância antiga. O vilarejo acha estranho e eu nunca pedi que não achasse.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist/2
    en  I keep the lamp and forty lines of names. That is the whole of it and I will not decorate it.
    >>  ............................................
    pt  Cuido da lamparina e de quarenta linhas de nomes. É tudo e eu não vou enfeitar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. Two families come and eleven don't, and I light it for all thirteen.
    >>  ............................................
    pt  Mantemos uma observância antiga. Duas famílias vêm e onze não, e eu acendo pelas treze.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist/2
    en  It's an order, of a sort. Ask me about it properly some evening and I'll tell you more than you expect.
    >>  ............................................
    pt  É uma ordem, de certo modo. Me pergunte direito numa noite e eu conto mais do que você espera.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. Two families come and eleven don't, and I light it for all thirteen.
    >>  ............................................
    pt  Mantemos uma observância antiga. Duas famílias vêm e onze não, e eu acendo pelas treze.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist/2
    en  It's an order, of a sort. Ask me about it properly some evening and I'll tell you more than you expect.
    >>  ............................................
    pt  É uma ordem, de certo modo. Me pergunte direito numa noite e eu conto mais do que você espera.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. Two families come and eleven don't, and I light it for all thirteen.
    >>  ............................................
    pt  Mantemos uma observância antiga. Duas famílias vêm e onze não, e eu acendo pelas treze.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist/2
    en  It's an order, of a sort. Ask me about it properly some evening and I'll tell you more than you expect.
    >>  ............................................
    pt  É uma ordem, de certo modo. Me pergunte direito numa noite e eu conto mais do que você espera.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist/1
    en  We keep an old rite. If I am wrong about all of it, I have given forty years to a lamp.
    >>  ............................................
    pt  Mantemos um rito antigo. Se eu estiver errado sobre tudo, dei quarenta anos a uma lamparina.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist/2
    en  I keep the names of everyone who has died here for ninety years. Somebody has to, and it weighs.
    >>  ............................................
    pt  Guardo os nomes de todos que morreram aqui por noventa anos. Alguém tem que guardar, e pesa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. The village finds it strange and I have never asked it not to.
    >>  ............................................
    pt  Mantemos uma observância antiga. O vilarejo acha estranho e eu nunca pedi que não achasse.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist/2
    en  I keep the lamp and forty lines of names. That is the whole of it and I will not decorate it.
    >>  ............................................
    pt  Cuido da lamparina e de quarenta linhas de nomes. É tudo e eu não vou enfeitar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist/1
    en  We keep an old observance. The village finds it strange and I have never asked it not to.
    >>  ............................................
    pt  Mantemos uma observância antiga. O vilarejo acha estranho e eu nunca pedi que não achasse.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist/2
    en  I keep the lamp and forty lines of names. That is the whole of it and I will not decorate it.
    >>  ............................................
    pt  Cuido da lamparina e de quarenta linhas de nomes. É tudo e eu não vou enfeitar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist/1
    en  I copy forty lines by hand until the hand does it without me. That is the discipline.
    >>  ............................................
    pt  Copio quarenta linhas à mão até a mão fazer sem mim. É essa a disciplina.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist/2
    en  There is a lamp, and it does not go out, and I am the reason. It sounds larger than it is.
    >>  ............................................
    pt  Existe uma lamparina, e ela não apaga, e eu sou a razão. Soa maior do que é.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist/1
    en  An old observance. It was here before me and it will be here after, and that suits me.
    >>  ............................................
    pt  Uma observância antiga. Estava aqui antes de mim e estará depois, e isso me serve.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist/2
    en  We keep a rite. Nothing about it is urgent, which is most of why it has lasted.
    >>  ............................................
    pt  Mantemos um rito. Nada nele é urgente, e é quase toda a razão de ter durado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist/1
    en  I copy forty lines by hand until the hand does it without me. That is the discipline.
    >>  ............................................
    pt  Copio quarenta linhas à mão até a mão fazer sem mim. É essa a disciplina.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist/2
    en  There is a lamp, and it does not go out, and I am the reason. It sounds larger than it is.
    >>  ............................................
    pt  Existe uma lamparina, e ela não apaga, e eu sou a razão. Soa maior do que é.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist/1
    en  An old observance. It was here before me and it will be here after, and that suits me.
    >>  ............................................
    pt  Uma observância antiga. Estava aqui antes de mim e estará depois, e isso me serve.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist/2
    en  We keep a rite. Nothing about it is urgent, which is most of why it has lasted.
    >>  ............................................
    pt  Mantemos um rito. Nada nele é urgente, e é quase toda a razão de ter durado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist/1
    en  We're a reading group with candles. The candles are load-bearing. The reading is optional.
    >>  ............................................
    pt  Somos um grupo de leitura com velas. As velas são estruturais. A leitura é opcional.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist/2
    en  Old rites, older books, and a lamp somebody has to sit up with. It's cosier than the rumours.
    >>  ............................................
    pt  Ritos antigos, livros mais antigos, e uma lamparina com que alguém tem que velar. É mais aconchegante que os boatos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist/1
    en  We're a reading group with candles. The candles are load-bearing. The reading is optional.
    >>  ............................................
    pt  Somos um grupo de leitura com velas. As velas são estruturais. A leitura é opcional.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist/2
    en  Old rites, older books, and a lamp somebody has to sit up with. It's cosier than the rumours.
    >>  ............................................
    pt  Ritos antigos, livros mais antigos, e uma lamparina com que alguém tem que velar. É mais aconchegante que os boatos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist/1
    en  An old observance. It was here before me and it will be here after, and that suits me.
    >>  ............................................
    pt  Uma observância antiga. Estava aqui antes de mim e estará depois, e isso me serve.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist/2
    en  We keep a rite. Nothing about it is urgent, which is most of why it has lasted.
    >>  ............................................
    pt  Mantemos um rito. Nada nele é urgente, e é quase toda a razão de ter durado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist/1
    en  We keep an old rite. If I am wrong about all of it, I have given forty years to a lamp.
    >>  ............................................
    pt  Mantemos um rito antigo. Se eu estiver errado sobre tudo, dei quarenta anos a uma lamparina.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist/2
    en  I keep the names of everyone who has died here for ninety years. Somebody has to, and it weighs.
    >>  ............................................
    pt  Guardo os nomes de todos que morreram aqui por noventa anos. Alguém tem que guardar, e pesa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist/1
    en  I copy forty lines by hand until the hand does it without me. That is the discipline.
    >>  ............................................
    pt  Copio quarenta linhas à mão até a mão fazer sem mim. É essa a disciplina.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist/2
    en  There is a lamp, and it does not go out, and I am the reason. It sounds larger than it is.
    >>  ............................................
    pt  Existe uma lamparina, e ela não apaga, e eu sou a razão. Soa maior do que é.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist/1
    en  We're a reading group with candles. The candles are load-bearing. The reading is optional.
    >>  ............................................
    pt  Somos um grupo de leitura com velas. As velas são estruturais. A leitura é opcional.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist/2
    en  Old rites, older books, and a lamp somebody has to sit up with. It's cosier than the rumours.
    >>  ............................................
    pt  Ritos antigos, livros mais antigos, e uma lamparina com que alguém tem que velar. É mais aconchegante que os boatos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist/1
    en  We're a reading group with candles. The candles are load-bearing. The reading is optional.
    >>  ............................................
    pt  Somos um grupo de leitura com velas. As velas são estruturais. A leitura é opcional.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist/2
    en  Old rites, older books, and a lamp somebody has to sit up with. It's cosier than the rumours.
    >>  ............................................
    pt  Ritos antigos, livros mais antigos, e uma lamparina com que alguém tem que velar. É mais aconchegante que os boatos.
    >>  ............................................
```

</details>


**Outcome 21 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.respond`
- …where the player's next choices will be: "What's it actually cost you?" | "You've not taken anything of mine." | "You could just stop." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.identity`: the villager explains. Subject `work.outlaw.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.prof.outlaw/1   [92 chars]
    en  My line of work is... freelance redistribution. The mayor and I disagree on the terminology.
    >>  ............................................
    pt  Meu ramo é... redistribuição autônoma. O prefeito e eu discordamos quanto à terminologia.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw/2   [80 chars]
    en  Every village needs someone the sermons can point at. I provide a vital service.
    >>  ............................................
    pt  Todo vilarejo precisa de alguém pra quem os sermões possam apontar. Eu presto um serviço essencial.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Anything that goes missing here is mine until proven otherwise, and that never expires.
    >>  ............................................
    pt  Sem ofício. Qualquer coisa que suma aqui é minha até prova em contrário, e isso nunca vence.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.outlaw/2
    en  Nothing honest yet. There's a name for me in the next valley and one day somebody walks in who knows it.
    >>  ............................................
    pt  Nada honesto ainda. Tem um nome pra mim no vale vizinho e um dia alguém entra sabendo dele.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Four years of quiet, and quiet is the only thing I'm trying to be good at now.
    >>  ............................................
    pt  Sem ofício. Quatro anos de calma, e calma é a única coisa em que eu tento ser bom agora.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw/2
    en  Nothing to speak of. This place let me stay when it had no reason to, and I'm in no hurry to test that.
    >>  ............................................
    pt  Nada pra falar. Este lugar me deixou ficar sem motivo, e eu não tenho pressa de testar isso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw/1
    en  I've no trade here. I did something elsewhere and this place let me stay, and that is the whole of it.
    >>  ............................................
    pt  Não tenho ofício aqui. Fiz algo em outro lugar e este lugar me deixou ficar, e é tudo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw/2
    en  Freelance redistribution, the mayor calls it. He and I disagree on the terminology and not the facts.
    >>  ............................................
    pt  Redistribuição autônoma, é como o prefeito chama. Ele e eu discordamos do termo, não dos fatos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw/1
    en  I've no trade here. I did something elsewhere and this place let me stay, and that is the whole of it.
    >>  ............................................
    pt  Não tenho ofício aqui. Fiz algo em outro lugar e este lugar me deixou ficar, e é tudo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw/2
    en  Freelance redistribution, the mayor calls it. He and I disagree on the terminology and not the facts.
    >>  ............................................
    pt  Redistribuição autônoma, é como o prefeito chama. Ele e eu discordamos do termo, não dos fatos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I split kindling for the widow at the end of the lane, and she's the only one who asks me for anything.
    >>  ............................................
    pt  Sem ofício. Racho lenha pra viúva do fim da viela, e ela é a única que me pede algo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw/2
    en  Nothing official. But I'd take a trade tomorrow if somebody were willing to be seen teaching me one.
    >>  ............................................
    pt  Nada oficial. Mas eu aceitaria um ofício amanhã se alguém topasse ser visto me ensinando.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I split kindling for the widow at the end of the lane, and she's the only one who asks me for anything.
    >>  ............................................
    pt  Sem ofício. Racho lenha pra viúva do fim da viela, e ela é a única que me pede algo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw/2
    en  Nothing official. But I'd take a trade tomorrow if somebody were willing to be seen teaching me one.
    >>  ............................................
    pt  Nada oficial. Mas eu aceitaria um ofício amanhã se alguém topasse ser visto me ensinando.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I split kindling for the widow at the end of the lane, and she's the only one who asks me for anything.
    >>  ............................................
    pt  Sem ofício. Racho lenha pra viúva do fim da viela, e ela é a única que me pede algo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw/2
    en  Nothing official. But I'd take a trade tomorrow if somebody were willing to be seen teaching me one.
    >>  ............................................
    pt  Nada oficial. Mas eu aceitaria um ofício amanhã se alguém topasse ser visto me ensinando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Anything that goes missing here is mine until proven otherwise, and that never expires.
    >>  ............................................
    pt  Sem ofício. Qualquer coisa que suma aqui é minha até prova em contrário, e isso nunca vence.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw/2
    en  Nothing honest yet. There's a name for me in the next valley and one day somebody walks in who knows it.
    >>  ............................................
    pt  Nada honesto ainda. Tem um nome pra mim no vale vizinho e um dia alguém entra sabendo dele.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw/1
    en  I've no trade here. I did something elsewhere and this place let me stay, and that is the whole of it.
    >>  ............................................
    pt  Não tenho ofício aqui. Fiz algo em outro lugar e este lugar me deixou ficar, e é tudo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw/2
    en  Freelance redistribution, the mayor calls it. He and I disagree on the terminology and not the facts.
    >>  ............................................
    pt  Redistribuição autônoma, é como o prefeito chama. Ele e eu discordamos do termo, não dos fatos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw/1
    en  I've no trade here. I did something elsewhere and this place let me stay, and that is the whole of it.
    >>  ............................................
    pt  Não tenho ofício aqui. Fiz algo em outro lugar e este lugar me deixou ficar, e é tudo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw/2
    en  Freelance redistribution, the mayor calls it. He and I disagree on the terminology and not the facts.
    >>  ............................................
    pt  Redistribuição autônoma, é como o prefeito chama. Ele e eu discordamos do termo, não dos fatos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I sit where I can be seen doing nothing, which is a full day's occupation, oddly.
    >>  ............................................
    pt  Sem ofício. Sento onde posso ser visto sem fazer nada, o que é ocupação de um dia inteiro, curiosamente.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw/2
    en  Nothing now. What I'm good at is reading a room in four seconds, and I learned it very badly.
    >>  ............................................
    pt  Nada agora. No que eu sou bom é em ler uma sala em quatro segundos, e aprendi muito mal.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Four years of quiet, and quiet is the only thing I'm trying to be good at now.
    >>  ............................................
    pt  Sem ofício. Quatro anos de calma, e calma é a única coisa em que eu tento ser bom agora.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw/2
    en  Nothing to speak of. This place let me stay when it had no reason to, and I'm in no hurry to test that.
    >>  ............................................
    pt  Nada pra falar. Este lugar me deixou ficar sem motivo, e eu não tenho pressa de testar isso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I sit where I can be seen doing nothing, which is a full day's occupation, oddly.
    >>  ............................................
    pt  Sem ofício. Sento onde posso ser visto sem fazer nada, o que é ocupação de um dia inteiro, curiosamente.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw/2
    en  Nothing now. What I'm good at is reading a room in four seconds, and I learned it very badly.
    >>  ............................................
    pt  Nada agora. No que eu sou bom é em ler uma sala em quatro segundos, e aprendi muito mal.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Four years of quiet, and quiet is the only thing I'm trying to be good at now.
    >>  ............................................
    pt  Sem ofício. Quatro anos de calma, e calma é a única coisa em que eu tento ser bom agora.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw/2
    en  Nothing to speak of. This place let me stay when it had no reason to, and I'm in no hurry to test that.
    >>  ............................................
    pt  Nada pra falar. Este lugar me deixou ficar sem motivo, e eu não tenho pressa de testar isso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw/1
    en  Freelance redistribution! Retired. Mostly. The mayor still calls it something less flattering.
    >>  ............................................
    pt  Redistribuição autônoma! Aposentado. Quase. O prefeito ainda chama de algo menos lisonjeiro.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw/2
    en  No trade at present. I split kindling for a widow and I'm told that counts as reform.
    >>  ............................................
    pt  Sem ofício no momento. Eu racho lenha pra uma viúva e me disseram que isso conta como regeneração.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw/1
    en  Freelance redistribution! Retired. Mostly. The mayor still calls it something less flattering.
    >>  ............................................
    pt  Redistribuição autônoma! Aposentado. Quase. O prefeito ainda chama de algo menos lisonjeiro.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw/2
    en  No trade at present. I split kindling for a widow and I'm told that counts as reform.
    >>  ............................................
    pt  Sem ofício no momento. Eu racho lenha pra uma viúva e me disseram que isso conta como regeneração.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Four years of quiet, and quiet is the only thing I'm trying to be good at now.
    >>  ............................................
    pt  Sem ofício. Quatro anos de calma, e calma é a única coisa em que eu tento ser bom agora.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw/2
    en  Nothing to speak of. This place let me stay when it had no reason to, and I'm in no hurry to test that.
    >>  ............................................
    pt  Nada pra falar. Este lugar me deixou ficar sem motivo, e eu não tenho pressa de testar isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw/1
    en  No trade. Anything that goes missing here is mine until proven otherwise, and that never expires.
    >>  ............................................
    pt  Sem ofício. Qualquer coisa que suma aqui é minha até prova em contrário, e isso nunca vence.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw/2
    en  Nothing honest yet. There's a name for me in the next valley and one day somebody walks in who knows it.
    >>  ............................................
    pt  Nada honesto ainda. Tem um nome pra mim no vale vizinho e um dia alguém entra sabendo dele.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw/1
    en  No trade. I sit where I can be seen doing nothing, which is a full day's occupation, oddly.
    >>  ............................................
    pt  Sem ofício. Sento onde posso ser visto sem fazer nada, o que é ocupação de um dia inteiro, curiosamente.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw/2
    en  Nothing now. What I'm good at is reading a room in four seconds, and I learned it very badly.
    >>  ............................................
    pt  Nada agora. No que eu sou bom é em ler uma sala em quatro segundos, e aprendi muito mal.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw/1
    en  Freelance redistribution! Retired. Mostly. The mayor still calls it something less flattering.
    >>  ............................................
    pt  Redistribuição autônoma! Aposentado. Quase. O prefeito ainda chama de algo menos lisonjeiro.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw/2
    en  No trade at present. I split kindling for a widow and I'm told that counts as reform.
    >>  ............................................
    pt  Sem ofício no momento. Eu racho lenha pra uma viúva e me disseram que isso conta como regeneração.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw/1
    en  Freelance redistribution! Retired. Mostly. The mayor still calls it something less flattering.
    >>  ............................................
    pt  Redistribuição autônoma! Aposentado. Quase. O prefeito ainda chama de algo menos lisonjeiro.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw/2
    en  No trade at present. I split kindling for a widow and I'm told that counts as reform.
    >>  ............................................
    pt  Sem ofício no momento. Eu racho lenha pra uma viúva e me disseram que isso conta como regeneração.
    >>  ............................................
```

</details>

