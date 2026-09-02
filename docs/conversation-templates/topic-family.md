# Topic: family

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.arc.family.resume.close`](#conversations-arc-family-resume-close)
- [`conversations.arc.family.resume.followup`](#conversations-arc-family-resume-followup)
- [`conversations.arc.family.resume.respond`](#conversations-arc-family-resume-respond)
- [`conversations.family`](#conversations-family)
- [`conversations.topic.family.close`](#conversations-topic-family-close)
- [`conversations.topic.family.scolded.close`](#conversations-topic-family-scolded-close)

---

## `conversations.arc.family.resume.close`

**Reached from 2 route(s):** `conversations.arc.family.resume.followup` / `offer_to_help`; `conversations.arc.family.resume.followup` / `just_listen`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.resume.just_listen` — e.g. "...That is better. I hadn't known I was allowed to want that one."
- `conversations.family.resume.offer_to_help` — e.g. "...Actually help. Nobody says actually. They say 'anything at all' and mean nothing."


```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.family.resume.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.family.resume.close   [35 chars]
    en  Understood. That's that, for today.
    >>  ............................................
    pt  Entendido. É isso, por hoje.
    >>  ............................................
```


### Button `say_it_matters` — "I'm glad you told me."

*stance family `encouragement` · tone `plain` · answers the beat(s) `family.resume.offer_to_help`, `family.resume.just_listen`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.say_it_matters` — accepted phrasings: "i am glad you told me"; "thank you for telling me"; "i am glad you said something"
  - the message must contain one of: `glad`, `told`, `you`
  - scored words: `glad`(1.5), `told`(1.0), `you`(0.2)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.close.say_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.close.say_it_matters   [21 chars]
    en  I'm glad you told me.
    >>  ............................................
    pt  Fico contente que você tenha me contado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `family.resume.say_it_matters`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `family.resume.say_it_matters`)_
- Does: arc `family` — advance to stage 3
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.resume.say_it_matters
WHO    VILLAGER — what the player reads after pressing "I'm glad you told me."
       spoken on: conversations.arc.family.resume.close, button `say_it_matters`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.say_it_matters`: the villager accepts. Subject `memories.revisit`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.say_it_matters/1   [71 chars]
    en  ...Glad. Right. I'd braced for the other thing, so that's landed oddly.
    >>  ............................................
    pt  ...Contente. Certo. Eu tinha me preparado pra outra coisa, então isso caiu estranho.
    >>  ............................................
  dialogue.conversations.family.resume.say_it_matters/2   [46 chars]
    en  So am I, and I'd not have said so an hour ago.
    >>  ............................................
    pt  Eu também, e eu não teria dito isso uma hora atrás.
    >>  ............................................
  dialogue.conversations.family.resume.say_it_matters/3   [52 chars]
    en  Then it was worth saying. I wasn't sure it would be.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza que valeria.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing, and I'd braced quite thoroughly.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa, e bem a fundo.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. I'd not have said so an hour ago and I'm not entirely sure what changed.
    >>  ............................................
    pt  Eu também. Eu não teria dito isso uma hora atrás e não sei bem o que mudou.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I'd been about ninety per cent sure it wouldn't be.
    >>  ............................................
    pt  Então valeu dizer. Eu tinha uns noventa por cento de certeza de que não valeria.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing, as one does after enough years.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa, como se faz depois de anos.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. It'll sit better in a week than it does tonight, in my experience.
    >>  ............................................
    pt  Eu também. Vai assentar melhor em uma semana do que hoje, na minha experiência.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Most things are, eventually, if you wait long enough to say them.
    >>  ............................................
    pt  Então valeu dizer. Quase tudo vale, uma hora, se você esperar o bastante pra dizer.
    >>  ............................................
  confident.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  confident.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I, and I'd not have said so an hour ago.
    >>  ............................................
    pt  Eu também, e eu não teria dito isso uma hora atrás.
    >>  ............................................
  confident.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I, and I'd not have said so an hour ago.
    >>  ............................................
    pt  Eu também, e eu não teria dito isso uma hora atrás.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. And I'd not have said so an hour ago, which is your doing.
    >>  ............................................
    pt  Eu também. E eu não teria dito isso uma hora atrás, e é obra sua.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Thank you for making it worth saying.
    >>  ............................................
    pt  Então valeu dizer. Obrigado por fazer valer.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. And I'd not have said so an hour ago, which is your doing.
    >>  ............................................
    pt  Eu também. E eu não teria dito isso uma hora atrás, e é obra sua.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Thank you for making it worth saying.
    >>  ............................................
    pt  Então valeu dizer. Obrigado por fazer valer.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. And I'd not have said so an hour ago, which is your doing.
    >>  ............................................
    pt  Eu também. E eu não teria dito isso uma hora atrás, e é obra sua.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Thank you for making it worth saying.
    >>  ............................................
    pt  Então valeu dizer. Obrigado por fazer valer.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing, and I'd braced quite thoroughly.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa, e bem a fundo.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. I'd not have said so an hour ago and I'm not entirely sure what changed.
    >>  ............................................
    pt  Eu também. Eu não teria dito isso uma hora atrás e não sei bem o que mudou.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I'd been about ninety per cent sure it wouldn't be.
    >>  ............................................
    pt  Então valeu dizer. Eu tinha uns noventa por cento de certeza de que não valeria.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I, and I'd not have said so an hour ago.
    >>  ............................................
    pt  Eu também, e eu não teria dito isso uma hora atrás.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I, and I'd not have said so an hour ago.
    >>  ............................................
    pt  Eu também, e eu não teria dito isso uma hora atrás.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right.
    >>  ............................................
    pt  ...Contente. Certo.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying.
    >>  ............................................
    pt  Então valeu dizer.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing, as one does after enough years.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa, como se faz depois de anos.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. It'll sit better in a week than it does tonight, in my experience.
    >>  ............................................
    pt  Eu também. Vai assentar melhor em uma semana do que hoje, na minha experiência.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Most things are, eventually, if you wait long enough to say them.
    >>  ............................................
    pt  Então valeu dizer. Quase tudo vale, uma hora, se você esperar o bastante pra dizer.
    >>  ............................................
  odd.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right.
    >>  ............................................
    pt  ...Contente. Certo.
    >>  ............................................
  odd.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  odd.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying.
    >>  ............................................
    pt  Então valeu dizer.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing, as one does after enough years.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa, como se faz depois de anos.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. It'll sit better in a week than it does tonight, in my experience.
    >>  ............................................
    pt  Eu também. Vai assentar melhor em uma semana do que hoje, na minha experiência.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Most things are, eventually, if you wait long enough to say them.
    >>  ............................................
    pt  Então valeu dizer. Quase tudo vale, uma hora, se você esperar o bastante pra dizer.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad! Right. I'd braced for the other thing entirely.
    >>  ............................................
    pt  Contente! Certo. Eu tinha me preparado pra outra coisa completamente.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I! And I'd not have said so an hour ago, which is a very fast hour.
    >>  ............................................
    pt  Eu também! E eu não teria dito isso uma hora atrás, que foi uma hora bem rápida.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be. Delightful to be wrong.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza. Que delícia estar errado.
    >>  ............................................
  playful.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad! Right. I'd braced for the other thing entirely.
    >>  ............................................
    pt  Contente! Certo. Eu tinha me preparado pra outra coisa completamente.
    >>  ............................................
  playful.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I! And I'd not have said so an hour ago, which is a very fast hour.
    >>  ............................................
    pt  Eu também! E eu não teria dito isso uma hora atrás, que foi uma hora bem rápida.
    >>  ............................................
  playful.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be. Delightful to be wrong.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza. Que delícia estar errado.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad. Right. I'd braced for the other thing, as one does after enough years.
    >>  ............................................
    pt  Contente. Certo. Eu tinha me preparado pra outra coisa, como se faz depois de anos.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. It'll sit better in a week than it does tonight, in my experience.
    >>  ............................................
    pt  Eu também. Vai assentar melhor em uma semana do que hoje, na minha experiência.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. Most things are, eventually, if you wait long enough to say them.
    >>  ............................................
    pt  Então valeu dizer. Quase tudo vale, uma hora, se você esperar o bastante pra dizer.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right, %1$s. I'd braced for the other thing, and I'd braced quite thoroughly.
    >>  ............................................
    pt  ...Contente. Certo, %1$s. Eu tinha me preparado pra outra coisa, e bem a fundo.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I. I'd not have said so an hour ago and I'm not entirely sure what changed.
    >>  ............................................
    pt  Eu também. Eu não teria dito isso uma hora atrás e não sei bem o que mudou.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I'd been about ninety per cent sure it wouldn't be.
    >>  ............................................
    pt  Então valeu dizer. Eu tinha uns noventa por cento de certeza de que não valeria.
    >>  ............................................
  shy.dialogue.conversations.family.resume.say_it_matters/1
    en  ...Glad. Right.
    >>  ............................................
    pt  ...Contente. Certo.
    >>  ............................................
  shy.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I.
    >>  ............................................
    pt  Eu também.
    >>  ............................................
  shy.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying.
    >>  ............................................
    pt  Então valeu dizer.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad! Right. I'd braced for the other thing entirely.
    >>  ............................................
    pt  Contente! Certo. Eu tinha me preparado pra outra coisa completamente.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I! And I'd not have said so an hour ago, which is a very fast hour.
    >>  ............................................
    pt  Eu também! E eu não teria dito isso uma hora atrás, que foi uma hora bem rápida.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be. Delightful to be wrong.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza. Que delícia estar errado.
    >>  ............................................
  witty.dialogue.conversations.family.resume.say_it_matters/1
    en  Glad! Right. I'd braced for the other thing entirely.
    >>  ............................................
    pt  Contente! Certo. Eu tinha me preparado pra outra coisa completamente.
    >>  ............................................
  witty.dialogue.conversations.family.resume.say_it_matters/2
    en  So am I! And I'd not have said so an hour ago, which is a very fast hour.
    >>  ............................................
    pt  Eu também! E eu não teria dito isso uma hora atrás, que foi uma hora bem rápida.
    >>  ............................................
  witty.dialogue.conversations.family.resume.say_it_matters/3
    en  Then it was worth saying. I wasn't sure it would be. Delightful to be wrong.
    >>  ............................................
    pt  Então valeu dizer. Eu não tinha certeza. Que delícia estar errado.
    >>  ............................................
```

</details>


### Button `ask_again_later` — "I'll ask how it's going next time."

*stance family `curiosity` · tone `plain` · answers the beat(s) `family.resume.offer_to_help`, `family.resume.just_listen`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.ask_again_later` — accepted phrasings: "i will ask how it is going next time"; "i will check in again"; "i will ask again next time"
  - the message must contain one of: `asking`, `going`, `next`
  - scored words: `asking`(1.2), `going`(1.0), `next`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.close.ask_again_later
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.close.ask_again_later   [34 chars]
    en  I'll ask how it's going next time.
    >>  ............................................
    pt  Vou perguntar como vai da próxima.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `family.resume.ask_again_later`)_
