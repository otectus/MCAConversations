# Work talk with a toolsmith

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.toolsmith.cheap_customer.active.respond`](#conversations-scene-work-toolsmith-cheap-customer-active-respond)
- [`conversations.scene.work.toolsmith.cheap_customer.succeeded.respond`](#conversations-scene-work-toolsmith-cheap-customer-succeeded-respond)
- [`conversations.scene.work.toolsmith.failed_tool.blocked.respond`](#conversations-scene-work-toolsmith-failed-tool-blocked-respond)
- [`conversations.scene.work.toolsmith.failed_tool.succeeded.respond`](#conversations-scene-work-toolsmith-failed-tool-succeeded-respond)
- [`conversations.scene.work.toolsmith.followup`](#conversations-scene-work-toolsmith-followup)
- [`conversations.scene.work.toolsmith.heirloom_repair.active.respond`](#conversations-scene-work-toolsmith-heirloom-repair-active-respond)
- [`conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond`](#conversations-scene-work-toolsmith-heirloom-repair-succeeded-respond)
- [`conversations.topic.work.toolsmith.craft.respond`](#conversations-topic-work-toolsmith-craft-respond)
- [`conversations.topic.work.toolsmith.followup`](#conversations-topic-work-toolsmith-followup)
- [`conversations.topic.work.toolsmith.future.respond`](#conversations-topic-work-toolsmith-future-respond)
- [`conversations.topic.work.toolsmith.respond`](#conversations-topic-work-toolsmith-respond)
- [`conversations.topic.work.toolsmith.risk.respond`](#conversations-topic-work-toolsmith-risk-respond)
- [`conversations.topic.work.toolsmith.task.respond`](#conversations-topic-work-toolsmith-task-respond)
- [`conversations.topic.work.toolsmith.village.respond`](#conversations-topic-work-toolsmith-village-respond)

---

## `conversations.scene.work.toolsmith.cheap_customer.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.cheap_customer.active` — e.g. "%2$s wants an edge for half what an edge costs, and I can do it, and it will fail in a season."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.cheap_customer.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond   [10 chars]
    en  The price.
    >>  ............................................
    pt  O preço.
    >>  ............................................
```


### Button `ask_what_she_does` — "So what do you offer them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.cheap_customer.active` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.cheap_customer.active.ask_what_she_does` — accepted phrasings: "so what do you offer them"; "so what do you offer them"; "what do you end up offering"
  - the message must contain one of: `offer`, `offering`
  - scored words: `offer`(1.8), `offering`(1.8), `end`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.ask_what_she_does
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.cheap_customer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.ask_what_she_does   [26 chars]
    en  So what do you offer them?
    >>  ............................................
    pt  Então o que você oferece?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.the_people_who_use_them`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.cheap_customer"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.explained
WHO    VILLAGER — what the player reads after pressing "So what do you offer them?"
       spoken on: conversations.scene.work.toolsmith.cheap_customer.active.respond, button `ask_what_she_does`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.cheap_customer.active.explained`: the villager explains. Subject `work.toolsmith.the_people_who_use_them`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.explained/1   [105 chars]
    en  A repair instead of a new one. Half the price, honestly earned, and it buys them a year to find the rest.
    >>  ............................................
    pt  Um conserto em vez de nova. Metade do preço, ganho honestamente, e compra a eles um ano para achar o resto.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.explained/2   [128 chars]
    en  Full price and a long wait for payment. It costs me nothing except the worry, and the worry is mine to carry rather than theirs.
    >>  ............................................
    pt  Preço cheio e prazo longo para pagar. Não me custa nada além da preocupação, e a preocupação é minha de carregar, não deles.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.explained/3   [119 chars]
    en  The truth, first. That there is no cheap edge, only a short one. Then we work out which of us can carry the difference.
    >>  ............................................
    pt  A verdade, primeiro. Que não existe fio barato, só fio curto. Depois a gente resolve qual de nós pode arcar com a diferença.
    >>  ............................................
```


### Button `back_the_honest_price` — "Hold your standard on the edge."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.cheap_customer.active` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.cheap_customer.active.back_the_honest_price` — accepted phrasings: "hold your standard on the edge"; "only make tools you would trust yourself"; "hold your standard on the edge"
  - the message must contain one of: `trust`, `standard`
  - scored words: `trust`(1.8), `standard`(1.8), `hold`(0.8), `edge`(0.8), `only`(0.8), `make`(0.8), `tools`(0.8), `yourself`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.back_the_honest_price
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.cheap_customer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.back_the_honest_price   [31 chars]
    en  Hold your standard on the edge.
    >>  ............................................
    pt  Mantenha seu padrão no fio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4  _(recorded under topic `work.toolsmith.the_people_who_use_them`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.cheap_customer"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.steadied
WHO    VILLAGER — what the player reads after pressing "Hold your standard on the edge."
       spoken on: conversations.scene.work.toolsmith.cheap_customer.active.respond, button `back_the_honest_price`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.cheap_customer.active.steadied`: the villager accepts. Subject `work.toolsmith.the_people_who_use_them`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.steadied/1   [126 chars]
    en  That is the rule and it is easy to say in a warm room. It is harder in a doorway with somebody who needs a scythe by Thursday.
    >>  ............................................
    pt  É a regra e é fácil dizer numa sala aquecida. É mais difícil numa porta com alguém que precisa de uma foice até quinta.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.steadied/2   [118 chars]
    en  I hold it. It has cost me four customers in eleven years and none of them came to harm, and that is the trade I chose.
    >>  ............................................
    pt  Eu mantenho. Custou quatro clientes em onze anos e nenhum se machucou, e foi essa a troca que escolhi.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.steadied/3   [131 chars]
    en  Thank you. It helps to hear it from outside, because from inside it sounds like pride dressed up as principle, and some days it is.
    >>  ............................................
    pt  Obrigada. Ajuda ouvir de fora, porque de dentro soa como orgulho fantasiado de princípio, e em alguns dias é.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.cheap_customer.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.cheap_customer.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.active.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.cheap_customer.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.toolsmith.leave/1   [34 chars]
    en  It never gets shorter. Off you go.
    >>  ............................................
    pt  Ela nunca diminui. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.leave/2   [51 chars]
    en  Aye. Bring me anything of yours that's blunt, %1$s.
    >>  ............................................
    pt  É. Me traga qualquer coisa sua que esteja cega, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.toolsmith.cheap_customer.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.cheap_customer.succeeded` — e.g. "%2$s took the repair and came back in the autumn and paid for a new one in full."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond   [14 chars]
    en  That customer.
    >>  ............................................
    pt  Aquele cliente.
    >>  ............................................
```


### Button `note_the_arrangement` — "Repair first is a good arrangement."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.toolsmith.cheap_customer.succeeded` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.cheap_customer.succeeded.note_the_arrangement` — accepted phrasings: "repair first is a good arrangement"; "repair first is a good arrangement"; "mending before selling is sensible"
  - the message must contain one of: `repair`, `mending`, `arrangement`
  - scored words: `repair`(1.8), `mending`(1.8), `arrangement`(1.8), `first`(0.8), `good`(0.8), `before`(0.8), `selling`(0.8), `sensible`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond.note_the_arrangement
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond.note_the_arrangement   [35 chars]
    en  Repair first is a good arrangement.
    >>  ............................................
    pt  Consertar primeiro é um bom arranjo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.toolsmith.the_people_who_use_them`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.toolsmith.cheap_customer"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Repair first is a good arrangement."
       spoken on: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond, button `note_the_arrangement`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.cheap_customer.succeeded.acknowledged`: the villager accepts. Subject `work.toolsmith.the_people_who_use_them`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.acknowledged/1   [96 chars]
    en  It is, and it took me eleven years to invent something my grandmother did as a matter of course.
    >>  ............................................
    pt  É, e eu levei onze anos para inventar uma coisa que minha avó fazia como rotina.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.acknowledged/2   [117 chars]
    en  Thank you. It also means I make fewer tools, which the ledger dislikes and the village does not seem to have noticed.
    >>  ............................................
    pt  Obrigada. Também significa que eu faço menos ferramentas, coisa de que o livro-caixa não gosta e que a vila parece não ter notado.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.acknowledged/3   [113 chars]
    en  The good part is that a repair teaches me something. A sale teaches me nothing at all until it comes back broken.
    >>  ............................................
    pt  A parte boa é que um conserto me ensina algo. Uma venda não me ensina nada até voltar quebrada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.cheap_customer.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.cheap_customer.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.cheap_customer.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.toolsmith.failed_tool.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.failed_tool.blocked` — e.g. "%2$s I made came apart in somebody's hands last week. It was %3$s and I still cannot put the thing down."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond   [20 chars]
    en  The tool that broke.
    >>  ............................................
    pt  A ferramenta que quebrou.
    >>  ............................................
```


