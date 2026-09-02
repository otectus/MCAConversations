# Hub, greeting and category pages — part 3 of 4

> Continued from [00-hub-part1.md](00-hub-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](00-hub-part1.md) · [part 2](00-hub-part2.md) · [part 3](00-hub-part3.md) · [part 4](00-hub-part4.md)


## Nodes in this file

- [`conversations.cat.profession`](#conversations-cat-profession)
- [`conversations.cat.relationships`](#conversations-cat-relationships)
- [`conversations.cat.village`](#conversations-cat-village)

---

## `conversations.cat.profession` — continued


**Outcome 105 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.leatherworker.the_complaint.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.leatherworker.the_complaint.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.leatherworker.the_complaint.succeeded.respond`
- …where the player's next choices will be: "Cheap at the price." | "I'll let you get back to the pits."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.leatherworker.the_complaint.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.the_complaint.succeeded`: the villager reports. Subject `work.leatherworker.the_smell`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded/1   [95 chars]
    en  The hedge is in and %2$s has said nothing since, which from that quarter is a standing ovation.
    >>  ............................................
    pt  A cerca está feita e %2$s não disse mais nada, o que daquele lado é ovação de pé.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded/2   [111 chars]
    en  I gave them the days and they gave me the lane back. Somebody's child came to watch me scrape a hide last week.
    >>  ............................................
    pt  Dei os dias a eles e eles me devolveram a viela. O filho de alguém veio ver eu raspar um couro semana passada.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded/3   [123 chars]
    en  It cost a week and a hide. Two years of being disliked, ended for a week and a hide, and I did the arithmetic far too late.
    >>  ............................................
    pt  Custou uma semana e um couro. Dois anos de antipatia, encerrados por uma semana e um couro, e eu fiz a conta tarde demais.
    >>  ............................................
```


**Outcome 106 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.leatherworker.old_repair.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.leatherworker.old_repair.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.leatherworker.old_repair.succeeded.respond`
- …where the player's next choices will be: "What was that like to see?" | "Twenty years is good work." | "I'll let you get back to the pits."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.leatherworker.old_repair.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.old_repair.succeeded`: the villager reports. Subject `work.leatherworker.old_repairs`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded/1   [109 chars]
    en  %2$s came back to me last week. I made it twenty years ago and I knew my own stitching before I saw the mark.
    >>  ............................................
    pt  %2$s voltou para mim semana passada. Fiz aquilo há vinte anos e reconheci minha costura antes de ver a marca.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded/2   [111 chars]
    en  Somebody brought in %2$s to be mended. It has outlived the person I made it for, and their son is using it now.
    >>  ............................................
    pt  Alguém trouxe %2$s para consertar. Sobreviveu à pessoa para quem eu fiz, e o filho dela usa agora.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded/3   [113 chars]
    en  %2$s. Twenty years of somebody's hands on it, and the leather has taken the shape of a life I know nothing about.
    >>  ............................................
    pt  %2$s. Vinte anos das mãos de alguém em cima, e o couro tomou o formato de uma vida da qual eu não sei nada.
    >>  ............................................
```


**Outcome 107 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.damaged_volume.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.damaged_volume.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.damaged_volume.blocked.respond`
- …where the player's next choices will be: "What do you lose if you press it flat?" | "Save the ink. A book nobody can read is only paper." | "That's beyond me. I'd only be guessing." | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked`: the villager reports. Subject `work.librarian.damaged_volume`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked/1   [183 chars]
    en  I took %2$s out of the flood cupboard this morning and %3$s had got right through the gathering. I can press the pages flat or I can save the ink. Not both, not with what I have here.
    >>  ............................................
    pt  Tirei %2$s do armário alagado hoje de manhã, e %3$s tinha atravessado o caderno inteiro. Posso prensar as páginas ou posso salvar a tinta. Não os dois, não com o que tenho aqui.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked/2   [161 chars]
    en  I have %2$s open on the back table and I have been standing over it for an hour. %3$s is winning. I need something that draws water and I have nothing that does.
    >>  ............................................
    pt  Deixei %2$s aberto na mesa do fundo e passei uma hora parada em frente a ele. %3$s está ganhando. Preciso de algo que puxe a água e não tenho nada que sirva.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked/3   [77 chars]
    en  Do not touch the back table. %2$s is on it, and %3$s reached it before I did.
    >>  ............................................
    pt  Não encoste na mesa do fundo. %2$s está lá em cima, e %3$s chegou antes de mim.
    >>  ............................................
```


**Outcome 108 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.damaged_volume.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.damaged_volume.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.damaged_volume.active.respond`
- …where the player's next choices will be: "How much of it has come back?" | "I'm glad it's out of the water, at least." | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.active`: the villager reports. Subject `work.librarian.damaged_volume`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active/1   [117 chars]
    en  %2$s is under cloth and weights. I change them twice a day and I look at it more often than that, which helps nobody.
    >>  ............................................
    pt  Deixei %2$s sob panos e pesos. Troco duas vezes por dia e olho muito mais do que isso, o que não ajuda ninguém.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active/2   [92 chars]
    en  Slow. %2$s is drying the way everything dries: from the outside, and not where I need it to.
    >>  ............................................
    pt  Devagar. %2$s está secando como tudo seca: pelas bordas, e não onde eu precisava.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active/3   [142 chars]
    en  Better than yesterday, worse than I would like. %2$s sits under two cloths and a stone, and I have been told off twice for lifting them early.
    >>  ............................................
    pt  Melhor que ontem, pior do que eu queria. %2$s está sob dois panos e uma pedra, e já me repreenderam duas vezes por levantá-los cedo demais.
    >>  ............................................
```


**Outcome 109 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.damaged_volume.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.damaged_volume.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.damaged_volume.succeeded.respond`
- …where the player's next choices will be: "You saved it. I only carried something heavy." | "Can the whole of it be read now?" | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.succeeded`: the villager celebrates. Subject `work.librarian.damaged_volume`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded/1   [123 chars]
    en  %2$s held. Not prettily — it will never sit flat again — but every name in it can be read, and that was the whole argument.
    >>  ............................................
    pt  %2$s resistiu. Não ficou bonito — nunca mais vai assentar direito — mas dá para ler cada nome ali dentro, e a discussão toda era essa.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded/2   [110 chars]
    en  I have put %2$s back on the shelf. I stood there a while afterwards. It is only a book. I stood there a while.
    >>  ............................................
    pt  Recoloquei %2$s na prateleira. Fiquei parada ali um tempo depois. É só um livro. Fiquei parada ali um tempo.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded/3   [118 chars]
    en  It is done. %2$s will never close properly again, and every word in it can be read. I will take that trade every time.
    >>  ............................................
    pt  Está pronto. %2$s nunca mais vai fechar direito, e cada palavra dentro dele pode ser lida. Eu faço essa troca todas as vezes.
    >>  ............................................
```


**Outcome 110 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.damaged_volume.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.damaged_volume.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.damaged_volume.failed.respond`
- …where the player's next choices will be: "That's a real loss. I'm sorry." | "Did any of it survive?" | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.failed`: the villager reminisces. Subject `work.librarian.damaged_volume`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed/1   [105 chars]
    en  %2$s did not come back. %3$s took the middle of it, and the middle was the part anyone would have wanted.
    >>  ............................................
    pt  %2$s não voltou. %3$s levou o miolo, e o miolo era justamente a parte que alguém iria querer.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed/2   [101 chars]
    en  I stopped working on %2$s three days ago. %3$s had already won and I was only keeping myself company.
    >>  ............................................
    pt  Parei de trabalhar em %2$s há três dias. %3$s já tinha vencido, e eu só estava me fazendo companhia.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed/3   [135 chars]
    en  I have stopped. %2$s is past helping — %3$s went straight through the middle of it, and the middle was the whole reason anyone kept it.
    >>  ............................................
    pt  Eu parei. %2$s já não tem salvação — %3$s passou direto pelo miolo, e o miolo era todo o motivo de alguém ter guardado.
    >>  ............................................
```


**Outcome 111 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.acquisition.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.acquisition.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.acquisition.blocked.respond`
- …where the player's next choices will be: "Ask the village to go in on it together." | "Why does that one matter so much?" | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.acquisition.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.blocked`: the villager complains. Subject `work.librarian.acquisition`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked/1   [126 chars]
    en  %2$s has %3$s and wants more for it than the library has had in a year. I have been doing sums that do not work since Tuesday.
    >>  ............................................
    pt  %2$s tem %3$s e quer mais por ele do que a biblioteca teve o ano inteiro. Desde terça faço contas que não fecham.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked/2   [132 chars]
    en  There is %3$s to be had from %2$s and I cannot make the money exist. I have counted it four ways and it is the same money each time.
    >>  ............................................
    pt  Dá para conseguir %3$s com %2$s, e eu não consigo fazer o dinheiro existir. Contei de quatro jeitos e é o mesmo dinheiro em todos.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked/3   [102 chars]
    en  %2$s will not hold %3$s past the market. If it goes, it goes to somebody who wants it for the binding.
    >>  ............................................
    pt  %2$s não vai segurar %3$s depois da feira. Se for embora, vai para alguém que o quer pela encadernação.
    >>  ............................................
```


**Outcome 112 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.acquisition.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.acquisition.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.acquisition.succeeded.respond`
- …where the player's next choices will be: "Can I see it?" | "They gave because you asked properly." | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.acquisition.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.succeeded`: the villager reports. Subject `work.librarian.acquisition`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded/1   [132 chars]
    en  %2$s is here. Eleven households and the innkeeper, and the innkeeper gave twice what he could spare, which I am not going to forget.
    >>  ............................................
    pt  %2$s está aqui. Onze famílias e o estalajadeiro, e o estalajadeiro deu o dobro do que podia, o que eu não vou esquecer.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded/2   [123 chars]
    en  It came. I have had %2$s on the desk for two days and I have not shelved it because shelving it means I stop looking at it.
    >>  ............................................
    pt  Chegou. Faz dois dias que %2$s está na mesa e eu não guardei, porque guardar significa parar de olhar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded/3   [74 chars]
    en  We bought %2$s. We — I keep saying we and I keep meaning it, which is new.
    >>  ............................................
    pt  Compramos %2$s. Nós — vivo dizendo nós e vivo querendo dizer isso mesmo, o que é novo.
    >>  ............................................
```


**Outcome 113 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.reader_need.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.reader_need.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.reader_need.active.respond`
- …where the player's next choices will be: "Keep going. Three weeks is early days." | "How are you teaching it?" | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.reader_need.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.active`: the villager reports. Subject `work.librarian.literacy`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active/1   [139 chars]
    en  I have been teaching %2$s their letters at the end of the day. It is going slowly and I do not know whether the slowness is theirs or mine.
    >>  ............................................
    pt  Venho ensinando as letras a %2$s no fim do dia. Vai devagar, e não sei se a lentidão é deles ou minha.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active/2   [119 chars]
    en  %2$s comes on the days I am busiest and I have never once said so. I am not sure whether that is kindness or cowardice.
    >>  ............................................
    pt  %2$s aparece nos dias em que estou mais ocupada, e eu nunca disse nada. Não sei se isso é bondade ou covardia.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active/3   [158 chars]
    en  Three weeks with %2$s and we are at the point where they can read a word and not the sentence around it, which everybody warned me about and nobody explained.
    >>  ............................................
    pt  Três semanas com %2$s e chegamos ao ponto em que se lê uma palavra e não a frase em volta, o que todo mundo avisou e ninguém explicou.
    >>  ............................................
```


**Outcome 114 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.librarian.reader_need.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.librarian.reader_need.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.librarian.reader_need.succeeded.respond`
- …where the player's next choices will be: "That's the best thing I've heard this week." | "What will they read next?" | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.librarian.reader_need.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.succeeded`: the villager reports. Subject `work.librarian.literacy`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded/1   [123 chars]
    en  %2$s read a whole page to me on Thursday without stopping. I had to go and reshelve something that did not need reshelving.
    >>  ............................................
    pt  %2$s me leu uma página inteira na quinta sem parar. Tive de ir guardar uma coisa que não precisava ser guardada.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded/2   [152 chars]
    en  It happened the way everyone said it would — nothing, nothing, nothing, and then %2$s was reading and had been for a minute before either of us noticed.
    >>  ............................................
    pt  Aconteceu como todo mundo dizia — nada, nada, nada, e de repente %2$s estava lendo, e já fazia um minuto quando percebemos.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded/3   [138 chars]
    en  %2$s has started reading things I did not give them. That is the end of my part of it, and it is the best possible way for my part to end.
    >>  ............................................
    pt  %2$s começou a ler coisas que eu não dei. É o fim da minha parte nisso, e é o melhor jeito possível de a minha parte acabar.
    >>  ............................................
```


**Outcome 115 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.rushed_foundation.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.rushed_foundation.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.rushed_foundation.blocked.respond`
- …where the player's next choices will be: "What happens in four years?" | "Refuse to build it that way." | "That's a rotten position to be in." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.blocked`: the villager reports. Subject `work.mason.foundations`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked/1   [100 chars]
    en  They want %2$s up by the festival and there is %3$s, and I have said so twice in front of witnesses.
    >>  ............................................
    pt  Querem %2$s de pé até a festa e existe %3$s, e eu já disse isso duas vezes diante de testemunhas.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked/2   [81 chars]
    en  %3$s under %2$s. It will stand for four years and look perfect for three of them.
    >>  ............................................
    pt  %3$s embaixo de %2$s. Vai ficar de pé por quatro anos e parecer perfeito em três deles.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked/3   [117 chars]
    en  I can build %2$s fast or I can build it once. Everybody nods at that sentence and then asks about the festival again.
    >>  ............................................
    pt  Posso construir %2$s rápido ou posso construir uma vez só. Todo mundo concorda com essa frase e depois pergunta da festa de novo.
    >>  ............................................
```


**Outcome 116 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.rushed_foundation.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.rushed_foundation.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.rushed_foundation.succeeded.respond`
- …where the player's next choices will be: "Does it bother you not to see them finished?" | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.rushed_foundation.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.succeeded`: the villager reports. Subject `work.mason.foundations`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded/1   [125 chars]
    en  They gave me the extra fortnight. %2$s is on proper footings and it will be standing when this village has forgotten my name.
    >>  ............................................
    pt  Me deram as duas semanas extras. %2$s está sobre sapatas de verdade e vai estar de pé quando esta vila tiver esquecido meu nome.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded/2   [97 chars]
    en  I put it in writing and they read it and something changed. I have no idea which sentence did it.
    >>  ............................................
    pt  Botei por escrito, eles leram e alguma coisa mudou. Não faço ideia de qual frase fez isso.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded/3   [114 chars]
    en  Dug down another three feet. The festival happened around a hole in the ground, and nobody has mentioned it since.
    >>  ............................................
    pt  Cavamos mais um metro. A festa aconteceu em volta de um buraco no chão, e ninguém comentou desde então.
    >>  ............................................
```


**Outcome 117 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.bad_stone.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.bad_stone.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.bad_stone.blocked.respond`
- …where the player's next choices will be: "Could you use it somewhere it matters less?" | "I'll bring you good stone." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.bad_stone.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.bad_stone.blocked`: the villager reports. Subject `work.mason.the_quarry`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked/1   [104 chars]
    en  %2$s. Half a cartload of it, and half a cartload is exactly the amount that tempts you to use it anyway.
    >>  ............................................
    pt  %2$s. Meia carroça disso, e meia carroça é exatamente a quantidade que tenta você a usar assim mesmo.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked/2   [83 chars]
    en  I have sorted through %2$s all morning and found enough for two courses out of six.
    >>  ............................................
    pt  Passei a manhã separando %2$s e achei o suficiente para duas fiadas de seis.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked/3   [99 chars]
    en  %2$s came in and the quarryman is four days away, so the wall stops until somebody rides out there.
    >>  ............................................
    pt  %2$s chegou e o pedreiro da pedreira está a quatro dias daqui, então o muro para até alguém cavalgar até lá.
    >>  ............................................
```


**Outcome 118 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.bad_stone.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.bad_stone.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.bad_stone.succeeded.respond`
- …where the player's next choices will be: "It'll outlast all of us." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.bad_stone.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.bad_stone.succeeded`: the villager reports. Subject `work.mason.the_quarry`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.succeeded/1   [105 chars]
    en  Six courses up and true. I ran a line along the top this morning and it did not want correcting anywhere.
    >>  ............................................
    pt  Seis fiadas de pé e no prumo. Passei uma linha no topo hoje de manhã e não pediu correção em lugar nenhum.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.succeeded/2   [111 chars]
    en  It went up in three days once the stone was right. The waiting was three weeks and the building was three days.
    >>  ............................................
    pt  Subiu em três dias depois que a pedra ficou certa. A espera foi três semanas e a obra foi três dias.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.succeeded/3   [104 chars]
    en  The corners are the ones you brought. I have not told anybody that and I know it every time I walk past.
    >>  ............................................
    pt  Os cantos são os que você trouxe. Não contei isso a ninguém e eu sei toda vez que passo por ali.
    >>  ............................................
```


**Outcome 119 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.quick_apprentice.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.quick_apprentice.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.quick_apprentice.active.respond`
- …where the player's next choices will be: "How do you teach patience?" | "Let them build one badly and see it." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.quick_apprentice.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.quick_apprentice.active`: the villager reports. Subject `work.mason.apprentices`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active/1   [97 chars]
    en  I have %2$s who can lay twice what I can in a day and will not check a single course with a line.
    >>  ............................................
    pt  Tenho %2$s que assenta o dobro do que eu num dia e não confere uma fiada sequer com a linha.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active/2   [103 chars]
    en  %2$s is better with a chisel at seventeen than I was at thirty. That is the problem, not the good news.
    >>  ............................................
    pt  %2$s é melhor com o cinzel aos dezessete do que eu era aos trinta. Esse é o problema, não a boa notícia.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active/3   [123 chars]
    en  Everything %2$s builds looks right. In eleven years half of it will still be right, and neither of us will know which half.
    >>  ............................................
    pt  Tudo o que %2$s constrói parece certo. Em onze anos metade ainda vai estar certa, e nenhum de nós vai saber qual metade.
    >>  ............................................
```


**Outcome 120 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mason.quick_apprentice.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mason.quick_apprentice.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mason.quick_apprentice.succeeded.respond`
- …where the player's next choices will be: "That's the trade passed on." | "I'll let you get back to the course."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mason.quick_apprentice.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.quick_apprentice.succeeded`: the villager reports. Subject `work.mason.apprentices`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded/1   [103 chars]
    en  We took the garden wall down together. %2$s did not say a word for an hour and now checks every course.
    >>  ............................................
    pt  Desmanchamos o muro da horta juntos. %2$s não disse uma palavra por uma hora e agora confere cada fiada.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded/2   [94 chars]
    en  It worked, and it cost me a wall and a very quiet afternoon, and I would do it again tomorrow.
    >>  ............................................
    pt  Funcionou, e custou um muro e uma tarde muito silenciosa, e eu faria de novo amanhã.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded/3   [116 chars]
    en  %2$s asked for the line before I offered it, last Tuesday. That is the whole of the trade passed on, in one gesture.
    >>  ............................................
    pt  %2$s pediu a linha antes de eu oferecer, na terça passada. É o ofício inteiro passado adiante, num gesto só.
    >>  ............................................
```


**Outcome 121 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.refused_job.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.refused_job.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.refused_job.blocked.respond`
- …where the player's next choices will be: "Can you do it?" | "I could speak to them for you." | "You deserve a fair chance at it." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.refused_job.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.blocked`: the villager reports. Subject `work.nitwit.small_usefulness`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.blocked/1   [103 chars]
    en  I asked %3$s if I could help with %2$s and got a laugh, and the laugh was meant kindly, which is worse.
    >>  ............................................
    pt  Perguntei a %3$s se eu podia ajudar com %2$s e recebi uma risada, e a risada era bem-intencionada, o que é pior.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked/2   [136 chars]
    en  %2$s is a thing I can do. %3$s took it back off me halfway through and finished it themselves, quickly, so that it would not be awkward.
    >>  ............................................
    pt  %2$s é uma coisa que eu sei fazer. %3$s tirou de mim no meio e terminou sozinho, rápido, para não ficar constrangedor.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.blocked/3   [95 chars]
    en  Nobody says I cannot do %2$s. They just always have somebody else in mind by the time I arrive.
    >>  ............................................
    pt  Ninguém diz que eu não posso fazer %2$s. Só que sempre já têm outra pessoa em mente quando eu chego.
    >>  ............................................
```


**Outcome 122 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.refused_job.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.refused_job.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.refused_job.succeeded.respond`
- …where the player's next choices will be: "How did they come round?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.refused_job.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.refused_job.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.refused_job.succeeded`: the villager reports. Subject `work.nitwit.small_usefulness`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded/1   [88 chars]
    en  I do %2$s now. Every day. Nobody announced it — they just stopped finding somebody else.
    >>  ............................................
    pt  Eu faço %2$s agora. Todo dia. Ninguém anunciou — só pararam de procurar outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded/2   [96 chars]
    en  It took a month of turning up before anybody asked, and then one person asked, and now three do.
    >>  ............................................
    pt  Levou um mês aparecendo antes de alguém pedir, e aí uma pessoa pediu, e agora três pedem.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.refused_job.succeeded/3   [125 chars]
    en  %2$s is mine. I am slower and I have not made a single mistake, and one of those facts gets mentioned and the other does not.
    >>  ............................................
    pt  %2$s é meu. Sou mais devagar e não cometi um erro sequer, e um desses fatos é mencionado e o outro não.
    >>  ............................................
```


**Outcome 123 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.i_noticed.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.i_noticed.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.i_noticed.active.respond`
- …where the player's next choices will be: "Tell me exactly what you saw." | "Who would actually listen?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.i_noticed.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.i_noticed.active`: the villager reports. Subject `work.nitwit.what_i_notice`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.active/1   [92 chars]
    en  There was %2$s yesterday and I told two people and both of them said thank you in the voice.
    >>  ............................................
    pt  Teve %2$s ontem e eu contei a duas pessoas e as duas disseram obrigado naquele tom.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active/2   [109 chars]
    en  %2$s. I am fairly sure it matters. I am also fairly sure nobody is going to check, because of who noticed it.
    >>  ............................................
    pt  %2$s. Tenho quase certeza de que importa. Também tenho quase certeza de que ninguém vai conferir, por causa de quem reparou.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.active/3   [112 chars]
    en  I see things. It is the one thing I am reliably better at, and it is worth nothing if the telling does not work.
    >>  ............................................
    pt  Eu reparo em coisas. É a única coisa em que sou confiavelmente melhor, e não vale nada se o contar não funciona.
    >>  ............................................
```


**Outcome 124 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.i_noticed.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.i_noticed.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.i_noticed.succeeded.respond`
- …where the player's next choices will be: "They asked you first." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.i_noticed.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.i_noticed.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.i_noticed.succeeded`: the villager reports. Subject `work.nitwit.what_i_notice`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded/1   [103 chars]
    en  %2$s turned out to matter. The guard went and looked and came back and said so where people could hear.
    >>  ............................................
    pt  %2$s acabou importando. A guarda foi lá, olhou, voltou e disse isso onde as pessoas podiam ouvir.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded/2   [89 chars]
    en  I was right, and being right in public once is worth about a year of being right quietly.
    >>  ............................................
    pt  Eu estava certa, e estar certa em público uma vez vale por um ano de estar certa em silêncio.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.i_noticed.succeeded/3   [123 chars]
    en  Somebody asked me yesterday whether I had seen anything odd. Asked me first. I have thought about it more than it deserves.
    >>  ............................................
    pt  Alguém me perguntou ontem se eu tinha visto algo estranho. Perguntou a mim primeiro. Pensei nisso mais do que merece.
    >>  ............................................
```


**Outcome 125 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.left_out.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.left_out.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.left_out.active.respond`
- …where the player's next choices will be: "What would you have said?" | "Say it again and I'll back you." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.left_out.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left_out.active`: the villager reports. Subject `work.nitwit.being_talked_over`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.active/1   [93 chars]
    en  There was %2$s and nobody told me it was happening, and it was about a thing I use every day.
    >>  ............................................
    pt  Teve %2$s e ninguém me avisou que ia acontecer, e era sobre uma coisa que eu uso todo dia.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active/2   [122 chars]
    en  I went to %2$s. I said one sentence and three people started talking, and by the time they finished the subject had moved.
    >>  ............................................
    pt  Fui a %2$s. Disse uma frase e três pessoas começaram a falar, e quando terminaram o assunto já tinha mudado.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.active/3   [77 chars]
    en  %2$s decided something that affects my morning, and I found out from a child.
    >>  ............................................
    pt  %2$s decidiu uma coisa que afeta minha manhã, e eu fiquei sabendo por uma criança.
    >>  ............................................
```


**Outcome 126 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.nitwit.left_out.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.nitwit.left_out.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.nitwit.left_out.succeeded.respond`
- …where the player's next choices will be: "You said it yourself." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.nitwit.left_out.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.nitwit.left_out.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.nitwit.left_out.succeeded`: the villager reports. Subject `work.nitwit.being_talked_over`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:nitwit` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.work.nitwit.left_out.succeeded/1   [91 chars]
    en  I said it standing up and somebody waited for me to finish, and then the rope got replaced.
    >>  ............................................
    pt  Eu disse de pé e alguém esperou eu terminar, e aí a corda foi trocada.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.succeeded/2   [81 chars]
    en  It took twenty seconds. Twenty seconds and a year of working out how to get them.
    >>  ............................................
    pt  Levou vinte segundos. Vinte segundos e um ano descobrindo como consegui-los.
    >>  ............................................
  dialogue.conversations.scene.work.nitwit.left_out.succeeded/3   [111 chars]
    en  Nobody has said anything about it since, which is exactly right. It was a sentence about a rope, not a triumph.
    >>  ............................................
    pt  Ninguém comentou nada desde então, o que está exatamente certo. Era uma frase sobre uma corda, não um triunfo.
    >>  ............................................
```


**Outcome 127 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.trade_decision.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.trade_decision.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.trade_decision.blocked.respond`
- …where the player's next choices will be: "Why that trade in particular?" | "Go in this week. I'll come back and hear how it went." | "That reason sounds like fear wearing a coat." | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.trade_decision.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.blocked`: the villager complains. Subject `work.none.between_trades`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.blocked/1   [137 chars]
    en  I have been standing outside %2$s for three mornings now, working up to going in. %3$s is what stops me, and I know how thin that sounds.
    >>  ............................................
    pt  Faz três manhãs que fico parado na frente d%2$s, criando coragem para entrar. %3$s é o que me trava, e sei como isso soa frágil.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked/2   [116 chars]
    en  Everyone here has a thing they are. I have %2$s in my head and %3$s in the way of it, and neither of them will move.
    >>  ............................................
    pt  Todo mundo aqui é alguma coisa. Eu tenho %2$s na cabeça e %3$s no caminho, e nenhum dos dois sai do lugar.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.blocked/3   [113 chars]
    en  I could go to %2$s tomorrow. I have been able to go tomorrow for a month. %3$s keeps making tomorrow further off.
    >>  ............................................
    pt  Eu poderia ir a %2$s amanhã. Faz um mês que posso ir amanhã. %3$s vai empurrando o amanhã para mais longe.
    >>  ............................................
```


**Outcome 128 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.trade_decision.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.trade_decision.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.trade_decision.succeeded.respond`
- …where the player's next choices will be: "You did the hard part yourself." | "What was the first day like?" | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.trade_decision.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.succeeded`: the villager reports. Subject `work.none.between_trades`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.succeeded/1   [109 chars]
    en  I went to %2$s. They said come back Thursday, which is not yes and is a great deal more than I had on Monday.
    >>  ............................................
    pt  Fui a %2$s. Disseram para eu voltar quinta, o que não é um sim e é muito mais do que eu tinha na segunda.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded/2   [114 chars]
    en  I asked. Out loud, to a person, at %2$s. Whatever happens next I have done the part I had been calling impossible.
    >>  ............................................
    pt  Eu pedi. Em voz alta, para uma pessoa, n%2$s. Aconteça o que acontecer, já fiz a parte que eu vinha chamando de impossível.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.succeeded/3   [120 chars]
    en  It is arranged. Three days a week to start, and I have already been told I hold things wrong, and I did not mind at all.
    >>  ............................................
    pt  Está combinado. Três dias por semana para começar, e já me disseram que seguro as coisas errado, e eu nem me importei.
    >>  ............................................
```


**Outcome 129 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.trade_decision.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.trade_decision.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.trade_decision.failed.respond`
- …where the player's next choices will be: "That took courage, and it still hurt. Both are true." | "What will you try instead?" | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.trade_decision.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.trade_decision.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.trade_decision.failed`: the villager complains. Subject `work.none.between_trades`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.trade_decision.failed/1   [103 chars]
    en  %2$s said no. Politely, and with a reason, and the reason was %3$s, so at least I was not imagining it.
    >>  ............................................
    pt  %2$s disse não. Com educação, e com um motivo, e o motivo era %3$s, então pelo menos não era coisa da minha cabeça.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed/2   [132 chars]
    en  I went and it did not take. Two weeks and then a conversation where we both agreed it was for the best, and only one of us meant it.
    >>  ............................................
    pt  Eu fui e não deu certo. Duas semanas e depois uma conversa em que nós dois concordamos que era o melhor, e só um dos dois falava sério.
    >>  ............................................
  dialogue.conversations.scene.work.none.trade_decision.failed/3   [130 chars]
    en  It is over. I am not going back to %2$s and I would rather talk about almost anything else, but you asked honestly so there it is.
    >>  ............................................
    pt  Acabou. Não vou voltar a %2$s e eu preferiria falar de quase qualquer outra coisa, mas você perguntou de verdade, então está aí.
    >>  ............................................
```


**Outcome 130 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.quiet_usefulness.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.quiet_usefulness.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.quiet_usefulness.active.respond`
- …where the player's next choices will be: "That's work. It just hasn't got a name." | "Who asked you to start doing it?" | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.quiet_usefulness.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.active`: the villager reports. Subject `work.none.freedom`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.active/1   [149 chars]
    en  I spend most mornings %2$s. Nobody pays me and nobody would notice if I stopped, and I have started to think that second part is the interesting one.
    >>  ............................................
    pt  Passo quase todas as manhãs %2$s. Ninguém me paga e ninguém notaria se eu parasse, e comecei a achar que essa segunda parte é a interessante.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active/2   [95 chars]
    en  %2$s takes about two hours and it is the two hours I would keep if I had to give the rest away.
    >>  ............................................
    pt  %2$s leva umas duas horas, e são as duas horas que eu manteria se tivesse de abrir mão do resto.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.active/3   [132 chars]
    en  Ask anyone what I do and they will say nothing. Ask them who does %2$s and they will think for a moment and then look uncomfortable.
    >>  ............................................
    pt  Pergunte a qualquer um o que eu faço e vão dizer nada. Pergunte quem faz %2$s e vão pensar um instante e depois ficar sem graça.
    >>  ............................................
```


**Outcome 131 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.quiet_usefulness.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.quiet_usefulness.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.quiet_usefulness.succeeded.respond`
- …where the player's next choices will be: "About time somebody said it." | "Does that change what you'll do next?" | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.quiet_usefulness.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.quiet_usefulness.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.quiet_usefulness.succeeded`: the villager reports. Subject `work.none.freedom`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded/1   [117 chars]
    en  Two people thanked me this week for %2$s. Two. After a year. I have thought about it more than I would like to admit.
    >>  ............................................
    pt  Duas pessoas me agradeceram esta semana por %2$s. Duas. Depois de um ano. Pensei nisso mais do que gostaria de admitir.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded/2   [98 chars]
    en  The mason called me by name in front of other people and said what I do. I went home and sat down.
    >>  ............................................
    pt  O pedreiro me chamou pelo nome na frente dos outros e disse o que eu faço. Fui para casa e sentei.
    >>  ............................................
  dialogue.conversations.scene.work.none.quiet_usefulness.succeeded/3   [131 chars]
    en  Somebody asked who does %2$s and somebody else said my name straight away, without having to work it out. That was the whole thing.
    >>  ............................................
    pt  Alguém perguntou quem faz %2$s e outra pessoa disse meu nome na hora, sem precisar pensar. Foi isso, inteiro.
    >>  ............................................
```


**Outcome 132 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.old_admiration.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.old_admiration.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.old_admiration.blocked.respond`
- …where the player's next choices will be: "What happened to that idea?" | "A child's idea can still be a good one." | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.old_admiration.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.blocked`: the villager reminisces. Subject `work.none.admiration`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.blocked/1   [164 chars]
    en  When I was small I was going to work at %2$s. I have not thought about that in years and then somebody mentioned it and I have thought of nothing else for two days.
    >>  ............................................
    pt  Quando eu era pequeno, ia trabalhar n%2$s. Não pensava nisso há anos, e aí alguém mencionou e faz dois dias que não penso em outra coisa.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked/2   [138 chars]
    en  There was a version of me that ended up at %2$s. I can see him quite clearly. He is more certain than I am and I do not entirely like him.
    >>  ............................................
    pt  Houve uma versão de mim que foi parar n%2$s. Consigo vê-lo bem claramente. Ele é mais seguro do que eu e eu não gosto muito dele.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.blocked/3   [134 chars]
    en  I wanted %2$s before I knew what wanting a trade even was. That is either a sign or it is a child's idea, and I cannot tell from here.
    >>  ............................................
    pt  Eu queria %2$s antes de saber o que era querer um ofício. Isso é um sinal ou é ideia de criança, e daqui eu não consigo distinguir.
    >>  ............................................
```


**Outcome 133 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.none.old_admiration.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.none.old_admiration.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.none.old_admiration.succeeded.respond`
- …where the player's next choices will be: "Then I'll remember you said it." | "What did they tell you about the work?" | "I'll let you get on with your day."

```text
POOL   dialogue key: dialogue.conversations.scene.work.none.old_admiration.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.none.old_admiration.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.none.old_admiration.succeeded`: the villager reports. Subject `work.none.admiration`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:none` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.none.old_admiration.succeeded/1   [123 chars]
    en  I asked at %2$s. Only what the work is like — not for anything — and they talked to me for an hour like a person who might.
    >>  ............................................
    pt  Perguntei n%2$s. Só como é o trabalho — não pedi nada — e conversaram comigo uma hora como se eu fosse alguém que poderia.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded/2   [131 chars]
    en  I have started paying attention again. That is all it is so far, and after twenty years of looking away it feels like a great deal.
    >>  ............................................
    pt  Voltei a prestar atenção. É só isso, por enquanto, e depois de vinte anos desviando o olhar, parece muita coisa.
    >>  ............................................
  dialogue.conversations.scene.work.none.old_admiration.succeeded/3   [125 chars]
    en  I am going to ask properly at the turn of the season. I have said that out loud now, to you, which is the point of saying it.
    >>  ............................................
    pt  Vou pedir de verdade na virada da estação. Já falei em voz alta agora, para você, que é justamente para isso que se fala.
    >>  ............................................
```


**Outcome 134 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.hard_lambing.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.hard_lambing.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.hard_lambing.blocked.respond`
- …where the player's next choices will be: "What actually goes wrong?" | "I'll bring wool to keep them warm." | "Ask the village for a night's help." | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.hard_lambing.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.blocked`: the villager reports. Subject `work.shepherd.lambing`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked/1   [103 chars]
    en  I have %2$s in trouble and %3$s on top of it, and I am running out of hands and hours at the same rate.
    >>  ............................................
    pt  Tenho %2$s com problema e %3$s por cima, e estou ficando sem mãos e sem horas no mesmo ritmo.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked/2   [111 chars]
    en  %3$s. %2$s would be manageable on its own and the two together are how you lose a whole night and half a flock.
    >>  ............................................
    pt  %3$s. %2$s sozinha seria administrável, e as duas juntas são como se perde uma noite inteira e metade do rebanho.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.blocked/3   [103 chars]
    en  %2$s needs watching and I need sleeping, and one of those is going to lose. It is not going to be %2$s.
    >>  ............................................
    pt  %2$s precisa de vigia e eu preciso de sono, e um dos dois vai perder. Não vai ser %2$s.
    >>  ............................................
```


**Outcome 135 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.hard_lambing.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.hard_lambing.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.hard_lambing.succeeded.respond`
- …where the player's next choices will be: "How do you take the ones you lose?" | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.hard_lambing.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.hard_lambing.succeeded`: the villager reports. Subject `work.shepherd.lambing`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded/1   [100 chars]
    en  %2$s came through and both lambs are up. I lost two elsewhere and I am counting this as a good year.
    >>  ............................................
    pt  %2$s superou e os dois cordeiros estão de pé. Perdi dois em outro lugar e estou contando este como um ano bom.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded/2   [100 chars]
    en  It is over. Nine nights. I slept for eleven hours and woke up in the same position I fell asleep in.
    >>  ............................................
    pt  Acabou. Nove noites. Dormi onze horas e acordei na mesma posição em que peguei no sono.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.hard_lambing.succeeded/3   [130 chars]
    en  The small one from %2$s is the strongest of the lot now, which happens about a third of the time and never stops being a surprise.
    >>  ............................................
    pt  O pequeno de %2$s é o mais forte de todos agora, o que acontece em um terço das vezes e nunca deixa de surpreender.
    >>  ............................................
```


**Outcome 136 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.old_dog.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.old_dog.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.old_dog.active.respond`
- …where the player's next choices will be: "What will you do about it?" | "She's had a good working life." | "Start training the young one now." | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.old_dog.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.active`: the villager reports. Subject `work.shepherd.the_dog`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.active/1   [97 chars]
    en  She has %2$s and she still goes up the hill, because I ask her to and she has never once said no.
    >>  ............................................
    pt  Ela tem %2$s e ainda sobe a encosta, porque eu peço e ela nunca disse não uma vez sequer.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active/2   [154 chars]
    en  %2$s. Eleven years old. Every morning I decide whether today is the day I leave her at the gate, and every morning she is already at the gate ahead of me.
    >>  ............................................
    pt  %2$s. Onze anos. Toda manhã eu decido se hoje é o dia de deixá-la no portão, e toda manhã ela já está no portão antes de mim.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.active/3   [122 chars]
    en  The flock is fine. It is the dog. %2$s means she has about a season of the top field left in her and she does not know it.
    >>  ............................................
    pt  O rebanho está bem. É a cadela. %2$s quer dizer que resta a ela uma estação de campo alto, e ela não sabe.
    >>  ............................................
```


**Outcome 137 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.old_dog.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.old_dog.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.old_dog.succeeded.respond`
- …where the player's next choices will be: "You gave her a way to keep working." | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.old_dog.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.old_dog.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.old_dog.succeeded`: the villager reports. Subject `work.shepherd.the_dog`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded/1   [80 chars]
    en  She has a pup to teach and she has taken to it like she was waiting to be asked.
    >>  ............................................
    pt  Ela tem um filhote para ensinar e assumiu isso como se estivesse esperando ser convidada.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded/2   [111 chars]
    en  Low field and a student. She is louder than she has been in two years, mostly at the pup, mostly about nothing.
    >>  ............................................
    pt  Campo baixo e uma aluna. Está mais barulhenta do que esteve em dois anos, quase toda com a filhote, quase toda por nada.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.old_dog.succeeded/3   [141 chars]
    en  It worked. She still comes to the gate every morning and she goes back to the fold on her own, and I did not have to be the one who told her.
    >>  ............................................
    pt  Deu certo. Ela ainda vem ao portão toda manhã e volta ao aprisco sozinha, e eu não precisei ser quem contou a ela.
    >>  ............................................
```


**Outcome 138 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.broken_fence.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.broken_fence.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.broken_fence.blocked.respond`
- …where the player's next choices will be: "I'll come up and hold the other end." | "What keeps people off the hill?" | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.broken_fence.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.broken_fence.blocked`: the villager reports. Subject `work.shepherd.high_pasture`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked/1   [80 chars]
    en  %2$s is down in three places and it is a two-person job, and there is one of me.
    >>  ............................................
    pt  %2$s está caída em três pontos e é serviço para duas pessoas, e existe uma de mim.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked/2   [96 chars]
    en  I have asked twice about %2$s. Everybody agrees it needs doing and everybody is busy on the day.
    >>  ............................................
    pt  Já perguntei duas vezes sobre %2$s. Todo mundo concorda que precisa ser feito e todo mundo está ocupado no dia.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.blocked/3   [133 chars]
    en  %2$s keeps my sheep off somebody else's corn, so it is not only my fence, and I appear to be the only person who thinks that follows.
    >>  ............................................
    pt  %2$s mantém minhas ovelhas longe do milho dos outros, então não é só a minha cerca, e parece que sou a única que acha que isso decorre.
    >>  ............................................
```


**Outcome 139 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shepherd.broken_fence.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shepherd.broken_fence.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shepherd.broken_fence.succeeded.respond`
- …where the player's next choices will be: "Asking worked, then." | "I'll let you get back to the flock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shepherd.broken_fence.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shepherd.broken_fence.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shepherd.broken_fence.succeeded`: the villager reports. Subject `work.shepherd.high_pasture`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shepherd` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded/1   [86 chars]
    en  %2$s is up and straight. Two of us, one morning, and nine months of me putting it off.
    >>  ............................................
    pt  %2$s está de pé e reta. Duas pessoas, uma manhã, e nove meses de eu adiando.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded/2   [96 chars]
    en  It is done, and whoever helped got fed, and they have been back twice since without being asked.
    >>  ............................................
    pt  Está feito, e quem ajudou comeu, e voltou duas vezes desde então sem ser chamado.
    >>  ............................................
  dialogue.conversations.scene.work.shepherd.broken_fence.succeeded/3   [97 chars]
    en  %2$s will hold five years. I would like to have been less proud about the nine months beforehand.
    >>  ............................................
    pt  %2$s aguenta cinco anos. Eu gostaria de ter sido menos orgulhosa nos nove meses anteriores.
    >>  ............................................
```


**Outcome 140 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.failed_tool.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.failed_tool.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.failed_tool.blocked.respond`
- …where the player's next choices will be: "Was it your work or their use?" | "I'll bring iron to remake it." | "Tell them the part that was yours." | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.blocked`: the villager reports. Subject `work.toolsmith.edges`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked/1   [104 chars]
    en  %2$s I made came apart in somebody's hands last week. It was %3$s and I still cannot put the thing down.
    >>  ............................................
    pt  %2$s que eu fiz se desfez nas mãos de alguém semana passada. Foi %3$s e eu ainda não consigo largar aquilo.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked/2   [124 chars]
    en  %3$s. That is what did it. I have had the pieces on the bench for four days working out which part of that sentence is mine.
    >>  ............................................
    pt  %3$s. Foi isso. Estou com os pedaços na bancada há quatro dias descobrindo qual parte dessa frase é minha.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked/3   [112 chars]
    en  Nobody was hurt. I want to say that first, because it is the only part of %2$s breaking that I would not change.
    >>  ............................................
    pt  Ninguém se machucou. Quero dizer isso primeiro, porque é a única parte de %2$s quebrar que eu não mudaria.
    >>  ............................................
```


**Outcome 141 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.failed_tool.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.failed_tool.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.failed_tool.succeeded.respond`
- …where the player's next choices will be: "What do you do differently now?" | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.failed_tool.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.succeeded`: the villager reports. Subject `work.toolsmith.edges`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded/1   [112 chars]
    en  %2$s is remade and handed over, and I told them which half was mine, and they told me the other half unprompted.
    >>  ............................................
    pt  %2$s foi refeita e entregue, e eu disse qual metade era minha, e eles contaram a outra metade sem eu pedir.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded/2   [113 chars]
    en  I cut the old one open. There was a fold in the metal from the first heat, which I could have caught and did not.
    >>  ............................................
    pt  Abri a antiga. Havia uma dobra no metal desde o primeiro aquecimento, que eu poderia ter pego e não peguei.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded/3   [123 chars]
    en  It is settled. They still buy from me, which I had genuinely not assumed, and the new one is better because of the old one.
    >>  ............................................
    pt  Está resolvido. Continuam comprando de mim, coisa que eu genuinamente não tinha como certa, e a nova é melhor por causa da velha.
    >>  ............................................
```


**Outcome 142 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.cheap_customer.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.cheap_customer.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.cheap_customer.active.respond`
- …where the player's next choices will be: "So what do you offer them?" | "Hold your standard on the edge." | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.cheap_customer.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.cheap_customer.active`: the villager reports. Subject `work.toolsmith.the_people_who_use_them`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active/1   [94 chars]
    en  %2$s wants an edge for half what an edge costs, and I can do it, and it will fail in a season.
    >>  ............................................
    pt  %2$s quer um fio pela metade do que um fio custa, e eu consigo fazer, e vai falhar em uma estação.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active/2   [131 chars]
    en  I keep being asked to make something cheaper by %2$s. There is no cheaper. There is only worse, and worse pretending to be cheaper.
    >>  ............................................
    pt  %2$s vive me pedindo algo mais barato. Não existe mais barato. Existe pior, e pior fingindo ser mais barato.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active/3   [107 chars]
    en  %2$s is not wrong to ask. That is what makes it difficult. If they were being greedy I would simply say no.
    >>  ............................................
    pt  %2$s não está errado em pedir. É isso que torna difícil. Se fosse ganância, eu simplesmente diria não.
    >>  ............................................
```


**Outcome 143 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.cheap_customer.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.cheap_customer.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.cheap_customer.succeeded.respond`
- …where the player's next choices will be: "Repair first is a good arrangement." | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.cheap_customer.succeeded`: the villager reports. Subject `work.toolsmith.the_people_who_use_them`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded/1   [80 chars]
    en  %2$s took the repair and came back in the autumn and paid for a new one in full.
    >>  ............................................
    pt  %2$s levou o conserto, voltou no outono e pagou uma nova integralmente.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded/2   [113 chars]
    en  It worked out. I mended rather than sold, and I have had three people since ask for the same arrangement by name.
    >>  ............................................
    pt  Deu certo. Consertei em vez de vender, e desde então três pessoas pediram o mesmo arranjo pelo nome.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded/3   [135 chars]
    en  %2$s tells people I am expensive. They also tell people the edge is still good after two winters, and both of those are advertisements.
    >>  ............................................
    pt  %2$s diz por aí que eu sou cara. Também diz que o fio continua bom depois de dois invernos, e as duas coisas são propaganda.
    >>  ............................................
```


**Outcome 144 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.heirloom_repair.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.heirloom_repair.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.heirloom_repair.active.respond`
- …where the player's next choices will be: "What are they really asking for?" | "Mend it and make a new one too." | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.heirloom_repair.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.heirloom_repair.active`: the villager reports. Subject `work.toolsmith.repairs`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active/1   [116 chars]
    en  Somebody has brought me %2$s and asked me to bring it back, and the honest answer is that a new one would be better.
    >>  ............................................
    pt  Alguém me trouxe %2$s e pediu para eu recuperar, e a resposta honesta é que uma nova seria melhor.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active/2   [111 chars]
    en  %2$s. The metal is tired all the way through. I can make it hold for a season and I cannot make it what it was.
    >>  ............................................
    pt  %2$s. O metal está cansado por inteiro. Consigo fazer aguentar uma estação e não consigo devolver o que era.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active/3   [119 chars]
    en  I have %2$s on the bench and I have been looking at it for two days, because this is not really a question about metal.
    >>  ............................................
    pt  Tenho %2$s na bancada e faz dois dias que olho para aquilo, porque isso não é bem uma questão sobre metal.
    >>  ............................................
```


**Outcome 145 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.toolsmith.heirloom_repair.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.toolsmith.heirloom_repair.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond`
- …where the player's next choices will be: "You understood what they needed." | "I'll let you get back to the forge."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.heirloom_repair.succeeded`: the villager reports. Subject `work.toolsmith.repairs`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded/1   [95 chars]
    en  They took both. %2$s is on a wall now and the new one is in the ground, which is exactly right.
    >>  ............................................
    pt  Levaram as duas. %2$s está numa parede agora e a nova está na terra, o que é exatamente certo.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded/2   [109 chars]
    en  I handed them over together and said nothing, and there was a long pause, and then they said thank you twice.
    >>  ............................................
    pt  Entreguei as duas juntas e não disse nada, e houve uma pausa longa, e depois disseram obrigado duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded/3   [107 chars]
    en  %2$s will never cut again and it did not need to. That took me eleven years to understand about this trade.
    >>  ............................................
    pt  %2$s nunca mais vai cortar e não precisava. Levei onze anos para entender isso sobre este ofício.
    >>  ............................................
```


**Outcome 146 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.uneasy_commission.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.uneasy_commission.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond`
- …where the player's next choices will be: "What do you refuse to make?" | "Turn that commission down." | "That's a lot to weigh on your own." | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.blocked`: the villager reports. Subject `work.weaponsmith.who_buys`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked/1   [87 chars]
    en  %3$s came in and asked for %2$s and would give me no reason, and I have not started it.
    >>  ............................................
    pt  %3$s apareceu e pediu %2$s e não me deu motivo nenhum, e eu não comecei.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked/2   [104 chars]
    en  %2$s, for %3$s. I have made forty of those and this is the first one I have left on the bench overnight.
    >>  ............................................
    pt  %2$s, para %3$s. Já fiz quarenta dessas e é a primeira que eu deixo na bancada de um dia para o outro.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked/3   [131 chars]
    en  There is nothing wrong with the order. %3$s paid properly and asked politely. It is the way they asked that I keep going back over.
    >>  ............................................
    pt  Não há nada de errado com o pedido. %3$s pagou direito e pediu com educação. É o jeito de pedir que eu fico remoendo.
    >>  ............................................
```


**Outcome 147 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.uneasy_commission.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.uneasy_commission.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond`
- …where the player's next choices will be: "Do you ever find out if you were right?" | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.succeeded`: the villager reports. Subject `work.weaponsmith.who_buys`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded/1   [110 chars]
    en  I gave %2$s the money back and said why. They were angry for about a minute and then they were something else.
    >>  ............................................
    pt  Devolvi o dinheiro a %2$s e disse por quê. Ficaram bravos por um minuto e depois ficaram outra coisa.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded/2   [126 chars]
    en  Returned. And the strange part is that %2$s came back a month later to buy an axe, and told me the reason without being asked.
    >>  ............................................
    pt  Devolvida. E o estranho é que %2$s voltou um mês depois para comprar um machado, e me contou o motivo sem eu perguntar.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded/3   [131 chars]
    en  I refused it. Nothing happened afterwards, which is the outcome you never get to enjoy, because you cannot see what did not happen.
    >>  ............................................
    pt  Recusei. Nada aconteceu depois, que é o resultado que a gente nunca chega a aproveitar, porque não dá para ver o que não aconteceu.
    >>  ............................................
```


**Outcome 148 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.returned_blade.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.returned_blade.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.returned_blade.active.respond`
- …where the player's next choices will be: "Would you rather know?" | "I'll bring iron for the repair." | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.returned_blade.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.returned_blade.active`: the villager reports. Subject `work.weaponsmith.edges`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active/1   [122 chars]
    en  One of the watch brought a blade back with %2$s, which means it was used, which means somebody was on the other end of it.
    >>  ............................................
    pt  Alguém da ronda trouxe uma lâmina de volta com %2$s, o que quer dizer que foi usada, o que quer dizer que alguém estava do outro lado.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active/2   [124 chars]
    en  %2$s. She handed it over and said nothing about the night, and I did not ask, and I have been thinking about the not asking.
    >>  ............................................
    pt  %2$s. Ela entregou e não disse nada sobre a noite, e eu não perguntei, e venho pensando no não ter perguntado.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active/3   [135 chars]
    en  I can fix %2$s in a morning. What I cannot do is hand it back without knowing whether it worked, and I am not going to ask that either.
    >>  ............................................
    pt  Consigo consertar %2$s numa manhã. O que eu não consigo é devolver sem saber se funcionou, e também não vou perguntar isso.
    >>  ............................................
```


**Outcome 149 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.returned_blade.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.returned_blade.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.returned_blade.succeeded.respond`
- …where the player's next choices will be: "Your discretion was the kind part." | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.returned_blade.succeeded`: the villager reports. Subject `work.weaponsmith.edges`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded/1   [104 chars]
    en  Repaired and handed back before her watch. She weighed it, nodded, and that was the entire conversation.
    >>  ............................................
    pt  Consertada e devolvida antes da ronda dela. Ela pesou na mão, assentiu, e a conversa foi essa inteira.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded/2   [106 chars]
    en  It is done. She has not told me about the night and I expect she never will, and that is how it should be.
    >>  ............................................
    pt  Está feito. Ela não me contou sobre a noite e imagino que nunca conte, e é assim que deve ser.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded/3   [120 chars]
    en  New grip, new edge. She noticed the grip and looked at me and said thank you in a way that was about more than the grip.
    >>  ............................................
    pt  Punho novo, fio novo. Ela reparou no punho, olhou para mim e disse obrigada de um jeito que era sobre mais do que o punho.
    >>  ............................................
```


**Outcome 150 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.trade_argument.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.trade_argument.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.trade_argument.active.respond`
- …where the player's next choices will be: "How would you settle it?" | "Let the watch carry both and choose." | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.trade_argument.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.trade_argument.active`: the villager reports. Subject `work.weaponsmith.balance`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active/1   [98 chars]
    en  %2$s and I disagree about weight, and we have been disagreeing about it, politely, for four years.
    >>  ............................................
    pt  %2$s e eu discordamos sobre peso, e vimos discordando, com educação, há quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active/2   [121 chars]
    en  %2$s says a heavier blade is safer. I say a blade nobody can hold for an hour is the most dangerous thing in the village.
    >>  ............................................
    pt  %2$s diz que lâmina mais pesada é mais segura. Eu digo que uma lâmina que ninguém segura por uma hora é a coisa mais perigosa da vila.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active/3   [104 chars]
    en  It sounds like a small argument with %2$s. It decides what the watch carries, so it is not small at all.
    >>  ............................................
    pt  Parece uma discussão pequena com %2$s. Decide o que a ronda carrega, então não é nada pequena.
    >>  ............................................
```


**Outcome 151 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.weaponsmith.trade_argument.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.weaponsmith.trade_argument.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.weaponsmith.trade_argument.succeeded.respond`
- …where the player's next choices will be: "Asking the people who carry them was right." | "I'll let you get back to the hammer."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.trade_argument.succeeded`: the villager reports. Subject `work.weaponsmith.balance`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded/1   [80 chars]
    en  The watch chose lighter, five to two, and %2$s took it better than I would have.
    >>  ............................................
    pt  A ronda escolheu a mais leve, cinco a dois, e %2$s levou melhor do que eu teria levado.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded/2   [138 chars]
    en  We ran the month. I was right about the weight and wrong about %2$s, who turned out to be interested in the answer rather than in winning.
    >>  ............................................
    pt  Fizemos o mês. Eu estava certa sobre o peso e errada sobre %2$s, que acabou se interessando pela resposta em vez de vencer.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded/3   [119 chars]
    en  Settled by the people who carry them, which is how it should have been settled in the first year rather than the fifth.
    >>  ............................................
    pt  Resolvido por quem carrega, que é como deveria ter sido resolvido no primeiro ano, não no quinto.
    >>  ............................................
```


**Outcome 152 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.enderian.lost_consignment.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.enderian.lost_consignment.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.enderian.lost_consignment.blocked.respond`
- …where the player's next choices will be: "Who buys that sort of thing?" | "I'll bring you pearls." | "Go and fetch it yourself." | "I'll let you get back to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.enderian.lost_consignment.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.blocked`: the villager reports. Subject `work.enderian.pearls`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked/1   [120 chars]
    en  %2$s should have been here a fortnight ago and there was %3$s, and I have three buyers waiting and nothing to show them.
    >>  ............................................
    pt  %2$s deveria ter chegado há duas semanas e houve %3$s, e eu tenho três compradores esperando e nada para mostrar.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked/2   [114 chars]
    en  %3$s. Which means %2$s is sitting in somebody's yard four days from here and neither of us can afford to fetch it.
    >>  ............................................
    pt  %3$s. O que quer dizer que %2$s está no pátio de alguém a quatro dias daqui e nenhum de nós dois pode pagar para buscar.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.blocked/3   [110 chars]
    en  I have paid for %2$s. That is the part people forget — a thing that has not arrived is still a thing I bought.
    >>  ............................................
    pt  Eu paguei por %2$s. É a parte que as pessoas esquecem — uma coisa que não chegou continua sendo uma coisa que eu comprei.
    >>  ............................................
```


**Outcome 153 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.enderian.lost_consignment.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.enderian.lost_consignment.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.enderian.lost_consignment.succeeded.respond`
- …where the player's next choices will be: "What was the walk like?" | "I'll let you get back to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.lost_consignment.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.enderian.lost_consignment.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.lost_consignment.succeeded`: the villager reports. Subject `work.enderian.pearls`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded/1   [89 chars]
    en  %2$s is in the shop. Eight days out and eight back, and I have never enjoyed a walk more.
    >>  ............................................
    pt  %2$s está na loja. Oito dias de ida e oito de volta, e eu nunca aproveitei tanto uma caminhada.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded/2   [111 chars]
    en  It arrived. All three buyers waited, which surprised me, and one of them apologised for doubting that it would.
    >>  ............................................
    pt  Chegou. Os três compradores esperaram, o que me surpreendeu, e um deles pediu desculpa por ter duvidado.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.lost_consignment.succeeded/3   [132 chars]
    en  %2$s came in and the season is whole again. I have started keeping a second carrier on retainer, which I should have done years ago.
    >>  ............................................
    pt  %2$s chegou e a estação está inteira de novo. Passei a manter um segundo transportador de sobreaviso, coisa que eu deveria ter feito anos atrás.
    >>  ............................................
```


**Outcome 154 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.enderian.being_looked_at.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.enderian.being_looked_at.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.enderian.being_looked_at.active.respond`
- …where the player's next choices will be: "What would make it easier?" | "Their discomfort shouldn't be your job." | "I'll let you get back to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.enderian.being_looked_at.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.being_looked_at.active`: the villager reports. Subject `work.enderian.being_looked_at`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.active/1   [91 chars]
    en  At %2$s I get looked at and then looked away from, in that order, about nine times an hour.
    >>  ............................................
    pt  Em %2$s eu sou olhada e depois evitada, nessa ordem, umas nove vezes por hora.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active/2   [107 chars]
    en  I have learned to keep my eyes on the goods at %2$s. It is easier for everybody and I resent that it works.
    >>  ............................................
    pt  Aprendi a manter os olhos na mercadoria em %2$s. É mais fácil para todo mundo e me irrita que funcione.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.active/3   [162 chars]
    en  %2$s is fine, mostly. It is the moment somebody realises they have been staring that is uncomfortable, and it is uncomfortable for them, and I end up managing it.
    >>  ............................................
    pt  %2$s está bem, na maior parte. É o instante em que alguém percebe que estava encarando que fica desconfortável, e o desconforto é deles, e sobra para mim administrar.
    >>  ............................................
```


**Outcome 155 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.enderian.being_looked_at.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.enderian.being_looked_at.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.enderian.being_looked_at.succeeded.respond`
- …where the player's next choices will be: "Arguing about prices is a good sign." | "I'll let you get back to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.being_looked_at.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.enderian.being_looked_at.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.being_looked_at.succeeded`: the villager reports. Subject `work.enderian.being_looked_at`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded/1   [140 chars]
    en  Two of the regular stallholders at %2$s have started saving me a place, and one of them argues with me about prices, which is the real sign.
    >>  ............................................
    pt  Dois feirantes fixos em %2$s passaram a guardar um lugar para mim, e um deles discute preço comigo, que é o sinal de verdade.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded/2   [106 chars]
    en  It has settled. I have been here long enough now that the staring is done by visitors, and visitors leave.
    >>  ............................................
    pt  Assentou. Já estou aqui tempo suficiente para que quem encara sejam os visitantes, e visitantes vão embora.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.being_looked_at.succeeded/3   [103 chars]
    en  %2$s is ordinary these days. I did nothing to make that happen except keep turning up for eleven years.
    >>  ............................................
    pt  %2$s é comum hoje em dia. Não fiz nada para isso acontecer além de continuar aparecendo por onze anos.
    >>  ............................................
```


**Outcome 156 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.enderian.the_quiet_place.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.enderian.the_quiet_place.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.enderian.the_quiet_place.succeeded.respond`
- …where the player's next choices will be: "What do you get from it?" | "That explanation is yours to keep." | "I'll let you get back to your stock."

```text
POOL   dialogue key: dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.enderian.the_quiet_place.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.enderian.the_quiet_place.succeeded`: the villager reports. Subject `work.enderian.the_silence`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:enderian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded/1   [92 chars]
    en  I go to %2$s about once a week. People find that unnerving and I have stopped explaining it.
    >>  ............................................
    pt  Vou a %2$s mais ou menos uma vez por semana. As pessoas acham isso perturbador e eu parei de explicar.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded/2   [101 chars]
    en  %2$s. There is nothing there. That is precisely the point and it is the part that will not translate.
    >>  ............................................
    pt  %2$s. Não tem nada lá. É exatamente esse o ponto e é a parte que não se traduz.
    >>  ............................................
  dialogue.conversations.scene.work.enderian.the_quiet_place.succeeded/3   [122 chars]
    en  Somebody followed me to %2$s last year to see what I did. What I did was sit down for an hour, and they were disappointed.
    >>  ............................................
    pt  Alguém me seguiu até %2$s ano passado para ver o que eu fazia. O que eu fiz foi sentar por uma hora, e a pessoa ficou decepcionada.
    >>  ............................................
```


**Outcome 157 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.prototype_fault.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.prototype_fault.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.prototype_fault.blocked.respond`
- …where the player's next choices will be: "Can you make it fail on purpose?" | "I can bring you iron for the part." | "Build a simpler one that always works." | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.blocked`: the villager reports. Subject `work.engineer.prototypes`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked/1   [130 chars]
    en  %2$s works four times out of five and I have not found the fifth. It is %3$s, and I can make it happen, and I cannot make it stop.
    >>  ............................................
    pt  %2$s funciona quatro vezes em cinco e eu não achei a quinta. É %3$s, e eu consigo reproduzir, e não consigo fazer parar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked/2   [112 chars]
    en  Do not lean on the bench. %2$s is in pieces because of %3$s, and I have the pieces in the order I took them off.
    >>  ............................................
    pt  Não encoste na bancada. %2$s está em pedaços por causa de %3$s, e eu tenho as peças na ordem em que tirei.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked/3   [134 chars]
    en  I have been at %2$s since first light. %3$s. I know that much and I have known that much since Tuesday, which is the frustrating part.
    >>  ............................................
    pt  Estou n%2$s desde o raiar do dia. %3$s. Sei isso e sei isso desde terça, que é a parte frustrante.
    >>  ............................................
```


**Outcome 158 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.prototype_fault.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.prototype_fault.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.prototype_fault.succeeded.respond`
- …where the player's next choices will be: "What was actually wrong with it?" | "You stayed with it after it stopped being interesting." | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.prototype_fault.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.succeeded`: the villager reports. Subject `work.engineer.prototypes`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded/1   [102 chars]
    en  %2$s has run forty times without stopping. I counted all forty. I am aware of what that says about me.
    >>  ............................................
    pt  %2$s rodou quarenta vezes sem parar. Contei as quarenta. Tenho noção do que isso diz sobre mim.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded/2   [131 chars]
    en  It works. Not because I was clever — because I was slow, and stayed with it after the point where being clever had stopped helping.
    >>  ............................................
    pt  Funciona. Não porque eu fui esperta — porque fui lenta, e continuei depois do ponto em que ser esperta parou de ajudar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded/3   [119 chars]
    en  Fixed. And the fix was two lines of file work in a place I had looked at nine times, which is exactly how it always is.
    >>  ............................................
    pt  Resolvido. E o conserto foram duas passadas de lima num lugar que eu já tinha olhado nove vezes, que é exatamente como sempre é.
    >>  ............................................
```


**Outcome 159 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.prototype_fault.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.prototype_fault.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.prototype_fault.failed.respond`
- …where the player's next choices will be: "What did you keep out of it?" | "Knowing when to walk away is a skill too." | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.prototype_fault.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.failed`: the villager reminisces. Subject `work.engineer.prototypes`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed/1   [142 chars]
    en  I have taken %2$s apart. %3$s was never going to be solved with what I have, and pretending otherwise was costing the village a working bench.
    >>  ............................................
    pt  Desmontei %2$s. %3$s nunca ia se resolver com o que eu tenho, e fingir o contrário custava à vila uma bancada funcionando.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed/2   [137 chars]
    en  It is off the bench. I kept the drawings and the good bearings and I burned the rest, which was more satisfying than it should have been.
    >>  ............................................
    pt  Saiu da bancada. Guardei os desenhos e os bons mancais e queimei o resto, o que foi mais satisfatório do que deveria.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed/3   [123 chars]
    en  %2$s is finished, in the sense of stopped. I got two useful things out of it and neither is the thing I was trying to make.
    >>  ............................................
    pt  %2$s acabou, no sentido de parou. Tirei duas coisas úteis disso e nenhuma é a coisa que eu tentava fazer.
    >>  ............................................
```


**Outcome 160 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.maintenance_round.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.maintenance_round.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.maintenance_round.active.respond`
- …where the player's next choices will be: "Set a fixed round and tell people it's fixed." | "What breaks first when you're late?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.maintenance_round.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.active`: the villager reports. Subject `work.engineer.maintenance`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active/1   [109 chars]
    en  %2$s wants greasing every eleven days and gets it every twenty, because nobody sends for me until it squeals.
    >>  ............................................
    pt  %2$s precisa de graxa a cada onze dias e recebe a cada vinte, porque ninguém me chama até guinchar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active/2   [133 chars]
    en  I have a list. %2$s is on it three times, which means three times I went and it was fine, and the fourth time is the one I will miss.
    >>  ............................................
    pt  Tenho uma lista. %2$s aparece três vezes, o que significa três vezes que fui e estava bem, e a quarta é a que eu vou perder.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active/3   [129 chars]
    en  Half my week is %2$s and the other three like it. It is not what anyone thinks I do and it is what keeps the village standing up.
    >>  ............................................
    pt  Metade da minha semana é %2$s e as outras três iguais a ele. Não é o que as pessoas acham que eu faço e é o que mantém a vila de pé.
    >>  ............................................
```


**Outcome 161 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.maintenance_round.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.maintenance_round.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.maintenance_round.succeeded.respond`
- …where the player's next choices will be: "The better it works, the less anyone sees you." | "What else should be on the round?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.maintenance_round.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.succeeded`: the villager reports. Subject `work.engineer.maintenance`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded/1   [125 chars]
    en  Three rounds in and nothing has squealed once. %2$s got its grease on the day, and I have started to enjoy the boredom of it.
    >>  ............................................
    pt  Três rondas e nada guinchou nenhuma vez. %2$s recebeu graxa no dia, e comecei a gostar do tédio disso.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded/2   [120 chars]
    en  It is written up at the well and two people have already asked me to add things to it, which I am counting as a triumph.
    >>  ............................................
    pt  Está afixado no poço e duas pessoas já me pediram para incluir coisas, o que estou contando como triunfo.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded/3   [111 chars]
    en  The round works. I fixed nothing this week. That is the whole point and it took me four years to understand it.
    >>  ............................................
    pt  A ronda funciona. Não consertei nada esta semana. É esse o objetivo e levei quatro anos para entender.
    >>  ............................................
```


**Outcome 162 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.doubted_idea.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.doubted_idea.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.doubted_idea.blocked.respond`
- …where the player's next choices will be: "Show me. I'll use it and tell you what's wrong." | "What would convince them?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.doubted_idea.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.blocked`: the villager complains. Subject `work.engineer.skepticism`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked/1   [101 chars]
    en  Half the village thinks %2$s is a toy. They are polite about it, which is worse than if they said so.
    >>  ............................................
    pt  Metade da vila acha que %2$s é brinquedo. São educados quanto a isso, o que é pior do que se dissessem.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked/2   [128 chars]
    en  I asked for two days of help with %2$s and was told, kindly, that people have real work. I have been chewing on that for a week.
    >>  ............................................
    pt  Pedi dois dias de ajuda com %2$s e me disseram, com gentileza, que as pessoas têm trabalho de verdade. Faz uma semana que eu remoo isso.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked/3   [147 chars]
    en  Nobody will use %2$s until it is finished and I cannot finish %2$s without somebody using it. That is the whole difficulty and it is not technical.
    >>  ............................................
    pt  Ninguém vai usar %2$s até estar pronto, e eu não consigo terminar %2$s sem alguém usando. É essa a dificuldade inteira, e não é técnica.
    >>  ............................................
```


**Outcome 163 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.engineer.doubted_idea.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.engineer.doubted_idea.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.engineer.doubted_idea.succeeded.respond`
- …where the player's next choices will be: "You were right and you waited it out." | "What will you build next?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.engineer.doubted_idea.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.succeeded`: the villager reports. Subject `work.engineer.skepticism`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded/1   [129 chars]
    en  The miller used %2$s twice this week without being asked. He has not said anything about it, which is how you know it has worked.
    >>  ............................................
    pt  O moleiro usou %2$s duas vezes esta semana sem que eu pedisse. Não comentou nada, que é como se sabe que funcionou.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded/2   [107 chars]
    en  Somebody else asked me to build one. Not admire one. Build one. I had to go and stand outside for a minute.
    >>  ............................................
    pt  Outra pessoa me pediu para construir um. Não admirar um. Construir. Tive de sair e ficar um minuto lá fora.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded/3   [121 chars]
    en  %2$s is on the list of things the village has, rather than the list of things I am doing. Those are very different lists.
    >>  ............................................
    pt  %2$s está na lista de coisas que a vila tem, em vez da lista de coisas que eu estou fazendo. São listas muito diferentes.
    >>  ............................................
```


**Outcome 164 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.failed_bed.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.failed_bed.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.failed_bed.blocked.respond`
- …where the player's next choices will be: "Can the bed be brought back?" | "I'll bring you bone meal." | "Warn the autumn orders now." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.blocked`: the villager reports. Subject `work.florist.the_beds`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked/1   [85 chars]
    en  %2$s has gone, all of it, to %3$s, and it was eight months of work standing in a row.
    >>  ............................................
    pt  %2$s se foi, inteiro, por causa de %3$s, e eram oito meses de trabalho enfileirados.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked/2   [112 chars]
    en  %3$s took %2$s in a night. I went out in the morning and stood there for a while before I did anything sensible.
    >>  ............................................
    pt  %3$s levou %2$s numa noite. Saí de manhã e fiquei parada ali um tempo antes de fazer qualquer coisa sensata.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked/3   [105 chars]
    en  %2$s is bare. I have orders for the autumn and the autumn is grown in the spring, and the spring is gone.
    >>  ............................................
    pt  %2$s está pelado. Tenho encomendas para o outono, e o outono se planta na primavera, e a primavera acabou.
    >>  ............................................
```


**Outcome 165 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.failed_bed.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.failed_bed.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.failed_bed.succeeded.respond`
- …where the player's next choices will be: "How did the four conversations go?" | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.failed_bed.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.succeeded`: the villager reports. Subject `work.florist.the_beds`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.succeeded/1   [94 chars]
    en  %2$s is planted and coming through. Not what it was, and coming through, and I will take that.
    >>  ............................................
    pt  %2$s está plantado e brotando. Não é o que era, e está brotando, e eu aceito.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.succeeded/2   [99 chars]
    en  I told the four households and every one of them was decent about it, and two of them ordered more.
    >>  ............................................
    pt  Avisei as quatro casas e todas foram decentes, e duas encomendaram mais.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.succeeded/3   [104 chars]
    en  The soil took the feeding. %2$s will be a year behind for a year and then it will simply be a bed again.
    >>  ............................................
    pt  O solo aceitou o adubo. %2$s vai estar um ano atrasado por um ano e depois vai voltar a ser simplesmente um canteiro.
    >>  ............................................
```


**Outcome 166 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.funeral_order.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.funeral_order.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.funeral_order.active.respond`
- …where the player's next choices will be: "How do you decide, then?" | "Make the small correct one." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.funeral_order.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.funeral_order.active`: the villager reports. Subject `work.florist.occasions`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active/1   [122 chars]
    en  I have %2$s on Thursday and the family cannot tell me what they want, which is entirely reasonable and leaves me guessing.
    >>  ............................................
    pt  Tenho %2$s na quinta e a família não consegue me dizer o que quer, o que é inteiramente razoável e me deixa adivinhando.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active/2   [120 chars]
    en  %2$s. They said something simple and then described something enormous, and both of those were true when they said them.
    >>  ............................................
    pt  %2$s. Disseram uma coisa simples e depois descreveram uma coisa enorme, e as duas eram verdade quando disseram.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active/3   [112 chars]
    en  %2$s is the hardest kind of order, because getting it wrong is not a bad sale. It is a thing they will remember.
    >>  ............................................
    pt  %2$s é o tipo mais difícil de encomenda, porque errar não é uma venda ruim. É uma coisa de que vão lembrar.
    >>  ............................................
```


**Outcome 167 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.funeral_order.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.funeral_order.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.funeral_order.succeeded.respond`
- …where the player's next choices will be: "Asking about her garden was the whole thing." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.funeral_order.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.funeral_order.succeeded`: the villager reports. Subject `work.florist.occasions`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.succeeded/1   [100 chars]
    en  %2$s went well. I asked about her garden and made what was in it, and her son held it the whole way.
    >>  ............................................
    pt  %2$s correu bem. Perguntei sobre o jardim dela e fiz o que havia lá, e o filho segurou o caminho inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.succeeded/2   [116 chars]
    en  Small and specific. Somebody asked me afterwards who had told me what she grew, and the answer was that I had asked.
    >>  ............................................
    pt  Pequeno e específico. Alguém me perguntou depois quem tinha me contado o que ela plantava, e a resposta é que eu perguntei.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.succeeded/3   [125 chars]
    en  It was right. I will never be certain it was right, and nobody said otherwise, and in this trade that is as close as you get.
    >>  ............................................
    pt  Estava certo. Nunca vou ter certeza de que estava certo, e ninguém disse o contrário, e neste ofício é o mais perto que se chega.
    >>  ............................................
```


**Outcome 168 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.stubborn_variety.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.stubborn_variety.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.stubborn_variety.active.respond`
- …where the player's next choices will be: "Why keep at it?" | "Give it another four years." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.stubborn_variety.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.stubborn_variety.active`: the villager reports. Subject `work.florist.a_plant_that_wont_take`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active/1   [85 chars]
    en  I have been trying to grow %2$s for four years and I have got as far as leaves twice.
    >>  ............................................
    pt  Faz quatro anos que eu tento cultivar %2$s e cheguei até as folhas duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active/2   [121 chars]
    en  %2$s. Everybody who knows anything tells me it will not do here. I have decided to keep failing at it for a while longer.
    >>  ............................................
    pt  %2$s. Todo mundo que entende do assunto me diz que não vinga aqui. Decidi continuar fracassando nisso mais um tempo.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active/3   [108 chars]
    en  Four years of %2$s. It is not a business decision. It is the only thing in the garden that is entirely mine.
    >>  ............................................
    pt  Quatro anos de %2$s. Não é decisão de negócio. É a única coisa no jardim que é inteiramente minha.
    >>  ............................................
```


**Outcome 169 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.florist.stubborn_variety.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.florist.stubborn_variety.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.florist.stubborn_variety.succeeded.respond`
- …where the player's next choices will be: "Five years is worth celebrating." | "I'll let you get back to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.florist.stubborn_variety.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.stubborn_variety.succeeded`: the villager reports. Subject `work.florist.a_plant_that_wont_take`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded/1   [110 chars]
    en  %2$s flowered. Five years, and it was shade all along, and it flowered in the corner I had almost given up on.
    >>  ............................................
    pt  %2$s floresceu. Cinco anos, e era sombra o tempo todo, e floresceu no canto que eu quase tinha abandonado.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded/2   [106 chars]
    en  It took. I did not tell anybody for four days because I wanted to be sure it was not a trick of the light.
    >>  ............................................
    pt  Pegou. Não contei a ninguém por quatro dias porque queria ter certeza de que não era truque da luz.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded/3   [116 chars]
    en  %2$s is up. Three of them. I have written down exactly what I did, in case it never happens again, which it may not.
    >>  ............................................
    pt  %2$s está de pé. Três delas. Anotei exatamente o que fiz, caso nunca mais aconteça, o que pode ser o caso.
    >>  ............................................
```


**Outcome 170 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.emptied_wood.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.emptied_wood.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.emptied_wood.blocked.respond`
- …where the player's next choices will be: "How do you count what's left?" | "Rest the wood for a year." | "Admitting your share is hard." | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.blocked`: the villager reports. Subject `work.hunter.what_the_wood_can_spare`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked/1   [116 chars]
    en  %2$s has %3$s and I can see it in the tracks — half what there was two years ago, in the same mud, at the same hour.
    >>  ............................................
    pt  %2$s tem %3$s e dá para ver nas pegadas — metade do que havia dois anos atrás, na mesma lama, na mesma hora.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked/2   [109 chars]
    en  %3$s. That is the whole story of %2$s and I am part of the story, which is the part I would rather skip over.
    >>  ............................................
    pt  %3$s. É essa a história inteira de %2$s, e eu faço parte da história, que é a parte que eu preferiria pular.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked/3   [106 chars]
    en  I have taken eleven out of %2$s this year and I should have taken four. I knew it at seven and kept going.
    >>  ............................................
    pt  Tirei onze de %2$s este ano e deveria ter tirado quatro. Eu sabia no sétimo e continuei.
    >>  ............................................
```


**Outcome 171 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.emptied_wood.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.emptied_wood.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.emptied_wood.succeeded.respond`
- …where the player's next choices will be: "How did the village take it?" | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.emptied_wood.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.succeeded`: the villager reports. Subject `work.hunter.what_the_wood_can_spare`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded/1   [91 chars]
    en  I rested %2$s for a full year. There were young at three of the four crossings this spring.
    >>  ............................................
    pt  Deixei %2$s descansar um ano inteiro. Nesta primavera havia filhotes em três das quatro travessias.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded/2   [95 chars]
    en  The village ate less and complained more and then stopped complaining, and %2$s is coming back.
    >>  ............................................
    pt  A vila comeu menos e reclamou mais e depois parou de reclamar, e %2$s está voltando.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded/3   [113 chars]
    en  %2$s has doubled at the crossings. It took one year of doing nothing, which is the hardest work I have ever done.
    >>  ............................................
    pt  %2$s dobrou nas travessias. Levou um ano fazendo nada, que é o trabalho mais difícil que eu já fiz.
    >>  ............................................
```


**Outcome 172 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.lost_track.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.lost_track.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.lost_track.failed.respond`
- …where the player's next choices will be: "Where did you lose it?" | "Even a good tracker loses one." | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.lost_track.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.lost_track.failed`: the villager reports. Subject `work.hunter.the_track`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed/1   [111 chars]
    en  I followed %2$s and lost it on the second afternoon, and I walked home in the dark thinking about the crossing.
    >>  ............................................
    pt  Segui %2$s e perdi na segunda tarde, e voltei para casa no escuro pensando na travessia.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed/2   [114 chars]
    en  %2$s. Two days of my life and a deer that is somewhere out there carrying an injury because I was not good enough.
    >>  ............................................
    pt  %2$s. Dois dias da minha vida e um cervo que está por aí com um ferimento porque eu não fui boa o bastante.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed/3   [122 chars]
    en  Losing a trail is one thing. Losing %2$s after wounding is another, and I do not intend to make peace with the second one.
    >>  ............................................
    pt  Perder um rastro é uma coisa. Perder %2$s depois de ferir é outra, e não pretendo fazer as pazes com a segunda.
    >>  ............................................
```


**Outcome 173 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.lost_track.remembered"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.lost_track.remembered", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.lost_track.remembered.respond`
- …where the player's next choices will be: "That rule cost you something." | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.remembered
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.lost_track.remembered.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.lost_track.remembered`: the villager reports. Subject `work.hunter.the_track`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.remembered/1   [106 chars]
    en  I went back to the crossing four times with no bow, just to learn it, and now I could work it in the dark.
    >>  ............................................
    pt  Voltei à travessia quatro vezes sem arco, só para aprender, e agora eu resolveria aquilo no escuro.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.remembered/2   [141 chars]
    en  I changed my rule. No shot after the light starts to go, whatever the day has been. It has cost me two deer and no more nights like that one.
    >>  ............................................
    pt  Mudei minha regra. Nenhum tiro depois que a luz começa a cair, seja qual for o dia. Custou-me dois cervos e nenhuma outra noite como aquela.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.remembered/3   [119 chars]
    en  The wound healed, I think. I found the same animal in the autumn, moving well, and I let it go and walked home pleased.
    >>  ............................................
    pt  O ferimento sarou, eu acho. Encontrei o mesmo animal no outono, andando bem, e deixei passar e voltei para casa contente.
    >>  ............................................
```


**Outcome 174 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.robbed_line.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.robbed_line.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.robbed_line.blocked.respond`
- …where the player's next choices will be: "I'll bring you string for new snares." | "Sit up one morning and see who it is." | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.robbed_line.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.robbed_line.blocked`: the villager reports. Subject `work.hunter.the_long_walk`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked/1   [103 chars]
    en  Somebody has been working %2$s ahead of me. Three snares empty and sprung, and the sets put back badly.
    >>  ............................................
    pt  Alguém vem trabalhando %2$s antes de mim. Três armadilhas vazias e disparadas, e as montagens recolocadas mal.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked/2   [111 chars]
    en  %2$s has been robbed four mornings running. Whoever it is knows my round, which narrows it to about six people.
    >>  ............................................
    pt  %2$s foi roubada quatro manhãs seguidas. Quem quer que seja conhece minha ronda, o que reduz a umas seis pessoas.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked/3   [117 chars]
    en  I have lost the snares as well as the catch on %2$s, and snares are string and string is the one thing I cannot make.
    >>  ............................................
    pt  Perdi as armadilhas junto com a caça em %2$s, e armadilha é barbante, e barbante é a única coisa que eu não sei fazer.
    >>  ............................................
```


**Outcome 175 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter.robbed_line.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter.robbed_line.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter.robbed_line.succeeded.respond`
- …where the player's next choices will be: "Teaching them was the better answer." | "I'll let you get back to the wood."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter.robbed_line.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.robbed_line.succeeded`: the villager reports. Subject `work.hunter.the_long_walk`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded/1   [104 chars]
    en  I sat up and it was who I thought. I said nothing, and I have been leaving one snare set for them since.
    >>  ............................................
    pt  Esperei e era quem eu achava. Não disse nada, e desde então venho deixando uma armadilha montada para eles.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded/2   [101 chars]
    en  It was a bad year in one house. We have an arrangement now that neither of us has ever said out loud.
    >>  ............................................
    pt  Era um ano ruim numa casa. Temos um arranjo agora que nenhum de nós dois jamais disse em voz alta.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded/3   [113 chars]
    en  The line runs clean. I moved it, told one person where, and taught the eldest of that household to set their own.
    >>  ............................................
    pt  A linha está limpa. Mudei de lugar, contei a uma pessoa onde, e ensinei o mais velho daquela casa a montar as próprias.
    >>  ............................................
```


**Outcome 176 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.miner.exhausted_seam.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.miner.exhausted_seam.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.miner.exhausted_seam.blocked.respond`
- …where the player's next choices will be: "How do you know it's finished?" | "Then open new ground." | "Eleven years is a long attachment." | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.miner.exhausted_seam.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.blocked`: the villager reports. Subject `work.miner.the_seam`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked/1   [108 chars]
    en  %2$s has given me %3$s, and I have been walking in every morning to look at it as if looking will change it.
    >>  ............................................
    pt  %2$s me deu %3$s, e eu venho descendo toda manhã para olhar aquilo como se olhar fosse mudar.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked/2   [106 chars]
    en  %3$s at %2$s. Forty minutes of walking each way to stand in front of a wall that has nothing left to give.
    >>  ............................................
    pt  %3$s em %2$s. Quarenta minutos de caminhada de ida e volta para ficar diante de uma parede que não tem mais nada a dar.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.blocked/3   [104 chars]
    en  %2$s fed this village for eleven years. It does not owe me anything and I am still taking it personally.
    >>  ............................................
    pt  %2$s alimentou esta vila por onze anos. Não me deve nada e eu continuo levando para o lado pessoal.
    >>  ............................................
```


**Outcome 177 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.miner.exhausted_seam.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.miner.exhausted_seam.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.miner.exhausted_seam.succeeded.respond`
- …where the player's next choices will be: "How did you stand the seven weeks?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.exhausted_seam.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.miner.exhausted_seam.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.exhausted_seam.succeeded`: the villager reports. Subject `work.miner.the_seam`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded/1   [104 chars]
    en  Seven weeks and then the pick bit. I sat down on the floor of the drift and laughed, alone, in the dark.
    >>  ............................................
    pt  Sete semanas e aí a picareta mordeu. Sentei no chão da galeria e ri, sozinha, no escuro.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded/2   [130 chars]
    en  The new working is good. Better than the east ever was, which I would have found insulting a season ago and now simply find funny.
    >>  ............................................
    pt  A escavação nova é boa. Melhor do que o leste jamais foi, o que eu teria achado ofensivo uma estação atrás e agora só acho engraçado.
    >>  ............................................
  dialogue.conversations.scene.work.miner.exhausted_seam.succeeded/3   [145 chars]
    en  It came in. I have brought up more in a fortnight than the last six months of the old seam, and I have stopped walking past the boarded entrance.
    >>  ............................................
    pt  Rendeu. Trouxe mais em duas semanas do que nos últimos seis meses do veio antigo, e parei de passar em frente à entrada fechada.
    >>  ............................................
```


**Outcome 178 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.miner.failing_props.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.miner.failing_props.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.miner.failing_props.blocked.respond`
- …where the player's next choices will be: "I'll bring you planks for props." | "Stay out until it's propped." | "How much warning would you get?" | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.miner.failing_props.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.blocked`: the villager reports. Subject `work.miner.props`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.blocked/1   [101 chars]
    en  There is %2$s at %3$s and I am still working under it, which I would tell anybody else to stop doing.
    >>  ............................................
    pt  Tem %2$s em %3$s e eu continuo trabalhando embaixo, coisa que eu mandaria qualquer outra pessoa parar de fazer.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked/2   [118 chars]
    en  %2$s. I have shifted the working further out and that is a delay, not a fix, and I know the difference perfectly well.
    >>  ............................................
    pt  %2$s. Afastei a frente de trabalho mais para fora, e isso é adiamento, não conserto, e eu sei muito bem a diferença.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.blocked/3   [110 chars]
    en  %3$s needs new timber and there is none, so every morning I make the same bad decision and call it experience.
    >>  ............................................
    pt  %3$s precisa de madeira nova e não tem nenhuma, então toda manhã eu tomo a mesma decisão ruim e chamo de experiência.
    >>  ............................................
```


**Outcome 179 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.miner.failing_props.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.miner.failing_props.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.miner.failing_props.succeeded.respond`
- …where the player's next choices will be: "Sleeping through is worth nine days." | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.failing_props.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.miner.failing_props.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.failing_props.succeeded`: the villager reports. Subject `work.miner.props`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.failing_props.succeeded/1   [99 chars]
    en  %2$s is propped end to end. I set them myself and I have been back down twice just to look at them.
    >>  ............................................
    pt  %2$s está escorado de ponta a ponta. Assentei tudo eu mesma e já desci duas vezes só para olhar.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.succeeded/2   [108 chars]
    en  Timber in, roof quiet. The creaking stopped the same afternoon, which I had not expected to move me and did.
    >>  ............................................
    pt  Madeira posta, teto quieto. O rangido parou na mesma tarde, coisa que eu não esperava que me emocionasse e emocionou.
    >>  ............................................
  dialogue.conversations.scene.work.miner.failing_props.succeeded/3   [115 chars]
    en  It is safe. I lost nine days of ore and I have been sleeping through the night for the first time since the autumn.
    >>  ............................................
    pt  Está seguro. Perdi nove dias de minério e venho dormindo a noite inteira pela primeira vez desde o outono.
    >>  ............................................
```


**Outcome 180 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.miner.the_day_it_moved.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.miner.the_day_it_moved.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.miner.the_day_it_moved.succeeded.respond`
- …where the player's next choices will be: "How did you go back down?" | "Eleven days is a long climb back." | "I'll let you get back down."

```text
POOL   dialogue key: dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.miner.the_day_it_moved.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.miner.the_day_it_moved.succeeded`: the villager reports. Subject `work.miner.the_day_it_moved`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:miner` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded/1   [90 chars]
    en  Four years ago there was %2$s and I walked out and I did not go back down for eleven days.
    >>  ............................................
    pt  Quatro anos atrás teve %2$s e eu saí andando e não desci de novo por onze dias.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded/2   [106 chars]
    en  %2$s. That is all it was. Nothing fell on me. I have been more frightened by that than by things that did.
    >>  ............................................
    pt  %2$s. Foi só isso. Nada caiu em cima de mim. Já me assustei mais com isso do que com coisas que caíram.
    >>  ............................................
  dialogue.conversations.scene.work.miner.the_day_it_moved.succeeded/3   [115 chars]
    en  I remember %2$s and then being outside, and I do not remember the walk in between, which is the part I think about.
    >>  ............................................
    pt  Lembro de %2$s e depois de estar do lado de fora, e não lembro da caminhada entre as duas, e é essa a parte em que eu penso.
    >>  ............................................
```


**Outcome 181 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.netherian.spoiled_load.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.netherian.spoiled_load.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.netherian.spoiled_load.blocked.respond`
- …where the player's next choices will be: "What does the journey take out of you?" | "I'll bring you quartz to make up the load." | "Split the load across two carriers." | "I'll let you get back to your load."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.blocked`: the villager reports. Subject `work.netherian.what_travels`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked/1   [98 chars]
    en  %2$s came back in a state because of %3$s, and a spoiled load is nine days of walking for nothing.
    >>  ............................................
    pt  %2$s voltou num estado ruim por causa de %3$s, e uma carga estragada são nove dias de caminhada por nada.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked/2   [112 chars]
    en  %3$s. That is all it takes. Nine days out, nine days back, and %2$s is worth about a third of what it should be.
    >>  ............................................
    pt  %3$s. É só o que basta. Nove dias de ida, nove de volta, e %2$s vale um terço do que deveria.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked/3   [87 chars]
    en  I have %2$s that I cannot sell at the price I need and I have already paid the carrier.
    >>  ............................................
    pt  Tenho %2$s que não consigo vender pelo preço de que preciso, e já paguei o transportador.
    >>  ............................................
```


**Outcome 182 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.netherian.spoiled_load.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.netherian.spoiled_load.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.netherian.spoiled_load.succeeded.respond`
- …where the player's next choices will be: "How was it with somebody along?" | "I'll let you get back to your load."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.netherian.spoiled_load.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.succeeded`: the villager reports. Subject `work.netherian.what_travels`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded/1   [99 chars]
    en  %2$s went out whole and at the right price. Two of us carried and neither of us ran short of water.
    >>  ............................................
    pt  %2$s saiu inteira e pelo preço certo. Nós duas carregamos e nenhuma ficou sem água.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded/2   [121 chars]
    en  It worked. Half the profit, all of the sleep, and I have already agreed to the next crossing before this one is unpacked.
    >>  ............................................
    pt  Funcionou. Metade do lucro, todo o sono, e eu já concordei com a próxima travessia antes de desfazer esta.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded/3   [83 chars]
    en  %2$s sold in a morning. I have not had a load sell in a morning since I was thirty.
    >>  ............................................
    pt  %2$s vendeu numa manhã. Eu não vendia uma carga numa manhã desde os trinta.
    >>  ............................................
```


**Outcome 183 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.netherian.the_burn.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.netherian.the_burn.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.netherian.the_burn.succeeded.respond`
- …where the player's next choices will be: "Did you think about giving it up?" | "You changed how you work because of it." | "I'll let you get back to your load."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.netherian.the_burn.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_burn.succeeded`: the villager reports. Subject `work.netherian.burns`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded/1   [89 chars]
    en  %2$s. Seven years ago, on the way out rather than the way back, which is the stupid part.
    >>  ............................................
    pt  %2$s. Sete anos atrás, na ida e não na volta, que é a parte idiota.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded/2   [111 chars]
    en  I got %2$s from a moment of not paying attention at the end of a good day, which is when everybody gets theirs.
    >>  ............................................
    pt  Ganhei %2$s num momento de desatenção no fim de um dia bom, que é quando todo mundo ganha a sua.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded/3   [118 chars]
    en  %2$s cost me four months and taught me to walk at night and rest at noon, and I should have been doing that all along.
    >>  ............................................
    pt  %2$s me custou quatro meses e me ensinou a caminhar de noite e descansar ao meio-dia, coisa que eu deveria estar fazendo desde sempre.
    >>  ............................................
```


**Outcome 184 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.netherian.the_apprentice_trader.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.netherian.the_apprentice_trader.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.netherian.the_apprentice_trader.active.respond`
- …where the player's next choices will be: "What are you worried about?" | "Take them on a short run first." | "I'll let you get back to your load."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.netherian.the_apprentice_trader.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_apprentice_trader.active`: the villager reports. Subject `work.netherian.the_crossing`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active/1   [105 chars]
    en  %2$s wants to come on the crossing and has asked three times, and I have run out of polite ways to delay.
    >>  ............................................
    pt  %2$s quer ir na travessia e já pediu três vezes, e eu esgotei os jeitos educados de adiar.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active/2   [114 chars]
    en  %2$s could do it. That is not the question. The question is whether they will still want to after the seventh day.
    >>  ............................................
    pt  %2$s conseguiria. Não é essa a questão. A questão é se ainda vai querer depois do sétimo dia.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active/3   [122 chars]
    en  If I say no, %2$s goes with somebody careless instead, and that is the argument that keeps winning against my own caution.
    >>  ............................................
    pt  Se eu disser não, %2$s vai com alguém descuidado, e é esse o argumento que sempre vence a minha cautela.
    >>  ............................................
```


**Outcome 185 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.netherian.the_apprentice_trader.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.netherian.the_apprentice_trader.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond`
- …where the player's next choices will be: "Two pages is eleven years of learning." | "I'll let you get back to your load."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_apprentice_trader.succeeded`: the villager reports. Subject `work.netherian.the_crossing`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded/1   [105 chars]
    en  %2$s did the short run and asked for the long one on the walk home, which is the answer I was hoping for.
    >>  ............................................
    pt  %2$s fez a viagem curta e pediu a longa no caminho de volta, que é a resposta que eu esperava.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded/2   [133 chars]
    en  Four days to the coast sorted it. %2$s was bored on the third day, said so out loud, and kept walking anyway. That is the whole test.
    >>  ............................................
    pt  Quatro dias até a costa resolveram. %2$s ficou entediada no terceiro dia, disse isso em voz alta, e continuou andando. É esse o teste inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded/3   [128 chars]
    en  %2$s is coming on the next crossing. I have written down everything I know about water, which took a night and filled two pages.
    >>  ............................................
    pt  %2$s vem na próxima travessia. Anotei tudo o que sei sobre água, o que levou uma noite e encheu duas páginas.
    >>  ............................................
```


**Outcome 186 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.contradictory_readings.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.contradictory_readings.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.contradictory_readings.blocked.respond`
- …where the player's next choices will be: "What would the answer change?" | "Then take another season of readings." | "Your caution there is right." | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.contradictory_readings.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.blocked`: the villager reports. Subject `work.oceanographer.readings`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked/1   [106 chars]
    en  I have %3$s for %2$s and I cannot tell you which of them is wrong, which means I cannot tell you anything.
    >>  ............................................
    pt  Tenho %3$s para %2$s e não sei dizer qual delas está errada, o que significa que não sei dizer nada.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked/2   [82 chars]
    en  %3$s. Four months of work on %2$s and the honest summary is a shrug in a notebook.
    >>  ............................................
    pt  %3$s. Quatro meses de trabalho em %2$s e o resumo honesto é um dar de ombros num caderno.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.blocked/3   [121 chars]
    en  %2$s is doing something. I am certain of that and certain of nothing else, and certainty about one thing is not a result.
    >>  ............................................
    pt  %2$s está fazendo alguma coisa. Disso eu tenho certeza, e de mais nada, e certeza de uma coisa só não é um resultado.
    >>  ............................................
```


**Outcome 187 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.contradictory_readings.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.contradictory_readings.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond`
- …where the player's next choices will be: "Did anybody listen?" | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.contradictory_readings.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.contradictory_readings.succeeded`: the villager reports. Subject `work.oceanographer.readings`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded/1   [120 chars]
    en  Eight months settled it. %2$s is shifting east, about a boat's length a year, and now I can say so with a straight face.
    >>  ............................................
    pt  Oito meses resolveram. %2$s está se deslocando para leste, cerca de um comprimento de barco por ano, e agora eu posso dizer isso sem hesitar.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded/2   [110 chars]
    en  The contradiction was the gauge. Eight months to find out that the instrument was lying and the water was not.
    >>  ............................................
    pt  A contradição era o medidor. Oito meses para descobrir que o instrumento mentia e a água não.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.contradictory_readings.succeeded/3   [122 chars]
    en  %2$s has an answer. It took twice as long as anybody wanted and it will be right in ten years, which was always the point.
    >>  ............................................
    pt  %2$s tem uma resposta. Levou o dobro do que qualquer um queria e vai estar certa em dez anos, que era o objetivo desde sempre.
    >>  ............................................
```


**Outcome 188 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.broken_instrument.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.broken_instrument.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.broken_instrument.blocked.respond`
- …where the player's next choices will be: "I'll bring you glass for it." | "What does a gap in the record cost?" | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.broken_instrument.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.broken_instrument.blocked`: the villager reports. Subject `work.oceanographer.instruments`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked/1   [97 chars]
    en  There is %2$s and until it is fixed everything I write down is a story rather than a measurement.
    >>  ............................................
    pt  Tem %2$s, e até consertar, tudo o que eu anoto é história e não medição.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked/2   [114 chars]
    en  %2$s. Nobody within four days can make the part, and I have been taking readings I know I will have to throw away.
    >>  ............................................
    pt  %2$s. Ninguém a quatro dias daqui faz a peça, e eu venho anotando medições que sei que vou ter que jogar fora.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.blocked/3   [146 chars]
    en  %2$s means I am guessing carefully, which is a thing people cannot tell apart from knowing, and that frightens me more than the gap in the record.
    >>  ............................................
    pt  %2$s significa que eu chuto com cuidado, coisa que as pessoas não distinguem de saber, e isso me assusta mais que a lacuna no registro.
    >>  ............................................
```


**Outcome 189 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.broken_instrument.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.broken_instrument.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.broken_instrument.succeeded.respond`
- …where the player's next choices will be: "Marking the gap was right." | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.broken_instrument.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.broken_instrument.succeeded`: the villager reports. Subject `work.oceanographer.instruments`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded/1   [120 chars]
    en  Ground and set and reading true. Nine days lost, and I have marked them as lost rather than filling them in from memory.
    >>  ............................................
    pt  Polido, montado e medindo certo. Nove dias perdidos, e eu marquei como perdidos em vez de preencher de memória.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded/2   [139 chars]
    en  It works. I spent an evening checking it against the old readings and it agrees with itself, which is all anybody can ask of an instrument.
    >>  ............................................
    pt  Funciona. Passei uma noite conferindo contra as medições antigas e ele concorda consigo mesmo, que é tudo o que se pode pedir de um instrumento.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.broken_instrument.succeeded/3   [98 chars]
    en  Mended, and I have made a spare part while I had the materials out, which is eleven years overdue.
    >>  ............................................
    pt  Consertado, e fiz uma peça reserva enquanto tinha o material à mão, coisa atrasada em onze anos.
    >>  ............................................
```


**Outcome 190 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.ignored_warning.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.ignored_warning.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.ignored_warning.failed.respond`
- …where the player's next choices will be: "What would you do differently?" | "You gave the warning in good time." | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.ignored_warning.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.ignored_warning.failed`: the villager reports. Subject `work.oceanographer.what_the_fishermen_say`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed/1   [97 chars]
    en  I told %2$s about the spring channel in good time and nothing happened, and then a boat grounded.
    >>  ............................................
    pt  Avisei %2$s sobre o canal da primavera com antecedência e nada aconteceu, e aí um barco encalhou.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed/2   [138 chars]
    en  %2$s heard me. That is the part I keep going over — it was not that I failed to speak. It is that speaking turned out to be the easy half.
    >>  ............................................
    pt  %2$s me ouviu. É essa a parte que eu fico remoendo — não é que eu tenha falhado em falar. É que falar acabou sendo a metade fácil.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.failed/3   [106 chars]
    en  Nobody was hurt. I have said that to myself about forty times and it is true and it is also not the point.
    >>  ............................................
    pt  Ninguém se machucou. Já disse isso a mim mesma umas quarenta vezes e é verdade e também não é o ponto.
    >>  ............................................
```


**Outcome 191 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.oceanographer.ignored_warning.remembered"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.oceanographer.ignored_warning.remembered", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.oceanographer.ignored_warning.remembered.respond`
- …where the player's next choices will be: "An absence is still your work." | "I'll let you get back to your readings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.oceanographer.ignored_warning.remembered.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.oceanographer.ignored_warning.remembered`: the villager reports. Subject `work.oceanographer.what_the_fishermen_say`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:oceanographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered/1   [115 chars]
    en  I drew it. Badly, on a board, at the quay, and eleven people stood and looked at it and two of them argued with me.
    >>  ............................................
    pt  Eu desenhei. Mal, numa tábua, no cais, e onze pessoas ficaram olhando e duas discutiram comigo.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered/2   [110 chars]
    en  The boats moved. It took a picture and three tellings, and the numbers had been correct for a year and a half.
    >>  ............................................
    pt  Os barcos mudaram. Precisou de uma figura e três avisos, e os números estavam certos havia um ano e meio.
    >>  ............................................
  dialogue.conversations.scene.work.oceanographer.ignored_warning.remembered/3   [133 chars]
    en  Nobody has grounded since. That is the whole result and it is not the sort of thing anybody thanks you for, because it is an absence.
    >>  ............................................
    pt  Ninguém encalhou desde então. É o resultado inteiro e não é o tipo de coisa pela qual alguém agradece, porque é uma ausência.
    >>  ............................................
```


**Outcome 192 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.woodworker.warped_piece.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.woodworker.warped_piece.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.woodworker.warped_piece.blocked.respond`
- …where the player's next choices will be: "Would anyone else notice?" | "I'll bring you seasoned planks." | "Tell them and offer to remake it." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.blocked`: the villager reports. Subject `work.woodworker.seasoning`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked/1   [113 chars]
    en  %2$s I finished in the autumn has cupped, because of %3$s, and it is sitting in somebody's kitchen looking wrong.
    >>  ............................................
    pt  %2$s que terminei no outono empenou, por causa de %3$s, e está na cozinha de alguém com cara de errado.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked/2   [120 chars]
    en  %3$s. Wood keeps moving for years after you think you have finished with it, and that is not an excuse, it is the trade.
    >>  ............................................
    pt  %3$s. Madeira continua se mexendo por anos depois de você achar que terminou, e isso não é desculpa, é o ofício.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked/3   [107 chars]
    en  %2$s is out by the thickness of a fingernail. Nobody would notice and I have noticed and I cannot leave it.
    >>  ............................................
    pt  %2$s está fora por uma espessura de unha. Ninguém repararia e eu reparei e eu não consigo deixar.
    >>  ............................................
```


**Outcome 193 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.woodworker.warped_piece.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.woodworker.warped_piece.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.woodworker.warped_piece.succeeded.respond`
- …where the player's next choices will be: "Why keep the failures on the wall?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.woodworker.warped_piece.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.succeeded`: the villager reports. Subject `work.woodworker.seasoning`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded/1   [91 chars]
    en  %2$s is remade in quarter-sawn oak and it will still be flat when the house has a new roof.
    >>  ............................................
    pt  %2$s foi refeita em carvalho serrado em quartos e ainda vai estar plana quando a casa tiver telhado novo.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded/2   [115 chars]
    en  I went and told them and they had not noticed and were baffled, and then delighted, and then they told four people.
    >>  ............................................
    pt  Fui contar e eles não tinham notado e ficaram confusos, depois encantados, e depois contaram para quatro pessoas.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded/3   [122 chars]
    en  The old one is on the workshop wall. It is the seventh piece up there and I can tell you what every one of them taught me.
    >>  ............................................
    pt  A antiga está na parede da oficina. É a sétima peça lá em cima e eu sei dizer o que cada uma me ensinou.
    >>  ............................................
```


**Outcome 194 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.woodworker.bad_joint.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.woodworker.bad_joint.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.woodworker.bad_joint.active.respond`
- …where the player's next choices will be: "How would you persuade them?" | "Offer both and price them honestly." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.woodworker.bad_joint.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.bad_joint.active`: the villager reports. Subject `work.woodworker.joints`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active/1   [127 chars]
    en  The customer wants %2$s because it is quicker and cheaper, and they are right about both and wrong about everything after that.
    >>  ............................................
    pt  O cliente quer %2$s porque é mais rápido e mais barato, e ele tem razão nas duas e está errado em tudo depois disso.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active/2   [125 chars]
    en  %2$s. I have explained why it fails and been heard politely, and politeness is the sound of somebody who has already decided.
    >>  ............................................
    pt  %2$s. Já expliquei por que falha e fui ouvida com educação, e educação é o som de quem já decidiu.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active/3   [145 chars]
    en  I could make it their way. It will hold for six years and then come apart, and by then everybody will have forgotten this conversation except me.
    >>  ............................................
    pt  Eu poderia fazer do jeito deles. Aguenta seis anos e depois solta, e até lá todo mundo terá esquecido esta conversa menos eu.
    >>  ............................................
```


**Outcome 195 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.woodworker.bad_joint.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.woodworker.bad_joint.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.woodworker.bad_joint.succeeded.respond`
- …where the player's next choices will be: "Two prices did more than arguing." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.woodworker.bad_joint.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.bad_joint.succeeded`: the villager reports. Subject `work.woodworker.joints`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded/1   [127 chars]
    en  They took the expensive one. I put the years next to the prices and they read it and changed their minds in about four seconds.
    >>  ............................................
    pt  Levaram a cara. Pus os anos ao lado dos preços, eles leram e mudaram de ideia em uns quatro segundos.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded/2   [87 chars]
    en  Two prices on a board. Eleven years of arguing about joints, ended by a piece of chalk.
    >>  ............................................
    pt  Dois preços numa tábua. Onze anos discutindo sobre juntas, encerrados por um pedaço de giz.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded/3   [117 chars]
    en  They chose the cheap one, actually, and I made it beautifully, and I have stopped feeling anything about that at all.
    >>  ............................................
    pt  Levaram a barata, na verdade, e eu fiz lindamente, e parei de sentir qualquer coisa a respeito.
    >>  ............................................
```


**Outcome 196 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.woodworker.teachers_chair.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.woodworker.teachers_chair.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.woodworker.teachers_chair.succeeded.respond`
- …where the player's next choices will be: "What was she like to learn from?" | "Forty years is a good run." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.woodworker.teachers_chair.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.teachers_chair.succeeded`: the villager reports. Subject `work.woodworker.the_workshop`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded/1   [119 chars]
    en  %2$s came in for mending last week. My teacher made it, forty years ago, and I knew her joints before I turned it over.
    >>  ............................................
    pt  %2$s chegou para conserto semana passada. Minha mestra fez, quarenta anos atrás, e reconheci as juntas dela antes de virar.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded/2   [125 chars]
    en  Somebody brought me %2$s and did not know whose it was. I did. I have been sitting with it for two days doing nothing useful.
    >>  ............................................
    pt  Alguém me trouxe %2$s e não sabia de quem era. Eu sabia. Faz dois dias que eu sento com aquilo sem fazer nada útil.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded/3   [132 chars]
    en  %2$s. Forty years of a family sitting on it, and one joint has finally let go, and it is the joint she told me she was unsure about.
    >>  ............................................
    pt  %2$s. Quarenta anos de uma família sentando, e uma junta finalmente soltou, e é a junta sobre a qual ela me disse que tinha dúvida.
    >>  ............................................
```


**Outcome 197 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter_expert.false_alarm.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter_expert.false_alarm.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter_expert.false_alarm.blocked.respond`
- …where the player's next choices will be: "How do you tell the difference?" | "Keep refusing until there's evidence." | "That puts you against the whole lane." | "I'll let you get back to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.blocked`: the villager reports. Subject `work.hunter_expert.false_alarms`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked/1   [113 chars]
    en  Half the lane has decided about %2$s on the strength of %3$s, and I am the only person expected to say otherwise.
    >>  ............................................
    pt  Metade da viela já decidiu sobre %2$s com base em %3$s, e eu sou a única pessoa de quem se espera o contrário.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked/2   [112 chars]
    en  %3$s. That is the whole case. I have been asked four times to go and look at %2$s and I have said no four times.
    >>  ............................................
    pt  %3$s. É o caso inteiro. Já me pediram quatro vezes para ir olhar %2$s e eu disse não quatro vezes.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked/3   [120 chars]
    en  I have seen %3$s a hundred times and it has meant something twice. The village has seen it once and it means everything.
    >>  ............................................
    pt  Já vi %3$s umas cem vezes e significou algo duas. A vila viu uma vez e significa tudo.
    >>  ............................................
```


**Outcome 198 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter_expert.false_alarm.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter_expert.false_alarm.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter_expert.false_alarm.succeeded.respond`
- …where the player's next choices will be: "What happens to them afterwards?" | "I'll let you get back to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.succeeded`: the villager reports. Subject `work.hunter_expert.false_alarms`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded/1   [104 chars]
    en  It was a dog. Two lanes over, somebody's dog, and %2$s is still here and still being looked at sideways.
    >>  ............................................
    pt  Era um cachorro. Duas vielas adiante, o cachorro de alguém, e %2$s continua aqui e continua sendo olhada de lado.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded/2   [122 chars]
    en  It passed. Nobody apologised to %2$s and nobody will, and I have started making a point of buying from them at the market.
    >>  ............................................
    pt  Passou. Ninguém pediu desculpa a %2$s e ninguém vai pedir, e eu passei a fazer questão de comprar deles na feira.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded/3   [137 chars]
    en  I held out for eleven days. On the twelfth the actual cause turned up and the whole thing evaporated as if nobody had ever said anything.
    >>  ............................................
    pt  Segurei por onze dias. No décimo segundo a causa real apareceu e a coisa toda evaporou como se ninguém tivesse dito nada.
    >>  ............................................
```


**Outcome 199 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter_expert.failing_lantern.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter_expert.failing_lantern.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter_expert.failing_lantern.blocked.respond`
- …where the player's next choices will be: "I'll bring you a lantern for the round." | "What does the round involve?" | "I'll let you get back to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.failing_lantern.blocked`: the villager reports. Subject `work.hunter_expert.the_kit`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked/1   [124 chars]
    en  I have %2$s and I am still walking the round with it, which is exactly the sort of thing I would tell somebody else off for.
    >>  ............................................
    pt  Tenho %2$s e continuo fazendo a ronda com isso, que é exatamente o tipo de coisa pela qual eu daria bronca em outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked/2   [110 chars]
    en  %2$s. It works until the night it does not, and I will not know which night that is until I am standing in it.
    >>  ............................................
    pt  %2$s. Funciona até a noite em que não funciona, e eu não vou saber qual noite é até estar dentro dela.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked/3   [74 chars]
    en  The village pays me in goodwill, which is generous and does not mend %2$s.
    >>  ............................................
    pt  A vila me paga em boa vontade, o que é generoso e não conserta %2$s.
    >>  ............................................
```


**Outcome 200 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter_expert.failing_lantern.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter_expert.failing_lantern.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond`
- …where the player's next choices will be: "Others followed once you said it." | "I'll let you get back to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.failing_lantern.succeeded`: the villager reports. Subject `work.hunter_expert.the_kit`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded/1   [117 chars]
    en  The round is done properly again. I had forgotten how much of the worry was about the equipment rather than the dark.
    >>  ............................................
    pt  A ronda voltou a ser feita direito. Eu tinha esquecido o quanto da preocupação era sobre o equipamento e não sobre o escuro.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded/2   [107 chars]
    en  Mended and paid for over the winter, in instalments, and I insisted on the instalments and I am glad I did.
    >>  ............................................
    pt  Consertado e pago ao longo do inverno, em parcelas, e eu insisti nas parcelas e estou feliz por ter insistido.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded/3   [138 chars]
    en  Two other people on the watch have replaced their lamps since. Apparently everybody had been carrying something broken and saying nothing.
    >>  ............................................
    pt  Duas outras pessoas da ronda trocaram as lamparinas desde então. Aparentemente todo mundo carregava algo quebrado e não dizia nada.
    >>  ............................................
```


**Outcome 201 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.hunter_expert.the_real_night.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.hunter_expert.the_real_night.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.hunter_expert.the_real_night.succeeded.respond`
- …where the player's next choices will be: "What did you take from it?" | "Keeping that private makes sense." | "I'll let you get back to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.the_real_night.succeeded`: the villager reports. Subject `work.hunter_expert.the_one_real_night`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded/1   [112 chars]
    en  There was %2$s, six years ago, and it was the one time in nineteen years that any of this was not a false alarm.
    >>  ............................................
    pt  Teve %2$s, seis anos atrás, e foi a única vez em dezenove anos em que nada disso foi alarme falso.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded/2   [123 chars]
    en  %2$s. Everybody in this village has a version of it and mine is the dullest, because I was busy and did not see most of it.
    >>  ............................................
    pt  %2$s. Todo mundo nesta vila tem uma versão e a minha é a mais sem graça, porque eu estava ocupada e não vi quase nada.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded/3   [125 chars]
    en  I do not talk about %2$s much. The talking is what turns one night into the reason a family gets driven out four years later.
    >>  ............................................
    pt  Eu não falo muito sobre %2$s. É a conversa que transforma uma noite no motivo de uma família ser expulsa quatro anos depois.
    >>  ............................................
```


**Outcome 202 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.blessing_refused.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.blessing_refused.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.blessing_refused.blocked.respond`
- …where the player's next choices will be: "What harm would it do?" | "Hold the line on that." | "Could you offer them something else?" | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.blessing_refused.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.blocked`: the villager reports. Subject `work.priest.the_accused`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.blocked/1   [118 chars]
    en  Four households have asked me for %3$s about %2$s, and every one of them asked kindly, and I have said no to all four.
    >>  ............................................
    pt  Quatro casas me pediram %3$s a respeito de %2$s, e todas pediram com educação, e eu disse não às quatro.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked/2   [142 chars]
    en  %3$s. It sounds like nothing. It would make the suspicion official, and an official suspicion cannot be argued with by the person it is about.
    >>  ............................................
    pt  %3$s. Parece não ser nada. Tornaria a suspeita oficial, e uma suspeita oficial não pode ser contestada por quem é acusado.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.blocked/3   [130 chars]
    en  They want the chapel behind them. That is the only thing I have and it is the one thing I will not lend to a thing I cannot check.
    >>  ............................................
    pt  Querem a capela do lado deles. É a única coisa que eu tenho e é a única que eu não empresto a algo que não posso conferir.
    >>  ............................................
```


**Outcome 203 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.blessing_refused.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.blessing_refused.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.blessing_refused.succeeded.respond`
- …where the player's next choices will be: "What was the vigil like?" | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.blessing_refused.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.blessing_refused.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.blessing_refused.succeeded`: the villager reports. Subject `work.priest.the_accused`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded/1   [122 chars]
    en  I held the vigil instead. Nineteen people came, and %2$s came too, and stood at the back, and nobody said a word about it.
    >>  ............................................
    pt  Fiz a vigília no lugar. Dezenove pessoas vieram, e %2$s também veio, e ficou no fundo, e ninguém comentou nada.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded/2   [116 chars]
    en  No rite. Four evenings in four kitchens and a vigil, and by the end of the fortnight the whole thing had gone quiet.
    >>  ............................................
    pt  Sem rito. Quatro noites em quatro cozinhas e uma vigília, e ao fim das duas semanas a coisa toda tinha silenciado.
    >>  ............................................
  dialogue.conversations.scene.work.priest.blessing_refused.succeeded/3   [124 chars]
    en  It cost me the collection for a season and I would do it again. %2$s is still here and still buying bread at the same stall.
    >>  ............................................
    pt  Custou-me a coleta de uma estação e eu faria de novo. %2$s continua aqui e continua comprando pão na mesma banca.
    >>  ............................................
```


**Outcome 204 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.frightened_family.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.frightened_family.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.frightened_family.active.respond`
- …where the player's next choices will be: "What actually helps them?" | "I'll bring candles for those houses." | "Every evening is a lot to give." | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.frightened_family.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.active`: the villager reports. Subject `work.priest.the_frightened`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.active/1   [112 chars]
    en  There is %2$s in this village and nothing I can say makes it smaller, because the fear is not about an argument.
    >>  ............................................
    pt  Existe %2$s nesta vila e nada do que eu diga diminui isso, porque o medo não é sobre um argumento.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active/2   [116 chars]
    en  %2$s. I have been going every evening. I do not bring an answer. I bring an hour of somebody else being in the room.
    >>  ............................................
    pt  %2$s. Venho indo toda noite. Não levo resposta. Levo uma hora de outra pessoa estando na sala.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.active/3   [114 chars]
    en  %2$s is the real cost of all this, and it is the part the lane never counts when it is deciding about a neighbour.
    >>  ............................................
    pt  %2$s é o custo real disso tudo, e é a parte que a viela nunca conta quando está decidindo sobre um vizinho.
    >>  ............................................
```


**Outcome 205 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.frightened_family.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.frightened_family.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.frightened_family.succeeded.respond`
- …where the player's next choices will be: "Ordinary conversation did that." | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.frightened_family.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.frightened_family.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.frightened_family.succeeded`: the villager reports. Subject `work.priest.the_frightened`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.frightened_family.succeeded/1   [103 chars]
    en  %2$s is sleeping. It took five weeks and candles and a great many ordinary conversations about turnips.
    >>  ............................................
    pt  %2$s está dormindo. Levou cinco semanas, velas e um monte de conversas comuns sobre nabos.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.succeeded/2   [115 chars]
    en  It has passed. Nobody could tell you which evening it turned, and I have decided that is because there was not one.
    >>  ............................................
    pt  Passou. Ninguém saberia dizer em que noite virou, e eu decidi que é porque não houve uma.
    >>  ............................................
  dialogue.conversations.scene.work.priest.frightened_family.succeeded/3   [135 chars]
    en  The child went outside on a Tuesday, to fetch water, without being asked. That was the whole recovery and it lasted about nine seconds.
    >>  ............................................
    pt  A criança saiu de casa numa terça, para buscar água, sem ninguém pedir. Foi a recuperação inteira e durou uns nove segundos.
    >>  ............................................
```


**Outcome 206 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.the_register.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.the_register.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.the_register.active.respond`
- …where the player's next choices will be: "What does the register show?" | "Read that page out more often." | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.the_register.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.the_register.active`: the villager reports. Subject `work.priest.the_register`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.the_register.active/1   [105 chars]
    en  I keep a register of every accusation this village has made and how each one ended. There are %2$s in it.
    >>  ............................................
    pt  Mantenho um registro de toda acusação que esta vila fez e como cada uma terminou. Tem %2$s nele.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active/2   [148 chars]
    en  %2$s. I started it eleven years ago because nobody could ever remember how the last one had turned out, and forgetting is what lets it happen again.
    >>  ............................................
    pt  %2$s. Comecei há onze anos porque ninguém conseguia lembrar como a última tinha acabado, e é o esquecimento que permite acontecer de novo.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.active/3   [136 chars]
    en  The register is the most unpopular object in this chapel. %2$s, and people would rather I did not have it, and that is exactly why I do.
    >>  ............................................
    pt  O registro é o objeto mais impopular desta capela. %2$s, e as pessoas preferiam que eu não o tivesse, e é exatamente por isso que eu tenho.
    >>  ............................................
```


**Outcome 207 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.priest.the_register.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.priest.the_register.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.priest.the_register.succeeded.respond`
- …where the player's next choices will be: "A winter with no accusation." | "I'll let you get back to the chapel."

```text
POOL   dialogue key: dialogue.conversations.scene.work.priest.the_register.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.priest.the_register.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.priest.the_register.succeeded`: the villager reports. Subject `work.priest.the_register`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:priest` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.priest.the_register.succeeded/1   [120 chars]
    en  I read it out after the poor harvest and there was no accusation at all this winter, for the first time in eleven years.
    >>  ............................................
    pt  Li em voz alta depois da colheita fraca e não houve acusação nenhuma neste inverno, pela primeira vez em onze anos.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.succeeded/2   [144 chars]
    en  Somebody asked to see the book afterwards and copied out the tally for themselves. I have no idea what they intend and I gave it to them gladly.
    >>  ............................................
    pt  Alguém pediu para ver o livro depois e copiou a contagem para si. Não faço ideia do que pretende e entreguei com prazer.
    >>  ............................................
  dialogue.conversations.scene.work.priest.the_register.succeeded/3   [130 chars]
    en  Four sentences, four times a year. Eleven years of keeping a record and it turns out the reading aloud was the entire point of it.
    >>  ............................................
    pt  Quatro frases, quatro vezes por ano. Onze anos mantendo um registro e acontece que ler em voz alta era o objetivo inteiro.
    >>  ............................................
```


**Outcome 208 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.demand_for_certainty.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.demand_for_certainty.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond`
- …where the player's next choices will be: "What do the tests involve?" | "I'll bring you bottles for the samples." | "Make them wait the six weeks." | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.demand_for_certainty.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.blocked`: the villager reports. Subject `work.vampire_expert.families`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked/1   [123 chars]
    en  %2$s came to me about %3$s and what they want is a yes or a no, and what I have is four possibilities and a waiting period.
    >>  ............................................
    pt  %2$s me procurou por causa de %3$s, e o que querem é um sim ou um não, e o que eu tenho são quatro possibilidades e um período de espera.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked/2   [133 chars]
    en  %3$s. It is consistent with the thing they are afraid of and with three duller things, and the three duller ones are far more likely.
    >>  ............................................
    pt  %3$s. É compatível com a coisa que temem e com três coisas mais banais, e as três banais são muito mais prováveis.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.blocked/3   [139 chars]
    en  %2$s asked me to be certain. I cannot be certain in under six weeks and I have said so, and six weeks is a very long time to be frightened.
    >>  ............................................
    pt  %2$s me pediu certeza. Eu não consigo ter certeza em menos de seis semanas e eu disse isso, e seis semanas é muito tempo para se ficar com medo.
    >>  ............................................
```


**Outcome 209 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.demand_for_certainty.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.demand_for_certainty.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond`
- …where the player's next choices will be: "Why visit every week?" | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.demand_for_certainty.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.demand_for_certainty.succeeded`: the villager reports. Subject `work.vampire_expert.families`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded/1   [111 chars]
    en  Six weeks and it was the dullest of the four. %2$s is well and I have never seen anybody so tired by good news.
    >>  ............................................
    pt  Seis semanas e era a mais banal das quatro. %2$s está bem e eu nunca vi ninguém tão cansada por uma boa notícia.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded/2   [124 chars]
    en  It resolved. I visited every week and by the fourth week we had stopped talking about it and started talking about the roof.
    >>  ............................................
    pt  Se resolveu. Visitei toda semana e na quarta a gente tinha parado de falar disso e começado a falar do telhado.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.demand_for_certainty.succeeded/3   [138 chars]
    en  %2$s got an answer that was worth having because it took six weeks. A four-day answer would have been a guess with a confident face on it.
    >>  ............................................
    pt  %2$s recebeu uma resposta que valia a pena porque levou seis semanas. Uma resposta em quatro dias teria sido um palpite com cara de confiança.
    >>  ............................................
```


**Outcome 210 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.the_case_i_got_wrong.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.the_case_i_got_wrong.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond`
- …where the player's next choices will be: "What changed in your practice?" | "You've carried that a long time." | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.the_case_i_got_wrong.failed`: the villager reports. Subject `work.vampire_expert.misdiagnosis`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed/1   [122 chars]
    en  There was %2$s. I shortened the waiting period because a family was suffering, and I was wrong, and they left the village.
    >>  ............................................
    pt  Teve %2$s. Encurtei o período de espera porque uma família estava sofrendo, e eu errei, e eles deixaram a vila.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed/2   [88 chars]
    en  %2$s. Nobody died. I want to say that first and it does not do the work I want it to do.
    >>  ............................................
    pt  %2$s. Ninguém morreu. Quero dizer isso primeiro e isso não faz o trabalho que eu queria que fizesse.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.failed/3   [129 chars]
    en  I was certain. That is the part I go back to — not that I was wrong, that I sounded certain, and certainty is what they acted on.
    >>  ............................................
    pt  Eu estava certa disso. É essa a parte à qual eu volto — não que eu errei, mas que eu soei certa, e foi na certeza que eles agiram.
    >>  ............................................
```


**Outcome 211 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.the_case_i_got_wrong.remembered"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.the_case_i_got_wrong.remembered", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond`
- …where the player's next choices will be: "Two others hold the line because of you." | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.the_case_i_got_wrong.remembered`: the villager reports. Subject `work.vampire_expert.misdiagnosis`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered/1   [143 chars]
    en  Two people in this field now hold a fixed waiting period because of a story I tell about myself, and that is the only good that has come of it.
    >>  ............................................
    pt  Duas pessoas desta área hoje mantêm um período de espera fixo por causa de uma história que eu conto sobre mim, e é o único bem que veio disso.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered/2   [117 chars]
    en  The family are well, four valleys over. I know because I asked, once, through somebody else, and then stopped asking.
    >>  ............................................
    pt  A família está bem, a quatro vales daqui. Eu sei porque perguntei, uma vez, por intermédio de outra pessoa, e depois parei de perguntar.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.the_case_i_got_wrong.remembered/3   [126 chars]
    en  It is in the front of the book and it is the first thing anybody reads, and I have stopped flinching when I turn to that page.
    >>  ............................................
    pt  Está na frente do livro e é a primeira coisa que qualquer um lê, e eu parei de me encolher quando abro naquela página.
    >>  ............................................
```


**Outcome 212 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.better_tests.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.better_tests.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.better_tests.active.respond`
- …where the player's next choices will be: "How would you know it was safe?" | "Collect the forty first." | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.better_tests.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.better_tests.active`: the villager reports. Subject `work.vampire_expert.the_tests`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.active/1   [123 chars]
    en  I am trying to shorten %2$s honestly rather than kindly, and honestly means four years of records before I change anything.
    >>  ............................................
    pt  Estou tentando encurtar %2$s com honestidade e não com bondade, e honestidade significa quatro anos de registros antes de mudar qualquer coisa.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active/2   [124 chars]
    en  %2$s is six weeks because that is what I inherited. I have never seen the evidence for six and I have started collecting it.
    >>  ............................................
    pt  %2$s são seis semanas porque foi o que eu herdei. Nunca vi a prova a favor de seis e comecei a reunir.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.active/3   [140 chars]
    en  If %2$s could be four weeks and be as good, that is two weeks off every frightened family for the rest of my life. That is worth four years.
    >>  ............................................
    pt  Se %2$s pudesse ser quatro semanas e ser igualmente bom, são duas semanas a menos para toda família assustada pelo resto da minha vida. Isso vale quatro anos.
    >>  ............................................
```


**Outcome 213 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.vampire_expert.better_tests.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.vampire_expert.better_tests.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.vampire_expert.better_tests.succeeded.respond`
- …where the player's next choices will be: "A result that changes nothing is still a result." | "I'll let you get back to your records."

```text
POOL   dialogue key: dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.vampire_expert.better_tests.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.vampire_expert.better_tests.succeeded`: the villager reports. Subject `work.vampire_expert.the_tests`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:vampire_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded/1   [125 chars]
    en  Forty cases. %2$s stays at six weeks, because two of the forty would have been called wrong at four, and two is two families.
    >>  ............................................
    pt  Quarenta casos. %2$s fica em seis semanas, porque dois dos quarenta teriam sido julgados errado aos quatro, e dois são duas famílias.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded/2   [120 chars]
    en  Four years and the answer is that I was already doing it correctly. I have never been so pleased to have wasted my time.
    >>  ............................................
    pt  Quatro anos e a resposta é que eu já estava fazendo certo. Nunca fiquei tão contente de ter perdido meu tempo.
    >>  ............................................
  dialogue.conversations.scene.work.vampire_expert.better_tests.succeeded/3   [111 chars]
    en  I sent the forty to the two others in the field. One of them had been quietly using four weeks and has stopped.
    >>  ............................................
    pt  Mandei os quarenta às duas outras pessoas da área. Uma delas vinha usando quatro semanas em silêncio e parou.
    >>  ............................................
```


**Outcome 214 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.unfunded_precautions.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.unfunded_precautions.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond`
- …where the player's next choices will be: "What would it cost to do?" | "I'll bring you iron bars for the shutters." | "Say it costs less than the harvest supper." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.blocked`: the villager reports. Subject `work.werewolf_expert.precautions`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked/1   [99 chars]
    en  %2$s wants doing before %3$s, and the village will not pay for it because nothing has happened yet.
    >>  ............................................
    pt  %2$s precisa ser feito antes de %3$s, e a vila não paga porque nada aconteceu ainda.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked/2   [117 chars]
    en  %3$s. I have the dates for the next four years on a sheet and the sheet has been on the headman's table since spring.
    >>  ............................................
    pt  %3$s. Tenho as datas dos próximos quatro anos numa folha e a folha está na mesa do chefe desde a primavera.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked/3   [120 chars]
    en  The whole of my work is %2$s and a calendar. It is dull, it is cheap, and it is impossible to get anybody excited about.
    >>  ............................................
    pt  Meu trabalho inteiro é %2$s e um calendário. É monótono, é barato, e é impossível empolgar alguém com isso.
    >>  ............................................
```


**Outcome 215 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.unfunded_precautions.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.unfunded_precautions.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond`
- …where the player's next choices will be: "What else is on that sheet?" | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.succeeded`: the villager reports. Subject `work.werewolf_expert.precautions`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded/1   [84 chars]
    en  %2$s is done. Two afternoons, and the sheet has its first tick on it in three years.
    >>  ............................................
    pt  %2$s está feito. Duas tardes, e a folha ganhou o primeiro visto em três anos.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded/2   [120 chars]
    en  I made the comparison at the meeting and there was a silence and then somebody said fine, and that was the whole battle.
    >>  ............................................
    pt  Fiz a comparação na reunião, houve um silêncio e depois alguém disse tudo bem, e a batalha inteira foi essa.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded/3   [81 chars]
    en  Nothing has happened since, which is what I wanted and is impossible to point at.
    >>  ............................................
    pt  Nada aconteceu desde então, que é o que eu queria e é impossível de apontar.
    >>  ............................................
```


**Outcome 216 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.a_confidence.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.a_confidence.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.a_confidence.active.respond`
- …where the player's next choices will be: "What does the help look like?" | "That name is yours to keep." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.a_confidence.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.a_confidence.active`: the villager reports. Subject `work.werewolf_expert.confidence`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active/1   [85 chars]
    en  There is %2$s, and that is the whole of what I am going to tell you or anybody, ever.
    >>  ............................................
    pt  Existe %2$s, e é tudo o que eu vou contar a você ou a quem quer que seja, nunca.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active/2   [120 chars]
    en  %2$s. They came for help and the help is practical and boring and it works, and none of it requires the village to know.
    >>  ............................................
    pt  %2$s. Vieram por ajuda, e a ajuda é prática, chata e funciona, e nada disso exige que a vila saiba.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active/3   [134 chars]
    en  I am carrying something for %2$s and it is not heavy. What is heavy is being asked about it four times a week by people who mean well.
    >>  ............................................
    pt  Estou carregando algo por %2$s e não é pesado. Pesado é ser perguntada sobre isso quatro vezes por semana por gente bem-intencionada.
    >>  ............................................
```


**Outcome 217 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.a_confidence.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.a_confidence.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond`
- …where the player's next choices will be: "Two uneventful years is the whole point." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.a_confidence.succeeded`: the villager reports. Subject `work.werewolf_expert.confidence`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded/1   [114 chars]
    en  Two years of it now, and it has never once been a problem for anybody, and nobody in this village knows it exists.
    >>  ............................................
    pt  Dois anos disso agora, e nunca foi problema para ninguém, e ninguém nesta vila sabe que existe.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded/2   [137 chars]
    en  They have started helping me with the calendar. It turns out the person who most needs the dates right is also the best at checking them.
    >>  ............................................
    pt  Começaram a me ajudar com o calendário. Acontece que quem mais precisa das datas certas é também quem melhor as confere.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded/3   [134 chars]
    en  It is the most successful thing I have ever done and I will never be able to describe it to anybody, which is exactly the arrangement.
    >>  ............................................
    pt  É a coisa mais bem-sucedida que eu já fiz e eu nunca vou poder descrever a ninguém, que é exatamente o arranjo.
    >>  ............................................
```


**Outcome 218 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.pressed_for_a_name.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.pressed_for_a_name.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond`
- …where the player's next choices will be: "Why is a name the wrong answer?" | "Hold to that, however they ask." | "That's a lot of pressure to stand under." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.blocked`: the villager reports. Subject `work.werewolf_expert.the_village_fear`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked/1   [87 chars]
    en  %2$s has asked me for a name three times this month, in increasingly reasonable voices.
    >>  ............................................
    pt  %2$s me pediu um nome três vezes este mês, em vozes cada vez mais razoáveis.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked/2   [138 chars]
    en  %2$s. They are not being cruel. They are frightened and they have decided that a name is the same thing as safety, and it is the opposite.
    >>  ............................................
    pt  %2$s. Não estão sendo cruéis. Estão com medo e decidiram que um nome é a mesma coisa que segurança, e é o oposto.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked/3   [119 chars]
    en  I have said no to %2$s and I will keep saying no, and the fourth time will be harder than the first three put together.
    >>  ............................................
    pt  Eu disse não a %2$s e vou continuar dizendo não, e a quarta vez vai ser mais difícil que as três primeiras juntas.
    >>  ............................................
```


**Outcome 219 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.werewolf_expert.pressed_for_a_name.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.werewolf_expert.pressed_for_a_name.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond`
- …where the player's next choices will be: "Writing it down outlasts you." | "I'll let you get back to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.succeeded`: the villager reports. Subject `work.werewolf_expert.the_village_fear`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded/1   [85 chars]
    en  I wrote down why and gave it to two people, and %2$s read it and has not asked since.
    >>  ............................................
    pt  Escrevi o motivo e entreguei a duas pessoas, e %2$s leu e não pediu mais.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded/2   [137 chars]
    en  Four refusals and then a written page. The page did what four conversations could not, which I should have worked out three refusals ago.
    >>  ............................................
    pt  Quatro recusas e depois uma página escrita. A página fez o que quatro conversas não fizeram, o que eu deveria ter concluído três recusas antes.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded/3   [124 chars]
    en  %2$s told me last week that they had been glad I said no, which took a year and is the only apology I am ever likely to get.
    >>  ............................................
    pt  %2$s me disse semana passada que ficou feliz de eu ter dito não, o que levou um ano e é a única desculpa que eu provavelmente vou receber.
    >>  ............................................
```


**Outcome 220 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:adventurer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.adventurer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.adventurer`: the villager reports. Subject `work.adventurer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.adventurer.resume/1   [92 chars]
    en  I've been pricing roofs. Not buying one. Pricing one, which is further than I've got before.
    >>  ............................................
    pt  Ando pesquisando preços de telhado. Não comprando. Pesquisando, o que já é mais longe do que cheguei.
    >>  ............................................
  dialogue.conversations.work.adventurer.resume/2   [74 chars]
    en  Still the same road. I said one more and I've walked three since we spoke.
    >>  ............................................
    pt  Ainda a mesma estrada. Eu disse mais uma e andei três desde que conversamos.
    >>  ............................................
  dialogue.conversations.work.adventurer.resume/3   [92 chars]
    en  The roof got smaller in my head. That's either progress or giving up and I can't tell which.
    >>  ............................................
    pt  O telhado ficou menor na minha cabeça. Ou é progresso ou é desistência e eu não sei dizer.
    >>  ............................................
```


**Outcome 221 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:archer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.archer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.archer`: the villager reports. Subject `work.archer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.archer.resume/1   [95 chars]
    en  I put the second-archer request in writing. It's on the mayor's table under three other things.
    >>  ............................................
    pt  Botei o pedido do segundo arqueiro por escrito. Está na mesa do prefeito sob outras três coisas.
    >>  ............................................
  dialogue.conversations.work.archer.resume/2   [72 chars]
    en  No second archer. I did get one night off, which somebody covered badly.
    >>  ............................................
    pt  Sem segundo arqueiro. Consegui uma noite de folga, que alguém cobriu mal.
    >>  ............................................
  dialogue.conversations.work.archer.resume/3   [94 chars]
    en  I've found a candidate. Fifteen years old and better than I was at fifteen, which is annoying.
    >>  ............................................
    pt  Achei uma candidata. Quinze anos e melhor do que eu era aos quinze, o que é irritante.
    >>  ............................................
```


**Outcome 222 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:armorer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.armorer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.armorer`: the villager reports. Subject `work.armorer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.armorer.resume/1   [83 chars]
    en  I started the suit. It's in pieces and I've already remade the left pauldron twice.
    >>  ............................................
    pt  Comecei a armadura. Está em pedaços e já refiz a espaldeira esquerda duas vezes.
    >>  ............................................
  dialogue.conversations.work.armorer.resume/2   [75 chars]
    en  Not started. Every week something urgent turns up wearing somebody's blood.
    >>  ............................................
    pt  Nem comecei. Toda semana aparece algo urgente com o sangue de alguém.
    >>  ............................................
  dialogue.conversations.work.armorer.resume/3   [79 chars]
    en  I finished a piece of it. Somebody asked what it was for and I couldn't answer.
    >>  ............................................
    pt  Terminei uma peça. Alguém perguntou pra que servia e eu não soube responder.
    >>  ............................................
```


**Outcome 223 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:butcher"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.butcher.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.butcher`: the villager reports. Subject `work.butcher.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.butcher.resume/1   [89 chars]
    en  I walked the ground for the new pens. There's a spot that would work if the smith agrees.
    >>  ............................................
    pt  Andei pelo terreno pros currais novos. Tem um lugar que serviria se o ferreiro concordar.
    >>  ............................................
  dialogue.conversations.work.butcher.resume/2   [71 chars]
    en  The pens haven't moved. I've stopped asking and started saving instead.
    >>  ............................................
    pt  Os currais não saíram do lugar. Parei de pedir e comecei a juntar dinheiro.
    >>  ............................................
  dialogue.conversations.work.butcher.resume/3   [75 chars]
    en  A child asked me what the sound was. That moved it up my list considerably.
    >>  ............................................
    pt  Uma criança me perguntou que barulho era aquele. Isso subiu bastante na minha lista.
    >>  ............................................
```


**Outcome 224 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:cartographer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.cartographer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.cartographer`: the villager reports. Subject `work.cartographer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.cartographer.resume/1   [87 chars]
    en  I've a fourth description of the coast now. It contradicts the other three differently.
    >>  ............................................
    pt  Tenho uma quarta descrição da costa. Ela contradiz as outras três de um jeito novo.
    >>  ............................................
  dialogue.conversations.work.cartographer.resume/2   [89 chars]
    en  Nobody's taken me north yet. The map still has that guess on it and I hate looking at it.
    >>  ............................................
    pt  Ninguém me levou ao norte ainda. O mapa ainda tem aquele chute e eu odeio olhar.
    >>  ............................................
  dialogue.conversations.work.cartographer.resume/3   [88 chars]
    en  I redrew it from scratch. It's honest now, and honest means a great deal of blank paper.
    >>  ............................................
    pt  Redesenhei do zero. Está honesto agora, e honesto significa muito papel em branco.
    >>  ............................................
```


**Outcome 225 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:cleric"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.cleric.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.cleric`: the villager reports. Subject `work.cleric.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.cleric.resume/1   [92 chars]
    en  I've a fourth candidate. She sat through an afternoon without asking once about the recipes.
    >>  ............................................
    pt  Tenho uma quarta candidata. Ela passou uma tarde inteira sem perguntar das receitas.
    >>  ............................................
  dialogue.conversations.work.cleric.resume/2   [86 chars]
    en  No apprentice. I did the afternoons alone all season and I'm told that's my own fault.
    >>  ............................................
    pt  Sem aprendiz. Fiz as tardes sozinho a estação toda e me dizem que a culpa é minha.
    >>  ............................................
  dialogue.conversations.work.cleric.resume/3   [81 chars]
    en  One lasted eleven days. That's the record and I'm not sure whether to be pleased.
    >>  ............................................
    pt  Um durou onze dias. É o recorde e não sei se devo ficar contente.
    >>  ............................................
```


**Outcome 226 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:cultist"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.cultist.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.cultist`: the villager reports. Subject `work.cultist.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.cultist.resume/1   [95 chars]
    en  I've tested a fourth. He reached line nineteen and asked what it meant, which disqualifies him.
    >>  ............................................
    pt  Testei um quarto. Chegou na linha dezenove e perguntou o que significava, o que o desclassifica.
    >>  ............................................
  dialogue.conversations.work.cultist.resume/2   [71 chars]
    en  Nobody yet. I've started writing them down, which I said I never would.
    >>  ............................................
    pt  Ninguém ainda. Comecei a anotá-las, o que eu disse que nunca faria.
    >>  ............................................
  dialogue.conversations.work.cultist.resume/3   [81 chars]
    en  One passed. I've told nobody, because saying it aloud would make it a succession.
    >>  ............................................
    pt  Um passou. Não contei a ninguém, porque dizer em voz alta viraria uma sucessão.
    >>  ............................................
```


**Outcome 227 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "chefsdelight:delightchef"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.delightchef.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.delightchef`: the villager reports. Subject `work.delightchef.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.delightchef.resume/1   [78 chars]
    en  I've a second pair of hands two days a week. December is now merely difficult.
    >>  ............................................
    pt  Tenho um segundo par de mãos dois dias por semana. Dezembro agora é só difícil.
    >>  ............................................
  dialogue.conversations.work.delightchef.resume/2   [64 chars]
    en  Still alone in the kitchen. December went the way December goes.
    >>  ............................................
    pt  Ainda sozinho na cozinha. Dezembro foi como dezembro sempre é.
    >>  ............................................
  dialogue.conversations.work.delightchef.resume/3   [76 chars]
    en  I trained somebody and they left for a town kitchen. I'd have done the same.
    >>  ............................................
    pt  Treinei alguém e a pessoa foi pra uma cozinha na cidade. Eu teria feito o mesmo.
    >>  ............................................
```


**Outcome 228 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "chefsdelight:delightcook"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.delightcook.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.delightcook`: the villager reports. Subject `work.delightcook.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.delightcook.resume/1   [84 chars]
    en  The pot has a fund now. Small, and it is a fund, and thinning is about barley again.
    >>  ............................................
    pt  A panela tem um fundo agora. Pequeno, mas é um fundo, e ralear voltou a ser sobre cevada.
    >>  ............................................
  dialogue.conversations.work.delightcook.resume/2   [77 chars]
    en  Not funded. I thinned it twice this month and both times it was about people.
    >>  ............................................
    pt  Sem fundo. Ralei duas vezes este mês e nas duas foi sobre pessoas.
    >>  ............................................
  dialogue.conversations.work.delightcook.resume/3   [80 chars]
    en  Two households put in without being asked. I've not stopped thinking about that.
    >>  ............................................
    pt  Duas casas contribuíram sem que eu pedisse. Não parei de pensar nisso.
    >>  ............................................
```


**Outcome 229 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:enderian"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.enderian.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.enderian`: the villager reports. Subject `work.enderian.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.enderian.resume/1   [89 chars]
    en  I showed the catalogue to the librarian. She turned three pages and asked good questions.
    >>  ............................................
    pt  Mostrei o catálogo à bibliotecária. Ela virou três páginas e fez boas perguntas.
    >>  ............................................
  dialogue.conversations.work.enderian.resume/2   [74 chars]
    en  Still mine. Everyone who's seen it has handled it as though it might bite.
    >>  ............................................
    pt  Ainda é meu. Todos que viram seguraram como se fosse morder.
    >>  ............................................
  dialogue.conversations.work.enderian.resume/3   [70 chars]
    en  I've begun copying it, which means I've decided somebody will have it.
    >>  ............................................
    pt  Comecei a copiá-lo, o que significa que decidi que alguém vai ficar com ele.
    >>  ............................................
```


**Outcome 230 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:engineer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.engineer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.engineer`: the villager reports. Subject `work.engineer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.engineer.resume/1   [90 chars]
    en  The race is surveyed. Digging it is a matter of hands and a summer, which is to say money.
    >>  ............................................
    pt  O canal está levantado. Cavar é questão de mãos e um verão, ou seja, de dinheiro.
    >>  ............................................
  dialogue.conversations.work.engineer.resume/2   [62 chars]
    en  Not dug. The mill runs in August the way it always has, badly.
    >>  ............................................
    pt  Não foi cavado. O moinho roda em agosto como sempre rodou, mal.
    >>  ............................................
  dialogue.conversations.work.engineer.resume/3   [88 chars]
    en  We dug forty feet of it. Forty feet is nothing, and it's forty feet more than last year.
    >>  ............................................
    pt  Cavamos doze metros. Doze metros não é nada, e é doze metros a mais que ano passado.
    >>  ............................................
```


**Outcome 231 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:farmer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.farmer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.farmer`: the villager reports. Subject `work.farmer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.farmer.resume/1   [75 chars]
    en  I got three weeks of only the field. Three weeks, and I slept like a stone.
    >>  ............................................
    pt  Consegui três semanas só de roça. Três semanas, e dormi feito pedra.
    >>  ............................................
  dialogue.conversations.work.farmer.resume/2   [81 chars]
    en  No such season. Fences, market, and an argument about a boundary that isn't mine.
    >>  ............................................
    pt  Estação nenhuma. Cercas, feira, e uma briga sobre uma divisa que nem é minha.
    >>  ............................................
  dialogue.conversations.work.farmer.resume/3   [68 chars]
    en  I traded the market days away. It cost me money and I'd do it again.
    >>  ............................................
    pt  Troquei os dias de feira. Me custou dinheiro e eu faria de novo.
    >>  ............................................
```


**Outcome 232 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:fisherman"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.fisherman.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.fisherman`: the villager reports. Subject `work.fisherman.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.fisherman.resume/1   [75 chars]
    en  I got as far as the headland. Not the stretch itself, but I've seen it now.
    >>  ............................................
    pt  Cheguei até o promontório. Não o trecho em si, mas já vi de longe.
    >>  ............................................
  dialogue.conversations.work.fisherman.resume/2   [69 chars]
    en  Still thinking about it more than I fish here, which the catch shows.
    >>  ............................................
    pt  Ainda penso mais nisso do que pesco aqui, e a pescaria mostra.
    >>  ............................................
  dialogue.conversations.work.fisherman.resume/3   [74 chars]
    en  I asked about the boat. It'd take a fortnight and nobody to mind the nets.
    >>  ............................................
    pt  Perguntei sobre o barco. Levaria quinze dias e não há quem cuide das redes.
    >>  ............................................
```


**Outcome 233 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:fletcher"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.fletcher.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.fletcher`: the villager reports. Subject `work.fletcher.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.fletcher.resume/1   [83 chars]
    en  I've a stave seasoning. It'll be two years before it's a bow, and that's the trade.
    >>  ............................................
    pt  Tenho uma vara curando. Vai levar dois anos até virar arco, e é assim o ofício.
    >>  ............................................
  dialogue.conversations.work.fletcher.resume/2   [59 chars]
    en  No bow. Arrows, arrows, arrows, and a winter of them ahead.
    >>  ............................................
    pt  Sem arco. Flechas, flechas, flechas, e um inverno delas pela frente.
    >>  ............................................
  dialogue.conversations.work.fletcher.resume/3   [78 chars]
    en  I ruined the first stave. That's apparently normal and it did not feel normal.
    >>  ............................................
    pt  Estraguei a primeira vara. Dizem que é normal e não pareceu normal.
    >>  ............................................
```


**Outcome 234 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:florist"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.florist.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.florist`: the villager reports. Subject `work.florist.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.florist.resume/1   [69 chars]
    en  I've the glass promised, secondhand, from a house that's coming down.
    >>  ............................................
    pt  Tenho o vidro prometido, de segunda mão, de uma casa que vai ser derrubada.
    >>  ............................................
  dialogue.conversations.work.florist.resume/2   [70 chars]
    en  No glasshouse. February was February and I sold nothing for six weeks.
    >>  ............................................
    pt  Sem estufa. Fevereiro foi fevereiro e não vendi nada por seis semanas.
    >>  ............................................
  dialogue.conversations.work.florist.resume/3   [68 chars]
    en  I built a cold frame instead. It's a tenth of the plan and it works.
    >>  ............................................
    pt  Fiz um viveiro frio no lugar. É um décimo do plano e funciona.
    >>  ............................................
```


**Outcome 235 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:guard"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.guard.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.guard`: the villager reports. Subject `work.guard.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.guard.resume/1   [84 chars]
    en  There's a second guard, three nights a week. Four years is now two people's problem.
    >>  ............................................
    pt  Tem um segundo guarda, três noites por semana. Quatro anos agora é problema de dois.
    >>  ............................................
  dialogue.conversations.work.guard.resume/2   [63 chars]
    en  Still one guard. The wall doesn't know the difference and I do.
    >>  ............................................
    pt  Ainda um guarda só. O muro não sabe a diferença e eu sei.
    >>  ............................................
  dialogue.conversations.work.guard.resume/3   [75 chars]
    en  They gave me a candidate who's frightened of the dark. I'm working with it.
    >>  ............................................
    pt  Me deram um candidato com medo do escuro. Estou trabalhando com isso.
    >>  ............................................
```


**Outcome 236 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:hunter"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.hunter.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.hunter`: the villager reports. Subject `work.hunter.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hunter.resume/1   [85 chars]
    en  The count is in the librarian's hand now. Twenty years of it, in ink that isn't mine.
    >>  ............................................
    pt  A contagem está com a bibliotecária agora. Vinte anos disso, em tinta que não é minha.
    >>  ............................................
  dialogue.conversations.work.hunter.resume/2   [63 chars]
    en  Not written. I've said I'll do it every winter for six winters.
    >>  ............................................
    pt  Não escrevi. Digo que vou fazer todo inverno há seis invernos.
    >>  ............................................
  dialogue.conversations.work.hunter.resume/3   [73 chars]
    en  I started it and reached the third year before I had to stop for a while.
    >>  ............................................
    pt  Comecei e cheguei no terceiro ano antes de ter que parar um tempo.
    >>  ............................................
```


**Outcome 237 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "vampirism:hunter_expert"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.hunter_expert.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.hunter_expert`: the villager reports. Subject `work.hunter_expert.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.hunter_expert.resume/1   [65 chars]
    en  They're slower than me now, which is the point, and they hate it.
    >>  ............................................
    pt  Agora são mais lentos que eu, que é o objetivo, e eles odeiam.
    >>  ............................................
  dialogue.conversations.work.hunter_expert.resume/2   [63 chars]
    en  One left. The other is better than I was and I've not told her.
    >>  ............................................
    pt  Um foi embora. A outra é melhor do que eu era e eu não contei a ela.
    >>  ............................................
  dialogue.conversations.work.hunter_expert.resume/3   [73 chars]
    en  I've stopped correcting them mid-hunt. That was harder than the teaching.
    >>  ............................................
    pt  Parei de corrigi-los no meio da caçada. Foi mais difícil que ensinar.
    >>  ............................................
```


**Outcome 238 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:leatherworker"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.leatherworker.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.leatherworker`: the villager reports. Subject `work.leatherworker.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.leatherworker.resume/1   [97 chars]
    en  Twelve years now. The mayor has agreed in principle, which is the twelfth agreement in principle.
    >>  ............................................
    pt  Doze anos agora. O prefeito concordou em princípio, que é o décimo segundo acordo em princípio.
    >>  ............................................
  dialogue.conversations.work.leatherworker.resume/2   [84 chars]
    en  Not moved. I've started tanning further out instead, which costs me a mile each way.
    >>  ............................................
    pt  Não mudou. Comecei a curtir mais longe, o que me custa um quilômetro e meio de ida e volta.
    >>  ............................................
  dialogue.conversations.work.leatherworker.resume/3   [84 chars]
    en  Somebody downstream complained and it moved further in a month than in eleven years.
    >>  ............................................
    pt  Alguém rio abaixo reclamou e andou mais em um mês do que em onze anos.
    >>  ............................................
```


**Outcome 239 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:librarian"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.librarian.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.librarian`: the villager reports. Subject `work.librarian.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.librarian.resume/1   [80 chars]
    en  The second room exists. Four people sat in it last week and nobody read a thing.
    >>  ............................................
    pt  A segunda sala existe. Quatro pessoas sentaram nela semana passada e ninguém leu nada.
    >>  ............................................
  dialogue.conversations.work.librarian.resume/2   [55 chars]
    en  No room. The books have the space and the people stand.
    >>  ............................................
    pt  Sem sala. Os livros têm o espaço e as pessoas ficam de pé.
    >>  ............................................
  dialogue.conversations.work.librarian.resume/3   [76 chars]
    en  I cleared a corner instead. Two chairs. It has already changed who comes in.
    >>  ............................................
    pt  Limpei um canto no lugar. Duas cadeiras. Já mudou quem entra aqui.
    >>  ............................................
```


**Outcome 240 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:mason"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.mason.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.mason`: the villager reports. Subject `work.mason.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.mason.resume/1   [80 chars]
    en  I've cut the voussoirs. They're stacked in the yard looking like nothing at all.
    >>  ............................................
    pt  Cortei as aduelas. Estão empilhadas no pátio parecendo coisa nenhuma.
    >>  ............................................
  dialogue.conversations.work.mason.resume/2   [45 chars]
    en  No arch. Walls, walls, a chimney, and a wall.
    >>  ............................................
    pt  Sem arco. Muros, muros, uma chaminé, e um muro.
    >>  ............................................
  dialogue.conversations.work.mason.resume/3   [72 chars]
    en  The centring collapsed. I'll build it again and I've learned the reason.
    >>  ............................................
    pt  O cimbre desabou. Vou construir de novo e já aprendi o motivo.
    >>  ............................................
```


**Outcome 241 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:mercenary"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.mercenary.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.mercenary`: the villager reports. Subject `work.mercenary.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.mercenary.resume/1   [65 chars]
    en  I've had an offer. Bad wage, real name, and I've not said no yet.
    >>  ............................................
    pt  Recebi uma oferta. Salário ruim, nome de verdade, e ainda não disse não.
    >>  ............................................
  dialogue.conversations.work.mercenary.resume/2   [67 chars]
    en  No post. Two contracts instead, both of which I regret differently.
    >>  ............................................
    pt  Sem posto. Dois contratos no lugar, e me arrependo dos dois de jeitos diferentes.
    >>  ............................................
  dialogue.conversations.work.mercenary.resume/3   [82 chars]
    en  I turned down a purse for a gate watch. Ask me next winter whether that was sense.
    >>  ............................................
    pt  Recusei uma bolsa por uma vigia de portão. Me pergunte no inverno se foi juízo.
    >>  ............................................
```


**Outcome 242 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:miner"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.miner.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.miner`: the villager reports. Subject `work.miner.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.miner.resume/1   [82 chars]
    en  There's a second man below twice a week now. It isn't enough and it isn't nothing.
    >>  ............................................
    pt  Tem um segundo homem lá embaixo duas vezes por semana. Não é suficiente e não é nada.
    >>  ............................................
  dialogue.conversations.work.miner.resume/2   [63 chars]
    en  Still alone down there. I've been careful, which is not a plan.
    >>  ............................................
    pt  Ainda sozinho lá embaixo. Ando com cuidado, o que não é um plano.
    >>  ............................................
  dialogue.conversations.work.miner.resume/3   [75 chars]
    en  A boy asked to learn. I said no, and I've thought about it every day since.
    >>  ............................................
    pt  Um garoto pediu pra aprender. Eu disse não, e penso nisso todo dia desde então.
    >>  ............................................
```


**Outcome 243 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:netherian"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.netherian.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.netherian`: the villager reports. Subject `work.netherian.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.netherian.resume/1   [78 chars]
    en  The cleric has it. She's already found two mistakes I made and hadn't noticed.
    >>  ............................................
    pt  A clériga está com ele. Ela já achou dois erros meus que eu não tinha notado.
    >>  ............................................
  dialogue.conversations.work.netherian.resume/2   [67 chars]
    en  Still on my shelf. I keep deciding to hand it over on a better day.
    >>  ............................................
    pt  Ainda na minha prateleira. Vivo decidindo entregar num dia melhor.
    >>  ............................................
  dialogue.conversations.work.netherian.resume/3   [70 chars]
    en  I copied the worst chapter for her first, to see what she did with it.
    >>  ............................................
    pt  Copiei o pior capítulo pra ela primeiro, pra ver o que ela fazia com aquilo.
    >>  ............................................
```


**Outcome 244 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:nitwit"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.nitwit.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.nitwit`: the villager reports. Subject `work.nitwit.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.nitwit.resume/1   [78 chars]
    en  They gave me the well. It's a post, it has a name, and people say it out loud.
    >>  ............................................
    pt  Me deram o poço. É um posto, tem nome, e as pessoas dizem em voz alta.
    >>  ............................................
  dialogue.conversations.work.nitwit.resume/2   [60 chars]
    en  No post. I've asked twice more and been kindly not answered.
    >>  ............................................
    pt  Sem posto. Pedi mais duas vezes e gentilmente não me responderam.
    >>  ............................................
  dialogue.conversations.work.nitwit.resume/3   [84 chars]
    en  I've been minding the gate unofficially. Nobody's stopped me, which is nearly a job.
    >>  ............................................
    pt  Ando cuidando do portão sem ser oficial. Ninguém me impediu, o que é quase um emprego.
    >>  ............................................
```


**Outcome 245 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:none"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.none.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.none`: the villager reports. Subject `work.none.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.none.resume/1   [80 chars]
    en  I've been sweeping the square. Nobody asked, and now they'd notice if I stopped.
    >>  ............................................
    pt  Ando varrendo a praça. Ninguém pediu, e agora notariam se eu parasse.
    >>  ............................................
  dialogue.conversations.work.none.resume/2   [80 chars]
    en  Nothing yet. The village manages without me, which is what I said I didn't want.
    >>  ............................................
    pt  Nada ainda. O vilarejo se vira sem mim, que é justo o que eu disse não querer.
    >>  ............................................
  dialogue.conversations.work.none.resume/3   [80 chars]
    en  The mayor mentioned a post. Mentioned. I've been living on the word for a month.
    >>  ............................................
    pt  O prefeito mencionou um posto. Mencionou. Vivo dessa palavra faz um mês.
    >>  ............................................
```


**Outcome 246 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:oceanographer"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.oceanographer.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.oceanographer`: the villager reports. Subject `work.oceanographer.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.oceanographer.resume/1   [76 chars]
    en  The second post is marked out. Marked out, not built, but the stakes are in.
    >>  ............................................
    pt  O segundo posto está demarcado. Demarcado, não construído, mas as estacas estão fincadas.
    >>  ............................................
  dialogue.conversations.work.oceanographer.resume/2   [77 chars]
    en  One post still. Nineteen years is now twenty years of a single line of marks.
    >>  ............................................
    pt  Ainda um posto. Dezenove anos agora são vinte anos de uma única linha de marcas.
    >>  ............................................
  dialogue.conversations.work.oceanographer.resume/3   [88 chars]
    en  Somebody offered to keep the second set of readings. A stranger. I said yes too quickly.
    >>  ............................................
    pt  Alguém ofereceu registrar a segunda série de leituras. Um estranho. Eu disse sim rápido demais.
    >>  ............................................
```


**Outcome 247 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "mca:outlaw"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.outlaw.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.outlaw`: the villager reports. Subject `work.outlaw.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.outlaw.resume/1   [73 chars]
    en  The woodworker let me hold a chisel. In daylight, where people could see.
    >>  ............................................
    pt  O marceneiro me deixou segurar um formão. À luz do dia, onde as pessoas viam.
    >>  ............................................
  dialogue.conversations.work.outlaw.resume/2   [75 chars]
    en  No trade. Nobody wants to be the one seen teaching me, and I understand it.
    >>  ............................................
    pt  Sem ofício. Ninguém quer ser visto me ensinando, e eu entendo.
    >>  ............................................
  dialogue.conversations.work.outlaw.resume/3   [69 chars]
    en  The smith said maybe. Maybe is the best word I've had in three years.
    >>  ............................................
    pt  O ferreiro disse talvez. Talvez é a melhor palavra que ouvi em três anos.
    >>  ............................................
```


**Outcome 248 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "vampirism:priest"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.priest.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.priest`: the villager reports. Subject `work.priest.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.priest.resume/1   [89 chars]
    en  I've told one person a third of the list. They've not asked for the rest, which is right.
    >>  ............................................
    pt  Contei a uma pessoa um terço da lista. Ela não pediu o resto, o que é certo.
    >>  ............................................
  dialogue.conversations.work.priest.resume/2   [70 chars]
    en  Still only mine. If I go tonight the list goes with me, and I know it.
    >>  ............................................
    pt  Ainda só minha. Se eu partir esta noite a lista vai comigo, e eu sei disso.
    >>  ............................................
  dialogue.conversations.work.priest.resume/3   [75 chars]
    en  I began writing it down and burned the page. It isn't a list to be written.
    >>  ............................................
    pt  Comecei a escrever e queimei a página. Não é uma lista pra ser escrita.
    >>  ............................................
```


**Outcome 249 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "iceandfire:scribe"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.scribe.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.scribe`: the villager reports. Subject `work.scribe.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.scribe.resume/1   [97 chars]
    en  The second copies exist. Six of them, in the cleric's cellar, which is not stone but is not here.
    >>  ............................................
    pt  As segundas cópias existem. Seis delas, no porão da clériga, que não é pedra mas não é aqui.
    >>  ............................................
  dialogue.conversations.work.scribe.resume/2   [68 chars]
    en  No room, no copies. One fire and nineteen years of work is a rumour.
    >>  ............................................
    pt  Sem sala, sem cópias. Um incêndio e dezenove anos de trabalho viram boato.
    >>  ............................................
  dialogue.conversations.work.scribe.resume/3   [83 chars]
    en  I copied the three that matter first. If it burns now we lose less than everything.
    >>  ............................................
    pt  Copiei as três que importam primeiro. Se pegar fogo agora perdemos menos que tudo.
    >>  ............................................
```


**Outcome 250 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "ars_nouveau:shady_wizard"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.shady_wizard.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.shady_wizard`: the villager reports. Subject `work.shady_wizard.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.shady_wizard.resume/1   [85 chars]
    en  I said it. To a customer. She came back the next day, which I still don't understand.
    >>  ............................................
    pt  Eu disse. A uma cliente. Ela voltou no dia seguinte, o que eu ainda não entendo.
    >>  ............................................
  dialogue.conversations.work.shady_wizard.resume/2   [74 chars]
    en  Not yet. I sold certainty three times this week and slept badly each time.
    >>  ............................................
    pt  Ainda não. Vendi certeza três vezes esta semana e dormi mal nas três.
    >>  ............................................
  dialogue.conversations.work.shady_wizard.resume/3   [67 chars]
    en  I said it once and lost the sale. It felt better than the sale did.
    >>  ............................................
    pt  Disse uma vez e perdi a venda. Foi melhor do que a venda teria sido.
    >>  ............................................
```


**Outcome 251 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:shepherd"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.shepherd.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.shepherd`: the villager reports. Subject `work.shepherd.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.shepherd.resume/1   [82 chars]
    en  Eleven fewer sheep and four of them past nine years. It's a strange sort of pride.
    >>  ............................................
    pt  Onze ovelhas a menos e quatro delas com mais de nove anos. É um orgulho estranho.
    >>  ............................................
  dialogue.conversations.work.shepherd.resume/2   [59 chars]
    en  Same flock. The market decides these things more than I do.
    >>  ............................................
    pt  Mesmo rebanho. O mercado decide essas coisas mais do que eu.
    >>  ............................................
  dialogue.conversations.work.shepherd.resume/3   [70 chars]
    en  I kept one back that I should have sold. That's how these plans start.
    >>  ............................................
    pt  Segurei uma que eu devia ter vendido. É assim que esses planos começam.
    >>  ............................................
```


**Outcome 252 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:toolsmith"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.toolsmith.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.toolsmith`: the villager reports. Subject `work.toolsmith.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.toolsmith.resume/1   [88 chars]
    en  I made six at the new angle. The farmer took one and hasn't complained, which is praise.
    >>  ............................................
    pt  Fiz seis no ângulo novo. O agricultor levou uma e não reclamou, o que é elogio.
    >>  ............................................
  dialogue.conversations.work.toolsmith.resume/2   [77 chars]
    en  Still nonsense, apparently. I've made none, because I'd have to explain them.
    >>  ............................................
    pt  Ainda é bobagem, dizem. Não fiz nenhuma, porque teria que explicá-las.
    >>  ............................................
  dialogue.conversations.work.toolsmith.resume/3   [85 chars]
    en  I made one and used it myself all spring. My back has an opinion and it's favourable.
    >>  ............................................
    pt  Fiz uma e usei eu mesmo a primavera toda. Minha coluna tem opinião e é favorável.
    >>  ............................................
```


**Outcome 253 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "vampirism:vampire_expert"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.vampire_expert.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.vampire_expert`: the villager reports. Subject `work.vampire_expert.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.vampire_expert.resume/1   [79 chars]
    en  The letters are lodged with the cleric, sealed. She doesn't know what they are.
    >>  ............................................
    pt  As cartas estão com a clériga, lacradas. Ela não sabe o que são.
    >>  ............................................
  dialogue.conversations.work.vampire_expert.resume/2   [72 chars]
    en  Still in my house. If it burns, four families never learn what happened.
    >>  ............................................
    pt  Ainda na minha casa. Se pegar fogo, quatro famílias nunca saberão o que houve.
    >>  ............................................
  dialogue.conversations.work.vampire_expert.resume/3   [72 chars]
    en  I nearly read one again. I've moved them somewhere I can't reach easily.
    >>  ............................................
    pt  Quase li uma de novo. Mudei elas pra um lugar que não alcanço fácil.
    >>  ............................................
```


**Outcome 254 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "minecraft:weaponsmith"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.weaponsmith.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.weaponsmith`: the villager reports. Subject `work.weaponsmith.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.weaponsmith.resume/1   [75 chars]
    en  I've made eleven ploughs and three swords. That ratio is new and I like it.
    >>  ............................................
    pt  Fiz onze arados e três espadas. Essa proporção é nova e eu gosto.
    >>  ............................................
  dialogue.conversations.work.weaponsmith.resume/2   [58 chars]
    en  No ploughs. The orders come in and they're not for fields.
    >>  ............................................
    pt  Sem arados. Os pedidos chegam e não são pra roça.
    >>  ............................................
  dialogue.conversations.work.weaponsmith.resume/3   [63 chars]
    en  I made a plough and gave it away, which is not a business plan.
    >>  ............................................
    pt  Fiz um arado e dei de presente, o que não é um plano de negócios.
    >>  ............................................
```


**Outcome 255 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "werewolves:werewolf_expert"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.werewolf_expert.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.werewolf_expert`: the villager reports. Subject `work.werewolf_expert.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.werewolf_expert.resume/1   [67 chars]
    en  It's written. Sealed, with the priest, and now I sleep differently.
    >>  ............................................
    pt  Está escrito. Lacrado, com o padre, e agora eu durmo diferente.
    >>  ............................................
  dialogue.conversations.work.werewolf_expert.resume/2   [73 chars]
    en  Not written. I keep starting it and stopping at the part about the chain.
    >>  ............................................
    pt  Não escrevi. Vivo começando e parando na parte da corrente.
    >>  ............................................
  dialogue.conversations.work.werewolf_expert.resume/3   [62 chars]
    en  I wrote half. The half I can't write is the half that matters.
    >>  ............................................
    pt  Escrevi metade. A metade que não consigo escrever é a que importa.
    >>  ............................................
```


**Outcome 256 of 261** — base weight `0`

- Fires when: weighted +150 when `profession` = "morevillagers:woodworker"
- Fires when: weighted +100 when arc `work` is at stage 1..2
- Fires when: RULED OUT when arc `work` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.arc.work.resume.respond`
- …where the player's next choices will be: "What's the next step?" | "Tell me what would move it." | "You said the same thing last time." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.work.woodworker.resume
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.arc.work.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.resume.opener.woodworker`: the villager reports. Subject `work.woodworker.future`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, challenge, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.woodworker.resume/1   [80 chars]
    en  I've the timber for the staircase. It's stacked, and I look at it every morning.
    >>  ............................................
    pt  Tenho a madeira da escada. Está empilhada, e eu olho pra ela toda manhã.
    >>  ............................................
  dialogue.conversations.work.woodworker.resume/2   [64 chars]
    en  No staircase. Doors, three doors, and a bench that needed doing.
    >>  ............................................
    pt  Sem escada. Portas, três portas, e um banco que precisava ser feito.
    >>  ............................................
  dialogue.conversations.work.woodworker.resume/3   [73 chars]
    en  I turned the newel post. Just that. It's on my bench like a small trophy.
    >>  ............................................
    pt  Torneei o pilar do corrimão. Só isso. Está na bancada como um pequeno troféu.
    >>  ............................................
```


**Outcome 257 of 261** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.work` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `again` budget `standard`
- Then opens: `conversations.topic.work.again.respond`
- …where the player's next choices will be: "Sorry — you've told me." | "Tell me again anyway." | "Fair. Another time."

```text
POOL   dialogue key: dialogue.conversations.work.again
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.topic.work.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.again.to.work.again`: the villager accepts. Subject `work.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work.again/1   [68 chars]
    en  We covered my work already. It hasn't gotten more interesting since.
    >>  ............................................
    pt  A gente já falou do meu trabalho. Ele não ficou mais interessante desde então.
    >>  ............................................
  dialogue.conversations.work.again/2   [67 chars]
    en  The job's the same as it was this morning, %1$s. Reliable that way.
    >>  ............................................
    pt  O serviço está igualzinho a hoje de manhã, %1$s. Confiável nesse ponto.
    >>  ............................................
  dialogue.conversations.work.again/3   [42 chars]
    en  Ask the work how it likes ME for a change.
    >>  ............................................
    pt  Pergunta pro trabalho se ELE gosta de MIM, pra variar.
    >>  ............................................
```


**Outcome 258 of 261** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `child` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.young.respond`
- …where the player's next choices will be: "That's real work, that is." | "Who taught you?" | "That's not a real job." | "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.work.child
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.topic.work.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.child.to.work.young`: the villager accepts. Subject `work.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work.child/1   [44 chars]
    en  My job is being a kid and I'm SO good at it.
    >>  ............................................
    pt  Meu trabalho é ser criança e eu sou MUITO bom nisso.
    >>  ............................................
  dialogue.conversations.work.child/2   [51 chars]
    en  I help the grown-ups sometimes! Mostly I supervise.
    >>  ............................................
    pt  Eu ajudo os adultos às vezes! Mas quase sempre eu supervisiono.
    >>  ............................................
```


**Outcome 259 of 261** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `teen` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.topic.work.young.respond`
- …where the player's next choices will be: "That's real work, that is." | "Who taught you?" | "That's not a real job." | "Back to it, then."

```text
POOL   dialogue key: dialogue.conversations.work.teen
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.topic.work.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.teen.to.work.young`: the villager accepts. Subject `work.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.work.teen/1   [72 chars]
    en  Bit of everything — whatever the grown-ups don't want to do, apparently.
    >>  ............................................
    pt  Um pouco de tudo — o que os adultos não querem fazer, pelo visto.
    >>  ............................................
  dialogue.conversations.work.teen/2   [66 chars]
    en  Still 'finding my calling'. Today my calling was hauling firewood.
    >>  ............................................
    pt  Ainda "achando minha vocação". Hoje a vocação foi carregar lenha.
    >>  ............................................
```


**Outcome 260 of 261** — base weight `1`

- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.work` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `prof` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.work`
- …where the player's next choices will be: "(auto — no button)"
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 261 of 261** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 48000 ticks
- Then opens: `conversations.work.legacy`
- …where the player's next choices will be: "(auto — no button)"
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `work_offer` — "Anything you need doing?"

Shown only when MCA's own constraints hold: `"adult"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `profession.work_offer` — accepted phrasings: "can i help you"; "do you need anything"; "do you need any help"; "got any work for me"; "any tasks for me"
  - the message must contain one of: `hire`, `quest`, `task`, `help`, `need`, `work`
  - scored words: `hire`(1.5), `quest`(1.5), `task`(1.4), `help`(1.0), `need`(1.0), `work`(0.8)

```text
POOL   dialogue key: dialogue.conversations.cat.profession.work_offer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.profession
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.profession.work_offer   [24 chars]
    en  Anything you need doing?
    >>  ............................................
    pt  Precisa que eu faça alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +100 when `conversations_quest_available` = {"scope": "this"}
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work_offer` branch `offer` budget `service`
- Then opens: `conversations.topic.work_offer.respond`
- …where the player's next choices will be: "What needs doing?" | "What's in it for me?" | "I'll do it." | "Not today."

```text
POOL   dialogue key: dialogue.conversations.quest.offer
WHO    VILLAGER — what the player reads after pressing "Anything you need doing?"
       spoken on: conversations.cat.profession, button `work_offer`
       leaves the player on: conversations.topic.work_offer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `quest.offer.to.work_offer`: the villager accepts. Subject `work_offer`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.quest.offer/1   [79 chars]
    en  Since you ask, %1$s — aye, there's a thing or two. Let me show you what I need.
    >>  ............................................
    pt  Já que você pergunta, %1$s — sim, tem uma coisinha ou outra. Deixa eu te mostrar o que eu preciso.
    >>  ............................................
  dialogue.conversations.quest.offer/2   [65 chars]
    en  Funny you should ask, %1$s. I do have something that wants doing.
    >>  ............................................
    pt  Engraçado você perguntar, %1$s. Tenho sim uma coisa que precisa ser feita.
    >>  ............................................
  dialogue.conversations.quest.offer/3   [68 chars]
    en  As it happens, %1$s, there's work if you want it. Here, take a look.
    >>  ............................................
    pt  Por acaso, %1$s, tem serviço se você quiser. Aqui, dá uma olhada.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when `conversations_quest_available` = {"scope": "this"}  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work_offer` branch `none` budget `service`
- Then opens: `conversations.topic.work_offer.none.respond`
- …where the player's next choices will be: "Anything I can help with regardless?" | "I'll check back." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.quest.none
WHO    VILLAGER — what the player reads after pressing "Anything you need doing?"
       spoken on: conversations.cat.profession, button `work_offer`
       leaves the player on: conversations.topic.work_offer.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `quest.none.to.work_offer.none`: the villager accepts. Subject `work_offer.none`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.quest.none/1   [65 chars]
    en  Nothing needs doing right now, %1$s. But I appreciate you asking.
    >>  ............................................
    pt  Nada precisando ser feito agora, %1$s. Mas agradeço por perguntar.
    >>  ............................................
  dialogue.conversations.quest.none/2   [68 chars]
    en  All's handled for the moment, %1$s. Come back and I might have work.
    >>  ............................................
    pt  Está tudo resolvido no momento, %1$s. Volte depois e talvez eu tenha serviço.
    >>  ............................................
  dialogue.conversations.quest.none/3   [54 chars]
    en  No, %1$s, I'm caught up. Kind of you to offer, though.
    >>  ............................................
    pt  Não, %1$s, estou em dia. Gentileza sua se oferecer, de todo modo.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `0`

- Fires when: weighted +100 when `conversations_quest_available` = {"scope": "this"}
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.work_offer` (this player only) permanently
- Does: `conversations_quest_open` = {"mode": "menu"}
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.quest.offer
WHO    VILLAGER — what the player reads after pressing "Anything you need doing?"
       spoken on: conversations.cat.profession, button `work_offer`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `quest.offer.terminal`: the villager accepts. Subject `quest.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.profession` / button `work_offer`** earlier in this file. Fill it in there, once.


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.work_offer` (this player only) permanently
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.quest.none
WHO    VILLAGER — what the player reads after pressing "Anything you need doing?"
       spoken on: conversations.cat.profession, button `work_offer`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `quest.none.terminal`: the villager accepts. Subject `quest.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.profession` / button `work_offer`** earlier in this file. Fill it in there, once.


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.profession.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.profession
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.profession.back   [15 chars]
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


## `conversations.cat.relationships`

**Reached from 3 route(s):** `conversations.family` / `back`; `conversations` / `relationships`; `conversations.us` / `back`


```text
POOL   dialogue key: dialogue.conversations.cat.relationships
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.relationships
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.relationships   [37 chars]
    en  That's close to my heart. What is it?
    >>  ............................................
    pt  Isso mexe comigo. O que foi?
    >>  ............................................
```


### Button `us` — "Can we talk about us?"

Shown only when MCA's own constraints hold: `"spouse,adult"`

```text
POOL   dialogue key: dialogue.conversations.cat.relationships.us
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.relationships
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.relationships.us   [21 chars]
    en  Can we talk about us?
    >>  ............................................
    pt  A gente pode falar sobre nós?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.us`
- …where the player's next choices will be: "Are you happy with us?" | "Remember when we met?" | "What do you want for our future?" | "Is anything weighing on you?" | "Let's talk about something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `family` — "How's the family?"

Shown only when MCA's own constraints hold: `"family,!spouse"`

```text
POOL   dialogue key: dialogue.conversations.cat.relationships.family
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.relationships
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.relationships.family   [17 chars]
    en  How's the family?
    >>  ............................................
    pt  Como vai a família?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.family`
- …where the player's next choices will be: "How are you holding up, little one?" | "Can I ask you something? Parent to child?" | "Tell me a family story." | "Let's talk about something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.relationships.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.relationships
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.relationships.back   [15 chars]
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


## `conversations.cat.village`

**Reached from 217 route(s):** `conversations.arc.neighbour.resume.followup` / `thank_you_for_telling`; `conversations.arc.neighbour.resume.followup` / `leave_it_with_you`; `conversations.arc.neighbour.resume.followup` / `leave`; `conversations.arc.neighbour.resume.respond` / `leave`; `conversations.arc.people.resume.followup` / `thank_you_for_telling`; `conversations.arc.people.resume.followup` / `leave_it_with_you`; `conversations.arc.people.resume.followup` / `leave`; `conversations.arc.people.resume.respond` / `leave`; `conversations.arc.rumors.resume.followup` / `thank_you_for_telling`; `conversations.arc.rumors.resume.followup` / `leave_it_with_you`; `conversations.arc.rumors.resume.followup` / `leave`; `conversations.arc.rumors.resume.respond` / `leave` …and 205 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.ask_anyway` — e.g. "Nobody worth naming. There's always somebody worth naming, so this is remarkable."
- `conversations.neighbour.ask_history` — e.g. "Longer than I've known most things. You learn a person slowly in a place this small."
- `conversations.neighbour.be_honest` — e.g. "...I'm not. I've been telling it as though I'd decided, and I haven't. You caught that."
- `conversations.neighbour.dispute.between` — e.g. "It is, and I'm glad somebody said so before offering to carry a message."
- `conversations.neighbour.dispute.leave` — e.g. "So it is."
- `conversations.neighbour.dispute.mend` — e.g. "...I would. I've not said that out loud before and I'd thank you not to repeat it."
- `conversations.neighbour.family.common` — e.g. "It is. Doesn't make it lighter, but it does stop me feeling singled out."
- `conversations.neighbour.family.hard` — e.g. "It is, and we've all got used to walking the long way round it."
- `conversations.neighbour.family.leave` — e.g. "Quite."
- `conversations.neighbour.followup_leave` — e.g. "It is. That's enough about anybody for one day."
- `conversations.neighbour.glad` — e.g. "It has been, at that. Nobody's fallen out with anybody. I'd forgotten it could be like this."
- `conversations.neighbour.leave` — e.g. "Good. There's little enough privacy in a place this size."
- `conversations.neighbour.let_it_lie` — e.g. "Let's. They'd do the same for me, most likely, and I'd want them to."
- `conversations.neighbour.more.leave` — e.g. "True enough, it is."
- …and 165 more pools


```text
POOL   dialogue key: dialogue.conversations.cat.village
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.village
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.village   [42 chars]
    en  The village, hm? What do you want to know?
    >>  ............................................
    pt  O vilarejo, é? O que você quer saber?
    >>  ............................................
```


### Button `village` — "What's it like living here?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.village` — accepted phrasings: "about the village"; "this town"; "tell me about the village"
  - the message must contain one of: `village`, `place`, `live`, `home`
  - scored words: `village`(1.5), `place`(0.8), `live`(0.8), `home`(0.8), `around`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.village.village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.village   [27 chars]
    en  What's it like living here?
    >>  ............................................
    pt  Como é morar aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.village.getting_bigger"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.village.getting_bigger", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 36000 ticks
- Then opens: `conversations.scene.village.getting_bigger.respond`
- …where the player's next choices will be: "Is bigger better?" | "It's a good place to arrive in." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.village.getting_bigger
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.scene.village.getting_bigger.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.getting_bigger.open`: the villager reports. Subject `village.growth`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.getting_bigger/1   [101 chars]
    en  It has got large enough that I do not know every face, and I am still deciding how I feel about that.
    >>  ............................................
    pt  Ficou grande o bastante para eu não conhecer todos os rostos, e ainda estou decidindo como me sinto sobre isso.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger/2   [100 chars]
    en  There are people here now who arrived after me. That happened at some point and nobody announced it.
    >>  ............................................
    pt  Tem gente aqui agora que chegou depois de mim. Isso aconteceu em algum momento e ninguém anunciou.
    >>  ............................................
  dialogue.conversations.scene.village.getting_bigger/3   [123 chars]
    en  Bigger means the well is busier and the winter is safer, and those two things arrive together whether you want both or not.
    >>  ............................................
    pt  Maior significa poço mais cheio e inverno mais seguro, e essas duas coisas chegam juntas, queira você as duas ou não.
    >>  ............................................
```


**Outcome 2 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.village.after_something_happened"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.village.after_something_happened", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 36000 ticks
- Then opens: `conversations.scene.village.after_something_happened.respond`
- …where the player's next choices will be: "Who needs a hand?" | "It's been a hard stretch here." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.village.after_something_happened
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.scene.village.after_something_happened.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.after_something_happened.open`: the villager reports. Subject `village.recent_event`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.after_something_happened/1   [104 chars]
    en  Quietly, and quietly is not the same as well. Ask again in a fortnight and you will get the real answer.
    >>  ............................................
    pt  Em silêncio, e em silêncio não é o mesmo que bem. Pergunte de novo em duas semanas e você tem a resposta real.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened/2   [102 chars]
    en  Everybody has been very decent to each other for about six days, which is roughly how long that lasts.
    >>  ............................................
    pt  Todo mundo tem sido muito decente uns com os outros por uns seis dias, que é mais ou menos quanto isso dura.
    >>  ............................................
  dialogue.conversations.scene.village.after_something_happened/3   [110 chars]
    en  The lane has closed up. That is what it does. It will open again and it will take exactly as long as it takes.
    >>  ............................................
    pt  A viela se fechou. É o que ela faz. Vai abrir de novo e vai levar exatamente o tempo que levar.
    >>  ............................................
```


**Outcome 3 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.village.still_placing_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.village.still_placing_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 36000 ticks
- Then opens: `conversations.scene.village.still_placing_it.respond`
- …where the player's next choices will be: "What has it changed, for you?" | "Anything that needs doing about it?" | "Villages change. That's all." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.village.still_placing_it
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.scene.village.still_placing_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.still_placing_it.open`: the villager discloses. Subject `village.change`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.still_placing_it/1   [72 chars]
    en  There has been %2$s and I have not worked out yet what I think about it.
    >>  ............................................
    pt  Houve %2$s e eu ainda não descobri o que penso disso.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it/2   [76 chars]
    en  We have had %2$s. I keep starting sentences about it and not finishing them.
    >>  ............................................
    pt  Tivemos %2$s. Fico começando frases sobre isso e não terminando.
    >>  ............................................
  dialogue.conversations.scene.village.still_placing_it/3   [77 chars]
    en  %2$s, and the village is behaving as though that is a small thing. It is not.
    >>  ............................................
    pt  %2$s, e a vila age como se fosse pouca coisa. Não é.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s and I have not been all right about it, if I am honest.
    >>  ............................................
    pt  Houve %2$s e eu não tenho ficado bem com isso, se quer saber a verdade.
    >>  ............................................
  anxious.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I keep thinking I should be further along with it than I am.
    >>  ............................................
    pt  Tivemos %2$s. Fico achando que já devia ter superado mais do que superei.
    >>  ............................................
  anxious.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone seems to have put it down already, and I have not managed to.
    >>  ............................................
    pt  %2$s, e todo mundo parece já ter deixado isso de lado, e eu não consegui.
    >>  ............................................
  athletic.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I am letting it sit before I decide anything about it.
    >>  ............................................
    pt  Houve %2$s. Estou deixando assentar antes de decidir qualquer coisa.
    >>  ............................................
  athletic.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. No hurry on working out what I think, so I have not.
    >>  ............................................
    pt  Tivemos %2$s. Não tem pressa para saber o que eu penso, então não sei.
    >>  ............................................
  athletic.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village went straight back to normal. I am taking a bit longer.
    >>  ............................................
    pt  %2$s. A vila voltou ao normal na hora. Eu estou levando um pouco mais.
    >>  ............................................
  confident.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have not decided what I think and I am not pretending otherwise.
    >>  ............................................
    pt  Houve %2$s. Não decidi o que penso e não vou fingir que decidi.
    >>  ............................................
  confident.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. Ask me for an opinion and you will get a shrug, which is honest at least.
    >>  ............................................
    pt  Tivemos %2$s. Me peça uma opinião e vai levar um dar de ombros, o que ao menos é honesto.
    >>  ............................................
  confident.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village is calling that a small thing. It is not a small thing.
    >>  ............................................
    pt  %2$s. A vila está chamando isso de pouca coisa. Não é pouca coisa.
    >>  ............................................
  crabby.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have not decided what I think and I am not pretending otherwise.
    >>  ............................................
    pt  Houve %2$s. Não decidi o que penso e não vou fingir que decidi.
    >>  ............................................
  crabby.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. Ask me for an opinion and you will get a shrug, which is honest at least.
    >>  ............................................
    pt  Tivemos %2$s. Me peça uma opinião e vai levar um dar de ombros, o que ao menos é honesto.
    >>  ............................................
  crabby.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village is calling that a small thing. It is not a small thing.
    >>  ............................................
    pt  %2$s. A vila está chamando isso de pouca coisa. Não é pouca coisa.
    >>  ............................................
  extroverted.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s, and I have wanted to say so to somebody who would sit still for it.
    >>  ............................................
    pt  Houve %2$s, e eu queria contar para alguém que ficasse quieto para ouvir.
    >>  ............................................
  extroverted.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. I keep starting to tell people and losing my nerve halfway.
    >>  ............................................
    pt  Tivemos %2$s. Fico começando a contar para as pessoas e perdendo a coragem no meio.
    >>  ............................................
  extroverted.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. Everyone has moved along already and I find I have not, quite.
    >>  ............................................
    pt  %2$s. Todo mundo já seguiu em frente e eu descobri que não segui, não de todo.
    >>  ............................................
  flirty.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s, and I have wanted to say so to somebody who would sit still for it.
    >>  ............................................
    pt  Houve %2$s, e eu queria contar para alguém que ficasse quieto para ouvir.
    >>  ............................................
  flirty.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. I keep starting to tell people and losing my nerve halfway.
    >>  ............................................
    pt  Tivemos %2$s. Fico começando a contar para as pessoas e perdendo a coragem no meio.
    >>  ............................................
  flirty.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. Everyone has moved along already and I find I have not, quite.
    >>  ............................................
    pt  %2$s. Todo mundo já seguiu em frente e eu descobri que não segui, não de todo.
    >>  ............................................
  friendly.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s, and I have wanted to say so to somebody who would sit still for it.
    >>  ............................................
    pt  Houve %2$s, e eu queria contar para alguém que ficasse quieto para ouvir.
    >>  ............................................
  friendly.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. I keep starting to tell people and losing my nerve halfway.
    >>  ............................................
    pt  Tivemos %2$s. Fico começando a contar para as pessoas e perdendo a coragem no meio.
    >>  ............................................
  friendly.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. Everyone has moved along already and I find I have not, quite.
    >>  ............................................
    pt  %2$s. Todo mundo já seguiu em frente e eu descobri que não segui, não de todo.
    >>  ............................................
  gloomy.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s and I have not been all right about it, if I am honest.
    >>  ............................................
    pt  Houve %2$s e eu não tenho ficado bem com isso, se quer saber a verdade.
    >>  ............................................
  gloomy.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I keep thinking I should be further along with it than I am.
    >>  ............................................
    pt  Tivemos %2$s. Fico achando que já devia ter superado mais do que superei.
    >>  ............................................
  gloomy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone seems to have put it down already, and I have not managed to.
    >>  ............................................
    pt  %2$s, e todo mundo parece já ter deixado isso de lado, e eu não consegui.
    >>  ............................................
  greedy.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have not decided what I think and I am not pretending otherwise.
    >>  ............................................
    pt  Houve %2$s. Não decidi o que penso e não vou fingir que decidi.
    >>  ............................................
  greedy.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. Ask me for an opinion and you will get a shrug, which is honest at least.
    >>  ............................................
    pt  Tivemos %2$s. Me peça uma opinião e vai levar um dar de ombros, o que ao menos é honesto.
    >>  ............................................
  greedy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village is calling that a small thing. It is not a small thing.
    >>  ............................................
    pt  %2$s. A vila está chamando isso de pouca coisa. Não é pouca coisa.
    >>  ............................................
  grumpy.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have not decided what I think and I am not pretending otherwise.
    >>  ............................................
    pt  Houve %2$s. Não decidi o que penso e não vou fingir que decidi.
    >>  ............................................
  grumpy.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s. Ask me for an opinion and you will get a shrug, which is honest at least.
    >>  ............................................
    pt  Tivemos %2$s. Me peça uma opinião e vai levar um dar de ombros, o que ao menos é honesto.
    >>  ............................................
  grumpy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village is calling that a small thing. It is not a small thing.
    >>  ............................................
    pt  %2$s. A vila está chamando isso de pouca coisa. Não é pouca coisa.
    >>  ............................................
  introverted.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have been turning it over. That is all I have so far.
    >>  ............................................
    pt  Houve %2$s. Fico remoendo. É tudo o que eu tenho até agora.
    >>  ............................................
  introverted.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I do not have the sentence for it yet.
    >>  ............................................
    pt  Tivemos %2$s. Ainda não tenho a frase para isso.
    >>  ............................................
  introverted.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. People keep asking me what I make of it. I keep not answering.
    >>  ............................................
    pt  %2$s. As pessoas ficam me perguntando o que eu acho. Eu fico não respondendo.
    >>  ............................................
  lazy.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I am letting it sit before I decide anything about it.
    >>  ............................................
    pt  Houve %2$s. Estou deixando assentar antes de decidir qualquer coisa.
    >>  ............................................
  lazy.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. No hurry on working out what I think, so I have not.
    >>  ............................................
    pt  Tivemos %2$s. Não tem pressa para saber o que eu penso, então não sei.
    >>  ............................................
  lazy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village went straight back to normal. I am taking a bit longer.
    >>  ............................................
    pt  %2$s. A vila voltou ao normal na hora. Eu estou levando um pouco mais.
    >>  ............................................
  odd.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have been turning it over. That is all I have so far.
    >>  ............................................
    pt  Houve %2$s. Fico remoendo. É tudo o que eu tenho até agora.
    >>  ............................................
  odd.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I do not have the sentence for it yet.
    >>  ............................................
    pt  Tivemos %2$s. Ainda não tenho a frase para isso.
    >>  ............................................
  odd.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. People keep asking me what I make of it. I keep not answering.
    >>  ............................................
    pt  %2$s. As pessoas ficam me perguntando o que eu acho. Eu fico não respondendo.
    >>  ............................................
  peaceful.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I am letting it sit before I decide anything about it.
    >>  ............................................
    pt  Houve %2$s. Estou deixando assentar antes de decidir qualquer coisa.
    >>  ............................................
  peaceful.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. No hurry on working out what I think, so I have not.
    >>  ............................................
    pt  Tivemos %2$s. Não tem pressa para saber o que eu penso, então não sei.
    >>  ............................................
  peaceful.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village went straight back to normal. I am taking a bit longer.
    >>  ............................................
    pt  %2$s. A vila voltou ao normal na hora. Eu estou levando um pouco mais.
    >>  ............................................
  peppy.dialogue.conversations.scene.village.still_placing_it/1
    en  So — %2$s! And I have absolutely no idea how I feel about it, which is new for me.
    >>  ............................................
    pt  Então — %2$s! E eu não faço a menor ideia de como me sinto, o que é novidade para mim.
    >>  ............................................
  peppy.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s and I keep opening my mouth about it and closing it again. Very unlike me.
    >>  ............................................
    pt  Tivemos %2$s e eu fico abrindo a boca sobre isso e fechando de novo. Nada a ver comigo.
    >>  ............................................
  peppy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone is being terribly casual, and I am not managing casual at all.
    >>  ............................................
    pt  %2$s, e todo mundo está tão de boa, e eu não estou conseguindo ficar de boa nem um pouco.
    >>  ............................................
  playful.dialogue.conversations.scene.village.still_placing_it/1
    en  So — %2$s! And I have absolutely no idea how I feel about it, which is new for me.
    >>  ............................................
    pt  Então — %2$s! E eu não faço a menor ideia de como me sinto, o que é novidade para mim.
    >>  ............................................
  playful.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s and I keep opening my mouth about it and closing it again. Very unlike me.
    >>  ............................................
    pt  Tivemos %2$s e eu fico abrindo a boca sobre isso e fechando de novo. Nada a ver comigo.
    >>  ............................................
  playful.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone is being terribly casual, and I am not managing casual at all.
    >>  ............................................
    pt  %2$s, e todo mundo está tão de boa, e eu não estou conseguindo ficar de boa nem um pouco.
    >>  ............................................
  relaxed.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I am letting it sit before I decide anything about it.
    >>  ............................................
    pt  Houve %2$s. Estou deixando assentar antes de decidir qualquer coisa.
    >>  ............................................
  relaxed.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. No hurry on working out what I think, so I have not.
    >>  ............................................
    pt  Tivemos %2$s. Não tem pressa para saber o que eu penso, então não sei.
    >>  ............................................
  relaxed.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. The village went straight back to normal. I am taking a bit longer.
    >>  ............................................
    pt  %2$s. A vila voltou ao normal na hora. Eu estou levando um pouco mais.
    >>  ............................................
  sensitive.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s and I have not been all right about it, if I am honest.
    >>  ............................................
    pt  Houve %2$s e eu não tenho ficado bem com isso, se quer saber a verdade.
    >>  ............................................
  sensitive.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I keep thinking I should be further along with it than I am.
    >>  ............................................
    pt  Tivemos %2$s. Fico achando que já devia ter superado mais do que superei.
    >>  ............................................
  sensitive.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone seems to have put it down already, and I have not managed to.
    >>  ............................................
    pt  %2$s, e todo mundo parece já ter deixado isso de lado, e eu não consegui.
    >>  ............................................
  shy.dialogue.conversations.scene.village.still_placing_it/1
    en  There has been %2$s. I have been turning it over. That is all I have so far.
    >>  ............................................
    pt  Houve %2$s. Fico remoendo. É tudo o que eu tenho até agora.
    >>  ............................................
  shy.dialogue.conversations.scene.village.still_placing_it/2
    en  We had %2$s. I do not have the sentence for it yet.
    >>  ............................................
    pt  Tivemos %2$s. Ainda não tenho a frase para isso.
    >>  ............................................
  shy.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s. People keep asking me what I make of it. I keep not answering.
    >>  ............................................
    pt  %2$s. As pessoas ficam me perguntando o que eu acho. Eu fico não respondendo.
    >>  ............................................
  upbeat.dialogue.conversations.scene.village.still_placing_it/1
    en  So — %2$s! And I have absolutely no idea how I feel about it, which is new for me.
    >>  ............................................
    pt  Então — %2$s! E eu não faço a menor ideia de como me sinto, o que é novidade para mim.
    >>  ............................................
  upbeat.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s and I keep opening my mouth about it and closing it again. Very unlike me.
    >>  ............................................
    pt  Tivemos %2$s e eu fico abrindo a boca sobre isso e fechando de novo. Nada a ver comigo.
    >>  ............................................
  upbeat.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone is being terribly casual, and I am not managing casual at all.
    >>  ............................................
    pt  %2$s, e todo mundo está tão de boa, e eu não estou conseguindo ficar de boa nem um pouco.
    >>  ............................................
  witty.dialogue.conversations.scene.village.still_placing_it/1
    en  So — %2$s! And I have absolutely no idea how I feel about it, which is new for me.
    >>  ............................................
    pt  Então — %2$s! E eu não faço a menor ideia de como me sinto, o que é novidade para mim.
    >>  ............................................
  witty.dialogue.conversations.scene.village.still_placing_it/2
    en  We have had %2$s and I keep opening my mouth about it and closing it again. Very unlike me.
    >>  ............................................
    pt  Tivemos %2$s e eu fico abrindo a boca sobre isso e fechando de novo. Nada a ver comigo.
    >>  ............................................
  witty.dialogue.conversations.scene.village.still_placing_it/3
    en  %2$s, and everyone is being terribly casual, and I am not managing casual at all.
    >>  ............................................
    pt  %2$s, e todo mundo está tão de boa, e eu não estou conseguindo ficar de boa nem um pouco.
    >>  ............................................
```

</details>


**Outcome 4 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.village.change_settled"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.village.change_settled", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 36000 ticks
- Then opens: `conversations.scene.village.change_settled.respond`
- …where the player's next choices will be: "I'm glad it sits easier now." | "What made the difference?" | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.village.change_settled
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.scene.village.change_settled.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.change_settled.open`: the villager reminisces. Subject `village.change`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:village` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.village.change_settled/1   [78 chars]
    en  %2$s, and the village closed over it the way water does. I have made my peace.
    >>  ............................................
    pt  %2$s, e a vila se fechou por cima como a água faz. Fiz as pazes com isso.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled/2   [90 chars]
    en  I have stopped starting sentences about %2$s. That is what settled looks like, apparently.
    >>  ............................................
    pt  Parei de começar frases sobre %2$s. É essa a cara de estar resolvido, pelo visto.
    >>  ............................................
  dialogue.conversations.scene.village.change_settled/3   [80 chars]
    en  We had %2$s and now we simply have had it. I did not expect that to be a relief.
    >>  ............................................
    pt  Tivemos %2$s e agora simplesmente já tivemos. Não esperava que isso fosse um alívio.
    >>  ............................................
```


**Outcome 5 of 12** — base weight `0`

- Fires when: weighted +200 when arc `village` is at stage 1..2
- Fires when: RULED OUT when arc `village` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 36000 ticks
- Then opens: `conversations.arc.village.resume.respond`
- …where the player's next choices will be: "When would suit?" | "The offer still stands." | "I can't, as it turns out." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.village.resume
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.arc.village.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.resume.opener`: the villager reports. Subject `village.help`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, practical_help
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.village.resume/1   [74 chars]
    en  That fault you said you'd put a hand to. It's still there, and so are you.
    >>  ............................................
    pt  Aquela falha que você disse que ia botar a mão. Ainda está lá, e você também.
    >>  ............................................
  dialogue.conversations.village.resume/2   [92 chars]
    en  You offered to help with the wall. I've told two people you offered, which was unwise of me.
    >>  ............................................
    pt  Você ofereceu ajuda com o muro. Contei a duas pessoas, o que foi imprudente.
    >>  ............................................
  dialogue.conversations.village.resume/3   [77 chars]
    en  The thing you said you'd look at — I've been half expecting you and half not.
    >>  ............................................
    pt  Aquilo que você disse que ia ver — venho meio esperando e meio não.
    >>  ............................................
```


**Outcome 6 of 12** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.village` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `again` budget `standard`
- Then opens: `conversations.topic.village.again.respond`
- …where the player's next choices will be: "Sorry — you've told me." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.village.again
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.again.to.village.again`: the villager accepts. Subject `village.repeat`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.again/1   [55 chars]
    en  Still the same village, %1$s. The fences haven't moved.
    >>  ............................................
    pt  Continua o mesmo vilarejo, %1$s. As cercas não saíram do lugar.
    >>  ............................................
  dialogue.conversations.village.again/2   [58 chars]
    en  The village hasn't moved since you asked, %1$s. I checked.
    >>  ............................................
    pt  O vilarejo não saiu do lugar desde que você perguntou, %1$s. Eu conferi.
    >>  ............................................
  dialogue.conversations.village.again/3   [71 chars]
    en  Same bell, same gossip, same crack in the well. Comforting, in its way.
    >>  ............................................
    pt  Mesmo sino, mesma fofoca, mesma rachadura no poço. Reconfortante, do seu jeito.
    >>  ............................................
```


**Outcome 7 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `toddler` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.topic.village.toddler.respond`
- …where the player's next choices will be: "It's a good village." | "What's your favourite bit of it?" | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.village.toddler
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.toddler.to.village.toddler`: the villager accepts. Subject `village.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.toddler/1   [54 chars]
    en  I like it here! My house is here and my toys are here.
    >>  ............................................
    pt  Eu gosto daqui! Minha casa é aqui e meus brinquedos são aqui.
    >>  ............................................
  dialogue.conversations.village.toddler/2   [50 chars]
    en  The village is big. I'm not allowed past the well.
    >>  ............................................
    pt  O vilarejo é grande. Não posso passar do poço.
    >>  ............................................
  dialogue.conversations.village.toddler/3   [45 chars]
    en  This is the best village 'cause I live in it.
    >>  ............................................
    pt  Esse é o melhor vilarejo porque eu moro nele.
    >>  ............................................
```


**Outcome 8 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `child` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.topic.village.young.respond`
- …where the player's next choices will be: "It's a good village, isn't it." | "What's the best bit?" | "It's a bit dull, though." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.village.child
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.child.to.village.young`: the villager accepts. Subject `village.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.child/1   [49 chars]
    en  It's good! I know all the shortcuts. ALL of them.
    >>  ............................................
    pt  É bom! Eu sei todos os atalhos. TODOS.
    >>  ............................................
  dialogue.conversations.village.child/2   [73 chars]
    en  Home's great — the baker waves at me and sometimes there are extra rolls.
    >>  ............................................
    pt  Aqui é ótimo — o padeiro acena pra mim e às vezes sobra pãozinho.
    >>  ............................................
```


**Outcome 9 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `teen` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.topic.village.young.respond`
- …where the player's next choices will be: "It's a good village, isn't it." | "What's the best bit?" | "It's a bit dull, though." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.village.teen
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.teen.to.village.young`: the villager accepts. Subject `village.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.teen/1   [53 chars]
    en  It's small. Everyone knows everything about everyone.
    >>  ............................................
    pt  É pequeno. Todo mundo sabe tudo de todo mundo.
    >>  ............................................
  dialogue.conversations.village.teen/2   [43 chars]
    en  It's home. Bit quiet. That's fine, I guess.
    >>  ............................................
    pt  É meu lar. Meio quieto. Tudo bem, eu acho.
    >>  ............................................
```


**Outcome 10 of 12** — base weight `0`

- Fires when: weighted +100 when `constraints` = "!has_village"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `none` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.topic.village.none.respond`
- …where the player's next choices will be: "That's a hard way to live." | "Where do you stay?" | "Your own fault, surely." | "I'll leave you be."

```text
POOL   dialogue key: dialogue.conversations.village.none
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.none.to.village.none`: the villager accepts. Subject `village.no_home`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.none/1   [54 chars]
    en  I don't really have a place. I go where the road goes.
    >>  ............................................
    pt  Não tenho um lugar de verdade. Vou aonde a estrada vai.
    >>  ............................................
  dialogue.conversations.village.none/2   [71 chars]
    en  No village claims me right now. It's quieter that way, mostly lonelier.
    >>  ............................................
    pt  Nenhum vilarejo me reivindica agora. É mais silencioso assim, e mais solitário também.
    >>  ............................................
  dialogue.conversations.village.none/3   [61 chars]
    en  Home is wherever my boots stop hurting. Haven't found it yet.
    >>  ............................................
    pt  Lar é onde minhas botas param de doer. Ainda não encontrei.
    >>  ............................................
```


**Outcome 11 of 12** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when `constraints` = "!has_village"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.village` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `village` branch `home` budget `standard`
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.topic.village.respond`
- …where the player's next choices will be: "It's a good place to live." | "What would you change about it?" | "It's a miserable little place." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.village.home
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.topic.village.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = village_name
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.home.to.village`: the villager accepts. Subject `village.home`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.village.home/1   [73 chars]
    en  %2$s? It's home. The bell's cracked and the gossip's fast, but it's home.
    >>  ............................................
    pt  %2$s? É meu lar. O sino está rachado e a fofoca corre solta, mas é meu lar.
    >>  ............................................
  dialogue.conversations.village.home/2   [81 chars]
    en  I know every fence post in %2$s. Some days that's comfort, some days it's a cage.
    >>  ............................................
    pt  Conheço cada mourão de cerca de %2$s. Tem dia que isso é conforto, tem dia que é gaiola.
    >>  ............................................
  dialogue.conversations.village.home/3   [77 chars]
    en  %2$s raised me, fed me, and talks about me behind my back. Family, basically.
    >>  ............................................
    pt  %2$s me criou, me alimentou e fala de mim pelas costas. Família, basicamente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.village.home/1
    en  %2$s, yes. I know where everything is here — that helps more than you'd think. New places undo me.
    >>  ............................................
    pt  %2$s, sim. Eu sei onde fica tudo aqui — isso ajuda mais do que parece. Lugares novos me desmontam.
    >>  ............................................
  anxious.dialogue.conversations.village.home/2
    en  I like %2$s because I know it. Every path, every face. There aren't many surprises left, and that's a comfort.
    >>  ............................................
    pt  Gosto de %2$s porque eu conheço. Cada caminho, cada rosto. Não sobraram muitas surpresas, e isso é um conforto.
    >>  ............................................
  athletic.dialogue.conversations.village.home/1
    en  %2$s has long walls and good hills — the finest training ground a body could ask for.
    >>  ............................................
    pt  %2$s tem muros compridos e boas ladeiras — o melhor campo de treino que um corpo poderia pedir.
    >>  ............................................
  athletic.dialogue.conversations.village.home/2
    en  Ran every street in %2$s twice this week. It's home, and it's my racecourse.
    >>  ............................................
    pt  Corri cada rua de %2$s duas vezes essa semana. É meu lar, e é a minha pista.
    >>  ............................................
  confident.dialogue.conversations.village.home/1
    en  %2$s is fortunate to have me. I like to think I raise the average considerably.
    >>  ............................................
    pt  %2$s tem sorte de me ter. Gosto de pensar que eu elevo bastante a média.
    >>  ............................................
  confident.dialogue.conversations.village.home/2
    en  %2$s knows me and I know %2$s. Ask anyone — they'll point you my way, smiling.
    >>  ............................................
    pt  %2$s me conhece e eu conheço %2$s. Pergunte a qualquer um — vão apontar pra mim, sorrindo.
    >>  ............................................
  crabby.dialogue.conversations.village.home/1
    en  %2$s. It's noisy, it's nosy, and I've no intention of leaving. Draw your own conclusions.
    >>  ............................................
    pt  %2$s. É barulhento, é intrometido, e eu não tenho a menor intenção de sair. Tire suas conclusões.
    >>  ............................................
  crabby.dialogue.conversations.village.home/2
    en  %2$s does. Been here long enough to be irritated by every stone in it. That's home, isn't it?
    >>  ............................................
    pt  %2$s serve. Estou aqui há tempo suficiente pra me irritar com cada pedra dele. Isso é lar, não é?
    >>  ............................................
  extroverted.dialogue.conversations.village.home/1
    en  %2$s! I know every single person here and most of their business. That's what makes it home.
    >>  ............................................
    pt  %2$s! Conheço cada pessoa daqui e quase todos os assuntos delas. É isso que faz ser lar.
    >>  ............................................
  extroverted.dialogue.conversations.village.home/2
    en  Love %2$s. Can't cross the square without three conversations. Takes me an hour to fetch water. Worth it.
    >>  ............................................
    pt  Amo %2$s. Não dá pra atravessar a praça sem três conversas. Levo uma hora pra buscar água. Vale a pena.
    >>  ............................................
  flirty.dialogue.conversations.village.home/1
    en  %2$s? Full of familiar faces — and I've charmed most of them at least once. It's home, and it adores me.
    >>  ............................................
    pt  %2$s? Cheio de rostos conhecidos — e eu já encantei quase todos pelo menos uma vez. É meu lar, e ele me adora.
    >>  ............................................
  flirty.dialogue.conversations.village.home/2
    en  I know all the quiet corners of %2$s, %1$s. I'd show you, but I do like to keep some mystery.
    >>  ............................................
    pt  Conheço todos os cantinhos silenciosos de %2$s, %1$s. Eu te mostraria, mas eu gosto de guardar certo mistério.
    >>  ............................................
  friendly.dialogue.conversations.village.home/1
    en  %2$s? Oh, I love it here. Everyone knows everyone, and that suits me right to the ground.
    >>  ............................................
    pt  %2$s? Ah, eu amo daqui. Todo mundo conhece todo mundo, e isso me cai como uma luva.
    >>  ............................................
  friendly.dialogue.conversations.village.home/2
    en  %2$s is one big family, %1$s. I know every name and most of the birthdays!
    >>  ............................................
    pt  %2$s é uma família grande, %1$s. Sei o nome de todo mundo e quase todos os aniversários!
    >>  ............................................
  gloomy.dialogue.conversations.village.home/1
    en  %2$s? It's where I am. The bell's cracked, the sky's grey, and nobody leaves. Home, I suppose.
    >>  ............................................
    pt  %2$s? É onde eu estou. O sino está rachado, o céu está cinza, e ninguém vai embora. Lar, eu suponho.
    >>  ............................................
  gloomy.dialogue.conversations.village.home/2
    en  I've watched every season pass over %2$s from the same window. They blur together after a while.
    >>  ............................................
    pt  Vi cada estação passar sobre %2$s da mesma janela. Depois de um tempo elas se borram.
    >>  ............................................
  greedy.dialogue.conversations.village.home/1
    en  %2$s? Decent market, reliable foot traffic, low rent on account of the smell by the tannery. Sound investment, home.
    >>  ............................................
    pt  %2$s? Feira decente, fluxo de gente confiável, aluguel baixo por causa do cheiro perto do curtume. Investimento sólido, esse lar.
    >>  ............................................
  greedy.dialogue.conversations.village.home/2
    en  I know every debt and favor owed in %2$s, %1$s. That's not gossip — that's bookkeeping.
    >>  ............................................
    pt  Conheço cada dívida e cada favor devido em %2$s, %1$s. Isso não é fofoca — é contabilidade.
    >>  ............................................
  grumpy.dialogue.conversations.village.home/1
    en  %2$s. It's noisy, it's nosy, and I've no intention of leaving. Draw your own conclusions.
    >>  ............................................
    pt  %2$s. É barulhento, é intrometido, e eu não tenho a menor intenção de sair. Tire suas conclusões.
    >>  ............................................
  grumpy.dialogue.conversations.village.home/2
    en  %2$s does. Been here long enough to be irritated by every stone in it. That's home, isn't it?
    >>  ............................................
    pt  %2$s serve. Estou aqui há tempo suficiente pra me irritar com cada pedra dele. Isso é lar, não é?
    >>  ............................................
  introverted.dialogue.conversations.village.home/1
    en  %2$s suits me. I know which paths stay empty and which hours the square is quiet. That's what makes a place home.
    >>  ............................................
    pt  %2$s me serve. Sei quais caminhos ficam vazios e em que horas a praça está quieta. É isso que faz um lugar ser lar.
    >>  ............................................
  introverted.dialogue.conversations.village.home/2
    en  I like %2$s at dawn, before it fills. For an hour it belongs entirely to me and the birds.
    >>  ............................................
    pt  Gosto de %2$s ao amanhecer, antes de encher. Por uma hora ele pertence inteiramente a mim e aos pássaros.
    >>  ............................................
  lazy.dialogue.conversations.village.home/1
    en  %2$s does me nicely. Nobody here is in much of a hurry, and that's rarer than you'd think.
    >>  ............................................
    pt  %2$s me serve bem. Ninguém aqui tem muita pressa, e isso é mais raro do que você imagina.
    >>  ............................................
  lazy.dialogue.conversations.village.home/2
    en  I like %2$s. Slow place, good shade, and the well's never far. What else is there?
    >>  ............................................
    pt  Gosto de %2$s. Lugar lento, boa sombra, e o poço nunca longe. O que mais tem?
    >>  ............................................
  odd.dialogue.conversations.village.home/1
    en  %2$s sits at exactly the right angle to the sunset, which is why the bread rises here and nowhere else. Home, obviously.
    >>  ............................................
    pt  %2$s fica exatamente no ângulo certo em relação ao pôr do sol, e é por isso que o pão cresce aqui e em nenhum outro lugar. Lar, obviamente.
    >>  ............................................
  odd.dialogue.conversations.village.home/2
    en  I know which stones in %2$s are listening and which are just resting. It's a good village. The well vouches for it.
    >>  ............................................
    pt  Eu sei quais pedras de %2$s estão escutando e quais só estão descansando. É um bom vilarejo. O poço garante.
    >>  ............................................
  peaceful.dialogue.conversations.village.home/1
    en  %2$s, yes. It's a gentle place, and gentle places are rarer than people realise.
    >>  ............................................
    pt  %2$s, sim. É um lugar gentil, e lugares gentis são mais raros do que as pessoas percebem.
    >>  ............................................
  peaceful.dialogue.conversations.village.home/2
    en  I've made my peace with %2$s and it with me. That's what home is, I think.
    >>  ............................................
    pt  Fiz as pazes com %2$s e ele comigo. É isso que lar significa, eu acho.
    >>  ............................................
  peppy.dialogue.conversations.village.home/1
    en  %2$s is the BEST village, %1$s! I've decided! Every fence, every squeaky gate — I love the whole squeaky lot of it!
    >>  ............................................
    pt  %2$s é o MELHOR vilarejo, %1$s! Eu decidi! Cada cerca, cada portão rangendo — eu amo esse monte rangente inteiro!
    >>  ............................................
  peppy.dialogue.conversations.village.home/2
    en  Oh, %2$s! I know everybody AND their chickens by name! It's home and it's PERFECT, cracked bell and all!
    >>  ............................................
    pt  Ah, %2$s! Eu conheço todo mundo E as galinhas de cada um pelo nome! É meu lar e é PERFEITO, sino rachado e tudo!
    >>  ............................................
  playful.dialogue.conversations.village.home/1
    en  %2$s! Best playground I've ever lived in. Every roof climbable, every neighbour teasable. Perfect.
    >>  ............................................
    pt  %2$s! Melhor parquinho em que eu já morei. Todo telhado escalável, todo vizinho provocável. Perfeito.
    >>  ............................................
  playful.dialogue.conversations.village.home/2
    en  I know every shortcut and hiding place in %2$s. Took years of dedicated misbehaviour.
    >>  ............................................
    pt  Conheço cada atalho e esconderijo de %2$s. Levou anos de má conduta dedicada.
    >>  ............................................
  relaxed.dialogue.conversations.village.home/1
    en  %2$s does me nicely. Nobody here is in much of a hurry, and that's rarer than you'd think.
    >>  ............................................
    pt  %2$s me serve bem. Ninguém aqui tem muita pressa, e isso é mais raro do que você imagina.
    >>  ............................................
  relaxed.dialogue.conversations.village.home/2
    en  I like %2$s. Slow place, good shade, and the well's never far. What else is there?
    >>  ............................................
    pt  Gosto de %2$s. Lugar lento, boa sombra, e o poço nunca longe. O que mais tem?
    >>  ............................................
  sensitive.dialogue.conversations.village.home/1
    en  %2$s holds a lot of quiet hearts, %1$s. I try to notice the ones that need noticing. It's home because of them.
    >>  ............................................
    pt  %2$s abriga muitos corações quietos, %1$s. Eu tento notar os que precisam ser notados. É meu lar por causa deles.
    >>  ............................................
  sensitive.dialogue.conversations.village.home/2
    en  I love %2$s the way you love people — for the cracks as much as anything. Every worn doorstep has a story.
    >>  ............................................
    pt  Amo %2$s do jeito que a gente ama pessoas — pelas rachaduras tanto quanto pelo resto. Cada soleira gasta tem uma história.
    >>  ............................................
  shy.dialogue.conversations.village.home/1
    en  %2$s suits me. I know which paths stay empty and which hours the square is quiet. That's what makes a place home.
    >>  ............................................
    pt  %2$s me serve. Sei quais caminhos ficam vazios e em que horas a praça está quieta. É isso que faz um lugar ser lar.
    >>  ............................................
  shy.dialogue.conversations.village.home/2
    en  I like %2$s at dawn, before it fills. For an hour it belongs entirely to me and the birds.
    >>  ............................................
    pt  Gosto de %2$s ao amanhecer, antes de encher. Por uma hora ele pertence inteiramente a mim e aos pássaros.
    >>  ............................................
  upbeat.dialogue.conversations.village.home/1
    en  %2$s? I'm fond of it. Good people, a decent well, and somebody always waving. That's a fine place to land.
    >>  ............................................
    pt  %2$s? Tenho carinho por ele. Gente boa, um poço decente, e sempre alguém acenando. É um belo lugar pra parar.
    >>  ............................................
  upbeat.dialogue.conversations.village.home/2
    en  I like %2$s more each year. It's not grand, but it's warm, and warm lasts longer than grand.
    >>  ............................................
    pt  Gosto mais de %2$s a cada ano. Não é grandioso, mas é acolhedor, e acolhedor dura mais que grandioso.
    >>  ............................................
  witty.dialogue.conversations.village.home/1
    en  %2$s? I'm fond of it. Good people, a decent well, and somebody always waving. That's a fine place to land.
    >>  ............................................
    pt  %2$s? Tenho carinho por ele. Gente boa, um poço decente, e sempre alguém acenando. É um belo lugar pra parar.
    >>  ............................................
  witty.dialogue.conversations.village.home/2
    en  I like %2$s more each year. It's not grand, but it's warm, and warm lasts longer than grand.
    >>  ............................................
    pt  Gosto mais de %2$s a cada ano. Não é grandioso, mas é acolhedor, e acolhedor dura mais que grandioso.
    >>  ............................................
```

</details>


**Outcome 12 of 12** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.cooldown.village` (this player only) for 48000 ticks
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.village.home
WHO    VILLAGER — what the player reads after pressing "What's it like living here?"
       spoken on: conversations.cat.village, button `village`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = village_name
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `village.home.terminal`: the villager accepts. Subject `village.home`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.village` / button `village`** earlier in this file. Fill it in there, once.


### Button `people` — "What do you make of your neighbors?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `village.people` — accepted phrasings: "the other villagers"; "who lives here"; "tell me about the people"; "how is everyone"; "how is everyone doing"
  - the message must contain one of: `people`, `everyone`, `others`
  - scored words: `people`(1.4), `everyone`(1.0), `others`(0.8), `someone`(0.6), `live`(0.8)

```text
POOL   dialogue key: dialogue.conversations.cat.village.people
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.village.people   [35 chars]
    en  What do you make of your neighbors?
    >>  ............................................
    pt  O que você acha dos seus vizinhos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 13** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.people.changed_my_mind"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.people.changed_my_mind", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.scene.people.changed_my_mind.respond`
- …where the player's next choices will be: "What changed your mind?" | "Changing your mind takes something." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.people.changed_my_mind
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.scene.people.changed_my_mind.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.changed_my_mind.open`: the villager reports. Subject `people.reappraisal`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.changed_my_mind/1   [98 chars]
    en  There is somebody here I disliked for two years on the strength of one afternoon, and I was wrong.
    >>  ............................................
    pt  Tem uma pessoa aqui de quem eu não gostei por dois anos com base numa tarde, e eu estava errada.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind/2   [112 chars]
    en  I had a person filed as difficult and it turned out they were tired, and tired for a reason, and for four years.
    >>  ............................................
    pt  Eu tinha uma pessoa catalogada como difícil e acontece que estava cansada, e cansada por um motivo, e havia quatro anos.
    >>  ............................................
  dialogue.conversations.scene.people.changed_my_mind/3   [102 chars]
    en  The change happened in one conversation. Two years of a settled opinion, undone in about nine minutes.
    >>  ............................................
    pt  A virada aconteceu numa conversa. Dois anos de opinião firmada, desfeitos em uns nove minutos.
    >>  ............................................
```


**Outcome 2 of 13** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.people.the_newcomer"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.people.the_newcomer", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.scene.people.the_newcomer.respond`
- …where the player's next choices will be: "Then ask them to supper." | "What are they like?" | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.people.the_newcomer
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.scene.people.the_newcomer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.the_newcomer.open`: the villager reports. Subject `people.new_arrival`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:people` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.people.the_newcomer/1   [120 chars]
    en  Somebody arrived in the spring and the lane has been very polite to them for four months, which is its own kind of cold.
    >>  ............................................
    pt  Alguém chegou na primavera e a viela tem sido muito educada com essa pessoa por quatro meses, o que é um tipo de frieza.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer/2   [99 chars]
    en  The new household has been asked where they are from eleven times and asked to supper nought times.
    >>  ............................................
    pt  A casa nova foi perguntada de onde vem onze vezes e convidada para jantar nenhuma.
    >>  ............................................
  dialogue.conversations.scene.people.the_newcomer/3   [120 chars]
    en  They are doing everything right and nobody has let them in yet, and I have started to think that is a thing I could fix.
    >>  ............................................
    pt  Estão fazendo tudo certo e ninguém deixou entrar ainda, e comecei a achar que isso é uma coisa que eu podia resolver.
    >>  ............................................
```


**Outcome 3 of 13** — base weight `0`

- Fires when: weighted +200 when arc `people` is at stage 1..2
- Fires when: RULED OUT when arc `people` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.arc.people.resume.respond`
- …where the player's next choices will be: "I'm glad you went." | "There's no deadline on it." | "What did you say to them?" | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.people.resume
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.arc.people.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.resume.opener`: the villager reports. Subject `people.repair`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, restraint
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.people.resume/1   [72 chars]
    en  I spoke to them. You'll want to know it was awkward and it was worth it.
    >>  ............................................
    pt  Falei com eles. Você vai querer saber que foi constrangedor e que valeu a pena.
    >>  ............................................
  dialogue.conversations.people.resume/2   [73 chars]
    en  I've not spoken to them. You said I should and I've been finding reasons.
    >>  ............................................
    pt  Não falei com eles. Você disse que eu devia e venho arranjando desculpas.
    >>  ............................................
  dialogue.conversations.people.resume/3   [86 chars]
    en  We spoke. It didn't mend anything and it stopped it getting worse, which is something.
    >>  ............................................
    pt  Nós falamos. Não consertou nada e impediu de piorar, o que já é algo.
    >>  ............................................
```


**Outcome 4 of 13** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.people` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `again` budget `standard`
- Then opens: `conversations.topic.people.again.respond`
- …where the player's next choices will be: "Sorry — asked already." | "Tell me anyway." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.people.again
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.again.to.people.again`: the villager accepts. Subject `people.repeat`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.people.again/1   [99 chars]
    en  I said my piece about the neighbors. Any more and it becomes gossip, which is a different question.
    >>  ............................................
    pt  Já falei o que tinha sobre os vizinhos. Mais que isso vira fofoca, que é outra pergunta.
    >>  ............................................
  dialogue.conversations.people.again/2   [78 chars]
    en  Careful, %1$s — twice in a week and people start thinking YOU'RE the nosy one.
    >>  ............................................
    pt  Cuidado, %1$s — duas vezes na semana e as pessoas começam a achar que o fofoqueiro é VOCÊ.
    >>  ............................................
  dialogue.conversations.people.again/3   [61 chars]
    en  Nothing's changed since you asked. Same saints, same menaces.
    >>  ............................................
    pt  Nada mudou desde que você perguntou. Mesmos santos, mesmas pragas.
    >>  ............................................
```


**Outcome 5 of 13** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `child` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.young.respond`
- …where the player's next choices will be: "Who do you get on with?" | "Is anyone unkind to you?" | "You'll grow out of caring." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.people.child
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.child.to.people.young`: the villager accepts. Subject `people.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.people.child/1   [55 chars]
    en  The miller's kid is my best friend except on race days.
    >>  ............................................
    pt  O filho do moleiro é meu melhor amigo, menos em dia de corrida.
    >>  ............................................
  dialogue.conversations.people.child/2   [61 chars]
    en  Everyone's nice! Except when they're telling me to slow down.
    >>  ............................................
    pt  Todo mundo é legal! Menos quando mandam eu ir mais devagar.
    >>  ............................................
```


**Outcome 6 of 13** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.people` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `people` branch `teen` budget `standard`
- Does: remembers `mcaconversations.cooldown.people` (this player only) for 36000 ticks
- Then opens: `conversations.topic.people.young.respond`
- …where the player's next choices will be: "Who do you get on with?" | "Is anyone unkind to you?" | "You'll grow out of caring." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.people.teen
WHO    VILLAGER — what the player reads after pressing "What do you make of your neighbors?"
       spoken on: conversations.cat.village, button `people`
       leaves the player on: conversations.topic.people.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `people.teen.to.people.young`: the villager accepts. Subject `people.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.people.teen/1   [61 chars]
    en  They're fine. Everybody's got opinions about my hair, though.
    >>  ............................................
    pt  São de boa. Mas todo mundo tem opinião sobre o meu cabelo.
    >>  ............................................
  dialogue.conversations.people.teen/2   [28 chars]
    en  Nice enough. Nosy, but nice.
    >>  ............................................
    pt  Bem legais. Enxeridos, mas legais.
    >>  ............................................
```

