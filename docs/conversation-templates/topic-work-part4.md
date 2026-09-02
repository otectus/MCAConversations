# Topic: work — part 4 of 4

> Continued from [topic-work-part1.md](topic-work-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](topic-work-part1.md) · [part 2](topic-work-part2.md) · [part 3](topic-work-part3.md) · [part 4](topic-work-part4.md)


## Nodes in this file

- [`conversations.work`](#conversations-work)
- [`conversations.work.legacy`](#conversations-work-legacy)

---

## `conversations.work` — continued


**Outcome 205 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.craft.respond`
- …where the player's next choices will be: "What was wrong with being certain?" | "Learning to be less certain is not the usual direction." | "How often has it actually been one?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.craft`: the villager reports. Subject `work.hunter_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.craft/1   [108 chars]
    en  Half the trade is knowing what isn't one. Nine years and I've been right about that far more often than not.
    >>  ............................................
    pt  Metade do ofício é saber o que não é um. Nove anos e eu acertei isso muito mais do que errei.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.craft/2   [111 chars]
    en  I was trained by people who were certain, and I've spent since then learning to be less certain than they were.
    >>  ............................................
    pt  Fui treinado por gente que tinha certeza, e passei desde então aprendendo a ter menos certeza que eles.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  A man with my training destroyed a family four valleys from here on a certainty. I've read the account.
    >>  ............................................
    pt  Um homem com o meu treino destruiu uma família a quatro vales por uma certeza. Eu li o relato.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned to be slow because being fast is right about one night in fifty and ruins somebody the other forty-nine.
    >>  ............................................
    pt  Aprendi a ser lento porque ser rápido acerta uma noite em cinquenta e arruína alguém nas outras quarenta e nove.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Nine years of unlearning what I was taught in one. That's the honest ratio of this trade.
    >>  ............................................
    pt  Nove anos desaprendendo o que me ensinaram em um. É a proporção honesta deste ofício.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  It took years to learn to wait, and waiting is the only thing I actually teach now.
    >>  ............................................
    pt  Levou anos pra aprender a esperar, e esperar é a única coisa que eu de fato ensino agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Half the trade is knowing what isn't one. Nine years, and I've been right about that far more than not.
    >>  ............................................
    pt  Metade do ofício é saber o que não é um. Nove anos, e eu acertei isso muito mais do que errei.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I was trained by people who were certain. I have spent since then learning to be less certain than they were.
    >>  ............................................
    pt  Fui treinado por gente que tinha certeza. Passei desde então aprendendo a ter menos certeza que eles.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Half the trade is knowing what isn't one. Nine years, and I've been right about that far more than not.
    >>  ............................................
    pt  Metade do ofício é saber o que não é um. Nove anos, e eu acertei isso muito mais do que errei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I was trained by people who were certain. I have spent since then learning to be less certain than they were.
    >>  ............................................
    pt  Fui treinado por gente que tinha certeza. Passei desde então aprendendo a ter menos certeza que eles.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  My teachers were certain and I am not, and the difference is people I did not put a name to.
    >>  ............................................
    pt  Meus mestres tinham certeza e eu não tenho, e a diferença são pessoas em quem eu não pus um nome.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned it beside somebody, and now I teach two people beside me, and I say much less than they'd like.
    >>  ............................................
    pt  Aprendi ao lado de alguém, e agora ensino duas pessoas ao meu lado, e falo bem menos do que gostariam.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  My teachers were certain and I am not, and the difference is people I did not put a name to.
    >>  ............................................
    pt  Meus mestres tinham certeza e eu não tenho, e a diferença são pessoas em quem eu não pus um nome.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned it beside somebody, and now I teach two people beside me, and I say much less than they'd like.
    >>  ............................................
    pt  Aprendi ao lado de alguém, e agora ensino duas pessoas ao meu lado, e falo bem menos do que gostariam.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  My teachers were certain and I am not, and the difference is people I did not put a name to.
    >>  ............................................
    pt  Meus mestres tinham certeza e eu não tenho, e a diferença são pessoas em quem eu não pus um nome.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned it beside somebody, and now I teach two people beside me, and I say much less than they'd like.
    >>  ............................................
    pt  Aprendi ao lado de alguém, e agora ensino duas pessoas ao meu lado, e falo bem menos do que gostariam.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  A man with my training destroyed a family four valleys from here on a certainty. I've read the account.
    >>  ............................................
    pt  Um homem com o meu treino destruiu uma família a quatro vales por uma certeza. Eu li o relato.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned to be slow because being fast is right about one night in fifty and ruins somebody the other forty-nine.
    >>  ............................................
    pt  Aprendi a ser lento porque ser rápido acerta uma noite em cinquenta e arruína alguém nas outras quarenta e nove.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Half the trade is knowing what isn't one. Nine years, and I've been right about that far more than not.
    >>  ............................................
    pt  Metade do ofício é saber o que não é um. Nove anos, e eu acertei isso muito mais do que errei.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I was trained by people who were certain. I have spent since then learning to be less certain than they were.
    >>  ............................................
    pt  Fui treinado por gente que tinha certeza. Passei desde então aprendendo a ter menos certeza que eles.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Half the trade is knowing what isn't one. Nine years, and I've been right about that far more than not.
    >>  ............................................
    pt  Metade do ofício é saber o que não é um. Nove anos, e eu acertei isso muito mais do que errei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I was trained by people who were certain. I have spent since then learning to be less certain than they were.
    >>  ............................................
    pt  Fui treinado por gente que tinha certeza. Passei desde então aprendendo a ter menos certeza que eles.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Certainty is what puts a name on a neighbour. I read one account of that and it settled the matter.
    >>  ............................................
    pt  Certeza é o que põe um nome num vizinho. Li um relato disso e a questão foi encerrada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Three nights of watching, two witnesses, and never on the word of somebody who stands to gain.
    >>  ............................................
    pt  Três noites de observação, duas testemunhas, e nunca pela palavra de quem tem a ganhar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Nine years of unlearning what I was taught in one. That's the honest ratio of this trade.
    >>  ............................................
    pt  Nove anos desaprendendo o que me ensinaram em um. É a proporção honesta deste ofício.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  It took years to learn to wait, and waiting is the only thing I actually teach now.
    >>  ............................................
    pt  Levou anos pra aprender a esperar, e esperar é a única coisa que eu de fato ensino agora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Certainty is what puts a name on a neighbour. I read one account of that and it settled the matter.
    >>  ............................................
    pt  Certeza é o que põe um nome num vizinho. Li um relato disso e a questão foi encerrada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Three nights of watching, two witnesses, and never on the word of somebody who stands to gain.
    >>  ............................................
    pt  Três noites de observação, duas testemunhas, e nunca pela palavra de quem tem a ganhar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Nine years of unlearning what I was taught in one. That's the honest ratio of this trade.
    >>  ............................................
    pt  Nove anos desaprendendo o que me ensinaram em um. É a proporção honesta deste ofício.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  It took years to learn to wait, and waiting is the only thing I actually teach now.
    >>  ............................................
    pt  Levou anos pra aprender a esperar, e esperar é a única coisa que eu de fato ensino agora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Most of my training was learning to slow down, which is the least exciting curriculum imaginable.
    >>  ............................................
    pt  Quase todo o meu treino foi aprender a ir devagar, o currículo menos empolgante que existe.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Nine times in ten it's a fox. I have an entire lecture about foxes and nobody has ever asked for it.
    >>  ............................................
    pt  Nove em dez vezes é uma raposa. Tenho uma palestra inteira sobre raposas e ninguém nunca pediu.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Most of my training was learning to slow down, which is the least exciting curriculum imaginable.
    >>  ............................................
    pt  Quase todo o meu treino foi aprender a ir devagar, o currículo menos empolgante que existe.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Nine times in ten it's a fox. I have an entire lecture about foxes and nobody has ever asked for it.
    >>  ............................................
    pt  Nove em dez vezes é uma raposa. Tenho uma palestra inteira sobre raposas e ninguém nunca pediu.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Nine years of unlearning what I was taught in one. That's the honest ratio of this trade.
    >>  ............................................
    pt  Nove anos desaprendendo o que me ensinaram em um. É a proporção honesta deste ofício.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  It took years to learn to wait, and waiting is the only thing I actually teach now.
    >>  ............................................
    pt  Levou anos pra aprender a esperar, e esperar é a única coisa que eu de fato ensino agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  A man with my training destroyed a family four valleys from here on a certainty. I've read the account.
    >>  ............................................
    pt  Um homem com o meu treino destruiu uma família a quatro vales por uma certeza. Eu li o relato.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  I learned to be slow because being fast is right about one night in fifty and ruins somebody the other forty-nine.
    >>  ............................................
    pt  Aprendi a ser lento porque ser rápido acerta uma noite em cinquenta e arruína alguém nas outras quarenta e nove.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Certainty is what puts a name on a neighbour. I read one account of that and it settled the matter.
    >>  ............................................
    pt  Certeza é o que põe um nome num vizinho. Li um relato disso e a questão foi encerrada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Three nights of watching, two witnesses, and never on the word of somebody who stands to gain.
    >>  ............................................
    pt  Três noites de observação, duas testemunhas, e nunca pela palavra de quem tem a ganhar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Most of my training was learning to slow down, which is the least exciting curriculum imaginable.
    >>  ............................................
    pt  Quase todo o meu treino foi aprender a ir devagar, o currículo menos empolgante que existe.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Nine times in ten it's a fox. I have an entire lecture about foxes and nobody has ever asked for it.
    >>  ............................................
    pt  Nove em dez vezes é uma raposa. Tenho uma palestra inteira sobre raposas e ninguém nunca pediu.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.craft/1
    en  Most of my training was learning to slow down, which is the least exciting curriculum imaginable.
    >>  ............................................
    pt  Quase todo o meu treino foi aprender a ir devagar, o currículo menos empolgante que existe.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.craft/2
    en  Nine times in ten it's a fox. I have an entire lecture about foxes and nobody has ever asked for it.
    >>  ............................................
    pt  Nove em dez vezes é uma raposa. Tenho uma palestra inteira sobre raposas e ninguém nunca pediu.
    >>  ............................................
```

</details>


**Outcome 206 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.risk.respond`
- …where the player's next choices will be: "Where did you read the account?" | "You carry another man's mistake as if it were yours." | "Why not tell your two why you're slow?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.risk`: the villager reports. Subject `work.hunter_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.risk/1   [100 chars]
    en  The danger in my trade is my trade. A hunter who's wrong is worse than the thing he was wrong about.
    >>  ............................................
    pt  O perigo do meu ofício é o meu ofício. Um caçador que erra é pior que a coisa sobre a qual errou.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.risk/2   [116 chars]
    en  There's a family four valleys from here that a man with my training destroyed on a certainty. I've read the account.
    >>  ............................................
    pt  Tem uma família a quatro vales que um homem com o meu treino destruiu por uma certeza. Eu li o relato.
    >>  ............................................
```


**Outcome 207 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.village.respond`
- …where the player's next choices will be: "Not one name in nine years?" | "Three refusals is a harder record than three catches." | "Why never disagree in public?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.village`: the villager reports. Subject `work.hunter_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.village/1   [107 chars]
    en  Nine years and I've never put a name to anybody in this valley. That's the achievement, not the two nights.
    >>  ............................................
    pt  Nove anos e eu nunca pus um nome em ninguém deste vale. É essa a conquista, não as duas noites.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.village/2   [93 chars]
    en  The priest and I disagree about almost everything and we have never once disagreed in public.
    >>  ............................................
    pt  O padre e eu discordamos sobre quase tudo e nunca discordamos em público.
    >>  ............................................
```


**Outcome 208 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.future.respond`
- …where the player's next choices will be: "What would the rule say?" | "Then write it. The scribe would keep a copy." | "Why will they hate being slower?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.future`: the villager reports. Subject `work.hunter_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.future/1   [91 chars]
    en  I want my two to be better than me, which means being slower than me, which they will hate.
    >>  ............................................
    pt  Quero que meus dois sejam melhores que eu, o que significa mais lentos que eu, o que eles vão detestar.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.future/2   [108 chars]
    en  There should be a written rule for when a watch may name somebody. There isn't one anywhere and I've looked.
    >>  ............................................
    pt  Devia haver uma regra escrita pra quando uma vigia pode nomear alguém. Não existe em lugar nenhum e eu procurei.
    >>  ............................................
```


**Outcome 209 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.task.respond`
- …where the player's next choices will be: "What are the two sentences?" | "I could sit with them instead this afternoon." | "What are they frightened of?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.task`: the villager reports. Subject `work.priest.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.task/1   [114 chars]
    en  Sitting with a family that's frightened of the wrong thing. It takes an afternoon and it is the whole of the work.
    >>  ............................................
    pt  Sentado com uma família com medo da coisa errada. Leva uma tarde e é todo o serviço.
    >>  ............................................
  dialogue.conversations.work.prof.priest.task/2   [96 chars]
    en  Preparing what I'll say on Sunday, and taking out the two sentences that would feel good to say.
    >>  ............................................
    pt  Preparando o que eu vou dizer no domingo, e tirando as duas frases que dariam gosto de dizer.
    >>  ............................................
```


**Outcome 210 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.craft.respond`
- …where the player's next choices will be: "Why does the pause matter so much?" | "Twenty years to unlearn answering is an honest account." | "What's the third thing they say?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.craft`: the villager reports. Subject `work.priest.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.craft/1   [104 chars]
    en  Nothing I do is doctrine. It's sitting in rooms and not filling the silence, and that took twenty years.
    >>  ............................................
    pt  Nada do que eu faço é doutrina. É sentar em cômodos e não preencher o silêncio, e isso levou vinte anos.
    >>  ............................................
  dialogue.conversations.work.prof.priest.craft/2   [100 chars]
    en  I was taught to answer. It has taken most of my life to learn that answering is usually the mistake.
    >>  ............................................
    pt  Fui ensinado a responder. Levou quase a vida toda pra aprender que responder costuma ser o erro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.priest.craft/1
    en  I learned by getting it wrong in rooms where being wrong cost somebody more than it cost me.
    >>  ............................................
    pt  Aprendi errando em cômodos onde errar custava mais a outra pessoa que a mim.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.priest.craft/2
    en  Twenty years of unlearning. It is an embarrassing account and I give it in full to anybody starting out.
    >>  ............................................
    pt  Vinte anos desaprendendo. É um relato constrangedor e eu conto inteiro pra quem está começando.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years, and it could not have been fifteen. Some things arrive only after enough afternoons.
    >>  ............................................
    pt  Vinte anos, e não podiam ser quinze. Algumas coisas só chegam depois de tardes suficientes.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.priest.craft/2
    en  Slowly, in the same four rooms, being asked the same question slightly differently every year.
    >>  ............................................
    pt  Devagar, nos mesmos quatro cômodos, ouvindo a mesma pergunta um pouco diferente todo ano.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.craft/1
    en  None of it is doctrine. It is sitting in rooms and not filling the silence, and that took twenty years.
    >>  ............................................
    pt  Nada disso é doutrina. É sentar em cômodos e não preencher o silêncio, e levou vinte anos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.priest.craft/2
    en  I was taught to answer. Most of my life has gone on learning that answering is usually the mistake.
    >>  ............................................
    pt  Fui ensinado a responder. Quase a vida toda foi aprender que responder costuma ser o erro.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.craft/1
    en  None of it is doctrine. It is sitting in rooms and not filling the silence, and that took twenty years.
    >>  ............................................
    pt  Nada disso é doutrina. É sentar em cômodos e não preencher o silêncio, e levou vinte anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.priest.craft/2
    en  I was taught to answer. Most of my life has gone on learning that answering is usually the mistake.
    >>  ............................................
    pt  Fui ensinado a responder. Quase a vida toda foi aprender que responder costuma ser o erro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.craft/1
    en  People taught me, one kitchen at a time, by letting me stay when I had nothing useful to say.
    >>  ............................................
    pt  As pessoas me ensinaram, uma cozinha por vez, me deixando ficar quando eu não tinha nada útil a dizer.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.priest.craft/2
    en  I learned it from the families who kept opening the door. That is a debt I could not itemise.
    >>  ............................................
    pt  Aprendi com as famílias que continuaram abrindo a porta. É uma dívida que eu não conseguiria detalhar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.craft/1
    en  People taught me, one kitchen at a time, by letting me stay when I had nothing useful to say.
    >>  ............................................
    pt  As pessoas me ensinaram, uma cozinha por vez, me deixando ficar quando eu não tinha nada útil a dizer.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.priest.craft/2
    en  I learned it from the families who kept opening the door. That is a debt I could not itemise.
    >>  ............................................
    pt  Aprendi com as famílias que continuaram abrindo a porta. É uma dívida que eu não conseguiria detalhar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.craft/1
    en  People taught me, one kitchen at a time, by letting me stay when I had nothing useful to say.
    >>  ............................................
    pt  As pessoas me ensinaram, uma cozinha por vez, me deixando ficar quando eu não tinha nada útil a dizer.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.priest.craft/2
    en  I learned it from the families who kept opening the door. That is a debt I could not itemise.
    >>  ............................................
    pt  Aprendi com as famílias que continuaram abrindo a porta. É uma dívida que eu não conseguiria detalhar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.craft/1
    en  I learned by getting it wrong in rooms where being wrong cost somebody more than it cost me.
    >>  ............................................
    pt  Aprendi errando em cômodos onde errar custava mais a outra pessoa que a mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.priest.craft/2
    en  Twenty years of unlearning. It is an embarrassing account and I give it in full to anybody starting out.
    >>  ............................................
    pt  Vinte anos desaprendendo. É um relato constrangedor e eu conto inteiro pra quem está começando.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.craft/1
    en  None of it is doctrine. It is sitting in rooms and not filling the silence, and that took twenty years.
    >>  ............................................
    pt  Nada disso é doutrina. É sentar em cômodos e não preencher o silêncio, e levou vinte anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.priest.craft/2
    en  I was taught to answer. Most of my life has gone on learning that answering is usually the mistake.
    >>  ............................................
    pt  Fui ensinado a responder. Quase a vida toda foi aprender que responder costuma ser o erro.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.craft/1
    en  None of it is doctrine. It is sitting in rooms and not filling the silence, and that took twenty years.
    >>  ............................................
    pt  Nada disso é doutrina. É sentar em cômodos e não preencher o silêncio, e levou vinte anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.priest.craft/2
    en  I was taught to answer. Most of my life has gone on learning that answering is usually the mistake.
    >>  ............................................
    pt  Fui ensinado a responder. Quase a vida toda foi aprender que responder costuma ser o erro.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.craft/1
    en  What they came to say is behind the third thing they say. Answering stops them at the first.
    >>  ............................................
    pt  O que vieram dizer está atrás da terceira coisa que dizem. Responder os para na primeira.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.priest.craft/2
    en  It arrives about forty minutes in, and it is different every time, and it is always in the same place.
    >>  ............................................
    pt  Chega uns quarenta minutos depois, e é diferente toda vez, e é sempre no mesmo ponto.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years, and it could not have been fifteen. Some things arrive only after enough afternoons.
    >>  ............................................
    pt  Vinte anos, e não podiam ser quinze. Algumas coisas só chegam depois de tardes suficientes.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.priest.craft/2
    en  Slowly, in the same four rooms, being asked the same question slightly differently every year.
    >>  ............................................
    pt  Devagar, nos mesmos quatro cômodos, ouvindo a mesma pergunta um pouco diferente todo ano.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.craft/1
    en  What they came to say is behind the third thing they say. Answering stops them at the first.
    >>  ............................................
    pt  O que vieram dizer está atrás da terceira coisa que dizem. Responder os para na primeira.
    >>  ............................................
  odd.dialogue.conversations.work.prof.priest.craft/2
    en  It arrives about forty minutes in, and it is different every time, and it is always in the same place.
    >>  ............................................
    pt  Chega uns quarenta minutos depois, e é diferente toda vez, e é sempre no mesmo ponto.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years, and it could not have been fifteen. Some things arrive only after enough afternoons.
    >>  ............................................
    pt  Vinte anos, e não podiam ser quinze. Algumas coisas só chegam depois de tardes suficientes.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.priest.craft/2
    en  Slowly, in the same four rooms, being asked the same question slightly differently every year.
    >>  ............................................
    pt  Devagar, nos mesmos quatro cômodos, ouvindo a mesma pergunta um pouco diferente todo ano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years to learn to shut up. I give that fact to every young cleric who will sit still for it.
    >>  ............................................
    pt  Vinte anos pra aprender a calar a boca. Dou esse fato a todo clérigo jovem que fique quieto pra ouvir.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.priest.craft/2
    en  The seminary taught me answers. The village taught me that nobody had asked a question yet.
    >>  ............................................
    pt  O seminário me ensinou respostas. O vilarejo me ensinou que ninguém tinha feito uma pergunta ainda.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years to learn to shut up. I give that fact to every young cleric who will sit still for it.
    >>  ............................................
    pt  Vinte anos pra aprender a calar a boca. Dou esse fato a todo clérigo jovem que fique quieto pra ouvir.
    >>  ............................................
  playful.dialogue.conversations.work.prof.priest.craft/2
    en  The seminary taught me answers. The village taught me that nobody had asked a question yet.
    >>  ............................................
    pt  O seminário me ensinou respostas. O vilarejo me ensinou que ninguém tinha feito uma pergunta ainda.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years, and it could not have been fifteen. Some things arrive only after enough afternoons.
    >>  ............................................
    pt  Vinte anos, e não podiam ser quinze. Algumas coisas só chegam depois de tardes suficientes.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.priest.craft/2
    en  Slowly, in the same four rooms, being asked the same question slightly differently every year.
    >>  ............................................
    pt  Devagar, nos mesmos quatro cômodos, ouvindo a mesma pergunta um pouco diferente todo ano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.craft/1
    en  I learned by getting it wrong in rooms where being wrong cost somebody more than it cost me.
    >>  ............................................
    pt  Aprendi errando em cômodos onde errar custava mais a outra pessoa que a mim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.priest.craft/2
    en  Twenty years of unlearning. It is an embarrassing account and I give it in full to anybody starting out.
    >>  ............................................
    pt  Vinte anos desaprendendo. É um relato constrangedor e eu conto inteiro pra quem está começando.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.craft/1
    en  What they came to say is behind the third thing they say. Answering stops them at the first.
    >>  ............................................
    pt  O que vieram dizer está atrás da terceira coisa que dizem. Responder os para na primeira.
    >>  ............................................
  shy.dialogue.conversations.work.prof.priest.craft/2
    en  It arrives about forty minutes in, and it is different every time, and it is always in the same place.
    >>  ............................................
    pt  Chega uns quarenta minutos depois, e é diferente toda vez, e é sempre no mesmo ponto.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years to learn to shut up. I give that fact to every young cleric who will sit still for it.
    >>  ............................................
    pt  Vinte anos pra aprender a calar a boca. Dou esse fato a todo clérigo jovem que fique quieto pra ouvir.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.priest.craft/2
    en  The seminary taught me answers. The village taught me that nobody had asked a question yet.
    >>  ............................................
    pt  O seminário me ensinou respostas. O vilarejo me ensinou que ninguém tinha feito uma pergunta ainda.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.craft/1
    en  Twenty years to learn to shut up. I give that fact to every young cleric who will sit still for it.
    >>  ............................................
    pt  Vinte anos pra aprender a calar a boca. Dou esse fato a todo clérigo jovem que fique quieto pra ouvir.
    >>  ............................................
  witty.dialogue.conversations.work.prof.priest.craft/2
    en  The seminary taught me answers. The village taught me that nobody had asked a question yet.
    >>  ............................................
    pt  O seminário me ensinou respostas. O vilarejo me ensinou que ninguém tinha feito uma pergunta ainda.
    >>  ............................................
```

</details>


**Outcome 211 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.risk.respond`
- …where the player's next choices will be: "What's the way you're most likely to be wrong?" | "Making the fortnight longer is a thin thing to hold a valley with." | "Does the hunter share the fortnight?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.risk`: the villager reports. Subject `work.priest.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.risk/1   [109 chars]
    en  A frightened valley will find somebody to blame within a fortnight. My part is to make that fortnight longer.
    >>  ............................................
    pt  Um vale assustado acha alguém pra culpar em quinze dias. Meu papel é fazer essa quinzena durar mais.
    >>  ............................................
  dialogue.conversations.work.prof.priest.risk/2   [93 chars]
    en  If I'm ever wrong about a family in the way I'm most likely to be wrong, somebody dies of it.
    >>  ............................................
    pt  Se eu errar sobre uma família do jeito que eu tenho mais chance de errar, alguém morre disso.
    >>  ............................................
```


**Outcome 212 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.village.respond`
- …where the player's next choices will be: "What do you do about the list?" | "Nobody driven out in twenty years is the record that counts." | "Has it ever come close?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.village`: the villager reports. Subject `work.priest.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.village/1   [103 chars]
    en  Twenty years and no one in this valley has been driven out on a rumour. That is the whole of my record.
    >>  ............................................
    pt  Vinte anos e ninguém neste vale foi expulso por boato. É todo o meu registro.
    >>  ............................................
  dialogue.conversations.work.prof.priest.village/2   [96 chars]
    en  The families who come to me are not the ones I worry about. I keep a list of the ones who don't.
    >>  ............................................
    pt  As famílias que me procuram não são as que me preocupam. Guardo uma lista das que não vêm.
    >>  ............................................
```


**Outcome 213 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.future.respond`
- …where the player's next choices will be: "You'd argue with him in public now?" | "Then argue at the reading of the rule. Let them watch." | "Who could keep an unwritten list?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.future`: the villager reports. Subject `work.priest.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.priest.future/1   [111 chars]
    en  Somebody has to keep the unwritten list after me. It's the only thing I do that couldn't be replaced by a book.
    >>  ............................................
    pt  Alguém tem que guardar a lista não escrita depois de mim. É a única coisa que eu faço que um livro não substitui.
    >>  ............................................
  dialogue.conversations.work.prof.priest.future/2   [101 chars]
    en  I'd like the hunter's rule to exist and I'd like to have argued about it with him in front of people.
    >>  ............................................
    pt  Queria que a regra do caçador existisse e queria ter discutido sobre ela com ele na frente das pessoas.
    >>  ............................................
```


**Outcome 214 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.task.respond`
- …where the player's next choices will be: "What do the useful ones ask?" | "I could take the letters to the road for you." | "Does the asking-what-you-are get tiring?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.task`: the villager reports. Subject `work.vampire_expert.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.task/1   [104 chars]
    en  Answering letters. Four of them, all from people asking me what I am before they ask me anything useful.
    >>  ............................................
    pt  Respondendo cartas. Quatro, todas de gente perguntando o que eu sou antes de perguntar algo útil.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.task/2   [117 chars]
    en  Reading in a shuttered room because the alternative is a headache that lasts two days. It's inconvenient, not tragic.
    >>  ............................................
    pt  Lendo num cômodo fechado porque a alternativa é uma dor de cabeça de dois dias. É inconveniente, não trágico.
    >>  ............................................
```


**Outcome 215 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.craft.respond`
- …where the player's next choices will be: "What are you not?" | "Assembling it from letters is a real body of knowledge." | "Forty people write to you?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.craft`: the villager reports. Subject `work.vampire_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.craft/1   [108 chars]
    en  I know exactly what I am and exactly what I'm not, and almost everything I do is correcting the second part.
    >>  ............................................
    pt  Sei exatamente o que eu sou e o que eu não sou, e quase tudo que eu faço é corrigir a segunda parte.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.craft/2   [100 chars]
    en  Nobody taught me. I've assembled nineteen years of it out of my own days and forty people's letters.
    >>  ............................................
    pt  Ninguém me ensinou. Montei dezenove anos disso com os meus dias e as cartas de quarenta pessoas.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nobody taught me because there was nobody. Nineteen years of working out what is true about myself, alone.
    >>  ............................................
    pt  Ninguém me ensinou porque não havia ninguém. Dezenove anos descobrindo o que é verdade sobre mim, sozinho.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I burn every letter the day I answer it, so nineteen years of the most useful thing I own leaves no record.
    >>  ............................................
    pt  Queimo cada carta no dia em que respondo, então dezenove anos da coisa mais útil que eu tenho não deixam registro.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nineteen years of my own days, read slowly. There was no faster way and I was in no position to hurry.
    >>  ............................................
    pt  Dezenove anos dos meus próprios dias, lidos devagar. Não havia jeito mais rápido e eu não tinha como apressar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Letter by letter, season by season. It assembled itself and I only kept turning up for it.
    >>  ............................................
    pt  Carta por carta, estação por estação. Se montou sozinho e eu só continuei aparecendo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  I know exactly what I am and what I am not, and almost everything I do is correcting the second part.
    >>  ............................................
    pt  Sei exatamente o que sou e o que não sou, e quase tudo que eu faço é corrigir a segunda parte.
    >>  ............................................
  confident.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nobody taught me. I assembled nineteen years of it out of my own days and forty people's letters.
    >>  ............................................
    pt  Ninguém me ensinou. Montei dezenove anos disso com os meus dias e as cartas de quarenta pessoas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  I know exactly what I am and what I am not, and almost everything I do is correcting the second part.
    >>  ............................................
    pt  Sei exatamente o que sou e o que não sou, e quase tudo que eu faço é corrigir a segunda parte.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nobody taught me. I assembled nineteen years of it out of my own days and forty people's letters.
    >>  ............................................
    pt  Ninguém me ensinou. Montei dezenove anos disso com os meus dias e as cartas de quarenta pessoas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty correspondents taught me. Eleven still write, and four write every season, and those four are my company.
    >>  ............................................
    pt  Quarenta correspondentes me ensinaram. Onze ainda escrevem, e quatro escrevem toda estação, e esses quatro são minha companhia.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I learned it from other people's letters, which is why I answer every one of mine the same day.
    >>  ............................................
    pt  Aprendi com as cartas dos outros, e por isso eu respondo cada uma das minhas no mesmo dia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty correspondents taught me. Eleven still write, and four write every season, and those four are my company.
    >>  ............................................
    pt  Quarenta correspondentes me ensinaram. Onze ainda escrevem, e quatro escrevem toda estação, e esses quatro são minha companhia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I learned it from other people's letters, which is why I answer every one of mine the same day.
    >>  ............................................
    pt  Aprendi com as cartas dos outros, e por isso eu respondo cada uma das minhas no mesmo dia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty correspondents taught me. Eleven still write, and four write every season, and those four are my company.
    >>  ............................................
    pt  Quarenta correspondentes me ensinaram. Onze ainda escrevem, e quatro escrevem toda estação, e esses quatro são minha companhia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I learned it from other people's letters, which is why I answer every one of mine the same day.
    >>  ............................................
    pt  Aprendi com as cartas dos outros, e por isso eu respondo cada uma das minhas no mesmo dia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nobody taught me because there was nobody. Nineteen years of working out what is true about myself, alone.
    >>  ............................................
    pt  Ninguém me ensinou porque não havia ninguém. Dezenove anos descobrindo o que é verdade sobre mim, sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I burn every letter the day I answer it, so nineteen years of the most useful thing I own leaves no record.
    >>  ............................................
    pt  Queimo cada carta no dia em que respondo, então dezenove anos da coisa mais útil que eu tenho não deixam registro.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  I know exactly what I am and what I am not, and almost everything I do is correcting the second part.
    >>  ............................................
    pt  Sei exatamente o que sou e o que não sou, e quase tudo que eu faço é corrigir a segunda parte.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nobody taught me. I assembled nineteen years of it out of my own days and forty people's letters.
    >>  ............................................
    pt  Ninguém me ensinou. Montei dezenove anos disso com os meus dias e as cartas de quarenta pessoas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  I know exactly what I am and what I am not, and almost everything I do is correcting the second part.
    >>  ............................................
    pt  Sei exatamente o que sou e o que não sou, e quase tudo que eu faço é corrigir a segunda parte.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nobody taught me. I assembled nineteen years of it out of my own days and forty people's letters.
    >>  ............................................
    pt  Ninguém me ensinou. Montei dezenove anos disso com os meus dias e as cartas de quarenta pessoas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Shutters, hours, what to tell a landlord. Practical things, written down badly by people who needed them.
    >>  ............................................
    pt  Venezianas, horários, o que dizer a um senhorio. Coisas práticas, mal escritas por gente que precisava delas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  The correction that matters is the one about catching, and it is the one that takes longest to land.
    >>  ............................................
    pt  A correção que importa é a sobre contágio, e é a que leva mais tempo pra assentar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nineteen years of my own days, read slowly. There was no faster way and I was in no position to hurry.
    >>  ............................................
    pt  Dezenove anos dos meus próprios dias, lidos devagar. Não havia jeito mais rápido e eu não tinha como apressar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Letter by letter, season by season. It assembled itself and I only kept turning up for it.
    >>  ............................................
    pt  Carta por carta, estação por estação. Se montou sozinho e eu só continuei aparecendo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Shutters, hours, what to tell a landlord. Practical things, written down badly by people who needed them.
    >>  ............................................
    pt  Venezianas, horários, o que dizer a um senhorio. Coisas práticas, mal escritas por gente que precisava delas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  The correction that matters is the one about catching, and it is the one that takes longest to land.
    >>  ............................................
    pt  A correção que importa é a sobre contágio, e é a que leva mais tempo pra assentar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nineteen years of my own days, read slowly. There was no faster way and I was in no position to hurry.
    >>  ............................................
    pt  Dezenove anos dos meus próprios dias, lidos devagar. Não havia jeito mais rápido e eu não tinha como apressar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Letter by letter, season by season. It assembled itself and I only kept turning up for it.
    >>  ............................................
    pt  Carta por carta, estação por estação. Se montou sozinho e eu só continuei aparecendo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty people's ordinary days, written badly, over nineteen years. Somehow that adds up to expertise.
    >>  ............................................
    pt  Os dias comuns de quarenta pessoas, mal escritos, ao longo de dezenove anos. De algum jeito isso vira perícia.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nine of the ten things a frightened person names, I am not. The tenth is extremely dull.
    >>  ............................................
    pt  Nove das dez coisas que um assustado nomeia, eu não sou. A décima é extremamente sem graça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty people's ordinary days, written badly, over nineteen years. Somehow that adds up to expertise.
    >>  ............................................
    pt  Os dias comuns de quarenta pessoas, mal escritos, ao longo de dezenove anos. De algum jeito isso vira perícia.
    >>  ............................................
  playful.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nine of the ten things a frightened person names, I am not. The tenth is extremely dull.
    >>  ............................................
    pt  Nove das dez coisas que um assustado nomeia, eu não sou. A décima é extremamente sem graça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nineteen years of my own days, read slowly. There was no faster way and I was in no position to hurry.
    >>  ............................................
    pt  Dezenove anos dos meus próprios dias, lidos devagar. Não havia jeito mais rápido e eu não tinha como apressar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Letter by letter, season by season. It assembled itself and I only kept turning up for it.
    >>  ............................................
    pt  Carta por carta, estação por estação. Se montou sozinho e eu só continuei aparecendo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Nobody taught me because there was nobody. Nineteen years of working out what is true about myself, alone.
    >>  ............................................
    pt  Ninguém me ensinou porque não havia ninguém. Dezenove anos descobrindo o que é verdade sobre mim, sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  I burn every letter the day I answer it, so nineteen years of the most useful thing I own leaves no record.
    >>  ............................................
    pt  Queimo cada carta no dia em que respondo, então dezenove anos da coisa mais útil que eu tenho não deixam registro.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Shutters, hours, what to tell a landlord. Practical things, written down badly by people who needed them.
    >>  ............................................
    pt  Venezianas, horários, o que dizer a um senhorio. Coisas práticas, mal escritas por gente que precisava delas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  The correction that matters is the one about catching, and it is the one that takes longest to land.
    >>  ............................................
    pt  A correção que importa é a sobre contágio, e é a que leva mais tempo pra assentar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty people's ordinary days, written badly, over nineteen years. Somehow that adds up to expertise.
    >>  ............................................
    pt  Os dias comuns de quarenta pessoas, mal escritos, ao longo de dezenove anos. De algum jeito isso vira perícia.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nine of the ten things a frightened person names, I am not. The tenth is extremely dull.
    >>  ............................................
    pt  Nove das dez coisas que um assustado nomeia, eu não sou. A décima é extremamente sem graça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.craft/1
    en  Forty people's ordinary days, written badly, over nineteen years. Somehow that adds up to expertise.
    >>  ............................................
    pt  Os dias comuns de quarenta pessoas, mal escritos, ao longo de dezenove anos. De algum jeito isso vira perícia.
    >>  ............................................
  witty.dialogue.conversations.work.prof.vampire_expert.craft/2
    en  Nine of the ten things a frightened person names, I am not. The tenth is extremely dull.
    >>  ............................................
    pt  Nove das dez coisas que um assustado nomeia, eu não sou. A décima é extremamente sem graça.
    >>  ............................................
```

</details>


**Outcome 216 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.risk.respond`
- …where the player's next choices will be: "Have you ever nearly taken the bag?" | "The letters costing somebody else is the worse half." | "You burn all of them?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.risk`: the villager reports. Subject `work.vampire_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.risk/1   [105 chars]
    en  Any bad month in this valley could end with me in it, and I've kept a bag by the door for nineteen years.
    >>  ............................................
    pt  Qualquer mês ruim neste vale pode terminar comigo dentro dele, e eu tenho uma bolsa na porta há dezenove anos.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.risk/2   [110 chars]
    en  The letters are the danger. If one is read by the wrong person, it isn't me who pays for it — it's the writer.
    >>  ............................................
    pt  As cartas são o perigo. Se uma for lida pela pessoa errada, não sou eu que pago — é quem escreveu.
    >>  ............................................
```


**Outcome 217 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.village.respond`
- …where the player's next choices will be: "Furniture is a strange thing to be grateful for." | "No panic in nineteen years is partly you and you should say so." | "Why does it need proving?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.village`: the villager reports. Subject `work.vampire_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.village/1   [110 chars]
    en  Nineteen years and this valley has not had one panic. I'd like to think I'm part of why, and I can't prove it.
    >>  ............................................
    pt  Dezenove anos e este vale não teve um pânico. Eu gostaria de pensar que faço parte disso, e não posso provar.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.village/2   [112 chars]
    en  Four people here speak to me as if I were furniture and thirty do not speak to me at all. I count both as peace.
    >>  ............................................
    pt  Quatro pessoas aqui falam comigo como se eu fosse mobília e trinta não falam comigo. Conto as duas coisas como paz.
    >>  ............................................
```


**Outcome 218 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.future.respond`
- …where the player's next choices will be: "Why keep those four?" | "The scribe sends a copy four valleys away. Send them with it." | "What would it take to put the bag away?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.future`: the villager reports. Subject `work.vampire_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.vampire_expert.future/1   [93 chars]
    en  I'd like the four letters kept somewhere they'd survive me and never be read by anybody here.
    >>  ............................................
    pt  Queria as quatro cartas guardadas onde me sobrevivessem e nunca fossem lidas por ninguém daqui.
    >>  ............................................
  dialogue.conversations.work.prof.vampire_expert.future/2   [101 chars]
    en  And I'd like to stop keeping the bag by the door. Nineteen years is a long time to be almost leaving.
    >>  ............................................
    pt  E queria parar de manter a bolsa na porta. Dezenove anos é muito tempo pra estar quase indo embora.
    >>  ............................................
```


**Outcome 219 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.task.respond`
- …where the player's next choices will be: "How do people take the three days?" | "I could check the cellar door with you." | "How often do you check it?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.task`: the villager reports. Subject `work.werewolf_expert.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.task/1   [100 chars]
    en  Marking the calendar. Three days a month I'm not available and everybody has learned not to ask why.
    >>  ............................................
    pt  Marcando o calendário. Três dias por mês eu não estou disponível e todos aprenderam a não perguntar.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.task/2   [100 chars]
    en  Checking the cellar door from the outside, which is a thing I do far more often than it needs doing.
    >>  ............................................
    pt  Conferindo a porta do porão por fora, o que eu faço muito mais vezes do que precisa.
    >>  ............................................
```


**Outcome 220 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.craft.respond`
- …where the player's next choices will be: "Who do you tell?" | "Arrangements are the practical knowledge nobody records." | "Eleven years of solving it alone?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.craft`: the villager reports. Subject `work.werewolf_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.craft/1   [109 chars]
    en  What I actually know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  O que eu sei mesmo são arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.craft/2   [107 chars]
    en  I worked it out alone over eleven years and I'd give the whole of it to anybody who asked, and nobody asks.
    >>  ............................................
    pt  Descobri sozinho ao longo de onze anos e daria tudo a quem perguntasse, e ninguém pergunta.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  I worked it out alone because there was nobody within four valleys who had solved the same Tuesday.
    >>  ............................................
    pt  Descobri sozinho porque não havia ninguém em quatro vales que tivesse resolvido a mesma terça.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  The first three years taught me by going wrong. I would not describe how, and I remember all of it.
    >>  ............................................
    pt  Os três primeiros anos me ensinaram dando errado. Eu não descreveria como, e eu lembro de tudo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Eleven years, three of them bad. There was no shorter route and I've stopped resenting that.
    >>  ............................................
    pt  Onze anos, três deles ruins. Não havia caminho mais curto e eu parei de me revoltar com isso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Slowly, one arrangement at a time. Doors first, then dates, then people. In that order and no other.
    >>  ............................................
    pt  Devagar, um arranjo por vez. Portas primeiro, depois datas, depois pessoas. Nessa ordem e nenhuma outra.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  What I know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  O que eu sei são arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I worked it out alone over eleven years. I'd give the whole of it to anybody who asked, and nobody asks.
    >>  ............................................
    pt  Descobri sozinho ao longo de onze anos. Daria tudo a quem perguntasse, e ninguém pergunta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  What I know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  O que eu sei são arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I worked it out alone over eleven years. I'd give the whole of it to anybody who asked, and nobody asks.
    >>  ............................................
    pt  Descobri sozinho ao longo de onze anos. Daria tudo a quem perguntasse, e ninguém pergunta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Three people helped without ever being asked to teach me. The mason built the cellar and asked no questions.
    >>  ............................................
    pt  Três pessoas ajudaram sem nunca serem convidadas a me ensinar. O pedreiro fez o porão e não perguntou nada.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I learned it alone and I'd rather nobody else had to. Ask me anything — I'll answer all of it.
    >>  ............................................
    pt  Aprendi sozinho e eu preferia que mais ninguém precisasse. Me pergunte qualquer coisa — eu respondo tudo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Three people helped without ever being asked to teach me. The mason built the cellar and asked no questions.
    >>  ............................................
    pt  Três pessoas ajudaram sem nunca serem convidadas a me ensinar. O pedreiro fez o porão e não perguntou nada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I learned it alone and I'd rather nobody else had to. Ask me anything — I'll answer all of it.
    >>  ............................................
    pt  Aprendi sozinho e eu preferia que mais ninguém precisasse. Me pergunte qualquer coisa — eu respondo tudo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Three people helped without ever being asked to teach me. The mason built the cellar and asked no questions.
    >>  ............................................
    pt  Três pessoas ajudaram sem nunca serem convidadas a me ensinar. O pedreiro fez o porão e não perguntou nada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I learned it alone and I'd rather nobody else had to. Ask me anything — I'll answer all of it.
    >>  ............................................
    pt  Aprendi sozinho e eu preferia que mais ninguém precisasse. Me pergunte qualquer coisa — eu respondo tudo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  I worked it out alone because there was nobody within four valleys who had solved the same Tuesday.
    >>  ............................................
    pt  Descobri sozinho porque não havia ninguém em quatro vales que tivesse resolvido a mesma terça.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  The first three years taught me by going wrong. I would not describe how, and I remember all of it.
    >>  ............................................
    pt  Os três primeiros anos me ensinaram dando errado. Eu não descreveria como, e eu lembro de tudo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  What I know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  O que eu sei são arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I worked it out alone over eleven years. I'd give the whole of it to anybody who asked, and nobody asks.
    >>  ............................................
    pt  Descobri sozinho ao longo de onze anos. Daria tudo a quem perguntasse, e ninguém pergunta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  What I know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  O que eu sei são arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  I worked it out alone over eleven years. I'd give the whole of it to anybody who asked, and nobody asks.
    >>  ............................................
    pt  Descobri sozinho ao longo de onze anos. Daria tudo a quem perguntasse, e ninguém pergunta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  The first three years were bad and the fourth was the cellar, and after the cellar it was arithmetic.
    >>  ............................................
    pt  Os três primeiros anos foram ruins e o quarto foi o porão, e depois do porão virou aritmética.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Four times a day for twenty-seven days, and about nine times on the twenty-eighth. That is the method.
    >>  ............................................
    pt  Quatro vezes por dia por vinte e sete dias, e umas nove no vigésimo oitavo. É esse o método.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Eleven years, three of them bad. There was no shorter route and I've stopped resenting that.
    >>  ............................................
    pt  Onze anos, três deles ruins. Não havia caminho mais curto e eu parei de me revoltar com isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Slowly, one arrangement at a time. Doors first, then dates, then people. In that order and no other.
    >>  ............................................
    pt  Devagar, um arranjo por vez. Portas primeiro, depois datas, depois pessoas. Nessa ordem e nenhuma outra.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  The first three years were bad and the fourth was the cellar, and after the cellar it was arithmetic.
    >>  ............................................
    pt  Os três primeiros anos foram ruins e o quarto foi o porão, e depois do porão virou aritmética.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Four times a day for twenty-seven days, and about nine times on the twenty-eighth. That is the method.
    >>  ............................................
    pt  Quatro vezes por dia por vinte e sete dias, e umas nove no vigésimo oitavo. É esse o método.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Eleven years, three of them bad. There was no shorter route and I've stopped resenting that.
    >>  ............................................
    pt  Onze anos, três deles ruins. Não havia caminho mais curto e eu parei de me revoltar com isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Slowly, one arrangement at a time. Doors first, then dates, then people. In that order and no other.
    >>  ............................................
    pt  Devagar, um arranjo por vez. Portas primeiro, depois datas, depois pessoas. Nessa ordem e nenhuma outra.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Everything written about people like me is about what we are. Nothing at all is about Tuesday.
    >>  ............................................
    pt  Tudo que se escreve sobre gente como eu é sobre o que somos. Nada é sobre a terça-feira.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Eleven years of trial and error and one very good cellar door. That's the curriculum.
    >>  ............................................
    pt  Onze anos de tentativa e erro e uma porta de porão muito boa. É esse o currículo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Everything written about people like me is about what we are. Nothing at all is about Tuesday.
    >>  ............................................
    pt  Tudo que se escreve sobre gente como eu é sobre o que somos. Nada é sobre a terça-feira.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Eleven years of trial and error and one very good cellar door. That's the curriculum.
    >>  ............................................
    pt  Onze anos de tentativa e erro e uma porta de porão muito boa. É esse o currículo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Eleven years, three of them bad. There was no shorter route and I've stopped resenting that.
    >>  ............................................
    pt  Onze anos, três deles ruins. Não havia caminho mais curto e eu parei de me revoltar com isso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Slowly, one arrangement at a time. Doors first, then dates, then people. In that order and no other.
    >>  ............................................
    pt  Devagar, um arranjo por vez. Portas primeiro, depois datas, depois pessoas. Nessa ordem e nenhuma outra.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  I worked it out alone because there was nobody within four valleys who had solved the same Tuesday.
    >>  ............................................
    pt  Descobri sozinho porque não havia ninguém em quatro vales que tivesse resolvido a mesma terça.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  The first three years taught me by going wrong. I would not describe how, and I remember all of it.
    >>  ............................................
    pt  Os três primeiros anos me ensinaram dando errado. Eu não descreveria como, e eu lembro de tudo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  The first three years were bad and the fourth was the cellar, and after the cellar it was arithmetic.
    >>  ............................................
    pt  Os três primeiros anos foram ruins e o quarto foi o porão, e depois do porão virou aritmética.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Four times a day for twenty-seven days, and about nine times on the twenty-eighth. That is the method.
    >>  ............................................
    pt  Quatro vezes por dia por vinte e sete dias, e umas nove no vigésimo oitavo. É esse o método.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Everything written about people like me is about what we are. Nothing at all is about Tuesday.
    >>  ............................................
    pt  Tudo que se escreve sobre gente como eu é sobre o que somos. Nada é sobre a terça-feira.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Eleven years of trial and error and one very good cellar door. That's the curriculum.
    >>  ............................................
    pt  Onze anos de tentativa e erro e uma porta de porão muito boa. É esse o currículo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.craft/1
    en  Everything written about people like me is about what we are. Nothing at all is about Tuesday.
    >>  ............................................
    pt  Tudo que se escreve sobre gente como eu é sobre o que somos. Nada é sobre a terça-feira.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.craft/2
    en  Eleven years of trial and error and one very good cellar door. That's the curriculum.
    >>  ............................................
    pt  Onze anos de tentativa e erro e uma porta de porão muito boa. É esse o currículo.
    >>  ............................................
```

</details>


**Outcome 221 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.risk.respond`
- …where the player's next choices will be: "Two bolts is a thin margin." | "Living as though they won't is a decision you make every day." | "Why those three and nobody else?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.risk`: the villager reports. Subject `work.werewolf_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.risk/1   [105 chars]
    en  Two bolts and a stone cellar are all that stand between me and being the story this valley tells forever.
    >>  ............................................
    pt  Dois ferrolhos e um porão de pedra é tudo entre mim e ser a história que este vale conta pra sempre.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.risk/2   [110 chars]
    en  The three people who know could tell three more on a bad night, and I've decided to live as though they won't.
    >>  ............................................
    pt  As três pessoas que sabem podiam contar a mais três numa noite ruim, e eu decidi viver como se não fossem.
    >>  ............................................
```


**Outcome 222 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.village.respond`
- …where the player's next choices will be: "Have you tried to pay them?" | "Eleven years of nothing happening is entirely your doing." | "The cook covers the pot for you?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.village`: the villager reports. Subject `work.werewolf_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.village/1   [96 chars]
    en  Eleven years and this valley has never had a night because of me. That's the only number I keep.
    >>  ............................................
    pt  Onze anos e este vale nunca teve uma noite por minha causa. É o único número que eu guardo.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.village/2   [104 chars]
    en  The mason built the cellar, the cook covers the pot, and the priest says nothing. None of them was paid.
    >>  ............................................
    pt  O pedreiro fez o porão, o cozinheiro cobre o caldeirão, e o padre não diz nada. Nenhum foi pago.
    >>  ............................................
```


**Outcome 223 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.future.respond`
- …where the player's next choices will be: "Who would you trust to write them?" | "Pay the mason by naming the cellar in what's written." | "Does somebody always come next?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.future`: the villager reports. Subject `work.werewolf_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.future/1   [118 chars]
    en  I'd like the arrangements written down for whoever comes next. Somebody always comes next and they start from nothing.
    >>  ............................................
    pt  Queria os arranjos escritos pra quem vier depois. Sempre vem alguém depois e começam do zero.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.future/2   [104 chars]
    en  And I'd like to pay the mason, somehow, in a way he can't refuse. I've had eleven years to think of one.
    >>  ............................................
    pt  E queria pagar o pedreiro, de algum jeito que ele não possa recusar. Tive onze anos pra pensar num.
    >>  ............................................
```


**Outcome 224 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 225 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 226 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 227 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 228 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 229 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 230 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 231 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 232 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 233 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 234 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 235 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 236 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 237 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 238 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 239 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 240 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 241 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 242 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 243 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 244 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
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

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 245 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.respond`
- …where the player's next choices will be: "What happens if you break rule one?" | "Somebody has to write this down." | "You're going to get yourself killed." | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.identity`: the villager explains. Subject `work.enderian.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 246 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.respond`
- …where the player's next choices will be: "How many of them actually work?" | "The farmer's crops water themselves now." | "Most of it just explodes." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.identity`: the villager explains. Subject `work.engineer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 247 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.respond`
- …where the player's next choices will be: "What's the hardest arrangement to make?" | "Every important day here passes through your hands." | "It's decoration, not work." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.identity`: the villager explains. Subject `work.florist.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 248 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.respond`
- …where the player's next choices will be: "What do you do about a wounded one?" | "You could take more than you do." | "It's killing, dressed up in tracking." | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.identity`: the villager explains. Subject `work.hunter.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 249 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.respond`
- …where the player's next choices will be: "What do you listen for down there?" | "Every smith here is waiting on you." | "You just swing at rocks in the dark." | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.identity`: the villager explains. Subject `work.miner.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 250 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.respond`
- …where the player's next choices will be: "Which rule do people forget?" | "Nobody else here will go through that gate." | "You're just showing off." | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.identity`: the villager explains. Subject `work.netherian.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 251 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.respond`
- …where the player's next choices will be: "How long can you stay down?" | "You bring up things nobody else can reach." | "You're going to drown one day." | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.identity`: the villager explains. Subject `work.oceanographer.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 252 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.respond`
- …where the player's next choices will be: "What's a difficult piece?" | "Families live inside what you build." | "It's just cutting up trees." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.identity`: the villager explains. Subject `work.woodworker.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 253 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.respond`
- …where the player's next choices will be: "What happens when a solution goes wrong?" | "You actually know what you're doing, don't you." | "Half of this is stolen." | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.identity`: the villager explains. Subject `work.shady_wizard.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 254 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.respond`
- …where the player's next choices will be: "What ruins a service?" | "You've made people close their eyes at a first bite." | "It's all show. Cooking is cooking." | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.identity`: the villager explains. Subject `work.delightchef.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 255 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.respond`
- …where the player's next choices will be: "What's the hardest week?" | "The children eat properly because of you." | "The chef does the real cooking." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.identity`: the villager explains. Subject `work.delightcook.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 256 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.respond`
- …where the player's next choices will be: "What do you mean, some of them bite?" | "Somebody has to keep these from being lost." | "You're copying words you don't understand." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.identity`: the villager explains. Subject `work.scribe.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 257 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.respond`
- …where the player's next choices will be: "What's the part you dread?" | "Nobody here knows how close it has been." | "You're teaching people to kill their neighbours." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.identity`: the villager explains. Subject `work.hunter_expert.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 258 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.respond`
- …where the player's next choices will be: "What do you say to the family?" | "You stayed when the last one left." | "You're frightening people." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.identity`: the villager explains. Subject `work.priest.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 259 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.respond`
- …where the player's next choices will be: "Where does the advice stop?" | "People come to you instead of doing something worse." | "You're on the wrong side of this." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.identity`: the villager explains. Subject `work.vampire_expert.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 260 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `confident`, `crabby`, `greedy`, `grumpy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.respond`
- …where the player's next choices will be: "What does an unprepared month look like?" | "You treat them like people, not problems." | "You're keeping something dangerous in the village." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.identity`: the villager explains. Subject `work.werewolf_expert.identity`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 261 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.task.respond`
- …where the player's next choices will be: "Will you get it done before dark?" | "I've two hands going spare." | "Is that the worst job on the list?" | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.task`: the villager reports. Subject `work.farmer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 262 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.craft.respond`
- …where the player's next choices will be: "What else did she teach you?" | "That's a skill, reading dirt." | "Do you ever get it wrong now?" | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.craft`: the villager reports. Subject `work.farmer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 263 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.risk.respond`
- …where the player's next choices will be: "How often does it actually happen?" | "That's a lot to carry every August." | "Can you do anything about it beforehand?" | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.risk`: the villager reports. Subject `work.farmer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 264 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.village.respond`
- …where the player's next choices will be: "Who'd notice first if you gave it up?" | "Then somebody should mention it. I will." | "Do they pay you what it's worth?" | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.village`: the villager reports. Subject `work.farmer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 265 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.farmer.future.respond`
- …where the player's next choices will be: "What's in the way of it?" | "Take the season. The village will manage." | "What sort of farmer was he?" | "I'll let you get back to the row."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.farmer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.future`: the villager reports. Subject `work.farmer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 266 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.task.respond`
- …where the player's next choices will be: "Why always that corner?" | "Show me the knot and I'll do half." | "Then I'll stand here and say nothing." | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.task`: the villager reports. Subject `work.fisherman.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 267 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.craft.respond`
- …where the player's next choices will be: "Which half was gold?" | "A hundred empty mornings is its own kind of skill." | "Would you teach it to anyone?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.craft`: the villager reports. Subject `work.fisherman.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 268 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.risk.respond`
- …where the player's next choices will be: "What happened the second time?" | "You go out alone, though." | "Do you have a rule about it?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.risk`: the villager reports. Subject `work.fisherman.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 269 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.village.respond`
- …where the player's next choices will be: "Are you teaching them, then?" | "You should take the credit for Friday." | "What happens on a week you catch nothing?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.village`: the villager reports. Subject `work.fisherman.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 270 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fisherman.future.respond`
- …where the player's next choices will be: "Why haven't you gone?" | "Go in the spring. Friday will survive one week." | "How will you know when it's time?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fisherman.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.future`: the villager reports. Subject `work.fisherman.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 271 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.task.respond`
- …where the player's next choices will be: "Which number do you trust?" | "I'll walk the far side." | "Do they always argue?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.task`: the villager reports. Subject `work.shepherd.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 272 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.craft.respond`
- …where the player's next choices will be: "Whose hands were over yours?" | "Nine years to learn a smell. That's dedication." | "Could you teach it to someone?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.craft`: the villager reports. Subject `work.shepherd.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 273 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.risk.respond`
- …where the player's next choices will be: "How many have you lost?" | "You name them, though." | "Can anything be done about the wolves?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.risk`: the villager reports. Subject `work.shepherd.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 274 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.village.respond`
- …where the player's next choices will be: "What's the arrangement?" | "Everyone here is warm because of that hill." | "What happens in a bad wool year?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.village`: the villager reports. Subject `work.shepherd.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 275 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shepherd.future.respond`
- …where the player's next choices will be: "Why smaller?" | "Then take the smaller flock." | "What holds you back from the hill?" | "I'll leave you to the flock."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shepherd.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.future`: the villager reports. Subject `work.shepherd.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 276 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.task.respond`
- …where the player's next choices will be: "Will sixty happen?" | "I could sort feathers." | "What's wrong with a bent one?" | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.task`: the villager reports. Subject `work.fletcher.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 277 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.craft.respond`
- …where the player's next choices will be: "What did the guard tell you?" | "Not many trades let you be told that plainly." | "How do you test them now?" | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.craft`: the villager reports. Subject `work.fletcher.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 278 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.risk.respond`
- …where the player's next choices will be: "Who rushes you?" | "You carry the ones you don't see." | "Have you ever refused an order?" | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.risk`: the villager reports. Subject `work.fletcher.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 279 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.village.respond`
- …where the player's next choices will be: "Does the archer say so?" | "Twelve years without running short is worth saying." | "What if you did run short?" | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.village`: the villager reports. Subject `work.fletcher.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 280 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.fletcher.future.respond`
- …where the player's next choices will be: "What's in the way of the bow?" | "Make the bad one. Then make the second." | "Has anyone sat at the bench?" | "I'll let you get on with the batch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.fletcher.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.future`: the villager reports. Subject `work.fletcher.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 281 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.task.respond`
- …where the player's next choices will be: "Whose bag?" | "I can turn pages while you write." | "How old is the ledger?" | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.task`: the villager reports. Subject `work.librarian.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 282 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.craft.respond`
- …where the player's next choices will be: "How do you decide where a thing goes?" | "Teaching yourself binding from a book is properly clever." | "What's the hardest thing to mend?" | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.craft`: the villager reports. Subject `work.librarian.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 283 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.risk.respond`
- …where the player's next choices will be: "What did you tell the families?" | "That's a heavy thing to be the keeper of." | "What's on the high shelf?" | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.risk`: the villager reports. Subject `work.librarian.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 284 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.village.respond`
- …where the player's next choices will be: "Who did you teach?" | "Half the town reading is not a small thing." | "Does the ledger always settle it?" | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.village`: the villager reports. Subject `work.librarian.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 285 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.librarian.future.respond`
- …where the player's next choices will be: "What would the second room be?" | "Ask the mason. He builds things that outlast people." | "Is there anyone who wants it?" | "I'll let you get back to the shelves."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.librarian.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.future`: the villager reports. Subject `work.librarian.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 286 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.task.respond`
- …where the player's next choices will be: "How far did it move?" | "I could walk it and report back." | "You draw it all from memory?" | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.task`: the villager reports. Subject `work.cartographer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 287 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.craft.respond`
- …where the player's next choices will be: "What does a dotted line mean?" | "Marking your own doubt takes some honesty." | "How accurate is pacing, really?" | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.craft`: the villager reports. Subject `work.cartographer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 288 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.risk.respond`
- …where the player's next choices will be: "Has one of yours been wrong?" | "People trust paper more than they should." | "Why not survey the north gap?" | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.risk`: the villager reports. Subject `work.cartographer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 289 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.village.respond`
- …where the player's next choices will be: "Do they know it was your map?" | "Three families came because of you." | "What do you do about the mayor?" | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.village`: the villager reports. Subject `work.cartographer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 290 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cartographer.future.respond`
- …where the player's next choices will be: "Which description do you believe?" | "Then go, and take the paper." | "What's it like, the edge of your map?" | "I'll let you get back to the survey."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cartographer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.future`: the villager reports. Subject `work.cartographer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 291 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.task.respond`
- …where the player's next choices will be: "Is the cough serious?" | "I could carry the bottles round." | "Who are you sitting with?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.task`: the villager reports. Subject `work.cleric.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 292 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.craft.respond`
- …where the player's next choices will be: "How does listening go wrong?" | "Knowing which is wanted is rarer than the brewing." | "Have you added to the book?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.craft`: the villager reports. Subject `work.cleric.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 293 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.risk.respond`
- …where the player's next choices will be: "How do you say a thing like that?" | "And who sits in the room with you afterwards?" | "Can you put that confidence down?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.risk`: the villager reports. Subject `work.cleric.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 294 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.village.respond`
- …where the player's next choices will be: "How do you check on them?" | "Eleven years is a number worth keeping." | "Could I walk past a door or two?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.village`: the villager reports. Subject `work.cleric.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 295 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cleric.future.respond`
- …where the player's next choices will be: "What makes someone want the afternoons?" | "You'd know one if you met them." | "What did they know that's lost?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cleric.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.future`: the villager reports. Subject `work.cleric.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 296 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.task.respond`
- …where the player's next choices will be: "Three in one place. What does that tell you?" | "I can hold the piece steady." | "Does a fitting really matter that much?" | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.task`: the villager reports. Subject `work.armorer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 297 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.craft.respond`
- …where the player's next choices will be: "Why have you never told him?" | "Restraint is the harder half." | "What happened at fifteen years?" | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.craft`: the villager reports. Subject `work.armorer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 298 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.risk.respond`
- …where the player's next choices will be: "How do you live with not knowing?" | "That's a strange weight to carry alone." | "Could the fuel be sorted before winter?" | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.risk`: the villager reports. Subject `work.armorer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 299 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.village.respond`
- …where the player's next choices will be: "What happened to the two?" | "Nineteen years and the count holds." | "Does anyone pay for all that?" | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.village`: the villager reports. Subject `work.armorer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 300 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.armorer.future.respond`
- …where the player's next choices will be: "Who would it be for?" | "Make it. The stand can wait for the wearer." | "Has nobody wanted the forge?" | "I'll let you get back to the fire."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.armorer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.future`: the villager reports. Subject `work.armorer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 301 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.task.respond`
- …where the player's next choices will be: "What's wrong with the commission?" | "I can pump the bellows." | "How bad is a damp scabbard?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.task`: the villager reports. Subject `work.weaponsmith.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 302 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.craft.respond`
- …where the player's next choices will be: "Four years of just watching?" | "Persuading steel to stay is a good way to put it." | "Would you teach it the same way?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.craft`: the villager reports. Subject `work.weaponsmith.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 303 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.risk.respond`
- …where the player's next choices will be: "What happened with that one?" | "You can't be responsible for every hand." | "Do you turn people away now?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.risk`: the villager reports. Subject `work.weaponsmith.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 304 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.village.respond`
- …where the player's next choices will be: "Would you want it mentioned?" | "Then I'll mention it. That night mattered." | "Could you sell only to the watch?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.village`: the villager reports. Subject `work.weaponsmith.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 305 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.weaponsmith.future.respond`
- …where the player's next choices will be: "What's keeping you from ploughs?" | "Make the blade. You've earned one selfish thing." | "Why does it take nerve?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.weaponsmith.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.future`: the villager reports. Subject `work.weaponsmith.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 306 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.task.respond`
- …where the player's next choices will be: "What are they doing to them?" | "I could take the grindstone for a few." | "You don't charge him?" | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.task`: the villager reports. Subject `work.toolsmith.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 307 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.craft.respond`
- …where the player's next choices will be: "What can you tell from wear?" | "That's a strange and useful thing to be able to do." | "What makes a handle good?" | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.craft`: the villager reports. Subject `work.toolsmith.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 308 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.risk.respond`
- …where the player's next choices will be: "Was it the tool's fault?" | "And you checked for a month anyway." | "Can you talk them out of fitting their own hafts?" | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.risk`: the villager reports. Subject `work.toolsmith.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 309 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.village.respond`
- …where the player's next choices will be: "Why small and sharp?" | "That's the sort of thing a place is built out of." | "Does the queue ever end?" | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.village`: the villager reports. Subject `work.toolsmith.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 310 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.toolsmith.future.respond`
- …where the player's next choices will be: "What's wrong with the angle now?" | "Prove it on one. That's not a month." | "How many sets would that be?" | "I'll let you get back to the queue."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.toolsmith.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.future`: the villager reports. Subject `work.toolsmith.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 311 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.task.respond`
- …where the player's next choices will be: "What happens if the heat wins?" | "I can pack barrels." | "Is February really that close a thing?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.task`: the villager reports. Subject `work.butcher.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 312 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.craft.respond`
- …where the player's next choices will be: "How can you tell it was frightened?" | "Following the animal rather than the knife is well put." | "How little goes to waste?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.craft`: the villager reports. Subject `work.butcher.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 313 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.risk.respond`
- …where the player's next choices will be: "How do you catch a bad barrel?" | "The rule about names sounds like it costs you." | "What happened the year you broke it?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.risk`: the villager reports. Subject `work.butcher.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 314 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.village.respond`
- …where the player's next choices will be: "How do you decide?" | "Somebody has to hold that, and you do." | "You put yourself last?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.village`: the villager reports. Subject `work.butcher.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 315 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.butcher.future.respond`
- …where the player's next choices will be: "What would moving the pens take?" | "Call it a plan, not a preference, and ask again." | "Who takes the block after you?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.butcher.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.future`: the villager reports. Subject `work.butcher.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 316 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.task.respond`
- …where the player's next choices will be: "Does the smell ever leave?" | "I could turn hides." | "Why not tell him he's broader?" | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.task`: the villager reports. Subject `work.leatherworker.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 317 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.craft.respond`
- …where the player's next choices will be: "Who paid for the nine hides?" | "A bad hole is forever. That's a whole philosophy." | "How do you make a good hole?" | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.craft`: the villager reports. Subject `work.leatherworker.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 318 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.risk.respond`
- …where the player's next choices will be: "Has anyone ever gone in?" | "Two saddles a year and you lose sleep over both." | "What makes a saddle fail?" | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.risk`: the villager reports. Subject `work.leatherworker.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 319 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.village.respond`
- …where the player's next choices will be: "How many pairs a year?" | "Everyone walking on something you made is not nothing." | "Does the butcher see it that way?" | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.village`: the villager reports. Subject `work.leatherworker.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 320 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.leatherworker.future.respond`
- …where the player's next choices will be: "Eleven years of the same request?" | "Take somebody with you next time." | "What would the proper saddle be?" | "I'll let you get back to the vat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.leatherworker.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.future`: the villager reports. Subject `work.leatherworker.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 321 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.task.respond`
- …where the player's next choices will be: "Why is identical harder?" | "I can haul stone." | "How bad is soft mortar?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.task`: the villager reports. Subject `work.mason.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 322 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.craft.respond`
- …where the player's next choices will be: "Was the year of carrying worth it?" | "Twenty years and you still admit to getting it wrong." | "How does stone tell you?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.craft`: the villager reports. Subject `work.mason.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 323 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.risk.respond`
- …where the player's next choices will be: "What did you find when you looked?" | "You went to the next valley though it wasn't yours." | "Does the outliving weigh on you?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.risk`: the villager reports. Subject `work.mason.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 324 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.village.respond`
- …where the player's next choices will be: "Twice?" | "You'll be here longer than any of us." | "Is cutting the names hard?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.village`: the villager reports. Subject `work.mason.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 325 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mason.future.respond`
- …where the player's next choices will be: "Why an arch?" | "Fifty-one is time enough for both." | "Is there really nobody else?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mason.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.future`: the villager reports. Subject `work.mason.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 326 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.task.respond`
- …where the player's next choices will be: "Who came in before noon?" | "That's worth knowing. Does anyone ask you?" | "Nobody's given you anything to do?" | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.task`: the villager reports. Subject `work.nitwit.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 327 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.craft.respond`
- …where the player's next choices will be: "Which ones flood?" | "That's real knowledge. It's just not written down." | "Who isn't speaking to whom?" | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.craft`: the villager reports. Subject `work.nitwit.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 328 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.risk.respond`
- …where the player's next choices will be: "What do people say in front of you?" | "Being talked over isn't the same as not being there." | "Forty years is a long time to wait to be asked." | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.risk`: the villager reports. Subject `work.nitwit.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 329 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.village.respond`
- …where the player's next choices will be: "Did you tell anyone?" | "Then somebody should say it now. You got them out." | "Would you tell the mayor if he asked?" | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.village`: the villager reports. Subject `work.nitwit.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 330 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.nitwit.future.respond`
- …where the player's next choices will be: "What post would you want?" | "Then I'll say your name next to it." | "What holds you here?" | "I'll let you get back to the clouds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.nitwit.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.future`: the villager reports. Subject `work.nitwit.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 331 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.task.respond`
- …where the player's next choices will be: "Does the mill pay you?" | "I'll take the afternoon with you." | "Would you want something with a name to it?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.task`: the villager reports. Subject `work.none.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 332 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.craft.respond`
- …where the player's next choices will be: "Which six?" | "Knowing who needs what is a skill people build careers on." | "Somebody's son turned sixteen?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.craft`: the villager reports. Subject `work.none.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 333 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.risk.respond`
- …where the player's next choices will be: "Has it come to that?" | "Being useful shouldn't have to be a survival plan." | "What would a bottom look like?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.risk`: the villager reports. Subject `work.none.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 334 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.village.respond`
- …where the player's next choices will be: "What happens in an emergency?" | "Those weeks are in the walls. Somebody should say so." | "Does it go back to how it was every time?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.village`: the villager reports. Subject `work.none.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 335 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.none.future.respond`
- …where the player's next choices will be: "What would they have to fill?" | "Ask for the post before you go east." | "What's holding you from the town?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.none.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.future`: the villager reports. Subject `work.none.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 336 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.task.respond`
- …where the player's next choices will be: "What answer are you waiting on?" | "I can ask along the east road for you." | "Is a day of mending straps so bad?" | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.task`: the villager reports. Subject `work.adventurer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 337 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.craft.respond`
- …where the player's next choices will be: "How do you know when to turn round?" | "Being wrong somewhere costly is a hard school." | "What were you wrong about?" | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.craft`: the villager reports. Subject `work.adventurer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 338 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.risk.respond`
- …where the player's next choices will be: "What happened in the one you didn't choose?" | "Coming back to a place that managed without you is its own wound." | "Is this place home now?" | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.risk`: the villager reports. Subject `work.adventurer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 339 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.village.respond`
- …where the player's next choices will be: "Who's the other person who knows?" | "A spare key is what gets people through a locked door." | "Would you rather be used more often?" | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.village`: the villager reports. Subject `work.adventurer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 340 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.adventurer.future.respond`
- …where the player's next choices will be: "Why not go back to the valley?" | "Take the roof first. The road keeps." | "Six years of one more road?" | "I'll let you rest up."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.adventurer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.future`: the villager reports. Subject `work.adventurer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 341 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.task.respond`
- …where the player's next choices will be: "Four places? Show me one." | "I could shift the wood stack." | "Two hundred every morning?" | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.task`: the villager reports. Subject `work.archer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 342 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.craft.respond`
- …where the player's next choices will be: "What does the breath do?" | "Not being able to explain it is a kind of mastery." | "How do you teach it, then?" | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.craft`: the villager reports. Subject `work.archer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 343 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.risk.respond`
- …where the player's next choices will be: "How do you make that decision?" | "First thing aimed at is a heavy way to describe your post." | "Does an arrow ever sit badly with you?" | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.risk`: the villager reports. Subject `work.archer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 344 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.village.respond`
- …where the player's next choices will be: "Does it bother you, the not knowing your name?" | "Six years is the whole reason this place has children in it." | "Is that arrangement fair?" | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.village`: the villager reports. Subject `work.archer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 345 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.archer.future.respond`
- …where the player's next choices will be: "What's needed for a second archer?" | "Tell her. Let her decide what to do with it." | "Too old for what?" | "I'll let you get back to the sightline."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.archer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.future`: the villager reports. Subject `work.archer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 346 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.task.respond`
- …where the player's next choices will be: "What are the forty lines?" | "I could fetch fire if the lamp went out." | "Has the lamp ever gone out?" | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.task`: the villager reports. Subject `work.cultist.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 347 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.craft.respond`
- …where the player's next choices will be: "How did she test you?" | "Holding something only in memory takes real discipline." | "What happened when you repeated it at nineteen?" | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.craft`: the villager reports. Subject `work.cultist.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 348 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.risk.respond`
- …where the player's next choices will be: "What if you are wrong about it?" | "Being treated carefully every day must be tiring." | "What came before careful?" | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.risk`: the villager reports. Subject `work.cultist.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 349 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.village.respond`
- …where the player's next choices will be: "What's 'the rest'?" | "Burning it for the eleven who don't come is decent of you." | "Which two families come?" | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village`: the villager reports. Subject `work.cultist.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 350 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.cultist.future.respond`
- …where the player's next choices will be: "Why did the three fail?" | "Ordinary is a fair thing to want." | "What happens if you pass nobody?" | "I'll let you get back to... reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.cultist.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.future`: the villager reports. Subject `work.cultist.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 351 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.task.respond`
- …where the player's next choices will be: "What happens at hour six?" | "I'll walk the perimeter with you." | "Do you tell on the children?" | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.task`: the villager reports. Subject `work.guard.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 352 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.craft.respond`
- …where the player's next choices will be: "When did something last look different?" | "Describing the square with your eyes shut is a real method." | "Do you hate him now?" | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.craft`: the villager reports. Subject `work.guard.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 353 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.risk.respond`
- …where the player's next choices will be: "What happened at the wrong gate?" | "You couldn't have been at both gates." | "What changed after?" | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.risk`: the villager reports. Subject `work.guard.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 354 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.village.respond`
- …where the player's next choices will be: "Why don't those six lock?" | "Two rounds a night that nobody assigned you." | "Why can't you claim the four years?" | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.village`: the villager reports. Subject `work.guard.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 355 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.guard.future.respond`
- …where the player's next choices will be: "Has the mayor said no?" | "Then say it in front of the families with the six doors." | "Would you really want to be unnecessary?" | "I'll let you keep watch."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.guard.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.future`: the villager reports. Subject `work.guard.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 356 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.task.respond`
- …where the player's next choices will be: "What sort of contract are you waiting for?" | "There's harvest labour going if you'd take it." | "Why keep the kit clean for nothing?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.task`: the villager reports. Subject `work.mercenary.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 357 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.craft.respond`
- …where the player's next choices will be: "How do you tell which to refuse?" | "Learning what to refuse is the part nobody trains for." | "Eleven of forty?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.craft`: the villager reports. Subject `work.mercenary.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 358 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.risk.respond`
- …where the player's next choices will be: "Have you had that year?" | "Calling both of those fair costs you something." | "Would you leave you alone with children?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.risk`: the villager reports. Subject `work.mercenary.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 359 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.village.respond`
- …where the player's next choices will be: "What was the unpaid one?" | "The unpaid one counts. Somebody should know it." | "Why would the guard rather you left?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.village`: the villager reports. Subject `work.mercenary.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 360 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.mercenary.future.respond`
- …where the player's next choices will be: "Why haven't you answered?" | "Ask the guard for the post before spring." | "What's the difference between a wage and a purse?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.mercenary.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.future`: the villager reports. Subject `work.mercenary.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 361 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.task.respond`
- …where the player's next choices will be: "Why does being seen doing nothing take all day?" | "I'll split the rest of that kindling." | "Why does the widow ask you?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.task`: the villager reports. Subject `work.outlaw.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 362 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.craft.respond`
- …where the player's next choices will be: "What do you read in four seconds?" | "That's a skill a guard would pay for." | "Could you learn carpentry now?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.craft`: the villager reports. Subject `work.outlaw.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 363 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.risk.respond`
- …where the player's next choices will be: "Has something gone missing?" | "Being first suspected every time would wear anybody down." | "What happens when somebody walks in who knows the name?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.risk`: the villager reports. Subject `work.outlaw.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 364 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.village.respond`
- …where the player's next choices will be: "Who let you stay?" | "Four years of no trouble is you keeping your side." | "Nobody's invited you through a door?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.village`: the villager reports. Subject `work.outlaw.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 365 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.outlaw.future.respond`
- …where the player's next choices will be: "Who might be willing?" | "Walk in the tenth time. I'll come with you." | "Would the road really be better?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.outlaw.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.future`: the villager reports. Subject `work.outlaw.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 366 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.task.respond`
- …where the player's next choices will be: "Why is the eleventh the hard one?" | "I could stack the seasoning stock." | "What happened to the three chairs?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.task`: the villager reports. Subject `work.woodworker.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 367 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.craft.respond`
- …where the player's next choices will be: "How do you guess which way?" | "Guessing years ahead is a strange thing to be good at." | "Did the knuckle-rapping teach you anything?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.craft`: the villager reports. Subject `work.woodworker.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 368 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.risk.respond`
- …where the player's next choices will be: "Why don't you walk past it?" | "Two fingers is a hard way to be reminded." | "What went wrong with the saw?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.risk`: the villager reports. Subject `work.woodworker.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 369 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.village.respond`
- …where the player's next choices will be: "Nine infants in the same cradle?" | "Not signing them doesn't mean nobody knows." | "How many doors in this place are yours?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.village`: the villager reports. Subject `work.woodworker.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 370 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.woodworker.future.respond`
- …where the player's next choices will be: "Where would a staircase even go?" | "Then build it for the mill and call it a commission." | "What's different about the three doors?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.woodworker.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.future`: the villager reports. Subject `work.woodworker.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 371 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.task.respond`
- …where the player's next choices will be: "How do you know four is enough?" | "I can haul for you." | "Is the hauling really the deciding half?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.task`: the villager reports. Subject `work.miner.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 372 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.craft.respond`
- …where the player's next choices will be: "How does rock lie to you?" | "Eleven is young to be underground." | "How do you teach it without words?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.craft`: the villager reports. Subject `work.miner.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 373 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.risk.respond`
- …where the player's next choices will be: "Who was the other one?" | "And you set four props every day since." | "What do you mean, on loan?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.risk`: the villager reports. Subject `work.miner.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 374 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.village.respond`
- …where the player's next choices will be: "Would you like him to ask?" | "Somebody's back carried all of it. That's worth saying." | "How much comes up in a year?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.village`: the villager reports. Subject `work.miner.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 375 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.miner.future.respond`
- …where the player's next choices will be: "Saving it for what?" | "Find the second pair of hands first." | "Inscription?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.miner.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.future`: the villager reports. Subject `work.miner.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 376 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.task.respond`
- …where the player's next choices will be: "How do you do both in one morning?" | "I can cut the early stems." | "What changes after ten?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.task`: the villager reports. Subject `work.florist.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 377 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.craft.respond`
- …where the player's next choices will be: "Why keep a book like that?" | "That book is doing more than flowers." | "Which bed is cold in May?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.craft`: the villager reports. Subject `work.florist.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 378 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.risk.respond`
- …where the player's next choices will be: "Can you do anything about a frost?" | "Being at every funeral without being family is its own weight." | "Fifty-one funerals?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.risk`: the villager reports. Subject `work.florist.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 379 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.village.respond`
- …where the player's next choices will be: "Both ends of everybody — does that sit strangely?" | "Colour in February is a kindness people don't credit." | "Have the beds ever been bare?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.village`: the villager reports. Subject `work.florist.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 380 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.florist.future.respond`
- …where the player's next choices will be: "What would a glasshouse cost?" | "Ask the families in the book to fund the glass." | "Who would you give the book to?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.florist.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.future`: the villager reports. Subject `work.florist.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 381 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.task.respond`
- …where the player's next choices will be: "Four days of nothing — is that unusual?" | "I'll take the north half of the snares." | "Why walk the line if it isn't worth it?" | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.task`: the villager reports. Subject `work.hunter.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 382 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.craft.respond`
- …where the player's next choices will be: "Did 'look again' actually teach you?" | "Calling it arithmetic is more honest than most would be." | "What was the ground doing yesterday?" | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.craft`: the villager reports. Subject `work.hunter.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 383 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.risk.respond`
- …where the player's next choices will be: "How do you know what it can spare?" | "Three hours from anybody is a long way to be alone." | "Who do you tell your line to?" | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.risk`: the villager reports. Subject `work.hunter.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 384 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.village.respond`
- …where the player's next choices will be: "How lean do they get?" | "A valley full of deer is the whole achievement." | "Nobody has framed it that way?" | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.village`: the villager reports. Subject `work.hunter.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 385 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter.future.respond`
- …where the player's next choices will be: "Why isn't the count written down?" | "The librarian would write it and ask no questions." | "How long do the knees have?" | "I'll let you get back to the tracks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.future`: the villager reports. Subject `work.hunter.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 386 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.task.respond`
- …where the player's next choices will be: "Which will you choose?" | "I can hold the measure at the far end." | "Nearly, you said?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.task`: the villager reports. Subject `work.engineer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 387 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.craft.respond`
- …where the player's next choices will be: "Does it always go back together?" | "Three machines self-taught is not a small claim." | "Whose clock did you break?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.craft`: the villager reports. Subject `work.engineer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 388 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.risk.respond`
- …where the player's next choices will be: "What would calling you before look like?" | "Being the person called only after is a thankless place." | "Has the winch ever failed?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.risk`: the villager reports. Subject `work.engineer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 389 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.village.respond`
- …where the player's next choices will be: "You shut it without permission?" | "Nine minutes and you got it right. That deserves saying." | "What happens if one of the three goes?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.village`: the villager reports. Subject `work.engineer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 390 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.engineer.future.respond`
- …where the player's next choices will be: "The mill doesn't run in August?" | "Put it to the miller as three weeks of flour." | "Why are the careful ones useless?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.engineer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.future`: the villager reports. Subject `work.engineer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 391 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.task.respond`
- …where the player's next choices will be: "Above all of them — is that bad?" | "I'll count the north shore." | "What does the census tell you?" | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.task`: the villager reports. Subject `work.oceanographer.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 392 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.craft.respond`
- …where the player's next choices will be: "Eleven years of nothing?" | "Eleven years before the shape appeared is real patience." | "What did the books get wrong?" | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.craft`: the villager reports. Subject `work.oceanographer.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 393 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.risk.respond`
- …where the player's next choices will be: "Are you going to say so?" | "Being right and being believed are two separate problems." | "The marks give no warning at all?" | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.risk`: the villager reports. Subject `work.oceanographer.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 394 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.village.respond`
- …where the player's next choices will be: "Why not tell the fishermen?" | "The only record anywhere is worth more than a post." | "What happens to the post in a storm?" | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.village`: the villager reports. Subject `work.oceanographer.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 395 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.oceanographer.future.respond`
- …where the player's next choices will be: "Where would the second post go?" | "Twenty years of walking there is a reason to start now." | "Why is a gap so bad?" | "I'll let you get to the water."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.oceanographer.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.future`: the villager reports. Subject `work.oceanographer.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 396 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.task.respond`
- …where the player's next choices will be: "What sickness?" | "I could catalogue while you sit." | "What can't you name?" | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.task`: the villager reports. Subject `work.enderian.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 397 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.craft.respond`
- …where the player's next choices will be: "How often do you arrive where you meant to?" | "Finding out the cost afterwards is a hard order to learn in." | "What happens on the tenth time?" | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.craft`: the villager reports. Subject `work.enderian.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 398 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.risk.respond`
- …where the player's next choices will be: "Why is the watching fair?" | "Nobody knowing what happened is the worse half of that." | "Who would find the note?" | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.risk`: the villager reports. Subject `work.enderian.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 399 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.village.respond`
- …where the player's next choices will be: "Who was the cleric for?" | "Two lives in an afternoon each. Say that out loud sometime." | "Does the not-inviting bother you?" | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.village`: the villager reports. Subject `work.enderian.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 400 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.enderian.future.respond`
- …where the player's next choices will be: "You'd go back for the warm thing?" | "Then that's a last trip worth taking." | "Who wouldn't be frightened of the catalogue?" | "I'll let you get back to the notes."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.enderian.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.future`: the villager reports. Subject `work.enderian.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 401 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.task.respond`
- …where the player's next choices will be: "It could take the roof off?" | "I could turn the drying trays." | "Four grains from two hours?" | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.task`: the villager reports. Subject `work.netherian.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 402 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.craft.respond`
- …where the player's next choices will be: "How many failures?" | "Writing down failures is rarer than writing down successes." | "What are the four underlined ones?" | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.craft`: the villager reports. Subject `work.netherian.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 403 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.risk.respond`
- …where the player's next choices will be: "How do you know a batch is right?" | "Testing it on yourself is not a small thing to mention in passing." | "What does the cleric want you to do instead?" | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.risk`: the villager reports. Subject `work.netherian.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 404 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.village.respond`
- …where the player's next choices will be: "Do the four know?" | "Four people are alive. That outranks the half who'd prefer otherwise." | "Does the half that objects say so to you?" | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.village`: the villager reports. Subject `work.netherian.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 405 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.netherian.future.respond`
- …where the player's next choices will be: "Would stone actually make it safe?" | "Then ask the four families to build it." | "Why would a successor repeat the failures?" | "I'll let you get ready."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.netherian.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.future`: the villager reports. Subject `work.netherian.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 406 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.task.respond`
- …where the player's next choices will be: "What wouldn't you sell, and to whom?" | "I can label the jars." | "Why pretend to understand it?" | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.task`: the villager reports. Subject `work.shady_wizard.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 407 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.craft.respond`
- …where the player's next choices will be: "What was in the chest?" | "Knowing which half is theatre is more than most manage." | "Have you read all nine books?" | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.craft`: the villager reports. Subject `work.shady_wizard.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 408 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.risk.respond`
- …where the player's next choices will be: "Has it happened?" | "Having to prove otherwise every time is exhausting." | "What's on the top shelf?" | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.risk`: the villager reports. Subject `work.shady_wizard.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 409 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.village.respond`
- …where the player's next choices will be: "The birth that went sideways?" | "A private ledger is a ledger nobody can argue with." | "Does the crossing the lane bother you?" | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.village`: the villager reports. Subject `work.shady_wizard.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 410 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.shady_wizard.future.respond`
- …where the player's next choices will be: "What's preventing you from saying it?" | "Say it once to the cleric. She already knows." | "Who's sensible enough for the books?" | "I'll let you get back to business."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.shady_wizard.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.future`: the villager reports. Subject `work.shady_wizard.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 411 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.task.respond`
- …where the player's next choices will be: "You plan it backwards from the bread?" | "I can skim the stock for you." | "They'd notice the absence?" | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.task`: the villager reports. Subject `work.delightchef.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 412 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.craft.respond`
- …where the player's next choices will be: "What's different about cooking alone?" | "A decade for timing sounds about right, honestly." | "Do you miss the kitchen with nine people?" | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.craft`: the villager reports. Subject `work.delightchef.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 413 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.risk.respond`
- …where the player's next choices will be: "What happens if you're ill in December?" | "Cooking through a fever rather than cancelling — that says something." | "Has a pot ever gone wrong?" | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.risk`: the villager reports. Subject `work.delightchef.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 414 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.village.respond`
- …where the player's next choices will be: "How do you cook for a wake?" | "Eleven years of nobody eating badly is a real record." | "You and the florist, at both ends?" | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.village`: the villager reports. Subject `work.delightchef.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 415 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightchef.future.respond`
- …where the player's next choices will be: "Why can't the timings be written?" | "Write the wrong numbers. Somebody can correct them." | "Has nobody wanted the second pair of hands?" | "I'll let you get back to service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightchef.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.future`: the villager reports. Subject `work.delightchef.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 416 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.task.respond`
- …where the player's next choices will be: "Which four houses?" | "I can carry the four loaves round." | "What's in the pot today?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.task`: the villager reports. Subject `work.delightcook.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 417 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.craft.respond`
- …where the player's next choices will be: "What were her proportions?" | "Feeding twelve on nothing is a harder skill than a feast." | "Why haven't you matched her?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.craft`: the villager reports. Subject `work.delightcook.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 418 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.risk.respond`
- …where the player's next choices will be: "How thin has it had to get?" | "Your own flour, unasked and unfunded, is a quiet kind of giving." | "Would the mayor really refuse to fund it?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.risk`: the villager reports. Subject `work.delightcook.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 419 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.village.respond`
- …where the player's next choices will be: "Do you say anything to the ones who come back?" | "Knowing before the mayor does is worth more than the pot." | "Who are the nineteen?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.village`: the villager reports. Subject `work.delightcook.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 420 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.delightcook.future.respond`
- …where the player's next choices will be: "What would funding the pot take?" | "Ask for it as a line, not a favour." | "Whose head should have the proportions?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.delightcook.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.future`: the villager reports. Subject `work.delightcook.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 421 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.task.respond`
- …where the player's next choices will be: "Do you believe it happened?" | "I could read one version aloud while you check the other." | "Why would they disagree about who was there?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.task`: the villager reports. Subject `work.scribe.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 422 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.craft.respond`
- …where the player's next choices will be: "How do you tell the difference?" | "Burning ten of eleven sounds cruel and probably wasn't." | "What was on the page?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.craft`: the villager reports. Subject `work.scribe.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 423 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.risk.respond`
- …where the player's next choices will be: "Who would burn it?" | "A thatched roof over that is a thing worth solving." | "Have you ever copied something wrong?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.risk`: the villager reports. Subject `work.scribe.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 424 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.village.respond`
- …where the player's next choices will be: "Two hundred years is a long time to be patient." | "Half the memory of a place is not nothing." | "Do you and the librarian ever compare?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.village`: the villager reports. Subject `work.scribe.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 425 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.scribe.future.respond`
- …where the player's next choices will be: "Where would the second copy live?" | "Send it. An unsent copy is the same as no copy." | "What are the eleven disagreements about?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.scribe.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.future`: the villager reports. Subject `work.scribe.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 426 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.task.respond`
- …where the player's next choices will be: "What shouldn't they be frightened of?" | "I'll take the boundary with you tonight." | "The same walk every evening?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.task`: the villager reports. Subject `work.hunter_expert.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 427 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.craft.respond`
- …where the player's next choices will be: "What was wrong with being certain?" | "Learning to be less certain is not the usual direction." | "How often has it actually been one?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.craft`: the villager reports. Subject `work.hunter_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 428 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.risk.respond`
- …where the player's next choices will be: "Where did you read the account?" | "You carry another man's mistake as if it were yours." | "Why not tell your two why you're slow?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.risk`: the villager reports. Subject `work.hunter_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 429 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.village.respond`
- …where the player's next choices will be: "Not one name in nine years?" | "Three refusals is a harder record than three catches." | "Why never disagree in public?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.village`: the villager reports. Subject `work.hunter_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 430 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.hunter_expert.future.respond`
- …where the player's next choices will be: "What would the rule say?" | "Then write it. The scribe would keep a copy." | "Why will they hate being slower?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.hunter_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.future`: the villager reports. Subject `work.hunter_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 431 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.task.respond`
- …where the player's next choices will be: "What are the two sentences?" | "I could sit with them instead this afternoon." | "What are they frightened of?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.task`: the villager reports. Subject `work.priest.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 432 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.craft.respond`
- …where the player's next choices will be: "Why does the pause matter so much?" | "Twenty years to unlearn answering is an honest account." | "What's the third thing they say?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.craft`: the villager reports. Subject `work.priest.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 433 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.risk.respond`
- …where the player's next choices will be: "What's the way you're most likely to be wrong?" | "Making the fortnight longer is a thin thing to hold a valley with." | "Does the hunter share the fortnight?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.risk`: the villager reports. Subject `work.priest.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 434 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.village.respond`
- …where the player's next choices will be: "What do you do about the list?" | "Nobody driven out in twenty years is the record that counts." | "Has it ever come close?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.village`: the villager reports. Subject `work.priest.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 435 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.priest.future.respond`
- …where the player's next choices will be: "You'd argue with him in public now?" | "Then argue at the reading of the rule. Let them watch." | "Who could keep an unwritten list?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.priest.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.future`: the villager reports. Subject `work.priest.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 436 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.task.respond`
- …where the player's next choices will be: "What do the useful ones ask?" | "I could take the letters to the road for you." | "Does the asking-what-you-are get tiring?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.task`: the villager reports. Subject `work.vampire_expert.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 437 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.craft.respond`
- …where the player's next choices will be: "What are you not?" | "Assembling it from letters is a real body of knowledge." | "Forty people write to you?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.craft`: the villager reports. Subject `work.vampire_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 438 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.risk.respond`
- …where the player's next choices will be: "Have you ever nearly taken the bag?" | "The letters costing somebody else is the worse half." | "You burn all of them?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.risk`: the villager reports. Subject `work.vampire_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 439 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.village.respond`
- …where the player's next choices will be: "Furniture is a strange thing to be grateful for." | "No panic in nineteen years is partly you and you should say so." | "Why does it need proving?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.village`: the villager reports. Subject `work.vampire_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 440 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.vampire_expert.future.respond`
- …where the player's next choices will be: "Why keep those four?" | "The scribe sends a copy four valleys away. Send them with it." | "What would it take to put the bag away?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.vampire_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.future`: the villager reports. Subject `work.vampire_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 441 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `peppy`, `upbeat`, `playful`, `witty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.task.respond`
- …where the player's next choices will be: "How do people take the three days?" | "I could check the cellar door with you." | "How often do you check it?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.task
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.task.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.task`: the villager reports. Subject `work.werewolf_expert.task`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 442 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `introverted`, `odd`, `shy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.craft.respond`
- …where the player's next choices will be: "Who do you tell?" | "Arrangements are the practical knowledge nobody records." | "Eleven years of solving it alone?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.craft
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.craft.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.craft`: the villager reports. Subject `work.werewolf_expert.craft`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 443 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `sensitive`, `anxious`, `gloomy`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.risk.respond`
- …where the player's next choices will be: "Two bolts is a thin margin." | "Living as though they won't is a decision you make every day." | "Why those three and nobody else?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.risk
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.risk.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.risk`: the villager reports. Subject `work.werewolf_expert.risk`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 444 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `friendly`, `extroverted`, `flirty`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.village.respond`
- …where the player's next choices will be: "Have you tried to pay them?" | "Eleven years of nothing happening is entirely your doing." | "The cook covers the pot for you?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.village
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.village`: the villager reports. Subject `work.werewolf_expert.village`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 445 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert" AND the personality is `relaxed`, `peaceful`, `lazy`, `athletic`
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.werewolf_expert.future.respond`
- …where the player's next choices will be: "Who would you trust to write them?" | "Pay the mason by naming the cellar in what's written." | "Does somebody always come next?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.future
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.werewolf_expert.future.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.future`: the villager reports. Subject `work.werewolf_expert.future`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (9 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 446 of 447** — base weight `2`

- Fires when: weighted +3 when the personality is `confident`
- Fires when: weighted +3 when the mood is `happy`
- Fires when: RULED OUT when the `templates` feature is OFF  _(chance -2000)_
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.pride.respond`
- …where the player's next choices will be: "You're good at what you do." | "Why does it matter to you?" | "You'd say that whether it was true or not." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.generic
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.pride.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = profession_name
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.templated`: the villager explains. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:any`, `feeling:content` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.generic/1   [76 chars]
    en  Being a %2$s isn't glamorous, but it's mine, and I'm better at it than most.
    >>  ............................................
    pt  Ser %2$s não é glamouroso, mas é meu, e eu faço melhor que a maioria.
    >>  ............................................
  dialogue.conversations.work.generic/2   [86 chars]
    en  A %2$s's day starts before the sun and ends after the complaints. I'd still choose it.
    >>  ............................................
    pt  O dia de um %2$s começa antes do sol e termina depois das reclamações. Ainda assim eu escolheria.
    >>  ............................................
  dialogue.conversations.work.generic/3   [70 chars]
    en  Somebody in this village has to be the %2$s. Turns out somebody is me.
    >>  ............................................
    pt  Alguém nesse vilarejo tem que ser o %2$s. Acontece que esse alguém sou eu.
    >>  ............................................
```


**Outcome 447 of 447** — base weight `0`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +2 when the `templates` feature is OFF
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.pride.respond`
- …where the player's next choices will be: "You're good at what you do." | "Why does it matter to you?" | "You'd say that whether it was true or not." | "I'll let you work."

```text
POOL   dialogue key: dialogue.conversations.work.like
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work, button `(auto)`
       leaves the player on: conversations.topic.work.pride.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.pride.likes_it`: the villager explains. Subject `work.any.pride`, polarity `positive`, permits followup, outcome `None`.
NOTE   this is the line that establishes `work:any`, `feeling:content` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, challenge, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.work.legacy / (auto)
```

```text
  dialogue.conversations.work.like/1   [72 chars]
    en  I do, most days. There's a rhythm to it. You stop noticing the blisters.
    >>  ............................................
    pt  Gosto, na maioria dos dias. Tem um ritmo nisso. Você para de sentir os calos.
    >>  ............................................
  dialogue.conversations.work.like/2   [61 chars]
    en  It's mine, you know? Nobody tells me how to run my own bench.
    >>  ............................................
    pt  É meu, sabe? Ninguém manda em como eu toco a minha própria bancada.
    >>  ............................................
  dialogue.conversations.work.like/3   [75 chars]
    en  Ask me at dawn and I'll grumble. Ask me when it's done and I'm proud of it.
    >>  ............................................
    pt  Me pergunte de madrugada e eu resmungo. Me pergunte com a obra pronta e eu me orgulho dela.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.like/1
    en  I do, when I know I've done it right. It's the not-knowing that gets to me — so I check. Often.
    >>  ............................................
    pt  Gosto, quando eu sei que fiz certo. É o não-saber que me pega — então eu confiro. Com frequência.
    >>  ............................................
  anxious.dialogue.conversations.work.like/2
    en  Yes — it gives me something certain to hold on to. I like certain things.
    >>  ............................................
    pt  Sim — me dá algo certo pra segurar. Eu gosto de coisas certas.
    >>  ............................................
  athletic.dialogue.conversations.work.like/1
    en  Love it. It's all reps. Lift, carry, repeat till dusk. Sleep like a rock, wake up hungry, go again.
    >>  ............................................
    pt  Amo. É tudo repetição. Levanta, carrega, repete até anoitecer. Durmo que nem pedra, acordo com fome, e vou de novo.
    >>  ............................................
  athletic.dialogue.conversations.work.like/2
    en  It's paid training, really. Every haul makes me stronger. Can't beat that.
    >>  ............................................
    pt  É treino pago, na verdade. Cada carga me deixa mais forte. Não tem como bater isso.
    >>  ............................................
  confident.dialogue.conversations.work.like/1
    en  Naturally. I'm the best at it for three villages in any direction, and that is a simple statement of fact.
    >>  ............................................
    pt  Naturalmente. Sou o melhor nisso em três vilarejos em qualquer direção, e isso é uma simples constatação de fato.
    >>  ............................................
  confident.dialogue.conversations.work.like/2
    en  Love it — there's nothing like being very good at a thing and knowing it.
    >>  ............................................
    pt  Adoro — não há nada como ser muito bom numa coisa e saber disso.
    >>  ............................................
  crabby.dialogue.conversations.work.like/1
    en  It's work. It gets done. That's as much as I'll say for it — and more than most jobs deserve.
    >>  ............................................
    pt  É trabalho. Ele é feito. É tudo que eu digo dele — e mais do que a maioria dos empregos merece.
    >>  ............................................
  crabby.dialogue.conversations.work.like/2
    en  It suits me. Nobody talks to me while I'm doing it. That's the appeal.
    >>  ............................................
    pt  Me serve. Ninguém fala comigo enquanto eu faço. É esse o apelo.
    >>  ............................................
  extroverted.dialogue.conversations.work.like/1
    en  I love it! You meet everyone doing this. Half my friendships started over this work.
    >>  ............................................
    pt  Eu amo! Você conhece todo mundo fazendo isso. Metade das minhas amizades começou nesse trabalho.
    >>  ............................................
  extroverted.dialogue.conversations.work.like/2
    en  Very much — mostly because it puts me right in the middle of everything. That's where I like to be.
    >>  ............................................
    pt  Muitíssimo — principalmente porque me coloca bem no meio de tudo. É ali que eu gosto de estar.
    >>  ............................................
  flirty.dialogue.conversations.work.like/1
    en  I'm very good at it, you know. I'm very good at a lot of things.
    >>  ............................................
    pt  Eu sou muito boa nisso, sabe. Sou muito boa em muitas coisas.
    >>  ............................................
  flirty.dialogue.conversations.work.like/2
    en  Love it — keeps my hands busy and my eyes free to wander. And they do wander, %1$s.
    >>  ............................................
    pt  Amo — mantém minhas mãos ocupadas e meus olhos livres pra passear. E eles passeiam, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.work.like/1
    en  I do! You see everybody's faces, hear the news, share a laugh. The work's mostly the excuse, really.
    >>  ............................................
    pt  Gosto! Você vê a cara de todo mundo, ouve as novidades, dá risada junto. O trabalho é quase só a desculpa, na verdade.
    >>  ............................................
  friendly.dialogue.conversations.work.like/2
    en  Very much! Every customer's a visit waiting to happen. I'd do it for the company alone.
    >>  ............................................
    pt  Muitíssimo! Cada cliente é uma visita esperando pra acontecer. Eu faria só pela companhia.
    >>  ............................................
  gloomy.dialogue.conversations.work.like/1
    en  It keeps my hands busy so my head can't wander. That's worth something.
    >>  ............................................
    pt  Mantém minhas mãos ocupadas pra minha cabeça não vagar. Isso vale alguma coisa.
    >>  ............................................
  gloomy.dialogue.conversations.work.like/2
    en  It'll outlast me, which is more than I'll manage. I find that steadying rather than sad.
    >>  ............................................
    pt  Vai durar mais que eu, o que é mais do que eu vou conseguir. Acho isso firme, não triste.
    >>  ............................................
  greedy.dialogue.conversations.work.like/1
    en  I love it. Every blister converts to bread eventually. Show me another investment with returns like that.
    >>  ............................................
    pt  Amo. Cada calo se converte em pão com o tempo. Me mostre outro investimento com retorno desses.
    >>  ............................................
  greedy.dialogue.conversations.work.like/2
    en  I'd not do it for free. Then again I'd not do anything for free, so that tells you nothing. Yes. I like it.
    >>  ............................................
    pt  Eu não faria de graça. Aliás, eu não faria nada de graça, então isso não diz nada. Sim. Eu gosto.
    >>  ............................................
  grumpy.dialogue.conversations.work.like/1
    en  It's work. It gets done. That's as much as I'll say for it — and more than most jobs deserve.
    >>  ............................................
    pt  É trabalho. Ele é feito. É tudo que eu digo dele — e mais do que a maioria dos empregos merece.
    >>  ............................................
  grumpy.dialogue.conversations.work.like/2
    en  It suits me. Nobody talks to me while I'm doing it. That's the appeal.
    >>  ............................................
    pt  Me serve. Ninguém fala comigo enquanto eu faço. É esse o apelo.
    >>  ............................................
  introverted.dialogue.conversations.work.like/1
    en  I do. It's honest work that doesn't require an audience — just me, the task, and enough quiet to think.
    >>  ............................................
    pt  Gosto. É trabalho honesto que não exige plateia — só eu, a tarefa, e silêncio suficiente pra pensar.
    >>  ............................................
  introverted.dialogue.conversations.work.like/2
    en  Very much. My hands work and my mind wanders somewhere better. That's a fair arrangement.
    >>  ............................................
    pt  Muito. Minhas mãos trabalham e minha cabeça vagueia por um lugar melhor. É um acordo justo.
    >>  ............................................
  lazy.dialogue.conversations.work.like/1
    en  I do. It's steady, and it doesn't shout at me. Done properly at my own pace, it's honest work.
    >>  ............................................
    pt  Gosto. É constante, e não grita comigo. Feito direito no meu ritmo, é trabalho honesto.
    >>  ............................................
  lazy.dialogue.conversations.work.like/2
    en  Well enough. I've never seen the point of rushing something you'll only have to redo.
    >>  ............................................
    pt  Bem o bastante. Nunca vi sentido em apressar uma coisa que você só vai ter que refazer.
    >>  ............................................
  odd.dialogue.conversations.work.like/1
    en  The work likes me, which matters more. Tools remember gentle hands. Mine remember me back — except the hammer. We're quarreling.
    >>  ............................................
    pt  O trabalho gosta de mim, o que importa mais. Ferramentas lembram de mãos gentis. As minhas lembram de mim — menos o martelo. Estamos brigados.
    >>  ............................................
  odd.dialogue.conversations.work.like/2
    en  Yes. It's the one part of the day that doesn't require me to explain myself.
    >>  ............................................
    pt  Sim. É a única parte do dia que não exige que eu me explique.
    >>  ............................................
  peaceful.dialogue.conversations.work.like/1
    en  I do. There's a rhythm to it, and when I find that rhythm the hours pass like water.
    >>  ............................................
    pt  Gosto. Tem um compasso nele, e quando eu encontro esse compasso as horas passam como água.
    >>  ............................................
  peaceful.dialogue.conversations.work.like/2
    en  Yes. Honest work, done carefully, is its own kind of quiet. I've come to value that.
    >>  ............................................
    pt  Sim. Trabalho honesto, feito com cuidado, é um tipo próprio de silêncio. Aprendi a valorizar isso.
    >>  ............................................
  peppy.dialogue.conversations.work.like/1
    en  LOVE it! There's a rhythm, and I hum along, and sometimes the humming becomes a whole song — anyway YES!
    >>  ............................................
    pt  AMO! Tem um ritmo, e eu cantarolo junto, e às vezes o cantarolar vira uma música inteira — enfim, SIM!
    >>  ............................................
  peppy.dialogue.conversations.work.like/2
    en  It's a race! Against yesterday's me! Yesterday's me is getting worried, I can tell!
    >>  ............................................
    pt  É uma corrida! Contra o eu de ontem! O eu de ontem está ficando preocupado, dá pra ver!
    >>  ............................................
  playful.dialogue.conversations.work.like/1
    en  It's better when I make a game of it. Beat yesterday's count, race the rain — suddenly it's not work at all.
    >>  ............................................
    pt  Fica melhor quando eu faço disso um jogo. Bater o número de ontem, correr contra a chuva — de repente nem é trabalho.
    >>  ............................................
  playful.dialogue.conversations.work.like/2
    en  I like it fine. I like it more when nobody's watching how I do it.
    >>  ............................................
    pt  Gosto, sim. Gosto mais quando ninguém está olhando como eu faço.
    >>  ............................................
  relaxed.dialogue.conversations.work.like/1
    en  I do. It's steady, and it doesn't shout at me. Done properly at my own pace, it's honest work.
    >>  ............................................
    pt  Gosto. É constante, e não grita comigo. Feito direito no meu ritmo, é trabalho honesto.
    >>  ............................................
  relaxed.dialogue.conversations.work.like/2
    en  Well enough. I've never seen the point of rushing something you'll only have to redo.
    >>  ............................................
    pt  Bem o bastante. Nunca vi sentido em apressar uma coisa que você só vai ter que refazer.
    >>  ............................................
  sensitive.dialogue.conversations.work.like/1
    en  I do. You can pour a whole mood into your hands and the work just... holds it for you. Does yours do that?
    >>  ............................................
    pt  Gosto. Você despeja um humor inteiro nas mãos e o trabalho simplesmente... segura pra você. O seu faz isso?
    >>  ............................................
  sensitive.dialogue.conversations.work.like/2
    en  I do. There's nobody to disappoint in it — the wood doesn't mind if my hands shake.
    >>  ............................................
    pt  Gosto. Não tem ninguém pra decepcionar nele — a madeira não se importa se as minhas mãos tremem.
    >>  ............................................
  shy.dialogue.conversations.work.like/1
    en  I do. It's honest work that doesn't require an audience — just me, the task, and enough quiet to think.
    >>  ............................................
    pt  Gosto. É trabalho honesto que não exige plateia — só eu, a tarefa, e silêncio suficiente pra pensar.
    >>  ............................................
  shy.dialogue.conversations.work.like/2
    en  Very much. My hands work and my mind wanders somewhere better. That's a fair arrangement.
    >>  ............................................
    pt  Muito. Minhas mãos trabalham e minha cabeça vagueia por um lugar melhor. É um acordo justo.
    >>  ............................................
  upbeat.dialogue.conversations.work.like/1
    en  I do, actually! It keeps my hands busy and my head quiet, and at the end there's something that wasn't there before.
    >>  ............................................
    pt  Gosto, sim! Mantém minhas mãos ocupadas e minha cabeça quieta, e no fim tem uma coisa que não existia antes.
    >>  ............................................
  upbeat.dialogue.conversations.work.like/2
    en  Very much. There's a real satisfaction in doing a thing properly, even a small thing. Especially a small thing.
    >>  ............................................
    pt  Muito. Tem uma satisfação real em fazer uma coisa direito, mesmo uma coisa pequena. Principalmente uma coisa pequena.
    >>  ............................................
  witty.dialogue.conversations.work.like/1
    en  I do, actually! It keeps my hands busy and my head quiet, and at the end there's something that wasn't there before.
    >>  ............................................
    pt  Gosto, sim! Mantém minhas mãos ocupadas e minha cabeça quieta, e no fim tem uma coisa que não existia antes.
    >>  ............................................
  witty.dialogue.conversations.work.like/2
    en  Very much. There's a real satisfaction in doing a thing properly, even a small thing. Especially a small thing.
    >>  ............................................
    pt  Muito. Tem uma satisfação real em fazer uma coisa direito, mesmo uma coisa pequena. Principalmente uma coisa pequena.
    >>  ............................................
```

</details>

---


## `conversations.work.legacy`

Question flags: `auto`, `silent`. An `auto` node shows no buttons — it plays its one answer straight through.

**Reached from 1 route(s):** `conversations.cat.profession` / `work`


```text
POOL   dialogue key: dialogue.conversations.work.legacy
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.work.legacy
ARGS   %1$s = the player's name
SIZE   0 lines in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

> **No English line ships under this key.** Write one, and its `pt_br` twin.

```text
  dialogue.conversations.work.legacy
    en  (missing)
    >>  ............................................
    pt  (missing)
    >>  ............................................
```


### Button `(auto)` — "(no label — this is an auto answer)"

```text
POOL   dialogue key: dialogue.conversations.work.legacy.(auto)
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.work.legacy
ARGS   none — button labels take no substitutions; write plain text
SIZE   0 lines in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

> **No English line ships under this key.** Write one, and its `pt_br` twin.

```text
  dialogue.conversations.work.legacy.(auto)
    en  (missing)
    >>  ............................................
    pt  (missing)
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:farmer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.farmer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 2 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fisherman"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fisherman.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 3 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:shepherd"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shepherd
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shepherd.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 4 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:fletcher"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fletcher.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 5 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:librarian"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.librarian.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 6 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cartographer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cartographer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cartographer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 7 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:cleric"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cleric
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cleric.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 8 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:armorer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.armorer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.armorer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 9 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:weaponsmith"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.weaponsmith.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 10 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:toolsmith"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.toolsmith.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 11 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:butcher"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.butcher
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.butcher.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 12 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.leatherworker.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 13 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mason.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 14 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.nitwit
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.nitwit.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 15 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.none
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.none.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 16 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.guard.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 17 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.archer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 18 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.adventurer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.adventurer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 19 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mercenary
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mercenary.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 20 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cultist.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 21 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.outlaw
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.outlaw.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 22 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.enderian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.enderian.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 23 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.engineer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 24 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.florist.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 25 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 26 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.miner
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.miner.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 27 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.netherian.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 28 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.oceanographer
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.oceanographer.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 29 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.woodworker.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 30 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shady_wizard.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 31 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightchef.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 32 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightcook
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightcook.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 33 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.scribe.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 34 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter_expert.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 35 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:priest"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.priest
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.priest.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 36 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:vampire_expert"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.vampire_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.vampire_expert.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 37 of 40** — base weight `0`

- Fires when: weighted +100 when `profession` = "werewolves:werewolf_expert"
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.werewolf_expert.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto); conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 38 of 40** — base weight `5`

- Fires when: weighted +60 when the personality is `relaxed`
- Fires when: weighted +50 when the mood is `depressed`
- Fires when: weighted +40 when the mood is `sad`
- Fires when: weighted +40 when the mood is `unhappy`
- Fires when: RULED OUT when has the memory `mcaconversations.state.workhate.done` (this player only)  _(chance -1000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Does: remembers `mcaconversations.state.workhate.done` (this player only) for 168000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.hate
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hate.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 39 of 40** — base weight `2`

- Fires when: weighted +3 when the personality is `confident`
- Fires when: weighted +3 when the mood is `happy`
- Fires when: RULED OUT when the `templates` feature is OFF  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.generic
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = profession_name
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.generic.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.


**Outcome 40 of 40** — base weight `0`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: weighted +2 when the `templates` feature is OFF
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.like
WHO    VILLAGER — what the player reads after pressing "(no label — this is an auto answer)"
       spoken on: conversations.work.legacy, button `(auto)`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.like.terminal`: the villager accepts. Subject `work.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.work / (auto)
```

> Written out in full under **`conversations.work` / button `(auto)`** earlier in this file. Fill it in there, once.

---

