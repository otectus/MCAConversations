# Topic: us

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.arc.us.resume.close`](#conversations-arc-us-resume-close)
- [`conversations.arc.us.resume.followup`](#conversations-arc-us-resume-followup)
- [`conversations.arc.us.resume.respond`](#conversations-arc-us-resume-respond)
- [`conversations.topic.us.close`](#conversations-topic-us-close)
- [`conversations.topic.us.hurt.close`](#conversations-topic-us-hurt-close)
- [`conversations.us`](#conversations-us)

---

## `conversations.arc.us.resume.close`

**Reached from 2 route(s):** `conversations.arc.us.resume.followup` / `name_a_step`; `conversations.arc.us.resume.followup` / `let_it_rest`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.resume.let_it_rest` — e.g. "...Rest. Aye. Not everything wants deciding this month."
- `conversations.us.resume.name_a_step` — e.g. "...One thing. Right. That's the first useful sentence either of us has managed."


```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.us.resume.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.us.resume.close   [33 chars]
    en  That's it settled, then. For now.
    >>  ............................................
    pt  Está resolvido, então. Por ora.
    >>  ............................................
```


### Button `say_it_matters` — "For what it's worth — this mattered."

*stance family `encouragement` · tone `plain` · answers the beat(s) `us.resume.name_a_step`, `us.resume.let_it_rest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.say_it_matters` — accepted phrasings: "for what it is worth this mattered"; "this mattered to me"; "i am glad we talked about it"
  - the message must contain one of: `mattered`, `this`, `worth`
  - scored words: `mattered`(1.5), `this`(0.3), `worth`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.close.say_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.close.say_it_matters   [36 chars]
    en  For what it's worth — this mattered.
    >>  ............................................
    pt  Pelo que vale — isso importou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.resume.say_it_matters`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `us.resume.say_it_matters`)_
- Does: arc `us` — advance to stage 3
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.resume.say_it_matters
WHO    VILLAGER — what the player reads after pressing "For what it's worth — this mattered."
       spoken on: conversations.arc.us.resume.close, button `say_it_matters`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.say_it_matters`: the villager accepts. Subject `future.revisit`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.say_it_matters/1   [71 chars]
    en  ...It mattered. Right. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo. Vou carregar essa frase por uma semana.
    >>  ............................................
  dialogue.conversations.us.resume.say_it_matters/2   [48 chars]
    en  It did. And you saying so is most of why it did.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão de ter importado.
    >>  ............................................
  dialogue.conversations.us.resume.say_it_matters/3   [55 chars]
    en  That's the part I'll remember, not whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar, não o que a gente decidiu.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, and I'd not have known that until now.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, e eu não saberia disso até agora.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Give me a moment — I'd not expected the evening to end here.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Me dê um momento — eu não esperava a noite terminar aqui.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that one about for rather longer than a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa por bem mais que uma semana.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why. These are the parts that last.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. São essas as partes que duram.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. In ten years it'll be the only part I remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Em dez anos vai ser a única parte que eu lembro.
    >>  ............................................
  confident.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa frase por uma semana.
    >>  ............................................
  confident.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  confident.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember, not whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar, não o que a gente decidiu.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa frase por uma semana.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember, not whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar, não o que a gente decidiu.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did. Thank you for saying it out loud.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. Obrigado por dizer em voz alta.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided — this.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu — isto.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did. Thank you for saying it out loud.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. Obrigado por dizer em voz alta.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided — this.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu — isto.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did. Thank you for saying it out loud.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. Obrigado por dizer em voz alta.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided — this.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu — isto.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, and I'd not have known that until now.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, e eu não saberia disso até agora.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Give me a moment — I'd not expected the evening to end here.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Me dê um momento — eu não esperava a noite terminar aqui.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa frase por uma semana.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember, not whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar, não o que a gente decidiu.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa frase por uma semana.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember, not whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar, não o que a gente decidiu.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right.
    >>  ............................................
    pt  ...Importou. Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that one about for rather longer than a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa por bem mais que uma semana.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why. These are the parts that last.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. São essas as partes que duram.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. In ten years it'll be the only part I remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Em dez anos vai ser a única parte que eu lembro.
    >>  ............................................
  odd.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right.
    >>  ............................................
    pt  ...Importou. Certo.
    >>  ............................................
  odd.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  odd.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that one about for rather longer than a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa por bem mais que uma semana.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why. These are the parts that last.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. São essas as partes que duram.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. In ten years it'll be the only part I remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Em dez anos vai ser a única parte que eu lembro.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered! Right. I'll be carrying that sentence about for a week, possibly two.
    >>  ............................................
    pt  Importou! Certo. Vou carregar essa frase por uma semana, talvez duas.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, which is a neat trick.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, o que é um truque elegante.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided. Sorry to whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu. Desculpe, o que a gente decidiu.
    >>  ............................................
  playful.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered! Right. I'll be carrying that sentence about for a week, possibly two.
    >>  ............................................
    pt  Importou! Certo. Vou carregar essa frase por uma semana, talvez duas.
    >>  ............................................
  playful.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, which is a neat trick.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, o que é um truque elegante.
    >>  ............................................
  playful.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided. Sorry to whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu. Desculpe, o que a gente decidiu.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered. Right. I'll be carrying that one about for rather longer than a week.
    >>  ............................................
    pt  Importou. Certo. Vou carregar essa por bem mais que uma semana.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why. These are the parts that last.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão. São essas as partes que duram.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. In ten years it'll be the only part I remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Em dez anos vai ser a única parte que eu lembro.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right, %1$s. I'll be carrying that sentence about for a week.
    >>  ............................................
    pt  ...Importou. Certo, %1$s. Vou carregar essa frase por uma semana.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, and I'd not have known that until now.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, e eu não saberia disso até agora.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Give me a moment — I'd not expected the evening to end here.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Me dê um momento — eu não esperava a noite terminar aqui.
    >>  ............................................
  shy.dialogue.conversations.us.resume.say_it_matters/1
    en  ...It mattered. Right.
    >>  ............................................
    pt  ...Importou. Certo.
    >>  ............................................
  shy.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão.
    >>  ............................................
  shy.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember.
    >>  ............................................
    pt  É a parte que eu vou lembrar.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered! Right. I'll be carrying that sentence about for a week, possibly two.
    >>  ............................................
    pt  Importou! Certo. Vou carregar essa frase por uma semana, talvez duas.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, which is a neat trick.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, o que é um truque elegante.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided. Sorry to whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu. Desculpe, o que a gente decidiu.
    >>  ............................................
  witty.dialogue.conversations.us.resume.say_it_matters/1
    en  It mattered! Right. I'll be carrying that sentence about for a week, possibly two.
    >>  ............................................
    pt  Importou! Certo. Vou carregar essa frase por uma semana, talvez duas.
    >>  ............................................
  witty.dialogue.conversations.us.resume.say_it_matters/2
    en  It did. And you saying so is most of why it did, which is a neat trick.
    >>  ............................................
    pt  Importou. E você dizer isso é quase toda a razão, o que é um truque elegante.
    >>  ............................................
  witty.dialogue.conversations.us.resume.say_it_matters/3
    en  That's the part I'll remember. Not whatever we decided. Sorry to whatever we decided.
    >>  ............................................
    pt  É a parte que eu vou lembrar. Não o que a gente decidiu. Desculpe, o que a gente decidiu.
    >>  ............................................
```

</details>


### Button `ask_again_later` — "Can I ask you again in a month?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.resume.name_a_step`, `us.resume.let_it_rest`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.ask_again_later` — accepted phrasings: "can i ask you again in a month"; "shall i ask again later"; "i will check back with you"
  - the message must contain one of: `again`, `ask`, `month`
  - scored words: `again`(1.2), `ask`(0.6), `month`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.close.ask_again_later
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.close.ask_again_later   [31 chars]
    en  Can I ask you again in a month?
    >>  ............................................
    pt  Posso te perguntar de novo em um mês?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `us.resume.ask_again_later`)_
- Does: arc `us` — advance to stage 3
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.resume.ask_again_later
WHO    VILLAGER — what the player reads after pressing "Can I ask you again in a month?"
       spoken on: conversations.arc.us.resume.close, button `ask_again_later`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.ask_again_later`: the villager accepts. Subject `future.revisit`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.ask_again_later/1   [58 chars]
    en  ...In a month. Yes. Put it somewhere you'll actually look.
    >>  ............................................
    pt  ...Em um mês. Sim. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  dialogue.conversations.us.resume.ask_again_later/2   [63 chars]
    en  Ask me. I'd rather be asked than left to raise it myself again.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar de novo sozinho.
    >>  ............................................
  dialogue.conversations.us.resume.ask_again_later/3   [68 chars]
    en  A month. Right. I'll have something better to say by then, probably.
    >>  ............................................
    pt  Um mês. Certo. Provavelmente vou ter algo melhor a dizer até lá.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look, because I won't remind you.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote onde você olhe de verdade, porque eu não vou lembrar você.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. If you don't, I'll not raise it, and then it quietly stops being a thing.
    >>  ............................................
    pt  Me pergunte. Se não perguntar, eu não levanto, e aí calado deixa de ser algo.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll spend the month half hoping and half telling myself not to.
    >>  ............................................
    pt  Um mês. Certo. Vou passar o mês metade esperando e metade me dizendo pra não esperar.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Or two. It'll keep either way.
    >>  ............................................
    pt  Em um mês. Sim. Ou dois. Espera de todo jeito.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me when you think of it. These things don't run to a schedule.
    >>  ............................................
    pt  Me pergunte quando lembrar. Essas coisas não seguem cronograma.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. There'll be more to say by then, or there won't, and both are fine.
    >>  ............................................
    pt  Um mês. Certo. Até lá vai ter mais a dizer, ou não, e tanto faz.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Put it somewhere you'll actually look.
    >>  ............................................
    pt  Em um mês. Sim. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar de novo sozinho.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Put it somewhere you'll actually look.
    >>  ............................................
    pt  Em um mês. Sim. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar de novo sozinho.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again, and you know I would.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar sozinho, e você sabe que eu levantaria.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'd like that. I'd like it more than I'd have admitted a year ago.
    >>  ............................................
    pt  Um mês. Certo. Eu gostaria. Mais do que eu admitiria um ano atrás.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again, and you know I would.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar sozinho, e você sabe que eu levantaria.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'd like that. I'd like it more than I'd have admitted a year ago.
    >>  ............................................
    pt  Um mês. Certo. Eu gostaria. Mais do que eu admitiria um ano atrás.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again, and you know I would.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar sozinho, e você sabe que eu levantaria.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'd like that. I'd like it more than I'd have admitted a year ago.
    >>  ............................................
    pt  Um mês. Certo. Eu gostaria. Mais do que eu admitiria um ano atrás.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look, because I won't remind you.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote onde você olhe de verdade, porque eu não vou lembrar você.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. If you don't, I'll not raise it, and then it quietly stops being a thing.
    >>  ............................................
    pt  Me pergunte. Se não perguntar, eu não levanto, e aí calado deixa de ser algo.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll spend the month half hoping and half telling myself not to.
    >>  ............................................
    pt  Um mês. Certo. Vou passar o mês metade esperando e metade me dizendo pra não esperar.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Put it somewhere you'll actually look.
    >>  ............................................
    pt  Em um mês. Sim. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar de novo sozinho.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Put it somewhere you'll actually look.
    >>  ............................................
    pt  Em um mês. Sim. Anote em algum lugar que você olhe de verdade.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked than left to raise it myself again.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado a ter que levantar de novo sozinho.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes.
    >>  ............................................
    pt  ...Em um mês. Sim.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right.
    >>  ............................................
    pt  Um mês. Certo.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Or two. It'll keep either way.
    >>  ............................................
    pt  Em um mês. Sim. Ou dois. Espera de todo jeito.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me when you think of it. These things don't run to a schedule.
    >>  ............................................
    pt  Me pergunte quando lembrar. Essas coisas não seguem cronograma.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. There'll be more to say by then, or there won't, and both are fine.
    >>  ............................................
    pt  Um mês. Certo. Até lá vai ter mais a dizer, ou não, e tanto faz.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes.
    >>  ............................................
    pt  ...Em um mês. Sim.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right.
    >>  ............................................
    pt  Um mês. Certo.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Or two. It'll keep either way.
    >>  ............................................
    pt  Em um mês. Sim. Ou dois. Espera de todo jeito.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me when you think of it. These things don't run to a schedule.
    >>  ............................................
    pt  Me pergunte quando lembrar. Essas coisas não seguem cronograma.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. There'll be more to say by then, or there won't, and both are fine.
    >>  ............................................
    pt  Um mês. Certo. Até lá vai ter mais a dizer, ou não, e tanto faz.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month! Yes. Put it somewhere you'll actually look, not the usual place.
    >>  ............................................
    pt  Em um mês! Sim. Anote em algum lugar que você olhe de verdade, não o de sempre.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me! I'd much rather be asked than have to raise it myself a third time.
    >>  ............................................
    pt  Me pergunte! Prefiro muito ser perguntado a ter que levantar sozinho uma terceira vez.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then. Probably.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer. Provavelmente.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month! Yes. Put it somewhere you'll actually look, not the usual place.
    >>  ............................................
    pt  Em um mês! Sim. Anote em algum lugar que você olhe de verdade, não o de sempre.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me! I'd much rather be asked than have to raise it myself a third time.
    >>  ............................................
    pt  Me pergunte! Prefiro muito ser perguntado a ter que levantar sozinho uma terceira vez.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then. Probably.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer. Provavelmente.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month. Yes. Or two. It'll keep either way.
    >>  ............................................
    pt  Em um mês. Sim. Ou dois. Espera de todo jeito.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me when you think of it. These things don't run to a schedule.
    >>  ............................................
    pt  Me pergunte quando lembrar. Essas coisas não seguem cronograma.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. There'll be more to say by then, or there won't, and both are fine.
    >>  ............................................
    pt  Um mês. Certo. Até lá vai ter mais a dizer, ou não, e tanto faz.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes, %1$s. Put it somewhere you'll actually look, because I won't remind you.
    >>  ............................................
    pt  ...Em um mês. Sim, %1$s. Anote onde você olhe de verdade, porque eu não vou lembrar você.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. If you don't, I'll not raise it, and then it quietly stops being a thing.
    >>  ............................................
    pt  Me pergunte. Se não perguntar, eu não levanto, e aí calado deixa de ser algo.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll spend the month half hoping and half telling myself not to.
    >>  ............................................
    pt  Um mês. Certo. Vou passar o mês metade esperando e metade me dizendo pra não esperar.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_again_later/1
    en  ...In a month. Yes.
    >>  ............................................
    pt  ...Em um mês. Sim.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me. I'd rather be asked.
    >>  ............................................
    pt  Me pergunte. Prefiro ser perguntado.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right.
    >>  ............................................
    pt  Um mês. Certo.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month! Yes. Put it somewhere you'll actually look, not the usual place.
    >>  ............................................
    pt  Em um mês! Sim. Anote em algum lugar que você olhe de verdade, não o de sempre.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me! I'd much rather be asked than have to raise it myself a third time.
    >>  ............................................
    pt  Me pergunte! Prefiro muito ser perguntado a ter que levantar sozinho uma terceira vez.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then. Probably.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer. Provavelmente.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_again_later/1
    en  In a month! Yes. Put it somewhere you'll actually look, not the usual place.
    >>  ............................................
    pt  Em um mês! Sim. Anote em algum lugar que você olhe de verdade, não o de sempre.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_again_later/2
    en  Ask me! I'd much rather be asked than have to raise it myself a third time.
    >>  ............................................
    pt  Me pergunte! Prefiro muito ser perguntado a ter que levantar sozinho uma terceira vez.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_again_later/3
    en  A month. Right. I'll have something better to say by then. Probably.
    >>  ............................................
    pt  Um mês. Certo. Até lá eu vou ter algo melhor a dizer. Provavelmente.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it with you."

