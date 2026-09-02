# Work talk with a netherian

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.netherian.followup`](#conversations-scene-work-netherian-followup)
- [`conversations.scene.work.netherian.spoiled_load.blocked.respond`](#conversations-scene-work-netherian-spoiled-load-blocked-respond)
- [`conversations.scene.work.netherian.spoiled_load.succeeded.respond`](#conversations-scene-work-netherian-spoiled-load-succeeded-respond)
- [`conversations.scene.work.netherian.the_apprentice_trader.active.respond`](#conversations-scene-work-netherian-the-apprentice-trader-active-respond)
- [`conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond`](#conversations-scene-work-netherian-the-apprentice-trader-succeeded-respond)
- [`conversations.scene.work.netherian.the_burn.succeeded.respond`](#conversations-scene-work-netherian-the-burn-succeeded-respond)
- [`conversations.topic.work.netherian.craft.respond`](#conversations-topic-work-netherian-craft-respond)
- [`conversations.topic.work.netherian.followup`](#conversations-topic-work-netherian-followup)
- [`conversations.topic.work.netherian.future.respond`](#conversations-topic-work-netherian-future-respond)
- [`conversations.topic.work.netherian.respond`](#conversations-topic-work-netherian-respond)
- [`conversations.topic.work.netherian.risk.respond`](#conversations-topic-work-netherian-risk-respond)
- [`conversations.topic.work.netherian.task.respond`](#conversations-topic-work-netherian-task-respond)
- [`conversations.topic.work.netherian.village.respond`](#conversations-topic-work-netherian-village-respond)

---

## `conversations.scene.work.netherian.followup`

**Reached from 9 route(s):** `conversations.scene.work.netherian.spoiled_load.blocked.respond` / `ask_about_the_crossing`; `conversations.scene.work.netherian.spoiled_load.blocked.respond` / `offer_quartz`; `conversations.scene.work.netherian.spoiled_load.blocked.respond` / `advise_a_second_carrier`; `conversations.scene.work.netherian.spoiled_load.succeeded.respond` / `ask_about_the_partner`; `conversations.scene.work.netherian.the_apprentice_trader.active.respond` / `ask_what_worries_her`; `conversations.scene.work.netherian.the_apprentice_trader.active.respond` / `advise_a_short_trip`; `conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond` / `note_the_two_pages`; `conversations.scene.work.netherian.the_burn.succeeded.respond` / `ask_if_it_stopped_her`; `conversations.scene.work.netherian.the_burn.succeeded.respond` / `note_the_change`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.spoiled_load.blocked.accepted` — e.g. "Then the season pays for itself and I do not have to make the crossing again before winter."
- `conversations.scene.work.netherian.spoiled_load.blocked.considered` — e.g. "Two carriers is half the profit and a quarter of the risk. Written down like that it has never once been a difficult sum."
- `conversations.scene.work.netherian.spoiled_load.blocked.explained` — e.g. "Water, mostly. You carry it, it weighs more than the goods, and you still run out on the sixth day if you have judged badly."
- `conversations.scene.work.netherian.spoiled_load.succeeded.answered` — e.g. "Easier and much more irritating. She talks in the mornings, which I had forgotten was a thing people did."
- `conversations.scene.work.netherian.the_apprentice_trader.active.accepted` — e.g. "Four days to the coast and back. Long enough to be dull and short enough that quitting costs nobody anything."
- `conversations.scene.work.netherian.the_apprentice_trader.active.explained` — e.g. "That %2$s will be my responsibility on day seven, when I am at my worst and least able to be anybody's responsibility."
- `conversations.scene.work.netherian.the_apprentice_trader.succeeded.acknowledged` — e.g. "Two pages, and about half of it is things somebody told me and I ignored until it cost me something."
- `conversations.scene.work.netherian.the_burn.succeeded.acknowledged` — e.g. "Night walking. It halves the water and doubles the sleep, and it took a burn to make me try a thing every old trader had told me."
- `conversations.scene.work.netherian.the_burn.succeeded.answered` — e.g. "Every day for four months. Then I could hold a pack again and the question simply stopped being interesting."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.followup   [25 chars]
    en  Was there something more?
    >>  ............................................
    pt  Tinha mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of the heat?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.netherian.*` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.followup.ask_more` — accepted phrasings: "whats the hardest part of the heat"; "what is the hardest part of the heat"; "hardest thing about working in that heat"
  - the message must contain one of: `hardest`, `heat`
  - scored words: `hardest`(1.8), `heat`(1.8), `whats`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.followup.ask_more   [36 chars]
    en  What's the hardest part of the heat?
    >>  ............................................
    pt  Qual é a parte mais difícil do calor?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of the heat?"
       spoken on: conversations.scene.work.netherian.followup, button `ask_more`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.hard`: the villager explains. Subject `work.netherian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.netherian.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.netherian.hard/1   [87 chars]
    en  Never build a portal where you can't see both sides of it. That one's got three people.
    >>  ............................................
    pt  Nunca construir um portal onde você não veja os dois lados. Essa já pegou três pessoas.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.hard/2   [85 chars]
    en  The gold. You wear it or you don't trade, %1$s, and forgetting is not a conversation.
    >>  ............................................
    pt  O ouro. Você usa ou não negocia, %1$s, e esquecer não vira conversa.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the heat."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.netherian.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.followup.leave   [27 chars]
    en  I'll leave you to the heat.
    >>  ............................................
    pt  Vou deixar você com o calor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the heat."
       spoken on: conversations.scene.work.netherian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.netherian.leave/1   [50 chars]
    en  There's a great deal of getting ready. Off you go.
    >>  ............................................
    pt  Tem muita preparação. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.leave/2   [45 chars]
    en  Aye. Don't touch anything in the crate, %1$s.
    >>  ............................................
    pt  É. Não toque em nada no caixote, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.netherian.spoiled_load.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.spoiled_load.blocked` — e.g. "%2$s came back in a state because of %3$s, and a spoiled load is nine days of walking for nothing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond   [9 chars]
    en  The load.
    >>  ............................................
    pt  A carga.
    >>  ............................................
```


### Button `ask_about_the_crossing` — "What does the journey take out of you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.spoiled_load.blocked` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.spoiled_load.blocked.ask_about_the_crossing` — accepted phrasings: "what does the journey take out of you"; "what does the journey take out of you"; "how much does the trip cost you"
  - the message must contain one of: `journey`, `trip`
  - scored words: `journey`(1.8), `trip`(1.8), `does`(0.8), `take`(0.8), `out`(0.8), `much`(0.8), `cost`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.ask_about_the_crossing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.ask_about_the_crossing   [38 chars]
    en  What does the journey take out of you?
    >>  ............................................
    pt  O que a viagem tira de você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.what_travels`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.netherian.spoiled_load"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What does the journey take out of you?"
       spoken on: conversations.scene.work.netherian.spoiled_load.blocked.respond, button `ask_about_the_crossing`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.blocked.explained`: the villager explains. Subject `work.netherian.what_travels`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.explained/1   [124 chars]
    en  Water, mostly. You carry it, it weighs more than the goods, and you still run out on the sixth day if you have judged badly.
    >>  ............................................
    pt  Água, principalmente. Você carrega, ela pesa mais que a mercadoria, e mesmo assim acaba no sexto dia se você calculou mal.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.explained/2   [137 chars]
    en  Sleep. You cannot sleep properly in that heat, so you walk nine days on four nights, and the mistakes all happen on days seven and eight.
    >>  ............................................
    pt  Sono. Não dá para dormir direito naquele calor, então você caminha nove dias com quatro noites, e os erros acontecem todos no sétimo e no oitavo.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.explained/3   [130 chars]
    en  People want me to say it is frightening. It is hot and it is dull and it is very heavy, and dull is what actually breaks a trader.
    >>  ............................................
    pt  As pessoas querem que eu diga que é assustador. É quente, é monótono e é muito pesado, e é a monotonia que de fato quebra um comerciante.
    >>  ............................................