- Does: arc `family` — advance to stage 3
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.resume.ask_again_later
WHO    VILLAGER — what the player reads after pressing "I'll ask how it's going next time."
       spoken on: conversations.arc.family.resume.close, button `ask_again_later`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.ask_again_later`: the villager accepts. Subject `memories.revisit`, polarity `mixed`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.ask_again_later/1   [67 chars]
    en  ...Next time. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  ...Da próxima. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  dialogue.conversations.family.resume.ask_again_later/2   [57 chars]
    en  Do. It's easier to answer the second time than the first.
    >>  ............................................
    pt  Faça. É mais fácil responder na segunda vez que na primeira.
    >>  ............................................
  dialogue.conversations.family.resume.ask_again_later/3   [84 chars]
    en  Fair. I'll have something to tell you, then. That's a good reason to have something.
    >>  ............................................
    pt  Justo. Aí eu vou ter algo pra te contar. É um bom motivo pra ter algo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice, and I've counted.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes, e eu contei.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. If you don't I'll not raise it, and then it'll simply stop existing.
    >>  ............................................
    pt  Certo. Me pergunte. Se não perguntar eu não levanto, e aí simplesmente deixa de existir.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Please. The first time is the hard one and you've already done that.
    >>  ............................................
    pt  Faça. Por favor. A primeira vez é a difícil e você já fez.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. Doing it twice is what turns it into something.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Fazer duas vezes é o que transforma em algo.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me in a month or a season; it'll keep either way.
    >>  ............................................
    pt  Certo. Me pergunte em um mês ou numa estação; espera de todo jeito.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Slowly. These conversations get easier the more of them there have been.
    >>  ............................................
    pt  Faça. Devagar. Essas conversas ficam mais fáceis quanto mais houve.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'll have something to say by then.
    >>  ............................................
    pt  Certo. Me pergunte. Até lá eu vou ter algo a dizer.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier to answer the second time.
    >>  ............................................
    pt  Faça. É mais fácil responder na segunda vez.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'll have something to say by then.
    >>  ............................................
    pt  Certo. Me pergunte. Até lá eu vou ter algo a dizer.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier to answer the second time.
    >>  ............................................
    pt  Faça. É mais fácil responder na segunda vez.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'd like to be asked, and I'd not have said so a year ago.
    >>  ............................................
    pt  Certo. Me pergunte. Eu gosto de ser perguntado, e eu não teria dito isso um ano atrás.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. And I'll have something to tell you, which is a good reason to have something.
    >>  ............................................
    pt  Faça. E eu vou ter algo pra te contar, o que é um bom motivo pra ter algo.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'd like to be asked, and I'd not have said so a year ago.
    >>  ............................................
    pt  Certo. Me pergunte. Eu gosto de ser perguntado, e eu não teria dito isso um ano atrás.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. And I'll have something to tell you, which is a good reason to have something.
    >>  ............................................
    pt  Faça. E eu vou ter algo pra te contar, o que é um bom motivo pra ter algo.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'd like to be asked, and I'd not have said so a year ago.
    >>  ............................................
    pt  Certo. Me pergunte. Eu gosto de ser perguntado, e eu não teria dito isso um ano atrás.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. And I'll have something to tell you, which is a good reason to have something.
    >>  ............................................
    pt  Faça. E eu vou ter algo pra te contar, o que é um bom motivo pra ter algo.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice, and I've counted.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes, e eu contei.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. If you don't I'll not raise it, and then it'll simply stop existing.
    >>  ............................................
    pt  Certo. Me pergunte. Se não perguntar eu não levanto, e aí simplesmente deixa de existir.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Please. The first time is the hard one and you've already done that.
    >>  ............................................
    pt  Faça. Por favor. A primeira vez é a difícil e você já fez.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'll have something to say by then.
    >>  ............................................
    pt  Certo. Me pergunte. Até lá eu vou ter algo a dizer.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier to answer the second time.
    >>  ............................................
    pt  Faça. É mais fácil responder na segunda vez.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. You'd be the first who did it twice.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. I'll have something to say by then.
    >>  ............................................
    pt  Certo. Me pergunte. Até lá eu vou ter algo a dizer.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier to answer the second time.
    >>  ............................................
    pt  Faça. É mais fácil responder na segunda vez.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me.
    >>  ............................................
    pt  Certo. Me pergunte.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier the second time.
    >>  ............................................
    pt  Faça. É mais fácil na segunda vez.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. Doing it twice is what turns it into something.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Fazer duas vezes é o que transforma em algo.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me in a month or a season; it'll keep either way.
    >>  ............................................
    pt  Certo. Me pergunte em um mês ou numa estação; espera de todo jeito.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Slowly. These conversations get easier the more of them there have been.
    >>  ............................................
    pt  Faça. Devagar. Essas conversas ficam mais fáceis quanto mais houve.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me.
    >>  ............................................
    pt  Certo. Me pergunte.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier the second time.
    >>  ............................................
    pt  Faça. É mais fácil na segunda vez.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. Doing it twice is what turns it into something.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Fazer duas vezes é o que transforma em algo.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me in a month or a season; it'll keep either way.
    >>  ............................................
    pt  Certo. Me pergunte em um mês ou numa estação; espera de todo jeito.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Slowly. These conversations get easier the more of them there have been.
    >>  ............................................
    pt  Faça. Devagar. Essas conversas ficam mais fáceis quanto mais houve.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time! People say that. You'd be the first who actually did it twice.
    >>  ............................................
    pt  Da próxima! As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right — ask me. I'll have something to report and I do love having something to report.
    >>  ............................................
    pt  Certo — me pergunte. Vou ter o que relatar e eu adoro ter o que relatar.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do! It's much easier to answer the second time. The first one is always dreadful.
    >>  ............................................
    pt  Faça! É bem mais fácil responder na segunda. A primeira é sempre horrível.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time! People say that. You'd be the first who actually did it twice.
    >>  ............................................
    pt  Da próxima! As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_again_later/2
    en  Right — ask me. I'll have something to report and I do love having something to report.
    >>  ............................................
    pt  Certo — me pergunte. Vou ter o que relatar e eu adoro ter o que relatar.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_again_later/3
    en  Do! It's much easier to answer the second time. The first one is always dreadful.
    >>  ............................................
    pt  Faça! É bem mais fácil responder na segunda. A primeira é sempre horrível.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that. Doing it twice is what turns it into something.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso. Fazer duas vezes é o que transforma em algo.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me in a month or a season; it'll keep either way.
    >>  ............................................
    pt  Certo. Me pergunte em um mês ou numa estação; espera de todo jeito.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Slowly. These conversations get easier the more of them there have been.
    >>  ............................................
    pt  Faça. Devagar. Essas conversas ficam mais fáceis quanto mais houve.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time, %1$s. People say that. You'd be the first who did it twice, and I've counted.
    >>  ............................................
    pt  Da próxima, %1$s. As pessoas dizem isso. Você seria o primeiro a fazer duas vezes, e eu contei.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me. If you don't I'll not raise it, and then it'll simply stop existing.
    >>  ............................................
    pt  Certo. Me pergunte. Se não perguntar eu não levanto, e aí simplesmente deixa de existir.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. Please. The first time is the hard one and you've already done that.
    >>  ............................................
    pt  Faça. Por favor. A primeira vez é a difícil e você já fez.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time. People say that.
    >>  ............................................
    pt  Da próxima. As pessoas dizem isso.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_again_later/2
    en  Right. Ask me.
    >>  ............................................
    pt  Certo. Me pergunte.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_again_later/3
    en  Do. It's easier the second time.
    >>  ............................................
    pt  Faça. É mais fácil na segunda vez.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time! People say that. You'd be the first who actually did it twice.
    >>  ............................................
    pt  Da próxima! As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_again_later/2
    en  Right — ask me. I'll have something to report and I do love having something to report.
    >>  ............................................
    pt  Certo — me pergunte. Vou ter o que relatar e eu adoro ter o que relatar.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_again_later/3
    en  Do! It's much easier to answer the second time. The first one is always dreadful.
    >>  ............................................
    pt  Faça! É bem mais fácil responder na segunda. A primeira é sempre horrível.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_again_later/1
    en  Next time! People say that. You'd be the first who actually did it twice.
    >>  ............................................
    pt  Da próxima! As pessoas dizem isso. Você seria o primeiro a fazer duas vezes.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_again_later/2
    en  Right — ask me. I'll have something to report and I do love having something to report.
    >>  ............................................
    pt  Certo — me pergunte. Vou ter o que relatar e eu adoro ter o que relatar.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_again_later/3
    en  Do! It's much easier to answer the second time. The first one is always dreadful.
    >>  ............................................
    pt  Faça! É bem mais fácil responder na segunda. A primeira é sempre horrível.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `family.resume.offer_to_help`, `family.resume.just_listen` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.close.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.arc.family.resume.close, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.leave`: the villager accepts. Subject `memories.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a toddler/child/teen/adult
NOTE   the same pool is also spoken at: conversations.arc.family.resume.followup / leave; conversations.arc.family.resume.respond / leave
```

```text
  dialogue.conversations.family.resume.leave/1   [49 chars]
    en  So be it. Thanks for coming back about it at all.
    >>  ............................................
    pt  Que seja. Obrigado por ter voltado a tocar no assunto.
    >>  ............................................
  dialogue.conversations.family.resume.leave/2   [47 chars]
    en  Off you go. I'll be about if it comes up again.
    >>  ............................................
    pt  Pode ir. Vou estar por aí se voltar à tona.
    >>  ............................................
  dialogue.conversations.family.resume.leave/3   [47 chars]
    en  Fine. It's said now, which is more than it was.
    >>  ............................................
    pt  Tudo bem. Está dito, que é mais do que estava.
    >>  ............................................
```

---


## `conversations.arc.family.resume.followup`

**Reached from 3 route(s):** `conversations.arc.family.resume.respond` / `ask_how_it_went`; `conversations.arc.family.resume.respond` / `keep_promise`; `conversations.arc.family.resume.respond` / `admit_forgot`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.resume.admit_forgot` — e.g. "...Honest, at least. Anyone else would have pretended and I'd have known."
- `conversations.family.resume.ask_how_it_went` — e.g. "Better than I expected and worse than I'd hoped, which is most of family."
- `conversations.family.resume.keep_promise` — e.g. "...You did. People say they will. You're the first who came back about it."


```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.family.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.family.resume.followup   [13 chars]
    en  So. Now what?
    >>  ............................................
    pt  Então. E agora?
    >>  ............................................
```


### Button `offer_to_help` — "Tell me what would actually help."

*stance family `practical_help` · tone `plain` · answers the beat(s) `family.resume.ask_how_it_went`, `family.resume.keep_promise`, `family.resume.admit_forgot`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.offer_to_help` — accepted phrasings: "tell me what would actually help"; "what would really help"; "what do you actually need"
  - the message must contain one of: `actually`, `help`, `would`
  - scored words: `actually`(1.5), `help`(0.4), `would`(0.4)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.followup.offer_to_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.followup.offer_to_help   [33 chars]
    en  Tell me what would actually help.
    >>  ............................................
    pt  Me diga o que ajudaria de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.resume.offer_to_help`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +3  _(recorded under topic `family.resume.offer_to_help`)_
- Does: arc `family` — advance to stage 3
- Then opens: `conversations.arc.family.resume.close`
- …where the player's next choices will be: "I'm glad you told me." | "I'll ask how it's going next time." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.resume.offer_to_help
WHO    VILLAGER — what the player reads after pressing "Tell me what would actually help."
       spoken on: conversations.arc.family.resume.followup, button `offer_to_help`
       leaves the player on: conversations.arc.family.resume.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.offer_to_help`: the villager accepts. Subject `memories.revisit`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.offer_to_help/1   [84 chars]
    en  ...Actually help. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  ...De verdade. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  dialogue.conversations.family.resume.offer_to_help/2   [70 chars]
    en  Come by on a Thursday. That's it. That's the whole of what would help.
    >>  ............................................
    pt  Apareça numa quinta. É isso. É tudo que ajudaria.
    >>  ............................................
  dialogue.conversations.family.resume.offer_to_help/3   [80 chars]
    en  Ask me again in front of them. That would do more than anything you could carry.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Isso faria mais que qualquer coisa que você carregasse.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. It sounds like nothing and it isn't nothing.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Soa como nada e não é nada.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. I'd not be able to ask for that, so I'm glad you asked me.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Eu não conseguiria pedir isso, então fico contente que você perguntou.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. Come by on a Thursday and keep coming by.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Apareça numa quinta e continue aparecendo.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.offer_to_help/2
    en  A Thursday. Every few weeks. That's the whole of it and it works slowly.
    >>  ............................................
    pt  Uma quinta. A cada poucas semanas. É tudo e funciona devagar.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. Not once — every few months. That's what shifts a family.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Não uma vez — a cada uns meses. É o que move uma família.
    >>  ............................................
  confident.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  confident.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. That's the whole of what would help.
    >>  ............................................
    pt  Apareça numa quinta. É isso. É tudo que ajudaria.
    >>  ............................................
  confident.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. That would do more than anything you could carry.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Isso faria mais que qualquer coisa que você carregasse.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. That's the whole of what would help.
    >>  ............................................
    pt  Apareça numa quinta. É isso. É tudo que ajudaria.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. That would do more than anything you could carry.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Isso faria mais que qualquer coisa que você carregasse.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. Bring nothing; just be in the room.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Não traga nada; só esteja na sala.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. You've no idea what that would do.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Você não faz ideia do que isso faria.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. Bring nothing; just be in the room.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Não traga nada; só esteja na sala.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. You've no idea what that would do.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Você não faz ideia do que isso faria.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. Bring nothing; just be in the room.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Não traga nada; só esteja na sala.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. You've no idea what that would do.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Você não faz ideia do que isso faria.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. It sounds like nothing and it isn't nothing.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Soa como nada e não é nada.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. I'd not be able to ask for that, so I'm glad you asked me.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Eu não conseguiria pedir isso, então fico contente que você perguntou.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. That's the whole of what would help.
    >>  ............................................
    pt  Apareça numa quinta. É isso. É tudo que ajudaria.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. That would do more than anything you could carry.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Isso faria mais que qualquer coisa que você carregasse.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. They say 'anything at all' and mean nothing.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Dizem 'qualquer coisa' e não querem dizer nada.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. That's the whole of what would help.
    >>  ............................................
    pt  Apareça numa quinta. É isso. É tudo que ajudaria.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. That would do more than anything you could carry.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Isso faria mais que qualquer coisa que você carregasse.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help. Nobody says actually.
    >>  ............................................
    pt  ...Ajudar de verdade. Ninguém diz de verdade.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it.
    >>  ............................................
    pt  Apareça numa quinta. É isso.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them.
    >>  ............................................
    pt  Me pergunte de novo na frente deles.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. Come by on a Thursday and keep coming by.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Apareça numa quinta e continue aparecendo.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.offer_to_help/2
    en  A Thursday. Every few weeks. That's the whole of it and it works slowly.
    >>  ............................................
    pt  Uma quinta. A cada poucas semanas. É tudo e funciona devagar.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. Not once — every few months. That's what shifts a family.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Não uma vez — a cada uns meses. É o que move uma família.
    >>  ............................................
  odd.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help. Nobody says actually.
    >>  ............................................
    pt  ...Ajudar de verdade. Ninguém diz de verdade.
    >>  ............................................
  odd.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it.
    >>  ............................................
    pt  Apareça numa quinta. É isso.
    >>  ............................................
  odd.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them.
    >>  ............................................
    pt  Me pergunte de novo na frente deles.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. Come by on a Thursday and keep coming by.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Apareça numa quinta e continue aparecendo.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.offer_to_help/2
    en  A Thursday. Every few weeks. That's the whole of it and it works slowly.
    >>  ............................................
    pt  Uma quinta. A cada poucas semanas. É tudo e funciona devagar.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. Not once — every few months. That's what shifts a family.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Não uma vez — a cada uns meses. É o que move uma família.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help! Nobody says actually. They say 'anything at all' and mean 'please say no'.
    >>  ............................................
    pt  Ajudar de verdade! Ninguém diz de verdade. Dizem 'qualquer coisa' e querem dizer 'diga não'.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it! That's the entire request. Astonishingly cheap.
    >>  ............................................
    pt  Apareça numa quinta. É isso! É todo o pedido. Espantosamente barato.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. It would do more than a cart full of good intentions.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Faria mais que uma carroça de boas intenções.
    >>  ............................................
  playful.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help! Nobody says actually. They say 'anything at all' and mean 'please say no'.
    >>  ............................................
    pt  Ajudar de verdade! Ninguém diz de verdade. Dizem 'qualquer coisa' e querem dizer 'diga não'.
    >>  ............................................
  playful.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it! That's the entire request. Astonishingly cheap.
    >>  ............................................
    pt  Apareça numa quinta. É isso! É todo o pedido. Espantosamente barato.
    >>  ............................................
  playful.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. It would do more than a cart full of good intentions.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Faria mais que uma carroça de boas intenções.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help. Nobody says actually. Come by on a Thursday and keep coming by.
    >>  ............................................
    pt  Ajudar de verdade. Ninguém diz de verdade. Apareça numa quinta e continue aparecendo.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.offer_to_help/2
    en  A Thursday. Every few weeks. That's the whole of it and it works slowly.
    >>  ............................................
    pt  Uma quinta. A cada poucas semanas. É tudo e funciona devagar.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. Not once — every few months. That's what shifts a family.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Não uma vez — a cada uns meses. É o que move uma família.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help, %1$s. Nobody says actually. I'd stopped hearing the offer at all.
    >>  ............................................
    pt  ...Ajudar de verdade, %1$s. Ninguém diz de verdade. Eu tinha parado de ouvir a oferta.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it. It sounds like nothing and it isn't nothing.
    >>  ............................................
    pt  Apareça numa quinta. É isso. Soa como nada e não é nada.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. I'd not be able to ask for that, so I'm glad you asked me.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Eu não conseguiria pedir isso, então fico contente que você perguntou.
    >>  ............................................
  shy.dialogue.conversations.family.resume.offer_to_help/1
    en  ...Actually help. Nobody says actually.
    >>  ............................................
    pt  ...Ajudar de verdade. Ninguém diz de verdade.
    >>  ............................................
  shy.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it.
    >>  ............................................
    pt  Apareça numa quinta. É isso.
    >>  ............................................
  shy.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them.
    >>  ............................................
    pt  Me pergunte de novo na frente deles.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help! Nobody says actually. They say 'anything at all' and mean 'please say no'.
    >>  ............................................
    pt  Ajudar de verdade! Ninguém diz de verdade. Dizem 'qualquer coisa' e querem dizer 'diga não'.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it! That's the entire request. Astonishingly cheap.
    >>  ............................................
    pt  Apareça numa quinta. É isso! É todo o pedido. Espantosamente barato.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. It would do more than a cart full of good intentions.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Faria mais que uma carroça de boas intenções.
    >>  ............................................
  witty.dialogue.conversations.family.resume.offer_to_help/1
    en  Actually help! Nobody says actually. They say 'anything at all' and mean 'please say no'.
    >>  ............................................
    pt  Ajudar de verdade! Ninguém diz de verdade. Dizem 'qualquer coisa' e querem dizer 'diga não'.
    >>  ............................................
  witty.dialogue.conversations.family.resume.offer_to_help/2
    en  Come by on a Thursday. That's it! That's the entire request. Astonishingly cheap.
    >>  ............................................
    pt  Apareça numa quinta. É isso! É todo o pedido. Espantosamente barato.
    >>  ............................................
  witty.dialogue.conversations.family.resume.offer_to_help/3
    en  Ask me again in front of them. It would do more than a cart full of good intentions.
    >>  ............................................
    pt  Me pergunte de novo na frente deles. Faria mais que uma carroça de boas intenções.
    >>  ............................................