*stance family `exit` · tone `plain` · answers the beat(s) `us.resume.name_a_step`, `us.resume.let_it_rest` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.close.leave   [23 chars]
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
POOL   dialogue key: dialogue.conversations.us.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.us.resume.close, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.leave`: the villager accepts. Subject `future.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.us.resume.followup / leave; conversations.arc.us.resume.respond / leave
```

```text
  dialogue.conversations.us.resume.leave/1   [51 chars]
    en  Right. It's left with me, then. That's not nothing.
    >>  ............................................
    pt  Certo. Fica comigo, então. Não é nada.
    >>  ............................................
  dialogue.conversations.us.resume.leave/2   [48 chars]
    en  Fine. I'll be here when you've thought about it.
    >>  ............................................
    pt  Tudo bem. Vou estar aqui quando você tiver pensado.
    >>  ............................................
  dialogue.conversations.us.resume.leave/3   [49 chars]
    en  Off you go. I'll not raise it again until you do.
    >>  ............................................
    pt  Pode ir. Não levanto de novo até você levantar.
    >>  ............................................
```

---


## `conversations.arc.us.resume.followup`

**Reached from 3 route(s):** `conversations.arc.us.resume.respond` / `ask_where_it_stands`; `conversations.arc.us.resume.respond` / `hold_to_it`; `conversations.arc.us.resume.respond` / `admit_drift`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.resume.admit_drift` — e.g. "...Honestly. Right. That's worth more than a better answer would have been."
- `conversations.us.resume.ask_where_it_stands` — e.g. "Further than I'd have said out loud a month ago. That's as much as I've got."
- `conversations.us.resume.hold_to_it` — e.g. "...You still do. Right. I'd been holding my breath about that for a fortnight."


```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.us.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.us.resume.followup   [26 chars]
    en  Very well. What now, then?
    >>  ............................................
    pt  Muito bem. E agora?
    >>  ............................................
```


### Button `name_a_step` — "One thing. Small enough to actually happen."

*stance family `practical_help` · tone `plain` · answers the beat(s) `us.resume.ask_where_it_stands`, `us.resume.hold_to_it`, `us.resume.admit_drift`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.name_a_step` — accepted phrasings: "one small step"; "name one thing"; "something small enough to happen"
  - the message must contain one of: `small`, `step`, `thing`
  - scored words: `small`(1.2), `step`(1.5), `thing`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.followup.name_a_step
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.followup.name_a_step   [43 chars]
    en  One thing. Small enough to actually happen.
    >>  ............................................
    pt  Uma coisa. Pequena o bastante pra acontecer de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.resume.name_a_step`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +4, respect +3  _(recorded under topic `us.resume.name_a_step`)_
- Does: arc `us` — advance to stage 3
- Then opens: `conversations.arc.us.resume.close`
- …where the player's next choices will be: "For what it's worth — this mattered." | "Can I ask you again in a month?" | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.resume.name_a_step
WHO    VILLAGER — what the player reads after pressing "One thing. Small enough to actually happen."
       spoken on: conversations.arc.us.resume.followup, button `name_a_step`
       leaves the player on: conversations.arc.us.resume.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.name_a_step`: the villager accepts. Subject `future.revisit`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.name_a_step/1   [79 chars]
    en  ...One thing. Right. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  dialogue.conversations.us.resume.name_a_step/2   [66 chars]
    en  Small enough to happen. I'd not have thought of it that way round.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem.
    >>  ............................................
  dialogue.conversations.us.resume.name_a_step/3   [66 chars]
    en  Then name it and I'll hold you to it, and you can hold me to mine.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. The whole of it has always been the part that stopped me.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it. And hold me to mine — I'll need holding.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar. E cobre a minha — eu vou precisar de cobrança.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. One thing at a time is how anything at all gets done.
    >>  ............................................
    pt  Uma coisa. Certo. Uma coisa por vez é como qualquer coisa se faz.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. Then another, in a month or two. That's the shape of it.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Depois outra, em um mês ou dois. É o formato.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it gently and over a long period.
    >>  ............................................
    pt  Então diga qual. Vou cobrar com gentileza e ao longo de muito tempo.
    >>  ............................................
  confident.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  Uma coisa. Certo. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  confident.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem.
    >>  ............................................
  confident.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  Uma coisa. Certo. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. That's a kinder way to put it than I'd have found.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. É um jeito mais gentil do que eu teria achado.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine. That's fair, isn't it.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha. É justo, não é?
    >>  ............................................
  flirty.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. That's a kinder way to put it than I'd have found.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. É um jeito mais gentil do que eu teria achado.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine. That's fair, isn't it.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha. É justo, não é?
    >>  ............................................
  friendly.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. That's a kinder way to put it than I'd have found.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. É um jeito mais gentil do que eu teria achado.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine. That's fair, isn't it.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha. É justo, não é?
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. The whole of it has always been the part that stopped me.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it. And hold me to mine — I'll need holding.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar. E cobre a minha — eu vou precisar de cobrança.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  Uma coisa. Certo. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  Uma coisa. Certo. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it, and you can hold me to mine.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar, e você pode cobrar a minha.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right.
    >>  ............................................
    pt  ...Uma coisa. Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen.
    >>  ............................................
    pt  Pequena o bastante pra acontecer.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it.
    >>  ............................................
    pt  Então diga qual. Eu vou cobrar.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. One thing at a time is how anything at all gets done.
    >>  ............................................
    pt  Uma coisa. Certo. Uma coisa por vez é como qualquer coisa se faz.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. Then another, in a month or two. That's the shape of it.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Depois outra, em um mês ou dois. É o formato.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it gently and over a long period.
    >>  ............................................
    pt  Então diga qual. Vou cobrar com gentileza e ao longo de muito tempo.
    >>  ............................................
  odd.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right.
    >>  ............................................
    pt  ...Uma coisa. Certo.
    >>  ............................................
  odd.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen.
    >>  ............................................
    pt  Pequena o bastante pra acontecer.
    >>  ............................................
  odd.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it.
    >>  ............................................
    pt  Então diga qual. Eu vou cobrar.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. One thing at a time is how anything at all gets done.
    >>  ............................................
    pt  Uma coisa. Certo. Uma coisa por vez é como qualquer coisa se faz.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. Then another, in a month or two. That's the shape of it.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Depois outra, em um mês ou dois. É o formato.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it gently and over a long period.
    >>  ............................................
    pt  Então diga qual. Vou cobrar com gentileza e ao longo de muito tempo.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.name_a_step/1
    en  One thing! Right. That's the first useful sentence either of us has managed all evening.
    >>  ............................................
    pt  Uma coisa! Certo. É a primeira frase útil que qualquer um de nós conseguiu a noite toda.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round and I'm annoyed I didn't.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem e me irrita não ter pensado.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it! I'll hold you to it. Ruthlessly. With a list.
    >>  ............................................
    pt  Então diga qual! Eu vou cobrar. Impiedosamente. Com uma lista.
    >>  ............................................
  playful.dialogue.conversations.us.resume.name_a_step/1
    en  One thing! Right. That's the first useful sentence either of us has managed all evening.
    >>  ............................................
    pt  Uma coisa! Certo. É a primeira frase útil que qualquer um de nós conseguiu a noite toda.
    >>  ............................................
  playful.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round and I'm annoyed I didn't.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem e me irrita não ter pensado.
    >>  ............................................
  playful.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it! I'll hold you to it. Ruthlessly. With a list.
    >>  ............................................
    pt  Então diga qual! Eu vou cobrar. Impiedosamente. Com uma lista.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.name_a_step/1
    en  One thing. Right. One thing at a time is how anything at all gets done.
    >>  ............................................
    pt  Uma coisa. Certo. Uma coisa por vez é como qualquer coisa se faz.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. Then another, in a month or two. That's the shape of it.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Depois outra, em um mês ou dois. É o formato.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it gently and over a long period.
    >>  ............................................
    pt  Então diga qual. Vou cobrar com gentileza e ao longo de muito tempo.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right, %1$s. That's the first useful sentence either of us has managed.
    >>  ............................................
    pt  ...Uma coisa. Certo, %1$s. É a primeira frase útil que qualquer um de nós conseguiu.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. The whole of it has always been the part that stopped me.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. A coisa inteira sempre foi o que me parava.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it and I'll hold you to it. And hold me to mine — I'll need holding.
    >>  ............................................
    pt  Então diga qual e eu vou cobrar. E cobre a minha — eu vou precisar de cobrança.
    >>  ............................................
  shy.dialogue.conversations.us.resume.name_a_step/1
    en  ...One thing. Right.
    >>  ............................................
    pt  ...Uma coisa. Certo.
    >>  ............................................
  shy.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen.
    >>  ............................................
    pt  Pequena o bastante pra acontecer.
    >>  ............................................
  shy.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it. I'll hold you to it.
    >>  ............................................
    pt  Então diga qual. Eu vou cobrar.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.name_a_step/1
    en  One thing! Right. That's the first useful sentence either of us has managed all evening.
    >>  ............................................
    pt  Uma coisa! Certo. É a primeira frase útil que qualquer um de nós conseguiu a noite toda.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round and I'm annoyed I didn't.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem e me irrita não ter pensado.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it! I'll hold you to it. Ruthlessly. With a list.
    >>  ............................................
    pt  Então diga qual! Eu vou cobrar. Impiedosamente. Com uma lista.
    >>  ............................................
  witty.dialogue.conversations.us.resume.name_a_step/1
    en  One thing! Right. That's the first useful sentence either of us has managed all evening.
    >>  ............................................
    pt  Uma coisa! Certo. É a primeira frase útil que qualquer um de nós conseguiu a noite toda.
    >>  ............................................
  witty.dialogue.conversations.us.resume.name_a_step/2
    en  Small enough to happen. I'd not have thought of it that way round and I'm annoyed I didn't.
    >>  ............................................
    pt  Pequena o bastante pra acontecer. Eu não teria pensado nessa ordem e me irrita não ter pensado.
    >>  ............................................
  witty.dialogue.conversations.us.resume.name_a_step/3
    en  Then name it! I'll hold you to it. Ruthlessly. With a list.
    >>  ............................................
    pt  Então diga qual! Eu vou cobrar. Impiedosamente. Com uma lista.
    >>  ............................................
```

</details>


### Button `let_it_rest` — "Let it rest a while longer."

*stance family `restraint` · tone `plain` · answers the beat(s) `us.resume.ask_where_it_stands`, `us.resume.hold_to_it`, `us.resume.admit_drift`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.let_it_rest` — accepted phrasings: "let it rest"; "leave it a while"; "no need to decide now"
  - the message must contain one of: `let`, `longer`, `rest`
  - scored words: `let`(0.5), `longer`(1.0), `rest`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.followup.let_it_rest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.followup.let_it_rest   [27 chars]
    en  Let it rest a while longer.
    >>  ............................................
    pt  Deixe descansar mais um tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, tension -2  _(recorded under topic `us.resume.let_it_rest`)_
- Does: arc `us` — advance to stage 3
- Then opens: `conversations.arc.us.resume.close`
- …where the player's next choices will be: "For what it's worth — this mattered." | "Can I ask you again in a month?" | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.resume.let_it_rest
WHO    VILLAGER — what the player reads after pressing "Let it rest a while longer."
       spoken on: conversations.arc.us.resume.followup, button `let_it_rest`
       leaves the player on: conversations.arc.us.resume.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.let_it_rest`: the villager accepts. Subject `future.revisit`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.let_it_rest/1   [55 chars]
    en  ...Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  dialogue.conversations.us.resume.let_it_rest/2   [71 chars]
    en  That's the kinder answer and I'd not have offered it myself. Thank you.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Obrigado.
    >>  ............................................
  dialogue.conversations.us.resume.let_it_rest/3   [62 chars]
    en  Then it rests. It'll still be here when either of us is ready.
    >>  ............................................
    pt  Então descansa. Vai continuar aqui quando um de nós estiver pronto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month, and I'd been trying to decide it.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês, e eu vinha tentando decidir.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd have pushed and pushing would have broken something.
    >>  ............................................
    pt  Certo. Então descansa. Eu teria empurrado e empurrar teria quebrado algo.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. I don't offer myself much.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Eu não me ofereço muito.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month, or the next.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês, nem o próximo.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. Things left to rest usually come back clearer.
    >>  ............................................
    pt  Certo. Então descansa. Coisas deixadas descansar costumam voltar mais claras.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer, and the patient one, and they're often the same answer.
    >>  ............................................
    pt  É a resposta mais gentil, e a paciente, e muitas vezes são a mesma resposta.
    >>  ............................................
  confident.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  confident.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. It'll still be here.
    >>  ............................................
    pt  Certo. Então descansa. Vai continuar aqui.
    >>  ............................................
  confident.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. It'll still be here.
    >>  ............................................
    pt  Certo. Então descansa. Vai continuar aqui.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd been about to force it and I'm glad you said so.
    >>  ............................................
    pt  Certo. Então descansa. Eu ia forçar e fico contente que você tenha dito.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer. I'd not have offered it and I needed it offered.
    >>  ............................................
    pt  É a resposta mais gentil. Eu não a ofereceria e eu precisava que fosse oferecida.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd been about to force it and I'm glad you said so.
    >>  ............................................
    pt  Certo. Então descansa. Eu ia forçar e fico contente que você tenha dito.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer. I'd not have offered it and I needed it offered.
    >>  ............................................
    pt  É a resposta mais gentil. Eu não a ofereceria e eu precisava que fosse oferecida.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd been about to force it and I'm glad you said so.
    >>  ............................................
    pt  Certo. Então descansa. Eu ia forçar e fico contente que você tenha dito.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer. I'd not have offered it and I needed it offered.
    >>  ............................................
    pt  É a resposta mais gentil. Eu não a ofereceria e eu precisava que fosse oferecida.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month, and I'd been trying to decide it.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês, e eu vinha tentando decidir.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd have pushed and pushing would have broken something.
    >>  ............................................
    pt  Certo. Então descansa. Eu teria empurrado e empurrar teria quebrado algo.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. I don't offer myself much.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Eu não me ofereço muito.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. It'll still be here.
    >>  ............................................
    pt  Certo. Então descansa. Vai continuar aqui.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. It'll still be here.
    >>  ............................................
    pt  Certo. Então descansa. Vai continuar aqui.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests.
    >>  ............................................
    pt  Certo. Então descansa.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer.
    >>  ............................................
    pt  É a resposta mais gentil.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month, or the next.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês, nem o próximo.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. Things left to rest usually come back clearer.
    >>  ............................................
    pt  Certo. Então descansa. Coisas deixadas descansar costumam voltar mais claras.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer, and the patient one, and they're often the same answer.
    >>  ............................................
    pt  É a resposta mais gentil, e a paciente, e muitas vezes são a mesma resposta.
    >>  ............................................
  odd.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  odd.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests.
    >>  ............................................
    pt  Certo. Então descansa.
    >>  ............................................
  odd.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer.
    >>  ............................................
    pt  É a resposta mais gentil.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month, or the next.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês, nem o próximo.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. Things left to rest usually come back clearer.
    >>  ............................................
    pt  Certo. Então descansa. Coisas deixadas descansar costumam voltar mais claras.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer, and the patient one, and they're often the same answer.
    >>  ............................................
    pt  É a resposta mais gentil, e a paciente, e muitas vezes são a mesma resposta.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest! Aye. Not everything wants deciding this month, or indeed this year.
    >>  ............................................
    pt  Descansar! É. Nem tudo quer ser decidido este mês, nem este ano.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right — then it rests. It'll still be here, being unresolved, whenever we want it.
    >>  ............................................
    pt  Certo — então descansa. Vai continuar aqui, sem resolver, quando a gente quiser.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. Thank you.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Obrigado.
    >>  ............................................
  playful.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest! Aye. Not everything wants deciding this month, or indeed this year.
    >>  ............................................
    pt  Descansar! É. Nem tudo quer ser decidido este mês, nem este ano.
    >>  ............................................
  playful.dialogue.conversations.us.resume.let_it_rest/2
    en  Right — then it rests. It'll still be here, being unresolved, whenever we want it.
    >>  ............................................
    pt  Certo — então descansa. Vai continuar aqui, sem resolver, quando a gente quiser.
    >>  ............................................
  playful.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. Thank you.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Obrigado.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest. Aye. Not everything wants deciding this month, or the next.
    >>  ............................................
    pt  Descansar. É. Nem tudo quer ser decidido este mês, nem o próximo.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. Things left to rest usually come back clearer.
    >>  ............................................
    pt  Certo. Então descansa. Coisas deixadas descansar costumam voltar mais claras.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer, and the patient one, and they're often the same answer.
    >>  ............................................
    pt  É a resposta mais gentil, e a paciente, e muitas vezes são a mesma resposta.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye, %1$s. Not everything wants deciding this month, and I'd been trying to decide it.
    >>  ............................................
    pt  ...Descansar. É, %1$s. Nem tudo quer ser decidido este mês, e eu vinha tentando decidir.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests. I'd have pushed and pushing would have broken something.
    >>  ............................................
    pt  Certo. Então descansa. Eu teria empurrado e empurrar teria quebrado algo.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. I don't offer myself much.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Eu não me ofereço muito.
    >>  ............................................
  shy.dialogue.conversations.us.resume.let_it_rest/1
    en  ...Rest. Aye. Not everything wants deciding this month.
    >>  ............................................
    pt  ...Descansar. É. Nem tudo quer ser decidido este mês.
    >>  ............................................
  shy.dialogue.conversations.us.resume.let_it_rest/2
    en  Right. Then it rests.
    >>  ............................................
    pt  Certo. Então descansa.
    >>  ............................................
  shy.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer.
    >>  ............................................
    pt  É a resposta mais gentil.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest! Aye. Not everything wants deciding this month, or indeed this year.
    >>  ............................................
    pt  Descansar! É. Nem tudo quer ser decidido este mês, nem este ano.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.let_it_rest/2
    en  Right — then it rests. It'll still be here, being unresolved, whenever we want it.
    >>  ............................................
    pt  Certo — então descansa. Vai continuar aqui, sem resolver, quando a gente quiser.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. Thank you.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Obrigado.
    >>  ............................................
  witty.dialogue.conversations.us.resume.let_it_rest/1
    en  Rest! Aye. Not everything wants deciding this month, or indeed this year.
    >>  ............................................
    pt  Descansar! É. Nem tudo quer ser decidido este mês, nem este ano.
    >>  ............................................
  witty.dialogue.conversations.us.resume.let_it_rest/2
    en  Right — then it rests. It'll still be here, being unresolved, whenever we want it.
    >>  ............................................
    pt  Certo — então descansa. Vai continuar aqui, sem resolver, quando a gente quiser.
    >>  ............................................
  witty.dialogue.conversations.us.resume.let_it_rest/3
    en  That's the kinder answer and I'd not have offered it myself. Thank you.
    >>  ............................................
    pt  É a resposta mais gentil e eu não a teria oferecido. Obrigado.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it with you."

