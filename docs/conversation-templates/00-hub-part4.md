# Hub, greeting and category pages — part 4 of 4

> Continued from [00-hub-part1.md](00-hub-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](00-hub-part1.md) · [part 2](00-hub-part2.md) · [part 3](00-hub-part3.md) · [part 4](00-hub-part4.md)


## Nodes in this file

- [`conversations.cat.village`](#conversations-cat-village)
- [`greet`](#greet)
- [`main`](#main)

---

## `conversations.cat.village` — continued


**Outcome 7 of 13** — base weight `0`

- Fires when: weighted +100 when the personality is `friendly`, `peppy`, `peaceful`, `upbeat`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `warm` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.fond.respond`
- …where the player's next choices will be: "They've been good to me as well." | "Who's your favourite, then?" | "Even the ones who borrow your tools?" | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.warm
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.fond.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.warm_view`: the villager celebrates. Subject `people.affection`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `people:fond` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.warm/1   [71 chars]
    en  I love them. All of them. Even the loud ones. ESPECIALLY the loud ones.
    >>  ............................................
    pt  Eu amo eles. Todos. Até os barulhentos. PRINCIPALMENTE os barulhentos.
    >>  ............................................
  dialogue.conversations.people.warm/2   [87 chars]
    en  This village is one big family that argues about fences. I wouldn't trade a single one.
    >>  ............................................
    pt  Esse vilarejo é uma família grande que discute sobre cerca. Eu não trocaria nenhum deles.
    >>  ............................................
  dialogue.conversations.people.warm/3   [80 chars]
    en  Every door here would open for me at midnight if I knocked. That's wealth, %1$s.
    >>  ............................................
    pt  Toda porta daqui se abriria pra mim à meia-noite se eu batesse. Isso é riqueza, %1$s.
    >>  ............................................
```


**Outcome 8 of 13** — base weight `0`

- Fires when: weighted +100 when the personality is `crabby`, `greedy`, `confident`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `judgy` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.sour.respond`
- …where the player's next choices will be: "I've noticed the same." | "They're not so bad, most of them." | "Give me an example." | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.judgy
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.sour.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.sour_view`: the villager complains. Subject `people.conflict`, polarity `negative`, permits followup, outcome `None`.
NOTE   this is the line that establishes `people:mixed`, `people:sour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, respectful_disagreement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.judgy/1   [78 chars]
    en  Half of them owe me favors and the other half owe me apologies. I keep a list.
    >>  ............................................
    pt  Metade me deve favor e a outra metade me deve desculpa. Eu mantenho uma lista.
    >>  ............................................
  dialogue.conversations.people.judgy/2   [87 chars]
    en  Fine people, if you like noise, borrowed tools that never return, and opinions at dawn.
    >>  ............................................
    pt  Gente ótima, se você gosta de barulho, ferramenta emprestada que nunca volta e opinião de madrugada.
    >>  ............................................
  dialogue.conversations.people.judgy/3   [70 chars]
    en  I'd rank them, but you'd repeat it, and then I'd have to rank you too.
    >>  ............................................
    pt  Eu faria um ranking, mas você repetiria, e aí eu teria que te ranquear também.
    >>  ............................................
```


**Outcome 9 of 13** — base weight `0`

- Fires when: weighted +100 when the personality is `introverted`, `anxious`, `sensitive`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `shy` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.mixed.respond`
- …where the player's next choices will be: "I've noticed the same." | "Give me an example." | "Some of that sounds like your side of it." | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.shy
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.mixed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.guarded_view`: the villager discloses. Subject `people.guarded`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `people:mixed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.shy/1   [75 chars]
    en  They're... a lot. Kind, but a lot. I like them best from my window, waving.
    >>  ............................................
    pt  Eles são... muita coisa. Gentis, mas muita coisa. Gosto mais deles da minha janela, acenando.
    >>  ............................................
  dialogue.conversations.people.shy/2   [84 chars]
    en  Everyone's lovely. I just need three days' notice and an escape route to enjoy them.
    >>  ............................................
    pt  Todo mundo é adorável. Eu só preciso de três dias de aviso e uma rota de fuga pra aproveitar.
    >>  ............................................
  dialogue.conversations.people.shy/3   [56 chars]
    en  I know them all by footstep. It saves having to look up.
    >>  ............................................
    pt  Conheço todos pelo passo. Poupa ter que levantar o olhar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving, %1$s.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.people.shy/2
    en  Kind and loud. I want to be in it and I can't be, and that's the whole difficulty.
    >>  ............................................
    pt  Gentis e barulhentos. Eu quero estar junto e não consigo, e é toda a dificuldade.
    >>  ............................................
  anxious.dialogue.conversations.people.shy/3
    en  A lot. It isn't them. It's me, and I've made my peace with about half of that.
    >>  ............................................
    pt  Muito. Não são eles. Sou eu, e eu fiz as pazes com uns cinquenta por cento disso.
    >>  ............................................
  athletic.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. The window suits me and it always has.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. A janela me serve e sempre serviu.
    >>  ............................................
  athletic.dialogue.conversations.people.shy/2
    en  Kind and loud. I've had years to arrange my days around the loud part.
    >>  ............................................
    pt  Gentis e barulhentos. Tive anos pra organizar meus dias em volta da parte barulhenta.
    >>  ............................................
  athletic.dialogue.conversations.people.shy/3
    en  A lot. There's no hurry to be in the middle of it, and I never have been.
    >>  ............................................
    pt  Muito. Não há pressa de estar no meio, e eu nunca estive.
    >>  ............................................
  confident.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da minha janela.
    >>  ............................................
  confident.dialogue.conversations.people.shy/2
    en  Kind and loud. I manage them in small quantities.
    >>  ............................................
    pt  Gentis e barulhentos. Eu os administro em pequenas quantidades.
    >>  ............................................
  confident.dialogue.conversations.people.shy/3
    en  A lot. That isn't a complaint; it's a measurement.
    >>  ............................................
    pt  Muito. Não é queixa; é medida.
    >>  ............................................
  crabby.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da minha janela.
    >>  ............................................
  crabby.dialogue.conversations.people.shy/2
    en  Kind and loud. I manage them in small quantities.
    >>  ............................................
    pt  Gentis e barulhentos. Eu os administro em pequenas quantidades.
    >>  ............................................
  crabby.dialogue.conversations.people.shy/3
    en  A lot. That isn't a complaint; it's a measurement.
    >>  ............................................
    pt  Muito. Não é queixa; é medida.
    >>  ............................................
  extroverted.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving — and you're easier than most.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando — e você é mais fácil que a maioria.
    >>  ............................................
  extroverted.dialogue.conversations.people.shy/2
    en  Kind and loud. I do like them; I'd simply prefer them one at a time.
    >>  ............................................
    pt  Gentis e barulhentos. Eu gosto deles; só preferia um por vez.
    >>  ............................................
  extroverted.dialogue.conversations.people.shy/3
    en  A lot, and I mean that fondly. Come round when it's just you and I'll be much better company.
    >>  ............................................
    pt  Muito, e eu digo com carinho. Apareça quando for só você e eu vou ser companhia bem melhor.
    >>  ............................................
  flirty.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving — and you're easier than most.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando — e você é mais fácil que a maioria.
    >>  ............................................
  flirty.dialogue.conversations.people.shy/2
    en  Kind and loud. I do like them; I'd simply prefer them one at a time.
    >>  ............................................
    pt  Gentis e barulhentos. Eu gosto deles; só preferia um por vez.
    >>  ............................................
  flirty.dialogue.conversations.people.shy/3
    en  A lot, and I mean that fondly. Come round when it's just you and I'll be much better company.
    >>  ............................................
    pt  Muito, e eu digo com carinho. Apareça quando for só você e eu vou ser companhia bem melhor.
    >>  ............................................
  friendly.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving — and you're easier than most.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando — e você é mais fácil que a maioria.
    >>  ............................................
  friendly.dialogue.conversations.people.shy/2
    en  Kind and loud. I do like them; I'd simply prefer them one at a time.
    >>  ............................................
    pt  Gentis e barulhentos. Eu gosto deles; só preferia um por vez.
    >>  ............................................
  friendly.dialogue.conversations.people.shy/3
    en  A lot, and I mean that fondly. Come round when it's just you and I'll be much better company.
    >>  ............................................
    pt  Muito, e eu digo com carinho. Apareça quando for só você e eu vou ser companhia bem melhor.
    >>  ............................................
  gloomy.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving, %1$s.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.people.shy/2
    en  Kind and loud. I want to be in it and I can't be, and that's the whole difficulty.
    >>  ............................................
    pt  Gentis e barulhentos. Eu quero estar junto e não consigo, e é toda a dificuldade.
    >>  ............................................
  gloomy.dialogue.conversations.people.shy/3
    en  A lot. It isn't them. It's me, and I've made my peace with about half of that.
    >>  ............................................
    pt  Muito. Não são eles. Sou eu, e eu fiz as pazes com uns cinquenta por cento disso.
    >>  ............................................
  greedy.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da minha janela.
    >>  ............................................
  greedy.dialogue.conversations.people.shy/2
    en  Kind and loud. I manage them in small quantities.
    >>  ............................................
    pt  Gentis e barulhentos. Eu os administro em pequenas quantidades.
    >>  ............................................
  greedy.dialogue.conversations.people.shy/3
    en  A lot. That isn't a complaint; it's a measurement.
    >>  ............................................
    pt  Muito. Não é queixa; é medida.
    >>  ............................................
  grumpy.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da minha janela.
    >>  ............................................
  grumpy.dialogue.conversations.people.shy/2
    en  Kind and loud. I manage them in small quantities.
    >>  ............................................
    pt  Gentis e barulhentos. Eu os administro em pequenas quantidades.
    >>  ............................................
  grumpy.dialogue.conversations.people.shy/3
    en  A lot. That isn't a complaint; it's a measurement.
    >>  ............................................
    pt  Muito. Não é queixa; é medida.
    >>  ............................................
  introverted.dialogue.conversations.people.shy/1
    en  They're... a lot. Kind, but a lot.
    >>  ............................................
    pt  Eles são... muito. Gentis, mas muito.
    >>  ............................................
  introverted.dialogue.conversations.people.shy/2
    en  I like them best from my window.
    >>  ............................................
    pt  Gosto mais deles da minha janela.
    >>  ............................................
  introverted.dialogue.conversations.people.shy/3
    en  A lot. One at a time is better.
    >>  ............................................
    pt  Muito. Um por vez é melhor.
    >>  ............................................
  lazy.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. The window suits me and it always has.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. A janela me serve e sempre serviu.
    >>  ............................................
  lazy.dialogue.conversations.people.shy/2
    en  Kind and loud. I've had years to arrange my days around the loud part.
    >>  ............................................
    pt  Gentis e barulhentos. Tive anos pra organizar meus dias em volta da parte barulhenta.
    >>  ............................................
  lazy.dialogue.conversations.people.shy/3
    en  A lot. There's no hurry to be in the middle of it, and I never have been.
    >>  ............................................
    pt  Muito. Não há pressa de estar no meio, e eu nunca estive.
    >>  ............................................
  odd.dialogue.conversations.people.shy/1
    en  They're... a lot. Kind, but a lot.
    >>  ............................................
    pt  Eles são... muito. Gentis, mas muito.
    >>  ............................................
  odd.dialogue.conversations.people.shy/2
    en  I like them best from my window.
    >>  ............................................
    pt  Gosto mais deles da minha janela.
    >>  ............................................
  odd.dialogue.conversations.people.shy/3
    en  A lot. One at a time is better.
    >>  ............................................
    pt  Muito. Um por vez é melhor.
    >>  ............................................
  peaceful.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. The window suits me and it always has.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. A janela me serve e sempre serviu.
    >>  ............................................
  peaceful.dialogue.conversations.people.shy/2
    en  Kind and loud. I've had years to arrange my days around the loud part.
    >>  ............................................
    pt  Gentis e barulhentos. Tive anos pra organizar meus dias em volta da parte barulhenta.
    >>  ............................................
  peaceful.dialogue.conversations.people.shy/3
    en  A lot. There's no hurry to be in the middle of it, and I never have been.
    >>  ............................................
    pt  Muito. Não há pressa de estar no meio, e eu nunca estive.
    >>  ............................................
  peppy.dialogue.conversations.people.shy/1
    en  They're... a lot! Kind, but a lot. I like them best from my window, waving.
    >>  ............................................
    pt  Eles são... muito! Gentis, mas muito. Gosto mais deles da janela, acenando.
    >>  ............................................
  peppy.dialogue.conversations.people.shy/2
    en  Kind and extremely loud. I'm fond of them in doses of about ten minutes.
    >>  ............................................
    pt  Gentis e extremamente barulhentos. Gosto deles em doses de uns dez minutos.
    >>  ............................................
  peppy.dialogue.conversations.people.shy/3
    en  A lot. Wonderful people. Wonderful in the way weather is wonderful.
    >>  ............................................
    pt  Muito. Gente maravilhosa. Maravilhosa do jeito que o tempo é maravilhoso.
    >>  ............................................
  playful.dialogue.conversations.people.shy/1
    en  They're... a lot! Kind, but a lot. I like them best from my window, waving.
    >>  ............................................
    pt  Eles são... muito! Gentis, mas muito. Gosto mais deles da janela, acenando.
    >>  ............................................
  playful.dialogue.conversations.people.shy/2
    en  Kind and extremely loud. I'm fond of them in doses of about ten minutes.
    >>  ............................................
    pt  Gentis e extremamente barulhentos. Gosto deles em doses de uns dez minutos.
    >>  ............................................
  playful.dialogue.conversations.people.shy/3
    en  A lot. Wonderful people. Wonderful in the way weather is wonderful.
    >>  ............................................
    pt  Muito. Gente maravilhosa. Maravilhosa do jeito que o tempo é maravilhoso.
    >>  ............................................
  relaxed.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. The window suits me and it always has.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. A janela me serve e sempre serviu.
    >>  ............................................
  relaxed.dialogue.conversations.people.shy/2
    en  Kind and loud. I've had years to arrange my days around the loud part.
    >>  ............................................
    pt  Gentis e barulhentos. Tive anos pra organizar meus dias em volta da parte barulhenta.
    >>  ............................................
  relaxed.dialogue.conversations.people.shy/3
    en  A lot. There's no hurry to be in the middle of it, and I never have been.
    >>  ............................................
    pt  Muito. Não há pressa de estar no meio, e eu nunca estive.
    >>  ............................................
  sensitive.dialogue.conversations.people.shy/1
    en  They're a lot. Kind, but a lot. I like them best from my window, waving, %1$s.
    >>  ............................................
    pt  Eles são muito. Gentis, mas muito. Gosto mais deles da janela, acenando, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.people.shy/2
    en  Kind and loud. I want to be in it and I can't be, and that's the whole difficulty.
    >>  ............................................
    pt  Gentis e barulhentos. Eu quero estar junto e não consigo, e é toda a dificuldade.
    >>  ............................................
  sensitive.dialogue.conversations.people.shy/3
    en  A lot. It isn't them. It's me, and I've made my peace with about half of that.
    >>  ............................................
    pt  Muito. Não são eles. Sou eu, e eu fiz as pazes com uns cinquenta por cento disso.
    >>  ............................................
  shy.dialogue.conversations.people.shy/1
    en  They're... a lot. Kind, but a lot.
    >>  ............................................
    pt  Eles são... muito. Gentis, mas muito.
    >>  ............................................
  shy.dialogue.conversations.people.shy/2
    en  I like them best from my window.
    >>  ............................................
    pt  Gosto mais deles da minha janela.
    >>  ............................................
  shy.dialogue.conversations.people.shy/3
    en  A lot. One at a time is better.
    >>  ............................................
    pt  Muito. Um por vez é melhor.
    >>  ............................................
  upbeat.dialogue.conversations.people.shy/1
    en  They're... a lot! Kind, but a lot. I like them best from my window, waving.
    >>  ............................................
    pt  Eles são... muito! Gentis, mas muito. Gosto mais deles da janela, acenando.
    >>  ............................................
  upbeat.dialogue.conversations.people.shy/2
    en  Kind and extremely loud. I'm fond of them in doses of about ten minutes.
    >>  ............................................
    pt  Gentis e extremamente barulhentos. Gosto deles em doses de uns dez minutos.
    >>  ............................................
  upbeat.dialogue.conversations.people.shy/3
    en  A lot. Wonderful people. Wonderful in the way weather is wonderful.
    >>  ............................................
    pt  Muito. Gente maravilhosa. Maravilhosa do jeito que o tempo é maravilhoso.
    >>  ............................................
  witty.dialogue.conversations.people.shy/1
    en  They're... a lot! Kind, but a lot. I like them best from my window, waving.
    >>  ............................................
    pt  Eles são... muito! Gentis, mas muito. Gosto mais deles da janela, acenando.
    >>  ............................................
  witty.dialogue.conversations.people.shy/2
    en  Kind and extremely loud. I'm fond of them in doses of about ten minutes.
    >>  ............................................
    pt  Gentis e extremamente barulhentos. Gosto deles em doses de uns dez minutos.
    >>  ............................................
  witty.dialogue.conversations.people.shy/3
    en  A lot. Wonderful people. Wonderful in the way weather is wonderful.
    >>  ............................................
    pt  Muito. Gente maravilhosa. Maravilhosa do jeito que o tempo é maravilhoso.
    >>  ............................................
```

</details>


**Outcome 10 of 13** — base weight `0`

- Fires when: weighted +100 when the personality is `odd`, `playful`, `relaxed`, `extroverted`, `flirty`, `gloomy`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `wry` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.mixed.respond`
- …where the player's next choices will be: "I've noticed the same." | "Give me an example." | "Some of that sounds like your side of it." | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.wry
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.mixed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.wry_view`: the villager explains. Subject `people.wry`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `people:mixed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.wry/1   [92 chars]
    en  A finer collection of characters was never assembled on purpose. Note: it wasn't on purpose.
    >>  ............................................
    pt  Nunca se reuniu de propósito uma coleção mais fina de figuras. Observação: não foi de propósito.
    >>  ............................................
  dialogue.conversations.people.wry/2   [78 chars]
    en  The neighbors are free theater, %1$s. The matinee is the market. Bring snacks.
    >>  ............................................
    pt  Os vizinhos são teatro de graça, %1$s. A matinê é a feira. Traga petisco.
    >>  ............................................
  dialogue.conversations.people.wry/3   [68 chars]
    en  Decent souls. Odd hats. The correlation is real and I'm studying it.
    >>  ............................................
    pt  Almas decentes. Chapéus estranhos. A correlação é real e eu estou estudando.
    >>  ............................................
```


**Outcome 11 of 13** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.people` (this player only)
- Fires when: RULED OUT when the personality is `friendly`, `peppy`, `peaceful`, `upbeat`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `crabby`, `greedy`, `confident`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `introverted`, `anxious`, `sensitive`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `odd`, `playful`, `relaxed`, `extroverted`, `flirty`, `gloomy`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `revisit` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.mixed.respond`
- …where the player's next choices will be: "I've noticed the same." | "Give me an example." | "Some of that sounds like your side of it." | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.revisit
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.mixed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.revisited`: the villager reports. Subject `people.repeat`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `people:mixed`, `people:asked_before` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.revisit/1   [87 chars]
    en  You asked about the neighbors once. I've warmed up on a couple of them since. Slightly.
    >>  ............................................
    pt  Você perguntou dos vizinhos uma vez. Desde então eu me afeiçoei a uns dois. Levemente.
    >>  ............................................
  dialogue.conversations.people.revisit/2   [89 chars]
    en  Been watching folk differently since we talked. Everyone's carrying something, turns out.
    >>  ............................................
    pt  Ando olhando as pessoas de outro jeito desde a nossa conversa. Todo mundo carrega alguma coisa, pelo visto.
    >>  ............................................
  dialogue.conversations.people.revisit/3   [78 chars]
    en  Since you asked last — the miller apologized. I nearly fainted into the flour.
    >>  ............................................
    pt  Desde a última vez que você perguntou — o moleiro pediu desculpas. Quase desmaiei na farinha.
    >>  ............................................
```


**Outcome 12 of 13** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.people` (this player only)
- Fires when: RULED OUT when the personality is `friendly`, `peppy`, `peaceful`, `upbeat`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `crabby`, `greedy`, `confident`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `introverted`, `anxious`, `sensitive`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `odd`, `playful`, `relaxed`, `extroverted`, `flirty`, `gloomy`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `first` budget `standard`
- Does: remembers `mcaconversations.topic.people` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.mixed.respond`
- …where the player's next choices will be: "I've noticed the same." | "Give me an example." | "Some of that sounds like your side of it." | "I'll not stir it."

```text
POOL   dialogue key: dialogue.conversations.people.first
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.mixed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.first_view`: the villager reports. Subject `people.neighbours`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `people:mixed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.first/1   [85 chars]
    en  Good folk, mostly. We share fences, weather, and opinions about each other. It works.
    >>  ............................................
    pt  Gente boa, na maioria. A gente divide cerca, tempo e opinião um sobre o outro. Funciona.
    >>  ............................................
  dialogue.conversations.people.first/2   [91 chars]
    en  You live close enough to anyone, you learn their sneezes by name. I know everyone's sneeze.
    >>  ............................................
    pt  Se você mora perto o bastante de alguém, aprende a reconhecer o espirro pelo nome. Conheço o espirro de todo mundo.
    >>  ............................................
  dialogue.conversations.people.first/3   [65 chars]
    en  The neighbors? We feud gently and forgive quickly. Village rules.
    >>  ............................................
    pt  Os vizinhos? A gente briga com jeitinho e perdoa rápido. Regras de vilarejo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.people.first/1
    en  They're kind. I do worry I've said the wrong thing to them — usually I haven't. Usually.
    >>  ............................................
    pt  São gentis. Eu fico preocupado de ter dito a coisa errada pra eles — geralmente não disse. Geralmente.
    >>  ............................................
  anxious.dialogue.conversations.people.first/2
    en  Good people. I replay our conversations afterwards, though. Every one of them.
    >>  ............................................
    pt  Gente boa. Mas eu repasso as nossas conversas depois. Todas elas.
    >>  ............................................
  athletic.dialogue.conversations.people.first/1
    en  Good sparring stock, most of them. The smith arm-wrestles like a bear — I'm owed a rematch.
    >>  ............................................
    pt  Boa gente pra treinar junto, quase todos. O ferreiro faz queda de braço que nem urso — ele me deve uma revanche.
    >>  ............................................
  athletic.dialogue.conversations.people.first/2
    en  Solid folk. Raced half, hauled water for the rest. That's how you get to know a village, %1$s.
    >>  ............................................
    pt  Gente firme. Apostei corrida com metade, carreguei água pro resto. É assim que você conhece um vilarejo, %1$s.
    >>  ............................................
  confident.dialogue.conversations.people.first/1
    en  They adore me, mostly. The holdouts are simply jealous, which is its own kind of praise.
    >>  ............................................
    pt  Eles me adoram, na maioria. Os resistentes só têm inveja, o que é um tipo próprio de elogio.
    >>  ............................................
  confident.dialogue.conversations.people.first/2
    en  Good people. Half come to ME for advice — which I give freely and, naturally, correctly.
    >>  ............................................
    pt  Gente boa. Metade vem pedir conselho a MIM — que eu dou de graça e, naturalmente, corretamente.
    >>  ............................................
  crabby.dialogue.conversations.people.first/1
    en  Too many of them and all too loud. ...They're decent enough. I'd not say it to their faces.
    >>  ............................................
    pt  Gente demais e todos barulhentos demais. ...São decentes o bastante. Eu não diria isso na cara deles.
    >>  ............................................
  crabby.dialogue.conversations.people.first/2
    en  They talk too much. Half of them are all right. I'll not tell you which half.
    >>  ............................................
    pt  Falam demais. Metade presta. Não vou dizer qual metade.
    >>  ............................................
  extroverted.dialogue.conversations.people.first/1
    en  They're the best part, %1$s! Every one of them's got a story and I intend to hear all of them eventually.
    >>  ............................................
    pt  Eles são a melhor parte, %1$s! Cada um tem uma história e eu pretendo ouvir todas com o tempo.
    >>  ............................................
  extroverted.dialogue.conversations.people.first/2
    en  I adore them. Loud ones, quiet ones, difficult ones. Especially the difficult ones.
    >>  ............................................
    pt  Eu adoro eles. Os barulhentos, os quietos, os difíceis. Principalmente os difíceis.
    >>  ............................................
  flirty.dialogue.conversations.people.first/1
    en  Darlings, all of them. Half the village has a soft spot for me and I've one right back. Don't be jealous.
    >>  ............................................
    pt  Queridos, todos eles. Meio vilarejo tem um fraco por mim e eu tenho um por eles. Não fica com ciúme.
    >>  ............................................
  flirty.dialogue.conversations.people.first/2
    en  Good company — though none make me lose my place mid-sentence the way you do, %1$s.
    >>  ............................................
    pt  Boa companhia — embora nenhum deles me faça perder o fio da frase do jeito que você faz, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.people.first/1
    en  I adore them, every one. Even the prickly ones have a soft middle, with patience and cake.
    >>  ............................................
    pt  Eu adoro eles, cada um. Até os espinhentos têm um meio macio, com paciência e bolo.
    >>  ............................................
  friendly.dialogue.conversations.people.first/2
    en  The best neighbors in the world. We look after each other — that's the whole point of a village.
    >>  ............................................
    pt  Os melhores vizinhos do mundo. A gente cuida um do outro — é esse o sentido inteiro de um vilarejo.
    >>  ............................................
  gloomy.dialogue.conversations.people.first/1
    en  They're fine. We nod, we pass, we don't ask too much of each other. It works, in a grey sort of way.
    >>  ............................................
    pt  São bem. A gente acena, a gente passa, a gente não pede muito um do outro. Funciona, de um jeito cinzento.
    >>  ............................................
  gloomy.dialogue.conversations.people.first/2
    en  Good enough folk. Everyone here's carrying something. You learn not to add to the load, %1$s.
    >>  ............................................
    pt  Gente boa o bastante. Todo mundo aqui carrega alguma coisa. Você aprende a não somar ao fardo, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.people.first/1
    en  Good customers, most of them. A few slow payers. I keep a ledger of who owes whom — it's practically a map of the village.
    >>  ............................................
    pt  Bons clientes, quase todos. Alguns pagadores lentos. Mantenho um livro de quem deve a quem — é praticamente um mapa do vilarejo.
    >>  ............................................
  greedy.dialogue.conversations.people.first/2
    en  Reliable folk, if you count the coin after. I like them fine. I like them better when they settle up, %1$s.
    >>  ............................................
    pt  Gente confiável, se você conferir a moeda depois. Gosto deles. Gosto mais quando acertam a conta, %1$s.
    >>  ............................................
  grumpy.dialogue.conversations.people.first/1
    en  Too many of them and all too loud. ...They're decent enough. I'd not say it to their faces.
    >>  ............................................
    pt  Gente demais e todos barulhentos demais. ...São decentes o bastante. Eu não diria isso na cara deles.
    >>  ............................................
  grumpy.dialogue.conversations.people.first/2
    en  They talk too much. Half of them are all right. I'll not tell you which half.
    >>  ............................................
    pt  Falam demais. Metade presta. Não vou dizer qual metade.
    >>  ............................................
  introverted.dialogue.conversations.people.first/1
    en  They're decent, and I'm fond of them — in small numbers, and briefly. That's not a complaint, it's just how I'm built.
    >>  ............................................
    pt  São decentes, e eu gosto deles — em pequenas quantidades, e brevemente. Não é reclamação, é só como eu sou feito.
    >>  ............................................
  introverted.dialogue.conversations.people.first/2
    en  Good people. I'd rather know three of them properly than all of them a little.
    >>  ............................................
    pt  Gente boa. Prefiro conhecer três de verdade a conhecer todos um pouquinho.
    >>  ............................................
  lazy.dialogue.conversations.people.first/1
    en  Good sorts, mostly. They let me be, and I let them be. That's the whole arrangement, and it works.
    >>  ............................................
    pt  Gente boa, na maioria. Eles me deixam em paz, e eu deixo eles. É esse o acordo todo, e funciona.
    >>  ............................................
  lazy.dialogue.conversations.people.first/2
    en  I get on with most of them. Easier when you're not in a hurry to be anywhere.
    >>  ............................................
    pt  Me dou bem com quase todos. Fica mais fácil quando você não tem pressa de estar em lugar nenhum.
    >>  ............................................
  odd.dialogue.conversations.people.first/1
    en  Good folk. I've assigned each of them a cloud. The mayor's is stubborn; the baker's is warm. It's a whole system, %1$s.
    >>  ............................................
    pt  Gente boa. Atribuí uma nuvem a cada um. A do prefeito é teimosa; a do padeiro é quentinha. É um sistema inteiro, %1$s.
    >>  ............................................
  odd.dialogue.conversations.people.first/2
    en  The neighbors are lovely once you learn their weathers. Some are drizzle, some are noon. All are welcome at my fence.
    >>  ............................................
    pt  Os vizinhos são adoráveis depois que você aprende o clima de cada um. Alguns são garoa, outros são meio-dia. Todos são bem-vindos na minha cerca.
    >>  ............................................
  peaceful.dialogue.conversations.people.first/1
    en  They're good, and they're trying. That's true of most people, if you give them room to show it.
    >>  ............................................
    pt  São bons, e estão tentando. Isso vale pra quase todo mundo, se você der espaço pra mostrarem.
    >>  ............................................
  peaceful.dialogue.conversations.people.first/2
    en  I'm fond of them. I don't need them to be anything other than what they are.
    >>  ............................................
    pt  Tenho carinho por eles. Não preciso que sejam nada além do que são.
    >>  ............................................
  peppy.dialogue.conversations.people.first/1
    en  I LOVE them! Every single one! Even the grumpy ones — ESPECIALLY the grumpy ones, they're just soft on the inside!
    >>  ............................................
    pt  Eu AMO eles! Cada um! Até os rabugentos — PRINCIPALMENTE os rabugentos, eles são molinhos por dentro!
    >>  ............................................
  peppy.dialogue.conversations.people.first/2
    en  Best neighbors ever! We wave, we borrow, we bicker, we make up by supper! That's a village, %1$s! I adore it!
    >>  ............................................
    pt  Melhores vizinhos do mundo! A gente acena, empresta, briga e faz as pazes até a janta! Isso é um vilarejo, %1$s! Eu adoro!
    >>  ............................................
  playful.dialogue.conversations.people.first/1
    en  They're marvellous — every one of them falls for the same trick twice. I love them dearly for it.
    >>  ............................................
    pt  São maravilhosos — cada um deles cai na mesma pegadinha duas vezes. Eu os amo profundamente por isso.
    >>  ............................................
  playful.dialogue.conversations.people.first/2
    en  Good sports, mostly. The ones who aren't get teased hardest. That's only fair.
    >>  ............................................
    pt  Boa gente de brincadeira, na maioria. Os que não são levam as piores provocações. Só justo.
    >>  ............................................
  relaxed.dialogue.conversations.people.first/1
    en  Good sorts, mostly. They let me be, and I let them be. That's the whole arrangement, and it works.
    >>  ............................................
    pt  Gente boa, na maioria. Eles me deixam em paz, e eu deixo eles. É esse o acordo todo, e funciona.
    >>  ............................................
  relaxed.dialogue.conversations.people.first/2
    en  I get on with most of them. Easier when you're not in a hurry to be anywhere.
    >>  ............................................
    pt  Me dou bem com quase todos. Fica mais fácil quando você não tem pressa de estar em lugar nenhum.
    >>  ............................................
  sensitive.dialogue.conversations.people.first/1
    en  Good souls, all carrying something. I feel it when one of them is struggling, even across the square. I bring bread.
    >>  ............................................
    pt  Boas almas, todas carregando alguma coisa. Eu sinto quando uma delas está sofrendo, mesmo do outro lado da praça. Eu levo pão.
    >>  ............................................
  sensitive.dialogue.conversations.people.first/2
    en  They're precious to me, honestly. I know who hasn't smiled this week, and I make it my business to fix it.
    >>  ............................................
    pt  São preciosos pra mim, sinceramente. Sei quem não sorriu essa semana, e faço questão de resolver isso.
    >>  ............................................
  shy.dialogue.conversations.people.first/1
    en  They're decent, and I'm fond of them — in small numbers, and briefly. That's not a complaint, it's just how I'm built.
    >>  ............................................
    pt  São decentes, e eu gosto deles — em pequenas quantidades, e brevemente. Não é reclamação, é só como eu sou feito.
    >>  ............................................
  shy.dialogue.conversations.people.first/2
    en  Good people. I'd rather know three of them properly than all of them a little.
    >>  ............................................
    pt  Gente boa. Prefiro conhecer três de verdade a conhecer todos um pouquinho.
    >>  ............................................
  upbeat.dialogue.conversations.people.first/1
    en  They're good folk, %1$s. Every one of them has something worth knowing, if you give them the chance to show it.
    >>  ............................................
    pt  São gente boa, %1$s. Cada um tem algo que vale a pena conhecer, se você der a chance de mostrarem.
    >>  ............................................
  upbeat.dialogue.conversations.people.first/2
    en  I like them. Properly. They look out for each other here, and that's not nothing.
    >>  ............................................
    pt  Gosto deles. De verdade. Aqui um cuida do outro, e isso não é pouca coisa.
    >>  ............................................
  witty.dialogue.conversations.people.first/1
    en  They're good folk, %1$s. Every one of them has something worth knowing, if you give them the chance to show it.
    >>  ............................................
    pt  São gente boa, %1$s. Cada um tem algo que vale a pena conhecer, se você der a chance de mostrarem.
    >>  ............................................
  witty.dialogue.conversations.people.first/2
    en  I like them. Properly. They look out for each other here, and that's not nothing.
    >>  ............................................
    pt  Gosto deles. De verdade. Aqui um cuida do outro, e isso não é pouca coisa.
    >>  ............................................
```

</details>


**Outcome 13 of 13** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.topic.people` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.people.first
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.first.terminal`: the villager accepts. Subject `people.first`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.village` / button `people`** earlier in this file. Fill it in there, once.


### Button `rumors` — "Any rumors going around?"

Shown only when MCA's own constraints hold: `"adult"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.rumors` — accepted phrasings: "heard any rumors"; "any gossip"; "what are people saying"; "hear anything"; "heard anything"
  - the message must contain one of: `rumor`, `heard`, `saying`
  - scored words: `rumor`(1.6), `heard`(1.0), `saying`(1.5), `latest`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.village.rumors
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.rumors   [24 chars]
    en  Any rumors going around?
    >>  ............................................
    pt  Tem algum boato rolando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 9** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.rumors.one_i_will_not_pass_on"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.rumors.one_i_will_not_pass_on", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.rumors` (this player only) for 36000 ticks
- Then opens: `conversations.scene.rumors.one_i_will_not_pass_on.respond`
- …where the player's next choices will be: "Then let it end with you." | "Would it hurt anybody?" | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.one_i_will_not_pass_on
WHO    VILLAGER — what the player reads after pressing "Any rumors going around?"
       spoken on: conversations.cat.village, button `rumors`
       leaves the player on: conversations.scene.rumors.one_i_will_not_pass_on.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.one_i_will_not_pass_on.open`: the villager reports. Subject `rumors.withheld`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on/1   [120 chars]
    en  There is one doing the rounds that I have heard four times and have not repeated once, and I intend to keep that record.
    >>  ............................................
    pt  Tem um circulando que eu ouvi quatro vezes e não repeti nenhuma, e pretendo manter esse retrospecto.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on/2   [94 chars]
    en  Somebody is being talked about and the talk is thinner than the confidence people say it with.
    >>  ............................................
    pt  Estão falando de alguém, e a conversa é mais fina do que a confiança com que dizem.
    >>  ............................................
  dialogue.conversations.scene.rumors.one_i_will_not_pass_on/3   [114 chars]
    en  I know the rumour and I know who started it, and the second of those is the part that would actually interest you.
    >>  ............................................
    pt  Eu conheço o boato e sei quem começou, e a segunda coisa é a parte que de fato te interessaria.
    >>  ............................................
```


**Outcome 2 of 9** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.rumors.the_correction"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.rumors.the_correction", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.rumors` (this player only) for 36000 ticks
- Then opens: `conversations.scene.rumors.the_correction.respond`
- …where the player's next choices will be: "Did the correction reach everyone?" | "Admitting you repeated one is rare." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.rumors.the_correction
WHO    VILLAGER — what the player reads after pressing "Any rumors going around?"
       spoken on: conversations.cat.village, button `rumors`
       leaves the player on: conversations.scene.rumors.the_correction.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.the_correction.open`: the villager reports. Subject `rumors.corrected`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:rumors` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.rumors.the_correction/1   [122 chars]
    en  A thing everybody believed last winter turned out to be nothing, and about four people have corrected themselves out loud.
    >>  ............................................
    pt  Uma coisa em que todo mundo acreditou no inverno passado acabou não sendo nada, e umas quatro pessoas se corrigiram em voz alta.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction/2   [102 chars]
    en  The truth came out and it took an afternoon, and the rumour had taken three months to travel that far.
    >>  ............................................
    pt  A verdade apareceu e levou uma tarde, e o boato tinha levado três meses para chegar até ali.
    >>  ............................................
  dialogue.conversations.scene.rumors.the_correction/3   [145 chars]
    en  I repeated one once, years ago. It was untrue and I have been careful ever since, and the person it was about never knew I was one of the mouths.
    >>  ............................................
    pt  Eu repeti um, anos atrás. Era falso e desde então eu sou cuidadosa, e a pessoa de quem falavam nunca soube que eu fui uma das bocas.
    >>  ............................................
```


**Outcome 3 of 9** — base weight `0`

- Fires when: weighted +200 when arc `rumors` is at stage 1..2
- Fires when: RULED OUT when arc `rumors` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.rumors` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.rumors` (this player only) for 36000 ticks
- Then opens: `conversations.arc.rumors.resume.respond`
- …where the player's next choices will be: "I believe you." | "Then who?" | "Let it die, then." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.rumors.resume
WHO    VILLAGER — what the player reads after pressing "Any rumors going around?"
       spoken on: conversations.cat.village, button `rumors`
       leaves the player on: conversations.arc.rumors.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.resume.opener`: the villager reports. Subject `rumors.spreading`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, restraint
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.rumors.resume/1   [81 chars]
    en  That thing I said I'd keep. I've kept it, and it has been harder than I expected.
    >>  ............................................
    pt  Aquilo que eu disse que ia guardar. Guardei, e foi mais difícil do que eu esperava.
    >>  ............................................
  dialogue.conversations.rumors.resume/2   [93 chars]
    en  It got out. Not through me, and I'd like you to believe that, which is a thing I can't prove.
    >>  ............................................
    pt  Vazou. Não por mim, e eu queria que você acreditasse, o que não posso provar.
    >>  ............................................
  dialogue.conversations.rumors.resume/3   [75 chars]
    en  Nobody's mentioned it in three weeks. That's what a thing dying looks like.
    >>  ............................................
    pt  Ninguém mencionou em três semanas. É assim que uma coisa morrendo se parece.
    >>  ............................................
```


**Outcome 4 of 9** — base weight `0`

- Fires when: weighted +100 when an untold village event exists of type death/divorce
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `private` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.private.<event type>`
- Then opens: `conversations.topic.rumors.private.respond`
- …where the player's next choices will be: "Is it the kind of thing I should know?" | "Then I'll not ask further." | "Come on. Who was it about?" | "I'll say nothing about it."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 5 of 9** — base weight `0`

- Fires when: weighted +100 when an untold village event exists
- Fires when: RULED OUT when the relationship band is one of   _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `private` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.private.<event type>`
- Then opens: `conversations.topic.rumors.private.respond`
- …where the player's next choices will be: "Is it the kind of thing I should know?" | "Then I'll not ask further." | "Come on. Who was it about?" | "I'll say nothing about it."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 6 of 9** — base weight `0`

- Fires when: weighted +100 when an untold village event exists
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `rumor` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.topic.rumors.respond`
- …where the player's next choices will be: "Who told you that?" | "That doesn't sound reliable." | "Go on." | "I'd rather not know."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 7 of 9** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when an untold village event exists  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `rumors` branch `none` budget `standard`
- Then opens: `conversations.topic.rumors.none.respond`
- …where the player's next choices will be: "Good. A quiet week suits me." | "Nothing at all?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.rumors.none
WHO    VILLAGER — what the player reads after pressing "Any rumors going around?"
       spoken on: conversations.cat.village, button `rumors`
       leaves the player on: conversations.topic.rumors.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.none.to.rumors.none`: the villager accepts. Subject `rumors.none`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.rumors.none/1   [82 chars]
    en  Rumors? It's been dead quiet, %1$s. Not so much as a misplaced goat to talk about.
    >>  ............................................
    pt  Boatos? Anda um silêncio de morte, %1$s. Nem uma cabra desgarrada pra comentar.
    >>  ............................................
  dialogue.conversations.rumors.none/2   [94 chars]
    en  Nothing's going round just now. Give the village a week — there's always something eventually.
    >>  ............................................
    pt  Nada rolando por enquanto. Dá uma semana pro vilarejo — sempre acaba aparecendo alguma coisa.
    >>  ............................................
  dialogue.conversations.rumors.none/3   [87 chars]
    en  No fresh talk that I've heard. And I'd have heard it, believe me. These walls are thin.
    >>  ............................................
    pt  Nenhuma conversa nova que eu tenha ouvido. E eu teria ouvido, pode acreditar. Essas paredes são finas.
    >>  ............................................
```


**Outcome 8 of 9** — base weight `0`

- Fires when: weighted +100 when an untold village event exists
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.rumors` (this player only) permanently
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 9 of 9** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.rumors` (this player only) permanently
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.rumors.none
WHO    VILLAGER — what the player reads after pressing "Any rumors going around?"
       spoken on: conversations.cat.village, button `rumors`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `rumors.none.terminal`: the villager accepts. Subject `rumors.none`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.village` / button `rumors`** earlier in this file. Fill it in there, once.


### Button `standing` — "What do people think of me around here?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.standing` — accepted phrasings: "what do people think of me"; "what do people think of me around here"; "how am i regarded"; "what is my reputation"; "what is my standing"; "do people like me"; "what do they say about me"
  - the message must contain one of: `think`, `reputation`, `standing`, `opinion`, `regarded`
  - scored words: `think`(1.4), `reputation`(1.6), `standing`(1.6), `me`(1.0), `opinion`(1.2), `regarded`(1.0)

```text
POOL   dialogue key: dialogue.conversations.cat.village.standing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.standing   [39 chars]
    en  What do people think of me around here?
    >>  ............................................
    pt  O que as pessoas acham de mim por aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 15** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.standing.still_being_read"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.standing.still_being_read", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.scene.standing.still_being_read.respond`
- …where the player's next choices will be: "How does anyone earn it here?" | "Fair. I'd rather hear it straight." | "Thanks for being straight."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.still_being_read
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.scene.standing.still_being_read.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.still_being_read.open`: the villager reports. Subject `standing.early`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.still_being_read/1   [102 chars]
    en  Undecided, which is the honest answer and the one nobody wants. You have been here weeks, not seasons.
    >>  ............................................
    pt  Indefinido, que é a resposta honesta e a que ninguém quer. Você está aqui há semanas, não há estações.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read/2   [106 chars]
    en  The lane is watching and being polite while it watches. That is the standard arrangement for a first year.
    >>  ............................................
    pt  A viela está observando e sendo educada enquanto observa. É o arranjo padrão para um primeiro ano.
    >>  ............................................
  dialogue.conversations.scene.standing.still_being_read/3   [109 chars]
    en  Nobody has anything against you. Nobody has anything for you either, and the second one takes longer to earn.
    >>  ............................................
    pt  Ninguém tem nada contra você. Ninguém tem nada a seu favor também, e a segunda coisa leva mais tempo para conquistar.
    >>  ............................................
```


**Outcome 2 of 15** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.standing.settled_opinion"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.standing.settled_opinion", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.scene.standing.settled_opinion.respond`
- …where the player's next choices will be: "Which household, and why?" | "Thank you for telling me plainly." | "Thanks for being straight."

```text
POOL   dialogue key: dialogue.conversations.scene.standing.settled_opinion
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.scene.standing.settled_opinion.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.settled_opinion.open`: the villager reports. Subject `standing.established`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:standing` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.standing.settled_opinion/1   [123 chars]
    en  Settled, and settled in your favour, and I will tell you the one household where it is not, because you would want to know.
    >>  ............................................
    pt  Assentado, e a seu favor, e eu vou te dizer a única casa onde não está, porque você ia querer saber.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion/2   [123 chars]
    en  You are on the list of people somebody sends a child to fetch in an emergency. That is the only ranking this village keeps.
    >>  ............................................
    pt  Você está na lista de gente para quem se manda uma criança correndo numa emergência. É a única classificação que esta vila mantém.
    >>  ............................................
  dialogue.conversations.scene.standing.settled_opinion/3   [96 chars]
    en  Well thought of, and quietly. Nobody is going to say it to your face except me, right now, once.
    >>  ............................................
    pt  Bem visto, e em silêncio. Ninguém vai dizer na sua cara, exceto eu, agora, uma vez.
    >>  ............................................
```


**Outcome 3 of 15** — base weight `0`

- Fires when: weighted +200 when arc `standing` is at stage 1..2
- Fires when: RULED OUT when arc `standing` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.arc.standing.resume.respond`
- …where the player's next choices will be: "Who changed their mind?" | "Then I'll keep at it." | "I'm tired of being measured." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.standing.resume
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.arc.standing.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.resume.opener`: the villager reports. Subject `standing.mixed`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, practical_help
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.resume/1   [72 chars]
    en  You said you'd keep at it. I've watched, and I'm telling you what I saw.
    >>  ............................................
    pt  Você disse que ia insistir. Eu observei, e vou te dizer o que vi.
    >>  ............................................
  dialogue.conversations.standing.resume/2   [83 chars]
    en  The half that wasn't sure is smaller than it was. I count these things, apparently.
    >>  ............................................
    pt  A metade que não tinha certeza está menor. Aparentemente eu conto essas coisas.
    >>  ............................................
  dialogue.conversations.standing.resume/3   [72 chars]
    en  Nothing's moved. I said years and I meant years, and you're a season in.
    >>  ............................................
    pt  Nada mudou. Eu disse anos e falei sério, e você está numa estação.
    >>  ............................................
```


**Outcome 4 of 15** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.standing` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `again` budget `standard`
- Then opens: `conversations.topic.standing.again.respond`
- …where the player's next choices will be: "Tell me anyway." | "Sorry — you've told me." | "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.standing.again
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.again.open`: the villager deflects. Subject `standing.repeat`, polarity `neutral`, guarded, outcome `None`.
NOTE   this is the line that establishes `standing:asked_before` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.again/1   [66 chars]
    en  Asking again? Standing doesn't shift by the hour, %1$s. But go on.
    >>  ............................................
    pt  Perguntando de novo? Reputação não muda de hora em hora, %1$s. Mas vá lá.
    >>  ............................................
  dialogue.conversations.standing.again/2   [72 chars]
    en  Same as when you last asked, more or less. Reputations move slowly here.
    >>  ............................................
    pt  Igual à última vez que perguntou, mais ou menos. Reputação anda devagar por aqui.
    >>  ............................................
  dialogue.conversations.standing.again/3   [67 chars]
    en  Still taking the village's temperature, %1$s? All right, once more.
    >>  ............................................
    pt  Ainda medindo a temperatura da vila, %1$s? Tudo bem, mais uma vez.
    >>  ............................................
```


**Outcome 5 of 15** — base weight `5`

- Fires when: RULED OUT when `conversations_reputation` = {}  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `unknown` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.unknown.respond`
- …where the player's next choices will be: "Then how do you read me?" | "Good. I'd rather not be measured." | "Who would know, if anyone?" | "Then this was a waste of breath." | "Never mind, then."

```text
POOL   dialogue key: dialogue.conversations.standing.no_tally
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.unknown.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.open`: the villager qualifys. Subject `standing.unknown`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:no_tally` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, self_disclosure, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.no_tally/1   [69 chars]
    en  Nobody keeps a tally here, %1$s. There's no ledger to look you up in.
    >>  ............................................
    pt  Ninguém faz contagem aqui, %1$s. Não existe livro onde te procurar.
    >>  ............................................
  dialogue.conversations.standing.no_tally/2   [84 chars]
    en  I couldn't tell you how the village reads you. We've never been a place that counts.
    >>  ............................................
    pt  Eu não saberia dizer como o vilarejo te lê. Nunca fomos um lugar de contar.
    >>  ............................................
  dialogue.conversations.standing.no_tally/3   [89 chars]
    en  Standing? We haven't a word for it here. People have opinions and they keep them indoors.
    >>  ............................................
    pt  Reputação? Não temos palavra pra isso aqui. As pessoas têm opiniões e guardam em casa.
    >>  ............................................
```


**Outcome 6 of 15** — base weight `0`

- Fires when: weighted +70 when `conversations_reputation` = {"min_tier": "acquaintance"}
- Fires when: weighted +70 when `conversations_reputation_incident` = {"statuses": ["apologized", "resolved"], "known_to_speaker": true}
- Fires when: RULED OUT when `conversations_reputation_incident` = {"statuses": ["active"], "tags": ["crime"], "known_to_speaker": true}  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `mixed` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.mixed.respond`
- …where the player's next choices will be: "Which half are you in?" | "What are they still holding on to?" | "Then they can argue it out between them." | "Half of them can think whatever they like." | "That's enough for today."

```text
POOL   dialogue key: dialogue.conversations.standing.mixed
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.mixed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.mixed.open`: the villager reports. Subject `standing.mixed`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:mixed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, practical_help, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.mixed/1   [99 chars]
    en  You'll get two answers depending who you ask, %1$s. Half the village has let it go and half hasn't.
    >>  ............................................
    pt  Você vai ouvir duas respostas dependendo de quem perguntar, %1$s. Metade do vilarejo largou aquilo e metade não.
    >>  ............................................
  dialogue.conversations.standing.mixed/2   [90 chars]
    en  It isn't one thing. Some here think well of you and some are still carrying what happened.
    >>  ............................................
    pt  Não é uma coisa só. Alguns aqui pensam bem de você e alguns ainda carregam o que aconteceu.
    >>  ............................................
  dialogue.conversations.standing.mixed/3   [96 chars]
    en  Split, if I'm honest with you. You've done enough good that it gets argued about, which is rare.
    >>  ............................................
    pt  Dividido, se eu for sincero. Você fez bem o bastante pra virar discussão, o que é raro.
    >>  ............................................
```


**Outcome 7 of 15** — base weight `0`

- Fires when: weighted +120 when `conversations_reputation` = {"min_tier": "honored"}
- Fires when: RULED OUT when `conversations_reputation_incident` = {"statuses": ["active"], "tags": ["crime"], "known_to_speaker": true}  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `praise` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.praise.respond`
- …where the player's next choices will be: "Who said it?" | "What did they say, exactly?" | "That's an odd thing to hear about yourself." | "People will say anything." | "Right. Thank you."

```text
POOL   dialogue key: dialogue.conversations.standing.praise
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.praise.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.praise.open`: the villager reports. Subject `standing.praise`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:praised` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.standing.praise/1   [100 chars]
    en  Someone spoke well of you where you couldn't hear it, %1$s. I thought you ought to know it happened.
    >>  ............................................
    pt  Alguém falou bem de você onde você não podia ouvir, %1$s. Achei que devia saber que aconteceu.
    >>  ............................................
  dialogue.conversations.standing.praise/2   [69 chars]
    en  You came up in conversation this week, and not one unkind word in it.
    >>  ............................................
    pt  Você foi assunto esta semana, e não teve uma palavra rude sequer.
    >>  ............................................
  dialogue.conversations.standing.praise/3   [96 chars]
    en  I'll pass this on because nobody else will: people say good things about you when you're absent.
    >>  ............................................
    pt  Vou passar adiante porque ninguém mais vai: falam bem de você quando você não está por perto.
    >>  ............................................
```


**Outcome 8 of 15** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.child
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.child.terminal`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.standing.child/1   [79 chars]
    en  People talk about grown-up things like that. I just know if you're nice or not.
    >>  ............................................
    pt  Gente grande fala dessas coisas. Eu só sei se você é legal ou não.
    >>  ............................................
  dialogue.conversations.standing.child/2   [83 chars]
    en  Mama says it's rude to repeat what people say. But nobody's thrown anything at you!
    >>  ............................................
    pt  Mamãe diz que é feio repetir o que os outros falam. Mas ninguém jogou nada em você!
    >>  ............................................
```


**Outcome 9 of 15** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.teen
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.teen.terminal`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.standing.teen/1   [90 chars]
    en  You're asking me? I mostly hear what my parents say, and they'd want me to stay out of it.
    >>  ............................................
    pt  Está perguntando pra mim? Eu ouço o que meus pais dizem, e eles iam querer que eu ficasse fora disso.
    >>  ............................................
  dialogue.conversations.standing.teen/2   [68 chars]
    en  People talk, sure. Ask one of the grown-ups if you want it straight.
    >>  ............................................
    pt  O povo fala, claro. Pergunte a um adulto se quiser a resposta sem rodeios.
    >>  ............................................
```


**Outcome 10 of 15** — base weight `0`

- Fires when: weighted +150 when `conversations_reputation_incident` = {"statuses": ["active"], "tags": ["crime"], "known_to_speaker": true}
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `trouble` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.incident.respond`
- …where the player's next choices will be: "What is it they haven't let go of?" | "How could I make things right?" | "It isn't what they say it is." | "Their opinions are their problem." | "Enough about me."

```text
POOL   dialogue key: dialogue.conversations.standing.trouble
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.incident.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.incident.open`: the villager disclose_problems. Subject `standing.incident`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:bad`, `incident:active` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, respectful_disagreement, dismissal, exit
```

```text
  dialogue.conversations.standing.trouble/1   [106 chars]
    en  You want it honest, %1$s? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer a verdade, %1$s? Há um assunto que o povo não deixou pra lá. Ele fica entre você e esta vila.
    >>  ............................................
  dialogue.conversations.standing.trouble/2   [104 chars]
    en  Folk remember what happened. Nobody's said 'settled' yet, and until someone does, that's your name here.
    >>  ............................................
    pt  O povo lembra do que houve. Ninguém deu por encerrado ainda, e até alguém dar, esse é o seu nome por aqui.
    >>  ............................................
  dialogue.conversations.standing.trouble/3   [106 chars]
    en  There's talk, %1$s, and not the kind that fades on its own. Something's still owed, the way people see it.
    >>  ............................................
    pt  Há conversas, %1$s, e não do tipo que morre sozinho. Algo ainda é devido, do jeito que o povo vê.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of, %1$s. I'm sorry to be the one.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram, %1$s. Desculpe ser eu.
    >>  ............................................
  anxious.dialogue.conversations.standing.trouble/2
    en  Honestly — it's still being said, and I've not enjoyed knowing it and not telling you.
    >>  ............................................
    pt  Honestamente — ainda está sendo dito, e eu não gostei de saber e não te contar.
    >>  ............................................
  anxious.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'd rather you heard it kindly than overheard it unkindly.
    >>  ............................................
    pt  Tem um assunto. Prefiro que você ouça com gentileza a escutar sem querer com maldade.
    >>  ............................................
  athletic.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. They will, in time. They always do.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Vão largar, com o tempo. Sempre largam.
    >>  ............................................
  athletic.dialogue.conversations.standing.trouble/2
    en  Honestly, it's still about. Villages hold things a good while and then set them down.
    >>  ............................................
    pt  Honestamente, ainda está por aí. Vilarejos seguram as coisas um bom tempo e depois largam.
    >>  ............................................
  athletic.dialogue.conversations.standing.trouble/3
    en  There's a matter. It'll wear out. Most of them do, given a few seasons.
    >>  ............................................
    pt  Tem um assunto. Vai se gastar. Quase todos se gastam, com algumas estações.
    >>  ............................................
  confident.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  confident.dialogue.conversations.standing.trouble/2
    en  Honestly: something you did is still being discussed, and not kindly.
    >>  ............................................
    pt  Honestamente: algo que você fez ainda é discutido, e não com carinho.
    >>  ............................................
  confident.dialogue.conversations.standing.trouble/3
    en  There's a matter. It hasn't been dropped and I'll not pretend it has.
    >>  ............................................
    pt  Tem um assunto. Não foi largado e eu não vou fingir que foi.
    >>  ............................................
  crabby.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  crabby.dialogue.conversations.standing.trouble/2
    en  Honestly: something you did is still being discussed, and not kindly.
    >>  ............................................
    pt  Honestamente: algo que você fez ainda é discutido, e não com carinho.
    >>  ............................................
  crabby.dialogue.conversations.standing.trouble/3
    en  There's a matter. It hasn't been dropped and I'll not pretend it has.
    >>  ............................................
    pt  Tem um assunto. Não foi largado e eu não vou fingir que foi.
    >>  ............................................
  extroverted.dialogue.conversations.standing.trouble/1
    en  You want it honest, %1$s? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto, %1$s? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  extroverted.dialogue.conversations.standing.trouble/2
    en  Honestly — something's still being talked about, and I'd rather you heard it from me.
    >>  ............................................
    pt  Honestamente — algo ainda está sendo falado, e eu prefiro que você ouça de mim.
    >>  ............................................
  extroverted.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'm telling you because I'd want telling, and nobody else will.
    >>  ............................................
    pt  Tem um assunto. Estou te contando porque eu ia querer ser avisado, e mais ninguém vai.
    >>  ............................................
  flirty.dialogue.conversations.standing.trouble/1
    en  You want it honest, %1$s? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto, %1$s? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  flirty.dialogue.conversations.standing.trouble/2
    en  Honestly — something's still being talked about, and I'd rather you heard it from me.
    >>  ............................................
    pt  Honestamente — algo ainda está sendo falado, e eu prefiro que você ouça de mim.
    >>  ............................................
  flirty.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'm telling you because I'd want telling, and nobody else will.
    >>  ............................................
    pt  Tem um assunto. Estou te contando porque eu ia querer ser avisado, e mais ninguém vai.
    >>  ............................................
  friendly.dialogue.conversations.standing.trouble/1
    en  You want it honest, %1$s? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto, %1$s? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  friendly.dialogue.conversations.standing.trouble/2
    en  Honestly — something's still being talked about, and I'd rather you heard it from me.
    >>  ............................................
    pt  Honestamente — algo ainda está sendo falado, e eu prefiro que você ouça de mim.
    >>  ............................................
  friendly.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'm telling you because I'd want telling, and nobody else will.
    >>  ............................................
    pt  Tem um assunto. Estou te contando porque eu ia querer ser avisado, e mais ninguém vai.
    >>  ............................................
  gloomy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of, %1$s. I'm sorry to be the one.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram, %1$s. Desculpe ser eu.
    >>  ............................................
  gloomy.dialogue.conversations.standing.trouble/2
    en  Honestly — it's still being said, and I've not enjoyed knowing it and not telling you.
    >>  ............................................
    pt  Honestamente — ainda está sendo dito, e eu não gostei de saber e não te contar.
    >>  ............................................
  gloomy.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'd rather you heard it kindly than overheard it unkindly.
    >>  ............................................
    pt  Tem um assunto. Prefiro que você ouça com gentileza a escutar sem querer com maldade.
    >>  ............................................
  greedy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  greedy.dialogue.conversations.standing.trouble/2
    en  Honestly: something you did is still being discussed, and not kindly.
    >>  ............................................
    pt  Honestamente: algo que você fez ainda é discutido, e não com carinho.
    >>  ............................................
  greedy.dialogue.conversations.standing.trouble/3
    en  There's a matter. It hasn't been dropped and I'll not pretend it has.
    >>  ............................................
    pt  Tem um assunto. Não foi largado e eu não vou fingir que foi.
    >>  ............................................
  grumpy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this village.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este vilarejo.
    >>  ............................................
  grumpy.dialogue.conversations.standing.trouble/2
    en  Honestly: something you did is still being discussed, and not kindly.
    >>  ............................................
    pt  Honestamente: algo que você fez ainda é discutido, e não com carinho.
    >>  ............................................
  grumpy.dialogue.conversations.standing.trouble/3
    en  There's a matter. It hasn't been dropped and I'll not pretend it has.
    >>  ............................................
    pt  Tem um assunto. Não foi largado e eu não vou fingir que foi.
    >>  ............................................
  introverted.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram.
    >>  ............................................
  introverted.dialogue.conversations.standing.trouble/2
    en  Something's still being said. It sits between you and here.
    >>  ............................................
    pt  Algo ainda está sendo dito. Está entre você e aqui.
    >>  ............................................
  introverted.dialogue.conversations.standing.trouble/3
    en  There's a matter. That's as much as I'll put into words.
    >>  ............................................
    pt  Tem um assunto. É tudo que eu ponho em palavras.
    >>  ............................................
  lazy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. They will, in time. They always do.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Vão largar, com o tempo. Sempre largam.
    >>  ............................................
  lazy.dialogue.conversations.standing.trouble/2
    en  Honestly, it's still about. Villages hold things a good while and then set them down.
    >>  ............................................
    pt  Honestamente, ainda está por aí. Vilarejos seguram as coisas um bom tempo e depois largam.
    >>  ............................................
  lazy.dialogue.conversations.standing.trouble/3
    en  There's a matter. It'll wear out. Most of them do, given a few seasons.
    >>  ............................................
    pt  Tem um assunto. Vai se gastar. Quase todos se gastam, com algumas estações.
    >>  ............................................
  odd.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram.
    >>  ............................................
  odd.dialogue.conversations.standing.trouble/2
    en  Something's still being said. It sits between you and here.
    >>  ............................................
    pt  Algo ainda está sendo dito. Está entre você e aqui.
    >>  ............................................
  odd.dialogue.conversations.standing.trouble/3
    en  There's a matter. That's as much as I'll put into words.
    >>  ............................................
    pt  Tem um assunto. É tudo que eu ponho em palavras.
    >>  ............................................
  peaceful.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. They will, in time. They always do.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Vão largar, com o tempo. Sempre largam.
    >>  ............................................
  peaceful.dialogue.conversations.standing.trouble/2
    en  Honestly, it's still about. Villages hold things a good while and then set them down.
    >>  ............................................
    pt  Honestamente, ainda está por aí. Vilarejos seguram as coisas um bom tempo e depois largam.
    >>  ............................................
  peaceful.dialogue.conversations.standing.trouble/3
    en  There's a matter. It'll wear out. Most of them do, given a few seasons.
    >>  ............................................
    pt  Tem um assunto. Vai se gastar. Quase todos se gastam, com algumas estações.
    >>  ............................................
  peppy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this place.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este lugar.
    >>  ............................................
  peppy.dialogue.conversations.standing.trouble/2
    en  Honestly? Something's still doing the rounds, and it isn't a flattering something.
    >>  ............................................
    pt  Honestamente? Algo ainda está circulando, e não é um algo lisonjeiro.
    >>  ............................................
  peppy.dialogue.conversations.standing.trouble/3
    en  There's a matter. Nobody's dropped it. I'd rather tell you than let you find out at the inn.
    >>  ............................................
    pt  Tem um assunto. Ninguém largou. Prefiro te contar a você descobrir na estalagem.
    >>  ............................................
  playful.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this place.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este lugar.
    >>  ............................................
  playful.dialogue.conversations.standing.trouble/2
    en  Honestly? Something's still doing the rounds, and it isn't a flattering something.
    >>  ............................................
    pt  Honestamente? Algo ainda está circulando, e não é um algo lisonjeiro.
    >>  ............................................
  playful.dialogue.conversations.standing.trouble/3
    en  There's a matter. Nobody's dropped it. I'd rather tell you than let you find out at the inn.
    >>  ............................................
    pt  Tem um assunto. Ninguém largou. Prefiro te contar a você descobrir na estalagem.
    >>  ............................................
  relaxed.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. They will, in time. They always do.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Vão largar, com o tempo. Sempre largam.
    >>  ............................................
  relaxed.dialogue.conversations.standing.trouble/2
    en  Honestly, it's still about. Villages hold things a good while and then set them down.
    >>  ............................................
    pt  Honestamente, ainda está por aí. Vilarejos seguram as coisas um bom tempo e depois largam.
    >>  ............................................
  relaxed.dialogue.conversations.standing.trouble/3
    en  There's a matter. It'll wear out. Most of them do, given a few seasons.
    >>  ............................................
    pt  Tem um assunto. Vai se gastar. Quase todos se gastam, com algumas estações.
    >>  ............................................
  sensitive.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of, %1$s. I'm sorry to be the one.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram, %1$s. Desculpe ser eu.
    >>  ............................................
  sensitive.dialogue.conversations.standing.trouble/2
    en  Honestly — it's still being said, and I've not enjoyed knowing it and not telling you.
    >>  ............................................
    pt  Honestamente — ainda está sendo dito, e eu não gostei de saber e não te contar.
    >>  ............................................
  sensitive.dialogue.conversations.standing.trouble/3
    en  There's a matter. I'd rather you heard it kindly than overheard it unkindly.
    >>  ............................................
    pt  Tem um assunto. Prefiro que você ouça com gentileza a escutar sem querer com maldade.
    >>  ............................................
  shy.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram.
    >>  ............................................
  shy.dialogue.conversations.standing.trouble/2
    en  Something's still being said. It sits between you and here.
    >>  ............................................
    pt  Algo ainda está sendo dito. Está entre você e aqui.
    >>  ............................................
  shy.dialogue.conversations.standing.trouble/3
    en  There's a matter. That's as much as I'll put into words.
    >>  ............................................
    pt  Tem um assunto. É tudo que eu ponho em palavras.
    >>  ............................................
  upbeat.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this place.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este lugar.
    >>  ............................................
  upbeat.dialogue.conversations.standing.trouble/2
    en  Honestly? Something's still doing the rounds, and it isn't a flattering something.
    >>  ............................................
    pt  Honestamente? Algo ainda está circulando, e não é um algo lisonjeiro.
    >>  ............................................
  upbeat.dialogue.conversations.standing.trouble/3
    en  There's a matter. Nobody's dropped it. I'd rather tell you than let you find out at the inn.
    >>  ............................................
    pt  Tem um assunto. Ninguém largou. Prefiro te contar a você descobrir na estalagem.
    >>  ............................................
  witty.dialogue.conversations.standing.trouble/1
    en  You want it honest? There's a matter people haven't let go of. It sits between you and this place.
    >>  ............................................
    pt  Quer honesto? Tem um assunto que as pessoas não largaram. Está entre você e este lugar.
    >>  ............................................
  witty.dialogue.conversations.standing.trouble/2
    en  Honestly? Something's still doing the rounds, and it isn't a flattering something.
    >>  ............................................
    pt  Honestamente? Algo ainda está circulando, e não é um algo lisonjeiro.
    >>  ............................................
  witty.dialogue.conversations.standing.trouble/3
    en  There's a matter. Nobody's dropped it. I'd rather tell you than let you find out at the inn.
    >>  ............................................
    pt  Tem um assunto. Ninguém largou. Prefiro te contar a você descobrir na estalagem.
    >>  ............................................
```

</details>


**Outcome 11 of 15** — base weight `0`

- Fires when: weighted +100 when `conversations_reputation` = {"max_tier": "wary"}
- Fires when: RULED OUT when `conversations_reputation_incident` = {"statuses": ["active"], "tags": ["crime"], "known_to_speaker": true}  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `bad` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.bad.respond`
- …where the player's next choices will be: "What have they heard, exactly?" | "What would mend it?" | "That's not who I am." | "Their opinions are their problem." | "Enough about me."

```text
POOL   dialogue key: dialogue.conversations.standing.bad
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.bad.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_tier
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.bad.open`: the villager reports. Subject `standing.bad`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:bad` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, respectful_disagreement, dismissal, exit
```

```text
  dialogue.conversations.standing.bad/1   [101 chars]
    en  You'd hear it kindlier from me than most: %2$s is the word for you here, and it wasn't given lightly.
    >>  ............................................
    pt  De mim você ouve com mais gentileza que da maioria: %2$s é a palavra pra você aqui, e não foi dada à toa.
    >>  ............................................
  dialogue.conversations.standing.bad/2   [97 chars]
    en  Honestly? Doors don't open easily for you, %1$s. %2$s, they say. That takes mending, not waiting.
    >>  ............................................
    pt  Sinceramente? As portas não se abrem fácil pra você, %1$s. %2$s, dizem. Isso se conserta agindo, não esperando.
    >>  ............................................
  dialogue.conversations.standing.bad/3   [81 chars]
    en  People keep their distance, and you know why. %2$s — that's how you're spoken of.
    >>  ............................................
    pt  O povo mantém distância, e você sabe por quê. %2$s — é assim que falam de você.
    >>  ............................................
```


**Outcome 12 of 15** — base weight `0`

- Fires when: weighted +100 when `conversations_reputation` = {"min_tier": "acquaintance"}
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `good` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.good.respond`
- …where the player's next choices will be: "What have they heard, exactly?" | "What keeps it that way?" | "I'd not have said so myself." | "Their opinions are their problem." | "Enough about me."

```text
POOL   dialogue key: dialogue.conversations.standing.good
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.good.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = reputation_tier
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.good.open`: the villager reports. Subject `standing.good`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:good` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, self_disclosure, dismissal, exit
```

```text
  dialogue.conversations.standing.good/1   [71 chars]
    en  You're well thought of, %1$s. %2$s, people say, and they say it warmly.
    >>  ............................................
    pt  Você é bem visto, %1$s. %2$s, dizem, e dizem com carinho.
    >>  ............................................
  dialogue.conversations.standing.good/2   [64 chars]
    en  Around here? %2$s. You've earned that — folk notice what you do.
    >>  ............................................
    pt  Por aqui? %2$s. Você mereceu — o povo repara no que você faz.
    >>  ............................................
  dialogue.conversations.standing.good/3   [64 chars]
    en  Good things, mostly. %2$s is how they put it, and nobody argues.
    >>  ............................................
    pt  Coisas boas, na maior parte. %2$s é como dizem, e ninguém discute.
    >>  ............................................
```


**Outcome 13 of 15** — base weight `0`

- Fires when: weighted +100 when `conversations_reputation` = {}
- Fires when: RULED OUT when `conversations_reputation` = {"min_tier": "acquaintance"}  _(chance -2000)_
- Fires when: RULED OUT when `conversations_reputation` = {"max_tier": "wary"}  _(chance -2000)_
- Fires when: RULED OUT when `conversations_reputation_incident` = {"statuses": ["active"], "tags": ["crime"], "known_to_speaker": true}  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.standing` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `standing` branch `neutral` budget `standard`
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.topic.standing.neutral.respond`
- …where the player's next choices will be: "What would give them a reason?" | "What do you make of me, then?" | "Being no one in particular suits me." | "Their opinions are their problem." | "Enough about me."

```text
POOL   dialogue key: dialogue.conversations.standing.neutral
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.topic.standing.neutral.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.neutral.open`: the villager reports. Subject `standing.neutral`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `standing:unknown` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, restraint, dismissal, exit
```

```text
  dialogue.conversations.standing.neutral/1   [80 chars]
    en  Truthfully? Most people don't know you well enough to have an opinion yet, %1$s.
    >>  ............................................
    pt  Verdade? A maioria ainda não te conhece o bastante pra ter opinião, %1$s.
    >>  ............................................
  dialogue.conversations.standing.neutral/2   [73 chars]
    en  You're a face people are still placing. Neither warm nor cold — just new.
    >>  ............................................
    pt  Você é um rosto que o povo ainda está situando. Nem quente, nem frio — só novo.
    >>  ............................................
  dialogue.conversations.standing.neutral/3   [73 chars]
    en  Nothing bad, nothing much at all yet. Give it time and give them reasons.
    >>  ............................................
    pt  Nada de ruim, nada de muito ainda. Dê tempo e dê motivos.
    >>  ............................................
```


**Outcome 14 of 15** — base weight `1`

- Fires when: RULED OUT when `conversations_reputation` = {}  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.terminal`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.standing.unknown/1   [53 chars]
    en  Couldn't tell you, honestly. I keep to my own fences.
    >>  ............................................
    pt  Não saberia dizer, sinceramente. Cuido das minhas cercas.
    >>  ............................................
  dialogue.conversations.standing.unknown/2   [66 chars]
    en  You'd have to ask around, %1$s. I don't keep score of such things.
    >>  ............................................
    pt  Teria que perguntar por aí, %1$s. Não guardo conta dessas coisas.
    >>  ............................................
  dialogue.conversations.standing.unknown/3   [51 chars]
    en  People say all sorts. I've learned not to carry it.
    >>  ............................................
    pt  O povo fala de tudo. Aprendi a não carregar.
    >>  ............................................
```


**Outcome 15 of 15** — base weight `0`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +1000 when the `branching` feature is OFF
- Does: remembers `mcaconversations.cooldown.standing` (this player only) for 36000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.standing.unknown
WHO    VILLAGER — what the player reads after pressing "What do people think of me around here?"
       spoken on: conversations.cat.village, button `standing`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `standing.unknown.terminal`: the villager accepts. Subject `standing.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.village` / button `standing`** earlier in this file. Fill it in there, once.


### Button `neighbour` — "Is there anyone on your mind?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour` — accepted phrasings: "is there anyone on your mind"; "anyone on your mind"; "is somebody on your mind"
  - the message must contain one of: `anyone`, `somebody`, `mind`
  - scored words: `anyone`(1.4), `mind`(1.3), `somebody`(1.0)

```text
POOL   dialogue key: dialogue.conversations.cat.village.neighbour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.neighbour   [29 chars]
    en  Is there anyone on your mind?
    >>  ............................................
    pt  Tem alguém na sua cabeça?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.neighbour.two_doors_down"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.neighbour.two_doors_down", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `neighbour` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.neighbour` (this player only) for 36000 ticks
- Then opens: `conversations.scene.neighbour.two_doors_down.respond`
- …where the player's next choices will be: "Keep refusing to take a side." | "What's it really about?" | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down
WHO    VILLAGER — what the player reads after pressing "Is there anyone on your mind?"
       spoken on: conversations.cat.village, button `neighbour`
       leaves the player on: conversations.scene.neighbour.two_doors_down.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.two_doors_down.open`: the villager reports. Subject `neighbour.dispute`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down/1   [106 chars]
    en  Two of them have stopped speaking over something neither will name, and the lane has quietly picked sides.
    >>  ............................................
    pt  Dois deles pararam de se falar por causa de algo que nenhum quer nomear, e a viela silenciosamente tomou partido.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down/2   [113 chars]
    en  It started over a fence and it is not about the fence, and everybody knows that and everybody keeps saying fence.
    >>  ............................................
    pt  Começou por uma cerca e não é sobre a cerca, e todo mundo sabe disso e todo mundo continua dizendo cerca.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down/3   [111 chars]
    en  I am being asked to agree with both of them separately, which is a request I have refused four times this week.
    >>  ............................................
    pt  Estão me pedindo para concordar com os dois em separado, um pedido que eu recusei quatro vezes esta semana.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.neighbour.the_unthanked_kindness"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.neighbour.the_unthanked_kindness", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `neighbour` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.neighbour` (this player only) for 36000 ticks
- Then opens: `conversations.scene.neighbour.the_unthanked_kindness.respond`
- …where the player's next choices will be: "Do you know who it was?" | "That's what a village is for." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness
WHO    VILLAGER — what the player reads after pressing "Is there anyone on your mind?"
       spoken on: conversations.cat.village, button `neighbour`
       leaves the player on: conversations.scene.neighbour.the_unthanked_kindness.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.the_unthanked_kindness.open`: the villager reports. Subject `neighbour.small_good`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness/1   [114 chars]
    en  Somebody has been leaving firewood at a door and has told nobody, and I have decided to let them get away with it.
    >>  ............................................
    pt  Alguém vem deixando lenha numa porta e não contou a ninguém, e eu decidi deixar essa pessoa escapar com isso.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness/2   [134 chars]
    en  A neighbour walked a mile in the rain to tell me a thing that could have waited until Thursday, and it could not, and they were right.
    >>  ............................................
    pt  Um vizinho caminhou um quilômetro na chuva para me contar uma coisa que podia esperar até quinta, e não podia, e ele tinha razão.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness/3   [106 chars]
    en  The house at the end got its roof mended by four people in an afternoon and nobody has mentioned it since.
    >>  ............................................
    pt  A casa do fim teve o telhado consertado por quatro pessoas numa tarde e ninguém comentou desde então.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +200 when arc `neighbour` is at stage 1..2
- Fires when: RULED OUT when arc `neighbour` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.neighbour` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `neighbour` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.neighbour` (this player only) for 36000 ticks
- Then opens: `conversations.arc.neighbour.resume.respond`
- …where the player's next choices will be: "Best they never know it was me." | "How are they, in themselves?" | "I'll keep looking in on them." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.neighbour.resume
WHO    VILLAGER — what the player reads after pressing "Is there anyone on your mind?"
       spoken on: conversations.cat.village, button `neighbour`
       leaves the player on: conversations.arc.neighbour.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.opener`: the villager reports. Subject `neighbour.needs`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit, practical_help, restraint
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume/1   [78 chars]
    en  They mentioned somebody had been. They didn't say who and they didn't have to.
    >>  ............................................
    pt  Comentaram que alguém tinha ido. Não disseram quem e nem precisavam.
    >>  ............................................
  dialogue.conversations.neighbour.resume/2   [76 chars]
    en  Nobody's been. I told them somebody would, which was my error and not yours.
    >>  ............................................
    pt  Ninguém foi. Eu disse a eles que alguém iria, e o erro foi meu e não seu.
    >>  ............................................
  dialogue.conversations.neighbour.resume/3   [76 chars]
    en  It was left by the door with no name on it. That's the correct way to do it.
    >>  ............................................
    pt  Foi deixado na porta sem nome. É o jeito certo de fazer.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when an untold village event exists
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.neighbour` (this player only)  _(chance -1000)_
- Does: session `begin` topic `neighbour` branch `someone` budget `standard`
- Does: remembers `mcaconversations.cooldown.neighbour` (this player only) for 24000 ticks
- Does: tells the next untold village event, from the pool `dialogue.conversations.neighbour.<event type>`
- Then opens: `conversations.topic.neighbour.respond`
- …where the player's next choices will be: "What are they like, really?" | "They've always been decent to me." | "That's theirs to tell, not yours." | "Go on — what else do you know?" | "I'll not pry."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 5 of 6** — base weight `1`

- Fires when: RULED OUT when an untold village event exists  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `neighbour` branch `none` budget `standard`
- Then opens: `conversations.topic.neighbour.none.respond`
- …where the player's next choices will be: "Nobody at all?" | "That sounds like a good week." | "Someone must have done something." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.neighbour.none
WHO    VILLAGER — what the player reads after pressing "Is there anyone on your mind?"
       spoken on: conversations.cat.village, button `neighbour`
       leaves the player on: conversations.topic.neighbour.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.none.to.neighbour.none`: the villager accepts. Subject `neighbour.none`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.neighbour.none/1   [76 chars]
    en  Nobody in particular. It's been a settled sort of week and I'll not jinx it.
    >>  ............................................
    pt  Ninguém em especial. Foi uma semana calma e eu não vou dar azar.
    >>  ............................................
  dialogue.conversations.neighbour.none/2   [68 chars]
    en  Nobody's on my mind just now. That's usually a good sign round here.
    >>  ............................................
    pt  Ninguém na minha cabeça agora. Por aqui isso costuma ser bom sinal.
    >>  ............................................
  dialogue.conversations.neighbour.none/3   [54 chars]
    en  No one springs to mind, %1$s. Ask me after market day.
    >>  ............................................
    pt  Não me vem ninguém, %1$s. Me pergunte depois do dia de feira.
    >>  ............................................
```


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.none
WHO    VILLAGER — what the player reads after pressing "Is there anyone on your mind?"
       spoken on: conversations.cat.village, button `neighbour`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.none.terminal`: the villager accepts. Subject `neighbour.none`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.village` / button `neighbour`** earlier in this file. Fill it in there, once.


### Button `place` — "Anywhere here you're fond of?"

Shown only when MCA's own constraints hold: `"!toddler,!child"`

```text
POOL   dialogue key: dialogue.conversations.cat.village.place
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.place   [29 chars]
    en  Anywhere here you're fond of?
    >>  ............................................
    pt  Tem algum lugar daqui de que você goste?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.place.the_village_at_this_hour"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.place.the_village_at_this_hour", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `place` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.place` (this player only) for 36000 ticks
- Then opens: `conversations.scene.place.the_village_at_this_hour.respond`
- …where the player's next choices will be: "Where's the best view of it?" | "It's worth stopping for." | "I'll go and look."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour
WHO    VILLAGER — what the player reads after pressing "Anywhere here you're fond of?"
       spoken on: conversations.cat.village, button `place`
       leaves the player on: conversations.scene.place.the_village_at_this_hour.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_village_at_this_hour.open`: the villager reports. Subject `place.at_this_hour`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour/1   [94 chars]
    en  This is the hour the village is at its best and there is almost never anybody about to see it.
    >>  ............................................
    pt  Esta é a hora em que a vila fica no melhor dela e quase nunca tem alguém por perto para ver.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour/2   [95 chars]
    en  Look at it now. In daylight it is a place of work and at this hour it is somewhere people live.
    >>  ............................................
    pt  Olhe agora. De dia é um lugar de trabalho e a esta hora é um lugar onde gente mora.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour/3   [95 chars]
    en  Half an hour from now the last shutter goes and the whole village belongs to about three of us.
    >>  ............................................
    pt  Daqui a meia hora fecha a última janela e a vila inteira pertence a umas três pessoas.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.place.the_place_this_season"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.place.the_place_this_season", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `place` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.place` (this player only) for 36000 ticks
- Then opens: `conversations.scene.place.the_place_this_season.respond`
- …where the player's next choices will be: "Which season shows it truly?" | "I'll be here for that one too." | "I'll go and look."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season
WHO    VILLAGER — what the player reads after pressing "Anywhere here you're fond of?"
       spoken on: conversations.cat.village, button `place`
       leaves the player on: conversations.scene.place.the_place_this_season.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_place_this_season.open`: the villager reports. Subject `place.this_season`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_place_this_season/1   [103 chars]
    en  The village changes shape this time of year. Ground that is a path in winter is somebody's crop by now.
    >>  ............................................
    pt  A vila muda de formato nesta época. O que é caminho no inverno já é plantação de alguém agora.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season/2   [106 chars]
    en  This is the season everybody remembers the village by, and it is not the season that tells you what it is.
    >>  ............................................
    pt  Esta é a estação pela qual todo mundo lembra da vila, e não é a estação que te conta o que ela é.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season/3   [121 chars]
    en  Everything is green and open and the place feels twice the size, and in four months I shall have forgotten this entirely.
    >>  ............................................
    pt  Tudo verde e aberto e o lugar parece o dobro do tamanho, e em quatro meses eu vou ter esquecido tudo isso.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.place.the_village_at_this_hour"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.place.the_place_this_season"}  _(chance -5000)_
- Does: session `begin` topic `place` branch `funnel` budget `quick`
- Then opens: `conversations.topic.place.open.respond`
- …where the player's next choices will be: "Where is this corner?" | "Every village needs one of those." | "It's a patch of dirt." | "I'll go and look."

```text
POOL   dialogue key: dialogue.conversations.place.open
WHO    VILLAGER — what the player reads after pressing "Anywhere here you're fond of?"
       spoken on: conversations.cat.village, button `place`
       leaves the player on: conversations.topic.place.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.open`: the villager reports. Subject `place.the_fond_one`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.open/1   [92 chars]
    en  There is one corner of this village I would defend in an argument, and it is not a building.
    >>  ............................................
    pt  Existe um canto desta vila que eu defenderia numa discussão, e não é um prédio.
    >>  ............................................
  dialogue.conversations.place.open/2   [104 chars]
    en  Everybody assumes I would name the square. It is a patch of ground behind it that nobody has ever swept.
    >>  ............................................
    pt  Todo mundo supõe que eu diria a praça. É um pedaço de chão atrás dela que ninguém nunca varreu.
    >>  ............................................
  dialogue.conversations.place.open/3   [91 chars]
    en  One place here has never once disappointed me, and I go and stand in it about twice a week.
    >>  ............................................
    pt  Um lugar daqui nunca me decepcionou nenhuma vez, e eu vou ficar de pé nele umas duas vezes por semana.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.place.legacy
WHO    VILLAGER — what the player reads after pressing "Anywhere here you're fond of?"
       spoken on: conversations.cat.village, button `place`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.legacy`: the villager reports. Subject `place.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.place.legacy/1   [96 chars]
    en  There is a spot at the edge of the village I am fond of. Nothing to see. It is just quiet there.
    >>  ............................................
    pt  Tem um ponto na beira da vila de que eu gosto. Nada para ver. É só tranquilo lá.
    >>  ............................................
  dialogue.conversations.place.legacy/2   [101 chars]
    en  The old wall, past the last house. I go there when I want the village to be a view rather than a job.
    >>  ............................................
    pt  O muro velho, depois da última casa. Vou lá quando quero que a vila seja paisagem em vez de trabalho.
    >>  ............................................
  dialogue.conversations.place.legacy/3   [92 chars]
    en  Anywhere I can see the whole place at once. It makes the small troubles look the right size.
    >>  ............................................
    pt  Qualquer lugar de onde eu veja tudo de uma vez. Faz os problemas pequenos parecerem do tamanho certo.
    >>  ............................................
```


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.village.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.back   [15 chars]
    en  Something else.
    >>  ............................................
    pt  Outra coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations`
- …where the player's next choices will be: "Just making conversation." | "About your work..." | "About the village..." | "Heard any news?" | "Something more personal." | "About us. About family." | "Goo? Ga-goo!" | "Never mind."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---


## `greet`


```text
POOL   dialogue key: dialogue.greet
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: greet
ARGS   %1$s = the player's name
SIZE   0 lines in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

> **No English line ships under this key.** Write one, and its `pt_br` twin.

```text
  dialogue.greet
    en  (missing)
    >>  ............................................
    pt  (missing)
    >>  ............................................
```


### Button `checkin` — "How have you been, really?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `greeting.checkin` — accepted phrasings: "how have you been"; "have you been well"; "how are you holding up"; "how have things been"; "how are you"
  - the message must contain one of: `how`, `lately`, `holding`
  - scored words: `how`(0.8), `lately`(1.0), `holding`(1.0)

```text
POOL   dialogue key: dialogue.greet.checkin
WHO    PLAYER — the words printed on the button the player presses
       on the node: greet
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.greet.checkin   [26 chars]
    en  How have you been, really?
    >>  ............................................
    pt  Como você tem passado, de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 8** — base weight `0`

- Fires when: weighted +100 when `time_min` = 13000
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `late` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.late.respond`
- …where the player's next choices will be: "What's keeping you up?" | "Go and sleep. It'll keep." | "I'll sit up with you a while." | "Goodnight, then."

```text
POOL   dialogue key: dialogue.conversations.checkin.late
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.late.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.late.to.checkin.late`: the villager accepts. Subject `checkin.late`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.late/1   [71 chars]
    en  Late to be about. I'm no better — I've been up since the fire went low.
    >>  ............................................
    pt  Tarde para estar de pé. Não que eu seja melhor — estou acordado desde que o fogo baixou.
    >>  ............................................
  dialogue.conversations.checkin.late/2   [62 chars]
    en  You're up. So am I, and neither of us has a good reason, %1$s.
    >>  ............................................
    pt  Você está de pé. Eu também, e nenhum dos dois tem um bom motivo, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.late/3   [72 chars]
    en  This hour, the village belongs to whoever couldn't sleep. Welcome to it.
    >>  ............................................
    pt  A esta hora a vila é de quem não conseguiu dormir. Bem-vindo.
    >>  ............................................
```


**Outcome 2 of 8** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.greet.today` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `again` budget `quick`
- Then opens: `conversations.topic.checkin.again.respond`
- …where the player's next choices will be: "Sorry — I've asked you that already." | "Humour me. Really, how are you?" | "Fair. Another time."

```text
POOL   dialogue key: dialogue.conversations.checkin.again
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.again.to.checkin.again`: the villager accepts. Subject `checkin.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.again/1   [60 chars]
    en  You asked me that this morning, %1$s. Still the same answer.
    >>  ............................................
    pt  Você já me perguntou isso hoje de manhã, %1$s. A resposta é a mesma.
    >>  ............................................
  dialogue.conversations.checkin.again/2   [64 chars]
    en  Twice in one day? Either you care a lot or you forgot you asked.
    >>  ............................................
    pt  Duas vezes no mesmo dia? Ou você se importa muito, ou esqueceu que já perguntou.
    >>  ............................................
  dialogue.conversations.checkin.again/3   [58 chars]
    en  Persistent today, aren't we? Same answer, warmer delivery.
    >>  ............................................
    pt  Insistente hoje, hein? Mesma resposta, entrega mais calorosa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.again/1
    en  You asked already — is that bad? Sorry. Nothing's changed, I promise, %1$s.
    >>  ............................................
    pt  Você já perguntou — isso é ruim? Desculpa. Nada mudou, eu prometo, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.again/2
    en  Twice now. Should I be reading into that? I'm still fine. Mostly fine.
    >>  ............................................
    pt  Duas vezes agora. Eu deveria tirar alguma conclusão disso? Continuo bem. Quase bem.
    >>  ............................................
  athletic.dialogue.conversations.checkin.again/1
    en  Same as an hour ago, %1$s — winded and standing. Ask my legs, they don't lie.
    >>  ............................................
    pt  Igual a uma hora atrás, %1$s — ofegante e de pé. Pergunta pras minhas pernas, elas não mentem.
    >>  ............................................
  athletic.dialogue.conversations.checkin.again/2
    en  Twice now? Still breathing hard, still upright. That's a good report in my book.
    >>  ............................................
    pt  Duas vezes? Continuo respirando pesado e continuo em pé. No meu livro isso é um bom relatório.
    >>  ............................................
  confident.dialogue.conversations.checkin.again/1
    en  Asked and answered, %1$s. My good mood needs no encore.
    >>  ............................................
    pt  Perguntado e respondido, %1$s. Meu bom humor não precisa de bis.
    >>  ............................................
  confident.dialogue.conversations.checkin.again/2
    en  Twice? I'm flattered. Still standing tall, still your favorite.
    >>  ............................................
    pt  Duas vezes? Estou lisonjeado. Continuo de cabeça erguida e continuo sendo o seu favorito.
    >>  ............................................
  crabby.dialogue.conversations.checkin.again/1
    en  I answered that. My mood doesn't turn on a coin, %1$s.
    >>  ............................................
    pt  Eu já respondi isso. Meu humor não vira numa moeda, %1$s.
    >>  ............................................
  crabby.dialogue.conversations.checkin.again/2
    en  Asked and answered. Nothing's improved in the last five minutes.
    >>  ............................................
    pt  Perguntado e respondido. Nada melhorou nos últimos cinco minutos.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.again/1
    en  You asked already! Not that I mind — ask me a third time, I'll happily answer.
    >>  ............................................
    pt  Você já perguntou! Não que eu me importe — pergunta uma terceira vez que eu respondo com prazer.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.again/2
    en  Again? Delighted to repeat myself. Still good, still glad you're here.
    >>  ............................................
    pt  De novo? Encantado em me repetir. Continuo bem, e continuo contente que você esteja aqui.
    >>  ............................................
  flirty.dialogue.conversations.checkin.again/1
    en  Asking twice, %1$s? People will talk. I don't mind if you don't.
    >>  ............................................
    pt  Perguntando duas vezes, %1$s? As pessoas vão comentar. Eu não me importo, se você não se importar.
    >>  ............................................
  flirty.dialogue.conversations.checkin.again/2
    en  Twice in one day — I'm blushing. Or you are. One of us certainly is.
    >>  ............................................
    pt  Duas vezes no mesmo dia — estou corando. Ou é você. Um de nós dois certamente está.
    >>  ............................................
  friendly.dialogue.conversations.checkin.again/1
    en  You already asked, you sweet thing! Still fine, still glad you care enough to check twice.
    >>  ............................................
    pt  Você já perguntou, sua criatura querida! Continuo bem, e continuo feliz que você se importe a ponto de conferir duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.checkin.again/2
    en  Twice in a day? You spoil me, %1$s. Same answer, twice the smile.
    >>  ............................................
    pt  Duas vezes no mesmo dia? Você me mima, %1$s. Mesma resposta, sorriso em dobro.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.again/1
    en  You asked already. The answer's the same shade of grey, %1$s. It doesn't change fast.
    >>  ............................................
    pt  Você já perguntou. A resposta tem o mesmo tom de cinza, %1$s. Não muda rápido.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.again/2
    en  Twice now. My moods move slower than the seasons. Give it a month.
    >>  ............................................
    pt  Duas vezes agora. Meus humores andam mais devagar que as estações. Dê um mês.
    >>  ............................................
  greedy.dialogue.conversations.checkin.again/1
    en  Asked already, %1$s. I don't discount the same answer twice — that's just giving product away.
    >>  ............................................
    pt  Já perguntou, %1$s. Não dou desconto na mesma resposta duas vezes — isso é dar produto de graça.
    >>  ............................................
  greedy.dialogue.conversations.checkin.again/2
    en  Twice now? You're generous with the concern. I'll note the surplus and still say: fine.
    >>  ............................................
    pt  Duas vezes? Você é generoso com a preocupação. Anoto o excedente e ainda digo: bem.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.again/1
    en  I answered that. My mood doesn't turn on a coin, %1$s.
    >>  ............................................
    pt  Eu já respondi isso. Meu humor não vira numa moeda, %1$s.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.again/2
    en  Asked and answered. Nothing's improved in the last five minutes.
    >>  ............................................
    pt  Perguntado e respondido. Nada melhorou nos últimos cinco minutos.
    >>  ............................................
  introverted.dialogue.conversations.checkin.again/1
    en  You asked already, %1$s. The answer hasn't changed — I don't change that quickly.
    >>  ............................................
    pt  Você já perguntou, %1$s. A resposta não mudou — eu não mudo tão rápido.
    >>  ............................................
  introverted.dialogue.conversations.checkin.again/2
    en  Twice. I'm the same as I was an hour ago. I usually am.
    >>  ............................................
    pt  Duas vezes. Estou igual a uma hora atrás. Costumo estar.
    >>  ............................................
  lazy.dialogue.conversations.checkin.again/1
    en  You asked already, %1$s. I move slower than the news does.
    >>  ............................................
    pt  Você já perguntou, %1$s. Eu me movo mais devagar que a notícia.
    >>  ............................................
  lazy.dialogue.conversations.checkin.again/2
    en  Same as before. Give it a few days and I might have something new.
    >>  ............................................
    pt  Igual a antes. Dá uns dias que talvez eu tenha algo novo.
    >>  ............................................
  odd.dialogue.conversations.checkin.again/1
    en  You've asked before — the bucket remembers. The answer hasn't changed shape yet, %1$s. These things ripen slowly.
    >>  ............................................
    pt  Você já perguntou — o balde lembra. A resposta ainda não mudou de formato, %1$s. Essas coisas amadurecem devagar.
    >>  ............................................
  odd.dialogue.conversations.checkin.again/2
    en  Twice! The candles noticed too. Still the same, still glad you count. The floorboards are warming to you.
    >>  ............................................
    pt  Duas vezes! As velas repararam também. Continua igual, e continuo feliz que você conte. O assoalho está se afeiçoando a você.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.again/1
    en  You've asked already, %1$s. The answer is the same. I don't change much between mornings.
    >>  ............................................
    pt  Você já perguntou, %1$s. A resposta é a mesma. Eu não mudo muito entre uma manhã e outra.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.again/2
    en  Still well. I'd tell you plainly if I weren't.
    >>  ............................................
    pt  Continuo bem. Eu te diria com clareza se não estivesse.
    >>  ............................................
  peppy.dialogue.conversations.checkin.again/1
    en  You already asked, silly! Still great! Ask a third time and it might get EVEN better, that's the rule!
    >>  ............................................
    pt  Você já perguntou, bobo! Continua ótimo! Pergunta uma terceira vez e pode ficar AINDA melhor, é a regra!
    >>  ............................................
  peppy.dialogue.conversations.checkin.again/2
    en  Twice in one day?! You really care, huh! Still wonderful, now with EXTRA sparkle because it's you, %1$s!
    >>  ............................................
    pt  Duas vezes no mesmo dia?! Você se importa mesmo, hein! Continua maravilhoso, agora com brilho EXTRA porque é você, %1$s!
    >>  ............................................
  playful.dialogue.conversations.checkin.again/1
    en  Asked twice! Are you checking whether I'll change my story? I might, just to keep you guessing.
    >>  ............................................
    pt  Perguntou duas vezes! Está checando se eu mudo a versão? Posso mudar, só pra te deixar na dúvida.
    >>  ............................................
  playful.dialogue.conversations.checkin.again/2
    en  Again? Fine — this time I'm splendid, and last time I was merely great. Spot the difference.
    >>  ............................................
    pt  De novo? Tudo bem — dessa vez estou esplêndido, e da última estava só ótimo. Acha a diferença.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.again/1
    en  You asked already, %1$s. I move slower than the news does.
    >>  ............................................
    pt  Você já perguntou, %1$s. Eu me movo mais devagar que a notícia.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.again/2
    en  Same as before. Give it a few days and I might have something new.
    >>  ............................................
    pt  Igual a antes. Dá uns dias que talvez eu tenha algo novo.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.again/1
    en  You asked already, and it still touches me. I'm alright, truly. Are YOU, though? You keep checking on me.
    >>  ............................................
    pt  Você já perguntou, e ainda me toca. Estou bem, sério. Mas e VOCÊ? Você fica me conferindo.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.again/2
    en  Twice now — you sweet thing. Still the same, still warmed that you care enough to ask again.
    >>  ............................................
    pt  Duas vezes agora — sua criatura doce. Continua igual, e continuo aquecido por você se importar a ponto de perguntar de novo.
    >>  ............................................
  shy.dialogue.conversations.checkin.again/1
    en  You asked already, %1$s. The answer hasn't changed — I don't change that quickly.
    >>  ............................................
    pt  Você já perguntou, %1$s. A resposta não mudou — eu não mudo tão rápido.
    >>  ............................................
  shy.dialogue.conversations.checkin.again/2
    en  Twice. I'm the same as I was an hour ago. I usually am.
    >>  ............................................
    pt  Duas vezes. Estou igual a uma hora atrás. Costumo estar.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.again/1
    en  You've asked already, %1$s! Still good. I'd tell you if it changed, I promise.
    >>  ............................................
    pt  Você já perguntou, %1$s! Continua bom. Eu te contaria se mudasse, prometo.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.again/2
    en  Twice now — you must really want good news. Happily, I've still got some.
    >>  ............................................
    pt  Duas vezes — você deve querer muito uma boa notícia. Felizmente, ainda tenho algumas.
    >>  ............................................
  witty.dialogue.conversations.checkin.again/1
    en  You've asked already, %1$s! Still good. I'd tell you if it changed, I promise.
    >>  ............................................
    pt  Você já perguntou, %1$s! Continua bom. Eu te contaria se mudasse, prometo.
    >>  ............................................
  witty.dialogue.conversations.checkin.again/2
    en  Twice now — you must really want good news. Happily, I've still got some.
    >>  ............................................
    pt  Duas vezes — você deve querer muito uma boa notícia. Felizmente, ainda tenho algumas.
    >>  ............................................
```

</details>


**Outcome 3 of 8** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.greet.today` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `toddler` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.toddler.respond`
- …where the player's next choices will be: "That is a very good report." | "Tell me the best bit." | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.checkin.toddler
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.toddler.to.checkin.toddler`: the villager accepts. Subject `checkin.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.toddler/1   [49 chars]
    en  I'm good! I found a bug this morning. It wiggled.
    >>  ............................................
    pt  Tô bem! Achei um bichinho hoje de manhã. Ele mexeu.
    >>  ............................................
  dialogue.conversations.checkin.toddler/2   [29 chars]
    en  Sleepy. But good. But sleepy.
    >>  ............................................
    pt  Com sono. Mas bem. Mas com sono.
    >>  ............................................
  dialogue.conversations.checkin.toddler/3   [29 chars]
    en  I got up ALL by myself today.
    >>  ............................................
    pt  Hoje eu levantei SOZINHO.
    >>  ............................................
```


**Outcome 4 of 8** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.greet.today` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `child` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.young.respond`
- …where the player's next choices will be: "Go on, tell me the whole thing." | "You're doing alright, you know." | "That's not much of an answer." | "See you around."

```text
POOL   dialogue key: dialogue.conversations.checkin.child
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.child.to.checkin.young`: the villager accepts. Subject `checkin.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.child/1   [51 chars]
    en  Good! Mostly good. The goat and I have a truce now.
    >>  ............................................
    pt  Bem! Quase tudo bem. Eu e a cabra fizemos as pazes agora.
    >>  ............................................
  dialogue.conversations.checkin.child/2   [55 chars]
    en  I'm okay! I only fell in the pond once this whole week.
    >>  ............................................
    pt  Tô bem! Só caí no lago uma vez a semana inteira.
    >>  ............................................
```


**Outcome 5 of 8** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.greet.today` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `teen` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.young.respond`
- …where the player's next choices will be: "Go on, tell me the whole thing." | "You're doing alright, you know." | "That's not much of an answer." | "See you around."

```text
POOL   dialogue key: dialogue.conversations.checkin.teen
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.teen.to.checkin.young`: the villager accepts. Subject `checkin.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.teen/1   [33 chars]
    en  Fine, I guess. Same as yesterday.
    >>  ............................................
    pt  Normal, eu acho. Igual ontem.
    >>  ............................................
  dialogue.conversations.checkin.teen/2   [46 chars]
    en  Busy. Everyone suddenly wants me to do chores.
    >>  ............................................
    pt  Ocupado. De repente todo mundo quer que eu faça tarefa.
    >>  ............................................
```


**Outcome 6 of 8** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.greet.today` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `rough` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.rough.respond`
- …where the player's next choices will be: "I'm listening, if you want to say more." | "What's been weighing on you?" | "You'll be fine. You always are." | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.checkin.rough
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.rough.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.rough.to.checkin.rough`: the villager accepts. Subject `checkin.rough`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.rough/1   [80 chars]
    en  You want the honest answer? It's been a heavy few days. Thanks for asking, %1$s.
    >>  ............................................
    pt  Quer a resposta sincera? Foram uns dias pesados. Obrigado por perguntar, %1$s.
    >>  ............................................
  dialogue.conversations.checkin.rough/2   [64 chars]
    en  Not my best morning. But you asking helps more than you'd think.
    >>  ............................................
    pt  Não foi minha melhor manhã. Mas você perguntar ajuda mais do que parece.
    >>  ............................................
  dialogue.conversations.checkin.rough/3   [61 chars]
    en  I've been carrying more than my basket lately. It shows, huh?
    >>  ............................................
    pt  Ando carregando mais que a minha cesta ultimamente. Dá pra ver, né?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.rough/1
    en  Not good. I've been turning the same worry over since Tuesday and it hasn't got any smaller. Sorry, %1$s.
    >>  ............................................
    pt  Não boa. Ando revirando a mesma preocupação desde terça e ela não diminuiu nada. Desculpa, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.checkin.rough/2
    en  Rough. I keep thinking I've forgotten something important and I can't work out what.
    >>  ............................................
    pt  Difícil. Fico achando que esqueci alguma coisa importante e não consigo descobrir o quê.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough/1
    en  Rough week. Tried to outrun it twice and it kept pace. Thanks for asking, %1$s. Really.
    >>  ............................................
    pt  Semana dura. Tentei correr mais rápido que ela duas vezes e ela acompanhou. Obrigado por perguntar, %1$s. Sério.
    >>  ............................................
  athletic.dialogue.conversations.checkin.rough/2
    en  Off my stride. A body that won't cooperate is a long few days. Kind of you to ask.
    >>  ............................................
    pt  Fora do meu ritmo. Um corpo que não colabora são uns dias bem longos. Gentileza sua perguntar.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough/1
    en  A difficult stretch. Not a defeat — a delay. Though I'll admit, %1$s, your asking helps the schedule.
    >>  ............................................
    pt  Um trecho difícil. Não uma derrota — um atraso. Embora eu admita, %1$s, você perguntar ajuda o cronograma.
    >>  ............................................
  confident.dialogue.conversations.checkin.rough/2
    en  A hard patch. Even I get them — I simply get through them louder.
    >>  ............................................
    pt  Fase dura. Até eu tenho delas — só atravesso mais alto.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough/1
    en  Terrible. Everything's gone wrong twice. ...Thanks for asking, though. Not many do.
    >>  ............................................
    pt  Terrível. Tudo deu errado duas vezes. ...Mas obrigado por perguntar. Poucos perguntam.
    >>  ............................................
  crabby.dialogue.conversations.checkin.rough/2
    en  Rotten week. I'd rather not detail it. But I'm glad somebody noticed, %1$s.
    >>  ............................................
    pt  Semana podre. Prefiro não detalhar. Mas fico contente que alguém tenha reparado, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough/1
    en  Quiet week, and quiet's the worst kind for me. Everyone's been indoors. I've been climbing the walls, honestly.
    >>  ............................................
    pt  Semana quieta, e quieta é o pior tipo pra mim. Todo mundo ficou dentro de casa. Estou subindo pelas paredes, sinceramente.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.rough/2
    en  Lonely one, if I'm honest. You turning up has fixed most of it, %1$s.
    >>  ............................................
    pt  Solitária, pra ser sincero. Você aparecer resolveu quase tudo, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough/1
    en  Rough day — but you asking about it? That's the first nice thing that's happened.
    >>  ............................................
    pt  Dia ruim — mas você perguntar sobre ele? Essa foi a primeira coisa boa que aconteceu.
    >>  ............................................
  flirty.dialogue.conversations.checkin.rough/2
    en  Low week, love. Your timing's impeccable, though. Stay and cheer me up?
    >>  ............................................
    pt  Semana baixa, amor. Mas o seu tempo é impecável. Fica e me anima?
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough/1
    en  A heavy stretch, truth be told. But you asking? That warms me like fresh bread, %1$s.
    >>  ............................................
    pt  Um trecho pesado, pra falar a verdade. Mas você perguntar? Isso me aquece que nem pão fresco, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.checkin.rough/2
    en  A bit heavy, if I'm honest. Come sit — talking with you already helps.
    >>  ............................................
    pt  Meio pesado, se eu for sincero. Vem sentar — conversar com você já ajuda.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough/1
    en  Every day is a heavy few days, %1$s. But thank you for noticing this one.
    >>  ............................................
    pt  Todo dia são uns dias pesados, %1$s. Mas obrigado por reparar neste aqui.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.rough/2
    en  Worse than usual, which takes some doing. I'd not have mentioned it if you hadn't stopped, %1$s.
    >>  ............................................
    pt  Pior que o normal, o que já é trabalhoso. Eu não teria comentado se você não tivesse parado, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough/1
    en  Terrible margin this week — six days in, one decent soup out. Your asking, though... I'll book that as income.
    >>  ............................................
    pt  Margem terrível essa semana — seis dias entrando, uma sopa decente saindo. Mas você perguntar... vou lançar como receita.
    >>  ............................................
  greedy.dialogue.conversations.checkin.rough/2
    en  Poorly, and I'll spare you the itemised list. It's long, it's dull, and it's mostly the miller's fault, %1$s.
    >>  ............................................
    pt  Mal, e vou te poupar da lista item por item. É longa, é chata, e é quase toda culpa do moleiro, %1$s.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough/1
    en  Terrible. Everything's gone wrong twice. ...Thanks for asking, though. Not many do.
    >>  ............................................
    pt  Terrível. Tudo deu errado duas vezes. ...Mas obrigado por perguntar. Poucos perguntam.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.rough/2
    en  Rotten week. I'd rather not detail it. But I'm glad somebody noticed, %1$s.
    >>  ............................................
    pt  Semana podre. Prefiro não detalhar. Mas fico contente que alguém tenha reparado, %1$s.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough/1
    en  It's been a heavy week, and a loud one. I didn't expect anyone to ask, %1$s. I'm glad you did.
    >>  ............................................
    pt  Foi uma semana pesada, e barulhenta. Eu não esperava que alguém perguntasse, %1$s. Fico contente que tenha sido você.
    >>  ............................................
  introverted.dialogue.conversations.checkin.rough/2
    en  Not good. Too many people, too little quiet. I'm running low on myself.
    >>  ............................................
    pt  Não foi boa. Gente demais, silêncio de menos. Estou ficando sem mim mesmo.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough/1
    en  A bit much, honestly — too many things wanting doing at once. I've been unpicking it slowly. It's coming right.
    >>  ............................................
    pt  Meio demais, sinceramente — coisa demais querendo ser feita ao mesmo tempo. Fui desemaranhando devagar. Está se ajeitando.
    >>  ............................................
  lazy.dialogue.conversations.checkin.rough/2
    en  Rough patch. Everything's been in a hurry except me. I'll outlast it, %1$s.
    >>  ............................................
    pt  Fase difícil. Tudo com pressa menos eu. Eu duro mais que ela, %1$s.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough/1
    en  The door has been sticking, and doors know things. It's been a heavy week, %1$s. The door agrees with me.
    >>  ............................................
    pt  A porta anda emperrando, e portas sabem das coisas. Foi uma semana pesada, %1$s. A porta concorda comigo.
    >>  ............................................
  odd.dialogue.conversations.checkin.rough/2
    en  Heavy. I've been putting things in the wrong drawers, which is always the first sign.
    >>  ............................................
    pt  Pesada. Ando guardando as coisas nas gavetas erradas, que é sempre o primeiro sinal.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough/1
    en  Unsettled, if I'm honest. I've been trying to sit with it rather than fight it. That helps, mostly.
    >>  ............................................
    pt  Inquieta, se for sincero. Ando tentando ficar com isso em vez de brigar. Ajuda, na maior parte.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.rough/2
    en  A harder week. It will pass — they always do. Thank you for asking, %1$s.
    >>  ............................................
    pt  Uma semana mais dura. Vai passar — sempre passam. Obrigado por perguntar, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough/1
    en  Rough patch, honestly! But you asked, so it's officially turning around — that's just how it works!
    >>  ............................................
    pt  Fase ruim, sinceramente! Mas você perguntou, então oficialmente já está melhorando — é assim que funciona!
    >>  ............................................
  peppy.dialogue.conversations.checkin.rough/2
    en  A little rough! But you asking just bumped it up three whole notches, so we're basically fine now, %1$s!
    >>  ............................................
    pt  Meio ruim! Mas você perguntar acabou de subir três degraus inteiros, então estamos basicamente bem agora, %1$s!
    >>  ............................................
  playful.dialogue.conversations.checkin.rough/1
    en  Bit grim, actually. Even I haven't felt like mischief. That's how you know it's serious, %1$s.
    >>  ............................................
    pt  Meio sombria, na verdade. Nem eu estive com vontade de aprontar. É assim que você sabe que é sério, %1$s.
    >>  ............................................
  playful.dialogue.conversations.checkin.rough/2
    en  Rough one. My jokes have all landed wrong. A tragedy for the village, really.
    >>  ............................................
    pt  Semana ruim. Minhas piadas caíram todas erradas. Uma tragédia pro vilarejo, na verdade.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough/1
    en  A bit much, honestly — too many things wanting doing at once. I've been unpicking it slowly. It's coming right.
    >>  ............................................
    pt  Meio demais, sinceramente — coisa demais querendo ser feita ao mesmo tempo. Fui desemaranhando devagar. Está se ajeitando.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.rough/2
    en  Rough patch. Everything's been in a hurry except me. I'll outlast it, %1$s.
    >>  ............................................
    pt  Fase difícil. Tudo com pressa menos eu. Eu duro mais que ela, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough/1
    en  Heavy, honestly. And you asking just now — sorry, give me a moment. That meant something, %1$s.
    >>  ............................................
    pt  Pesada, sinceramente. E você perguntar agora — desculpa, me dá um instante. Isso significou algo, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.rough/2
    en  Heavy. I feel everything twice, I think. Your asking lightens it, %1$s. Genuinely.
    >>  ............................................
    pt  Pesada. Eu sinto tudo em dobro, acho. Você perguntar alivia, %1$s. De verdade.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough/1
    en  It's been a heavy week, and a loud one. I didn't expect anyone to ask, %1$s. I'm glad you did.
    >>  ............................................
    pt  Foi uma semana pesada, e barulhenta. Eu não esperava que alguém perguntasse, %1$s. Fico contente que tenha sido você.
    >>  ............................................
  shy.dialogue.conversations.checkin.rough/2
    en  Not good. Too many people, too little quiet. I'm running low on myself.
    >>  ............................................
    pt  Não foi boa. Gente demais, silêncio de menos. Estou ficando sem mim mesmo.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough/1
    en  Bit of a rough one, honestly. But you asking has already improved it, %1$s. That counts for a lot.
    >>  ............................................
    pt  Meio dura, sinceramente. Mas você perguntar já melhorou tudo, %1$s. Isso conta bastante.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.rough/2
    en  Not my finest week — though it's turning around as we speak. Funny how that works.
    >>  ............................................
    pt  Não foi a minha melhor semana — embora esteja virando enquanto a gente fala. Engraçado como funciona.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough/1
    en  Bit of a rough one, honestly. But you asking has already improved it, %1$s. That counts for a lot.
    >>  ............................................
    pt  Meio dura, sinceramente. Mas você perguntar já melhorou tudo, %1$s. Isso conta bastante.
    >>  ............................................
  witty.dialogue.conversations.checkin.rough/2
    en  Not my finest week — though it's turning around as we speak. Funny how that works.
    >>  ............................................
    pt  Não foi a minha melhor semana — embora esteja virando enquanto a gente fala. Engraçado como funciona.
    >>  ............................................
```

</details>


**Outcome 7 of 8** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.greet.today` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `checkin` branch `good` budget `quick`
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `conversations.topic.checkin.good.respond`
- …where the player's next choices will be: "I'm glad. Genuinely." | "What's been going right?" | "Give it a week." | "Good to hear. I'll get on."

```text
POOL   dialogue key: dialogue.conversations.checkin.good
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: conversations.topic.checkin.good.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.to.checkin.good`: the villager accepts. Subject `checkin.good`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.checkin.good/1   [80 chars]
    en  Honestly? Pretty good. The bread turned out right and nobody's yelled at me yet.
    >>  ............................................
    pt  Sinceramente? Bem tranquilo. O pão cresceu direito e ninguém gritou comigo ainda.
    >>  ............................................
  dialogue.conversations.checkin.good/2   [72 chars]
    en  Better now that someone actually asked. Most folks just want directions.
    >>  ............................................
    pt  Melhor agora que alguém perguntou de verdade. A maioria só quer saber o caminho.
    >>  ............................................
  dialogue.conversations.checkin.good/3   [81 chars]
    en  Can't complain. Slept through the night, and the roof only leaks in one spot now.
    >>  ............................................
    pt  Não posso reclamar. Dormi a noite toda, e o telhado só goteja num lugar agora.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.checkin.good/1
    en  Good, I think? Nothing's gone wrong yet, which usually means something's about to. But — good. Thank you for asking.
    >>  ............................................
    pt  Boa, eu acho? Nada deu errado ainda, o que geralmente significa que alguma coisa está prestes a dar. Mas — boa. Obrigado por perguntar.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good/2
    en  Fine, actually! I keep waiting for the catch, but there hasn't been one. That's new.
    >>  ............................................
    pt  Bem, na verdade! Fico esperando a pegadinha, mas não teve nenhuma. Isso é novo.
    >>  ............................................
  anxious.dialogue.conversations.checkin.good/3
    en  Better than I expected. I'd braced for much worse, so I'm counting it a win.
    >>  ............................................
    pt  Melhor do que eu esperava. Eu tinha me preparado pra muito pior, então conto como vitória.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good/1
    en  Great stretch. Beat my own time to the well three mornings running. Legs ache. The good ache.
    >>  ............................................
    pt  Ótima fase. Bati meu próprio tempo até o poço três manhãs seguidas. As pernas doem. A dor boa.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good/2
    en  Strong. Lungs like a forge bellows and legs that won't quit. Good week.
    >>  ............................................
    pt  Forte. Pulmão que nem fole de forja e pernas que não param. Boa semana.
    >>  ............................................
  athletic.dialogue.conversations.checkin.good/3
    en  Never better. Every muscle's earned its keep today.
    >>  ............................................
    pt  Nunca estive melhor. Cada músculo justificou o sustento hoje.
    >>  ............................................
  confident.dialogue.conversations.checkin.good/1
    en  Splendidly, of course. The bread rose, the fence held, and the week went precisely as I intended.
    >>  ............................................
    pt  Esplendidamente, claro. O pão cresceu, a cerca aguentou, e a semana correu exatamente como eu pretendia.
    >>  ............................................
  confident.dialogue.conversations.checkin.good/2
    en  Magnificent. I usually am — it's a discipline, %1$s.
    >>  ............................................
    pt  Magnífico. Costumo estar — é uma disciplina, %1$s.
    >>  ............................................
  confident.dialogue.conversations.checkin.good/3
    en  Better than most, worse than I'll be tomorrow. I improve on schedule.
    >>  ............................................
    pt  Melhor que a maioria, pior do que estarei amanhã. Eu melhoro conforme o cronograma.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good/1
    en  Fine. Nothing broke, nobody bothered me. Don't make a thing of it.
    >>  ............................................
    pt  Bem. Nada quebrou, ninguém me amolou. Não faz disso um caso.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good/2
    en  Better than usual, which isn't saying much. Still — I'll take it.
    >>  ............................................
    pt  Melhor que o normal, o que não é dizer muita coisa. Ainda assim — eu aceito.
    >>  ............................................
  crabby.dialogue.conversations.checkin.good/3
    en  Well enough, if you must know. Ask me again tomorrow and you'll get a worse answer.
    >>  ............................................
    pt  Bem o bastante, já que você quer saber. Me pergunte amanhã e a resposta será pior.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good/1
    en  Wonderful! Market day was packed and I spoke to every single person there. I'm still buzzing from it.
    >>  ............................................
    pt  Maravilhosa! O dia de feira estava lotado e eu falei com cada pessoa que estava lá. Ainda estou vibrando.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good/2
    en  Great week — full house at the inn two nights running. That's my idea of a good time.
    >>  ............................................
    pt  Semana ótima — estalagem cheia duas noites seguidas. É essa a minha ideia de diversão.
    >>  ............................................
  extroverted.dialogue.conversations.checkin.good/3
    en  Splendid, thanks for asking! Half the village stopped to chat this week. I keep count.
    >>  ............................................
    pt  Esplêndida, obrigado por perguntar! Metade do vilarejo parou pra conversar essa semana. Eu conto.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good/1
    en  Better now. Funny how that works when you show up.
    >>  ............................................
    pt  Melhor agora. Engraçado como isso funciona quando você aparece.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good/2
    en  Wonderful — and about to be more so, I suspect. You bring trouble with you, %1$s.
    >>  ............................................
    pt  Maravilhosa — e prestes a ficar mais, eu desconfio. Você traz confusão junto, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.checkin.good/3
    en  Splendid. Though I'd have said 'lonely' five minutes ago.
    >>  ............................................
    pt  Esplêndida. Embora cinco minutos atrás eu tivesse dito "solitária".
    >>  ............................................
  friendly.dialogue.conversations.checkin.good/1
    en  So good, honestly! The oven's been kind, the neighbors kinder, and now you're here. Full week!
    >>  ............................................
    pt  Muito bem, sinceramente! O forno andou generoso, os vizinhos mais ainda, e agora você está aqui. Semana completa!
    >>  ............................................
  friendly.dialogue.conversations.checkin.good/2
    en  Really good, and better for your face at the door, %1$s. You brighten a room.
    >>  ............................................
    pt  Muito bem mesmo, e melhor ainda com a sua cara na porta, %1$s. Você ilumina um cômodo.
    >>  ............................................
  friendly.dialogue.conversations.checkin.good/3
    en  Lovely week, lovelier now. No complaints — and I'd share them if I had any!
    >>  ............................................
    pt  Semana adorável, mais adorável agora. Sem reclamações — e eu dividiria se tivesse alguma!
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good/1
    en  Good enough. The rain held off. That's as good as it gets.
    >>  ............................................
    pt  Boa o bastante. A chuva não veio. É o melhor que dá pra esperar.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good/2
    en  I slept through, for once. I keep waiting to find out what it cost.
    >>  ............................................
    pt  Dormi a noite toda, pela primeira vez. Continuo esperando descobrir o que isso custou.
    >>  ............................................
  gloomy.dialogue.conversations.checkin.good/3
    en  Fine, I suppose. Ask me again when the clouds come back and I'll sound more like myself.
    >>  ............................................
    pt  Bem, eu suponho. Me pergunte de novo quando as nuvens voltarem e eu vou soar mais como eu mesmo.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good/1
    en  Excellent week. Eggs are up, expenses are down, and the baker owes me two favors. The portfolio thrives.
    >>  ............................................
    pt  Semana excelente. Ovos em alta, despesas em baixa, e o padeiro me deve dois favores. A carteira prospera.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good/2
    en  Nobody has asked me for anything all week. Do you know what that's worth? Neither do I, but it's a lot.
    >>  ............................................
    pt  Ninguém me pediu nada a semana inteira. Sabe quanto isso vale? Nem eu, mas é muito.
    >>  ............................................
  greedy.dialogue.conversations.checkin.good/3
    en  Thriving, thank you. Even the weather's been thrifty — free sunshine all week.
    >>  ............................................
    pt  Prosperando, obrigado. Até o tempo andou econômico — sol de graça a semana inteira.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good/1
    en  Fine. Nothing broke, nobody bothered me. Don't make a thing of it.
    >>  ............................................
    pt  Bem. Nada quebrou, ninguém me amolou. Não faz disso um caso.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good/2
    en  Better than usual, which isn't saying much. Still — I'll take it.
    >>  ............................................
    pt  Melhor que o normal, o que não é dizer muita coisa. Ainda assim — eu aceito.
    >>  ............................................
  grumpy.dialogue.conversations.checkin.good/3
    en  Well enough, if you must know. Ask me again tomorrow and you'll get a worse answer.
    >>  ............................................
    pt  Bem o bastante, já que você quer saber. Me pergunte amanhã e a resposta será pior.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good/1
    en  Good. The week left me alone, mostly, and I got a great deal of thinking done. That's a good week, by my measure.
    >>  ............................................
    pt  Boa. A semana me deixou em paz, na maior parte, e eu pensei bastante. É uma boa semana, pela minha medida.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good/2
    en  Well enough. Quiet mornings, work I could do without talking. It suited me.
    >>  ............................................
    pt  Bem o bastante. Manhãs quietas, trabalho que eu podia fazer sem falar. Me serviu.
    >>  ............................................
  introverted.dialogue.conversations.checkin.good/3
    en  Better than good. Nobody needed much of me, so there was plenty left over for myself.
    >>  ............................................
    pt  Melhor que boa. Ninguém precisou muito de mim, então sobrou bastante pra mim mesmo.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good/1
    en  Comfortable, thanks. Everything got done eventually, and eventually was soon enough.
    >>  ............................................
    pt  Confortável, obrigado. Tudo acabou saindo, e "acabou" foi cedo o bastante.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good/2
    en  Easy week. Nothing urgent turned up, and I didn't go looking for any.
    >>  ............................................
    pt  Semana leve. Nada urgente apareceu, e eu não fui procurar nenhum.
    >>  ............................................
  lazy.dialogue.conversations.checkin.good/3
    en  Can't complain. Warm afternoons, work that waited its turn. That's about ideal.
    >>  ............................................
    pt  Não posso reclamar. Tardes quentes, serviço que esperou a vez. É quase o ideal.
    >>  ............................................
  odd.dialogue.conversations.checkin.good/1
    en  The bees hummed in E this week, which means luck. Also I found my other spoon. So: thriving, thank you for asking.
    >>  ............................................
    pt  As abelhas zumbiram em mi essa semana, o que significa sorte. Também achei a minha outra colher. Então: prosperando, obrigado por perguntar.
    >>  ............................................
  odd.dialogue.conversations.checkin.good/2
    en  Good. I've not lost anything all week, which for me is remarkable.
    >>  ............................................
    pt  Bem. Não perdi nada a semana inteira, o que pra mim é notável.
    >>  ............................................
  odd.dialogue.conversations.checkin.good/3
    en  Thriving. The well echoed twice this morning, and two is the number for yes. I trust the well, %1$s.
    >>  ............................................
    pt  Prosperando. O poço ecoou duas vezes hoje de manhã, e dois é o número do sim. Eu confio no poço, %1$s.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good/1
    en  Calm, thank you. The mornings have been still and the work has been kind. That's all I ask of a week.
    >>  ............................................
    pt  Calma, obrigado. As manhãs andaram serenas e o trabalho, generoso. É tudo que eu peço de uma semana.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good/2
    en  Good. Nothing dramatic — the garden grew, the sun came, I slept well. It's enough.
    >>  ............................................
    pt  Boa. Nada dramático — a horta cresceu, o sol veio, eu dormi bem. É o bastante.
    >>  ............................................
  peaceful.dialogue.conversations.checkin.good/3
    en  Quiet and steady. I've learned to be grateful for weeks like this one.
    >>  ............................................
    pt  Quieta e firme. Aprendi a agradecer por semanas assim.
    >>  ............................................
  peppy.dialogue.conversations.checkin.good/1
    en  SO good! The bread rose, the chickens marched in a LINE — a line! — and now you're here asking!
    >>  ............................................
    pt  MUITO bem! O pão cresceu, as galinhas marcharam em FILA — uma fila! — e agora você está aqui perguntando!
    >>  ............................................
  peppy.dialogue.conversations.checkin.good/2
    en  Brilliant! I got up before the rooster just to beat him at it. I'm counting that as a win!
    >>  ............................................
    pt  Ótima! Levantei antes do galo só pra ganhar dele. Estou contando como vitória!
    >>  ............................................
  peppy.dialogue.conversations.checkin.good/3
    en  So good I could burst! Do you ever have a day so nice it's almost suspicious? THIS! This is that!
    >>  ............................................
    pt  Tão bem que eu podia explodir! Você já teve um dia tão bom que fica até suspeito? ISSO! É isso!
    >>  ............................................
  playful.dialogue.conversations.checkin.good/1
    en  Brilliant! I hid the miller's hat this morning and he still hasn't found it. It's been a very good week.
    >>  ............................................
    pt  Excelente! Escondi o chapéu do moleiro hoje de manhã e ele ainda não achou. Foi uma semana muito boa.
    >>  ............................................
  playful.dialogue.conversations.checkin.good/2
    en  Excellent. Nobody's caught me at anything yet. That's my measure of a fine week.
    >>  ............................................
    pt  Ótima. Ninguém me pegou em nada ainda. É essa a minha medida de uma boa semana.
    >>  ............................................
  playful.dialogue.conversations.checkin.good/3
    en  Wonderful. I've a new joke, three people fell for it, and one of them was the guard.
    >>  ............................................
    pt  Maravilhosa. Tenho uma piada nova, três pessoas caíram, e uma delas foi o guarda.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good/1
    en  Comfortable, thanks. Everything got done eventually, and eventually was soon enough.
    >>  ............................................
    pt  Confortável, obrigado. Tudo acabou saindo, e "acabou" foi cedo o bastante.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good/2
    en  Easy week. Nothing urgent turned up, and I didn't go looking for any.
    >>  ............................................
    pt  Semana leve. Nada urgente apareceu, e eu não fui procurar nenhum.
    >>  ............................................
  relaxed.dialogue.conversations.checkin.good/3
    en  Can't complain. Warm afternoons, work that waited its turn. That's about ideal.
    >>  ............................................
    pt  Não posso reclamar. Tardes quentes, serviço que esperou a vez. É quase o ideal.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good/1
    en  Truly good, for once. The bread came out right and the sunrise nearly made me cry. But how are YOU, really?
    >>  ............................................
    pt  Realmente boa, pra variar. O pão saiu certo e o nascer do sol quase me fez chorar. Mas e VOCÊ, de verdade?
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good/2
    en  Good, quietly. Nobody needed me to be strong about anything this week.
    >>  ............................................
    pt  Boa, em silêncio. Ninguém precisou que eu fosse forte sobre nada essa semana.
    >>  ............................................
  sensitive.dialogue.conversations.checkin.good/3
    en  Good, and a little tender about it. Joy hits me square in the chest. But how are you?
    >>  ............................................
    pt  Boa, e eu meio sensível por causa disso. Alegria me acerta em cheio no peito. Mas e você?
    >>  ............................................
  shy.dialogue.conversations.checkin.good/1
    en  Good. The week left me alone, mostly, and I got a great deal of thinking done. That's a good week, by my measure.
    >>  ............................................
    pt  Boa. A semana me deixou em paz, na maior parte, e eu pensei bastante. É uma boa semana, pela minha medida.
    >>  ............................................
  shy.dialogue.conversations.checkin.good/2
    en  Well enough. Quiet mornings, work I could do without talking. It suited me.
    >>  ............................................
    pt  Bem o bastante. Manhãs quietas, trabalho que eu podia fazer sem falar. Me serviu.
    >>  ............................................
  shy.dialogue.conversations.checkin.good/3
    en  Better than good. Nobody needed much of me, so there was plenty left over for myself.
    >>  ............................................
    pt  Melhor que boa. Ninguém precisou muito de mim, então sobrou bastante pra mim mesmo.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good/1
    en  Really well, thank you for asking! The bread rose, the roof held, and the morning was kind. I'll take it.
    >>  ............................................
    pt  Muito bem, obrigado por perguntar! O pão cresceu, o telhado aguentou, e a manhã foi generosa. Eu aceito.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good/2
    en  Good — genuinely good. Small things went right and I noticed all of them. That's the trick, I think.
    >>  ............................................
    pt  Bem — bem de verdade. Coisas pequenas deram certo e eu reparei em todas. É esse o truque, eu acho.
    >>  ............................................
  upbeat.dialogue.conversations.checkin.good/3
    en  Wonderfully. Nothing dramatic, just a week that behaved itself. Those are the best kind.
    >>  ............................................
    pt  Maravilhosamente. Nada dramático, só uma semana que se comportou. Essas são as melhores.
    >>  ............................................
  witty.dialogue.conversations.checkin.good/1
    en  Really well, thank you for asking! The bread rose, the roof held, and the morning was kind. I'll take it.
    >>  ............................................
    pt  Muito bem, obrigado por perguntar! O pão cresceu, o telhado aguentou, e a manhã foi generosa. Eu aceito.
    >>  ............................................
  witty.dialogue.conversations.checkin.good/2
    en  Good — genuinely good. Small things went right and I noticed all of them. That's the trick, I think.
    >>  ............................................
    pt  Bem — bem de verdade. Coisas pequenas deram certo e eu reparei em todas. É esse o truque, eu acho.
    >>  ............................................
  witty.dialogue.conversations.checkin.good/3
    en  Wonderfully. Nothing dramatic, just a week that behaved itself. Those are the best kind.
    >>  ............................................
    pt  Maravilhosamente. Nada dramático, só uma semana que se comportou. Essas são as melhores.
    >>  ............................................
```

</details>


**Outcome 8 of 8** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.greet.today` (this player only) for 12000 ticks
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.checkin.good
WHO    VILLAGER — what the player reads after pressing "How have you been, really?"
       spoken on: greet, button `checkin`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin.good.terminal`: the villager accepts. Subject `checkin.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`greet` / button `checkin`** earlier in this file. Fill it in there, once.

---


## `main`

Question flags: `silent`.

**Reached from 50 route(s):** `conversations` / `babble`; `conversations` / `back`; `conversations.topic.checkin.again.respond` / `apologize`; `conversations.topic.checkin.again.respond` / `press`; `conversations.topic.checkin.again.respond` / `leave`; `conversations.topic.checkin.deflated.followup` / `apologize`; `conversations.topic.checkin.deflated.followup` / `restore`; `conversations.topic.checkin.deflated.followup` / `explain`; `conversations.topic.checkin.deflated.followup` / `leave`; `conversations.topic.checkin.good.followup` / `keep_it_up`; `conversations.topic.checkin.good.followup` / `tease`; `conversations.topic.checkin.good.followup` / `tease` …and 38 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.babble.baby` — e.g. "Baba! Ah-goo."
- `conversations.checkin.again.apologize` — e.g. "It's alright. Nice to be asked twice, honestly."
- `conversations.checkin.again.leave` — e.g. "Aye. Catch me tomorrow."
- `conversations.checkin.again.press` — e.g. "The same as an hour ago. I'd tell you if it had changed."
- `conversations.checkin.deflated.apologize` — e.g. "It was. And now it's said and unsaid, so we'll leave it."
- `conversations.checkin.deflated.explain` — e.g. "Most things that land badly were meant lightly."
- `conversations.checkin.deflated.leave` — e.g. "True enough. Do."
- `conversations.checkin.deflated.restore` — e.g. "...Earned. I'll take that word and keep it, thank you."
- `conversations.checkin.good` — e.g. "Honestly? Pretty good. The bread turned out right and nobody's yelled at me yet."
- `conversations.checkin.good.keep_it_up` — e.g. "I intend to. Ask me again in a month and we'll see if I managed."
- `conversations.checkin.good.leave` — e.g. "Aye, off you go. Enjoy yours."
- `conversations.checkin.good.share_own` — e.g. "Two of us doing alright at once. The village won't survive it."
- `conversations.checkin.good.tease.flat` — e.g. "Must you? It was a nice moment."
- `conversations.checkin.good.tease.landed` — e.g. "Ruined already. Word'll be round the well by supper."
- …and 33 more pools


```text
POOL   dialogue key: dialogue.main
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: main
ARGS   %1$s = the player's name
SIZE   5 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.main/8   [31 chars]
    en  Was there something else, %1$s?
    >>  ............................................
    pt  Tinha mais alguma coisa, %1$s?
    >>  ............................................
  dialogue.main/9   [54 chars]
    en  I've got a few minutes before someone needs something.
    >>  ............................................
    pt  Tenho uns minutos antes que alguém precise de alguma coisa.
    >>  ............................................
  dialogue.main/10   [21 chars]
    en  Go on, I'm listening.
    >>  ............................................
    pt  Pode falar, estou ouvindo.
    >>  ............................................
  dialogue.main/11   [43 chars]
    en  You've got that look. What is it this time?
    >>  ............................................
    pt  Você está com aquela cara. O que foi dessa vez?
    >>  ............................................
  dialogue.main/12   [33 chars]
    en  Talk to me. The turnips can wait.
    >>  ............................................
    pt  Fala comigo. Os nabos podem esperar.
    >>  ............................................
```


### Button `conversations` — "Let's talk properly"

```text
POOL   dialogue key: dialogue.main.conversations
WHO    PLAYER — the words printed on the button the player presses
       on the node: main
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.main.conversations   [19 chars]
    en  Let's talk properly
    >>  ............................................
    pt  Vamos conversar de verdade
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations`
- …where the player's next choices will be: "Just making conversation." | "About your work..." | "About the village..." | "Heard any news?" | "Something more personal." | "About us. About family." | "Goo? Ga-goo!" | "Never mind."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---