```

</details>


### Button `just_listen` — "I'll just listen, if that's better."

*stance family `empathy` · tone `plain` · answers the beat(s) `family.resume.ask_how_it_went`, `family.resume.keep_promise`, `family.resume.admit_forgot`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.just_listen` — accepted phrasings: "i will just listen"; "i can just listen"; "i do not need to fix it"
  - the message must contain one of: `better`, `just`, `listen`
  - scored words: `better`(0.8), `just`(0.5), `listen`(1.5)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.followup.just_listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.followup.just_listen   [35 chars]
    en  I'll just listen, if that's better.
    >>  ............................................
    pt  Eu só escuto, se for melhor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +2  _(recorded under topic `family.resume.just_listen`)_
- Does: arc `family` — advance to stage 3
- Then opens: `conversations.arc.family.resume.close`
- …where the player's next choices will be: "I'm glad you told me." | "I'll ask how it's going next time." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.resume.just_listen
WHO    VILLAGER — what the player reads after pressing "I'll just listen, if that's better."
       spoken on: conversations.arc.family.resume.followup, button `just_listen`
       leaves the player on: conversations.arc.family.resume.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.just_listen`: the villager accepts. Subject `memories.revisit`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.just_listen/1   [65 chars]
    en  ...That is better. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  dialogue.conversations.family.resume.just_listen/2   [75 chars]
    en  Then sit down. I'll get to it in my own order and it won't be the tidy one.
    >>  ............................................
    pt  Então sente-se. Eu chego lá na minha ordem e não vai ser a arrumada.
    >>  ............................................
  dialogue.conversations.family.resume.just_listen/3   [72 chars]
    en  Listening. Yes. Everyone else arrives with a plan and I've plans enough.
    >>  ............................................
    pt  Escutar. Sim. Todo mundo chega com um plano e eu já tenho planos demais.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. I'll probably not make much sense and that's what I need.
    >>  ............................................
    pt  Certo. Sem plano. Eu provavelmente não vou fazer sentido e é disso que eu preciso.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Everyone arrives with a plan and I end up comforting them about their plan.
    >>  ............................................
    pt  Escutar. Todo mundo chega com um plano e eu acabo consolando eles pelo plano.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want it. Sit down; there's time.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer. Sente-se; tem tempo.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Plans have their place and this isn't one of them.
    >>  ............................................
    pt  Certo. Sem plano. Planos têm lugar e não é aqui.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.just_listen/3
    en  Listening. It's the only thing that has ever actually helped with family.
    >>  ............................................
    pt  Escutar. É a única coisa que já ajudou de verdade com família.
    >>  ............................................
  confident.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  confident.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit down, then.
    >>  ............................................
    pt  Certo. Sem plano. Então sente-se.
    >>  ............................................
  confident.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes. Everyone else arrives with a plan.
    >>  ............................................
    pt  Escutar. Sim. Todo mundo chega com um plano.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit down, then.
    >>  ............................................
    pt  Certo. Sem plano. Então sente-se.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes. Everyone else arrives with a plan.
    >>  ............................................
    pt  Escutar. Sim. Todo mundo chega com um plano.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit with me and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Certo. Sem plano. Sente comigo e eu digo na ordem que vier.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Thank you. Everyone else arrives with a plan and none of them fit.
    >>  ............................................
    pt  Escutar. Obrigado. Todo mundo chega com um plano e nenhum serve.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit with me and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Certo. Sem plano. Sente comigo e eu digo na ordem que vier.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Thank you. Everyone else arrives with a plan and none of them fit.
    >>  ............................................
    pt  Escutar. Obrigado. Todo mundo chega com um plano e nenhum serve.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit with me and I'll say it in whatever order it comes.
    >>  ............................................
    pt  Certo. Sem plano. Sente comigo e eu digo na ordem que vier.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Thank you. Everyone else arrives with a plan and none of them fit.
    >>  ............................................
    pt  Escutar. Obrigado. Todo mundo chega com um plano e nenhum serve.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. I'll probably not make much sense and that's what I need.
    >>  ............................................
    pt  Certo. Sem plano. Eu provavelmente não vou fazer sentido e é disso que eu preciso.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Everyone arrives with a plan and I end up comforting them about their plan.
    >>  ............................................
    pt  Escutar. Todo mundo chega com um plano e eu acabo consolando eles pelo plano.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit down, then.
    >>  ............................................
    pt  Certo. Sem plano. Então sente-se.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes. Everyone else arrives with a plan.
    >>  ............................................
    pt  Escutar. Sim. Todo mundo chega com um plano.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Sit down, then.
    >>  ............................................
    pt  Certo. Sem plano. Então sente-se.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes. Everyone else arrives with a plan.
    >>  ............................................
    pt  Escutar. Sim. Todo mundo chega com um plano.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better. I hadn't known I could want that.
    >>  ............................................
    pt  ...É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan.
    >>  ............................................
    pt  Certo. Sem plano.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes.
    >>  ............................................
    pt  Escutar. Sim.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want it. Sit down; there's time.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer. Sente-se; tem tempo.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Plans have their place and this isn't one of them.
    >>  ............................................
    pt  Certo. Sem plano. Planos têm lugar e não é aqui.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.just_listen/3
    en  Listening. It's the only thing that has ever actually helped with family.
    >>  ............................................
    pt  Escutar. É a única coisa que já ajudou de verdade com família.
    >>  ............................................
  odd.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better. I hadn't known I could want that.
    >>  ............................................
    pt  ...É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  odd.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan.
    >>  ............................................
    pt  Certo. Sem plano.
    >>  ............................................
  odd.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes.
    >>  ............................................
    pt  Escutar. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want it. Sit down; there's time.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer. Sente-se; tem tempo.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Plans have their place and this isn't one of them.
    >>  ............................................
    pt  Certo. Sem plano. Planos têm lugar e não é aqui.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.just_listen/3
    en  Listening. It's the only thing that has ever actually helped with family.
    >>  ............................................
    pt  Escutar. É a única coisa que já ajudou de verdade com família.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.just_listen/1
    en  That is better! I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo! Eu não sabia que eu podia querer isso.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.just_listen/2
    en  Right — no plan. Sit down, then. I'll get to it in a thoroughly untidy order.
    >>  ............................................
    pt  Certo — sem plano. Então sente-se. Eu chego lá numa ordem completamente bagunçada.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.just_listen/3
    en  Listening! Everyone else arrives with a plan and I've plans coming out of my ears.
    >>  ............................................
    pt  Escutar! Todo mundo chega com um plano e eu tenho planos saindo pelas orelhas.
    >>  ............................................
  playful.dialogue.conversations.family.resume.just_listen/1
    en  That is better! I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo! Eu não sabia que eu podia querer isso.
    >>  ............................................
  playful.dialogue.conversations.family.resume.just_listen/2
    en  Right — no plan. Sit down, then. I'll get to it in a thoroughly untidy order.
    >>  ............................................
    pt  Certo — sem plano. Então sente-se. Eu chego lá numa ordem completamente bagunçada.
    >>  ............................................
  playful.dialogue.conversations.family.resume.just_listen/3
    en  Listening! Everyone else arrives with a plan and I've plans coming out of my ears.
    >>  ............................................
    pt  Escutar! Todo mundo chega com um plano e eu tenho planos saindo pelas orelhas.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.just_listen/1
    en  That is better. I hadn't known I was allowed to want it. Sit down; there's time.
    >>  ............................................
    pt  É melhor mesmo. Eu não sabia que eu podia querer. Sente-se; tem tempo.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. Plans have their place and this isn't one of them.
    >>  ............................................
    pt  Certo. Sem plano. Planos têm lugar e não é aqui.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.just_listen/3
    en  Listening. It's the only thing that has ever actually helped with family.
    >>  ............................................
    pt  Escutar. É a única coisa que já ajudou de verdade com família.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better, %1$s. I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  ...É melhor mesmo, %1$s. Eu não sabia que eu podia querer isso.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan. I'll probably not make much sense and that's what I need.
    >>  ............................................
    pt  Certo. Sem plano. Eu provavelmente não vou fazer sentido e é disso que eu preciso.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Everyone arrives with a plan and I end up comforting them about their plan.
    >>  ............................................
    pt  Escutar. Todo mundo chega com um plano e eu acabo consolando eles pelo plano.
    >>  ............................................
  shy.dialogue.conversations.family.resume.just_listen/1
    en  ...That is better. I hadn't known I could want that.
    >>  ............................................
    pt  ...É melhor mesmo. Eu não sabia que eu podia querer isso.
    >>  ............................................
  shy.dialogue.conversations.family.resume.just_listen/2
    en  Right. No plan.
    >>  ............................................
    pt  Certo. Sem plano.
    >>  ............................................
  shy.dialogue.conversations.family.resume.just_listen/3
    en  Listening. Yes.
    >>  ............................................
    pt  Escutar. Sim.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.just_listen/1
    en  That is better! I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo! Eu não sabia que eu podia querer isso.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.just_listen/2
    en  Right — no plan. Sit down, then. I'll get to it in a thoroughly untidy order.
    >>  ............................................
    pt  Certo — sem plano. Então sente-se. Eu chego lá numa ordem completamente bagunçada.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.just_listen/3
    en  Listening! Everyone else arrives with a plan and I've plans coming out of my ears.
    >>  ............................................
    pt  Escutar! Todo mundo chega com um plano e eu tenho planos saindo pelas orelhas.
    >>  ............................................
  witty.dialogue.conversations.family.resume.just_listen/1
    en  That is better! I hadn't known I was allowed to want that one.
    >>  ............................................
    pt  É melhor mesmo! Eu não sabia que eu podia querer isso.
    >>  ............................................
  witty.dialogue.conversations.family.resume.just_listen/2
    en  Right — no plan. Sit down, then. I'll get to it in a thoroughly untidy order.
    >>  ............................................
    pt  Certo — sem plano. Então sente-se. Eu chego lá numa ordem completamente bagunçada.
    >>  ............................................
  witty.dialogue.conversations.family.resume.just_listen/3
    en  Listening! Everyone else arrives with a plan and I've plans coming out of my ears.
    >>  ............................................
    pt  Escutar! Todo mundo chega com um plano e eu tenho planos saindo pelas orelhas.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `family.resume.ask_how_it_went`, `family.resume.keep_promise`, `family.resume.admit_forgot` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.followup.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.arc.family.resume.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.leave`: the villager accepts. Subject `memories.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a toddler/child/teen/adult
NOTE   the same pool is also spoken at: conversations.arc.family.resume.close / leave; conversations.arc.family.resume.respond / leave
```

> Written out in full under **`conversations.arc.family.resume.close` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.arc.family.resume.respond`

**Reached from 1 route(s):** `conversations.family` / `memories`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.revisit` — e.g. "I've been thinking about what we talked about. The family thing. I didn't forget it."


```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.family.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.family.resume.respond   [33 chars]
    en  Anyway. That's still where it is.
    >>  ............................................
    pt  Enfim. Continua nesse ponto.
    >>  ............................................
```


### Button `ask_how_it_went` — "How did it go, in the end?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `family.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.ask_how_it_went` — accepted phrasings: "how did it go"; "how did it end up"; "did it work out"
  - the message must contain one of: `end`, `how`, `went`
  - scored words: `end`(0.8), `how`(0.5), `went`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.respond.ask_how_it_went
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.respond.ask_how_it_went   [26 chars]
    en  How did it go, in the end?
    >>  ............................................
    pt  Como ficou, no fim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +4, trust +2  _(recorded under topic `family.resume.ask_how_it_went`)_