*stance family `exit` · tone `plain` · answers the beat(s) `us.resume.ask_where_it_stands`, `us.resume.hold_to_it`, `us.resume.admit_drift` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.followup.leave   [23 chars]
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
POOL   dialogue key: dialogue.conversations.us.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.us.resume.followup, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.leave`: the villager accepts. Subject `future.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.us.resume.close / leave; conversations.arc.us.resume.respond / leave
```

> Written out in full under **`conversations.arc.us.resume.close` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.arc.us.resume.respond`

**Reached from 1 route(s):** `conversations.us` / `future`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.revisit` — e.g. "I've been thinking about what we settled, you and I. It hasn't left me alone."


```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.us.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.us.resume.respond   [25 chars]
    en  So. Where are we with it?
    >>  ............................................
    pt  Então. Como estamos com isso?
    >>  ............................................
```


### Button `ask_where_it_stands` — "Where has it got to, for you?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `us.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.ask_where_it_stands` — accepted phrasings: "where has it got to"; "where do we stand"; "how far has it got"
  - the message must contain one of: `got`, `stands`, `where`
  - scored words: `got`(0.6), `stands`(1.5), `where`(0.8)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.respond.ask_where_it_stands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.respond.ask_where_it_stands   [29 chars]
    en  Where has it got to, for you?
    >>  ............................................
    pt  Aonde isso chegou, pra você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +4, trust +2  _(recorded under topic `us.resume.ask_where_it_stands`)_
- Does: arc `us` — advance to stage 2
- Then opens: `conversations.arc.us.resume.followup`
- …where the player's next choices will be: "One thing. Small enough to actually happen." | "Let it rest a while longer." | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.resume.ask_where_it_stands
WHO    VILLAGER — what the player reads after pressing "Where has it got to, for you?"
       spoken on: conversations.arc.us.resume.respond, button `ask_where_it_stands`
       leaves the player on: conversations.arc.us.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.ask_where_it_stands`: the villager discloses. Subject `future.revisit`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.ask_where_it_stands/1   [76 chars]
    en  Further than I'd have said out loud a month ago. That's as much as I've got.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás. É tudo que eu tenho.
    >>  ............................................
  dialogue.conversations.us.resume.ask_where_it_stands/2   [84 chars]
    en  Not far. But it hasn't gone backwards either, and I'd counted on it going backwards.
    >>  ............................................
    pt  Não muito. Mas também não voltou atrás, e eu contava que voltasse.
    >>  ............................................
  dialogue.conversations.us.resume.ask_where_it_stands/3   [57 chars]
    en  I think about it most evenings. That's where it's got to.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s. That frightens me a little.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s. Isso me assusta um pouco.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd been quite sure it would.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu tinha certeza que voltaria.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. I'd not admit that to anybody but you.
    >>  ............................................
    pt  Penso nisso quase toda noite. Eu não admitiria isso a ninguém além de você.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said a month ago. Slowly, which is how it should go.
    >>  ............................................
    pt  Mais longe do que eu diria um mês atrás. Devagar, que é como deve ser.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards either, and over a year that's the number that counts.
    >>  ............................................
    pt  Não muito. Também não voltou atrás, e ao longo de um ano é esse o número que conta.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. It'll settle when it settles.
    >>  ............................................
    pt  Penso nisso quase toda noite. Vai assentar quando assentar.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd counted on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu contava com voltar atrás.
    >>  ............................................
  confident.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd counted on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu contava com voltar atrás.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and having you ask is part of why.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e você perguntar é parte do porquê.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to, and I'd only tell you.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou, e eu só contaria a você.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and having you ask is part of why.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e você perguntar é parte do porquê.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to, and I'd only tell you.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou, e eu só contaria a você.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and having you ask is part of why.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e você perguntar é parte do porquê.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to, and I'd only tell you.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou, e eu só contaria a você.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s. That frightens me a little.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s. Isso me assusta um pouco.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd been quite sure it would.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu tinha certeza que voltaria.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. I'd not admit that to anybody but you.
    >>  ............................................
    pt  Penso nisso quase toda noite. Eu não admitiria isso a ninguém além de você.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd counted on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu contava com voltar atrás.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd counted on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu contava com voltar atrás.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards.
    >>  ............................................
    pt  Não muito. Não voltou atrás.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings.
    >>  ............................................
    pt  Penso nisso quase toda noite.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said a month ago. Slowly, which is how it should go.
    >>  ............................................
    pt  Mais longe do que eu diria um mês atrás. Devagar, que é como deve ser.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards either, and over a year that's the number that counts.
    >>  ............................................
    pt  Não muito. Também não voltou atrás, e ao longo de um ano é esse o número que conta.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. It'll settle when it settles.
    >>  ............................................
    pt  Penso nisso quase toda noite. Vai assentar quando assentar.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards.
    >>  ............................................
    pt  Não muito. Não voltou atrás.
    >>  ............................................
  odd.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings.
    >>  ............................................
    pt  Penso nisso quase toda noite.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said a month ago. Slowly, which is how it should go.
    >>  ............................................
    pt  Mais longe do que eu diria um mês atrás. Devagar, que é como deve ser.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards either, and over a year that's the number that counts.
    >>  ............................................
    pt  Não muito. Também não voltou atrás, e ao longo de um ano é esse o número que conta.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. It'll settle when it settles.
    >>  ............................................
    pt  Penso nisso quase toda noite. Vai assentar quando assentar.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago! Which is my entire report.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás! É todo o meu relatório.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I had money on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu apostava em voltar atrás.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to. Riveting, I know.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou. Fascinante, eu sei.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago! Which is my entire report.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás! É todo o meu relatório.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I had money on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu apostava em voltar atrás.
    >>  ............................................
  playful.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to. Riveting, I know.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou. Fascinante, eu sei.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said a month ago. Slowly, which is how it should go.
    >>  ............................................
    pt  Mais longe do que eu diria um mês atrás. Devagar, que é como deve ser.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards either, and over a year that's the number that counts.
    >>  ............................................
    pt  Não muito. Também não voltou atrás, e ao longo de um ano é esse o número que conta.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. It'll settle when it settles.
    >>  ............................................
    pt  Penso nisso quase toda noite. Vai assentar quando assentar.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago, %1$s. That frightens me a little.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás, %1$s. Isso me assusta um pouco.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I'd been quite sure it would.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu tinha certeza que voltaria.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. I'd not admit that to anybody but you.
    >>  ............................................
    pt  Penso nisso quase toda noite. Eu não admitiria isso a ninguém além de você.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. It hasn't gone backwards.
    >>  ............................................
    pt  Não muito. Não voltou atrás.
    >>  ............................................
  shy.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings.
    >>  ............................................
    pt  Penso nisso quase toda noite.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago! Which is my entire report.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás! É todo o meu relatório.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I had money on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu apostava em voltar atrás.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to. Riveting, I know.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou. Fascinante, eu sei.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_where_it_stands/1
    en  Further than I'd have said out loud a month ago! Which is my entire report.
    >>  ............................................
    pt  Mais longe do que eu diria em voz alta um mês atrás! É todo o meu relatório.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_where_it_stands/2
    en  Not far. But it hasn't gone backwards, and I had money on backwards.
    >>  ............................................
    pt  Não muito. Mas não voltou atrás, e eu apostava em voltar atrás.
    >>  ............................................
  witty.dialogue.conversations.us.resume.ask_where_it_stands/3
    en  I think about it most evenings. That's where it's got to. Riveting, I know.
    >>  ............................................
    pt  Penso nisso quase toda noite. É aonde chegou. Fascinante, eu sei.
    >>  ............................................
```

</details>


### Button `hold_to_it` — "I meant what I said. I still do."

*stance family `candor` · tone `plain` · answers the beat(s) `us.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.hold_to_it` — accepted phrasings: "i meant what i said"; "i still mean it"; "nothing has changed for me"
  - the message must contain one of: `meant`, `said`, `still`
  - scored words: `meant`(1.5), `said`(0.8), `still`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.respond.hold_to_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.respond.hold_to_it   [32 chars]
    en  I meant what I said. I still do.
    >>  ............................................
    pt  Eu falei sério. Ainda falo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `us.resume.hold_to_it`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +4  _(recorded under topic `us.resume.hold_to_it`)_
- Does: arc `us` — advance to stage 2
- Then opens: `conversations.arc.us.resume.followup`
- …where the player's next choices will be: "One thing. Small enough to actually happen." | "Let it rest a while longer." | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.resume.hold_to_it
WHO    VILLAGER — what the player reads after pressing "I meant what I said. I still do."
       spoken on: conversations.arc.us.resume.respond, button `hold_to_it`
       leaves the player on: conversations.arc.us.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.hold_to_it`: the villager accepts. Subject `future.revisit`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.hold_to_it/1   [78 chars]
    en  ...You still do. Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala. Certo. Eu vinha prendendo a respiração sobre isso há quinze dias.
    >>  ............................................
  dialogue.conversations.us.resume.hold_to_it/2   [85 chars]
    en  Then we're both still standing where we said we'd stand. That's rarer than it sounds.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. É mais raro do que parece.
    >>  ............................................
  dialogue.conversations.us.resume.hold_to_it/3   [67 chars]
    en  I hoped you'd say that and I'd decided not to expect it. Thank you.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar. Obrigado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right, %1$s. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo, %1$s. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said. I'd made a plan for the other answer.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos. Eu tinha um plano pra outra resposta.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it, so that it couldn't hurt.
    >>  ............................................
    pt  Eu esperava que você dissesse e decidi não esperar, pra não poder doer.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. Said twice, a month apart — that's how a thing proves itself.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Dito duas vezes, com um mês de diferença — é assim que algo se prova.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand, and we'll be there next month too.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos, e vamos estar mês que vem também.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Good. There's no hurry on any of the rest of it.
    >>  ............................................
    pt  Eu esperava que você dissesse. Bom. Não há pressa em nada do resto.
    >>  ............................................
  confident.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. I'd been holding my breath about that.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Eu vinha prendendo a respiração sobre isso.
    >>  ............................................
  confident.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos.
    >>  ............................................
  confident.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. I'd been holding my breath about that.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Eu vinha prendendo a respiração sobre isso.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do, %1$s. Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério, %1$s. Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. That's rarer than it sounds.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. É mais raro do que parece.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Thank you for saying it before I had to ask.
    >>  ............................................
    pt  Eu esperava que você dissesse. Obrigado por dizer antes de eu ter que perguntar.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do, %1$s. Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério, %1$s. Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. That's rarer than it sounds.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. É mais raro do que parece.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Thank you for saying it before I had to ask.
    >>  ............................................
    pt  Eu esperava que você dissesse. Obrigado por dizer antes de eu ter que perguntar.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do, %1$s. Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério, %1$s. Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. That's rarer than it sounds.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. É mais raro do que parece.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Thank you for saying it before I had to ask.
    >>  ............................................
    pt  Eu esperava que você dissesse. Obrigado por dizer antes de eu ter que perguntar.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right, %1$s. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo, %1$s. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said. I'd made a plan for the other answer.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos. Eu tinha um plano pra outra resposta.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it, so that it couldn't hurt.
    >>  ............................................
    pt  Eu esperava que você dissesse e decidi não esperar, pra não poder doer.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. I'd been holding my breath about that.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Eu vinha prendendo a respiração sobre isso.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. I'd been holding my breath about that.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Eu vinha prendendo a respiração sobre isso.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that.
    >>  ............................................
    pt  Eu esperava que você dissesse.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. Said twice, a month apart — that's how a thing proves itself.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Dito duas vezes, com um mês de diferença — é assim que algo se prova.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand, and we'll be there next month too.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos, e vamos estar mês que vem também.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Good. There's no hurry on any of the rest of it.
    >>  ............................................
    pt  Eu esperava que você dissesse. Bom. Não há pressa em nada do resto.
    >>  ............................................
  odd.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo.
    >>  ............................................
  odd.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos.
    >>  ............................................
  odd.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that.
    >>  ............................................
    pt  Eu esperava que você dissesse.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. Said twice, a month apart — that's how a thing proves itself.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Dito duas vezes, com um mês de diferença — é assim que algo se prova.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand, and we'll be there next month too.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos, e vamos estar mês que vem também.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Good. There's no hurry on any of the rest of it.
    >>  ............................................
    pt  Eu esperava que você dissesse. Bom. Não há pressa em nada do resto.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do! Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  Você ainda fala sério! Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. Extraordinary organisation.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. Organização extraordinária.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it. Delighted to be wrong.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar. Encantado de estar errado.
    >>  ............................................
  playful.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do! Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  Você ainda fala sério! Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  playful.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. Extraordinary organisation.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. Organização extraordinária.
    >>  ............................................
  playful.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it. Delighted to be wrong.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar. Encantado de estar errado.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do. Right. Said twice, a month apart — that's how a thing proves itself.
    >>  ............................................
    pt  Você ainda fala sério. Certo. Dito duas vezes, com um mês de diferença — é assim que algo se prova.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand, and we'll be there next month too.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos, e vamos estar mês que vem também.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that. Good. There's no hurry on any of the rest of it.
    >>  ............................................
    pt  Eu esperava que você dissesse. Bom. Não há pressa em nada do resto.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right, %1$s. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo, %1$s. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said. I'd made a plan for the other answer.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos. Eu tinha um plano pra outra resposta.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it, so that it couldn't hurt.
    >>  ............................................
    pt  Eu esperava que você dissesse e decidi não esperar, pra não poder doer.
    >>  ............................................
  shy.dialogue.conversations.us.resume.hold_to_it/1
    en  ...You still do. Right.
    >>  ............................................
    pt  ...Você ainda fala sério. Certo.
    >>  ............................................
  shy.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos.
    >>  ............................................
  shy.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that.
    >>  ............................................
    pt  Eu esperava que você dissesse.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do! Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  Você ainda fala sério! Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. Extraordinary organisation.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. Organização extraordinária.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it. Delighted to be wrong.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar. Encantado de estar errado.
    >>  ............................................
  witty.dialogue.conversations.us.resume.hold_to_it/1
    en  You still do! Right. I'd been holding my breath about that for a fortnight.
    >>  ............................................
    pt  Você ainda fala sério! Certo. Eu vinha prendendo a respiração há quinze dias.
    >>  ............................................
  witty.dialogue.conversations.us.resume.hold_to_it/2
    en  Then we're both still standing where we said we'd stand. Extraordinary organisation.
    >>  ............................................
    pt  Então nós dois ainda estamos onde dissemos que estaríamos. Organização extraordinária.
    >>  ............................................
  witty.dialogue.conversations.us.resume.hold_to_it/3
    en  I hoped you'd say that and I'd decided not to expect it. Delighted to be wrong.
    >>  ............................................
    pt  Eu esperava que você dissesse e tinha decidido não esperar. Encantado de estar errado.
    >>  ............................................