### Button `ask_whose_fault` — "Was it your work or their use?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.failed_tool.blocked` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.failed_tool.blocked.ask_whose_fault` — accepted phrasings: "was it your work or their use"; "was it your work or their use"; "did the fault sit with the making or the using"
  - the message must contain one of: `use`, `making`, `fault`
  - scored words: `use`(1.8), `making`(1.8), `fault`(1.8), `work`(0.8), `their`(0.8), `sit`(0.8), `using`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.ask_whose_fault
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.ask_whose_fault   [30 chars]
    en  Was it your work or their use?
    >>  ............................................
    pt  Foi seu trabalho ou o uso deles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.toolsmith.edges`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.failed_tool"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Was it your work or their use?"
       spoken on: conversations.scene.work.toolsmith.failed_tool.blocked.respond, button `ask_whose_fault`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.blocked.explained`: the villager explains. Subject `work.toolsmith.edges`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.explained/1   [103 chars]
    en  %2$s, which means both, which is the answer nobody wants because you cannot apologise for half a thing.
    >>  ............................................
    pt  %2$s, o que significa os dois, que é a resposta que ninguém quer porque não dá para pedir desculpa por meia coisa.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.explained/2   [114 chars]
    en  Mine, in the part that mattered. I could tell you about the misuse as well and I would be doing it to feel better.
    >>  ............................................
    pt  Minha, na parte que importava. Eu também poderia falar do mau uso, e estaria fazendo isso para me sentir melhor.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.explained/3   [129 chars]
    en  I will know when I have cut it open. Until then I am guessing, and a smith who guesses about a break is how the next one happens.
    >>  ............................................
    pt  Vou saber quando abrir aquilo. Até lá estou chutando, e uma ferreira que chuta sobre uma quebra é como a próxima acontece.
    >>  ............................................
```


### Button `offer_iron` — "I'll bring iron to remake it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.toolsmith.failed_tool.blocked` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.failed_tool.blocked.offer_iron` — accepted phrasings: "ill bring iron to remake it"; "i can bring iron to remake it"; "let me fetch iron for the replacement"
  - the message must contain one of: `iron`, `replacement`
  - scored words: `iron`(1.8), `replacement`(1.8), `ill`(0.8), `bring`(0.8), `remake`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.offer_iron
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.offer_iron   [29 chars]
    en  I'll bring iron to remake it.
    >>  ............................................
    pt  Vou trazer ferro para refazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.toolsmith.failure.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.toolsmith.edges`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.failed_tool", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.failed_tool", "obligation": "commitment:work.toolsmith.bring_iron"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.toolsmith.bring_iron"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring iron to remake it."
       spoken on: conversations.scene.work.toolsmith.failed_tool.blocked.respond, button `offer_iron`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.blocked.accepted`: the villager accepts. Subject `work.toolsmith.edges`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.accepted/1   [88 chars]
    en  Then they get %2$s back inside three days and I get to hand it over rather than explain.
    >>  ............................................
    pt  Então eles recebem %2$s de volta em três dias e eu posso entregar em vez de explicar.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.accepted/2   [116 chars]
    en  I would have paid for it myself. I am taking your iron because it means they wait three days instead of a fortnight.
    >>  ............................................
    pt  Eu teria pagado do meu bolso. Estou aceitando seu ferro porque significa que eles esperam três dias em vez de duas semanas.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.accepted/3   [118 chars]
    en  Yes. And I will make it heavier this time, which they will complain about, and it will still be there in twenty years.
    >>  ............................................
    pt  Sim. E vou fazer mais pesada desta vez, coisa de que vão reclamar, e ainda vai estar lá em vinte anos.
    >>  ............................................
```


### Button `advise_owning_it` — "Tell them the part that was yours."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.failed_tool.blocked` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.failed_tool.blocked.advise_owning_it` — accepted phrasings: "tell them the part that was yours"; "tell them the part that was yours"; "own the share of it that is yours"
  - the message must contain one of: `yours`, `own`, `share`
  - scored words: `yours`(1.8), `own`(1.8), `share`(1.8), `tell`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.advise_owning_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.advise_owning_it   [34 chars]
    en  Tell them the part that was yours.
    >>  ............................................
    pt  Diga a eles a parte que foi sua.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.toolsmith.edges`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.failed_tool"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.resolved
WHO    VILLAGER — what the player reads after pressing "Tell them the part that was yours."
       spoken on: conversations.scene.work.toolsmith.failed_tool.blocked.respond, button `advise_owning_it`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.blocked.resolved`: the villager accepts. Subject `work.toolsmith.edges`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.resolved/1   [120 chars]
    en  The part, not the whole. You are right that those are different, and I have been about to do the wrong one out of guilt.
    >>  ............................................
    pt  A parte, não o todo. Você tem razão que são coisas diferentes, e eu estava prestes a fazer a errada por culpa.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.resolved/2   [123 chars]
    en  Yes. Naming my half lets them own theirs without me having to say it, and that is the only way that conversation goes well.
    >>  ............................................
    pt  Sim. Nomear a minha metade permite que assumam a deles sem eu precisar dizer, e é o único jeito de essa conversa correr bem.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.resolved/3   [104 chars]
    en  I will do it in the doorway, standing, in under a minute. Long apologies are for the person apologising.
    >>  ............................................
    pt  Vou fazer na porta, de pé, em menos de um minuto. Desculpas longas são para quem se desculpa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.failed_tool.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.failed_tool.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.toolsmith.failed_tool.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.failed_tool.succeeded` — e.g. "%2$s is remade and handed over, and I told them which half was mine, and they told me the other half unprompted."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.failed_tool.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond   [11 chars]
    en  That break.
    >>  ............................................
    pt  Aquela quebra.
    >>  ............................................
```


### Button `ask_what_she_changed` — "What do you do differently now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.failed_tool.succeeded` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.failed_tool.succeeded.ask_what_she_changed` — accepted phrasings: "what do you do differently now"; "what do you do differently now"; "what changed in how you work"
  - the message must contain one of: `differently`, `changed`
  - scored words: `differently`(1.8), `changed`(1.8), `now`(0.8), `work`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond.ask_what_she_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond.ask_what_she_changed   [31 chars]
    en  What do you do differently now?
    >>  ............................................
    pt  O que você faz diferente agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.toolsmith.edges`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.toolsmith.failed_tool"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What do you do differently now?"
       spoken on: conversations.scene.work.toolsmith.failed_tool.succeeded.respond, button `ask_what_she_changed`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.failed_tool.succeeded.explained`: the villager explains. Subject `work.toolsmith.edges`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.explained/1   [126 chars]
    en  One extra heat and a look along the edge in daylight. It costs a quarter of an hour a tool and it would have caught that fold.
    >>  ............................................
    pt  Um aquecimento a mais e uma olhada ao longo do fio na luz do dia. Custa um quarto de hora por ferramenta e teria pego aquela dobra.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.explained/2   [134 chars]
    en  I ask what they will use it for and I believe the answer less. People say felling and mean prying, and the metal knows the difference.
    >>  ............................................
    pt  Pergunto para que vão usar e acredito menos na resposta. As pessoas dizem derrubar e querem dizer alavancar, e o metal sabe a diferença.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.explained/3   [129 chars]
    en  I keep the broken pieces. There are eleven on the shelf now. Visitors think it is morbid; it is the only textbook this trade has.
    >>  ............................................
    pt  Guardo os pedaços quebrados. São onze na prateleira agora. Visitantes acham mórbido; é o único livro-texto que este ofício tem.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.failed_tool.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.failed_tool.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.failed_tool.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.failed_tool.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.toolsmith.followup`

**Reached from 10 route(s):** `conversations.scene.work.toolsmith.cheap_customer.active.respond` / `ask_what_she_does`; `conversations.scene.work.toolsmith.cheap_customer.active.respond` / `back_the_honest_price`; `conversations.scene.work.toolsmith.cheap_customer.succeeded.respond` / `note_the_arrangement`; `conversations.scene.work.toolsmith.failed_tool.blocked.respond` / `ask_whose_fault`; `conversations.scene.work.toolsmith.failed_tool.blocked.respond` / `offer_iron`; `conversations.scene.work.toolsmith.failed_tool.blocked.respond` / `advise_owning_it`; `conversations.scene.work.toolsmith.failed_tool.succeeded.respond` / `ask_what_she_changed`; `conversations.scene.work.toolsmith.heirloom_repair.active.respond` / `ask_what_they_want`; `conversations.scene.work.toolsmith.heirloom_repair.active.respond` / `advise_doing_both`; `conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond` / `note_the_understanding`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.cheap_customer.active.explained` — e.g. "A repair instead of a new one. Half the price, honestly earned, and it buys them a year to find the rest."
- `conversations.scene.work.toolsmith.cheap_customer.active.steadied` — e.g. "That is the rule and it is easy to say in a warm room. It is harder in a doorway with somebody who needs a scythe by Thursday."
- `conversations.scene.work.toolsmith.cheap_customer.succeeded.acknowledged` — e.g. "It is, and it took me eleven years to invent something my grandmother did as a matter of course."
- `conversations.scene.work.toolsmith.failed_tool.blocked.accepted` — e.g. "Then they get %2$s back inside three days and I get to hand it over rather than explain."
- `conversations.scene.work.toolsmith.failed_tool.blocked.explained` — e.g. "%2$s, which means both, which is the answer nobody wants because you cannot apologise for half a thing."
- `conversations.scene.work.toolsmith.failed_tool.blocked.resolved` — e.g. "The part, not the whole. You are right that those are different, and I have been about to do the wrong one out of guilt."
- `conversations.scene.work.toolsmith.failed_tool.succeeded.explained` — e.g. "One extra heat and a look along the edge in daylight. It costs a quarter of an hour a tool and it would have caught that fold."
- `conversations.scene.work.toolsmith.heirloom_repair.active.accepted` — e.g. "One to work with and one to keep. I had been treating it as a choice, and it was never a choice, it was two objects."
- `conversations.scene.work.toolsmith.heirloom_repair.active.explained` — e.g. "For it to still be his. That is what they are asking and no new blade does that, however much better it cuts."
- `conversations.scene.work.toolsmith.heirloom_repair.succeeded.acknowledged` — e.g. "Somebody told me. I want that on the record, because I would have handed back one object and thought I had done well."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.followup   [13 chars]
    en  Was that all?
    >>  ............................................
    pt  Era só isso?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a tool that fails?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.toolsmith.*` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.followup.ask_more` — accepted phrasings: "whats the hardest part of a tool that fails"; "what is the hardest part of a tool that fails"; "hardest thing about a tool failing"
  - the message must contain one of: `hardest`, `tool`
  - scored words: `hardest`(1.8), `tool`(1.8), `whats`(0.8), `part`(0.8), `fails`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.followup.ask_more   [45 chars]
    en  What's the hardest part of a tool that fails?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma ferramenta que falha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a tool that fails?"
       spoken on: conversations.scene.work.toolsmith.followup, button `ask_more`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.hard`: the villager explains. Subject `work.toolsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.toolsmith.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.toolsmith.hard/1   [73 chars]
    en  Hoes, in spring. Everyone forgets what a season of stone does to an edge.
    >>  ............................................
    pt  Enxadas, na primavera. Todo mundo esquece o que uma estação de pedra faz com um fio.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.hard/2   [82 chars]
    en  Whatever the farmer bought last, %1$s. He uses them like he's angry at the ground.
    >>  ............................................
    pt  O que o fazendeiro comprou por último, %1$s. Ele usa como se estivesse com raiva do chão.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the grindstone."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.toolsmith.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.followup.leave   [33 chars]
    en  I'll leave you to the grindstone.
    >>  ............................................
    pt  Vou deixar você com o rebolo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the grindstone."
       spoken on: conversations.scene.work.toolsmith.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.toolsmith.heirloom_repair.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.heirloom_repair.active` — e.g. "Somebody has brought me %2$s and asked me to bring it back, and the honest answer is that a new one would be better."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.heirloom_repair.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond   [14 chars]
    en  The old piece.
    >>  ............................................
    pt  A peça antiga.
    >>  ............................................
```