- Does: arc `family` — advance to stage 2
- Then opens: `conversations.arc.family.resume.followup`
- …where the player's next choices will be: "Tell me what would actually help." | "I'll just listen, if that's better." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.resume.ask_how_it_went
WHO    VILLAGER — what the player reads after pressing "How did it go, in the end?"
       spoken on: conversations.arc.family.resume.respond, button `ask_how_it_went`
       leaves the player on: conversations.arc.family.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.ask_how_it_went`: the villager discloses. Subject `memories.revisit`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.ask_how_it_went/1   [73 chars]
    en  Better than I expected and worse than I'd hoped, which is most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, que é quase toda família.
    >>  ............................................
  dialogue.conversations.family.resume.ask_how_it_went/2   [76 chars]
    en  It went. That's the honest answer and it took a while to be able to give it.
    >>  ............................................
    pt  Ficou. É a resposta honesta e levou um tempo pra eu conseguir dá-la.
    >>  ............................................
  dialogue.conversations.family.resume.ask_how_it_went/3   [79 chars]
    en  Nothing's fixed. But nobody's stopped talking either, and that's the good news.
    >>  ............................................
    pt  Nada resolvido. Mas ninguém parou de se falar, e essa é a boa notícia.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family, isn't it.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família, não é?
    >>  ............................................
  anxious.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. I'd rather say that than the tidy version, and the tidy version was ready.
    >>  ............................................
    pt  Ficou. Prefiro dizer isso à versão arrumada, e a versão arrumada estava pronta.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. I'd stopped expecting fixed. Nobody's stopped talking, and that's a lot.
    >>  ............................................
    pt  Nada resolvido. Eu tinha parado de esperar resolvido. Ninguém parou de se falar, e isso é muito.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. Families go at their own pace.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. Famílias vão no ritmo delas.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. It'll go on going. That's what family does.
    >>  ............................................
    pt  Ficou. Vai continuar indo. É o que família faz.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Give it a few years; most of it sorts itself out eventually.
    >>  ............................................
    pt  Nada resolvido. Dê uns anos; quase tudo se resolve uma hora.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. É quase toda família.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer and it took a while to be able to give it.
    >>  ............................................
    pt  Ficou. É a resposta honesta e levou um tempo pra eu conseguir dá-la.
    >>  ............................................
  confident.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. É quase toda família.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer and it took a while to be able to give it.
    >>  ............................................
    pt  Ficou. É a resposta honesta e levou um tempo pra eu conseguir dá-la.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. Sit down and I'll tell you the parts that surprised me.
    >>  ............................................
    pt  Ficou. Sente-se e eu conto as partes que me surpreenderam.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. But nobody's stopped talking, and you asking is part of why.
    >>  ............................................
    pt  Nada resolvido. Mas ninguém parou de se falar, e você perguntar é parte do porquê.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. Sit down and I'll tell you the parts that surprised me.
    >>  ............................................
    pt  Ficou. Sente-se e eu conto as partes que me surpreenderam.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. But nobody's stopped talking, and you asking is part of why.
    >>  ............................................
    pt  Nada resolvido. Mas ninguém parou de se falar, e você perguntar é parte do porquê.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. Sit down and I'll tell you the parts that surprised me.
    >>  ............................................
    pt  Ficou. Sente-se e eu conto as partes que me surpreenderam.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. But nobody's stopped talking, and you asking is part of why.
    >>  ............................................
    pt  Nada resolvido. Mas ninguém parou de se falar, e você perguntar é parte do porquê.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family, isn't it.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família, não é?
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. I'd rather say that than the tidy version, and the tidy version was ready.
    >>  ............................................
    pt  Ficou. Prefiro dizer isso à versão arrumada, e a versão arrumada estava pronta.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. I'd stopped expecting fixed. Nobody's stopped talking, and that's a lot.
    >>  ............................................
    pt  Nada resolvido. Eu tinha parado de esperar resolvido. Ninguém parou de se falar, e isso é muito.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. É quase toda família.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer and it took a while to be able to give it.
    >>  ............................................
    pt  Ficou. É a resposta honesta e levou um tempo pra eu conseguir dá-la.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. That's most of family.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. É quase toda família.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer and it took a while to be able to give it.
    >>  ............................................
    pt  Ficou. É a resposta honesta e levou um tempo pra eu conseguir dá-la.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer.
    >>  ............................................
    pt  Ficou. É a resposta honesta.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. Families go at their own pace.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. Famílias vão no ritmo delas.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. It'll go on going. That's what family does.
    >>  ............................................
    pt  Ficou. Vai continuar indo. É o que família faz.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Give it a few years; most of it sorts itself out eventually.
    >>  ............................................
    pt  Nada resolvido. Dê uns anos; quase tudo se resolve uma hora.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer.
    >>  ............................................
    pt  Ficou. É a resposta honesta.
    >>  ............................................
  odd.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. Families go at their own pace.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. Famílias vão no ritmo delas.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. It'll go on going. That's what family does.
    >>  ............................................
    pt  Ficou. Vai continuar indo. É o que família faz.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Give it a few years; most of it sorts itself out eventually.
    >>  ............................................
    pt  Nada resolvido. Dê uns anos; quase tudo se resolve uma hora.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped! Which is most of family, honestly.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria! Que é quase toda família, sinceramente.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the whole report. Riveting stuff, family.
    >>  ............................................
    pt  Ficou. É todo o relatório. Fascinante, família.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed! Nobody's stopped talking either, which I'm choosing to call a triumph.
    >>  ............................................
    pt  Nada resolvido! E ninguém parou de se falar, o que eu escolho chamar de triunfo.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped! Which is most of family, honestly.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria! Que é quase toda família, sinceramente.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the whole report. Riveting stuff, family.
    >>  ............................................
    pt  Ficou. É todo o relatório. Fascinante, família.
    >>  ............................................
  playful.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed! Nobody's stopped talking either, which I'm choosing to call a triumph.
    >>  ............................................
    pt  Nada resolvido! E ninguém parou de se falar, o que eu escolho chamar de triunfo.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped. Families go at their own pace.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria. Famílias vão no ritmo delas.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. It'll go on going. That's what family does.
    >>  ............................................
    pt  Ficou. Vai continuar indo. É o que família faz.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Give it a few years; most of it sorts itself out eventually.
    >>  ............................................
    pt  Nada resolvido. Dê uns anos; quase tudo se resolve uma hora.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped, %1$s. That's most of family, isn't it.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria, %1$s. É quase toda família, não é?
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. I'd rather say that than the tidy version, and the tidy version was ready.
    >>  ............................................
    pt  Ficou. Prefiro dizer isso à versão arrumada, e a versão arrumada estava pronta.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. I'd stopped expecting fixed. Nobody's stopped talking, and that's a lot.
    >>  ............................................
    pt  Nada resolvido. Eu tinha parado de esperar resolvido. Ninguém parou de se falar, e isso é muito.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the honest answer.
    >>  ............................................
    pt  Ficou. É a resposta honesta.
    >>  ............................................
  shy.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed. Nobody's stopped talking either.
    >>  ............................................
    pt  Nada resolvido. E ninguém parou de se falar.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped! Which is most of family, honestly.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria! Que é quase toda família, sinceramente.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the whole report. Riveting stuff, family.
    >>  ............................................
    pt  Ficou. É todo o relatório. Fascinante, família.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed! Nobody's stopped talking either, which I'm choosing to call a triumph.
    >>  ............................................
    pt  Nada resolvido! E ninguém parou de se falar, o que eu escolho chamar de triunfo.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_how_it_went/1
    en  Better than I expected and worse than I'd hoped! Which is most of family, honestly.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu queria! Que é quase toda família, sinceramente.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_how_it_went/2
    en  It went. That's the whole report. Riveting stuff, family.
    >>  ............................................
    pt  Ficou. É todo o relatório. Fascinante, família.
    >>  ............................................
  witty.dialogue.conversations.family.resume.ask_how_it_went/3
    en  Nothing's fixed! Nobody's stopped talking either, which I'm choosing to call a triumph.
    >>  ............................................
    pt  Nada resolvido! E ninguém parou de se falar, o que eu escolho chamar de triunfo.
    >>  ............................................
```

</details>


### Button `keep_promise` — "I said I'd remember. I did."

*stance family `candor` · tone `plain` · answers the beat(s) `family.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.keep_promise` — accepted phrasings: "i said i would remember"; "i kept my word"; "i did not forget"
  - the message must contain one of: `promise`, `remember`, `said`
  - scored words: `promise`(1.2), `remember`(1.5), `said`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.respond.keep_promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.respond.keep_promise   [27 chars]
    en  I said I'd remember. I did.
    >>  ............................................
    pt  Eu disse que ia lembrar. E lembrei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.resume.keep_promise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +4  _(recorded under topic `family.resume.keep_promise`)_
- Does: arc `family` — advance to stage 2
- Then opens: `conversations.arc.family.resume.followup`
- …where the player's next choices will be: "Tell me what would actually help." | "I'll just listen, if that's better." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.resume.keep_promise
WHO    VILLAGER — what the player reads after pressing "I said I'd remember. I did."
       spoken on: conversations.arc.family.resume.respond, button `keep_promise`
       leaves the player on: conversations.arc.family.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.keep_promise`: the villager accepts. Subject `memories.revisit`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.keep_promise/1   [74 chars]
    en  ...You did. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  ...Você lembrou. As pessoas dizem que vão. Você é o primeiro que voltou por causa disso.
    >>  ............................................
  dialogue.conversations.family.resume.keep_promise/2   [68 chars]
    en  You remembered. I'd made myself not expect that, and I was wrong to.
    >>  ............................................
    pt  Você lembrou. Eu tinha me obrigado a não esperar isso, e eu estava errado.
    >>  ............................................
  dialogue.conversations.family.resume.keep_promise/3   [73 chars]
    en  That matters more than the thing itself did. I'd not know how to say why.
    >>  ............................................
    pt  Isso importa mais que a coisa em si. Eu não saberia dizer por quê.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it, ever.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso, sempre.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. I'd made myself not expect it, and I was wrong to, and I'm glad.
    >>  ............................................
    pt  Você lembrou. Eu me obriguei a não esperar, e eu estava errado, e fico contente.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Give me a moment. That's undone a fair amount of careful arithmetic.
    >>  ............................................
    pt  Você voltou. Me dê um momento. Isso desfez uma boa quantidade de contas cuidadosas.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. Coming back is what tells you which sort somebody is.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Voltar é o que diz de que tipo alguém é.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered, months on. That's how these things prove themselves.
    >>  ............................................
    pt  Você lembrou, meses depois. É assim que essas coisas se provam.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. It'll be worth more again if you do it a third time.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vai valer mais ainda se você fizer uma terceira vez.
    >>  ............................................
  confident.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  confident.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right. That's the whole of what I needed.
    >>  ............................................
    pt  Você lembrou. Certo. É tudo que eu precisava.
    >>  ............................................
  confident.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. Noted.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Anotado.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right. That's the whole of what I needed.
    >>  ............................................
    pt  Você lembrou. Certo. É tudo que eu precisava.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. Noted.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Anotado.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. That matters more than the thing itself did, and I'd not know how to say why.
    >>  ............................................
    pt  Você lembrou. Isso importa mais que a coisa em si, e eu não saberia dizer por quê.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. I'd like you to know that I noticed, and that I'd counted the days.
    >>  ............................................
    pt  Você voltou. Queria que você soubesse que eu reparei, e que eu contei os dias.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. That matters more than the thing itself did, and I'd not know how to say why.
    >>  ............................................
    pt  Você lembrou. Isso importa mais que a coisa em si, e eu não saberia dizer por quê.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. I'd like you to know that I noticed, and that I'd counted the days.
    >>  ............................................
    pt  Você voltou. Queria que você soubesse que eu reparei, e que eu contei os dias.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. That matters more than the thing itself did, and I'd not know how to say why.
    >>  ............................................
    pt  Você lembrou. Isso importa mais que a coisa em si, e eu não saberia dizer por quê.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. I'd like you to know that I noticed, and that I'd counted the days.
    >>  ............................................
    pt  Você voltou. Queria que você soubesse que eu reparei, e que eu contei os dias.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it, ever.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso, sempre.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. I'd made myself not expect it, and I was wrong to, and I'm glad.
    >>  ............................................
    pt  Você lembrou. Eu me obriguei a não esperar, e eu estava errado, e fico contente.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Give me a moment. That's undone a fair amount of careful arithmetic.
    >>  ............................................
    pt  Você voltou. Me dê um momento. Isso desfez uma boa quantidade de contas cuidadosas.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right. That's the whole of what I needed.
    >>  ............................................
    pt  Você lembrou. Certo. É tudo que eu precisava.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. Noted.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Anotado.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. You're the first who came back about it.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right. That's the whole of what I needed.
    >>  ............................................
    pt  Você lembrou. Certo. É tudo que eu precisava.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. Noted.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Anotado.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did. People say they will.
    >>  ............................................
    pt  ...Você lembrou. As pessoas dizem que vão.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right.
    >>  ............................................
    pt  Você lembrou. Certo.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't.
    >>  ............................................
    pt  Você voltou. A maioria não volta.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. Coming back is what tells you which sort somebody is.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Voltar é o que diz de que tipo alguém é.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered, months on. That's how these things prove themselves.
    >>  ............................................
    pt  Você lembrou, meses depois. É assim que essas coisas se provam.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. It'll be worth more again if you do it a third time.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vai valer mais ainda se você fizer uma terceira vez.
    >>  ............................................
  odd.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did. People say they will.
    >>  ............................................
    pt  ...Você lembrou. As pessoas dizem que vão.
    >>  ............................................
  odd.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right.
    >>  ............................................
    pt  Você lembrou. Certo.
    >>  ............................................
  odd.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't.
    >>  ............................................
    pt  Você voltou. A maioria não volta.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. Coming back is what tells you which sort somebody is.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Voltar é o que diz de que tipo alguém é.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered, months on. That's how these things prove themselves.
    >>  ............................................
    pt  Você lembrou, meses depois. É assim que essas coisas se provam.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. It'll be worth more again if you do it a third time.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vai valer mais ainda se você fizer uma terceira vez.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.keep_promise/1
    en  You did! People say they will. You're the first who actually came back about it.
    >>  ............................................
    pt  Você lembrou! As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered! Right. I'd written this one off entirely, which is embarrassing now.
    >>  ............................................
    pt  Você lembrou! Certo. Eu tinha descartado essa completamente, o que agora é constrangedor.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. I'm going to be smug about my judgement all week.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vou me achar sobre o meu julgamento a semana toda.
    >>  ............................................
  playful.dialogue.conversations.family.resume.keep_promise/1
    en  You did! People say they will. You're the first who actually came back about it.
    >>  ............................................
    pt  Você lembrou! As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  playful.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered! Right. I'd written this one off entirely, which is embarrassing now.
    >>  ............................................
    pt  Você lembrou! Certo. Eu tinha descartado essa completamente, o que agora é constrangedor.
    >>  ............................................
  playful.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. I'm going to be smug about my judgement all week.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vou me achar sobre o meu julgamento a semana toda.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.keep_promise/1
    en  You did. People say they will. Coming back is what tells you which sort somebody is.
    >>  ............................................
    pt  Você lembrou. As pessoas dizem que vão. Voltar é o que diz de que tipo alguém é.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered, months on. That's how these things prove themselves.
    >>  ............................................
    pt  Você lembrou, meses depois. É assim que essas coisas se provam.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. It'll be worth more again if you do it a third time.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vai valer mais ainda se você fizer uma terceira vez.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did, %1$s. People say they will. You're the first who came back about it, ever.
    >>  ............................................
    pt  ...Você lembrou, %1$s. As pessoas dizem que vão. Você é o primeiro que voltou por isso, sempre.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. I'd made myself not expect it, and I was wrong to, and I'm glad.
    >>  ............................................
    pt  Você lembrou. Eu me obriguei a não esperar, e eu estava errado, e fico contente.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Give me a moment. That's undone a fair amount of careful arithmetic.
    >>  ............................................
    pt  Você voltou. Me dê um momento. Isso desfez uma boa quantidade de contas cuidadosas.
    >>  ............................................
  shy.dialogue.conversations.family.resume.keep_promise/1
    en  ...You did. People say they will.
    >>  ............................................
    pt  ...Você lembrou. As pessoas dizem que vão.
    >>  ............................................
  shy.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered. Right.
    >>  ............................................
    pt  Você lembrou. Certo.
    >>  ............................................
  shy.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't.
    >>  ............................................
    pt  Você voltou. A maioria não volta.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.keep_promise/1
    en  You did! People say they will. You're the first who actually came back about it.
    >>  ............................................
    pt  Você lembrou! As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered! Right. I'd written this one off entirely, which is embarrassing now.
    >>  ............................................
    pt  Você lembrou! Certo. Eu tinha descartado essa completamente, o que agora é constrangedor.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. I'm going to be smug about my judgement all week.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vou me achar sobre o meu julgamento a semana toda.
    >>  ............................................
  witty.dialogue.conversations.family.resume.keep_promise/1
    en  You did! People say they will. You're the first who actually came back about it.
    >>  ............................................
    pt  Você lembrou! As pessoas dizem que vão. Você é o primeiro que voltou por isso.
    >>  ............................................
  witty.dialogue.conversations.family.resume.keep_promise/2
    en  You remembered! Right. I'd written this one off entirely, which is embarrassing now.
    >>  ............................................
    pt  Você lembrou! Certo. Eu tinha descartado essa completamente, o que agora é constrangedor.
    >>  ............................................
  witty.dialogue.conversations.family.resume.keep_promise/3
    en  You came back. Most don't. I'm going to be smug about my judgement all week.
    >>  ............................................
    pt  Você voltou. A maioria não volta. Vou me achar sobre o meu julgamento a semana toda.
    >>  ............................................
```