```

</details>


### Button `admit_drift` — "Honestly? I've let it drift."

*stance family `candor` · tone `plain` · answers the beat(s) `us.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.resume.admit_drift` — accepted phrasings: "i have let it drift"; "i have not done much about it"; "honestly i let it slide"
  - the message must contain one of: `drift`, `honestly`, `let`
  - scored words: `drift`(1.5), `honestly`(1.2), `let`(0.5)

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.respond.admit_drift
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.respond.admit_drift   [28 chars]
    en  Honestly? I've let it drift.
    >>  ............................................
    pt  Sinceramente? Eu deixei isso escorrer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +3, warmth -1  _(recorded under topic `us.resume.admit_drift`)_
- Does: arc `us` — advance to stage 2
- Then opens: `conversations.arc.us.resume.followup`
- …where the player's next choices will be: "One thing. Small enough to actually happen." | "Let it rest a while longer." | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.resume.admit_drift
WHO    VILLAGER — what the player reads after pressing "Honestly? I've let it drift."
       spoken on: conversations.arc.us.resume.respond, button `admit_drift`
       leaves the player on: conversations.arc.us.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.admit_drift`: the villager qualifys. Subject `future.revisit`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.resume.admit_drift/1   [75 chars]
    en  ...Honestly. Right. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  dialogue.conversations.us.resume.admit_drift/2   [75 chars]
    en  So have I, if we're being honest at each other. That's something, at least.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto. Já é alguma coisa.
    >>  ............................................
  dialogue.conversations.us.resume.admit_drift/3   [60 chars]
    en  Drifted. Yes. I'd rather hear that than be told it was fine.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. I'd been dreading having to say it and you went first.
    >>  ............................................
    pt  Eu também. Eu temia ter que dizer e você foi primeiro.
    >>  ............................................
  anxious.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine and spend a month wondering.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem e passar um mês imaginando.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. Things drift; it's what they do when nobody looks at them.
    >>  ............................................
    pt  Sinceramente. Certo. As coisas escorrem; é o que fazem quando ninguém olha.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. We'll both look at it now, and slowly it'll stop drifting.
    >>  ............................................
    pt  Eu também. Agora nós dois vamos olhar, e devagar vai parar de escorrer.
    >>  ............................................
  athletic.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Better said now than found out in a year.
    >>  ............................................
    pt  Escorreu. Sim. Melhor dito agora que descoberto em um ano.
    >>  ............................................
  confident.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. That's worth more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente. Certo. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  confident.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto.
    >>  ............................................
  confident.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. That's worth more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente. Certo. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto.
    >>  ............................................
  crabby.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. There — now neither of us has to perform, which is a relief.
    >>  ............................................
    pt  Eu também. Pronto — agora nenhum de nós precisa encenar, o que é um alívio.
    >>  ............................................
  extroverted.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Thank you for saying it first; I'd been working up to it.
    >>  ............................................
    pt  Escorreu. Sim. Obrigado por dizer primeiro; eu vinha me preparando.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. There — now neither of us has to perform, which is a relief.
    >>  ............................................
    pt  Eu também. Pronto — agora nenhum de nós precisa encenar, o que é um alívio.
    >>  ............................................
  flirty.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Thank you for saying it first; I'd been working up to it.
    >>  ............................................
    pt  Escorreu. Sim. Obrigado por dizer primeiro; eu vinha me preparando.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. There — now neither of us has to perform, which is a relief.
    >>  ............................................
    pt  Eu também. Pronto — agora nenhum de nós precisa encenar, o que é um alívio.
    >>  ............................................
  friendly.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Thank you for saying it first; I'd been working up to it.
    >>  ............................................
    pt  Escorreu. Sim. Obrigado por dizer primeiro; eu vinha me preparando.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. I'd been dreading having to say it and you went first.
    >>  ............................................
    pt  Eu também. Eu temia ter que dizer e você foi primeiro.
    >>  ............................................
  gloomy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine and spend a month wondering.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem e passar um mês imaginando.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. That's worth more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente. Certo. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto.
    >>  ............................................
  greedy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. That's worth more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente. Certo. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto.
    >>  ............................................
  grumpy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right. That's worth more than a better answer.
    >>  ............................................
    pt  ...Sinceramente. Certo. Vale mais que uma resposta melhor.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.admit_drift/2
    en  So have I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  introverted.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes.
    >>  ............................................
    pt  Escorreu. Sim.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. Things drift; it's what they do when nobody looks at them.
    >>  ............................................
    pt  Sinceramente. Certo. As coisas escorrem; é o que fazem quando ninguém olha.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. We'll both look at it now, and slowly it'll stop drifting.
    >>  ............................................
    pt  Eu também. Agora nós dois vamos olhar, e devagar vai parar de escorrer.
    >>  ............................................
  lazy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Better said now than found out in a year.
    >>  ............................................
    pt  Escorreu. Sim. Melhor dito agora que descoberto em um ano.
    >>  ............................................
  odd.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right. That's worth more than a better answer.
    >>  ............................................
    pt  ...Sinceramente. Certo. Vale mais que uma resposta melhor.
    >>  ............................................
  odd.dialogue.conversations.us.resume.admit_drift/2
    en  So have I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  odd.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes.
    >>  ............................................
    pt  Escorreu. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. Things drift; it's what they do when nobody looks at them.
    >>  ............................................
    pt  Sinceramente. Certo. As coisas escorrem; é o que fazem quando ninguém olha.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. We'll both look at it now, and slowly it'll stop drifting.
    >>  ............................................
    pt  Eu também. Agora nós dois vamos olhar, e devagar vai parar de escorrer.
    >>  ............................................
  peaceful.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Better said now than found out in a year.
    >>  ............................................
    pt  Escorreu. Sim. Melhor dito agora que descoberto em um ano.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly! Right. That's worth considerably more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente! Certo. Vale consideravelmente mais que uma resposta melhor teria valido.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other. What a pair we are.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto. Que dupla nós somos.
    >>  ............................................
  peppy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted! Yes. I'd rather hear that than be told it was fine by somebody with a straight face.
    >>  ............................................
    pt  Escorreu! Sim. Prefiro ouvir isso a ouvir que estava tudo bem com cara séria.
    >>  ............................................
  playful.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly! Right. That's worth considerably more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente! Certo. Vale consideravelmente mais que uma resposta melhor teria valido.
    >>  ............................................
  playful.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other. What a pair we are.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto. Que dupla nós somos.
    >>  ............................................
  playful.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted! Yes. I'd rather hear that than be told it was fine by somebody with a straight face.
    >>  ............................................
    pt  Escorreu! Sim. Prefiro ouvir isso a ouvir que estava tudo bem com cara séria.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly. Right. Things drift; it's what they do when nobody looks at them.
    >>  ............................................
    pt  Sinceramente. Certo. As coisas escorrem; é o que fazem quando ninguém olha.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. We'll both look at it now, and slowly it'll stop drifting.
    >>  ............................................
    pt  Eu também. Agora nós dois vamos olhar, e devagar vai parar de escorrer.
    >>  ............................................
  relaxed.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. Better said now than found out in a year.
    >>  ............................................
    pt  Escorreu. Sim. Melhor dito agora que descoberto em um ano.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right, %1$s. That's worth more than a better answer would have been.
    >>  ............................................
    pt  ...Sinceramente. Certo, %1$s. Vale mais que uma resposta melhor teria valido.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.admit_drift/2
    en  So have I. I'd been dreading having to say it and you went first.
    >>  ............................................
    pt  Eu também. Eu temia ter que dizer e você foi primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes. I'd rather hear that than be told it was fine and spend a month wondering.
    >>  ............................................
    pt  Escorreu. Sim. Prefiro ouvir isso a ouvir que estava tudo bem e passar um mês imaginando.
    >>  ............................................
  shy.dialogue.conversations.us.resume.admit_drift/1
    en  ...Honestly. Right. That's worth more than a better answer.
    >>  ............................................
    pt  ...Sinceramente. Certo. Vale mais que uma resposta melhor.
    >>  ............................................
  shy.dialogue.conversations.us.resume.admit_drift/2
    en  So have I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  shy.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted. Yes.
    >>  ............................................
    pt  Escorreu. Sim.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly! Right. That's worth considerably more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente! Certo. Vale consideravelmente mais que uma resposta melhor teria valido.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other. What a pair we are.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto. Que dupla nós somos.
    >>  ............................................
  upbeat.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted! Yes. I'd rather hear that than be told it was fine by somebody with a straight face.
    >>  ............................................
    pt  Escorreu! Sim. Prefiro ouvir isso a ouvir que estava tudo bem com cara séria.
    >>  ............................................
  witty.dialogue.conversations.us.resume.admit_drift/1
    en  Honestly! Right. That's worth considerably more than a better answer would have been.
    >>  ............................................
    pt  Sinceramente! Certo. Vale consideravelmente mais que uma resposta melhor teria valido.
    >>  ............................................
  witty.dialogue.conversations.us.resume.admit_drift/2
    en  So have I, if we're being honest at each other. What a pair we are.
    >>  ............................................
    pt  Eu também, se a gente vai ser honesto. Que dupla nós somos.
    >>  ............................................
  witty.dialogue.conversations.us.resume.admit_drift/3
    en  Drifted! Yes. I'd rather hear that than be told it was fine by somebody with a straight face.
    >>  ............................................
    pt  Escorreu! Sim. Prefiro ouvir isso a ouvir que estava tudo bem com cara séria.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave it with you."

*stance family `exit` · tone `plain` · answers the beat(s) `us.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.us.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.us.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.us.resume.respond.leave   [23 chars]
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
POOL   dialogue key: dialogue.conversations.us.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.us.resume.respond, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.leave`: the villager accepts. Subject `future.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.us.resume.close / leave; conversations.arc.us.resume.followup / leave
```

> Written out in full under **`conversations.arc.us.resume.close` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.us.close`

**Reached from 10 route(s):** `conversations.topic.firstmet.followup` / `ask_stood_out`; `conversations.topic.firstmet.followup` / `correct_gently`; `conversations.topic.firstmet.followup` / `dismiss`; `conversations.topic.future.followup` / `negotiate`; `conversations.topic.future.followup` / `promise_nothing`; `conversations.topic.happy.followup` / `ask_improve`; `conversations.topic.happy.followup` / `reassure`; `conversations.topic.worries.followup` / `help_solve`; `conversations.topic.worries.followup` / `give_space`; `conversations.topic.worries.followup` / `change_subject`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.firstmet.ask_stood_out` — e.g. "...You did. Not anything you said. Just that you stayed."
- `conversations.us.firstmet.correct_gently` — e.g. "...Was it not? Huh. I've told it wrong for years, then."
- `conversations.us.firstmet.dismiss` — e.g. "It was. It's still the day I count from, %1$s."
- `conversations.us.future.negotiate` — e.g. "...Work it out. Aye. That's better than one of us just giving in."
- `conversations.us.future.promise_nothing` — e.g. "That's fair. I'd rather that than a promise you'd resent."
- `conversations.us.happy.ask_improve` — e.g. "...You want to know. Right. Small things — and you asking is one of them fixed."
- `conversations.us.happy.reassure` — e.g. "We will. I believe you when you say it."
- `conversations.us.worries.change_subject` — e.g. "Aye, let's. I've turned it over enough for one evening."
- `conversations.us.worries.give_space` — e.g. "...Not today. That's permission I didn't know I needed."
- `conversations.us.worries.help_solve` — e.g. "Together. ...That's the part I couldn't do alone. Alright."


```text
POOL   dialogue key: dialogue.conversations.topic.us.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.us.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.us.close   [33 chars]
    en  Anyway. We're alright, you and I.
    >>  ............................................
    pt  Enfim. Estamos bem, você e eu.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `us.firstmet.ask_stood_out.to.us`, `us.firstmet.correct_gently.to.us`, `us.firstmet.dismiss.to.us`, `us.future.negotiate.to.us`, `us.future.promise_nothing.to.us`, `us.happy.ask_improve.to.us`, `us.happy.reassure.to.us`, `us.worries.change_subject.to.us`, `us.worries.give_space.to.us`, `us.worries.help_solve.to.us`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.close.thank` — accepted phrasings: "thank you for trusting me with that"; "thank you for telling me that"; "thanks for letting me in"
  - the message must contain one of: `trusting`
  - scored words: `thank`(0.5), `trusting`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.us.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.close.thank`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `us.close.thank`)_
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.us.close, button `thank`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.close.thank.terminal`: the villager accepts. Subject `us.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.us.close.thank/1   [36 chars]
    en  You don't need to thank me for that.
    >>  ............................................
    pt  Você não precisa me agradecer por isso.
    >>  ............................................
  dialogue.conversations.us.close.thank/2   [41 chars]
    en  Aye, well. That's what this is for, %1$s.
    >>  ............................................
    pt  É, bom. É para isso que serve, %1$s.
    >>  ............................................
  dialogue.conversations.us.close.thank/3   [30 chars]
    en  Thank you for listening to it.
    >>  ............................................
    pt  Obrigado por ter ouvido.
    >>  ............................................
```


### Button `say_means` — "That mattered, what you said."