```


### Button `offer_quartz` — "I'll bring you quartz to make up the load."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.netherian.spoiled_load.blocked` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.spoiled_load.blocked.offer_quartz` — accepted phrasings: "ill bring you quartz to make up the load"; "i can bring you quartz"; "let me fetch quartz for that"
  - the message must contain one of: `quartz`
  - scored words: `quartz`(1.8), `ill`(0.8), `bring`(0.8), `make`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.offer_quartz
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.offer_quartz   [42 chars]
    en  I'll bring you quartz to make up the load.
    >>  ............................................
    pt  Vou trazer quartzo para completar a carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.netherian.load.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.netherian.what_travels`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.spoiled_load", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.netherian.spoiled_load", "obligation": "commitment:work.netherian.bring_quartz"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.netherian.bring_quartz"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you quartz to make up the load."
       spoken on: conversations.scene.work.netherian.spoiled_load.blocked.respond, button `offer_quartz`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.blocked.accepted`: the villager accepts. Subject `work.netherian.what_travels`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.accepted/1   [91 chars]
    en  Then the season pays for itself and I do not have to make the crossing again before winter.
    >>  ............................................
    pt  Então a estação se paga e eu não preciso fazer a travessia de novo antes do inverno.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.accepted/2   [119 chars]
    en  You are handing me nine days of my life back. I am going to spend most of them asleep and I am telling you so honestly.
    >>  ............................................
    pt  Você está me devolvendo nove dias de vida. Vou passar quase todos dormindo e digo isso com toda a franqueza.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.accepted/3   [115 chars]
    en  Yes. And I will trade you at cost for as long as I am in this village, and I will not be argued out of that either.
    >>  ............................................
    pt  Sim. E vou te vender a preço de custo enquanto eu estiver nesta vila, e também não aceito discussão sobre isso.
    >>  ............................................
```


### Button `advise_a_second_carrier` — "Split the load across two carriers."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.spoiled_load.blocked` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.spoiled_load.blocked.advise_a_second_carrier` — accepted phrasings: "split the load across two carriers"; "split the load across two carriers"; "carry it in two loads instead of one"
  - the message must contain one of: `split`, `carriers`, `loads`
  - scored words: `split`(1.8), `carriers`(1.8), `loads`(1.8), `across`(0.8), `two`(0.8), `carry`(0.8), `instead`(0.8), `one`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.advise_a_second_carrier
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.advise_a_second_carrier   [35 chars]
    en  Split the load across two carriers.
    >>  ............................................
    pt  Divida a carga entre dois transportadores.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.netherian.what_travels`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.netherian.spoiled_load"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.considered
WHO    VILLAGER — what the player reads after pressing "Split the load across two carriers."
       spoken on: conversations.scene.work.netherian.spoiled_load.blocked.respond, button `advise_a_second_carrier`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.blocked.considered`: the villager accepts. Subject `work.netherian.what_travels`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.considered/1   [121 chars]
    en  Two carriers is half the profit and a quarter of the risk. Written down like that it has never once been a difficult sum.
    >>  ............................................
    pt  Dois transportadores são metade do lucro e um quarto do risco. Escrito assim, nunca foi uma conta difícil.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.considered/2   [123 chars]
    en  I have carried alone for eleven years because I did not want to trust anybody with the crossing. That is pride, not policy.
    >>  ............................................
    pt  Carreguei sozinha por onze anos porque não queria confiar a travessia a ninguém. Isso é orgulho, não política.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.considered/3   [114 chars]
    en  Yes. There is a woman in the next valley who has been asking to come along for two years and I have said no twice.
    >>  ............................................
    pt  Sim. Tem uma mulher no vale vizinho que pede para ir junto há dois anos e eu disse não duas vezes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your load."

*stance family `exit` · tone `plain` · answers the beat(s) `work.netherian.spoiled_load.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.blocked.respond.leave   [35 chars]
    en  I'll let you get back to your load.
    >>  ............................................
    pt  Vou deixar você voltar à sua carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your load."
       spoken on: conversations.scene.work.netherian.spoiled_load.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.netherian.spoiled_load.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.spoiled_load.succeeded` — e.g. "%2$s went out whole and at the right price. Two of us carried and neither of us ran short of water."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.spoiled_load.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond   [21 chars]
    en  The load, in the end.
    >>  ............................................
    pt  A carga, no fim.
    >>  ............................................
```


### Button `ask_about_the_partner` — "How was it with somebody along?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.spoiled_load.succeeded` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.spoiled_load.succeeded.ask_about_the_partner` — accepted phrasings: "how was it with somebody along"; "how was it with somebody along"; "what was it like having company"
  - the message must contain one of: `somebody`, `company`
  - scored words: `somebody`(1.8), `company`(1.8), `along`(0.8), `like`(0.8), `having`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond.ask_about_the_partner
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond.ask_about_the_partner   [31 chars]
    en  How was it with somebody along?
    >>  ............................................
    pt  Como foi com alguém junto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.what_travels`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.netherian.spoiled_load"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How was it with somebody along?"
       spoken on: conversations.scene.work.netherian.spoiled_load.succeeded.respond, button `ask_about_the_partner`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.spoiled_load.succeeded.answered`: the villager explains. Subject `work.netherian.what_travels`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.answered/1   [105 chars]
    en  Easier and much more irritating. She talks in the mornings, which I had forgotten was a thing people did.
    >>  ............................................
    pt  Mais fácil e muito mais irritante. Ela fala de manhã, e eu tinha esquecido que as pessoas fazem isso.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.answered/2   [126 chars]
    en  She spotted a bad skin on the second day that I would have found on the sixth. That is the whole argument for a second person.
    >>  ............................................
    pt  Ela viu um odre ruim no segundo dia que eu teria descoberto no sexto. É esse o argumento inteiro a favor de uma segunda pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.answered/3   [125 chars]
    en  I slept. Actually slept, because somebody else was awake. Eleven years and I had forgotten what a full night on the road was.
    >>  ............................................
    pt  Eu dormi. Dormi de verdade, porque tinha outra pessoa acordada. Onze anos e eu tinha esquecido o que era uma noite inteira na estrada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your load."

*stance family `exit` · tone `plain` · answers the beat(s) `work.netherian.spoiled_load.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.spoiled_load.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.spoiled_load.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to your load.
    >>  ............................................
    pt  Vou deixar você voltar à sua carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your load."
       spoken on: conversations.scene.work.netherian.spoiled_load.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.netherian.the_apprentice_trader.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.the_apprentice_trader.active` — e.g. "%2$s wants to come on the crossing and has asked three times, and I have run out of polite ways to delay."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.the_apprentice_trader.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond   [26 chars]
    en  The one who wants to come.
    >>  ............................................
    pt  Quem quer ir junto.
    >>  ............................................
```


### Button `ask_what_worries_her` — "What are you worried about?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.the_apprentice_trader.active` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.the_apprentice_trader.active.ask_what_worries_her` — accepted phrasings: "what are you worried about"; "what are you worried about"; "what is the worry exactly"
  - the message must contain one of: `worried`, `worry`
  - scored words: `worried`(1.8), `worry`(1.8), `exactly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.ask_what_worries_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_apprentice_trader.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.ask_what_worries_her   [27 chars]
    en  What are you worried about?
    >>  ............................................
    pt  Com o que você se preocupa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.the_crossing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.netherian.the_apprentice_trader"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.explained
WHO    VILLAGER — what the player reads after pressing "What are you worried about?"
       spoken on: conversations.scene.work.netherian.the_apprentice_trader.active.respond, button `ask_what_worries_her`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_apprentice_trader.active.explained`: the villager explains. Subject `work.netherian.the_crossing`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.explained/1   [118 chars]
    en  That %2$s will be my responsibility on day seven, when I am at my worst and least able to be anybody's responsibility.
    >>  ............................................
    pt  Que %2$s vire responsabilidade minha no sétimo dia, quando eu estou no meu pior e menos capaz de ser responsável por alguém.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.explained/2   [122 chars]
    en  Not the heat. The dullness. People who are excited about the crossing are exactly the people who find day five unbearable.
    >>  ............................................
    pt  Não o calor. A monotonia. Quem se empolga com a travessia é exatamente quem acha o quinto dia insuportável.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.explained/3   [126 chars]
    en  That I will get it wrong the way somebody got it wrong with me at twenty, and I remember exactly how that felt and who it was.
    >>  ............................................
    pt  Que eu erre do jeito que alguém errou comigo aos vinte, e eu lembro exatamente como aquilo foi e quem era.
    >>  ............................................
```


### Button `advise_a_short_trip` — "Take them on a short run first."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.the_apprentice_trader.active` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.the_apprentice_trader.active.advise_a_short_trip` — accepted phrasings: "take them on a short run first"; "take them on a short run first"; "try a shorter journey before the crossing"
  - the message must contain one of: `short`, `shorter`, `run`
  - scored words: `short`(1.8), `shorter`(1.8), `run`(1.8), `take`(0.8), `first`(0.8), `try`(0.8), `journey`(0.8), `before`(0.8), `crossing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.advise_a_short_trip
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_apprentice_trader.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.advise_a_short_trip   [31 chars]
    en  Take them on a short run first.
    >>  ............................................
    pt  Leve numa viagem curta primeiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.netherian.the_crossing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.netherian.the_apprentice_trader"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.accepted