</details>


### Button `admit_forgot` — "I'd forgotten, if I'm honest."

*stance family `candor` · tone `plain` · answers the beat(s) `family.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.resume.admit_forgot` — accepted phrasings: "i had forgotten"; "i forgot honestly"; "it slipped my mind"
  - the message must contain one of: `forgot`, `forgotten`, `honest`
  - scored words: `forgot`(1.5), `forgotten`(1.5), `honest`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.respond.admit_forgot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.respond.admit_forgot   [29 chars]
    en  I'd forgotten, if I'm honest.
    >>  ............................................
    pt  Eu tinha esquecido, sendo honesto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — trust +2, warmth -1  _(recorded under topic `family.resume.admit_forgot`)_
- Does: arc `family` — advance to stage 2
- Then opens: `conversations.arc.family.resume.followup`
- …where the player's next choices will be: "Tell me what would actually help." | "I'll just listen, if that's better." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.resume.admit_forgot
WHO    VILLAGER — what the player reads after pressing "I'd forgotten, if I'm honest."
       spoken on: conversations.arc.family.resume.respond, button `admit_forgot`
       leaves the player on: conversations.arc.family.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.admit_forgot`: the villager qualifys. Subject `memories.revisit`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, practical_help, empathy, restraint, encouragement, curiosity, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.resume.admit_forgot/1   [73 chars]
    en  ...Honest, at least. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  ...Ao menos honesto. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  dialogue.conversations.family.resume.admit_forgot/2   [70 chars]
    en  You forgot. Right. I'd rather that said out loud than performed at me.
    >>  ............................................
    pt  Você esqueceu. Certo. Prefiro ouvir dito a ver encenado.
    >>  ............................................
  dialogue.conversations.family.resume.admit_forgot/3   [64 chars]
    en  Everyone forgets. Not everyone says so. I'll take the saying so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz. Eu fico com o dizer.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have spent a week knowing.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu passaria a semana sabendo.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. That stings and it stings less than the pretending would have.
    >>  ............................................
    pt  Você esqueceu. Isso dói e dói menos do que fingir doeria.
    >>  ............................................
  anxious.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. I'd told myself that in advance, so that it wouldn't land.
    >>  ............................................
    pt  Todo mundo esquece. Eu tinha dito isso a mim antes, pra não pegar.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended. Pretending never lasts.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido. Fingir nunca dura.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Months pass and things fall out of heads. It's not a crime.
    >>  ............................................
    pt  Você esqueceu. Meses passam e as coisas caem da cabeça. Não é crime.
    >>  ............................................
  athletic.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Coming back afterwards is what separates people, not the forgetting.
    >>  ............................................
    pt  Todo mundo esquece. Voltar depois é o que separa as pessoas, não o esquecer.
    >>  ............................................
  confident.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  confident.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. I'd rather that said out loud than performed at me.
    >>  ............................................
    pt  Você esqueceu. Certo. Prefiro ouvir dito a ver encenado.
    >>  ............................................
  confident.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. I'd rather that said out loud than performed at me.
    >>  ............................................
    pt  Você esqueceu. Certo. Prefiro ouvir dito a ver encenado.
    >>  ............................................
  crabby.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. I'd rather that than a performance. Ask me again and I'll tell you again.
    >>  ............................................
    pt  Você esqueceu. Prefiro isso a uma encenação. Pergunte de novo e eu conto de novo.
    >>  ............................................
  extroverted.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. You came back, which is the part that actually counts.
    >>  ............................................
    pt  Todo mundo esquece. Você voltou, que é a parte que conta.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. I'd rather that than a performance. Ask me again and I'll tell you again.
    >>  ............................................
    pt  Você esqueceu. Prefiro isso a uma encenação. Pergunte de novo e eu conto de novo.
    >>  ............................................
  flirty.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. You came back, which is the part that actually counts.
    >>  ............................................
    pt  Todo mundo esquece. Você voltou, que é a parte que conta.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. I'd rather that than a performance. Ask me again and I'll tell you again.
    >>  ............................................
    pt  Você esqueceu. Prefiro isso a uma encenação. Pergunte de novo e eu conto de novo.
    >>  ............................................
  friendly.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. You came back, which is the part that actually counts.
    >>  ............................................
    pt  Todo mundo esquece. Você voltou, que é a parte que conta.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have spent a week knowing.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu passaria a semana sabendo.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. That stings and it stings less than the pretending would have.
    >>  ............................................
    pt  Você esqueceu. Isso dói e dói menos do que fingir doeria.
    >>  ............................................
  gloomy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. I'd told myself that in advance, so that it wouldn't land.
    >>  ............................................
    pt  Todo mundo esquece. Eu tinha dito isso a mim antes, pra não pegar.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. I'd rather that said out loud than performed at me.
    >>  ............................................
    pt  Você esqueceu. Certo. Prefiro ouvir dito a ver encenado.
    >>  ............................................
  greedy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended and I'd have known.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido e eu saberia.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. I'd rather that said out loud than performed at me.
    >>  ............................................
    pt  Você esqueceu. Certo. Prefiro ouvir dito a ver encenado.
    >>  ............................................
  grumpy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least.
    >>  ............................................
    pt  Ao menos honesto.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right.
    >>  ............................................
    pt  Você esqueceu. Certo.
    >>  ............................................
  introverted.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended. Pretending never lasts.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido. Fingir nunca dura.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Months pass and things fall out of heads. It's not a crime.
    >>  ............................................
    pt  Você esqueceu. Meses passam e as coisas caem da cabeça. Não é crime.
    >>  ............................................
  lazy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Coming back afterwards is what separates people, not the forgetting.
    >>  ............................................
    pt  Todo mundo esquece. Voltar depois é o que separa as pessoas, não o esquecer.
    >>  ............................................
  odd.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least.
    >>  ............................................
    pt  Ao menos honesto.
    >>  ............................................
  odd.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right.
    >>  ............................................
    pt  Você esqueceu. Certo.
    >>  ............................................
  odd.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended. Pretending never lasts.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido. Fingir nunca dura.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Months pass and things fall out of heads. It's not a crime.
    >>  ............................................
    pt  Você esqueceu. Meses passam e as coisas caem da cabeça. Não é crime.
    >>  ............................................
  peaceful.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Coming back afterwards is what separates people, not the forgetting.
    >>  ............................................
    pt  Todo mundo esquece. Voltar depois é o que separa as pessoas, não o esquecer.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least! Anyone else would have pretended, badly, and I'd have known.
    >>  ............................................
    pt  Ao menos honesto! Qualquer outro teria fingido, mal, e eu saberia.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. Everyone forgets; you're just the first to admit it aloud.
    >>  ............................................
    pt  Você esqueceu. Certo. Todo mundo esquece; você só é o primeiro a admitir.
    >>  ............................................
  peppy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets! Not everyone says so. I'll take the saying so.
    >>  ............................................
    pt  Todo mundo esquece! Nem todo mundo diz. Eu fico com o dizer.
    >>  ............................................
  playful.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least! Anyone else would have pretended, badly, and I'd have known.
    >>  ............................................
    pt  Ao menos honesto! Qualquer outro teria fingido, mal, e eu saberia.
    >>  ............................................
  playful.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. Everyone forgets; you're just the first to admit it aloud.
    >>  ............................................
    pt  Você esqueceu. Certo. Todo mundo esquece; você só é o primeiro a admitir.
    >>  ............................................
  playful.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets! Not everyone says so. I'll take the saying so.
    >>  ............................................
    pt  Todo mundo esquece! Nem todo mundo diz. Eu fico com o dizer.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least. Anyone else would have pretended. Pretending never lasts.
    >>  ............................................
    pt  Ao menos honesto. Qualquer outro teria fingido. Fingir nunca dura.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Months pass and things fall out of heads. It's not a crime.
    >>  ............................................
    pt  Você esqueceu. Meses passam e as coisas caem da cabeça. Não é crime.
    >>  ............................................
  relaxed.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Coming back afterwards is what separates people, not the forgetting.
    >>  ............................................
    pt  Todo mundo esquece. Voltar depois é o que separa as pessoas, não o esquecer.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least, %1$s. Anyone else would have pretended and I'd have spent a week knowing.
    >>  ............................................
    pt  Ao menos honesto, %1$s. Qualquer outro teria fingido e eu passaria a semana sabendo.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. That stings and it stings less than the pretending would have.
    >>  ............................................
    pt  Você esqueceu. Isso dói e dói menos do que fingir doeria.
    >>  ............................................
  sensitive.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. I'd told myself that in advance, so that it wouldn't land.
    >>  ............................................
    pt  Todo mundo esquece. Eu tinha dito isso a mim antes, pra não pegar.
    >>  ............................................
  shy.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least.
    >>  ............................................
    pt  Ao menos honesto.
    >>  ............................................
  shy.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right.
    >>  ............................................
    pt  Você esqueceu. Certo.
    >>  ............................................
  shy.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets. Not everyone says so.
    >>  ............................................
    pt  Todo mundo esquece. Nem todo mundo diz.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least! Anyone else would have pretended, badly, and I'd have known.
    >>  ............................................
    pt  Ao menos honesto! Qualquer outro teria fingido, mal, e eu saberia.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. Everyone forgets; you're just the first to admit it aloud.
    >>  ............................................
    pt  Você esqueceu. Certo. Todo mundo esquece; você só é o primeiro a admitir.
    >>  ............................................
  upbeat.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets! Not everyone says so. I'll take the saying so.
    >>  ............................................
    pt  Todo mundo esquece! Nem todo mundo diz. Eu fico com o dizer.
    >>  ............................................
  witty.dialogue.conversations.family.resume.admit_forgot/1
    en  Honest, at least! Anyone else would have pretended, badly, and I'd have known.
    >>  ............................................
    pt  Ao menos honesto! Qualquer outro teria fingido, mal, e eu saberia.
    >>  ............................................
  witty.dialogue.conversations.family.resume.admit_forgot/2
    en  You forgot. Right. Everyone forgets; you're just the first to admit it aloud.
    >>  ............................................
    pt  Você esqueceu. Certo. Todo mundo esquece; você só é o primeiro a admitir.
    >>  ............................................
  witty.dialogue.conversations.family.resume.admit_forgot/3
    en  Everyone forgets! Not everyone says so. I'll take the saying so.
    >>  ............................................
    pt  Todo mundo esquece! Nem todo mundo diz. Eu fico com o dizer.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `family.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.family.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.family.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.family.resume.respond.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao que fazia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.arc.family.resume.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.leave`: the villager accepts. Subject `memories.revisit`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a toddler/child/teen/adult
NOTE   the same pool is also spoken at: conversations.arc.family.resume.close / leave; conversations.arc.family.resume.followup / leave
```