*stance family `candor` · tone `gentle` · answers the beat(s) `us.firstmet.ask_stood_out.to.us`, `us.firstmet.correct_gently.to.us`, `us.firstmet.dismiss.to.us`, `us.future.negotiate.to.us`, `us.future.promise_nothing.to.us`, `us.happy.ask_improve.to.us`, `us.happy.reassure.to.us`, `us.worries.change_subject.to.us`, `us.worries.give_space.to.us`, `us.worries.help_solve.to.us`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.close.say_means` — accepted phrasings: "that mattered to me"; "what you said mattered"; "i will not forget you said that"
  - the message must contain one of: `mattered`
  - scored words: `mattered`(1.2), `us`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.us.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.close.say_means   [29 chars]
    en  That mattered, what you said.
    >>  ............................................
    pt  Isso importou, o que você disse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.close.say_means`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `us.close.say_means`)_
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.close.say_means
WHO    VILLAGER — what the player reads after pressing "That mattered, what you said."
       spoken on: conversations.topic.us.close, button `say_means`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.close.say_means.terminal`: the villager accepts. Subject `us.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.us.close.say_means/1   [30 chars]
    en  ...It did. More than I let on.
    >>  ............................................
    pt  ...Importou. Mais do que eu demonstrei.
    >>  ............................................
  dialogue.conversations.us.close.say_means/2   [31 chars]
    en  Then it was worth saying, %1$s.
    >>  ............................................
    pt  Então valeu a pena dizer, %1$s.
    >>  ............................................
  dialogue.conversations.us.close.say_means/3   [43 chars]
    en  Good. I'd hate to have said it for nothing.
    >>  ............................................
    pt  Bom. Eu odiaria ter dito à toa.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `us.firstmet.ask_stood_out.to.us`, `us.firstmet.correct_gently.to.us`, `us.firstmet.dismiss.to.us`, `us.future.negotiate.to.us`, `us.future.promise_nothing.to.us`, `us.happy.ask_improve.to.us`, `us.happy.reassure.to.us`, `us.worries.change_subject.to.us`, `us.worries.give_space.to.us`, `us.worries.help_solve.to.us` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.us.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.close.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.us.close, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.close.leave.terminal`: the villager accepts. Subject `us.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.us.close.leave/1   [16 chars]
    en  Aye. Off you go.
    >>  ............................................
    pt  É. Pode ir.
    >>  ............................................
  dialogue.conversations.us.close.leave/2   [27 chars]
    en  That'll do for today, %1$s.
    >>  ............................................
    pt  Por hoje está bom, %1$s.
    >>  ............................................
  dialogue.conversations.us.close.leave/3   [12 chars]
    en  Later, then.
    >>  ............................................
    pt  Depois, então.
    >>  ............................................
```

---


## `conversations.topic.us.hurt.close`

**Reached from 2 route(s):** `conversations.topic.future.followup` / `reject`; `conversations.topic.happy.followup` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.us.future.reject` — e.g. "...Never. Right. That's a hard word, %1$s."
- `conversations.us.happy.dismiss` — e.g. "...I asked you a serious question, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.us.hurt.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.us.hurt.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.us.hurt.close   [27 chars]
    en  We'll leave it there, then.
    >>  ............................................
    pt  Então vamos parar por aqui.
    >>  ............................................
```


### Button `apologize` — "That came out harder than I meant it."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `us.future.rejected`, `us.happy.brushed_off` · offered only once the villager has actually said `relationship:strained`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.hurt.apologize` — accepted phrasings: "that came out harder than i meant it"
  - the message must contain one of: `harder`, `meant`
  - scored words: `harder`(1.5), `meant`(1.0), `came`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.us.hurt.close.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.hurt.close.apologize   [37 chars]
    en  That came out harder than I meant it.
    >>  ............................................
    pt  Saiu mais duro do que eu quis.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `us.hurt.apologize`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.hurt.apologize
WHO    VILLAGER — what the player reads after pressing "That came out harder than I meant it."
       spoken on: conversations.topic.us.hurt.close, button `apologize`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.hurt.apologize`: the villager qualifys. Subject `us.relationship`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.hurt.apologize/1   [47 chars]
    en  ...Then say the soft version. I'll wait for it.
    >>  ............................................
    pt  ...Então diga a versão suave. Eu espero.
    >>  ............................................
  dialogue.conversations.us.hurt.apologize/2   [70 chars]
    en  Harder than you meant. I'll take that, %1$s. Barely, but I'll take it.
    >>  ............................................
    pt  Mais duro do que quis. Eu aceito, %1$s. Por pouco, mas aceito.
    >>  ............................................
  dialogue.conversations.us.hurt.apologize/3   [71 chars]
    en  It did. And I'd rather have the true thing than a kind lie, so — go on.
    >>  ............................................
    pt  Saiu. E eu prefiro o verdadeiro a uma mentira gentil, então — pode falar.
    >>  ............................................
```


### Button `soften` — "You matter to me. That part is true."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `us.future.rejected`, `us.happy.brushed_off`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.hurt.soften` — accepted phrasings: "you matter to me. that part is true"
  - the message must contain one of: `matter`, `true`
  - scored words: `matter`(1.5), `true`(1.2), `part`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.us.hurt.close.soften
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.hurt.close.soften   [36 chars]
    en  You matter to me. That part is true.
    >>  ............................................
    pt  Você importa pra mim. Essa parte é verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `us.hurt.soften`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `us.hurt.soften`)_
- Does: session `turn`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.hurt.soften
WHO    VILLAGER — what the player reads after pressing "You matter to me. That part is true."
       spoken on: conversations.topic.us.hurt.close, button `soften`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.hurt.soften`: the villager accepts. Subject `us.relationship`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.hurt.soften/1   [60 chars]
    en  ...Alright. I'll hold on to that one and put the other down.
    >>  ............................................
    pt  ...Está bem. Vou segurar essa e largar a outra.
    >>  ............................................
  dialogue.conversations.us.hurt.soften/2   [71 chars]
    en  That's the part I needed, %1$s. The rest can wait for a better evening.
    >>  ............................................
    pt  É essa a parte que eu precisava, %1$s. O resto pode esperar por uma noite melhor.
    >>  ............................................
  dialogue.conversations.us.hurt.soften/3   [61 chars]
    en  Then we're not undone. Only bruised. I can work with bruised.
    >>  ............................................
    pt  Então não estamos desfeitos. Só machucados. Machucado eu resolvo.
    >>  ............................................
```


### Button `leave` — "I'll give you the evening."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `us.future.rejected`, `us.happy.brushed_off` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.us.hurt.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.us.hurt.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.us.hurt.close.leave   [26 chars]
    en  I'll give you the evening.
    >>  ............................................
    pt  Vou te dar a noite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.hurt.leave
WHO    VILLAGER — what the player reads after pressing "I'll give you the evening."
       spoken on: conversations.topic.us.hurt.close, button `leave`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.hurt.leave`: the villager accepts. Subject `us.relationship`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.hurt.leave/1   [16 chars]
    en  ...Aye. Do that.
    >>  ............................................
    pt  ...É. Faça isso.
    >>  ............................................
  dialogue.conversations.us.hurt.leave/2   [41 chars]
    en  Go on, %1$s. I'll be here in the morning.
    >>  ............................................
    pt  Pode ir, %1$s. Estarei aqui de manhã.
    >>  ............................................
  dialogue.conversations.us.hurt.leave/3   [25 chars]
    en  Right. The evening, then.
    >>  ............................................
    pt  Certo. A noite, então.
    >>  ............................................
```

---


## `conversations.us`

**Reached from 63 route(s):** `conversations.arc.us.resume.close` / `say_it_matters`; `conversations.arc.us.resume.close` / `ask_again_later`; `conversations.arc.us.resume.close` / `leave`; `conversations.arc.us.resume.followup` / `leave`; `conversations.arc.us.resume.respond` / `leave`; `conversations.cat.relationships` / `us`; `conversations.scene.firstmet.followup` / `leave`; `conversations.scene.firstmet.long_enough_to_be_a_story.respond` / `leave`; `conversations.scene.firstmet.recent_and_plain.respond` / `leave`; `conversations.scene.future.followup` / `leave`; `conversations.scene.future.next_year.respond` / `leave`; `conversations.scene.future.the_decision_between_us.respond` / `leave` …and 51 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.firstmet.brushed.apologize` — e.g. "...Then say what you remember and I'll stop sulking."
- `conversations.firstmet.brushed.explain` — e.g. "...Differently. Go on, then. I'd like to hear the other version."
- `conversations.firstmet.brushed.leave` — e.g. "True enough. Off you go."
- `conversations.firstmet.disagreement.both` — e.g. "Both, then. It'll be a better story with two endings anyway."
- `conversations.firstmet.disagreement.corrected` — e.g. "...It was, wasn't it. I've had that backwards for years and told it proudly."
- `conversations.firstmet.disagreement.leave` — e.g. "It doesn't. Off you go."
- `conversations.firstmet.impression.fair` — e.g. "You were. I'd have said so at the time if I'd had the nerve."
- `conversations.firstmet.impression.leave` — e.g. "Take it in, then."
- `conversations.firstmet.impression.now` — e.g. "Now I'd not want the version of this village that didn't have you in it."
- `conversations.future.home.build` — e.g. "You say that like it's a small thing. It isn't, and I'm glad you said it."
- `conversations.future.home.leave` — e.g. "Think, then."
- `conversations.future.home.when` — e.g. "After the harvest, if the harvest is kind. Before it, if I lose my nerve."
- `conversations.happy.gratitude.didnt_know` — e.g. "You weren't meant to. I'm telling you because not telling you got heavy."
- `conversations.happy.gratitude.leave` — e.g. "Do."
- …and 34 more pools


```text
POOL   dialogue key: dialogue.conversations.us
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.us
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.us   [21 chars]
    en  Can we talk about us?
    >>  ............................................
    pt  A gente pode falar sobre nós?
    >>  ............................................
```


### Button `happy` — "Are you happy with us?"

Shown only when MCA's own constraints hold: `"spouse"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.happy` — accepted phrasings: "are you happy"; "happy with us"; "happy with me"
  - the message must contain one of: `happy`, `content`, `glad`, `satisfied`
  - scored words: `happy`(1.5), `content`(1.0), `glad`(0.8), `satisfied`(0.8)

```text
POOL   dialogue key: dialogue.conversations.us.happy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.us
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.us.happy   [22 chars]
    en  Are you happy with us?
    >>  ............................................
    pt  Você está feliz com a gente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 7** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.happy.a_good_stretch"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.happy.a_good_stretch", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.scene.happy.a_good_stretch.respond`
- …where the player's next choices will be: "Good. Take the run while it lasts." | "What turned it around?" | "Glad to hear it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.a_good_stretch
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.scene.happy.a_good_stretch.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.a_good_stretch.open`: the villager celebrates. Subject `happy.ordinary`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.a_good_stretch/1   [89 chars]
    en  Four good days in a row, which at my age counts as a run and I am refusing to examine it.
    >>  ............................................
    pt  Quatro dias bons seguidos, o que na minha idade conta como sequência e eu me recuso a examinar.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch/2   [120 chars]
    en  One thing I have been dreading turned out to be twenty minutes of work. I have been unreasonably pleased about it since.
    >>  ............................................
    pt  Uma coisa que eu vinha temendo acabou sendo vinte minutos de trabalho. Estou irracionalmente contente desde então.
    >>  ............................................
  dialogue.conversations.scene.happy.a_good_stretch/3   [98 chars]
    en  Nothing large. The bread came out right and somebody said so, and that has carried the whole week.
    >>  ............................................
    pt  Nada grande. O pão saiu certo e alguém falou, e isso segurou a semana inteira.
    >>  ............................................
```


**Outcome 2 of 7** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.happy.glad_of_you"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.happy.glad_of_you", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.scene.happy.glad_of_you.respond`
- …where the player's next choices will be: "The same is true from here." | "What brought that on?" | "Glad to hear it."

```text
POOL   dialogue key: dialogue.conversations.scene.happy.glad_of_you
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.scene.happy.glad_of_you.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `happy.glad_of_you.open`: the villager reports. Subject `happy.of_the_player`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:happy` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.happy.glad_of_you/1   [134 chars]
    en  I am glad you turned up here. I have been meaning to say that for about two months and have been putting it off because it is awkward.
    >>  ............................................
    pt  Fico feliz que você tenha aparecido aqui. Faz uns dois meses que eu pretendo dizer isso e venho adiando porque é constrangedor.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you/2   [152 chars]
    en  There are four people in this village I am pleased to see coming up the lane, and I am telling you that you are one of them, once, and then never again.
    >>  ............................................
    pt  Existem quatro pessoas nesta vila que eu fico contente de ver subindo a viela, e estou te dizendo que você é uma delas, uma vez, e nunca mais.
    >>  ............................................
  dialogue.conversations.scene.happy.glad_of_you/3   [106 chars]
    en  You have made this place easier to live in. That is a large sentence and I have thought about the wording.
    >>  ............................................
    pt  Você tornou este lugar mais fácil de viver. É uma frase grande e eu pensei na formulação.
    >>  ............................................
```


**Outcome 3 of 7** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.happy` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.topic.happy.respond`
- …where the player's next choices will be: "Tell me honestly." | "I'm happy with us." | "What's that supposed to mean?" | "Is there anything you're grateful for?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.again
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.topic.happy.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.again.to.happy`: the villager accepts. Subject `happy`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.again/1   [79 chars]
    en  You asked me that this morning, love, and the answer hasn't had time to change.
    >>  ............................................
    pt  Você me perguntou isso hoje de manhã, amor, e a resposta não teve tempo de mudar.
    >>  ............................................
  dialogue.conversations.us.happy.again/2   [74 chars]
    en  Still happy. Ask me nightly if you like — I'll not get tired of saying it.
    >>  ............................................
    pt  Continuo feliz. Pergunte toda noite se quiser — não vou me cansar de dizer.
    >>  ............................................
  dialogue.conversations.us.happy.again/3   [73 chars]
    en  Twice in a day? Either you're worried or you're fishing. Either way: yes.
    >>  ............................................
    pt  Duas vezes no mesmo dia? Ou é preocupação, ou é pedido de elogio. De todo jeito: sim.
    >>  ............................................
```


**Outcome 4 of 7** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.state.grateful` (this player only)
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.happy` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `grateful` budget `relationship`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.topic.happy.respond`
- …where the player's next choices will be: "Tell me honestly." | "I'm happy with us." | "What's that supposed to mean?" | "Is there anything you're grateful for?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.grateful
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.topic.happy.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = last_gift_item
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.grateful.to.happy`: the villager accepts. Subject `happy`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.grateful/1   [82 chars]
    en  Happy? You brought me %2$s and asked me that with a straight face. Yes, love. Yes.
    >>  ............................................
    pt  Feliz? Você me trouxe %2$s e me pergunta isso com essa cara séria. Sim, amor. Sim.
    >>  ............................................
  dialogue.conversations.us.happy.grateful/2   [67 chars]
    en  Still smiling about that %2$s, if I'm honest. You know me too well.
    >>  ............................................
    pt  Ainda estou sorrindo por causa daquele %2$s, pra ser sincero. Você me conhece bem demais.
    >>  ............................................
  dialogue.conversations.us.happy.grateful/3   [79 chars]
    en  Between you and that %2$s, I've run clean out of complaints. It's disorienting.
    >>  ............................................
    pt  Entre você e aquele %2$s, fiquei sem reclamações nenhuma. É desorientador.
    >>  ............................................
```


**Outcome 5 of 7** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when has the memory `mcaconversations.state.grateful` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.happy` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `low` budget `relationship`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.topic.happy.respond`
- …where the player's next choices will be: "Tell me honestly." | "I'm happy with us." | "What's that supposed to mean?" | "Is there anything you're grateful for?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.low
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.topic.happy.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.low.to.happy`: the villager accepts. Subject `happy`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.low/1   [66 chars]
    en  I'm happy with you. It's everything else that's been heavy lately.
    >>  ............................................
    pt  Estou feliz com você. É todo o resto que anda pesado.
    >>  ............................................
  dialogue.conversations.us.happy.low/2   [81 chars]
    en  You're the good part, love. The rest of it has just been raining sideways lately.
    >>  ............................................
    pt  Você é a parte boa, amor. O resto é que anda chovendo de lado ultimamente.
    >>  ............................................
  dialogue.conversations.us.happy.low/3   [63 chars]
    en  With us? Always. With everything around us? Ask me after a nap.
    >>  ............................................
    pt  Com a gente? Sempre. Com tudo em volta? Me pergunte depois de uma soneca.
    >>  ............................................
```