WHO    VILLAGER — what the player reads after pressing "Take them on a short run first."
       spoken on: conversations.scene.work.netherian.the_apprentice_trader.active.respond, button `advise_a_short_trip`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_apprentice_trader.active.accepted`: the villager accepts. Subject `work.netherian.the_crossing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.accepted/1   [109 chars]
    en  Four days to the coast and back. Long enough to be dull and short enough that quitting costs nobody anything.
    >>  ............................................
    pt  Quatro dias até a costa e volta. Longo o bastante para ser monótono e curto o bastante para desistir não custar nada a ninguém.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.accepted/2   [110 chars]
    en  That is obvious and I had not thought of it, because I have been treating this as a yes or a no for two years.
    >>  ............................................
    pt  Isso é óbvio e eu não tinha pensado, porque venho tratando a coisa como um sim ou um não há dois anos.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.accepted/3   [108 chars]
    en  Yes. And if they hate it, they will find that out at four days from home instead of nine days from anywhere.
    >>  ............................................
    pt  Sim. E se odiarem, vão descobrir a quatro dias de casa em vez de a nove dias de qualquer lugar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your load."

*stance family `exit` · tone `plain` · answers the beat(s) `work.netherian.the_apprentice_trader.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_apprentice_trader.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.active.respond.leave   [35 chars]
    en  I'll let you get back to your load.
    >>  ............................................
    pt  Vou deixar você voltar à sua carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your load."
       spoken on: conversations.scene.work.netherian.the_apprentice_trader.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.the_apprentice_trader.succeeded` — e.g. "%2$s did the short run and asked for the long one on the walk home, which is the answer I was hoping for."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond   [20 chars]
    en  Your second carrier.
    >>  ............................................
    pt  Sua segunda carregadora.
    >>  ............................................
```


### Button `note_the_two_pages` — "Two pages is eleven years of learning."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.netherian.the_apprentice_trader.succeeded` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.the_apprentice_trader.succeeded.note_the_two_pages` — accepted phrasings: "two pages is eleven years of learning"; "two pages is eleven years of learning"; "that is your whole experience written down"
  - the message must contain one of: `pages`, `experience`, `learning`
  - scored words: `pages`(1.8), `experience`(1.8), `learning`(1.8), `two`(0.8), `eleven`(0.8), `years`(0.8), `whole`(0.8), `written`(0.8), `down`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond.note_the_two_pages
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond.note_the_two_pages   [38 chars]
    en  Two pages is eleven years of learning.
    >>  ............................................
    pt  Duas páginas são onze anos de aprendizado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.netherian.the_crossing`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.netherian.the_apprentice_trader"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two pages is eleven years of learning."
       spoken on: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond, button `note_the_two_pages`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_apprentice_trader.succeeded.acknowledged`: the villager accepts. Subject `work.netherian.the_crossing`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.acknowledged/1   [100 chars]
    en  Two pages, and about half of it is things somebody told me and I ignored until it cost me something.
    >>  ............................................
    pt  Duas páginas, e quase metade são coisas que alguém me disse e eu ignorei até me custarem alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.acknowledged/2   [116 chars]
    en  Thank you. I had never written any of it down. If I had gone under on a crossing, all of it would have gone with me.
    >>  ............................................
    pt  Obrigada. Eu nunca tinha escrito nada disso. Se eu tivesse ficado numa travessia, tudo teria ido junto.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.acknowledged/3   [138 chars]
    en  It was the strangest night's work I have done. Everything I know about a nine-day walk fitted on two sides of a page, and I expected more.
    >>  ............................................
    pt  Foi a noite de trabalho mais estranha que eu já tive. Tudo o que eu sei sobre uma caminhada de nove dias coube em duas páginas, e eu esperava mais.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your load."

*stance family `exit` · tone `plain` · answers the beat(s) `work.netherian.the_apprentice_trader.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to your load.
    >>  ............................................
    pt  Vou deixar você voltar à sua carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your load."
       spoken on: conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.netherian.the_burn.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.netherian.the_burn.succeeded` — e.g. "%2$s. Seven years ago, on the way out rather than the way back, which is the stupid part."


```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.netherian.the_burn.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond   [10 chars]
    en  Your hand.
    >>  ............................................
    pt  Sua mão.
    >>  ............................................
```


### Button `ask_if_it_stopped_her` — "Did you think about giving it up?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.netherian.the_burn.succeeded` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.the_burn.succeeded.ask_if_it_stopped_her` — accepted phrasings: "did you think about giving it up"; "did you think about giving it up"; "were you tempted to leave the trade"
  - the message must contain one of: `giving`, `tempted`, `leave`
  - scored words: `giving`(1.8), `tempted`(1.8), `leave`(1.8), `think`(0.8), `were`(0.8), `trade`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.ask_if_it_stopped_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_burn.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.ask_if_it_stopped_her   [33 chars]
    en  Did you think about giving it up?
    >>  ............................................
    pt  Você pensou em largar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.netherian.burns`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.netherian.the_burn"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Did you think about giving it up?"
       spoken on: conversations.scene.work.netherian.the_burn.succeeded.respond, button `ask_if_it_stopped_her`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_burn.succeeded.answered`: the villager explains. Subject `work.netherian.burns`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.answered/1   [108 chars]
    en  Every day for four months. Then I could hold a pack again and the question simply stopped being interesting.
    >>  ............................................
    pt  Todo dia por quatro meses. Depois consegui segurar uma mochila de novo e a pergunta simplesmente parou de ser interessante.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.answered/2   [142 chars]
    en  I made a list of other trades. It had four things on it and I was qualified for none of them, which settled it faster than courage would have.
    >>  ............................................
    pt  Fiz uma lista de outros ofícios. Tinha quatro itens e eu não servia para nenhum, o que resolveu mais rápido do que a coragem resolveria.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.answered/3   [136 chars]
    en  I went back out too early, at three months, and turned round on the second day. The second attempt at four months was the one that held.
    >>  ............................................
    pt  Voltei cedo demais, aos três meses, e desisti no segundo dia. A segunda tentativa, aos quatro meses, foi a que segurou.
    >>  ............................................
```


### Button `note_the_change` — "You changed how you work because of it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.netherian.the_burn.succeeded` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.netherian.the_burn.succeeded.note_the_change` — accepted phrasings: "you changed how you work because of it"; "you changed how you work because of it"; "it changed your habits for good"
  - the message must contain one of: `changed`, `habits`
  - scored words: `changed`(1.8), `habits`(1.8), `work`(0.8), `because`(0.8), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.note_the_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_burn.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.note_the_change   [39 chars]
    en  You changed how you work because of it.
    >>  ............................................
    pt  Você mudou como trabalha por causa disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.netherian.burns`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.netherian.the_burn"}
- Then opens: `conversations.scene.work.netherian.followup`
- …where the player's next choices will be: "What's the hardest part of the heat?" | "I'll leave you to the heat."

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You changed how you work because of it."
       spoken on: conversations.scene.work.netherian.the_burn.succeeded.respond, button `note_the_change`
       leaves the player on: conversations.scene.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.the_burn.succeeded.acknowledged`: the villager accepts. Subject `work.netherian.burns`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.acknowledged/1   [129 chars]
    en  Night walking. It halves the water and doubles the sleep, and it took a burn to make me try a thing every old trader had told me.
    >>  ............................................
    pt  Caminhar de noite. Corta a água pela metade e dobra o sono, e foi preciso uma queimadura para eu testar algo que todo comerciante velho já tinha me dito.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.acknowledged/2   [120 chars]
    en  Thank you. I would rather have been told and listened. That option was available for eleven years and I did not take it.
    >>  ............................................
    pt  Obrigada. Eu preferia ter sido avisada e ter escutado. Essa opção esteve disponível por onze anos e eu não aproveitei.
    >>  ............................................
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.acknowledged/3   [91 chars]
    en  I teach it now, first thing, before anybody has earned the right to argue with me about it.
    >>  ............................................
    pt  Eu ensino isso agora, de saída, antes de qualquer um ganhar o direito de discutir comigo a respeito.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your load."

*stance family `exit` · tone `plain` · answers the beat(s) `work.netherian.the_burn.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.netherian.the_burn.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.netherian.the_burn.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to your load.
    >>  ............................................
    pt  Vou deixar você voltar à sua carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your load."
       spoken on: conversations.scene.work.netherian.the_burn.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.craft` — e.g. "Heat, mostly. Knowing exactly how much and for exactly how long, and there is no book that says."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.craft.respond   [18 chars]
    en  That's the method.
    >>  ............................................
    pt  É esse o método.
    >>  ............................................