### Button `ask_what_they_want` — "What are they really asking for?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.toolsmith.heirloom_repair.active` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.heirloom_repair.active.ask_what_they_want` — accepted phrasings: "what are they really asking for"; "what are they really asking for"; "what is the real request there"
  - the message must contain one of: `asking`, `request`, `really`
  - scored words: `asking`(1.8), `request`(1.8), `really`(1.8), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.ask_what_they_want
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.heirloom_repair.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.ask_what_they_want   [32 chars]
    en  What are they really asking for?
    >>  ............................................
    pt  O que estão realmente pedindo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.toolsmith.repairs`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.heirloom_repair"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.explained
WHO    VILLAGER — what the player reads after pressing "What are they really asking for?"
       spoken on: conversations.scene.work.toolsmith.heirloom_repair.active.respond, button `ask_what_they_want`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.heirloom_repair.active.explained`: the villager explains. Subject `work.toolsmith.repairs`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.explained/1   [109 chars]
    en  For it to still be his. That is what they are asking and no new blade does that, however much better it cuts.
    >>  ............................................
    pt  Que continue sendo dele. É isso que estão pedindo, e nenhuma lâmina nova faz isso, por melhor que corte.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.explained/2   [130 chars]
    en  They want somebody to take it seriously. Half the repair is me spending two days on a thing everybody else would have thrown away.
    >>  ............................................
    pt  Querem que alguém leve a sério. Metade do conserto é eu passar dois dias numa coisa que qualquer outro teria jogado fora.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.explained/3   [133 chars]
    en  I do not think they know. They will find out when I hand it back, and my job is to make sure that the answer is available either way.
    >>  ............................................
    pt  Acho que não sabem. Vão descobrir quando eu devolver, e o meu trabalho é garantir que a resposta esteja disponível dos dois jeitos.
    >>  ............................................
```


### Button `advise_doing_both` — "Mend it and make a new one too."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.heirloom_repair.active` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.heirloom_repair.active.advise_doing_both` — accepted phrasings: "mend it and make a new one too"; "mend it and make a new one too"; "do both the repair and a replacement"
  - the message must contain one of: `mend`, `both`, `replacement`
  - scored words: `mend`(1.8), `both`(1.8), `replacement`(1.8), `make`(0.8), `new`(0.8), `one`(0.8), `too`(0.8), `repair`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.advise_doing_both
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.heirloom_repair.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.advise_doing_both   [31 chars]
    en  Mend it and make a new one too.
    >>  ............................................
    pt  Conserte e faça uma nova também.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.toolsmith.repairs`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.toolsmith.heirloom_repair"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.accepted
WHO    VILLAGER — what the player reads after pressing "Mend it and make a new one too."
       spoken on: conversations.scene.work.toolsmith.heirloom_repair.active.respond, button `advise_doing_both`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.heirloom_repair.active.accepted`: the villager accepts. Subject `work.toolsmith.repairs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.accepted/1   [116 chars]
    en  One to work with and one to keep. I had been treating it as a choice, and it was never a choice, it was two objects.
    >>  ............................................
    pt  Uma para trabalhar e uma para guardar. Eu vinha tratando como escolha, e nunca foi escolha, eram dois objetos.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.accepted/2   [123 chars]
    en  Yes. And I will charge for the new one and not for the old, because the old one is two days of my time and none of my iron.
    >>  ............................................
    pt  Sim. E vou cobrar pela nova e não pela velha, porque a velha são dois dias do meu tempo e nada do meu ferro.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.accepted/3   [107 chars]
    en  That is the answer. I will hand them both over at once and say nothing, and let them decide which is which.
    >>  ............................................
    pt  É a resposta. Vou entregar as duas de uma vez e não dizer nada, e deixar que decidam qual é qual.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.heirloom_repair.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.heirloom_repair.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.active.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.heirloom_repair.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.toolsmith.heirloom_repair.succeeded` — e.g. "They took both. %2$s is on a wall now and the new one is in the ground, which is exactly right."


```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond   [15 chars]
    en  That old piece.
    >>  ............................................
    pt  Aquela peça antiga.
    >>  ............................................
```


### Button `note_the_understanding` — "You understood what they needed."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.toolsmith.heirloom_repair.succeeded` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.toolsmith.heirloom_repair.succeeded.note_the_understanding` — accepted phrasings: "you understood what they needed"; "you understood what they needed"; "you read the request correctly"
  - the message must contain one of: `understood`, `needed`, `read`
  - scored words: `understood`(1.8), `needed`(1.8), `read`(1.8), `request`(0.8), `correctly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond.note_the_understanding
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond.note_the_understanding   [32 chars]
    en  You understood what they needed.
    >>  ............................................
    pt  Você entendeu o que precisavam.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.toolsmith.repairs`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.toolsmith.heirloom_repair"}
- Then opens: `conversations.scene.work.toolsmith.followup`
- …where the player's next choices will be: "What's the hardest part of a tool that fails?" | "I'll leave you to the grindstone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You understood what they needed."
       spoken on: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond, button `note_the_understanding`
       leaves the player on: conversations.scene.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.heirloom_repair.succeeded.acknowledged`: the villager accepts. Subject `work.toolsmith.repairs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.acknowledged/1   [117 chars]
    en  Somebody told me. I want that on the record, because I would have handed back one object and thought I had done well.
    >>  ............................................
    pt  Alguém me disse. Quero isso registrado, porque eu teria devolvido um objeto só e achado que tinha me saído bem.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.acknowledged/2   [126 chars]
    en  Thank you. Most of this trade is metal and about a tenth of it is knowing what a thing is for, and the tenth is the hard part.
    >>  ............................................
    pt  Obrigada. Quase todo este ofício é metal e uns dez por cento é saber para que serve uma coisa, e os dez por cento são a parte difícil.
    >>  ............................................
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.acknowledged/3   [108 chars]
    en  It is the only repair I have been thanked for twice. Once for the blade and once, I think, for the two days.
    >>  ............................................
    pt  É o único conserto pelo qual me agradeceram duas vezes. Uma pela lâmina e uma, acho, pelos dois dias.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the forge."

*stance family `exit` · tone `plain` · answers the beat(s) `work.toolsmith.heirloom_repair.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the forge.
    >>  ............................................
    pt  Vou deixar você voltar à forja.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the forge."
       spoken on: conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.craft` — e.g. "A good handle is the whole tool. The head is the part people look at and the least of the problem."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.craft.respond   [23 chars]
    en  That's the knack of it.
    >>  ............................................
    pt  É esse o jeito.
    >>  ............................................
```