> Written out in full under **`conversations.arc.family.resume.close` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.family`

**Reached from 45 route(s):** `conversations.arc.family.resume.close` / `say_it_matters`; `conversations.arc.family.resume.close` / `ask_again_later`; `conversations.arc.family.resume.close` / `leave`; `conversations.arc.family.resume.followup` / `leave`; `conversations.arc.family.resume.respond` / `leave`; `conversations.cat.relationships` / `family`; `conversations.family` / `checkin_child`; `conversations.family` / `ask_parent`; `conversations.family` / `memories`; `conversations.scene.memories.followup` / `leave`; `conversations.scene.memories.the_house_as_it_was.respond` / `leave`; `conversations.scene.memories.the_one_who_is_not_here.respond` / `leave` …and 33 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.ask_parent.event.leave` — e.g. "...Alright."
- `conversations.ask_parent.event.plain` — e.g. "Thank you. The guessing was worse than any of it turned out to be."
- `conversations.ask_parent.event.together` — e.g. "Do you mean that? People say next time and then there's a next time."
- `conversations.ask_parent.pride.leave` — e.g. "Later, then."
- `conversations.ask_parent.pride.said` — e.g. "You mean it? People say things to children. You don't say things."
- `conversations.ask_parent.pride.show` — e.g. "Now? ...Alright. Don't say anything about the corner, I know about the corner."
- `conversations.ask_parent.rebuffed.apologize` — e.g. "...Then I'll ask again tomorrow. Quieter, mind."
- `conversations.ask_parent.rebuffed.explain` — e.g. "...That's a fair distinction, and I'll try to hear it as one."
- `conversations.ask_parent.rebuffed.leave` — e.g. "Just so. Do that."
- `conversations.checkin_child.promise.keep` — e.g. "This week. I'm saying it out loud so you can't quietly mean next week."
- `conversations.checkin_child.promise.leave` — e.g. "...Later."
- `conversations.checkin_child.promise.owned` — e.g. "...Nobody says that to me. Grown people usually explain instead."
- `conversations.checkin_child.rebuffed.apologize` — e.g. "...It was. Thank you for noticing before I had to explain it."
- `conversations.checkin_child.rebuffed.explain` — e.g. "...Nothing about them is light to me. That's the whole trouble."
- …and 23 more pools


```text
POOL   dialogue key: dialogue.conversations.family
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.family
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.family   [17 chars]
    en  How's the family?
    >>  ............................................
    pt  Como vai a família?
    >>  ............................................
```


### Button `checkin_child` — "How are you holding up, little one?"

Shown only when MCA's own constraints hold: `"kids"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.checkin_child` — accepted phrasings: "holding up"; "how are the kids"; "little one"; "how is your child"
  - the message must contain one of: `child`, `holding`
  - scored words: `child`(1.4), `holding`(1.0), `okay`(0.6)

```text
POOL   dialogue key: dialogue.conversations.family.checkin_child
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.family.checkin_child   [35 chars]
    en  How are you holding up, little one?
    >>  ............................................
    pt  Como você está se virando, pequeno?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.checkin_child` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin_child` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.checkin_child` (this player only) for 36000 ticks
- Then opens: `conversations.topic.checkin_child.respond`
- …where the player's next choices will be: "Tell me how they really are." | "You're doing right by them." | "Children are all the same." | "Was there something I promised you?" | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.again
WHO    VILLAGER — what the player reads after pressing "How are you holding up, little one?"
       spoken on: conversations.family, button `checkin_child`
       leaves the player on: conversations.topic.checkin_child.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.again.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.again/1   [81 chars]
    en  You already asked! Nothing new happened since then. Well. One thing. No, nothing.
    >>  ............................................
    pt  Você já perguntou! Não aconteceu nada novo desde então. Bom. Uma coisa. Não, nada.
    >>  ............................................
  dialogue.conversations.family.child.again/2   [67 chars]
    en  Still the same as before. You keep checking like I might've broken.
    >>  ............................................
    pt  Continuo igual a antes. Você fica checando como se eu fosse quebrar.
    >>  ............................................
  dialogue.conversations.family.child.again/3   [51 chars]
    en  I'm FINE. I was fine the first time you asked, too.
    >>  ............................................
    pt  Eu estou BEM. Eu já estava bem da primeira vez que você perguntou também.
    >>  ............................................
```


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: weighted +100 when the villager is a `baby`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.checkin_child` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin_child` branch `little` budget `relationship`
- Does: remembers `mcaconversations.cooldown.checkin_child` (this player only) for 36000 ticks
- Then opens: `conversations.topic.checkin_child.respond`
- …where the player's next choices will be: "Tell me how they really are." | "You're doing right by them." | "Children are all the same." | "Was there something I promised you?" | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.little
WHO    VILLAGER — what the player reads after pressing "How are you holding up, little one?"
       spoken on: conversations.family, button `checkin_child`
       leaves the player on: conversations.topic.checkin_child.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.little.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.little/1   [45 chars]
    en  Up! Up! I found a bug and it's my friend now!
    >>  ............................................
    pt  Colo! Colo! Achei um bicho e agora ele é meu amigo!
    >>  ............................................
  dialogue.conversations.family.child.little/2   [38 chars]
    en  I'm gonna be tall tomorrow. I decided.
    >>  ............................................
    pt  Amanhã eu vou ser alto. Já decidi.
    >>  ............................................
  dialogue.conversations.family.child.little/3   [44 chars]
    en  I ate a flower today. Don't tell the flower.
    >>  ............................................
    pt  Comi uma flor hoje. Não conta pra flor.
    >>  ............................................
```


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.checkin_child` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin_child` branch `teen` budget `relationship`
- Does: remembers `mcaconversations.cooldown.checkin_child` (this player only) for 36000 ticks
- Then opens: `conversations.topic.checkin_child.respond`
- …where the player's next choices will be: "Tell me how they really are." | "You're doing right by them." | "Children are all the same." | "Was there something I promised you?" | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.teen
WHO    VILLAGER — what the player reads after pressing "How are you holding up, little one?"
       spoken on: conversations.family, button `checkin_child`
       leaves the player on: conversations.topic.checkin_child.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.teen.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.teen/1   [72 chars]
    en  I'm fine. Everyone keeps asking. I'm FINE. ...Thanks for asking, though.
    >>  ............................................
    pt  Estou bem. Todo mundo fica perguntando. Eu estou BEM. ...Mas obrigado por perguntar.
    >>  ............................................
  dialogue.conversations.family.child.teen/2   [75 chars]
    en  Can I have more responsibility and also no responsibility? Is that a thing?
    >>  ............................................
    pt  Posso ter mais responsabilidade e também nenhuma responsabilidade? Isso existe?
    >>  ............................................
```


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.checkin_child` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin_child` branch `doing` budget `relationship`
- Does: remembers `mcaconversations.cooldown.checkin_child` (this player only) for 36000 ticks
- Then opens: `conversations.topic.checkin_child.respond`
- …where the player's next choices will be: "Tell me how they really are." | "You're doing right by them." | "Children are all the same." | "Was there something I promised you?" | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.doing
WHO    VILLAGER — what the player reads after pressing "How are you holding up, little one?"
       spoken on: conversations.family, button `checkin_child`
       leaves the player on: conversations.topic.checkin_child.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.doing.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.doing/1   [59 chars]
    en  Pretty good! I beat the miller's kid at races twice. TWICE.
    >>  ............................................
    pt  Muito bem! Ganhei do filho do moleiro na corrida duas vezes. DUAS.
    >>  ............................................
  dialogue.conversations.family.child.doing/2   [79 chars]
    en  Good! I taught the cat to sit. It sat for entirely unrelated reasons but STILL.
    >>  ............................................
    pt  Bem! Ensinei o gato a sentar. Ele sentou por motivos completamente alheios, mas MESMO ASSIM.
    >>  ............................................
  dialogue.conversations.family.child.doing/3   [58 chars]
    en  I grew this much since you last asked! At least this much.
    >>  ............................................
    pt  Cresci isso tudo desde a última vez que você perguntou! Pelo menos isso tudo.
    >>  ............................................
```


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.checkin_child` (this player only) for 36000 ticks
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.child.doing
WHO    VILLAGER — what the player reads after pressing "How are you holding up, little one?"
       spoken on: conversations.family, button `checkin_child`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.doing.terminal`: the villager accepts. Subject `family.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.family` / button `checkin_child`** earlier in this file. Fill it in there, once.


### Button `ask_parent` — "Can I ask you something? Parent to child?"

Shown only when MCA's own constraints hold: `"parent"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.ask_parent` — accepted phrasings: "your parents"; "parent to child"; "raised you"
  - the message must contain one of: `parent`, `raised`
  - scored words: `parent`(1.5), `raised`(0.8), `advice`(0.8)

```text
POOL   dialogue key: dialogue.conversations.family.ask_parent
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.family.ask_parent   [41 chars]
    en  Can I ask you something? Parent to child?
    >>  ............................................
    pt  Posso te perguntar uma coisa? De pai pra filho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.ask_parent` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `ask_parent` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.ask_parent` (this player only) for 36000 ticks
- Then opens: `conversations.topic.ask_parent.respond`
- …where the player's next choices will be: "I'll tell you the truth." | "You don't need to worry about me." | "Stop fussing over me." | "Is there something you're proud of?" | "I should go."

```text
POOL   dialogue key: dialogue.conversations.family.parent.again
WHO    VILLAGER — what the player reads after pressing "Can I ask you something? Parent to child?"
       spoken on: conversations.family, button `ask_parent`
       leaves the player on: conversations.topic.ask_parent.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.again.to.ask_parent`: the villager accepts. Subject `ask_parent`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.again/1   [77 chars]
    en  You've had one question out of me today. Save the next for when it's heavier.
    >>  ............................................
    pt  Você já tirou uma pergunta de mim hoje. Guarde a próxima pra quando for mais pesada.
    >>  ............................................
  dialogue.conversations.family.parent.again/2   [58 chars]
    en  Ask me twice and you'll get the same advice, only shorter.
    >>  ............................................
    pt  Pergunte duas vezes e vai ouvir o mesmo conselho, só que mais curto.
    >>  ............................................
  dialogue.conversations.family.parent.again/3   [74 chars]
    en  That question again? Go on, then. But I'm no wiser than I was an hour ago.
    >>  ............................................
    pt  Essa pergunta de novo? Pode falar. Mas não sei mais nada do que sabia há uma hora.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.ask_parent` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `ask_parent` branch `worried` budget `relationship`
- Does: remembers `mcaconversations.cooldown.ask_parent` (this player only) for 36000 ticks
- Then opens: `conversations.topic.ask_parent.respond`
- …where the player's next choices will be: "I'll tell you the truth." | "You don't need to worry about me." | "Stop fussing over me." | "Is there something you're proud of?" | "I should go."

```text
POOL   dialogue key: dialogue.conversations.family.parent.worried
WHO    VILLAGER — what the player reads after pressing "Can I ask you something? Parent to child?"
       spoken on: conversations.family, button `ask_parent`
       leaves the player on: conversations.topic.ask_parent.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.worried.to.ask_parent`: the villager accepts. Subject `ask_parent`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.worried/1   [73 chars]
    en  I worry, that's all. It's the job. You never stop being someone's parent.
    >>  ............................................
    pt  Eu me preocupo, é só isso. É o ofício. Você nunca deixa de ser pai de alguém.
    >>  ............................................
  dialogue.conversations.family.parent.worried/2   [75 chars]
    en  You look thinner. Are you eating? Don't make that face, it's my job to ask.
    >>  ............................................
    pt  Você está mais magro. Está comendo? Não faz essa cara, é meu ofício perguntar.
    >>  ............................................
  dialogue.conversations.family.parent.worried/3   [78 chars]
    en  I still check the door at night wondering if you're safe out there. Old habit.
    >>  ............................................
    pt  Ainda confiro a porta de noite me perguntando se você está seguro lá fora. Mania antiga.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.ask_parent` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `ask_parent` branch `proud` budget `relationship`
- Does: remembers `mcaconversations.cooldown.ask_parent` (this player only) for 36000 ticks
- Then opens: `conversations.topic.ask_parent.respond`
- …where the player's next choices will be: "I'll tell you the truth." | "You don't need to worry about me." | "Stop fussing over me." | "Is there something you're proud of?" | "I should go."

```text
POOL   dialogue key: dialogue.conversations.family.parent.proud
WHO    VILLAGER — what the player reads after pressing "Can I ask you something? Parent to child?"
       spoken on: conversations.family, button `ask_parent`
       leaves the player on: conversations.topic.ask_parent.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.proud.to.ask_parent`: the villager accepts. Subject `ask_parent`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.parent.proud/1   [72 chars]
    en  You turned out well, you know. I don't say it enough. I'm saying it now.
    >>  ............................................
    pt  Você se tornou uma boa pessoa, sabia? Eu não digo isso o bastante. Estou dizendo agora.
    >>  ............................................
  dialogue.conversations.family.parent.proud/2   [74 chars]
    en  Ask away. Though watching you lately, you need less advice than you think.
    >>  ............................................
    pt  Pode perguntar. Mas vendo você ultimamente, precisa de menos conselho do que pensa.
    >>  ............................................
  dialogue.conversations.family.parent.proud/3   [70 chars]
    en  Whatever you're about to ask — yes, I'm proud of you. Now what was it?
    >>  ............................................
    pt  Seja lá o que você vai perguntar — sim, tenho orgulho de você. Agora, o que era mesmo?
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.ask_parent` (this player only) for 36000 ticks
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.parent.proud
WHO    VILLAGER — what the player reads after pressing "Can I ask you something? Parent to child?"
       spoken on: conversations.family, button `ask_parent`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.parent.proud.terminal`: the villager accepts. Subject `family.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.family` / button `ask_parent`** earlier in this file. Fill it in there, once.


### Button `memories` — "Tell me a family story."

Shown only when MCA's own constraints hold: `"family"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.memories` — accepted phrasings: "family story"; "a memory"; "tell me a story"; "family memories"
  - the message must contain one of: `memory`, `story`, `past`
  - scored words: `memory`(1.2), `story`(1.2), `past`(0.8), `remember`(0.8)

```text
POOL   dialogue key: dialogue.conversations.family.memories
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.family.memories   [23 chars]
    en  Tell me a family story.
    >>  ............................................
    pt  Me conta uma história da família.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.memories.the_house_as_it_was"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.memories.the_house_as_it_was", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.scene.memories.the_house_as_it_was.respond`
- …where the player's next choices will be: "What are the mornings like?" | "Sounds like a full house." | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_house_as_it_was
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.scene.memories.the_house_as_it_was.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_house_as_it_was.open`: the villager reminisces. Subject `memories.household`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_house_as_it_was/1   [111 chars]
    en  It was loud in the morning and asleep by nine, and I complained about both and would take either back tomorrow.
    >>  ............................................
    pt  Era barulhenta de manhã e dormia às nove, e eu reclamava das duas coisas e aceitaria qualquer uma de volta amanhã.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was/2   [105 chars]
    en  We were all in each other's way, and I did not know at the time that it was the arrangement I would miss.
    >>  ............................................
    pt  Estávamos todos no caminho uns dos outros, e eu não sabia na época que era o arranjo de que eu sentiria falta.
    >>  ............................................
  dialogue.conversations.scene.memories.the_house_as_it_was/3   [107 chars]
    en  Everybody was well and nobody was easy, which is the ordinary state of a house and nobody puts it in songs.
    >>  ............................................
    pt  Todo mundo estava bem e ninguém era fácil, que é o estado comum de uma casa e ninguém põe isso em canção.
    >>  ............................................