```


### Button `ask_failures` — "How many failures?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.craft` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.craft.ask_failures` — accepted phrasings: "how many failures"
  - the message must contain one of: `failures`, `numbered`
  - scored words: `failures`(1.5), `many`(0.8), `numbered`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.craft.respond.ask_failures
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.craft.respond.ask_failures   [18 chars]
    en  How many failures?
    >>  ............................................
    pt  Quantas falhas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.craft.ask_failures`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.craft.ask_failures
WHO    VILLAGER — what the player reads after pressing "How many failures?"
       spoken on: conversations.topic.work.netherian.craft.respond, button `ask_failures`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.craft.ask_failures`: the villager explains. Subject `work.netherian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.craft.ask_failures/1   [94 chars]
    en  Two hundred and six. Numbered. Four of them are underlined and those four are the useful ones.
    >>  ............................................
    pt  Duzentas e seis. Numeradas. Quatro estão sublinhadas e essas quatro são as úteis.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.craft.ask_failures/2   [94 chars]
    en  Enough that the book is thicker than anything the librarian has, %1$s, and he knows it exists.
    >>  ............................................
    pt  O bastante pro livro ser mais grosso que qualquer coisa do bibliotecário, %1$s, e ele sabe que existe.
    >>  ............................................
```


### Button `admire` — "Writing down failures is rarer than writing down successes."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.craft` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.craft.admire` — accepted phrasings: "writing down failures is rarer than writing down successes"
  - the message must contain one of: `failures`, `writing`, `rarer`
  - scored words: `failures`(1.2), `writing`(1.5), `rarer`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.craft.respond.admire   [59 chars]
    en  Writing down failures is rarer than writing down successes.
    >>  ............................................
    pt  Anotar falhas é mais raro que anotar acertos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.netherian.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.netherian.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.craft.admire
WHO    VILLAGER — what the player reads after pressing "Writing down failures is rarer than writing down successes."
       spoken on: conversations.topic.work.netherian.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.craft.admire`: the villager accepts. Subject `work.netherian.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.craft.admire/1   [104 chars]
    en  A success tells you one thing that worked. Two hundred failures tell you the shape of the whole problem.
    >>  ............................................
    pt  Um acerto te diz uma coisa que funcionou. Duzentas falhas te dizem o formato do problema inteiro.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.craft.admire/2   [94 chars]
    en  It also means anyone reading it thinks I'm terrible at this, %1$s, which is a cost I accepted.
    >>  ............................................
    pt  Também significa que quem ler acha que eu sou péssimo nisso, %1$s, um custo que eu aceitei.
    >>  ............................................
```


### Button `ask_underlined` — "What are the four underlined ones?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.craft` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.craft.ask_underlined` — accepted phrasings: "what are the four underlined ones"
  - the message must contain one of: `underlined`, `four`, `worst`
  - scored words: `underlined`(1.5), `four`(1.0), `worst`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.craft.respond.ask_underlined
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.craft.respond.ask_underlined   [34 chars]
    en  What are the four underlined ones?
    >>  ............................................
    pt  Quais são as quatro sublinhadas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.craft.ask_underlined`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.craft.ask_underlined
WHO    VILLAGER — what the player reads after pressing "What are the four underlined ones?"
       spoken on: conversations.topic.work.netherian.craft.respond, button `ask_underlined`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.craft.ask_underlined`: the villager explains. Subject `work.netherian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.craft.ask_underlined/1   [101 chars]
    en  The four that nearly killed me. They're underlined so that whoever reads it next doesn't repeat them.
    >>  ............................................
    pt  As quatro que quase me mataram. Sublinhadas pra que quem ler depois não repita.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.craft.ask_underlined/2   [94 chars]
    en  Numbers nine, forty-one, ninety and a hundred and eighty-three, %1$s. I could recite all four.
    >>  ............................................
    pt  Números nove, quarenta e um, noventa e cento e oitenta e três, %1$s. Eu recitaria as quatro.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.craft.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.followup / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.followup`

**Reached from 20 route(s):** `conversations.scene.work.netherian.followup` / `ask_more`; `conversations.topic.work.netherian.craft.respond` / `ask_failures`; `conversations.topic.work.netherian.craft.respond` / `admire`; `conversations.topic.work.netherian.craft.respond` / `ask_underlined`; `conversations.topic.work.netherian.future.respond` / `ask_stone`; `conversations.topic.work.netherian.future.respond` / `encourage`; `conversations.topic.work.netherian.future.respond` / `ask_successor`; `conversations.topic.work.netherian.respond` / `ask_hard`; `conversations.topic.work.netherian.respond` / `value`; `conversations.topic.work.netherian.respond` / `challenge`; `conversations.topic.work.netherian.respond` / `challenge`; `conversations.topic.work.netherian.risk.respond` / `ask_batch` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.challenge.landed` — e.g. "A little. Anyone who does this without a bit of showing off wouldn't do it twice."
- `conversations.work.prof.netherian.challenge.stung` — e.g. "...The eyebrows grow back. The people who came with me the first time did not."
- `conversations.work.prof.netherian.craft.admire` — e.g. "A success tells you one thing that worked. Two hundred failures tell you the shape of the whole problem."
- `conversations.work.prof.netherian.craft.ask_failures` — e.g. "Two hundred and six. Numbered. Four of them are underlined and those four are the useful ones."
- `conversations.work.prof.netherian.craft.ask_underlined` — e.g. "The four that nearly killed me. They're underlined so that whoever reads it next doesn't repeat them."
- `conversations.work.prof.netherian.future.ask_stone` — e.g. "Safer. Nothing about this is safe and I'd not stand here and tell you it was."
- `conversations.work.prof.netherian.future.ask_successor` — e.g. "Because they'd want to know what number ninety felt like. I'd have wanted to know, at twenty."
- `conversations.work.prof.netherian.future.encourage` — e.g. "...The four families. Three of whom don't know. That's an unkind and very effective idea."
- `conversations.work.prof.netherian.hard` — e.g. "Never build a portal where you can't see both sides of it. That one's got three people."
- `conversations.work.prof.netherian.risk.ask_batch` — e.g. "I take it myself first. A quarter dose, in the shed, alone, and then I wait four hours."
- `conversations.work.prof.netherian.risk.ask_cleric` — e.g. "Bring her the batch and let her judge it. Which puts it on her, and I'd rather it stayed on me."
- `conversations.work.prof.netherian.risk.sympathise` — e.g. "...No. I've said it aloud twice, and both times the other person went quiet the way you just did."
- `conversations.work.prof.netherian.task.ask_grains` — e.g. "Four grains that stop a fever. The ratio is the trade; everybody balks at it once and then doesn't."
- `conversations.work.prof.netherian.task.ask_roof` — e.g. "It has taken a roof off. Not mine, and not here, and I've built this shed away from everything."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.followup   [31 chars]
    en  That's the other side, briefly.
    >>  ............................................
    pt  É o outro lado, resumido.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.challenge.landed`, `work.netherian.challenge.stung`, `work.netherian.craft.admire`, `work.netherian.craft.ask_failures`, `work.netherian.craft.ask_underlined`, `work.netherian.future.ask_stone`, `work.netherian.future.ask_successor`, `work.netherian.future.encourage`, `work.netherian.hard`, `work.netherian.risk.ask_batch`, `work.netherian.risk.ask_cleric`, `work.netherian.risk.sympathise`, `work.netherian.task.ask_grains`, `work.netherian.task.ask_roof`, `work.netherian.task.offer_hands`, `work.netherian.value`, `work.netherian.village.ask_four_fevers`, `work.netherian.village.ask_half`, `work.netherian.village.say_thanks` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.netherian.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `monsters`, `rules`
  - scored words: `thought`(1.2), `monsters`(1.2), `rules`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.netherian.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.netherian.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.netherian.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.netherian.thanks`: the villager accepts. Subject `work.netherian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.thanks/1   [65 chars]
    en  Nobody up here does. Down there it's just Tuesday with worse air.
    >>  ............................................
    pt  Ninguém aqui em cima pensa. Lá embaixo é só uma terça-feira com ar pior.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.thanks/2   [84 chars]
    en  It's a place with rules, %1$s. People prefer to imagine it as a place with monsters.
    >>  ............................................
    pt  É um lugar com regras, %1$s. As pessoas preferem imaginar um lugar com monstros.
    >>  ............................................