### Button `ask_wear` — "What can you tell from wear?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.craft` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.craft.ask_wear` — accepted phrasings: "what can you tell from wear"
  - the message must contain one of: `wear`, `read`
  - scored words: `wear`(1.5), `tell`(0.8), `read`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.craft.respond.ask_wear
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.craft.respond.ask_wear   [28 chars]
    en  What can you tell from wear?
    >>  ............................................
    pt  O que você descobre pelo desgaste?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.craft.ask_wear`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.craft.ask_wear
WHO    VILLAGER — what the player reads after pressing "What can you tell from wear?"
       spoken on: conversations.topic.work.toolsmith.craft.respond, button `ask_wear`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.craft.ask_wear`: the villager explains. Subject `work.toolsmith.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.craft.ask_wear/1   [93 chars]
    en  Which hand leads, whether they hurry, and whether they've been taught or worked it out alone.
    >>  ............................................
    pt  Qual mão manda, se tem pressa, e se aprendeu com alguém ou descobriu sozinho.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.craft.ask_wear/2   [82 chars]
    en  The miller's shovel told me his back had gone before he did, %1$s. I said nothing.
    >>  ............................................
    pt  A pá do moleiro me disse que as costas dele tinham ido antes dele dizer, %1$s. Eu não falei nada.
    >>  ............................................
```


### Button `admire` — "That's a strange and useful thing to be able to do."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.craft` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.craft.admire` — accepted phrasings: "that's a strange and useful thing to be able to do"
  - the message must contain one of: `strange`, `useful`, `able`
  - scored words: `strange`(1.2), `useful`(1.2), `able`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.craft.respond.admire   [51 chars]
    en  That's a strange and useful thing to be able to do.
    >>  ............................................
    pt  É uma coisa estranha e útil de se saber fazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.toolsmith.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.craft.admire
WHO    VILLAGER — what the player reads after pressing "That's a strange and useful thing to be able to do."
       spoken on: conversations.topic.work.toolsmith.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.craft.admire`: the villager accepts. Subject `work.toolsmith.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.craft.admire/1   [76 chars]
    en  It's twenty years of the same nine tools coming back. Anyone would learn it.
    >>  ............................................
    pt  São vinte anos das mesmas nove ferramentas voltando. Qualquer um aprenderia.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.craft.admire/2   [71 chars]
    en  Useful, aye. Strange, also aye. Both have been said to me before, %1$s.
    >>  ............................................
    pt  Útil, sim. Estranho, também sim. Já me disseram os dois, %1$s.
    >>  ............................................
```


### Button `ask_handle` — "What makes a handle good?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.craft` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.craft.ask_handle` — accepted phrasings: "what makes a handle good"
  - the message must contain one of: `handle`, `haft`
  - scored words: `handle`(1.5), `good`(0.5), `haft`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.craft.respond.ask_handle
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.craft.respond.ask_handle   [25 chars]
    en  What makes a handle good?
    >>  ............................................
    pt  O que faz um cabo ser bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.craft.ask_handle`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.craft.ask_handle
WHO    VILLAGER — what the player reads after pressing "What makes a handle good?"
       spoken on: conversations.topic.work.toolsmith.craft.respond, button `ask_handle`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.craft.ask_handle`: the villager explains. Subject `work.toolsmith.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.craft.ask_handle/1   [96 chars]
    en  Grain running the right way and a shape that fits the one hand it's for. Not any hand. That one.
    >>  ............................................
    pt  Fibra na direção certa e formato que sirva à única mão pra qual é. Não qualquer mão. Aquela.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.craft.ask_handle/2   [89 chars]
    en  Ash, seasoned two years, and no varnish. Varnish is a lie you can feel through your palm.
    >>  ............................................
    pt  Freixo, curado dois anos, e sem verniz. Verniz é uma mentira que se sente na palma.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.craft.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.followup`

**Reached from 20 route(s):** `conversations.scene.work.toolsmith.followup` / `ask_more`; `conversations.topic.work.toolsmith.craft.respond` / `ask_wear`; `conversations.topic.work.toolsmith.craft.respond` / `admire`; `conversations.topic.work.toolsmith.craft.respond` / `ask_handle`; `conversations.topic.work.toolsmith.future.respond` / `ask_angle`; `conversations.topic.work.toolsmith.future.respond` / `encourage`; `conversations.topic.work.toolsmith.future.respond` / `ask_sets`; `conversations.topic.work.toolsmith.respond` / `ask_hard`; `conversations.topic.work.toolsmith.respond` / `value`; `conversations.topic.work.toolsmith.respond` / `challenge`; `conversations.topic.work.toolsmith.respond` / `challenge`; `conversations.topic.work.toolsmith.risk.respond` / `ask_finger` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.challenge.landed` — e.g. "It is. Dull things are what everything else stands on."
- `conversations.work.prof.toolsmith.challenge.stung` — e.g. "...Try a week with a bad hoe and come tell me about dull."
- `conversations.work.prof.toolsmith.craft.admire` — e.g. "It's twenty years of the same nine tools coming back. Anyone would learn it."
- `conversations.work.prof.toolsmith.craft.ask_handle` — e.g. "Grain running the right way and a shape that fits the one hand it's for. Not any hand. That one."
- `conversations.work.prof.toolsmith.craft.ask_wear` — e.g. "Which hand leads, whether they hurry, and whether they've been taught or worked it out alone."
- `conversations.work.prof.toolsmith.future.ask_angle` — e.g. "It's the angle a smith likes to forge, not the angle a back likes to swing. No one has thought to ask a back."
- `conversations.work.prof.toolsmith.future.ask_sets` — e.g. "Fourteen children. Four tools each. It's a winter's work and I've costed it twice."
- `conversations.work.prof.toolsmith.future.encourage` — e.g. "...One. Huh. I've been asking for a month when I needed an afternoon. That is embarrassing."
- `conversations.work.prof.toolsmith.hard` — e.g. "Hoes, in spring. Everyone forgets what a season of stone does to an edge."
- `conversations.work.prof.toolsmith.risk.ask_finger` — e.g. "No. I checked for a month. It was a rotten haft he'd fitted himself, and I still checked for a month."
- `conversations.work.prof.toolsmith.risk.ask_prevent` — e.g. "No. I can make spares cheap enough that they don't bother, and I do, and I lose money on it."
- `conversations.work.prof.toolsmith.risk.sympathise` — e.g. "...I did. That's the trade. You check even when you know, because knowing isn't the same as being sure."
- `conversations.work.prof.toolsmith.task.ask_farm` — e.g. "Hitting stone and pretending they didn't. Nine times. In one season."
- `conversations.work.prof.toolsmith.task.ask_free` — e.g. "He built my workshop door. We are eleven years into a debt neither of us is settling."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.followup   [24 chars]
    en  That's the queue and me.
    >>  ............................................
    pt  É a fila e eu.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.challenge.landed`, `work.toolsmith.challenge.stung`, `work.toolsmith.craft.admire`, `work.toolsmith.craft.ask_handle`, `work.toolsmith.craft.ask_wear`, `work.toolsmith.future.ask_angle`, `work.toolsmith.future.ask_sets`, `work.toolsmith.future.encourage`, `work.toolsmith.hard`, `work.toolsmith.risk.ask_finger`, `work.toolsmith.risk.ask_prevent`, `work.toolsmith.risk.sympathise`, `work.toolsmith.task.ask_farm`, `work.toolsmith.task.ask_free`, `work.toolsmith.task.offer_hands`, `work.toolsmith.value`, `work.toolsmith.village.ask_children`, `work.toolsmith.village.ask_queue`, `work.toolsmith.village.say_thanks` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.toolsmith.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `noticed`, `quiet`
  - scored words: `thought`(1.2), `noticed`(1.2), `quiet`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.toolsmith.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.toolsmith.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.toolsmith.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.toolsmith.thanks`: the villager accepts. Subject `work.toolsmith.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.thanks/1   [79 chars]
    en  Few do. A tool is only noticed when it fails, which is a hard way to be judged.
    >>  ............................................
    pt  Poucos pensam. Uma ferramenta só é notada quando falha, o que é um jeito duro de ser julgado.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.thanks/2   [79 chars]
    en  It's the quiet trades that hold a village up, %1$s. Ask the mason, he'll agree.
    >>  ............................................
    pt  São os ofícios silenciosos que seguram um vilarejo, %1$s. Pergunte ao pedreiro, ele concorda.
    >>  ............................................
```