**Outcome 6 of 7** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.grateful` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.happy` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `happy` branch `yes` budget `relationship`
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.topic.happy.respond`
- …where the player's next choices will be: "Tell me honestly." | "I'm happy with us." | "What's that supposed to mean?" | "Is there anything you're grateful for?" | "Let's talk later."

```text
POOL   dialogue key: dialogue.conversations.us.happy.yes
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.topic.happy.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.yes.to.happy`: the villager accepts. Subject `happy`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.happy.yes/1   [68 chars]
    en  With you? Yes. Even when you track mud in. Especially then, somehow.
    >>  ............................................
    pt  Com você? Estou. Até quando você entra com lama no pé. Principalmente aí, de algum jeito.
    >>  ............................................
  dialogue.conversations.us.happy.yes/2   [61 chars]
    en  I catch myself humming while I work. That's your fault, %1$s.
    >>  ............................................
    pt  Me pego cantarolando enquanto trabalho. A culpa é sua, %1$s.
    >>  ............................................
  dialogue.conversations.us.happy.yes/3   [66 chars]
    en  Happy enough to hum. And you know how I feel about people who hum.
    >>  ............................................
    pt  Feliz a ponto de cantarolar. E você sabe o que eu acho de gente que cantarola.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.happy.yes/1
    en  Yes. Properly yes. You're the one thing I've stopped worrying about losing, %1$s, and that's everything.
    >>  ............................................
    pt  Sim. Sim de verdade. Você é a única coisa que eu parei de temer perder, %1$s, e isso é tudo.
    >>  ............................................
  anxious.dialogue.conversations.us.happy.yes/2
    en  I am. It still surprises me some mornings, but I am.
    >>  ............................................
    pt  Estou. Ainda me surpreende em algumas manhãs, mas estou.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.yes/1
    en  Happy? You're my favorite finish line and my reason to start again. Yes, easily.
    >>  ............................................
    pt  Feliz? Você é a minha linha de chegada favorita e o meu motivo pra começar de novo. Sim, fácil.
    >>  ............................................
  athletic.dialogue.conversations.us.happy.yes/2
    en  With you I stopped feeling like I was running FROM something. That's rare. That's you.
    >>  ............................................
    pt  Com você eu parei de sentir que estava fugindo DE alguma coisa. Isso é raro. Isso é você.
    >>  ............................................
  confident.dialogue.conversations.us.happy.yes/1
    en  Happiest I've been — and I don't hand out superlatives. You did that, %1$s. Well done.
    >>  ............................................
    pt  O mais feliz que já estive — e eu não distribuo superlativos. Foi você que fez isso, %1$s. Muito bem.
    >>  ............................................
  confident.dialogue.conversations.us.happy.yes/2
    en  I chose you over everyone and I never second-guess. Best decision I ever made.
    >>  ............................................
    pt  Escolhi você acima de todos e eu nunca questiono minhas escolhas. Melhor decisão que já tomei.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.yes/1
    en  ...Yes. Don't make me say it twice, %1$s, and don't go telling anyone.
    >>  ............................................
    pt  ...Estou. Não me faça dizer duas vezes, %1$s, e não sai contando por aí.
    >>  ............................................
  crabby.dialogue.conversations.us.happy.yes/2
    en  I am. Astonishing, I know. You've worn me down entirely and I've stopped minding.
    >>  ............................................
    pt  Estou. Espantoso, eu sei. Você me desgastou por completo e eu parei de me importar.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.yes/1
    en  Happy? %1$s, I'd tell the entire village if you let me. Actually, I think I already have.
    >>  ............................................
    pt  Feliz? %1$s, eu contaria pro vilarejo inteiro se você deixasse. Na verdade, acho que já contei.
    >>  ............................................
  extroverted.dialogue.conversations.us.happy.yes/2
    en  Enormously. You're the conversation I never want to finish, and that's the truest thing I know.
    >>  ............................................
    pt  Enormemente. Você é a conversa que eu nunca quero terminar, e essa é a coisa mais verdadeira que eu sei.
    >>  ............................................
  flirty.dialogue.conversations.us.happy.yes/1
    en  Deliriously. Obviously. Have you seen who I married?
    >>  ............................................
    pt  Delirantemente. Obviamente. Você já viu com quem eu casei?
    >>  ............................................
  flirty.dialogue.conversations.us.happy.yes/2
    en  You're the one I never got tired of. Do you know how rare that is for me? Yes, darling.
    >>  ............................................
    pt  Você é o único de quem eu nunca me cansei. Sabe como isso é raro pra mim? Sim, querido.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.yes/1
    en  Happy? You're the best thing that ever walked through my door. Every single day, yes.
    >>  ............................................
    pt  Feliz? Você é a melhor coisa que já entrou pela minha porta. Todo santo dia, sim.
    >>  ............................................
  friendly.dialogue.conversations.us.happy.yes/2
    en  The luckiest soul in the village, and it's your fault entirely. Yes, love. Yes.
    >>  ............................................
    pt  A alma mais sortuda do vilarejo, e a culpa é inteiramente sua. Sim, amor. Sim.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.yes/1
    en  Happy is a strong word. Content, though. Dangerously close to happy. Because of you.
    >>  ............................................
    pt  Feliz é uma palavra forte. Contente, isso sim. Perigosamente perto de feliz. Por sua causa.
    >>  ............................................
  gloomy.dialogue.conversations.us.happy.yes/2
    en  I catch myself expecting good things when you're about. It's very disorienting.
    >>  ............................................
    pt  Me pego esperando coisas boas quando você está por perto. É muito desorientador.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.yes/1
    en  Happy? You're the one line item I never regret, %1$s. Priceless — and I've priced everything. Yes.
    >>  ............................................
    pt  Feliz? Você é o único item da lista do qual eu nunca me arrependo, %1$s. Inestimável — e eu já pus preço em tudo. Sim.
    >>  ............................................
  greedy.dialogue.conversations.us.happy.yes/2
    en  I'd trade the whole vault for you and call it a bargain. Don't let that get out — it'd ruin my reputation.
    >>  ............................................
    pt  Eu trocaria o cofre inteiro por você e chamaria de pechincha. Não deixa isso vazar — arruinaria a minha reputação.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.yes/1
    en  ...Yes. Don't make me say it twice, %1$s, and don't go telling anyone.
    >>  ............................................
    pt  ...Estou. Não me faça dizer duas vezes, %1$s, e não sai contando por aí.
    >>  ............................................
  grumpy.dialogue.conversations.us.happy.yes/2
    en  I am. Astonishing, I know. You've worn me down entirely and I've stopped minding.
    >>  ............................................
    pt  Estou. Espantoso, eu sei. Você me desgastou por completo e eu parei de me importar.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.yes/1
    en  Yes. Quietly, and completely. I'd not have let anyone this close otherwise, %1$s.
    >>  ............................................
    pt  Sim. Em silêncio, e completamente. Eu não teria deixado ninguém chegar tão perto de outro jeito, %1$s.
    >>  ............................................
  introverted.dialogue.conversations.us.happy.yes/2
    en  I am. You're the only company that doesn't cost me anything to keep.
    >>  ............................................
    pt  Estou. Você é a única companhia que não me custa nada manter.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.yes/1
    en  Very. You're the easiest thing in my life, %1$s, and I don't say that lightly.
    >>  ............................................
    pt  Muito. Você é a coisa mais fácil da minha vida, %1$s, e eu não digo isso à toa.
    >>  ............................................
  lazy.dialogue.conversations.us.happy.yes/2
    en  Yes. Comfortably so. No part of this is hard work, and that's exactly how it should be.
    >>  ............................................
    pt  Sim. Confortavelmente. Nenhuma parte disso dá trabalho, e é exatamente como deveria ser.
    >>  ............................................
  odd.dialogue.conversations.us.happy.yes/1
    en  Happy? The candles burn straight, the well says yes twice, and my odd little world makes sense with you in it. So: yes.
    >>  ............................................
    pt  Feliz? As velas queimam retas, o poço diz sim duas vezes, e o meu mundinho estranho faz sentido com você nele. Então: sim.
    >>  ............................................
  odd.dialogue.conversations.us.happy.yes/2
    en  You're the one thing the omens and I agree on completely, %1$s. That's never happened before. Yes. Yes.
    >>  ............................................
    pt  Você é a única coisa em que os presságios e eu concordamos completamente, %1$s. Isso nunca aconteceu antes. Sim. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.yes/1
    en  Yes, %1$s. Deeply, and without any noise about it. That's the kind that lasts.
    >>  ............................................
    pt  Sim, %1$s. Profundamente, e sem barulho nenhum a respeito. É esse tipo que dura.
    >>  ............................................
  peaceful.dialogue.conversations.us.happy.yes/2
    en  I am. You brought me a stillness I didn't know I was missing.
    >>  ............................................
    pt  Estou. Você me trouxe uma quietude que eu nem sabia que me faltava.
    >>  ............................................
  peppy.dialogue.conversations.us.happy.yes/1
    en  Happy?! %1$s, you're my top one of EVERYTHING! I hum your name at the chickens! ...I've said too much! YES!
    >>  ............................................
    pt  Feliz?! %1$s, você é o meu top um de TUDO! Eu cantarolo o seu nome pras galinhas! ...Falei demais! SIM!
    >>  ............................................
  peppy.dialogue.conversations.us.happy.yes/2
    en  SO happy I made the well echo it back! You're the best thing that ever happened to me, no takebacks, YES!
    >>  ............................................
    pt  TÃO feliz que fiz o poço ecoar de volta! Você é a melhor coisa que já me aconteceu, sem voltar atrás, SIM!
    >>  ............................................
  playful.dialogue.conversations.us.happy.yes/1
    en  Happy? You've no idea. You're the only one who plays along and still means it, %1$s.
    >>  ............................................
    pt  Feliz? Você não faz ideia. Você é o único que entra na brincadeira e ainda assim fala sério, %1$s.
    >>  ............................................
  playful.dialogue.conversations.us.happy.yes/2
    en  Ridiculously so. You're the best game I've ever got mixed up in, and I'm not letting go.
    >>  ............................................
    pt  Ridiculamente. Você é o melhor jogo em que eu já me meti, e eu não vou largar.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.yes/1
    en  Very. You're the easiest thing in my life, %1$s, and I don't say that lightly.
    >>  ............................................
    pt  Muito. Você é a coisa mais fácil da minha vida, %1$s, e eu não digo isso à toa.
    >>  ............................................
  relaxed.dialogue.conversations.us.happy.yes/2
    en  Yes. Comfortably so. No part of this is hard work, and that's exactly how it should be.
    >>  ............................................
    pt  Sim. Confortavelmente. Nenhuma parte disso dá trabalho, e é exatamente como deveria ser.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.yes/1
    en  Happy? You let me be soft without flinching, %1$s. Do you know how safe that feels? Yes. Endlessly yes.
    >>  ............................................
    pt  Feliz? Você me deixa ser sensível sem recuar, %1$s. Sabe o quanto isso parece seguro? Sim. Infinitamente sim.
    >>  ............................................
  sensitive.dialogue.conversations.us.happy.yes/2
    en  With you the tears are mostly the good kind now. That's everything to me. Yes, love.
    >>  ............................................
    pt  Com você as lágrimas agora são quase todas do tipo bom. Isso é tudo pra mim. Sim, amor.
    >>  ............................................
  shy.dialogue.conversations.us.happy.yes/1
    en  Yes. Quietly, and completely. I'd not have let anyone this close otherwise, %1$s.
    >>  ............................................
    pt  Sim. Em silêncio, e completamente. Eu não teria deixado ninguém chegar tão perto de outro jeito, %1$s.
    >>  ............................................
  shy.dialogue.conversations.us.happy.yes/2
    en  I am. You're the only company that doesn't cost me anything to keep.
    >>  ............................................
    pt  Estou. Você é a única companhia que não me custa nada manter.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.yes/1
    en  Happy? Every single day, %1$s. I'd say it more often but you'd stop believing me.
    >>  ............................................
    pt  Feliz? Todo santo dia, %1$s. Eu diria mais vezes, mas você pararia de acreditar em mim.
    >>  ............................................
  upbeat.dialogue.conversations.us.happy.yes/2
    en  Yes. Easily, gladly, and without a moment's doubt. You're the best thing I've said yes to.
    >>  ............................................
    pt  Sim. Fácil, com gosto, e sem um instante de dúvida. Você é a melhor coisa a que eu já disse sim.
    >>  ............................................
  witty.dialogue.conversations.us.happy.yes/1
    en  Happy? Every single day, %1$s. I'd say it more often but you'd stop believing me.
    >>  ............................................
    pt  Feliz? Todo santo dia, %1$s. Eu diria mais vezes, mas você pararia de acreditar em mim.
    >>  ............................................
  witty.dialogue.conversations.us.happy.yes/2
    en  Yes. Easily, gladly, and without a moment's doubt. You're the best thing I've said yes to.
    >>  ............................................
    pt  Sim. Fácil, com gosto, e sem um instante de dúvida. Você é a melhor coisa a que eu já disse sim.
    >>  ............................................
```

</details>


**Outcome 7 of 7** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.cooldown.happy` (this player only) for 36000 ticks
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.happy.yes
WHO    VILLAGER — what the player reads after pressing "Are you happy with us?"
       spoken on: conversations.us, button `happy`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.happy.yes.terminal`: the villager accepts. Subject `us.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.us` / button `happy`** earlier in this file. Fill it in there, once.


### Button `firstmet` — "Remember when we met?"

Shown only when MCA's own constraints hold: `"spouse"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.firstmet` — accepted phrasings: "when we met"; "remember when we met"; "first met"; "how we met"
  - the message must contain one of: `met`, `meet`, `remember`
  - scored words: `met`(1.5), `meet`(1.2), `remember`(1.0), `first`(0.6)

```text
POOL   dialogue key: dialogue.conversations.us.firstmet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.us
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.us.firstmet   [21 chars]
    en  Remember when we met?
    >>  ............................................
    pt  Lembra quando a gente se conheceu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.firstmet.recent_and_plain"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.firstmet.recent_and_plain", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `firstmet` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.scene.firstmet.recent_and_plain.respond`
- …where the player's next choices will be: "What did you make of me?" | "I remember it too." | "Good to remember it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.recent_and_plain
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.scene.firstmet.recent_and_plain.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.recent_and_plain.open`: the villager reports. Subject `firstmet.recent`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.recent_and_plain/1   [113 chars]
    en  You came through the gate and asked a sensible question, which put you ahead of about half the people who arrive.
    >>  ............................................
    pt  Você passou pelo portão e fez uma pergunta sensata, o que já te colocou à frente de metade de quem chega.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain/2   [93 chars]
    en  I remember it exactly, which is only because it was recent and I want to be honest about why.
    >>  ............................................
    pt  Lembro exatamente, e é só porque foi recente, e quero ser honesta sobre o motivo.
    >>  ............................................
  dialogue.conversations.scene.firstmet.recent_and_plain/3   [100 chars]
    en  It was unremarkable and I mean that kindly. The remarkable arrivals are usually somebody in trouble.
    >>  ............................................
    pt  Foi comum, e digo isso com carinho. As chegadas memoráveis costumam ser alguém em apuros.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.firstmet.long_enough_to_be_a_story"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.firstmet.long_enough_to_be_a_story", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `firstmet` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.scene.firstmet.long_enough_to_be_a_story.respond`
- …where the player's next choices will be: "My memory of it differs." | "Let's hear the good line." | "Good to remember it."

```text
POOL   dialogue key: dialogue.conversations.scene.firstmet.long_enough_to_be_a_story
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.scene.firstmet.long_enough_to_be_a_story.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `firstmet.long_enough_to_be_a_story.open`: the villager reminisces. Subject `firstmet.retold`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:firstmet` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story/1   [120 chars]
    en  I have told that story about nine times and I am fairly sure it has improved, which means it is no longer entirely true.
    >>  ............................................
    pt  Já contei essa história umas nove vezes e tenho quase certeza de que ela melhorou, o que quer dizer que não é mais inteiramente verdade.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story/2   [109 chars]
    en  You were carrying something and it was raining, and I have since discovered that neither of those is certain.
    >>  ............................................
    pt  Você carregava alguma coisa e chovia, e desde então eu descobri que nenhuma das duas é certeza.
    >>  ............................................
  dialogue.conversations.scene.firstmet.long_enough_to_be_a_story/3   [87 chars]
    en  The version I tell has a good line in it that I am almost sure I thought of afterwards.
    >>  ............................................
    pt  A versão que eu conto tem uma boa frase que eu tenho quase certeza de ter pensado depois.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.firstmet` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `firstmet` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.topic.firstmet.respond`