```


### Button `ask_more` — "What's still unmapped?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.challenge.landed`, `work.netherian.challenge.stung`, `work.netherian.craft.admire`, `work.netherian.craft.ask_failures`, `work.netherian.craft.ask_underlined`, `work.netherian.future.ask_stone`, `work.netherian.future.ask_successor`, `work.netherian.future.encourage`, `work.netherian.hard`, `work.netherian.risk.ask_batch`, `work.netherian.risk.ask_cleric`, `work.netherian.risk.sympathise`, `work.netherian.task.ask_grains`, `work.netherian.task.ask_roof`, `work.netherian.task.offer_hands`, `work.netherian.value`, `work.netherian.village.ask_four_fevers`, `work.netherian.village.ask_half`, `work.netherian.village.say_thanks` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.netherian.more` — accepted phrasings: "what's still unmapped"
  - the message must contain one of: `unmapped`, `mapped`, `beyond`
  - scored words: `unmapped`(1.5), `mapped`(1.2), `beyond`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.followup.ask_more   [22 chars]
    en  What's still unmapped?
    >>  ............................................
    pt  O que ainda não está mapeado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.netherian.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.more
WHO    VILLAGER — what the player reads after pressing "What's still unmapped?"
       spoken on: conversations.topic.work.netherian.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.netherian.more`: the villager discloses. Subject `work.netherian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.more/1   [76 chars]
    en  A whole quarter, past the second bridge. I've stood at the edge of it twice.
    >>  ............................................
    pt  Um quarto inteiro, depois da segunda ponte. Já parei na beira dele duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.more/2   [80 chars]
    en  Everything past the sea of fire. I know the way in. I've not solved the way out.
    >>  ............................................
    pt  Tudo depois do mar de fogo. Sei o caminho de entrada. Não resolvi o de saída.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I've turned round twice and I know why both times.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Voltei duas vezes e sei por quê nas duas.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Nothing about my trade is safe and I'd not stand here and tell you it was.
    >>  ............................................
    pt  Um galpão de pedra. Nada no meu ofício é seguro e eu não ficaria aqui dizendo que é.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, unmapped. It has been there a long time and it will be there when I'm ready.
    >>  ............................................
    pt  Um quarteirão inteiro, sem mapa. Está lá faz muito tempo e vai estar quando eu estiver pronto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Nineteen years of timber has held so far; there's no rush, only sense.
    >>  ............................................
    pt  O galpão em pedra. Dezenove anos de madeira aguentaram; não há pressa, só bom senso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. I've stood at the edge of it twice.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Já fiquei na borda dele duas vezes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Then the half who object would have a reason to stop objecting.
    >>  ............................................
    pt  O galpão em pedra. Aí a metade que objeta teria motivo pra parar de objetar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. I've stood at the edge of it twice.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Já fiquei na borda dele duas vezes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Then the half who object would have a reason to stop objecting.
    >>  ............................................
    pt  O galpão em pedra. Aí a metade que objeta teria motivo pra parar de objetar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I'd describe it properly if you've an evening.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Eu descreveria direito se você tiver uma noite.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. The four families who don't know what I did for them could build it in a week.
    >>  ............................................
    pt  Um galpão de pedra. As quatro famílias que não sabem o que eu fiz por elas construiriam numa semana.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I'd describe it properly if you've an evening.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Eu descreveria direito se você tiver uma noite.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. The four families who don't know what I did for them could build it in a week.
    >>  ............................................
    pt  Um galpão de pedra. As quatro famílias que não sabem o que eu fiz por elas construiriam numa semana.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I'd describe it properly if you've an evening.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Eu descreveria direito se você tiver uma noite.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. The four families who don't know what I did for them could build it in a week.
    >>  ............................................
    pt  Um galpão de pedra. As quatro famílias que não sabem o que eu fiz por elas construiriam numa semana.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I've turned round twice and I know why both times.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Voltei duas vezes e sei por quê nas duas.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Nothing about my trade is safe and I'd not stand here and tell you it was.
    >>  ............................................
    pt  Um galpão de pedra. Nada no meu ofício é seguro e eu não ficaria aqui dizendo que é.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. I've stood at the edge of it twice.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Já fiquei na borda dele duas vezes.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Then the half who object would have a reason to stop objecting.
    >>  ............................................
    pt  O galpão em pedra. Aí a metade que objeta teria motivo pra parar de objetar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. I've stood at the edge of it twice.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Já fiquei na borda dele duas vezes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Then the half who object would have a reason to stop objecting.
    >>  ............................................
    pt  O galpão em pedra. Aí a metade que objeta teria motivo pra parar de objetar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. Unmapped, unnamed, and I have not gone in.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Sem mapa, sem nome, e eu não entrei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. It would contain the worst of it, which is the honest version of the word safe.
    >>  ............................................
    pt  O galpão em pedra. Conteria o pior, que é a versão honesta da palavra seguro.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, unmapped. It has been there a long time and it will be there when I'm ready.
    >>  ............................................
    pt  Um quarteirão inteiro, sem mapa. Está lá faz muito tempo e vai estar quando eu estiver pronto.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Nineteen years of timber has held so far; there's no rush, only sense.
    >>  ............................................
    pt  O galpão em pedra. Dezenove anos de madeira aguentaram; não há pressa, só bom senso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. Unmapped, unnamed, and I have not gone in.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Sem mapa, sem nome, e eu não entrei.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. It would contain the worst of it, which is the honest version of the word safe.
    >>  ............................................
    pt  O galpão em pedra. Conteria o pior, que é a versão honesta da palavra seguro.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, unmapped. It has been there a long time and it will be there when I'm ready.
    >>  ............................................
    pt  Um quarteirão inteiro, sem mapa. Está lá faz muito tempo e vai estar quando eu estiver pronto.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Nineteen years of timber has held so far; there's no rush, only sense.
    >>  ............................................
    pt  O galpão em pedra. Dezenove anos de madeira aguentaram; não há pressa, só bom senso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge! I've stood at the edge twice and turned round twice.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte! Fiquei na borda duas vezes e voltei duas vezes.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Then the objectors would need a new hobby, which I'd frankly enjoy.
    >>  ............................................
    pt  Um galpão de pedra. Aí os que objetam precisariam de outro passatempo, o que eu francamente adoraria.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge! I've stood at the edge twice and turned round twice.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte! Fiquei na borda duas vezes e voltei duas vezes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Then the objectors would need a new hobby, which I'd frankly enjoy.
    >>  ............................................
    pt  Um galpão de pedra. Aí os que objetam precisariam de outro passatempo, o que eu francamente adoraria.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, unmapped. It has been there a long time and it will be there when I'm ready.
    >>  ............................................
    pt  Um quarteirão inteiro, sem mapa. Está lá faz muito tempo e vai estar quando eu estiver pronto.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. Nineteen years of timber has held so far; there's no rush, only sense.
    >>  ............................................
    pt  O galpão em pedra. Dezenove anos de madeira aguentaram; não há pressa, só bom senso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge. I've turned round twice and I know why both times.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte. Voltei duas vezes e sei por quê nas duas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Nothing about my trade is safe and I'd not stand here and tell you it was.
    >>  ............................................
    pt  Um galpão de pedra. Nada no meu ofício é seguro e eu não ficaria aqui dizendo que é.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter, past the second bridge. Unmapped, unnamed, and I have not gone in.
    >>  ............................................
    pt  Um quarteirão inteiro, depois da segunda ponte. Sem mapa, sem nome, e eu não entrei.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.more/2
    en  The shed in stone. It would contain the worst of it, which is the honest version of the word safe.
    >>  ............................................
    pt  O galpão em pedra. Conteria o pior, que é a versão honesta da palavra seguro.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge! I've stood at the edge twice and turned round twice.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte! Fiquei na borda duas vezes e voltei duas vezes.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Then the objectors would need a new hobby, which I'd frankly enjoy.
    >>  ............................................
    pt  Um galpão de pedra. Aí os que objetam precisariam de outro passatempo, o que eu francamente adoraria.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.more/1
    en  A whole quarter past the second bridge! I've stood at the edge twice and turned round twice.
    >>  ............................................
    pt  Um quarteirão inteiro depois da segunda ponte! Fiquei na borda duas vezes e voltei duas vezes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.more/2
    en  A stone shed. Then the objectors would need a new hobby, which I'd frankly enjoy.
    >>  ............................................
    pt  Um galpão de pedra. Aí os que objetam precisariam de outro passatempo, o que eu francamente adoraria.
    >>  ............................................
```

</details>