```


**Outcome 2 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.memories.the_one_who_is_not_here"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.memories.the_one_who_is_not_here", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.scene.memories.the_one_who_is_not_here.respond`
- …where the player's next choices will be: "Six years is a long chair." | "Do you hear from them?" | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.memories.the_one_who_is_not_here
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.scene.memories.the_one_who_is_not_here.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `memories.the_one_who_is_not_here.open`: the villager reports. Subject `memories.absent`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:memories` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.memories.the_one_who_is_not_here/1   [91 chars]
    en  There is a chair at our table that has been nobody's for six years and is still that chair.
    >>  ............................................
    pt  Existe uma cadeira na nossa mesa que não é de ninguém há seis anos e continua sendo aquela cadeira.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here/2   [110 chars]
    en  One of us left and the leaving was reasonable, and I have spent six years being reasonable about it in public.
    >>  ............................................
    pt  Um de nós foi embora e a partida foi razoável, e eu passei seis anos sendo razoável a respeito em público.
    >>  ............................................
  dialogue.conversations.scene.memories.the_one_who_is_not_here/3   [103 chars]
    en  We do not talk about them at the table. We talk about them one at a time, outside, to different people.
    >>  ............................................
    pt  A gente não fala deles à mesa. A gente fala deles um por vez, do lado de fora, para pessoas diferentes.
    >>  ............................................
```


**Outcome 3 of 8** — base weight `0`

- Fires when: weighted +100 when arc `family` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.memories` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `resume` budget `relationship`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.arc.family.resume.respond`
- …where the player's next choices will be: "How did it go, in the end?" | "I said I'd remember. I did." | "I'd forgotten, if I'm honest." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.family.revisit
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.arc.family.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.resume.opener`: the villager reminisces. Subject `memories.revisit`, polarity `mixed`, invites followup, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, candor, exit
NOTE   only ever spoken by a toddler/child/teen/adult
```

```text
  dialogue.conversations.family.revisit/1   [84 chars]
    en  I've been thinking about what we talked about. The family thing. I didn't forget it.
    >>  ............................................
    pt  Venho pensando no que a gente conversou. A coisa da família. Eu não esqueci.
    >>  ............................................
  dialogue.conversations.family.revisit/2   [76 chars]
    en  You remember what I told you, about us? I've been waiting to see if you did.
    >>  ............................................
    pt  Você lembra do que eu te contei, sobre a gente? Eu esperei pra ver se você lembrava.
    >>  ............................................
  dialogue.conversations.family.revisit/3   [86 chars]
    en  That thing about my family — it hasn't gone anywhere since. I thought you should know.
    >>  ............................................
    pt  Aquilo da minha família — não foi a lugar nenhum desde então. Achei que você devia saber.
    >>  ............................................
```


**Outcome 4 of 8** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.memories` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `again` budget `relationship`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.topic.memories.respond`
- …where the player's next choices will be: "I'd forgotten that." | "What else do you remember?" | "That's not how it went." | "That's not quite how I remember it." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.again
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.topic.memories.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.again.to.memories`: the villager accepts. Subject `memories`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.again/1   [83 chars]
    en  I told you the good one already. The rest need supper and a fire to come out right.
    >>  ............................................
    pt  Já te contei a boa. As outras precisam de janta e fogueira pra saírem direito.
    >>  ............................................
  dialogue.conversations.family.memories.again/2   [55 chars]
    en  You've had your story for the week. Family rates apply.
    >>  ............................................
    pt  Você já teve a sua história da semana. Valem as tarifas de família.
    >>  ............................................
  dialogue.conversations.family.memories.again/3   [57 chars]
    en  Later. A story told twice too soon goes stale like bread.
    >>  ............................................
    pt  Depois. História contada duas vezes cedo demais fica dura que nem pão velho.
    >>  ............................................
```


**Outcome 5 of 8** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.memories` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `toddler` budget `relationship`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.topic.memories.respond`
- …where the player's next choices will be: "I'd forgotten that." | "What else do you remember?" | "That's not how it went." | "That's not quite how I remember it." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.toddler
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.topic.memories.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.toddler.to.memories`: the villager accepts. Subject `memories`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.toddler/1   [64 chars]
    en  'Member when we saw the sheep? That was the best day of my LIFE.
    >>  ............................................
    pt  Lembra quando a gente viu a ovelha? Foi o melhor dia da minha VIDA.
    >>  ............................................
  dialogue.conversations.family.memories.toddler/2   [61 chars]
    en  One time you were here and I showed you my rock. You 'member.
    >>  ............................................
    pt  Uma vez você tava aqui e eu te mostrei minha pedra. Você lembra.
    >>  ............................................
  dialogue.conversations.family.memories.toddler/3   [34 chars]
    en  I remember breakfast. It was good.
    >>  ............................................
    pt  Eu lembro do café da manhã. Estava bom.
    >>  ............................................
```


**Outcome 6 of 8** — base weight `0`

- Fires when: weighted +100 when the personality is `sensitive`, `upbeat`, `friendly`, `peppy`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.memories` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `share` budget `relationship`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.topic.memories.respond`
- …where the player's next choices will be: "I'd forgotten that." | "What else do you remember?" | "That's not how it went." | "That's not quite how I remember it." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.share
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.topic.memories.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.share.to.memories`: the villager accepts. Subject `memories`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.share/1   [89 chars]
    en  Did I ever tell you about the winter my uncle got snowed into the outhouse? No? Sit down.
    >>  ............................................
    pt  Eu já te contei do inverno em que meu tio ficou preso na latrina pela neve? Não? Senta aí.
    >>  ............................................
  dialogue.conversations.family.memories.share/2   [99 chars]
    en  There's a story about my brother and a runaway pig that this family swore to keep secret. Anyway...
    >>  ............................................
    pt  Tem uma história sobre meu irmão e um porco fugitivo que essa família jurou manter em segredo. Enfim...
    >>  ............................................
  dialogue.conversations.family.memories.share/3   [92 chars]
    en  Ask my mother about the beehive someday. Watch her face. That's the whole story right there.
    >>  ............................................
    pt  Pergunte à minha mãe sobre a colmeia um dia desses. Observe a cara dela. A história inteira está ali.
    >>  ............................................
```


**Outcome 7 of 8** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `sensitive`, `upbeat`, `friendly`, `peppy`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.memories` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `memories` branch `tell` budget `relationship`
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.topic.memories.respond`
- …where the player's next choices will be: "I'd forgotten that." | "What else do you remember?" | "That's not how it went." | "That's not quite how I remember it." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.family.memories.tell
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.topic.memories.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.tell.to.memories`: the villager discloses. Subject `memories`, polarity `neutral`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.memories.tell/1   [44 chars]
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma que eu sempre lembro. Senta.
    >>  ............................................
  dialogue.conversations.family.memories.tell/2   [60 chars]
    en  Family stories. I've a hundred and you'll hear at least one.
    >>  ............................................
    pt  Histórias de família. Tenho cem e você vai ouvir pelo menos uma.
    >>  ............................................
  dialogue.conversations.family.memories.tell/3   [42 chars]
    en  Ha — where do I start. The goat, probably.
    >>  ............................................
    pt  Rá — por onde começo. Pelo bode, provavelmente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s. I don't tell it often.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s. Não conto sempre.
    >>  ............................................
  anxious.dialogue.conversations.family.memories.tell/2
    en  One story. It's a good one and it still catches me somewhere in the middle.
    >>  ............................................
    pt  Uma história. É boa e ainda me pega em algum ponto do meio.
    >>  ............................................
  anxious.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll try to get through it without stopping.
    >>  ............................................
    pt  Tem uma. Vou tentar chegar ao fim sem parar.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down; it's not a short one.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se; não é curta.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.tell/2
    en  One story. It's been the same story for thirty years and it hasn't worn out.
    >>  ............................................
    pt  Uma história. É a mesma história há trinta anos e não gastou.
    >>  ............................................
  athletic.dialogue.conversations.family.memories.tell/3
    en  There's one. It takes as long as it takes, so make yourself comfortable.
    >>  ............................................
    pt  Tem uma. Leva o tempo que levar, então fique à vontade.
    >>  ............................................
  confident.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  confident.dialogue.conversations.family.memories.tell/2
    en  One story, and I'll tell it once. Sit.
    >>  ............................................
    pt  Uma história, e eu conto uma vez. Sente.
    >>  ............................................
  confident.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll tell it plainly and I'll not dress it up.
    >>  ............................................
    pt  Tem uma. Vou contar direto e não vou enfeitar.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.tell/2
    en  One story, and I'll tell it once. Sit.
    >>  ............................................
    pt  Uma história, e eu conto uma vez. Sente.
    >>  ............................................
  crabby.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll tell it plainly and I'll not dress it up.
    >>  ............................................
    pt  Tem uma. Vou contar direto e não vou enfeitar.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s — this one's for people I like.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s — essa é pra gente de quem eu gosto.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.tell/2
    en  One story. I've been waiting for somebody to ask, if I'm honest.
    >>  ............................................
    pt  Uma história. Se for honesto, eu vinha esperando alguém perguntar.
    >>  ............................................
  extroverted.dialogue.conversations.family.memories.tell/3
    en  There's one. Sit with me and I'll give you the whole of it.
    >>  ............................................
    pt  Tem uma. Sente comigo e eu te dou ela inteira.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s — this one's for people I like.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s — essa é pra gente de quem eu gosto.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.tell/2
    en  One story. I've been waiting for somebody to ask, if I'm honest.
    >>  ............................................
    pt  Uma história. Se for honesto, eu vinha esperando alguém perguntar.
    >>  ............................................
  flirty.dialogue.conversations.family.memories.tell/3
    en  There's one. Sit with me and I'll give you the whole of it.
    >>  ............................................
    pt  Tem uma. Sente comigo e eu te dou ela inteira.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s — this one's for people I like.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s — essa é pra gente de quem eu gosto.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.tell/2
    en  One story. I've been waiting for somebody to ask, if I'm honest.
    >>  ............................................
    pt  Uma história. Se for honesto, eu vinha esperando alguém perguntar.
    >>  ............................................
  friendly.dialogue.conversations.family.memories.tell/3
    en  There's one. Sit with me and I'll give you the whole of it.
    >>  ............................................
    pt  Tem uma. Sente comigo e eu te dou ela inteira.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s. I don't tell it often.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s. Não conto sempre.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.tell/2
    en  One story. It's a good one and it still catches me somewhere in the middle.
    >>  ............................................
    pt  Uma história. É boa e ainda me pega em algum ponto do meio.
    >>  ............................................
  gloomy.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll try to get through it without stopping.
    >>  ............................................
    pt  Tem uma. Vou tentar chegar ao fim sem parar.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.tell/2
    en  One story, and I'll tell it once. Sit.
    >>  ............................................
    pt  Uma história, e eu conto uma vez. Sente.
    >>  ............................................
  greedy.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll tell it plainly and I'll not dress it up.
    >>  ............................................
    pt  Tem uma. Vou contar direto e não vou enfeitar.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.tell/2
    en  One story, and I'll tell it once. Sit.
    >>  ............................................
    pt  Uma história, e eu conto uma vez. Sente.
    >>  ............................................
  grumpy.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll tell it plainly and I'll not dress it up.
    >>  ............................................
    pt  Tem uma. Vou contar direto e não vou enfeitar.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.tell/2
    en  One. I'll tell it quietly.
    >>  ............................................
    pt  Uma. Vou contar baixinho.
    >>  ............................................
  introverted.dialogue.conversations.family.memories.tell/3
    en  There's one. It's short and it's the one that stayed.
    >>  ............................................
    pt  Tem uma. É curta e é a que ficou.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down; it's not a short one.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se; não é curta.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.tell/2
    en  One story. It's been the same story for thirty years and it hasn't worn out.
    >>  ............................................
    pt  Uma história. É a mesma história há trinta anos e não gastou.
    >>  ............................................
  lazy.dialogue.conversations.family.memories.tell/3
    en  There's one. It takes as long as it takes, so make yourself comfortable.
    >>  ............................................
    pt  Tem uma. Leva o tempo que levar, então fique à vontade.
    >>  ............................................
  odd.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  odd.dialogue.conversations.family.memories.tell/2
    en  One. I'll tell it quietly.
    >>  ............................................
    pt  Uma. Vou contar baixinho.
    >>  ............................................
  odd.dialogue.conversations.family.memories.tell/3
    en  There's one. It's short and it's the one that stayed.
    >>  ............................................
    pt  Tem uma. É curta e é a que ficou.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down; it's not a short one.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se; não é curta.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.tell/2
    en  One story. It's been the same story for thirty years and it hasn't worn out.
    >>  ............................................
    pt  Uma história. É a mesma história há trinta anos e não gastou.
    >>  ............................................
  peaceful.dialogue.conversations.family.memories.tell/3
    en  There's one. It takes as long as it takes, so make yourself comfortable.
    >>  ............................................
    pt  Tem uma. Leva o tempo que levar, então fique à vontade.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to! Sit down, this takes a moment and it's worth it.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto! Sente-se, leva um momento e vale a pena.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.tell/2
    en  One story. It's the good one. I've told it eleven times and it improves each time.
    >>  ............................................
    pt  Uma história. É a boa. Já contei onze vezes e melhora sempre.
    >>  ............................................
  peppy.dialogue.conversations.family.memories.tell/3
    en  There's one I keep. Sit — you'll want to be sitting for the end of it.
    >>  ............................................
    pt  Tem uma que eu guardo. Sente — você vai querer estar sentado no fim.
    >>  ............................................
  playful.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to! Sit down, this takes a moment and it's worth it.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto! Sente-se, leva um momento e vale a pena.
    >>  ............................................
  playful.dialogue.conversations.family.memories.tell/2
    en  One story. It's the good one. I've told it eleven times and it improves each time.
    >>  ............................................
    pt  Uma história. É a boa. Já contei onze vezes e melhora sempre.
    >>  ............................................
  playful.dialogue.conversations.family.memories.tell/3
    en  There's one I keep. Sit — you'll want to be sitting for the end of it.
    >>  ............................................
    pt  Tem uma que eu guardo. Sente — você vai querer estar sentado no fim.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down; it's not a short one.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se; não é curta.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.tell/2
    en  One story. It's been the same story for thirty years and it hasn't worn out.
    >>  ............................................
    pt  Uma história. É a mesma história há trinta anos e não gastou.
    >>  ............................................
  relaxed.dialogue.conversations.family.memories.tell/3
    en  There's one. It takes as long as it takes, so make yourself comfortable.
    >>  ............................................
    pt  Tem uma. Leva o tempo que levar, então fique à vontade.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down, %1$s. I don't tell it often.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se, %1$s. Não conto sempre.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.tell/2
    en  One story. It's a good one and it still catches me somewhere in the middle.
    >>  ............................................
    pt  Uma história. É boa e ainda me pega em algum ponto do meio.
    >>  ............................................
  sensitive.dialogue.conversations.family.memories.tell/3
    en  There's one. I'll try to get through it without stopping.
    >>  ............................................
    pt  Tem uma. Vou tentar chegar ao fim sem parar.
    >>  ............................................
  shy.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to. Sit down.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto. Sente-se.
    >>  ............................................
  shy.dialogue.conversations.family.memories.tell/2
    en  One. I'll tell it quietly.
    >>  ............................................
    pt  Uma. Vou contar baixinho.
    >>  ............................................
  shy.dialogue.conversations.family.memories.tell/3
    en  There's one. It's short and it's the one that stayed.
    >>  ............................................
    pt  Tem uma. É curta e é a que ficou.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to! Sit down, this takes a moment and it's worth it.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto! Sente-se, leva um momento e vale a pena.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.tell/2
    en  One story. It's the good one. I've told it eleven times and it improves each time.
    >>  ............................................
    pt  Uma história. É a boa. Já contei onze vezes e melhora sempre.
    >>  ............................................
  upbeat.dialogue.conversations.family.memories.tell/3
    en  There's one I keep. Sit — you'll want to be sitting for the end of it.
    >>  ............................................
    pt  Tem uma que eu guardo. Sente — você vai querer estar sentado no fim.
    >>  ............................................
  witty.dialogue.conversations.family.memories.tell/1
    en  There's one I always come back to! Sit down, this takes a moment and it's worth it.
    >>  ............................................
    pt  Tem uma à qual eu sempre volto! Sente-se, leva um momento e vale a pena.
    >>  ............................................
  witty.dialogue.conversations.family.memories.tell/2
    en  One story. It's the good one. I've told it eleven times and it improves each time.
    >>  ............................................
    pt  Uma história. É a boa. Já contei onze vezes e melhora sempre.
    >>  ............................................
  witty.dialogue.conversations.family.memories.tell/3
    en  There's one I keep. Sit — you'll want to be sitting for the end of it.
    >>  ............................................
    pt  Tem uma que eu guardo. Sente — você vai querer estar sentado no fim.
    >>  ............................................
```

