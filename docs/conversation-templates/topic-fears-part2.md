# Topic: fears — part 2 of 2

> Continued from [topic-fears-part1.md](topic-fears-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](topic-fears-part1.md) · [part 2](topic-fears-part2.md)


## Nodes in this file

- [`conversations.topic.fears.open.respond`](#conversations-topic-fears-open-respond)
- [`conversations.topic.fears.pressed`](#conversations-topic-fears-pressed)
- [`conversations.topic.fears.repaired`](#conversations-topic-fears-repaired)
- [`conversations.topic.fears.scarred.respond`](#conversations-topic-fears-scarred-respond)
- [`conversations.topic.fears.toddler.respond`](#conversations-topic-fears-toddler-respond)
- [`conversations.topic.fears.young.respond`](#conversations-topic-fears-young-respond)

---

## `conversations.topic.fears.open.respond` — continued


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.comfort` lands on tier **crit** (axis warmth, difficulty 30, stance empathy, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +3** — decision id `fears.open.comfort.crit`, budget `deep`, replay policy `once`
- Does: disposition — warmth +6, trust +4, familiarity +2  _(recorded under topic `fears.open.comfort`)_
- Does: arc `fears` — advance to stage 1
- Does: milestone `fears.revelation` set (fires once, ever)
- Then opens: `conversations.topic.fears.open.disclosed`
- …where the player's next choices will be: "I'll help you carry that." | "How long have you had that?" | "That's enough for one day."

```text
POOL   dialogue key: dialogue.conversations.fears.open.comfort.crit
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.topic.fears.open.respond, button `comfort`
       leaves the player on: conversations.topic.fears.open.disclosed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.comfort.crit.to.fears.open.disclosed`: the villager accepts. Subject `fears.open.disclosed`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.comfort.crit/1   [138 chars]
    en  ...You mean that. You're not just being polite. Then here's the part I never say: it isn't the thing itself. It's that I'd be alone in it.
    >>  ............................................
    pt  ...Você está falando sério. Não é só educação. Então aqui vai a parte que eu nunca digo: não é a coisa em si. É que eu estaria sozinho nela.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.crit/2   [95 chars]
    en  Careful, %1$s. Kindness like that makes a person put the whole thing down. ...There. It's down.
    >>  ............................................
    pt  Cuidado, %1$s. Uma gentileza dessas faz a pessoa largar tudo no chão. ...Pronto. Larguei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. You're not being polite, %1$s. Then here's the part I've never said.
    >>  ............................................
    pt  ...Você fala sério. Não está sendo educado, %1$s. Então aqui vai a parte que eu nunca disse.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.crit/2
    en  I can tell the difference. I've had a great deal of practice at the other kind.
    >>  ............................................
    pt  Eu sei a diferença. Tive muita prática com o outro tipo.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. Not politeness. Then here's the part I'd usually leave for another year.
    >>  ............................................
    pt  Você fala sério. Não é educação. Então aqui vai a parte que eu deixaria pra outro ano.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.crit/2
    en  Right. It was real. I'll give you the rest, slowly.
    >>  ............................................
    pt  Certo. Foi de verdade. Vou te dar o resto, devagar.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  Você fala sério. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.crit/2
    en  That wasn't a polite noise. Right. Then I'll give you the rest of it.
    >>  ............................................
    pt  Aquilo não foi um ruído educado. Certo. Então eu te dou o resto.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  Você fala sério. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.crit/2
    en  That wasn't a polite noise. Right. Then I'll give you the rest of it.
    >>  ............................................
    pt  Aquilo não foi um ruído educado. Certo. Então eu te dou o resto.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that, %1$s. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério, %1$s. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.crit/2
    en  That landed as true. Alright. I'll trust it and give you the rest.
    >>  ............................................
    pt  Aquilo soou verdadeiro. Está bem. Eu confio e te dou o resto.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that, %1$s. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério, %1$s. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.crit/2
    en  That landed as true. Alright. I'll trust it and give you the rest.
    >>  ............................................
    pt  Aquilo soou verdadeiro. Está bem. Eu confio e te dou o resto.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that, %1$s. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério, %1$s. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.crit/2
    en  That landed as true. Alright. I'll trust it and give you the rest.
    >>  ............................................
    pt  Aquilo soou verdadeiro. Está bem. Eu confio e te dou o resto.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. You're not being polite, %1$s. Then here's the part I've never said.
    >>  ............................................
    pt  ...Você fala sério. Não está sendo educado, %1$s. Então aqui vai a parte que eu nunca disse.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.crit/2
    en  I can tell the difference. I've had a great deal of practice at the other kind.
    >>  ............................................
    pt  Eu sei a diferença. Tive muita prática com o outro tipo.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  Você fala sério. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.crit/2
    en  That wasn't a polite noise. Right. Then I'll give you the rest of it.
    >>  ............................................
    pt  Aquilo não foi um ruído educado. Certo. Então eu te dou o resto.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. You're not just being polite. Then here's the part I don't say.
    >>  ............................................
    pt  Você fala sério. Não está só sendo educado. Então aqui vai a parte que eu não digo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.crit/2
    en  That wasn't a polite noise. Right. Then I'll give you the rest of it.
    >>  ............................................
    pt  Aquilo não foi um ruído educado. Certo. Então eu te dou o resto.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério. Então aqui vai a parte que eu não digo.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.crit/2
    en  Not politeness. Right. There's more, then.
    >>  ............................................
    pt  Não foi educação. Certo. Então tem mais.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. Not politeness. Then here's the part I'd usually leave for another year.
    >>  ............................................
    pt  Você fala sério. Não é educação. Então aqui vai a parte que eu deixaria pra outro ano.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.crit/2
    en  Right. It was real. I'll give you the rest, slowly.
    >>  ............................................
    pt  Certo. Foi de verdade. Vou te dar o resto, devagar.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério. Então aqui vai a parte que eu não digo.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.crit/2
    en  Not politeness. Right. There's more, then.
    >>  ............................................
    pt  Não foi educação. Certo. Então tem mais.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. Not politeness. Then here's the part I'd usually leave for another year.
    >>  ............................................
    pt  Você fala sério. Não é educação. Então aqui vai a parte que eu deixaria pra outro ano.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.crit/2
    en  Right. It was real. I'll give you the rest, slowly.
    >>  ............................................
    pt  Certo. Foi de verdade. Vou te dar o resto, devagar.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that! You're not just being polite. Right — here's the bit I don't hand out.
    >>  ............................................
    pt  Você fala sério! Não está só sendo educado. Certo — aqui vai a parte que eu não distribuo.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.crit/2
    en  That was real. Extraordinary. Sit down, then, there's more.
    >>  ............................................
    pt  Aquilo foi de verdade. Extraordinário. Então sente-se, tem mais.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that! You're not just being polite. Right — here's the bit I don't hand out.
    >>  ............................................
    pt  Você fala sério! Não está só sendo educado. Certo — aqui vai a parte que eu não distribuo.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.crit/2
    en  That was real. Extraordinary. Sit down, then, there's more.
    >>  ............................................
    pt  Aquilo foi de verdade. Extraordinário. Então sente-se, tem mais.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that. Not politeness. Then here's the part I'd usually leave for another year.
    >>  ............................................
    pt  Você fala sério. Não é educação. Então aqui vai a parte que eu deixaria pra outro ano.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.crit/2
    en  Right. It was real. I'll give you the rest, slowly.
    >>  ............................................
    pt  Certo. Foi de verdade. Vou te dar o resto, devagar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. You're not being polite, %1$s. Then here's the part I've never said.
    >>  ............................................
    pt  ...Você fala sério. Não está sendo educado, %1$s. Então aqui vai a parte que eu nunca disse.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.crit/2
    en  I can tell the difference. I've had a great deal of practice at the other kind.
    >>  ............................................
    pt  Eu sei a diferença. Tive muita prática com o outro tipo.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.crit/1
    en  ...You mean that. Then here's the part I don't say.
    >>  ............................................
    pt  ...Você fala sério. Então aqui vai a parte que eu não digo.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.crit/2
    en  Not politeness. Right. There's more, then.
    >>  ............................................
    pt  Não foi educação. Certo. Então tem mais.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that! You're not just being polite. Right — here's the bit I don't hand out.
    >>  ............................................
    pt  Você fala sério! Não está só sendo educado. Certo — aqui vai a parte que eu não distribuo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.crit/2
    en  That was real. Extraordinary. Sit down, then, there's more.
    >>  ............................................
    pt  Aquilo foi de verdade. Extraordinário. Então sente-se, tem mais.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.crit/1
    en  You mean that! You're not just being polite. Right — here's the bit I don't hand out.
    >>  ............................................
    pt  Você fala sério! Não está só sendo educado. Certo — aqui vai a parte que eu não distribuo.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.crit/2
    en  That was real. Extraordinary. Sit down, then, there's more.
    >>  ............................................
    pt  Aquilo foi de verdade. Extraordinário. Então sente-se, tem mais.
    >>  ............................................
```

</details>


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.comfort` lands on tier **success** (axis warmth, difficulty 30, stance empathy, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `fears.open.comfort`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `fears.open.comfort`)_
- Does: arc `fears` — advance to stage 1
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.comfort.success
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.topic.fears.open.respond, button `comfort`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.comfort.success.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.comfort.success/1   [78 chars]
    en  ...That helps. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  ...Isso ajuda. Não faço ideia de por que falar em voz alta para alguém ajuda, mas ajuda.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.success/2   [83 chars]
    en  Right. Something's come off my shoulders and I'd rather not examine it too closely.
    >>  ............................................
    pt  Pronto. Saiu um peso dos meus ombros e eu prefiro não examinar isso de perto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I've no idea why saying it aloud helps, %1$s, and I'd been afraid it wouldn't.
    >>  ............................................
    pt  ...Isso ajuda. Não faço ideia de por que dizer em voz alta ajuda, %1$s, e eu temia que não ajudasse.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.success/2
    en  Better. I'd braced for feeling worse for having said it, and I don't.
    >>  ............................................
    pt  Melhor. Eu tinha me preparado pra me sentir pior por ter dito, e não me sinto.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've never known why. It has helped every time and I've stopped asking.
    >>  ............................................
    pt  Isso ajuda. Nunca soube por quê. Ajudou todas as vezes e eu parei de perguntar.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.success/2
    en  Better. It'll be back next month, and saying it will help again then too.
    >>  ............................................
    pt  Melhor. Vai voltar mês que vem, e dizer vai ajudar de novo.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  Isso ajuda. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.success/2
    en  Right. Better. I'll not pretend to understand the mechanism.
    >>  ............................................
    pt  Certo. Melhor. Não vou fingir que entendo o mecanismo.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  Isso ajuda. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.success/2
    en  Right. Better. I'll not pretend to understand the mechanism.
    >>  ............................................
    pt  Certo. Melhor. Não vou fingir que entendo o mecanismo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps, %1$s. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  ...Isso ajuda, %1$s. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.success/2
    en  Better. And it's specifically better for having said it to you.
    >>  ............................................
    pt  Melhor. E é melhor especificamente por ter dito a você.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps, %1$s. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  ...Isso ajuda, %1$s. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.success/2
    en  Better. And it's specifically better for having said it to you.
    >>  ............................................
    pt  Melhor. E é melhor especificamente por ter dito a você.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps, %1$s. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  ...Isso ajuda, %1$s. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.success/2
    en  Better. And it's specifically better for having said it to you.
    >>  ............................................
    pt  Melhor. E é melhor especificamente por ter dito a você.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I've no idea why saying it aloud helps, %1$s, and I'd been afraid it wouldn't.
    >>  ............................................
    pt  ...Isso ajuda. Não faço ideia de por que dizer em voz alta ajuda, %1$s, e eu temia que não ajudasse.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.success/2
    en  Better. I'd braced for feeling worse for having said it, and I don't.
    >>  ............................................
    pt  Melhor. Eu tinha me preparado pra me sentir pior por ter dito, e não me sinto.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  Isso ajuda. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.success/2
    en  Right. Better. I'll not pretend to understand the mechanism.
    >>  ............................................
    pt  Certo. Melhor. Não vou fingir que entendo o mecanismo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've no idea why saying it aloud to someone helps, but it does.
    >>  ............................................
    pt  Isso ajuda. Não faço ideia de por que dizer em voz alta a alguém ajuda, mas ajuda.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.success/2
    en  Right. Better. I'll not pretend to understand the mechanism.
    >>  ............................................
    pt  Certo. Melhor. Não vou fingir que entendo o mecanismo.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I don't know why.
    >>  ............................................
    pt  ...Isso ajuda. Não sei por quê.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.success/2
    en  Better. Saying it aloud does something.
    >>  ............................................
    pt  Melhor. Dizer em voz alta faz algo.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've never known why. It has helped every time and I've stopped asking.
    >>  ............................................
    pt  Isso ajuda. Nunca soube por quê. Ajudou todas as vezes e eu parei de perguntar.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.success/2
    en  Better. It'll be back next month, and saying it will help again then too.
    >>  ............................................
    pt  Melhor. Vai voltar mês que vem, e dizer vai ajudar de novo.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I don't know why.
    >>  ............................................
    pt  ...Isso ajuda. Não sei por quê.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.success/2
    en  Better. Saying it aloud does something.
    >>  ............................................
    pt  Melhor. Dizer em voz alta faz algo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've never known why. It has helped every time and I've stopped asking.
    >>  ............................................
    pt  Isso ajuda. Nunca soube por quê. Ajudou todas as vezes e eu parei de perguntar.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.success/2
    en  Better. It'll be back next month, and saying it will help again then too.
    >>  ............................................
    pt  Melhor. Vai voltar mês que vem, e dizer vai ajudar de novo.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.success/1
    en  That helps! No idea why saying it aloud helps, but it does, and I'm not questioning it.
    >>  ............................................
    pt  Isso ajuda! Sem ideia de por que dizer em voz alta ajuda, mas ajuda, e eu não vou questionar.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.success/2
    en  Better already. Extraordinary. Words are ridiculous and they work.
    >>  ............................................
    pt  Já melhor. Extraordinário. Palavras são ridículas e funcionam.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.success/1
    en  That helps! No idea why saying it aloud helps, but it does, and I'm not questioning it.
    >>  ............................................
    pt  Isso ajuda! Sem ideia de por que dizer em voz alta ajuda, mas ajuda, e eu não vou questionar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.success/2
    en  Better already. Extraordinary. Words are ridiculous and they work.
    >>  ............................................
    pt  Já melhor. Extraordinário. Palavras são ridículas e funcionam.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.success/1
    en  That helps. I've never known why. It has helped every time and I've stopped asking.
    >>  ............................................
    pt  Isso ajuda. Nunca soube por quê. Ajudou todas as vezes e eu parei de perguntar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.success/2
    en  Better. It'll be back next month, and saying it will help again then too.
    >>  ............................................
    pt  Melhor. Vai voltar mês que vem, e dizer vai ajudar de novo.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I've no idea why saying it aloud helps, %1$s, and I'd been afraid it wouldn't.
    >>  ............................................
    pt  ...Isso ajuda. Não faço ideia de por que dizer em voz alta ajuda, %1$s, e eu temia que não ajudasse.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.success/2
    en  Better. I'd braced for feeling worse for having said it, and I don't.
    >>  ............................................
    pt  Melhor. Eu tinha me preparado pra me sentir pior por ter dito, e não me sinto.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.success/1
    en  ...That helps. I don't know why.
    >>  ............................................
    pt  ...Isso ajuda. Não sei por quê.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.success/2
    en  Better. Saying it aloud does something.
    >>  ............................................
    pt  Melhor. Dizer em voz alta faz algo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.success/1
    en  That helps! No idea why saying it aloud helps, but it does, and I'm not questioning it.
    >>  ............................................
    pt  Isso ajuda! Sem ideia de por que dizer em voz alta ajuda, mas ajuda, e eu não vou questionar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.success/2
    en  Better already. Extraordinary. Words are ridiculous and they work.
    >>  ............................................
    pt  Já melhor. Extraordinário. Palavras são ridículas e funcionam.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.success/1
    en  That helps! No idea why saying it aloud helps, but it does, and I'm not questioning it.
    >>  ............................................
    pt  Isso ajuda! Sem ideia de por que dizer em voz alta ajuda, mas ajuda, e eu não vou questionar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.success/2
    en  Better already. Extraordinary. Words are ridiculous and they work.
    >>  ............................................
    pt  Já melhor. Extraordinário. Palavras são ridículas e funcionam.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.comfort` lands on tier **partial** (axis warmth, difficulty 30, stance empathy, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.open.comfort.partial`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +1  _(recorded under topic `fears.open.comfort`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.comfort.partial
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.topic.fears.open.respond, button `comfort`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.comfort.partial.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.comfort.partial/1   [48 chars]
    en  Mm. It is. Not much to be done about it, though.
    >>  ............................................
    pt  Hm. É. Mas não tem muito o que fazer.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.partial/2   [96 chars]
    en  You're being kind. I never know what to do with kind, so I generally stand here and say nothing.
    >>  ............................................
    pt  Você está sendo gentil. Eu nunca sei o que fazer com gentileza, então costumo ficar aqui calado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though, and that's the part that wears.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer, e é essa a parte que desgasta.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Being told so helps for about an hour, and I'll take the hour.
    >>  ............................................
    pt  Duro, sim. Ouvir isso ajuda por uma hora, e eu fico com a hora.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it. Some things you carry rather than solve.
    >>  ............................................
    pt  É. Não tem muito o que fazer. Algumas coisas você carrega em vez de resolver.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, aye. It'll be hard next year too, and I'll still be here.
    >>  ............................................
    pt  Duro, é. Vai ser duro ano que vem também, e eu ainda vou estar aqui.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye, it's hard. That's true and it doesn't move anything.
    >>  ............................................
    pt  É, é duro. É verdade e não move nada.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye, it's hard. That's true and it doesn't move anything.
    >>  ............................................
    pt  É, é duro. É verdade e não move nada.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.partial/1
    en  It is, %1$s. Not much to be done about it, though.
    >>  ............................................
    pt  É, %1$s. Mas não tem muito o que fazer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. I'm glad you said so, even if saying so doesn't shift it.
    >>  ............................................
    pt  Duro, sim. Fico contente que você tenha dito, mesmo que dizer não mova.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.partial/1
    en  It is, %1$s. Not much to be done about it, though.
    >>  ............................................
    pt  É, %1$s. Mas não tem muito o que fazer.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. I'm glad you said so, even if saying so doesn't shift it.
    >>  ............................................
    pt  Duro, sim. Fico contente que você tenha dito, mesmo que dizer não mova.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.partial/1
    en  It is, %1$s. Not much to be done about it, though.
    >>  ............................................
    pt  É, %1$s. Mas não tem muito o que fazer.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. I'm glad you said so, even if saying so doesn't shift it.
    >>  ............................................
    pt  Duro, sim. Fico contente que você tenha dito, mesmo que dizer não mova.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though, and that's the part that wears.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer, e é essa a parte que desgasta.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Being told so helps for about an hour, and I'll take the hour.
    >>  ............................................
    pt  Duro, sim. Ouvir isso ajuda por uma hora, e eu fico com a hora.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye, it's hard. That's true and it doesn't move anything.
    >>  ............................................
    pt  É, é duro. É verdade e não move nada.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye, it's hard. That's true and it doesn't move anything.
    >>  ............................................
    pt  É, é duro. É verdade e não move nada.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye. Hard.
    >>  ............................................
    pt  É. Duro.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it. Some things you carry rather than solve.
    >>  ............................................
    pt  É. Não tem muito o que fazer. Algumas coisas você carrega em vez de resolver.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, aye. It'll be hard next year too, and I'll still be here.
    >>  ............................................
    pt  Duro, é. Vai ser duro ano que vem também, e eu ainda vou estar aqui.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye. Hard.
    >>  ............................................
    pt  É. Duro.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it. Some things you carry rather than solve.
    >>  ............................................
    pt  É. Não tem muito o que fazer. Algumas coisas você carrega em vez de resolver.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, aye. It'll be hard next year too, and I'll still be here.
    >>  ............................................
    pt  Duro, é. Vai ser duro ano que vem também, e eu ainda vou estar aqui.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is! Not much to be done about it, mind. Still, kind of you to notice.
    >>  ............................................
    pt  É! Mas não tem muito o que fazer. Mesmo assim, gentil da sua parte reparar.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Unfixably so. But thank you for the acknowledgement.
    >>  ............................................
    pt  Duro, sim. Irremediavelmente. Mas obrigado pelo reconhecimento.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.partial/1
    en  It is! Not much to be done about it, mind. Still, kind of you to notice.
    >>  ............................................
    pt  É! Mas não tem muito o que fazer. Mesmo assim, gentil da sua parte reparar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Unfixably so. But thank you for the acknowledgement.
    >>  ............................................
    pt  Duro, sim. Irremediavelmente. Mas obrigado pelo reconhecimento.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it. Some things you carry rather than solve.
    >>  ............................................
    pt  É. Não tem muito o que fazer. Algumas coisas você carrega em vez de resolver.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, aye. It'll be hard next year too, and I'll still be here.
    >>  ............................................
    pt  Duro, é. Vai ser duro ano que vem também, e eu ainda vou estar aqui.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done about it, though, and that's the part that wears.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer, e é essa a parte que desgasta.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Being told so helps for about an hour, and I'll take the hour.
    >>  ............................................
    pt  Duro, sim. Ouvir isso ajuda por uma hora, e eu fico com a hora.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.partial/1
    en  It is. Not much to be done, though.
    >>  ............................................
    pt  É. Mas não tem muito o que fazer.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.partial/2
    en  Aye. Hard.
    >>  ............................................
    pt  É. Duro.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.partial/1
    en  It is! Not much to be done about it, mind. Still, kind of you to notice.
    >>  ............................................
    pt  É! Mas não tem muito o que fazer. Mesmo assim, gentil da sua parte reparar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Unfixably so. But thank you for the acknowledgement.
    >>  ............................................
    pt  Duro, sim. Irremediavelmente. Mas obrigado pelo reconhecimento.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.partial/1
    en  It is! Not much to be done about it, mind. Still, kind of you to notice.
    >>  ............................................
    pt  É! Mas não tem muito o que fazer. Mesmo assim, gentil da sua parte reparar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.partial/2
    en  Hard, yes. Unfixably so. But thank you for the acknowledgement.
    >>  ............................................
    pt  Duro, sim. Irremediavelmente. Mas obrigado pelo reconhecimento.
    >>  ............................................
```

</details>


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.comfort` lands on tier **rebuff** (axis warmth, difficulty 30, stance empathy, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -1** — decision id `fears.open.comfort.rebuff`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +4  _(recorded under topic `fears.open.comfort`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.open.close.rebuffed`
- …where the player's next choices will be: "Fair. That came out wrong." | "Understood. I'll shut up." | "I'll let you be."

```text
POOL   dialogue key: dialogue.conversations.fears.open.comfort.rebuff
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.topic.fears.open.respond, button `comfort`
       leaves the player on: conversations.topic.fears.open.close.rebuffed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.comfort.rebuff.to.fears.open.close.rebuffed`: the villager refuses. Subject `fears.open.close.rebuffed`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, boundary_push, exit
```

```text
  dialogue.conversations.fears.open.comfort.rebuff/1   [62 chars]
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Não te entreguei isso para você fazer carinho na cabeça.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.rebuff/2   [53 chars]
    en  Soft words, %1$s? It isn't a soft thing. Leave it be.
    >>  ............................................
    pt  Palavras macias, %1$s? Não é uma coisa macia. Deixa quieto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't, %1$s. I didn't tell you so you'd make it smaller.
    >>  ............................................
    pt  Não faça isso, %1$s. Eu não contei pra você diminuir.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.rebuff/2
    en  That's kindly meant and it lands wrong. I'd rather you just sat there.
    >>  ............................................
    pt  É bem-intencionado e cai errado. Eu preferia que você só ficasse aí.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I didn't hand you that so you could pat it on the head.
    >>  ............................................
    pt  Não. Eu não te entreguei aquilo pra você fazer carinho na cabeça.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. I told you a fact, not a wound for you to dress.
    >>  ............................................
    pt  Não. Eu te disse um fato, não uma ferida pra você enfaixar.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. It doesn't want patting; it wants time.
    >>  ............................................
    pt  Não. Isso não quer carinho; quer tempo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Leave it where it is. It'll settle on its own.
    >>  ............................................
    pt  Não. Deixe onde está. Vai assentar sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. I know you mean well and I can't take it kindly just now.
    >>  ............................................
    pt  Não. Eu sei que você quer bem e eu não consigo receber bem agora.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Please don't soften it, %1$s. Softening it makes me feel foolish for having said it.
    >>  ............................................
    pt  Por favor não suavize, %1$s. Suavizar me faz sentir bobo por ter dito.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Don't. Please.
    >>  ............................................
    pt  Não. Por favor.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.rebuff/2
    en  No. Not that.
    >>  ............................................
    pt  Não. Isso não.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.rebuff/1
    en  Ah — no, not the soothing voice. Anything but that.
    >>  ............................................
    pt  Ah — não, não a voz de acalmar. Qualquer coisa menos isso.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.rebuff/2
    en  Don't. I'd rather you laughed than did whatever that was.
    >>  ............................................
    pt  Não. Eu preferia que você risse a fazer o que quer que fosse aquilo.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: **hearts +1** — decision id `fears.open.comfort`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `fears.open.comfort`)_
- Does: arc `fears` — advance to stage 1
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.comfort.plain
WHO    VILLAGER — what the player reads after pressing "That sounds hard to carry."
       spoken on: conversations.topic.fears.open.respond, button `comfort`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.comfort.plain.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.comfort.plain/1   [60 chars]
    en  That's... yes. It is hard to carry. Thank you for saying so.
    >>  ............................................
    pt  Isso... é. É pesado de carregar mesmo. Obrigado por dizer.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.plain/2   [47 chars]
    en  Most people go quiet when I say it. You didn't.
    >>  ............................................
    pt  A maioria fica quieta quando eu falo. Você não.
    >>  ............................................
  dialogue.conversations.fears.open.comfort.plain/3   [29 chars]
    en  Heavy's the word for it, aye.
    >>  ............................................
    pt  Pesado é a palavra certa, é.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so out loud.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer em voz alta.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd been calling it something smaller so that I could keep going.
    >>  ............................................
    pt  Certo. Duro. Eu vinha chamando de algo menor pra conseguir continuar.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Nobody names it. Naming it has undone something and I'm not sorry.
    >>  ............................................
    pt  Sim. Ninguém nomeia. Nomear desfez algo e eu não lamento.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.plain/1
    en  Yes. It is hard to carry. It'll be lighter some months and heavier others.
    >>  ............................................
    pt  Sim. É duro de carregar. Vai ser mais leve em alguns meses e mais pesado em outros.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. Saying so doesn't move it and it does make the carrying easier.
    >>  ............................................
    pt  Certo. Duro. Dizer não move e torna o carregar mais fácil.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard, aye. Thank you. That's a thing worth saying and not many bother.
    >>  ............................................
    pt  Duro, é. Obrigado. É algo que vale dizer e poucos se dão ao trabalho.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry. Thank you for saying so.
    >>  ............................................
    pt  É duro de carregar. Obrigado por dizer.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. It is. Nobody says the obvious thing and the obvious thing helps.
    >>  ............................................
    pt  Certo. É. Ninguém diz o óbvio e o óbvio ajuda.
    >>  ............................................
  confident.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Hard. That's the word for it.
    >>  ............................................
    pt  Sim. Duro. É a palavra pra isso.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry. Thank you for saying so.
    >>  ............................................
    pt  É duro de carregar. Obrigado por dizer.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. It is. Nobody says the obvious thing and the obvious thing helps.
    >>  ............................................
    pt  Certo. É. Ninguém diz o óbvio e o óbvio ajuda.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Hard. That's the word for it.
    >>  ............................................
    pt  Sim. Duro. É a palavra pra isso.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd not have said it first and I'm glad you did.
    >>  ............................................
    pt  Certo. Duro. Eu não teria dito primeiro e fico contente que você tenha dito.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. And you stayed to say it, which is most of why it helped.
    >>  ............................................
    pt  Sim. E você ficou pra dizer, que é quase toda a razão de ter ajudado.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd not have said it first and I'm glad you did.
    >>  ............................................
    pt  Certo. Duro. Eu não teria dito primeiro e fico contente que você tenha dito.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. And you stayed to say it, which is most of why it helped.
    >>  ............................................
    pt  Sim. E você ficou pra dizer, que é quase toda a razão de ter ajudado.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd not have said it first and I'm glad you did.
    >>  ............................................
    pt  Certo. Duro. Eu não teria dito primeiro e fico contente que você tenha dito.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. And you stayed to say it, which is most of why it helped.
    >>  ............................................
    pt  Sim. E você ficou pra dizer, que é quase toda a razão de ter ajudado.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so out loud.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer em voz alta.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd been calling it something smaller so that I could keep going.
    >>  ............................................
    pt  Certo. Duro. Eu vinha chamando de algo menor pra conseguir continuar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Nobody names it. Naming it has undone something and I'm not sorry.
    >>  ............................................
    pt  Sim. Ninguém nomeia. Nomear desfez algo e eu não lamento.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry. Thank you for saying so.
    >>  ............................................
    pt  É duro de carregar. Obrigado por dizer.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. It is. Nobody says the obvious thing and the obvious thing helps.
    >>  ............................................
    pt  Certo. É. Ninguém diz o óbvio e o óbvio ajuda.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Hard. That's the word for it.
    >>  ............................................
    pt  Sim. Duro. É a palavra pra isso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry. Thank you for saying so.
    >>  ............................................
    pt  É duro de carregar. Obrigado por dizer.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. It is. Nobody says the obvious thing and the obvious thing helps.
    >>  ............................................
    pt  Certo. É. Ninguém diz o óbvio e o óbvio ajuda.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Hard. That's the word for it.
    >>  ............................................
    pt  Sim. Duro. É a palavra pra isso.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.plain/1
    en  ...Yes. It is hard to carry.
    >>  ............................................
    pt  ...Sim. É duro de carregar.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Thank you for saying so.
    >>  ............................................
    pt  Certo. Obrigado por dizer.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard. Yes.
    >>  ............................................
    pt  Duro. Sim.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.plain/1
    en  Yes. It is hard to carry. It'll be lighter some months and heavier others.
    >>  ............................................
    pt  Sim. É duro de carregar. Vai ser mais leve em alguns meses e mais pesado em outros.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. Saying so doesn't move it and it does make the carrying easier.
    >>  ............................................
    pt  Certo. Duro. Dizer não move e torna o carregar mais fácil.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard, aye. Thank you. That's a thing worth saying and not many bother.
    >>  ............................................
    pt  Duro, é. Obrigado. É algo que vale dizer e poucos se dão ao trabalho.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.plain/1
    en  ...Yes. It is hard to carry.
    >>  ............................................
    pt  ...Sim. É duro de carregar.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Thank you for saying so.
    >>  ............................................
    pt  Certo. Obrigado por dizer.
    >>  ............................................
  odd.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard. Yes.
    >>  ............................................
    pt  Duro. Sim.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.plain/1
    en  Yes. It is hard to carry. It'll be lighter some months and heavier others.
    >>  ............................................
    pt  Sim. É duro de carregar. Vai ser mais leve em alguns meses e mais pesado em outros.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. Saying so doesn't move it and it does make the carrying easier.
    >>  ............................................
    pt  Certo. Duro. Dizer não move e torna o carregar mais fácil.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard, aye. Thank you. That's a thing worth saying and not many bother.
    >>  ............................................
    pt  Duro, é. Obrigado. É algo que vale dizer e poucos se dão ao trabalho.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry! Thank you for saying so — nobody does, they all get busy instead.
    >>  ............................................
    pt  É duro de carregar! Obrigado por dizer — ninguém diz, todos ficam ocupados.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. And you said it out loud rather than finding somewhere else to be.
    >>  ............................................
    pt  Certo. Duro. E você disse em voz alta em vez de achar outro lugar pra estar.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes! Hard. It's oddly cheering to have it named.
    >>  ............................................
    pt  Sim! Duro. É estranhamente animador ouvir isso ser nomeado.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry! Thank you for saying so — nobody does, they all get busy instead.
    >>  ............................................
    pt  É duro de carregar! Obrigado por dizer — ninguém diz, todos ficam ocupados.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. And you said it out loud rather than finding somewhere else to be.
    >>  ............................................
    pt  Certo. Duro. E você disse em voz alta em vez de achar outro lugar pra estar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes! Hard. It's oddly cheering to have it named.
    >>  ............................................
    pt  Sim! Duro. É estranhamente animador ouvir isso ser nomeado.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.plain/1
    en  Yes. It is hard to carry. It'll be lighter some months and heavier others.
    >>  ............................................
    pt  Sim. É duro de carregar. Vai ser mais leve em alguns meses e mais pesado em outros.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. Saying so doesn't move it and it does make the carrying easier.
    >>  ............................................
    pt  Certo. Duro. Dizer não move e torna o carregar mais fácil.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard, aye. Thank you. That's a thing worth saying and not many bother.
    >>  ............................................
    pt  Duro, é. Obrigado. É algo que vale dizer e poucos se dão ao trabalho.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.plain/1
    en  That's... yes. It is hard to carry, %1$s. Thank you for saying so out loud.
    >>  ............................................
    pt  Isso é... sim. É duro de carregar, %1$s. Obrigado por dizer em voz alta.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. I'd been calling it something smaller so that I could keep going.
    >>  ............................................
    pt  Certo. Duro. Eu vinha chamando de algo menor pra conseguir continuar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes. Nobody names it. Naming it has undone something and I'm not sorry.
    >>  ............................................
    pt  Sim. Ninguém nomeia. Nomear desfez algo e eu não lamento.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.plain/1
    en  ...Yes. It is hard to carry.
    >>  ............................................
    pt  ...Sim. É duro de carregar.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Thank you for saying so.
    >>  ............................................
    pt  Certo. Obrigado por dizer.
    >>  ............................................
  shy.dialogue.conversations.fears.open.comfort.plain/3
    en  Hard. Yes.
    >>  ............................................
    pt  Duro. Sim.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry! Thank you for saying so — nobody does, they all get busy instead.
    >>  ............................................
    pt  É duro de carregar! Obrigado por dizer — ninguém diz, todos ficam ocupados.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. And you said it out loud rather than finding somewhere else to be.
    >>  ............................................
    pt  Certo. Duro. E você disse em voz alta em vez de achar outro lugar pra estar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes! Hard. It's oddly cheering to have it named.
    >>  ............................................
    pt  Sim! Duro. É estranhamente animador ouvir isso ser nomeado.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.plain/1
    en  It is hard to carry! Thank you for saying so — nobody does, they all get busy instead.
    >>  ............................................
    pt  É duro de carregar! Obrigado por dizer — ninguém diz, todos ficam ocupados.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.plain/2
    en  Right. Hard. And you said it out loud rather than finding somewhere else to be.
    >>  ............................................
    pt  Certo. Duro. E você disse em voz alta em vez de achar outro lugar pra estar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.comfort.plain/3
    en  Yes! Hard. It's oddly cheering to have it named.
    >>  ............................................
    pt  Sim! Duro. É estranhamente animador ouvir isso ser nomeado.
    >>  ............................................
```

</details>


### Button `press` — "Tell me the rest of it."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `fears.first.to.fears.open`, `fears.revisit.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.press` — accepted phrasings: "tell me the rest"; "the rest of it"; "tell me everything"; "there is more"
  - the message must contain one of: `rest`, `more`, `everything`, `all`
  - scored words: `rest`(1.5), `more`(1.0), `everything`(1.2), `all`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.respond.press   [23 chars]
    en  Tell me the rest of it.
    >>  ............................................
    pt  Me conta o resto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.press` lands on tier **crit** (axis trust, difficulty 55, stance boundary_push, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +3** — decision id `fears.open.press.crit`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, familiarity +2  _(recorded under topic `fears.open.press`)_
- Does: arc `fears` — advance to stage 1
- Does: milestone `fears.revelation` set (fires once, ever)
- Then opens: `conversations.topic.fears.open.disclosed`
- …where the player's next choices will be: "I'll help you carry that." | "How long have you had that?" | "That's enough for one day."

```text
POOL   dialogue key: dialogue.conversations.fears.open.press.crit
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.fears.open.respond, button `press`
       leaves the player on: conversations.topic.fears.open.disclosed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.press.crit.to.fears.open.disclosed`: the villager resists. Subject `fears.open.disclosed`, polarity `positive`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.press.crit/1   [95 chars]
    en  ...You don't flinch, do you. Alright. The rest of it, the true shape — and it stays between us.
    >>  ............................................
    pt  ...Você não recua, né. Certo. O resto, o formato verdadeiro — e fica entre nós.
    >>  ............................................
  dialogue.conversations.fears.open.press.crit/2   [74 chars]
    en  Persistent. And gentle about it, which is worse. ...Fine. All of it, then.
    >>  ............................................
    pt  Persistente. E gentil, o que é pior. ...Tudo bem. Tudo, então.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you. Alright, %1$s. The rest of it. Let me get through it.
    >>  ............................................
    pt  ...Você não recua, né. Está bem, %1$s. O resto. Me deixe chegar ao fim.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.crit/2
    en  You didn't look away. That's the only reason I can say the next part.
    >>  ............................................
    pt  Você não desviou o olhar. É a única razão de eu conseguir dizer a próxima parte.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch. Alright. The rest of it, and I'll take my time over it.
    >>  ............................................
    pt  Você não recua. Está bem. O resto, e eu vou com calma.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then sit down properly, because this part is not short.
    >>  ............................................
    pt  Você ficou. Então sente-se direito, porque esta parte não é curta.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you. Alright. The rest of it, the true shape.
    >>  ............................................
    pt  Você não recua, né. Está bem. O resto, o formato verdadeiro.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then you get the whole of it, and it isn't tidy.
    >>  ............................................
    pt  Você ficou. Então você recebe tudo, e não é arrumado.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you. Alright. The rest of it, the true shape.
    >>  ............................................
    pt  Você não recua, né. Está bem. O resto, o formato verdadeiro.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then you get the whole of it, and it isn't tidy.
    >>  ............................................
    pt  Você ficou. Então você recebe tudo, e não é arrumado.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you, %1$s. Alright. The rest of it, then.
    >>  ............................................
    pt  ...Você não recua, né, %1$s. Está bem. Então o resto.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. That's what buys the rest, and nobody else has bought it.
    >>  ............................................
    pt  Você ficou. É isso que compra o resto, e mais ninguém comprou.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you, %1$s. Alright. The rest of it, then.
    >>  ............................................
    pt  ...Você não recua, né, %1$s. Está bem. Então o resto.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. That's what buys the rest, and nobody else has bought it.
    >>  ............................................
    pt  Você ficou. É isso que compra o resto, e mais ninguém comprou.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you, %1$s. Alright. The rest of it, then.
    >>  ............................................
    pt  ...Você não recua, né, %1$s. Está bem. Então o resto.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. That's what buys the rest, and nobody else has bought it.
    >>  ............................................
    pt  Você ficou. É isso que compra o resto, e mais ninguém comprou.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you. Alright, %1$s. The rest of it. Let me get through it.
    >>  ............................................
    pt  ...Você não recua, né. Está bem, %1$s. O resto. Me deixe chegar ao fim.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.crit/2
    en  You didn't look away. That's the only reason I can say the next part.
    >>  ............................................
    pt  Você não desviou o olhar. É a única razão de eu conseguir dizer a próxima parte.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you. Alright. The rest of it, the true shape.
    >>  ............................................
    pt  Você não recua, né. Está bem. O resto, o formato verdadeiro.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then you get the whole of it, and it isn't tidy.
    >>  ............................................
    pt  Você ficou. Então você recebe tudo, e não é arrumado.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you. Alright. The rest of it, the true shape.
    >>  ............................................
    pt  Você não recua, né. Está bem. O resto, o formato verdadeiro.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then you get the whole of it, and it isn't tidy.
    >>  ............................................
    pt  Você ficou. Então você recebe tudo, e não é arrumado.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch. Alright. The rest of it.
    >>  ............................................
    pt  ...Você não recua. Está bem. O resto.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. Then here's the true shape.
    >>  ............................................
    pt  Você ficou. Então aqui vai o formato verdadeiro.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch. Alright. The rest of it, and I'll take my time over it.
    >>  ............................................
    pt  Você não recua. Está bem. O resto, e eu vou com calma.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then sit down properly, because this part is not short.
    >>  ............................................
    pt  Você ficou. Então sente-se direito, porque esta parte não é curta.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch. Alright. The rest of it.
    >>  ............................................
    pt  ...Você não recua. Está bem. O resto.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. Then here's the true shape.
    >>  ............................................
    pt  Você ficou. Então aqui vai o formato verdadeiro.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch. Alright. The rest of it, and I'll take my time over it.
    >>  ............................................
    pt  Você não recua. Está bem. O resto, e eu vou com calma.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then sit down properly, because this part is not short.
    >>  ............................................
    pt  Você ficou. Então sente-se direito, porque esta parte não é curta.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you! Right. The rest of it, then. The true shape.
    >>  ............................................
    pt  Você não recua, né! Certo. Então o resto. O formato verdadeiro.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.crit/2
    en  Not a flicker. Fine. You've earned the unabridged version.
    >>  ............................................
    pt  Nem um piscar. Tudo bem. Você mereceu a versão sem cortes.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you! Right. The rest of it, then. The true shape.
    >>  ............................................
    pt  Você não recua, né! Certo. Então o resto. O formato verdadeiro.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.crit/2
    en  Not a flicker. Fine. You've earned the unabridged version.
    >>  ............................................
    pt  Nem um piscar. Tudo bem. Você mereceu a versão sem cortes.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch. Alright. The rest of it, and I'll take my time over it.
    >>  ............................................
    pt  Você não recua. Está bem. O resto, e eu vou com calma.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.crit/2
    en  You stayed put. Then sit down properly, because this part is not short.
    >>  ............................................
    pt  Você ficou. Então sente-se direito, porque esta parte não é curta.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch, do you. Alright, %1$s. The rest of it. Let me get through it.
    >>  ............................................
    pt  ...Você não recua, né. Está bem, %1$s. O resto. Me deixe chegar ao fim.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.crit/2
    en  You didn't look away. That's the only reason I can say the next part.
    >>  ............................................
    pt  Você não desviou o olhar. É a única razão de eu conseguir dizer a próxima parte.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.crit/1
    en  ...You don't flinch. Alright. The rest of it.
    >>  ............................................
    pt  ...Você não recua. Está bem. O resto.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.crit/2
    en  You stayed. Then here's the true shape.
    >>  ............................................
    pt  Você ficou. Então aqui vai o formato verdadeiro.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you! Right. The rest of it, then. The true shape.
    >>  ............................................
    pt  Você não recua, né! Certo. Então o resto. O formato verdadeiro.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.crit/2
    en  Not a flicker. Fine. You've earned the unabridged version.
    >>  ............................................
    pt  Nem um piscar. Tudo bem. Você mereceu a versão sem cortes.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.crit/1
    en  You don't flinch, do you! Right. The rest of it, then. The true shape.
    >>  ............................................
    pt  Você não recua, né! Certo. Então o resto. O formato verdadeiro.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.crit/2
    en  Not a flicker. Fine. You've earned the unabridged version.
    >>  ............................................
    pt  Nem um piscar. Tudo bem. Você mereceu a versão sem cortes.
    >>  ............................................
```

</details>


**Outcome 2 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.press` lands on tier **success** (axis trust, difficulty 55, stance boundary_push, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts +2** — decision id `fears.open.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, familiarity +1  _(recorded under topic `fears.open.press`)_
- Does: arc `fears` — advance to stage 1
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.press.success
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.fears.open.respond, button `press`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.press.success.to.fears.open`: the villager resists. Subject `fears.open`, polarity `positive`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.press.success/1   [73 chars]
    en  There's more, yes. Give me a breath. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dá um segundo. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  dialogue.conversations.fears.open.press.success/2   [63 chars]
    en  You pull gently, I'll give you that. A little more of it, then.
    >>  ............................................
    pt  Você puxa com jeito, isso eu admito. Um pouco mais, então.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.success/2
    en  More. I've never got this far with anybody and I'm shaking a little, which is fine.
    >>  ............................................
    pt  Mais. Nunca cheguei tão longe com ninguém e eu estou tremendo um pouco, e tudo bem.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There. Two of us now, and no hurry about the rest.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto. Agora somos dois, e sem pressa pro resto.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.success/2
    en  More. Slowly, then. It'll come out in its own order and that order won't be tidy.
    >>  ............................................
    pt  Mais. Devagar, então. Vai sair na ordem dele e essa ordem não vai ser arrumada.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. Agora somos dois carregando.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Say nothing for a moment and I'll get through it.
    >>  ............................................
    pt  Mais. Certo. Não diga nada por um momento e eu chego ao fim.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. Agora somos dois carregando.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Say nothing for a moment and I'll get through it.
    >>  ............................................
    pt  Mais. Certo. Não diga nada por um momento e eu chego ao fim.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.success/2
    en  More. Sit closer, then. I'd rather not say this across a room.
    >>  ............................................
    pt  Mais. Então chegue mais perto. Prefiro não dizer isso atravessando a sala.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.success/2
    en  More. Sit closer, then. I'd rather not say this across a room.
    >>  ............................................
    pt  Mais. Então chegue mais perto. Prefiro não dizer isso atravessando a sala.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.success/2
    en  More. Sit closer, then. I'd rather not say this across a room.
    >>  ............................................
    pt  Mais. Então chegue mais perto. Prefiro não dizer isso atravessando a sala.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.success/2
    en  More. I've never got this far with anybody and I'm shaking a little, which is fine.
    >>  ............................................
    pt  Mais. Nunca cheguei tão longe com ninguém e eu estou tremendo um pouco, e tudo bem.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. Agora somos dois carregando.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Say nothing for a moment and I'll get through it.
    >>  ............................................
    pt  Mais. Certo. Não diga nada por um momento e eu chego ao fim.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. Agora somos dois carregando.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Say nothing for a moment and I'll get through it.
    >>  ............................................
    pt  Mais. Certo. Não diga nada por um momento e eu chego ao fim.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.success/2
    en  More. Two of us now.
    >>  ............................................
    pt  Mais. Agora somos dois.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There. Two of us now, and no hurry about the rest.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto. Agora somos dois, e sem pressa pro resto.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.success/2
    en  More. Slowly, then. It'll come out in its own order and that order won't be tidy.
    >>  ............................................
    pt  Mais. Devagar, então. Vai sair na ordem dele e essa ordem não vai ser arrumada.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.success/2
    en  More. Two of us now.
    >>  ............................................
    pt  Mais. Agora somos dois.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There. Two of us now, and no hurry about the rest.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto. Agora somos dois, e sem pressa pro resto.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.success/2
    en  More. Slowly, then. It'll come out in its own order and that order won't be tidy.
    >>  ............................................
    pt  Mais. Devagar, então. Vai sair na ordem dele e essa ordem não vai ser arrumada.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes! Give me a breath. ...There. Two of us carrying it now, which is better maths.
    >>  ............................................
    pt  Tem mais, sim! Me dê um fôlego. ...Pronto. Agora somos dois carregando, que é uma conta melhor.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Hold on. ...Done. That was easier than I'd budgeted for.
    >>  ............................................
    pt  Mais. Certo. Espere. ...Pronto. Foi mais fácil do que eu tinha orçado.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes! Give me a breath. ...There. Two of us carrying it now, which is better maths.
    >>  ............................................
    pt  Tem mais, sim! Me dê um fôlego. ...Pronto. Agora somos dois carregando, que é uma conta melhor.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Hold on. ...Done. That was easier than I'd budgeted for.
    >>  ............................................
    pt  Mais. Certo. Espere. ...Pronto. Foi mais fácil do que eu tinha orçado.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There. Two of us now, and no hurry about the rest.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto. Agora somos dois, e sem pressa pro resto.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.success/2
    en  More. Slowly, then. It'll come out in its own order and that order won't be tidy.
    >>  ............................................
    pt  Mais. Devagar, então. Vai sair na ordem dele e essa ordem não vai ser arrumada.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath, %1$s. ...There. Two of us carrying it now.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego, %1$s. ...Pronto. Agora somos dois carregando.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.success/2
    en  More. I've never got this far with anybody and I'm shaking a little, which is fine.
    >>  ............................................
    pt  Mais. Nunca cheguei tão longe com ninguém e eu estou tremendo um pouco, e tudo bem.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes. Give me a breath. ...There.
    >>  ............................................
    pt  Tem mais, sim. Me dê um fôlego. ...Pronto.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.success/2
    en  More. Two of us now.
    >>  ............................................
    pt  Mais. Agora somos dois.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes! Give me a breath. ...There. Two of us carrying it now, which is better maths.
    >>  ............................................
    pt  Tem mais, sim! Me dê um fôlego. ...Pronto. Agora somos dois carregando, que é uma conta melhor.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Hold on. ...Done. That was easier than I'd budgeted for.
    >>  ............................................
    pt  Mais. Certo. Espere. ...Pronto. Foi mais fácil do que eu tinha orçado.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.success/1
    en  There's more, yes! Give me a breath. ...There. Two of us carrying it now, which is better maths.
    >>  ............................................
    pt  Tem mais, sim! Me dê um fôlego. ...Pronto. Agora somos dois carregando, que é uma conta melhor.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.success/2
    en  More. Right. Hold on. ...Done. That was easier than I'd budgeted for.
    >>  ............................................
    pt  Mais. Certo. Espere. ...Pronto. Foi mais fácil do que eu tinha orçado.
    >>  ............................................
```

</details>


**Outcome 3 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.press` lands on tier **partial** (axis trust, difficulty 55, stance boundary_push, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: disposition — tension +2  _(recorded under topic `fears.open.press`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.pressed`
- …where the player's next choices will be: "You're right. Forget I asked." | "I want the rest. Now." | "Alright. I'll drop it."

```text
POOL   dialogue key: dialogue.conversations.fears.open.press.partial
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.fears.open.respond, button `press`
       leaves the player on: conversations.topic.fears.pressed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.press.partial.to.fears.pressed`: the villager resists. Subject `fears.pressed`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.press.partial/1   [78 chars]
    en  Some of it, maybe. Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Um pouco, talvez. Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  dialogue.conversations.fears.open.press.partial/2   [74 chars]
    en  That thread's still attached to something. Later. I mean later, not never.
    >>  ............................................
    pt  Esse fio ainda está preso em algo. Depois. Digo depois, não nunca.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.partial/2
    en  A little. If I go further now I'll not sleep, and I've a day tomorrow.
    >>  ............................................
    pt  Um pouco. Se eu for além agora eu não durmo, e eu tenho um dia amanhã.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight. It'll still be true next week.
    >>  ............................................
    pt  Parte, talvez. Hoje não. Vai continuar verdade semana que vem.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest keeps. It has kept a long while already.
    >>  ............................................
    pt  Um pouco. O resto espera. Já esperou muito tempo.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not the rest, and not now.
    >>  ............................................
    pt  Um pouco. Não o resto, e não agora.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not the rest, and not now.
    >>  ............................................
    pt  Um pouco. Não o resto, e não agora.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.partial/2
    en  A little. Ask me again in daylight and you might get further.
    >>  ............................................
    pt  Um pouco. Me pergunte de dia e você pode ir mais longe.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.partial/2
    en  A little. Ask me again in daylight and you might get further.
    >>  ............................................
    pt  Um pouco. Me pergunte de dia e você pode ir mais longe.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.partial/2
    en  A little. Ask me again in daylight and you might get further.
    >>  ............................................
    pt  Um pouco. Me pergunte de dia e você pode ir mais longe.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.partial/2
    en  A little. If I go further now I'll not sleep, and I've a day tomorrow.
    >>  ............................................
    pt  Um pouco. Se eu for além agora eu não durmo, e eu tenho um dia amanhã.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not the rest, and not now.
    >>  ............................................
    pt  Um pouco. Não o resto, e não agora.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not the rest, and not now.
    >>  ............................................
    pt  Um pouco. Não o resto, e não agora.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not now.
    >>  ............................................
    pt  Um pouco. Agora não.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight. It'll still be true next week.
    >>  ............................................
    pt  Parte, talvez. Hoje não. Vai continuar verdade semana que vem.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest keeps. It has kept a long while already.
    >>  ............................................
    pt  Um pouco. O resto espera. Já esperou muito tempo.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not now.
    >>  ............................................
    pt  Um pouco. Agora não.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight. It'll still be true next week.
    >>  ............................................
    pt  Parte, talvez. Hoje não. Vai continuar verdade semana que vem.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest keeps. It has kept a long while already.
    >>  ............................................
    pt  Um pouco. O resto espera. Já esperou muito tempo.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe! Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez! Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest is a daylight conversation and it isn't daylight.
    >>  ............................................
    pt  Um pouco. O resto é conversa de dia e não é dia.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe! Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez! Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest is a daylight conversation and it isn't daylight.
    >>  ............................................
    pt  Um pouco. O resto é conversa de dia e não é dia.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight. It'll still be true next week.
    >>  ............................................
    pt  Parte, talvez. Hoje não. Vai continuar verdade semana que vem.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest keeps. It has kept a long while already.
    >>  ............................................
    pt  Um pouco. O resto espera. Já esperou muito tempo.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight, %1$s. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez. Hoje não, %1$s. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.partial/2
    en  A little. If I go further now I'll not sleep, and I've a day tomorrow.
    >>  ............................................
    pt  Um pouco. Se eu for além agora eu não durmo, e eu tenho um dia amanhã.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe. Not tonight.
    >>  ............................................
    pt  Parte, talvez. Hoje não.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.partial/2
    en  A little. Not now.
    >>  ............................................
    pt  Um pouco. Agora não.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe! Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez! Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest is a daylight conversation and it isn't daylight.
    >>  ............................................
    pt  Um pouco. O resto é conversa de dia e não é dia.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.partial/1
    en  Some of it, maybe! Not tonight. Tonight's too much like the night it happened.
    >>  ............................................
    pt  Parte, talvez! Hoje não. Hoje está parecido demais com a noite em que aconteceu.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.partial/2
    en  A little. The rest is a daylight conversation and it isn't daylight.
    >>  ............................................
    pt  Um pouco. O resto é conversa de dia e não é dia.
    >>  ............................................
```

</details>


**Outcome 4 of 5** — base weight `0`

- Fires when: weighted +1000 when the dialogue check `fears.open.press` lands on tier **rebuff** (axis trust, difficulty 55, stance boundary_push, arc fears)
- Fires when: RULED OUT when the `checks` feature is OFF  _(chance -2000)_
- Does: **hearts -2** — decision id `fears.open.press.rebuff`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +6, trust -2  _(recorded under topic `fears.open.press`)_
- Does: arc `fears` — hold
- Does: session `turn`
- Then opens: `conversations.topic.fears.pressed`
- …where the player's next choices will be: "You're right. Forget I asked." | "I want the rest. Now." | "Alright. I'll drop it."

```text
POOL   dialogue key: dialogue.conversations.fears.open.press.rebuff
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.fears.open.respond, button `press`
       leaves the player on: conversations.topic.fears.pressed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.press.rebuff.to.fears.pressed`: the villager refuses. Subject `fears.pressed`, polarity `negative`, closes subject, outcome `rebuffed`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, boundary_push, exit
```

```text
  dialogue.conversations.fears.open.press.rebuff/1   [47 chars]
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixa.
    >>  ............................................
  dialogue.conversations.fears.open.press.rebuff/2   [55 chars]
    en  No. Some doors you don't knock on twice in a day, %1$s.
    >>  ............................................
    pt  Não. Tem portas em que não se bate duas vezes no mesmo dia, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.rebuff/1
    en  I've told you more than I've told anyone, %1$s. Let that be enough tonight.
    >>  ............................................
    pt  Eu te contei mais do que contei a qualquer um, %1$s. Que baste por hoje.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.rebuff/2
    en  That's as far as I go, even with you. Especially with you, maybe.
    >>  ............................................
    pt  É até onde eu vou, mesmo com você. Especialmente com você, talvez.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said. The rest is mine. Leave it.
    >>  ............................................
    pt  Eu disse o que disse. O resto é meu. Deixe.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.rebuff/2
    en  No further. That's not stubbornness, it's the edge of it.
    >>  ............................................
    pt  Não passe daí. Não é teimosia, é o limite.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.rebuff/1
    en  That's what there is today. Ask me another year, perhaps.
    >>  ............................................
    pt  É o que tem hoje. Me pergunte em outro ano, talvez.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.rebuff/2
    en  The rest is mine for now. It's not locked; it's just not open.
    >>  ............................................
    pt  O resto é meu por enquanto. Não está trancado; só não está aberto.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.rebuff/1
    en  Please. I've given you the part I could give, %1$s.
    >>  ............................................
    pt  Por favor. Eu te dei a parte que eu conseguia dar, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.rebuff/2
    en  Don't ask again. I'll say yes and then I'll regret it all night.
    >>  ............................................
    pt  Não pergunte de novo. Eu vou dizer sim e depois vou me arrepender a noite toda.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.rebuff/1
    en  I said what I said.
    >>  ............................................
    pt  Eu disse o que disse.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.rebuff/2
    en  No. The rest stays with me.
    >>  ............................................
    pt  Não. O resto fica comigo.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.rebuff/1
    en  That's the whole of it! Truly. There's no better bit behind the curtain.
    >>  ............................................
    pt  É tudo! Sério. Não tem parte melhor atrás da cortina.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.rebuff/2
    en  Right — that's my lot. You'll have to be satisfied with it.
    >>  ............................................
    pt  Certo — é o que eu tenho. Vai ter que se contentar.
    >>  ............................................
```

</details>


**Outcome 5 of 5** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `checks` feature is ON  _(chance -2000)_
- Does: disposition — tension +1  _(recorded under topic `fears.open.press`)_
- Does: arc `fears` — hold
- Then opens: `conversations.topic.fears.pressed`
- …where the player's next choices will be: "You're right. Forget I asked." | "I want the rest. Now." | "Alright. I'll drop it."

```text
POOL   dialogue key: dialogue.conversations.fears.open.press.plain
WHO    VILLAGER — what the player reads after pressing "Tell me the rest of it."
       spoken on: conversations.topic.fears.open.respond, button `press`
       leaves the player on: conversations.topic.fears.pressed
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.press.plain.to.fears.pressed`: the villager resists. Subject `fears.pressed`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.press.plain/1   [59 chars]
    en  There's more to it. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais coisa. Se você vai ouvir é outra história.
    >>  ............................................
  dialogue.conversations.fears.open.press.plain/2   [54 chars]
    en  Bold, asking for the rest. Give me a moment with that.
    >>  ............................................
    pt  Corajoso, pedir o resto. Me dá um instante.
    >>  ............................................
  dialogue.conversations.fears.open.press.plain/3   [49 chars]
    en  Mm. Not sure I've decided how much of it you get.
    >>  ............................................
    pt  Hm. Não sei se decidi quanto disso você recebe.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question, %1$s, and not an unkind one.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão, %1$s, e não é uma questão cruel.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I've said the part I can say without shaking.
    >>  ............................................
    pt  Mais, sim. Eu disse a parte que consigo dizer sem tremer.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I'd want to be sure before I let it out of my own head.
    >>  ............................................
    pt  Tem um resto. Eu ia querer ter certeza antes de deixar sair da minha cabeça.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is a question for another year, perhaps.
    >>  ............................................
    pt  Tem mais. Se você recebe é questão pra outro ano, talvez.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It'll keep. Everything about this has kept so far.
    >>  ............................................
    pt  Mais, sim. Espera. Tudo nisso esperou até agora.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. No hurry on it, for either of us.
    >>  ............................................
    pt  Tem um resto. Sem pressa, pra nenhum de nós.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not automatically yours, though.
    >>  ............................................
    pt  Mais, sim. Mas não é automaticamente seu.
    >>  ............................................
  confident.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided about it.
    >>  ............................................
    pt  Tem um resto. Eu não decidi sobre ele.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not automatically yours, though.
    >>  ............................................
    pt  Mais, sim. Mas não é automaticamente seu.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided about it.
    >>  ............................................
    pt  Tem um resto. Eu não decidi sobre ele.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it, %1$s. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais, %1$s. Se você recebe é outra questão.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I'd like to tell you and I'm not there yet.
    >>  ............................................
    pt  Mais, sim. Eu gostaria de te contar e ainda não cheguei lá.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. Ask me another week and it might be different.
    >>  ............................................
    pt  Tem um resto. Me pergunte em outra semana e pode ser diferente.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it, %1$s. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais, %1$s. Se você recebe é outra questão.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I'd like to tell you and I'm not there yet.
    >>  ............................................
    pt  Mais, sim. Eu gostaria de te contar e ainda não cheguei lá.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. Ask me another week and it might be different.
    >>  ............................................
    pt  Tem um resto. Me pergunte em outra semana e pode ser diferente.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it, %1$s. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais, %1$s. Se você recebe é outra questão.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I'd like to tell you and I'm not there yet.
    >>  ............................................
    pt  Mais, sim. Eu gostaria de te contar e ainda não cheguei lá.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. Ask me another week and it might be different.
    >>  ............................................
    pt  Tem um resto. Me pergunte em outra semana e pode ser diferente.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question, %1$s, and not an unkind one.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão, %1$s, e não é uma questão cruel.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I've said the part I can say without shaking.
    >>  ............................................
    pt  Mais, sim. Eu disse a parte que consigo dizer sem tremer.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I'd want to be sure before I let it out of my own head.
    >>  ............................................
    pt  Tem um resto. Eu ia querer ter certeza antes de deixar sair da minha cabeça.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not automatically yours, though.
    >>  ............................................
    pt  Mais, sim. Mas não é automaticamente seu.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided about it.
    >>  ............................................
    pt  Tem um resto. Eu não decidi sobre ele.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not automatically yours, though.
    >>  ............................................
    pt  Mais, sim. Mas não é automaticamente seu.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided about it.
    >>  ............................................
    pt  Tem um resto. Eu não decidi sobre ele.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it.
    >>  ............................................
    pt  Tem mais.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not now.
    >>  ............................................
    pt  Mais, sim. Agora não.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided.
    >>  ............................................
    pt  Tem um resto. Não decidi.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is a question for another year, perhaps.
    >>  ............................................
    pt  Tem mais. Se você recebe é questão pra outro ano, talvez.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It'll keep. Everything about this has kept so far.
    >>  ............................................
    pt  Mais, sim. Espera. Tudo nisso esperou até agora.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. No hurry on it, for either of us.
    >>  ............................................
    pt  Tem um resto. Sem pressa, pra nenhum de nós.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it.
    >>  ............................................
    pt  Tem mais.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not now.
    >>  ............................................
    pt  Mais, sim. Agora não.
    >>  ............................................
  odd.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided.
    >>  ............................................
    pt  Tem um resto. Não decidi.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is a question for another year, perhaps.
    >>  ............................................
    pt  Tem mais. Se você recebe é questão pra outro ano, talvez.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It'll keep. Everything about this has kept so far.
    >>  ............................................
    pt  Mais, sim. Espera. Tudo nisso esperou até agora.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. No hurry on it, for either of us.
    >>  ............................................
    pt  Tem um resto. Sem pressa, pra nenhum de nós.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it! Whether you get it is an entirely separate question.
    >>  ............................................
    pt  Tem mais! Se você recebe é uma questão completamente separada.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It's behind a door and I'm holding the handle.
    >>  ............................................
    pt  Mais, sim. Está atrás de uma porta e eu estou segurando a maçaneta.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided who's having it.
    >>  ............................................
    pt  Tem um resto. Não decidi quem vai receber.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it! Whether you get it is an entirely separate question.
    >>  ............................................
    pt  Tem mais! Se você recebe é uma questão completamente separada.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It's behind a door and I'm holding the handle.
    >>  ............................................
    pt  Mais, sim. Está atrás de uma porta e eu estou segurando a maçaneta.
    >>  ............................................
  playful.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided who's having it.
    >>  ............................................
    pt  Tem um resto. Não decidi quem vai receber.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is a question for another year, perhaps.
    >>  ............................................
    pt  Tem mais. Se você recebe é questão pra outro ano, talvez.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It'll keep. Everything about this has kept so far.
    >>  ............................................
    pt  Mais, sim. Espera. Tudo nisso esperou até agora.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. No hurry on it, for either of us.
    >>  ............................................
    pt  Tem um resto. Sem pressa, pra nenhum de nós.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it. Whether you get it is another question, %1$s, and not an unkind one.
    >>  ............................................
    pt  Tem mais. Se você recebe é outra questão, %1$s, e não é uma questão cruel.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. I've said the part I can say without shaking.
    >>  ............................................
    pt  Mais, sim. Eu disse a parte que consigo dizer sem tremer.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I'd want to be sure before I let it out of my own head.
    >>  ............................................
    pt  Tem um resto. Eu ia querer ter certeza antes de deixar sair da minha cabeça.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it.
    >>  ............................................
    pt  Tem mais.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. Not now.
    >>  ............................................
    pt  Mais, sim. Agora não.
    >>  ............................................
  shy.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided.
    >>  ............................................
    pt  Tem um resto. Não decidi.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it! Whether you get it is an entirely separate question.
    >>  ............................................
    pt  Tem mais! Se você recebe é uma questão completamente separada.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It's behind a door and I'm holding the handle.
    >>  ............................................
    pt  Mais, sim. Está atrás de uma porta e eu estou segurando a maçaneta.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided who's having it.
    >>  ............................................
    pt  Tem um resto. Não decidi quem vai receber.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.plain/1
    en  There's more to it! Whether you get it is an entirely separate question.
    >>  ............................................
    pt  Tem mais! Se você recebe é uma questão completamente separada.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.plain/2
    en  More, yes. It's behind a door and I'm holding the handle.
    >>  ............................................
    pt  Mais, sim. Está atrás de uma porta e eu estou segurando a maçaneta.
    >>  ............................................
  witty.dialogue.conversations.fears.open.press.plain/3
    en  There's a rest. I've not decided who's having it.
    >>  ............................................
    pt  Tem um resto. Não decidi quem vai receber.
    >>  ............................................
```

</details>


### Button `share` — "I'm afraid of that too."

*stance family `self_disclosure` · tone `gentle` · answers the beat(s) `fears.first.to.fears.open`, `fears.revisit.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.share` — accepted phrasings: "me too"; "i am scared of that too"; "same here"; "i fear that myself"; "i am afraid of that as well"
  - the message must contain one of: `too`, `also`, `same`, `myself`
  - scored words: `too`(1.2), `also`(1.0), `same`(1.0), `myself`(1.2), `scared`(0.8), `afraid`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond.share
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.respond.share   [23 chars]
    en  I'm afraid of that too.
    >>  ............................................
    pt  Eu também tenho medo disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.open.share`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, familiarity +2  _(recorded under topic `fears.open.share`)_
- Does: arc `fears` — advance to stage 1
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.open.share
WHO    VILLAGER — what the player reads after pressing "I'm afraid of that too."
       spoken on: conversations.topic.fears.open.respond, button `share`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.share.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.open.share/1   [55 chars]
    en  You too? Huh. It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Hm. Fica menos pesado quando duas pessoas carregam.
    >>  ............................................
  dialogue.conversations.fears.open.share/2   [55 chars]
    en  Strange comfort, that. Two cowards make one brave pair.
    >>  ............................................
    pt  Conforto estranho, esse. Dois covardes fazem uma dupla corajosa.
    >>  ............................................
  dialogue.conversations.fears.open.share/3   [64 chars]
    en  Then we watch each other's backs. That's how it's meant to work.
    >>  ............................................
    pt  Então a gente se protege. É assim que deveria funcionar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s. I'd not known that.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s. Eu não sabia disso.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.share/2
    en  You as well. I'd been sure I was the only one and being wrong about that is a relief.
    >>  ............................................
    pt  Você também. Eu tinha certeza de que era o único e errar nisso é um alívio.
    >>  ............................................
  anxious.dialogue.conversations.fears.open.share/3
    en  Two of us. Give me a moment. That's changed the shape of it.
    >>  ............................................
    pt  Dois de nós. Me dê um momento. Isso mudou o formato da coisa.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it. That's held true my whole life.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam. Isso foi verdade a vida toda.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.share/2
    en  You as well. Then we'll carry it between us and neither of us has to hurry.
    >>  ............................................
    pt  Você também. Então a gente carrega junto e nenhum precisa ter pressa.
    >>  ............................................
  athletic.dialogue.conversations.fears.open.share/3
    en  Two of us. Good. These things go easier in pairs.
    >>  ............................................
    pt  Dois de nós. Bom. Essas coisas vão melhor em pares.
    >>  ............................................
  confident.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  confident.dialogue.conversations.fears.open.share/2
    en  You as well. Right. That's the first useful thing anyone's said about it.
    >>  ............................................
    pt  Você também. Certo. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  confident.dialogue.conversations.fears.open.share/3
    en  Two of us, then. That halves something.
    >>  ............................................
    pt  Dois de nós, então. Isso divide algo.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.share/2
    en  You as well. Right. That's the first useful thing anyone's said about it.
    >>  ............................................
    pt  Você também. Certo. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  crabby.dialogue.conversations.fears.open.share/3
    en  Two of us, then. That halves something.
    >>  ............................................
    pt  Dois de nós, então. Isso divide algo.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.share/2
    en  You as well. Then tell me yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Então me conte o seu — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  extroverted.dialogue.conversations.fears.open.share/3
    en  Two of us. I'd not have guessed, and I'm glad you said.
    >>  ............................................
    pt  Dois de nós. Eu não teria adivinhado, e fico contente que você tenha dito.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.share/2
    en  You as well. Then tell me yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Então me conte o seu — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  flirty.dialogue.conversations.fears.open.share/3
    en  Two of us. I'd not have guessed, and I'm glad you said.
    >>  ............................................
    pt  Dois de nós. Eu não teria adivinhado, e fico contente que você tenha dito.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.share/2
    en  You as well. Then tell me yours — I'd rather this went both ways.
    >>  ............................................
    pt  Você também. Então me conte o seu — prefiro que isso vá nos dois sentidos.
    >>  ............................................
  friendly.dialogue.conversations.fears.open.share/3
    en  Two of us. I'd not have guessed, and I'm glad you said.
    >>  ............................................
    pt  Dois de nós. Eu não teria adivinhado, e fico contente que você tenha dito.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s. I'd not known that.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s. Eu não sabia disso.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.share/2
    en  You as well. I'd been sure I was the only one and being wrong about that is a relief.
    >>  ............................................
    pt  Você também. Eu tinha certeza de que era o único e errar nisso é um alívio.
    >>  ............................................
  gloomy.dialogue.conversations.fears.open.share/3
    en  Two of us. Give me a moment. That's changed the shape of it.
    >>  ............................................
    pt  Dois de nós. Me dê um momento. Isso mudou o formato da coisa.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.share/2
    en  You as well. Right. That's the first useful thing anyone's said about it.
    >>  ............................................
    pt  Você também. Certo. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  greedy.dialogue.conversations.fears.open.share/3
    en  Two of us, then. That halves something.
    >>  ............................................
    pt  Dois de nós, então. Isso divide algo.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.share/2
    en  You as well. Right. That's the first useful thing anyone's said about it.
    >>  ............................................
    pt  Você também. Certo. É a primeira coisa útil que alguém disse sobre isso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.open.share/3
    en  Two of us, then. That halves something.
    >>  ............................................
    pt  Dois de nós, então. Isso divide algo.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.share/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  introverted.dialogue.conversations.fears.open.share/3
    en  Two of us, then.
    >>  ............................................
    pt  Dois de nós, então.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it. That's held true my whole life.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam. Isso foi verdade a vida toda.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.share/2
    en  You as well. Then we'll carry it between us and neither of us has to hurry.
    >>  ............................................
    pt  Você também. Então a gente carrega junto e nenhum precisa ter pressa.
    >>  ............................................
  lazy.dialogue.conversations.fears.open.share/3
    en  Two of us. Good. These things go easier in pairs.
    >>  ............................................
    pt  Dois de nós. Bom. Essas coisas vão melhor em pares.
    >>  ............................................
  odd.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  odd.dialogue.conversations.fears.open.share/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  odd.dialogue.conversations.fears.open.share/3
    en  Two of us, then.
    >>  ............................................
    pt  Dois de nós, então.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it. That's held true my whole life.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam. Isso foi verdade a vida toda.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.share/2
    en  You as well. Then we'll carry it between us and neither of us has to hurry.
    >>  ............................................
    pt  Você também. Então a gente carrega junto e nenhum precisa ter pressa.
    >>  ............................................
  peaceful.dialogue.conversations.fears.open.share/3
    en  Two of us. Good. These things go easier in pairs.
    >>  ............................................
    pt  Dois de nós. Bom. Essas coisas vão melhor em pares.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.share/1
    en  You too? Huh! It's much less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Huh! Fica bem menos pesado quando dois carregam.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.share/2
    en  You as well! Right. Misery adores company and I'm delighted to have some.
    >>  ............................................
    pt  Você também! Certo. A tristeza adora companhia e eu estou encantado de ter.
    >>  ............................................
  peppy.dialogue.conversations.fears.open.share/3
    en  Two of us. Excellent. Now it's a club rather than an affliction.
    >>  ............................................
    pt  Dois de nós. Excelente. Agora é um clube em vez de uma aflição.
    >>  ............................................
  playful.dialogue.conversations.fears.open.share/1
    en  You too? Huh! It's much less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Huh! Fica bem menos pesado quando dois carregam.
    >>  ............................................
  playful.dialogue.conversations.fears.open.share/2
    en  You as well! Right. Misery adores company and I'm delighted to have some.
    >>  ............................................
    pt  Você também! Certo. A tristeza adora companhia e eu estou encantado de ter.
    >>  ............................................
  playful.dialogue.conversations.fears.open.share/3
    en  Two of us. Excellent. Now it's a club rather than an affliction.
    >>  ............................................
    pt  Dois de nós. Excelente. Agora é um clube em vez de uma aflição.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two people carry it. That's held true my whole life.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam. Isso foi verdade a vida toda.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.share/2
    en  You as well. Then we'll carry it between us and neither of us has to hurry.
    >>  ............................................
    pt  Você também. Então a gente carrega junto e nenhum precisa ter pressa.
    >>  ............................................
  relaxed.dialogue.conversations.fears.open.share/3
    en  Two of us. Good. These things go easier in pairs.
    >>  ............................................
    pt  Dois de nós. Bom. Essas coisas vão melhor em pares.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.share/1
    en  You too? Huh. It's less heavy when two people carry it, %1$s. I'd not known that.
    >>  ............................................
    pt  Você também? Huh. Fica menos pesado quando dois carregam, %1$s. Eu não sabia disso.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.share/2
    en  You as well. I'd been sure I was the only one and being wrong about that is a relief.
    >>  ............................................
    pt  Você também. Eu tinha certeza de que era o único e errar nisso é um alívio.
    >>  ............................................
  sensitive.dialogue.conversations.fears.open.share/3
    en  Two of us. Give me a moment. That's changed the shape of it.
    >>  ............................................
    pt  Dois de nós. Me dê um momento. Isso mudou o formato da coisa.
    >>  ............................................
  shy.dialogue.conversations.fears.open.share/1
    en  You too? It's less heavy when two carry it.
    >>  ............................................
    pt  Você também? Fica menos pesado quando dois carregam.
    >>  ............................................
  shy.dialogue.conversations.fears.open.share/2
    en  You as well. Right.
    >>  ............................................
    pt  Você também. Certo.
    >>  ............................................
  shy.dialogue.conversations.fears.open.share/3
    en  Two of us, then.
    >>  ............................................
    pt  Dois de nós, então.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.share/1
    en  You too? Huh! It's much less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Huh! Fica bem menos pesado quando dois carregam.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.share/2
    en  You as well! Right. Misery adores company and I'm delighted to have some.
    >>  ............................................
    pt  Você também! Certo. A tristeza adora companhia e eu estou encantado de ter.
    >>  ............................................
  upbeat.dialogue.conversations.fears.open.share/3
    en  Two of us. Excellent. Now it's a club rather than an affliction.
    >>  ............................................
    pt  Dois de nós. Excelente. Agora é um clube em vez de uma aflição.
    >>  ............................................
  witty.dialogue.conversations.fears.open.share/1
    en  You too? Huh! It's much less heavy when two people carry it.
    >>  ............................................
    pt  Você também? Huh! Fica bem menos pesado quando dois carregam.
    >>  ............................................
  witty.dialogue.conversations.fears.open.share/2
    en  You as well! Right. Misery adores company and I'm delighted to have some.
    >>  ............................................
    pt  Você também! Certo. A tristeza adora companhia e eu estou encantado de ter.
    >>  ............................................
  witty.dialogue.conversations.fears.open.share/3
    en  Two of us. Excellent. Now it's a club rather than an affliction.
    >>  ............................................
    pt  Dois de nós. Excelente. Agora é um clube em vez de uma aflição.
    >>  ............................................
```

</details>


### Button `no_words` — "I don't know what to say to that."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.first.to.fears.open`, `fears.revisit.to.fears.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.open.no_words` — accepted phrasings: "i am lost for words"; "i do not know how to answer that"; "i cannot find the words"
  - the message must contain one of: `lost`, `words`
  - scored words: `lost`(1.0), `words`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond.no_words
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.respond.no_words   [33 chars]
    en  I don't know what to say to that.
    >>  ............................................
    pt  Não sei o que dizer sobre isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.no_words`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `fears.no_words`)_
- Then opens: `conversations.topic.fears.open.followup`
- …where the player's next choices will be: "You won't face it alone." | "I can't promise that. But I'm listening." | "You could face it, you know." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.fears.no_words
WHO    VILLAGER — what the player reads after pressing "I don't know what to say to that."
       spoken on: conversations.topic.fears.open.respond, button `no_words`
       leaves the player on: conversations.topic.fears.open.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.no_words.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.no_words/1   [102 chars]
    en  ...Nor did I, for years. That's rather the whole trouble with it. We'll sit here not knowing together.
    >>  ............................................
    pt  ...Nem eu, por anos. É esse o problema todo. Vamos ficar aqui sem saber juntos.
    >>  ............................................
  dialogue.conversations.fears.no_words/2   [80 chars]
    en  You don't need words, %1$s. You've stayed in the room, which is the harder half.
    >>  ............................................
    pt  Você não precisa de palavras, %1$s. Você ficou na sala, que é a metade difícil.
    >>  ............................................
  dialogue.conversations.fears.no_words/3   [79 chars]
    en  Neither do I. Alright. That's oddly the most comforting thing anyone's managed.
    >>  ............................................
    pt  Nem eu. Tudo bem. Estranhamente é a coisa mais reconfortante que alguém conseguiu.
    >>  ............................................
```


### Button `leave` — "Thank you for telling me. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.first.to.fears.open`, `fears.revisit.to.fears.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.open.respond.leave   [34 chars]
    en  Thank you for telling me. I'll go.
    >>  ............................................
    pt  Obrigado por me contar. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.open.leave
WHO    VILLAGER — what the player reads after pressing "Thank you for telling me. I'll go."
       spoken on: conversations.topic.fears.open.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.open.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.fears.open.followup / leave
```

> Written out in full under **`conversations.topic.fears.open.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.fears.pressed`

**Reached from 4 route(s):** `conversations.topic.fears.guarded.respond` / `press`; `conversations.topic.fears.open.respond` / `press`; `conversations.topic.fears.open.respond` / `press`; `conversations.topic.fears.open.respond` / `press`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.guarded.press` — e.g. "I said no. Do you want to hear it a second time?"
- `conversations.fears.open.press.partial` — e.g. "Some of it, maybe. Not tonight. Tonight's too much like the night it happened."
- `conversations.fears.open.press.plain` — e.g. "There's more to it. Whether you get it is another question."
- `conversations.fears.open.press.rebuff` — e.g. "I said what I said. The rest is mine. Leave it."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.pressed
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.pressed
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.pressed   [32 chars]
    en  I've said what I'm going to say.
    >>  ............................................
    pt  Já disse o que ia dizer.
    >>  ............................................
```


### Button `back_off` — "You're right. Forget I asked."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.guarded.press.to.fears.pressed`, `fears.open.press.partial.to.fears.pressed`, `fears.open.press.plain.to.fears.pressed`, `fears.open.press.rebuff.to.fears.pressed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.pressed.back_off` — accepted phrasings: "forget i asked"; "you are right"; "sorry i asked"; "i will drop it"
  - the message must contain one of: `forget`, `right`, `sorry`, `drop`
  - scored words: `forget`(1.5), `right`(1.0), `sorry`(1.0), `asked`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.pressed.back_off
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.pressed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.pressed.back_off   [29 chars]
    en  You're right. Forget I asked.
    >>  ............................................
    pt  Você tem razão. Esquece que eu perguntei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -4, respect +3  _(recorded under topic `fears.pressed.back_off`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.pressed.back_off
WHO    VILLAGER — what the player reads after pressing "You're right. Forget I asked."
       spoken on: conversations.topic.fears.pressed, button `back_off`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.pressed.back_off.terminal`: the villager accepts. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.pressed.back_off/1   [60 chars]
    en  ...Thank you. Not many people can stop when they're told to.
    >>  ............................................
    pt  ...Obrigado. Pouca gente consegue parar quando pedem.
    >>  ............................................
  dialogue.conversations.fears.pressed.back_off/2   [57 chars]
    en  That's the right answer, %1$s. I'll remember you gave it.
    >>  ............................................
    pt  Essa é a resposta certa, %1$s. Vou lembrar que você a deu.
    >>  ............................................
  dialogue.conversations.fears.pressed.back_off/3   [52 chars]
    en  Good. The rest will come when it comes, or it won't.
    >>  ............................................
    pt  Bom. O resto vem quando vier, ou não vem.
    >>  ............................................
```


### Button `push` — "I want the rest. Now."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `fears.guarded.press.to.fears.pressed`, `fears.open.press.partial.to.fears.pressed`, `fears.open.press.plain.to.fears.pressed`, `fears.open.press.rebuff.to.fears.pressed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.pressed.push` — accepted phrasings: "i want the rest now"; "tell me now"; "i insist"; "no, tell me"
  - the message must contain one of: `now`, `demand`, `insist`, `want`
  - scored words: `now`(1.5), `want`(1.2), `demand`(1.5), `insist`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.pressed.push
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.pressed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.pressed.push   [21 chars]
    en  I want the rest. Now.
    >>  ............................................
    pt  Quero o resto. Agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -5** — decision id `fears.pressed.push`, budget `deep`, replay policy `once`
- Does: disposition — tension +10, trust -6, warmth -4  _(recorded under topic `fears.pressed.push`)_
- Does: milestone `fears.scar` set (fires once, ever)
- Does: arc `fears` — regress to stage 0
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.pressed.push
WHO    VILLAGER — what the player reads after pressing "I want the rest. Now."
       spoken on: conversations.topic.fears.pressed, button `push`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.pressed.push.terminal`: the villager accepts. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.pressed.push/1   [61 chars]
    en  No. NO. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Sai de perto de mim.
    >>  ............................................
  dialogue.conversations.fears.pressed.push/2   [70 chars]
    en  That's what you are, then. Someone who takes. I won't forget it, %1$s.
    >>  ............................................
    pt  Então é isso que você é. Alguém que toma. Não vou esquecer, %1$s.
    >>  ............................................
  dialogue.conversations.fears.pressed.push/3   [76 chars]
    en  You cracked it open because you wanted to see. I hope it was worth the look.
    >>  ............................................
    pt  Você arrombou porque queria ver. Espero que a vista tenha valido a pena.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me, %1$s. Please.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  anxious.dialogue.conversations.fears.pressed.push/2
    en  I said no. I had to say it once and I should not have had to say it twice.
    >>  ............................................
    pt  Eu disse não. Tive que dizer uma vez e não devia ter tido que dizer duas.
    >>  ............................................
  anxious.dialogue.conversations.fears.pressed.push/3
    en  Twice. I'd trusted you with the first half and now I'm frightened of the rest.
    >>  ............................................
    pt  Duas vezes. Eu te confiei a primeira metade e agora eu tenho medo do resto.
    >>  ............................................
  athletic.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  athletic.dialogue.conversations.fears.pressed.push/2
    en  I said no. I'll say it once more and then I'll simply stop talking to you.
    >>  ............................................
    pt  Eu disse não. Vou dizer mais uma vez e depois eu simplesmente paro de falar com você.
    >>  ............................................
  athletic.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go and think about it. I'll be here in a month if you have.
    >>  ............................................
    pt  Duas vezes. Vá pensar. Eu vou estar aqui em um mês se você pensar.
    >>  ............................................
  confident.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  confident.dialogue.conversations.fears.pressed.push/2
    en  I said no. You heard it. We're finished.
    >>  ............................................
    pt  Eu disse não. Você ouviu. Acabamos.
    >>  ............................................
  confident.dialogue.conversations.fears.pressed.push/3
    en  That's twice. There isn't a third.
    >>  ............................................
    pt  Foi duas vezes. Não vai ter uma terceira.
    >>  ............................................
  crabby.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  crabby.dialogue.conversations.fears.pressed.push/2
    en  I said no. You heard it. We're finished.
    >>  ............................................
    pt  Eu disse não. Você ouviu. Acabamos.
    >>  ............................................
  crabby.dialogue.conversations.fears.pressed.push/3
    en  That's twice. There isn't a third.
    >>  ............................................
    pt  Foi duas vezes. Não vai ter uma terceira.
    >>  ............................................
  extroverted.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me, %1$s.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.pressed.push/2
    en  I said no. From anybody else I'd have expected it. Not from you.
    >>  ............................................
    pt  Eu disse não. De qualquer outro eu esperaria. De você não.
    >>  ............................................
  extroverted.dialogue.conversations.fears.pressed.push/3
    en  That's twice. Go, before I say the thing I'd not be able to take back.
    >>  ............................................
    pt  Foi duas vezes. Vá, antes que eu diga o que eu não conseguiria retirar.
    >>  ............................................
  flirty.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me, %1$s.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.fears.pressed.push/2
    en  I said no. From anybody else I'd have expected it. Not from you.
    >>  ............................................
    pt  Eu disse não. De qualquer outro eu esperaria. De você não.
    >>  ............................................
  flirty.dialogue.conversations.fears.pressed.push/3
    en  That's twice. Go, before I say the thing I'd not be able to take back.
    >>  ............................................
    pt  Foi duas vezes. Vá, antes que eu diga o que eu não conseguiria retirar.
    >>  ............................................
  friendly.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me, %1$s.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.fears.pressed.push/2
    en  I said no. From anybody else I'd have expected it. Not from you.
    >>  ............................................
    pt  Eu disse não. De qualquer outro eu esperaria. De você não.
    >>  ............................................
  friendly.dialogue.conversations.fears.pressed.push/3
    en  That's twice. Go, before I say the thing I'd not be able to take back.
    >>  ............................................
    pt  Foi duas vezes. Vá, antes que eu diga o que eu não conseguiria retirar.
    >>  ............................................
  gloomy.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me, %1$s. Please.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  gloomy.dialogue.conversations.fears.pressed.push/2
    en  I said no. I had to say it once and I should not have had to say it twice.
    >>  ............................................
    pt  Eu disse não. Tive que dizer uma vez e não devia ter tido que dizer duas.
    >>  ............................................
  gloomy.dialogue.conversations.fears.pressed.push/3
    en  Twice. I'd trusted you with the first half and now I'm frightened of the rest.
    >>  ............................................
    pt  Duas vezes. Eu te confiei a primeira metade e agora eu tenho medo do resto.
    >>  ............................................
  greedy.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  greedy.dialogue.conversations.fears.pressed.push/2
    en  I said no. You heard it. We're finished.
    >>  ............................................
    pt  Eu disse não. Você ouviu. Acabamos.
    >>  ............................................
  greedy.dialogue.conversations.fears.pressed.push/3
    en  That's twice. There isn't a third.
    >>  ............................................
    pt  Foi duas vezes. Não vai ter uma terceira.
    >>  ............................................
  grumpy.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  grumpy.dialogue.conversations.fears.pressed.push/2
    en  I said no. You heard it. We're finished.
    >>  ............................................
    pt  Eu disse não. Você ouviu. Acabamos.
    >>  ............................................
  grumpy.dialogue.conversations.fears.pressed.push/3
    en  That's twice. There isn't a third.
    >>  ............................................
    pt  Foi duas vezes. Não vai ter uma terceira.
    >>  ............................................
  introverted.dialogue.conversations.fears.pressed.push/1
    en  No. You were told. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado. Saia de perto de mim.
    >>  ............................................
  introverted.dialogue.conversations.fears.pressed.push/2
    en  I said no.
    >>  ............................................
    pt  Eu disse não.
    >>  ............................................
  introverted.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go.
    >>  ............................................
    pt  Duas vezes. Vá.
    >>  ............................................
  lazy.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  lazy.dialogue.conversations.fears.pressed.push/2
    en  I said no. I'll say it once more and then I'll simply stop talking to you.
    >>  ............................................
    pt  Eu disse não. Vou dizer mais uma vez e depois eu simplesmente paro de falar com você.
    >>  ............................................
  lazy.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go and think about it. I'll be here in a month if you have.
    >>  ............................................
    pt  Duas vezes. Vá pensar. Eu vou estar aqui em um mês se você pensar.
    >>  ............................................
  odd.dialogue.conversations.fears.pressed.push/1
    en  No. You were told. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado. Saia de perto de mim.
    >>  ............................................
  odd.dialogue.conversations.fears.pressed.push/2
    en  I said no.
    >>  ............................................
    pt  Eu disse não.
    >>  ............................................
  odd.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go.
    >>  ............................................
    pt  Duas vezes. Vá.
    >>  ............................................
  peaceful.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  peaceful.dialogue.conversations.fears.pressed.push/2
    en  I said no. I'll say it once more and then I'll simply stop talking to you.
    >>  ............................................
    pt  Eu disse não. Vou dizer mais uma vez e depois eu simplesmente paro de falar com você.
    >>  ............................................
  peaceful.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go and think about it. I'll be here in a month if you have.
    >>  ............................................
    pt  Duas vezes. Vá pensar. Eu vou estar aqui em um mês se você pensar.
    >>  ............................................
  peppy.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  peppy.dialogue.conversations.fears.pressed.push/2
    en  I said no and you heard me say it. That's the end of the light-hearted portion.
    >>  ............................................
    pt  Eu disse não e você me ouviu dizer. Acabou a parte leve.
    >>  ............................................
  peppy.dialogue.conversations.fears.pressed.push/3
    en  Twice. There is no third time, %1$s.
    >>  ............................................
    pt  Duas vezes. Não vai ter uma terceira, %1$s.
    >>  ............................................
  playful.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  playful.dialogue.conversations.fears.pressed.push/2
    en  I said no and you heard me say it. That's the end of the light-hearted portion.
    >>  ............................................
    pt  Eu disse não e você me ouviu dizer. Acabou a parte leve.
    >>  ............................................
  playful.dialogue.conversations.fears.pressed.push/3
    en  Twice. There is no third time, %1$s.
    >>  ............................................
    pt  Duas vezes. Não vai ter uma terceira, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.fears.pressed.push/1
    en  No. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  relaxed.dialogue.conversations.fears.pressed.push/2
    en  I said no. I'll say it once more and then I'll simply stop talking to you.
    >>  ............................................
    pt  Eu disse não. Vou dizer mais uma vez e depois eu simplesmente paro de falar com você.
    >>  ............................................
  relaxed.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go and think about it. I'll be here in a month if you have.
    >>  ............................................
    pt  Duas vezes. Vá pensar. Eu vou estar aqui em um mês se você pensar.
    >>  ............................................
  sensitive.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me, %1$s. Please.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim, %1$s. Por favor.
    >>  ............................................
  sensitive.dialogue.conversations.fears.pressed.push/2
    en  I said no. I had to say it once and I should not have had to say it twice.
    >>  ............................................
    pt  Eu disse não. Tive que dizer uma vez e não devia ter tido que dizer duas.
    >>  ............................................
  sensitive.dialogue.conversations.fears.pressed.push/3
    en  Twice. I'd trusted you with the first half and now I'm frightened of the rest.
    >>  ............................................
    pt  Duas vezes. Eu te confiei a primeira metade e agora eu tenho medo do resto.
    >>  ............................................
  shy.dialogue.conversations.fears.pressed.push/1
    en  No. You were told. Get away from me.
    >>  ............................................
    pt  Não. Você foi avisado. Saia de perto de mim.
    >>  ............................................
  shy.dialogue.conversations.fears.pressed.push/2
    en  I said no.
    >>  ............................................
    pt  Eu disse não.
    >>  ............................................
  shy.dialogue.conversations.fears.pressed.push/3
    en  Twice. Go.
    >>  ............................................
    pt  Duas vezes. Vá.
    >>  ............................................
  upbeat.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  upbeat.dialogue.conversations.fears.pressed.push/2
    en  I said no and you heard me say it. That's the end of the light-hearted portion.
    >>  ............................................
    pt  Eu disse não e você me ouviu dizer. Acabou a parte leve.
    >>  ............................................
  upbeat.dialogue.conversations.fears.pressed.push/3
    en  Twice. There is no third time, %1$s.
    >>  ............................................
    pt  Duas vezes. Não vai ter uma terceira, %1$s.
    >>  ............................................
  witty.dialogue.conversations.fears.pressed.push/1
    en  No. NO. You were told, and you asked again. Get away from me.
    >>  ............................................
    pt  Não. NÃO. Você foi avisado, e perguntou de novo. Saia de perto de mim.
    >>  ............................................
  witty.dialogue.conversations.fears.pressed.push/2
    en  I said no and you heard me say it. That's the end of the light-hearted portion.
    >>  ............................................
    pt  Eu disse não e você me ouviu dizer. Acabou a parte leve.
    >>  ............................................
  witty.dialogue.conversations.fears.pressed.push/3
    en  Twice. There is no third time, %1$s.
    >>  ............................................
    pt  Duas vezes. Não vai ter uma terceira, %1$s.
    >>  ............................................
```

</details>


### Button `leave` — "Alright. I'll drop it."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.guarded.press.to.fears.pressed`, `fears.open.press.partial.to.fears.pressed`, `fears.open.press.plain.to.fears.pressed`, `fears.open.press.rebuff.to.fears.pressed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.pressed.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.pressed
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.pressed.leave   [22 chars]
    en  Alright. I'll drop it.
    >>  ............................................
    pt  Tudo bem. Deixo quieto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.pressed.leave
WHO    VILLAGER — what the player reads after pressing "Alright. I'll drop it."
       spoken on: conversations.topic.fears.pressed, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.pressed.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.pressed.leave/1   [17 chars]
    en  Aye. Best you do.
    >>  ............................................
    pt  Tá. Melhor mesmo.
    >>  ............................................
  dialogue.conversations.fears.pressed.leave/2   [43 chars]
    en  Go on, then. Before either of us says more.
    >>  ............................................
    pt  Pode ir, então. Antes que um de nós fale mais.
    >>  ............................................
  dialogue.conversations.fears.pressed.leave/3   [28 chars]
    en  Right. We'll leave it there.
    >>  ............................................
    pt  Certo. Vamos parar por aqui.
    >>  ............................................
```

---


## `conversations.topic.fears.repaired`

**Reached from 1 route(s):** `conversations.topic.fears.scarred.respond` / `apologize`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.scarred.apologize` — e.g. "...Took you long enough. It doesn't undo it. But it's something."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.repaired
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.repaired
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.repaired   [38 chars]
    en  Alright. We'll try this again, slower.
    >>  ............................................
    pt  Certo. Vamos tentar de novo, mais devagar.
    >>  ............................................
```


### Button `ask_safer` — "Ask me something I can answer easily."

*stance family `curiosity` · tone `gentle` · answers the beat(s) `fears.scarred.apologize.to.fears.repaired`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.repaired.ask_safer` — accepted phrasings: "ask me something i can answer easily"; "something easier then"; "let us keep to easy things"
  - the message must contain one of: `easier`, `easily`, `simple`
  - scored words: `easier`(1.5), `easily`(1.2), `simple`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.repaired.ask_safer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.repaired
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.repaired.ask_safer   [37 chars]
    en  Ask me something I can answer easily.
    >>  ............................................
    pt  Me pergunte algo que eu consiga responder fácil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.repaired.ask_safer`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, tension -3  _(recorded under topic `fears.repaired.ask_safer`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.repaired.ask_safer
WHO    VILLAGER — what the player reads after pressing "Ask me something I can answer easily."
       spoken on: conversations.topic.fears.repaired, button `ask_safer`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.repaired.ask_safer.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.repaired.ask_safer/1   [90 chars]
    en  Now that I can do. Ask me about the fence, the harvest, anything with a floor to stand on.
    >>  ............................................
    pt  Isso eu consigo. Me pergunte da cerca, da colheita, qualquer coisa com chão firme embaixo.
    >>  ............................................
  dialogue.conversations.fears.repaired.ask_safer/2   [70 chars]
    en  Something easy. Aye. We'll build back up to the rest, in our own time.
    >>  ............................................
    pt  Algo fácil. Isso. A gente volta ao resto no nosso tempo.
    >>  ............................................
  dialogue.conversations.fears.repaired.ask_safer/3   [73 chars]
    en  Sensible, %1$s. Shallow water first. I'll tell you when I can swim again.
    >>  ............................................
    pt  Sensato, %1$s. Água rasa primeiro. Eu aviso quando puder nadar de novo.
    >>  ............................................
```


### Button `respect` — "You set the pace. I'll keep to it."

*stance family `restraint` · tone `plain` · answers the beat(s) `fears.scarred.apologize.to.fears.repaired`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.repaired.respect` — accepted phrasings: "you set the pace"; "we go at your pace"; "i will keep to your pace"
  - the message must contain one of: `pace`, `slower`
  - scored words: `pace`(1.6), `slower`(1.1), `lead`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.repaired.respect
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.repaired
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.repaired.respect   [34 chars]
    en  You set the pace. I'll keep to it.
    >>  ............................................
    pt  Você dá o ritmo. Eu acompanho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.repaired.respect`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — respect +5, tension -4  _(recorded under topic `fears.repaired.respect`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.repaired.respect
WHO    VILLAGER — what the player reads after pressing "You set the pace. I'll keep to it."
       spoken on: conversations.topic.fears.repaired, button `respect`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.repaired.respect.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.repaired.respect/1   [70 chars]
    en  ...Then we'll get there. Slowly, and without anyone's hand on my back.
    >>  ............................................
    pt  ...Então a gente chega lá. Devagar, e sem a mão de ninguém nas minhas costas.
    >>  ............................................
  dialogue.conversations.fears.repaired.respect/2   [60 chars]
    en  You'll keep to it. I believe you, which is the strange part.
    >>  ............................................
    pt  Você vai acompanhar. Eu acredito, o que é a parte estranha.
    >>  ............................................
  dialogue.conversations.fears.repaired.respect/3   [54 chars]
    en  That's all I ever wanted asked of me, %1$s. Just that.
    >>  ............................................
    pt  É só isso que eu sempre quis que me pedissem, %1$s. Só isso.
    >>  ............................................
```


### Button `leave` — "That's enough for today."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.scarred.apologize.to.fears.repaired` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.repaired.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.repaired
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.repaired.leave   [24 chars]
    en  That's enough for today.
    >>  ............................................
    pt  Já chega por hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.repaired.leave
WHO    VILLAGER — what the player reads after pressing "That's enough for today."
       spoken on: conversations.topic.fears.repaired, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.repaired.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.repaired.leave/1   [76 chars]
    en  So I've found. Enough digging for one day. Come back and I'll still be here.
    >>  ............................................
    pt  Foi o que eu vi. Já cavamos bastante por hoje. Volte e eu ainda estarei aqui.
    >>  ............................................
  dialogue.conversations.fears.repaired.leave/2   [56 chars]
    en  Right. Off you go, and thank you for the slower version.
    >>  ............................................
    pt  Certo. Pode ir, e obrigado pela versão mais devagar.
    >>  ............................................
  dialogue.conversations.fears.repaired.leave/3   [38 chars]
    en  Go on, %1$s. We're alright, you and I.
    >>  ............................................
    pt  Vá lá, %1$s. Estamos bem, você e eu.
    >>  ............................................
```

---


## `conversations.topic.fears.scarred.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.scarred` — e.g. "...You. Right. Ask me about the weather, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.scarred.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.scarred.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.scarred.respond   [28 chars]
    en  You know why it's like this.
    >>  ............................................
    pt  Você sabe por que está assim.
    >>  ............................................
```


### Button `apologize` — "I pushed you, and I was wrong to."

*stance family `candor` · tone `gentle` · answers the beat(s) `fears.scarred.to.fears.scarred`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.scarred.apologize` — accepted phrasings: "i pushed you and i was wrong"; "i should not have pushed"; "i am sorry for that"; "that was wrong of me"
  - the message must contain one of: `pushed`, `wrong`, `sorry`, `shouldnt`
  - scored words: `pushed`(1.5), `wrong`(1.5), `sorry`(1.2), `shouldnt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.scarred.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.scarred.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.scarred.respond.apologize   [33 chars]
    en  I pushed you, and I was wrong to.
    >>  ............................................
    pt  Eu te pressionei, e fiz errado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -6, respect +2  _(recorded under topic `fears.scarred.apologize`)_
- Then opens: `conversations.topic.fears.repaired`
- …where the player's next choices will be: "Ask me something I can answer easily." | "You set the pace. I'll keep to it." | "That's enough for today."

```text
POOL   dialogue key: dialogue.conversations.fears.scarred.apologize
WHO    VILLAGER — what the player reads after pressing "I pushed you, and I was wrong to."
       spoken on: conversations.topic.fears.scarred.respond, button `apologize`
       leaves the player on: conversations.topic.fears.repaired
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scarred.apologize.to.fears.repaired`: the villager accepts. Subject `fears.repaired`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.scarred.apologize/1   [64 chars]
    en  ...Took you long enough. It doesn't undo it. But it's something.
    >>  ............................................
    pt  ...Demorou. Não desfaz nada. Mas é alguma coisa.
    >>  ............................................
  dialogue.conversations.fears.scarred.apologize/2   [72 chars]
    en  You were wrong, aye. Saying so out loud costs you something, so — noted.
    >>  ............................................
    pt  Você errou, é. Dizer isso em voz alta te custa algo, então — anotado.
    >>  ............................................
  dialogue.conversations.fears.scarred.apologize/3   [66 chars]
    en  I'll not pretend it never happened, %1$s. But you can stand there.
    >>  ............................................
    pt  Não vou fingir que não aconteceu, %1$s. Mas você pode ficar aí.
    >>  ............................................
```


### Button `give_space` — "I'll not ask again."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.scarred.to.fears.scarred`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.scarred.give_space` — accepted phrasings: "i will not ask again"; "i will give you space"; "never again"; "i will leave it alone"
  - the message must contain one of: `again`, `space`, `never`
  - scored words: `again`(1.5), `space`(1.5), `never`(1.2), `ask`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.scarred.respond.give_space
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.scarred.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.scarred.respond.give_space   [19 chars]
    en  I'll not ask again.
    >>  ............................................
    pt  Não vou perguntar de novo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `fears.scarred.give_space`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.scarred.give_space
WHO    VILLAGER — what the player reads after pressing "I'll not ask again."
       spoken on: conversations.topic.fears.scarred.respond, button `give_space`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scarred.give_space.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.scarred.give_space/1   [44 chars]
    en  Good. That's the only thing left that helps.
    >>  ............................................
    pt  Bom. É a única coisa que ainda ajuda.
    >>  ............................................
  dialogue.conversations.fears.scarred.give_space/2   [53 chars]
    en  Just so. Space. I'd rather that than another apology.
    >>  ............................................
    pt  Pois é. Espaço. Prefiro isso a mais um pedido de desculpa.
    >>  ............................................
  dialogue.conversations.fears.scarred.give_space/3   [82 chars]
    en  Right. Come back in a week and ask me about the fence, and we'll see where we are.
    >>  ............................................
    pt  Certo. Volte daqui a uma semana e me pergunte da cerca, e a gente vê como estamos.
    >>  ............................................
```


### Button `leave` — "I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.scarred.to.fears.scarred` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.scarred.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.scarred.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.scarred.respond.leave   [8 chars]
    en  I'll go.
    >>  ............................................
    pt  Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.scarred.leave
WHO    VILLAGER — what the player reads after pressing "I'll go."
       spoken on: conversations.topic.fears.scarred.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scarred.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.scarred.leave/1   [3 chars]
    en  Mm.
    >>  ............................................
    pt  Hm.
    >>  ............................................
  dialogue.conversations.fears.scarred.leave/2   [8 chars]
    en  Aye. Go.
    >>  ............................................
    pt  Tá. Vai.
    >>  ............................................
  dialogue.conversations.fears.scarred.leave/3   [21 chars]
    en  That's probably best.
    >>  ............................................
    pt  É melhor mesmo.
    >>  ............................................
```

---


## `conversations.topic.fears.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.toddler` — e.g. "The dark under the bed. There's a WHOLE nothing under there."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.toddler.respond   [23 chars]
    en  That's the scary thing.
    >>  ............................................
    pt  Essa é a coisa assustadora.
    >>  ............................................
```


### Button `reassure` — "That does sound scary. You're safe here."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.toddler.to.fears.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.toddler.reassure` — accepted phrasings: "you are safe here"; "that does sound scary"; "it is okay"; "nothing will get you"
  - the message must contain one of: `safe`, `okay`, `scary`, `protect`
  - scored words: `safe`(1.5), `scary`(1.0), `okay`(1.0), `here`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.toddler.respond.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.toddler.respond.reassure   [40 chars]
    en  That does sound scary. You're safe here.
    >>  ............................................
    pt  Isso é assustador mesmo. Você está seguro aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.toddler.reassure`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `fears.toddler.reassure`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.toddler.reassure
WHO    VILLAGER — what the player reads after pressing "That does sound scary. You're safe here."
       spoken on: conversations.topic.fears.toddler.respond, button `reassure`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.toddler.reassure.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.toddler.reassure/1   [29 chars]
    en  Really? Okay. Okay. I'm safe.
    >>  ............................................
    pt  Sério? Tá. Tá bom. Estou seguro.
    >>  ............................................
  dialogue.conversations.fears.toddler.reassure/2   [53 chars]
    en  You'd tell me if there WAS a monster though. Promise?
    >>  ............................................
    pt  Mas você me contaria se TIVESSE um monstro. Promete?
    >>  ............................................
  dialogue.conversations.fears.toddler.reassure/3   [30 chars]
    en  Okay. I feel a bit braver now.
    >>  ............................................
    pt  Tá. Estou me sentindo um pouco mais corajoso agora.
    >>  ............................................
```


### Button `ask` — "What's it like, the scary thing?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `fears.toddler.to.fears.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.toddler.ask` — accepted phrasings: "what is it like"; "what does it look like"; "describe it to me"; "what is the scary thing"
  - the message must contain one of: `like`, `look`, `describe`, `what`
  - scored words: `like`(1.0), `look`(1.5), `what`(0.6), `describe`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.toddler.respond.ask   [32 chars]
    en  What's it like, the scary thing?
    >>  ............................................
    pt  Como é essa coisa assustadora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +1, familiarity +1  _(recorded under topic `fears.toddler.ask`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What's it like, the scary thing?"
       spoken on: conversations.topic.fears.toddler.respond, button `ask`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.toddler.ask.terminal`: the villager asks. Subject `fears.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.toddler.ask/1   [58 chars]
    en  It's big. And it doesn't have a face. THAT'S the bad part.
    >>  ............................................
    pt  É grande. E não tem rosto. ESSA é a parte ruim.
    >>  ............................................
  dialogue.conversations.fears.toddler.ask/2   [52 chars]
    en  It lives under there. Only at night. It's very rude.
    >>  ............................................
    pt  Mora ali embaixo. Só de noite. É muito mal-educado.
    >>  ............................................
  dialogue.conversations.fears.toddler.ask/3   [47 chars]
    en  I don't want to say it out loud. It might hear.
    >>  ............................................
    pt  Não quero falar em voz alta. Pode escutar.
    >>  ............................................
```


### Button `leave` — "Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.toddler.to.fears.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.toddler.respond.leave   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go."
       spoken on: conversations.topic.fears.toddler.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.toddler.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.toddler.leave/1   [10 chars]
    en  Okay. Bye.
    >>  ............................................
    pt  Tá. Tchau.
    >>  ............................................
  dialogue.conversations.fears.toddler.leave/2   [23 chars]
    en  Bye! Leave the lamp on?
    >>  ............................................
    pt  Tchau! Deixa a luz acesa?
    >>  ............................................
  dialogue.conversations.fears.toddler.leave/3   [23 chars]
    en  Bye bye. I'll be brave.
    >>  ............................................
    pt  Tchau tchau. Vou ser corajoso.
    >>  ............................................
```

---


## `conversations.topic.fears.young.respond`

**Reached from 2 route(s):** `conversations.cat.personal` / `fears`; `conversations.cat.personal` / `fears`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.fears.child` — e.g. "The under-the-bed place. Papa checked it but monsters KNOW when papas leave."
- `conversations.fears.teen` — e.g. "That I'll end up exactly like everyone here and never find out what else I could've been."


```text
POOL   dialogue key: dialogue.conversations.topic.fears.young.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.fears.young.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.fears.young.respond   [30 chars]
    en  That's what scares me, anyway.
    >>  ............................................
    pt  Enfim, é isso que me assusta.
    >>  ............................................
```


### Button `reassure` — "That's a real thing to be scared of."

*stance family `restraint` · tone `gentle` · answers the beat(s) `fears.child.to.fears.young`, `fears.teen.to.fears.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.young.reassure` — accepted phrasings: "that is a real thing to fear"; "that is fair"; "that is valid"; "that is a real fear"
  - the message must contain one of: `real`, `valid`, `fair`
  - scored words: `real`(1.5), `valid`(1.5), `fair`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.young.respond.reassure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.young.respond.reassure   [36 chars]
    en  That's a real thing to be scared of.
    >>  ............................................
    pt  É uma coisa de verdade para se ter medo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.young.reassure`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `fears.young.reassure`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.young.reassure
WHO    VILLAGER — what the player reads after pressing "That's a real thing to be scared of."
       spoken on: conversations.topic.fears.young.respond, button `reassure`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.young.reassure.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.young.reassure/1   [47 chars]
    en  ...Yeah. It is. Everyone acts like it's stupid.
    >>  ............................................
    pt  ...É. É sim. Todo mundo age como se fosse bobagem.
    >>  ............................................
  dialogue.conversations.fears.young.reassure/2   [49 chars]
    en  Thanks. Most grown-ups just say 'don't be silly'.
    >>  ............................................
    pt  Valeu. A maioria dos adultos só diz 'não seja bobo'.
    >>  ............................................
  dialogue.conversations.fears.young.reassure/3   [32 chars]
    en  You're not laughing. That's new.
    >>  ............................................
    pt  Você não está rindo. Isso é novo.
    >>  ............................................
```


### Button `normalize` — "Everyone I know is scared of something."

*stance family `empathy` · tone `gentle` · answers the beat(s) `fears.child.to.fears.young`, `fears.teen.to.fears.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.young.normalize` — accepted phrasings: "everyone is scared of something"; "everybody has fears"; "that is normal"; "we all have one"
  - the message must contain one of: `everyone`, `everybody`, `normal`
  - scored words: `everyone`(1.5), `everybody`(1.5), `normal`(1.2), `all`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.young.respond.normalize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.young.respond.normalize   [39 chars]
    en  Everyone I know is scared of something.
    >>  ............................................
    pt  Todo mundo que conheço tem medo de alguma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `fears.young.normalize`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — trust +3, respect +1  _(recorded under topic `fears.young.normalize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.young.normalize
WHO    VILLAGER — what the player reads after pressing "Everyone I know is scared of something."
       spoken on: conversations.topic.fears.young.respond, button `normalize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.young.normalize.terminal`: the villager accepts. Subject `fears.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.young.normalize/1   [49 chars]
    en  Everyone? Even you? ...Huh. That helps, actually.
    >>  ............................................
    pt  Todo mundo? Até você? ...Hm. Isso ajuda, na real.
    >>  ............................................
  dialogue.conversations.fears.young.normalize/2   [47 chars]
    en  So it's not just me being weird about it. Good.
    >>  ............................................
    pt  Então não sou só eu sendo esquisito. Que bom.
    >>  ............................................
  dialogue.conversations.fears.young.normalize/3   [52 chars]
    en  Everyone's scared of something. I'll hold onto that.
    >>  ............................................
    pt  Todo mundo tem medo de algo. Vou guardar isso.
    >>  ............................................
```


### Button `dismiss` — "You'll grow out of it."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `fears.child.to.fears.young`, `fears.teen.to.fears.young`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `fears.young.dismiss` — accepted phrasings: "you will grow out of it"; "do not be silly"; "that is childish"; "you will get over it"
  - the message must contain one of: `grow`, `silly`, `childish`
  - scored words: `grow`(1.5), `silly`(1.5), `childish`(1.5), `out`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.fears.young.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.young.respond.dismiss   [22 chars]
    en  You'll grow out of it.
    >>  ............................................
    pt  Você vai superar isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `fears.young.dismiss`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +4  _(recorded under topic `fears.young.dismiss`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.young.dismiss
WHO    VILLAGER — what the player reads after pressing "You'll grow out of it."
       spoken on: conversations.topic.fears.young.respond, button `dismiss`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.young.dismiss.terminal`: the villager dismisss. Subject `fears.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.young.dismiss/1   [31 chars]
    en  ...Right. Thanks. Very helpful.
    >>  ............................................
    pt  ...Certo. Valeu. Muito útil.
    >>  ............................................
  dialogue.conversations.fears.young.dismiss/2   [76 chars]
    en  Grow out of it. Sure. That's what people say when they've stopped listening.
    >>  ............................................
    pt  Superar. Claro. É o que se diz quando já parou de ouvir.
    >>  ............................................
  dialogue.conversations.fears.young.dismiss/3   [29 chars]
    en  Forget I said anything, then.
    >>  ............................................
    pt  Esquece que eu falei, então.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.young.dismiss/1
    en  ...I know it's silly. Knowing hasn't stopped it, %1$s.
    >>  ............................................
    pt  ...Eu sei que é bobo. Saber não fez parar, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.fears.young.dismiss/2
    en  Right. Yes. I'd already told myself all of that.
    >>  ............................................
    pt  Certo. Sim. Eu já tinha dito tudo isso a mim mesmo.
    >>  ............................................
  anxious.dialogue.conversations.fears.young.dismiss/3
    en  ...Sorry. I'll not bring it up again.
    >>  ............................................
    pt  ...Desculpe. Não levanto de novo.
    >>  ............................................
  athletic.dialogue.conversations.fears.young.dismiss/1
    en  ...Aye, well. It's still there in the dark whatever we call it.
    >>  ............................................
    pt  ...É, bom. Continua lá no escuro, chamemos como chamarmos.
    >>  ............................................
  athletic.dialogue.conversations.fears.young.dismiss/2
    en  Right you are. It'll pass, or it won't, and I'll be here either way.
    >>  ............................................
    pt  Você tem razão. Vai passar, ou não, e eu vou estar aqui de todo jeito.
    >>  ............................................
  athletic.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll sit with it a while longer.
    >>  ............................................
    pt  ...Tudo bem. Vou ficar com isso mais um pouco.
    >>  ............................................
  confident.dialogue.conversations.fears.young.dismiss/1
    en  ...Right. Thanks. Very helpful.
    >>  ............................................
    pt  ...Certo. Obrigado. Muito útil.
    >>  ............................................
  confident.dialogue.conversations.fears.young.dismiss/2
    en  That's not an answer. That's you not wanting to hear it.
    >>  ............................................
    pt  Isso não é resposta. É você não querendo ouvir.
    >>  ............................................
  confident.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll be frightened quietly, then.
    >>  ............................................
    pt  ...Tudo bem. Vou ter medo em silêncio, então.
    >>  ............................................
  crabby.dialogue.conversations.fears.young.dismiss/1
    en  ...Right. Thanks. Very helpful.
    >>  ............................................
    pt  ...Certo. Obrigado. Muito útil.
    >>  ............................................
  crabby.dialogue.conversations.fears.young.dismiss/2
    en  That's not an answer. That's you not wanting to hear it.
    >>  ............................................
    pt  Isso não é resposta. É você não querendo ouvir.
    >>  ............................................
  crabby.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll be frightened quietly, then.
    >>  ............................................
    pt  ...Tudo bem. Vou ter medo em silêncio, então.
    >>  ............................................
  extroverted.dialogue.conversations.fears.young.dismiss/1
    en  ...I told you because I thought you'd understand, %1$s.
    >>  ............................................
    pt  ...Eu contei porque achei que você fosse entender, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.young.dismiss/2
    en  That's what people say when they'd rather not be in the room.
    >>  ............................................
    pt  É o que as pessoas dizem quando preferem não estar na sala.
    >>  ............................................
  extroverted.dialogue.conversations.fears.young.dismiss/3
    en  ...Right. I'll take it to somebody else, then.
    >>  ............................................
    pt  ...Certo. Vou levar pra outra pessoa, então.
    >>  ............................................
  flirty.dialogue.conversations.fears.young.dismiss/1
    en  ...I told you because I thought you'd understand, %1$s.
    >>  ............................................
    pt  ...Eu contei porque achei que você fosse entender, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.fears.young.dismiss/2
    en  That's what people say when they'd rather not be in the room.
    >>  ............................................
    pt  É o que as pessoas dizem quando preferem não estar na sala.
    >>  ............................................
  flirty.dialogue.conversations.fears.young.dismiss/3
    en  ...Right. I'll take it to somebody else, then.
    >>  ............................................
    pt  ...Certo. Vou levar pra outra pessoa, então.
    >>  ............................................
  friendly.dialogue.conversations.fears.young.dismiss/1
    en  ...I told you because I thought you'd understand, %1$s.
    >>  ............................................
    pt  ...Eu contei porque achei que você fosse entender, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.fears.young.dismiss/2
    en  That's what people say when they'd rather not be in the room.
    >>  ............................................
    pt  É o que as pessoas dizem quando preferem não estar na sala.
    >>  ............................................
  friendly.dialogue.conversations.fears.young.dismiss/3
    en  ...Right. I'll take it to somebody else, then.
    >>  ............................................
    pt  ...Certo. Vou levar pra outra pessoa, então.
    >>  ............................................
  gloomy.dialogue.conversations.fears.young.dismiss/1
    en  ...I know it's silly. Knowing hasn't stopped it, %1$s.
    >>  ............................................
    pt  ...Eu sei que é bobo. Saber não fez parar, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.fears.young.dismiss/2
    en  Right. Yes. I'd already told myself all of that.
    >>  ............................................
    pt  Certo. Sim. Eu já tinha dito tudo isso a mim mesmo.
    >>  ............................................
  gloomy.dialogue.conversations.fears.young.dismiss/3
    en  ...Sorry. I'll not bring it up again.
    >>  ............................................
    pt  ...Desculpe. Não levanto de novo.
    >>  ............................................
  greedy.dialogue.conversations.fears.young.dismiss/1
    en  ...Right. Thanks. Very helpful.
    >>  ............................................
    pt  ...Certo. Obrigado. Muito útil.
    >>  ............................................
  greedy.dialogue.conversations.fears.young.dismiss/2
    en  That's not an answer. That's you not wanting to hear it.
    >>  ............................................
    pt  Isso não é resposta. É você não querendo ouvir.
    >>  ............................................
  greedy.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll be frightened quietly, then.
    >>  ............................................
    pt  ...Tudo bem. Vou ter medo em silêncio, então.
    >>  ............................................
  grumpy.dialogue.conversations.fears.young.dismiss/1
    en  ...Right. Thanks. Very helpful.
    >>  ............................................
    pt  ...Certo. Obrigado. Muito útil.
    >>  ............................................
  grumpy.dialogue.conversations.fears.young.dismiss/2
    en  That's not an answer. That's you not wanting to hear it.
    >>  ............................................
    pt  Isso não é resposta. É você não querendo ouvir.
    >>  ............................................
  grumpy.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll be frightened quietly, then.
    >>  ............................................
    pt  ...Tudo bem. Vou ter medo em silêncio, então.
    >>  ............................................
  introverted.dialogue.conversations.fears.young.dismiss/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  introverted.dialogue.conversations.fears.young.dismiss/2
    en  That's not helpful and you knew it wasn't.
    >>  ............................................
    pt  Isso não ajuda e você sabia que não ajudava.
    >>  ............................................
  introverted.dialogue.conversations.fears.young.dismiss/3
    en  ...I'll stop mentioning it.
    >>  ............................................
    pt  ...Vou parar de mencionar.
    >>  ............................................
  lazy.dialogue.conversations.fears.young.dismiss/1
    en  ...Aye, well. It's still there in the dark whatever we call it.
    >>  ............................................
    pt  ...É, bom. Continua lá no escuro, chamemos como chamarmos.
    >>  ............................................
  lazy.dialogue.conversations.fears.young.dismiss/2
    en  Right you are. It'll pass, or it won't, and I'll be here either way.
    >>  ............................................
    pt  Você tem razão. Vai passar, ou não, e eu vou estar aqui de todo jeito.
    >>  ............................................
  lazy.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll sit with it a while longer.
    >>  ............................................
    pt  ...Tudo bem. Vou ficar com isso mais um pouco.
    >>  ............................................
  odd.dialogue.conversations.fears.young.dismiss/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  odd.dialogue.conversations.fears.young.dismiss/2
    en  That's not helpful and you knew it wasn't.
    >>  ............................................
    pt  Isso não ajuda e você sabia que não ajudava.
    >>  ............................................
  odd.dialogue.conversations.fears.young.dismiss/3
    en  ...I'll stop mentioning it.
    >>  ............................................
    pt  ...Vou parar de mencionar.
    >>  ............................................
  peaceful.dialogue.conversations.fears.young.dismiss/1
    en  ...Aye, well. It's still there in the dark whatever we call it.
    >>  ............................................
    pt  ...É, bom. Continua lá no escuro, chamemos como chamarmos.
    >>  ............................................
  peaceful.dialogue.conversations.fears.young.dismiss/2
    en  Right you are. It'll pass, or it won't, and I'll be here either way.
    >>  ............................................
    pt  Você tem razão. Vai passar, ou não, e eu vou estar aqui de todo jeito.
    >>  ............................................
  peaceful.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll sit with it a while longer.
    >>  ............................................
    pt  ...Tudo bem. Vou ficar com isso mais um pouco.
    >>  ............................................
  peppy.dialogue.conversations.fears.young.dismiss/1
    en  ...Ha. Yes. Solved. Thank you ever so much.
    >>  ............................................
    pt  ...Ha. Sim. Resolvido. Muitíssimo obrigado.
    >>  ............................................
  peppy.dialogue.conversations.fears.young.dismiss/2
    en  Right! I'll simply stop being frightened. Why did nobody say?
    >>  ............................................
    pt  Certo! Vou simplesmente parar de ter medo. Por que ninguém disse?
    >>  ............................................
  peppy.dialogue.conversations.fears.young.dismiss/3
    en  ...Marvellous advice. I'll get straight on with it.
    >>  ............................................
    pt  ...Conselho maravilhoso. Vou providenciar agora mesmo.
    >>  ............................................
  playful.dialogue.conversations.fears.young.dismiss/1
    en  ...Ha. Yes. Solved. Thank you ever so much.
    >>  ............................................
    pt  ...Ha. Sim. Resolvido. Muitíssimo obrigado.
    >>  ............................................
  playful.dialogue.conversations.fears.young.dismiss/2
    en  Right! I'll simply stop being frightened. Why did nobody say?
    >>  ............................................
    pt  Certo! Vou simplesmente parar de ter medo. Por que ninguém disse?
    >>  ............................................
  playful.dialogue.conversations.fears.young.dismiss/3
    en  ...Marvellous advice. I'll get straight on with it.
    >>  ............................................
    pt  ...Conselho maravilhoso. Vou providenciar agora mesmo.
    >>  ............................................
  relaxed.dialogue.conversations.fears.young.dismiss/1
    en  ...Aye, well. It's still there in the dark whatever we call it.
    >>  ............................................
    pt  ...É, bom. Continua lá no escuro, chamemos como chamarmos.
    >>  ............................................
  relaxed.dialogue.conversations.fears.young.dismiss/2
    en  Right you are. It'll pass, or it won't, and I'll be here either way.
    >>  ............................................
    pt  Você tem razão. Vai passar, ou não, e eu vou estar aqui de todo jeito.
    >>  ............................................
  relaxed.dialogue.conversations.fears.young.dismiss/3
    en  ...Fine. I'll sit with it a while longer.
    >>  ............................................
    pt  ...Tudo bem. Vou ficar com isso mais um pouco.
    >>  ............................................
  sensitive.dialogue.conversations.fears.young.dismiss/1
    en  ...I know it's silly. Knowing hasn't stopped it, %1$s.
    >>  ............................................
    pt  ...Eu sei que é bobo. Saber não fez parar, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.fears.young.dismiss/2
    en  Right. Yes. I'd already told myself all of that.
    >>  ............................................
    pt  Certo. Sim. Eu já tinha dito tudo isso a mim mesmo.
    >>  ............................................
  sensitive.dialogue.conversations.fears.young.dismiss/3
    en  ...Sorry. I'll not bring it up again.
    >>  ............................................
    pt  ...Desculpe. Não levanto de novo.
    >>  ............................................
  shy.dialogue.conversations.fears.young.dismiss/1
    en  ...Right.
    >>  ............................................
    pt  ...Certo.
    >>  ............................................
  shy.dialogue.conversations.fears.young.dismiss/2
    en  That's not helpful and you knew it wasn't.
    >>  ............................................
    pt  Isso não ajuda e você sabia que não ajudava.
    >>  ............................................
  shy.dialogue.conversations.fears.young.dismiss/3
    en  ...I'll stop mentioning it.
    >>  ............................................
    pt  ...Vou parar de mencionar.
    >>  ............................................
  upbeat.dialogue.conversations.fears.young.dismiss/1
    en  ...Ha. Yes. Solved. Thank you ever so much.
    >>  ............................................
    pt  ...Ha. Sim. Resolvido. Muitíssimo obrigado.
    >>  ............................................
  upbeat.dialogue.conversations.fears.young.dismiss/2
    en  Right! I'll simply stop being frightened. Why did nobody say?
    >>  ............................................
    pt  Certo! Vou simplesmente parar de ter medo. Por que ninguém disse?
    >>  ............................................
  upbeat.dialogue.conversations.fears.young.dismiss/3
    en  ...Marvellous advice. I'll get straight on with it.
    >>  ............................................
    pt  ...Conselho maravilhoso. Vou providenciar agora mesmo.
    >>  ............................................
  witty.dialogue.conversations.fears.young.dismiss/1
    en  ...Ha. Yes. Solved. Thank you ever so much.
    >>  ............................................
    pt  ...Ha. Sim. Resolvido. Muitíssimo obrigado.
    >>  ............................................
  witty.dialogue.conversations.fears.young.dismiss/2
    en  Right! I'll simply stop being frightened. Why did nobody say?
    >>  ............................................
    pt  Certo! Vou simplesmente parar de ter medo. Por que ninguém disse?
    >>  ............................................
  witty.dialogue.conversations.fears.young.dismiss/3
    en  ...Marvellous advice. I'll get straight on with it.
    >>  ............................................
    pt  ...Conselho maravilhoso. Vou providenciar agora mesmo.
    >>  ............................................
```

</details>


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `fears.child.to.fears.young`, `fears.teen.to.fears.young` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.fears.young.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.fears.young.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.fears.young.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.fears.young.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.fears.young.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.young.leave.terminal`: the villager accepts. Subject `fears.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.fears.young.leave/1   [15 chars]
    en  See you around.
    >>  ............................................
    pt  Até mais.
    >>  ............................................
  dialogue.conversations.fears.young.leave/2   [10 chars]
    en  Yeah. Bye.
    >>  ............................................
    pt  É. Tchau.
    >>  ............................................
  dialogue.conversations.fears.young.leave/3   [39 chars]
    en  Alright. Don't tell anyone I said that.
    >>  ............................................
    pt  Tá bom. Não conta para ninguém que eu falei isso.
    >>  ............................................
```

---