### Button `leave` — "Mind the heat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.challenge.landed`, `work.netherian.challenge.stung`, `work.netherian.craft.admire`, `work.netherian.craft.ask_failures`, `work.netherian.craft.ask_underlined`, `work.netherian.future.ask_stone`, `work.netherian.future.ask_successor`, `work.netherian.future.encourage`, `work.netherian.hard`, `work.netherian.risk.ask_batch`, `work.netherian.risk.ask_cleric`, `work.netherian.risk.sympathise`, `work.netherian.task.ask_grains`, `work.netherian.task.ask_roof`, `work.netherian.task.offer_hands`, `work.netherian.value`, `work.netherian.village.ask_four_fevers`, `work.netherian.village.ask_half`, `work.netherian.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.followup.leave   [14 chars]
    en  Mind the heat.
    >>  ............................................
    pt  Cuidado com o calor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "Mind the heat."
       spoken on: conversations.topic.work.netherian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.future` — e.g. "The book of failures should go to the cleric, not to a successor. She'd use it and a successor would repeat it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.future.respond   [23 chars]
    en  That's what I'd settle.
    >>  ............................................
    pt  É o que eu resolveria.
    >>  ............................................
```


### Button `ask_stone` — "Would stone actually make it safe?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.future` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.future.ask_stone` — accepted phrasings: "would stone actually make it safe"
  - the message must contain one of: `stone`, `safe`, `building`
  - scored words: `stone`(1.5), `safe`(1.2), `building`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.future.respond.ask_stone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.future.respond.ask_stone   [34 chars]
    en  Would stone actually make it safe?
    >>  ............................................
    pt  Pedra tornaria seguro de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.future.ask_stone`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.future.ask_stone
WHO    VILLAGER — what the player reads after pressing "Would stone actually make it safe?"
       spoken on: conversations.topic.work.netherian.future.respond, button `ask_stone`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.future.ask_stone`: the villager explains. Subject `work.netherian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.future.ask_stone/1   [77 chars]
    en  Safer. Nothing about this is safe and I'd not stand here and tell you it was.
    >>  ............................................
    pt  Mais seguro. Nada nisso é seguro e eu não ficaria aqui dizendo que é.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.future.ask_stone/2   [85 chars]
    en  It would contain the worst of it, %1$s, which is the honest version of the word safe.
    >>  ............................................
    pt  Conteria o pior, %1$s, que é a versão honesta da palavra seguro.
    >>  ............................................
```


### Button `encourage` — "Then ask the four families to build it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.future` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.future.encourage` — accepted phrasings: "then ask the four families to build it"
  - the message must contain one of: `families`, `build`
  - scored words: `families`(1.5), `build`(1.2), `four`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.future.respond.encourage   [39 chars]
    en  Then ask the four families to build it.
    >>  ............................................
    pt  Então peça às quatro famílias pra construir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.netherian.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.netherian.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then ask the four families to build it."
       spoken on: conversations.topic.work.netherian.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.future.encourage`: the villager accepts. Subject `work.netherian.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.future.encourage/1   [89 chars]
    en  ...The four families. Three of whom don't know. That's an unkind and very effective idea.
    >>  ............................................
    pt  ...As quatro famílias. Três das quais não sabem. É uma ideia cruel e muito eficaz.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.future.encourage/2   [95 chars]
    en  The one who brings me bread would build it herself, %1$s. I've never let myself think that far.
    >>  ............................................
    pt  A que me traz pão construiria sozinha, %1$s. Eu nunca me deixei pensar tão longe.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know, and telling them is on me.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e contar é comigo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself, and I'd not know where to look.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma, e eu não saberia onde olhar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know, and secrets that old get heavier, not lighter.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e segredos velhos pesam mais, não menos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. People do, when you finally ask.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. As pessoas fazem, quando enfim se pede.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know. That's an unkind and effective idea.
    >>  ............................................
    pt  ...As quatro famílias. Três delas não sabem. É uma ideia cruel e eficaz.
    >>  ............................................
  confident.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know. That's an unkind and effective idea.
    >>  ............................................
    pt  ...As quatro famílias. Três delas não sabem. É uma ideia cruel e eficaz.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families, %1$s. Three of whom don't know. Unkind, and it would work.
    >>  ............................................
    pt  ...As quatro famílias, %1$s. Três não sabem. Cruel, e funcionaria.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families, %1$s. Three of whom don't know. Unkind, and it would work.
    >>  ............................................
    pt  ...As quatro famílias, %1$s. Três não sabem. Cruel, e funcionaria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families, %1$s. Three of whom don't know. Unkind, and it would work.
    >>  ............................................
    pt  ...As quatro famílias, %1$s. Três não sabem. Cruel, e funcionaria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know, and telling them is on me.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e contar é comigo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself, and I'd not know where to look.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma, e eu não saberia onde olhar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know. That's an unkind and effective idea.
    >>  ............................................
    pt  ...As quatro famílias. Três delas não sabem. É uma ideia cruel e eficaz.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know. That's an unkind and effective idea.
    >>  ............................................
    pt  ...As quatro famílias. Três delas não sabem. É uma ideia cruel e eficaz.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never thought that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca pensei tão longe.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one with the bread would build it herself.
    >>  ............................................
    pt  A do pão construiria ela mesma.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know, and secrets that old get heavier, not lighter.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e segredos velhos pesam mais, não menos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. People do, when you finally ask.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. As pessoas fazem, quando enfim se pede.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem.
    >>  ............................................
  odd.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one with the bread would build it herself.
    >>  ............................................
    pt  A do pão construiria ela mesma.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know, and secrets that old get heavier, not lighter.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e segredos velhos pesam mais, não menos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. People do, when you finally ask.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. As pessoas fazem, quando enfim se pede.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families! Three of whom don't know. Unkind and effective, which is a pair.
    >>  ............................................
    pt  ...As quatro famílias! Três não sabem. Cruel e eficaz, que é um belo par.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never let myself think that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca me deixei pensar tão longe.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families! Three of whom don't know. Unkind and effective, which is a pair.
    >>  ............................................
    pt  ...As quatro famílias! Três não sabem. Cruel e eficaz, que é um belo par.
    >>  ............................................
  playful.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never let myself think that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca me deixei pensar tão longe.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know, and secrets that old get heavier, not lighter.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e segredos velhos pesam mais, não menos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. People do, when you finally ask.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. As pessoas fazem, quando enfim se pede.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three of whom don't know, and telling them is on me.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem, e contar é comigo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself, and I'd not know where to look.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma, e eu não saberia onde olhar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families. Three don't know.
    >>  ............................................
    pt  ...As quatro famílias. Três não sabem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one with the bread would build it herself.
    >>  ............................................
    pt  A do pão construiria ela mesma.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families! Three of whom don't know. Unkind and effective, which is a pair.
    >>  ............................................
    pt  ...As quatro famílias! Três não sabem. Cruel e eficaz, que é um belo par.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never let myself think that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca me deixei pensar tão longe.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.future.encourage/1
    en  ...The four families! Three of whom don't know. Unkind and effective, which is a pair.
    >>  ............................................
    pt  ...As quatro famílias! Três não sabem. Cruel e eficaz, que é um belo par.
    >>  ............................................
  witty.dialogue.conversations.work.prof.netherian.future.encourage/2
    en  The one who brings me bread would build it herself. I've never let myself think that far.
    >>  ............................................
    pt  A que me traz pão construiria ela mesma. Nunca me deixei pensar tão longe.
    >>  ............................................
```

</details>


### Button `ask_successor` — "Why would a successor repeat the failures?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.future` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.future.ask_successor` — accepted phrasings: "why would a successor repeat the failures"
  - the message must contain one of: `successor`, `repeat`, `failures`
  - scored words: `successor`(1.5), `repeat`(1.2), `failures`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.future.respond.ask_successor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.future.respond.ask_successor   [42 chars]
    en  Why would a successor repeat the failures?
    >>  ............................................
    pt  Por que um sucessor repetiria as falhas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.future.ask_successor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.future.ask_successor
WHO    VILLAGER — what the player reads after pressing "Why would a successor repeat the failures?"
       spoken on: conversations.topic.work.netherian.future.respond, button `ask_successor`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.future.ask_successor`: the villager explains. Subject `work.netherian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.future.ask_successor/1   [93 chars]
    en  Because they'd want to know what number ninety felt like. I'd have wanted to know, at twenty.
    >>  ............................................
    pt  Porque ia querer saber como era a número noventa. Eu teria querido saber, aos vinte.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.future.ask_successor/2   [105 chars]
    en  Because a book of failures reads like a challenge to the right sort of person, %1$s, and I was that sort.
    >>  ............................................
    pt  Porque um livro de falhas soa como desafio pro tipo certo de pessoa, %1$s, e eu era esse tipo.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.future.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian` — e.g. "I map the red place. Yes, the burning one. My eyebrows grow back faster than you'd think."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.respond   [35 chars]
    en  That's the red place and its rules.
    >>  ............................................
    pt  É o lugar vermelho e as regras dele.
    >>  ............................................
