# Topic: checkin_child

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `checkin_child` |
| Opened from | question `conversations.family`, button `checkin_child` |
| Depth class (its heart budget) | `relationship` |
| Returns to | `conversations.family` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `encouragement`, `practical_help`, `dismissal`, `exit` |
| Narrative arc | `family`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.family`, which is written out in **topic-family.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.family.checkin_child
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.family
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in topic-family*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.family.checkin_child   [35 chars]
    en  How are you holding up, little one?
    >>  ............................................
    pt  Como você está se virando, pequeno?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.topic.checkin_child.followup`](#conversations-topic-checkin-child-followup)
- [`conversations.topic.checkin_child.promise`](#conversations-topic-checkin-child-promise)
- [`conversations.topic.checkin_child.rebuffed.followup`](#conversations-topic-checkin-child-rebuffed-followup)
- [`conversations.topic.checkin_child.respond`](#conversations-topic-checkin-child-respond)

---

## `conversations.topic.checkin_child.followup`

**Reached from 2 route(s):** `conversations.topic.checkin_child.respond` / `listen`; `conversations.topic.checkin_child.respond` / `praise`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.child.listen` — e.g. "...Really are. Right. Not as well as I tell people, if I'm honest."
- `conversations.family.child.praise` — e.g. "...Am I? Some days I'm certain I'm not."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin_child.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin_child.followup   [28 chars]
    en  They're growing, either way.
    >>  ............................................
    pt  Estão crescendo, de todo jeito.
    >>  ............................................
```


### Button `guide` — "Here's what helped me."

*stance family `practical_help` · tone `plain` · answers the beat(s) `family.child.listen.to.checkin_child`, `family.child.praise.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.followup.guide` — accepted phrasings: "here is what helped me"; "here is what got me through it"; "let me tell you what helped"
  - the message must contain one of: `helped`
  - scored words: `helped`(1.5), `me`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.followup.guide
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.followup.guide   [22 chars]
    en  Here's what helped me.
    >>  ............................................
    pt  Aqui está o que me ajudou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.child.guide`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `family.child.guide`)_
- Does: arc `family` — advance to stage 1
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.child.guide
WHO    VILLAGER — what the player reads after pressing "Here's what helped me."
       spoken on: conversations.topic.checkin_child.followup, button `guide`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.guide.to.family`: the villager accepts. Subject `family`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.guide/1   [56 chars]
    en  ...That's useful. Actually useful, not the usual advice.
    >>  ............................................
    pt  ...Isso é útil. Realmente útil, não o conselho de sempre.
    >>  ............................................
  dialogue.conversations.family.child.guide/2   [48 chars]
    en  You've thought about this. Say it again, slower.
    >>  ............................................
    pt  Você pensou sobre isso. Diz de novo, mais devagar.
    >>  ............................................
  dialogue.conversations.family.child.guide/3   [49 chars]
    en  I'll try it. If it works I'll say it was my idea.
    >>  ............................................
    pt  Vou tentar. Se funcionar eu digo que a ideia foi minha.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, and I'd stopped expecting useful.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, e eu tinha parado de esperar útil.
    >>  ............................................
  anxious.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Everything else people say leaves me exactly where I was.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Todo o resto que dizem me deixa onde eu estava.
    >>  ............................................
  anxious.dialogue.conversations.family.child.guide/3
    en  That I can use. Give me a moment — I'd braced for another lecture.
    >>  ............................................
    pt  Isso eu consigo usar. Me dê um momento — eu esperava outro sermão.
    >>  ............................................
  athletic.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful. It'll take a while to know whether it works.
    >>  ............................................
    pt  Isso é útil. Útil de verdade. Vai levar um tempo pra saber se funciona.
    >>  ............................................
  athletic.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do, slowly, without anybody watching.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer, devagar, sem ninguém olhando.
    >>  ............................................
  athletic.dialogue.conversations.family.child.guide/3
    en  That I can use. I'll try it over a few months and see.
    >>  ............................................
    pt  Isso eu consigo usar. Vou tentar por uns meses e ver.
    >>  ............................................
  confident.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  confident.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Most advice isn't.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. A maioria dos conselhos não é.
    >>  ............................................
  confident.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado.
    >>  ............................................
  crabby.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  crabby.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Most advice isn't.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. A maioria dos conselhos não é.
    >>  ............................................
  crabby.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Come back and I'll tell you whether it worked.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Volte que eu te digo se funcionou.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you for treating it as a real question.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado por tratar como uma pergunta de verdade.
    >>  ............................................
  flirty.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  flirty.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Come back and I'll tell you whether it worked.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Volte que eu te digo se funcionou.
    >>  ............................................
  flirty.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you for treating it as a real question.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado por tratar como uma pergunta de verdade.
    >>  ............................................
  friendly.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  friendly.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Come back and I'll tell you whether it worked.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Volte que eu te digo se funcionou.
    >>  ............................................
  friendly.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you for treating it as a real question.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado por tratar como uma pergunta de verdade.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, and I'd stopped expecting useful.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, e eu tinha parado de esperar útil.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Everything else people say leaves me exactly where I was.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Todo o resto que dizem me deixa onde eu estava.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.guide/3
    en  That I can use. Give me a moment — I'd braced for another lecture.
    >>  ............................................
    pt  Isso eu consigo usar. Me dê um momento — eu esperava outro sermão.
    >>  ............................................
  greedy.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  greedy.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Most advice isn't.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. A maioria dos conselhos não é.
    >>  ............................................
  greedy.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful, not the usual advice.
    >>  ............................................
    pt  Isso é útil. Útil de verdade, não o conselho de sempre.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Most advice isn't.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. A maioria dos conselhos não é.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.guide/3
    en  That I can use. Thank you.
    >>  ............................................
    pt  Isso eu consigo usar. Obrigado.
    >>  ............................................
  introverted.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful.
    >>  ............................................
    pt  Isso é útil. Útil de verdade.
    >>  ............................................
  introverted.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer.
    >>  ............................................
  introverted.dialogue.conversations.family.child.guide/3
    en  That I can use.
    >>  ............................................
    pt  Isso eu consigo usar.
    >>  ............................................
  lazy.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful. It'll take a while to know whether it works.
    >>  ............................................
    pt  Isso é útil. Útil de verdade. Vai levar um tempo pra saber se funciona.
    >>  ............................................
  lazy.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do, slowly, without anybody watching.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer, devagar, sem ninguém olhando.
    >>  ............................................
  lazy.dialogue.conversations.family.child.guide/3
    en  That I can use. I'll try it over a few months and see.
    >>  ............................................
    pt  Isso eu consigo usar. Vou tentar por uns meses e ver.
    >>  ............................................
  odd.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful.
    >>  ............................................
    pt  Isso é útil. Útil de verdade.
    >>  ............................................
  odd.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer.
    >>  ............................................
  odd.dialogue.conversations.family.child.guide/3
    en  That I can use.
    >>  ............................................
    pt  Isso eu consigo usar.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful. It'll take a while to know whether it works.
    >>  ............................................
    pt  Isso é útil. Útil de verdade. Vai levar um tempo pra saber se funciona.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do, slowly, without anybody watching.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer, devagar, sem ninguém olhando.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.guide/3
    en  That I can use. I'll try it over a few months and see.
    >>  ............................................
    pt  Isso eu consigo usar. Vou tentar por uns meses e ver.
    >>  ............................................
  peppy.dialogue.conversations.family.child.guide/1
    en  That's useful! Actually useful, not the usual advice. What a novelty.
    >>  ............................................
    pt  Isso é útil! Útil de verdade, não o conselho de sempre. Que novidade.
    >>  ............................................
  peppy.dialogue.conversations.family.child.guide/2
    en  Right — something I can do! Most advice is a lecture with a bow on it.
    >>  ............................................
    pt  Certo — algo que eu consigo fazer! A maioria dos conselhos é sermão com laço.
    >>  ............................................
  peppy.dialogue.conversations.family.child.guide/3
    en  That I can use. Genuinely. I'm as surprised as you are.
    >>  ............................................
    pt  Isso eu consigo usar. Genuinamente. Estou tão surpreso quanto você.
    >>  ............................................
  playful.dialogue.conversations.family.child.guide/1
    en  That's useful! Actually useful, not the usual advice. What a novelty.
    >>  ............................................
    pt  Isso é útil! Útil de verdade, não o conselho de sempre. Que novidade.
    >>  ............................................
  playful.dialogue.conversations.family.child.guide/2
    en  Right — something I can do! Most advice is a lecture with a bow on it.
    >>  ............................................
    pt  Certo — algo que eu consigo fazer! A maioria dos conselhos é sermão com laço.
    >>  ............................................
  playful.dialogue.conversations.family.child.guide/3
    en  That I can use. Genuinely. I'm as surprised as you are.
    >>  ............................................
    pt  Isso eu consigo usar. Genuinamente. Estou tão surpreso quanto você.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful. It'll take a while to know whether it works.
    >>  ............................................
    pt  Isso é útil. Útil de verdade. Vai levar um tempo pra saber se funciona.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do, slowly, without anybody watching.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer, devagar, sem ninguém olhando.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.guide/3
    en  That I can use. I'll try it over a few months and see.
    >>  ............................................
    pt  Isso eu consigo usar. Vou tentar por uns meses e ver.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.guide/1
    en  That's useful, %1$s. Actually useful, and I'd stopped expecting useful.
    >>  ............................................
    pt  Isso é útil, %1$s. Útil de verdade, e eu tinha parado de esperar útil.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do. Everything else people say leaves me exactly where I was.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer. Todo o resto que dizem me deixa onde eu estava.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.guide/3
    en  That I can use. Give me a moment — I'd braced for another lecture.
    >>  ............................................
    pt  Isso eu consigo usar. Me dê um momento — eu esperava outro sermão.
    >>  ............................................
  shy.dialogue.conversations.family.child.guide/1
    en  That's useful. Actually useful.
    >>  ............................................
    pt  Isso é útil. Útil de verdade.
    >>  ............................................
  shy.dialogue.conversations.family.child.guide/2
    en  Right. Something I can do.
    >>  ............................................
    pt  Certo. Algo que eu consigo fazer.
    >>  ............................................
  shy.dialogue.conversations.family.child.guide/3
    en  That I can use.
    >>  ............................................
    pt  Isso eu consigo usar.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.guide/1
    en  That's useful! Actually useful, not the usual advice. What a novelty.
    >>  ............................................
    pt  Isso é útil! Útil de verdade, não o conselho de sempre. Que novidade.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.guide/2
    en  Right — something I can do! Most advice is a lecture with a bow on it.
    >>  ............................................
    pt  Certo — algo que eu consigo fazer! A maioria dos conselhos é sermão com laço.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.guide/3
    en  That I can use. Genuinely. I'm as surprised as you are.
    >>  ............................................
    pt  Isso eu consigo usar. Genuinamente. Estou tão surpreso quanto você.
    >>  ............................................
  witty.dialogue.conversations.family.child.guide/1
    en  That's useful! Actually useful, not the usual advice. What a novelty.
    >>  ............................................
    pt  Isso é útil! Útil de verdade, não o conselho de sempre. Que novidade.
    >>  ............................................
  witty.dialogue.conversations.family.child.guide/2
    en  Right — something I can do! Most advice is a lecture with a bow on it.
    >>  ............................................
    pt  Certo — algo que eu consigo fazer! A maioria dos conselhos é sermão com laço.
    >>  ............................................
  witty.dialogue.conversations.family.child.guide/3
    en  That I can use. Genuinely. I'm as surprised as you are.
    >>  ............................................
    pt  Isso eu consigo usar. Genuinamente. Estou tão surpreso quanto você.
    >>  ............................................
```

</details>


### Button `play_along` — "They sound wonderful."

*stance family `encouragement` · tone `playful` · answers the beat(s) `family.child.listen.to.checkin_child`, `family.child.praise.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.followup.play_along` — accepted phrasings: "they sound wonderful"; "they sound marvellous"; "what a wonderful child"
  - the message must contain one of: `wonderful`
  - scored words: `sound`(0.5), `wonderful`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.followup.play_along
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.followup.play_along   [21 chars]
    en  They sound wonderful.
    >>  ............................................
    pt  Parecem maravilhosos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.child.play_along`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5  _(recorded under topic `family.child.play_along`)_
- Does: arc `family` — advance to stage 1
- Then opens: `conversations.topic.family.close`
- …where the player's next choices will be: "Thank you for telling me." | "That mattered, what you said." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.family.child.play_along
WHO    VILLAGER — what the player reads after pressing "They sound wonderful."
       spoken on: conversations.topic.checkin_child.followup, button `play_along`
       leaves the player on: conversations.topic.family.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.play_along.to.family`: the villager accepts. Subject `family`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.play_along/1   [44 chars]
    en  They ARE. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO. Não conte a eles que eu concordei com você.
    >>  ............................................
  dialogue.conversations.family.child.play_along/2   [40 chars]
    en  Wonderful and exhausting, in that order.
    >>  ............................................
    pt  Maravilhosos e exaustivos, nessa ordem.
    >>  ............................................
  dialogue.conversations.family.child.play_along/3   [43 chars]
    en  Ha! You'd change your mind by supper, %1$s.
    >>  ............................................
    pt  Rá! Você mudaria de ideia até o jantar, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you — they'd take it badly and they'd be right to.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei — eles ficariam mal e com razão.
    >>  ............................................
  anxious.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. It's a relief to be allowed to say so once.
    >>  ............................................
    pt  Certo. Você não está errado. É um alívio poder dizer isso uma vez.
    >>  ............................................
  anxious.dialogue.conversations.family.child.play_along/3
    en  They are exactly that, and I feel awful for enjoying being told.
    >>  ............................................
    pt  São exatamente isso, e eu me sinto horrível por gostar de ouvir.
    >>  ............................................
  athletic.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you. They'll grow out of half of it.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei. Metade disso eles superam.
    >>  ............................................
  athletic.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. Families are like that and always have been.
    >>  ............................................
    pt  Certo. Você não está errado. Famílias são assim e sempre foram.
    >>  ............................................
  athletic.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. Give it ten years and it'll be a story we tell.
    >>  ............................................
    pt  São exatamente isso. Dê dez anos e vira história que a gente conta.
    >>  ............................................
  confident.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei com você.
    >>  ............................................
  confident.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. That stays between us.
    >>  ............................................
    pt  Certo. Você não está errado. Isso fica entre nós.
    >>  ............................................
  confident.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I'll deny having said so.
    >>  ............................................
    pt  São exatamente isso. Eu vou negar ter dito.
    >>  ............................................
  crabby.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei com você.
    >>  ............................................
  crabby.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. That stays between us.
    >>  ............................................
    pt  Certo. Você não está errado. Isso fica entre nós.
    >>  ............................................
  crabby.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I'll deny having said so.
    >>  ............................................
    pt  São exatamente isso. Eu vou negar ter dito.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei com você.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong, and I'd only say so to you.
    >>  ............................................
    pt  Certo. Você não está errado, e eu só diria isso a você.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I love them and they are exactly that.
    >>  ............................................
    pt  São exatamente isso. Eu os amo e são exatamente isso.
    >>  ............................................
  flirty.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei com você.
    >>  ............................................
  flirty.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong, and I'd only say so to you.
    >>  ............................................
    pt  Certo. Você não está errado, e eu só diria isso a você.
    >>  ............................................
  flirty.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I love them and they are exactly that.
    >>  ............................................
    pt  São exatamente isso. Eu os amo e são exatamente isso.
    >>  ............................................
  friendly.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei com você.
    >>  ............................................
  friendly.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong, and I'd only say so to you.
    >>  ............................................
    pt  Certo. Você não está errado, e eu só diria isso a você.
    >>  ............................................
  friendly.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I love them and they are exactly that.
    >>  ............................................
    pt  São exatamente isso. Eu os amo e são exatamente isso.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you — they'd take it badly and they'd be right to.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei — eles ficariam mal e com razão.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. It's a relief to be allowed to say so once.
    >>  ............................................
    pt  Certo. Você não está errado. É um alívio poder dizer isso uma vez.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that, and I feel awful for enjoying being told.
    >>  ............................................
    pt  São exatamente isso, e eu me sinto horrível por gostar de ouvir.
    >>  ............................................
  greedy.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei com você.
    >>  ............................................
  greedy.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. That stays between us.
    >>  ............................................
    pt  Certo. Você não está errado. Isso fica entre nós.
    >>  ............................................
  greedy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I'll deny having said so.
    >>  ............................................
    pt  São exatamente isso. Eu vou negar ter dito.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei com você.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. That stays between us.
    >>  ............................................
    pt  Certo. Você não está errado. Isso fica entre nós.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. I'll deny having said so.
    >>  ............................................
    pt  São exatamente isso. Eu vou negar ter dito.
    >>  ............................................
  introverted.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei.
    >>  ............................................
  introverted.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong.
    >>  ............................................
    pt  Certo. Você não está errado.
    >>  ............................................
  introverted.dialogue.conversations.family.child.play_along/3
    en  They are exactly that.
    >>  ............................................
    pt  São exatamente isso.
    >>  ............................................
  lazy.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you. They'll grow out of half of it.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei. Metade disso eles superam.
    >>  ............................................
  lazy.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. Families are like that and always have been.
    >>  ............................................
    pt  Certo. Você não está errado. Famílias são assim e sempre foram.
    >>  ............................................
  lazy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. Give it ten years and it'll be a story we tell.
    >>  ............................................
    pt  São exatamente isso. Dê dez anos e vira história que a gente conta.
    >>  ............................................
  odd.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei.
    >>  ............................................
  odd.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong.
    >>  ............................................
    pt  Certo. Você não está errado.
    >>  ............................................
  odd.dialogue.conversations.family.child.play_along/3
    en  They are exactly that.
    >>  ............................................
    pt  São exatamente isso.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you. They'll grow out of half of it.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei. Metade disso eles superam.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. Families are like that and always have been.
    >>  ............................................
    pt  Certo. Você não está errado. Famílias são assim e sempre foram.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. Give it ten years and it'll be a story we tell.
    >>  ............................................
    pt  São exatamente isso. Dê dez anos e vira história que a gente conta.
    >>  ............................................
  peppy.dialogue.conversations.family.child.play_along/1
    en  They ARE! Don't tell them I agreed with you. I'll deny everything.
    >>  ............................................
    pt  Eles SÃO! Não conte que eu concordei com você. Eu vou negar tudo.
    >>  ............................................
  peppy.dialogue.conversations.family.child.play_along/2
    en  Right — you're not wrong. That stays between us, on pain of a very awkward supper.
    >>  ............................................
    pt  Certo — você não está errado. Isso fica entre nós, sob pena de um jantar constrangedor.
    >>  ............................................
  peppy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that! Ha. I've been waiting years for somebody to say it.
    >>  ............................................
    pt  São exatamente isso! Ha. Esperei anos por alguém dizer.
    >>  ............................................
  playful.dialogue.conversations.family.child.play_along/1
    en  They ARE! Don't tell them I agreed with you. I'll deny everything.
    >>  ............................................
    pt  Eles SÃO! Não conte que eu concordei com você. Eu vou negar tudo.
    >>  ............................................
  playful.dialogue.conversations.family.child.play_along/2
    en  Right — you're not wrong. That stays between us, on pain of a very awkward supper.
    >>  ............................................
    pt  Certo — você não está errado. Isso fica entre nós, sob pena de um jantar constrangedor.
    >>  ............................................
  playful.dialogue.conversations.family.child.play_along/3
    en  They are exactly that! Ha. I've been waiting years for somebody to say it.
    >>  ............................................
    pt  São exatamente isso! Ha. Esperei anos por alguém dizer.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed with you. They'll grow out of half of it.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei. Metade disso eles superam.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. Families are like that and always have been.
    >>  ............................................
    pt  Certo. Você não está errado. Famílias são assim e sempre foram.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.play_along/3
    en  They are exactly that. Give it ten years and it'll be a story we tell.
    >>  ............................................
    pt  São exatamente isso. Dê dez anos e vira história que a gente conta.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.play_along/1
    en  They ARE, %1$s. Don't tell them I agreed with you — they'd take it badly and they'd be right to.
    >>  ............................................
    pt  Eles SÃO, %1$s. Não conte que eu concordei — eles ficariam mal e com razão.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong. It's a relief to be allowed to say so once.
    >>  ............................................
    pt  Certo. Você não está errado. É um alívio poder dizer isso uma vez.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.play_along/3
    en  They are exactly that, and I feel awful for enjoying being told.
    >>  ............................................
    pt  São exatamente isso, e eu me sinto horrível por gostar de ouvir.
    >>  ............................................
  shy.dialogue.conversations.family.child.play_along/1
    en  They ARE. Don't tell them I agreed.
    >>  ............................................
    pt  Eles SÃO. Não conte que eu concordei.
    >>  ............................................
  shy.dialogue.conversations.family.child.play_along/2
    en  Right. You're not wrong.
    >>  ............................................
    pt  Certo. Você não está errado.
    >>  ............................................
  shy.dialogue.conversations.family.child.play_along/3
    en  They are exactly that.
    >>  ............................................
    pt  São exatamente isso.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.play_along/1
    en  They ARE! Don't tell them I agreed with you. I'll deny everything.
    >>  ............................................
    pt  Eles SÃO! Não conte que eu concordei com você. Eu vou negar tudo.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.play_along/2
    en  Right — you're not wrong. That stays between us, on pain of a very awkward supper.
    >>  ............................................
    pt  Certo — você não está errado. Isso fica entre nós, sob pena de um jantar constrangedor.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.play_along/3
    en  They are exactly that! Ha. I've been waiting years for somebody to say it.
    >>  ............................................
    pt  São exatamente isso! Ha. Esperei anos por alguém dizer.
    >>  ............................................
  witty.dialogue.conversations.family.child.play_along/1
    en  They ARE! Don't tell them I agreed with you. I'll deny everything.
    >>  ............................................
    pt  Eles SÃO! Não conte que eu concordei com você. Eu vou negar tudo.
    >>  ............................................
  witty.dialogue.conversations.family.child.play_along/2
    en  Right — you're not wrong. That stays between us, on pain of a very awkward supper.
    >>  ............................................
    pt  Certo — você não está errado. Isso fica entre nós, sob pena de um jantar constrangedor.
    >>  ............................................
  witty.dialogue.conversations.family.child.play_along/3
    en  They are exactly that! Ha. I've been waiting years for somebody to say it.
    >>  ............................................
    pt  São exatamente isso! Ha. Esperei anos por alguém dizer.
    >>  ............................................
```

</details>


### Button `scold` — "They need a firmer hand."

*stance family `respectful_disagreement` · tone `blunt` · answers the beat(s) `family.child.listen.to.checkin_child`, `family.child.praise.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.followup.scold` — accepted phrasings: "they need a firmer hand"; "you should be stricter with them"; "they need a firmer hand from you"
  - the message must contain one of: `firmer`, `strict`
  - scored words: `firmer`(1.5), `strict`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.followup.scold
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.followup.scold   [24 chars]
    en  They need a firmer hand.
    >>  ............................................
    pt  Precisam de mão mais firme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `family.child.scold`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -3, tension +4  _(recorded under topic `family.child.scold`)_
- Does: session `turn`
- Then opens: `conversations.topic.family.scolded.close`
- …where the player's next choices will be: "You know them. I don't." | "What do they need, then?" | "I'll not tell you your business."

```text
POOL   dialogue key: dialogue.conversations.family.child.scold
WHO    VILLAGER — what the player reads after pressing "They need a firmer hand."
       spoken on: conversations.topic.checkin_child.followup, button `scold`
       leaves the player on: conversations.topic.family.scolded.close
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.scolded`: the villager hurts. Subject `family.child`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   this is the line that establishes `family:child`, `player:urged_severity` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, empathy, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.child.scold/1   [44 chars]
    en  ...A firmer hand. That's not what they need.
    >>  ............................................
    pt  ...Mão mais firme. Não é disso que precisam.
    >>  ............................................
  dialogue.conversations.family.child.scold/2   [29 chars]
    en  I'll raise mine my way, %1$s.
    >>  ............................................
    pt  Vou criar os meus do meu jeito, %1$s.
    >>  ............................................
  dialogue.conversations.family.child.scold/3   [37 chars]
    en  Mm. I'll pretend you didn't say that.
    >>  ............................................
    pt  Hm. Vou fingir que você não disse isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's what I was given, %1$s, and I remember it.
    >>  ............................................
    pt  ...Mão mais firme. É o que eu recebi, %1$s, e eu lembro.
    >>  ............................................
  anxious.dialogue.conversations.family.child.scold/2
    en  No. Please. That isn't what they need and I know exactly why.
    >>  ............................................
    pt  Não. Por favor. Não é disso que eles precisam e eu sei exatamente por quê.
    >>  ............................................
  anxious.dialogue.conversations.family.child.scold/3
    en  ...I'd not do that to them. Not for anything.
    >>  ............................................
    pt  ...Eu não faria isso com eles. Por nada.
    >>  ............................................
  athletic.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. No. They'll come round in their own time.
    >>  ............................................
    pt  ...Mão mais firme. Não. Eles vão chegar lá no tempo deles.
    >>  ............................................
  athletic.dialogue.conversations.family.child.scold/2
    en  That's not the way with them, and I'm in no rush to force it.
    >>  ............................................
    pt  Não é o jeito com eles, e eu não tenho pressa de forçar.
    >>  ............................................
  athletic.dialogue.conversations.family.child.scold/3
    en  ...I'll not push them harder. Pushing has never yet worked.
    >>  ............................................
    pt  ...Não vou pressionar mais. Pressionar nunca funcionou.
    >>  ............................................
  confident.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's not what they need.
    >>  ............................................
    pt  ...Mão mais firme. Não é disso que eles precisam.
    >>  ............................................
  confident.dialogue.conversations.family.child.scold/2
    en  No. I've tried firm. Firm is what got us here.
    >>  ............................................
    pt  Não. Já tentei firmeza. Firmeza foi o que nos trouxe aqui.
    >>  ............................................
  confident.dialogue.conversations.family.child.scold/3
    en  ...I'll not take that advice, and I'd rather not hear it twice.
    >>  ............................................
    pt  ...Não vou seguir esse conselho, e prefiro não ouvir de novo.
    >>  ............................................
  crabby.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's not what they need.
    >>  ............................................
    pt  ...Mão mais firme. Não é disso que eles precisam.
    >>  ............................................
  crabby.dialogue.conversations.family.child.scold/2
    en  No. I've tried firm. Firm is what got us here.
    >>  ............................................
    pt  Não. Já tentei firmeza. Firmeza foi o que nos trouxe aqui.
    >>  ............................................
  crabby.dialogue.conversations.family.child.scold/3
    en  ...I'll not take that advice, and I'd rather not hear it twice.
    >>  ............................................
    pt  ...Não vou seguir esse conselho, e prefiro não ouvir de novo.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.scold/1
    en  ...That's not what they need, %1$s. I'd have hoped you'd see that.
    >>  ............................................
    pt  ...Não é disso que eles precisam, %1$s. Eu esperava que você visse.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.scold/2
    en  Firmer isn't the answer with mine. I'd tell you why if you'd ask.
    >>  ............................................
    pt  Mais firme não é a resposta com os meus. Eu te diria por quê se você perguntasse.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.scold/3
    en  ...I know you mean it kindly. It's still the wrong advice.
    >>  ............................................
    pt  ...Eu sei que você quer ajudar. Ainda é o conselho errado.
    >>  ............................................
  flirty.dialogue.conversations.family.child.scold/1
    en  ...That's not what they need, %1$s. I'd have hoped you'd see that.
    >>  ............................................
    pt  ...Não é disso que eles precisam, %1$s. Eu esperava que você visse.
    >>  ............................................
  flirty.dialogue.conversations.family.child.scold/2
    en  Firmer isn't the answer with mine. I'd tell you why if you'd ask.
    >>  ............................................
    pt  Mais firme não é a resposta com os meus. Eu te diria por quê se você perguntasse.
    >>  ............................................
  flirty.dialogue.conversations.family.child.scold/3
    en  ...I know you mean it kindly. It's still the wrong advice.
    >>  ............................................
    pt  ...Eu sei que você quer ajudar. Ainda é o conselho errado.
    >>  ............................................
  friendly.dialogue.conversations.family.child.scold/1
    en  ...That's not what they need, %1$s. I'd have hoped you'd see that.
    >>  ............................................
    pt  ...Não é disso que eles precisam, %1$s. Eu esperava que você visse.
    >>  ............................................
  friendly.dialogue.conversations.family.child.scold/2
    en  Firmer isn't the answer with mine. I'd tell you why if you'd ask.
    >>  ............................................
    pt  Mais firme não é a resposta com os meus. Eu te diria por quê se você perguntasse.
    >>  ............................................
  friendly.dialogue.conversations.family.child.scold/3
    en  ...I know you mean it kindly. It's still the wrong advice.
    >>  ............................................
    pt  ...Eu sei que você quer ajudar. Ainda é o conselho errado.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's what I was given, %1$s, and I remember it.
    >>  ............................................
    pt  ...Mão mais firme. É o que eu recebi, %1$s, e eu lembro.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.scold/2
    en  No. Please. That isn't what they need and I know exactly why.
    >>  ............................................
    pt  Não. Por favor. Não é disso que eles precisam e eu sei exatamente por quê.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.scold/3
    en  ...I'd not do that to them. Not for anything.
    >>  ............................................
    pt  ...Eu não faria isso com eles. Por nada.
    >>  ............................................
  greedy.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's not what they need.
    >>  ............................................
    pt  ...Mão mais firme. Não é disso que eles precisam.
    >>  ............................................
  greedy.dialogue.conversations.family.child.scold/2
    en  No. I've tried firm. Firm is what got us here.
    >>  ............................................
    pt  Não. Já tentei firmeza. Firmeza foi o que nos trouxe aqui.
    >>  ............................................
  greedy.dialogue.conversations.family.child.scold/3
    en  ...I'll not take that advice, and I'd rather not hear it twice.
    >>  ............................................
    pt  ...Não vou seguir esse conselho, e prefiro não ouvir de novo.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's not what they need.
    >>  ............................................
    pt  ...Mão mais firme. Não é disso que eles precisam.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.scold/2
    en  No. I've tried firm. Firm is what got us here.
    >>  ............................................
    pt  Não. Já tentei firmeza. Firmeza foi o que nos trouxe aqui.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.scold/3
    en  ...I'll not take that advice, and I'd rather not hear it twice.
    >>  ............................................
    pt  ...Não vou seguir esse conselho, e prefiro não ouvir de novo.
    >>  ............................................
  introverted.dialogue.conversations.family.child.scold/1
    en  ...That isn't what they need.
    >>  ............................................
    pt  ...Não é disso que eles precisam.
    >>  ............................................
  introverted.dialogue.conversations.family.child.scold/2
    en  No. Firmer is not the missing part.
    >>  ............................................
    pt  Não. Firmeza não é a parte que falta.
    >>  ............................................
  introverted.dialogue.conversations.family.child.scold/3
    en  ...I've tried that. It didn't go well.
    >>  ............................................
    pt  ...Já tentei isso. Não deu certo.
    >>  ............................................
  lazy.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. No. They'll come round in their own time.
    >>  ............................................
    pt  ...Mão mais firme. Não. Eles vão chegar lá no tempo deles.
    >>  ............................................
  lazy.dialogue.conversations.family.child.scold/2
    en  That's not the way with them, and I'm in no rush to force it.
    >>  ............................................
    pt  Não é o jeito com eles, e eu não tenho pressa de forçar.
    >>  ............................................
  lazy.dialogue.conversations.family.child.scold/3
    en  ...I'll not push them harder. Pushing has never yet worked.
    >>  ............................................
    pt  ...Não vou pressionar mais. Pressionar nunca funcionou.
    >>  ............................................
  odd.dialogue.conversations.family.child.scold/1
    en  ...That isn't what they need.
    >>  ............................................
    pt  ...Não é disso que eles precisam.
    >>  ............................................
  odd.dialogue.conversations.family.child.scold/2
    en  No. Firmer is not the missing part.
    >>  ............................................
    pt  Não. Firmeza não é a parte que falta.
    >>  ............................................
  odd.dialogue.conversations.family.child.scold/3
    en  ...I've tried that. It didn't go well.
    >>  ............................................
    pt  ...Já tentei isso. Não deu certo.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. No. They'll come round in their own time.
    >>  ............................................
    pt  ...Mão mais firme. Não. Eles vão chegar lá no tempo deles.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.scold/2
    en  That's not the way with them, and I'm in no rush to force it.
    >>  ............................................
    pt  Não é o jeito com eles, e eu não tenho pressa de forçar.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.scold/3
    en  ...I'll not push them harder. Pushing has never yet worked.
    >>  ............................................
    pt  ...Não vou pressionar mais. Pressionar nunca funcionou.
    >>  ............................................
  peppy.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand! Yes. That'll fix a frightened child, will it.
    >>  ............................................
    pt  ...Mão mais firme! Sim. Isso vai consertar uma criança assustada, vai?
    >>  ............................................
  peppy.dialogue.conversations.family.child.scold/2
    en  Right. Thank you. I'll add that to the list of things I won't do.
    >>  ............................................
    pt  Certo. Obrigado. Vou acrescentar à lista do que eu não vou fazer.
    >>  ............................................
  peppy.dialogue.conversations.family.child.scold/3
    en  ...Firmer. Yes. Marvellous. Anything else?
    >>  ............................................
    pt  ...Mais firme. Sim. Maravilhoso. Mais alguma coisa?
    >>  ............................................
  playful.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand! Yes. That'll fix a frightened child, will it.
    >>  ............................................
    pt  ...Mão mais firme! Sim. Isso vai consertar uma criança assustada, vai?
    >>  ............................................
  playful.dialogue.conversations.family.child.scold/2
    en  Right. Thank you. I'll add that to the list of things I won't do.
    >>  ............................................
    pt  Certo. Obrigado. Vou acrescentar à lista do que eu não vou fazer.
    >>  ............................................
  playful.dialogue.conversations.family.child.scold/3
    en  ...Firmer. Yes. Marvellous. Anything else?
    >>  ............................................
    pt  ...Mais firme. Sim. Maravilhoso. Mais alguma coisa?
    >>  ............................................
  relaxed.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. No. They'll come round in their own time.
    >>  ............................................
    pt  ...Mão mais firme. Não. Eles vão chegar lá no tempo deles.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.scold/2
    en  That's not the way with them, and I'm in no rush to force it.
    >>  ............................................
    pt  Não é o jeito com eles, e eu não tenho pressa de forçar.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.scold/3
    en  ...I'll not push them harder. Pushing has never yet worked.
    >>  ............................................
    pt  ...Não vou pressionar mais. Pressionar nunca funcionou.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand. That's what I was given, %1$s, and I remember it.
    >>  ............................................
    pt  ...Mão mais firme. É o que eu recebi, %1$s, e eu lembro.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.scold/2
    en  No. Please. That isn't what they need and I know exactly why.
    >>  ............................................
    pt  Não. Por favor. Não é disso que eles precisam e eu sei exatamente por quê.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.scold/3
    en  ...I'd not do that to them. Not for anything.
    >>  ............................................
    pt  ...Eu não faria isso com eles. Por nada.
    >>  ............................................
  shy.dialogue.conversations.family.child.scold/1
    en  ...That isn't what they need.
    >>  ............................................
    pt  ...Não é disso que eles precisam.
    >>  ............................................
  shy.dialogue.conversations.family.child.scold/2
    en  No. Firmer is not the missing part.
    >>  ............................................
    pt  Não. Firmeza não é a parte que falta.
    >>  ............................................
  shy.dialogue.conversations.family.child.scold/3
    en  ...I've tried that. It didn't go well.
    >>  ............................................
    pt  ...Já tentei isso. Não deu certo.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand! Yes. That'll fix a frightened child, will it.
    >>  ............................................
    pt  ...Mão mais firme! Sim. Isso vai consertar uma criança assustada, vai?
    >>  ............................................
  upbeat.dialogue.conversations.family.child.scold/2
    en  Right. Thank you. I'll add that to the list of things I won't do.
    >>  ............................................
    pt  Certo. Obrigado. Vou acrescentar à lista do que eu não vou fazer.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.scold/3
    en  ...Firmer. Yes. Marvellous. Anything else?
    >>  ............................................
    pt  ...Mais firme. Sim. Maravilhoso. Mais alguma coisa?
    >>  ............................................
  witty.dialogue.conversations.family.child.scold/1
    en  ...A firmer hand! Yes. That'll fix a frightened child, will it.
    >>  ............................................
    pt  ...Mão mais firme! Sim. Isso vai consertar uma criança assustada, vai?
    >>  ............................................
  witty.dialogue.conversations.family.child.scold/2
    en  Right. Thank you. I'll add that to the list of things I won't do.
    >>  ............................................
    pt  Certo. Obrigado. Vou acrescentar à lista do que eu não vou fazer.
    >>  ............................................
  witty.dialogue.conversations.family.child.scold/3
    en  ...Firmer. Yes. Marvellous. Anything else?
    >>  ............................................
    pt  ...Mais firme. Sim. Maravilhoso. Mais alguma coisa?
    >>  ............................................
```

</details>


### Button `leave` — "I'll not keep you."

*stance family `exit` · tone `plain` · answers the beat(s) `family.child.listen.to.checkin_child`, `family.child.praise.to.checkin_child` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.followup.leave   [18 chars]
    en  I'll not keep you.
    >>  ............................................
    pt  Não vou te prender.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.child.leave
WHO    VILLAGER — what the player reads after pressing "I'll not keep you."
       spoken on: conversations.topic.checkin_child.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.checkin_child.respond / leave
```

```text
  dialogue.conversations.family.child.leave/1   [43 chars]
    en  Quite. They'll want feeding shortly anyway.
    >>  ............................................
    pt  Exato. Vão querer comer daqui a pouco de todo jeito.
    >>  ............................................
  dialogue.conversations.family.child.leave/2   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
  dialogue.conversations.family.child.leave/3   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
```

---


## `conversations.topic.checkin_child.promise`

**Reached from 1 route(s):** `conversations.topic.checkin_child.respond` / `ask_promise`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin_child.promise` — e.g. "The river. You said the river, twice, and it's been dry weather since."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.promise
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin_child.promise
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin_child.promise   [39 chars]
    en  You did say it. I'm only reminding you.
    >>  ............................................
    pt  Você disse mesmo. Só estou lembrando.
    >>  ............................................
```


### Button `keep_it` — "Then we'll do it this week."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `checkin_child.promise`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.promise.keep` — accepted phrasings: "then we will do it this week"; "we will do it this week"; "let us do it this week"
  - the message must contain one of: `week`
  - scored words: `week`(1.0), `do`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.promise.keep_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.promise
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.promise.keep_it   [27 chars]
    en  Then we'll do it this week.
    >>  ............................................
    pt  Então a gente faz isso esta semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `checkin_child.promise.keep`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +3  _(recorded under topic `checkin_child.promise.keep`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.promise.keep
WHO    VILLAGER — what the player reads after pressing "Then we'll do it this week."
       spoken on: conversations.topic.checkin_child.promise, button `keep_it`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.promise.keep`: the villager accepts. Subject `checkin_child.promise`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin_child.promise.keep/1   [70 chars]
    en  This week. I'm saying it out loud so you can't quietly mean next week.
    >>  ............................................
    pt  Esta semana. Estou dizendo alto pra você não querer dizer semana que vem.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.keep/2   [78 chars]
    en  Fair. I'll not mention it again until the day, and then I'll mention it a lot.
    >>  ............................................
    pt  Justo. Não menciono mais até o dia, e aí eu menciono bastante.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.keep/3   [71 chars]
    en  Good. I'd stopped expecting it, and I'd rather go back to expecting it.
    >>  ............................................
    pt  Bom. Eu tinha parado de esperar, e prefiro voltar a esperar.
    >>  ............................................
```


### Button `own_the_miss` — "I said it and I didn't do it. That's on me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `checkin_child.promise`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.promise.owned` — accepted phrasings: "that is my fault"; "i said it and i did not do it"; "that one is on me"
  - the message must contain one of: `fault`
  - scored words: `mine`(0.6), `fault`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.promise.own_the_miss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.promise
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.promise.own_the_miss   [43 chars]
    en  I said it and I didn't do it. That's on me.
    >>  ............................................
    pt  Eu disse e não fiz. A culpa é minha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `checkin_child.promise.owned`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +1  _(recorded under topic `checkin_child.promise.owned`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.promise.owned
WHO    VILLAGER — what the player reads after pressing "I said it and I didn't do it. That's on me."
       spoken on: conversations.topic.checkin_child.promise, button `own_the_miss`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.promise.owned`: the villager accepts. Subject `checkin_child.promise`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin_child.promise.owned/1   [64 chars]
    en  ...Nobody says that to me. Grown people usually explain instead.
    >>  ............................................
    pt  ...Ninguém me diz isso. Os adultos geralmente explicam.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.owned/2   [66 chars]
    en  Alright. I'd been practising being angry about it and now I can't.
    >>  ............................................
    pt  Está bem. Eu vinha treinando ficar bravo e agora não consigo.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.owned/3   [57 chars]
    en  Then I'll stop counting. That was the only part I wanted.
    >>  ............................................
    pt  Então eu paro de contar. Era só essa parte que eu queria.
    >>  ............................................
```


### Button `leave` — "We'll talk about it later."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin_child.promise` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.promise.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.promise
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.promise.leave   [26 chars]
    en  We'll talk about it later.
    >>  ............................................
    pt  A gente conversa sobre isso depois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.promise.leave
WHO    VILLAGER — what the player reads after pressing "We'll talk about it later."
       spoken on: conversations.topic.checkin_child.promise, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.promise.leave`: the villager accepts. Subject `checkin_child.promise`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.checkin_child.promise.leave/1   [9 chars]
    en  ...Later.
    >>  ............................................
    pt  ...Depois.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.leave/2   [31 chars]
    en  That's what you said last time.
    >>  ............................................
    pt  Foi o que você disse da última vez.
    >>  ............................................
  dialogue.conversations.checkin_child.promise.leave/3   [8 chars]
    en  Alright.
    >>  ............................................
    pt  Está bem.
    >>  ............................................
```

---


## `conversations.topic.checkin_child.rebuffed.followup`

**Reached from 1 route(s):** `conversations.topic.checkin_child.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.child.dismiss` — e.g. "...Mine aren't. Mine are theirs."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.rebuffed.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin_child.rebuffed.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin_child.rebuffed.followup   [16 chars]
    en  Mine are theirs.
    >>  ............................................
    pt  Os meus são deles.
    >>  ............................................
```


### Button `apologize` — "That was a careless thing to say about your child."

*stance family `candor` · tone `gentle` · outcome `accepted` · answers the beat(s) `checkin_child.rebuffed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.rebuffed.apologize` — accepted phrasings: "that was a careless thing to say about your child"
  - the message must contain one of: `careless`, `child`
  - scored words: `careless`(1.5), `child`(1.2), `said`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.rebuffed.followup.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.rebuffed.followup.apologize   [50 chars]
    en  That was a careless thing to say about your child.
    >>  ............................................
    pt  Foi uma coisa descuidada de se dizer sobre seu filho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -3  _(recorded under topic `checkin_child.rebuffed.apologize`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.rebuffed.apologize
WHO    VILLAGER — what the player reads after pressing "That was a careless thing to say about your child."
       spoken on: conversations.topic.checkin_child.rebuffed.followup, button `apologize`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.rebuffed.apologize`: the villager qualifys. Subject `checkin_child.wellbeing`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin_child.rebuffed.apologize/1   [61 chars]
    en  ...It was. Thank you for noticing before I had to explain it.
    >>  ............................................
    pt  ...Foi. Obrigado por perceber antes de eu ter que explicar.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.apologize/2   [63 chars]
    en  Careless. Aye. People are, about other people's children, %1$s.
    >>  ............................................
    pt  Descuidado. É. As pessoas são, com filho dos outros, %1$s.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.apologize/3   [78 chars]
    en  Then we'll not speak of it again and I'll go on being insufferable about them.
    >>  ............................................
    pt  Então não falamos mais disso e eu sigo sendo insuportável sobre eles.
    >>  ............................................
```


### Button `explain` — "I meant no harm by it. It didn't come out that way."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `checkin_child.rebuffed.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.rebuffed.explain` — accepted phrasings: "i meant no harm by it. it didn't come out that way"
  - the message must contain one of: `meant`, `came`, `wrongly`
  - scored words: `meant`(1.5), `came`(1.2), `wrongly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.rebuffed.followup.explain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.rebuffed.followup.explain   [51 chars]
    en  I meant no harm by it. It didn't come out that way.
    >>  ............................................
    pt  Não falei por mal. Não saiu assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — tension -1  _(recorded under topic `checkin_child.rebuffed.explain`)_
- Does: session `turn`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.rebuffed.explain
WHO    VILLAGER — what the player reads after pressing "I meant no harm by it. It didn't come out that way."
       spoken on: conversations.topic.checkin_child.rebuffed.followup, button `explain`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.rebuffed.explain`: the villager qualifys. Subject `checkin_child.wellbeing`, polarity `negative`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin_child.rebuffed.explain/1   [63 chars]
    en  ...Nothing about them is light to me. That's the whole trouble.
    >>  ............................................
    pt  ...Nada sobre eles é leve pra mim. É esse o problema.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.explain/2   [66 chars]
    en  Lightly. Right. I'd take the light version, if you have one, %1$s.
    >>  ............................................
    pt  Sem peso. Certo. Eu aceitaria a versão leve, se você tiver, %1$s.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.explain/3   [59 chars]
    en  Then say the light one and I'll pretend I heard that first.
    >>  ............................................
    pt  Então diga a leve e eu finjo que ouvi essa primeiro.
    >>  ............................................
```


### Button `leave` — "I'll say no more about them."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `checkin_child.rebuffed.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.rebuffed.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.rebuffed.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.rebuffed.followup.leave   [28 chars]
    en  I'll say no more about them.
    >>  ............................................
    pt  Não falo mais deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.rebuffed.leave
WHO    VILLAGER — what the player reads after pressing "I'll say no more about them."
       spoken on: conversations.topic.checkin_child.rebuffed.followup, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.rebuffed.leave`: the villager accepts. Subject `checkin_child.wellbeing`, polarity `negative`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.checkin_child.rebuffed.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.leave/2   [32 chars]
    en  So I've found. Off you go, %1$s.
    >>  ............................................
    pt  Foi o que eu vi. Pode ir, %1$s.
    >>  ............................................
  dialogue.conversations.checkin_child.rebuffed.leave/3   [18 chars]
    en  Mm. Probably wise.
    >>  ............................................
    pt  Mm. Provavelmente sábio.
    >>  ............................................
```

---


## `conversations.topic.checkin_child.respond`

**Reached from 4 route(s):** `conversations.family` / `checkin_child`; `conversations.family` / `checkin_child`; `conversations.family` / `checkin_child`; `conversations.family` / `checkin_child`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.family.child.again` — e.g. "You already asked! Nothing new happened since then. Well. One thing. No, nothing."
- `conversations.family.child.doing` — e.g. "Pretty good! I beat the miller's kid at races twice. TWICE."
- `conversations.family.child.little` — e.g. "Up! Up! I found a bug and it's my friend now!"
- `conversations.family.child.teen` — e.g. "I'm fine. Everyone keeps asking. I'm FINE. ...Thanks for asking, though."


```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.checkin_child.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.checkin_child.respond   [30 chars]
    en  That's how they're getting on.
    >>  ............................................
    pt  É assim que eles estão indo.
    >>  ............................................
```


### Button `listen` — "Tell me how they really are."

*stance family `restraint` · tone `gentle` · answers the beat(s) `family.child.again.to.checkin_child`, `family.child.doing.to.checkin_child`, `family.child.little.to.checkin_child`, `family.child.teen.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.listen` — accepted phrasings: "tell me how they really are"; "how are they really doing"; "give me the honest version about them"
  - scored words: `how`(0.4), `really`(0.8), `them`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond.listen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.respond.listen   [28 chars]
    en  Tell me how they really are.
    >>  ............................................
    pt  Me conta como eles realmente estão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.child.listen`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `family.child.listen`)_
- Then opens: `conversations.topic.checkin_child.followup`
- …where the player's next choices will be: "Here's what helped me." | "They sound wonderful." | "They need a firmer hand." | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.listen
WHO    VILLAGER — what the player reads after pressing "Tell me how they really are."
       spoken on: conversations.topic.checkin_child.respond, button `listen`
       leaves the player on: conversations.topic.checkin_child.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.listen.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.listen/1   [66 chars]
    en  ...Really are. Right. Not as well as I tell people, if I'm honest.
    >>  ............................................
    pt  ...Realmente estão. Certo. Não tão bem quanto eu digo às pessoas, para ser sincero.
    >>  ............................................
  dialogue.conversations.family.child.listen/2   [67 chars]
    en  You want the true answer about them. That's kind of you to ask for.
    >>  ............................................
    pt  Você quer a resposta verdadeira sobre eles. É gentil da sua parte pedir.
    >>  ............................................
  dialogue.conversations.family.child.listen/3   [61 chars]
    en  Better than last month. Worse than I'd like. That's children.
    >>  ............................................
    pt  Melhor que mês passado. Pior do que eu gostaria. É assim com crianças.
    >>  ............................................
```


### Button `praise` — "You're doing right by them."

*stance family `encouragement` · tone `plain` · answers the beat(s) `family.child.again.to.checkin_child`, `family.child.doing.to.checkin_child`, `family.child.little.to.checkin_child`, `family.child.teen.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.praise` — accepted phrasings: "you are doing right by them"; "you are a good parent to them"; "you are raising them well"
  - scored words: `doing`(0.5), `right`(0.8), `them`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond.praise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.respond.praise   [27 chars]
    en  You're doing right by them.
    >>  ............................................
    pt  Você está fazendo o certo por eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `family.child.praise`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth +5  _(recorded under topic `family.child.praise`)_
- Then opens: `conversations.topic.checkin_child.followup`
- …where the player's next choices will be: "Here's what helped me." | "They sound wonderful." | "They need a firmer hand." | "I'll not keep you."

```text
POOL   dialogue key: dialogue.conversations.family.child.praise
WHO    VILLAGER — what the player reads after pressing "You're doing right by them."
       spoken on: conversations.topic.checkin_child.respond, button `praise`
       leaves the player on: conversations.topic.checkin_child.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.praise.to.checkin_child`: the villager accepts. Subject `checkin_child`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.family.child.praise/1   [39 chars]
    en  ...Am I? Some days I'm certain I'm not.
    >>  ............................................
    pt  ...Estou? Alguns dias tenho certeza de que não.
    >>  ............................................
  dialogue.conversations.family.child.praise/2   [58 chars]
    en  That's the thing nobody says to a parent. Thank you, %1$s.
    >>  ............................................
    pt  É isso que ninguém diz a um pai. Obrigado, %1$s.
    >>  ............................................
  dialogue.conversations.family.child.praise/3   [45 chars]
    en  I try. It's good to be told the trying shows.
    >>  ............................................
    pt  Eu tento. É bom ouvir que o esforço aparece.
    >>  ............................................
```


### Button `dismiss` — "Children are all the same."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `family.child.again.to.checkin_child`, `family.child.doing.to.checkin_child`, `family.child.little.to.checkin_child`, `family.child.teen.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.dismiss` — accepted phrasings: "children are all the same"; "all children are like that"; "every child is the same"
  - the message must contain one of: `children`
  - scored words: `children`(1.2), `same`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.respond.dismiss   [26 chars]
    en  Children are all the same.
    >>  ............................................
    pt  Crianças são todas iguais.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -2** — decision id `family.child.dismiss`, budget `relationship`, replay policy `daily_repeat`
- Does: disposition — warmth -5, tension +5  _(recorded under topic `family.child.dismiss`)_
- Does: session `turn`
- Then opens: `conversations.topic.checkin_child.rebuffed.followup`
- …where the player's next choices will be: "That was a careless thing to say about your child." | "I meant no harm by it. It didn't come out that way." | "I'll say no more about them."

```text
POOL   dialogue key: dialogue.conversations.family.child.dismiss
WHO    VILLAGER — what the player reads after pressing "Children are all the same."
       spoken on: conversations.topic.checkin_child.respond, button `dismiss`
       leaves the player on: conversations.topic.checkin_child.rebuffed.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.rebuffed.open`: the villager hurts. Subject `checkin_child.wellbeing`, polarity `negative`, closes subject, outcome `hurt`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.family.child.dismiss/1   [32 chars]
    en  ...Mine aren't. Mine are theirs.
    >>  ............................................
    pt  ...Os meus não. Os meus são eles mesmos.
    >>  ............................................
  dialogue.conversations.family.child.dismiss/2   [49 chars]
    en  That's a thing said by someone without any, %1$s.
    >>  ............................................
    pt  Isso é coisa que se diz sem ter nenhum, %1$s.
    >>  ............................................
  dialogue.conversations.family.child.dismiss/3   [51 chars]
    en  Understood. I'll stop telling you about them, then.
    >>  ............................................
    pt  Entendido. Então paro de te contar sobre eles.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Please don't say that about them, %1$s.
    >>  ............................................
    pt  ...Os meus não. Por favor, não diga isso deles, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.family.child.dismiss/2
    en  They're theirs. I've spent years making sure of it.
    >>  ............................................
    pt  Eles são deles. Passei anos garantindo isso.
    >>  ............................................
  anxious.dialogue.conversations.family.child.dismiss/3
    en  ...I know what people say. I'd hoped not from you.
    >>  ............................................
    pt  ...Eu sei o que dizem. Eu esperava que não de você.
    >>  ............................................
  athletic.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. They came out as themselves and I let them.
    >>  ............................................
    pt  ...Os meus não. Saíram como eles mesmos e eu deixei.
    >>  ............................................
  athletic.dialogue.conversations.family.child.dismiss/2
    en  Not mine, no. They've their own way of going about it.
    >>  ............................................
    pt  Os meus não. Eles têm o jeito deles de fazer as coisas.
    >>  ............................................
  athletic.dialogue.conversations.family.child.dismiss/3
    en  ...I'd not put them in with everyone else's.
    >>  ............................................
    pt  ...Eu não os poria junto com os dos outros.
    >>  ............................................
  confident.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Mine are theirs.
    >>  ............................................
    pt  ...Os meus não. Os meus são deles mesmos.
    >>  ............................................
  confident.dialogue.conversations.family.child.dismiss/2
    en  They're not a category. They're two people with names.
    >>  ............................................
    pt  Eles não são uma categoria. São duas pessoas com nome.
    >>  ............................................
  confident.dialogue.conversations.family.child.dismiss/3
    en  ...I'll not have them talked about like that.
    >>  ............................................
    pt  ...Não vou deixar falarem deles assim.
    >>  ............................................
  crabby.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Mine are theirs.
    >>  ............................................
    pt  ...Os meus não. Os meus são deles mesmos.
    >>  ............................................
  crabby.dialogue.conversations.family.child.dismiss/2
    en  They're not a category. They're two people with names.
    >>  ............................................
    pt  Eles não são uma categoria. São duas pessoas com nome.
    >>  ............................................
  crabby.dialogue.conversations.family.child.dismiss/3
    en  ...I'll not have them talked about like that.
    >>  ............................................
    pt  ...Não vou deixar falarem deles assim.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't, %1$s. Mine are entirely their own people.
    >>  ............................................
    pt  ...Os meus não, %1$s. Os meus são completamente eles mesmos.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.dismiss/2
    en  That's not how it goes with them. I'd have told you if you'd asked.
    >>  ............................................
    pt  Não é assim com eles. Eu teria te contado se você perguntasse.
    >>  ............................................
  extroverted.dialogue.conversations.family.child.dismiss/3
    en  ...I'd rather you met them before you decided that.
    >>  ............................................
    pt  ...Eu preferia que você os conhecesse antes de decidir isso.
    >>  ............................................
  flirty.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't, %1$s. Mine are entirely their own people.
    >>  ............................................
    pt  ...Os meus não, %1$s. Os meus são completamente eles mesmos.
    >>  ............................................
  flirty.dialogue.conversations.family.child.dismiss/2
    en  That's not how it goes with them. I'd have told you if you'd asked.
    >>  ............................................
    pt  Não é assim com eles. Eu teria te contado se você perguntasse.
    >>  ............................................
  flirty.dialogue.conversations.family.child.dismiss/3
    en  ...I'd rather you met them before you decided that.
    >>  ............................................
    pt  ...Eu preferia que você os conhecesse antes de decidir isso.
    >>  ............................................
  friendly.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't, %1$s. Mine are entirely their own people.
    >>  ............................................
    pt  ...Os meus não, %1$s. Os meus são completamente eles mesmos.
    >>  ............................................
  friendly.dialogue.conversations.family.child.dismiss/2
    en  That's not how it goes with them. I'd have told you if you'd asked.
    >>  ............................................
    pt  Não é assim com eles. Eu teria te contado se você perguntasse.
    >>  ............................................
  friendly.dialogue.conversations.family.child.dismiss/3
    en  ...I'd rather you met them before you decided that.
    >>  ............................................
    pt  ...Eu preferia que você os conhecesse antes de decidir isso.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Please don't say that about them, %1$s.
    >>  ............................................
    pt  ...Os meus não. Por favor, não diga isso deles, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.dismiss/2
    en  They're theirs. I've spent years making sure of it.
    >>  ............................................
    pt  Eles são deles. Passei anos garantindo isso.
    >>  ............................................
  gloomy.dialogue.conversations.family.child.dismiss/3
    en  ...I know what people say. I'd hoped not from you.
    >>  ............................................
    pt  ...Eu sei o que dizem. Eu esperava que não de você.
    >>  ............................................
  greedy.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Mine are theirs.
    >>  ............................................
    pt  ...Os meus não. Os meus são deles mesmos.
    >>  ............................................
  greedy.dialogue.conversations.family.child.dismiss/2
    en  They're not a category. They're two people with names.
    >>  ............................................
    pt  Eles não são uma categoria. São duas pessoas com nome.
    >>  ............................................
  greedy.dialogue.conversations.family.child.dismiss/3
    en  ...I'll not have them talked about like that.
    >>  ............................................
    pt  ...Não vou deixar falarem deles assim.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Mine are theirs.
    >>  ............................................
    pt  ...Os meus não. Os meus são deles mesmos.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.dismiss/2
    en  They're not a category. They're two people with names.
    >>  ............................................
    pt  Eles não são uma categoria. São duas pessoas com nome.
    >>  ............................................
  grumpy.dialogue.conversations.family.child.dismiss/3
    en  ...I'll not have them talked about like that.
    >>  ............................................
    pt  ...Não vou deixar falarem deles assim.
    >>  ............................................
  introverted.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't.
    >>  ............................................
    pt  ...Os meus não.
    >>  ............................................
  introverted.dialogue.conversations.family.child.dismiss/2
    en  That isn't true of them.
    >>  ............................................
    pt  Isso não é verdade sobre eles.
    >>  ............................................
  introverted.dialogue.conversations.family.child.dismiss/3
    en  ...No. Not mine.
    >>  ............................................
    pt  ...Não. Os meus não.
    >>  ............................................
  lazy.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. They came out as themselves and I let them.
    >>  ............................................
    pt  ...Os meus não. Saíram como eles mesmos e eu deixei.
    >>  ............................................
  lazy.dialogue.conversations.family.child.dismiss/2
    en  Not mine, no. They've their own way of going about it.
    >>  ............................................
    pt  Os meus não. Eles têm o jeito deles de fazer as coisas.
    >>  ............................................
  lazy.dialogue.conversations.family.child.dismiss/3
    en  ...I'd not put them in with everyone else's.
    >>  ............................................
    pt  ...Eu não os poria junto com os dos outros.
    >>  ............................................
  odd.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't.
    >>  ............................................
    pt  ...Os meus não.
    >>  ............................................
  odd.dialogue.conversations.family.child.dismiss/2
    en  That isn't true of them.
    >>  ............................................
    pt  Isso não é verdade sobre eles.
    >>  ............................................
  odd.dialogue.conversations.family.child.dismiss/3
    en  ...No. Not mine.
    >>  ............................................
    pt  ...Não. Os meus não.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. They came out as themselves and I let them.
    >>  ............................................
    pt  ...Os meus não. Saíram como eles mesmos e eu deixei.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.dismiss/2
    en  Not mine, no. They've their own way of going about it.
    >>  ............................................
    pt  Os meus não. Eles têm o jeito deles de fazer as coisas.
    >>  ............................................
  peaceful.dialogue.conversations.family.child.dismiss/3
    en  ...I'd not put them in with everyone else's.
    >>  ............................................
    pt  ...Eu não os poria junto com os dos outros.
    >>  ............................................
  peppy.dialogue.conversations.family.child.dismiss/1
    en  ...Ha. No. Mine came out entirely their own, thank you.
    >>  ............................................
    pt  ...Ha. Não. Os meus saíram completamente deles mesmos, obrigado.
    >>  ............................................
  peppy.dialogue.conversations.family.child.dismiss/2
    en  Right! Well. You've not met them, clearly.
    >>  ............................................
    pt  Certo! Bom. Claramente você não os conhece.
    >>  ............................................
  peppy.dialogue.conversations.family.child.dismiss/3
    en  ...Mine are theirs. Ask them, they'll tell you at length.
    >>  ............................................
    pt  ...Os meus são deles. Pergunte, eles contam longamente.
    >>  ............................................
  playful.dialogue.conversations.family.child.dismiss/1
    en  ...Ha. No. Mine came out entirely their own, thank you.
    >>  ............................................
    pt  ...Ha. Não. Os meus saíram completamente deles mesmos, obrigado.
    >>  ............................................
  playful.dialogue.conversations.family.child.dismiss/2
    en  Right! Well. You've not met them, clearly.
    >>  ............................................
    pt  Certo! Bom. Claramente você não os conhece.
    >>  ............................................
  playful.dialogue.conversations.family.child.dismiss/3
    en  ...Mine are theirs. Ask them, they'll tell you at length.
    >>  ............................................
    pt  ...Os meus são deles. Pergunte, eles contam longamente.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. They came out as themselves and I let them.
    >>  ............................................
    pt  ...Os meus não. Saíram como eles mesmos e eu deixei.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.dismiss/2
    en  Not mine, no. They've their own way of going about it.
    >>  ............................................
    pt  Os meus não. Eles têm o jeito deles de fazer as coisas.
    >>  ............................................
  relaxed.dialogue.conversations.family.child.dismiss/3
    en  ...I'd not put them in with everyone else's.
    >>  ............................................
    pt  ...Eu não os poria junto com os dos outros.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't. Please don't say that about them, %1$s.
    >>  ............................................
    pt  ...Os meus não. Por favor, não diga isso deles, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.dismiss/2
    en  They're theirs. I've spent years making sure of it.
    >>  ............................................
    pt  Eles são deles. Passei anos garantindo isso.
    >>  ............................................
  sensitive.dialogue.conversations.family.child.dismiss/3
    en  ...I know what people say. I'd hoped not from you.
    >>  ............................................
    pt  ...Eu sei o que dizem. Eu esperava que não de você.
    >>  ............................................
  shy.dialogue.conversations.family.child.dismiss/1
    en  ...Mine aren't.
    >>  ............................................
    pt  ...Os meus não.
    >>  ............................................
  shy.dialogue.conversations.family.child.dismiss/2
    en  That isn't true of them.
    >>  ............................................
    pt  Isso não é verdade sobre eles.
    >>  ............................................
  shy.dialogue.conversations.family.child.dismiss/3
    en  ...No. Not mine.
    >>  ............................................
    pt  ...Não. Os meus não.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.dismiss/1
    en  ...Ha. No. Mine came out entirely their own, thank you.
    >>  ............................................
    pt  ...Ha. Não. Os meus saíram completamente deles mesmos, obrigado.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.dismiss/2
    en  Right! Well. You've not met them, clearly.
    >>  ............................................
    pt  Certo! Bom. Claramente você não os conhece.
    >>  ............................................
  upbeat.dialogue.conversations.family.child.dismiss/3
    en  ...Mine are theirs. Ask them, they'll tell you at length.
    >>  ............................................
    pt  ...Os meus são deles. Pergunte, eles contam longamente.
    >>  ............................................
  witty.dialogue.conversations.family.child.dismiss/1
    en  ...Ha. No. Mine came out entirely their own, thank you.
    >>  ............................................
    pt  ...Ha. Não. Os meus saíram completamente deles mesmos, obrigado.
    >>  ............................................
  witty.dialogue.conversations.family.child.dismiss/2
    en  Right! Well. You've not met them, clearly.
    >>  ............................................
    pt  Certo! Bom. Claramente você não os conhece.
    >>  ............................................
  witty.dialogue.conversations.family.child.dismiss/3
    en  ...Mine are theirs. Ask them, they'll tell you at length.
    >>  ............................................
    pt  ...Os meus são deles. Pergunte, eles contam longamente.
    >>  ............................................
```

</details>


### Button `ask_promise` — "Was there something I promised you?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `family.child.again.to.checkin_child`, `family.child.doing.to.checkin_child`, `family.child.little.to.checkin_child`, `family.child.teen.to.checkin_child`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `checkin_child.promise` — accepted phrasings: "was there something i promised you"; "did i promise you something"; "have i promised you anything"
  - the message must contain one of: `promise`, `promised`
  - scored words: `promise`(1.2), `promised`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond.ask_promise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.respond.ask_promise   [35 chars]
    en  Was there something I promised you?
    >>  ............................................
    pt  Eu tinha prometido alguma coisa pra você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.checkin_child.promise`
- …where the player's next choices will be: "Then we'll do it this week." | "I said it and I didn't do it. That's on me." | "We'll talk about it later."

```text
POOL   dialogue key: dialogue.conversations.checkin_child.promise
WHO    VILLAGER — what the player reads after pressing "Was there something I promised you?"
       spoken on: conversations.topic.checkin_child.respond, button `ask_promise`
       leaves the player on: conversations.topic.checkin_child.promise
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `checkin_child.promise`: the villager reports. Subject `checkin_child.promise`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, encouragement, practical_help, exit
```

```text
  dialogue.conversations.checkin_child.promise/1   [70 chars]
    en  The river. You said the river, twice, and it's been dry weather since.
    >>  ............................................
    pt  O rio. Você disse o rio, duas vezes, e o tempo tem sido seco desde então.
    >>  ............................................
  dialogue.conversations.checkin_child.promise/2   [78 chars]
    en  You said I could have the small knife when I could sharpen the big one. I can.
    >>  ............................................
    pt  Você disse que eu podia ter a faca pequena quando soubesse afiar a grande. Eu sei.
    >>  ............................................
  dialogue.conversations.checkin_child.promise/3   [93 chars]
    en  You said you'd stop going out at night. I've been counting and I'd rather not say the number.
    >>  ............................................
    pt  Você disse que ia parar de sair à noite. Eu venho contando e prefiro não dizer o número.
    >>  ............................................
```


### Button `leave` — "I'll not keep you."

*stance family `exit` · tone `plain` · answers the beat(s) `family.child.again.to.checkin_child`, `family.child.doing.to.checkin_child`, `family.child.little.to.checkin_child`, `family.child.teen.to.checkin_child` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.checkin_child.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.checkin_child.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.checkin_child.respond.leave   [18 chars]
    en  I'll not keep you.
    >>  ............................................
    pt  Não vou te prender.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.family.child.leave
WHO    VILLAGER — what the player reads after pressing "I'll not keep you."
       spoken on: conversations.topic.checkin_child.respond, button `leave`
       leaves the player on: conversations.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `family.child.leave.terminal`: the villager accepts. Subject `family.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.checkin_child.followup / leave
```

> Written out in full under **`conversations.topic.checkin_child.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