### Button `ask_more` — "What would you make if nobody was waiting?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.challenge.landed`, `work.toolsmith.challenge.stung`, `work.toolsmith.craft.admire`, `work.toolsmith.craft.ask_handle`, `work.toolsmith.craft.ask_wear`, `work.toolsmith.future.ask_angle`, `work.toolsmith.future.ask_sets`, `work.toolsmith.future.encourage`, `work.toolsmith.hard`, `work.toolsmith.risk.ask_finger`, `work.toolsmith.risk.ask_prevent`, `work.toolsmith.risk.sympathise`, `work.toolsmith.task.ask_farm`, `work.toolsmith.task.ask_free`, `work.toolsmith.task.offer_hands`, `work.toolsmith.value`, `work.toolsmith.village.ask_children`, `work.toolsmith.village.ask_queue`, `work.toolsmith.village.say_thanks` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.toolsmith.more` — accepted phrasings: "what would you make if nobody was waiting"
  - the message must contain one of: `make`, `waiting`, `nobody`
  - scored words: `make`(1.0), `waiting`(1.5), `nobody`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.followup.ask_more   [42 chars]
    en  What would you make if nobody was waiting?
    >>  ............................................
    pt  O que você faria se ninguém estivesse esperando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.toolsmith.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.more
WHO    VILLAGER — what the player reads after pressing "What would you make if nobody was waiting?"
       spoken on: conversations.topic.work.toolsmith.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.toolsmith.more`: the villager discloses. Subject `work.toolsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.more/1   [81 chars]
    en  A better handle. I've an idea about the angle that everyone tells me is nonsense.
    >>  ............................................
    pt  Um cabo melhor. Tenho uma ideia sobre o ângulo que todo mundo diz que é bobagem.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.more/2   [83 chars]
    en  Something for the children. Small tools, properly made. They'd learn twice as fast.
    >>  ............................................
    pt  Algo pras crianças. Ferramentas pequenas, bem feitas. Aprenderiam duas vezes mais rápido.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Nine hoes a season come back blunted and somebody's back is doing that.
    >>  ............................................
    pt  Um cabo melhor. Nove enxadas por estação voltam cegas e as costas de alguém estão fazendo isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. I made a hoe once that cost a man a finger, and I check everything now.
    >>  ............................................
    pt  Ferramentas pras crianças. Uma vez eu fiz uma enxada que custou um dedo a um homem, e agora eu confiro tudo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Two degrees. It'll take an afternoon to prove and I've been asking for a month.
    >>  ............................................
    pt  Um cabo melhor. Dois graus. Leva uma tarde pra provar e eu venho pedindo um mês.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets for the children. A winter's work, and winters come round reliably.
    >>  ............................................
    pt  Jogos pras crianças. Trabalho de um inverno, e invernos voltam com confiabilidade.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. I've an idea about the angle that everyone tells me is nonsense.
    >>  ............................................
    pt  Um cabo melhor. Tenho uma ideia sobre o ângulo que todos dizem ser bobagem.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children, properly made, a set at a time. Fourteen children, four tools each.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças, bem feitas, um jogo por vez. Catorze crianças, quatro ferramentas cada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. I've an idea about the angle that everyone tells me is nonsense.
    >>  ............................................
    pt  Um cabo melhor. Tenho uma ideia sobre o ângulo que todos dizem ser bobagem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children, properly made, a set at a time. Fourteen children, four tools each.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças, bem feitas, um jogo por vez. Catorze crianças, quatro ferramentas cada.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Bring me your worn one and I'll show you exactly what I mean by the angle.
    >>  ............................................
    pt  Um cabo melhor. Traga o seu gasto e eu mostro exatamente o que eu quero dizer com o ângulo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. They get their first real one from me and I don't charge for those.
    >>  ............................................
    pt  Ferramentas pras crianças. Elas ganham a primeira de verdade comigo e essas eu não cobro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Bring me your worn one and I'll show you exactly what I mean by the angle.
    >>  ............................................
    pt  Um cabo melhor. Traga o seu gasto e eu mostro exatamente o que eu quero dizer com o ângulo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. They get their first real one from me and I don't charge for those.
    >>  ............................................
    pt  Ferramentas pras crianças. Elas ganham a primeira de verdade comigo e essas eu não cobro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Bring me your worn one and I'll show you exactly what I mean by the angle.
    >>  ............................................
    pt  Um cabo melhor. Traga o seu gasto e eu mostro exatamente o que eu quero dizer com o ângulo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. They get their first real one from me and I don't charge for those.
    >>  ............................................
    pt  Ferramentas pras crianças. Elas ganham a primeira de verdade comigo e essas eu não cobro.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Nine hoes a season come back blunted and somebody's back is doing that.
    >>  ............................................
    pt  Um cabo melhor. Nove enxadas por estação voltam cegas e as costas de alguém estão fazendo isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. I made a hoe once that cost a man a finger, and I check everything now.
    >>  ............................................
    pt  Ferramentas pras crianças. Uma vez eu fiz uma enxada que custou um dedo a um homem, e agora eu confiro tudo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. I've an idea about the angle that everyone tells me is nonsense.
    >>  ............................................
    pt  Um cabo melhor. Tenho uma ideia sobre o ângulo que todos dizem ser bobagem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children, properly made, a set at a time. Fourteen children, four tools each.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças, bem feitas, um jogo por vez. Catorze crianças, quatro ferramentas cada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. I've an idea about the angle that everyone tells me is nonsense.
    >>  ............................................
    pt  Um cabo melhor. Tenho uma ideia sobre o ângulo que todos dizem ser bobagem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children, properly made, a set at a time. Fourteen children, four tools each.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças, bem feitas, um jogo por vez. Catorze crianças, quatro ferramentas cada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. It's the angle a smith likes to forge, not the angle a back likes to swing.
    >>  ............................................
    pt  Um cabo melhor. É o ângulo que o ferreiro gosta de forjar, não o que as costas gostam de balançar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children. Sharp ones. A blunt tool teaches force and a sharp one teaches care.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças. Afiadas. Ferramenta cega ensina força e afiada ensina cuidado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Two degrees. It'll take an afternoon to prove and I've been asking for a month.
    >>  ............................................
    pt  Um cabo melhor. Dois graus. Leva uma tarde pra provar e eu venho pedindo um mês.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets for the children. A winter's work, and winters come round reliably.
    >>  ............................................
    pt  Jogos pras crianças. Trabalho de um inverno, e invernos voltam com confiabilidade.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. It's the angle a smith likes to forge, not the angle a back likes to swing.
    >>  ............................................
    pt  Um cabo melhor. É o ângulo que o ferreiro gosta de forjar, não o que as costas gostam de balançar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children. Sharp ones. A blunt tool teaches force and a sharp one teaches care.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças. Afiadas. Ferramenta cega ensina força e afiada ensina cuidado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Two degrees. It'll take an afternoon to prove and I've been asking for a month.
    >>  ............................................
    pt  Um cabo melhor. Dois graus. Leva uma tarde pra provar e eu venho pedindo um mês.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets for the children. A winter's work, and winters come round reliably.
    >>  ............................................
    pt  Jogos pras crianças. Trabalho de um inverno, e invernos voltam com confiabilidade.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle! Two degrees. Everyone says it's nonsense. Nine blunt hoes a season say otherwise.
    >>  ............................................
    pt  Um cabo melhor! Dois graus. Todos dizem que é bobagem. Nove enxadas cegas por estação dizem o contrário.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets of small tools for the children. Fourteen children, four tools each — I've costed it twice.
    >>  ............................................
    pt  Jogos de ferramentas pequenas pras crianças. Catorze crianças, quatro cada — eu orcei duas vezes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle! Two degrees. Everyone says it's nonsense. Nine blunt hoes a season say otherwise.
    >>  ............................................
    pt  Um cabo melhor! Dois graus. Todos dizem que é bobagem. Nove enxadas cegas por estação dizem o contrário.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets of small tools for the children. Fourteen children, four tools each — I've costed it twice.
    >>  ............................................
    pt  Jogos de ferramentas pequenas pras crianças. Catorze crianças, quatro cada — eu orcei duas vezes.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Two degrees. It'll take an afternoon to prove and I've been asking for a month.
    >>  ............................................
    pt  Um cabo melhor. Dois graus. Leva uma tarde pra provar e eu venho pedindo um mês.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets for the children. A winter's work, and winters come round reliably.
    >>  ............................................
    pt  Jogos pras crianças. Trabalho de um inverno, e invernos voltam com confiabilidade.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. Nine hoes a season come back blunted and somebody's back is doing that.
    >>  ............................................
    pt  Um cabo melhor. Nove enxadas por estação voltam cegas e as costas de alguém estão fazendo isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith.more/2
    en  Tools for the children. I made a hoe once that cost a man a finger, and I check everything now.
    >>  ............................................
    pt  Ferramentas pras crianças. Uma vez eu fiz uma enxada que custou um dedo a um homem, e agora eu confiro tudo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle. It's the angle a smith likes to forge, not the angle a back likes to swing.
    >>  ............................................
    pt  Um cabo melhor. É o ângulo que o ferreiro gosta de forjar, não o que as costas gostam de balançar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith.more/2
    en  Small tools for the children. Sharp ones. A blunt tool teaches force and a sharp one teaches care.
    >>  ............................................
    pt  Ferramentas pequenas pras crianças. Afiadas. Ferramenta cega ensina força e afiada ensina cuidado.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle! Two degrees. Everyone says it's nonsense. Nine blunt hoes a season say otherwise.
    >>  ............................................
    pt  Um cabo melhor! Dois graus. Todos dizem que é bobagem. Nove enxadas cegas por estação dizem o contrário.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets of small tools for the children. Fourteen children, four tools each — I've costed it twice.
    >>  ............................................
    pt  Jogos de ferramentas pequenas pras crianças. Catorze crianças, quatro cada — eu orcei duas vezes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith.more/1
    en  A better handle! Two degrees. Everyone says it's nonsense. Nine blunt hoes a season say otherwise.
    >>  ............................................
    pt  Um cabo melhor! Dois graus. Todos dizem que é bobagem. Nove enxadas cegas por estação dizem o contrário.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith.more/2
    en  Sets of small tools for the children. Fourteen children, four tools each — I've costed it twice.
    >>  ............................................
    pt  Jogos de ferramentas pequenas pras crianças. Catorze crianças, quatro cada — eu orcei duas vezes.
    >>  ............................................