- …where the player's next choices will be: "Here's what I remember." | "You were a state, as I recall." | "I don't really remember it." | "What did you make of me, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.again
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.topic.firstmet.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.again.to.firstmet`: the villager accepts. Subject `firstmet`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.again/1   [63 chars]
    en  We just walked down that road, love. But I never mind the view.
    >>  ............................................
    pt  A gente acabou de percorrer essa estrada, amor. Mas eu nunca me canso da vista.
    >>  ............................................
  dialogue.conversations.us.firstmet.again/2   [71 chars]
    en  Again? Alright — you were lost, I was smitten. Short version this time.
    >>  ............................................
    pt  De novo? Tudo bem — você estava perdido, eu estava caidinho. Versão curta dessa vez.
    >>  ............................................
  dialogue.conversations.us.firstmet.again/3   [78 chars]
    en  We told this one already, love. It gets ten percent more heroic every telling.
    >>  ............................................
    pt  Já contamos essa, amor. Ela fica dez por cento mais heroica a cada vez.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when the personality is `sensitive`, `flirty`, `gloomy`, `peaceful`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.firstmet` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `firstmet` branch `memory` budget `relationship`
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.topic.firstmet.respond`
- …where the player's next choices will be: "Here's what I remember." | "You were a state, as I recall." | "I don't really remember it." | "What did you make of me, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.memory
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.topic.firstmet.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = village_name
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.memory.to.firstmet`: the villager accepts. Subject `firstmet`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.memory/1   [96 chars]
    en  Of course. You looked completely lost, and I pretended I wasn't staring. Best day %2$s ever had.
    >>  ............................................
    pt  Claro. Você parecia completamente perdido, e eu fingi que não estava encarando. O melhor dia que %2$s já teve.
    >>  ............................................
  dialogue.conversations.us.firstmet.memory/2   [89 chars]
    en  You had the wrong boots for the weather and the right smile for me. I remember all of it.
    >>  ............................................
    pt  Você estava com a bota errada pro tempo e o sorriso certo pra mim. Lembro de tudo.
    >>  ............................................
  dialogue.conversations.us.firstmet.memory/3   [81 chars]
    en  I remember the exact spot. I walk past it some mornings just to smile at nothing.
    >>  ............................................
    pt  Lembro do lugar exato. Passo por ele em algumas manhãs só pra sorrir do nada.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 1 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  flirty.dialogue.conversations.us.firstmet.memory/1
    en  You stared. Don't rewrite history, %1$s — you stared first, and I let you.
    >>  ............................................
    pt  Você encarou. Não reescreve a história, %1$s — você encarou primeiro, e eu deixei.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.memory/2
    en  You had the wrong boots and exactly the right smile. I remember all of it, %1$s.
    >>  ............................................
    pt  Você estava com a bota errada e exatamente o sorriso certo. Lembro de tudo, %1$s.
    >>  ............................................
```

</details>

> Falls back to the base pool above, no voice of its own here: anxious, athletic, confident, crabby, extroverted, friendly, gloomy, greedy, grumpy, introverted, lazy, odd, peaceful, peppy, playful, relaxed, sensitive, shy, upbeat, witty.


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the personality is `sensitive`, `flirty`, `gloomy`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.firstmet` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `firstmet` branch `tell` budget `relationship`
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.topic.firstmet.respond`
- …where the player's next choices will be: "Here's what I remember." | "You were a state, as I recall." | "I don't really remember it." | "What did you make of me, back then?" | "Another time."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.tell
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.topic.firstmet.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.tell.to.firstmet`: the villager discloses. Subject `firstmet`, polarity `neutral`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.firstmet.tell/1   [67 chars]
    en  You want the story of how we met? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Você quer a história de como nos conhecemos? Certo. Vou contar do meu jeito.
    >>  ............................................
  dialogue.conversations.us.firstmet.tell/2   [58 chars]
    en  The day we met. I remember more of it than I let on, %1$s.
    >>  ............................................
    pt  O dia em que nos conhecemos. Lembro mais do que demonstro, %1$s.
    >>  ............................................
  dialogue.conversations.us.firstmet.tell/3   [39 chars]
    en  Ha. That day. Right — my version first.
    >>  ............................................
    pt  Rá. Aquele dia. Certo — minha versão primeiro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way, %1$s.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.tell/2
    en  The story. I've told it to myself often enough; it'll be strange to say it out loud.
    >>  ............................................
    pt  A história. Já contei pra mim mesmo muitas vezes; vai ser estranho dizer em voz alta.
    >>  ............................................
  anxious.dialogue.conversations.us.firstmet.tell/3
    en  Right. Sit down. I've thought about that day more than I'd want to admit.
    >>  ............................................
    pt  Certo. Sente-se. Eu pensei naquele dia mais do que eu gostaria de admitir.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. It'll take a while and it's worth the while.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Leva um tempo e vale o tempo.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit down; there's no short version and I'd not give you one if there were.
    >>  ............................................
    pt  A história. Sente-se; não tem versão curta e eu não daria se tivesse.
    >>  ............................................
  athletic.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it slowly. Old stories don't improve for being hurried.
    >>  ............................................
    pt  Certo. Vou contar devagar. Histórias velhas não melhoram com pressa.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.tell/2
    en  Right. I'll tell it, and I'll tell it accurately, which you may not enjoy.
    >>  ............................................
    pt  Certo. Eu conto, e conto com exatidão, o que você pode não curtir.
    >>  ............................................
  confident.dialogue.conversations.us.firstmet.tell/3
    en  The story, then. Sit down; there are parts of it you've forgotten.
    >>  ............................................
    pt  A história, então. Sente-se; tem partes que você esqueceu.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.tell/2
    en  Right. I'll tell it, and I'll tell it accurately, which you may not enjoy.
    >>  ............................................
    pt  Certo. Eu conto, e conto com exatidão, o que você pode não curtir.
    >>  ............................................
  crabby.dialogue.conversations.us.firstmet.tell/3
    en  The story, then. Sit down; there are parts of it you've forgotten.
    >>  ............................................
    pt  A história, então. Sente-se; tem partes que você esqueceu.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met, %1$s? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu, %1$s? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit with me. It's a better one than either of us behaved in it.
    >>  ............................................
    pt  A história. Sente comigo. É melhor do que nós dois nos comportamos nela.
    >>  ............................................
  extroverted.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it, and then you tell me yours, and we'll see how different they are.
    >>  ............................................
    pt  Certo. Eu conto, e depois você conta a sua, e a gente vê o quão diferentes são.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met, %1$s? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu, %1$s? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit with me. It's a better one than either of us behaved in it.
    >>  ............................................
    pt  A história. Sente comigo. É melhor do que nós dois nos comportamos nela.
    >>  ............................................
  flirty.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it, and then you tell me yours, and we'll see how different they are.
    >>  ............................................
    pt  Certo. Eu conto, e depois você conta a sua, e a gente vê o quão diferentes são.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met, %1$s? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu, %1$s? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit with me. It's a better one than either of us behaved in it.
    >>  ............................................
    pt  A história. Sente comigo. É melhor do que nós dois nos comportamos nela.
    >>  ............................................
  friendly.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it, and then you tell me yours, and we'll see how different they are.
    >>  ............................................
    pt  Certo. Eu conto, e depois você conta a sua, e a gente vê o quão diferentes são.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way, %1$s.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.tell/2
    en  The story. I've told it to myself often enough; it'll be strange to say it out loud.
    >>  ............................................
    pt  A história. Já contei pra mim mesmo muitas vezes; vai ser estranho dizer em voz alta.
    >>  ............................................
  gloomy.dialogue.conversations.us.firstmet.tell/3
    en  Right. Sit down. I've thought about that day more than I'd want to admit.
    >>  ............................................
    pt  Certo. Sente-se. Eu pensei naquele dia mais do que eu gostaria de admitir.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.tell/2
    en  Right. I'll tell it, and I'll tell it accurately, which you may not enjoy.
    >>  ............................................
    pt  Certo. Eu conto, e conto com exatidão, o que você pode não curtir.
    >>  ............................................
  greedy.dialogue.conversations.us.firstmet.tell/3
    en  The story, then. Sit down; there are parts of it you've forgotten.
    >>  ............................................
    pt  A história, então. Sente-se; tem partes que você esqueceu.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.tell/2
    en  Right. I'll tell it, and I'll tell it accurately, which you may not enjoy.
    >>  ............................................
    pt  Certo. Eu conto, e conto com exatidão, o que você pode não curtir.
    >>  ............................................
  grumpy.dialogue.conversations.us.firstmet.tell/3
    en  The story, then. Sit down; there are parts of it you've forgotten.
    >>  ............................................
    pt  A história, então. Sente-se; tem partes que você esqueceu.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.tell/1
    en  You want the story? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.tell/2
    en  Right. Sit down.
    >>  ............................................
    pt  Certo. Sente-se.
    >>  ............................................
  introverted.dialogue.conversations.us.firstmet.tell/3
    en  The story. It's short, and I remember all of it.
    >>  ............................................
    pt  A história. É curta, e eu lembro de tudo.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. It'll take a while and it's worth the while.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Leva um tempo e vale o tempo.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit down; there's no short version and I'd not give you one if there were.
    >>  ............................................
    pt  A história. Sente-se; não tem versão curta e eu não daria se tivesse.
    >>  ............................................
  lazy.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it slowly. Old stories don't improve for being hurried.
    >>  ............................................
    pt  Certo. Vou contar devagar. Histórias velhas não melhoram com pressa.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.tell/1
    en  You want the story? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.tell/2
    en  Right. Sit down.
    >>  ............................................
    pt  Certo. Sente-se.
    >>  ............................................
  odd.dialogue.conversations.us.firstmet.tell/3
    en  The story. It's short, and I remember all of it.
    >>  ............................................
    pt  A história. É curta, e eu lembro de tudo.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. It'll take a while and it's worth the while.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Leva um tempo e vale o tempo.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit down; there's no short version and I'd not give you one if there were.
    >>  ............................................
    pt  A história. Sente-se; não tem versão curta e eu não daria se tivesse.
    >>  ............................................
  peaceful.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it slowly. Old stories don't improve for being hurried.
    >>  ............................................
    pt  Certo. Vou contar devagar. Histórias velhas não melhoram com pressa.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way and my way is better.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito e o meu é melhor.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.tell/2
    en  Right! The story. Sit down, because I've been waiting to tell this properly for ages.
    >>  ............................................
    pt  Certo! A história. Sente-se, porque eu venho esperando pra contar direito faz tempo.
    >>  ............................................
  peppy.dialogue.conversations.us.firstmet.tell/3
    en  The story! Excellent. I warn you now that I come out of it rather well.
    >>  ............................................
    pt  A história! Excelente. Já aviso que eu saio bem dela.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way and my way is better.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito e o meu é melhor.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.tell/2
    en  Right! The story. Sit down, because I've been waiting to tell this properly for ages.
    >>  ............................................
    pt  Certo! A história. Sente-se, porque eu venho esperando pra contar direito faz tempo.
    >>  ............................................
  playful.dialogue.conversations.us.firstmet.tell/3
    en  The story! Excellent. I warn you now that I come out of it rather well.
    >>  ............................................
    pt  A história! Excelente. Já aviso que eu saio bem dela.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. It'll take a while and it's worth the while.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Leva um tempo e vale o tempo.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.tell/2
    en  The story. Sit down; there's no short version and I'd not give you one if there were.
    >>  ............................................
    pt  A história. Sente-se; não tem versão curta e eu não daria se tivesse.
    >>  ............................................
  relaxed.dialogue.conversations.us.firstmet.tell/3
    en  Right. I'll tell it slowly. Old stories don't improve for being hurried.
    >>  ............................................
    pt  Certo. Vou contar devagar. Histórias velhas não melhoram com pressa.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way, %1$s.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.tell/2
    en  The story. I've told it to myself often enough; it'll be strange to say it out loud.
    >>  ............................................
    pt  A história. Já contei pra mim mesmo muitas vezes; vai ser estranho dizer em voz alta.
    >>  ............................................
  sensitive.dialogue.conversations.us.firstmet.tell/3
    en  Right. Sit down. I've thought about that day more than I'd want to admit.
    >>  ............................................
    pt  Certo. Sente-se. Eu pensei naquele dia mais do que eu gostaria de admitir.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.tell/1
    en  You want the story? Go on, then. I'll tell it my way.
    >>  ............................................
    pt  Quer a história? Vá lá. Vou contar do meu jeito.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.tell/2
    en  Right. Sit down.
    >>  ............................................
    pt  Certo. Sente-se.
    >>  ............................................
  shy.dialogue.conversations.us.firstmet.tell/3
    en  The story. It's short, and I remember all of it.
    >>  ............................................
    pt  A história. É curta, e eu lembro de tudo.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way and my way is better.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito e o meu é melhor.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.tell/2
    en  Right! The story. Sit down, because I've been waiting to tell this properly for ages.
    >>  ............................................
    pt  Certo! A história. Sente-se, porque eu venho esperando pra contar direito faz tempo.
    >>  ............................................
  upbeat.dialogue.conversations.us.firstmet.tell/3
    en  The story! Excellent. I warn you now that I come out of it rather well.
    >>  ............................................
    pt  A história! Excelente. Já aviso que eu saio bem dela.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.tell/1
    en  You want the story of how we met? Go on, then. I'll tell it my way and my way is better.
    >>  ............................................
    pt  Quer a história de como a gente se conheceu? Vá lá. Vou contar do meu jeito e o meu é melhor.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.tell/2
    en  Right! The story. Sit down, because I've been waiting to tell this properly for ages.
    >>  ............................................
    pt  Certo! A história. Sente-se, porque eu venho esperando pra contar direito faz tempo.
    >>  ............................................
  witty.dialogue.conversations.us.firstmet.tell/3
    en  The story! Excellent. I warn you now that I come out of it rather well.
    >>  ............................................
    pt  A história! Excelente. Já aviso que eu saio bem dela.
    >>  ............................................
```

</details>


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.cooldown.firstmet` (this player only) for 36000 ticks
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.firstmet.tell
WHO    VILLAGER — what the player reads after pressing "Remember when we met?"
       spoken on: conversations.us, button `firstmet`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.firstmet.tell.terminal`: the villager discloses. Subject `us.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.us` / button `firstmet`** earlier in this file. Fill it in there, once.


### Button `future` — "What do you want for our future?"

Shown only when MCA's own constraints hold: `"spouse"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.future` — accepted phrasings: "our future"; "for the future"; "future together"
  - the message must contain one of: `future`, `plan`, `tomorrow`, `ahead`
  - scored words: `future`(1.5), `plan`(1.0), `tomorrow`(0.8), `ahead`(0.6)

```text
POOL   dialogue key: dialogue.conversations.us.future
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.us
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.us.future   [32 chars]
    en  What do you want for our future?
    >>  ............................................
    pt  O que você quer pro nosso futuro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.future.next_year"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.future.next_year", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.scene.future.next_year.respond`
- …where the player's next choices will be: "What's the one thing?" | "I hope to still be around for it." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.future.next_year
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.scene.future.next_year.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.next_year.open`: the villager reports. Subject `future.practical`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.next_year/1   [94 chars]
    en  Much like this one, which I say as a plan rather than as resignation, and I mean it as a plan.
    >>  ............................................
    pt  Bem parecido com este, e digo isso como plano e não como resignação, e é plano mesmo.
    >>  ............................................
  dialogue.conversations.scene.future.next_year/2   [109 chars]
    en  There is one thing that will be different and I have not decided it yet, and everything else follows from it.
    >>  ............................................
    pt  Tem uma coisa que vai ser diferente e eu ainda não decidi, e todo o resto decorre disso.
    >>  ............................................
  dialogue.conversations.scene.future.next_year/3   [84 chars]
    en  I stopped making five-year plans after two of them and I have not regretted it once.
    >>  ............................................
    pt  Parei de fazer planos de cinco anos depois de dois deles e nunca me arrependi.
    >>  ............................................
