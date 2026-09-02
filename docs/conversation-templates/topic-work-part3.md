# Topic: work — part 3 of 4

> Continued from [topic-work-part1.md](topic-work-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](topic-work-part1.md) · [part 2](topic-work-part2.md) · [part 3](topic-work-part3.md) · [part 4](topic-work-part4.md)


## Nodes in this file

- [`conversations.work`](#conversations-work)

---

## `conversations.work` — continued


**Outcome 95 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
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

```text
  dialogue.conversations.work.prof.leatherworker.craft/1   [91 chars]
    en  Tanning is chemistry you learn by ruining things. I ruined nine hides before I ruined none.
    >>  ............................................
    pt  Curtir é química que se aprende estragando coisas. Estraguei nove couros antes de não estragar nenhum.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.craft/2   [87 chars]
    en  The stitch matters less than the awl. A bad hole is forever; a bad stitch you pull out.
    >>  ............................................
    pt  O ponto importa menos que a sovela. Um furo ruim é pra sempre; um ponto ruim se desfaz.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides, and I paid my master back out of my first three years of takings without telling him why.
    >>  ............................................
    pt  Nove couros estragados, e eu paguei meu mestre com os ganhos dos três primeiros anos sem dizer por quê.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The hardest part to learn is that a bad hole cannot be undone. I know because I ruined somebody's saddle.
    >>  ............................................
    pt  O mais difícil de aprender é que um furo ruim não se desfaz. Eu sei porque estraguei a sela de alguém.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine hides over about four years. Ruining them faster would not have taught me anything sooner.
    >>  ............................................
    pt  Nove couros ao longo de uns quatro anos. Estragar mais rápido não teria me ensinado mais cedo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Slowly. Hides take months and my master took years, and neither was ever going to be hurried.
    >>  ............................................
    pt  Devagar. Couros levam meses e meu mestre levou anos, e nenhum dos dois ia ser apressado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Chemistry you learn by ruining things. Nine hides ruined before I ruined none, and my master paid for all nine.
    >>  ............................................
    pt  Química que se aprende estragando coisas. Nove couros estragados antes de eu não estragar nenhum, e meu mestre pagou os nove.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The stitch matters less than the awl. A bad hole is forever and a bad stitch you pull out.
    >>  ............................................
    pt  O ponto importa menos que a sovela. Um furo ruim é pra sempre e um ponto ruim se desfaz.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Chemistry you learn by ruining things. Nine hides ruined before I ruined none, and my master paid for all nine.
    >>  ............................................
    pt  Química que se aprende estragando coisas. Nove couros estragados antes de eu não estragar nenhum, e meu mestre pagou os nove.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The stitch matters less than the awl. A bad hole is forever and a bad stitch you pull out.
    >>  ............................................
    pt  O ponto importa menos que a sovela. Um furo ruim é pra sempre e um ponto ruim se desfaz.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.craft/1
    en  My master taught me and never billed me for the nine hides I ruined. I think about that more than he'd like.
    >>  ............................................
    pt  Meu mestre me ensinou e nunca me cobrou os nove couros que eu estraguei. Penso nisso mais do que ele gostaria.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.craft/2
    en  I learned by being allowed to waste his materials. It's the most generous thing anyone has done for me.
    >>  ............................................
    pt  Aprendi sendo autorizado a desperdiçar o material dele. É a coisa mais generosa que já fizeram por mim.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.craft/1
    en  My master taught me and never billed me for the nine hides I ruined. I think about that more than he'd like.
    >>  ............................................
    pt  Meu mestre me ensinou e nunca me cobrou os nove couros que eu estraguei. Penso nisso mais do que ele gostaria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.craft/2
    en  I learned by being allowed to waste his materials. It's the most generous thing anyone has done for me.
    >>  ............................................
    pt  Aprendi sendo autorizado a desperdiçar o material dele. É a coisa mais generosa que já fizeram por mim.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.craft/1
    en  My master taught me and never billed me for the nine hides I ruined. I think about that more than he'd like.
    >>  ............................................
    pt  Meu mestre me ensinou e nunca me cobrou os nove couros que eu estraguei. Penso nisso mais do que ele gostaria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.craft/2
    en  I learned by being allowed to waste his materials. It's the most generous thing anyone has done for me.
    >>  ............................................
    pt  Aprendi sendo autorizado a desperdiçar o material dele. É a coisa mais generosa que já fizeram por mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides, and I paid my master back out of my first three years of takings without telling him why.
    >>  ............................................
    pt  Nove couros estragados, e eu paguei meu mestre com os ganhos dos três primeiros anos sem dizer por quê.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The hardest part to learn is that a bad hole cannot be undone. I know because I ruined somebody's saddle.
    >>  ............................................
    pt  O mais difícil de aprender é que um furo ruim não se desfaz. Eu sei porque estraguei a sela de alguém.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Chemistry you learn by ruining things. Nine hides ruined before I ruined none, and my master paid for all nine.
    >>  ............................................
    pt  Química que se aprende estragando coisas. Nove couros estragados antes de eu não estragar nenhum, e meu mestre pagou os nove.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The stitch matters less than the awl. A bad hole is forever and a bad stitch you pull out.
    >>  ............................................
    pt  O ponto importa menos que a sovela. Um furo ruim é pra sempre e um ponto ruim se desfaz.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Chemistry you learn by ruining things. Nine hides ruined before I ruined none, and my master paid for all nine.
    >>  ............................................
    pt  Química que se aprende estragando coisas. Nove couros estragados antes de eu não estragar nenhum, e meu mestre pagou os nove.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The stitch matters less than the awl. A bad hole is forever and a bad stitch you pull out.
    >>  ............................................
    pt  O ponto importa menos que a sovela. Um furo ruim é pra sempre e um ponto ruim se desfaz.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The awl does the work. Your part is to hold steady and not help, which is the hardest instruction I give.
    >>  ............................................
    pt  A sovela faz o trabalho. Sua parte é ficar firme e não ajudar, a instrução mais difícil que eu dou.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine hides over about four years. Ruining them faster would not have taught me anything sooner.
    >>  ............................................
    pt  Nove couros ao longo de uns quatro anos. Estragar mais rápido não teria me ensinado mais cedo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Slowly. Hides take months and my master took years, and neither was ever going to be hurried.
    >>  ............................................
    pt  Devagar. Couros levam meses e meu mestre levou anos, e nenhum dos dois ia ser apressado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The awl does the work. Your part is to hold steady and not help, which is the hardest instruction I give.
    >>  ............................................
    pt  A sovela faz o trabalho. Sua parte é ficar firme e não ajudar, a instrução mais difícil que eu dou.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine hides over about four years. Ruining them faster would not have taught me anything sooner.
    >>  ............................................
    pt  Nove couros ao longo de uns quatro anos. Estragar mais rápido não teria me ensinado mais cedo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Slowly. Hides take months and my master took years, and neither was ever going to be hurried.
    >>  ............................................
    pt  Devagar. Couros levam meses e meu mestre levou anos, e nenhum dos dois ia ser apressado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides! My master never once mentioned the cost, which I only understood about ten years later.
    >>  ............................................
    pt  Nove couros estragados! Meu mestre nunca mencionou o custo, o que eu só entendi uns dez anos depois.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Tanning is chemistry with a smell. Wonderful trade. Nobody sits next to me at supper.
    >>  ............................................
    pt  Curtir é química com cheiro. Ofício maravilhoso. Ninguém senta do meu lado no jantar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides! My master never once mentioned the cost, which I only understood about ten years later.
    >>  ............................................
    pt  Nove couros estragados! Meu mestre nunca mencionou o custo, o que eu só entendi uns dez anos depois.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Tanning is chemistry with a smell. Wonderful trade. Nobody sits next to me at supper.
    >>  ............................................
    pt  Curtir é química com cheiro. Ofício maravilhoso. Ninguém senta do meu lado no jantar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine hides over about four years. Ruining them faster would not have taught me anything sooner.
    >>  ............................................
    pt  Nove couros ao longo de uns quatro anos. Estragar mais rápido não teria me ensinado mais cedo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Slowly. Hides take months and my master took years, and neither was ever going to be hurried.
    >>  ............................................
    pt  Devagar. Couros levam meses e meu mestre levou anos, e nenhum dos dois ia ser apressado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides, and I paid my master back out of my first three years of takings without telling him why.
    >>  ............................................
    pt  Nove couros estragados, e eu paguei meu mestre com os ganhos dos três primeiros anos sem dizer por quê.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The hardest part to learn is that a bad hole cannot be undone. I know because I ruined somebody's saddle.
    >>  ............................................
    pt  O mais difícil de aprender é que um furo ruim não se desfaz. Eu sei porque estraguei a sela de alguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Mark the hole, breathe, push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Marque o furo, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.craft/2
    en  The awl does the work. Your part is to hold steady and not help, which is the hardest instruction I give.
    >>  ............................................
    pt  A sovela faz o trabalho. Sua parte é ficar firme e não ajudar, a instrução mais difícil que eu dou.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides! My master never once mentioned the cost, which I only understood about ten years later.
    >>  ............................................
    pt  Nove couros estragados! Meu mestre nunca mencionou o custo, o que eu só entendi uns dez anos depois.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Tanning is chemistry with a smell. Wonderful trade. Nobody sits next to me at supper.
    >>  ............................................
    pt  Curtir é química com cheiro. Ofício maravilhoso. Ninguém senta do meu lado no jantar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.craft/1
    en  Nine ruined hides! My master never once mentioned the cost, which I only understood about ten years later.
    >>  ............................................
    pt  Nove couros estragados! Meu mestre nunca mencionou o custo, o que eu só entendi uns dez anos depois.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.craft/2
    en  Tanning is chemistry with a smell. Wonderful trade. Nobody sits next to me at supper.
    >>  ............................................
    pt  Curtir é química com cheiro. Ofício maravilhoso. Ninguém senta do meu lado no jantar.
    >>  ............................................
```

</details>


**Outcome 96 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
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

```text
  dialogue.conversations.work.prof.leatherworker.risk/1   [91 chars]
    en  The pits are poison and everyone's boots walk past them. I've a fence and I check it daily.
    >>  ............................................
    pt  Os tanques são veneno e as botas de todo mundo passam por perto. Tenho cerca e confiro todo dia.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.risk/2   [91 chars]
    en  A saddle that fails throws somebody at speed. I make two a year and I lose sleep over both.
    >>  ............................................
    pt  Uma sela que falha joga alguém em velocidade. Faço duas por ano e perco sono com as duas.
    >>  ............................................
```


**Outcome 97 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
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

```text
  dialogue.conversations.work.prof.leatherworker.village/1   [85 chars]
    en  Boots. That's the whole of my case. Everyone in this place walks on something I made.
    >>  ............................................
    pt  Botas. É todo o meu argumento. Todo mundo aqui pisa em algo que eu fiz.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.village/2   [85 chars]
    en  The butcher's hides would be buried without me and half his trade would go with them.
    >>  ............................................
    pt  Os couros do açougueiro seriam enterrados sem mim e metade do negócio dele iria junto.
    >>  ............................................
```


**Outcome 98 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:leatherworker"
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

```text
  dialogue.conversations.work.prof.leatherworker.future/1   [96 chars]
    en  I want the pits moved downwind and downstream. It's the same request I've made for eleven years.
    >>  ............................................
    pt  Quero os tanques a favor do vento e rio abaixo. É o mesmo pedido que eu faço há onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.future/2   [101 chars]
    en  There's a saddle I could make properly if I had a month and nobody needed boots. Neither will happen.
    >>  ............................................
    pt  Tem uma sela que eu faria direito se eu tivesse um mês e ninguém precisasse de bota. Nenhum dos dois acontece.
    >>  ............................................
```


**Outcome 99 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
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

```text
  dialogue.conversations.work.prof.mason.task/1   [88 chars]
    en  Cutting sill stones. Six of them, all identical, and identical is harder than beautiful.
    >>  ............................................
    pt  Cortando peitoris. Seis deles, todos idênticos, e idêntico é mais difícil que bonito.
    >>  ............................................
  dialogue.conversations.work.prof.mason.task/2   [88 chars]
    en  Repointing the well. The mortar's gone soft on the north face and nobody noticed but me.
    >>  ............................................
    pt  Rejuntando o poço. A argamassa amoleceu na face norte e ninguém reparou além de mim.
    >>  ............................................
```


**Outcome 100 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
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

```text
  dialogue.conversations.work.prof.mason.craft/1   [94 chars]
    en  Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season.
    >>  ............................................
    pt  A pedra te diz onde quer rachar. Vinte anos e eu ainda erro duas vezes por estação.
    >>  ............................................
  dialogue.conversations.work.prof.mason.craft/2   [99 chars]
    en  I was taught by a man who let me cut nothing for a year. I carried and I watched and I resented it.
    >>  ............................................
    pt  Fui ensinado por um homem que não me deixou cortar nada por um ano. Eu carreguei, observei e me revoltei.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying and watching, and I hated him the whole time, and he died before I could say thank you.
    >>  ............................................
    pt  Um ano carregando e observando, e eu o odiei o tempo todo, e ele morreu antes de eu agradecer.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mason.craft/2
    en  You learn it wrong and then a wall stands wrong for a hundred years. That is what makes it slow.
    >>  ............................................
    pt  Você aprende errado e aí uma parede fica errada por cem anos. É isso que torna lento.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.craft/1
    en  A year carrying, four cutting badly, and then it simply started coming out right. Stone sets its own term.
    >>  ............................................
    pt  Um ano carregando, quatro cortando mal, e aí simplesmente começou a sair certo. A pedra define o prazo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.craft/2
    en  Slowly, from a slow man, in a cold yard. I have never met a version of this that hurried.
    >>  ............................................
    pt  Devagar, com um homem lento, num pátio frio. Nunca vi uma versão disso com pressa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.craft/1
    en  Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season.
    >>  ............................................
    pt  A pedra te diz onde quer rachar. Vinte anos e eu ainda erro duas vezes por estação.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.craft/2
    en  I was taught by a man who let me cut nothing for a year. I carried, I watched, and I resented it.
    >>  ............................................
    pt  Fui ensinado por um homem que não me deixou cortar nada por um ano. Carreguei, observei e me revoltei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.craft/1
    en  Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season.
    >>  ............................................
    pt  A pedra te diz onde quer rachar. Vinte anos e eu ainda erro duas vezes por estação.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.craft/2
    en  I was taught by a man who let me cut nothing for a year. I carried, I watched, and I resented it.
    >>  ............................................
    pt  Fui ensinado por um homem que não me deixou cortar nada por um ano. Carreguei, observei e me revoltei.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.craft/1
    en  A man taught me by making me carry for a year. I do the same to my own now and they resent me identically.
    >>  ............................................
    pt  Um homem me ensinou fazendo eu carregar por um ano. Faço o mesmo com os meus e me detestam igual.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.craft/2
    en  I learned it standing next to somebody. There's no version of this trade you can be told over a table.
    >>  ............................................
    pt  Aprendi de pé ao lado de alguém. Não existe versão deste ofício que se conte numa mesa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.craft/1
    en  A man taught me by making me carry for a year. I do the same to my own now and they resent me identically.
    >>  ............................................
    pt  Um homem me ensinou fazendo eu carregar por um ano. Faço o mesmo com os meus e me detestam igual.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.craft/2
    en  I learned it standing next to somebody. There's no version of this trade you can be told over a table.
    >>  ............................................
    pt  Aprendi de pé ao lado de alguém. Não existe versão deste ofício que se conte numa mesa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.craft/1
    en  A man taught me by making me carry for a year. I do the same to my own now and they resent me identically.
    >>  ............................................
    pt  Um homem me ensinou fazendo eu carregar por um ano. Faço o mesmo com os meus e me detestam igual.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.craft/2
    en  I learned it standing next to somebody. There's no version of this trade you can be told over a table.
    >>  ............................................
    pt  Aprendi de pé ao lado de alguém. Não existe versão deste ofício que se conte numa mesa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying and watching, and I hated him the whole time, and he died before I could say thank you.
    >>  ............................................
    pt  Um ano carregando e observando, e eu o odiei o tempo todo, e ele morreu antes de eu agradecer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.craft/2
    en  You learn it wrong and then a wall stands wrong for a hundred years. That is what makes it slow.
    >>  ............................................
    pt  Você aprende errado e aí uma parede fica errada por cem anos. É isso que torna lento.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.craft/1
    en  Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season.
    >>  ............................................
    pt  A pedra te diz onde quer rachar. Vinte anos e eu ainda erro duas vezes por estação.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.craft/2
    en  I was taught by a man who let me cut nothing for a year. I carried, I watched, and I resented it.
    >>  ............................................
    pt  Fui ensinado por um homem que não me deixou cortar nada por um ano. Carreguei, observei e me revoltei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.craft/1
    en  Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season.
    >>  ............................................
    pt  A pedra te diz onde quer rachar. Vinte anos e eu ainda erro duas vezes por estação.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.craft/2
    en  I was taught by a man who let me cut nothing for a year. I carried, I watched, and I resented it.
    >>  ............................................
    pt  Fui ensinado por um homem que não me deixou cortar nada por um ano. Carreguei, observei e me revoltei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.craft/1
    en  Grain, sound, and how dust sits in the scratch. Mostly sound. Tap it and listen.
    >>  ............................................
    pt  Fibra, som, e como a poeira assenta no risco. Principalmente som. Bata e escute.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.craft/2
    en  There are marks on my yard wall for every stone I've got wrong. Anyone is welcome to count them.
    >>  ............................................
    pt  Tem marcas na parede do meu pátio pra cada pedra que eu errei. Qualquer um pode contar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.craft/1
    en  A year carrying, four cutting badly, and then it simply started coming out right. Stone sets its own term.
    >>  ............................................
    pt  Um ano carregando, quatro cortando mal, e aí simplesmente começou a sair certo. A pedra define o prazo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.craft/2
    en  Slowly, from a slow man, in a cold yard. I have never met a version of this that hurried.
    >>  ............................................
    pt  Devagar, com um homem lento, num pátio frio. Nunca vi uma versão disso com pressa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.craft/1
    en  Grain, sound, and how dust sits in the scratch. Mostly sound. Tap it and listen.
    >>  ............................................
    pt  Fibra, som, e como a poeira assenta no risco. Principalmente som. Bata e escute.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.craft/2
    en  There are marks on my yard wall for every stone I've got wrong. Anyone is welcome to count them.
    >>  ............................................
    pt  Tem marcas na parede do meu pátio pra cada pedra que eu errei. Qualquer um pode contar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.craft/1
    en  A year carrying, four cutting badly, and then it simply started coming out right. Stone sets its own term.
    >>  ............................................
    pt  Um ano carregando, quatro cortando mal, e aí simplesmente começou a sair certo. A pedra define o prazo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.craft/2
    en  Slowly, from a slow man, in a cold yard. I have never met a version of this that hurried.
    >>  ............................................
    pt  Devagar, com um homem lento, num pátio frio. Nunca vi uma versão disso com pressa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying before I was allowed to cut anything. I'd have punched him at the time. He was right.
    >>  ............................................
    pt  Um ano carregando antes de me deixarem cortar. Eu teria batido nele na época. Ele tinha razão.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.craft/2
    en  Twenty years and I still get it wrong twice a season! A mason who says otherwise is building something to avoid.
    >>  ............................................
    pt  Vinte anos e eu ainda erro duas vezes por estação! Um pedreiro que diz o contrário constrói algo a se evitar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying before I was allowed to cut anything. I'd have punched him at the time. He was right.
    >>  ............................................
    pt  Um ano carregando antes de me deixarem cortar. Eu teria batido nele na época. Ele tinha razão.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.craft/2
    en  Twenty years and I still get it wrong twice a season! A mason who says otherwise is building something to avoid.
    >>  ............................................
    pt  Vinte anos e eu ainda erro duas vezes por estação! Um pedreiro que diz o contrário constrói algo a se evitar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.craft/1
    en  A year carrying, four cutting badly, and then it simply started coming out right. Stone sets its own term.
    >>  ............................................
    pt  Um ano carregando, quatro cortando mal, e aí simplesmente começou a sair certo. A pedra define o prazo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.craft/2
    en  Slowly, from a slow man, in a cold yard. I have never met a version of this that hurried.
    >>  ............................................
    pt  Devagar, com um homem lento, num pátio frio. Nunca vi uma versão disso com pressa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying and watching, and I hated him the whole time, and he died before I could say thank you.
    >>  ............................................
    pt  Um ano carregando e observando, e eu o odiei o tempo todo, e ele morreu antes de eu agradecer.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.craft/2
    en  You learn it wrong and then a wall stands wrong for a hundred years. That is what makes it slow.
    >>  ............................................
    pt  Você aprende errado e aí uma parede fica errada por cem anos. É isso que torna lento.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.craft/1
    en  Grain, sound, and how dust sits in the scratch. Mostly sound. Tap it and listen.
    >>  ............................................
    pt  Fibra, som, e como a poeira assenta no risco. Principalmente som. Bata e escute.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.craft/2
    en  There are marks on my yard wall for every stone I've got wrong. Anyone is welcome to count them.
    >>  ............................................
    pt  Tem marcas na parede do meu pátio pra cada pedra que eu errei. Qualquer um pode contar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying before I was allowed to cut anything. I'd have punched him at the time. He was right.
    >>  ............................................
    pt  Um ano carregando antes de me deixarem cortar. Eu teria batido nele na época. Ele tinha razão.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.craft/2
    en  Twenty years and I still get it wrong twice a season! A mason who says otherwise is building something to avoid.
    >>  ............................................
    pt  Vinte anos e eu ainda erro duas vezes por estação! Um pedreiro que diz o contrário constrói algo a se evitar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.craft/1
    en  A year of carrying before I was allowed to cut anything. I'd have punched him at the time. He was right.
    >>  ............................................
    pt  Um ano carregando antes de me deixarem cortar. Eu teria batido nele na época. Ele tinha razão.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.craft/2
    en  Twenty years and I still get it wrong twice a season! A mason who says otherwise is building something to avoid.
    >>  ............................................
    pt  Vinte anos e eu ainda erro duas vezes por estação! Um pedreiro que diz o contrário constrói algo a se evitar.
    >>  ............................................
```

</details>


**Outcome 101 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
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

```text
  dialogue.conversations.work.prof.mason.risk/1   [98 chars]
    en  Everything I build outlives me, and so does everything I get wrong. That's not a comfortable pair.
    >>  ............................................
    pt  Tudo que eu construo me sobrevive, e tudo que eu erro também. Não é um par confortável.
    >>  ............................................
  dialogue.conversations.work.prof.mason.risk/2   [88 chars]
    en  A wall came down in the next valley last year. Not mine. I went and looked at it anyway.
    >>  ............................................
    pt  Uma parede caiu no vale vizinho ano passado. Não minha. Eu fui olhar mesmo assim.
    >>  ............................................
```


**Outcome 102 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
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

```text
  dialogue.conversations.work.prof.mason.village/1   [115 chars]
    en  The well, the church footing, four chimneys and the bridge abutment. That's me, and it'll be me in a hundred years.
    >>  ............................................
    pt  O poço, a fundação da igreja, quatro chaminés e o encontro da ponte. Sou eu, e serei eu em cem anos.
    >>  ............................................
  dialogue.conversations.work.prof.mason.village/2   [88 chars]
    en  I cut the stones over the graves. Every name in that ground came through my hands twice.
    >>  ............................................
    pt  Eu corto as pedras sobre os túmulos. Todo nome naquele chão passou pelas minhas mãos duas vezes.
    >>  ............................................
```


**Outcome 103 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:mason"
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

```text
  dialogue.conversations.work.prof.mason.future/1   [91 chars]
    en  An arch. A real one, over the lane, that holds itself up by argument rather than by mortar.
    >>  ............................................
    pt  Um arco. De verdade, sobre a viela, que se sustenta por argumento e não por argamassa.
    >>  ............................................
  dialogue.conversations.work.prof.mason.future/2   [95 chars]
    en  The bridge needs its second abutment and I'm the only one here who could set it. I'm fifty-one.
    >>  ............................................
    pt  A ponte precisa do segundo encontro e eu sou o único aqui que saberia assentar. Tenho cinquenta e um.
    >>  ............................................
```


**Outcome 104 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
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

```text
  dialogue.conversations.work.prof.nitwit.task/1   [71 chars]
    en  Watching the road. Somebody has to and nobody else has the time for it.
    >>  ............................................
    pt  Olhando a estrada. Alguém tem que olhar e mais ninguém tem tempo.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.task/2   [93 chars]
    en  Nothing anybody's given me. So: the birds, the weather, and who came in the gate before noon.
    >>  ............................................
    pt  Nada que alguém tenha me dado. Então: os pássaros, o tempo, e quem entrou pelo portão antes do meio-dia.
    >>  ............................................
```


**Outcome 105 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
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

```text
  dialogue.conversations.work.prof.nitwit.craft/1   [91 chars]
    en  I know every path out of this valley and which ones flood. Nobody taught me. I walked them.
    >>  ............................................
    pt  Conheço cada caminho pra fora deste vale e quais alagam. Ninguém me ensinou. Eu andei neles.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.craft/2   [81 chars]
    en  I can name every person here and tell you who they're not speaking to this month.
    >>  ............................................
    pt  Sei nomear cada pessoa daqui e dizer com quem não estão se falando este mês.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me because nobody thought there was any point. I learned anyway, and I'd rather they had.
    >>  ............................................
    pt  Ninguém me ensinou porque acharam que não valia. Eu aprendi mesmo assim, e eu preferia que tivessem ensinado.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.nitwit.craft/2
    en  I learned by being talked over. You hear a great deal from where I stand, and none of it was offered.
    >>  ............................................
    pt  Aprendi sendo ignorado. Você ouve muita coisa de onde eu fico, e nada disso me foi oferecido.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.craft/1
    en  Thirty years of walking the same five tracks. It's not a method so much as a habit that turned into one.
    >>  ............................................
    pt  Trinta anos andando as mesmas cinco trilhas. Não é bem um método, é um hábito que virou um.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.nitwit.craft/2
    en  Slowly, and by having the time. Having the time is the one thing I've never been short of.
    >>  ............................................
    pt  Devagar, e por ter tempo. Tempo é a única coisa que nunca me faltou.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me. I walked every path out of this valley and I know which ones flood.
    >>  ............................................
    pt  Ninguém me ensinou. Andei cada caminho pra fora deste vale e sei quais alagam.
    >>  ............................................
  confident.dialogue.conversations.work.prof.nitwit.craft/2
    en  I know the name of every person here and who they are not speaking to. That is knowledge, whatever it is called.
    >>  ............................................
    pt  Sei o nome de cada pessoa daqui e com quem não estão se falando. Isso é conhecimento, chamem como quiserem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me. I walked every path out of this valley and I know which ones flood.
    >>  ............................................
    pt  Ninguém me ensinou. Andei cada caminho pra fora deste vale e sei quais alagam.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.nitwit.craft/2
    en  I know the name of every person here and who they are not speaking to. That is knowledge, whatever it is called.
    >>  ............................................
    pt  Sei o nome de cada pessoa daqui e com quem não estão se falando. Isso é conhecimento, chamem como quiserem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.craft/1
    en  I learned it by paying attention to people who never noticed I was paying attention.
    >>  ............................................
    pt  Aprendi prestando atenção em pessoas que nunca notaram que eu prestava atenção.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.nitwit.craft/2
    en  Nobody taught me, and I'd tell any of it to anyone who asked properly. Ask properly sometime.
    >>  ............................................
    pt  Ninguém me ensinou, e eu contaria qualquer parte pra quem perguntasse direito. Pergunte direito um dia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.craft/1
    en  I learned it by paying attention to people who never noticed I was paying attention.
    >>  ............................................
    pt  Aprendi prestando atenção em pessoas que nunca notaram que eu prestava atenção.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.nitwit.craft/2
    en  Nobody taught me, and I'd tell any of it to anyone who asked properly. Ask properly sometime.
    >>  ............................................
    pt  Ninguém me ensinou, e eu contaria qualquer parte pra quem perguntasse direito. Pergunte direito um dia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.craft/1
    en  I learned it by paying attention to people who never noticed I was paying attention.
    >>  ............................................
    pt  Aprendi prestando atenção em pessoas que nunca notaram que eu prestava atenção.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.nitwit.craft/2
    en  Nobody taught me, and I'd tell any of it to anyone who asked properly. Ask properly sometime.
    >>  ............................................
    pt  Ninguém me ensinou, e eu contaria qualquer parte pra quem perguntasse direito. Pergunte direito um dia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me because nobody thought there was any point. I learned anyway, and I'd rather they had.
    >>  ............................................
    pt  Ninguém me ensinou porque acharam que não valia. Eu aprendi mesmo assim, e eu preferia que tivessem ensinado.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.nitwit.craft/2
    en  I learned by being talked over. You hear a great deal from where I stand, and none of it was offered.
    >>  ............................................
    pt  Aprendi sendo ignorado. Você ouve muita coisa de onde eu fico, e nada disso me foi oferecido.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me. I walked every path out of this valley and I know which ones flood.
    >>  ............................................
    pt  Ninguém me ensinou. Andei cada caminho pra fora deste vale e sei quais alagam.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.nitwit.craft/2
    en  I know the name of every person here and who they are not speaking to. That is knowledge, whatever it is called.
    >>  ............................................
    pt  Sei o nome de cada pessoa daqui e com quem não estão se falando. Isso é conhecimento, chamem como quiserem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me. I walked every path out of this valley and I know which ones flood.
    >>  ............................................
    pt  Ninguém me ensinou. Andei cada caminho pra fora deste vale e sei quais alagam.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.nitwit.craft/2
    en  I know the name of every person here and who they are not speaking to. That is knowledge, whatever it is called.
    >>  ............................................
    pt  Sei o nome de cada pessoa daqui e com quem não estão se falando. Isso é conhecimento, chamem como quiserem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.craft/1
    en  I walked them. That is the whole method and it took about thirty years and nobody was watching.
    >>  ............................................
    pt  Eu andei neles. É todo o método e levou uns trinta anos e ninguém estava olhando.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.nitwit.craft/2
    en  Knowing a thing and spreading it are different, and I have been careful about that for a very long time.
    >>  ............................................
    pt  Saber uma coisa e espalhar são diferentes, e eu tenho cuidado com isso há muito tempo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.craft/1
    en  Thirty years of walking the same five tracks. It's not a method so much as a habit that turned into one.
    >>  ............................................
    pt  Trinta anos andando as mesmas cinco trilhas. Não é bem um método, é um hábito que virou um.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.nitwit.craft/2
    en  Slowly, and by having the time. Having the time is the one thing I've never been short of.
    >>  ............................................
    pt  Devagar, e por ter tempo. Tempo é a única coisa que nunca me faltou.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.craft/1
    en  I walked them. That is the whole method and it took about thirty years and nobody was watching.
    >>  ............................................
    pt  Eu andei neles. É todo o método e levou uns trinta anos e ninguém estava olhando.
    >>  ............................................
  odd.dialogue.conversations.work.prof.nitwit.craft/2
    en  Knowing a thing and spreading it are different, and I have been careful about that for a very long time.
    >>  ............................................
    pt  Saber uma coisa e espalhar são diferentes, e eu tenho cuidado com isso há muito tempo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.craft/1
    en  Thirty years of walking the same five tracks. It's not a method so much as a habit that turned into one.
    >>  ............................................
    pt  Trinta anos andando as mesmas cinco trilhas. Não é bem um método, é um hábito que virou um.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.nitwit.craft/2
    en  Slowly, and by having the time. Having the time is the one thing I've never been short of.
    >>  ............................................
    pt  Devagar, e por ter tempo. Tempo é a única coisa que nunca me faltou.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me a thing! So I taught myself the roads, the weather and everybody's business.
    >>  ............................................
    pt  Ninguém me ensinou nada! Então eu aprendi sozinho as estradas, o tempo e a vida de todo mundo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.nitwit.craft/2
    en  Three of the five tracks flood. I told the cartographer once and he wrote it down, which surprised us both.
    >>  ............................................
    pt  Três das cinco trilhas alagam. Falei pro cartógrafo uma vez e ele anotou, o que surpreendeu nós dois.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me a thing! So I taught myself the roads, the weather and everybody's business.
    >>  ............................................
    pt  Ninguém me ensinou nada! Então eu aprendi sozinho as estradas, o tempo e a vida de todo mundo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.nitwit.craft/2
    en  Three of the five tracks flood. I told the cartographer once and he wrote it down, which surprised us both.
    >>  ............................................
    pt  Três das cinco trilhas alagam. Falei pro cartógrafo uma vez e ele anotou, o que surpreendeu nós dois.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.craft/1
    en  Thirty years of walking the same five tracks. It's not a method so much as a habit that turned into one.
    >>  ............................................
    pt  Trinta anos andando as mesmas cinco trilhas. Não é bem um método, é um hábito que virou um.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.nitwit.craft/2
    en  Slowly, and by having the time. Having the time is the one thing I've never been short of.
    >>  ............................................
    pt  Devagar, e por ter tempo. Tempo é a única coisa que nunca me faltou.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me because nobody thought there was any point. I learned anyway, and I'd rather they had.
    >>  ............................................
    pt  Ninguém me ensinou porque acharam que não valia. Eu aprendi mesmo assim, e eu preferia que tivessem ensinado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.nitwit.craft/2
    en  I learned by being talked over. You hear a great deal from where I stand, and none of it was offered.
    >>  ............................................
    pt  Aprendi sendo ignorado. Você ouve muita coisa de onde eu fico, e nada disso me foi oferecido.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.craft/1
    en  I walked them. That is the whole method and it took about thirty years and nobody was watching.
    >>  ............................................
    pt  Eu andei neles. É todo o método e levou uns trinta anos e ninguém estava olhando.
    >>  ............................................
  shy.dialogue.conversations.work.prof.nitwit.craft/2
    en  Knowing a thing and spreading it are different, and I have been careful about that for a very long time.
    >>  ............................................
    pt  Saber uma coisa e espalhar são diferentes, e eu tenho cuidado com isso há muito tempo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me a thing! So I taught myself the roads, the weather and everybody's business.
    >>  ............................................
    pt  Ninguém me ensinou nada! Então eu aprendi sozinho as estradas, o tempo e a vida de todo mundo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.nitwit.craft/2
    en  Three of the five tracks flood. I told the cartographer once and he wrote it down, which surprised us both.
    >>  ............................................
    pt  Três das cinco trilhas alagam. Falei pro cartógrafo uma vez e ele anotou, o que surpreendeu nós dois.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.craft/1
    en  Nobody taught me a thing! So I taught myself the roads, the weather and everybody's business.
    >>  ............................................
    pt  Ninguém me ensinou nada! Então eu aprendi sozinho as estradas, o tempo e a vida de todo mundo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.nitwit.craft/2
    en  Three of the five tracks flood. I told the cartographer once and he wrote it down, which surprised us both.
    >>  ............................................
    pt  Três das cinco trilhas alagam. Falei pro cartógrafo uma vez e ele anotou, o que surpreendeu nós dois.
    >>  ............................................
```

</details>


**Outcome 106 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
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

```text
  dialogue.conversations.work.prof.nitwit.risk/1   [92 chars]
    en  The risk is being here in forty years having been asked nothing. It's a slow sort of danger.
    >>  ............................................
    pt  O risco é estar aqui em quarenta anos sem ninguém ter me perguntado nada. É um perigo lento.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.risk/2   [109 chars]
    en  People say things in front of me they wouldn't say in front of anyone. I carry a great deal I didn't ask for.
    >>  ............................................
    pt  As pessoas dizem coisas na minha frente que não diriam na de mais ninguém. Carrego muito que eu não pedi.
    >>  ............................................
```


**Outcome 107 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
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

```text
  dialogue.conversations.work.prof.nitwit.village/1   [91 chars]
    en  I know this place better than the mayor does and he has never once come to ask me anything.
    >>  ............................................
    pt  Conheço este lugar melhor que o prefeito e ele nunca veio me perguntar nada.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.village/2   [95 chars]
    en  When the raid came I knew which house had children in it and which was empty. Nobody used that.
    >>  ............................................
    pt  Quando o ataque veio eu sabia qual casa tinha criança e qual estava vazia. Ninguém usou isso.
    >>  ............................................
```


**Outcome 108 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:nitwit"
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

```text
  dialogue.conversations.work.prof.nitwit.future/1   [93 chars]
    en  I'd take a post. Any post. Gate, well, road — something with a name that people say out loud.
    >>  ............................................
    pt  Eu aceitaria um posto. Qualquer um. Portão, poço, estrada — algo com nome que as pessoas digam alto.
    >>  ............................................
  dialogue.conversations.work.prof.nitwit.future/2   [99 chars]
    en  I've thought about walking one of those paths out and not coming back. I've thought about it a lot.
    >>  ............................................
    pt  Já pensei em andar por um daqueles caminhos e não voltar. Pensei muito nisso.
    >>  ............................................
```


**Outcome 109 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
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

```text
  dialogue.conversations.work.prof.none.task/1   [96 chars]
    en  Whatever wants doing. This morning it was the mill's sacks; this afternoon nobody's decided yet.
    >>  ............................................
    pt  O que precisar. De manhã foram os sacos do moinho; à tarde ninguém decidiu ainda.
    >>  ............................................
  dialogue.conversations.work.prof.none.task/2   [96 chars]
    en  Nothing with a name to it. I turn up where there are hands short and I'm short of hands nowhere.
    >>  ............................................
    pt  Nada com nome. Eu apareço onde falta mão e não falto em lugar nenhum.
    >>  ............................................
```


**Outcome 110 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
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

```text
  dialogue.conversations.work.prof.none.craft/1   [95 chars]
    en  I've half of six trades. Enough to be useful in all of them and enough to be trusted with none.
    >>  ............................................
    pt  Tenho metade de seis ofícios. O bastante pra ser útil em todos e o bastante pra não ter a confiança de nenhum.
    >>  ............................................
  dialogue.conversations.work.prof.none.craft/2   [106 chars]
    en  The thing I'm actually good at is knowing who needs what before they ask. There's no name for that either.
    >>  ............................................
    pt  No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem. Também não tem nome pra isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.none.craft/1
    en  Four of the six went to somebody's son. I don't blame them and I have counted anyway.
    >>  ............................................
    pt  Quatro dos seis foram pro filho de alguém. Não os culpo e contei mesmo assim.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.none.craft/2
    en  I learned the first two months of six trades. That is a sentence I've said cheerfully for years.
    >>  ............................................
    pt  Aprendi os dois primeiros meses de seis ofícios. É uma frase que eu digo alegremente há anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.craft/1
    en  A bit of everything, slowly, over about twelve years. It has not been a wasted twelve years.
    >>  ............................................
    pt  Um pouco de tudo, devagar, ao longo de uns doze anos. Não foram doze anos desperdiçados.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.none.craft/2
    en  Six starts and no finishes. I've stopped treating that as a failure and started treating it as a shape.
    >>  ............................................
    pt  Seis começos e nenhum fim. Parei de tratar isso como fracasso e comecei a tratar como um formato.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.craft/1
    en  Half of six trades. Enough to be useful in all of them and enough to be trusted with none.
    >>  ............................................
    pt  Metade de seis ofícios. O bastante pra ser útil em todos e o bastante pra não ter a confiança de nenhum.
    >>  ............................................
  confident.dialogue.conversations.work.prof.none.craft/2
    en  Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen.
    >>  ............................................
    pt  Moinho, forja, campo, telhado, curral e tanque. Dois meses cada, e aí o filho de alguém fez dezesseis.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.craft/1
    en  Half of six trades. Enough to be useful in all of them and enough to be trusted with none.
    >>  ............................................
    pt  Metade de seis ofícios. O bastante pra ser útil em todos e o bastante pra não ter a confiança de nenhum.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.none.craft/2
    en  Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen.
    >>  ............................................
    pt  Moinho, forja, campo, telhado, curral e tanque. Dois meses cada, e aí o filho de alguém fez dezesseis.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.craft/1
    en  Six people started teaching me and none of them finished. I liked all six of them, mind.
    >>  ............................................
    pt  Seis pessoas começaram a me ensinar e nenhuma terminou. Eu gostava das seis, veja bem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.none.craft/2
    en  What I'm actually good at is knowing who needs what before they ask. Nobody taught me that one.
    >>  ............................................
    pt  No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem. Ninguém me ensinou isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.craft/1
    en  Six people started teaching me and none of them finished. I liked all six of them, mind.
    >>  ............................................
    pt  Seis pessoas começaram a me ensinar e nenhuma terminou. Eu gostava das seis, veja bem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.none.craft/2
    en  What I'm actually good at is knowing who needs what before they ask. Nobody taught me that one.
    >>  ............................................
    pt  No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem. Ninguém me ensinou isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.craft/1
    en  Six people started teaching me and none of them finished. I liked all six of them, mind.
    >>  ............................................
    pt  Seis pessoas começaram a me ensinar e nenhuma terminou. Eu gostava das seis, veja bem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.none.craft/2
    en  What I'm actually good at is knowing who needs what before they ask. Nobody taught me that one.
    >>  ............................................
    pt  No que eu sou bom mesmo é em saber quem precisa do quê antes de pedirem. Ninguém me ensinou isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.craft/1
    en  Four of the six went to somebody's son. I don't blame them and I have counted anyway.
    >>  ............................................
    pt  Quatro dos seis foram pro filho de alguém. Não os culpo e contei mesmo assim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.none.craft/2
    en  I learned the first two months of six trades. That is a sentence I've said cheerfully for years.
    >>  ............................................
    pt  Aprendi os dois primeiros meses de seis ofícios. É uma frase que eu digo alegremente há anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.craft/1
    en  Half of six trades. Enough to be useful in all of them and enough to be trusted with none.
    >>  ............................................
    pt  Metade de seis ofícios. O bastante pra ser útil em todos e o bastante pra não ter a confiança de nenhum.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.none.craft/2
    en  Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen.
    >>  ............................................
    pt  Moinho, forja, campo, telhado, curral e tanque. Dois meses cada, e aí o filho de alguém fez dezesseis.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.craft/1
    en  Half of six trades. Enough to be useful in all of them and enough to be trusted with none.
    >>  ............................................
    pt  Metade de seis ofícios. O bastante pra ser útil em todos e o bastante pra não ter a confiança de nenhum.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.none.craft/2
    en  Mill, forge, field, roof, pen and pit. Two months each, and then somebody's son turned sixteen.
    >>  ............................................
    pt  Moinho, forja, campo, telhado, curral e tanque. Dois meses cada, e aí o filho de alguém fez dezesseis.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.craft/1
    en  Two months at a time, six times over. You learn the beginning of everything and the end of nothing.
    >>  ............................................
    pt  Dois meses por vez, seis vezes. Você aprende o começo de tudo e o fim de nada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.none.craft/2
    en  There's no name for the thing I'm best at, which is why nobody has ever offered me a trade for it.
    >>  ............................................
    pt  Não tem nome pra coisa em que eu sou melhor, e por isso nunca me ofereceram um ofício por ela.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.craft/1
    en  A bit of everything, slowly, over about twelve years. It has not been a wasted twelve years.
    >>  ............................................
    pt  Um pouco de tudo, devagar, ao longo de uns doze anos. Não foram doze anos desperdiçados.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.none.craft/2
    en  Six starts and no finishes. I've stopped treating that as a failure and started treating it as a shape.
    >>  ............................................
    pt  Seis começos e nenhum fim. Parei de tratar isso como fracasso e comecei a tratar como um formato.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.craft/1
    en  Two months at a time, six times over. You learn the beginning of everything and the end of nothing.
    >>  ............................................
    pt  Dois meses por vez, seis vezes. Você aprende o começo de tudo e o fim de nada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.none.craft/2
    en  There's no name for the thing I'm best at, which is why nobody has ever offered me a trade for it.
    >>  ............................................
    pt  Não tem nome pra coisa em que eu sou melhor, e por isso nunca me ofereceram um ofício por ela.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.craft/1
    en  A bit of everything, slowly, over about twelve years. It has not been a wasted twelve years.
    >>  ............................................
    pt  Um pouco de tudo, devagar, ao longo de uns doze anos. Não foram doze anos desperdiçados.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.none.craft/2
    en  Six starts and no finishes. I've stopped treating that as a failure and started treating it as a shape.
    >>  ............................................
    pt  Seis começos e nenhum fim. Parei de tratar isso como fracasso e comecei a tratar como um formato.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.craft/1
    en  I've half of six trades! Which sounds tragic and is actually a very good week of conversation.
    >>  ............................................
    pt  Tenho metade de seis ofícios! O que soa trágico e é na verdade uma semana de conversa muito boa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.none.craft/2
    en  Six trades, two months each. I know exactly how much I don't know, which is an education in itself.
    >>  ............................................
    pt  Seis ofícios, dois meses cada. Sei exatamente o quanto eu não sei, o que é uma educação em si.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.craft/1
    en  I've half of six trades! Which sounds tragic and is actually a very good week of conversation.
    >>  ............................................
    pt  Tenho metade de seis ofícios! O que soa trágico e é na verdade uma semana de conversa muito boa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.none.craft/2
    en  Six trades, two months each. I know exactly how much I don't know, which is an education in itself.
    >>  ............................................
    pt  Seis ofícios, dois meses cada. Sei exatamente o quanto eu não sei, o que é uma educação em si.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.craft/1
    en  A bit of everything, slowly, over about twelve years. It has not been a wasted twelve years.
    >>  ............................................
    pt  Um pouco de tudo, devagar, ao longo de uns doze anos. Não foram doze anos desperdiçados.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.none.craft/2
    en  Six starts and no finishes. I've stopped treating that as a failure and started treating it as a shape.
    >>  ............................................
    pt  Seis começos e nenhum fim. Parei de tratar isso como fracasso e comecei a tratar como um formato.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.craft/1
    en  Four of the six went to somebody's son. I don't blame them and I have counted anyway.
    >>  ............................................
    pt  Quatro dos seis foram pro filho de alguém. Não os culpo e contei mesmo assim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.none.craft/2
    en  I learned the first two months of six trades. That is a sentence I've said cheerfully for years.
    >>  ............................................
    pt  Aprendi os dois primeiros meses de seis ofícios. É uma frase que eu digo alegremente há anos.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.craft/1
    en  Two months at a time, six times over. You learn the beginning of everything and the end of nothing.
    >>  ............................................
    pt  Dois meses por vez, seis vezes. Você aprende o começo de tudo e o fim de nada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.none.craft/2
    en  There's no name for the thing I'm best at, which is why nobody has ever offered me a trade for it.
    >>  ............................................
    pt  Não tem nome pra coisa em que eu sou melhor, e por isso nunca me ofereceram um ofício por ela.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.craft/1
    en  I've half of six trades! Which sounds tragic and is actually a very good week of conversation.
    >>  ............................................
    pt  Tenho metade de seis ofícios! O que soa trágico e é na verdade uma semana de conversa muito boa.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.none.craft/2
    en  Six trades, two months each. I know exactly how much I don't know, which is an education in itself.
    >>  ............................................
    pt  Seis ofícios, dois meses cada. Sei exatamente o quanto eu não sei, o que é uma educação em si.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.craft/1
    en  I've half of six trades! Which sounds tragic and is actually a very good week of conversation.
    >>  ............................................
    pt  Tenho metade de seis ofícios! O que soa trágico e é na verdade uma semana de conversa muito boa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.none.craft/2
    en  Six trades, two months each. I know exactly how much I don't know, which is an education in itself.
    >>  ............................................
    pt  Seis ofícios, dois meses cada. Sei exatamente o quanto eu não sei, o que é uma educação em si.
    >>  ............................................
```

</details>


**Outcome 111 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
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

```text
  dialogue.conversations.work.prof.none.risk/1   [99 chars]
    en  A bad winter and I'm the one nobody has a reason to feed. I know exactly where I sit in that queue.
    >>  ............................................
    pt  Um inverno ruim e eu sou quem ninguém tem motivo pra alimentar. Sei exatamente onde eu fico nessa fila.
    >>  ............................................
  dialogue.conversations.work.prof.none.risk/2   [111 chars]
    en  There's no bottom under me. Everyone else has a trade to fall back on and I have a reputation for being nearby.
    >>  ............................................
    pt  Não tem fundo embaixo de mim. Todo mundo tem um ofício pra cair e eu tenho fama de estar por perto.
    >>  ............................................
```


**Outcome 112 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
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

```text
  dialogue.conversations.work.prof.none.village/1   [98 chars]
    en  Every trade here has had a week of me in it. Nobody adds those weeks up, but they're in the walls.
    >>  ............................................
    pt  Todo ofício daqui teve uma semana minha. Ninguém soma essas semanas, mas estão nas paredes.
    >>  ............................................
  dialogue.conversations.work.prof.none.village/2   [107 chars]
    en  I'm the one who knows what everybody's short of. That's useful to precisely nobody until it's an emergency.
    >>  ............................................
    pt  Sou quem sabe o que falta a cada um. Isso é útil a exatamente ninguém até virar emergência.
    >>  ............................................
```


**Outcome 113 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "minecraft:none"
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

```text
  dialogue.conversations.work.prof.none.future/1   [82 chars]
    en  A post. Not a grand one. Something the village would have to fill again if I left.
    >>  ............................................
    pt  Um posto. Não grandioso. Algo que o vilarejo teria que preencher de novo se eu fosse embora.
    >>  ............................................
  dialogue.conversations.work.prof.none.future/2   [95 chars]
    en  Or out. There's a town four days east that hires by the week and doesn't ask whose son you are.
    >>  ............................................
    pt  Ou fora. Tem uma cidade a quatro dias a leste que contrata por semana e não pergunta de quem você é filho.
    >>  ............................................
```


**Outcome 114 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
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

```text
  dialogue.conversations.work.prof.adventurer.task/1   [94 chars]
    en  Mending a strap and pretending that's a whole day's occupation. It isn't, and I know it isn't.
    >>  ............................................
    pt  Consertando uma correia e fingindo que isso é um dia de ocupação. Não é, e eu sei que não é.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.task/2   [98 chars]
    en  Waiting on word from the east road. Somebody owes me an answer and the answer is three weeks late.
    >>  ............................................
    pt  Esperando notícia da estrada leste. Alguém me deve uma resposta e a resposta está três semanas atrasada.
    >>  ............................................
```


**Outcome 115 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
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

```text
  dialogue.conversations.work.prof.adventurer.craft/1   [92 chars]
    en  Nobody taught me anything. I learned by being wrong somewhere I couldn't afford to be wrong.
    >>  ............................................
    pt  Ninguém me ensinou nada. Aprendi errando em lugares onde eu não podia errar.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.craft/2   [89 chars]
    en  The skill is knowing when to turn round. Everything else is walking, and anyone can walk.
    >>  ............................................
    pt  A habilidade é saber a hora de voltar. O resto é caminhar, e qualquer um caminha.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. There was nobody to, which is a different sentence and the truer one.
    >>  ............................................
    pt  Ninguém me ensinou. Não havia ninguém pra ensinar, o que é outra frase e a mais verdadeira.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned alone, and the parts I learned worst are the parts that would have needed somebody there.
    >>  ............................................
    pt  Aprendi sozinho, e as partes que aprendi pior são as que precisariam de alguém ali.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.craft/1
    en  Slowly, and mostly by not dying. There's no faster way into this and I've watched people look for one.
    >>  ............................................
    pt  Devagar, e principalmente por não morrer. Não há caminho mais rápido e eu vi gente procurar um.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by walking. Twenty years of it, and I'd not shorten a single one of them now.
    >>  ............................................
    pt  Aprendi caminhando. Vinte anos disso, e eu não encurtaria nenhum deles agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.craft/1
    en  No master, no guild, no examination. You get it wrong once somewhere expensive and then you know.
    >>  ............................................
    pt  Sem mestre, sem guilda, sem exame. Você erra uma vez num lugar caro e aí você sabe.
    >>  ............................................
  confident.dialogue.conversations.work.prof.adventurer.craft/2
    en  I taught myself. Everything useful I know cost me a scar or a season, and I'd not give either back.
    >>  ............................................
    pt  Aprendi sozinho. Tudo de útil que eu sei me custou uma cicatriz ou uma estação, e eu não devolveria nenhuma.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.craft/1
    en  No master, no guild, no examination. You get it wrong once somewhere expensive and then you know.
    >>  ............................................
    pt  Sem mestre, sem guilda, sem exame. Você erra uma vez num lugar caro e aí você sabe.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.adventurer.craft/2
    en  I taught myself. Everything useful I know cost me a scar or a season, and I'd not give either back.
    >>  ............................................
    pt  Aprendi sozinho. Tudo de útil que eu sei me custou uma cicatriz ou uma estação, e eu não devolveria nenhuma.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.craft/1
    en  Other travellers taught me, a piece at a time, over fires. None of them ever knew they were teaching.
    >>  ............................................
    pt  Outros viajantes me ensinaram, um pedaço por vez, em volta do fogo. Nenhum sabia que estava ensinando.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned it from people I met once. Half of what I know has somebody else's name quietly on it.
    >>  ............................................
    pt  Aprendi com gente que encontrei uma vez. Metade do que eu sei tem o nome de outra pessoa em silêncio.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.craft/1
    en  Other travellers taught me, a piece at a time, over fires. None of them ever knew they were teaching.
    >>  ............................................
    pt  Outros viajantes me ensinaram, um pedaço por vez, em volta do fogo. Nenhum sabia que estava ensinando.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned it from people I met once. Half of what I know has somebody else's name quietly on it.
    >>  ............................................
    pt  Aprendi com gente que encontrei uma vez. Metade do que eu sei tem o nome de outra pessoa em silêncio.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.craft/1
    en  Other travellers taught me, a piece at a time, over fires. None of them ever knew they were teaching.
    >>  ............................................
    pt  Outros viajantes me ensinaram, um pedaço por vez, em volta do fogo. Nenhum sabia que estava ensinando.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned it from people I met once. Half of what I know has somebody else's name quietly on it.
    >>  ............................................
    pt  Aprendi com gente que encontrei uma vez. Metade do que eu sei tem o nome de outra pessoa em silêncio.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. There was nobody to, which is a different sentence and the truer one.
    >>  ............................................
    pt  Ninguém me ensinou. Não havia ninguém pra ensinar, o que é outra frase e a mais verdadeira.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned alone, and the parts I learned worst are the parts that would have needed somebody there.
    >>  ............................................
    pt  Aprendi sozinho, e as partes que aprendi pior são as que precisariam de alguém ali.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.craft/1
    en  No master, no guild, no examination. You get it wrong once somewhere expensive and then you know.
    >>  ............................................
    pt  Sem mestre, sem guilda, sem exame. Você erra uma vez num lugar caro e aí você sabe.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I taught myself. Everything useful I know cost me a scar or a season, and I'd not give either back.
    >>  ............................................
    pt  Aprendi sozinho. Tudo de útil que eu sei me custou uma cicatriz ou uma estação, e eu não devolveria nenhuma.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.craft/1
    en  No master, no guild, no examination. You get it wrong once somewhere expensive and then you know.
    >>  ............................................
    pt  Sem mestre, sem guilda, sem exame. Você erra uma vez num lugar caro e aí você sabe.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I taught myself. Everything useful I know cost me a scar or a season, and I'd not give either back.
    >>  ............................................
    pt  Aprendi sozinho. Tudo de útil que eu sei me custou uma cicatriz ou uma estação, e eu não devolveria nenhuma.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. I watched what other people did wrong and wrote it down, and I still have the list.
    >>  ............................................
    pt  Ninguém me ensinou. Vi o que os outros faziam de errado e anotei, e eu ainda tenho a lista.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by being cold. That's the shortest honest answer and it took nine years to be able to give it.
    >>  ............................................
    pt  Aprendi passando frio. É a resposta honesta mais curta e levei nove anos pra conseguir dá-la.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.craft/1
    en  Slowly, and mostly by not dying. There's no faster way into this and I've watched people look for one.
    >>  ............................................
    pt  Devagar, e principalmente por não morrer. Não há caminho mais rápido e eu vi gente procurar um.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by walking. Twenty years of it, and I'd not shorten a single one of them now.
    >>  ............................................
    pt  Aprendi caminhando. Vinte anos disso, e eu não encurtaria nenhum deles agora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. I watched what other people did wrong and wrote it down, and I still have the list.
    >>  ............................................
    pt  Ninguém me ensinou. Vi o que os outros faziam de errado e anotei, e eu ainda tenho a lista.
    >>  ............................................
  odd.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by being cold. That's the shortest honest answer and it took nine years to be able to give it.
    >>  ............................................
    pt  Aprendi passando frio. É a resposta honesta mais curta e levei nove anos pra conseguir dá-la.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.craft/1
    en  Slowly, and mostly by not dying. There's no faster way into this and I've watched people look for one.
    >>  ............................................
    pt  Devagar, e principalmente por não morrer. Não há caminho mais rápido e eu vi gente procurar um.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by walking. Twenty years of it, and I'd not shorten a single one of them now.
    >>  ............................................
    pt  Aprendi caminhando. Vinte anos disso, e eu não encurtaria nenhum deles agora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me! Which is a marvellous excuse whenever I get something spectacularly wrong.
    >>  ............................................
    pt  Ninguém me ensinou! O que é uma desculpa maravilhosa sempre que eu erro espetacularmente.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned on the road, badly, and the bad parts make much better stories than the competent ones.
    >>  ............................................
    pt  Aprendi na estrada, mal, e as partes ruins dão histórias muito melhores que as competentes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me! Which is a marvellous excuse whenever I get something spectacularly wrong.
    >>  ............................................
    pt  Ninguém me ensinou! O que é uma desculpa maravilhosa sempre que eu erro espetacularmente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned on the road, badly, and the bad parts make much better stories than the competent ones.
    >>  ............................................
    pt  Aprendi na estrada, mal, e as partes ruins dão histórias muito melhores que as competentes.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.craft/1
    en  Slowly, and mostly by not dying. There's no faster way into this and I've watched people look for one.
    >>  ............................................
    pt  Devagar, e principalmente por não morrer. Não há caminho mais rápido e eu vi gente procurar um.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by walking. Twenty years of it, and I'd not shorten a single one of them now.
    >>  ............................................
    pt  Aprendi caminhando. Vinte anos disso, e eu não encurtaria nenhum deles agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. There was nobody to, which is a different sentence and the truer one.
    >>  ............................................
    pt  Ninguém me ensinou. Não havia ninguém pra ensinar, o que é outra frase e a mais verdadeira.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned alone, and the parts I learned worst are the parts that would have needed somebody there.
    >>  ............................................
    pt  Aprendi sozinho, e as partes que aprendi pior são as que precisariam de alguém ali.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me. I watched what other people did wrong and wrote it down, and I still have the list.
    >>  ............................................
    pt  Ninguém me ensinou. Vi o que os outros faziam de errado e anotei, e eu ainda tenho a lista.
    >>  ............................................
  shy.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned by being cold. That's the shortest honest answer and it took nine years to be able to give it.
    >>  ............................................
    pt  Aprendi passando frio. É a resposta honesta mais curta e levei nove anos pra conseguir dá-la.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me! Which is a marvellous excuse whenever I get something spectacularly wrong.
    >>  ............................................
    pt  Ninguém me ensinou! O que é uma desculpa maravilhosa sempre que eu erro espetacularmente.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned on the road, badly, and the bad parts make much better stories than the competent ones.
    >>  ............................................
    pt  Aprendi na estrada, mal, e as partes ruins dão histórias muito melhores que as competentes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.craft/1
    en  Nobody taught me! Which is a marvellous excuse whenever I get something spectacularly wrong.
    >>  ............................................
    pt  Ninguém me ensinou! O que é uma desculpa maravilhosa sempre que eu erro espetacularmente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.adventurer.craft/2
    en  I learned on the road, badly, and the bad parts make much better stories than the competent ones.
    >>  ............................................
    pt  Aprendi na estrada, mal, e as partes ruins dão histórias muito melhores que as competentes.
    >>  ............................................
```

</details>


**Outcome 116 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
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

```text
  dialogue.conversations.work.prof.adventurer.risk/1   [93 chars]
    en  The danger isn't the road. It's coming back to a place that got on perfectly well without me.
    >>  ............................................
    pt  O perigo não é a estrada. É voltar a um lugar que se virou muito bem sem mim.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.risk/2   [90 chars]
    en  I've been in three fights I chose and one I didn't. The one I didn't is the one I revisit.
    >>  ............................................
    pt  Estive em três brigas que eu escolhi e uma que não. A que eu não escolhi é a que eu revisito.
    >>  ............................................
```


**Outcome 117 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
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

```text
  dialogue.conversations.work.prof.adventurer.village/1   [92 chars]
    en  I brought the seed corn through in the bad year. Two people know that and one of them is me.
    >>  ............................................
    pt  Eu trouxe a semente no ano ruim. Duas pessoas sabem disso e uma delas sou eu.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.village/2   [97 chars]
    en  This place uses me the way you use a spare key. Fondly, rarely, and only when something's locked.
    >>  ............................................
    pt  Este lugar me usa como se usa uma chave reserva. Com carinho, raramente, e só quando algo trancou.
    >>  ............................................
```


**Outcome 118 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:adventurer"
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

```text
  dialogue.conversations.work.prof.adventurer.future/1   [101 chars]
    en  One more road, and then a roof. That's been the plan for six years and the road keeps being one more.
    >>  ............................................
    pt  Mais uma estrada, e aí um telhado. É o plano há seis anos e a estrada continua sendo mais uma.
    >>  ............................................
  dialogue.conversations.work.prof.adventurer.future/2   [86 chars]
    en  There's a valley two months west I've described to four people and never gone back to.
    >>  ............................................
    pt  Tem um vale a dois meses a oeste que eu descrevi a quatro pessoas e nunca voltei.
    >>  ............................................
```


**Outcome 119 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
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

```text
  dialogue.conversations.work.prof.archer.task/1   [92 chars]
    en  Two hundred draws before noon. It's dull and it's the only reason my hand is steady at dusk.
    >>  ............................................
    pt  Duzentos puxões antes do meio-dia. É maçante e é a única razão de minha mão estar firme ao anoitecer.
    >>  ............................................
  dialogue.conversations.work.prof.archer.task/2   [93 chars]
    en  Walking the wall line and marking where a man could stand unseen. There are four such places.
    >>  ............................................
    pt  Andando a linha da muralha e marcando onde alguém poderia ficar sem ser visto. Há quatro lugares assim.
    >>  ............................................
```


**Outcome 120 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
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

```text
  dialogue.conversations.work.prof.archer.craft/1   [93 chars]
    en  Everyone thinks it's the eye. It's the breath. The eye only tells you how badly you breathed.
    >>  ............................................
    pt  Todos acham que é o olho. É a respiração. O olho só te diz o quão mal você respirou.
    >>  ............................................
  dialogue.conversations.work.prof.archer.craft/2   [94 chars]
    en  I can put six in a hand's width at eighty paces and I cannot explain a single thing about how.
    >>  ............................................
    pt  Ponho seis num palmo a oitenta passos e não sei explicar nada sobre como.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.archer.craft/1
    en  I learned it after something got over the wall. That's not a story I tell in full.
    >>  ............................................
    pt  Aprendi depois que algo passou a muralha. Não é uma história que eu conto inteira.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.archer.craft/2
    en  The breath. I know that because for two years mine wasn't steady and I knew exactly why.
    >>  ............................................
    pt  A respiração. Eu sei disso porque por dois anos a minha não era firme e eu sabia exatamente por quê.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.craft/1
    en  Slowly. The eye is quick and the breath is slow, and the trade belongs to the slow half.
    >>  ............................................
    pt  Devagar. O olho é rápido e a respiração é lenta, e o ofício pertence à metade lenta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.craft/2
    en  Nine years of mornings. Nothing about it arrived early and nothing about it has left.
    >>  ............................................
    pt  Nove anos de manhãs. Nada nisso chegou cedo e nada nisso foi embora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.craft/1
    en  Two hundred draws a morning for nine years. There is no other answer and people keep asking for one.
    >>  ............................................
    pt  Duzentos puxões por manhã durante nove anos. Não há outra resposta e continuam pedindo uma.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.craft/2
    en  The breath, not the eye. Get that wrong and no amount of practice puts it right.
    >>  ............................................
    pt  A respiração, não o olho. Erre isso e nenhuma prática conserta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.craft/1
    en  Two hundred draws a morning for nine years. There is no other answer and people keep asking for one.
    >>  ............................................
    pt  Duzentos puxões por manhã durante nove anos. Não há outra resposta e continuam pedindo uma.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.craft/2
    en  The breath, not the eye. Get that wrong and no amount of practice puts it right.
    >>  ............................................
    pt  A respiração, não o olho. Erre isso e nenhuma prática conserta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.craft/1
    en  An old woman on the next wall taught me, one winter, without once telling me I was doing it wrong.
    >>  ............................................
    pt  Uma velha da muralha vizinha me ensinou, num inverno, sem nunca dizer que eu fazia errado.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.craft/2
    en  I learned beside somebody. That's the only way it goes in — you draw next to them until it takes.
    >>  ............................................
    pt  Aprendi ao lado de alguém. É o único jeito de entrar — você puxa junto até pegar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.craft/1
    en  An old woman on the next wall taught me, one winter, without once telling me I was doing it wrong.
    >>  ............................................
    pt  Uma velha da muralha vizinha me ensinou, num inverno, sem nunca dizer que eu fazia errado.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.craft/2
    en  I learned beside somebody. That's the only way it goes in — you draw next to them until it takes.
    >>  ............................................
    pt  Aprendi ao lado de alguém. É o único jeito de entrar — você puxa junto até pegar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.craft/1
    en  An old woman on the next wall taught me, one winter, without once telling me I was doing it wrong.
    >>  ............................................
    pt  Uma velha da muralha vizinha me ensinou, num inverno, sem nunca dizer que eu fazia errado.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.craft/2
    en  I learned beside somebody. That's the only way it goes in — you draw next to them until it takes.
    >>  ............................................
    pt  Aprendi ao lado de alguém. É o único jeito de entrar — você puxa junto até pegar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.craft/1
    en  I learned it after something got over the wall. That's not a story I tell in full.
    >>  ............................................
    pt  Aprendi depois que algo passou a muralha. Não é uma história que eu conto inteira.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.craft/2
    en  The breath. I know that because for two years mine wasn't steady and I knew exactly why.
    >>  ............................................
    pt  A respiração. Eu sei disso porque por dois anos a minha não era firme e eu sabia exatamente por quê.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.craft/1
    en  Two hundred draws a morning for nine years. There is no other answer and people keep asking for one.
    >>  ............................................
    pt  Duzentos puxões por manhã durante nove anos. Não há outra resposta e continuam pedindo uma.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.craft/2
    en  The breath, not the eye. Get that wrong and no amount of practice puts it right.
    >>  ............................................
    pt  A respiração, não o olho. Erre isso e nenhuma prática conserta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.craft/1
    en  Two hundred draws a morning for nine years. There is no other answer and people keep asking for one.
    >>  ............................................
    pt  Duzentos puxões por manhã durante nove anos. Não há outra resposta e continuam pedindo uma.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.craft/2
    en  The breath, not the eye. Get that wrong and no amount of practice puts it right.
    >>  ............................................
    pt  A respiração, não o olho. Erre isso e nenhuma prática conserta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.craft/1
    en  You loose in the gap between breaths, and the gap is small, and it closes. That's the whole of it.
    >>  ............................................
    pt  Você solta na pausa entre as respirações, e a pausa é pequena, e ela fecha. É tudo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.craft/2
    en  I learned by drawing beside somebody and saying nothing for a year. It took a year.
    >>  ............................................
    pt  Aprendi puxando ao lado de alguém e sem dizer nada por um ano. Levou um ano.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.craft/1
    en  Slowly. The eye is quick and the breath is slow, and the trade belongs to the slow half.
    >>  ............................................
    pt  Devagar. O olho é rápido e a respiração é lenta, e o ofício pertence à metade lenta.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.craft/2
    en  Nine years of mornings. Nothing about it arrived early and nothing about it has left.
    >>  ............................................
    pt  Nove anos de manhãs. Nada nisso chegou cedo e nada nisso foi embora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.craft/1
    en  You loose in the gap between breaths, and the gap is small, and it closes. That's the whole of it.
    >>  ............................................
    pt  Você solta na pausa entre as respirações, e a pausa é pequena, e ela fecha. É tudo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.craft/2
    en  I learned by drawing beside somebody and saying nothing for a year. It took a year.
    >>  ............................................
    pt  Aprendi puxando ao lado de alguém e sem dizer nada por um ano. Levou um ano.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.craft/1
    en  Slowly. The eye is quick and the breath is slow, and the trade belongs to the slow half.
    >>  ............................................
    pt  Devagar. O olho é rápido e a respiração é lenta, e o ofício pertence à metade lenta.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.craft/2
    en  Nine years of mornings. Nothing about it arrived early and nothing about it has left.
    >>  ............................................
    pt  Nove anos de manhãs. Nada nisso chegou cedo e nada nisso foi embora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.craft/1
    en  Practice, practice, and then more practice while pretending it's not practice. That's the secret.
    >>  ............................................
    pt  Prática, prática, e mais prática fingindo que não é prática. É esse o segredo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.craft/2
    en  I can put six in a hand's width at eighty paces and I cannot explain a single thing about how!
    >>  ............................................
    pt  Ponho seis num palmo a oitenta passos e não sei explicar nada sobre como!
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.craft/1
    en  Practice, practice, and then more practice while pretending it's not practice. That's the secret.
    >>  ............................................
    pt  Prática, prática, e mais prática fingindo que não é prática. É esse o segredo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.craft/2
    en  I can put six in a hand's width at eighty paces and I cannot explain a single thing about how!
    >>  ............................................
    pt  Ponho seis num palmo a oitenta passos e não sei explicar nada sobre como!
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.craft/1
    en  Slowly. The eye is quick and the breath is slow, and the trade belongs to the slow half.
    >>  ............................................
    pt  Devagar. O olho é rápido e a respiração é lenta, e o ofício pertence à metade lenta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.craft/2
    en  Nine years of mornings. Nothing about it arrived early and nothing about it has left.
    >>  ............................................
    pt  Nove anos de manhãs. Nada nisso chegou cedo e nada nisso foi embora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.craft/1
    en  I learned it after something got over the wall. That's not a story I tell in full.
    >>  ............................................
    pt  Aprendi depois que algo passou a muralha. Não é uma história que eu conto inteira.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.craft/2
    en  The breath. I know that because for two years mine wasn't steady and I knew exactly why.
    >>  ............................................
    pt  A respiração. Eu sei disso porque por dois anos a minha não era firme e eu sabia exatamente por quê.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.craft/1
    en  You loose in the gap between breaths, and the gap is small, and it closes. That's the whole of it.
    >>  ............................................
    pt  Você solta na pausa entre as respirações, e a pausa é pequena, e ela fecha. É tudo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.craft/2
    en  I learned by drawing beside somebody and saying nothing for a year. It took a year.
    >>  ............................................
    pt  Aprendi puxando ao lado de alguém e sem dizer nada por um ano. Levou um ano.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.craft/1
    en  Practice, practice, and then more practice while pretending it's not practice. That's the secret.
    >>  ............................................
    pt  Prática, prática, e mais prática fingindo que não é prática. É esse o segredo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.craft/2
    en  I can put six in a hand's width at eighty paces and I cannot explain a single thing about how!
    >>  ............................................
    pt  Ponho seis num palmo a oitenta passos e não sei explicar nada sobre como!
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.craft/1
    en  Practice, practice, and then more practice while pretending it's not practice. That's the secret.
    >>  ............................................
    pt  Prática, prática, e mais prática fingindo que não é prática. É esse o segredo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.craft/2
    en  I can put six in a hand's width at eighty paces and I cannot explain a single thing about how!
    >>  ............................................
    pt  Ponho seis num palmo a oitenta passos e não sei explicar nada sobre como!
    >>  ............................................
```

</details>


**Outcome 121 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
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

```text
  dialogue.conversations.work.prof.archer.risk/1   [84 chars]
    en  An arrow doesn't come back. Every one I loose is a decision that stays made forever.
    >>  ............................................
    pt  Uma flecha não volta. Cada uma que eu solto é uma decisão que fica tomada pra sempre.
    >>  ............................................
  dialogue.conversations.work.prof.archer.risk/2   [96 chars]
    en  I stand where I can see and be seen. That's the post, and it means I'm the first thing aimed at.
    >>  ............................................
    pt  Fico onde eu vejo e sou vista. É o posto, e significa que eu sou a primeira coisa mirada.
    >>  ............................................
```


**Outcome 122 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
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

```text
  dialogue.conversations.work.prof.archer.village/1   [101 chars]
    en  Nothing has come over that wall in six years. That's the only sentence I'd let anyone write about me.
    >>  ............................................
    pt  Nada passou aquela muralha em seis anos. É a única frase que eu deixaria alguém escrever sobre mim.
    >>  ............................................
  dialogue.conversations.work.prof.archer.village/2   [103 chars]
    en  Half this place doesn't know my name and all of it sleeps behind where I stand. That's the arrangement.
    >>  ............................................
    pt  Metade daqui não sabe meu nome e o lugar inteiro dorme atrás de onde eu fico. É esse o acordo.
    >>  ............................................
```


**Outcome 123 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:archer"
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

```text
  dialogue.conversations.work.prof.archer.future/1   [82 chars]
    en  I want two archers on that wall, not one. Then a night off is a thing that exists.
    >>  ............................................
    pt  Quero duas arqueiras naquela muralha, não uma. Aí uma noite de folga passa a existir.
    >>  ............................................
  dialogue.conversations.work.prof.archer.future/2   [99 chars]
    en  The girl who has it and doesn't know — I'd like to be the one who tells her, before I'm too old to.
    >>  ............................................
    pt  A menina que tem o dom e não sabe — queria ser eu a contar, antes de ficar velha demais.
    >>  ............................................
```


**Outcome 124 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
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

```text
  dialogue.conversations.work.prof.cultist.task/1   [97 chars]
    en  Copying. The same forty lines, by hand, until the hand does it without me. That's the discipline.
    >>  ............................................
    pt  Copiando. As mesmas quarenta linhas, à mão, até a mão fazer sem mim. É essa a disciplina.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.task/2   [102 chars]
    en  Keeping the lamp. It's not symbolic — if it goes out somebody has to walk to the next valley for fire.
    >>  ............................................
    pt  Cuidando da lamparina. Não é símbolo — se apagar alguém tem que ir ao vale vizinho buscar fogo.
    >>  ............................................
```


**Outcome 125 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
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

```text
  dialogue.conversations.work.prof.cultist.craft/1   [98 chars]
    en  It's memory, mostly. Nothing we hold is written anywhere a fire could reach, so it lives in heads.
    >>  ............................................
    pt  É memória, principalmente. Nada do que guardamos está escrito onde o fogo alcance, então vive em cabeças.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.craft/2   [88 chars]
    en  I was taught by someone who tested me for four years before she taught me anything true.
    >>  ............................................
    pt  Fui ensinado por alguém que me testou quatro anos antes de me ensinar algo verdadeiro.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cultist.craft/1
    en  She let me find out for myself that what I'd repeated was false. That was the entire punishment.
    >>  ............................................
    pt  Ela me deixou descobrir sozinho que o que eu repeti era falso. Foi toda a punição.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cultist.craft/2
    en  It has to live in a head, and heads end. We have never solved that and I think about it often.
    >>  ............................................
    pt  Tem que viver numa cabeça, e cabeças acabam. Nunca resolvemos isso e eu penso nisso sempre.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of being watched, then eleven of being trusted. Neither number could have been smaller.
    >>  ............................................
    pt  Quatro anos sendo observado, depois onze sendo confiado. Nenhum dos números podia ser menor.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.craft/2
    en  It takes a person who can be boring for decades. That is the actual requirement and I met it.
    >>  ............................................
    pt  Exige uma pessoa que consiga ser entediante por décadas. É esse o requisito real e eu atendi.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.craft/1
    en  Memory. Nothing we hold is written where a fire could reach, so it lives in heads, and mine is one.
    >>  ............................................
    pt  Memória. Nada do que guardamos está escrito onde o fogo alcance, então vive em cabeças, e a minha é uma.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.craft/2
    en  I was taught by someone who tested me for four years before she taught me anything true.
    >>  ............................................
    pt  Fui ensinado por alguém que me testou quatro anos antes de me ensinar algo verdadeiro.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.craft/1
    en  Memory. Nothing we hold is written where a fire could reach, so it lives in heads, and mine is one.
    >>  ............................................
    pt  Memória. Nada do que guardamos está escrito onde o fogo alcance, então vive em cabeças, e a minha é uma.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.craft/2
    en  I was taught by someone who tested me for four years before she taught me anything true.
    >>  ............................................
    pt  Fui ensinado por alguém que me testou quatro anos antes de me ensinar algo verdadeiro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.craft/1
    en  From a woman who fed me for four years while telling me nothing. I understood that much later.
    >>  ............................................
    pt  De uma mulher que me alimentou por quatro anos sem me contar nada. Eu entendi muito depois.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.craft/2
    en  It passes hand to hand. She had it from someone, and I'll pass it on, and that's the whole shape of it.
    >>  ............................................
    pt  Passa de mão em mão. Ela recebeu de alguém, e eu vou passar, e é todo o formato da coisa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.craft/1
    en  From a woman who fed me for four years while telling me nothing. I understood that much later.
    >>  ............................................
    pt  De uma mulher que me alimentou por quatro anos sem me contar nada. Eu entendi muito depois.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.craft/2
    en  It passes hand to hand. She had it from someone, and I'll pass it on, and that's the whole shape of it.
    >>  ............................................
    pt  Passa de mão em mão. Ela recebeu de alguém, e eu vou passar, e é todo o formato da coisa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.craft/1
    en  From a woman who fed me for four years while telling me nothing. I understood that much later.
    >>  ............................................
    pt  De uma mulher que me alimentou por quatro anos sem me contar nada. Eu entendi muito depois.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.craft/2
    en  It passes hand to hand. She had it from someone, and I'll pass it on, and that's the whole shape of it.
    >>  ............................................
    pt  Passa de mão em mão. Ela recebeu de alguém, e eu vou passar, e é todo o formato da coisa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.craft/1
    en  She let me find out for myself that what I'd repeated was false. That was the entire punishment.
    >>  ............................................
    pt  Ela me deixou descobrir sozinho que o que eu repeti era falso. Foi toda a punição.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.craft/2
    en  It has to live in a head, and heads end. We have never solved that and I think about it often.
    >>  ............................................
    pt  Tem que viver numa cabeça, e cabeças acabam. Nunca resolvemos isso e eu penso nisso sempre.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.craft/1
    en  Memory. Nothing we hold is written where a fire could reach, so it lives in heads, and mine is one.
    >>  ............................................
    pt  Memória. Nada do que guardamos está escrito onde o fogo alcance, então vive em cabeças, e a minha é uma.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.craft/2
    en  I was taught by someone who tested me for four years before she taught me anything true.
    >>  ............................................
    pt  Fui ensinado por alguém que me testou quatro anos antes de me ensinar algo verdadeiro.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.craft/1
    en  Memory. Nothing we hold is written where a fire could reach, so it lives in heads, and mine is one.
    >>  ............................................
    pt  Memória. Nada do que guardamos está escrito onde o fogo alcance, então vive em cabeças, e a minha é uma.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.craft/2
    en  I was taught by someone who tested me for four years before she taught me anything true.
    >>  ............................................
    pt  Fui ensinado por alguém que me testou quatro anos antes de me ensinar algo verdadeiro.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.craft/1
    en  She told me falsehoods and waited to see whether I'd repeat them outside. I did, once, at nineteen.
    >>  ............................................
    pt  Ela me contava falsidades e esperava pra ver se eu repetia fora. Eu repeti, uma vez, aos dezenove.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.craft/2
    en  Copying, by hand, the same forty lines, until the hand does it without me. That is the whole method.
    >>  ............................................
    pt  Copiar, à mão, as mesmas quarenta linhas, até a mão fazer sem mim. É todo o método.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of being watched, then eleven of being trusted. Neither number could have been smaller.
    >>  ............................................
    pt  Quatro anos sendo observado, depois onze sendo confiado. Nenhum dos números podia ser menor.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.craft/2
    en  It takes a person who can be boring for decades. That is the actual requirement and I met it.
    >>  ............................................
    pt  Exige uma pessoa que consiga ser entediante por décadas. É esse o requisito real e eu atendi.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.craft/1
    en  She told me falsehoods and waited to see whether I'd repeat them outside. I did, once, at nineteen.
    >>  ............................................
    pt  Ela me contava falsidades e esperava pra ver se eu repetia fora. Eu repeti, uma vez, aos dezenove.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.craft/2
    en  Copying, by hand, the same forty lines, until the hand does it without me. That is the whole method.
    >>  ............................................
    pt  Copiar, à mão, as mesmas quarenta linhas, até a mão fazer sem mim. É todo o método.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of being watched, then eleven of being trusted. Neither number could have been smaller.
    >>  ............................................
    pt  Quatro anos sendo observado, depois onze sendo confiado. Nenhum dos números podia ser menor.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.craft/2
    en  It takes a person who can be boring for decades. That is the actual requirement and I met it.
    >>  ............................................
    pt  Exige uma pessoa que consiga ser entediante por décadas. É esse o requisito real e eu atendi.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of tests before a single true word. She had a sense of theatre, my teacher.
    >>  ............................................
    pt  Quatro anos de testes antes de uma palavra verdadeira. Ela tinha senso de teatro, minha mestra.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.craft/2
    en  It's all memory. Which means the whole order could be lost to one bad head cold. We don't dwell on it.
    >>  ............................................
    pt  É tudo memória. O que significa que a ordem inteira pode se perder num resfriado ruim. A gente não pensa nisso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of tests before a single true word. She had a sense of theatre, my teacher.
    >>  ............................................
    pt  Quatro anos de testes antes de uma palavra verdadeira. Ela tinha senso de teatro, minha mestra.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.craft/2
    en  It's all memory. Which means the whole order could be lost to one bad head cold. We don't dwell on it.
    >>  ............................................
    pt  É tudo memória. O que significa que a ordem inteira pode se perder num resfriado ruim. A gente não pensa nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of being watched, then eleven of being trusted. Neither number could have been smaller.
    >>  ............................................
    pt  Quatro anos sendo observado, depois onze sendo confiado. Nenhum dos números podia ser menor.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.craft/2
    en  It takes a person who can be boring for decades. That is the actual requirement and I met it.
    >>  ............................................
    pt  Exige uma pessoa que consiga ser entediante por décadas. É esse o requisito real e eu atendi.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.craft/1
    en  She let me find out for myself that what I'd repeated was false. That was the entire punishment.
    >>  ............................................
    pt  Ela me deixou descobrir sozinho que o que eu repeti era falso. Foi toda a punição.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.craft/2
    en  It has to live in a head, and heads end. We have never solved that and I think about it often.
    >>  ............................................
    pt  Tem que viver numa cabeça, e cabeças acabam. Nunca resolvemos isso e eu penso nisso sempre.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.craft/1
    en  She told me falsehoods and waited to see whether I'd repeat them outside. I did, once, at nineteen.
    >>  ............................................
    pt  Ela me contava falsidades e esperava pra ver se eu repetia fora. Eu repeti, uma vez, aos dezenove.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.craft/2
    en  Copying, by hand, the same forty lines, until the hand does it without me. That is the whole method.
    >>  ............................................
    pt  Copiar, à mão, as mesmas quarenta linhas, até a mão fazer sem mim. É todo o método.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of tests before a single true word. She had a sense of theatre, my teacher.
    >>  ............................................
    pt  Quatro anos de testes antes de uma palavra verdadeira. Ela tinha senso de teatro, minha mestra.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.craft/2
    en  It's all memory. Which means the whole order could be lost to one bad head cold. We don't dwell on it.
    >>  ............................................
    pt  É tudo memória. O que significa que a ordem inteira pode se perder num resfriado ruim. A gente não pensa nisso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.craft/1
    en  Four years of tests before a single true word. She had a sense of theatre, my teacher.
    >>  ............................................
    pt  Quatro anos de testes antes de uma palavra verdadeira. Ela tinha senso de teatro, minha mestra.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.craft/2
    en  It's all memory. Which means the whole order could be lost to one bad head cold. We don't dwell on it.
    >>  ............................................
    pt  É tudo memória. O que significa que a ordem inteira pode se perder num resfriado ruim. A gente não pensa nisso.
    >>  ............................................
```

</details>


**Outcome 126 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
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

```text
  dialogue.conversations.work.prof.cultist.risk/1   [98 chars]
    en  People here are polite to my face and careful behind it. I've made my peace with the careful part.
    >>  ............................................
    pt  As pessoas aqui são educadas na minha frente e cautelosas atrás. Fiz as pazes com a parte cautelosa.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.risk/2   [112 chars]
    en  If I'm wrong about all of it, I've given forty years to a lamp. I think about that more than anyone would guess.
    >>  ............................................
    pt  Se eu estiver errado sobre tudo, dei quarenta anos a uma lamparina. Penso nisso mais do que imaginam.
    >>  ............................................
```


**Outcome 127 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
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

```text
  dialogue.conversations.work.prof.cultist.village/1   [103 chars]
    en  I keep the names of everyone who's died here for ninety years. The church keeps dates; I keep the rest.
    >>  ............................................
    pt  Guardo os nomes de todos que morreram aqui por noventa anos. A igreja guarda datas; eu guardo o resto.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.village/2   [80 chars]
    en  Two families come to me and eleven don't, and I light the lamp for all thirteen.
    >>  ............................................
    pt  Duas famílias vêm até mim e onze não vêm, e eu acendo a lamparina pelas treze.
    >>  ............................................
```


**Outcome 128 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:cultist"
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

```text
  dialogue.conversations.work.prof.cultist.future/1   [87 chars]
    en  Somebody has to take the forty lines. I've tested three people and passed none of them.
    >>  ............................................
    pt  Alguém tem que receber as quarenta linhas. Testei três pessoas e nenhuma passou.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.future/2   [103 chars]
    en  I'd like the lamp to be ordinary. Not respected — ordinary. A thing on a shelf that nobody flinches at.
    >>  ............................................
    pt  Queria que a lamparina fosse comum. Não respeitada — comum. Uma coisa na prateleira que ninguém estranha.
    >>  ............................................
```


**Outcome 129 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
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

```text
  dialogue.conversations.work.prof.guard.task/1   [87 chars]
    en  Standing here. That's the whole of it and it is harder than it looks by about hour six.
    >>  ............................................
    pt  Ficando aqui. É tudo, e é mais difícil do que parece lá pela sexta hora.
    >>  ............................................
  dialogue.conversations.work.prof.guard.task/2   [98 chars]
    en  Walking the perimeter and finding out which gate the children have been getting through this week.
    >>  ............................................
    pt  Andando o perímetro e descobrindo por qual portão as crianças estão passando esta semana.
    >>  ............................................
```


**Outcome 130 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
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

```text
  dialogue.conversations.work.prof.guard.craft/1   [105 chars]
    en  The skill is noticing what's different. Same lane, same faces, every day — until one day something isn't.
    >>  ............................................
    pt  A habilidade é reparar no que mudou. Mesma viela, mesmos rostos, todo dia — até que um dia algo não está.
    >>  ............................................
  dialogue.conversations.work.prof.guard.craft/2   [93 chars]
    en  I was trained by a man who made me describe the square with my eyes shut. I hated him for it.
    >>  ............................................
    pt  Fui treinado por um homem que me fazia descrever a praça de olhos fechados. Eu o odiava por isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.guard.craft/1
    en  I learned properly four years ago, an hour before the raid, when I noticed and did not act fast enough.
    >>  ............................................
    pt  Aprendi de verdade quatro anos atrás, uma hora antes do ataque, quando eu reparei e não agi rápido.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.guard.craft/2
    en  The training was fine. What taught me was standing at a quiet gate while the noise was elsewhere.
    >>  ............................................
    pt  O treino foi bom. O que me ensinou foi ficar num portão calmo enquanto o barulho estava em outro lugar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.craft/1
    en  Years of the same street. That's not a shortcut anybody has ever found a way around.
    >>  ............................................
    pt  Anos da mesma rua. Não é um atalho que alguém tenha achado como contornar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.craft/2
    en  An old guard, at his pace, over about four years. He was in no hurry and neither was the wall.
    >>  ............................................
    pt  Um guarda velho, no ritmo dele, ao longo de uns quatro anos. Ele não tinha pressa e a muralha também não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.craft/1
    en  Noticing what's different. Same lane, same faces, until one day something isn't, and you must already be looking.
    >>  ............................................
    pt  Reparar no que mudou. Mesma viela, mesmos rostos, até que um dia algo não está, e você já tem que estar olhando.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.craft/2
    en  A man made me describe the square with my eyes shut. I hated him for it and he was right.
    >>  ............................................
    pt  Um homem me fez descrever a praça de olhos fechados. Eu o odiei por isso e ele tinha razão.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.craft/1
    en  Noticing what's different. Same lane, same faces, until one day something isn't, and you must already be looking.
    >>  ............................................
    pt  Reparar no que mudou. Mesma viela, mesmos rostos, até que um dia algo não está, e você já tem que estar olhando.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.craft/2
    en  A man made me describe the square with my eyes shut. I hated him for it and he was right.
    >>  ............................................
    pt  Um homem me fez descrever a praça de olhos fechados. Eu o odiei por isso e ele tinha razão.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.craft/1
    en  An old guard trained me. He died before I could tell him he was right, which is how it goes with teachers.
    >>  ............................................
    pt  Um guarda velho me treinou. Morreu antes de eu poder dizer que ele tinha razão, é assim com mestres.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.craft/2
    en  I learned it walking beside somebody. It doesn't go in any other way — you have to be shown the same lane twice.
    >>  ............................................
    pt  Aprendi andando ao lado de alguém. Não entra de outro jeito — você tem que ver a mesma viela duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.craft/1
    en  An old guard trained me. He died before I could tell him he was right, which is how it goes with teachers.
    >>  ............................................
    pt  Um guarda velho me treinou. Morreu antes de eu poder dizer que ele tinha razão, é assim com mestres.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.craft/2
    en  I learned it walking beside somebody. It doesn't go in any other way — you have to be shown the same lane twice.
    >>  ............................................
    pt  Aprendi andando ao lado de alguém. Não entra de outro jeito — você tem que ver a mesma viela duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.craft/1
    en  An old guard trained me. He died before I could tell him he was right, which is how it goes with teachers.
    >>  ............................................
    pt  Um guarda velho me treinou. Morreu antes de eu poder dizer que ele tinha razão, é assim com mestres.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.craft/2
    en  I learned it walking beside somebody. It doesn't go in any other way — you have to be shown the same lane twice.
    >>  ............................................
    pt  Aprendi andando ao lado de alguém. Não entra de outro jeito — você tem que ver a mesma viela duas vezes.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.craft/1
    en  I learned properly four years ago, an hour before the raid, when I noticed and did not act fast enough.
    >>  ............................................
    pt  Aprendi de verdade quatro anos atrás, uma hora antes do ataque, quando eu reparei e não agi rápido.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.craft/2
    en  The training was fine. What taught me was standing at a quiet gate while the noise was elsewhere.
    >>  ............................................
    pt  O treino foi bom. O que me ensinou foi ficar num portão calmo enquanto o barulho estava em outro lugar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.craft/1
    en  Noticing what's different. Same lane, same faces, until one day something isn't, and you must already be looking.
    >>  ............................................
    pt  Reparar no que mudou. Mesma viela, mesmos rostos, até que um dia algo não está, e você já tem que estar olhando.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.craft/2
    en  A man made me describe the square with my eyes shut. I hated him for it and he was right.
    >>  ............................................
    pt  Um homem me fez descrever a praça de olhos fechados. Eu o odiei por isso e ele tinha razão.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.craft/1
    en  Noticing what's different. Same lane, same faces, until one day something isn't, and you must already be looking.
    >>  ............................................
    pt  Reparar no que mudou. Mesma viela, mesmos rostos, até que um dia algo não está, e você já tem que estar olhando.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.craft/2
    en  A man made me describe the square with my eyes shut. I hated him for it and he was right.
    >>  ............................................
    pt  Um homem me fez descrever a praça de olhos fechados. Eu o odiei por isso e ele tinha razão.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.craft/1
    en  Eyes shut, describing the square. Every fence, every barrel, every window that's usually open.
    >>  ............................................
    pt  Olhos fechados, descrevendo a praça. Cada cerca, cada barril, cada janela que costuma estar aberta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.craft/2
    en  Same round, same hour, same order. Vary it and you lose the only instrument the trade has.
    >>  ............................................
    pt  Mesma ronda, mesma hora, mesma ordem. Varie e você perde o único instrumento do ofício.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.craft/1
    en  Years of the same street. That's not a shortcut anybody has ever found a way around.
    >>  ............................................
    pt  Anos da mesma rua. Não é um atalho que alguém tenha achado como contornar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.craft/2
    en  An old guard, at his pace, over about four years. He was in no hurry and neither was the wall.
    >>  ............................................
    pt  Um guarda velho, no ritmo dele, ao longo de uns quatro anos. Ele não tinha pressa e a muralha também não.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.craft/1
    en  Eyes shut, describing the square. Every fence, every barrel, every window that's usually open.
    >>  ............................................
    pt  Olhos fechados, descrevendo a praça. Cada cerca, cada barril, cada janela que costuma estar aberta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.craft/2
    en  Same round, same hour, same order. Vary it and you lose the only instrument the trade has.
    >>  ............................................
    pt  Mesma ronda, mesma hora, mesma ordem. Varie e você perde o único instrumento do ofício.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.craft/1
    en  Years of the same street. That's not a shortcut anybody has ever found a way around.
    >>  ............................................
    pt  Anos da mesma rua. Não é um atalho que alguém tenha achado como contornar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.craft/2
    en  An old guard, at his pace, over about four years. He was in no hurry and neither was the wall.
    >>  ............................................
    pt  Um guarda velho, no ritmo dele, ao longo de uns quatro anos. Ele não tinha pressa e a muralha também não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.craft/1
    en  Somebody made me describe the whole square with my eyes shut. I've since done it to two other people. Fair's fair.
    >>  ............................................
    pt  Alguém me fez descrever a praça inteira de olhos fechados. Já fiz isso com duas outras pessoas. É justo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.craft/2
    en  The trade is looking at the same street every day until you'd notice a moved bucket. Riveting, I know.
    >>  ............................................
    pt  O ofício é olhar a mesma rua todo dia até você reparar num balde movido. Empolgante, eu sei.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.craft/1
    en  Somebody made me describe the whole square with my eyes shut. I've since done it to two other people. Fair's fair.
    >>  ............................................
    pt  Alguém me fez descrever a praça inteira de olhos fechados. Já fiz isso com duas outras pessoas. É justo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.craft/2
    en  The trade is looking at the same street every day until you'd notice a moved bucket. Riveting, I know.
    >>  ............................................
    pt  O ofício é olhar a mesma rua todo dia até você reparar num balde movido. Empolgante, eu sei.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.craft/1
    en  Years of the same street. That's not a shortcut anybody has ever found a way around.
    >>  ............................................
    pt  Anos da mesma rua. Não é um atalho que alguém tenha achado como contornar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.craft/2
    en  An old guard, at his pace, over about four years. He was in no hurry and neither was the wall.
    >>  ............................................
    pt  Um guarda velho, no ritmo dele, ao longo de uns quatro anos. Ele não tinha pressa e a muralha também não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.craft/1
    en  I learned properly four years ago, an hour before the raid, when I noticed and did not act fast enough.
    >>  ............................................
    pt  Aprendi de verdade quatro anos atrás, uma hora antes do ataque, quando eu reparei e não agi rápido.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.craft/2
    en  The training was fine. What taught me was standing at a quiet gate while the noise was elsewhere.
    >>  ............................................
    pt  O treino foi bom. O que me ensinou foi ficar num portão calmo enquanto o barulho estava em outro lugar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.craft/1
    en  Eyes shut, describing the square. Every fence, every barrel, every window that's usually open.
    >>  ............................................
    pt  Olhos fechados, descrevendo a praça. Cada cerca, cada barril, cada janela que costuma estar aberta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.craft/2
    en  Same round, same hour, same order. Vary it and you lose the only instrument the trade has.
    >>  ............................................
    pt  Mesma ronda, mesma hora, mesma ordem. Varie e você perde o único instrumento do ofício.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.craft/1
    en  Somebody made me describe the whole square with my eyes shut. I've since done it to two other people. Fair's fair.
    >>  ............................................
    pt  Alguém me fez descrever a praça inteira de olhos fechados. Já fiz isso com duas outras pessoas. É justo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.craft/2
    en  The trade is looking at the same street every day until you'd notice a moved bucket. Riveting, I know.
    >>  ............................................
    pt  O ofício é olhar a mesma rua todo dia até você reparar num balde movido. Empolgante, eu sei.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.craft/1
    en  Somebody made me describe the whole square with my eyes shut. I've since done it to two other people. Fair's fair.
    >>  ............................................
    pt  Alguém me fez descrever a praça inteira de olhos fechados. Já fiz isso com duas outras pessoas. É justo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.craft/2
    en  The trade is looking at the same street every day until you'd notice a moved bucket. Riveting, I know.
    >>  ............................................
    pt  O ofício é olhar a mesma rua todo dia até você reparar num balde movido. Empolgante, eu sei.
    >>  ............................................
```

</details>


**Outcome 131 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
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

```text
  dialogue.conversations.work.prof.guard.risk/1   [97 chars]
    en  The raid, four years back. I was at the wrong gate and I have never been at the wrong gate since.
    >>  ............................................
    pt  O ataque, quatro anos atrás. Eu estava no portão errado e nunca mais estive no portão errado.
    >>  ............................................
  dialogue.conversations.work.prof.guard.risk/2   [94 chars]
    en  Everyone here is somebody's. If I'm slow, the arithmetic of that lands on a family, not on me.
    >>  ............................................
    pt  Todo mundo aqui é de alguém. Se eu for lento, a conta cai numa família, não em mim.
    >>  ............................................
```


**Outcome 132 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
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

```text
  dialogue.conversations.work.prof.guard.village/1   [93 chars]
    en  Four years without a second raid. I don't get to claim that and I count it every single week.
    >>  ............................................
    pt  Quatro anos sem um segundo ataque. Eu não posso reivindicar isso e eu conto toda semana.
    >>  ............................................
  dialogue.conversations.work.prof.guard.village/2   [99 chars]
    en  I know every door here and which ones don't lock. Six of them. I walk past those six twice a night.
    >>  ............................................
    pt  Conheço cada porta daqui e quais não trancam. Seis. Passo por essas seis duas vezes por noite.
    >>  ............................................
```


**Outcome 133 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:guard"
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

```text
  dialogue.conversations.work.prof.guard.future/1   [98 chars]
    en  A second guard, so that four years becomes a thing two people did and not a thing I got away with.
    >>  ............................................
    pt  Um segundo guarda, pra que quatro anos vire coisa que duas pessoas fizeram e não coisa de que eu escapei.
    >>  ............................................
  dialogue.conversations.work.prof.guard.future/2   [100 chars]
    en  I'd like to be wrong about needing to be here. Nothing would make me happier than being unnecessary.
    >>  ............................................
    pt  Queria estar errado sobre precisar estar aqui. Nada me faria mais feliz que ser desnecessário.
    >>  ............................................
```


**Outcome 134 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
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

```text
  dialogue.conversations.work.prof.mercenary.task/1   [99 chars]
    en  Waiting on a contract that hasn't come. Nine days now, which is nine days of eating and no earning.
    >>  ............................................
    pt  Esperando um contrato que não veio. Nove dias, o que são nove dias comendo e não ganhando.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.task/2   [106 chars]
    en  Cleaning kit nobody has paid me to use this month. It's habit, and habit is what I have instead of a post.
    >>  ............................................
    pt  Limpando equipamento que ninguém pagou pra eu usar este mês. É hábito, e hábito é o que eu tenho em vez de posto.
    >>  ............................................
```


**Outcome 135 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
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

```text
  dialogue.conversations.work.prof.mercenary.craft/1   [98 chars]
    en  Half of it is fighting and half is knowing which contracts to refuse. The second half took longer.
    >>  ............................................
    pt  Metade é lutar e metade é saber quais contratos recusar. A segunda metade levou mais tempo.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.craft/2   [93 chars]
    en  Everything I know I learned in a company of forty, of whom I could name eleven who are alive.
    >>  ............................................
    pt  Tudo que eu sei aprendi numa companhia de quarenta, dos quais eu nomearia onze que estão vivos.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mercenary.craft/1
    en  Most of the forty didn't die in battle. Fever, roads and bad water. Nobody teaches you about that part.
    >>  ............................................
    pt  A maioria dos quarenta não morreu em batalha. Febre, estradas e água ruim. Ninguém te ensina essa parte.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned what to refuse by nearly taking one. Nine days hungry, and I walked away on the ninth.
    >>  ............................................
    pt  Aprendi o que recusar quase aceitando um. Nove dias com fome, e eu fui embora no nono.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years to learn the fighting and about the same again to learn the refusing.
    >>  ............................................
    pt  Dezenove anos pra aprender a lutar e mais ou menos o mesmo pra aprender a recusar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mercenary.craft/2
    en  Slowly, and then all at once, on one bad afternoon. The slow part is the part I'd keep.
    >>  ............................................
    pt  Devagar, e aí de uma vez, numa tarde ruim. A parte devagar é a que eu guardaria.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.craft/1
    en  Half of it is fighting and half is knowing which contracts to refuse. The second half took longer.
    >>  ............................................
    pt  Metade é lutar e metade é saber quais contratos recusar. A segunda metade levou mais tempo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mercenary.craft/2
    en  Everything I know came out of a company of forty, of whom I can name eleven who are alive.
    >>  ............................................
    pt  Tudo que eu sei veio de uma companhia de quarenta, dos quais eu nomeio onze que estão vivos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.craft/1
    en  Half of it is fighting and half is knowing which contracts to refuse. The second half took longer.
    >>  ............................................
    pt  Metade é lutar e metade é saber quais contratos recusar. A segunda metade levou mais tempo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mercenary.craft/2
    en  Everything I know came out of a company of forty, of whom I can name eleven who are alive.
    >>  ............................................
    pt  Tudo que eu sei veio de uma companhia de quarenta, dos quais eu nomeio onze que estão vivos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.craft/1
    en  A captain taught me what to refuse. He's one of the eleven still alive, and that is not a coincidence.
    >>  ............................................
    pt  Um capitão me ensinou o que recusar. Ele é um dos onze ainda vivos, e não é coincidência.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned it from forty people, and I could tell you which of them taught me which piece.
    >>  ............................................
    pt  Aprendi com quarenta pessoas, e eu saberia dizer qual delas me ensinou qual pedaço.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.craft/1
    en  A captain taught me what to refuse. He's one of the eleven still alive, and that is not a coincidence.
    >>  ............................................
    pt  Um capitão me ensinou o que recusar. Ele é um dos onze ainda vivos, e não é coincidência.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned it from forty people, and I could tell you which of them taught me which piece.
    >>  ............................................
    pt  Aprendi com quarenta pessoas, e eu saberia dizer qual delas me ensinou qual pedaço.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.craft/1
    en  A captain taught me what to refuse. He's one of the eleven still alive, and that is not a coincidence.
    >>  ............................................
    pt  Um capitão me ensinou o que recusar. Ele é um dos onze ainda vivos, e não é coincidência.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned it from forty people, and I could tell you which of them taught me which piece.
    >>  ............................................
    pt  Aprendi com quarenta pessoas, e eu saberia dizer qual delas me ensinou qual pedaço.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.craft/1
    en  Most of the forty didn't die in battle. Fever, roads and bad water. Nobody teaches you about that part.
    >>  ............................................
    pt  A maioria dos quarenta não morreu em batalha. Febre, estradas e água ruim. Ninguém te ensina essa parte.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned what to refuse by nearly taking one. Nine days hungry, and I walked away on the ninth.
    >>  ............................................
    pt  Aprendi o que recusar quase aceitando um. Nove dias com fome, e eu fui embora no nono.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.craft/1
    en  Half of it is fighting and half is knowing which contracts to refuse. The second half took longer.
    >>  ............................................
    pt  Metade é lutar e metade é saber quais contratos recusar. A segunda metade levou mais tempo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mercenary.craft/2
    en  Everything I know came out of a company of forty, of whom I can name eleven who are alive.
    >>  ............................................
    pt  Tudo que eu sei veio de uma companhia de quarenta, dos quais eu nomeio onze que estão vivos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.craft/1
    en  Half of it is fighting and half is knowing which contracts to refuse. The second half took longer.
    >>  ............................................
    pt  Metade é lutar e metade é saber quais contratos recusar. A segunda metade levou mais tempo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mercenary.craft/2
    en  Everything I know came out of a company of forty, of whom I can name eleven who are alive.
    >>  ............................................
    pt  Tudo que eu sei veio de uma companhia de quarenta, dos quais eu nomeio onze que estão vivos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.craft/1
    en  If they won't say who is on the other side, the other side is somebody's village.
    >>  ............................................
    pt  Se não dizem quem está do outro lado, o outro lado é o vilarejo de alguém.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mercenary.craft/2
    en  I read a contract twice when it flatters me. That habit is the only thing keeping me from being a different man.
    >>  ............................................
    pt  Leio um contrato duas vezes quando ele me bajula. Esse hábito é a única coisa que me impede de ser outro homem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years to learn the fighting and about the same again to learn the refusing.
    >>  ............................................
    pt  Dezenove anos pra aprender a lutar e mais ou menos o mesmo pra aprender a recusar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mercenary.craft/2
    en  Slowly, and then all at once, on one bad afternoon. The slow part is the part I'd keep.
    >>  ............................................
    pt  Devagar, e aí de uma vez, numa tarde ruim. A parte devagar é a que eu guardaria.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.craft/1
    en  If they won't say who is on the other side, the other side is somebody's village.
    >>  ............................................
    pt  Se não dizem quem está do outro lado, o outro lado é o vilarejo de alguém.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mercenary.craft/2
    en  I read a contract twice when it flatters me. That habit is the only thing keeping me from being a different man.
    >>  ............................................
    pt  Leio um contrato duas vezes quando ele me bajula. Esse hábito é a única coisa que me impede de ser outro homem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years to learn the fighting and about the same again to learn the refusing.
    >>  ............................................
    pt  Dezenove anos pra aprender a lutar e mais ou menos o mesmo pra aprender a recusar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mercenary.craft/2
    en  Slowly, and then all at once, on one bad afternoon. The slow part is the part I'd keep.
    >>  ............................................
    pt  Devagar, e aí de uma vez, numa tarde ruim. A parte devagar é a que eu guardaria.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years in a company, and the useful half of it was learning what to say no to.
    >>  ............................................
    pt  Dezenove anos numa companhia, e a metade útil foi aprender a dizer não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mercenary.craft/2
    en  If the pay is high for the work described, the work described is not the work. Free advice, that.
    >>  ............................................
    pt  Se o pagamento é alto pro serviço descrito, o serviço descrito não é o serviço. Conselho de graça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years in a company, and the useful half of it was learning what to say no to.
    >>  ............................................
    pt  Dezenove anos numa companhia, e a metade útil foi aprender a dizer não.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mercenary.craft/2
    en  If the pay is high for the work described, the work described is not the work. Free advice, that.
    >>  ............................................
    pt  Se o pagamento é alto pro serviço descrito, o serviço descrito não é o serviço. Conselho de graça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years to learn the fighting and about the same again to learn the refusing.
    >>  ............................................
    pt  Dezenove anos pra aprender a lutar e mais ou menos o mesmo pra aprender a recusar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mercenary.craft/2
    en  Slowly, and then all at once, on one bad afternoon. The slow part is the part I'd keep.
    >>  ............................................
    pt  Devagar, e aí de uma vez, numa tarde ruim. A parte devagar é a que eu guardaria.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.craft/1
    en  Most of the forty didn't die in battle. Fever, roads and bad water. Nobody teaches you about that part.
    >>  ............................................
    pt  A maioria dos quarenta não morreu em batalha. Febre, estradas e água ruim. Ninguém te ensina essa parte.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mercenary.craft/2
    en  I learned what to refuse by nearly taking one. Nine days hungry, and I walked away on the ninth.
    >>  ............................................
    pt  Aprendi o que recusar quase aceitando um. Nove dias com fome, e eu fui embora no nono.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.craft/1
    en  If they won't say who is on the other side, the other side is somebody's village.
    >>  ............................................
    pt  Se não dizem quem está do outro lado, o outro lado é o vilarejo de alguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mercenary.craft/2
    en  I read a contract twice when it flatters me. That habit is the only thing keeping me from being a different man.
    >>  ............................................
    pt  Leio um contrato duas vezes quando ele me bajula. Esse hábito é a única coisa que me impede de ser outro homem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years in a company, and the useful half of it was learning what to say no to.
    >>  ............................................
    pt  Dezenove anos numa companhia, e a metade útil foi aprender a dizer não.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mercenary.craft/2
    en  If the pay is high for the work described, the work described is not the work. Free advice, that.
    >>  ............................................
    pt  Se o pagamento é alto pro serviço descrito, o serviço descrito não é o serviço. Conselho de graça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.craft/1
    en  Nineteen years in a company, and the useful half of it was learning what to say no to.
    >>  ............................................
    pt  Dezenove anos numa companhia, e a metade útil foi aprender a dizer não.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mercenary.craft/2
    en  If the pay is high for the work described, the work described is not the work. Free advice, that.
    >>  ............................................
    pt  Se o pagamento é alto pro serviço descrito, o serviço descrito não é o serviço. Conselho de graça.
    >>  ............................................
```

</details>


**Outcome 136 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
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

```text
  dialogue.conversations.work.prof.mercenary.risk/1   [97 chars]
    en  The risk isn't dying. It's the year you're hungry enough to take the contract you'd have refused.
    >>  ............................................
    pt  O risco não é morrer. É o ano em que você está com fome o bastante pra aceitar o contrato que recusaria.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.risk/2   [110 chars]
    en  People here are civil to me and none of them would leave me alone with their children. Both of those are fair.
    >>  ............................................
    pt  As pessoas aqui são civis comigo e nenhuma me deixaria sozinho com os filhos. As duas coisas são justas.
    >>  ............................................
```


**Outcome 137 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
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

```text
  dialogue.conversations.work.prof.mercenary.village/1   [101 chars]
    en  I've done two things for this place and been paid for one. It's the unpaid one they don't know about.
    >>  ............................................
    pt  Fiz duas coisas por este lugar e fui pago por uma. É a não paga que eles não sabem.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.village/2   [105 chars]
    en  The guard would rather I left and the cleric would rather I stayed. I've decided the cleric outranks him.
    >>  ............................................
    pt  O guarda preferia que eu fosse embora e a clériga preferia que eu ficasse. Decidi que a clériga manda mais.
    >>  ............................................
```


**Outcome 138 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:mercenary"
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

```text
  dialogue.conversations.work.prof.mercenary.future/1   [97 chars]
    en  A post. Anybody's. I'd take a wage and a name over a contract and a purse, and I'd take it today.
    >>  ............................................
    pt  Um posto. De qualquer um. Eu trocaria salário e nome por contrato e bolsa, e trocaria hoje.
    >>  ............................................
  dialogue.conversations.work.prof.mercenary.future/2   [92 chars]
    en  There's a company reforming in the spring and they've sent for me. I have not answered them.
    >>  ............................................
    pt  Tem uma companhia se reformando na primavera e mandaram me chamar. Eu não respondi.
    >>  ............................................
```


**Outcome 139 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
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

```text
  dialogue.conversations.work.prof.outlaw.task/1   [96 chars]
    en  Sitting where I can see the road and be seen doing nothing. It's a full day's occupation, oddly.
    >>  ............................................
    pt  Sentado onde eu vejo a estrada e sou visto sem fazer nada. É ocupação de um dia inteiro, curiosamente.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.task/2   [101 chars]
    en  Splitting kindling for the widow at the end of the lane. She's the only one who asks me for anything.
    >>  ............................................
    pt  Rachando lenha pra viúva do fim da viela. Ela é a única que me pede alguma coisa.
    >>  ............................................
```


**Outcome 140 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
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

```text
  dialogue.conversations.work.prof.outlaw.craft/1   [98 chars]
    en  I'm good at reading a room in about four seconds. It's a useful skill and I learned it very badly.
    >>  ............................................
    pt  Eu sou bom em ler uma sala em uns quatro segundos. É uma habilidade útil e eu aprendi muito mal.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.craft/2   [95 chars]
    en  Locks, doors, and which floorboard talks. I'd rather have learned carpentry and I learned this.
    >>  ............................................
    pt  Fechaduras, portas, e qual tábua fala. Eu preferia ter aprendido carpintaria e aprendi isto.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.outlaw.craft/1
    en  I learned it because I had to, in places I'd not describe, and it is not a skill I'd wish on anybody.
    >>  ............................................
    pt  Aprendi porque tive que aprender, em lugares que eu não descreveria, e não é habilidade que eu deseje a alguém.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.outlaw.craft/2
    en  Every useful thing I know came from a year I'd undo. That is a hard thing to hold and I hold it daily.
    >>  ............................................
    pt  Toda coisa útil que eu sei veio de um ano que eu desfaria. É duro de carregar e eu carrego todo dia.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.craft/1
    en  Slowly and badly, over years I'd rather not itemise. It's still the only trade I fully have.
    >>  ............................................
    pt  Devagar e mal, ao longo de anos que eu prefiro não detalhar. Ainda é o único ofício que eu tenho inteiro.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it young and I've spent four quiet years learning something else. The second lot is going slower.
    >>  ............................................
    pt  Aprendi jovem e passei quatro anos calmos aprendendo outra coisa. A segunda parte está indo mais devagar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.craft/1
    en  Reading a room in four seconds. It's a useful skill and I learned it very badly, and I'd not lie about that.
    >>  ............................................
    pt  Ler uma sala em quatro segundos. É útil e eu aprendi muito mal, e eu não mentiria sobre isso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.outlaw.craft/2
    en  Locks, doors, and which floorboard talks. I'd rather have learned carpentry and I learned this.
    >>  ............................................
    pt  Fechaduras, portas, e qual tábua fala. Eu preferia ter aprendido carpintaria e aprendi isto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.craft/1
    en  Reading a room in four seconds. It's a useful skill and I learned it very badly, and I'd not lie about that.
    >>  ............................................
    pt  Ler uma sala em quatro segundos. É útil e eu aprendi muito mal, e eu não mentiria sobre isso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.outlaw.craft/2
    en  Locks, doors, and which floorboard talks. I'd rather have learned carpentry and I learned this.
    >>  ............................................
    pt  Fechaduras, portas, e qual tábua fala. Eu preferia ter aprendido carpintaria e aprendi isto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.craft/1
    en  Nobody taught me anything I'm proud of. But the woodworker let me hold a plane once, and I remember it.
    >>  ............................................
    pt  Ninguém me ensinou nada de que eu me orgulhe. Mas o marceneiro me deixou segurar uma plaina uma vez, e eu lembro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.outlaw.craft/2
    en  I'd rather be learning something now, beside somebody, than be good at what I got good at.
    >>  ............................................
    pt  Eu preferia estar aprendendo algo agora, ao lado de alguém, do que ser bom no que eu fiquei bom.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.craft/1
    en  Nobody taught me anything I'm proud of. But the woodworker let me hold a plane once, and I remember it.
    >>  ............................................
    pt  Ninguém me ensinou nada de que eu me orgulhe. Mas o marceneiro me deixou segurar uma plaina uma vez, e eu lembro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.outlaw.craft/2
    en  I'd rather be learning something now, beside somebody, than be good at what I got good at.
    >>  ............................................
    pt  Eu preferia estar aprendendo algo agora, ao lado de alguém, do que ser bom no que eu fiquei bom.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.craft/1
    en  Nobody taught me anything I'm proud of. But the woodworker let me hold a plane once, and I remember it.
    >>  ............................................
    pt  Ninguém me ensinou nada de que eu me orgulhe. Mas o marceneiro me deixou segurar uma plaina uma vez, e eu lembro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.outlaw.craft/2
    en  I'd rather be learning something now, beside somebody, than be good at what I got good at.
    >>  ............................................
    pt  Eu preferia estar aprendendo algo agora, ao lado de alguém, do que ser bom no que eu fiquei bom.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.craft/1
    en  I learned it because I had to, in places I'd not describe, and it is not a skill I'd wish on anybody.
    >>  ............................................
    pt  Aprendi porque tive que aprender, em lugares que eu não descreveria, e não é habilidade que eu deseje a alguém.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.outlaw.craft/2
    en  Every useful thing I know came from a year I'd undo. That is a hard thing to hold and I hold it daily.
    >>  ............................................
    pt  Toda coisa útil que eu sei veio de um ano que eu desfaria. É duro de carregar e eu carrego todo dia.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.craft/1
    en  Reading a room in four seconds. It's a useful skill and I learned it very badly, and I'd not lie about that.
    >>  ............................................
    pt  Ler uma sala em quatro segundos. É útil e eu aprendi muito mal, e eu não mentiria sobre isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.outlaw.craft/2
    en  Locks, doors, and which floorboard talks. I'd rather have learned carpentry and I learned this.
    >>  ............................................
    pt  Fechaduras, portas, e qual tábua fala. Eu preferia ter aprendido carpintaria e aprendi isto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.craft/1
    en  Reading a room in four seconds. It's a useful skill and I learned it very badly, and I'd not lie about that.
    >>  ............................................
    pt  Ler uma sala em quatro segundos. É útil e eu aprendi muito mal, e eu não mentiria sobre isso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.outlaw.craft/2
    en  Locks, doors, and which floorboard talks. I'd rather have learned carpentry and I learned this.
    >>  ............................................
    pt  Fechaduras, portas, e qual tábua fala. Eu preferia ter aprendido carpintaria e aprendi isto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.craft/1
    en  I can tell in about two seconds which of them has already decided about me. That one is quicker.
    >>  ............................................
    pt  Eu digo em uns dois segundos qual deles já decidiu sobre mim. Esse é mais rápido.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.outlaw.craft/2
    en  Nobody taught me. You pick it up in rooms where being wrong about the mood costs you something.
    >>  ............................................
    pt  Ninguém me ensinou. Você pega em salas onde errar o clima te custa algo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.craft/1
    en  Slowly and badly, over years I'd rather not itemise. It's still the only trade I fully have.
    >>  ............................................
    pt  Devagar e mal, ao longo de anos que eu prefiro não detalhar. Ainda é o único ofício que eu tenho inteiro.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it young and I've spent four quiet years learning something else. The second lot is going slower.
    >>  ............................................
    pt  Aprendi jovem e passei quatro anos calmos aprendendo outra coisa. A segunda parte está indo mais devagar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.craft/1
    en  I can tell in about two seconds which of them has already decided about me. That one is quicker.
    >>  ............................................
    pt  Eu digo em uns dois segundos qual deles já decidiu sobre mim. Esse é mais rápido.
    >>  ............................................
  odd.dialogue.conversations.work.prof.outlaw.craft/2
    en  Nobody taught me. You pick it up in rooms where being wrong about the mood costs you something.
    >>  ............................................
    pt  Ninguém me ensinou. Você pega em salas onde errar o clima te custa algo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.craft/1
    en  Slowly and badly, over years I'd rather not itemise. It's still the only trade I fully have.
    >>  ............................................
    pt  Devagar e mal, ao longo de anos que eu prefiro não detalhar. Ainda é o único ofício que eu tenho inteiro.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it young and I've spent four quiet years learning something else. The second lot is going slower.
    >>  ............................................
    pt  Aprendi jovem e passei quatro anos calmos aprendendo outra coisa. A segunda parte está indo mais devagar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.craft/1
    en  Four seconds and I can tell you who's watching the door and who's had too much. Party trick, that.
    >>  ............................................
    pt  Quatro segundos e eu digo quem está vigiando a porta e quem bebeu demais. Truque de festa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it the wrong way round and in the wrong order, and it works anyway, which is annoying.
    >>  ............................................
    pt  Aprendi ao contrário e na ordem errada, e funciona mesmo assim, o que é irritante.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.craft/1
    en  Four seconds and I can tell you who's watching the door and who's had too much. Party trick, that.
    >>  ............................................
    pt  Quatro segundos e eu digo quem está vigiando a porta e quem bebeu demais. Truque de festa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it the wrong way round and in the wrong order, and it works anyway, which is annoying.
    >>  ............................................
    pt  Aprendi ao contrário e na ordem errada, e funciona mesmo assim, o que é irritante.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.craft/1
    en  Slowly and badly, over years I'd rather not itemise. It's still the only trade I fully have.
    >>  ............................................
    pt  Devagar e mal, ao longo de anos que eu prefiro não detalhar. Ainda é o único ofício que eu tenho inteiro.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it young and I've spent four quiet years learning something else. The second lot is going slower.
    >>  ............................................
    pt  Aprendi jovem e passei quatro anos calmos aprendendo outra coisa. A segunda parte está indo mais devagar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.craft/1
    en  I learned it because I had to, in places I'd not describe, and it is not a skill I'd wish on anybody.
    >>  ............................................
    pt  Aprendi porque tive que aprender, em lugares que eu não descreveria, e não é habilidade que eu deseje a alguém.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.outlaw.craft/2
    en  Every useful thing I know came from a year I'd undo. That is a hard thing to hold and I hold it daily.
    >>  ............................................
    pt  Toda coisa útil que eu sei veio de um ano que eu desfaria. É duro de carregar e eu carrego todo dia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.craft/1
    en  I can tell in about two seconds which of them has already decided about me. That one is quicker.
    >>  ............................................
    pt  Eu digo em uns dois segundos qual deles já decidiu sobre mim. Esse é mais rápido.
    >>  ............................................
  shy.dialogue.conversations.work.prof.outlaw.craft/2
    en  Nobody taught me. You pick it up in rooms where being wrong about the mood costs you something.
    >>  ............................................
    pt  Ninguém me ensinou. Você pega em salas onde errar o clima te custa algo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.craft/1
    en  Four seconds and I can tell you who's watching the door and who's had too much. Party trick, that.
    >>  ............................................
    pt  Quatro segundos e eu digo quem está vigiando a porta e quem bebeu demais. Truque de festa.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it the wrong way round and in the wrong order, and it works anyway, which is annoying.
    >>  ............................................
    pt  Aprendi ao contrário e na ordem errada, e funciona mesmo assim, o que é irritante.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.craft/1
    en  Four seconds and I can tell you who's watching the door and who's had too much. Party trick, that.
    >>  ............................................
    pt  Quatro segundos e eu digo quem está vigiando a porta e quem bebeu demais. Truque de festa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.outlaw.craft/2
    en  I learned it the wrong way round and in the wrong order, and it works anyway, which is annoying.
    >>  ............................................
    pt  Aprendi ao contrário e na ordem errada, e funciona mesmo assim, o que é irritante.
    >>  ............................................
```

</details>


**Outcome 141 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
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

```text
  dialogue.conversations.work.prof.outlaw.risk/1   [108 chars]
    en  Anything that goes missing here is mine until proven otherwise. That's the arrangement and it never expires.
    >>  ............................................
    pt  Qualquer coisa que suma aqui é minha até prova em contrário. É o acordo e ele nunca vence.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.risk/2   [84 chars]
    en  There's a name for me in the next valley and one day somebody walks in who knows it.
    >>  ............................................
    pt  Tem um nome pra mim no vale vizinho e um dia alguém entra aqui sabendo dele.
    >>  ............................................
```


**Outcome 142 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
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

```text
  dialogue.conversations.work.prof.outlaw.village/1   [106 chars]
    en  This place let me stay when it had no reason to. I've not forgotten that and I've not been told it counts.
    >>  ............................................
    pt  Este lugar me deixou ficar quando não tinha motivo. Eu não esqueci e não me disseram que conta.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.village/2   [79 chars]
    en  No one here has locked a door against me and no one has invited me through one.
    >>  ............................................
    pt  Nesta terra ninguém trancou uma porta contra mim e ninguém me convidou a atravessar uma.
    >>  ............................................
```


**Outcome 143 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "mca:outlaw"
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

```text
  dialogue.conversations.work.prof.outlaw.future/1   [84 chars]
    en  I want a trade. Any trade, badly paid, with somebody willing to be seen teaching me.
    >>  ............................................
    pt  Quero um ofício. Qualquer um, mal pago, com alguém disposto a ser visto me ensinando.
    >>  ............................................
  dialogue.conversations.work.prof.outlaw.future/2   [98 chars]
    en  Or the road again, before somebody arrives from the next valley and takes the choice away from me.
    >>  ............................................
    pt  Ou a estrada de novo, antes que alguém chegue do vale vizinho e tire a escolha de mim.
    >>  ............................................
```


**Outcome 144 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
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

```text
  dialogue.conversations.work.prof.woodworker.task/1   [89 chars]
    en  Joints. Eleven of them for a frame, and the eleventh has to fit as well as the first did.
    >>  ............................................
    pt  Encaixes. Onze deles pra uma armação, e o décimo primeiro tem que servir tão bem quanto o primeiro.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.task/2   [90 chars]
    en  Seasoning stock and resisting the urge to use it early. That urge has ruined three chairs.
    >>  ............................................
    pt  Curando madeira e resistindo à vontade de usar cedo. Essa vontade estragou três cadeiras.
    >>  ............................................
```


**Outcome 145 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
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

```text
  dialogue.conversations.work.prof.woodworker.craft/1   [93 chars]
    en  Wood moves after you've finished with it. The whole trade is guessing which way, years ahead.
    >>  ............................................
    pt  A madeira se mexe depois que você termina. O ofício inteiro é adivinhar pra que lado, anos à frente.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.craft/2   [87 chars]
    en  I was taught to plane by a woman who'd rap my knuckles when the shaving came off thick.
    >>  ............................................
    pt  Aprendi a aplainar com uma mulher que batia nos meus nós dos dedos quando a lasca saía grossa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.woodworker.craft/1
    en  Nobody notices when I get it right. They only ever see the three chairs that twisted over one winter.
    >>  ............................................
    pt  Ninguém repara quando eu acerto. Só veem as três cadeiras que empenaram num inverno.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.woodworker.craft/2
    en  I learned by watching things I'd made go wrong slowly, years later, in somebody else's house.
    >>  ............................................
    pt  Aprendi vendo coisas que eu fiz darem errado devagar, anos depois, na casa de outra pessoa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood seasons for two years and a woodworker for about twelve. Neither can be argued down.
    >>  ............................................
    pt  A madeira cura por dois anos e um marceneiro por uns doze. Nenhum dos dois se discute.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.craft/2
    en  Slowly, and by ruining stock I'd been too impatient to leave alone. The impatience went first.
    >>  ............................................
    pt  Devagar, e estragando madeira que eu fui impaciente demais pra deixar em paz. A impaciência foi primeiro.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood moves after you've finished with it. The whole trade is guessing which way, years ahead.
    >>  ............................................
    pt  A madeira se mexe depois que você termina. O ofício inteiro é adivinhar pra que lado, anos à frente.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.craft/2
    en  I was taught to plane by a woman who rapped my knuckles when the shaving came off thick. It worked.
    >>  ............................................
    pt  Aprendi a aplainar com uma mulher que batia nos meus dedos quando a lasca saía grossa. Funcionou.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood moves after you've finished with it. The whole trade is guessing which way, years ahead.
    >>  ............................................
    pt  A madeira se mexe depois que você termina. O ofício inteiro é adivinhar pra que lado, anos à frente.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.craft/2
    en  I was taught to plane by a woman who rapped my knuckles when the shaving came off thick. It worked.
    >>  ............................................
    pt  Aprendi a aplainar com uma mulher que batia nos meus dedos quando a lasca saía grossa. Funcionou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.craft/1
    en  A woman taught me, hands on the plane, and I have kept the thin shaving and let go of most of the resentment.
    >>  ............................................
    pt  Uma mulher me ensinou, com as mãos na plaina, e eu guardei a lasca fina e larguei quase todo o ressentimento.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.craft/2
    en  She taught me to listen to the plane instead of watching it. She was right and she was awful, both fully.
    >>  ............................................
    pt  Ela me ensinou a escutar a plaina em vez de olhar. Ela tinha razão e era terrível, as duas coisas inteiras.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.craft/1
    en  A woman taught me, hands on the plane, and I have kept the thin shaving and let go of most of the resentment.
    >>  ............................................
    pt  Uma mulher me ensinou, com as mãos na plaina, e eu guardei a lasca fina e larguei quase todo o ressentimento.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.craft/2
    en  She taught me to listen to the plane instead of watching it. She was right and she was awful, both fully.
    >>  ............................................
    pt  Ela me ensinou a escutar a plaina em vez de olhar. Ela tinha razão e era terrível, as duas coisas inteiras.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.craft/1
    en  A woman taught me, hands on the plane, and I have kept the thin shaving and let go of most of the resentment.
    >>  ............................................
    pt  Uma mulher me ensinou, com as mãos na plaina, e eu guardei a lasca fina e larguei quase todo o ressentimento.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.craft/2
    en  She taught me to listen to the plane instead of watching it. She was right and she was awful, both fully.
    >>  ............................................
    pt  Ela me ensinou a escutar a plaina em vez de olhar. Ela tinha razão e era terrível, as duas coisas inteiras.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Nobody notices when I get it right. They only ever see the three chairs that twisted over one winter.
    >>  ............................................
    pt  Ninguém repara quando eu acerto. Só veem as três cadeiras que empenaram num inverno.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.craft/2
    en  I learned by watching things I'd made go wrong slowly, years later, in somebody else's house.
    >>  ............................................
    pt  Aprendi vendo coisas que eu fiz darem errado devagar, anos depois, na casa de outra pessoa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood moves after you've finished with it. The whole trade is guessing which way, years ahead.
    >>  ............................................
    pt  A madeira se mexe depois que você termina. O ofício inteiro é adivinhar pra que lado, anos à frente.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.craft/2
    en  I was taught to plane by a woman who rapped my knuckles when the shaving came off thick. It worked.
    >>  ............................................
    pt  Aprendi a aplainar com uma mulher que batia nos meus dedos quando a lasca saía grossa. Funcionou.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood moves after you've finished with it. The whole trade is guessing which way, years ahead.
    >>  ............................................
    pt  A madeira se mexe depois que você termina. O ofício inteiro é adivinhar pra que lado, anos à frente.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.craft/2
    en  I was taught to plane by a woman who rapped my knuckles when the shaving came off thick. It worked.
    >>  ............................................
    pt  Aprendi a aplainar com uma mulher que batia nos meus dedos quando a lasca saía grossa. Funcionou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.craft/1
    en  Read the end grain and assume it will do the thing you'd least like. It is usually right.
    >>  ............................................
    pt  Leia a fibra da ponta e suponha que vai fazer o que você menos quer. Costuma acertar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.craft/2
    en  Heart side where it can't pull the joint apart. That's most of the trade in one sentence.
    >>  ............................................
    pt  Lado do cerne onde ele não abre o encaixe. É quase todo o ofício numa frase.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood seasons for two years and a woodworker for about twelve. Neither can be argued down.
    >>  ............................................
    pt  A madeira cura por dois anos e um marceneiro por uns doze. Nenhum dos dois se discute.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.craft/2
    en  Slowly, and by ruining stock I'd been too impatient to leave alone. The impatience went first.
    >>  ............................................
    pt  Devagar, e estragando madeira que eu fui impaciente demais pra deixar em paz. A impaciência foi primeiro.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.craft/1
    en  Read the end grain and assume it will do the thing you'd least like. It is usually right.
    >>  ............................................
    pt  Leia a fibra da ponta e suponha que vai fazer o que você menos quer. Costuma acertar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.craft/2
    en  Heart side where it can't pull the joint apart. That's most of the trade in one sentence.
    >>  ............................................
    pt  Lado do cerne onde ele não abre o encaixe. É quase todo o ofício numa frase.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood seasons for two years and a woodworker for about twelve. Neither can be argued down.
    >>  ............................................
    pt  A madeira cura por dois anos e um marceneiro por uns doze. Nenhum dos dois se discute.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.craft/2
    en  Slowly, and by ruining stock I'd been too impatient to leave alone. The impatience went first.
    >>  ............................................
    pt  Devagar, e estragando madeira que eu fui impaciente demais pra deixar em paz. A impaciência foi primeiro.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Guessing what a plank will do in ten years is a strange thing to be good at, and I am good at it.
    >>  ............................................
    pt  Adivinhar o que uma tábua vai fazer em dez anos é coisa estranha de se ser bom, e eu sou bom.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.craft/2
    en  Knuckles rapped for every thick shaving. I have a thin shaving and a lasting resentment to show for it.
    >>  ............................................
    pt  Dedos batidos por cada lasca grossa. Ficaram uma lasca fina e um ressentimento duradouro.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.craft/1
    en  Guessing what a plank will do in ten years is a strange thing to be good at, and I am good at it.
    >>  ............................................
    pt  Adivinhar o que uma tábua vai fazer em dez anos é coisa estranha de se ser bom, e eu sou bom.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.craft/2
    en  Knuckles rapped for every thick shaving. I have a thin shaving and a lasting resentment to show for it.
    >>  ............................................
    pt  Dedos batidos por cada lasca grossa. Ficaram uma lasca fina e um ressentimento duradouro.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.craft/1
    en  Wood seasons for two years and a woodworker for about twelve. Neither can be argued down.
    >>  ............................................
    pt  A madeira cura por dois anos e um marceneiro por uns doze. Nenhum dos dois se discute.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.craft/2
    en  Slowly, and by ruining stock I'd been too impatient to leave alone. The impatience went first.
    >>  ............................................
    pt  Devagar, e estragando madeira que eu fui impaciente demais pra deixar em paz. A impaciência foi primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.craft/1
    en  Nobody notices when I get it right. They only ever see the three chairs that twisted over one winter.
    >>  ............................................
    pt  Ninguém repara quando eu acerto. Só veem as três cadeiras que empenaram num inverno.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.craft/2
    en  I learned by watching things I'd made go wrong slowly, years later, in somebody else's house.
    >>  ............................................
    pt  Aprendi vendo coisas que eu fiz darem errado devagar, anos depois, na casa de outra pessoa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.craft/1
    en  Read the end grain and assume it will do the thing you'd least like. It is usually right.
    >>  ............................................
    pt  Leia a fibra da ponta e suponha que vai fazer o que você menos quer. Costuma acertar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.craft/2
    en  Heart side where it can't pull the joint apart. That's most of the trade in one sentence.
    >>  ............................................
    pt  Lado do cerne onde ele não abre o encaixe. É quase todo o ofício numa frase.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.craft/1
    en  Guessing what a plank will do in ten years is a strange thing to be good at, and I am good at it.
    >>  ............................................
    pt  Adivinhar o que uma tábua vai fazer em dez anos é coisa estranha de se ser bom, e eu sou bom.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.craft/2
    en  Knuckles rapped for every thick shaving. I have a thin shaving and a lasting resentment to show for it.
    >>  ............................................
    pt  Dedos batidos por cada lasca grossa. Ficaram uma lasca fina e um ressentimento duradouro.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.craft/1
    en  Guessing what a plank will do in ten years is a strange thing to be good at, and I am good at it.
    >>  ............................................
    pt  Adivinhar o que uma tábua vai fazer em dez anos é coisa estranha de se ser bom, e eu sou bom.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.craft/2
    en  Knuckles rapped for every thick shaving. I have a thin shaving and a lasting resentment to show for it.
    >>  ............................................
    pt  Dedos batidos por cada lasca grossa. Ficaram uma lasca fina e um ressentimento duradouro.
    >>  ............................................
```

</details>


**Outcome 146 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
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

```text
  dialogue.conversations.work.prof.woodworker.risk/1   [96 chars]
    en  A roof beam I cut holds up a room with a bed in it. I know which room and I do not walk past it.
    >>  ............................................
    pt  Uma viga que eu cortei sustenta um quarto com uma cama. Sei qual quarto e eu não passo por lá.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.risk/2   [93 chars]
    en  The saw doesn't care whose hand it is. I've two fingers that bend the wrong way to remind me.
    >>  ............................................
    pt  A serra não liga pra quem é a mão. Tenho dois dedos que dobram errado pra me lembrar.
    >>  ............................................
```


**Outcome 147 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
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

```text
  dialogue.conversations.work.prof.woodworker.village/1   [92 chars]
    en  Every roof, every door, every table people eat at. I don't sign them and I know all of them.
    >>  ............................................
    pt  Todo telhado, toda porta, toda mesa em que comem. Eu não assino e conheço todas.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.village/2   [88 chars]
    en  I made the cradle that four families have now borrowed in turn. It's on its ninth child.
    >>  ............................................
    pt  Fiz o berço que quatro famílias já emprestaram uma pra outra. Está na nona criança.
    >>  ............................................
```


**Outcome 148 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:woodworker"
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

```text
  dialogue.conversations.work.prof.woodworker.future/1   [86 chars]
    en  A staircase. A proper turned one. Nobody here needs one and I want to make one anyway.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém aqui precisa e eu quero fazer mesmo assim.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.future/2   [92 chars]
    en  The three doors older than me — I'd like to learn how they were made before they finally go.
    >>  ............................................
    pt  As três portas mais velhas que eu — queria aprender como foram feitas antes que se acabem.
    >>  ............................................
```


**Outcome 149 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
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

```text
  dialogue.conversations.work.prof.miner.task/1   [96 chars]
    en  Timbering the new drift. Four props today, and I'd rather set four than explain why I set three.
    >>  ............................................
    pt  Escorando a nova galeria. Quatro escoras hoje, e prefiro pôr quatro a explicar por que pus três.
    >>  ............................................
  dialogue.conversations.work.prof.miner.task/2   [98 chars]
    en  Hauling out. It's the dull half and it's the half that decides whether the good half was worth it.
    >>  ............................................
    pt  Tirando o material. É a metade maçante e é a que decide se a boa metade valeu.
    >>  ............................................
```


**Outcome 150 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
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

```text
  dialogue.conversations.work.prof.miner.craft/1   [103 chars]
    en  Reading rock. Where it's sound, where it's lying to you, and where it changed its mind a long time ago.
    >>  ............................................
    pt  Ler a rocha. Onde é firme, onde está mentindo, e onde ela mudou de ideia há muito tempo.
    >>  ............................................
  dialogue.conversations.work.prof.miner.craft/2   [104 chars]
    en  My father went down at twelve and I went down at eleven, and neither of us was taught anything in words.
    >>  ............................................
    pt  Meu pai desceu aos doze e eu desci aos onze, e nenhum de nós foi ensinado com palavras.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.miner.craft/1
    en  My father taught me, and nine years ago I set three props and only one of us walked out.
    >>  ............................................
    pt  Meu pai me ensinou, e nove anos atrás eu pus três escoras e só um de nós saiu andando.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.miner.craft/2
    en  You learn to read rock properly on the day it stops being a lesson. I'd not wish that lesson on anybody.
    >>  ............................................
    pt  Você aprende a ler rocha de verdade no dia em que deixa de ser lição. Eu não desejaria essa lição a ninguém.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.craft/1
    en  Two years of watching, and then twenty of my own. The rock has never once been in a hurry to teach me.
    >>  ............................................
    pt  Dois anos observando, e depois vinte meus. A rocha nunca teve pressa de me ensinar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.miner.craft/2
    en  Slowly, in the dark, one drift at a time. There isn't a version of this that goes faster and survives.
    >>  ............................................
    pt  Devagar, no escuro, uma galeria por vez. Não há versão disso que vá mais rápido e sobreviva.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.craft/1
    en  Reading rock. Where it's sound, where it's lying to you, and where it changed its mind long ago.
    >>  ............................................
    pt  Ler a rocha. Onde é firme, onde mente, e onde ela mudou de ideia há muito tempo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.miner.craft/2
    en  My father went down at twelve and I went down at eleven, and neither of us was taught anything in words.
    >>  ............................................
    pt  Meu pai desceu aos doze e eu aos onze, e nenhum de nós foi ensinado com palavras.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.craft/1
    en  Reading rock. Where it's sound, where it's lying to you, and where it changed its mind long ago.
    >>  ............................................
    pt  Ler a rocha. Onde é firme, onde mente, e onde ela mudou de ideia há muito tempo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.miner.craft/2
    en  My father went down at twelve and I went down at eleven, and neither of us was taught anything in words.
    >>  ............................................
    pt  Meu pai desceu aos doze e eu aos onze, e nenhum de nós foi ensinado com palavras.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.craft/1
    en  My father took me down and stayed quiet. That's the teaching — you go together and you watch his hands.
    >>  ............................................
    pt  Meu pai me levou pra baixo e ficou quieto. É esse o ensino — vocês descem juntos e você olha as mãos dele.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.miner.craft/2
    en  I'd teach it the same way and I'd talk more, because two years of silence is a long time for a boy.
    >>  ............................................
    pt  Eu ensinaria do mesmo jeito e falaria mais, porque dois anos de silêncio é muito tempo pra um menino.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.craft/1
    en  My father took me down and stayed quiet. That's the teaching — you go together and you watch his hands.
    >>  ............................................
    pt  Meu pai me levou pra baixo e ficou quieto. É esse o ensino — vocês descem juntos e você olha as mãos dele.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.miner.craft/2
    en  I'd teach it the same way and I'd talk more, because two years of silence is a long time for a boy.
    >>  ............................................
    pt  Eu ensinaria do mesmo jeito e falaria mais, porque dois anos de silêncio é muito tempo pra um menino.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.craft/1
    en  My father took me down and stayed quiet. That's the teaching — you go together and you watch his hands.
    >>  ............................................
    pt  Meu pai me levou pra baixo e ficou quieto. É esse o ensino — vocês descem juntos e você olha as mãos dele.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.miner.craft/2
    en  I'd teach it the same way and I'd talk more, because two years of silence is a long time for a boy.
    >>  ............................................
    pt  Eu ensinaria do mesmo jeito e falaria mais, porque dois anos de silêncio é muito tempo pra um menino.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.craft/1
    en  My father taught me, and nine years ago I set three props and only one of us walked out.
    >>  ............................................
    pt  Meu pai me ensinou, e nove anos atrás eu pus três escoras e só um de nós saiu andando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.miner.craft/2
    en  You learn to read rock properly on the day it stops being a lesson. I'd not wish that lesson on anybody.
    >>  ............................................
    pt  Você aprende a ler rocha de verdade no dia em que deixa de ser lição. Eu não desejaria essa lição a ninguém.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.craft/1
    en  Reading rock. Where it's sound, where it's lying to you, and where it changed its mind long ago.
    >>  ............................................
    pt  Ler a rocha. Onde é firme, onde mente, e onde ela mudou de ideia há muito tempo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.miner.craft/2
    en  My father went down at twelve and I went down at eleven, and neither of us was taught anything in words.
    >>  ............................................
    pt  Meu pai desceu aos doze e eu aos onze, e nenhum de nós foi ensinado com palavras.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.craft/1
    en  Reading rock. Where it's sound, where it's lying to you, and where it changed its mind long ago.
    >>  ............................................
    pt  Ler a rocha. Onde é firme, onde mente, e onde ela mudou de ideia há muito tempo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.miner.craft/2
    en  My father went down at twelve and I went down at eleven, and neither of us was taught anything in words.
    >>  ............................................
    pt  Meu pai desceu aos doze e eu aos onze, e nenhum de nós foi ensinado com palavras.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.craft/1
    en  You listen to the roof for a week before you decide anything. It talks if you're quiet enough.
    >>  ............................................
    pt  Você escuta o teto por uma semana antes de decidir. Ele fala se você ficar quieto o bastante.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.miner.craft/2
    en  It holds beautifully for eleven months and then it rains hard. That's the lie, and you learn it once.
    >>  ............................................
    pt  Segura lindamente por onze meses e aí chove forte. É essa a mentira, e você aprende uma vez.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.craft/1
    en  Two years of watching, and then twenty of my own. The rock has never once been in a hurry to teach me.
    >>  ............................................
    pt  Dois anos observando, e depois vinte meus. A rocha nunca teve pressa de me ensinar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.miner.craft/2
    en  Slowly, in the dark, one drift at a time. There isn't a version of this that goes faster and survives.
    >>  ............................................
    pt  Devagar, no escuro, uma galeria por vez. Não há versão disso que vá mais rápido e sobreviva.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.craft/1
    en  You listen to the roof for a week before you decide anything. It talks if you're quiet enough.
    >>  ............................................
    pt  Você escuta o teto por uma semana antes de decidir. Ele fala se você ficar quieto o bastante.
    >>  ............................................
  odd.dialogue.conversations.work.prof.miner.craft/2
    en  It holds beautifully for eleven months and then it rains hard. That's the lie, and you learn it once.
    >>  ............................................
    pt  Segura lindamente por onze meses e aí chove forte. É essa a mentira, e você aprende uma vez.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.craft/1
    en  Two years of watching, and then twenty of my own. The rock has never once been in a hurry to teach me.
    >>  ............................................
    pt  Dois anos observando, e depois vinte meus. A rocha nunca teve pressa de me ensinar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.miner.craft/2
    en  Slowly, in the dark, one drift at a time. There isn't a version of this that goes faster and survives.
    >>  ............................................
    pt  Devagar, no escuro, uma galeria por vez. Não há versão disso que vá mais rápido e sobreviva.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.craft/1
    en  Nobody said a word to me for two years. I watched where his hand went and where it didn't. Effective!
    >>  ............................................
    pt  Ninguém me disse uma palavra por dois anos. Eu via onde a mão dele ia e onde não ia. Eficaz!
    >>  ............................................
  peppy.dialogue.conversations.work.prof.miner.craft/2
    en  Rock lies. It looks solid and it's a lid, and there's a sound it makes that I hope you never hear.
    >>  ............................................
    pt  A rocha mente. Parece sólida e é uma tampa, e tem um som que eu espero que você nunca ouça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.craft/1
    en  Nobody said a word to me for two years. I watched where his hand went and where it didn't. Effective!
    >>  ............................................
    pt  Ninguém me disse uma palavra por dois anos. Eu via onde a mão dele ia e onde não ia. Eficaz!
    >>  ............................................
  playful.dialogue.conversations.work.prof.miner.craft/2
    en  Rock lies. It looks solid and it's a lid, and there's a sound it makes that I hope you never hear.
    >>  ............................................
    pt  A rocha mente. Parece sólida e é uma tampa, e tem um som que eu espero que você nunca ouça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.craft/1
    en  Two years of watching, and then twenty of my own. The rock has never once been in a hurry to teach me.
    >>  ............................................
    pt  Dois anos observando, e depois vinte meus. A rocha nunca teve pressa de me ensinar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.miner.craft/2
    en  Slowly, in the dark, one drift at a time. There isn't a version of this that goes faster and survives.
    >>  ............................................
    pt  Devagar, no escuro, uma galeria por vez. Não há versão disso que vá mais rápido e sobreviva.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.craft/1
    en  My father taught me, and nine years ago I set three props and only one of us walked out.
    >>  ............................................
    pt  Meu pai me ensinou, e nove anos atrás eu pus três escoras e só um de nós saiu andando.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.miner.craft/2
    en  You learn to read rock properly on the day it stops being a lesson. I'd not wish that lesson on anybody.
    >>  ............................................
    pt  Você aprende a ler rocha de verdade no dia em que deixa de ser lição. Eu não desejaria essa lição a ninguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.craft/1
    en  You listen to the roof for a week before you decide anything. It talks if you're quiet enough.
    >>  ............................................
    pt  Você escuta o teto por uma semana antes de decidir. Ele fala se você ficar quieto o bastante.
    >>  ............................................
  shy.dialogue.conversations.work.prof.miner.craft/2
    en  It holds beautifully for eleven months and then it rains hard. That's the lie, and you learn it once.
    >>  ............................................
    pt  Segura lindamente por onze meses e aí chove forte. É essa a mentira, e você aprende uma vez.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.craft/1
    en  Nobody said a word to me for two years. I watched where his hand went and where it didn't. Effective!
    >>  ............................................
    pt  Ninguém me disse uma palavra por dois anos. Eu via onde a mão dele ia e onde não ia. Eficaz!
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.miner.craft/2
    en  Rock lies. It looks solid and it's a lid, and there's a sound it makes that I hope you never hear.
    >>  ............................................
    pt  A rocha mente. Parece sólida e é uma tampa, e tem um som que eu espero que você nunca ouça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.craft/1
    en  Nobody said a word to me for two years. I watched where his hand went and where it didn't. Effective!
    >>  ............................................
    pt  Ninguém me disse uma palavra por dois anos. Eu via onde a mão dele ia e onde não ia. Eficaz!
    >>  ............................................
  witty.dialogue.conversations.work.prof.miner.craft/2
    en  Rock lies. It looks solid and it's a lid, and there's a sound it makes that I hope you never hear.
    >>  ............................................
    pt  A rocha mente. Parece sólida e é uma tampa, e tem um som que eu espero que você nunca ouça.
    >>  ............................................
```

</details>


**Outcome 151 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
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

```text
  dialogue.conversations.work.prof.miner.risk/1   [95 chars]
    en  There was a fall in the old drift nine years ago. Two of us went down and one of us walked out.
    >>  ............................................
    pt  Houve um desmoronamento na galeria velha nove anos atrás. Dois desceram e um saiu andando.
    >>  ............................................
  dialogue.conversations.work.prof.miner.risk/2   [94 chars]
    en  Everything below ground is on loan. You go in knowing it and you come out having forgotten it.
    >>  ............................................
    pt  Tudo lá embaixo é emprestado. Você entra sabendo e sai tendo esquecido.
    >>  ............................................
```


**Outcome 152 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
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

```text
  dialogue.conversations.work.prof.miner.village/1   [93 chars]
    en  Every nail in this place, every hinge, every pot. It all came up a ladder on somebody's back.
    >>  ............................................
    pt  Todo prego daqui, toda dobradiça, toda panela. Tudo subiu uma escada nas costas de alguém.
    >>  ............................................
  dialogue.conversations.work.prof.miner.village/2   [83 chars]
    en  The smith has never once asked where the ore comes from and I've never once minded.
    >>  ............................................
    pt  O ferreiro nunca perguntou de onde vem o minério e eu nunca me importei.
    >>  ............................................
```


**Outcome 153 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:miner"
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

```text
  dialogue.conversations.work.prof.miner.future/1   [83 chars]
    en  A second pair of hands below, so that a fall is a rescue instead of an inscription.
    >>  ............................................
    pt  Um segundo par de mãos lá embaixo, pra que um desmoronamento seja resgate e não inscrição.
    >>  ............................................
  dialogue.conversations.work.prof.miner.future/2   [105 chars]
    en  There's a seam east of the old drift I've never opened. I'm saving it, which is a strange thing to admit.
    >>  ............................................
    pt  Tem um veio a leste da galeria velha que eu nunca abri. Estou guardando, o que é estranho de admitir.
    >>  ............................................
```


**Outcome 154 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
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

```text
  dialogue.conversations.work.prof.florist.task/1   [87 chars]
    en  Cutting before the sun gets high. After ten they're just decorations that die in a day.
    >>  ............................................
    pt  Cortando antes do sol subir. Depois das dez são só enfeites que morrem num dia.
    >>  ............................................
  dialogue.conversations.work.prof.florist.task/2   [92 chars]
    en  Making up two arrangements: one for a wedding and one for a burial. Same shed, same morning.
    >>  ............................................
    pt  Montando dois arranjos: um pra um casamento e um pra um enterro. Mesmo galpão, mesma manhã.
    >>  ............................................
```


**Outcome 155 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
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

```text
  dialogue.conversations.work.prof.florist.craft/1   [97 chars]
    en  Everyone thinks it's arranging. It's growing, and drainage, and knowing which bed is cold in May.
    >>  ............................................
    pt  Todos acham que é arranjar. É cultivar, e drenagem, e saber qual canteiro é frio em maio.
    >>  ............................................
  dialogue.conversations.work.prof.florist.craft/2   [81 chars]
    en  I keep a book of what everybody's mother liked. It's the most useful thing I own.
    >>  ............................................
    pt  Guardo um caderno do que a mãe de cada um gostava. É a coisa mais útil que eu tenho.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.florist.craft/1
    en  I learned the beds from my mother and the rest from burials. That is a hard sentence and a true one.
    >>  ............................................
    pt  Aprendi os canteiros com minha mãe e o resto com enterros. É uma frase dura e verdadeira.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.florist.craft/2
    en  Two late frosts taught me more than nineteen years of good springs, and I'd have preferred the springs.
    >>  ............................................
    pt  Duas geadas tardias me ensinaram mais que dezenove anos de boas primaveras, e eu preferia as primaveras.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.craft/1
    en  Sow, wait, cut, wait. That's the lesson and the whole of the trade is repeating it until it's yours.
    >>  ............................................
    pt  Semear, esperar, cortar, esperar. É a lição e todo o ofício é repetir até ser seu.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.craft/2
    en  Nineteen years of the same months. The months are the teacher and they only teach once a year.
    >>  ............................................
    pt  Dezenove anos dos mesmos meses. Os meses são o professor e só ensinam uma vez por ano.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.craft/1
    en  It isn't arranging. It's growing, and drainage, and knowing which bed is cold in May.
    >>  ............................................
    pt  Não é arranjar. É cultivar, e drenagem, e saber qual canteiro é frio em maio.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.craft/2
    en  Cut on the slant, above a leaf pair, into water before the second step. Everything else is decoration.
    >>  ............................................
    pt  Corte no viés, acima de um par de folhas, na água antes do segundo passo. Todo o resto é enfeite.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.craft/1
    en  It isn't arranging. It's growing, and drainage, and knowing which bed is cold in May.
    >>  ............................................
    pt  Não é arranjar. É cultivar, e drenagem, e saber qual canteiro é frio em maio.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.craft/2
    en  Cut on the slant, above a leaf pair, into water before the second step. Everything else is decoration.
    >>  ............................................
    pt  Corte no viés, acima de um par de folhas, na água antes do segundo passo. Todo o resto é enfeite.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.craft/1
    en  My mother's beds, my mother's frost dates, and fifty-one families who told me what they wanted.
    >>  ............................................
    pt  Os canteiros da minha mãe, as datas de geada dela, e cinquenta e uma famílias que me disseram o que queriam.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.craft/2
    en  The trade is knowing people. What flower is only ever the second half of the question.
    >>  ............................................
    pt  O ofício é conhecer gente. Qual flor é sempre só a segunda metade da pergunta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.craft/1
    en  My mother's beds, my mother's frost dates, and fifty-one families who told me what they wanted.
    >>  ............................................
    pt  Os canteiros da minha mãe, as datas de geada dela, e cinquenta e uma famílias que me disseram o que queriam.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.craft/2
    en  The trade is knowing people. What flower is only ever the second half of the question.
    >>  ............................................
    pt  O ofício é conhecer gente. Qual flor é sempre só a segunda metade da pergunta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.craft/1
    en  My mother's beds, my mother's frost dates, and fifty-one families who told me what they wanted.
    >>  ............................................
    pt  Os canteiros da minha mãe, as datas de geada dela, e cinquenta e uma famílias que me disseram o que queriam.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.craft/2
    en  The trade is knowing people. What flower is only ever the second half of the question.
    >>  ............................................
    pt  O ofício é conhecer gente. Qual flor é sempre só a segunda metade da pergunta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.craft/1
    en  I learned the beds from my mother and the rest from burials. That is a hard sentence and a true one.
    >>  ............................................
    pt  Aprendi os canteiros com minha mãe e o resto com enterros. É uma frase dura e verdadeira.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.craft/2
    en  Two late frosts taught me more than nineteen years of good springs, and I'd have preferred the springs.
    >>  ............................................
    pt  Duas geadas tardias me ensinaram mais que dezenove anos de boas primaveras, e eu preferia as primaveras.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.craft/1
    en  It isn't arranging. It's growing, and drainage, and knowing which bed is cold in May.
    >>  ............................................
    pt  Não é arranjar. É cultivar, e drenagem, e saber qual canteiro é frio em maio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.craft/2
    en  Cut on the slant, above a leaf pair, into water before the second step. Everything else is decoration.
    >>  ............................................
    pt  Corte no viés, acima de um par de folhas, na água antes do segundo passo. Todo o resto é enfeite.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.craft/1
    en  It isn't arranging. It's growing, and drainage, and knowing which bed is cold in May.
    >>  ............................................
    pt  Não é arranjar. É cultivar, e drenagem, e saber qual canteiro é frio em maio.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.craft/2
    en  Cut on the slant, above a leaf pair, into water before the second step. Everything else is decoration.
    >>  ............................................
    pt  Corte no viés, acima de um par de folhas, na água antes do segundo passo. Todo o resto é enfeite.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.craft/1
    en  A stem stops drinking after ten. You cannot see it happen and you can see it three days later.
    >>  ............................................
    pt  Um talo para de beber depois das dez. Você não vê acontecer e vê três dias depois.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.craft/2
    en  I keep a book of what everybody's mother liked. It is the most useful thing I own and it is not about flowers.
    >>  ............................................
    pt  Guardo um caderno do que a mãe de cada um gostava. É a coisa mais útil que eu tenho e não é sobre flores.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.craft/1
    en  Sow, wait, cut, wait. That's the lesson and the whole of the trade is repeating it until it's yours.
    >>  ............................................
    pt  Semear, esperar, cortar, esperar. É a lição e todo o ofício é repetir até ser seu.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.craft/2
    en  Nineteen years of the same months. The months are the teacher and they only teach once a year.
    >>  ............................................
    pt  Dezenove anos dos mesmos meses. Os meses são o professor e só ensinam uma vez por ano.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.craft/1
    en  A stem stops drinking after ten. You cannot see it happen and you can see it three days later.
    >>  ............................................
    pt  Um talo para de beber depois das dez. Você não vê acontecer e vê três dias depois.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.craft/2
    en  I keep a book of what everybody's mother liked. It is the most useful thing I own and it is not about flowers.
    >>  ............................................
    pt  Guardo um caderno do que a mãe de cada um gostava. É a coisa mais útil que eu tenho e não é sobre flores.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.craft/1
    en  Sow, wait, cut, wait. That's the lesson and the whole of the trade is repeating it until it's yours.
    >>  ............................................
    pt  Semear, esperar, cortar, esperar. É a lição e todo o ofício é repetir até ser seu.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.craft/2
    en  Nineteen years of the same months. The months are the teacher and they only teach once a year.
    >>  ............................................
    pt  Dezenove anos dos mesmos meses. Os meses são o professor e só ensinam uma vez por ano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.craft/1
    en  Everyone thinks it's arranging! It's drainage. Drainage and frost dates and a great deal of mud.
    >>  ............................................
    pt  Todo mundo acha que é arranjar! É drenagem. Drenagem e datas de geada e muita lama.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.craft/2
    en  The north bed looks like the best of them and is a fortnight behind. It has fooled every apprentice I've had.
    >>  ............................................
    pt  O canteiro norte parece o melhor e está quinze dias atrasado. Já enganou todo aprendiz que eu tive.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.craft/1
    en  Everyone thinks it's arranging! It's drainage. Drainage and frost dates and a great deal of mud.
    >>  ............................................
    pt  Todo mundo acha que é arranjar! É drenagem. Drenagem e datas de geada e muita lama.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.craft/2
    en  The north bed looks like the best of them and is a fortnight behind. It has fooled every apprentice I've had.
    >>  ............................................
    pt  O canteiro norte parece o melhor e está quinze dias atrasado. Já enganou todo aprendiz que eu tive.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.craft/1
    en  Sow, wait, cut, wait. That's the lesson and the whole of the trade is repeating it until it's yours.
    >>  ............................................
    pt  Semear, esperar, cortar, esperar. É a lição e todo o ofício é repetir até ser seu.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.craft/2
    en  Nineteen years of the same months. The months are the teacher and they only teach once a year.
    >>  ............................................
    pt  Dezenove anos dos mesmos meses. Os meses são o professor e só ensinam uma vez por ano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.craft/1
    en  I learned the beds from my mother and the rest from burials. That is a hard sentence and a true one.
    >>  ............................................
    pt  Aprendi os canteiros com minha mãe e o resto com enterros. É uma frase dura e verdadeira.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.craft/2
    en  Two late frosts taught me more than nineteen years of good springs, and I'd have preferred the springs.
    >>  ............................................
    pt  Duas geadas tardias me ensinaram mais que dezenove anos de boas primaveras, e eu preferia as primaveras.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.craft/1
    en  A stem stops drinking after ten. You cannot see it happen and you can see it three days later.
    >>  ............................................
    pt  Um talo para de beber depois das dez. Você não vê acontecer e vê três dias depois.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.craft/2
    en  I keep a book of what everybody's mother liked. It is the most useful thing I own and it is not about flowers.
    >>  ............................................
    pt  Guardo um caderno do que a mãe de cada um gostava. É a coisa mais útil que eu tenho e não é sobre flores.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.craft/1
    en  Everyone thinks it's arranging! It's drainage. Drainage and frost dates and a great deal of mud.
    >>  ............................................
    pt  Todo mundo acha que é arranjar! É drenagem. Drenagem e datas de geada e muita lama.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.craft/2
    en  The north bed looks like the best of them and is a fortnight behind. It has fooled every apprentice I've had.
    >>  ............................................
    pt  O canteiro norte parece o melhor e está quinze dias atrasado. Já enganou todo aprendiz que eu tive.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.craft/1
    en  Everyone thinks it's arranging! It's drainage. Drainage and frost dates and a great deal of mud.
    >>  ............................................
    pt  Todo mundo acha que é arranjar! É drenagem. Drenagem e datas de geada e muita lama.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.craft/2
    en  The north bed looks like the best of them and is a fortnight behind. It has fooled every apprentice I've had.
    >>  ............................................
    pt  O canteiro norte parece o melhor e está quinze dias atrasado. Já enganou todo aprendiz que eu tive.
    >>  ............................................
```

</details>


**Outcome 156 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
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

```text
  dialogue.conversations.work.prof.florist.risk/1   [98 chars]
    en  A late frost takes a year in one night. I've had two and I've stopped pretending I sleep in April.
    >>  ............................................
    pt  Uma geada tardia leva um ano numa noite. Já tive duas e parei de fingir que durmo em abril.
    >>  ............................................
  dialogue.conversations.work.prof.florist.risk/2   [92 chars]
    en  I'm at every funeral in this valley. I'm not family and I'm always there and it accumulates.
    >>  ............................................
    pt  Estou em todo funeral deste vale. Não sou da família e estou sempre lá e isso acumula.
    >>  ............................................
```


**Outcome 157 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
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

```text
  dialogue.conversations.work.prof.florist.village/1   [94 chars]
    en  Every wedding and every burial in nineteen years came through my shed. Both ends of everybody.
    >>  ............................................
    pt  Todo casamento e todo enterro em dezenove anos passou pelo meu galpão. As duas pontas de cada um.
    >>  ............................................
  dialogue.conversations.work.prof.florist.village/2   [104 chars]
    en  The beds are the only colour this place has between November and March. I hear about it if they're bare.
    >>  ............................................
    pt  Os canteiros são a única cor daqui entre novembro e março. Eu escuto se ficarem vazios.
    >>  ............................................
```


**Outcome 158 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:florist"
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

```text
  dialogue.conversations.work.prof.florist.future/1   [100 chars]
    en  A glasshouse. Then February has colour by choice instead of by luck, and April stops frightening me.
    >>  ............................................
    pt  Uma estufa. Aí fevereiro tem cor por escolha e não por sorte, e abril para de me assustar.
    >>  ............................................
  dialogue.conversations.work.prof.florist.future/2   [87 chars]
    en  I'd like somebody to take the book. Not the beds — the book. The beds anyone can learn.
    >>  ............................................
    pt  Queria que alguém ficasse com o caderno. Não os canteiros — o caderno. Os canteiros qualquer um aprende.
    >>  ............................................
```


**Outcome 159 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
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

```text
  dialogue.conversations.work.prof.hunter.task/1   [91 chars]
    en  Out before dawn and back with nothing. That's four days in a row and I've started counting.
    >>  ............................................
    pt  Saio antes do amanhecer e volto sem nada. São quatro dias seguidos e eu comecei a contar.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.task/2   [102 chars]
    en  Checking the snare line. Twenty-two of them and I'll walk the whole line whether it's worth it or not.
    >>  ............................................
    pt  Conferindo a linha de armadilhas. Vinte e duas e eu ando a linha toda valendo a pena ou não.
    >>  ............................................
```


**Outcome 160 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
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

```text
  dialogue.conversations.work.prof.hunter.craft/1   [107 chars]
    en  Tracking is arithmetic. Depth, spacing, and what the ground was doing yesterday. Nothing mystical about it.
    >>  ............................................
    pt  Rastrear é aritmética. Profundidade, espaçamento, e o que o chão fazia ontem. Nada de místico.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.craft/2   [88 chars]
    en  I learned by following my aunt for two years and being told nothing except 'look again'.
    >>  ............................................
    pt  Aprendi seguindo minha tia por dois anos e sem ouvir nada além de 'olhe de novo'.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter.craft/1
    en  My aunt taught me. She's dead, and I still hear 'look again' on the mornings I'm hurrying.
    >>  ............................................
    pt  Minha tia me ensinou. Ela morreu, e eu ainda ouço 'olhe de novo' nas manhãs em que eu me apresso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter.craft/2
    en  You learn it by getting it wrong when something is depending on you being right. That's a hard classroom.
    >>  ............................................
    pt  Você aprende errando quando algo depende de você acertar. É uma sala de aula dura.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years behind my aunt, and then nineteen of my own. Neither number could have been smaller.
    >>  ............................................
    pt  Dois anos atrás da minha tia, e depois dezenove meus. Nenhum dos números podia ser menor.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.craft/2
    en  Slowly. The ground only teaches you at the speed the weather changes it, which is not fast.
    >>  ............................................
    pt  Devagar. O chão só te ensina na velocidade em que o tempo o muda, que não é rápida.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.craft/1
    en  Depth, spacing, and what the ground was doing yesterday. It is arithmetic and I will not dress it up.
    >>  ............................................
    pt  Profundidade, espaçamento, e o que o chão fazia ontem. É aritmética e eu não vou enfeitar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.craft/2
    en  My aunt taught me by saying nothing except 'look again' for two years. It worked, unfortunately.
    >>  ............................................
    pt  Minha tia me ensinou dizendo só 'olhe de novo' por dois anos. Funcionou, infelizmente.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.craft/1
    en  Depth, spacing, and what the ground was doing yesterday. It is arithmetic and I will not dress it up.
    >>  ............................................
    pt  Profundidade, espaçamento, e o que o chão fazia ontem. É aritmética e eu não vou enfeitar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.craft/2
    en  My aunt taught me by saying nothing except 'look again' for two years. It worked, unfortunately.
    >>  ............................................
    pt  Minha tia me ensinou dizendo só 'olhe de novo' por dois anos. Funcionou, infelizmente.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.craft/1
    en  Following my aunt for two years. She fed me, walked ahead of me, and told me almost nothing.
    >>  ............................................
    pt  Seguindo minha tia por dois anos. Ela me alimentava, andava na frente, e não me contava quase nada.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.craft/2
    en  I learned it from her and I'd teach it the same way, except I'd talk more, because she never did.
    >>  ............................................
    pt  Aprendi com ela e eu ensinaria do mesmo jeito, só que falando mais, porque ela nunca falava.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.craft/1
    en  Following my aunt for two years. She fed me, walked ahead of me, and told me almost nothing.
    >>  ............................................
    pt  Seguindo minha tia por dois anos. Ela me alimentava, andava na frente, e não me contava quase nada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.craft/2
    en  I learned it from her and I'd teach it the same way, except I'd talk more, because she never did.
    >>  ............................................
    pt  Aprendi com ela e eu ensinaria do mesmo jeito, só que falando mais, porque ela nunca falava.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.craft/1
    en  Following my aunt for two years. She fed me, walked ahead of me, and told me almost nothing.
    >>  ............................................
    pt  Seguindo minha tia por dois anos. Ela me alimentava, andava na frente, e não me contava quase nada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.craft/2
    en  I learned it from her and I'd teach it the same way, except I'd talk more, because she never did.
    >>  ............................................
    pt  Aprendi com ela e eu ensinaria do mesmo jeito, só que falando mais, porque ela nunca falava.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.craft/1
    en  My aunt taught me. She's dead, and I still hear 'look again' on the mornings I'm hurrying.
    >>  ............................................
    pt  Minha tia me ensinou. Ela morreu, e eu ainda ouço 'olhe de novo' nas manhãs em que eu me apresso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.craft/2
    en  You learn it by getting it wrong when something is depending on you being right. That's a hard classroom.
    >>  ............................................
    pt  Você aprende errando quando algo depende de você acertar. É uma sala de aula dura.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.craft/1
    en  Depth, spacing, and what the ground was doing yesterday. It is arithmetic and I will not dress it up.
    >>  ............................................
    pt  Profundidade, espaçamento, e o que o chão fazia ontem. É aritmética e eu não vou enfeitar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.craft/2
    en  My aunt taught me by saying nothing except 'look again' for two years. It worked, unfortunately.
    >>  ............................................
    pt  Minha tia me ensinou dizendo só 'olhe de novo' por dois anos. Funcionou, infelizmente.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.craft/1
    en  Depth, spacing, and what the ground was doing yesterday. It is arithmetic and I will not dress it up.
    >>  ............................................
    pt  Profundidade, espaçamento, e o que o chão fazia ontem. É aritmética e eu não vou enfeitar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.craft/2
    en  My aunt taught me by saying nothing except 'look again' for two years. It worked, unfortunately.
    >>  ............................................
    pt  Minha tia me ensinou dizendo só 'olhe de novo' por dois anos. Funcionou, infelizmente.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.craft/1
    en  'Look again' taught me that I was deciding what I saw before I'd finished seeing it.
    >>  ............................................
    pt  'Olhe de novo' me ensinou que eu decidia o que via antes de terminar de ver.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.craft/2
    en  Distrust the first read. That is the whole of the craft in four words and it took two years.
    >>  ............................................
    pt  Desconfie da primeira leitura. É todo o ofício em quatro palavras e levou dois anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years behind my aunt, and then nineteen of my own. Neither number could have been smaller.
    >>  ............................................
    pt  Dois anos atrás da minha tia, e depois dezenove meus. Nenhum dos números podia ser menor.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.craft/2
    en  Slowly. The ground only teaches you at the speed the weather changes it, which is not fast.
    >>  ............................................
    pt  Devagar. O chão só te ensina na velocidade em que o tempo o muda, que não é rápida.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.craft/1
    en  'Look again' taught me that I was deciding what I saw before I'd finished seeing it.
    >>  ............................................
    pt  'Olhe de novo' me ensinou que eu decidia o que via antes de terminar de ver.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.craft/2
    en  Distrust the first read. That is the whole of the craft in four words and it took two years.
    >>  ............................................
    pt  Desconfie da primeira leitura. É todo o ofício em quatro palavras e levou dois anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years behind my aunt, and then nineteen of my own. Neither number could have been smaller.
    >>  ............................................
    pt  Dois anos atrás da minha tia, e depois dezenove meus. Nenhum dos números podia ser menor.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.craft/2
    en  Slowly. The ground only teaches you at the speed the weather changes it, which is not fast.
    >>  ............................................
    pt  Devagar. O chão só te ensina na velocidade em que o tempo o muda, que não é rápida.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years of 'look again' and nothing else. My aunt had a very economical teaching style.
    >>  ............................................
    pt  Dois anos de 'olhe de novo' e nada mais. Minha tia tinha um estilo de ensino muito econômico.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.craft/2
    en  Everyone dresses tracking up as instinct. It's spacing and depth, and it's more fun once you admit that.
    >>  ............................................
    pt  Todo mundo enfeita rastrear como instinto. É espaçamento e profundidade, e é mais divertido quando se admite.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years of 'look again' and nothing else. My aunt had a very economical teaching style.
    >>  ............................................
    pt  Dois anos de 'olhe de novo' e nada mais. Minha tia tinha um estilo de ensino muito econômico.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.craft/2
    en  Everyone dresses tracking up as instinct. It's spacing and depth, and it's more fun once you admit that.
    >>  ............................................
    pt  Todo mundo enfeita rastrear como instinto. É espaçamento e profundidade, e é mais divertido quando se admite.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years behind my aunt, and then nineteen of my own. Neither number could have been smaller.
    >>  ............................................
    pt  Dois anos atrás da minha tia, e depois dezenove meus. Nenhum dos números podia ser menor.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.craft/2
    en  Slowly. The ground only teaches you at the speed the weather changes it, which is not fast.
    >>  ............................................
    pt  Devagar. O chão só te ensina na velocidade em que o tempo o muda, que não é rápida.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.craft/1
    en  My aunt taught me. She's dead, and I still hear 'look again' on the mornings I'm hurrying.
    >>  ............................................
    pt  Minha tia me ensinou. Ela morreu, e eu ainda ouço 'olhe de novo' nas manhãs em que eu me apresso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.craft/2
    en  You learn it by getting it wrong when something is depending on you being right. That's a hard classroom.
    >>  ............................................
    pt  Você aprende errando quando algo depende de você acertar. É uma sala de aula dura.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.craft/1
    en  'Look again' taught me that I was deciding what I saw before I'd finished seeing it.
    >>  ............................................
    pt  'Olhe de novo' me ensinou que eu decidia o que via antes de terminar de ver.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.craft/2
    en  Distrust the first read. That is the whole of the craft in four words and it took two years.
    >>  ............................................
    pt  Desconfie da primeira leitura. É todo o ofício em quatro palavras e levou dois anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years of 'look again' and nothing else. My aunt had a very economical teaching style.
    >>  ............................................
    pt  Dois anos de 'olhe de novo' e nada mais. Minha tia tinha um estilo de ensino muito econômico.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.craft/2
    en  Everyone dresses tracking up as instinct. It's spacing and depth, and it's more fun once you admit that.
    >>  ............................................
    pt  Todo mundo enfeita rastrear como instinto. É espaçamento e profundidade, e é mais divertido quando se admite.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.craft/1
    en  Two years of 'look again' and nothing else. My aunt had a very economical teaching style.
    >>  ............................................
    pt  Dois anos de 'olhe de novo' e nada mais. Minha tia tinha um estilo de ensino muito econômico.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.craft/2
    en  Everyone dresses tracking up as instinct. It's spacing and depth, and it's more fun once you admit that.
    >>  ............................................
    pt  Todo mundo enfeita rastrear como instinto. É espaçamento e profundidade, e é mais divertido quando se admite.
    >>  ............................................
```

</details>


**Outcome 161 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
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

```text
  dialogue.conversations.work.prof.hunter.risk/1   [99 chars]
    en  I take what the valley can spare and no more. Get that wrong for three years and there's no fourth.
    >>  ............................................
    pt  Eu tiro o que o vale pode dispensar e nada além. Erre isso por três anos e não tem um quarto.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.risk/2   [97 chars]
    en  I'm out alone, three hours from anybody, most days of my life. A turned ankle is a serious event.
    >>  ............................................
    pt  Estou sozinho, a três horas de qualquer um, quase todo dia da minha vida. Um tornozelo torcido é sério.
    >>  ............................................
```


**Outcome 162 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
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

```text
  dialogue.conversations.work.prof.hunter.village/1   [101 chars]
    en  Meat through the lean months, and I decide how lean they get. That framing is mine and nobody else's.
    >>  ............................................
    pt  Carne nos meses magros, e eu decido quão magros ficam. Essa forma de ver é minha e de mais ninguém.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.village/2   [97 chars]
    en  The valley is still full of deer after nineteen years of me. That's the number I'd want read out.
    >>  ............................................
    pt  O vale ainda está cheio de cervos depois de dezenove anos de mim. É esse o número que eu queria lido.
    >>  ............................................
```


**Outcome 163 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:hunter"
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

```text
  dialogue.conversations.work.prof.hunter.future/1   [102 chars]
    en  I want the count written down somewhere it survives me. Otherwise the next hunter starts from nothing.
    >>  ............................................
    pt  Quero a contagem escrita em algum lugar que me sobreviva. Senão o próximo caçador começa do zero.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.future/2   [91 chars]
    en  One day my knees go and I'd like there to be somebody walking the line before that happens.
    >>  ............................................
    pt  Um dia meus joelhos vão e eu queria que alguém já andasse a linha antes disso.
    >>  ............................................
```


**Outcome 164 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
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

```text
  dialogue.conversations.work.prof.engineer.task/1   [97 chars]
    en  The mill gear has a tooth going and I'm deciding whether to replace one tooth or the whole wheel.
    >>  ............................................
    pt  A engrenagem do moinho tem um dente indo e eu decido se troco um dente ou a roda inteira.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.task/2   [97 chars]
    en  Measuring the fall of the race. Somebody dug it by eye ninety years ago and it very nearly works.
    >>  ............................................
    pt  Medindo a queda do canal. Alguém cavou no olho há noventa anos e quase funciona.
    >>  ............................................
```


**Outcome 165 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
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

```text
  dialogue.conversations.work.prof.engineer.craft/1   [107 chars]
    en  I take things apart. That's the entire method and I've been doing it since I was six and in trouble for it.
    >>  ............................................
    pt  Eu desmonto coisas. É todo o método e eu faço isso desde os seis anos e já me meti em encrenca por isso.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.craft/2   [99 chars]
    en  The mill, the bridge winch, the well pulley. Nobody taught me any of the three; I opened all three.
    >>  ............................................
    pt  O moinho, o guincho da ponte, a roldana do poço. Ninguém me ensinou nenhum dos três; eu abri os três.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.engineer.craft/1
    en  Alone, mostly. There's no one within four valleys to check my thinking against, and I feel that.
    >>  ............................................
    pt  Sozinho, principalmente. Não tem ninguém em quatro vales pra conferir meu raciocínio, e eu sinto isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned by breaking my grandmother's clock. She left it to me anyway, and I've not recovered from that.
    >>  ............................................
    pt  Aprendi quebrando o relógio da minha avó. Ela me deixou de herança mesmo assim, e eu não me recuperei.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.craft/1
    en  Slowly, one machine at a time, over about twenty years. There was never a rush and there is no rush now.
    >>  ............................................
    pt  Devagar, uma máquina por vez, ao longo de uns vinte anos. Nunca houve pressa e não há pressa agora.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.craft/2
    en  You listen to a machine for a week before you touch it. That week is where all the learning happens.
    >>  ............................................
    pt  Você escuta uma máquina por uma semana antes de tocar. Nessa semana é que todo o aprendizado acontece.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.craft/1
    en  I open things that are working. That is the method, and it is why I know what nobody else here knows.
    >>  ............................................
    pt  Eu abro coisas que estão funcionando. É o método, e é por isso que eu sei o que mais ninguém aqui sabe.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.craft/2
    en  Nobody taught me the mill, the winch or the well pulley. I opened all three and I put all three back.
    >>  ............................................
    pt  Ninguém me ensinou o moinho, o guincho nem a roldana. Abri os três e montei os três de volta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.craft/1
    en  I open things that are working. That is the method, and it is why I know what nobody else here knows.
    >>  ............................................
    pt  Eu abro coisas que estão funcionando. É o método, e é por isso que eu sei o que mais ninguém aqui sabe.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.craft/2
    en  Nobody taught me the mill, the winch or the well pulley. I opened all three and I put all three back.
    >>  ............................................
    pt  Ninguém me ensinou o moinho, o guincho nem a roldana. Abri os três e montei os três de volta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.craft/1
    en  Nobody taught me — so I'd teach anybody who asked, and I've been waiting a while for somebody to ask.
    >>  ............................................
    pt  Ninguém me ensinou — então eu ensinaria qualquer um que pedisse, e faz tempo que eu espero alguém pedir.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned it in other people's cellars, with them holding the lamp and telling me what it used to sound like.
    >>  ............................................
    pt  Aprendi no porão dos outros, com eles segurando a lamparina e contando como soava antes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.craft/1
    en  Nobody taught me — so I'd teach anybody who asked, and I've been waiting a while for somebody to ask.
    >>  ............................................
    pt  Ninguém me ensinou — então eu ensinaria qualquer um que pedisse, e faz tempo que eu espero alguém pedir.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned it in other people's cellars, with them holding the lamp and telling me what it used to sound like.
    >>  ............................................
    pt  Aprendi no porão dos outros, com eles segurando a lamparina e contando como soava antes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.craft/1
    en  Nobody taught me — so I'd teach anybody who asked, and I've been waiting a while for somebody to ask.
    >>  ............................................
    pt  Ninguém me ensinou — então eu ensinaria qualquer um que pedisse, e faz tempo que eu espero alguém pedir.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned it in other people's cellars, with them holding the lamp and telling me what it used to sound like.
    >>  ............................................
    pt  Aprendi no porão dos outros, com eles segurando a lamparina e contando como soava antes.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.craft/1
    en  Alone, mostly. There's no one within four valleys to check my thinking against, and I feel that.
    >>  ............................................
    pt  Sozinho, principalmente. Não tem ninguém em quatro vales pra conferir meu raciocínio, e eu sinto isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned by breaking my grandmother's clock. She left it to me anyway, and I've not recovered from that.
    >>  ............................................
    pt  Aprendi quebrando o relógio da minha avó. Ela me deixou de herança mesmo assim, e eu não me recuperei.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.craft/1
    en  I open things that are working. That is the method, and it is why I know what nobody else here knows.
    >>  ............................................
    pt  Eu abro coisas que estão funcionando. É o método, e é por isso que eu sei o que mais ninguém aqui sabe.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.craft/2
    en  Nobody taught me the mill, the winch or the well pulley. I opened all three and I put all three back.
    >>  ............................................
    pt  Ninguém me ensinou o moinho, o guincho nem a roldana. Abri os três e montei os três de volta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.craft/1
    en  I open things that are working. That is the method, and it is why I know what nobody else here knows.
    >>  ............................................
    pt  Eu abro coisas que estão funcionando. É o método, e é por isso que eu sei o que mais ninguém aqui sabe.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.craft/2
    en  Nobody taught me the mill, the winch or the well pulley. I opened all three and I put all three back.
    >>  ............................................
    pt  Ninguém me ensinou o moinho, o guincho nem a roldana. Abri os três e montei os três de volta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.craft/1
    en  I open a thing and I don't put it back until I can say why every piece is where it is.
    >>  ............................................
    pt  Eu abro uma coisa e não monto de volta até conseguir dizer por que cada peça está onde está.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.craft/2
    en  There's a clock in this valley that has been wrong since I was seven. That was the first lesson.
    >>  ............................................
    pt  Tem um relógio neste vale errado desde os meus sete anos. Foi a primeira lição.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.craft/1
    en  Slowly, one machine at a time, over about twenty years. There was never a rush and there is no rush now.
    >>  ............................................
    pt  Devagar, uma máquina por vez, ao longo de uns vinte anos. Nunca houve pressa e não há pressa agora.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.craft/2
    en  You listen to a machine for a week before you touch it. That week is where all the learning happens.
    >>  ............................................
    pt  Você escuta uma máquina por uma semana antes de tocar. Nessa semana é que todo o aprendizado acontece.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.craft/1
    en  I open a thing and I don't put it back until I can say why every piece is where it is.
    >>  ............................................
    pt  Eu abro uma coisa e não monto de volta até conseguir dizer por que cada peça está onde está.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.craft/2
    en  There's a clock in this valley that has been wrong since I was seven. That was the first lesson.
    >>  ............................................
    pt  Tem um relógio neste vale errado desde os meus sete anos. Foi a primeira lição.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.craft/1
    en  Slowly, one machine at a time, over about twenty years. There was never a rush and there is no rush now.
    >>  ............................................
    pt  Devagar, uma máquina por vez, ao longo de uns vinte anos. Nunca houve pressa e não há pressa agora.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.craft/2
    en  You listen to a machine for a week before you touch it. That week is where all the learning happens.
    >>  ............................................
    pt  Você escuta uma máquina por uma semana antes de tocar. Nessa semana é que todo o aprendizado acontece.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.craft/1
    en  I've been taking things apart since I was six and in trouble for it. Nothing has changed but the size.
    >>  ............................................
    pt  Desmonto coisas desde os seis anos e já me meti em encrenca por isso. Só mudou o tamanho.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.craft/2
    en  Self-taught, with about nine spectacular failures nobody counts. I'd like the count to include those.
    >>  ............................................
    pt  Autodidata, com umas nove falhas espetaculares que ninguém conta. Eu queria que a conta as incluísse.
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.craft/1
    en  I've been taking things apart since I was six and in trouble for it. Nothing has changed but the size.
    >>  ............................................
    pt  Desmonto coisas desde os seis anos e já me meti em encrenca por isso. Só mudou o tamanho.
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.craft/2
    en  Self-taught, with about nine spectacular failures nobody counts. I'd like the count to include those.
    >>  ............................................
    pt  Autodidata, com umas nove falhas espetaculares que ninguém conta. Eu queria que a conta as incluísse.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.craft/1
    en  Slowly, one machine at a time, over about twenty years. There was never a rush and there is no rush now.
    >>  ............................................
    pt  Devagar, uma máquina por vez, ao longo de uns vinte anos. Nunca houve pressa e não há pressa agora.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.craft/2
    en  You listen to a machine for a week before you touch it. That week is where all the learning happens.
    >>  ............................................
    pt  Você escuta uma máquina por uma semana antes de tocar. Nessa semana é que todo o aprendizado acontece.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.craft/1
    en  Alone, mostly. There's no one within four valleys to check my thinking against, and I feel that.
    >>  ............................................
    pt  Sozinho, principalmente. Não tem ninguém em quatro vales pra conferir meu raciocínio, e eu sinto isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.craft/2
    en  I learned by breaking my grandmother's clock. She left it to me anyway, and I've not recovered from that.
    >>  ............................................
    pt  Aprendi quebrando o relógio da minha avó. Ela me deixou de herança mesmo assim, e eu não me recuperei.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.craft/1
    en  I open a thing and I don't put it back until I can say why every piece is where it is.
    >>  ............................................
    pt  Eu abro uma coisa e não monto de volta até conseguir dizer por que cada peça está onde está.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.craft/2
    en  There's a clock in this valley that has been wrong since I was seven. That was the first lesson.
    >>  ............................................
    pt  Tem um relógio neste vale errado desde os meus sete anos. Foi a primeira lição.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.craft/1
    en  I've been taking things apart since I was six and in trouble for it. Nothing has changed but the size.
    >>  ............................................
    pt  Desmonto coisas desde os seis anos e já me meti em encrenca por isso. Só mudou o tamanho.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.craft/2
    en  Self-taught, with about nine spectacular failures nobody counts. I'd like the count to include those.
    >>  ............................................
    pt  Autodidata, com umas nove falhas espetaculares que ninguém conta. Eu queria que a conta as incluísse.
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.craft/1
    en  I've been taking things apart since I was six and in trouble for it. Nothing has changed but the size.
    >>  ............................................
    pt  Desmonto coisas desde os seis anos e já me meti em encrenca por isso. Só mudou o tamanho.
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.craft/2
    en  Self-taught, with about nine spectacular failures nobody counts. I'd like the count to include those.
    >>  ............................................
    pt  Autodidata, com umas nove falhas espetaculares que ninguém conta. Eu queria que a conta as incluísse.
    >>  ............................................
```

</details>


**Outcome 166 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
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

```text
  dialogue.conversations.work.prof.engineer.risk/1   [98 chars]
    en  A winch that fails drops whatever it was holding onto whoever was underneath. I check mine weekly.
    >>  ............................................
    pt  Um guincho que falha derruba o que segurava em quem estava embaixo. Eu confiro o meu toda semana.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.risk/2   [98 chars]
    en  Everyone here calls me when something breaks and nobody calls me before. That's the actual danger.
    >>  ............................................
    pt  Todos me chamam quando algo quebra e ninguém me chama antes. É esse o perigo de verdade.
    >>  ............................................
```


**Outcome 167 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
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

```text
  dialogue.conversations.work.prof.engineer.village/1   [106 chars]
    en  The mill turns, the bridge lifts, the well draws. Three machines and this place stops without any of them.
    >>  ............................................
    pt  O moinho gira, a ponte levanta, o poço puxa. Três máquinas e este lugar para sem qualquer uma.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.village/2   [86 chars]
    en  I saved the mill in the flood year by shutting the race before anyone agreed I should.
    >>  ............................................
    pt  Salvei o moinho no ano da cheia fechando o canal antes de alguém concordar.
    >>  ............................................
```


**Outcome 168 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:engineer"
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

```text
  dialogue.conversations.work.prof.engineer.future/1   [96 chars]
    en  The race, dug properly. A third more water for the same river, and the mill would run in August.
    >>  ............................................
    pt  O canal, cavado direito. Um terço a mais de água pro mesmo rio, e o moinho rodaria em agosto.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.future/2   [110 chars]
    en  I'd like an apprentice who breaks things. Everyone sends me the careful ones and the careful ones are useless.
    >>  ............................................
    pt  Queria um aprendiz que quebre coisas. Todos me mandam os cuidadosos e os cuidadosos não servem.
    >>  ............................................
```


**Outcome 169 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
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

```text
  dialogue.conversations.work.prof.oceanographer.task/1   [99 chars]
    en  Reading the tide board. Nineteen years of marks on one post, and this month sits above all of them.
    >>  ............................................
    pt  Lendo o marégrafo. Dezenove anos de marcas num poste, e este mês está acima de todas.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.task/2   [102 chars]
    en  Walking the shore counting what washed up. It's a dull census and it tells me what the water is doing.
    >>  ............................................
    pt  Andando a praia contando o que deu à costa. É um censo maçante e me diz o que a água está fazendo.
    >>  ............................................
```


**Outcome 170 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
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

```text
  dialogue.conversations.work.prof.oceanographer.craft/1   [95 chars]
    en  It's counting and waiting. Nineteen years of both, and the first eleven told me almost nothing.
    >>  ............................................
    pt  É contar e esperar. Dezenove anos dos dois, e os onze primeiros não me disseram quase nada.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.craft/2   [99 chars]
    en  There was no one to teach me. I read three books wrong and then corrected myself against the water.
    >>  ............................................
    pt  Não havia ninguém pra me ensinar. Li três livros errado e depois me corrigi contra a água.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.oceanographer.craft/1
    en  I read the books wrong and it took me six years to be willing to say so out loud.
    >>  ............................................
    pt  Li os livros errado e levei seis anos pra conseguir admitir em voz alta.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Eleven years of marks that meant nothing. I kept going because I could not afford them to have meant nothing.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada. Continuei porque não podia deixar que não significassem nada.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nineteen years, and the trade only started at the twelfth. There is no shorter apprenticeship in it.
    >>  ............................................
    pt  Dezenove anos, e o ofício só começou no décimo segundo. Não há aprendizado mais curto nisso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Counting and waiting, and the waiting is the larger half by a long way.
    >>  ............................................
    pt  Contar e esperar, e esperar é a metade maior de longe.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Counting and waiting. Nineteen years of both, and the first eleven told me almost nothing.
    >>  ............................................
    pt  Contar e esperar. Dezenove anos dos dois, e os onze primeiros não me disseram quase nada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.oceanographer.craft/2
    en  There was nobody to teach me. I read three books wrong and corrected myself against the water.
    >>  ............................................
    pt  Não havia ninguém pra me ensinar. Li três livros errado e me corrigi contra a água.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Counting and waiting. Nineteen years of both, and the first eleven told me almost nothing.
    >>  ............................................
    pt  Contar e esperar. Dezenove anos dos dois, e os onze primeiros não me disseram quase nada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.oceanographer.craft/2
    en  There was nobody to teach me. I read three books wrong and corrected myself against the water.
    >>  ............................................
    pt  Não havia ninguém pra me ensinar. Li três livros errado e me corrigi contra a água.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nobody taught me, so I write to a scribe four valleys away who is doing the same on her coast.
    >>  ............................................
    pt  Ninguém me ensinou, então eu escrevo pra uma escriba a quatro vales que faz o mesmo na costa dela.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.oceanographer.craft/2
    en  The fishermen taught me more than the books did, and not one of them knows they were teaching.
    >>  ............................................
    pt  Os pescadores me ensinaram mais que os livros, e nenhum deles sabe que estava ensinando.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nobody taught me, so I write to a scribe four valleys away who is doing the same on her coast.
    >>  ............................................
    pt  Ninguém me ensinou, então eu escrevo pra uma escriba a quatro vales que faz o mesmo na costa dela.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.oceanographer.craft/2
    en  The fishermen taught me more than the books did, and not one of them knows they were teaching.
    >>  ............................................
    pt  Os pescadores me ensinaram mais que os livros, e nenhum deles sabe que estava ensinando.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nobody taught me, so I write to a scribe four valleys away who is doing the same on her coast.
    >>  ............................................
    pt  Ninguém me ensinou, então eu escrevo pra uma escriba a quatro vales que faz o mesmo na costa dela.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.oceanographer.craft/2
    en  The fishermen taught me more than the books did, and not one of them knows they were teaching.
    >>  ............................................
    pt  Os pescadores me ensinaram mais que os livros, e nenhum deles sabe que estava ensinando.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  I read the books wrong and it took me six years to be willing to say so out loud.
    >>  ............................................
    pt  Li os livros errado e levei seis anos pra conseguir admitir em voz alta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Eleven years of marks that meant nothing. I kept going because I could not afford them to have meant nothing.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada. Continuei porque não podia deixar que não significassem nada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Counting and waiting. Nineteen years of both, and the first eleven told me almost nothing.
    >>  ............................................
    pt  Contar e esperar. Dezenove anos dos dois, e os onze primeiros não me disseram quase nada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  There was nobody to teach me. I read three books wrong and corrected myself against the water.
    >>  ............................................
    pt  Não havia ninguém pra me ensinar. Li três livros errado e me corrigi contra a água.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Counting and waiting. Nineteen years of both, and the first eleven told me almost nothing.
    >>  ............................................
    pt  Contar e esperar. Dezenove anos dos dois, e os onze primeiros não me disseram quase nada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  There was nobody to teach me. I read three books wrong and corrected myself against the water.
    >>  ............................................
    pt  Não havia ninguém pra me ensinar. Li três livros errado e me corrigi contra a água.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.craft/1
    en  You cannot see a tide from inside one year. That is the whole difficulty and nobody warns you.
    >>  ............................................
    pt  Não dá pra ver uma maré de dentro de um ano só. É toda a dificuldade e ninguém te avisa.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Weed from the far headland means the current has swung two points west. It took me nine years to know that.
    >>  ............................................
    pt  Alga do promontório distante significa que a corrente virou dois pontos a oeste. Levei nove anos pra saber.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nineteen years, and the trade only started at the twelfth. There is no shorter apprenticeship in it.
    >>  ............................................
    pt  Dezenove anos, e o ofício só começou no décimo segundo. Não há aprendizado mais curto nisso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Counting and waiting, and the waiting is the larger half by a long way.
    >>  ............................................
    pt  Contar e esperar, e esperar é a metade maior de longe.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.craft/1
    en  You cannot see a tide from inside one year. That is the whole difficulty and nobody warns you.
    >>  ............................................
    pt  Não dá pra ver uma maré de dentro de um ano só. É toda a dificuldade e ninguém te avisa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Weed from the far headland means the current has swung two points west. It took me nine years to know that.
    >>  ............................................
    pt  Alga do promontório distante significa que a corrente virou dois pontos a oeste. Levei nove anos pra saber.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nineteen years, and the trade only started at the twelfth. There is no shorter apprenticeship in it.
    >>  ............................................
    pt  Dezenove anos, e o ofício só começou no décimo segundo. Não há aprendizado mais curto nisso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Counting and waiting, and the waiting is the larger half by a long way.
    >>  ............................................
    pt  Contar e esperar, e esperar é a metade maior de longe.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Eleven years of marks that meant nothing! And in the twelfth they stopped being marks and became a shape.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada! E no décimo segundo deixaram de ser marcas e viraram forma.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Three books, all about a different coast. Everything in them was true somewhere else. Marvellously unhelpful.
    >>  ............................................
    pt  Três livros, todos sobre outra costa. Tudo neles era verdade em outro lugar. Maravilhosamente inútil.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Eleven years of marks that meant nothing! And in the twelfth they stopped being marks and became a shape.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada! E no décimo segundo deixaram de ser marcas e viraram forma.
    >>  ............................................
  playful.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Three books, all about a different coast. Everything in them was true somewhere else. Marvellously unhelpful.
    >>  ............................................
    pt  Três livros, todos sobre outra costa. Tudo neles era verdade em outro lugar. Maravilhosamente inútil.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Nineteen years, and the trade only started at the twelfth. There is no shorter apprenticeship in it.
    >>  ............................................
    pt  Dezenove anos, e o ofício só começou no décimo segundo. Não há aprendizado mais curto nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Counting and waiting, and the waiting is the larger half by a long way.
    >>  ............................................
    pt  Contar e esperar, e esperar é a metade maior de longe.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.craft/1
    en  I read the books wrong and it took me six years to be willing to say so out loud.
    >>  ............................................
    pt  Li os livros errado e levei seis anos pra conseguir admitir em voz alta.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Eleven years of marks that meant nothing. I kept going because I could not afford them to have meant nothing.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada. Continuei porque não podia deixar que não significassem nada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.craft/1
    en  You cannot see a tide from inside one year. That is the whole difficulty and nobody warns you.
    >>  ............................................
    pt  Não dá pra ver uma maré de dentro de um ano só. É toda a dificuldade e ninguém te avisa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Weed from the far headland means the current has swung two points west. It took me nine years to know that.
    >>  ............................................
    pt  Alga do promontório distante significa que a corrente virou dois pontos a oeste. Levei nove anos pra saber.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Eleven years of marks that meant nothing! And in the twelfth they stopped being marks and became a shape.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada! E no décimo segundo deixaram de ser marcas e viraram forma.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Three books, all about a different coast. Everything in them was true somewhere else. Marvellously unhelpful.
    >>  ............................................
    pt  Três livros, todos sobre outra costa. Tudo neles era verdade em outro lugar. Maravilhosamente inútil.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.craft/1
    en  Eleven years of marks that meant nothing! And in the twelfth they stopped being marks and became a shape.
    >>  ............................................
    pt  Onze anos de marcas que não significavam nada! E no décimo segundo deixaram de ser marcas e viraram forma.
    >>  ............................................
  witty.dialogue.conversations.work.prof.oceanographer.craft/2
    en  Three books, all about a different coast. Everything in them was true somewhere else. Marvellously unhelpful.
    >>  ............................................
    pt  Três livros, todos sobre outra costa. Tudo neles era verdade em outro lugar. Maravilhosamente inútil.
    >>  ............................................
```

</details>


**Outcome 171 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
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

```text
  dialogue.conversations.work.prof.oceanographer.risk/1   [89 chars]
    en  If I'm right about the lower houses, I have to say so, and saying so moves four families.
    >>  ............................................
    pt  Se eu estiver certo sobre as casas baixas, tenho que dizer, e dizer move quatro famílias.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.risk/2   [96 chars]
    en  The water doesn't care that I've measured it. Nineteen years of marks buys me no warning at all.
    >>  ............................................
    pt  A água não liga que eu a medi. Dezenove anos de marcas não me compram aviso nenhum.
    >>  ............................................
```


**Outcome 172 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
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

```text
  dialogue.conversations.work.prof.oceanographer.village/1   [106 chars]
    en  The fishermen go where the current is and the current is what I count. They don't know that and it's fine.
    >>  ............................................
    pt  Os pescadores vão onde a corrente está e a corrente é o que eu conto. Eles não sabem e tudo bem.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.village/2   [88 chars]
    en  Nineteen years of marks on a post is the only record of this coast that exists anywhere.
    >>  ............................................
    pt  Dezenove anos de marcas num poste é o único registro desta costa que existe em algum lugar.
    >>  ............................................
```


**Outcome 173 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:oceanographer"
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

```text
  dialogue.conversations.work.prof.oceanographer.future/1   [101 chars]
    en  Two posts, not one. A second coast to compare against turns nineteen years of marks into an argument.
    >>  ............................................
    pt  Dois postes, não um. Uma segunda costa pra comparar transforma dezenove anos de marcas num argumento.
    >>  ............................................
  dialogue.conversations.work.prof.oceanographer.future/2   [98 chars]
    en  Somebody has to keep marking after me. A record with a gap in it is worth less than half a record.
    >>  ............................................
    pt  Alguém tem que continuar marcando depois de mim. Um registro com lacuna vale menos que metade.
    >>  ............................................
```


**Outcome 174 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
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

```text
  dialogue.conversations.work.prof.enderian.task/1   [99 chars]
    en  Sorting what came back through with me. Half of it I can't name and all of it has to be catalogued.
    >>  ............................................
    pt  Separando o que voltou comigo. Metade eu não sei nomear e tudo tem que ser catalogado.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.task/2   [100 chars]
    en  Sitting very still. It sounds like nothing and it's the only way the sickness passes before evening.
    >>  ............................................
    pt  Ficando muito parado. Parece nada e é o único jeito de o mal-estar passar antes da noite.
    >>  ............................................
```


**Outcome 175 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
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

```text
  dialogue.conversations.work.prof.enderian.craft/1   [99 chars]
    en  Nobody teaches this. You find out you can do it and then you find out what it costs, in that order.
    >>  ............................................
    pt  Ninguém ensina isso. Você descobre que consegue e depois descobre o preço, nessa ordem.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.craft/2   [95 chars]
    en  The skill isn't going. Anyone can go once. The skill is arriving somewhere you meant to arrive.
    >>  ............................................
    pt  A habilidade não é ir. Qualquer um vai uma vez. A habilidade é chegar onde você pretendia.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.enderian.craft/1
    en  I have a scar from each lesson between not knowing and knowing, and I'd not show you all of them.
    >>  ............................................
    pt  Tenho uma cicatriz de cada lição entre não saber e saber, e eu não mostraria todas.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.enderian.craft/2
    en  Nobody taught me because nobody here had done it. I learned it alone and I would not recommend that.
    >>  ............................................
    pt  Ninguém me ensinou porque ninguém aqui tinha feito. Aprendi sozinho e eu não recomendaria.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.craft/1
    en  Slowly, and by only doing it when the alternative is worse. That rule came late and it was the right rule.
    >>  ............................................
    pt  Devagar, e só fazendo quando a alternativa é pior. Essa regra veio tarde e era a regra certa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.enderian.craft/2
    en  Nineteen years of finding out. There is no faster version and I've stopped looking for one.
    >>  ............................................
    pt  Dezenove anos descobrindo. Não há versão mais rápida e eu parei de procurar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches this. You find out you can do it and then you find out what it costs, in that order.
    >>  ............................................
    pt  Ninguém ensina isso. Você descobre que consegue e depois descobre o preço, nessa ordem.
    >>  ............................................
  confident.dialogue.conversations.work.prof.enderian.craft/2
    en  Anyone can go once. Arriving where you meant to is the skill, and I manage it nine times in ten.
    >>  ............................................
    pt  Qualquer um vai uma vez. Chegar onde pretendia é a habilidade, e eu consigo nove em dez.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches this. You find out you can do it and then you find out what it costs, in that order.
    >>  ............................................
    pt  Ninguém ensina isso. Você descobre que consegue e depois descobre o preço, nessa ordem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.enderian.craft/2
    en  Anyone can go once. Arriving where you meant to is the skill, and I manage it nine times in ten.
    >>  ............................................
    pt  Qualquer um vai uma vez. Chegar onde pretendia é a habilidade, e eu consigo nove em dez.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody taught me, so I tell anyone who asks, in full, at length, until they're bored of me.
    >>  ............................................
    pt  Ninguém me ensinou, então eu conto a quem perguntar, inteiro, longamente, até enjoarem de mim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.enderian.craft/2
    en  There was no one to learn from. That's why I'd rather be the one somebody else learns from.
    >>  ............................................
    pt  Não havia com quem aprender. Por isso eu prefiro ser aquele com quem outro aprenda.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody taught me, so I tell anyone who asks, in full, at length, until they're bored of me.
    >>  ............................................
    pt  Ninguém me ensinou, então eu conto a quem perguntar, inteiro, longamente, até enjoarem de mim.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.enderian.craft/2
    en  There was no one to learn from. That's why I'd rather be the one somebody else learns from.
    >>  ............................................
    pt  Não havia com quem aprender. Por isso eu prefiro ser aquele com quem outro aprenda.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody taught me, so I tell anyone who asks, in full, at length, until they're bored of me.
    >>  ............................................
    pt  Ninguém me ensinou, então eu conto a quem perguntar, inteiro, longamente, até enjoarem de mim.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.enderian.craft/2
    en  There was no one to learn from. That's why I'd rather be the one somebody else learns from.
    >>  ............................................
    pt  Não havia com quem aprender. Por isso eu prefiro ser aquele com quem outro aprenda.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.craft/1
    en  I have a scar from each lesson between not knowing and knowing, and I'd not show you all of them.
    >>  ............................................
    pt  Tenho uma cicatriz de cada lição entre não saber e saber, e eu não mostraria todas.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.enderian.craft/2
    en  Nobody taught me because nobody here had done it. I learned it alone and I would not recommend that.
    >>  ............................................
    pt  Ninguém me ensinou porque ninguém aqui tinha feito. Aprendi sozinho e eu não recomendaria.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches this. You find out you can do it and then you find out what it costs, in that order.
    >>  ............................................
    pt  Ninguém ensina isso. Você descobre que consegue e depois descobre o preço, nessa ordem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.enderian.craft/2
    en  Anyone can go once. Arriving where you meant to is the skill, and I manage it nine times in ten.
    >>  ............................................
    pt  Qualquer um vai uma vez. Chegar onde pretendia é a habilidade, e eu consigo nove em dez.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches this. You find out you can do it and then you find out what it costs, in that order.
    >>  ............................................
    pt  Ninguém ensina isso. Você descobre que consegue e depois descobre o preço, nessa ordem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.enderian.craft/2
    en  Anyone can go once. Arriving where you meant to is the skill, and I manage it nine times in ten.
    >>  ............................................
    pt  Qualquer um vai uma vez. Chegar onde pretendia é a habilidade, e eu consigo nove em dez.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.craft/1
    en  You learn the cost afterwards. That is the only order available and nobody could have warned me.
    >>  ............................................
    pt  Você aprende o custo depois. É a única ordem disponível e ninguém podia ter me avisado.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.enderian.craft/2
    en  The tenth time you arrive somewhere adjacent. Usually cold, usually dark, always far from a road.
    >>  ............................................
    pt  Na décima vez você chega num lugar vizinho. Normalmente frio, escuro, sempre longe de uma estrada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.craft/1
    en  Slowly, and by only doing it when the alternative is worse. That rule came late and it was the right rule.
    >>  ............................................
    pt  Devagar, e só fazendo quando a alternativa é pior. Essa regra veio tarde e era a regra certa.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.enderian.craft/2
    en  Nineteen years of finding out. There is no faster version and I've stopped looking for one.
    >>  ............................................
    pt  Dezenove anos descobrindo. Não há versão mais rápida e eu parei de procurar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.craft/1
    en  You learn the cost afterwards. That is the only order available and nobody could have warned me.
    >>  ............................................
    pt  Você aprende o custo depois. É a única ordem disponível e ninguém podia ter me avisado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.enderian.craft/2
    en  The tenth time you arrive somewhere adjacent. Usually cold, usually dark, always far from a road.
    >>  ............................................
    pt  Na décima vez você chega num lugar vizinho. Normalmente frio, escuro, sempre longe de uma estrada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.craft/1
    en  Slowly, and by only doing it when the alternative is worse. That rule came late and it was the right rule.
    >>  ............................................
    pt  Devagar, e só fazendo quando a alternativa é pior. Essa regra veio tarde e era a regra certa.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.enderian.craft/2
    en  Nineteen years of finding out. There is no faster version and I've stopped looking for one.
    >>  ............................................
    pt  Dezenove anos descobrindo. Não há versão mais rápida e eu parei de procurar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches it! You simply find out, usually somewhere cold, usually at a very inconvenient hour.
    >>  ............................................
    pt  Ninguém ensina! Você simplesmente descobre, normalmente num lugar frio, numa hora muito inconveniente.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.enderian.craft/2
    en  Nine times in ten I land where I meant to. The tenth is why I have such varied stories.
    >>  ............................................
    pt  Nove em dez vezes eu chego onde pretendia. A décima é por que eu tenho histórias tão variadas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches it! You simply find out, usually somewhere cold, usually at a very inconvenient hour.
    >>  ............................................
    pt  Ninguém ensina! Você simplesmente descobre, normalmente num lugar frio, numa hora muito inconveniente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.enderian.craft/2
    en  Nine times in ten I land where I meant to. The tenth is why I have such varied stories.
    >>  ............................................
    pt  Nove em dez vezes eu chego onde pretendia. A décima é por que eu tenho histórias tão variadas.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.craft/1
    en  Slowly, and by only doing it when the alternative is worse. That rule came late and it was the right rule.
    >>  ............................................
    pt  Devagar, e só fazendo quando a alternativa é pior. Essa regra veio tarde e era a regra certa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.enderian.craft/2
    en  Nineteen years of finding out. There is no faster version and I've stopped looking for one.
    >>  ............................................
    pt  Dezenove anos descobrindo. Não há versão mais rápida e eu parei de procurar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.craft/1
    en  I have a scar from each lesson between not knowing and knowing, and I'd not show you all of them.
    >>  ............................................
    pt  Tenho uma cicatriz de cada lição entre não saber e saber, e eu não mostraria todas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.enderian.craft/2
    en  Nobody taught me because nobody here had done it. I learned it alone and I would not recommend that.
    >>  ............................................
    pt  Ninguém me ensinou porque ninguém aqui tinha feito. Aprendi sozinho e eu não recomendaria.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.craft/1
    en  You learn the cost afterwards. That is the only order available and nobody could have warned me.
    >>  ............................................
    pt  Você aprende o custo depois. É a única ordem disponível e ninguém podia ter me avisado.
    >>  ............................................
  shy.dialogue.conversations.work.prof.enderian.craft/2
    en  The tenth time you arrive somewhere adjacent. Usually cold, usually dark, always far from a road.
    >>  ............................................
    pt  Na décima vez você chega num lugar vizinho. Normalmente frio, escuro, sempre longe de uma estrada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches it! You simply find out, usually somewhere cold, usually at a very inconvenient hour.
    >>  ............................................
    pt  Ninguém ensina! Você simplesmente descobre, normalmente num lugar frio, numa hora muito inconveniente.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.enderian.craft/2
    en  Nine times in ten I land where I meant to. The tenth is why I have such varied stories.
    >>  ............................................
    pt  Nove em dez vezes eu chego onde pretendia. A décima é por que eu tenho histórias tão variadas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.craft/1
    en  Nobody teaches it! You simply find out, usually somewhere cold, usually at a very inconvenient hour.
    >>  ............................................
    pt  Ninguém ensina! Você simplesmente descobre, normalmente num lugar frio, numa hora muito inconveniente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.enderian.craft/2
    en  Nine times in ten I land where I meant to. The tenth is why I have such varied stories.
    >>  ............................................
    pt  Nove em dez vezes eu chego onde pretendia. A décima é por que eu tenho histórias tão variadas.
    >>  ............................................
```

</details>


**Outcome 176 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
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

```text
  dialogue.conversations.work.prof.enderian.risk/1   [103 chars]
    en  People here watch my hands. Nineteen years and they still watch my hands, and I've decided that's fair.
    >>  ............................................
    pt  As pessoas aqui olham minhas mãos. Dezenove anos e ainda olham, e eu decidi que é justo.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.risk/2   [98 chars]
    en  Every time I go, there's a chance I don't come back and nobody here would ever know what happened.
    >>  ............................................
    pt  Toda vez que eu vou, tem uma chance de eu não voltar e ninguém aqui saberia o que houve.
    >>  ............................................
```


**Outcome 177 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
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

```text
  dialogue.conversations.work.prof.enderian.village/1   [98 chars]
    en  I've fetched a cleric from four valleys away in an afternoon. Twice. Nobody discusses either time.
    >>  ............................................
    pt  Já trouxe uma clériga de quatro vales numa tarde. Duas vezes. Ninguém comenta nenhuma delas.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.village/2   [99 chars]
    en  This place tolerates me, feeds me, and does not invite me. All three are true and I hold all three.
    >>  ............................................
    pt  Este lugar me tolera, me alimenta, e não me convida. As três coisas são verdade e eu carrego as três.
    >>  ............................................
```


**Outcome 178 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:enderian"
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

```text
  dialogue.conversations.work.prof.enderian.future/1   [88 chars]
    en  I'd like to give the catalogue to somebody who'd keep it without being frightened of it.
    >>  ............................................
    pt  Queria dar o catálogo a alguém que o guardasse sem ter medo dele.
    >>  ............................................
  dialogue.conversations.work.prof.enderian.future/2   [95 chars]
    en  One more trip, to find out what the warm thing is. And then I'd like to be finished with going.
    >>  ............................................
    pt  Mais uma viagem, pra descobrir o que é a coisa morna. E aí eu queria terminar com o ir.
    >>  ............................................
```


**Outcome 179 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
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

```text
  dialogue.conversations.work.prof.netherian.task/1   [108 chars]
    en  Drying what I brought back. It has to be done slowly or it takes the roof off, and I'd rather keep the roof.
    >>  ............................................
    pt  Secando o que eu trouxe. Tem que ser devagar ou leva o telhado, e eu prefiro manter o telhado.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.task/2   [108 chars]
    en  Grinding. Two hours of it for a jar the cleric will use four grains of, and both of us think that's correct.
    >>  ............................................
    pt  Moendo. Duas horas pra um pote de que a clériga vai usar quatro grãos, e nós dois achamos correto.
    >>  ............................................
```


**Outcome 180 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
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

```text
  dialogue.conversations.work.prof.netherian.craft/1   [96 chars]
    en  Heat, mostly. Knowing exactly how much and for exactly how long, and there is no book that says.
    >>  ............................................
    pt  Calor, principalmente. Saber exatamente quanto e por exatamente quanto tempo, e não há livro que diga.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.craft/2   [108 chars]
    en  I've written down every failure for nineteen years. The failures are the only thing anyone could learn from.
    >>  ............................................
    pt  Anotei cada falha por dezenove anos. As falhas são a única coisa de que alguém poderia aprender.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.netherian.craft/1
    en  Numbers nine, forty-one, ninety and a hundred and eighty-three nearly killed me. They are underlined for that.
    >>  ............................................
    pt  Os números nove, quarenta e um, noventa e cento e oitenta e três quase me mataram. Estão sublinhados por isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.netherian.craft/2
    en  I learned by taking a quarter dose myself and waiting four hours, alone, and I still do.
    >>  ............................................
    pt  Aprendi tomando um quarto de dose eu mesmo e esperando quatro horas, sozinho, e eu ainda faço.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.craft/1
    en  Nineteen years of heat and patience. The drying cannot be hurried and I have never hurried it.
    >>  ............................................
    pt  Dezenove anos de calor e paciência. A secagem não se apressa e eu nunca apressei.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.craft/2
    en  Two hundred failures at about eleven a year. That is the actual speed of this and I've made peace with it.
    >>  ............................................
    pt  Duzentas falhas a umas onze por ano. É a velocidade real disso e eu fiz as pazes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.craft/1
    en  Heat. How much and for exactly how long, and there is no book anywhere that says.
    >>  ............................................
    pt  Calor. Quanto e por exatamente quanto tempo, e não há livro em lugar nenhum que diga.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.craft/2
    en  I have written down every failure for nineteen years. Two hundred and six of them, numbered.
    >>  ............................................
    pt  Anotei cada falha por dezenove anos. Duzentas e seis, numeradas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.craft/1
    en  Heat. How much and for exactly how long, and there is no book anywhere that says.
    >>  ............................................
    pt  Calor. Quanto e por exatamente quanto tempo, e não há livro em lugar nenhum que diga.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.craft/2
    en  I have written down every failure for nineteen years. Two hundred and six of them, numbered.
    >>  ............................................
    pt  Anotei cada falha por dezenove anos. Duzentas e seis, numeradas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.craft/1
    en  Nobody taught me, so I write everything down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Ninguém me ensinou, então eu anoto tudo pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.craft/2
    en  The cleric taught me more than any book did, by telling me exactly what four grains had to do.
    >>  ............................................
    pt  A clériga me ensinou mais que qualquer livro, dizendo exatamente o que quatro grãos tinham que fazer.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.craft/1
    en  Nobody taught me, so I write everything down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Ninguém me ensinou, então eu anoto tudo pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.craft/2
    en  The cleric taught me more than any book did, by telling me exactly what four grains had to do.
    >>  ............................................
    pt  A clériga me ensinou mais que qualquer livro, dizendo exatamente o que quatro grãos tinham que fazer.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.craft/1
    en  Nobody taught me, so I write everything down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Ninguém me ensinou, então eu anoto tudo pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.craft/2
    en  The cleric taught me more than any book did, by telling me exactly what four grains had to do.
    >>  ............................................
    pt  A clériga me ensinou mais que qualquer livro, dizendo exatamente o que quatro grãos tinham que fazer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.craft/1
    en  Numbers nine, forty-one, ninety and a hundred and eighty-three nearly killed me. They are underlined for that.
    >>  ............................................
    pt  Os números nove, quarenta e um, noventa e cento e oitenta e três quase me mataram. Estão sublinhados por isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.craft/2
    en  I learned by taking a quarter dose myself and waiting four hours, alone, and I still do.
    >>  ............................................
    pt  Aprendi tomando um quarto de dose eu mesmo e esperando quatro horas, sozinho, e eu ainda faço.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.craft/1
    en  Heat. How much and for exactly how long, and there is no book anywhere that says.
    >>  ............................................
    pt  Calor. Quanto e por exatamente quanto tempo, e não há livro em lugar nenhum que diga.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.craft/2
    en  I have written down every failure for nineteen years. Two hundred and six of them, numbered.
    >>  ............................................
    pt  Anotei cada falha por dezenove anos. Duzentas e seis, numeradas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.craft/1
    en  Heat. How much and for exactly how long, and there is no book anywhere that says.
    >>  ............................................
    pt  Calor. Quanto e por exatamente quanto tempo, e não há livro em lugar nenhum que diga.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.craft/2
    en  I have written down every failure for nineteen years. Two hundred and six of them, numbered.
    >>  ............................................
    pt  Anotei cada falha por dezenove anos. Duzentas e seis, numeradas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.craft/1
    en  Slow drying, in a shed a field from everything. Rush it and you take a roof off.
    >>  ............................................
    pt  Secagem lenta, num galpão a um campo de tudo. Apresse e você leva um telhado.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.craft/2
    en  The failures are the only thing anyone could learn from. Successes tell you one thing that worked, once.
    >>  ............................................
    pt  As falhas são a única coisa de que alguém poderia aprender. Acertos te dizem uma coisa que funcionou, uma vez.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.craft/1
    en  Nineteen years of heat and patience. The drying cannot be hurried and I have never hurried it.
    >>  ............................................
    pt  Dezenove anos de calor e paciência. A secagem não se apressa e eu nunca apressei.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.craft/2
    en  Two hundred failures at about eleven a year. That is the actual speed of this and I've made peace with it.
    >>  ............................................
    pt  Duzentas falhas a umas onze por ano. É a velocidade real disso e eu fiz as pazes.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.craft/1
    en  Slow drying, in a shed a field from everything. Rush it and you take a roof off.
    >>  ............................................
    pt  Secagem lenta, num galpão a um campo de tudo. Apresse e você leva um telhado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.craft/2
    en  The failures are the only thing anyone could learn from. Successes tell you one thing that worked, once.
    >>  ............................................
    pt  As falhas são a única coisa de que alguém poderia aprender. Acertos te dizem uma coisa que funcionou, uma vez.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.craft/1
    en  Nineteen years of heat and patience. The drying cannot be hurried and I have never hurried it.
    >>  ............................................
    pt  Dezenove anos de calor e paciência. A secagem não se apressa e eu nunca apressei.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.craft/2
    en  Two hundred failures at about eleven a year. That is the actual speed of this and I've made peace with it.
    >>  ............................................
    pt  Duzentas falhas a umas onze por ano. É a velocidade real disso e eu fiz as pazes.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.craft/1
    en  Two hundred and six recorded failures! Four of them underlined. The underlined ones are the good stories.
    >>  ............................................
    pt  Duzentas e seis falhas registradas! Quatro sublinhadas. As sublinhadas são as boas histórias.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.craft/2
    en  There's no book, so I wrote one. It is mostly a list of things that do not work, which is the useful kind.
    >>  ............................................
    pt  Não tem livro, então eu escrevi um. É quase só uma lista do que não funciona, que é o tipo útil.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.craft/1
    en  Two hundred and six recorded failures! Four of them underlined. The underlined ones are the good stories.
    >>  ............................................
    pt  Duzentas e seis falhas registradas! Quatro sublinhadas. As sublinhadas são as boas histórias.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.craft/2
    en  There's no book, so I wrote one. It is mostly a list of things that do not work, which is the useful kind.
    >>  ............................................
    pt  Não tem livro, então eu escrevi um. É quase só uma lista do que não funciona, que é o tipo útil.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.craft/1
    en  Nineteen years of heat and patience. The drying cannot be hurried and I have never hurried it.
    >>  ............................................
    pt  Dezenove anos de calor e paciência. A secagem não se apressa e eu nunca apressei.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.craft/2
    en  Two hundred failures at about eleven a year. That is the actual speed of this and I've made peace with it.
    >>  ............................................
    pt  Duzentas falhas a umas onze por ano. É a velocidade real disso e eu fiz as pazes.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.craft/1
    en  Numbers nine, forty-one, ninety and a hundred and eighty-three nearly killed me. They are underlined for that.
    >>  ............................................
    pt  Os números nove, quarenta e um, noventa e cento e oitenta e três quase me mataram. Estão sublinhados por isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.craft/2
    en  I learned by taking a quarter dose myself and waiting four hours, alone, and I still do.
    >>  ............................................
    pt  Aprendi tomando um quarto de dose eu mesmo e esperando quatro horas, sozinho, e eu ainda faço.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.craft/1
    en  Slow drying, in a shed a field from everything. Rush it and you take a roof off.
    >>  ............................................
    pt  Secagem lenta, num galpão a um campo de tudo. Apresse e você leva um telhado.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.craft/2
    en  The failures are the only thing anyone could learn from. Successes tell you one thing that worked, once.
    >>  ............................................
    pt  As falhas são a única coisa de que alguém poderia aprender. Acertos te dizem uma coisa que funcionou, uma vez.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.craft/1
    en  Two hundred and six recorded failures! Four of them underlined. The underlined ones are the good stories.
    >>  ............................................
    pt  Duzentas e seis falhas registradas! Quatro sublinhadas. As sublinhadas são as boas histórias.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.craft/2
    en  There's no book, so I wrote one. It is mostly a list of things that do not work, which is the useful kind.
    >>  ............................................
    pt  Não tem livro, então eu escrevi um. É quase só uma lista do que não funciona, que é o tipo útil.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.craft/1
    en  Two hundred and six recorded failures! Four of them underlined. The underlined ones are the good stories.
    >>  ............................................
    pt  Duzentas e seis falhas registradas! Quatro sublinhadas. As sublinhadas são as boas histórias.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.craft/2
    en  There's no book, so I wrote one. It is mostly a list of things that do not work, which is the useful kind.
    >>  ............................................
    pt  Não tem livro, então eu escrevi um. É quase só uma lista do que não funciona, que é o tipo útil.
    >>  ............................................
```

</details>


**Outcome 181 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
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

```text
  dialogue.conversations.work.prof.netherian.risk/1   [92 chars]
    en  I keep the shed a field away and I've never asked anybody to help me carry anything into it.
    >>  ............................................
    pt  Mantenho o galpão a um campo de distância e nunca pedi a ninguém pra me ajudar a carregar nada pra lá.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.risk/2   [103 chars]
    en  If I get a batch wrong the cleric gives it to somebody ill. That's the risk, and it isn't mine to take.
    >>  ............................................
    pt  Se eu errar um lote a clériga dá pra alguém doente. É esse o risco, e não é meu pra correr.
    >>  ............................................
```


**Outcome 182 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
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

```text
  dialogue.conversations.work.prof.netherian.village/1   [91 chars]
    en  Four fevers in eleven years that would have gone the other way. That's what the jar is for.
    >>  ............................................
    pt  Quatro febres em onze anos que teriam ido pro outro lado. É pra isso que serve o pote.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.village/2   [94 chars]
    en  Half this place would prefer the shed weren't here. The other half has been ill at some point.
    >>  ............................................
    pt  Metade daqui preferia que o galpão não existisse. A outra metade já ficou doente em algum momento.
    >>  ............................................
```


**Outcome 183 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "morevillagers:netherian"
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

```text
  dialogue.conversations.work.prof.netherian.future/1   [111 chars]
    en  The book of failures should go to the cleric, not to a successor. She'd use it and a successor would repeat it.
    >>  ............................................
    pt  O livro de falhas devia ir pra clériga, não pra um sucessor. Ela usaria e um sucessor repetiria.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.future/2   [107 chars]
    en  I'd like the shed to be a proper stone building. Then the half who object would have a real reason to stop.
    >>  ............................................
    pt  Queria que o galpão fosse de pedra de verdade. Aí a metade que objeta teria motivo real pra desistir.
    >>  ............................................
```


**Outcome 184 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
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

```text
  dialogue.conversations.work.prof.shady_wizard.task/1   [99 chars]
    en  Sorting stock and deciding what I'm prepared to sell to whom. That second part takes the afternoon.
    >>  ............................................
    pt  Separando estoque e decidindo o que eu topo vender e a quem. Essa segunda parte leva a tarde.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.task/2   [103 chars]
    en  Copying a diagram I only half understand. I'll not pretend otherwise to you and I do pretend otherwise.
    >>  ............................................
    pt  Copiando um diagrama que eu só entendo pela metade. Não vou fingir com você e eu finjo com os outros.
    >>  ............................................
```


**Outcome 185 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
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

```text
  dialogue.conversations.work.prof.shady_wizard.craft/1   [108 chars]
    en  Nobody trained me. I bought a chest at an estate sale when I was twenty-two and I've been catching up since.
    >>  ............................................
    pt  Ninguém me treinou. Comprei um baú num leilão aos vinte e dois e venho correndo atrás desde então.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.craft/2   [118 chars]
    en  Half of this is chemistry and half is theatre, and the trade only survives if you're honest with yourself about which.
    >>  ............................................
    pt  Metade disto é química e metade é teatro, e o ofício só sobrevive se você for honesto consigo sobre qual é qual.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Somebody's whole life, sold off by relatives who wanted the furniture. I think about them more than I say.
    >>  ............................................
    pt  A vida inteira de alguém, leiloada por parentes que queriam os móveis. Penso neles mais do que digo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  There was nobody to check my chemistry against — only my theatre. That is a lonelier trade than it looks.
    >>  ............................................
    pt  Não havia ninguém pra conferir minha química — só meu teatro. É um ofício mais solitário do que parece.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nineteen years of reading a dead stranger's books. Slowly. There has never been anyone to hurry me.
    >>  ............................................
    pt  Dezenove anos lendo os livros de um estranho morto. Devagar. Nunca houve ninguém pra me apressar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  A chest at twenty-two, and I am still working through it. That pace has suited me and suits me now.
    >>  ............................................
    pt  Um baú aos vinte e dois, e eu ainda estou passando por ele. Esse ritmo me serviu e me serve.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nobody trained me. I bought a chest at an estate sale at twenty-two and I have been catching up since.
    >>  ............................................
    pt  Ninguém me treinou. Comprei um baú num leilão aos vinte e dois e venho correndo atrás desde então.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Half of this is chemistry and half is theatre, and the trade only survives if you know which is which.
    >>  ............................................
    pt  Metade disto é química e metade é teatro, e o ofício só sobrevive se você souber qual é qual.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nobody trained me. I bought a chest at an estate sale at twenty-two and I have been catching up since.
    >>  ............................................
    pt  Ninguém me treinou. Comprei um baú num leilão aos vinte e dois e venho correndo atrás desde então.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Half of this is chemistry and half is theatre, and the trade only survives if you know which is which.
    >>  ............................................
    pt  Metade disto é química e metade é teatro, e o ofício só sobrevive se você souber qual é qual.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  A chest, nine books and nobody to ask. Which is why I answer anybody who asks me anything.
    >>  ............................................
    pt  Um baú, nove livros e ninguém pra perguntar. Por isso eu respondo qualquer um que me pergunte algo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  The cleric has taught me more than the books did, and she has never once charged me for it.
    >>  ............................................
    pt  A clériga me ensinou mais que os livros, e ela nunca me cobrou por isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  A chest, nine books and nobody to ask. Which is why I answer anybody who asks me anything.
    >>  ............................................
    pt  Um baú, nove livros e ninguém pra perguntar. Por isso eu respondo qualquer um que me pergunte algo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  The cleric has taught me more than the books did, and she has never once charged me for it.
    >>  ............................................
    pt  A clériga me ensinou mais que os livros, e ela nunca me cobrou por isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  A chest, nine books and nobody to ask. Which is why I answer anybody who asks me anything.
    >>  ............................................
    pt  Um baú, nove livros e ninguém pra perguntar. Por isso eu respondo qualquer um que me pergunte algo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  The cleric has taught me more than the books did, and she has never once charged me for it.
    >>  ............................................
    pt  A clériga me ensinou mais que os livros, e ela nunca me cobrou por isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Somebody's whole life, sold off by relatives who wanted the furniture. I think about them more than I say.
    >>  ............................................
    pt  A vida inteira de alguém, leiloada por parentes que queriam os móveis. Penso neles mais do que digo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  There was nobody to check my chemistry against — only my theatre. That is a lonelier trade than it looks.
    >>  ............................................
    pt  Não havia ninguém pra conferir minha química — só meu teatro. É um ofício mais solitário do que parece.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nobody trained me. I bought a chest at an estate sale at twenty-two and I have been catching up since.
    >>  ............................................
    pt  Ninguém me treinou. Comprei um baú num leilão aos vinte e dois e venho correndo atrás desde então.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Half of this is chemistry and half is theatre, and the trade only survives if you know which is which.
    >>  ............................................
    pt  Metade disto é química e metade é teatro, e o ofício só sobrevive se você souber qual é qual.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nobody trained me. I bought a chest at an estate sale at twenty-two and I have been catching up since.
    >>  ............................................
    pt  Ninguém me treinou. Comprei um baú num leilão aos vinte e dois e venho correndo atrás desde então.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Half of this is chemistry and half is theatre, and the trade only survives if you know which is which.
    >>  ............................................
    pt  Metade disto é química e metade é teatro, e o ofício só sobrevive se você souber qual é qual.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Seven of the nine books I've read. The eighth is in a hand I can't read and the ninth I've chosen not to finish.
    >>  ............................................
    pt  Sete dos nove livros eu li. O oitavo tem uma letra que eu não leio e o nono eu escolhi não terminar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Four readings each. The fourth is where I found out how wrong the first one had made me.
    >>  ............................................
    pt  Quatro leituras cada. Na quarta é que eu descobri o quanto a primeira me deixou errado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nineteen years of reading a dead stranger's books. Slowly. There has never been anyone to hurry me.
    >>  ............................................
    pt  Dezenove anos lendo os livros de um estranho morto. Devagar. Nunca houve ninguém pra me apressar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  A chest at twenty-two, and I am still working through it. That pace has suited me and suits me now.
    >>  ............................................
    pt  Um baú aos vinte e dois, e eu ainda estou passando por ele. Esse ritmo me serviu e me serve.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Seven of the nine books I've read. The eighth is in a hand I can't read and the ninth I've chosen not to finish.
    >>  ............................................
    pt  Sete dos nove livros eu li. O oitavo tem uma letra que eu não leio e o nono eu escolhi não terminar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Four readings each. The fourth is where I found out how wrong the first one had made me.
    >>  ............................................
    pt  Quatro leituras cada. Na quarta é que eu descobri o quanto a primeira me deixou errado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nineteen years of reading a dead stranger's books. Slowly. There has never been anyone to hurry me.
    >>  ............................................
    pt  Dezenove anos lendo os livros de um estranho morto. Devagar. Nunca houve ninguém pra me apressar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  A chest at twenty-two, and I am still working through it. That pace has suited me and suits me now.
    >>  ............................................
    pt  Um baú aos vinte e dois, e eu ainda estou passando por ele. Esse ritmo me serviu e me serve.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  I bought a dead stranger's chest at twenty-two and have been improvising magnificently ever since.
    >>  ............................................
    pt  Comprei o baú de um estranho morto aos vinte e dois e venho improvisando magnificamente desde então.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Forty jars, nine books, and one thing I put straight back in. That was my entire apprenticeship.
    >>  ............................................
    pt  Quarenta potes, nove livros, e uma coisa que eu pus de volta na hora. Foi todo o meu aprendizado.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  I bought a dead stranger's chest at twenty-two and have been improvising magnificently ever since.
    >>  ............................................
    pt  Comprei o baú de um estranho morto aos vinte e dois e venho improvisando magnificamente desde então.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Forty jars, nine books, and one thing I put straight back in. That was my entire apprenticeship.
    >>  ............................................
    pt  Quarenta potes, nove livros, e uma coisa que eu pus de volta na hora. Foi todo o meu aprendizado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Nineteen years of reading a dead stranger's books. Slowly. There has never been anyone to hurry me.
    >>  ............................................
    pt  Dezenove anos lendo os livros de um estranho morto. Devagar. Nunca houve ninguém pra me apressar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  A chest at twenty-two, and I am still working through it. That pace has suited me and suits me now.
    >>  ............................................
    pt  Um baú aos vinte e dois, e eu ainda estou passando por ele. Esse ritmo me serviu e me serve.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Somebody's whole life, sold off by relatives who wanted the furniture. I think about them more than I say.
    >>  ............................................
    pt  A vida inteira de alguém, leiloada por parentes que queriam os móveis. Penso neles mais do que digo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  There was nobody to check my chemistry against — only my theatre. That is a lonelier trade than it looks.
    >>  ............................................
    pt  Não havia ninguém pra conferir minha química — só meu teatro. É um ofício mais solitário do que parece.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  Seven of the nine books I've read. The eighth is in a hand I can't read and the ninth I've chosen not to finish.
    >>  ............................................
    pt  Sete dos nove livros eu li. O oitavo tem uma letra que eu não leio e o nono eu escolhi não terminar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Four readings each. The fourth is where I found out how wrong the first one had made me.
    >>  ............................................
    pt  Quatro leituras cada. Na quarta é que eu descobri o quanto a primeira me deixou errado.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  I bought a dead stranger's chest at twenty-two and have been improvising magnificently ever since.
    >>  ............................................
    pt  Comprei o baú de um estranho morto aos vinte e dois e venho improvisando magnificamente desde então.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Forty jars, nine books, and one thing I put straight back in. That was my entire apprenticeship.
    >>  ............................................
    pt  Quarenta potes, nove livros, e uma coisa que eu pus de volta na hora. Foi todo o meu aprendizado.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.craft/1
    en  I bought a dead stranger's chest at twenty-two and have been improvising magnificently ever since.
    >>  ............................................
    pt  Comprei o baú de um estranho morto aos vinte e dois e venho improvisando magnificamente desde então.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.craft/2
    en  Forty jars, nine books, and one thing I put straight back in. That was my entire apprenticeship.
    >>  ............................................
    pt  Quarenta potes, nove livros, e uma coisa que eu pus de volta na hora. Foi todo o meu aprendizado.
    >>  ............................................
```

</details>


**Outcome 186 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
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

```text
  dialogue.conversations.work.prof.shady_wizard.risk/1   [93 chars]
    en  If something goes wrong in this valley, the man with the jars is the first name anybody says.
    >>  ............................................
    pt  Se algo der errado neste vale, o homem dos potes é o primeiro nome que dizem.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.risk/2   [101 chars]
    en  I've one thing on the top shelf I'd destroy if I knew how. Selling it is out and burying it is worse.
    >>  ............................................
    pt  Tenho uma coisa na prateleira de cima que eu destruiria se soubesse como. Vender está fora e enterrar é pior.
    >>  ............................................
```


**Outcome 187 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
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

```text
  dialogue.conversations.work.prof.shady_wizard.village/1   [96 chars]
    en  Four fevers, two burns and a birth that went sideways. That's my ledger and I keep it privately.
    >>  ............................................
    pt  Quatro febres, duas queimaduras e um parto que complicou. É o meu registro e eu guardo em silêncio.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.village/2   [83 chars]
    en  This place is polite to me on market day and crosses the lane the rest of the week.
    >>  ............................................
    pt  Este lugar é educado comigo em dia de feira e atravessa a rua no resto da semana.
    >>  ............................................
```


**Outcome 188 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "ars_nouveau:shady_wizard"
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

```text
  dialogue.conversations.work.prof.shady_wizard.future/1   [104 chars]
    en  I want to be able to say 'I don't know' at the counter and keep the customer. That's the whole ambition.
    >>  ............................................
    pt  Quero conseguir dizer 'eu não sei' no balcão e manter o cliente. É toda a ambição.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.future/2   [115 chars]
    en  Somebody sensible should have the eight books when I go. Sensible, not eager. Eager is how the chest ended up sold.
    >>  ............................................
    pt  Alguém sensato devia ficar com os oito livros quando eu for. Sensato, não ávido. Ávido é como o baú foi parar em leilão.
    >>  ............................................
```


**Outcome 189 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
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

```text
  dialogue.conversations.work.prof.delightchef.task/1   [100 chars]
    en  Eleven at the long table tonight and I've planned it back from the moment the bread has to come out.
    >>  ............................................
    pt  Onze na mesa comprida hoje e eu planejei tudo de trás pra frente, a partir da hora do pão sair.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.task/2   [99 chars]
    en  Reducing stock. Six hours for something nobody will name and everybody would notice the absence of.
    >>  ............................................
    pt  Reduzindo caldo. Seis horas por algo que ninguém vai nomear e cuja falta todos notariam.
    >>  ............................................
```


**Outcome 190 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
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

```text
  dialogue.conversations.work.prof.delightchef.craft/1   [105 chars]
    en  Timing. Everything else can be taught in a fortnight and timing takes a decade and can't be written down.
    >>  ............................................
    pt  Tempo. Todo o resto se ensina em quinze dias e o tempo leva uma década e não se escreve.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.craft/2   [114 chars]
    en  I trained in a kitchen with nine people in it and I've run one alone for eleven years. Those are different trades.
    >>  ............................................
    pt  Eu treinei numa cozinha com nove pessoas e comando uma sozinho há onze anos. São ofícios diferentes.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightchef.craft/1
    en  In a loud kitchen where nine people caught my mistakes. Alone, nobody catches them, and then it's the meal.
    >>  ............................................
    pt  Numa cozinha barulhenta onde nove pessoas pegavam meus erros. Sozinho, ninguém pega, e aí é a refeição.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade for timing. I know the exact evening I stopped being frightened and it was long after I was any good.
    >>  ............................................
    pt  Uma década pro tempo. Sei a noite exata em que parei de ter medo e foi muito depois de eu ser bom.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.craft/1
    en  A decade, and it could not have been nine years. Some things simply take the time they take.
    >>  ............................................
    pt  Uma década, e não podiam ser nove anos. Algumas coisas simplesmente levam o tempo que levam.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a big kitchen, slowly, being given one more job a year until there were no jobs left to give.
    >>  ............................................
    pt  Numa cozinha grande, devagar, ganhando mais um serviço por ano até não sobrar serviço.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.craft/1
    en  Timing, and there is no shorter answer. A fortnight teaches you everything else and timing takes a decade.
    >>  ............................................
    pt  Tempo, e não há resposta mais curta. Quinze dias ensinam todo o resto e tempo leva uma década.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.craft/2
    en  I trained in a kitchen with nine people in it. Running one alone is a different trade entirely.
    >>  ............................................
    pt  Treinei numa cozinha com nove pessoas. Comandar uma sozinho é um ofício completamente outro.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.craft/1
    en  Timing, and there is no shorter answer. A fortnight teaches you everything else and timing takes a decade.
    >>  ............................................
    pt  Tempo, e não há resposta mais curta. Quinze dias ensinam todo o resto e tempo leva uma década.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.craft/2
    en  I trained in a kitchen with nine people in it. Running one alone is a different trade entirely.
    >>  ............................................
    pt  Treinei numa cozinha com nove pessoas. Comandar uma sozinho é um ofício completamente outro.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine of us in one kitchen. I learned it from all nine and I could tell you which bit came from whom.
    >>  ............................................
    pt  Nove de nós numa cozinha. Aprendi com os nove e eu saberia dizer qual parte veio de quem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a crowded kitchen, where somebody catches your mistake while it's still a mistake. I miss that badly.
    >>  ............................................
    pt  Numa cozinha cheia, onde alguém pega seu erro enquanto ainda é erro. Sinto muita falta disso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine of us in one kitchen. I learned it from all nine and I could tell you which bit came from whom.
    >>  ............................................
    pt  Nove de nós numa cozinha. Aprendi com os nove e eu saberia dizer qual parte veio de quem.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a crowded kitchen, where somebody catches your mistake while it's still a mistake. I miss that badly.
    >>  ............................................
    pt  Numa cozinha cheia, onde alguém pega seu erro enquanto ainda é erro. Sinto muita falta disso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine of us in one kitchen. I learned it from all nine and I could tell you which bit came from whom.
    >>  ............................................
    pt  Nove de nós numa cozinha. Aprendi com os nove e eu saberia dizer qual parte veio de quem.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a crowded kitchen, where somebody catches your mistake while it's still a mistake. I miss that badly.
    >>  ............................................
    pt  Numa cozinha cheia, onde alguém pega seu erro enquanto ainda é erro. Sinto muita falta disso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.craft/1
    en  In a loud kitchen where nine people caught my mistakes. Alone, nobody catches them, and then it's the meal.
    >>  ............................................
    pt  Numa cozinha barulhenta onde nove pessoas pegavam meus erros. Sozinho, ninguém pega, e aí é a refeição.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade for timing. I know the exact evening I stopped being frightened and it was long after I was any good.
    >>  ............................................
    pt  Uma década pro tempo. Sei a noite exata em que parei de ter medo e foi muito depois de eu ser bom.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.craft/1
    en  Timing, and there is no shorter answer. A fortnight teaches you everything else and timing takes a decade.
    >>  ............................................
    pt  Tempo, e não há resposta mais curta. Quinze dias ensinam todo o resto e tempo leva uma década.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.craft/2
    en  I trained in a kitchen with nine people in it. Running one alone is a different trade entirely.
    >>  ............................................
    pt  Treinei numa cozinha com nove pessoas. Comandar uma sozinho é um ofício completamente outro.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.craft/1
    en  Timing, and there is no shorter answer. A fortnight teaches you everything else and timing takes a decade.
    >>  ............................................
    pt  Tempo, e não há resposta mais curta. Quinze dias ensinam todo o resto e tempo leva uma década.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.craft/2
    en  I trained in a kitchen with nine people in it. Running one alone is a different trade entirely.
    >>  ............................................
    pt  Treinei numa cozinha com nove pessoas. Comandar uma sozinho é um ofício completamente outro.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.craft/1
    en  You plan the evening backwards from the moment the bread has to come out. Everything else negotiates.
    >>  ............................................
    pt  Você planeja a noite de trás pra frente, da hora do pão sair. Todo o resto negocia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.craft/2
    en  Timing. It cannot be written down, and I have tried, and the numbers are wrong on a cold day.
    >>  ............................................
    pt  Tempo. Não se escreve, e eu tentei, e os números erram num dia frio.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.craft/1
    en  A decade, and it could not have been nine years. Some things simply take the time they take.
    >>  ............................................
    pt  Uma década, e não podiam ser nove anos. Algumas coisas simplesmente levam o tempo que levam.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a big kitchen, slowly, being given one more job a year until there were no jobs left to give.
    >>  ............................................
    pt  Numa cozinha grande, devagar, ganhando mais um serviço por ano até não sobrar serviço.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.craft/1
    en  You plan the evening backwards from the moment the bread has to come out. Everything else negotiates.
    >>  ............................................
    pt  Você planeja a noite de trás pra frente, da hora do pão sair. Todo o resto negocia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.craft/2
    en  Timing. It cannot be written down, and I have tried, and the numbers are wrong on a cold day.
    >>  ............................................
    pt  Tempo. Não se escreve, e eu tentei, e os números erram num dia frio.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.craft/1
    en  A decade, and it could not have been nine years. Some things simply take the time they take.
    >>  ............................................
    pt  Uma década, e não podiam ser nove anos. Algumas coisas simplesmente levam o tempo que levam.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a big kitchen, slowly, being given one more job a year until there were no jobs left to give.
    >>  ............................................
    pt  Numa cozinha grande, devagar, ganhando mais um serviço por ano até não sobrar serviço.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine people in one kitchen, all shouting, and somehow dinner appeared. Best years of my life.
    >>  ............................................
    pt  Nove pessoas numa cozinha, todas gritando, e de algum jeito o jantar aparecia. Melhores anos da minha vida.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade to learn timing. Say that to somebody who's only ever eaten and watch their face.
    >>  ............................................
    pt  Uma década pra aprender tempo. Diga isso a quem só comeu e veja a cara.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine people in one kitchen, all shouting, and somehow dinner appeared. Best years of my life.
    >>  ............................................
    pt  Nove pessoas numa cozinha, todas gritando, e de algum jeito o jantar aparecia. Melhores anos da minha vida.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade to learn timing. Say that to somebody who's only ever eaten and watch their face.
    >>  ............................................
    pt  Uma década pra aprender tempo. Diga isso a quem só comeu e veja a cara.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.craft/1
    en  A decade, and it could not have been nine years. Some things simply take the time they take.
    >>  ............................................
    pt  Uma década, e não podiam ser nove anos. Algumas coisas simplesmente levam o tempo que levam.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.craft/2
    en  In a big kitchen, slowly, being given one more job a year until there were no jobs left to give.
    >>  ............................................
    pt  Numa cozinha grande, devagar, ganhando mais um serviço por ano até não sobrar serviço.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.craft/1
    en  In a loud kitchen where nine people caught my mistakes. Alone, nobody catches them, and then it's the meal.
    >>  ............................................
    pt  Numa cozinha barulhenta onde nove pessoas pegavam meus erros. Sozinho, ninguém pega, e aí é a refeição.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade for timing. I know the exact evening I stopped being frightened and it was long after I was any good.
    >>  ............................................
    pt  Uma década pro tempo. Sei a noite exata em que parei de ter medo e foi muito depois de eu ser bom.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.craft/1
    en  You plan the evening backwards from the moment the bread has to come out. Everything else negotiates.
    >>  ............................................
    pt  Você planeja a noite de trás pra frente, da hora do pão sair. Todo o resto negocia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.craft/2
    en  Timing. It cannot be written down, and I have tried, and the numbers are wrong on a cold day.
    >>  ............................................
    pt  Tempo. Não se escreve, e eu tentei, e os números erram num dia frio.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine people in one kitchen, all shouting, and somehow dinner appeared. Best years of my life.
    >>  ............................................
    pt  Nove pessoas numa cozinha, todas gritando, e de algum jeito o jantar aparecia. Melhores anos da minha vida.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade to learn timing. Say that to somebody who's only ever eaten and watch their face.
    >>  ............................................
    pt  Uma década pra aprender tempo. Diga isso a quem só comeu e veja a cara.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.craft/1
    en  Nine people in one kitchen, all shouting, and somehow dinner appeared. Best years of my life.
    >>  ............................................
    pt  Nove pessoas numa cozinha, todas gritando, e de algum jeito o jantar aparecia. Melhores anos da minha vida.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.craft/2
    en  A decade to learn timing. Say that to somebody who's only ever eaten and watch their face.
    >>  ............................................
    pt  Uma década pra aprender tempo. Diga isso a quem só comeu e veja a cara.
    >>  ............................................
```

</details>


**Outcome 191 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
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

```text
  dialogue.conversations.work.prof.delightchef.risk/1   [91 chars]
    en  A kitchen feeds people. Get one pot wrong and eleven households find out on the same night.
    >>  ............................................
    pt  Uma cozinha alimenta gente. Erre um caldeirão e onze casas descobrem na mesma noite.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.risk/2   [111 chars]
    en  Every feast in this place runs through me. If I'm ill in December, December is a different month for everybody.
    >>  ............................................
    pt  Toda festa daqui passa por mim. Se eu adoecer em dezembro, dezembro é outro mês pra todo mundo.
    >>  ............................................
```


**Outcome 192 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
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

```text
  dialogue.conversations.work.prof.delightchef.village/1   [114 chars]
    en  Every wedding, every harvest supper, every wake. I've cooked for this place at both its ends, same as the florist.
    >>  ............................................
    pt  Todo casamento, toda ceia de colheita, todo velório. Cozinhei pra este lugar nas duas pontas, igual à florista.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.village/2   [100 chars]
    en  Nobody here has eaten badly at a gathering in eleven years. That's the only claim I'd make out loud.
    >>  ............................................
    pt  Ninguém aqui comeu mal numa reunião em onze anos. É a única afirmação que eu faria em voz alta.
    >>  ............................................
```


**Outcome 193 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightchef"
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

```text
  dialogue.conversations.work.prof.delightchef.future/1   [80 chars]
    en  A second pair of hands in the kitchen. Then December is a month and not a wager.
    >>  ............................................
    pt  Um segundo par de mãos na cozinha. Aí dezembro é um mês e não uma aposta.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.future/2   [115 chars]
    en  I'd like to write the timings down, even knowing they can't be. Somebody could start from further along than I did.
    >>  ............................................
    pt  Queria anotar os tempos, mesmo sabendo que não dá. Alguém poderia começar mais adiante do que eu comecei.
    >>  ............................................
```


**Outcome 194 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
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

```text
  dialogue.conversations.work.prof.delightcook.task/1   [90 chars]
    en  The pot. It goes on before dawn and it comes off after dark and something is always in it.
    >>  ............................................
    pt  O caldeirão. Vai ao fogo antes do amanhecer e sai depois de escurecer e sempre tem algo dentro.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.task/2   [90 chars]
    en  Bread. Nine loaves, and four of them go to houses that haven't asked and won't mention it.
    >>  ............................................
    pt  Pão. Nove fornadas, e quatro vão pra casas que não pediram e não vão mencionar.
    >>  ............................................
```


**Outcome 195 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
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

```text
  dialogue.conversations.work.prof.delightcook.craft/1   [101 chars]
    en  Making a great deal out of very little. That's not humility, it's the actual skill and it took years.
    >>  ............................................
    pt  Fazer muito de muito pouco. Não é humildade, é a habilidade de verdade e levou anos.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.craft/2   [109 chars]
    en  My grandmother cooked for a household of twelve on nothing. I've never matched her and I use her proportions.
    >>  ............................................
    pt  Minha avó cozinhava pra uma casa de doze com nada. Nunca a igualei e uso as proporções dela.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, who had less than I do and never once served a thin pot. I have served thin pots.
    >>  ............................................
    pt  Com minha avó, que tinha menos que eu e nunca serviu um caldo ralo. Eu já servi.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightcook.craft/2
    en  She was hungry and I have only ever been careful. That is the whole difference and it is not small.
    >>  ............................................
    pt  Ela passava fome e eu só fui cuidadoso. É toda a diferença e não é pequena.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, over years, without either of us calling it teaching.
    >>  ............................................
    pt  Com minha avó, ao longo de anos, sem nenhum de nós chamar de ensino.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightcook.craft/2
    en  Slowly. A pot teaches you at its own pace and there has never been a way to hurry a pot.
    >>  ............................................
    pt  Devagar. Um caldeirão ensina no ritmo dele e nunca houve jeito de apressar um caldeirão.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.craft/1
    en  Three of the cheap thing to one of the dear thing, and salt like you mean it. That is the whole method.
    >>  ............................................
    pt  Três do barato pra um do caro, e sal como quem quer dizer algo. É todo o método.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightcook.craft/2
    en  My grandmother fed a household of twelve on nothing. I use her proportions and I've never matched her.
    >>  ............................................
    pt  Minha avó alimentava uma casa de doze com nada. Uso as proporções dela e nunca a igualei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.craft/1
    en  Three of the cheap thing to one of the dear thing, and salt like you mean it. That is the whole method.
    >>  ............................................
    pt  Três do barato pra um do caro, e sal como quem quer dizer algo. É todo o método.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightcook.craft/2
    en  My grandmother fed a household of twelve on nothing. I use her proportions and I've never matched her.
    >>  ............................................
    pt  Minha avó alimentava uma casa de doze com nada. Uso as proporções dela e nunca a igualei.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.craft/1
    en  My grandmother taught me in a kitchen full of people, which is the only place it can be taught.
    >>  ............................................
    pt  Minha avó me ensinou numa cozinha cheia de gente, que é o único lugar em que se pode ensinar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightcook.craft/2
    en  From her, and from every family that ever sent a child round to say they were short. They taught me too.
    >>  ............................................
    pt  Com ela, e com cada família que mandou uma criança dizer que estava faltando. Elas também me ensinaram.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.craft/1
    en  My grandmother taught me in a kitchen full of people, which is the only place it can be taught.
    >>  ............................................
    pt  Minha avó me ensinou numa cozinha cheia de gente, que é o único lugar em que se pode ensinar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightcook.craft/2
    en  From her, and from every family that ever sent a child round to say they were short. They taught me too.
    >>  ............................................
    pt  Com ela, e com cada família que mandou uma criança dizer que estava faltando. Elas também me ensinaram.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.craft/1
    en  My grandmother taught me in a kitchen full of people, which is the only place it can be taught.
    >>  ............................................
    pt  Minha avó me ensinou numa cozinha cheia de gente, que é o único lugar em que se pode ensinar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightcook.craft/2
    en  From her, and from every family that ever sent a child round to say they were short. They taught me too.
    >>  ............................................
    pt  Com ela, e com cada família que mandou uma criança dizer que estava faltando. Elas também me ensinaram.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, who had less than I do and never once served a thin pot. I have served thin pots.
    >>  ............................................
    pt  Com minha avó, que tinha menos que eu e nunca serviu um caldo ralo. Eu já servi.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightcook.craft/2
    en  She was hungry and I have only ever been careful. That is the whole difference and it is not small.
    >>  ............................................
    pt  Ela passava fome e eu só fui cuidadoso. É toda a diferença e não é pequena.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.craft/1
    en  Three of the cheap thing to one of the dear thing, and salt like you mean it. That is the whole method.
    >>  ............................................
    pt  Três do barato pra um do caro, e sal como quem quer dizer algo. É todo o método.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightcook.craft/2
    en  My grandmother fed a household of twelve on nothing. I use her proportions and I've never matched her.
    >>  ............................................
    pt  Minha avó alimentava uma casa de doze com nada. Uso as proporções dela e nunca a igualei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.craft/1
    en  Three of the cheap thing to one of the dear thing, and salt like you mean it. That is the whole method.
    >>  ............................................
    pt  Três do barato pra um do caro, e sal como quem quer dizer algo. É todo o método.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightcook.craft/2
    en  My grandmother fed a household of twelve on nothing. I use her proportions and I've never matched her.
    >>  ............................................
    pt  Minha avó alimentava uma casa de doze com nada. Uso as proporções dela e nunca a igualei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.craft/1
    en  Her proportions are in her head and then in mine, and now they're nowhere else, and that worries me.
    >>  ............................................
    pt  As proporções dela estavam na cabeça dela e depois na minha, e agora não estão em mais lugar nenhum.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightcook.craft/2
    en  I say them out loud while I cook, to an empty room. It is not eccentricity; it's how they survive.
    >>  ............................................
    pt  Eu digo em voz alta enquanto cozinho, pra uma sala vazia. Não é excentricidade; é como elas sobrevivem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, over years, without either of us calling it teaching.
    >>  ............................................
    pt  Com minha avó, ao longo de anos, sem nenhum de nós chamar de ensino.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightcook.craft/2
    en  Slowly. A pot teaches you at its own pace and there has never been a way to hurry a pot.
    >>  ............................................
    pt  Devagar. Um caldeirão ensina no ritmo dele e nunca houve jeito de apressar um caldeirão.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.craft/1
    en  Her proportions are in her head and then in mine, and now they're nowhere else, and that worries me.
    >>  ............................................
    pt  As proporções dela estavam na cabeça dela e depois na minha, e agora não estão em mais lugar nenhum.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightcook.craft/2
    en  I say them out loud while I cook, to an empty room. It is not eccentricity; it's how they survive.
    >>  ............................................
    pt  Eu digo em voz alta enquanto cozinho, pra uma sala vazia. Não é excentricidade; é como elas sobrevivem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, over years, without either of us calling it teaching.
    >>  ............................................
    pt  Com minha avó, ao longo de anos, sem nenhum de nós chamar de ensino.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightcook.craft/2
    en  Slowly. A pot teaches you at its own pace and there has never been a way to hurry a pot.
    >>  ............................................
    pt  Devagar. Um caldeirão ensina no ritmo dele e nunca houve jeito de apressar um caldeirão.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.craft/1
    en  My gran's proportions, and a great deal of confidence. Mostly the confidence, if I'm honest.
    >>  ............................................
    pt  As proporções da minha avó, e muita confiança. Principalmente a confiança, se for honesto.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightcook.craft/2
    en  Feeding a lot on very little is basically arithmetic with an apron on. I'm quite good at it.
    >>  ............................................
    pt  Alimentar muitos com pouco é basicamente aritmética de avental. Eu sou bem bom nisso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.craft/1
    en  My gran's proportions, and a great deal of confidence. Mostly the confidence, if I'm honest.
    >>  ............................................
    pt  As proporções da minha avó, e muita confiança. Principalmente a confiança, se for honesto.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightcook.craft/2
    en  Feeding a lot on very little is basically arithmetic with an apron on. I'm quite good at it.
    >>  ............................................
    pt  Alimentar muitos com pouco é basicamente aritmética de avental. Eu sou bem bom nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, over years, without either of us calling it teaching.
    >>  ............................................
    pt  Com minha avó, ao longo de anos, sem nenhum de nós chamar de ensino.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightcook.craft/2
    en  Slowly. A pot teaches you at its own pace and there has never been a way to hurry a pot.
    >>  ............................................
    pt  Devagar. Um caldeirão ensina no ritmo dele e nunca houve jeito de apressar um caldeirão.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.craft/1
    en  From my grandmother, who had less than I do and never once served a thin pot. I have served thin pots.
    >>  ............................................
    pt  Com minha avó, que tinha menos que eu e nunca serviu um caldo ralo. Eu já servi.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightcook.craft/2
    en  She was hungry and I have only ever been careful. That is the whole difference and it is not small.
    >>  ............................................
    pt  Ela passava fome e eu só fui cuidadoso. É toda a diferença e não é pequena.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.craft/1
    en  Her proportions are in her head and then in mine, and now they're nowhere else, and that worries me.
    >>  ............................................
    pt  As proporções dela estavam na cabeça dela e depois na minha, e agora não estão em mais lugar nenhum.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightcook.craft/2
    en  I say them out loud while I cook, to an empty room. It is not eccentricity; it's how they survive.
    >>  ............................................
    pt  Eu digo em voz alta enquanto cozinho, pra uma sala vazia. Não é excentricidade; é como elas sobrevivem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.craft/1
    en  My gran's proportions, and a great deal of confidence. Mostly the confidence, if I'm honest.
    >>  ............................................
    pt  As proporções da minha avó, e muita confiança. Principalmente a confiança, se for honesto.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightcook.craft/2
    en  Feeding a lot on very little is basically arithmetic with an apron on. I'm quite good at it.
    >>  ............................................
    pt  Alimentar muitos com pouco é basicamente aritmética de avental. Eu sou bem bom nisso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.craft/1
    en  My gran's proportions, and a great deal of confidence. Mostly the confidence, if I'm honest.
    >>  ............................................
    pt  As proporções da minha avó, e muita confiança. Principalmente a confiança, se for honesto.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightcook.craft/2
    en  Feeding a lot on very little is basically arithmetic with an apron on. I'm quite good at it.
    >>  ............................................
    pt  Alimentar muitos com pouco é basicamente aritmética de avental. Eu sou bem bom nisso.
    >>  ............................................
```

</details>


**Outcome 196 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
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

```text
  dialogue.conversations.work.prof.delightcook.risk/1   [100 chars]
    en  When the stores are short I'm the one who thins the pot, and thinning it is a decision about people.
    >>  ............................................
    pt  Quando os estoques encurtam eu sou quem rala o caldo, e ralar é uma decisão sobre pessoas.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.risk/2   [97 chars]
    en  The four loaves come out of my own flour. No one asked me to and no one would fund it if I asked.
    >>  ............................................
    pt  Os quatro pães saem da minha farinha. Não me pediram e não financiariam se eu pedisse.
    >>  ............................................
```


**Outcome 197 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
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

```text
  dialogue.conversations.work.prof.delightcook.village/1   [104 chars]
    en  Nineteen people eat from that pot most days. It's not a feast and it's the reason they get to the feast.
    >>  ............................................
    pt  Dezenove pessoas comem daquele caldeirão quase todo dia. Não é banquete e é o motivo de chegarem ao banquete.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.village/2   [90 chars]
    en  I know who's short before the mayor does, because I know who comes back for a second bowl.
    >>  ............................................
    pt  Sei quem está apertado antes do prefeito, porque sei quem volta pra segunda tigela.
    >>  ............................................
```


**Outcome 198 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "chefsdelight:delightcook"
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

```text
  dialogue.conversations.work.prof.delightcook.future/1   [91 chars]
    en  I want the pot funded, so that thinning it is a decision about barley and not about people.
    >>  ............................................
    pt  Quero o caldeirão financiado, pra que ralar seja decisão sobre cevada e não sobre pessoas.
    >>  ............................................
  dialogue.conversations.work.prof.delightcook.future/2   [89 chars]
    en  My grandmother's proportions should be in somebody else's head before mine gives them up.
    >>  ............................................
    pt  As proporções da minha avó deviam estar na cabeça de outra pessoa antes que a minha as largue.
    >>  ............................................
```


**Outcome 199 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
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

```text
  dialogue.conversations.work.prof.scribe.task/1   [99 chars]
    en  Copying an account of something nobody here believes happened. I copy it exactly and I add nothing.
    >>  ............................................
    pt  Copiando um relato de algo em que ninguém aqui acredita. Copio exatamente e não acrescento nada.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.task/2   [93 chars]
    en  Cross-checking two versions of the same night. They agree on everything except who was there.
    >>  ............................................
    pt  Confrontando duas versões da mesma noite. Concordam em tudo exceto em quem estava lá.
    >>  ............................................
```


**Outcome 200 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
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

```text
  dialogue.conversations.work.prof.scribe.craft/1   [105 chars]
    en  Copying is the easy half. The hard half is telling a first-hand account from a well-told second-hand one.
    >>  ............................................
    pt  Copiar é a metade fácil. A difícil é distinguir um relato em primeira mão de um segunda-mão bem contado.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.craft/2   [104 chars]
    en  I was taught by a man who made me copy the same page eleven times and burned ten of them in front of me.
    >>  ............................................
    pt  Fui ensinado por um homem que me fez copiar a mesma página onze vezes e queimou dez na minha frente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.scribe.craft/1
    en  He burned ten of my eleven in front of me. He was cruel and he was right, and both are still true.
    >>  ............................................
    pt  Ele queimou dez das minhas onze na minha frente. Era cruel e tinha razão, e as duas coisas continuam verdade.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned exactness after a number of mine was in circulation for four years before anyone caught it.
    >>  ............................................
    pt  Aprendi exatidão depois que um número meu circulou por quatro anos até alguém pegar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of one page. Slowly, in order, and there is no version of exactness that is quick.
    >>  ............................................
    pt  Onze cópias de uma página. Devagar, em ordem, e não há versão rápida de exatidão.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.craft/2
    en  My master, then years. He gave me the discipline and the years gave me the judgement.
    >>  ............................................
    pt  Meu mestre, depois anos. Ele me deu a disciplina e os anos me deram o julgamento.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.craft/1
    en  Copying is the easy half. Telling a first-hand account from a well-told second-hand one is the trade.
    >>  ............................................
    pt  Copiar é a metade fácil. Distinguir um relato em primeira mão de um segunda-mão bem contado é o ofício.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.craft/2
    en  My master made me copy one page eleven times and burned ten of them in front of me. It worked.
    >>  ............................................
    pt  Meu mestre me fez copiar uma página onze vezes e queimou dez na minha frente. Funcionou.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.craft/1
    en  Copying is the easy half. Telling a first-hand account from a well-told second-hand one is the trade.
    >>  ............................................
    pt  Copiar é a metade fácil. Distinguir um relato em primeira mão de um segunda-mão bem contado é o ofício.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.craft/2
    en  My master made me copy one page eleven times and burned ten of them in front of me. It worked.
    >>  ............................................
    pt  Meu mestre me fez copiar uma página onze vezes e queimou dez na minha frente. Funcionou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.craft/1
    en  My master taught me and the librarian finished the job, on Thursdays, over two hours, for nine years.
    >>  ............................................
    pt  Meu mestre me ensinou e o bibliotecário terminou o serviço, nas quintas, por duas horas, durante nove anos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned it from two people who disagreed about eleven things, and the disagreeing taught me most.
    >>  ............................................
    pt  Aprendi com duas pessoas que discordavam sobre onze coisas, e a discordância me ensinou mais.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.craft/1
    en  My master taught me and the librarian finished the job, on Thursdays, over two hours, for nine years.
    >>  ............................................
    pt  Meu mestre me ensinou e o bibliotecário terminou o serviço, nas quintas, por duas horas, durante nove anos.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned it from two people who disagreed about eleven things, and the disagreeing taught me most.
    >>  ............................................
    pt  Aprendi com duas pessoas que discordavam sobre onze coisas, e a discordância me ensinou mais.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.craft/1
    en  My master taught me and the librarian finished the job, on Thursdays, over two hours, for nine years.
    >>  ............................................
    pt  Meu mestre me ensinou e o bibliotecário terminou o serviço, nas quintas, por duas horas, durante nove anos.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned it from two people who disagreed about eleven things, and the disagreeing taught me most.
    >>  ............................................
    pt  Aprendi com duas pessoas que discordavam sobre onze coisas, e a discordância me ensinou mais.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.craft/1
    en  He burned ten of my eleven in front of me. He was cruel and he was right, and both are still true.
    >>  ............................................
    pt  Ele queimou dez das minhas onze na minha frente. Era cruel e tinha razão, e as duas coisas continuam verdade.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned exactness after a number of mine was in circulation for four years before anyone caught it.
    >>  ............................................
    pt  Aprendi exatidão depois que um número meu circulou por quatro anos até alguém pegar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.craft/1
    en  Copying is the easy half. Telling a first-hand account from a well-told second-hand one is the trade.
    >>  ............................................
    pt  Copiar é a metade fácil. Distinguir um relato em primeira mão de um segunda-mão bem contado é o ofício.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.craft/2
    en  My master made me copy one page eleven times and burned ten of them in front of me. It worked.
    >>  ............................................
    pt  Meu mestre me fez copiar uma página onze vezes e queimou dez na minha frente. Funcionou.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.craft/1
    en  Copying is the easy half. Telling a first-hand account from a well-told second-hand one is the trade.
    >>  ............................................
    pt  Copiar é a metade fácil. Distinguir um relato em primeira mão de um segunda-mão bem contado é o ofício.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.craft/2
    en  My master made me copy one page eleven times and burned ten of them in front of me. It worked.
    >>  ............................................
    pt  Meu mestre me fez copiar uma página onze vezes e queimou dez na minha frente. Funcionou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.craft/1
    en  The dull details. Nobody inventing a story remembers that their boots were wet.
    >>  ............................................
    pt  Os detalhes chatos. Ninguém inventando uma história lembra que as botas estavam molhadas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.craft/2
    en  He gave me a list of provisions to copy — deliberately dull, so that I could not be carried along.
    >>  ............................................
    pt  Ele me deu uma lista de mantimentos pra copiar — deliberadamente chata, pra eu não me deixar levar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of one page. Slowly, in order, and there is no version of exactness that is quick.
    >>  ............................................
    pt  Onze cópias de uma página. Devagar, em ordem, e não há versão rápida de exatidão.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.craft/2
    en  My master, then years. He gave me the discipline and the years gave me the judgement.
    >>  ............................................
    pt  Meu mestre, depois anos. Ele me deu a disciplina e os anos me deram o julgamento.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.craft/1
    en  The dull details. Nobody inventing a story remembers that their boots were wet.
    >>  ............................................
    pt  Os detalhes chatos. Ninguém inventando uma história lembra que as botas estavam molhadas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.craft/2
    en  He gave me a list of provisions to copy — deliberately dull, so that I could not be carried along.
    >>  ............................................
    pt  Ele me deu uma lista de mantimentos pra copiar — deliberadamente chata, pra eu não me deixar levar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of one page. Slowly, in order, and there is no version of exactness that is quick.
    >>  ............................................
    pt  Onze cópias de uma página. Devagar, em ordem, e não há versão rápida de exatidão.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.craft/2
    en  My master, then years. He gave me the discipline and the years gave me the judgement.
    >>  ............................................
    pt  Meu mestre, depois anos. Ele me deu a disciplina e os anos me deram o julgamento.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of a provisions list, ten of them burned. He had a flair for pedagogy, my master.
    >>  ............................................
    pt  Onze cópias de uma lista de mantimentos, dez queimadas. Ele tinha talento pra pedagogia, meu mestre.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.craft/2
    en  A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about!
    >>  ............................................
    pt  Um relato em primeira mão acerta o clima e erra o significado. Um em segunda é o contrário!
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of a provisions list, ten of them burned. He had a flair for pedagogy, my master.
    >>  ............................................
    pt  Onze cópias de uma lista de mantimentos, dez queimadas. Ele tinha talento pra pedagogia, meu mestre.
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.craft/2
    en  A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about!
    >>  ............................................
    pt  Um relato em primeira mão acerta o clima e erra o significado. Um em segunda é o contrário!
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of one page. Slowly, in order, and there is no version of exactness that is quick.
    >>  ............................................
    pt  Onze cópias de uma página. Devagar, em ordem, e não há versão rápida de exatidão.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.craft/2
    en  My master, then years. He gave me the discipline and the years gave me the judgement.
    >>  ............................................
    pt  Meu mestre, depois anos. Ele me deu a disciplina e os anos me deram o julgamento.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.craft/1
    en  He burned ten of my eleven in front of me. He was cruel and he was right, and both are still true.
    >>  ............................................
    pt  Ele queimou dez das minhas onze na minha frente. Era cruel e tinha razão, e as duas coisas continuam verdade.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.craft/2
    en  I learned exactness after a number of mine was in circulation for four years before anyone caught it.
    >>  ............................................
    pt  Aprendi exatidão depois que um número meu circulou por quatro anos até alguém pegar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.craft/1
    en  The dull details. Nobody inventing a story remembers that their boots were wet.
    >>  ............................................
    pt  Os detalhes chatos. Ninguém inventando uma história lembra que as botas estavam molhadas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.craft/2
    en  He gave me a list of provisions to copy — deliberately dull, so that I could not be carried along.
    >>  ............................................
    pt  Ele me deu uma lista de mantimentos pra copiar — deliberadamente chata, pra eu não me deixar levar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of a provisions list, ten of them burned. He had a flair for pedagogy, my master.
    >>  ............................................
    pt  Onze cópias de uma lista de mantimentos, dez queimadas. Ele tinha talento pra pedagogia, meu mestre.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.craft/2
    en  A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about!
    >>  ............................................
    pt  Um relato em primeira mão acerta o clima e erra o significado. Um em segunda é o contrário!
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.craft/1
    en  Eleven copies of a provisions list, ten of them burned. He had a flair for pedagogy, my master.
    >>  ............................................
    pt  Onze cópias de uma lista de mantimentos, dez queimadas. Ele tinha talento pra pedagogia, meu mestre.
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.craft/2
    en  A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about!
    >>  ............................................
    pt  Um relato em primeira mão acerta o clima e erra o significado. Um em segunda é o contrário!
    >>  ............................................
```

</details>


**Outcome 201 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
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

```text
  dialogue.conversations.work.prof.scribe.risk/1   [107 chars]
    en  An account I copy wrong becomes the true one in ninety years. That's not dramatic; that's just how it goes.
    >>  ............................................
    pt  Um relato que eu copie errado vira o verdadeiro em noventa anos. Não é drama; é como funciona.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.risk/2   [111 chars]
    en  Some of what I hold would be burned if the wrong person read it, and I keep it in a house with a thatched roof.
    >>  ............................................
    pt  Parte do que eu guardo seria queimada se a pessoa errada lesse, e eu guardo numa casa de telhado de palha.
    >>  ............................................
```


**Outcome 202 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
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

```text
  dialogue.conversations.work.prof.scribe.village/1   [106 chars]
    en  Nothing I do is any use to this place this year. In two hundred years it may be the only use anything was.
    >>  ............................................
    pt  Nada do que eu faço serve a este lugar este ano. Em duzentos anos pode ser a única serventia que houve.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.village/2   [108 chars]
    en  The librarian and I are the whole of the memory here. He keeps what happened; I keep what was said about it.
    >>  ............................................
    pt  O bibliotecário e eu somos toda a memória daqui. Ele guarda o que aconteceu; eu guardo o que disseram.
    >>  ............................................
```


**Outcome 203 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "iceandfire:scribe"
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

```text
  dialogue.conversations.work.prof.scribe.future/1   [114 chars]
    en  A stone room and a second copy of everything, kept somewhere else. That's the whole plan and it's not complicated.
    >>  ............................................
    pt  Um cômodo de pedra e uma segunda cópia de tudo, guardada em outro lugar. É todo o plano e não é complicado.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.future/2   [105 chars]
    en  I'd like the eleven disagreements settled before one of us dies. He thinks that's morbid. He's not wrong.
    >>  ............................................
    pt  Queria as onze discordâncias resolvidas antes de um de nós morrer. Ele acha mórbido. Ele não está errado.
    >>  ............................................
```


**Outcome 204 of 447** — base weight `0`

- Fires when: weighted +100 when `profession` = "vampirism:hunter_expert"
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

```text
  dialogue.conversations.work.prof.hunter_expert.task/1   [103 chars]
    en  Teaching two people to keep watch properly. Most of that is teaching them what not to be frightened of.
    >>  ............................................
    pt  Ensinando duas pessoas a vigiar direito. Boa parte é ensinar do que não ter medo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.task/2   [111 chars]
    en  Walking the boundary at dusk. It's the same walk every evening and the evening it isn't is the one that counts.
    >>  ............................................
    pt  Andando a divisa ao anoitecer. É a mesma caminhada toda noite e a noite em que não for é a que conta.
    >>  ............................................
```