```

</details>


### Button `leave` — "Sharp edges."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.challenge.landed`, `work.toolsmith.challenge.stung`, `work.toolsmith.craft.admire`, `work.toolsmith.craft.ask_handle`, `work.toolsmith.craft.ask_wear`, `work.toolsmith.future.ask_angle`, `work.toolsmith.future.ask_sets`, `work.toolsmith.future.encourage`, `work.toolsmith.hard`, `work.toolsmith.risk.ask_finger`, `work.toolsmith.risk.ask_prevent`, `work.toolsmith.risk.sympathise`, `work.toolsmith.task.ask_farm`, `work.toolsmith.task.ask_free`, `work.toolsmith.task.offer_hands`, `work.toolsmith.value`, `work.toolsmith.village.ask_children`, `work.toolsmith.village.ask_queue`, `work.toolsmith.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.followup.leave   [12 chars]
    en  Sharp edges.
    >>  ............................................
    pt  Fios afiados.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "Sharp edges."
       spoken on: conversations.topic.work.toolsmith.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.future` — e.g. "I've an idea about the angle of a hoe head that everyone tells me is nonsense. I'd like a month to prove it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.future.respond   [29 chars]
    en  That's what I'd rather be at.
    >>  ............................................
    pt  É no que eu preferia estar.
    >>  ............................................
```


### Button `ask_angle` — "What's wrong with the angle now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.future` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.future.ask_angle` — accepted phrasings: "what's wrong with the angle now"
  - the message must contain one of: `angle`, `design`
  - scored words: `angle`(1.5), `wrong`(0.8), `design`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.future.respond.ask_angle
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.future.respond.ask_angle   [32 chars]
    en  What's wrong with the angle now?
    >>  ............................................
    pt  O que tem de errado com o ângulo agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.future.ask_angle`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.future.ask_angle
WHO    VILLAGER — what the player reads after pressing "What's wrong with the angle now?"
       spoken on: conversations.topic.work.toolsmith.future.respond, button `ask_angle`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.future.ask_angle`: the villager explains. Subject `work.toolsmith.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.future.ask_angle/1   [109 chars]
    en  It's the angle a smith likes to forge, not the angle a back likes to swing. No one has thought to ask a back.
    >>  ............................................
    pt  É o ângulo que o ferreiro gosta de forjar, não o que as costas gostam de balançar. Nunca pensaram em perguntar às costas.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.future.ask_angle/2   [77 chars]
    en  Two degrees. It sounds like nothing. Nine hoes a season says otherwise, %1$s.
    >>  ............................................
    pt  Dois graus. Parece nada. Nove enxadas por estação dizem o contrário, %1$s.
    >>  ............................................
```


### Button `encourage` — "Prove it on one. That's not a month."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.future` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.future.encourage` — accepted phrasings: "prove it on one. that's not a month"
  - the message must contain one of: `prove`, `try`
  - scored words: `prove`(1.5), `one`(0.6), `try`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.future.respond.encourage   [36 chars]
    en  Prove it on one. That's not a month.
    >>  ............................................
    pt  Prove numa. Isso não é um mês.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.toolsmith.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.future.encourage
WHO    VILLAGER — what the player reads after pressing "Prove it on one. That's not a month."
       spoken on: conversations.topic.work.toolsmith.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.future.encourage`: the villager accepts. Subject `work.toolsmith.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.future.encourage/1   [91 chars]
    en  ...One. Huh. I've been asking for a month when I needed an afternoon. That is embarrassing.
    >>  ............................................
    pt  ...Uma. Huh. Venho pedindo um mês quando precisava de uma tarde. Que vergonha.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.future.encourage/2   [76 chars]
    en  And give it to the farm that blunts nine a season. Now that is a test, %1$s.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. A month was easier to be refused for than an afternoon would be.
    >>  ............................................
    pt  ...Uma. Um mês era mais fácil de ser recusado do que uma tarde seria.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. If it fails there, it fails in public.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Se falhar lá, falha em público.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Thirty years of asking for months when an afternoon would have done.
    >>  ............................................
    pt  ...Uma. Trinta anos pedindo meses quando uma tarde teria bastado.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's how you settle an argument.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É assim que se encerra uma discussão.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Huh. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma. Hm. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  confident.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Huh. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma. Hm. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One, %1$s. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma, %1$s. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's a test worth running.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É um teste que vale fazer.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One, %1$s. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma, %1$s. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's a test worth running.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É um teste que vale fazer.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One, %1$s. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma, %1$s. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's a test worth running.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É um teste que vale fazer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. A month was easier to be refused for than an afternoon would be.
    >>  ............................................
    pt  ...Uma. Um mês era mais fácil de ser recusado do que uma tarde seria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. If it fails there, it fails in public.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Se falhar lá, falha em público.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Huh. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma. Hm. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Huh. I've been asking for a month when I needed an afternoon.
    >>  ............................................
    pt  ...Uma. Hm. Venho pedindo um mês quando eu precisava de uma tarde.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. An afternoon, not a month.
    >>  ............................................
    pt  ...Uma. Uma tarde, não um mês.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  Give it to the farm that blunts nine a season.
    >>  ............................................
    pt  Dê pra fazenda que cega nove por estação.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Thirty years of asking for months when an afternoon would have done.
    >>  ............................................
    pt  ...Uma. Trinta anos pedindo meses quando uma tarde teria bastado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's how you settle an argument.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É assim que se encerra uma discussão.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. An afternoon, not a month.
    >>  ............................................
    pt  ...Uma. Uma tarde, não um mês.
    >>  ............................................
  odd.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  Give it to the farm that blunts nine a season.
    >>  ............................................
    pt  Dê pra fazenda que cega nove por estação.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Thirty years of asking for months when an afternoon would have done.
    >>  ............................................
    pt  ...Uma. Trinta anos pedindo meses quando uma tarde teria bastado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's how you settle an argument.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É assim que se encerra uma discussão.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One! I've been asking for a month when what I needed was an afternoon.
    >>  ............................................
    pt  ...Uma! Venho pedindo um mês quando o que eu precisava era uma tarde.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a proper test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste de verdade.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One! I've been asking for a month when what I needed was an afternoon.
    >>  ............................................
    pt  ...Uma! Venho pedindo um mês quando o que eu precisava era uma tarde.
    >>  ............................................
  playful.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a proper test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste de verdade.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. Thirty years of asking for months when an afternoon would have done.
    >>  ............................................
    pt  ...Uma. Trinta anos pedindo meses quando uma tarde teria bastado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. That's how you settle an argument.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. É assim que se encerra uma discussão.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. A month was easier to be refused for than an afternoon would be.
    >>  ............................................
    pt  ...Uma. Um mês era mais fácil de ser recusado do que uma tarde seria.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. If it fails there, it fails in public.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Se falhar lá, falha em público.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One. An afternoon, not a month.
    >>  ............................................
    pt  ...Uma. Uma tarde, não um mês.
    >>  ............................................
  shy.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  Give it to the farm that blunts nine a season.
    >>  ............................................
    pt  Dê pra fazenda que cega nove por estação.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One! I've been asking for a month when what I needed was an afternoon.
    >>  ............................................
    pt  ...Uma! Venho pedindo um mês quando o que eu precisava era uma tarde.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a proper test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste de verdade.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith.future.encourage/1
    en  ...One! I've been asking for a month when what I needed was an afternoon.
    >>  ............................................
    pt  ...Uma! Venho pedindo um mês quando o que eu precisava era uma tarde.
    >>  ............................................
  witty.dialogue.conversations.work.prof.toolsmith.future.encourage/2
    en  And give it to the farm that blunts nine a season. Now that is a proper test.
    >>  ............................................
    pt  E dar pra fazenda que cega nove por estação. Aí sim é um teste de verdade.
    >>  ............................................
```

