# Topic: noticed — part 2 of 2

> Continued from [topic-noticed-part1.md](topic-noticed-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](topic-noticed-part1.md) · [part 2](topic-noticed-part2.md)


## Nodes in this file

- [`conversations.topic.noticed.grieving.respond`](#conversations-topic-noticed-grieving-respond)
- [`conversations.topic.noticed.grieving.supported.followup`](#conversations-topic-noticed-grieving-supported-followup)
- [`conversations.topic.noticed.guarded.followup`](#conversations-topic-noticed-guarded-followup)
- [`conversations.topic.noticed.guarded.respond`](#conversations-topic-noticed-guarded-respond)
- [`conversations.topic.noticed.hurt.followup`](#conversations-topic-noticed-hurt-followup)
- [`conversations.topic.noticed.hurt.respond`](#conversations-topic-noticed-hurt-respond)
- [`conversations.topic.noticed.proud.followup`](#conversations-topic-noticed-proud-followup)
- [`conversations.topic.noticed.proud.respond`](#conversations-topic-noticed-proud-respond)
- [`conversations.topic.noticed.worn.followup`](#conversations-topic-noticed-worn-followup)
- [`conversations.topic.noticed.worn.respond`](#conversations-topic-noticed-worn-respond)

---

## `conversations.topic.noticed.grieving.respond` — continued


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.give_space`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +1  _(recorded under topic `noticed.grieving.give_space`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.grieving.quiet.followup`
- …where the player's next choices will be: "I'll stay, and say nothing." | "Come and find me when you can." | "I'll see to anything that needs doing." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.give_space
WHO    VILLAGER — what the player reads after pressing "I'll not make you talk about it."
       spoken on: conversations.topic.noticed.grieving.respond, button `give_space`
       leaves the player on: conversations.topic.noticed.grieving.quiet.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.space_given`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent`, `villager:wants_quiet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, restraint, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.give_space/1   [59 chars]
    en  Thank you. Everyone wants the story. You didn't ask for it.
    >>  ............................................
    pt  Obrigado. Todo mundo quer a história. Você não pediu.
    >>  ............................................
  dialogue.conversations.noticed.grieving.give_space/2   [56 chars]
    en  That's a kindness. I'll come find you when I can say it.
    >>  ............................................
    pt  Isso é uma gentileza. Vou te procurar quando conseguir falar.
    >>  ............................................
  dialogue.conversations.noticed.grieving.give_space/3   [65 chars]
    en  Good. Sitting here quietly is the most anyone's helped this week.
    >>  ............................................
    pt  Bom. Ficar aqui em silêncio é a maior ajuda que recebi esta semana.
    >>  ............................................
```


### Button `dismiss` — "People die. That's life."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.grieving.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.dismiss` — accepted phrasings: "people die, that is life"; "it happens"; "that is life"
  - the message must contain one of: `die`, `life`, `happens`
  - scored words: `die`(1.5), `life`(1.2), `happens`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.respond.dismiss   [24 chars]
    en  People die. That's life.
    >>  ............................................
    pt  As pessoas morrem. É a vida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.grieving.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +6  _(recorded under topic `noticed.grieving.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.grieving.hostile.followup`
- …where the player's next choices will be: "That was cruel of me. I'm sorry." | "I meant it as comfort. It wasn't." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.dismiss
WHO    VILLAGER — what the player reads after pressing "People die. That's life."
       spoken on: conversations.topic.noticed.grieving.respond, button `dismiss`
       leaves the player on: conversations.topic.noticed.grieving.hostile.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.dismissed`: the villager hurts. Subject `noticed.grief`, polarity `acute`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent`, `player:dismissed_grief` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.dismiss/1   [26 chars]
    en  ...Get away from me, %1$s.
    >>  ............................................
    pt  ...Sai de perto de mim, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving.dismiss/2   [41 chars]
    en  That's life. Aye. Say that to the family.
    >>  ............................................
    pt  É a vida. É. Diga isso para a família.
    >>  ............................................
  dialogue.conversations.noticed.grieving.dismiss/3   [35 chars]
    en  I know people die. I knew this one.
    >>  ............................................
    pt  Eu sei que as pessoas morrem. Eu conhecia essa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me, %1$s. Please. Just go.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor. Só vá.
    >>  ............................................
  anxious.dialogue.conversations.noticed.grieving.dismiss/2
    en  Don't. I've nothing left to hold that with.
    >>  ............................................
    pt  Não. Não me sobrou nada pra segurar isso.
    >>  ............................................
  anxious.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...I can't. Not today. Please go.
    >>  ............................................
    pt  ...Eu não consigo. Hoje não. Por favor, vá.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. There's nothing here for you today.
    >>  ............................................
    pt  ...Saia de perto de mim. Não tem nada aqui pra você hoje.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go. I'll not hold it against you tomorrow, but go now.
    >>  ............................................
    pt  Vá. Amanhã eu não guardo mágoa, mas vá agora.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Leave me be a while.
    >>  ............................................
    pt  ...Não. Me deixe em paz um pouco.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving.dismiss/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving.dismiss/2
    en  No. Not from you, not about this, not today.
    >>  ............................................
    pt  Não. De você não, sobre isso não, hoje não.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...We're finished talking.
    >>  ............................................
    pt  ...Terminamos de conversar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving.dismiss/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving.dismiss/2
    en  No. Not from you, not about this, not today.
    >>  ............................................
    pt  Não. De você não, sobre isso não, hoje não.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...We're finished talking.
    >>  ............................................
    pt  ...Terminamos de conversar.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. I'd have taken that from anyone but you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu teria aguentado isso de qualquer um menos de você.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go, %1$s. Please. Before this becomes the thing I remember about you.
    >>  ............................................
    pt  Vá, %1$s. Por favor. Antes que isso vire o que eu lembro de você.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Not now. Come back when you've thought about it.
    >>  ............................................
    pt  ...Não. Agora não. Volte quando tiver pensado.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. I'd have taken that from anyone but you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu teria aguentado isso de qualquer um menos de você.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go, %1$s. Please. Before this becomes the thing I remember about you.
    >>  ............................................
    pt  Vá, %1$s. Por favor. Antes que isso vire o que eu lembro de você.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Not now. Come back when you've thought about it.
    >>  ............................................
    pt  ...Não. Agora não. Volte quando tiver pensado.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. I'd have taken that from anyone but you.
    >>  ............................................
    pt  ...Saia de perto de mim. Eu teria aguentado isso de qualquer um menos de você.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go, %1$s. Please. Before this becomes the thing I remember about you.
    >>  ............................................
    pt  Vá, %1$s. Por favor. Antes que isso vire o que eu lembro de você.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Not now. Come back when you've thought about it.
    >>  ............................................
    pt  ...Não. Agora não. Volte quando tiver pensado.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me, %1$s. Please. Just go.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor. Só vá.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving.dismiss/2
    en  Don't. I've nothing left to hold that with.
    >>  ............................................
    pt  Não. Não me sobrou nada pra segurar isso.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...I can't. Not today. Please go.
    >>  ............................................
    pt  ...Eu não consigo. Hoje não. Por favor, vá.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving.dismiss/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving.dismiss/2
    en  No. Not from you, not about this, not today.
    >>  ............................................
    pt  Não. De você não, sobre isso não, hoje não.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...We're finished talking.
    >>  ............................................
    pt  ...Terminamos de conversar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving.dismiss/1
    en  Get away from me.
    >>  ............................................
    pt  Saia de perto de mim.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving.dismiss/2
    en  No. Not from you, not about this, not today.
    >>  ............................................
    pt  Não. De você não, sobre isso não, hoje não.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...We're finished talking.
    >>  ............................................
    pt  ...Terminamos de conversar.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go.
    >>  ............................................
    pt  Vá.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. There's nothing here for you today.
    >>  ............................................
    pt  ...Saia de perto de mim. Não tem nada aqui pra você hoje.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go. I'll not hold it against you tomorrow, but go now.
    >>  ............................................
    pt  Vá. Amanhã eu não guardo mágoa, mas vá agora.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Leave me be a while.
    >>  ............................................
    pt  ...Não. Me deixe em paz um pouco.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go.
    >>  ............................................
    pt  Vá.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. There's nothing here for you today.
    >>  ............................................
    pt  ...Saia de perto de mim. Não tem nada aqui pra você hoje.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go. I'll not hold it against you tomorrow, but go now.
    >>  ............................................
    pt  Vá. Amanhã eu não guardo mágoa, mas vá agora.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Leave me be a while.
    >>  ............................................
    pt  ...Não. Me deixe em paz um pouco.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...No. There's no joke in here and I'd know.
    >>  ............................................
    pt  ...Não. Não tem piada aqui dentro e eu saberia.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving.dismiss/2
    en  Get away from me, %1$s. I've no lightness left for this.
    >>  ............................................
    pt  Saia de perto de mim, %1$s. Não me sobrou leveza pra isso.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...Go. I'll be someone else again tomorrow.
    >>  ............................................
    pt  ...Vá. Amanhã eu volto a ser outro.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...No. There's no joke in here and I'd know.
    >>  ............................................
    pt  ...Não. Não tem piada aqui dentro e eu saberia.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving.dismiss/2
    en  Get away from me, %1$s. I've no lightness left for this.
    >>  ............................................
    pt  Saia de perto de mim, %1$s. Não me sobrou leveza pra isso.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...Go. I'll be someone else again tomorrow.
    >>  ............................................
    pt  ...Vá. Amanhã eu volto a ser outro.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me. There's nothing here for you today.
    >>  ............................................
    pt  ...Saia de perto de mim. Não tem nada aqui pra você hoje.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go. I'll not hold it against you tomorrow, but go now.
    >>  ............................................
    pt  Vá. Amanhã eu não guardo mágoa, mas vá agora.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No. Leave me be a while.
    >>  ............................................
    pt  ...Não. Me deixe em paz um pouco.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me, %1$s. Please. Just go.
    >>  ............................................
    pt  ...Saia de perto de mim, %1$s. Por favor. Só vá.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving.dismiss/2
    en  Don't. I've nothing left to hold that with.
    >>  ............................................
    pt  Não. Não me sobrou nada pra segurar isso.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...I can't. Not today. Please go.
    >>  ............................................
    pt  ...Eu não consigo. Hoje não. Por favor, vá.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...Get away from me.
    >>  ............................................
    pt  ...Saia de perto de mim.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving.dismiss/2
    en  Go.
    >>  ............................................
    pt  Vá.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...No.
    >>  ............................................
    pt  ...Não.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...No. There's no joke in here and I'd know.
    >>  ............................................
    pt  ...Não. Não tem piada aqui dentro e eu saberia.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving.dismiss/2
    en  Get away from me, %1$s. I've no lightness left for this.
    >>  ............................................
    pt  Saia de perto de mim, %1$s. Não me sobrou leveza pra isso.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...Go. I'll be someone else again tomorrow.
    >>  ............................................
    pt  ...Vá. Amanhã eu volto a ser outro.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving.dismiss/1
    en  ...No. There's no joke in here and I'd know.
    >>  ............................................
    pt  ...Não. Não tem piada aqui dentro e eu saberia.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving.dismiss/2
    en  Get away from me, %1$s. I've no lightness left for this.
    >>  ............................................
    pt  Saia de perto de mim, %1$s. Não me sobrou leveza pra isso.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving.dismiss/3
    en  ...Go. I'll be someone else again tomorrow.
    >>  ............................................
    pt  ...Vá. Amanhã eu volto a ser outro.
    >>  ............................................
```

</details>


### Button `leave` — "I'm sorry. I'll go."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.grieving.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.respond.leave   [19 chars]
    en  I'm sorry. I'll go.
    >>  ............................................
    pt  Sinto muito. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.leave
WHO    VILLAGER — what the player reads after pressing "I'm sorry. I'll go."
       spoken on: conversations.topic.noticed.grieving.respond, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.left`: the villager accepts. Subject `noticed.grief`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.leave/1   [37 chars]
    en  Quite. Thank you for stopping at all.
    >>  ............................................
    pt  Exato. Obrigado por ter parado.
    >>  ............................................
  dialogue.conversations.noticed.grieving.leave/2   [34 chars]
    en  Go on. It's not a day for company.
    >>  ............................................
    pt  Pode ir. Não é dia para companhia.
    >>  ............................................
  dialogue.conversations.noticed.grieving.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.noticed.grieving.supported.followup`

**Reached from 1 route(s):** `conversations.topic.noticed.grieving.respond` / `validate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.grieving.validate` — e.g. "...Aye. I am allowed, aren't I. Nobody's said that."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.supported.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.grieving.supported.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.grieving.supported.followup   [36 chars]
    en  Nobody had said that to me all week.
    >>  ............................................
    pt  Ninguém tinha me dito isso a semana toda.
    >>  ............................................
```


### Button `ask_about_them` — "Tell me about them, if you want to."

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `noticed.grieving.validated` · offered only once the villager has actually said `loss:recent`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.supported.ask_about_them` — accepted phrasings: "tell me about them"; "what were they like"; "what sort of person were they"
  - the message must contain one of: `them`, `were`, `sort`
  - scored words: `them`(1.5), `were`(1.0), `sort`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.supported.followup.ask_about_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.supported.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.supported.followup.ask_about_them   [35 chars]
    en  Tell me about them, if you want to.
    >>  ............................................
    pt  Me fale dessa pessoa, se você quiser.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.supported.ask_about_them`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `noticed.grieving.supported.ask_about_them`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.supported.ask_about_them
WHO    VILLAGER — what the player reads after pressing "Tell me about them, if you want to."
       spoken on: conversations.topic.noticed.grieving.supported.followup, button `ask_about_them`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.supported.ask_about_them`: the villager reminisces. Subject `noticed.grief`, polarity `acute`, guarded, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.supported.ask_about_them/1   [79 chars]
    en  ...They were loud. Nobody says that at a funeral, but they were, and I miss it.
    >>  ............................................
    pt  ...Era barulhenta. Ninguém diz isso num funeral, mas era, e eu sinto falta.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.ask_about_them/2   [71 chars]
    en  They'd have hated the fuss. That's the bit that keeps getting me, %1$s.
    >>  ............................................
    pt  Ia detestar todo esse alarde. É essa parte que me pega, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.ask_about_them/3   [53 chars]
    en  Give me a moment. ...No. Not today. But ask me again.
    >>  ............................................
    pt  Me dá um momento. ...Não. Hoje não. Mas me pergunte de novo.
    >>  ............................................
```


### Button `sit_with` — "I'll sit here a while."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.grieving.validated`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.supported.sit_with` — accepted phrasings: "i will sit here a while"; "i will stay a while"; "let me sit with you"
  - the message must contain one of: `sit`, `while`, `stay`
  - scored words: `sit`(1.5), `while`(1.2), `stay`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.supported.followup.sit_with
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.supported.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.supported.followup.sit_with   [22 chars]
    en  I'll sit here a while.
    >>  ............................................
    pt  Vou ficar sentado aqui um pouco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.supported.sit_with`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — trust +2, warmth +3  _(recorded under topic `noticed.grieving.supported.sit_with`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.supported.sit_with
WHO    VILLAGER — what the player reads after pressing "I'll sit here a while."
       spoken on: conversations.topic.noticed.grieving.supported.followup, button `sit_with`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.supported.sit_with`: the villager accepts. Subject `noticed.grief`, polarity `acute`, guarded, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.supported.sit_with/1   [16 chars]
    en  ...Aye. Do that.
    >>  ............................................
    pt  ...É. Fique.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.sit_with/2   [64 chars]
    en  You don't have to say anything. That's the whole of what I want.
    >>  ............................................
    pt  Você não precisa dizer nada. É só isso que eu quero.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.sit_with/3   [44 chars]
    en  Sit, then. It's better than the house, %1$s.
    >>  ............................................
    pt  Sente, então. É melhor que a casa, %1$s.
    >>  ............................................
```


### Button `offer_practical` — "Is there anything that needs doing?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.grieving.validated` · offered only once the villager has actually said `state:grieving`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.grieving.supported.offer_practical` — accepted phrasings: "is there anything that needs doing"; "any chores i can do"; "anything that needs doing"
  - the message must contain one of: `needs`, `chores`, `doing`
  - scored words: `needs`(1.5), `chores`(1.5), `doing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.supported.followup.offer_practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.supported.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.supported.followup.offer_practical   [35 chars]
    en  Is there anything that needs doing?
    >>  ............................................
    pt  Tem alguma coisa que precisa ser feita?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.grieving.supported.offer_practical`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, trust +2  _(recorded under topic `noticed.grieving.supported.offer_practical`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.supported.offer_practical
WHO    VILLAGER — what the player reads after pressing "Is there anything that needs doing?"
       spoken on: conversations.topic.noticed.grieving.supported.followup, button `offer_practical`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.supported.offer_practical`: the villager request_helps. Subject `noticed.grief`, polarity `acute`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.supported.offer_practical/1   [56 chars]
    en  The animals. Nobody thinks of the animals. If you would.
    >>  ............................................
    pt  Os animais. Ninguém pensa nos animais. Se você puder.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.offer_practical/2   [70 chars]
    en  There's a fence I've not touched in a week. It'd be a mercy, honestly.
    >>  ............................................
    pt  Tem uma cerca que eu não toco faz uma semana. Seria uma misericórdia, sério.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.offer_practical/3   [47 chars]
    en  ...Aye. There is. I'll show you tomorrow, %1$s.
    >>  ............................................
    pt  ...É. Tem. Eu te mostro amanhã, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you be."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.grieving.validated` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.grieving.supported.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.grieving.supported.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.grieving.supported.followup.leave   [16 chars]
    en  I'll let you be.
    >>  ............................................
    pt  Vou te deixar em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving.supported.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you be."
       spoken on: conversations.topic.noticed.grieving.supported.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.supported.leave`: the villager accepts. Subject `noticed.grief`, polarity `acute`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving.supported.leave/1   [38 chars]
    en  So it is. Thank you for asking at all.
    >>  ............................................
    pt  É assim mesmo. Obrigado por ter perguntado.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.leave/2   [21 chars]
    en  Leave it there, %1$s.
    >>  ............................................
    pt  Deixe por aí, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving.supported.leave/3   [12 chars]
    en  Away you go.
    >>  ............................................
    pt  Pode seguir.
    >>  ............................................
```

---


## `conversations.topic.noticed.guarded.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.guarded.respond` / `respect`; `conversations.topic.noticed.guarded.respond` / `lighter`; `conversations.topic.noticed.guarded.respond` / `trade`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.guarded.lighter` — e.g. "Right. Has the rain reached your side of the village yet?"
- `conversations.noticed.guarded.respect` — e.g. "...Thank you. People generally push, and then wonder why I stay short with them."
- `conversations.noticed.guarded.trade` — e.g. "...That's an unfair tactic and it's working. Go on, then."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.guarded.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.guarded.followup   [37 chars]
    en  And that's where I'd rather leave it.
    >>  ............................................
    pt  E é aí que eu prefiro deixar.
    >>  ............................................
```


### Button `another_day` — "Another day, then."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `noticed.guarded.respect`, `noticed.guarded.lighter`, `noticed.guarded.trade`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.followup.another` — accepted phrasings: "another day then"; "some other time then"; "we can talk another day"
  - the message must contain one of: `another`
  - scored words: `another`(1.2), `day`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.followup.another_day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.followup.another_day   [18 chars]
    en  Another day, then.
    >>  ............................................
    pt  Outro dia, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.guarded.followup.another`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, respect +2  _(recorded under topic `noticed.guarded.followup.another`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.followup.another
WHO    VILLAGER — what the player reads after pressing "Another day, then."
       spoken on: conversations.topic.noticed.guarded.followup, button `another_day`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.followup.another`: the villager accepts. Subject `noticed.guarded`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.followup.another/1   [75 chars]
    en  Another day. You'd be surprised how many people don't leave that door open.
    >>  ............................................
    pt  Outro dia. Você se surpreenderia com quantos não deixam essa porta aberta.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.another/2   [77 chars]
    en  True enough. Ask me in a month and you may get a longer answer than this one.
    >>  ............................................
    pt  Bem verdade. Pergunte em um mês e talvez receba uma resposta mais longa que esta.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.another/3   [62 chars]
    en  That suits me. I warm slowly and I've never apologised for it.
    >>  ............................................
    pt  Isso me serve. Eu esquento devagar e nunca pedi desculpa por isso.
    >>  ............................................
```


### Button `fair_enough` — "Fair. I'd say the same to a stranger."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.guarded.respect`, `noticed.guarded.lighter`, `noticed.guarded.trade`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.followup.fair` — accepted phrasings: "i would say the same to a stranger"; "that is fair enough"; "i would not tell a stranger either"
  - the message must contain one of: `stranger`
  - scored words: `stranger`(1.5), `fair`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.followup.fair_enough
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.followup.fair_enough   [37 chars]
    en  Fair. I'd say the same to a stranger.
    >>  ............................................
    pt  Justo. Eu diria o mesmo a um estranho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.guarded.followup.fair`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `noticed.guarded.followup.fair`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.followup.fair
WHO    VILLAGER — what the player reads after pressing "Fair. I'd say the same to a stranger."
       spoken on: conversations.topic.noticed.guarded.followup, button `fair_enough`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.followup.fair`: the villager accepts. Subject `noticed.guarded`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.followup.fair/1   [64 chars]
    en  Then we understand each other, which is a fair start on its own.
    >>  ............................................
    pt  Então nos entendemos, o que já é um começo justo.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.fair/2   [69 chars]
    en  Good. The ones who take offence at it are the ones I was right about.
    >>  ............................................
    pt  Bom. Os que se ofendem com isso são justamente sobre quem eu estava certo.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.fair/3   [69 chars]
    en  That's the first sensible thing anyone has said to me about it, %1$s.
    >>  ............................................
    pt  É a primeira coisa sensata que alguém me disse sobre isso, %1$s.
    >>  ............................................
```


### Button `leave` — "Good day to you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.guarded.respect`, `noticed.guarded.lighter`, `noticed.guarded.trade` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.followup.leave   [16 chars]
    en  Good day to you.
    >>  ............................................
    pt  Bom dia pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.followup.leave
WHO    VILLAGER — what the player reads after pressing "Good day to you."
       spoken on: conversations.topic.noticed.guarded.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.followup.leave`: the villager accepts. Subject `noticed.guarded`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.followup.leave/1   [11 chars]
    en  And to you.
    >>  ............................................
    pt  Igualmente.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.leave/2   [9 chars]
    en  Good day.
    >>  ............................................
    pt  Bom dia.
    >>  ............................................
  dialogue.conversations.noticed.guarded.followup.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.noticed.guarded.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.guarded` — e.g. "Well enough, thank you. And yourself?"


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.guarded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.guarded.respond   [56 chars]
    en  That's as much of an answer as that question gets today.
    >>  ............................................
    pt  É toda a resposta que essa pergunta recebe hoje.
    >>  ............................................
```


### Button `respect` — "I won't push. I only wondered."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.guarded.open` · offered only once the villager has actually said `noticed:guarded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.respect` — accepted phrasings: "i will not push"; "i only wondered"; "i did not mean to pry"
  - the message must contain one of: `push`, `wondered`
  - scored words: `push`(1.5), `wondered`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.respond.respect   [30 chars]
    en  I won't push. I only wondered.
    >>  ............................................
    pt  Não vou insistir. Só fiquei pensando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.guarded.respect`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +1  _(recorded under topic `noticed.guarded.respect`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.guarded.followup`
- …where the player's next choices will be: "Another day, then." | "Fair. I'd say the same to a stranger." | "Good day to you."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.respect
WHO    VILLAGER — what the player reads after pressing "I won't push. I only wondered."
       spoken on: conversations.topic.noticed.guarded.respond, button `respect`
       leaves the player on: conversations.topic.noticed.guarded.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.respect`: the villager accepts. Subject `noticed.guarded`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.respect/1   [80 chars]
    en  ...Thank you. People generally push, and then wonder why I stay short with them.
    >>  ............................................
    pt  ...Obrigado. As pessoas geralmente insistem, e depois se perguntam por que sou seco.
    >>  ............................................
  dialogue.conversations.noticed.guarded.respect/2   [68 chars]
    en  That's noted, and it counts for more than the answer you were after.
    >>  ............................................
    pt  Isso fica anotado, e vale mais do que a resposta que você queria.
    >>  ............................................
  dialogue.conversations.noticed.guarded.respect/3   [71 chars]
    en  Then I'll tell you this much: it's been a long month and it isn't over.
    >>  ............................................
    pt  Então eu te digo o seguinte: foi um mês longo e ainda não acabou.
    >>  ............................................
```


### Button `lighter` — "Ask me something easier, then."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.guarded.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.lighter` — accepted phrasings: "ask me something easier"; "let us talk about something lighter"; "give me an easier question"
  - the message must contain one of: `easier`, `lighter`
  - scored words: `easier`(1.5), `lighter`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond.lighter
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.respond.lighter   [30 chars]
    en  Ask me something easier, then.
    >>  ............................................
    pt  Então me pergunte algo mais fácil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.guarded.followup`
- …where the player's next choices will be: "Another day, then." | "Fair. I'd say the same to a stranger." | "Good day to you."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.lighter
WHO    VILLAGER — what the player reads after pressing "Ask me something easier, then."
       spoken on: conversations.topic.noticed.guarded.respond, button `lighter`
       leaves the player on: conversations.topic.noticed.guarded.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.lighter`: the villager invites. Subject `noticed.guarded`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.lighter/1   [57 chars]
    en  Right. Has the rain reached your side of the village yet?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo?
    >>  ............................................
  dialogue.conversations.noticed.guarded.lighter/2   [70 chars]
    en  Very well. What's your opinion of the new gate? Everyone else has one.
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo? Todo mundo tem uma opinião.
    >>  ............................................
  dialogue.conversations.noticed.guarded.lighter/3   [79 chars]
    en  Easier. Where were you before here? That's a question I can hear the answer to.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? É uma pergunta cuja resposta eu aguento.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. The rain — has it reached your side yet? That's a safe one, I think.
    >>  ............................................
    pt  Certo. A chuva — já chegou no seu lado? Essa é segura, eu acho.
    >>  ............................................
  anxious.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I can manage the gate.
    >>  ............................................
    pt  Muito bem. O portão novo. Do portão eu dou conta.
    >>  ............................................
  anxious.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? You needn't answer if that one's heavy too.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Não precisa responder se essa também pesar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side yet? It comes up the valley in the same order every year.
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado? Ela sobe o vale na mesma ordem todo ano.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I've seen four of them go up on that spot.
    >>  ............................................
    pt  Muito bem. O portão novo. Já vi quatro subirem naquele lugar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? Everyone was somewhere before here.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Todo mundo esteve em algum lugar antes daqui.
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo?
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What's your opinion of the new gate?
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo?
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here?
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui?
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo?
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What's your opinion of the new gate?
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo?
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here?
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui?
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet, %1$s?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo, %1$s?
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What do you make of the new gate? I want an honest answer, mind.
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo? Quero uma resposta honesta, veja bem.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier, then. Where were you before here? I've wondered and never asked.
    >>  ............................................
    pt  Mais fácil, então. Onde você estava antes daqui? Eu me perguntei e nunca perguntei.
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet, %1$s?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo, %1$s?
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What do you make of the new gate? I want an honest answer, mind.
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo? Quero uma resposta honesta, veja bem.
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier, then. Where were you before here? I've wondered and never asked.
    >>  ............................................
    pt  Mais fácil, então. Onde você estava antes daqui? Eu me perguntei e nunca perguntei.
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet, %1$s?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo, %1$s?
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What do you make of the new gate? I want an honest answer, mind.
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo? Quero uma resposta honesta, veja bem.
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier, then. Where were you before here? I've wondered and never asked.
    >>  ............................................
    pt  Mais fácil, então. Onde você estava antes daqui? Eu me perguntei e nunca perguntei.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. The rain — has it reached your side yet? That's a safe one, I think.
    >>  ............................................
    pt  Certo. A chuva — já chegou no seu lado? Essa é segura, eu acho.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I can manage the gate.
    >>  ............................................
    pt  Muito bem. O portão novo. Do portão eu dou conta.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? You needn't answer if that one's heavy too.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Não precisa responder se essa também pesar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo?
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What's your opinion of the new gate?
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo?
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here?
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui?
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side of the village yet?
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado do vilarejo?
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. What's your opinion of the new gate?
    >>  ............................................
    pt  Muito bem. O que você acha do portão novo?
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here?
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui?
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.lighter/1
    en  Has the rain reached your side yet?
    >>  ............................................
    pt  A chuva já chegou no seu lado?
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.lighter/2
    en  The new gate. Thoughts?
    >>  ............................................
    pt  O portão novo. Opinião?
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.lighter/3
    en  Where were you before here?
    >>  ............................................
    pt  Onde você estava antes daqui?
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side yet? It comes up the valley in the same order every year.
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado? Ela sobe o vale na mesma ordem todo ano.
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I've seen four of them go up on that spot.
    >>  ............................................
    pt  Muito bem. O portão novo. Já vi quatro subirem naquele lugar.
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? Everyone was somewhere before here.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Todo mundo esteve em algum lugar antes daqui.
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.lighter/1
    en  Has the rain reached your side yet?
    >>  ............................................
    pt  A chuva já chegou no seu lado?
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.lighter/2
    en  The new gate. Thoughts?
    >>  ............................................
    pt  O portão novo. Opinião?
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.lighter/3
    en  Where were you before here?
    >>  ............................................
    pt  Onde você estava antes daqui?
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side yet? It comes up the valley in the same order every year.
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado? Ela sobe o vale na mesma ordem todo ano.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I've seen four of them go up on that spot.
    >>  ............................................
    pt  Muito bem. O portão novo. Já vi quatro subirem naquele lugar.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? Everyone was somewhere before here.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Todo mundo esteve em algum lugar antes daqui.
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.lighter/1
    en  Right! Has the rain reached your end of the village, or is it hoarding it down here?
    >>  ............................................
    pt  Certo! A chuva já chegou no seu lado, ou está acumulando aqui embaixo?
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. Everyone has an opinion and I want yours immediately.
    >>  ............................................
    pt  Muito bem. O portão novo. Todos têm opinião e eu quero a sua agora.
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? And make it a good story, I've had a dull week.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? E que seja boa, tive uma semana chata.
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.lighter/1
    en  Right! Has the rain reached your end of the village, or is it hoarding it down here?
    >>  ............................................
    pt  Certo! A chuva já chegou no seu lado, ou está acumulando aqui embaixo?
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. Everyone has an opinion and I want yours immediately.
    >>  ............................................
    pt  Muito bem. O portão novo. Todos têm opinião e eu quero a sua agora.
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? And make it a good story, I've had a dull week.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? E que seja boa, tive uma semana chata.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. Has the rain reached your side yet? It comes up the valley in the same order every year.
    >>  ............................................
    pt  Certo. A chuva já chegou no seu lado? Ela sobe o vale na mesma ordem todo ano.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I've seen four of them go up on that spot.
    >>  ............................................
    pt  Muito bem. O portão novo. Já vi quatro subirem naquele lugar.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? Everyone was somewhere before here.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Todo mundo esteve em algum lugar antes daqui.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.lighter/1
    en  Right. The rain — has it reached your side yet? That's a safe one, I think.
    >>  ............................................
    pt  Certo. A chuva — já chegou no seu lado? Essa é segura, eu acho.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. I can manage the gate.
    >>  ............................................
    pt  Muito bem. O portão novo. Do portão eu dou conta.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? You needn't answer if that one's heavy too.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? Não precisa responder se essa também pesar.
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.lighter/1
    en  Has the rain reached your side yet?
    >>  ............................................
    pt  A chuva já chegou no seu lado?
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.lighter/2
    en  The new gate. Thoughts?
    >>  ............................................
    pt  O portão novo. Opinião?
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.lighter/3
    en  Where were you before here?
    >>  ............................................
    pt  Onde você estava antes daqui?
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.lighter/1
    en  Right! Has the rain reached your end of the village, or is it hoarding it down here?
    >>  ............................................
    pt  Certo! A chuva já chegou no seu lado, ou está acumulando aqui embaixo?
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. Everyone has an opinion and I want yours immediately.
    >>  ............................................
    pt  Muito bem. O portão novo. Todos têm opinião e eu quero a sua agora.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? And make it a good story, I've had a dull week.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? E que seja boa, tive uma semana chata.
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.lighter/1
    en  Right! Has the rain reached your end of the village, or is it hoarding it down here?
    >>  ............................................
    pt  Certo! A chuva já chegou no seu lado, ou está acumulando aqui embaixo?
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.lighter/2
    en  Very well. The new gate. Everyone has an opinion and I want yours immediately.
    >>  ............................................
    pt  Muito bem. O portão novo. Todos têm opinião e eu quero a sua agora.
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.lighter/3
    en  Easier. Where were you before here? And make it a good story, I've had a dull week.
    >>  ............................................
    pt  Mais fácil. Onde você estava antes daqui? E que seja boa, tive uma semana chata.
    >>  ............................................
```

</details>


### Button `trade` — "I'll go first, if that helps."

*stance family `self_disclosure` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.guarded.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.trade` — accepted phrasings: "i will go first"; "i will tell you mine first"; "shall we trade answers"
  - the message must contain one of: `first`, `trade`
  - scored words: `first`(1.2), `trade`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond.trade
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.respond.trade   [29 chars]
    en  I'll go first, if that helps.
    >>  ............................................
    pt  Eu começo, se ajudar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `noticed.guarded.trade`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.guarded.followup`
- …where the player's next choices will be: "Another day, then." | "Fair. I'd say the same to a stranger." | "Good day to you."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.trade
WHO    VILLAGER — what the player reads after pressing "I'll go first, if that helps."
       spoken on: conversations.topic.noticed.guarded.respond, button `trade`
       leaves the player on: conversations.topic.noticed.guarded.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.trade`: the villager qualifys. Subject `noticed.guarded`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.trade/1   [57 chars]
    en  ...That's an unfair tactic and it's working. Go on, then.
    >>  ............................................
    pt  ...É uma tática injusta e está funcionando. Então vá.
    >>  ............................................
  dialogue.conversations.noticed.guarded.trade/2   [74 chars]
    en  I've not been offered that before. Alright — a sentence each, and no more.
    >>  ............................................
    pt  Ninguém nunca me ofereceu isso. Está bem — uma frase cada, e nada mais.
    >>  ............................................
  dialogue.conversations.noticed.guarded.trade/3   [65 chars]
    en  It helps. I'll match whatever you give me and not a word past it.
    >>  ............................................
    pt  Ajuda. Eu igualo o que você der e nem uma palavra além.
    >>  ............................................
```


### Button `press` — "Come on. What's really going on with you?"

*stance family `boundary_push` · tone `blunt` · outcome `boundary_closed` · answers the beat(s) `noticed.guarded.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.guarded.pressed` — accepted phrasings: "what is really going on with you"; "come on tell me the truth"; "you can tell me what is wrong"
  - scored words: `really`(0.8), `going`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.respond.press   [41 chars]
    en  Come on. What's really going on with you?
    >>  ............................................
    pt  Vamos lá. O que está acontecendo de verdade com você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.guarded.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -2, warmth -2  _(recorded under topic `noticed.guarded.pressed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.pressed
WHO    VILLAGER — what the player reads after pressing "Come on. What's really going on with you?"
       spoken on: conversations.topic.noticed.guarded.respond, button `press`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.pressed`: the villager set_boundarys. Subject `noticed.guarded`, polarity `negative`, closes subject, outcome `boundary_closed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.pressed/1   [73 chars]
    en  Nothing that's yours yet. Ask again in a year and you may have earned it.
    >>  ............................................
    pt  Nada que seja seu ainda. Pergunte em um ano e talvez você tenha merecido.
    >>  ............................................
  dialogue.conversations.noticed.guarded.pressed/2   [74 chars]
    en  That's the second time today. I'll give the same answer and enjoy it less.
    >>  ............................................
    pt  É a segunda vez hoje. Vou dar a mesma resposta e gostar menos dela.
    >>  ............................................
  dialogue.conversations.noticed.guarded.pressed/3   [54 chars]
    en  No. I gave you the polite version and you've spent it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada e você a gastou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Please don't. I'd have to lie, and I'm bad at it.
    >>  ............................................
    pt  Nada que seja seu ainda. Por favor, não. Eu teria que mentir, e sou ruim nisso.
    >>  ............................................
  anxious.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice now. Asking twice doesn't open a door; it just makes me hold it.
    >>  ............................................
    pt  Já são duas. Perguntar duas vezes não abre a porta; só me faz segurá-la.
    >>  ............................................
  anxious.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you what I could and I've nothing left behind it today.
    >>  ............................................
    pt  Não. Eu te dei o que pude e não sobrou nada atrás disso hoje.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Things become yours here slowly, and that's how they last.
    >>  ............................................
    pt  Nada que seja seu ainda. As coisas viram suas aqui devagar, e é por isso que duram.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I've said no to better askers than you and I'll say it again.
    >>  ............................................
    pt  Já são duas. Já disse não a gente melhor em perguntar e vou dizer de novo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.guarded.pressed/3
    en  No. The polite version is the only one I hand out to people I've known a season.
    >>  ............................................
    pt  Não. A versão educada é a única que dou a quem conheço há uma estação.
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Ask again in a year.
    >>  ............................................
    pt  Nada que seja seu ainda. Pergunte em um ano.
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.pressed/2
    en  That's the second time today. Same answer, less patience.
    >>  ............................................
    pt  É a segunda vez hoje. Mesma resposta, menos paciência.
    >>  ............................................
  confident.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version and you've spent it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada e você a gastou.
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Ask again in a year.
    >>  ............................................
    pt  Nada que seja seu ainda. Pergunte em um ano.
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.pressed/2
    en  That's the second time today. Same answer, less patience.
    >>  ............................................
    pt  É a segunda vez hoje. Mesma resposta, menos paciência.
    >>  ............................................
  crabby.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version and you've spent it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada e você a gastou.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet, %1$s. It might be, in time. Not today.
    >>  ............................................
    pt  Nada que seja seu ainda, %1$s. Pode vir a ser, com o tempo. Hoje não.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I'd like to keep liking you, so let's stop here.
    >>  ............................................
    pt  Já são duas. Eu gostaria de continuar gostando de você, então vamos parar aqui.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version because I wanted to. Don't make me regret it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada porque quis. Não me faça me arrepender.
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet, %1$s. It might be, in time. Not today.
    >>  ............................................
    pt  Nada que seja seu ainda, %1$s. Pode vir a ser, com o tempo. Hoje não.
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I'd like to keep liking you, so let's stop here.
    >>  ............................................
    pt  Já são duas. Eu gostaria de continuar gostando de você, então vamos parar aqui.
    >>  ............................................
  flirty.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version because I wanted to. Don't make me regret it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada porque quis. Não me faça me arrepender.
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet, %1$s. It might be, in time. Not today.
    >>  ............................................
    pt  Nada que seja seu ainda, %1$s. Pode vir a ser, com o tempo. Hoje não.
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I'd like to keep liking you, so let's stop here.
    >>  ............................................
    pt  Já são duas. Eu gostaria de continuar gostando de você, então vamos parar aqui.
    >>  ............................................
  friendly.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version because I wanted to. Don't make me regret it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada porque quis. Não me faça me arrepender.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Please don't. I'd have to lie, and I'm bad at it.
    >>  ............................................
    pt  Nada que seja seu ainda. Por favor, não. Eu teria que mentir, e sou ruim nisso.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice now. Asking twice doesn't open a door; it just makes me hold it.
    >>  ............................................
    pt  Já são duas. Perguntar duas vezes não abre a porta; só me faz segurá-la.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you what I could and I've nothing left behind it today.
    >>  ............................................
    pt  Não. Eu te dei o que pude e não sobrou nada atrás disso hoje.
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Ask again in a year.
    >>  ............................................
    pt  Nada que seja seu ainda. Pergunte em um ano.
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.pressed/2
    en  That's the second time today. Same answer, less patience.
    >>  ............................................
    pt  É a segunda vez hoje. Mesma resposta, menos paciência.
    >>  ............................................
  greedy.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version and you've spent it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada e você a gastou.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Ask again in a year.
    >>  ............................................
    pt  Nada que seja seu ainda. Pergunte em um ano.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.pressed/2
    en  That's the second time today. Same answer, less patience.
    >>  ............................................
    pt  É a segunda vez hoje. Mesma resposta, menos paciência.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you the polite version and you've spent it.
    >>  ............................................
    pt  Não. Eu te dei a versão educada e você a gastou.
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet.
    >>  ............................................
    pt  Nada que seja seu ainda.
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice.
    >>  ............................................
    pt  Já são duas.
    >>  ............................................
  introverted.dialogue.conversations.noticed.guarded.pressed/3
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Things become yours here slowly, and that's how they last.
    >>  ............................................
    pt  Nada que seja seu ainda. As coisas viram suas aqui devagar, e é por isso que duram.
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I've said no to better askers than you and I'll say it again.
    >>  ............................................
    pt  Já são duas. Já disse não a gente melhor em perguntar e vou dizer de novo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.guarded.pressed/3
    en  No. The polite version is the only one I hand out to people I've known a season.
    >>  ............................................
    pt  Não. A versão educada é a única que dou a quem conheço há uma estação.
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet.
    >>  ............................................
    pt  Nada que seja seu ainda.
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice.
    >>  ............................................
    pt  Já são duas.
    >>  ............................................
  odd.dialogue.conversations.noticed.guarded.pressed/3
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Things become yours here slowly, and that's how they last.
    >>  ............................................
    pt  Nada que seja seu ainda. As coisas viram suas aqui devagar, e é por isso que duram.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I've said no to better askers than you and I'll say it again.
    >>  ............................................
    pt  Já são duas. Já disse não a gente melhor em perguntar e vou dizer de novo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.guarded.pressed/3
    en  No. The polite version is the only one I hand out to people I've known a season.
    >>  ............................................
    pt  Não. A versão educada é a única que dou a quem conheço há uma estação.
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet! Try again in a year and I'll be delightful about it.
    >>  ............................................
    pt  Nada que seja seu ainda! Tente em um ano e eu serei encantador sobre isso.
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.pressed/2
    en  Second time today. Same answer, and I'm enjoying it considerably less.
    >>  ............................................
    pt  Segunda vez hoje. Mesma resposta, e estou gostando consideravelmente menos.
    >>  ............................................
  peppy.dialogue.conversations.noticed.guarded.pressed/3
    en  No. You had the polite version, you spent it, and there isn't a third.
    >>  ............................................
    pt  Não. Você teve a versão educada, gastou, e não existe uma terceira.
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet! Try again in a year and I'll be delightful about it.
    >>  ............................................
    pt  Nada que seja seu ainda! Tente em um ano e eu serei encantador sobre isso.
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.pressed/2
    en  Second time today. Same answer, and I'm enjoying it considerably less.
    >>  ............................................
    pt  Segunda vez hoje. Mesma resposta, e estou gostando consideravelmente menos.
    >>  ............................................
  playful.dialogue.conversations.noticed.guarded.pressed/3
    en  No. You had the polite version, you spent it, and there isn't a third.
    >>  ............................................
    pt  Não. Você teve a versão educada, gastou, e não existe uma terceira.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Things become yours here slowly, and that's how they last.
    >>  ............................................
    pt  Nada que seja seu ainda. As coisas viram suas aqui devagar, e é por isso que duram.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice. I've said no to better askers than you and I'll say it again.
    >>  ............................................
    pt  Já são duas. Já disse não a gente melhor em perguntar e vou dizer de novo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.guarded.pressed/3
    en  No. The polite version is the only one I hand out to people I've known a season.
    >>  ............................................
    pt  Não. A versão educada é a única que dou a quem conheço há uma estação.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet. Please don't. I'd have to lie, and I'm bad at it.
    >>  ............................................
    pt  Nada que seja seu ainda. Por favor, não. Eu teria que mentir, e sou ruim nisso.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice now. Asking twice doesn't open a door; it just makes me hold it.
    >>  ............................................
    pt  Já são duas. Perguntar duas vezes não abre a porta; só me faz segurá-la.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.guarded.pressed/3
    en  No. I gave you what I could and I've nothing left behind it today.
    >>  ............................................
    pt  Não. Eu te dei o que pude e não sobrou nada atrás disso hoje.
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet.
    >>  ............................................
    pt  Nada que seja seu ainda.
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.pressed/2
    en  That's twice.
    >>  ............................................
    pt  Já são duas.
    >>  ............................................
  shy.dialogue.conversations.noticed.guarded.pressed/3
    en  No.
    >>  ............................................
    pt  Não.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet! Try again in a year and I'll be delightful about it.
    >>  ............................................
    pt  Nada que seja seu ainda! Tente em um ano e eu serei encantador sobre isso.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.pressed/2
    en  Second time today. Same answer, and I'm enjoying it considerably less.
    >>  ............................................
    pt  Segunda vez hoje. Mesma resposta, e estou gostando consideravelmente menos.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.guarded.pressed/3
    en  No. You had the polite version, you spent it, and there isn't a third.
    >>  ............................................
    pt  Não. Você teve a versão educada, gastou, e não existe uma terceira.
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.pressed/1
    en  Nothing that's yours yet! Try again in a year and I'll be delightful about it.
    >>  ............................................
    pt  Nada que seja seu ainda! Tente em um ano e eu serei encantador sobre isso.
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.pressed/2
    en  Second time today. Same answer, and I'm enjoying it considerably less.
    >>  ............................................
    pt  Segunda vez hoje. Mesma resposta, e estou gostando consideravelmente menos.
    >>  ............................................
  witty.dialogue.conversations.noticed.guarded.pressed/3
    en  No. You had the polite version, you spent it, and there isn't a third.
    >>  ............................................
    pt  Não. Você teve a versão educada, gastou, e não existe uma terceira.
    >>  ............................................
```

</details>


### Button `back` — "Of course. Good day."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.guarded.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.guarded.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.guarded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.guarded.respond.back   [20 chars]
    en  Of course. Good day.
    >>  ............................................
    pt  Claro. Bom dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded.back
WHO    VILLAGER — what the player reads after pressing "Of course. Good day."
       spoken on: conversations.topic.noticed.guarded.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.back`: the villager accepts. Subject `noticed.guarded`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded.back/1   [11 chars]
    en  And to you.
    >>  ............................................
    pt  Igualmente.
    >>  ............................................
  dialogue.conversations.noticed.guarded.back/2   [15 chars]
    en  Good day, %1$s.
    >>  ............................................
    pt  Bom dia, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.guarded.back/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.noticed.hurt.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.hurt.respond` / `what_happened`; `conversations.topic.noticed.hurt.respond` / `tend_it`; `conversations.topic.noticed.hurt.respond` / `rest`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.hurt.rest` — e.g. "The work doesn't rest, so neither do I. That's how it's always gone here."
- `conversations.noticed.hurt.tend_it` — e.g. "I've bound it myself, badly, and I'll not hear a word about that."
- `conversations.noticed.hurt.what_happened` — e.g. "Something came out of the dark faster than I did. That's the whole story."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.hurt.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.hurt.followup   [23 chars]
    en  That's the state of me.
    >>  ............................................
    pt  É esse o meu estado.
    >>  ............................................
```


### Button `check_tomorrow` — "I'll come and check on you tomorrow."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.hurt.what_happened`, `noticed.hurt.tend_it`, `noticed.hurt.rest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.followup.check` — accepted phrasings: "i will come and check on you tomorrow"; "i will look in on you tomorrow"; "i will come back and see how you are"
  - the message must contain one of: `tomorrow`, `check`
  - scored words: `tomorrow`(1.5), `check`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.followup.check_tomorrow
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.followup.check_tomorrow   [36 chars]
    en  I'll come and check on you tomorrow.
    >>  ............................................
    pt  Eu venho ver como você está amanhã.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.hurt.followup.check`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +3  _(recorded under topic `noticed.hurt.followup.check`)_
- Does: arc `noticed` — advance
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.followup.check
WHO    VILLAGER — what the player reads after pressing "I'll come and check on you tomorrow."
       spoken on: conversations.topic.noticed.hurt.followup, button `check_tomorrow`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.followup.check`: the villager accepts. Subject `noticed.injury`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.followup.check/1   [73 chars]
    en  Then I'll still be here, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e menos simpático. Venha assim mesmo.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.check/2   [64 chars]
    en  You'd best, because I'll not send for anyone myself. I never do.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.check/3   [80 chars]
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and worse company, and I'd still rather you came.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e pior companhia, e ainda prefiro que você venha.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. I've never learned how to ask.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca aprendi a pedir.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Sympathy is easy. Coming back is the hard part.
    >>  ............................................
    pt  É o tipo útil de gentileza. Pena é fácil. Voltar é a parte difícil.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. At my age these take a fortnight and they take it slowly.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Na minha idade isso leva quinze dias, e devagar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. Forty years of not sending for anyone.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Quarenta anos sem chamar ninguém.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. I've had a lifetime of the other sort.
    >>  ............................................
    pt  É o tipo útil de gentileza. Já tive uma vida inteira do outro tipo.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e menos simpático. Venha assim mesmo.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e menos simpático. Venha assim mesmo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, %1$s, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, %1$s, e menos simpático. Venha assim mesmo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone myself, and you're the only one who'd notice.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém, e você é o único que notaria.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; you came back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; você voltou.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, %1$s, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, %1$s, e menos simpático. Venha assim mesmo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone myself, and you're the only one who'd notice.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém, e você é o único que notaria.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; you came back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; você voltou.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, %1$s, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, %1$s, e menos simpático. Venha assim mesmo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone myself, and you're the only one who'd notice.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém, e você é o único que notaria.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; you came back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; você voltou.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and worse company, and I'd still rather you came.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e pior companhia, e ainda prefiro que você venha.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. I've never learned how to ask.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca aprendi a pedir.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Sympathy is easy. Coming back is the hard part.
    >>  ............................................
    pt  É o tipo útil de gentileza. Pena é fácil. Voltar é a parte difícil.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e menos simpático. Venha assim mesmo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and I'll be less pleasant about it. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e menos simpático. Venha assim mesmo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Venha assim mesmo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.followup.check/3
    en  The useful sort of kindness.
    >>  ............................................
    pt  O tipo útil de gentileza.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. At my age these take a fortnight and they take it slowly.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Na minha idade isso leva quinze dias, e devagar.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. Forty years of not sending for anyone.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Quarenta anos sem chamar ninguém.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. I've had a lifetime of the other sort.
    >>  ............................................
    pt  É o tipo útil de gentileza. Já tive uma vida inteira do outro tipo.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Venha assim mesmo.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.followup.check/3
    en  The useful sort of kindness.
    >>  ............................................
    pt  O tipo útil de gentileza.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. At my age these take a fortnight and they take it slowly.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Na minha idade isso leva quinze dias, e devagar.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. Forty years of not sending for anyone.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Quarenta anos sem chamar ninguém.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. I've had a lifetime of the other sort.
    >>  ............................................
    pt  É o tipo útil de gentileza. Já tive uma vida inteira do outro tipo.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and considerably less pleasant about it. Come anyway!
    >>  ............................................
    pt  Então eu ainda estarei aqui, e bem menos simpático. Venha assim mesmo!
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do, on principle.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo, por princípio.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and considerably less pleasant about it. Come anyway!
    >>  ............................................
    pt  Então eu ainda estarei aqui, e bem menos simpático. Venha assim mesmo!
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do, on principle.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo, por princípio.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. At my age these take a fortnight and they take it slowly.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Na minha idade isso leva quinze dias, e devagar.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. Forty years of not sending for anyone.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Quarenta anos sem chamar ninguém.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. I've had a lifetime of the other sort.
    >>  ............................................
    pt  É o tipo útil de gentileza. Já tive uma vida inteira do outro tipo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and worse company, and I'd still rather you came.
    >>  ............................................
    pt  Então eu ainda estarei aqui, e pior companhia, e ainda prefiro que você venha.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone. I've never learned how to ask.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca aprendi a pedir.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Sympathy is easy. Coming back is the hard part.
    >>  ............................................
    pt  É o tipo útil de gentileza. Pena é fácil. Voltar é a parte difícil.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here. Come anyway.
    >>  ............................................
    pt  Então eu ainda estarei aqui. Venha assim mesmo.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best. I'll not send for anyone.
    >>  ............................................
    pt  É bom que venha. Eu não chamo ninguém.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.followup.check/3
    en  The useful sort of kindness.
    >>  ............................................
    pt  O tipo útil de gentileza.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and considerably less pleasant about it. Come anyway!
    >>  ............................................
    pt  Então eu ainda estarei aqui, e bem menos simpático. Venha assim mesmo!
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do, on principle.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo, por princípio.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.followup.check/1
    en  Then I'll still be here, and considerably less pleasant about it. Come anyway!
    >>  ............................................
    pt  Então eu ainda estarei aqui, e bem menos simpático. Venha assim mesmo!
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.followup.check/2
    en  You'd best, because I'll not send for anyone myself. I never do, on principle.
    >>  ............................................
    pt  É bom que venha, porque eu não chamo ninguém. Nunca chamo, por princípio.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.followup.check/3
    en  That's the useful sort of kindness. Everyone offers sympathy; nobody comes back.
    >>  ............................................
    pt  É o tipo útil de gentileza. Todos oferecem pena; ninguém volta.
    >>  ............................................
```

</details>


### Button `sit_with_you` — "I can sit with you a while."

*stance family `empathy` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.hurt.what_happened`, `noticed.hurt.tend_it`, `noticed.hurt.rest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.followup.sit` — accepted phrasings: "i can sit with you a while"; "i will stay a while"; "shall i keep you company"
  - the message must contain one of: `sit`
  - scored words: `sit`(1.5), `while`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.followup.sit_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.followup.sit_with_you   [27 chars]
    en  I can sit with you a while.
    >>  ............................................
    pt  Posso ficar um pouco com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.hurt.followup.sit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `noticed.hurt.followup.sit`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.followup.sit
WHO    VILLAGER — what the player reads after pressing "I can sit with you a while."
       spoken on: conversations.topic.noticed.hurt.followup, button `sit_with_you`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.followup.sit`: the villager accepts. Subject `noticed.injury`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.followup.sit/1   [58 chars]
    en  ...Go on, then. Don't talk at me and we'll get along fine.
    >>  ............................................
    pt  ...Então fique. Não fale comigo e vamos nos dar bem.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.sit/2   [64 chars]
    en  You don't have to. But sit, and I'll stop pretending to be busy.
    >>  ............................................
    pt  Você não precisa. Mas sente, e eu paro de fingir que estou ocupado.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.sit/3   [67 chars]
    en  That's the first offer today that didn't come with advice attached.
    >>  ............................................
    pt  É a primeira oferta hoje que não veio com conselho junto.
    >>  ............................................
```


### Button `leave` — "I'll let you rest."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.hurt.what_happened`, `noticed.hurt.tend_it`, `noticed.hurt.rest` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.followup.leave   [18 chars]
    en  I'll let you rest.
    >>  ............................................
    pt  Vou deixar você descansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you rest."
       spoken on: conversations.topic.noticed.hurt.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.followup.leave`: the villager accepts. Subject `noticed.injury`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.followup.leave/1   [21 chars]
    en  It is. Mind the door.
    >>  ............................................
    pt  É sim. Cuidado com a porta.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.leave/2   [17 chars]
    en  Do that. Quietly.
    >>  ............................................
    pt  Faça isso. Em silêncio.
    >>  ............................................
  dialogue.conversations.noticed.hurt.followup.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.hurt.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.hurt` — e.g. "Been better. I took a bad one two nights back and it hasn't finished with me yet."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.hurt.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.hurt.respond   [48 chars]
    en  You asked, so you're getting the honest version.
    >>  ............................................
    pt  Você perguntou, então vai receber a versão honesta.
    >>  ............................................
```


### Button `what_happened` — "What happened to you?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `noticed.hurt.open` · offered only once the villager has actually said `noticed:hurt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.what_happened` — accepted phrasings: "what happened to you"; "how did you get hurt"; "what did that to you"
  - the message must contain one of: `happened`
  - scored words: `happened`(1.5), `hurt`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond.what_happened
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.respond.what_happened   [21 chars]
    en  What happened to you?
    >>  ............................................
    pt  O que aconteceu com você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.hurt.followup`
- …where the player's next choices will be: "I'll come and check on you tomorrow." | "I can sit with you a while." | "I'll let you rest."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.what_happened
WHO    VILLAGER — what the player reads after pressing "What happened to you?"
       spoken on: conversations.topic.noticed.hurt.respond, button `what_happened`
       leaves the player on: conversations.topic.noticed.hurt.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.what_happened`: the villager discloses. Subject `noticed.injury`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.what_happened/1   [73 chars]
    en  Something came out of the dark faster than I did. That's the whole story.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. É a história inteira.
    >>  ............................................
  dialogue.conversations.noticed.hurt.what_happened/2   [77 chars]
    en  My own fault. I went where I'd been told not to go and the telling was right.
    >>  ............................................
    pt  Culpa minha. Fui onde me disseram pra não ir e o aviso estava certo.
    >>  ............................................
  dialogue.conversations.noticed.hurt.what_happened/3   [78 chars]
    en  A fall, and a stupid one. I'd rather it had been something with a story in it.
    >>  ............................................
    pt  Uma queda, e das burras. Preferia que tivesse sido algo com história.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark. I've not been able to stop hearing it since.
    >>  ............................................
    pt  Algo saiu do escuro. Não consegui parar de ouvir aquilo desde então.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, and that's the part that keeps me awake rather than the wound.
    >>  ............................................
    pt  Culpa minha, e é essa parte que me tira o sono, não o ferimento.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. I'd rather it had been something with a story in it, so it meant something.
    >>  ............................................
    pt  Uma queda. Preferia que fosse algo com história, pra significar alguma coisa.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It happens about once a decade.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Acontece uma vez por década.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I've been told not to go there for twenty years and I keep testing it.
    >>  ............................................
    pt  Culpa minha. Me dizem pra não ir lá há vinte anos e eu continuo testando.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. The ground's been in the same place my whole life and it still catches me.
    >>  ............................................
    pt  Uma queda. O chão está no mesmo lugar a vida toda e ainda me pega.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. That's the whole story.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. É a história inteira.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I went where I'd been told not to go.
    >>  ............................................
    pt  Culpa minha. Fui onde me disseram pra não ir.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one, and there's no more to it than that.
    >>  ............................................
    pt  Uma queda. Das burras, e não tem mais nada nisso.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. That's the whole story.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. É a história inteira.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I went where I'd been told not to go.
    >>  ............................................
    pt  Culpa minha. Fui onde me disseram pra não ir.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one, and there's no more to it than that.
    >>  ............................................
    pt  Uma queda. Das burras, e não tem mais nada nisso.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. I'd not tell that to everyone.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Não contaria isso a qualquer um.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, %1$s, and I'd rather hear you say so than have you be polite.
    >>  ............................................
    pt  Culpa minha, %1$s, e prefiro que você diga isso a que seja educado.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall, and a stupid one. You may laugh. Everyone else has managed to.
    >>  ............................................
    pt  Uma queda, e das burras. Pode rir. Todo mundo conseguiu.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. I'd not tell that to everyone.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Não contaria isso a qualquer um.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, %1$s, and I'd rather hear you say so than have you be polite.
    >>  ............................................
    pt  Culpa minha, %1$s, e prefiro que você diga isso a que seja educado.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall, and a stupid one. You may laugh. Everyone else has managed to.
    >>  ............................................
    pt  Uma queda, e das burras. Pode rir. Todo mundo conseguiu.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. I'd not tell that to everyone.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Não contaria isso a qualquer um.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, %1$s, and I'd rather hear you say so than have you be polite.
    >>  ............................................
    pt  Culpa minha, %1$s, e prefiro que você diga isso a que seja educado.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall, and a stupid one. You may laugh. Everyone else has managed to.
    >>  ............................................
    pt  Uma queda, e das burras. Pode rir. Todo mundo conseguiu.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark. I've not been able to stop hearing it since.
    >>  ............................................
    pt  Algo saiu do escuro. Não consegui parar de ouvir aquilo desde então.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, and that's the part that keeps me awake rather than the wound.
    >>  ............................................
    pt  Culpa minha, e é essa parte que me tira o sono, não o ferimento.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. I'd rather it had been something with a story in it, so it meant something.
    >>  ............................................
    pt  Uma queda. Preferia que fosse algo com história, pra significar alguma coisa.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. That's the whole story.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. É a história inteira.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I went where I'd been told not to go.
    >>  ............................................
    pt  Culpa minha. Fui onde me disseram pra não ir.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one, and there's no more to it than that.
    >>  ............................................
    pt  Uma queda. Das burras, e não tem mais nada nisso.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. That's the whole story.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. É a história inteira.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I went where I'd been told not to go.
    >>  ............................................
    pt  Culpa minha. Fui onde me disseram pra não ir.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one, and there's no more to it than that.
    >>  ............................................
    pt  Uma queda. Das burras, e não tem mais nada nisso.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something in the dark. Faster than me.
    >>  ............................................
    pt  Algo no escuro. Mais rápido que eu.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault.
    >>  ............................................
    pt  Culpa minha.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one.
    >>  ............................................
    pt  Uma queda. Das burras.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It happens about once a decade.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Acontece uma vez por década.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I've been told not to go there for twenty years and I keep testing it.
    >>  ............................................
    pt  Culpa minha. Me dizem pra não ir lá há vinte anos e eu continuo testando.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. The ground's been in the same place my whole life and it still catches me.
    >>  ............................................
    pt  Uma queda. O chão está no mesmo lugar a vida toda e ainda me pega.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something in the dark. Faster than me.
    >>  ............................................
    pt  Algo no escuro. Mais rápido que eu.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault.
    >>  ............................................
    pt  Culpa minha.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one.
    >>  ............................................
    pt  Uma queda. Das burras.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It happens about once a decade.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Acontece uma vez por década.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I've been told not to go there for twenty years and I keep testing it.
    >>  ............................................
    pt  Culpa minha. Me dizem pra não ir lá há vinte anos e eu continuo testando.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. The ground's been in the same place my whole life and it still catches me.
    >>  ............................................
    pt  Uma queda. O chão está no mesmo lugar a vida toda e ainda me pega.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It won, and it knows it won.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Venceu, e sabe que venceu.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault entirely. I was told not to go and I went with real commitment.
    >>  ............................................
    pt  Culpa inteiramente minha. Me disseram pra não ir e eu fui com convicção.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall! A magnificent one. No audience, which is the only mercy in it.
    >>  ............................................
    pt  Uma queda! Magnífica. Sem plateia, que é a única misericórdia nisso.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It won, and it knows it won.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Venceu, e sabe que venceu.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault entirely. I was told not to go and I went with real commitment.
    >>  ............................................
    pt  Culpa inteiramente minha. Me disseram pra não ir e eu fui com convicção.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall! A magnificent one. No audience, which is the only mercy in it.
    >>  ............................................
    pt  Uma queda! Magnífica. Sem plateia, que é a única misericórdia nisso.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It happens about once a decade.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Acontece uma vez por década.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault. I've been told not to go there for twenty years and I keep testing it.
    >>  ............................................
    pt  Culpa minha. Me dizem pra não ir lá há vinte anos e eu continuo testando.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. The ground's been in the same place my whole life and it still catches me.
    >>  ............................................
    pt  Uma queda. O chão está no mesmo lugar a vida toda e ainda me pega.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark. I've not been able to stop hearing it since.
    >>  ............................................
    pt  Algo saiu do escuro. Não consegui parar de ouvir aquilo desde então.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault, and that's the part that keeps me awake rather than the wound.
    >>  ............................................
    pt  Culpa minha, e é essa parte que me tira o sono, não o ferimento.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. I'd rather it had been something with a story in it, so it meant something.
    >>  ............................................
    pt  Uma queda. Preferia que fosse algo com história, pra significar alguma coisa.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something in the dark. Faster than me.
    >>  ............................................
    pt  Algo no escuro. Mais rápido que eu.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault.
    >>  ............................................
    pt  Culpa minha.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall. A stupid one.
    >>  ............................................
    pt  Uma queda. Das burras.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It won, and it knows it won.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Venceu, e sabe que venceu.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault entirely. I was told not to go and I went with real commitment.
    >>  ............................................
    pt  Culpa inteiramente minha. Me disseram pra não ir e eu fui com convicção.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall! A magnificent one. No audience, which is the only mercy in it.
    >>  ............................................
    pt  Uma queda! Magnífica. Sem plateia, que é a única misericórdia nisso.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.what_happened/1
    en  Something came out of the dark faster than I did. It won, and it knows it won.
    >>  ............................................
    pt  Algo saiu do escuro mais rápido que eu. Venceu, e sabe que venceu.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.what_happened/2
    en  My own fault entirely. I was told not to go and I went with real commitment.
    >>  ............................................
    pt  Culpa inteiramente minha. Me disseram pra não ir e eu fui com convicção.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.what_happened/3
    en  A fall! A magnificent one. No audience, which is the only mercy in it.
    >>  ............................................
    pt  Uma queda! Magnífica. Sem plateia, que é a única misericórdia nisso.
    >>  ............................................
```

</details>


### Button `tend_it` — "Has anyone tended it?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.hurt.open` · offered only once the villager has actually said `noticed:hurt`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.tend_it` — accepted phrasings: "has anyone tended it"; "has anyone treated that"; "has the cleric seen it"
  - the message must contain one of: `tended`, `treated`
  - scored words: `tended`(1.5), `treated`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond.tend_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.respond.tend_it   [21 chars]
    en  Has anyone tended it?
    >>  ............................................
    pt  Alguém cuidou disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.hurt.followup`
- …where the player's next choices will be: "I'll come and check on you tomorrow." | "I can sit with you a while." | "I'll let you rest."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.tend_it
WHO    VILLAGER — what the player reads after pressing "Has anyone tended it?"
       spoken on: conversations.topic.noticed.hurt.respond, button `tend_it`
       leaves the player on: conversations.topic.noticed.hurt.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.tend_it`: the villager reports. Subject `noticed.injury`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.tend_it/1   [65 chars]
    en  I've bound it myself, badly, and I'll not hear a word about that.
    >>  ............................................
    pt  Eu mesmo enfaixei, mal, e não quero ouvir nada sobre isso.
    >>  ............................................
  dialogue.conversations.noticed.hurt.tend_it/2   [75 chars]
    en  The cleric looked at it and made a face. That's all the treatment I've had.
    >>  ............................................
    pt  O clérigo olhou e fez uma cara. É todo o tratamento que tive.
    >>  ............................................
  dialogue.conversations.noticed.hurt.tend_it/3   [78 chars]
    en  Not yet. Everyone here is busy and I'm bad at asking, which is a poor pairing.
    >>  ............................................
    pt  Ainda não. Todos estão ocupados e eu sou ruim em pedir, o que é uma combinação péssima.
    >>  ............................................
```


### Button `rest` — "You should be resting, not standing here."

*stance family `practical_help` · tone `gentle` · outcome `qualified` · answers the beat(s) `noticed.hurt.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.rest` — accepted phrasings: "you should be resting"; "you ought to be lying down"; "you should not be on your feet"
  - the message must contain one of: `resting`
  - scored words: `resting`(1.5), `standing`(0.7)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond.rest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.respond.rest   [41 chars]
    en  You should be resting, not standing here.
    >>  ............................................
    pt  Você devia estar descansando, não de pé aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1  _(recorded under topic `noticed.hurt.rest`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.hurt.followup`
- …where the player's next choices will be: "I'll come and check on you tomorrow." | "I can sit with you a while." | "I'll let you rest."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.rest
WHO    VILLAGER — what the player reads after pressing "You should be resting, not standing here."
       spoken on: conversations.topic.noticed.hurt.respond, button `rest`
       leaves the player on: conversations.topic.noticed.hurt.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.rest`: the villager qualifys. Subject `noticed.injury`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.rest/1   [73 chars]
    en  The work doesn't rest, so neither do I. That's how it's always gone here.
    >>  ............................................
    pt  O trabalho não descansa, então eu também não. É como sempre foi aqui.
    >>  ............................................
  dialogue.conversations.noticed.hurt.rest/2   [79 chars]
    en  You're right and I'll ignore you, which is what I do with everyone who's right.
    >>  ............................................
    pt  Você tem razão e eu vou te ignorar, como faço com todos que têm razão.
    >>  ............................................
  dialogue.conversations.noticed.hurt.rest/3   [79 chars]
    en  Say that to whoever's expecting me at the gate and I'll go and lie down gladly.
    >>  ............................................
    pt  Diga isso a quem me espera no portão e eu vou me deitar com prazer.
    >>  ............................................
```


### Button `dismiss` — "It's only a scratch."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.hurt.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.hurt.dismissed` — accepted phrasings: "it is only a scratch"; "that is hardly anything"; "you will live"
  - the message must contain one of: `scratch`
  - scored words: `scratch`(1.5), `only`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.respond.dismiss   [20 chars]
    en  It's only a scratch.
    >>  ............................................
    pt  É só um arranhão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.hurt.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, warmth -3  _(recorded under topic `noticed.hurt.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.dismissed
WHO    VILLAGER — what the player reads after pressing "It's only a scratch."
       spoken on: conversations.topic.noticed.hurt.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.dismissed`: the villager hurts. Subject `noticed.injury`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.dismissed/1   [54 chars]
    en  Then you've a poor eye for scratches. Good day to you.
    >>  ............................................
    pt  Então você tem um olho ruim pra arranhões. Bom dia.
    >>  ............................................
  dialogue.conversations.noticed.hurt.dismissed/2   [55 chars]
    en  It's my scratch, and I'll decide how much of one it is.
    >>  ............................................
    pt  O arranhão é meu, e eu decido o tamanho dele.
    >>  ............................................
  dialogue.conversations.noticed.hurt.dismissed/3   [69 chars]
    en  Right. I'll remember that the next time you want telling how you are.
    >>  ............................................
    pt  Certo. Vou lembrar disso na próxima vez que você quiser contar como está.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.hurt.dismissed/1
    en  ...Please don't. I had to work up to telling you at all.
    >>  ............................................
    pt  ...Por favor, não. Eu tive que criar coragem pra te contar.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I know it's small. That isn't why I said it.
    >>  ............................................
    pt  O arranhão é meu. Sei que é pequeno. Não foi por isso que eu disse.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll go back to saying I'm fine, then, and we'll both prefer that.
    >>  ............................................
    pt  Certo. Volto a dizer que estou bem, então, e nós dois vamos preferir assim.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.dismissed/1
    en  It is a scratch. It's also the fourth this year, and I'm not as quick as I was.
    >>  ............................................
    pt  É um arranhão. Também é o quarto este ano, e eu não sou tão rápido quanto era.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I've had enough of them to know which ones matter.
    >>  ............................................
    pt  O arranhão é meu. Já tive o bastante pra saber quais importam.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. You'll say something else in thirty years, and you'll mean it.
    >>  ............................................
    pt  Certo. Você vai dizer outra coisa em trinta anos, e vai falar sério.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye for scratches. Good day to you.
    >>  ............................................
    pt  Então você tem um olho ruim pra arranhões. Bom dia.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch, and I'll decide how much of one it is.
    >>  ............................................
    pt  O arranhão é meu, e eu decido o tamanho dele.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll remember that next time you want telling how I am.
    >>  ............................................
    pt  Certo. Vou lembrar disso na próxima vez que quiser saber como estou.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye for scratches. Good day to you.
    >>  ............................................
    pt  Então você tem um olho ruim pra arranhões. Bom dia.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch, and I'll decide how much of one it is.
    >>  ............................................
    pt  O arranhão é meu, e eu decido o tamanho dele.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll remember that next time you want telling how I am.
    >>  ............................................
    pt  Certo. Vou lembrar disso na próxima vez que quiser saber como estou.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.dismissed/1
    en  From you? That stings worse than the wound, %1$s.
    >>  ............................................
    pt  De você? Isso arde mais do que o ferimento, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I'd have taken anything else from you but that.
    >>  ............................................
    pt  O arranhão é meu. Eu aceitaria qualquer outra coisa de você, menos isso.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I told you because it was you. I'll not make that error twice.
    >>  ............................................
    pt  Certo. Eu contei porque era você. Não vou cometer esse erro duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.dismissed/1
    en  From you? That stings worse than the wound, %1$s.
    >>  ............................................
    pt  De você? Isso arde mais do que o ferimento, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I'd have taken anything else from you but that.
    >>  ............................................
    pt  O arranhão é meu. Eu aceitaria qualquer outra coisa de você, menos isso.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I told you because it was you. I'll not make that error twice.
    >>  ............................................
    pt  Certo. Eu contei porque era você. Não vou cometer esse erro duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.dismissed/1
    en  From you? That stings worse than the wound, %1$s.
    >>  ............................................
    pt  De você? Isso arde mais do que o ferimento, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I'd have taken anything else from you but that.
    >>  ............................................
    pt  O arranhão é meu. Eu aceitaria qualquer outra coisa de você, menos isso.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I told you because it was you. I'll not make that error twice.
    >>  ............................................
    pt  Certo. Eu contei porque era você. Não vou cometer esse erro duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.dismissed/1
    en  ...Please don't. I had to work up to telling you at all.
    >>  ............................................
    pt  ...Por favor, não. Eu tive que criar coragem pra te contar.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I know it's small. That isn't why I said it.
    >>  ............................................
    pt  O arranhão é meu. Sei que é pequeno. Não foi por isso que eu disse.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll go back to saying I'm fine, then, and we'll both prefer that.
    >>  ............................................
    pt  Certo. Volto a dizer que estou bem, então, e nós dois vamos preferir assim.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye for scratches. Good day to you.
    >>  ............................................
    pt  Então você tem um olho ruim pra arranhões. Bom dia.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch, and I'll decide how much of one it is.
    >>  ............................................
    pt  O arranhão é meu, e eu decido o tamanho dele.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll remember that next time you want telling how I am.
    >>  ............................................
    pt  Certo. Vou lembrar disso na próxima vez que quiser saber como estou.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye for scratches. Good day to you.
    >>  ............................................
    pt  Então você tem um olho ruim pra arranhões. Bom dia.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch, and I'll decide how much of one it is.
    >>  ............................................
    pt  O arranhão é meu, e eu decido o tamanho dele.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll remember that next time you want telling how I am.
    >>  ............................................
    pt  Certo. Vou lembrar disso na próxima vez que quiser saber como estou.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye.
    >>  ............................................
    pt  Então seu olho é ruim.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's mine to measure.
    >>  ............................................
    pt  É meu pra medir.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. Noted.
    >>  ............................................
    pt  Certo. Anotado.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.dismissed/1
    en  It is a scratch. It's also the fourth this year, and I'm not as quick as I was.
    >>  ............................................
    pt  É um arranhão. Também é o quarto este ano, e eu não sou tão rápido quanto era.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I've had enough of them to know which ones matter.
    >>  ............................................
    pt  O arranhão é meu. Já tive o bastante pra saber quais importam.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. You'll say something else in thirty years, and you'll mean it.
    >>  ............................................
    pt  Certo. Você vai dizer outra coisa em trinta anos, e vai falar sério.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye.
    >>  ............................................
    pt  Então seu olho é ruim.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's mine to measure.
    >>  ............................................
    pt  É meu pra medir.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. Noted.
    >>  ............................................
    pt  Certo. Anotado.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.dismissed/1
    en  It is a scratch. It's also the fourth this year, and I'm not as quick as I was.
    >>  ............................................
    pt  É um arranhão. Também é o quarto este ano, e eu não sou tão rápido quanto era.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I've had enough of them to know which ones matter.
    >>  ............................................
    pt  O arranhão é meu. Já tive o bastante pra saber quais importam.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. You'll say something else in thirty years, and you'll mean it.
    >>  ............................................
    pt  Certo. Você vai dizer outra coisa em trinta anos, e vai falar sério.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.dismissed/1
    en  A scratch! Marvellous. Come back when your eyes work and we'll talk again.
    >>  ............................................
    pt  Um arranhão! Maravilhoso. Volte quando seus olhos funcionarem e conversamos.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch and I've grown fond of it, so mind how you speak about it.
    >>  ............................................
    pt  O arranhão é meu e eu me afeiçoei a ele, então cuidado como fala dele.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll be sure to bleed more entertainingly for you next time.
    >>  ............................................
    pt  Certo. Vou sangrar de forma mais divertida pra você na próxima.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.dismissed/1
    en  A scratch! Marvellous. Come back when your eyes work and we'll talk again.
    >>  ............................................
    pt  Um arranhão! Maravilhoso. Volte quando seus olhos funcionarem e conversamos.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch and I've grown fond of it, so mind how you speak about it.
    >>  ............................................
    pt  O arranhão é meu e eu me afeiçoei a ele, então cuidado como fala dele.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll be sure to bleed more entertainingly for you next time.
    >>  ............................................
    pt  Certo. Vou sangrar de forma mais divertida pra você na próxima.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.dismissed/1
    en  It is a scratch. It's also the fourth this year, and I'm not as quick as I was.
    >>  ............................................
    pt  É um arranhão. Também é o quarto este ano, e eu não sou tão rápido quanto era.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I've had enough of them to know which ones matter.
    >>  ............................................
    pt  O arranhão é meu. Já tive o bastante pra saber quais importam.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. You'll say something else in thirty years, and you'll mean it.
    >>  ............................................
    pt  Certo. Você vai dizer outra coisa em trinta anos, e vai falar sério.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.dismissed/1
    en  ...Please don't. I had to work up to telling you at all.
    >>  ............................................
    pt  ...Por favor, não. Eu tive que criar coragem pra te contar.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch. I know it's small. That isn't why I said it.
    >>  ............................................
    pt  O arranhão é meu. Sei que é pequeno. Não foi por isso que eu disse.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll go back to saying I'm fine, then, and we'll both prefer that.
    >>  ............................................
    pt  Certo. Volto a dizer que estou bem, então, e nós dois vamos preferir assim.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.dismissed/1
    en  Then you've a poor eye.
    >>  ............................................
    pt  Então seu olho é ruim.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's mine to measure.
    >>  ............................................
    pt  É meu pra medir.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. Noted.
    >>  ............................................
    pt  Certo. Anotado.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.dismissed/1
    en  A scratch! Marvellous. Come back when your eyes work and we'll talk again.
    >>  ............................................
    pt  Um arranhão! Maravilhoso. Volte quando seus olhos funcionarem e conversamos.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch and I've grown fond of it, so mind how you speak about it.
    >>  ............................................
    pt  O arranhão é meu e eu me afeiçoei a ele, então cuidado como fala dele.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll be sure to bleed more entertainingly for you next time.
    >>  ............................................
    pt  Certo. Vou sangrar de forma mais divertida pra você na próxima.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.dismissed/1
    en  A scratch! Marvellous. Come back when your eyes work and we'll talk again.
    >>  ............................................
    pt  Um arranhão! Maravilhoso. Volte quando seus olhos funcionarem e conversamos.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.dismissed/2
    en  It's my scratch and I've grown fond of it, so mind how you speak about it.
    >>  ............................................
    pt  O arranhão é meu e eu me afeiçoei a ele, então cuidado como fala dele.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt.dismissed/3
    en  Right. I'll be sure to bleed more entertainingly for you next time.
    >>  ............................................
    pt  Certo. Vou sangrar de forma mais divertida pra você na próxima.
    >>  ............................................
```

</details>


### Button `back` — "I'll leave you to it."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.hurt.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.hurt.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.hurt.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.hurt.respond.back   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Deixo você com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt.back
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.topic.noticed.hurt.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.back`: the villager accepts. Subject `noticed.injury`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt.back/1   [31 chars]
    en  Quite. Mind yourself out there.
    >>  ............................................
    pt  Exato. Se cuide por aí.
    >>  ............................................
  dialogue.conversations.noticed.hurt.back/2   [8 chars]
    en  Do that.
    >>  ............................................
    pt  Faça isso.
    >>  ............................................
  dialogue.conversations.noticed.hurt.back/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.proud.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.proud.respond` / `what_about`; `conversations.topic.noticed.proud.respond` / `who_told`; `conversations.topic.noticed.proud.respond` / `embarrassed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.proud.embarrassed` — e.g. "Nothing. That's the trick of it — you're allowed to just hear it."
- `conversations.noticed.proud.what_about` — e.g. "You finished the thing you said you'd finish. You'd be amazed how rare that is."
- `conversations.noticed.proud.who_told` — e.g. "Anyone who slowed down near me. Two of them twice, I think."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.proud.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.proud.followup   [36 chars]
    en  That's all I wanted to say about it.
    >>  ............................................
    pt  É tudo que eu queria dizer sobre isso.
    >>  ............................................
```


### Button `means_a_lot` — "That means more than you know."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.proud.what_about`, `noticed.proud.who_told`, `noticed.proud.embarrassed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.followup.means` — accepted phrasings: "that means more than you know"; "you have no idea what that means"; "that means a great deal to me"
  - the message must contain one of: `means`
  - scored words: `means`(1.2), `know`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.followup.means_a_lot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.followup.means_a_lot   [30 chars]
    en  That means more than you know.
    >>  ............................................
    pt  Isso significa mais do que você imagina.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.proud.followup.means`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +2, warmth +2  _(recorded under topic `noticed.proud.followup.means`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.followup.means
WHO    VILLAGER — what the player reads after pressing "That means more than you know."
       spoken on: conversations.topic.noticed.proud.followup, button `means_a_lot`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.followup.means`: the villager accepts. Subject `noticed.pride`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.followup.means/1   [63 chars]
    en  Then I've said it too rarely, and I'll do something about that.
    >>  ............................................
    pt  Então eu digo isso raramente demais, e vou fazer algo a respeito.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.means/2   [61 chars]
    en  Good. I nearly kept it to myself, the way I keep most things.
    >>  ............................................
    pt  Bom. Quase guardei pra mim, como guardo quase tudo.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.means/3   [84 chars]
    en  It cost me nothing and it seems to have been worth a great deal. I'll remember that.
    >>  ............................................
    pt  Não me custou nada e parece ter valido muito. Vou lembrar disso.
    >>  ............................................
```


### Button `we_did_it` — "You had as much to do with it as I did."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `noticed.proud.what_about`, `noticed.proud.who_told`, `noticed.proud.embarrassed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.followup.shared` — accepted phrasings: "you had as much to do with it"; "the credit is yours too"; "we did that together"
  - the message must contain one of: `credit`
  - scored words: `much`(0.5), `credit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.followup.we_did_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.followup.we_did_it   [39 chars]
    en  You had as much to do with it as I did.
    >>  ............................................
    pt  Você teve tanto a ver com isso quanto eu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.proud.followup.shared`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, respect +2  _(recorded under topic `noticed.proud.followup.shared`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.followup.shared
WHO    VILLAGER — what the player reads after pressing "You had as much to do with it as I did."
       spoken on: conversations.topic.noticed.proud.followup, button `we_did_it`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.followup.shared`: the villager qualifys. Subject `noticed.pride`, polarity `positive`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.followup.shared/1   [73 chars]
    en  I held a lantern and complained. Let's not rewrite it into a partnership.
    >>  ............................................
    pt  Eu segurei uma lanterna e reclamei. Não vamos reescrever isso como parceria.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.shared/2   [75 chars]
    en  That's generous and about a third true, which is the good kind of generous.
    >>  ............................................
    pt  É generoso e um terço verdade, que é o bom tipo de generosidade.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.shared/3   [72 chars]
    en  Take the credit, %1$s. It's not so heavy that you need help carrying it.
    >>  ............................................
    pt  Aceite o crédito, %1$s. Não é tão pesado que você precise de ajuda pra carregar.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.proud.what_about`, `noticed.proud.who_told`, `noticed.proud.embarrassed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.topic.noticed.proud.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.followup.leave`: the villager accepts. Subject `noticed.pride`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.followup.leave/1   [25 chars]
    en  Do. And well done, again.
    >>  ............................................
    pt  Siga. E parabéns, de novo.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.leave/2   [15 chars]
    en  Just so. Go on.
    >>  ............................................
    pt  Pois é. Vá lá.
    >>  ............................................
  dialogue.conversations.noticed.proud.followup.leave/3   [16 chars]
    en  Go safely, %1$s.
    >>  ............................................
    pt  Vá com cuidado, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.proud.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.proud` — e.g. "Better than I've been in a while, and you're a good part of the reason, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.proud.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.proud.respond   [47 chars]
    en  Since you asked, I've been meaning to say this.
    >>  ............................................
    pt  Já que perguntou, eu vinha querendo dizer isso.
    >>  ............................................
```


### Button `what_about` — "What have I done, exactly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.proud.open` · offered only once the villager has actually said `noticed:proud`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.what_about` — accepted phrasings: "what have i done exactly"; "what is it i did"; "what are you talking about exactly"
  - the message must contain one of: `exactly`
  - scored words: `done`(0.8), `exactly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond.what_about
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.respond.what_about   [26 chars]
    en  What have I done, exactly?
    >>  ............................................
    pt  O que exatamente eu fiz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.proud.followup`
- …where the player's next choices will be: "That means more than you know." | "You had as much to do with it as I did." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.what_about
WHO    VILLAGER — what the player reads after pressing "What have I done, exactly?"
       spoken on: conversations.topic.noticed.proud.respond, button `what_about`
       leaves the player on: conversations.topic.noticed.proud.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.what_about`: the villager explains. Subject `noticed.pride`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.what_about/1   [79 chars]
    en  You finished the thing you said you'd finish. You'd be amazed how rare that is.
    >>  ............................................
    pt  Você terminou aquilo que disse que terminaria. Você ficaria espantado com o quão raro é.
    >>  ............................................
  dialogue.conversations.noticed.proud.what_about/2   [78 chars]
    en  You did it without being asked twice, and without telling everyone afterwards.
    >>  ............................................
    pt  Você fez sem precisar ser pedido duas vezes, e sem contar a todos depois.
    >>  ............................................
  dialogue.conversations.noticed.proud.what_about/3   [71 chars]
    en  You took a problem off me that I'd stopped believing anyone would take.
    >>  ............................................
    pt  Você tirou de mim um problema que eu já não acreditava que alguém tiraria.
    >>  ............................................
```


### Button `who_told` — "Who have you been telling?"

*stance family `curiosity` · tone `playful` · outcome `engaged` · answers the beat(s) `noticed.proud.open` · offered only once the villager has actually said `noticed:proud`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.who_told` — accepted phrasings: "who have you been telling"; "who did you tell"; "have you been talking about me"
  - the message must contain one of: `telling`
  - scored words: `telling`(1.5), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond.who_told
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.respond.who_told   [26 chars]
    en  Who have you been telling?
    >>  ............................................
    pt  Pra quem você andou contando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.proud.followup`
- …where the player's next choices will be: "That means more than you know." | "You had as much to do with it as I did." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.who_told
WHO    VILLAGER — what the player reads after pressing "Who have you been telling?"
       spoken on: conversations.topic.noticed.proud.respond, button `who_told`
       leaves the player on: conversations.topic.noticed.proud.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.who_told`: the villager celebrates. Subject `noticed.pride`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.who_told/1   [59 chars]
    en  Anyone who slowed down near me. Two of them twice, I think.
    >>  ............................................
    pt  Qualquer um que diminuiu o passo perto de mim. Dois deles duas vezes, acho.
    >>  ............................................
  dialogue.conversations.noticed.proud.who_told/2   [57 chars]
    en  The baker, at length, and she'd not asked. She knows now.
    >>  ............................................
    pt  A padeira, demoradamente, e ela nem tinha perguntado. Agora ela sabe.
    >>  ............................................
  dialogue.conversations.noticed.proud.who_told/3   [72 chars]
    en  Enough people that you'll be greeted oddly for a week. You've earned it.
    >>  ............................................
    pt  Gente o bastante pra você ser cumprimentado de um jeito estranho por uma semana.
    >>  ............................................
```


### Button `embarrassed` — "I don't know what to do with that."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `noticed.proud.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.embarrassed` — accepted phrasings: "i do not know what to do with that"; "that is awkward to hear"; "i am not good at being praised"
  - the message must contain one of: `praised`, `awkward`
  - scored words: `praised`(1.2), `awkward`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond.embarrassed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.respond.embarrassed   [34 chars]
    en  I don't know what to do with that.
    >>  ............................................
    pt  Não sei o que fazer com isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `noticed.proud.embarrassed`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.proud.followup`
- …where the player's next choices will be: "That means more than you know." | "You had as much to do with it as I did." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.embarrassed
WHO    VILLAGER — what the player reads after pressing "I don't know what to do with that."
       spoken on: conversations.topic.noticed.proud.respond, button `embarrassed`
       leaves the player on: conversations.topic.noticed.proud.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.embarrassed`: the villager accepts. Subject `noticed.pride`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, self_disclosure, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.embarrassed/1   [65 chars]
    en  Nothing. That's the trick of it — you're allowed to just hear it.
    >>  ............................................
    pt  Nada. É esse o truque — você tem permissão de só ouvir.
    >>  ............................................
  dialogue.conversations.noticed.proud.embarrassed/2   [51 chars]
    en  Neither did I, so I said it badly. It still counts.
    >>  ............................................
    pt  Eu também não sabia, então disse mal. Ainda vale.
    >>  ............................................
  dialogue.conversations.noticed.proud.embarrassed/3   [72 chars]
    en  Stand there a moment and let it be true. That's all that's wanted, %1$s.
    >>  ............................................
    pt  Fique aí um momento e deixe ser verdade. É tudo que se pede, %1$s.
    >>  ............................................
```


### Button `dismiss` — "It was nothing. Forget it."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.proud.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.proud.dismissed` — accepted phrasings: "it was nothing forget it"; "do not make a fuss"; "it really was not a big deal"
  - the message must contain one of: `forget`
  - scored words: `forget`(1.2), `nothing`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.respond.dismiss   [26 chars]
    en  It was nothing. Forget it.
    >>  ............................................
    pt  Não foi nada. Esqueça.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `noticed.proud.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `noticed.proud.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.dismissed
WHO    VILLAGER — what the player reads after pressing "It was nothing. Forget it."
       spoken on: conversations.topic.noticed.proud.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.dismissed`: the villager hurts. Subject `noticed.pride`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.dismissed/1   [60 chars]
    en  It wasn't nothing to me. But I'll forget it if you'd rather.
    >>  ............................................
    pt  Não foi nada pra mim. Mas eu esqueço, se você preferir.
    >>  ............................................
  dialogue.conversations.noticed.proud.dismissed/2   [67 chars]
    en  Then I'll keep the next one in my head where it can't be waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde não pode ser dispensado.
    >>  ............................................
  dialogue.conversations.noticed.proud.dismissed/3   [73 chars]
    en  Fine. That's twice today somebody has told me my own feelings were wrong.
    >>  ............................................
    pt  Tudo bem. É a segunda vez hoje que me dizem que meus sentimentos estão errados.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.proud.dismissed/1
    en  ...It wasn't nothing to me. I had to work myself up to saying it at all.
    >>  ............................................
    pt  ...Não foi nada pra mim. Eu tive que criar coragem só pra dizer aquilo.
    >>  ............................................
  anxious.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head, where it's safe from being waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde está a salvo de ser dispensado.
    >>  ............................................
  anxious.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'll stop. I'm sorry — I thought you'd want to know.
    >>  ............................................
    pt  Tudo bem. Eu paro. Desculpe — achei que você fosse querer saber.
    >>  ............................................
  athletic.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. I don't say that sort of thing more than twice a decade.
    >>  ............................................
    pt  Não foi nada pra mim. Não digo esse tipo de coisa mais que duas vezes por década.
    >>  ............................................
  athletic.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one. I've kept plenty; one more costs me nothing.
    >>  ............................................
    pt  Então guardo o próximo. Já guardei muitos; mais um não me custa nada.
    >>  ............................................
  athletic.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. You'll learn to take it eventually. Everyone does, around sixty.
    >>  ............................................
    pt  Tudo bem. Você vai aprender a aceitar. Todos aprendem, lá pelos sessenta.
    >>  ............................................
  confident.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. But I'll forget it if you'd rather.
    >>  ............................................
    pt  Não foi nada pra mim. Mas eu esqueço, se preferir.
    >>  ............................................
  confident.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head where it can't be waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde não pode ser dispensado.
    >>  ............................................
  confident.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. That's twice today somebody told me my own feelings were wrong.
    >>  ............................................
    pt  Tudo bem. É a segunda vez hoje que me dizem que meus sentimentos estão errados.
    >>  ............................................
  crabby.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. But I'll forget it if you'd rather.
    >>  ............................................
    pt  Não foi nada pra mim. Mas eu esqueço, se preferir.
    >>  ............................................
  crabby.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head where it can't be waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde não pode ser dispensado.
    >>  ............................................
  crabby.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. That's twice today somebody told me my own feelings were wrong.
    >>  ............................................
    pt  Tudo bem. É a segunda vez hoje que me dizem que meus sentimentos estão errados.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me, %1$s. That's the part I'd wanted you to hear.
    >>  ............................................
    pt  Não foi nada pra mim, %1$s. É essa parte que eu queria que você ouvisse.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one to myself, and you'll never know there was one.
    >>  ............................................
    pt  Então guardo o próximo pra mim, e você nunca vai saber que existiu.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'd been saving that up for a fortnight, but fine.
    >>  ............................................
    pt  Tudo bem. Eu vinha guardando aquilo há quinze dias, mas tudo bem.
    >>  ............................................
  flirty.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me, %1$s. That's the part I'd wanted you to hear.
    >>  ............................................
    pt  Não foi nada pra mim, %1$s. É essa parte que eu queria que você ouvisse.
    >>  ............................................
  flirty.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one to myself, and you'll never know there was one.
    >>  ............................................
    pt  Então guardo o próximo pra mim, e você nunca vai saber que existiu.
    >>  ............................................
  flirty.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'd been saving that up for a fortnight, but fine.
    >>  ............................................
    pt  Tudo bem. Eu vinha guardando aquilo há quinze dias, mas tudo bem.
    >>  ............................................
  friendly.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me, %1$s. That's the part I'd wanted you to hear.
    >>  ............................................
    pt  Não foi nada pra mim, %1$s. É essa parte que eu queria que você ouvisse.
    >>  ............................................
  friendly.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one to myself, and you'll never know there was one.
    >>  ............................................
    pt  Então guardo o próximo pra mim, e você nunca vai saber que existiu.
    >>  ............................................
  friendly.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'd been saving that up for a fortnight, but fine.
    >>  ............................................
    pt  Tudo bem. Eu vinha guardando aquilo há quinze dias, mas tudo bem.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.proud.dismissed/1
    en  ...It wasn't nothing to me. I had to work myself up to saying it at all.
    >>  ............................................
    pt  ...Não foi nada pra mim. Eu tive que criar coragem só pra dizer aquilo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head, where it's safe from being waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde está a salvo de ser dispensado.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'll stop. I'm sorry — I thought you'd want to know.
    >>  ............................................
    pt  Tudo bem. Eu paro. Desculpe — achei que você fosse querer saber.
    >>  ............................................
  greedy.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. But I'll forget it if you'd rather.
    >>  ............................................
    pt  Não foi nada pra mim. Mas eu esqueço, se preferir.
    >>  ............................................
  greedy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head where it can't be waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde não pode ser dispensado.
    >>  ............................................
  greedy.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. That's twice today somebody told me my own feelings were wrong.
    >>  ............................................
    pt  Tudo bem. É a segunda vez hoje que me dizem que meus sentimentos estão errados.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. But I'll forget it if you'd rather.
    >>  ............................................
    pt  Não foi nada pra mim. Mas eu esqueço, se preferir.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head where it can't be waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde não pode ser dispensado.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. That's twice today somebody told me my own feelings were wrong.
    >>  ............................................
    pt  Tudo bem. É a segunda vez hoje que me dizem que meus sentimentos estão errados.
    >>  ............................................
  introverted.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me.
    >>  ............................................
    pt  Não foi nada pra mim.
    >>  ............................................
  introverted.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one.
    >>  ............................................
    pt  Então guardo o próximo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine.
    >>  ............................................
    pt  Tudo bem.
    >>  ............................................
  lazy.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. I don't say that sort of thing more than twice a decade.
    >>  ............................................
    pt  Não foi nada pra mim. Não digo esse tipo de coisa mais que duas vezes por década.
    >>  ............................................
  lazy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one. I've kept plenty; one more costs me nothing.
    >>  ............................................
    pt  Então guardo o próximo. Já guardei muitos; mais um não me custa nada.
    >>  ............................................
  lazy.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. You'll learn to take it eventually. Everyone does, around sixty.
    >>  ............................................
    pt  Tudo bem. Você vai aprender a aceitar. Todos aprendem, lá pelos sessenta.
    >>  ............................................
  odd.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me.
    >>  ............................................
    pt  Não foi nada pra mim.
    >>  ............................................
  odd.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one.
    >>  ............................................
    pt  Então guardo o próximo.
    >>  ............................................
  odd.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine.
    >>  ............................................
    pt  Tudo bem.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. I don't say that sort of thing more than twice a decade.
    >>  ............................................
    pt  Não foi nada pra mim. Não digo esse tipo de coisa mais que duas vezes por década.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one. I've kept plenty; one more costs me nothing.
    >>  ............................................
    pt  Então guardo o próximo. Já guardei muitos; mais um não me custa nada.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. You'll learn to take it eventually. Everyone does, around sixty.
    >>  ............................................
    pt  Tudo bem. Você vai aprender a aceitar. Todos aprendem, lá pelos sessenta.
    >>  ............................................
  peppy.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me! But fine, consider it forgotten with great enthusiasm.
    >>  ............................................
    pt  Não foi nada pra mim! Mas tudo bem, considere esquecido com muito entusiasmo.
    >>  ............................................
  peppy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then the next one stays in my head, where it will be admired by me alone.
    >>  ............................................
    pt  Então o próximo fica na minha cabeça, onde só eu vou admirá-lo.
    >>  ............................................
  peppy.dialogue.conversations.noticed.proud.dismissed/3
    en  Twice today somebody has corrected my own feelings. It's becoming a hobby of theirs.
    >>  ............................................
    pt  Duas vezes hoje corrigiram meus sentimentos. Está virando um passatempo.
    >>  ............................................
  playful.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me! But fine, consider it forgotten with great enthusiasm.
    >>  ............................................
    pt  Não foi nada pra mim! Mas tudo bem, considere esquecido com muito entusiasmo.
    >>  ............................................
  playful.dialogue.conversations.noticed.proud.dismissed/2
    en  Then the next one stays in my head, where it will be admired by me alone.
    >>  ............................................
    pt  Então o próximo fica na minha cabeça, onde só eu vou admirá-lo.
    >>  ............................................
  playful.dialogue.conversations.noticed.proud.dismissed/3
    en  Twice today somebody has corrected my own feelings. It's becoming a hobby of theirs.
    >>  ............................................
    pt  Duas vezes hoje corrigiram meus sentimentos. Está virando um passatempo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me. I don't say that sort of thing more than twice a decade.
    >>  ............................................
    pt  Não foi nada pra mim. Não digo esse tipo de coisa mais que duas vezes por década.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one. I've kept plenty; one more costs me nothing.
    >>  ............................................
    pt  Então guardo o próximo. Já guardei muitos; mais um não me custa nada.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. You'll learn to take it eventually. Everyone does, around sixty.
    >>  ............................................
    pt  Tudo bem. Você vai aprender a aceitar. Todos aprendem, lá pelos sessenta.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.proud.dismissed/1
    en  ...It wasn't nothing to me. I had to work myself up to saying it at all.
    >>  ............................................
    pt  ...Não foi nada pra mim. Eu tive que criar coragem só pra dizer aquilo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one in my head, where it's safe from being waved off.
    >>  ............................................
    pt  Então guardo o próximo na cabeça, onde está a salvo de ser dispensado.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine. I'll stop. I'm sorry — I thought you'd want to know.
    >>  ............................................
    pt  Tudo bem. Eu paro. Desculpe — achei que você fosse querer saber.
    >>  ............................................
  shy.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me.
    >>  ............................................
    pt  Não foi nada pra mim.
    >>  ............................................
  shy.dialogue.conversations.noticed.proud.dismissed/2
    en  Then I'll keep the next one.
    >>  ............................................
    pt  Então guardo o próximo.
    >>  ............................................
  shy.dialogue.conversations.noticed.proud.dismissed/3
    en  Fine.
    >>  ............................................
    pt  Tudo bem.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me! But fine, consider it forgotten with great enthusiasm.
    >>  ............................................
    pt  Não foi nada pra mim! Mas tudo bem, considere esquecido com muito entusiasmo.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.proud.dismissed/2
    en  Then the next one stays in my head, where it will be admired by me alone.
    >>  ............................................
    pt  Então o próximo fica na minha cabeça, onde só eu vou admirá-lo.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.proud.dismissed/3
    en  Twice today somebody has corrected my own feelings. It's becoming a hobby of theirs.
    >>  ............................................
    pt  Duas vezes hoje corrigiram meus sentimentos. Está virando um passatempo.
    >>  ............................................
  witty.dialogue.conversations.noticed.proud.dismissed/1
    en  It wasn't nothing to me! But fine, consider it forgotten with great enthusiasm.
    >>  ............................................
    pt  Não foi nada pra mim! Mas tudo bem, considere esquecido com muito entusiasmo.
    >>  ............................................
  witty.dialogue.conversations.noticed.proud.dismissed/2
    en  Then the next one stays in my head, where it will be admired by me alone.
    >>  ............................................
    pt  Então o próximo fica na minha cabeça, onde só eu vou admirá-lo.
    >>  ............................................
  witty.dialogue.conversations.noticed.proud.dismissed/3
    en  Twice today somebody has corrected my own feelings. It's becoming a hobby of theirs.
    >>  ............................................
    pt  Duas vezes hoje corrigiram meus sentimentos. Está virando um passatempo.
    >>  ............................................
```

</details>


### Button `back` — "Right, then."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `noticed.proud.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.proud.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.proud.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.proud.respond.back   [12 chars]
    en  Right, then.
    >>  ............................................
    pt  Certo, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud.back
WHO    VILLAGER — what the player reads after pressing "Right, then."
       spoken on: conversations.topic.noticed.proud.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.back`: the villager accepts. Subject `noticed.pride`, polarity `positive`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud.back/1   [14 chars]
    en  So I've found.
    >>  ............................................
    pt  Foi o que eu vi.
    >>  ............................................
  dialogue.conversations.noticed.proud.back/2   [6 chars]
    en  Go on.
    >>  ............................................
    pt  Vá lá.
    >>  ............................................
  dialogue.conversations.noticed.proud.back/3   [22 chars]
    en  Mind how you go, %1$s.
    >>  ............................................
    pt  Se cuide, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.worn.followup`

**Reached from 3 route(s):** `conversations.topic.noticed.worn.respond` / `how_long`; `conversations.topic.noticed.worn.respond` / `take_something`; `conversations.topic.noticed.worn.respond` / `no_fixing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.worn.how_long` — e.g. "Since the spring, and I only noticed it in the autumn. That's how it goes."
- `conversations.noticed.worn.no_fixing` — e.g. "...No. But everyone wants one, and I've been inventing them to be polite."
- `conversations.noticed.worn.take_something` — e.g. "The evening rounds, if you're serious. That's the hour that finishes me."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.worn.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.worn.followup   [42 chars]
    en  That's the whole of it, and it isn't much.
    >>  ............................................
    pt  É tudo, e não é muito.
    >>  ............................................
```


### Button `not_alone` — "You don't have to carry it on your own."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.worn.how_long`, `noticed.worn.take_something`, `noticed.worn.no_fixing`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.followup.not_alone` — accepted phrasings: "you do not have to carry it alone"; "you are not on your own"; "let someone else carry some of it"
  - the message must contain one of: `carry`, `alone`
  - scored words: `carry`(1.2), `alone`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.followup.not_alone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.followup.not_alone   [39 chars]
    en  You don't have to carry it on your own.
    >>  ............................................
    pt  Você não precisa carregar isso sozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.worn.followup.not_alone`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +3  _(recorded under topic `noticed.worn.followup.not_alone`)_
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.followup.not_alone
WHO    VILLAGER — what the player reads after pressing "You don't have to carry it on your own."
       spoken on: conversations.topic.noticed.worn.followup, button `not_alone`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.followup.not_alone`: the villager accepts. Subject `noticed.exhaustion`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.followup.not_alone/1   [76 chars]
    en  I've been carrying it on my own so long I'd forgotten there was another way.
    >>  ............................................
    pt  Carreguei sozinho por tanto tempo que esqueci que havia outro jeito.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.not_alone/2   [69 chars]
    en  ...Right. I'll try to remember you said that on the next bad morning.
    >>  ............................................
    pt  ...Certo. Vou tentar lembrar que você disse isso na próxima manhã ruim.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.not_alone/3   [75 chars]
    en  That's the sentence I needed and couldn't have asked anyone for. Thank you.
    >>  ............................................
    pt  É a frase de que eu precisava e que não conseguiria pedir a ninguém. Obrigado.
    >>  ............................................
```


### Button `take_a_day` — "Take a day. I'll cover what needs covering."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `noticed.worn.how_long`, `noticed.worn.take_something`, `noticed.worn.no_fixing`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.followup.day` — accepted phrasings: "take a day i will cover it"; "i will cover your work for a day"; "take a day off and i will manage"
  - the message must contain one of: `cover`
  - scored words: `cover`(1.2), `day`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.followup.take_a_day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.followup.take_a_day   [43 chars]
    en  Take a day. I'll cover what needs covering.
    >>  ............................................
    pt  Tire um dia. Eu cubro o que precisar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `noticed.worn.followup.day`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `noticed.worn.followup.day`)_
- Does: arc `noticed` — advance
- Does: session `turn`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.followup.day
WHO    VILLAGER — what the player reads after pressing "Take a day. I'll cover what needs covering."
       spoken on: conversations.topic.noticed.worn.followup, button `take_a_day`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.followup.day`: the villager accepts. Subject `noticed.exhaustion`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.followup.day/1   [65 chars]
    en  A whole day. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.day/2   [72 chars]
    en  You mean that, don't you. Nobody's offered and I'd stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu e eu tinha parado de esperar.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.day/3   [72 chars]
    en  Then I'll take it, and I'll not spend it usefully, and that's the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, and that frightens me a little.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, e isso me assusta um pouco.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped letting myself want it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de me deixar querer.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. I'll waste it, and I'll feel guilty, and I'll take it anyway.
    >>  ............................................
    pt  Então eu tiro. Vou desperdiçar, vou me sentir culpado, e vou tirar mesmo assim.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I've had four in thirty years and I remember all four.
    >>  ............................................
    pt  Um dia inteiro. Tive quatro em trinta anos e lembro dos quatro.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered since my wife, and she stopped asking.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém oferece desde minha esposa, e ela parou de pedir.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully. That's what a day off is.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil. É isso que é um dia de folga.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered and I'd stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu e eu tinha parado de esperar.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered and I'd stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu e eu tinha parado de esperar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day, %1$s. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro, %1$s. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped expecting anyone would.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de esperar.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point of it.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day, %1$s. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro, %1$s. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped expecting anyone would.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de esperar.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point of it.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day, %1$s. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro, %1$s. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped expecting anyone would.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de esperar.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point of it.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, and that frightens me a little.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, e isso me assusta um pouco.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped letting myself want it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de me deixar querer.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. I'll waste it, and I'll feel guilty, and I'll take it anyway.
    >>  ............................................
    pt  Então eu tiro. Vou desperdiçar, vou me sentir culpado, e vou tirar mesmo assim.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered and I'd stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu e eu tinha parado de esperar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, but I'll find out.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, mas vou descobrir.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered and I'd stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu e eu tinha parado de esperar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that's the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é esse o ponto.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean it. Nobody's offered before.
    >>  ............................................
    pt  Você fala sério. Ninguém ofereceu antes.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. Usefully spent would spoil it.
    >>  ............................................
    pt  Então eu tiro. Gastar com algo útil estragaria.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I've had four in thirty years and I remember all four.
    >>  ............................................
    pt  Um dia inteiro. Tive quatro em trinta anos e lembro dos quatro.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered since my wife, and she stopped asking.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém oferece desde minha esposa, e ela parou de pedir.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully. That's what a day off is.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil. É isso que é um dia de folga.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean it. Nobody's offered before.
    >>  ............................................
    pt  Você fala sério. Ninguém ofereceu antes.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. Usefully spent would spoil it.
    >>  ............................................
    pt  Então eu tiro. Gastar com algo útil estragaria.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I've had four in thirty years and I remember all four.
    >>  ............................................
    pt  Um dia inteiro. Tive quatro em trinta anos e lembro dos quatro.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered since my wife, and she stopped asking.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém oferece desde minha esposa, e ela parou de pedir.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully. That's what a day off is.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil. É isso que é um dia de folga.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day! I'd not know what to do with one, but I intend to find out.
    >>  ............................................
    pt  Um dia inteiro! Eu não saberia o que fazer com um, mas pretendo descobrir.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody has offered, and I'd entirely stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado totalmente de esperar.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that is precisely the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é precisamente esse o ponto.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day! I'd not know what to do with one, but I intend to find out.
    >>  ............................................
    pt  Um dia inteiro! Eu não saberia o que fazer com um, mas pretendo descobrir.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody has offered, and I'd entirely stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado totalmente de esperar.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that is precisely the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é precisamente esse o ponto.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I've had four in thirty years and I remember all four.
    >>  ............................................
    pt  Um dia inteiro. Tive quatro em trinta anos e lembro dos quatro.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered since my wife, and she stopped asking.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém oferece desde minha esposa, e ela parou de pedir.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully. That's what a day off is.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil. É isso que é um dia de folga.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one, and that frightens me a little.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um, e isso me assusta um pouco.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody's offered, and I'd stopped letting myself want it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado de me deixar querer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. I'll waste it, and I'll feel guilty, and I'll take it anyway.
    >>  ............................................
    pt  Então eu tiro. Vou desperdiçar, vou me sentir culpado, e vou tirar mesmo assim.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day. I'd not know what to do with one.
    >>  ............................................
    pt  Um dia inteiro. Eu não saberia o que fazer com um.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean it. Nobody's offered before.
    >>  ............................................
    pt  Você fala sério. Ninguém ofereceu antes.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it. Usefully spent would spoil it.
    >>  ............................................
    pt  Então eu tiro. Gastar com algo útil estragaria.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day! I'd not know what to do with one, but I intend to find out.
    >>  ............................................
    pt  Um dia inteiro! Eu não saberia o que fazer com um, mas pretendo descobrir.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody has offered, and I'd entirely stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado totalmente de esperar.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that is precisely the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é precisamente esse o ponto.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.followup.day/1
    en  A whole day! I'd not know what to do with one, but I intend to find out.
    >>  ............................................
    pt  Um dia inteiro! Eu não saberia o que fazer com um, mas pretendo descobrir.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.followup.day/2
    en  You mean that, don't you. Nobody has offered, and I'd entirely stopped expecting it.
    >>  ............................................
    pt  Você fala sério, não fala. Ninguém ofereceu, e eu tinha parado totalmente de esperar.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.followup.day/3
    en  Then I'll take it, and I'll not spend it usefully, and that is precisely the point.
    >>  ............................................
    pt  Então eu tiro, e não vou gastá-lo com nada útil, e é precisamente esse o ponto.
    >>  ............................................
```

</details>


### Button `leave` — "I'll not keep you standing."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.worn.how_long`, `noticed.worn.take_something`, `noticed.worn.no_fixing` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.followup.leave   [27 chars]
    en  I'll not keep you standing.
    >>  ............................................
    pt  Não vou te deixar de pé.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.followup.leave
WHO    VILLAGER — what the player reads after pressing "I'll not keep you standing."
       spoken on: conversations.topic.noticed.worn.followup, button `leave`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.followup.leave`: the villager accepts. Subject `noticed.exhaustion`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.followup.leave/1   [12 chars]
    en  Kind of you.
    >>  ............................................
    pt  Gentil da sua parte.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.leave/2   [34 chars]
    en  True enough. Thank you for asking.
    >>  ............................................
    pt  Bem verdade. Obrigado por perguntar.
    >>  ............................................
  dialogue.conversations.noticed.worn.followup.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.noticed.worn.respond`

**Reached from 1 route(s):** `conversations.cat.events` / `noticed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.noticed.worn` — e.g. "Tired. Not the kind that sleep fixes, before you offer."


```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.noticed.worn.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.noticed.worn.respond   [62 chars]
    en  You asked, and I've stopped having the energy to lie about it.
    >>  ............................................
    pt  Você perguntou, e eu não tenho mais energia pra mentir sobre isso.
    >>  ............................................
```


### Button `how_long` — "How long has it been like this?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `noticed.worn.open` · offered only once the villager has actually said `noticed:worn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.how_long` — accepted phrasings: "how long has it been like this"; "how long have you felt like that"; "since when has it been this way"
  - the message must contain one of: `since`
  - scored words: `long`(0.6), `been`(0.3), `since`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond.how_long
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.respond.how_long   [31 chars]
    en  How long has it been like this?
    >>  ............................................
    pt  Há quanto tempo está assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.worn.followup`
- …where the player's next choices will be: "You don't have to carry it on your own." | "Take a day. I'll cover what needs covering." | "I'll not keep you standing."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.how_long
WHO    VILLAGER — what the player reads after pressing "How long has it been like this?"
       spoken on: conversations.topic.noticed.worn.respond, button `how_long`
       leaves the player on: conversations.topic.noticed.worn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.how_long`: the villager discloses. Subject `noticed.exhaustion`, polarity `negative`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.how_long/1   [74 chars]
    en  Since the spring, and I only noticed it in the autumn. That's how it goes.
    >>  ............................................
    pt  Desde a primavera, e eu só notei no outono. É assim que funciona.
    >>  ............................................
  dialogue.conversations.noticed.worn.how_long/2   [79 chars]
    en  Long enough that I've stopped calling it a bad patch and started calling it me.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim e começar a chamar de eu.
    >>  ............................................
  dialogue.conversations.noticed.worn.how_long/3   [78 chars]
    en  I couldn't tell you. There wasn't a day it started; it just kept not stopping.
    >>  ............................................
    pt  Eu não saberia dizer. Não teve um dia em que começou; só continuou não parando.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only let myself notice in the autumn. That's the shameful part.
    >>  ............................................
    pt  Desde a primavera, e só me deixei notar no outono. É essa a parte vergonhosa.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I stopped calling it a bad patch. Names make things real.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim. Nomes tornam as coisas reais.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It crept, and I'd rather it had arrived all at once.
    >>  ............................................
    pt  Eu não saberia dizer. Foi se arrastando, e eu preferia que tivesse chegado de uma vez.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. At my age a season goes by before you've looked at it properly.
    >>  ............................................
    pt  Desde a primavera. Na minha idade uma estação passa antes de você olhar direito.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that it isn't a bad patch. Bad patches end; this has settled in.
    >>  ............................................
    pt  Tempo o bastante pra não ser uma fase ruim. Fases acabam; isto se instalou.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. The years stopped having edges some time ago.
    >>  ............................................
    pt  Eu não saberia dizer. Os anos pararam de ter bordas faz tempo.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only noticed it in the autumn.
    >>  ............................................
    pt  Desde a primavera, e eu só notei no outono.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It just kept not stopping.
    >>  ............................................
    pt  Eu não saberia dizer. Só continuou não parando.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only noticed it in the autumn.
    >>  ............................................
    pt  Desde a primavera, e eu só notei no outono.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It just kept not stopping.
    >>  ............................................
    pt  Eu não saberia dizer. Só continuou não parando.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. You're the first to ask, and I'd counted on nobody asking.
    >>  ............................................
    pt  Desde a primavera. Você é o primeiro a perguntar, e eu contava que ninguém perguntasse.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it me.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim e começar a chamar de eu.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you, %1$s. There wasn't a day it started.
    >>  ............................................
    pt  Eu não saberia dizer, %1$s. Não teve um dia em que começou.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. You're the first to ask, and I'd counted on nobody asking.
    >>  ............................................
    pt  Desde a primavera. Você é o primeiro a perguntar, e eu contava que ninguém perguntasse.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it me.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim e começar a chamar de eu.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you, %1$s. There wasn't a day it started.
    >>  ............................................
    pt  Eu não saberia dizer, %1$s. Não teve um dia em que começou.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. You're the first to ask, and I'd counted on nobody asking.
    >>  ............................................
    pt  Desde a primavera. Você é o primeiro a perguntar, e eu contava que ninguém perguntasse.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it me.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim e começar a chamar de eu.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you, %1$s. There wasn't a day it started.
    >>  ............................................
    pt  Eu não saberia dizer, %1$s. Não teve um dia em que começou.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only let myself notice in the autumn. That's the shameful part.
    >>  ............................................
    pt  Desde a primavera, e só me deixei notar no outono. É essa a parte vergonhosa.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I stopped calling it a bad patch. Names make things real.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim. Nomes tornam as coisas reais.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It crept, and I'd rather it had arrived all at once.
    >>  ............................................
    pt  Eu não saberia dizer. Foi se arrastando, e eu preferia que tivesse chegado de uma vez.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only noticed it in the autumn.
    >>  ............................................
    pt  Desde a primavera, e eu só notei no outono.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It just kept not stopping.
    >>  ............................................
    pt  Eu não saberia dizer. Só continuou não parando.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only noticed it in the autumn.
    >>  ............................................
    pt  Desde a primavera, e eu só notei no outono.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It just kept not stopping.
    >>  ............................................
    pt  Eu não saberia dizer. Só continuou não parando.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring.
    >>  ............................................
    pt  Desde a primavera.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.how_long/3
    en  There wasn't a first day.
    >>  ............................................
    pt  Não teve um primeiro dia.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. At my age a season goes by before you've looked at it properly.
    >>  ............................................
    pt  Desde a primavera. Na minha idade uma estação passa antes de você olhar direito.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that it isn't a bad patch. Bad patches end; this has settled in.
    >>  ............................................
    pt  Tempo o bastante pra não ser uma fase ruim. Fases acabam; isto se instalou.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. The years stopped having edges some time ago.
    >>  ............................................
    pt  Eu não saberia dizer. Os anos pararam de ter bordas faz tempo.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring.
    >>  ............................................
    pt  Desde a primavera.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.how_long/3
    en  There wasn't a first day.
    >>  ............................................
    pt  Não teve um primeiro dia.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. At my age a season goes by before you've looked at it properly.
    >>  ............................................
    pt  Desde a primavera. Na minha idade uma estação passa antes de você olhar direito.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that it isn't a bad patch. Bad patches end; this has settled in.
    >>  ............................................
    pt  Tempo o bastante pra não ser uma fase ruim. Fases acabam; isto se instalou.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. The years stopped having edges some time ago.
    >>  ............................................
    pt  Eu não saberia dizer. Os anos pararam de ter bordas faz tempo.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.how_long/1
    en  Since spring! I noticed in autumn, which shows what a keen self-observer I am.
    >>  ............................................
    pt  Desde a primavera! Notei no outono, o que mostra que ótimo auto-observador eu sou.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it a wing.
    >>  ............................................
    pt  Tempo o bastante pra parar de chamar de fase ruim e começar a chamar de ala.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.how_long/3
    en  Couldn't tell you. There was no first day. It simply declined to stop.
    >>  ............................................
    pt  Não saberia dizer. Não teve primeiro dia. Simplesmente se recusou a parar.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.how_long/1
    en  Since spring! I noticed in autumn, which shows what a keen self-observer I am.
    >>  ............................................
    pt  Desde a primavera! Notei no outono, o que mostra que ótimo auto-observador eu sou.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it a wing.
    >>  ............................................
    pt  Tempo o bastante pra parar de chamar de fase ruim e começar a chamar de ala.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.how_long/3
    en  Couldn't tell you. There was no first day. It simply declined to stop.
    >>  ............................................
    pt  Não saberia dizer. Não teve primeiro dia. Simplesmente se recusou a parar.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring. At my age a season goes by before you've looked at it properly.
    >>  ............................................
    pt  Desde a primavera. Na minha idade uma estação passa antes de você olhar direito.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that it isn't a bad patch. Bad patches end; this has settled in.
    >>  ............................................
    pt  Tempo o bastante pra não ser uma fase ruim. Fases acabam; isto se instalou.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. The years stopped having edges some time ago.
    >>  ............................................
    pt  Eu não saberia dizer. Os anos pararam de ter bordas faz tempo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring, and I only let myself notice in the autumn. That's the shameful part.
    >>  ............................................
    pt  Desde a primavera, e só me deixei notar no outono. É essa a parte vergonhosa.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I stopped calling it a bad patch. Names make things real.
    >>  ............................................
    pt  Tempo o bastante pra eu parar de chamar de fase ruim. Nomes tornam as coisas reais.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.how_long/3
    en  I couldn't tell you. It crept, and I'd rather it had arrived all at once.
    >>  ............................................
    pt  Eu não saberia dizer. Foi se arrastando, e eu preferia que tivesse chegado de uma vez.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.how_long/1
    en  Since the spring.
    >>  ............................................
    pt  Desde a primavera.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough.
    >>  ............................................
    pt  Tempo o bastante.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.how_long/3
    en  There wasn't a first day.
    >>  ............................................
    pt  Não teve um primeiro dia.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.how_long/1
    en  Since spring! I noticed in autumn, which shows what a keen self-observer I am.
    >>  ............................................
    pt  Desde a primavera! Notei no outono, o que mostra que ótimo auto-observador eu sou.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it a wing.
    >>  ............................................
    pt  Tempo o bastante pra parar de chamar de fase ruim e começar a chamar de ala.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.how_long/3
    en  Couldn't tell you. There was no first day. It simply declined to stop.
    >>  ............................................
    pt  Não saberia dizer. Não teve primeiro dia. Simplesmente se recusou a parar.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.how_long/1
    en  Since spring! I noticed in autumn, which shows what a keen self-observer I am.
    >>  ............................................
    pt  Desde a primavera! Notei no outono, o que mostra que ótimo auto-observador eu sou.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.how_long/2
    en  Long enough that I've stopped calling it a bad patch and started calling it a wing.
    >>  ............................................
    pt  Tempo o bastante pra parar de chamar de fase ruim e começar a chamar de ala.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.how_long/3
    en  Couldn't tell you. There was no first day. It simply declined to stop.
    >>  ............................................
    pt  Não saberia dizer. Não teve primeiro dia. Simplesmente se recusou a parar.
    >>  ............................................
```

</details>


### Button `take_something` — "Is there something I could take off you?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `noticed.worn.open` · offered only once the villager has actually said `noticed:worn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.take_something` — accepted phrasings: "is there something i could take off you"; "can i take some of the load"; "what can i take off your hands"
  - the message must contain one of: `load`
  - scored words: `off`(0.4), `take`(0.6), `load`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond.take_something
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.respond.take_something   [40 chars]
    en  Is there something I could take off you?
    >>  ............................................
    pt  Tem alguma coisa que eu possa tirar de você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.noticed.worn.followup`
- …where the player's next choices will be: "You don't have to carry it on your own." | "Take a day. I'll cover what needs covering." | "I'll not keep you standing."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.take_something
WHO    VILLAGER — what the player reads after pressing "Is there something I could take off you?"
       spoken on: conversations.topic.noticed.worn.respond, button `take_something`
       leaves the player on: conversations.topic.noticed.worn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.take_something`: the villager request_helps. Subject `noticed.exhaustion`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.take_something/1   [72 chars]
    en  The evening rounds, if you're serious. That's the hour that finishes me.
    >>  ............................................
    pt  A ronda da noite, se estiver falando sério. É a hora que me acaba.
    >>  ............................................
  dialogue.conversations.noticed.worn.take_something/2   [76 chars]
    en  Nothing you could lift. It's not the weight, it's that there's no end to it.
    >>  ............................................
    pt  Nada que você pudesse levantar. Não é o peso, é que não tem fim.
    >>  ............................................
  dialogue.conversations.noticed.worn.take_something/3   [74 chars]
    en  The asking helps more than the taking would, which I didn't expect to say.
    >>  ............................................
    pt  Perguntar ajuda mais do que tirar ajudaria, o que eu não esperava dizer.
    >>  ............................................
```


### Button `no_fixing` — "You don't have to have a reason for it."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `noticed.worn.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.no_fixing` — accepted phrasings: "you do not have to have a reason"; "you do not need to explain it"; "it does not need a cause"
  - the message must contain one of: `reason`, `explain`
  - scored words: `reason`(1.5), `explain`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond.no_fixing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.respond.no_fixing   [39 chars]
    en  You don't have to have a reason for it.
    >>  ............................................
    pt  Você não precisa ter um motivo pra isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `noticed.worn.no_fixing`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `noticed.worn.no_fixing`)_
- Does: session `turn`
- Then opens: `conversations.topic.noticed.worn.followup`
- …where the player's next choices will be: "You don't have to carry it on your own." | "Take a day. I'll cover what needs covering." | "I'll not keep you standing."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.no_fixing
WHO    VILLAGER — what the player reads after pressing "You don't have to have a reason for it."
       spoken on: conversations.topic.noticed.worn.respond, button `no_fixing`
       leaves the player on: conversations.topic.noticed.worn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.no_fixing`: the villager accepts. Subject `noticed.exhaustion`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.no_fixing/1   [73 chars]
    en  ...No. But everyone wants one, and I've been inventing them to be polite.
    >>  ............................................
    pt  ...Não. Mas todos querem um, e eu venho inventando por educação.
    >>  ............................................
  dialogue.conversations.noticed.worn.no_fixing/2   [67 chars]
    en  That's the first time this month somebody hasn't tried to solve me.
    >>  ............................................
    pt  É a primeira vez neste mês que alguém não tentou me resolver.
    >>  ............................................
  dialogue.conversations.noticed.worn.no_fixing/3   [65 chars]
    en  Then I'll stop hunting for one. It's tiring work on top of tired.
    >>  ............................................
    pt  Então eu paro de procurar um. É um trabalho cansativo em cima de cansado.
    >>  ............................................
```


### Button `dismiss` — "Everyone's tired. Get on with it."

*stance family `dismissal` · tone `blunt` · outcome `hurt` · answers the beat(s) `noticed.worn.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `noticed.worn.dismissed` — accepted phrasings: "everyone is tired get on with it"; "we are all tired"; "just push through it"
  - the message must contain one of: `everyone`
  - scored words: `tired`(0.6), `everyone`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.respond.dismiss   [33 chars]
    en  Everyone's tired. Get on with it.
    >>  ............................................
    pt  Todo mundo está cansado. Siga em frente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `noticed.worn.dismiss`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, warmth -3  _(recorded under topic `noticed.worn.dismissed`)_
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.dismissed
WHO    VILLAGER — what the player reads after pressing "Everyone's tired. Get on with it."
       spoken on: conversations.topic.noticed.worn.respond, button `dismiss`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.dismissed`: the villager hurts. Subject `noticed.exhaustion`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.dismissed/1   [58 chars]
    en  I have been getting on with it. That's the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente. É esse o problema inteiro.
    >>  ............................................
  dialogue.conversations.noticed.worn.dismissed/2   [64 chars]
    en  Right. That's why I said fine to the last four people who asked.
    >>  ............................................
    pt  Certo. Por isso eu disse 'bem' para as últimas quatro pessoas que perguntaram.
    >>  ............................................
  dialogue.conversations.noticed.worn.dismissed/3   [47 chars]
    en  Then I'll go and be tired somewhere you're not.
    >>  ............................................
    pt  Então vou ficar cansado em algum lugar onde você não esteja.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.worn.dismissed/1
    en  ...I have been getting on with it. Every day. That's the whole of what's wrong.
    >>  ............................................
    pt  ...Eu venho seguindo em frente. Todo dia. É tudo que está errado.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I should have given you the same and saved us both.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Devia ter te dado o mesmo e poupado nós dois.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go. I'm sorry I said anything — I don't know why I did.
    >>  ............................................
    pt  Então eu vou. Desculpe ter dito qualquer coisa — nem sei por que disse.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it for forty years. That is precisely the problem.
    >>  ............................................
    pt  Eu venho seguindo em frente há quarenta anos. É precisamente o problema.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why four people got 'fine' this week and slept soundly afterwards.
    >>  ............................................
    pt  Certo. Por isso quatro pessoas ouviram 'bem' esta semana e dormiram tranquilas.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired elsewhere. I've outlasted worse advice than that.
    >>  ............................................
    pt  Então fico cansado em outro lugar. Já sobrevivi a conselhos piores.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. That's the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente. É esse o problema inteiro.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why I said fine to the last four people who asked.
    >>  ............................................
    pt  Certo. Por isso eu disse 'bem' às últimas quatro pessoas que perguntaram.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go and be tired somewhere you're not.
    >>  ............................................
    pt  Então vou ficar cansado em algum lugar onde você não esteja.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. That's the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente. É esse o problema inteiro.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why I said fine to the last four people who asked.
    >>  ............................................
    pt  Certo. Por isso eu disse 'bem' às últimas quatro pessoas que perguntaram.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go and be tired somewhere you're not.
    >>  ............................................
    pt  Então vou ficar cansado em algum lugar onde você não esteja.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. From you I'd hoped for a different sentence.
    >>  ............................................
    pt  Eu venho seguindo em frente. De você eu esperava outra frase.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I gave you the true one and this is what it bought.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Eu te dei a verdadeira e foi isso que ela comprou.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else. I'd rather have stayed here, but there it is.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar. Preferia ter ficado aqui, mas é assim.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. From you I'd hoped for a different sentence.
    >>  ............................................
    pt  Eu venho seguindo em frente. De você eu esperava outra frase.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I gave you the true one and this is what it bought.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Eu te dei a verdadeira e foi isso que ela comprou.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else. I'd rather have stayed here, but there it is.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar. Preferia ter ficado aqui, mas é assim.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. From you I'd hoped for a different sentence.
    >>  ............................................
    pt  Eu venho seguindo em frente. De você eu esperava outra frase.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I gave you the true one and this is what it bought.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Eu te dei a verdadeira e foi isso que ela comprou.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else. I'd rather have stayed here, but there it is.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar. Preferia ter ficado aqui, mas é assim.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.dismissed/1
    en  ...I have been getting on with it. Every day. That's the whole of what's wrong.
    >>  ............................................
    pt  ...Eu venho seguindo em frente. Todo dia. É tudo que está errado.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I should have given you the same and saved us both.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Devia ter te dado o mesmo e poupado nós dois.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go. I'm sorry I said anything — I don't know why I did.
    >>  ............................................
    pt  Então eu vou. Desculpe ter dito qualquer coisa — nem sei por que disse.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. That's the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente. É esse o problema inteiro.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why I said fine to the last four people who asked.
    >>  ............................................
    pt  Certo. Por isso eu disse 'bem' às últimas quatro pessoas que perguntaram.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go and be tired somewhere you're not.
    >>  ............................................
    pt  Então vou ficar cansado em algum lugar onde você não esteja.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it. That's the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente. É esse o problema inteiro.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why I said fine to the last four people who asked.
    >>  ............................................
    pt  Certo. Por isso eu disse 'bem' às últimas quatro pessoas que perguntaram.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go and be tired somewhere you're not.
    >>  ............................................
    pt  Então vou ficar cansado em algum lugar onde você não esteja.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it.
    >>  ............................................
    pt  Eu venho seguindo em frente.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.dismissed/2
    en  That's why the others got 'fine'.
    >>  ............................................
    pt  Por isso os outros ouviram 'bem'.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go.
    >>  ............................................
    pt  Então eu vou.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it for forty years. That is precisely the problem.
    >>  ............................................
    pt  Eu venho seguindo em frente há quarenta anos. É precisamente o problema.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why four people got 'fine' this week and slept soundly afterwards.
    >>  ............................................
    pt  Certo. Por isso quatro pessoas ouviram 'bem' esta semana e dormiram tranquilas.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired elsewhere. I've outlasted worse advice than that.
    >>  ............................................
    pt  Então fico cansado em outro lugar. Já sobrevivi a conselhos piores.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it.
    >>  ............................................
    pt  Eu venho seguindo em frente.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.dismissed/2
    en  That's why the others got 'fine'.
    >>  ............................................
    pt  Por isso os outros ouviram 'bem'.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go.
    >>  ............................................
    pt  Então eu vou.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it for forty years. That is precisely the problem.
    >>  ............................................
    pt  Eu venho seguindo em frente há quarenta anos. É precisamente o problema.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why four people got 'fine' this week and slept soundly afterwards.
    >>  ............................................
    pt  Certo. Por isso quatro pessoas ouviram 'bem' esta semana e dormiram tranquilas.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired elsewhere. I've outlasted worse advice than that.
    >>  ............................................
    pt  Então fico cansado em outro lugar. Já sobrevivi a conselhos piores.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it! Tirelessly. That is, in fact, the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente! Incansavelmente. Isso, na verdade, é o problema inteiro.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. Which is why the last four people got a cheerful 'fine' and went away happy.
    >>  ............................................
    pt  Certo. Por isso as últimas quatro pessoas ouviram um alegre 'bem' e foram embora felizes.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else, where the company is worse but quieter.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar, com companhia pior mas mais quieta.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it! Tirelessly. That is, in fact, the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente! Incansavelmente. Isso, na verdade, é o problema inteiro.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. Which is why the last four people got a cheerful 'fine' and went away happy.
    >>  ............................................
    pt  Certo. Por isso as últimas quatro pessoas ouviram um alegre 'bem' e foram embora felizes.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else, where the company is worse but quieter.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar, com companhia pior mas mais quieta.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it for forty years. That is precisely the problem.
    >>  ............................................
    pt  Eu venho seguindo em frente há quarenta anos. É precisamente o problema.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. That's why four people got 'fine' this week and slept soundly afterwards.
    >>  ............................................
    pt  Certo. Por isso quatro pessoas ouviram 'bem' esta semana e dormiram tranquilas.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired elsewhere. I've outlasted worse advice than that.
    >>  ............................................
    pt  Então fico cansado em outro lugar. Já sobrevivi a conselhos piores.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.dismissed/1
    en  ...I have been getting on with it. Every day. That's the whole of what's wrong.
    >>  ............................................
    pt  ...Eu venho seguindo em frente. Todo dia. É tudo que está errado.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. The last four got 'fine'. I should have given you the same and saved us both.
    >>  ............................................
    pt  Certo. As últimas quatro ouviram 'bem'. Devia ter te dado o mesmo e poupado nós dois.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go. I'm sorry I said anything — I don't know why I did.
    >>  ............................................
    pt  Então eu vou. Desculpe ter dito qualquer coisa — nem sei por que disse.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it.
    >>  ............................................
    pt  Eu venho seguindo em frente.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.dismissed/2
    en  That's why the others got 'fine'.
    >>  ............................................
    pt  Por isso os outros ouviram 'bem'.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll go.
    >>  ............................................
    pt  Então eu vou.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it! Tirelessly. That is, in fact, the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente! Incansavelmente. Isso, na verdade, é o problema inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. Which is why the last four people got a cheerful 'fine' and went away happy.
    >>  ............................................
    pt  Certo. Por isso as últimas quatro pessoas ouviram um alegre 'bem' e foram embora felizes.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else, where the company is worse but quieter.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar, com companhia pior mas mais quieta.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.dismissed/1
    en  I have been getting on with it! Tirelessly. That is, in fact, the entire problem.
    >>  ............................................
    pt  Eu venho seguindo em frente! Incansavelmente. Isso, na verdade, é o problema inteiro.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.dismissed/2
    en  Right. Which is why the last four people got a cheerful 'fine' and went away happy.
    >>  ............................................
    pt  Certo. Por isso as últimas quatro pessoas ouviram um alegre 'bem' e foram embora felizes.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn.dismissed/3
    en  Then I'll be tired somewhere else, where the company is worse but quieter.
    >>  ............................................
    pt  Então vou ficar cansado em outro lugar, com companhia pior mas mais quieta.
    >>  ............................................
```

</details>


### Button `back` — "I'll leave it there."

*stance family `exit` · tone `gentle` · outcome `conversation_ended` · answers the beat(s) `noticed.worn.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.noticed.worn.respond.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.noticed.worn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.noticed.worn.respond.back   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn.back
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.noticed.worn.respond, button `back`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.back`: the villager accepts. Subject `noticed.exhaustion`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn.back/1   [38 chars]
    en  So it is. Thank you for asking at all.
    >>  ............................................
    pt  É assim mesmo. Obrigado por ter perguntado.
    >>  ............................................
  dialogue.conversations.noticed.worn.back/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.noticed.worn.back/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---