```


### Button `ask_hard` — "Which rule do people forget?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.identity` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.hard` — accepted phrasings: "which rule do people forget"
  - the message must contain one of: `rule`, `forget`, `mistake`
  - scored words: `rule`(1.5), `forget`(1.2), `mistake`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.respond.ask_hard   [28 chars]
    en  Which rule do people forget?
    >>  ............................................
    pt  Qual regra as pessoas esquecem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.netherian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.hard
WHO    VILLAGER — what the player reads after pressing "Which rule do people forget?"
       spoken on: conversations.topic.work.netherian.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.hard`: the villager explains. Subject `work.netherian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / ask_more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Nobody else here will go through that gate."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.identity` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.value` — accepted phrasings: "nobody else here will go through that gate"
  - the message must contain one of: `gate`, `portal`
  - scored words: `gate`(1.5), `portal`(1.5), `nobody`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.respond.value   [43 chars]
    en  Nobody else here will go through that gate.
    >>  ............................................
    pt  Mais ninguém aqui atravessa aquele portal.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.netherian.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.netherian.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.value
WHO    VILLAGER — what the player reads after pressing "Nobody else here will go through that gate."
       spoken on: conversations.topic.work.netherian.respond, button `value`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.value`: the villager accepts. Subject `work.netherian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.value/1   [69 chars]
    en  Nobody else needs to. That's the arrangement and I'm content with it.
    >>  ............................................
    pt  Mais ninguém precisa. É o combinado e eu estou satisfeito.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.value/2   [73 chars]
    en  They won't, and they'll still take the quartz. I've made peace with that.
    >>  ............................................
    pt  Não atravessam, e ainda assim levam o quartzo. Já fiz as pazes com isso.
    >>  ............................................
```


### Button `challenge` — "You're just showing off."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.netherian.identity` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.challenge` — accepted phrasings: "you're just showing off"
  - the message must contain one of: `showing`, `boasting`
  - scored words: `showing`(1.5), `boasting`(1.5), `off`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.respond.challenge   [24 chars]
    en  You're just showing off.
    >>  ............................................
    pt  Você só está se exibindo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.netherian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.netherian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're just showing off."
       spoken on: conversations.topic.work.netherian.respond, button `challenge`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.challenge.landed`: the villager resists. Subject `work.netherian.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.challenge.landed/1   [81 chars]
    en  A little. Anyone who does this without a bit of showing off wouldn't do it twice.
    >>  ............................................
    pt  Um pouco. Quem faz isso sem um pouco de exibição não faria duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.challenge.landed/2   [78 chars]
    en  There's showing off in it, aye. There's also a map nobody else can make, %1$s.
    >>  ............................................
    pt  Tem exibição, sim. Também tem um mapa que mais ninguém consegue fazer, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.netherian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.netherian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're just showing off."
       spoken on: conversations.topic.work.netherian.respond, button `challenge`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.challenge.stung`: the villager resists. Subject `work.netherian.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.challenge.stung/1   [78 chars]
    en  ...The eyebrows grow back. The people who came with me the first time did not.
    >>  ............................................
    pt  ...As sobrancelhas crescem de volta. As pessoas que foram comigo da primeira vez não voltaram.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.challenge.stung/2   [69 chars]
    en  Showing off. Right. Step through once and then tell me what it's for.
    >>  ............................................
    pt  Exibição. Certo. Atravesse uma vez e aí me diga pra que serve.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.risk` — e.g. "I keep the shed a field away and I've never asked anybody to help me carry anything into it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.risk.respond   [28 chars]
    en  That's what's underneath it.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_batch` — "How do you know a batch is right?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.risk` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.risk.ask_batch` — accepted phrasings: "how do you know a batch is right"
  - the message must contain one of: `batch`, `test`
  - scored words: `batch`(1.5), `right`(0.8), `test`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.risk.respond.ask_batch
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.risk.respond.ask_batch   [33 chars]
    en  How do you know a batch is right?
    >>  ............................................
    pt  Como você sabe que um lote está certo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.risk.ask_batch`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.risk.ask_batch
WHO    VILLAGER — what the player reads after pressing "How do you know a batch is right?"
       spoken on: conversations.topic.work.netherian.risk.respond, button `ask_batch`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.risk.ask_batch`: the villager explains. Subject `work.netherian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.risk.ask_batch/1   [87 chars]
    en  I take it myself first. A quarter dose, in the shed, alone, and then I wait four hours.
    >>  ............................................
    pt  Eu tomo primeiro. Um quarto de dose, no galpão, sozinho, e aí espero quatro horas.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.risk.ask_batch/2   [106 chars]
    en  Colour and weight, and then the thing I just described, %1$s, which the cleric has asked me to stop doing.
    >>  ............................................
    pt  Cor e peso, e depois o que eu acabei de descrever, %1$s, que a clériga me pediu pra não fazer mais.
    >>  ............................................
```


### Button `sympathise` — "Testing it on yourself is not a small thing to mention in passing."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.risk` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.risk.sympathise` — accepted phrasings: "testing it on yourself is not a small thing to mention in passing"
  - the message must contain one of: `testing`, `yourself`, `passing`
  - scored words: `testing`(1.5), `yourself`(1.2), `passing`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.risk.respond.sympathise   [66 chars]
    en  Testing it on yourself is not a small thing to mention in passing.
    >>  ............................................
    pt  Testar em si mesmo não é coisa pequena de mencionar de passagem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.netherian.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.netherian.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Testing it on yourself is not a small thing to mention in passing."
       spoken on: conversations.topic.work.netherian.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.risk.sympathise`: the villager accepts. Subject `work.netherian.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.risk.sympathise/1   [97 chars]
    en  ...No. I've said it aloud twice, and both times the other person went quiet the way you just did.
    >>  ............................................
    pt  ...Não. Já disse em voz alta duas vezes, e nas duas a outra pessoa ficou quieta como você.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.risk.sympathise/2   [103 chars]
    en  It's the only honest way I've found, %1$s, and I would very much like somebody to find me a better one.
    >>  ............................................
    pt  É o único jeito honesto que eu achei, %1$s, e eu gostaria muito que alguém achasse um melhor.
    >>  ............................................
```


### Button `ask_cleric` — "What does the cleric want you to do instead?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.risk` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.risk.ask_cleric` — accepted phrasings: "what does the cleric want you to do instead"
  - the message must contain one of: `cleric`, `instead`
  - scored words: `cleric`(1.5), `instead`(1.2), `wants`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.risk.respond.ask_cleric
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.risk.respond.ask_cleric   [44 chars]
    en  What does the cleric want you to do instead?
    >>  ............................................
    pt  O que a clériga quer que você faça?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.risk.ask_cleric`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.risk.ask_cleric
WHO    VILLAGER — what the player reads after pressing "What does the cleric want you to do instead?"
       spoken on: conversations.topic.work.netherian.risk.respond, button `ask_cleric`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.risk.ask_cleric`: the villager explains. Subject `work.netherian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.risk.ask_cleric/1   [95 chars]
    en  Bring her the batch and let her judge it. Which puts it on her, and I'd rather it stayed on me.
    >>  ............................................
    pt  Levar o lote e deixar ela julgar. O que põe nela, e eu prefiro que fique em mim.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.risk.ask_cleric/2   [89 chars]
    en  She's offered to take the quarter dose herself, %1$s. We have not resolved that argument.
    >>  ............................................
    pt  Ela se ofereceu pra tomar o quarto de dose, %1$s. Nós não resolvemos essa discussão.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.risk.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.task` — e.g. "Drying what I brought back. It has to be done slowly or it takes the roof off, and I'd rather keep the roof."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.task.respond   [22 chars]
    en  That's the shed today.
    >>  ............................................
    pt  É o galpão hoje.
    >>  ............................................
```