</details>


### Button `ask_sets` — "How many sets would that be?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.future` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.future.ask_sets` — accepted phrasings: "how many sets would that be"
  - the message must contain one of: `sets`, `many`
  - scored words: `sets`(1.5), `many`(1.0), `children`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.future.respond.ask_sets
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.future.respond.ask_sets   [28 chars]
    en  How many sets would that be?
    >>  ............................................
    pt  Quantos jogos seriam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.future.ask_sets`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.future.ask_sets
WHO    VILLAGER — what the player reads after pressing "How many sets would that be?"
       spoken on: conversations.topic.work.toolsmith.future.respond, button `ask_sets`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.future.ask_sets`: the villager explains. Subject `work.toolsmith.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.future.ask_sets/1   [82 chars]
    en  Fourteen children. Four tools each. It's a winter's work and I've costed it twice.
    >>  ............................................
    pt  Catorze crianças. Quatro ferramentas cada. É trabalho de um inverno e eu já orcei duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.future.ask_sets/2   [88 chars]
    en  As many as there are children, and there are more every year, which is the good problem.
    >>  ............................................
    pt  Quantas crianças houver, e há mais todo ano, que é o bom problema.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.future.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith` — e.g. "Shovels, hoes, picks — unglamorous things that build everything. Somebody has to make the quiet heroes."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.respond   [46 chars]
    en  That's the bench and its hundred small heroes.
    >>  ............................................
    pt  É a bancada e seus cem pequenos heróis.
    >>  ............................................
```


### Button `ask_hard` — "What comes back most often?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.identity` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.hard` — accepted phrasings: "what comes back most often"
  - the message must contain one of: `repair`, `often`
  - scored words: `back`(0.8), `repair`(1.5), `often`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.respond.ask_hard   [27 chars]
    en  What comes back most often?
    >>  ............................................
    pt  O que volta com mais frequência?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.toolsmith.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.hard
WHO    VILLAGER — what the player reads after pressing "What comes back most often?"
       spoken on: conversations.topic.work.toolsmith.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.hard`: the villager explains. Subject `work.toolsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.followup / ask_more
```

> Written out in full under **`conversations.scene.work.toolsmith.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Nothing in this village gets built without you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.identity` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.value` — accepted phrasings: "nothing in this village gets built without you"
  - the message must contain one of: `built`, `tools`
  - scored words: `built`(1.5), `tools`(1.2), `without`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.respond.value   [47 chars]
    en  Nothing in this village gets built without you.
    >>  ............................................
    pt  Nada neste vilarejo se constrói sem você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.toolsmith.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.toolsmith.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.value
WHO    VILLAGER — what the player reads after pressing "Nothing in this village gets built without you."
       spoken on: conversations.topic.work.toolsmith.respond, button `value`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.value`: the villager accepts. Subject `work.toolsmith.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.value/1   [63 chars]
    en  Not one thing. And nobody's ever put a toolsmith on a monument.
    >>  ............................................
    pt  Nada mesmo. E ninguém nunca colocou um ferramenteiro num monumento.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.value/2   [70 chars]
    en  That's true and it's the sort of true nobody says out loud. Thank you.
    >>  ............................................
    pt  É verdade, e é do tipo de verdade que ninguém diz em voz alta. Obrigado.
    >>  ............................................
```


### Button `challenge` — "It's the dull end of smithing."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.toolsmith.identity` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.challenge` — accepted phrasings: "it's the dull end of smithing"
  - the message must contain one of: `dull`, `boring`
  - scored words: `dull`(1.5), `boring`(1.5), `smithing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.respond.challenge   [30 chars]
    en  It's the dull end of smithing.
    >>  ............................................
    pt  É a parte sem graça da ferraria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.toolsmith.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.toolsmith.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's the dull end of smithing."
       spoken on: conversations.topic.work.toolsmith.respond, button `challenge`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.challenge.landed`: the villager resists. Subject `work.toolsmith.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.challenge.landed/1   [54 chars]
    en  It is. Dull things are what everything else stands on.
    >>  ............................................
    pt  É. Coisas sem graça são o que sustenta todo o resto.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.challenge.landed/2   [70 chars]
    en  The dull end, aye. Also the end that gets used every single day, %1$s.
    >>  ............................................
    pt  A parte sem graça, sim. Também a parte usada todo santo dia, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.toolsmith.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.toolsmith.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's the dull end of smithing."
       spoken on: conversations.topic.work.toolsmith.respond, button `challenge`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.challenge.stung`: the villager resists. Subject `work.toolsmith.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.challenge.stung/1   [57 chars]
    en  ...Try a week with a bad hoe and come tell me about dull.
    >>  ............................................
    pt  ...Passe uma semana com uma enxada ruim e venha me falar de sem graça.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.challenge.stung/2   [81 chars]
    en  Dull. Right. Nobody says that about the weaponsmith, and his work sits in a rack.
    >>  ............................................
    pt  Sem graça. Certo. Ninguém diz isso do armeiro, e o trabalho dele fica na prateleira.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.risk` — e.g. "A tool that fails does it at the worst moment, because that's the moment it was being used hardest."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.risk.respond   [23 chars]
    en  That's what's under it.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_finger` — "Was it the tool's fault?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.risk` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.risk.ask_finger` — accepted phrasings: "was it the tool's fault"
  - the message must contain one of: `fault`, `finger`
  - scored words: `fault`(1.5), `finger`(1.2), `tool`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.risk.respond.ask_finger
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.risk.respond.ask_finger   [24 chars]
    en  Was it the tool's fault?
    >>  ............................................
    pt  Foi culpa da ferramenta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.risk.ask_finger`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.risk.ask_finger
WHO    VILLAGER — what the player reads after pressing "Was it the tool's fault?"
       spoken on: conversations.topic.work.toolsmith.risk.respond, button `ask_finger`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.risk.ask_finger`: the villager explains. Subject `work.toolsmith.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.risk.ask_finger/1   [101 chars]
    en  No. I checked for a month. It was a rotten haft he'd fitted himself, and I still checked for a month.
    >>  ............................................
    pt  Não. Verifiquei por um mês. Era um cabo podre que ele mesmo pôs, e eu ainda verifiquei um mês.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.risk.ask_finger/2   [76 chars]
    en  Half. The wedge had shifted and I should have seen it, %1$s. Half is enough.
    >>  ............................................
    pt  Metade. A cunha tinha saído do lugar e eu devia ter visto, %1$s. Metade basta.
    >>  ............................................
```


### Button `sympathise` — "And you checked for a month anyway."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.risk` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.risk.sympathise` — accepted phrasings: "and you checked for a month anyway"
  - the message must contain one of: `checked`, `month`
  - scored words: `checked`(1.5), `month`(1.2), `anyway`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.risk.respond.sympathise   [35 chars]
    en  And you checked for a month anyway.
    >>  ............................................
    pt  E você verificou um mês mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.toolsmith.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "And you checked for a month anyway."
       spoken on: conversations.topic.work.toolsmith.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.risk.sympathise`: the villager accepts. Subject `work.toolsmith.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.risk.sympathise/1   [103 chars]
    en  ...I did. That's the trade. You check even when you know, because knowing isn't the same as being sure.
    >>  ............................................
    pt  ...Verifiquei. É o ofício. Você confere mesmo sabendo, porque saber não é o mesmo que ter certeza.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.risk.sympathise/2   [57 chars]
    en  Every tool in the shop. Twice. It was a long month, %1$s.
    >>  ............................................
    pt  Toda ferramenta da oficina. Duas vezes. Foi um mês longo, %1$s.
    >>  ............................................
```


### Button `ask_prevent` — "Can you talk them out of fitting their own hafts?"

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.toolsmith.risk` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.risk.ask_prevent` — accepted phrasings: "can you talk them out of fitting their own hafts"
  - the message must contain one of: `prevent`, `hafts`, `spares`
  - scored words: `prevent`(1.5), `hafts`(1.5), `spares`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.risk.respond.ask_prevent
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.risk.respond.ask_prevent   [49 chars]
    en  Can you talk them out of fitting their own hafts?
    >>  ............................................
    pt  Dá pra convencer eles a não pôr os próprios cabos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.toolsmith.risk.ask_prevent`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.risk.ask_prevent`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.risk.ask_prevent
WHO    VILLAGER — what the player reads after pressing "Can you talk them out of fitting their own hafts?"
       spoken on: conversations.topic.work.toolsmith.risk.respond, button `ask_prevent`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.risk.ask_prevent`: the villager accepts. Subject `work.toolsmith.risk`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.risk.ask_prevent/1   [92 chars]
    en  No. I can make spares cheap enough that they don't bother, and I do, and I lose money on it.
    >>  ............................................
    pt  Não. Posso fazer sobressalentes baratos pra não valer a pena, e faço, e perco dinheiro.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.risk.ask_prevent/2   [94 chars]
    en  I keep a barrel of seasoned ash by the door and it is free. Half of them still don't take one.
    >>  ............................................
    pt  Mantenho um barril de freixo curado na porta e é de graça. Metade ainda não pega.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.risk.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.task` — e.g. "Nine hoes, all from the same farm, all blunted the same way. I have opinions about that farm."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.task.respond   [23 chars]
    en  That's the queue today.
    >>  ............................................
    pt  É a fila de hoje.
    >>  ............................................