</details>


**Outcome 8 of 8** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.memories` (this player only) for 36000 ticks
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.memories.tell
WHO    VILLAGER — what the player reads after pressing "Tell me a family story."
       spoken on: conversations.family, button `memories`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.memories.tell.terminal`: the villager discloses. Subject `family.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.family` / button `memories`** earlier in this file. Fill it in there, once.


### Button `back` — "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.family.back   [32 chars]
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


## `conversations.topic.family.close`

**Reached from 8 route(s):** `conversations.topic.ask_parent.followup` / `ask_worries`; `conversations.topic.ask_parent.followup` / `thank_them`; `conversations.topic.ask_parent.followup` / `deflect`; `conversations.topic.checkin_child.followup` / `guide`; `conversations.topic.checkin_child.followup` / `play_along`; `conversations.topic.memories.followup` / `add_own`; `conversations.topic.memories.followup` / `question_detail`; `conversations.topic.memories.followup` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.child.guide` — e.g. "...That's useful. Actually useful, not the usual advice."
- `conversations.family.child.play_along` — e.g. "They ARE. Don't tell them I agreed with you."
- `conversations.family.memories.add_own` — e.g. "You've one I don't know? Go on — I'll not interrupt. Much."
- `conversations.family.memories.dismiss` — e.g. "...I do always tell that one. It's a good one."
- `conversations.family.memories.question_detail` — e.g. "...It might have been the other summer. Doesn't spoil it."
- `conversations.family.parent.ask_worries` — e.g. "...You want to know what I worry about? Nobody's asked me that."
- `conversations.family.parent.deflect` — e.g. "Nothing. It's never nothing, %1$s."
- `conversations.family.parent.thank_them` — e.g. "...You're welcome. It's the only useful thing I do."


```text
POOL   dialogue key: dialogue.conversations.topic.family.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.family.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.family.close   [16 chars]
    en  Family's family.
    >>  ............................................
    pt  Família é família.
    >>  ............................................
```


### Button `thank` — "Thank you for telling me."

*stance family `candor` · tone `gentle` · answers the beat(s) `family.child.guide.to.family`, `family.child.play_along.to.family`, `family.memories.add_own.to.family`, `family.memories.dismiss.to.family`, `family.memories.question_detail.to.family`, `family.parent.ask_worries.to.family`, `family.parent.deflect.to.family`, `family.parent.thank_them.to.family`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.close.thank` — accepted phrasings: "thank you for telling me"; "thanks for telling me that"; "i am glad you told me"
  - the message must contain one of: `telling`
  - scored words: `telling`(1.2), `thank`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.family.close.thank
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.close.thank   [25 chars]
    en  Thank you for telling me.
    >>  ............................................
    pt  Obrigado por me contar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `family.close.thank`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `family.close.thank`)_
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.close.thank
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me."
       spoken on: conversations.topic.family.close, button `thank`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.close.thank.terminal`: the villager accepts. Subject `family.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.family.close.thank/1   [49 chars]
    en  Family doesn't need thanking. But you're welcome.
    >>  ............................................
    pt  Família não precisa agradecer. Mas de nada.
    >>  ............................................
  dialogue.conversations.family.close.thank/2   [47 chars]
    en  True enough, well. That's what we're for, %1$s.
    >>  ............................................
    pt  Bem verdade, bom. É para isso que servimos, %1$s.
    >>  ............................................
  dialogue.conversations.family.close.thank/3   [9 chars]
    en  Any time.
    >>  ............................................
    pt  Quando quiser.
    >>  ............................................
```


### Button `say_means` — "That mattered, what you said."

*stance family `candor` · tone `gentle` · answers the beat(s) `family.child.guide.to.family`, `family.child.play_along.to.family`, `family.memories.add_own.to.family`, `family.memories.dismiss.to.family`, `family.memories.question_detail.to.family`, `family.parent.ask_worries.to.family`, `family.parent.deflect.to.family`, `family.parent.thank_them.to.family`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `family.close.say_means` — accepted phrasings: "that mattered what you said"; "what you said mattered to me"; "that meant something to me"
  - the message must contain one of: `mattered`
  - scored words: `mattered`(1.5), `said`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.family.close.say_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.close.say_means   [29 chars]
    en  That mattered, what you said.
    >>  ............................................
    pt  Isso importou, o que você disse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `family.close.say_means`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +2, familiarity +2  _(recorded under topic `family.close.say_means`)_
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.close.say_means
WHO    VILLAGER — what the player reads after pressing "That mattered, what you said."
       spoken on: conversations.topic.family.close, button `say_means`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.close.say_means.terminal`: the villager accepts. Subject `family.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.family.close.say_means/1   [38 chars]
    en  ...It did. More than I'd say out loud.
    >>  ............................................
    pt  ...Importou. Mais do que eu diria em voz alta.
    >>  ............................................
  dialogue.conversations.family.close.say_means/2   [29 chars]
    en  Then it was worth the saying.
    >>  ............................................
    pt  Então valeu a pena dizer.
    >>  ............................................
  dialogue.conversations.family.close.say_means/3   [42 chars]
    en  Good. We don't say enough, in this family.
    >>  ............................................
    pt  Bom. A gente não fala o suficiente, nesta família.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `family.child.guide.to.family`, `family.child.play_along.to.family`, `family.memories.add_own.to.family`, `family.memories.dismiss.to.family`, `family.memories.question_detail.to.family`, `family.parent.ask_worries.to.family`, `family.parent.deflect.to.family`, `family.parent.thank_them.to.family` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.family.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.close.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.close.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.family.close, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.close.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.family.close.leave/1   [18 chars]
    en  It is. Off you go.
    >>  ............................................
    pt  É sim. Pode ir.
    >>  ............................................
  dialogue.conversations.family.close.leave/2   [17 chars]
    en  So you are, %1$s.
    >>  ............................................
    pt  Pois é, %1$s.
    >>  ............................................
  dialogue.conversations.family.close.leave/3   [12 chars]
    en  Later, then.
    >>  ............................................
    pt  Depois, então.
    >>  ............................................
```

---


## `conversations.topic.family.scolded.close`

**Reached from 1 route(s):** `conversations.topic.checkin_child.followup` / `scold`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.child.scold` — e.g. "...A firmer hand. That's not what they need."


```text
POOL   dialogue key: dialogue.conversations.topic.family.scolded.close
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.family.scolded.close
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.family.scolded.close   [26 chars]
    en  That's not what they need.
    >>  ............................................
    pt  Não é disso que eles precisam.
    >>  ............................................
```


### Button `apologize` — "You know them. I don't."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `family.child.scolded` · offered only once the villager has actually said `player:urged_severity`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.hurt.apologize` — accepted phrasings: "you know them. i don't"
  - the message must contain one of: `know`, `presume`
  - scored words: `know`(1.0), `them`(0.8), `presume`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.family.scolded.close.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.scolded.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.scolded.close.apologize   [23 chars]
    en  You know them. I don't.
    >>  ............................................
    pt  Você conhece. Eu não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4  _(recorded under topic `family.scolded.apologize`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.scolded.apologize
WHO    VILLAGER — what the player reads after pressing "You know them. I don't."
       spoken on: conversations.topic.family.scolded.close, button `apologize`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.scolded.apologize`: the villager qualifys. Subject `family.child`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.scolded.apologize/1   [68 chars]
    en  ...I do. And most days I've no idea, which is the honest part of it.
    >>  ............................................
    pt  ...Conheço. E na maioria dos dias não faço ideia, que é a parte honesta.
    >>  ............................................
  dialogue.conversations.family.scolded.apologize/2   [63 chars]
    en  Thank you. Everyone has a plan for somebody else's child, %1$s.
    >>  ............................................
    pt  Obrigado. Todo mundo tem um plano para o filho dos outros, %1$s.
    >>  ............................................
  dialogue.conversations.family.scolded.apologize/3   [77 chars]
    en  True enough. Come and watch them for an afternoon and you'll see what I mean.
    >>  ............................................
    pt  Bem verdade. Venha observá-los por uma tarde e você vai ver o que eu quero dizer.
    >>  ............................................
```


### Button `soften` — "What do they need, then?"

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `family.child.scolded`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.hurt.soften` — accepted phrasings: "what do they need, then"
  - the message must contain one of: `need`, `instead`
  - scored words: `need`(1.5), `instead`(1.2), `then`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.family.scolded.close.soften
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.scolded.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.scolded.close.soften   [24 chars]
    en  What do they need, then?
    >>  ............................................
    pt  Então do que eles precisam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `checkin_child.hurt.soften`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension -2, warmth +3  _(recorded under topic `family.scolded.soften`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.scolded.soften
WHO    VILLAGER — what the player reads after pressing "What do they need, then?"
       spoken on: conversations.topic.family.scolded.close, button `soften`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.scolded.soften`: the villager accepts. Subject `family.child`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.scolded.soften/1   [79 chars]
    en  ...Somebody to notice when it's gone right. That's it. That's the whole method.
    >>  ............................................
    pt  ...Alguém que repare quando dá certo. É isso. É o método inteiro.
    >>  ............................................
  dialogue.conversations.family.scolded.soften/2   [62 chars]
    en  Time and a soft voice, mostly, %1$s. It's slower and it works.
    >>  ............................................
    pt  Tempo e voz mansa, principalmente, %1$s. É mais devagar e funciona.
    >>  ............................................
  dialogue.conversations.family.scolded.soften/3   [69 chars]
    en  To be asked, the way you just asked me. Nobody asks a child anything.
    >>  ............................................
    pt  Ser perguntado, do jeito que você acabou de me perguntar. Ninguém pergunta nada a uma criança.
    >>  ............................................
```


### Button `leave` — "I'll not tell you your business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `family.child.scolded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.family.scolded.close.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.family.scolded.close
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.family.scolded.close.leave   [32 chars]
    en  I'll not tell you your business.
    >>  ............................................
    pt  Não vou te dizer como cuidar dos seus.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.scolded.leave
WHO    VILLAGER — what the player reads after pressing "I'll not tell you your business."
       spoken on: conversations.topic.family.scolded.close, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.scolded.leave`: the villager accepts. Subject `family.child`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.scolded.leave/1   [55 chars]
    en  Good. It's the one thing I'd rather nobody helped with.
    >>  ............................................
    pt  Bom. É a única coisa com que eu preferia que ninguém ajudasse.
    >>  ............................................
  dialogue.conversations.family.scolded.leave/2   [23 chars]
    en  Quite. Thank you, %1$s.
    >>  ............................................
    pt  Exato. Obrigado, %1$s.
    >>  ............................................
  dialogue.conversations.family.scolded.leave/3   [18 chars]
    en  Noted. Off you go.
    >>  ............................................
    pt  Anotado. Pode ir.
    >>  ............................................
```

---