```


**Outcome 2 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.future.the_decision_between_us"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.future.the_decision_between_us", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.scene.future.the_decision_between_us.respond`
- …where the player's next choices will be: "Then let's settle it this week." | "Which way do you lean?" | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.future.the_decision_between_us
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.scene.future.the_decision_between_us.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `future.the_decision_between_us.open`: the villager reports. Subject `future.shared`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:future` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.future.the_decision_between_us/1   [114 chars]
    en  There is a decision the two of us keep almost making, and I would rather make it badly than keep almost making it.
    >>  ............................................
    pt  Existe uma decisão que nós dois quase tomamos toda vez, e eu prefiro tomar mal a continuar quase tomando.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us/2   [128 chars]
    en  We have talked around it four times. I am raising it plainly because the talking around is costing more than the decision would.
    >>  ............................................
    pt  Já demos volta nisso quatro vezes. Estou levantando com franqueza porque dar voltas custa mais do que a decisão custaria.
    >>  ............................................
  dialogue.conversations.scene.future.the_decision_between_us/3   [112 chars]
    en  I know which way I lean and I have not said, because I wanted to hear where you were before I put a thumb on it.
    >>  ............................................
    pt  Eu sei para que lado eu pendo e não disse, porque queria ouvir onde você estava antes de pôr o polegar na balança.
    >>  ............................................
```


**Outcome 3 of 8** — base weight `0`

- Fires when: weighted +100 when arc `us` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.future` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `resume` budget `relationship`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.arc.us.resume.respond`
- …where the player's next choices will be: "Where has it got to, for you?" | "I meant what I said. I still do." | "Honestly? I've let it drift." | "I'll leave it with you."

```text
POOL   dialogue key: dialogue.conversations.us.revisit
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.arc.us.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.resume.opener`: the villager reminisces. Subject `future.revisit`, polarity `mixed`, invites followup, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.us.revisit/1   [77 chars]
    en  I've been thinking about what we settled, you and I. It hasn't left me alone.
    >>  ............................................
    pt  Venho pensando no que a gente resolveu, você e eu. Não me deixou em paz.
    >>  ............................................
  dialogue.conversations.us.revisit/2   [68 chars]
    en  That thing we landed on last time — I've been turning it over since.
    >>  ............................................
    pt  Aquilo em que a gente parou da última vez — venho remoendo desde então.
    >>  ............................................
  dialogue.conversations.us.revisit/3   [73 chars]
    en  We left something half-said. I'd rather not leave it there another month.
    >>  ............................................
    pt  A gente deixou algo pela metade. Prefiro não deixar assim mais um mês.
    >>  ............................................
```


**Outcome 4 of 8** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.future` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.topic.future.respond`
- …where the player's next choices will be: "I want the same thing." | "What matters most to you?" | "Let's not plan that far ahead." | "Where would we live?" | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.again
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.topic.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.again.to.future`: the villager accepts. Subject `future`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.again/1   [69 chars]
    en  We just planned the whole of it, love. Give the plan a day to settle.
    >>  ............................................
    pt  A gente acabou de planejar tudo, amor. Deixa o plano assentar um dia.
    >>  ............................................
  dialogue.conversations.us.future.again/2   [85 chars]
    en  Same future as an hour ago. It's a slow thing to build, and I've not been idle since.
    >>  ............................................
    pt  Mesmo futuro de uma hora atrás. É coisa lenta de construir, e eu não parei de pensar nele desde então.
    >>  ............................................
  dialogue.conversations.us.future.again/3   [73 chars]
    en  You'll have me redrawing the whole life by supper. Ask me again tomorrow.
    >>  ............................................
    pt  Você vai me fazer redesenhar a vida inteira antes da janta. Pergunte de novo amanhã.
    >>  ............................................
```


**Outcome 5 of 8** — base weight `0`

- Fires when: weighted +100 when the villager is pregnant
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.future` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `baby` budget `relationship`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.topic.future.respond`
- …where the player's next choices will be: "I want the same thing." | "What matters most to you?" | "Let's not plan that far ahead." | "Where would we live?" | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.baby
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.topic.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.baby.to.future`: the villager accepts. Subject `future`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.baby/1   [81 chars]
    en  Right now? I mostly think about the little one coming. Terrified. Thrilled. Both.
    >>  ............................................
    pt  Agora? Penso mais no pequeno que está vindo. Apavorado. Empolgado. Os dois.
    >>  ............................................
  dialogue.conversations.us.future.baby/2   [84 chars]
    en  I keep talking to the little one. Mostly apologies for the world, and one good joke.
    >>  ............................................
    pt  Fico conversando com o pequeno. Quase tudo desculpas pelo mundo, e uma piada boa.
    >>  ............................................
  dialogue.conversations.us.future.baby/3   [65 chars]
    en  A name. We need a name, and the courage to argue about it kindly.
    >>  ............................................
    pt  Um nome. Precisamos de um nome, e da coragem de discutir sobre ele com carinho.
    >>  ............................................
```


**Outcome 6 of 8** — base weight `0`

- Fires when: weighted +100 when `constraints` = "kids"
- Fires when: RULED OUT when the villager is pregnant  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.future` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `kids` budget `relationship`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.topic.future.respond`
- …where the player's next choices will be: "I want the same thing." | "What matters most to you?" | "Let's not plan that far ahead." | "Where would we live?" | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.kids
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.topic.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.kids.to.future`: the villager accepts. Subject `future`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.kids/1   [77 chars]
    en  I want the kids to have it easier than we did. That's the whole plan, really.
    >>  ............................................
    pt  Quero que as crianças tenham a vida mais fácil que a nossa. É esse o plano todo, na real.
    >>  ............................................
  dialogue.conversations.us.future.kids/2   [84 chars]
    en  I want the kids to inherit a full pantry and none of my stubbornness. Half's likely.
    >>  ............................................
    pt  Quero que as crianças herdem uma despensa cheia e nada da minha teimosia. Metade é provável.
    >>  ............................................
  dialogue.conversations.us.future.kids/3   [63 chars]
    en  Watching them grow is the future. Everything else is furniture.
    >>  ............................................
    pt  Ver eles crescerem é o futuro. Todo o resto é mobília.
    >>  ............................................
```


**Outcome 7 of 8** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the villager is pregnant  _(chance -2000)_
- Fires when: RULED OUT when `constraints` = "kids"  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.future` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `future` branch `together` budget `relationship`
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.topic.future.respond`
- …where the player's next choices will be: "I want the same thing." | "What matters most to you?" | "Let's not plan that far ahead." | "Where would we live?" | "We'll come back to this."

```text
POOL   dialogue key: dialogue.conversations.us.future.together
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.topic.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.together.to.future`: the villager accepts. Subject `future`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.future.together/1   [71 chars]
    en  More mornings like this one. Maybe a bigger table. People to sit at it.
    >>  ............................................
    pt  Mais manhãs como essa. Talvez uma mesa maior. Gente pra sentar nela.
    >>  ............................................
  dialogue.conversations.us.future.together/2   [79 chars]
    en  I want us old and insufferable on a porch somewhere, judging everyone's fences.
    >>  ............................................
    pt  Quero a gente velho e insuportável numa varanda, julgando a cerca dos outros.
    >>  ............................................
  dialogue.conversations.us.future.together/3   [71 chars]
    en  A garden big enough to argue about. That's the dream, and you're in it.
    >>  ............................................
    pt  Uma horta grande o bastante pra dar discussão. Esse é o sonho, e você está nele.
    >>  ............................................
```


**Outcome 8 of 8** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.cooldown.future` (this player only) for 36000 ticks
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.future.together
WHO    VILLAGER — what the player reads after pressing "What do you want for our future?"
       spoken on: conversations.us, button `future`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.future.together.terminal`: the villager accepts. Subject `us.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.us` / button `future`** earlier in this file. Fill it in there, once.


### Button `worries` — "Is anything weighing on you?"

Shown only when MCA's own constraints hold: `"spouse"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `us.worries` — accepted phrasings: "weighing on you"; "on your mind"; "bothering you"; "anything wrong"
  - the message must contain one of: `worry`, `weigh`, `bother`, `trouble`
  - scored words: `worry`(1.2), `weigh`(1.2), `bother`(1.0), `trouble`(0.8), `mind`(0.6)

```text
POOL   dialogue key: dialogue.conversations.us.worries
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.us
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.us.worries   [28 chars]
    en  Is anything weighing on you?
    >>  ............................................
    pt  Tem alguma coisa te pesando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.worries.the_practical_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.worries.the_practical_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `worries` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.scene.worries.the_practical_one.respond`
- …where the player's next choices will be: "Tell me what would actually help." | "What's the worst case?" | "Thanks for saying."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_practical_one
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.scene.worries.the_practical_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_practical_one.open`: the villager reports. Subject `worries.practical`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_practical_one/1   [108 chars]
    en  A thing that is probably fine and that I have checked four times, which tells you it is not about the thing.
    >>  ............................................
    pt  Uma coisa que provavelmente está bem e que eu conferi quatro vezes, o que já mostra que não é sobre a coisa.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one/2   [107 chars]
    en  Money, in the ordinary way. Not a crisis. Just the arithmetic being tighter than I would like for a season.
    >>  ............................................
    pt  Dinheiro, do jeito comum. Não é crise. Só a conta mais apertada do que eu gostaria por uma estação.
    >>  ............................................
  dialogue.conversations.scene.worries.the_practical_one/3   [93 chars]
    en  I am waiting on somebody else's decision and I hate that more than I would hate a bad answer.
    >>  ............................................
    pt  Estou esperando a decisão de outra pessoa e eu odeio isso mais do que odiaria uma resposta ruim.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.worries.the_late_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.worries.the_late_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `worries` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.scene.worries.the_late_one.respond`
- …where the player's next choices will be: "That is a lot to carry by yourself." | "Would telling them help?" | "Thanks for saying."

```text
POOL   dialogue key: dialogue.conversations.scene.worries.the_late_one
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.scene.worries.the_late_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `worries.the_late_one.open`: the villager reports. Subject `worries.private`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:worries` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.worries.the_late_one/1   [97 chars]
    en  I worry about somebody who is fine, constantly, and I have never told them and I am not going to.
    >>  ............................................
    pt  Eu me preocupo constantemente com alguém que está bem, e nunca contei a essa pessoa e não vou contar.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one/2   [129 chars]
    en  There is a thing I check on that does not need checking on, three times a week, and I have started walking a longer way to do it.
    >>  ............................................
    pt  Existe uma coisa que eu confiro e que não precisa ser conferida, três vezes por semana, e passei a dar uma volta maior para fazer isso.
    >>  ............................................
  dialogue.conversations.scene.worries.the_late_one/3   [107 chars]
    en  It is about a person, and it is not about anything they have done, and that is why it will not go anywhere.
    >>  ............................................
    pt  É sobre uma pessoa, e não é sobre nada que ela tenha feito, e é por isso que não vai a lugar nenhum.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.worries` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `worries` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.topic.worries.respond`
- …where the player's next choices will be: "That's worth worrying about." | "Tell me the rest of it." | "That's nothing to fret over." | "Would you rather I just listened?" | "I'll let you think."

```text
POOL   dialogue key: dialogue.conversations.us.worries.again
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.topic.worries.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.again.to.worries`: the villager accepts. Subject `worries`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.again/1   [56 chars]
    en  You checked on me already, love. I promise I'd tell you.
    >>  ............................................
    pt  Você já veio saber de mim, amor. Prometo que eu te contaria.
    >>  ............................................
  dialogue.conversations.us.worries.again/2   [59 chars]
    en  Still fine, love. Checking twice doesn't double the answer.
    >>  ............................................
    pt  Continuo bem, amor. Perguntar duas vezes não dobra a resposta.
    >>  ............................................
  dialogue.conversations.us.worries.again/3   [73 chars]
    en  If something were wrong, you'd hear it through the wall before you asked.
    >>  ............................................
    pt  Se tivesse algo errado, você ouviria pela parede antes de perguntar.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.worries` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `worries` branch `open` budget `relationship`
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.topic.worries.respond`
- …where the player's next choices will be: "That's worth worrying about." | "Tell me the rest of it." | "That's nothing to fret over." | "Would you rather I just listened?" | "I'll let you think."

```text
POOL   dialogue key: dialogue.conversations.us.worries.open
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.topic.worries.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.open.to.worries`: the villager accepts. Subject `worries`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.open/1   [81 chars]
    en  Since you ask... yes. I didn't want to burden you, but yes. Sit with me a moment.
    >>  ............................................
    pt  Já que você pergunta... tem sim. Eu não queria te pesar, mas tem. Senta aqui comigo um pouco.
    >>  ............................................
  dialogue.conversations.us.worries.open/2   [75 chars]
    en  There's a weight I've been carrying quietly. I should have told you sooner.
    >>  ............................................
    pt  Tem um peso que eu venho carregando calado. Devia ter te contado antes.
    >>  ............................................
  dialogue.conversations.us.worries.open/3   [79 chars]
    en  There is something. I've been rehearsing how to say it for a week. Sit with me.
    >>  ............................................
    pt  Tem uma coisa, sim. Faz uma semana que eu ensaio como falar. Senta aqui comigo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 1 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  gloomy.dialogue.conversations.us.worries.open/1
    en  Everything weighs, love. But since it's you asking... sit. I'll share the heavy end.
    >>  ............................................
    pt  Tudo pesa, amor. Mas já que é você perguntando... senta. Eu divido a ponta pesada.
    >>  ............................................
  gloomy.dialogue.conversations.us.worries.open/2
    en  There's one thing, and it's been growing since spring. Sit down. I'll say it badly and only once.
    >>  ............................................
    pt  Tem uma coisa, e ela vem crescendo desde a primavera. Senta. Vou dizer mal e uma vez só.
    >>  ............................................
```

</details>

> Falls back to the base pool above, no voice of its own here: anxious, athletic, confident, crabby, extroverted, flirty, friendly, greedy, grumpy, introverted, lazy, odd, peaceful, peppy, playful, relaxed, sensitive, shy, upbeat, witty.


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.worries` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `worries` branch `fine` budget `relationship`
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.topic.worries.respond`
- …where the player's next choices will be: "That's worth worrying about." | "Tell me the rest of it." | "That's nothing to fret over." | "Would you rather I just listened?" | "I'll let you think."

```text
POOL   dialogue key: dialogue.conversations.us.worries.fine
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.topic.worries.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.fine.to.worries`: the villager accepts. Subject `worries`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.us.worries.fine/1   [75 chars]
    en  Nothing worth your frown. The usual aches. Having you ask is half the cure.
    >>  ............................................
    pt  Nada que mereça essa sua testa franzida. As dores de sempre. Você perguntar já é metade da cura.
    >>  ............................................
  dialogue.conversations.us.worries.fine/2   [63 chars]
    en  Just the knees and the weather. Both complain, neither listens.
    >>  ............................................
    pt  Só os joelhos e o tempo. Os dois reclamam, nenhum escuta.
    >>  ............................................
  dialogue.conversations.us.worries.fine/3   [90 chars]
    en  Nothing today. Keep asking though — someday the answer changes, and I'd want it to be you.
    >>  ............................................
    pt  Hoje nada. Mas continue perguntando — um dia a resposta muda, e eu ia querer que fosse você.
    >>  ............................................
```


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.cooldown.worries` (this player only) for 36000 ticks
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.worries.fine
WHO    VILLAGER — what the player reads after pressing "Is anything weighing on you?"
       spoken on: conversations.us, button `worries`
       leaves the player on: conversations.us
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `us.worries.fine.terminal`: the villager accepts. Subject `us.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.us` / button `worries`** earlier in this file. Fill it in there, once.


### Button `back` — "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.us.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.us
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.us.back   [32 chars]
    en  Let's talk about something else.
    >>  ............................................
    pt  Vamos falar de outra coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.relationships`
- …where the player's next choices will be: "Can we talk about us?" | "How's the family?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---