```


### Button `ask_farm` — "What are they doing to them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.task` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.task.ask_farm` — accepted phrasings: "what are they doing to them"
  - the message must contain one of: `doing`, `farm`, `blunt`
  - scored words: `doing`(1.0), `farm`(1.5), `blunt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.task.respond.ask_farm
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.task.respond.ask_farm   [28 chars]
    en  What are they doing to them?
    >>  ............................................
    pt  O que eles estão fazendo com elas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.task.ask_farm`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.task.ask_farm
WHO    VILLAGER — what the player reads after pressing "What are they doing to them?"
       spoken on: conversations.topic.work.toolsmith.task.respond, button `ask_farm`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.task.ask_farm`: the villager explains. Subject `work.toolsmith.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.task.ask_farm/1   [68 chars]
    en  Hitting stone and pretending they didn't. Nine times. In one season.
    >>  ............................................
    pt  Batendo em pedra e fingindo que não. Nove vezes. Em uma estação.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.task.ask_farm/2   [74 chars]
    en  Working ground that wants a mattock, not a hoe. I've said so. Twice, %1$s.
    >>  ............................................
    pt  Trabalhando terra que quer picareta, não enxada. Já falei. Duas vezes, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I could take the grindstone for a few."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.toolsmith.task` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.task.offer_hands` — accepted phrasings: "i could take the grindstone for a few"
  - the message must contain one of: `grindstone`, `sharpen`
  - scored words: `grindstone`(1.5), `sharpen`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.task.respond.offer_hands   [38 chars]
    en  I could take the grindstone for a few.
    >>  ............................................
    pt  Eu podia pegar a mó pra algumas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.toolsmith.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could take the grindstone for a few."
       spoken on: conversations.topic.work.toolsmith.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.task.offer_hands`: the villager accepts. Subject `work.toolsmith.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.task.offer_hands/1   [83 chars]
    en  ...You could. Same angle every stroke, and stop when I say, not when it looks done.
    >>  ............................................
    pt  ...Podia. Mesmo ângulo em cada passada, e pare quando eu disser, não quando parecer pronto.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.task.offer_hands/2   [61 chars]
    en  Take three. If the edge goes blue you've been too keen, %1$s.
    >>  ............................................
    pt  Pegue três. Se o fio ficar azul você caprichou demais, %1$s.
    >>  ............................................
```


### Button `ask_free` — "You don't charge him?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.task` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.task.ask_free` — accepted phrasings: "you don't charge him"
  - the message must contain one of: `charging`, `free`, `payment`
  - scored words: `charging`(1.5), `free`(1.2), `payment`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.task.respond.ask_free
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.task.respond.ask_free   [21 chars]
    en  You don't charge him?
    >>  ............................................
    pt  Você não cobra dele?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.task.ask_free`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.task.ask_free
WHO    VILLAGER — what the player reads after pressing "You don't charge him?"
       spoken on: conversations.topic.work.toolsmith.task.respond, button `ask_free`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.task.ask_free`: the villager explains. Subject `work.toolsmith.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.task.ask_free/1   [85 chars]
    en  He built my workshop door. We are eleven years into a debt neither of us is settling.
    >>  ............................................
    pt  Ele fez a porta da minha oficina. Estamos onze anos numa dívida que nenhum dos dois quita.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.task.ask_free/2   [74 chars]
    en  For an axe I've re-hafted five times, aye. It's cheaper than the argument.
    >>  ............................................
    pt  Por um machado que eu já recabei cinco vezes, sim. É mais barato que a discussão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.task.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.toolsmith.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.toolsmith.village` — e.g. "Every field, every roof, every wall in this place was made with something off this bench."


```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.toolsmith.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.toolsmith.village.respond   [25 chars]
    en  That's what comes off it.
    >>  ............................................
    pt  É o que sai dela.
    >>  ............................................
```


### Button `ask_children` — "Why small and sharp?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.village` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.village.ask_children` — accepted phrasings: "why small and sharp"
  - the message must contain one of: `sharp`, `children`, `small`
  - scored words: `sharp`(1.5), `children`(1.2), `small`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.village.respond.ask_children
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.village.respond.ask_children   [20 chars]
    en  Why small and sharp?
    >>  ............................................
    pt  Por que pequena e afiada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.village.ask_children`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.village.ask_children
WHO    VILLAGER — what the player reads after pressing "Why small and sharp?"
       spoken on: conversations.topic.work.toolsmith.village.respond, button `ask_children`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.village.ask_children`: the villager explains. Subject `work.toolsmith.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.village.ask_children/1   [95 chars]
    en  Because a blunt tool teaches force and a sharp one teaches care. They learn what they're given.
    >>  ............................................
    pt  Porque ferramenta cega ensina força e afiada ensina cuidado. Elas aprendem o que recebem.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.village.ask_children/2   [81 chars]
    en  Blunt tools cut people. That's a thing nobody believes until you show them, %1$s.
    >>  ............................................
    pt  Ferramenta cega corta gente. É algo em que ninguém acredita até você mostrar, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "That's the sort of thing a place is built out of."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.toolsmith.village` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.village.say_thanks` — accepted phrasings: "that's the sort of thing a place is built out of"
  - the message must contain one of: `built`, `foundation`
  - scored words: `built`(1.5), `foundation`(1.5), `place`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.village.respond.say_thanks   [49 chars]
    en  That's the sort of thing a place is built out of.
    >>  ............................................
    pt  É desse tipo de coisa que um lugar é feito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.toolsmith.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.toolsmith.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "That's the sort of thing a place is built out of."
       spoken on: conversations.topic.work.toolsmith.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.village.say_thanks`: the villager accepts. Subject `work.toolsmith.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.village.say_thanks/1   [76 chars]
    en  ...Literally, in this case. I'd not thought of the phrase working both ways.
    >>  ............................................
    pt  ...Literalmente, neste caso. Não tinha pensado que a frase funciona nos dois sentidos.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.village.say_thanks/2   [84 chars]
    en  Built out of, aye. And nobody puts a toolsmith on a monument, which is fine, mostly.
    >>  ............................................
    pt  Feito disso, sim. E ninguém põe ferramenteiro em monumento, o que está bem, quase sempre.
    >>  ............................................
```


### Button `ask_queue` — "Does the queue ever end?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.toolsmith.village` · offered only once the villager has actually said `work:toolsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.toolsmith.village.ask_queue` — accepted phrasings: "does the queue ever end"
  - the message must contain one of: `queue`, `end`, `quiet`
  - scored words: `queue`(1.5), `end`(1.0), `quiet`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.village.respond.ask_queue
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.village.respond.ask_queue   [24 chars]
    en  Does the queue ever end?
    >>  ............................................
    pt  A fila algum dia acaba?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.toolsmith.village.ask_queue`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.toolsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What would you make if nobody was waiting?" | "Sharp edges."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.village.ask_queue
WHO    VILLAGER — what the player reads after pressing "Does the queue ever end?"
       spoken on: conversations.topic.work.toolsmith.village.respond, button `ask_queue`
       leaves the player on: conversations.topic.work.toolsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.village.ask_queue`: the villager explains. Subject `work.toolsmith.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:toolsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.toolsmith.village.ask_queue/1   [84 chars]
    en  It ended once, for two days, in a hard frost. I did not know what to do with myself.
    >>  ............................................
    pt  Acabou uma vez, por dois dias, numa geada forte. Não soube o que fazer comigo.
    >>  ............................................
  dialogue.conversations.work.prof.toolsmith.village.ask_queue/2   [69 chars]
    en  No. And on the day it does, something has gone wrong somewhere, %1$s.
    >>  ............................................
    pt  Não. E no dia em que acabar, algo deu errado em algum lugar, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the queue."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.toolsmith.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.toolsmith.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.toolsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.toolsmith.village.respond.leave   [35 chars]
    en  I'll let you get back to the queue.
    >>  ............................................
    pt  Vou deixar você voltar pra fila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.toolsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the queue."
       spoken on: conversations.topic.work.toolsmith.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.toolsmith.left`: the villager accepts. Subject `work.toolsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.toolsmith.cheap_customer.active.respond / leave; conversations.scene.work.toolsmith.cheap_customer.succeeded.respond / leave; conversations.scene.work.toolsmith.failed_tool.blocked.respond / leave; conversations.scene.work.toolsmith.failed_tool.succeeded.respond / leave; conversations.scene.work.toolsmith.followup / leave; conversations.scene.work.toolsmith.heirloom_repair.active.respond / leave; conversations.scene.work.toolsmith.heirloom_repair.succeeded.respond / leave; conversations.topic.work.toolsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.toolsmith.cheap_customer.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