### Button `ask_roof` — "It could take the roof off?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.task` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.task.ask_roof` — accepted phrasings: "it could take the roof off"
  - the message must contain one of: `roof`, `explode`, `danger`
  - scored words: `roof`(1.5), `explode`(1.2), `danger`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.task.respond.ask_roof
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.task.respond.ask_roof   [27 chars]
    en  It could take the roof off?
    >>  ............................................
    pt  Poderia levar o telhado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.task.ask_roof`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.task.ask_roof
WHO    VILLAGER — what the player reads after pressing "It could take the roof off?"
       spoken on: conversations.topic.work.netherian.task.respond, button `ask_roof`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.task.ask_roof`: the villager explains. Subject `work.netherian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.task.ask_roof/1   [95 chars]
    en  It has taken a roof off. Not mine, and not here, and I've built this shed away from everything.
    >>  ............................................
    pt  Já levou um telhado. Não o meu, e não aqui, e eu fiz este galpão longe de tudo.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.task.ask_roof/2   [99 chars]
    en  That's why the shed is where it is, %1$s, and why I never argue when people ask me to move further.
    >>  ............................................
    pt  Por isso o galpão está onde está, %1$s, e por isso eu nunca discuto quando pedem pra eu ir mais longe.
    >>  ............................................
```


### Button `offer_hands` — "I could turn the drying trays."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.netherian.task` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.task.offer_hands` — accepted phrasings: "i could turn the drying trays"
  - the message must contain one of: `trays`, `drying`, `turn`
  - scored words: `trays`(1.5), `drying`(1.2), `turn`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.task.respond.offer_hands   [30 chars]
    en  I could turn the drying trays.
    >>  ............................................
    pt  Eu podia virar as bandejas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.netherian.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.netherian.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could turn the drying trays."
       spoken on: conversations.topic.work.netherian.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.task.offer_hands`: the villager accepts. Subject `work.netherian.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.task.offer_hands/1   [92 chars]
    en  ...From the doorway, with the long paddle, and not one step further in. Those are the terms.
    >>  ............................................
    pt  ...Da porta, com a pá longa, e nem um passo a mais. São os termos.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.task.offer_hands/2   [92 chars]
    en  You'd be the second person ever to stand in here, %1$s. Take that seriously and I'll accept.
    >>  ............................................
    pt  Você seria a segunda pessoa a ficar aqui dentro, %1$s. Leve a sério e eu aceito.
    >>  ............................................
```


### Button `ask_grains` — "Four grains from two hours?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.task` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.task.ask_grains` — accepted phrasings: "four grains from two hours"
  - the message must contain one of: `grains`, `hours`
  - scored words: `grains`(1.5), `hours`(1.0), `four`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.task.respond.ask_grains
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.task.respond.ask_grains   [27 chars]
    en  Four grains from two hours?
    >>  ............................................
    pt  Quatro grãos de duas horas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.task.ask_grains`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.task.ask_grains
WHO    VILLAGER — what the player reads after pressing "Four grains from two hours?"
       spoken on: conversations.topic.work.netherian.task.respond, button `ask_grains`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.task.ask_grains`: the villager explains. Subject `work.netherian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.task.ask_grains/1   [99 chars]
    en  Four grains that stop a fever. The ratio is the trade; everybody balks at it once and then doesn't.
    >>  ............................................
    pt  Quatro grãos que cortam uma febre. A proporção é o ofício; todos estranham uma vez e depois não.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.task.ask_grains/2   [86 chars]
    en  The cleric wastes none of it, %1$s. In eleven years she has not wasted a single grain.
    >>  ............................................
    pt  A clériga não desperdiça nada, %1$s. Em onze anos ela não desperdiçou um grão.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.task.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.netherian.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.netherian.village` — e.g. "Four fevers in eleven years that would have gone the other way. That's what the jar is for."


```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.netherian.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.netherian.village.respond   [25 chars]
    en  That's the balance of it.
    >>  ............................................
    pt  É esse o equilíbrio.
    >>  ............................................
```


### Button `ask_four_fevers` — "Do the four know?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.village` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.village.ask_four_fevers` — accepted phrasings: "do the four know"
  - the message must contain one of: `fevers`, `four`
  - scored words: `fevers`(1.5), `know`(0.6), `four`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.village.respond.ask_four_fevers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.village.respond.ask_four_fevers   [17 chars]
    en  Do the four know?
    >>  ............................................
    pt  As quatro sabem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.village.ask_four_fevers`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.village.ask_four_fevers
WHO    VILLAGER — what the player reads after pressing "Do the four know?"
       spoken on: conversations.topic.work.netherian.village.respond, button `ask_four_fevers`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.village.ask_four_fevers`: the villager explains. Subject `work.netherian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.village.ask_four_fevers/1   [82 chars]
    en  One does. She brings me bread and doesn't say why, and I don't ask her to say why.
    >>  ............................................
    pt  Uma sabe. Ela me traz pão e não diz por quê, e eu não peço que diga.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.village.ask_four_fevers/2   [87 chars]
    en  None of them, %1$s. The cleric tells them it was her, which is the correct arrangement.
    >>  ............................................
    pt  Nenhuma, %1$s. A clériga diz que foi ela, que é o arranjo correto.
    >>  ............................................
```


### Button `say_thanks` — "Four people are alive. That outranks the half who'd prefer otherwise."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.netherian.village` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.village.say_thanks` — accepted phrasings: "four people are alive. that outranks the half who'd prefer otherwise"
  - the message must contain one of: `alive`, `outranks`
  - scored words: `alive`(1.5), `outranks`(1.2), `four`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.village.respond.say_thanks   [69 chars]
    en  Four people are alive. That outranks the half who'd prefer otherwise.
    >>  ............................................
    pt  Quatro pessoas estão vivas. Isso supera a metade que preferia o contrário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.netherian.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.netherian.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Four people are alive. That outranks the half who'd prefer otherwise."
       spoken on: conversations.topic.work.netherian.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.village.say_thanks`: the villager accepts. Subject `work.netherian.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.village.say_thanks/1   [81 chars]
    en  ...Outranks. That's a word I'd not have used and I'm going to use it from now on.
    >>  ............................................
    pt  ...Supera. É uma palavra que eu não teria usado e vou usar de agora em diante.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.village.say_thanks/2   [97 chars]
    en  It doesn't make them wrong to be frightened, %1$s. It makes them wrong to be frightened out loud.
    >>  ............................................
    pt  Não os torna errados por terem medo, %1$s. Torna errado ter medo em voz alta.
    >>  ............................................
```


### Button `ask_half` — "Does the half that objects say so to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.netherian.village` · offered only once the villager has actually said `work:netherian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.netherian.village.ask_half` — accepted phrasings: "does the half that objects say so to you"
  - the message must contain one of: `objects`, `half`, `complain`
  - scored words: `objects`(1.5), `half`(1.0), `complain`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.village.respond.ask_half
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.village.respond.ask_half   [41 chars]
    en  Does the half that objects say so to you?
    >>  ............................................
    pt  A metade que objeta te diz isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.netherian.village.ask_half`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.netherian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's still unmapped?" | "Mind the heat."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.village.ask_half
WHO    VILLAGER — what the player reads after pressing "Does the half that objects say so to you?"
       spoken on: conversations.topic.work.netherian.village.respond, button `ask_half`
       leaves the player on: conversations.topic.work.netherian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.village.ask_half`: the villager explains. Subject `work.netherian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:netherian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.netherian.village.ask_half/1   [86 chars]
    en  Twice a year, at the meeting, in the polite words people use when they mean move away.
    >>  ............................................
    pt  Duas vezes por ano, na reunião, com as palavras educadas que se usa pra dizer se muda daqui.
    >>  ............................................
  dialogue.conversations.work.prof.netherian.village.ask_half/2   [93 chars]
    en  One of them says it to my face and I respect him for it, %1$s. The rest say it to each other.
    >>  ............................................
    pt  Um deles diz na minha cara e eu o respeito por isso, %1$s. O resto diz entre si.
    >>  ............................................
```


### Button `leave` — "I'll let you get ready."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.netherian.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.netherian.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.netherian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.netherian.village.respond.leave   [23 chars]
    en  I'll let you get ready.
    >>  ............................................
    pt  Vou deixar você se preparar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.netherian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get ready."
       spoken on: conversations.topic.work.netherian.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.netherian.left`: the villager accepts. Subject `work.netherian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.netherian.followup / leave; conversations.scene.work.netherian.spoiled_load.blocked.respond / leave; conversations.scene.work.netherian.spoiled_load.succeeded.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.active.respond / leave; conversations.scene.work.netherian.the_apprentice_trader.succeeded.respond / leave; conversations.scene.work.netherian.the_burn.succeeded.respond / leave; conversations.topic.work.netherian.craft.respond / leave; conversations.topic.work.netherian.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.netherian.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

