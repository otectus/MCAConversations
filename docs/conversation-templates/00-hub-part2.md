# Hub, greeting and category pages — part 2 of 4

> Continued from [00-hub-part1.md](00-hub-part1.md). Read [README.md](README.md) first.

**Parts of this conversation:** [part 1](00-hub-part1.md) · [part 2](00-hub-part2.md) · [part 3](00-hub-part3.md) · [part 4](00-hub-part4.md)


## Nodes in this file

- [`conversations.cat.personal`](#conversations-cat-personal)
- [`conversations.cat.profession`](#conversations-cat-profession)

---

## `conversations.cat.personal` — continued


**Outcome 9 of 11** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.dreams` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `dreams` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `first` budget `deep`
- Does: remembers `mcaconversations.topic.dreams` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.respond`
- …where the player's next choices will be: "You should chase that." | "Tell me more about it." | "That's a long way from here." | "I don't know what to say to that." | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.first
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.first.to.dreams`: the villager accepts. Subject `dreams`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.first/1   [69 chars]
    en  Don't laugh. I want to see the ocean once. Just once. Smell the salt.
    >>  ............................................
    pt  Não ria. Quero ver o mar uma vez. Só uma. Sentir o cheiro de sal.
    >>  ............................................
  dialogue.conversations.dreams.first/2   [78 chars]
    en  I dream about a bigger garden. And once, flying. The garden feels more likely.
    >>  ............................................
    pt  Sonho com uma horta maior. E, uma vez, que eu voava. A horta parece mais provável.
    >>  ............................................
  dialogue.conversations.dreams.first/3   [81 chars]
    en  Someday I want to build something that outlasts me. A bridge, maybe. Or a family.
    >>  ............................................
    pt  Um dia quero construir algo que dure mais que eu. Uma ponte, quem sabe. Ou uma família.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.dreams.first/1
    en  A year where nothing goes wrong. Just one. I'd like to know what that feels like.
    >>  ............................................
    pt  Um ano em que nada dê errado. Só um. Eu queria saber como é isso.
    >>  ............................................
  anxious.dialogue.conversations.dreams.first/2
    en  To stop bracing. To put something down and trust it'll still be there. That's the whole dream.
    >>  ............................................
    pt  Parar de me preparar pro pior. Pousar uma coisa e confiar que ela vai continuar ali. É esse o sonho todo.
    >>  ............................................
  anxious.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. I've been frightened to say it in case saying it uses it up.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Tive medo de dizer, caso dizer gastasse.
    >>  ............................................
  athletic.dialogue.conversations.dreams.first/1
    en  One dream: outrun a horse. Ten strides, that's all I need. I've already paced the course behind the barn.
    >>  ............................................
    pt  Um sonho: correr mais que um cavalo. Dez passadas, é tudo que eu preciso. Já medi o percurso atrás do celeiro.
    >>  ............................................
  athletic.dialogue.conversations.dreams.first/2
    en  To still be running when I'm grey. Slower, fine. But running, %1$s.
    >>  ............................................
    pt  Ainda estar correndo quando eu estiver grisalho. Mais devagar, tudo bem. Mas correndo, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. It'll keep. Most of the good ones do.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Fica pra depois. Quase todos os bons ficam.
    >>  ............................................
  confident.dialogue.conversations.dreams.first/1
    en  I don't dream, %1$s — I plan. And the plan is a house on the hill everyone said couldn't be built. Watch me.
    >>  ............................................
    pt  Eu não sonho, %1$s — eu planejo. E o plano é uma casa na colina que todos disseram ser impossível construir. Vai por mim.
    >>  ............................................
  confident.dialogue.conversations.dreams.first/2
    en  My name outlasting the wall and the well both. Grand? Yes. Beyond me? Never.
    >>  ............................................
    pt  Meu nome durando mais que o muro e o poço juntos. Grandioso? Sim. Além de mim? Jamais.
    >>  ............................................
  confident.dialogue.conversations.dreams.first/3
    en  I want to stand somewhere the land runs out. That's the whole of it.
    >>  ............................................
    pt  Quero ficar em algum lugar onde a terra acaba. É tudo.
    >>  ............................................
  crabby.dialogue.conversations.dreams.first/1
    en  A quiet house, a locked gate, and nobody knocking on it. Modest, and still out of reach.
    >>  ............................................
    pt  Uma casa quieta, um portão trancado, e ninguém batendo nele. Modesto, e ainda assim fora de alcance.
    >>  ............................................
  crabby.dialogue.conversations.dreams.first/2
    en  To be left alone by people I'd chosen to be left alone by. There's a difference.
    >>  ............................................
    pt  Ser deixado em paz por pessoas que eu escolhi que me deixassem em paz. Tem diferença.
    >>  ............................................
  crabby.dialogue.conversations.dreams.first/3
    en  I want to stand somewhere the land runs out. That's the whole of it.
    >>  ............................................
    pt  Quero ficar em algum lugar onde a terra acaba. É tudo.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.first/1
    en  A house that's always full. Doors open, table crowded, someone always arriving. That's my whole ambition.
    >>  ............................................
    pt  Uma casa sempre cheia. Portas abertas, mesa lotada, sempre alguém chegando. É essa a minha ambição inteira.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.first/2
    en  To know everyone in the valley by name. I'm about a third of the way there, %1$s.
    >>  ............................................
    pt  Conhecer todo mundo do vale pelo nome. Estou mais ou menos a um terço do caminho, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out, and I'd want to tell somebody about it after.
    >>  ............................................
    pt  Quero ficar onde a terra acaba, e queria contar a alguém depois.
    >>  ............................................
  flirty.dialogue.conversations.dreams.first/1
    en  Lately? Someone keeps showing up in them. Lovely nuisance. No, I won't say who.
    >>  ............................................
    pt  Ultimamente? Tem alguém que insiste em aparecer neles. Um estorvo adorável. Não, não vou dizer quem.
    >>  ............................................
  flirty.dialogue.conversations.dreams.first/2
    en  To be swept off my feet just once by someone who means it. High bar. You may try.
    >>  ............................................
    pt  Ser varrida do chão uma vez só por alguém que fale sério. Sarrafo alto. Você pode tentar.
    >>  ............................................
  flirty.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out, and I'd want to tell somebody about it after.
    >>  ............................................
    pt  Quero ficar onde a terra acaba, e queria contar a alguém depois.
    >>  ............................................
  friendly.dialogue.conversations.dreams.first/1
    en  A table long enough for the whole village. Everybody fed, nobody hurrying. You'd have a seat, of course.
    >>  ............................................
    pt  Uma mesa comprida o bastante pro vilarejo inteiro. Todo mundo alimentado, ninguém com pressa. Você teria um lugar, claro.
    >>  ............................................
  friendly.dialogue.conversations.dreams.first/2
    en  A house where nobody ever eats alone. That's the whole dream, %1$s.
    >>  ............................................
    pt  Uma casa onde ninguém nunca come sozinho. É esse o sonho todo, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out, and I'd want to tell somebody about it after.
    >>  ............................................
    pt  Quero ficar onde a terra acaba, e queria contar a alguém depois.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.first/1
    en  I used to dream big. Now I dream the roof doesn't leak. Aim small, miss small.
    >>  ............................................
    pt  Eu sonhava grande. Agora sonho que o telhado não goteje. Mire pequeno, erre pequeno.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.first/2
    en  To be missed. Not mourned, mind — just noticed gone. That's the whole of it.
    >>  ............................................
    pt  Que sintam a minha falta. Não que me chorem, veja bem — só que reparem que sumi. É só isso.
    >>  ............................................
  gloomy.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. I've been frightened to say it in case saying it uses it up.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Tive medo de dizer, caso dizer gastasse.
    >>  ............................................
  greedy.dialogue.conversations.dreams.first/1
    en  A pantry so full I could lend flour at NO interest, just to see their faces. Don't you dare tell anyone I said that.
    >>  ............................................
    pt  Uma despensa tão cheia que eu pudesse emprestar farinha SEM juros, só pra ver a cara deles. Não ouse contar que eu falei isso.
    >>  ............................................
  greedy.dialogue.conversations.dreams.first/2
    en  To be owed nothing by anyone. No favours out, none in. Clean books and a quiet head.
    >>  ............................................
    pt  Não dever nada a ninguém. Nenhum favor emprestado, nenhum recebido. Livros limpos e cabeça quieta.
    >>  ............................................
  greedy.dialogue.conversations.dreams.first/3
    en  I want to stand somewhere the land runs out. That's the whole of it.
    >>  ............................................
    pt  Quero ficar em algum lugar onde a terra acaba. É tudo.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.first/1
    en  A quiet house, a locked gate, and nobody knocking on it. Modest, and still out of reach.
    >>  ............................................
    pt  Uma casa quieta, um portão trancado, e ninguém batendo nele. Modesto, e ainda assim fora de alcance.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.first/2
    en  To be left alone by people I'd chosen to be left alone by. There's a difference.
    >>  ............................................
    pt  Ser deixado em paz por pessoas que eu escolhi que me deixassem em paz. Tem diferença.
    >>  ............................................
  grumpy.dialogue.conversations.dreams.first/3
    en  I want to stand somewhere the land runs out. That's the whole of it.
    >>  ............................................
    pt  Quero ficar em algum lugar onde a terra acaba. É tudo.
    >>  ............................................
  introverted.dialogue.conversations.dreams.first/1
    en  A garden behind a tall hedge, and long afternoons nobody interrupts. Modest, I know. I've thought about it a great deal.
    >>  ............................................
    pt  Uma horta atrás de uma cerca viva alta, e tardes longas que ninguém interrompe. Modesto, eu sei. Já pensei muito nisso.
    >>  ............................................
  introverted.dialogue.conversations.dreams.first/2
    en  A room with a door I can close, and a whole day on the other side of it. That's the entire dream.
    >>  ............................................
    pt  Um cômodo com uma porta que eu possa fechar, e um dia inteiro do outro lado dela. É esse o sonho todo.
    >>  ............................................
  introverted.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. That's all I want.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. É só isso que eu quero.
    >>  ............................................
  lazy.dialogue.conversations.dreams.first/1
    en  A porch, a long afternoon, and nothing on the list. I'm told that isn't ambitious. I'm told a lot of things.
    >>  ............................................
    pt  Uma varanda, uma tarde longa, e nada na lista. Dizem que não é ambicioso. Me dizem muita coisa.
    >>  ............................................
  lazy.dialogue.conversations.dreams.first/2
    en  Enough put by that I never have to hurry again. That's the whole of it.
    >>  ............................................
    pt  Guardado o bastante pra eu nunca mais precisar ter pressa. É isso.
    >>  ............................................
  lazy.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. It'll keep. Most of the good ones do.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Fica pra depois. Quase todos os bons ficam.
    >>  ............................................
  odd.dialogue.conversations.dreams.first/1
    en  I dream of a staircase that goes sideways, into a room where everyone I ever fed is fine. The chickens agree it's real.
    >>  ............................................
    pt  Sonho com uma escada que sobe de lado, até um cômodo onde todo mundo que eu já alimentei está bem. As galinhas concordam que é real.
    >>  ............................................
  odd.dialogue.conversations.dreams.first/2
    en  One day the door at the back of the pantry will open onto somewhere else. I keep the hinges oiled for it.
    >>  ............................................
    pt  Um dia a porta no fundo da despensa vai abrir pra outro lugar. Eu mantenho as dobradiças azeitadas pra isso.
    >>  ............................................
  odd.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. That's all I want.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. É só isso que eu quero.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.first/1
    en  Nothing to chase, honestly. To stay as I am, near people I care for. That would do very well.
    >>  ............................................
    pt  Nada pra perseguir, sinceramente. Continuar como estou, perto de quem eu gosto. Isso serviria muito bem.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.first/2
    en  A quiet life that stays quiet. I know how that sounds. I've wanted louder things and liked them less.
    >>  ............................................
    pt  Uma vida quieta que continue quieta. Sei como isso soa. Já quis coisas mais barulhentas e gostei menos delas.
    >>  ............................................
  peaceful.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. It'll keep. Most of the good ones do.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Fica pra depois. Quase todos os bons ficam.
    >>  ............................................
  peppy.dialogue.conversations.dreams.first/1
    en  The ocean! I want to yell at the ocean and hear it yell back! It's going to be the BEST! Top one, easy!
    >>  ............................................
    pt  O mar! Eu quero gritar com o mar e ouvir ele gritar de volta! Vai ser o MÁXIMO! Top um, fácil!
    >>  ............................................
  peppy.dialogue.conversations.dreams.first/2
    en  A festival that never ends, %1$s! Just one long one! Somebody has to organise it and I have VOLUNTEERED!
    >>  ............................................
    pt  Uma festa que nunca acaba, %1$s! Uma só, bem comprida! Alguém tem que organizar e eu me VOLUNTARIEI!
    >>  ............................................
  peppy.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out. Just to check that it does.
    >>  ............................................
    pt  Quero ficar onde a terra acaba. Só pra conferir que acaba.
    >>  ............................................
  playful.dialogue.conversations.dreams.first/1
    en  To pull off one truly legendary trick. The kind they still talk about when I'm old and slow.
    >>  ............................................
    pt  Aprontar uma pegadinha verdadeiramente lendária. Daquelas que ainda comentam quando eu estiver velho e lento.
    >>  ............................................
  playful.dialogue.conversations.dreams.first/2
    en  A life where nothing ever gets boring. That's it. That's the dream, and I'm chasing it daily.
    >>  ............................................
    pt  Uma vida em que nada nunca fique sem graça. É isso. É esse o sonho, e eu corro atrás dele diariamente.
    >>  ............................................
  playful.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out. Just to check that it does.
    >>  ............................................
    pt  Quero ficar onde a terra acaba. Só pra conferir que acaba.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.first/1
    en  A porch, a long afternoon, and nothing on the list. I'm told that isn't ambitious. I'm told a lot of things.
    >>  ............................................
    pt  Uma varanda, uma tarde longa, e nada na lista. Dizem que não é ambicioso. Me dizem muita coisa.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.first/2
    en  Enough put by that I never have to hurry again. That's the whole of it.
    >>  ............................................
    pt  Guardado o bastante pra eu nunca mais precisar ter pressa. É isso.
    >>  ............................................
  relaxed.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. It'll keep. Most of the good ones do.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Fica pra depois. Quase todos os bons ficam.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.first/1
    en  A house where nobody leaves the table angry. That's it, that's the whole dream. Small, isn't it? It doesn't feel small.
    >>  ............................................
    pt  Uma casa onde ninguém sai da mesa com raiva. É isso, é esse o sonho todo. Pequeno, né? Não parece pequeno.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.first/2
    en  That someone I love says the thing they're afraid to say, and I'm the one they say it to. That's the dream, %1$s.
    >>  ............................................
    pt  Que alguém que eu amo diga a coisa que tem medo de dizer, e que seja pra mim que diga. É esse o sonho, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. I've been frightened to say it in case saying it uses it up.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. Tive medo de dizer, caso dizer gastasse.
    >>  ............................................
  shy.dialogue.conversations.dreams.first/1
    en  A garden behind a tall hedge, and long afternoons nobody interrupts. Modest, I know. I've thought about it a great deal.
    >>  ............................................
    pt  Uma horta atrás de uma cerca viva alta, e tardes longas que ninguém interrompe. Modesto, eu sei. Já pensei muito nisso.
    >>  ............................................
  shy.dialogue.conversations.dreams.first/2
    en  A room with a door I can close, and a whole day on the other side of it. That's the entire dream.
    >>  ............................................
    pt  Um cômodo com uma porta que eu possa fechar, e um dia inteiro do outro lado dela. É esse o sonho todo.
    >>  ............................................
  shy.dialogue.conversations.dreams.first/3
    en  Somewhere the land runs out. That's all I want.
    >>  ............................................
    pt  Algum lugar onde a terra acaba. É só isso que eu quero.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.first/1
    en  A full table, a warm house, and people in it who want to be there. I've most of it already, which is rather the point.
    >>  ............................................
    pt  Uma mesa cheia, uma casa quente, e pessoas nela que querem estar ali. Já tenho quase tudo, que é justamente o ponto.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.first/2
    en  Nothing grand. To be someone the village is glad to see coming. I'm working on it daily.
    >>  ............................................
    pt  Nada grandioso. Ser alguém que o vilarejo fica contente de ver chegando. Trabalho nisso diariamente.
    >>  ............................................
  upbeat.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out. Just to check that it does.
    >>  ............................................
    pt  Quero ficar onde a terra acaba. Só pra conferir que acaba.
    >>  ............................................
  witty.dialogue.conversations.dreams.first/1
    en  A full table, a warm house, and people in it who want to be there. I've most of it already, which is rather the point.
    >>  ............................................
    pt  Uma mesa cheia, uma casa quente, e pessoas nela que querem estar ali. Já tenho quase tudo, que é justamente o ponto.
    >>  ............................................
  witty.dialogue.conversations.dreams.first/2
    en  Nothing grand. To be someone the village is glad to see coming. I'm working on it daily.
    >>  ............................................
    pt  Nada grandioso. Ser alguém que o vilarejo fica contente de ver chegando. Trabalho nisso diariamente.
    >>  ............................................
  witty.dialogue.conversations.dreams.first/3
    en  I want to stand where the land runs out. Just to check that it does.
    >>  ............................................
    pt  Quero ficar onde a terra acaba. Só pra conferir que acaba.
    >>  ............................................
```

</details>


**Outcome 10 of 11** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.dreams` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `dreams` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `again_open` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.respond`
- …where the player's next choices will be: "You should chase that." | "Tell me more about it." | "That's a long way from here." | "I don't know what to say to that." | "I hope you get it."

```text
POOL   dialogue key: dialogue.conversations.dreams.revisit
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.revisit.to.dreams`: the villager accepts. Subject `dreams`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `dreams`** earlier in this file. Fill it in there, once.


**Outcome 11 of 11** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.topic.dreams` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.dreams`
- …where the player's next choices will be: "You should chase that." | "Tell me more about it." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.first
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.dreams
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.first.opens`: the villager discloses. Subject `dreams.ambition`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `dreams:named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.cat.personal` / button `dreams`** earlier in this file. Fill it in there, once.


### Button `fears` — "What are you afraid of?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.fears` — accepted phrasings: "what are you afraid of"; "what scares you"; "your fears"; "what do you fear"
  - the message must contain one of: `fear`, `scare`
  - scored words: `fear`(1.5), `scare`(1.2), `worry`(0.8), `nightmare`(0.8)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.fears
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.fears   [23 chars]
    en  What are you afraid of?
    >>  ............................................
    pt  Do que você tem medo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 13** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.fears.the_night_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.fears.the_night_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 36000 ticks
- Then opens: `conversations.scene.fears.the_night_one.respond`
- …where the player's next choices will be: "That's a real thing to be afraid of." | "How long have you carried that?" | "Thank you for trusting me."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_night_one
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.scene.fears.the_night_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_night_one.open`: the villager reports. Subject `fears.the_real_one`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_night_one/1   [105 chars]
    en  It is not a thing that could happen. It is a thing that already has, somewhere else, to somebody like me.
    >>  ............................................
    pt  Não é uma coisa que possa acontecer. É uma coisa que já aconteceu, em outro lugar, com alguém como eu.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one/2   [130 chars]
    en  Being the last one who knows how to do something. Everybody else finds that flattering and it frightens me at four in the morning.
    >>  ............................................
    pt  Ser a última que sabe fazer alguma coisa. Todo mundo acha isso lisonjeiro e me assusta às quatro da manhã.
    >>  ............................................
  dialogue.conversations.scene.fears.the_night_one/3   [95 chars]
    en  That I will be exactly like this in twenty years and will have decided by then that I chose it.
    >>  ............................................
    pt  Que eu esteja exatamente assim daqui a vinte anos e já tenha decidido, até lá, que foi escolha minha.
    >>  ............................................
```


**Outcome 2 of 13** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.fears.the_doorway_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.fears.the_doorway_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 36000 ticks
- Then opens: `conversations.scene.fears.the_doorway_one.respond`
- …where the player's next choices will be: "Where did that one come from?" | "I've got one just as daft." | "Thank you for trusting me."

```text
POOL   dialogue key: dialogue.conversations.scene.fears.the_doorway_one
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.scene.fears.the_doorway_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.the_doorway_one.open`: the villager reports. Subject `fears.ordinary`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:fears` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.fears.the_doorway_one/1   [103 chars]
    en  Deep water and public speaking, in that order, and I have arranged my entire life around avoiding both.
    >>  ............................................
    pt  Água funda e falar em público, nessa ordem, e organizei minha vida inteira para evitar as duas.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one/2   [76 chars]
    en  Ladders. Genuinely. I will carry a thing twice as far to avoid going up one.
    >>  ............................................
    pt  Escadas. Sério. Eu carrego uma coisa o dobro da distância para não subir numa.
    >>  ............................................
  dialogue.conversations.scene.fears.the_doorway_one/3   [104 chars]
    en  The usual. Debt, weather, and being asked a question in front of everybody. Two of those I can plan for.
    >>  ............................................
    pt  O de sempre. Dívida, tempo, e ser perguntada na frente de todo mundo. Duas dessas dá para planejar.
    >>  ............................................
```


**Outcome 3 of 13** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.fears` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `again` budget `deep`
- Then opens: `conversations.topic.fears.again.respond`
- …where the player's next choices will be: "Sorry — I shouldn't have asked twice." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.fears.again
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.again.to.fears.again`: the villager accepts. Subject `fears.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.again/1   [70 chars]
    en  I already showed you that scar, %1$s. Don't poke it twice in one week.
    >>  ............................................
    pt  Já te mostrei essa cicatriz, %1$s. Não cutuca ela duas vezes na mesma semana.
    >>  ............................................
  dialogue.conversations.fears.again/2   [63 chars]
    en  Twice in one week you want my nightmares? Buy me a drink first.
    >>  ............................................
    pt  Duas vezes na mesma semana você quer os meus pesadelos? Me paga uma bebida primeiro.
    >>  ............................................
  dialogue.conversations.fears.again/3   [56 chars]
    en  Let that one rest, %1$s. It bites when it's poked fresh.
    >>  ............................................
    pt  Deixa esse quieto, %1$s. Ele morde quando é cutucado fresco.
    >>  ............................................
```


**Outcome 4 of 13** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `toddler` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.toddler.respond`
- …where the player's next choices will be: "That does sound scary. You're safe here." | "What's it like, the scary thing?" | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.fears.toddler
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.toddler.to.fears.toddler`: the villager accepts. Subject `fears.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.toddler/1   [60 chars]
    en  The dark under the bed. There's a WHOLE nothing under there.
    >>  ............................................
    pt  O escuro debaixo da cama. Tem um NADA inteiro lá embaixo.
    >>  ............................................
  dialogue.conversations.fears.toddler/2   [32 chars]
    en  Thunder. But only the loud kind.
    >>  ............................................
    pt  Trovão. Mas só o tipo alto.
    >>  ............................................
  dialogue.conversations.fears.toddler/3   [61 chars]
    en  When Mama hides her face behind her hands. Where does she GO?
    >>  ............................................
    pt  Quando a mamãe esconde o rosto atrás das mãos. Pra ONDE ela vai?
    >>  ............................................
```


**Outcome 5 of 13** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `child` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.young.respond`
- …where the player's next choices will be: "That's a real thing to be scared of." | "Everyone I know is scared of something." | "You'll grow out of it." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.fears.child
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.child.to.fears.young`: the villager accepts. Subject `fears.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.child/1   [76 chars]
    en  The under-the-bed place. Papa checked it but monsters KNOW when papas leave.
    >>  ............................................
    pt  O lugar debaixo da cama. O papai conferiu, mas os monstros SABEM quando os papais saem.
    >>  ............................................
  dialogue.conversations.fears.child/2   [84 chars]
    en  Thunder. But only the loud kind. The rumbly kind is the sky's tummy and that's fine.
    >>  ............................................
    pt  Trovão. Mas só o tipo alto. O tipo roncado é a barriga do céu e tudo bem.
    >>  ............................................
```


**Outcome 6 of 13** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `teen` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.young.respond`
- …where the player's next choices will be: "That's a real thing to be scared of." | "Everyone I know is scared of something." | "You'll grow out of it." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.fears.teen
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.teen.to.fears.young`: the villager accepts. Subject `fears.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.teen/1   [89 chars]
    en  That I'll end up exactly like everyone here and never find out what else I could've been.
    >>  ............................................
    pt  Que eu acabe exatamente como todo mundo daqui e nunca descubra o que mais eu poderia ter sido.
    >>  ............................................
  dialogue.conversations.fears.teen/2   [73 chars]
    en  Honestly? Disappointing people. I act like I don't care. I care the most.
    >>  ............................................
    pt  Sinceramente? Decepcionar as pessoas. Eu ajo como se não ligasse. Eu ligo mais que todo mundo.
    >>  ............................................
```


**Outcome 7 of 13** — base weight `0`

- Fires when: weighted +100 when milestone `fears.scar` is set
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `scarred` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.scarred.respond`
- …where the player's next choices will be: "I pushed you, and I was wrong to." | "I'll not ask again." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.fears.scarred
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.scarred.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.scarred.to.fears.scarred`: the villager accepts. Subject `fears.scarred`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.scarred/1   [46 chars]
    en  ...You. Right. Ask me about the weather, %1$s.
    >>  ............................................
    pt  ...Você. Certo. Me pergunte sobre o tempo, %1$s.
    >>  ............................................
  dialogue.conversations.fears.scarred/2   [44 chars]
    en  I remember how the last one went. Let's not.
    >>  ............................................
    pt  Lembro como foi da última vez. Vamos deixar quieto.
    >>  ............................................
  dialogue.conversations.fears.scarred/3   [51 chars]
    en  That door's got a bolt on it now. You put it there.
    >>  ............................................
    pt  Aquela porta agora tem tranca. Você que colocou.
    >>  ............................................
```


**Outcome 8 of 13** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `tense`, `hostile`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when milestone `fears.scar` is set  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.guarded.respond`
- …where the player's next choices will be: "Fair enough. It's yours to keep." | "Then tell me something easier." | "Come on. Out with it." | "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.deflect.personal
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   6 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.personal.to.fears.guarded`: the villager deflects. Subject `fears.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / life; conversations.cat.personal / dreams; conversations.cat.personal / hopes
```

> Written out in full under **`conversations.cat.personal` / button `life`** earlier in this file. Fill it in there, once.


**Outcome 9 of 13** — base weight `0`

- Fires when: weighted +100 when arc `fears` is at stage 2..3
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when milestone `fears.scar` is set  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `followthrough` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.arc.fears.followthrough.respond`
- …where the player's next choices will be: "How's it been going?" | "Look at you. That's not nothing." | "I meant what I said, back then." | "I'll not make a fuss of it."

```text
POOL   dialogue key: dialogue.conversations.fears.resume.followthrough
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.arc.fears.followthrough.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.resume.followthrough.to.fears.followthrough`: the villager accepts. Subject `fears.followthrough`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.resume.followthrough/1   [52 chars]
    en  I did the thing. Or started it. You'll want to hear.
    >>  ............................................
    pt  Eu fiz aquilo. Ou comecei. Você vai querer saber.
    >>  ............................................
  dialogue.conversations.fears.resume.followthrough/2   [45 chars]
    en  Small progress on the old fear. Small counts.
    >>  ............................................
    pt  Um pequeno progresso com o velho medo. Pequeno também conta.
    >>  ............................................
  dialogue.conversations.fears.resume.followthrough/3   [47 chars]
    en  Ask me about it. Go on. I've earned the asking.
    >>  ............................................
    pt  Me pergunte sobre isso. Vai. Eu mereci a pergunta.
    >>  ............................................
```


**Outcome 10 of 13** — base weight `0`

- Fires when: weighted +100 when arc `fears` is at stage 1..1
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when milestone `fears.scar` is set  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `plan` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.arc.fears.plan.respond`
- …where the player's next choices will be: "What would actually help?" | "Then let's do something about it." | "You don't need a plan. I'm here." | "Take your time."

```text
POOL   dialogue key: dialogue.conversations.fears.resume.plan
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.arc.fears.plan.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.resume.plan.to.fears.plan`: the villager accepts. Subject `fears.plan`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.resume.plan/1   [53 chars]
    en  That thing I told you about. It hasn't gone anywhere.
    >>  ............................................
    pt  Aquilo que eu te contei. Não foi a lugar nenhum.
    >>  ............................................
  dialogue.conversations.fears.resume.plan/2   [69 chars]
    en  I've been thinking about what I said to you. Out loud didn't kill me.
    >>  ............................................
    pt  Andei pensando no que eu te disse. Falar em voz alta não me matou.
    >>  ............................................
  dialogue.conversations.fears.resume.plan/3   [50 chars]
    en  It's still sat there, the fear. Politely. Waiting.
    >>  ............................................
    pt  O medo continua ali sentado. Educadamente. Esperando.
    >>  ............................................
```


**Outcome 11 of 13** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.fears` (this player only)
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when milestone `fears.scar` is set  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `fears` is at stage 1..3  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `open` budget `deep`
- Does: remembers `mcaconversations.topic.fears` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.open.respond`
- …where the player's next choices will be: "That sounds hard to carry." | "Tell me the rest of it." | "I'm afraid of that too." | "I don't know what to say to that." | "Thank you for telling me. I'll go."

```text
POOL   dialogue key: dialogue.conversations.fears.first
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.first.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.first/1   [96 chars]
    en  Honestly? Thunder. And the thing that scratched at my door two winters back. Mostly the thunder.
    >>  ............................................
    pt  Sinceramente? Trovão. E aquela coisa que arranhou a minha porta dois invernos atrás. Mas principalmente o trovão.
    >>  ............................................
  dialogue.conversations.fears.first/2   [83 chars]
    en  Being forgotten. Everyone remembers the mayor. Nobody remembers who fixed the well.
    >>  ............................................
    pt  Ser esquecido. Todo mundo lembra do prefeito. Ninguém lembra de quem consertou o poço.
    >>  ............................................
  dialogue.conversations.fears.first/3   [90 chars]
    en  The dark past the last lantern. Everyone pretends they're not afraid of it. They're lying.
    >>  ............................................
    pt  O escuro depois da última lanterna. Todo mundo finge que não tem medo dele. Estão mentindo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.fears.first/1
    en  All of them, honestly — that's rather the problem. Mostly that something bad happens and it's my fault.
    >>  ............................................
    pt  Todos eles, sinceramente — é justamente esse o problema. Principalmente que aconteça algo ruim e a culpa seja minha.
    >>  ............................................
  anxious.dialogue.conversations.fears.first/2
    en  Letting someone down without realising I'd done it. That one follows me about, %1$s.
    >>  ............................................
    pt  Decepcionar alguém sem perceber que decepcionei. Esse me segue por aí, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.fears.first/1
    en  Truth? The day my knees quit. Everything I am is in these legs. What's left of me when they slow down?
    >>  ............................................
    pt  Verdade? O dia em que os meus joelhos desistirem. Tudo que eu sou está nessas pernas. O que sobra de mim quando elas desacelerarem?
    >>  ............................................
  athletic.dialogue.conversations.fears.first/2
    en  Rusting. Sitting still till the body forgets how to move. I outrun that one daily.
    >>  ............................................
    pt  Enferrujar. Ficar parado até o corpo esquecer como se move. Desse eu fujo todo dia.
    >>  ............................................
  confident.dialogue.conversations.fears.first/1
    en  You'll keep this quiet. I fear being ordinary. Waking up one morning as just another face at the well.
    >>  ............................................
    pt  Você vai guardar isso. Tenho medo de ser comum. Acordar uma manhã como só mais um rosto perto do poço.
    >>  ............................................
  confident.dialogue.conversations.fears.first/2
    en  Being seen to fall. I hide it well — that's rather the point, %1$s.
    >>  ............................................
    pt  Ser visto caindo. Eu escondo bem — que é justamente o ponto, %1$s.
    >>  ............................................
  crabby.dialogue.conversations.fears.first/1
    en  That the grumbling's all that'll be left of me. ...Forget I said that.
    >>  ............................................
    pt  Que o resmungo seja tudo que sobre de mim. ...Esquece que eu falei isso.
    >>  ............................................
  crabby.dialogue.conversations.fears.first/2
    en  Ending up alone and having earned it. That one keeps me up, %1$s.
    >>  ............................................
    pt  Acabar sozinho e ter merecido. Esse me tira o sono, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.fears.first/1
    en  An empty village. Everyone gone, doors shut, nobody left to talk to. It frightens me more than it should.
    >>  ............................................
    pt  Um vilarejo vazio. Todo mundo embora, portas fechadas, ninguém pra conversar. Isso me assusta mais do que deveria.
    >>  ............................................
  extroverted.dialogue.conversations.fears.first/2
    en  Being left out. Hearing the laughter from the wrong side of a door. That's the one.
    >>  ............................................
    pt  Ser deixado de fora. Ouvir a risada do lado errado de uma porta. É esse.
    >>  ............................................
  flirty.dialogue.conversations.fears.first/1
    en  Promise not to use it against me? ...Ending up alone. There. Now you owe me a secret.
    >>  ............................................
    pt  Promete não usar isso contra mim? ...Acabar sozinha. Pronto. Agora você me deve um segredo.
    >>  ............................................
  flirty.dialogue.conversations.fears.first/2
    en  That the charm's all there is to me. ...Deny I said that and I'll deny it right back, smiling.
    >>  ............................................
    pt  Que o charme seja tudo que existe em mim. ...Negue que eu disse isso e eu nego de volta, sorrindo.
    >>  ............................................
  friendly.dialogue.conversations.fears.first/1
    en  Honestly? An empty chair at supper. Folks drifting off one by one till it's just me and the kettle.
    >>  ............................................
    pt  Sinceramente? Uma cadeira vazia na janta. As pessoas indo embora uma a uma até sobrar só eu e a chaleira.
    >>  ............................................
  friendly.dialogue.conversations.fears.first/2
    en  That someone here is hurting and I didn't notice. I'd never forgive myself.
    >>  ............................................
    pt  Que alguém aqui esteja sofrendo e eu não tenha percebido. Eu nunca me perdoaria.
    >>  ............................................
  gloomy.dialogue.conversations.fears.first/1
    en  That the best days already happened and I wasn't paying attention.
    >>  ............................................
    pt  Que os melhores dias já tenham acontecido e eu não estivesse prestando atenção.
    >>  ............................................
  gloomy.dialogue.conversations.fears.first/2
    en  Outliving everyone who'd know what I meant by a look.
    >>  ............................................
    pt  Sobreviver a todo mundo que entenderia o que eu quis dizer com um olhar.
    >>  ............................................
  greedy.dialogue.conversations.fears.first/1
    en  Honestly? Dying with full pockets and an empty table. What was I saving it all FOR, then? ...Forget the second part.
    >>  ............................................
    pt  Sinceramente? Morrer com os bolsos cheios e a mesa vazia. Pra que eu estava guardando tudo, então? ...Esquece a segunda parte.
    >>  ............................................
  greedy.dialogue.conversations.fears.first/2
    en  That the counting is the only thing I'm any good at. ...Strike that.
    >>  ............................................
    pt  Que contar seja a única coisa em que eu sou bom. ...Risca isso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.first/1
    en  That the grumbling's all that'll be left of me. ...Forget I said that.
    >>  ............................................
    pt  Que o resmungo seja tudo que sobre de mim. ...Esquece que eu falei isso.
    >>  ............................................
  grumpy.dialogue.conversations.fears.first/2
    en  Ending up alone and having earned it. That one keeps me up, %1$s.
    >>  ............................................
    pt  Acabar sozinho e ter merecido. Esse me tira o sono, %1$s.
    >>  ............................................
  introverted.dialogue.conversations.fears.first/1
    en  That the quiet becomes permanent — that I'd get so good at being alone I'd forget how to stop.
    >>  ............................................
    pt  Que o silêncio vire permanente — que eu fique tão bom em ficar sozinho que esqueça como parar.
    >>  ............................................
  introverted.dialogue.conversations.fears.first/2
    en  Being alone by habit rather than by choice. There's a difference, and it's easy to lose.
    >>  ............................................
    pt  Ficar sozinho por hábito e não por escolha. Tem diferença, e é fácil perder ela de vista.
    >>  ............................................
  lazy.dialogue.conversations.fears.first/1
    en  Waking up one day in a hurry and never getting out of it. You see it happen to people, %1$s.
    >>  ............................................
    pt  Acordar um dia com pressa e nunca mais sair dela. Você vê acontecer com as pessoas, %1$s.
    >>  ............................................
  lazy.dialogue.conversations.fears.first/2
    en  Being rushed through the good parts. That's my only real fear, and it's a serious one.
    >>  ............................................
    pt  Ser apressado nas partes boas. É esse o meu único medo de verdade, e é sério.
    >>  ............................................
  odd.dialogue.conversations.fears.first/1
    en  The gap between thunder and thunder — that's when the sky decides. And that only the bucket would miss me. ...Silly. Probably.
    >>  ............................................
    pt  O intervalo entre um trovão e outro — é aí que o céu decide. E que só o balde sentiria a minha falta. ...Bobagem. Provavelmente.
    >>  ............................................
  odd.dialogue.conversations.fears.first/2
    en  Being the last one who remembers a thing. Then it isn't remembered. It's just me.
    >>  ............................................
    pt  Ser o último que lembra de uma coisa. Aí ela não está lembrada. Está só em mim.
    >>  ............................................
  peaceful.dialogue.conversations.fears.first/1
    en  Bitterness. I've watched it take good people slowly, and they never saw it happening.
    >>  ............................................
    pt  Amargura. Vi ela tomar gente boa devagar, e eles nunca perceberam acontecendo.
    >>  ............................................
  peaceful.dialogue.conversations.fears.first/2
    en  Losing my calm and not being able to find it again. It took me long enough the first time.
    >>  ............................................
    pt  Perder a minha calma e não conseguir encontrar de novo. Já levou tempo demais da primeira vez.
    >>  ............................................
  peppy.dialogue.conversations.fears.first/1
    en  That one morning the cheer won't come, and everyone finds out how hard I row to keep it. ...ANYWAY! Also spiders!
    >>  ............................................
    pt  Que uma manhã a alegria não venha, e todo mundo descubra o quanto eu remo pra manter ela. ...ENFIM! E também aranha!
    >>  ............................................
  peppy.dialogue.conversations.fears.first/2
    en  Being the one everyone's glad to see and nobody thinks to check on. ...ANYWAY! Have you eaten?
    >>  ............................................
    pt  Ser aquela pessoa que todo mundo gosta de ver e ninguém lembra de perguntar como está. ...ENFIM! Você já comeu?
    >>  ............................................
  playful.dialogue.conversations.fears.first/1
    en  Getting dull. Waking up one day and finding all the fun's gone out of me. Don't laugh — I mean it.
    >>  ............................................
    pt  Ficar sem graça. Acordar um dia e descobrir que toda a diversão saiu de mim. Não ri — eu falo sério.
    >>  ............................................
  playful.dialogue.conversations.fears.first/2
    en  That people would stop playing along. It'd be a lonely sort of joke with no one to tell it to.
    >>  ............................................
    pt  Que as pessoas parassem de entrar na brincadeira. Seria uma piada solitária, sem ninguém pra contar.
    >>  ............................................
  relaxed.dialogue.conversations.fears.first/1
    en  Waking up one day in a hurry and never getting out of it. You see it happen to people, %1$s.
    >>  ............................................
    pt  Acordar um dia com pressa e nunca mais sair dela. Você vê acontecer com as pessoas, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.fears.first/2
    en  Being rushed through the good parts. That's my only real fear, and it's a serious one.
    >>  ............................................
    pt  Ser apressado nas partes boas. É esse o meu único medo de verdade, e é sério.
    >>  ............................................
  sensitive.dialogue.conversations.fears.first/1
    en  That someone I love will be hurting right in front of me and I'll miss it. I check faces the way the guard checks the wall.
    >>  ............................................
    pt  Que alguém que eu amo esteja sofrendo bem na minha frente e eu não perceba. Eu confiro rostos como o guarda confere o muro.
    >>  ............................................
  sensitive.dialogue.conversations.fears.first/2
    en  That I'm too much. That people are kind to my face and relieved when I've gone.
    >>  ............................................
    pt  Que eu seja demais. Que as pessoas sejam gentis na minha frente e aliviadas quando eu vou embora.
    >>  ............................................
  shy.dialogue.conversations.fears.first/1
    en  That the quiet becomes permanent — that I'd get so good at being alone I'd forget how to stop.
    >>  ............................................
    pt  Que o silêncio vire permanente — que eu fique tão bom em ficar sozinho que esqueça como parar.
    >>  ............................................
  shy.dialogue.conversations.fears.first/2
    en  Being alone by habit rather than by choice. There's a difference, and it's easy to lose.
    >>  ............................................
    pt  Ficar sozinho por hábito e não por escolha. Tem diferença, e é fácil perder ela de vista.
    >>  ............................................
  upbeat.dialogue.conversations.fears.first/1
    en  That I'd stop noticing the good days. It'd be so easy to let them blur past, %1$s. I'd hate that.
    >>  ............................................
    pt  Que eu parasse de reparar nos dias bons. Seria tão fácil deixar eles passarem borrados, %1$s. Eu detestaria isso.
    >>  ............................................
  upbeat.dialogue.conversations.fears.first/2
    en  Turning sour. I've seen it happen to good people, and I'd rather not join them.
    >>  ............................................
    pt  Azedar. Já vi acontecer com gente boa, e prefiro não me juntar a eles.
    >>  ............................................
  witty.dialogue.conversations.fears.first/1
    en  That I'd stop noticing the good days. It'd be so easy to let them blur past, %1$s. I'd hate that.
    >>  ............................................
    pt  Que eu parasse de reparar nos dias bons. Seria tão fácil deixar eles passarem borrados, %1$s. Eu detestaria isso.
    >>  ............................................
  witty.dialogue.conversations.fears.first/2
    en  Turning sour. I've seen it happen to good people, and I'd rather not join them.
    >>  ............................................
    pt  Azedar. Já vi acontecer com gente boa, e prefiro não me juntar a eles.
    >>  ............................................
```

</details>


**Outcome 12 of 13** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.fears` (this player only)
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when milestone `fears.scar` is set  _(chance -2000)_
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `fears` is at stage 1..3  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.fears` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `fears` branch `revisit` budget `deep`
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.topic.fears.open.respond`
- …where the player's next choices will be: "That sounds hard to carry." | "Tell me the rest of it." | "I'm afraid of that too." | "I don't know what to say to that." | "Thank you for telling me. I'll go."

```text
POOL   dialogue key: dialogue.conversations.fears.revisit
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.topic.fears.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.revisit.to.fears.open`: the villager accepts. Subject `fears.open`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.fears.revisit/1   [93 chars]
    en  I've been chewing on what you asked me — about being afraid. It's lighter now that it's said.
    >>  ............................................
    pt  Fiquei remoendo o que você me perguntou — sobre ter medo. Está mais leve agora que foi dito.
    >>  ............................................
  dialogue.conversations.fears.revisit/2   [83 chars]
    en  That fear we talked about — I slept with the lantern out twice this week. Progress.
    >>  ............................................
    pt  Aquele medo que a gente conversou — dormi com a lanterna apagada duas vezes essa semana. Progresso.
    >>  ............................................
  dialogue.conversations.fears.revisit/3   [77 chars]
    en  I keep coming back to what you asked. Naming a thing takes some of its teeth.
    >>  ............................................
    pt  Fico voltando no que você perguntou. Dar nome a uma coisa tira alguns dentes dela.
    >>  ............................................
```


**Outcome 13 of 13** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 5
- Does: remembers `mcaconversations.topic.fears` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.fears` (this player only) for 48000 ticks
- Then opens: `conversations.fears`
- …where the player's next choices will be: "That sounds hard to carry." | "You could face it. I'd stand with you." | "Tell me the rest of it." | "I'm scared of that too." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.fears.first
WHO    VILLAGER — what the player reads after pressing "What are you afraid of?"
       spoken on: conversations.cat.personal, button `fears`
       leaves the player on: conversations.fears
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `fears.first.to.fears`: the villager accepts. Subject `fears`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `fears`** earlier in this file. Fill it in there, once.


### Button `hopes` — "What are you hoping for?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.hopes` — accepted phrasings: "what do you hope for"; "what do you wish for"; "your hopes"; "hope for the future"
  - the message must contain one of: `hope`, `wish`, `someday`
  - scored words: `hope`(1.5), `wish`(1.0), `someday`(0.8), `better`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.hopes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.hopes   [24 chars]
    en  What are you hoping for?
    >>  ............................................
    pt  O que você espera da vida?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.hopes.spring_list"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.hopes.spring_list", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 36000 ticks
- Then opens: `conversations.scene.hopes.spring_list.respond`
- …where the player's next choices will be: "What's the large one?" | "I hope the year gives you all three." | "I hope it comes off."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.spring_list
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.scene.hopes.spring_list.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.spring_list.open`: the villager reports. Subject `hopes.this_year`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.spring_list/1   [77 chars]
    en  Three things, written on the inside of a cupboard door where only I see them.
    >>  ............................................
    pt  Três coisas, escritas na parte de dentro da porta do armário, onde só eu vejo.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list/2   [112 chars]
    en  I want this year to be dull and productive. I have wanted exciting years and I have had two and that was plenty.
    >>  ............................................
    pt  Quero que este ano seja monótono e produtivo. Já quis anos empolgantes, tive dois, e foi mais que suficiente.
    >>  ............................................
  dialogue.conversations.scene.hopes.spring_list/3   [108 chars]
    en  One large hope and two that I could finish by summer, which is how you keep a list from becoming a reproach.
    >>  ............................................
    pt  Uma esperança grande e duas que eu poderia terminar até o verão, que é como se impede uma lista de virar cobrança.
    >>  ............................................
```


**Outcome 2 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.hopes.the_long_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.hopes.the_long_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 36000 ticks
- Then opens: `conversations.scene.hopes.the_long_one.respond`
- …where the player's next choices will be: "What would move it along?" | "It's safe with me." | "I hope it comes off."

```text
POOL   dialogue key: dialogue.conversations.scene.hopes.the_long_one
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.scene.hopes.the_long_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.the_long_one.open`: the villager reports. Subject `hopes.carried`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:hopes` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.hopes.the_long_one/1   [109 chars]
    en  There is one I have had for eleven years and have said out loud about four times, all to the same two people.
    >>  ............................................
    pt  Existe uma que eu tenho há onze anos e disse em voz alta umas quatro vezes, todas para as mesmas duas pessoas.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one/2   [86 chars]
    en  It is not impossible. That is what makes it heavy. An impossible hope is easy company.
    >>  ............................................
    pt  Não é impossível. É isso que a torna pesada. Uma esperança impossível é companhia fácil.
    >>  ............................................
  dialogue.conversations.scene.hopes.the_long_one/3   [98 chars]
    en  I keep it the way you keep a seed. Somewhere dry, checked rarely, and never explained to visitors.
    >>  ............................................
    pt  Guardo do jeito que se guarda uma semente. Num lugar seco, conferida raramente, e nunca explicada a visitas.
    >>  ............................................
```


**Outcome 3 of 11** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.hopes` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `again` budget `deep`
- Then opens: `conversations.topic.deep.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.hopes.again
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.again.to.deep.again`: the villager accepts. Subject `deep.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.again/1   [87 chars]
    en  You already asked what I'm hoping for, %1$s. My wishes haven't changed since breakfast.
    >>  ............................................
    pt  Você já perguntou o que eu espero, %1$s. Meus desejos não mudaram desde o café da manhã.
    >>  ............................................
  dialogue.conversations.hopes.again/2   [62 chars]
    en  Same hopes as before. They don't turn over that quickly, mine.
    >>  ............................................
    pt  As mesmas esperanças de antes. Elas não viram tão rápido assim, as minhas.
    >>  ............................................
  dialogue.conversations.hopes.again/3   [81 chars]
    en  Asking twice won't make them come true any faster. But I appreciate the interest.
    >>  ............................................
    pt  Perguntar duas vezes não faz elas se realizarem mais rápido. Mas eu agradeço o interesse.
    >>  ............................................
```


**Outcome 4 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `toddler` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.toddler.respond`
- …where the player's next choices will be: "I hope so too." | "What else are you hoping for?" | "Off you go and hope, then."

```text
POOL   dialogue key: dialogue.conversations.hopes.toddler
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.toddler.to.hopes.toddler`: the villager accepts. Subject `hopes.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.toddler/1   [35 chars]
    en  Snacks tomorrow! And the day after!
    >>  ............................................
    pt  Lanche amanhã! E depois de amanhã!
    >>  ............................................
  dialogue.conversations.hopes.toddler/2   [43 chars]
    en  I hope the frog comes back. He's my friend.
    >>  ............................................
    pt  Espero que o sapo volte. Ele é meu amigo.
    >>  ............................................
  dialogue.conversations.hopes.toddler/3   [41 chars]
    en  Being big! With pockets. Lots of pockets.
    >>  ............................................
    pt  Ser grande! Com bolso. Muito bolso.
    >>  ............................................
```


**Outcome 5 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `child` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.young.respond`
- …where the player's next choices will be: "Tell me the whole list, then." | "I hope you get it too." | "That's a bit silly, isn't it." | "Keep hoping. I'll get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.child
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.child.to.hopes.young`: the villager accepts. Subject `hopes.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.child/1   [54 chars]
    en  I hope I get my own fishing rod this year. A real one.
    >>  ............................................
    pt  Espero ganhar minha própria vara de pescar esse ano. Uma de verdade.
    >>  ............................................
  dialogue.conversations.hopes.child/2   [48 chars]
    en  I'm hoping for a dog. I've been very convincing.
    >>  ............................................
    pt  Tô esperando um cachorro. Já fui bem convincente.
    >>  ............................................
```


**Outcome 6 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `teen` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.young.respond`
- …where the player's next choices will be: "Tell me the whole list, then." | "I hope you get it too." | "That's a bit silly, isn't it." | "Keep hoping. I'll get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.teen
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.teen.to.hopes.young`: the villager accepts. Subject `hopes.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.teen/1   [68 chars]
    en  To get out of chores... and maybe see the next village over someday.
    >>  ............................................
    pt  Me livrar das tarefas... e talvez ver o vilarejo vizinho um dia.
    >>  ............................................
  dialogue.conversations.hopes.teen/2   [51 chars]
    en  Honestly? A day where nobody needs me for anything.
    >>  ............................................
    pt  Sinceramente? Um dia em que ninguém precise de mim pra nada.
    >>  ............................................
```


**Outcome 7 of 11** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `tense`, `hostile`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.guarded.respond`
- …where the player's next choices will be: "Then don't name it." | "Something with less riding on it, then." | "Say it anyway. I'll not jinx you." | "Fair. I'll not press it."

```text
POOL   dialogue key: dialogue.conversations.deflect.personal
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   6 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.personal.to.hopes.guarded`: the villager deflects. Subject `hopes.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / life; conversations.cat.personal / dreams; conversations.cat.personal / fears
```

> Written out in full under **`conversations.cat.personal` / button `life`** earlier in this file. Fill it in there, once.


**Outcome 8 of 11** — base weight `0`

- Fires when: weighted +100 when arc `hopes` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `resume` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.arc.hopes.resume.respond`
- …where the player's next choices will be: "Still hoping for it?" | "Let me help with it." | "You're still on that?" | "I hope it comes."

```text
POOL   dialogue key: dialogue.conversations.hopes.revisit
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.arc.hopes.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.revisit.opens`: the villager reminisces. Subject `hopes.wish`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `hopes:named`, `arc:resumed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.hopes.revisit/1   [109 chars]
    en  I've thought more about what I said I was hoping for. There's one wish under all the others I didn't mention.
    >>  ............................................
    pt  Pensei mais sobre o que eu disse que esperava. Tem um desejo embaixo de todos os outros que eu não mencionei.
    >>  ............................................
  dialogue.conversations.hopes.revisit/2   [103 chars]
    en  Remember I told you my hopes, %1$s? I left the biggest one off the list. Maybe another day I'll say it.
    >>  ............................................
    pt  Lembra que eu te contei as minhas esperanças, %1$s? Deixei a maior de fora da lista. Talvez outro dia eu diga.
    >>  ............................................
  dialogue.conversations.hopes.revisit/3   [97 chars]
    en  Been turning my hopes over since you asked. Funny how naming them makes them feel closer to real.
    >>  ............................................
    pt  Ando revirando minhas esperanças desde que você perguntou. Engraçado como nomear elas faz parecerem mais reais.
    >>  ............................................
```


**Outcome 9 of 11** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.hopes` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `hopes` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `first` budget `deep`
- Does: remembers `mcaconversations.topic.hopes` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.respond`
- …where the player's next choices will be: "I'm listening." | "I hope you get it." | "That's a bit much to hope for." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.first
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.first.to.hopes`: the villager accepts. Subject `hopes`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.hopes.first/1   [129 chars]
    en  Hoping for? A good harvest, a quiet winter, and to see the people I love do well. Simple things, %1$s. The best ones usually are.
    >>  ............................................
    pt  Esperando? Uma boa colheita, um inverno tranquilo, e ver quem eu amo bem de vida. Coisas simples, %1$s. As melhores costumam ser.
    >>  ............................................
  dialogue.conversations.hopes.first/2   [126 chars]
    en  I try not to hope too loud — but if you're asking, I'd like the roof to hold another year and the little ones to grow up kind.
    >>  ............................................
    pt  Tento não ter esperança alto demais — mas já que perguntou, queria que o telhado aguentasse mais um ano e que os pequenos crescessem gentis.
    >>  ............................................
  dialogue.conversations.hopes.first/3   [115 chars]
    en  What am I hoping for? That things stay steady. That the ones who left come back. That's enough to be going on with.
    >>  ............................................
    pt  O que eu espero? Que as coisas fiquem estáveis. Que quem foi embora volte. Já dá pra ir levando com isso.
    >>  ............................................
```


**Outcome 10 of 11** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.hopes` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `hopes` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.hopes` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `hopes` branch `again_open` budget `deep`
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.topic.hopes.respond`
- …where the player's next choices will be: "I'm listening." | "I hope you get it." | "That's a bit much to hope for." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.hopes.revisit
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.topic.hopes.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.revisit.to.hopes`: the villager accepts. Subject `hopes`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `hopes`** earlier in this file. Fill it in there, once.


**Outcome 11 of 11** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.topic.hopes` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.hopes` (this player only) for 48000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.first
WHO    VILLAGER — what the player reads after pressing "What are you hoping for?"
       spoken on: conversations.cat.personal, button `hopes`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.first.terminal`: the villager accepts. Subject `hopes.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.personal` / button `hopes`** earlier in this file. Fill it in there, once.


### Button `feelings` — "How do you really feel about me?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.feelings` — accepted phrasings: "how do you feel"; "how are you feeling"; "what are you feeling"; "your feelings"
  - the message must contain one of: `feel`, `emotion`, `mood`
  - scored words: `feel`(1.5), `emotion`(1.0), `mood`(0.8), `okay`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.feelings
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.feelings   [32 chars]
    en  How do you really feel about me?
    >>  ............................................
    pt  O que você realmente sente por mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.feelings.a_flat_stretch"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.feelings.a_flat_stretch", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 36000 ticks
- Then opens: `conversations.scene.feelings.a_flat_stretch.respond`
- …where the player's next choices will be: "That sounds wearing." | "What helps, when it's like this?" | "Thanks for saying."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.a_flat_stretch
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.scene.feelings.a_flat_stretch.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.a_flat_stretch.open`: the villager reports. Subject `feelings.low`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.a_flat_stretch/1   [92 chars]
    en  Flat. Not sad, which people always want it to be, because sad has a shape and this does not.
    >>  ............................................
    pt  Chata. Não triste, que é o que as pessoas sempre querem que seja, porque triste tem formato e isso não tem.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch/2   [95 chars]
    en  I am doing everything and enjoying none of it, and I have been like that for about eleven days.
    >>  ............................................
    pt  Estou fazendo tudo e não estou gostando de nada, e estou assim há uns onze dias.
    >>  ............................................
  dialogue.conversations.scene.feelings.a_flat_stretch/3   [96 chars]
    en  Fine in the sense that nothing is wrong, and something is wrong, and both of those are accurate.
    >>  ............................................
    pt  Bem no sentido de que nada está errado, e alguma coisa está errada, e as duas afirmações são exatas.
    >>  ............................................
```


**Outcome 2 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.feelings.ordinary_answer"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.feelings.ordinary_answer", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 36000 ticks
- Then opens: `conversations.scene.feelings.ordinary_answer.respond`
- …where the player's next choices will be: "What made today good?" | "Glad to hear it." | "Thanks for saying."

```text
POOL   dialogue key: dialogue.conversations.scene.feelings.ordinary_answer
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.scene.feelings.ordinary_answer.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.ordinary_answer.open`: the villager reports. Subject `feelings.steady`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:feelings` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, restraint, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.feelings.ordinary_answer/1   [88 chars]
    en  Middling, which is where I like to be. The interesting weeks have all been the bad ones.
    >>  ............................................
    pt  Mediana, que é onde eu gosto de estar. As semanas interessantes foram todas as ruins.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer/2   [90 chars]
    en  Well enough. Ask me on a day when something has happened and you will get a longer answer.
    >>  ............................................
    pt  Bem o bastante. Me pergunte num dia em que algo aconteceu e você recebe uma resposta mais longa.
    >>  ............................................
  dialogue.conversations.scene.feelings.ordinary_answer/3   [84 chars]
    en  Good, and I checked before I said it, which is more than that question usually gets.
    >>  ............................................
    pt  Bem, e eu conferi antes de dizer, o que já é mais do que essa pergunta costuma receber.
    >>  ............................................
```


**Outcome 3 of 11** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.feelings` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `again` budget `relationship`
- Then opens: `conversations.topic.feelings.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Say it again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.feelings.again
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.again.to.feelings.again`: the villager accepts. Subject `feelings.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.again/1   [63 chars]
    en  I laid my heart on the table already. Give it a day to recover.
    >>  ............................................
    pt  Já coloquei meu coração na mesa. Deixa ele se recuperar um dia.
    >>  ............................................
  dialogue.conversations.feelings.again/2   [48 chars]
    en  My heart's still where it was an hour ago, %1$s.
    >>  ............................................
    pt  Meu coração continua onde estava uma hora atrás, %1$s.
    >>  ............................................
  dialogue.conversations.feelings.again/3   [65 chars]
    en  Don't spend a good answer twice in one day. Let it earn interest.
    >>  ............................................
    pt  Não gasta uma resposta boa duas vezes no mesmo dia. Deixa render juros.
    >>  ............................................
```


**Outcome 4 of 11** — base weight `0`

- Fires when: weighted +100 when arc `feelings` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `resume` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.arc.feelings.resume.respond`
- …where the player's next choices will be: "Has anything changed for you?" | "I meant what I said." | "I'll let it be."

```text
POOL   dialogue key: dialogue.conversations.feelings.revisit
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.arc.feelings.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.revisit.to.feelings`: the villager accepts. Subject `feelings`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.revisit/1   [78 chars]
    en  I've been thinking about what I said to you. I meant it, in case you wondered.
    >>  ............................................
    pt  Andei pensando no que eu te disse. Eu falei sério, caso você tenha ficado em dúvida.
    >>  ............................................
  dialogue.conversations.feelings.revisit/2   [67 chars]
    en  That conversation we had. It's been sitting with me, in a good way.
    >>  ............................................
    pt  Aquela conversa que a gente teve. Ficou comigo, de um jeito bom.
    >>  ............................................
  dialogue.conversations.feelings.revisit/3   [64 chars]
    en  You'll remember what I told you. I've not changed my mind, %1$s.
    >>  ............................................
    pt  Você deve lembrar do que eu te contei. Não mudei de ideia, %1$s.
    >>  ............................................
```


**Outcome 5 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `toddler` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.toddler.respond`
- …where the player's next choices will be: "Those are good feelings to have." | "What does it feel like?" | "Off you go and feel it, then."

```text
POOL   dialogue key: dialogue.conversations.feelings.toddler
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.toddler.to.feelings.toddler`: the villager accepts. Subject `feelings.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.toddler/1   [44 chars]
    en  I feel... hungry. And a little like dancing.
    >>  ............................................
    pt  Eu tô me sentindo... com fome. E com um pouquinho de vontade de dançar.
    >>  ............................................
  dialogue.conversations.feelings.toddler/2   [49 chars]
    en  Happy! 'Cept my sock has a hole. So mostly happy.
    >>  ............................................
    pt  Feliz! Só que minha meia tem um furo. Então quase tudo feliz.
    >>  ............................................
  dialogue.conversations.feelings.toddler/3   [33 chars]
    en  I dunno. Warm? Is warm a feeling?
    >>  ............................................
    pt  Sei lá. Quentinho? Quentinho é um sentimento?
    >>  ............................................
```


**Outcome 6 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `child` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.young.respond`
- …where the player's next choices will be: "You're a good sort, you know." | "I'm glad you're around." | "That's a bit much." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.feelings.child
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.child.to.feelings.young`: the villager accepts. Subject `feelings.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.child/1   [42 chars]
    en  Good! Unless it's bath day. Then betrayed.
    >>  ............................................
    pt  Bem! A não ser que seja dia de banho. Aí traído.
    >>  ............................................
  dialogue.conversations.feelings.child/2   [59 chars]
    en  I'm happy when I'm outside and grumpy when I'm not. Simple.
    >>  ............................................
    pt  Fico feliz quando tô lá fora e emburrado quando não tô. Simples.
    >>  ............................................
```


**Outcome 7 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `teen` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.young.respond`
- …where the player's next choices will be: "You're a good sort, you know." | "I'm glad you're around." | "That's a bit much." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.feelings.teen
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.teen.to.feelings.young`: the villager accepts. Subject `feelings.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.teen/1   [61 chars]
    en  That's kind of personal. ...Fine. Bit stormy lately, alright?
    >>  ............................................
    pt  Isso é meio pessoal. ...Tá bom. Meio tempestuoso ultimamente, satisfeito?
    >>  ............................................
  dialogue.conversations.feelings.teen/2   [42 chars]
    en  I'm FINE. Why does everyone keep checking?
    >>  ............................................
    pt  Eu tô BEM. Por que todo mundo fica perguntando?
    >>  ............................................
```


**Outcome 8 of 11** — base weight `0`

- Fires when: weighted +100 when `constraints` = "spouse,adult"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `romantic` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.romantic.respond`
- …where the player's next choices will be: "I feel the same about you." | "What do you need from me?" | "There's something we should talk about." | "I'll let that sit."

```text
POOL   dialogue key: dialogue.conversations.feelings.spouse
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.romantic.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.spouse.to.feelings.romantic`: the villager accepts. Subject `feelings.romantic`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.spouse/1   [64 chars]
    en  You're my whole ledger, %1$s. Every column. Even the messy ones.
    >>  ............................................
    pt  Você é o meu livro-caixa inteiro, %1$s. Todas as colunas. Até as bagunçadas.
    >>  ............................................
  dialogue.conversations.feelings.spouse/2   [74 chars]
    en  Married you, didn't I? But since you're asking — I'd do it again tomorrow.
    >>  ............................................
    pt  Casei com você, não casei? Mas já que perguntou — casaria de novo amanhã.
    >>  ............................................
  dialogue.conversations.feelings.spouse/3   [70 chars]
    en  I chose you on purpose, %1$s. I keep choosing you every morning since.
    >>  ............................................
    pt  Escolhi você de propósito, %1$s. E sigo te escolhendo toda manhã desde então.
    >>  ............................................
```


**Outcome 9 of 11** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`
- Fires when: RULED OUT when `constraints` = "spouse"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `guarded` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.guarded.respond`
- …where the player's next choices will be: "Fair. We're not there yet." | "Then something easier." | "You can tell me, though." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.deflect.intimate
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   5 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.intimate.to.feelings.guarded`: the villager deflects. Subject `feelings.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / regrets
```

```text
  dialogue.conversations.deflect.intimate/1   [74 chars]
    en  That question belongs to someone I trust with everything. We're not there.
    >>  ............................................
    pt  Essa pergunta é de alguém em quem eu confio tudo. A gente não chegou lá.
    >>  ............................................
  dialogue.conversations.deflect.intimate/2   [86 chars]
    en  Ask me again when you've earned it, %1$s. That one costs more than hearts on a sleeve.
    >>  ............................................
    pt  Me pergunte de novo quando tiver merecido, %1$s. Essa custa mais que coração na manga.
    >>  ............................................
  dialogue.conversations.deflect.intimate/3   [50 chars]
    en  Some doors open slowly. Keep showing up and maybe.
    >>  ............................................
    pt  Algumas portas abrem devagar. Continue aparecendo e quem sabe.
    >>  ............................................
  dialogue.conversations.deflect.intimate/4   [90 chars]
    en  There are maybe two people alive who get that answer. Working on whether you're the third.
    >>  ............................................
    pt  Existem umas duas pessoas vivas que recebem essa resposta. Ainda estou decidindo se você é a terceira.
    >>  ............................................
  dialogue.conversations.deflect.intimate/5   [64 chars]
    en  Not yet. And 'yet' is doing a lot of kind work in that sentence.
    >>  ............................................
    pt  Ainda não. E esse "ainda" está trabalhando bastante nessa frase.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.deflect.intimate/1
    en  Oh, that's a big one. If I get it wrong I'll think about it for weeks. Let me have some time.
    >>  ............................................
    pt  Ah, essa é grande. Se eu errar vou pensar nisso por semanas. Me dá um tempo.
    >>  ............................................
  anxious.dialogue.conversations.deflect.intimate/2
    en  I want to answer that properly, and properly takes me longer than most. Please be patient.
    >>  ............................................
    pt  Eu quero responder isso direito, e direito leva mais tempo pra mim do que pra maioria. Tenha paciência, por favor.
    >>  ............................................
  athletic.dialogue.conversations.deflect.intimate/1
    en  That question's a marathon and we're still on morning stretches. Keep showing up. You're pacing well.
    >>  ............................................
    pt  Essa pergunta é uma maratona e a gente ainda está no alongamento matinal. Continue aparecendo. Seu ritmo está bom.
    >>  ............................................
  athletic.dialogue.conversations.deflect.intimate/2
    en  That's the far end of a long course. We're pacing there. Not yet.
    >>  ............................................
    pt  Essa é a ponta distante de um percurso longo. A gente está indo pra lá. Ainda não.
    >>  ............................................
  confident.dialogue.conversations.deflect.intimate/1
    en  Even I don't hand that answer to just anyone, %1$s. Prove yourself first. I suspect you will.
    >>  ............................................
    pt  Nem eu entrego essa resposta a qualquer um, %1$s. Prove-se primeiro. Desconfio que você vai.
    >>  ............................................
  confident.dialogue.conversations.deflect.intimate/2
    en  That one stays locked till you've earned the key. You're close.
    >>  ............................................
    pt  Essa fica trancada até você merecer a chave. Você está perto.
    >>  ............................................
  crabby.dialogue.conversations.deflect.intimate/1
    en  Absolutely not. ...Ask me again in a year and I might have softened. Might.
    >>  ............................................
    pt  Absolutamente não. ...Me pergunte daqui a um ano e talvez eu tenha amolecido. Talvez.
    >>  ............................................
  crabby.dialogue.conversations.deflect.intimate/2
    en  You're pushing it, %1$s. I'll allow it — but I'm still not answering.
    >>  ............................................
    pt  Você está forçando, %1$s. Vou permitir — mas ainda não vou responder.
    >>  ............................................
  extroverted.dialogue.conversations.deflect.intimate/1
    en  Now that's a quiet question, and I'm not much good at quiet yet. Give me time and you'll get it.
    >>  ............................................
    pt  Agora essa é uma pergunta silenciosa, e eu ainda não sou muito bom em silêncio. Me dá tempo que você recebe.
    >>  ............................................
  extroverted.dialogue.conversations.deflect.intimate/2
    en  I talk a great deal and say very little about that. Not today, %1$s. Someday.
    >>  ............................................
    pt  Eu falo muitíssimo e digo muito pouco sobre isso. Hoje não, %1$s. Um dia.
    >>  ............................................
  flirty.dialogue.conversations.deflect.intimate/1
    en  Sweet thing, that question is fourth-date material and you know it.
    >>  ............................................
    pt  Meu bem, essa pergunta é material de quarto encontro e você sabe disso.
    >>  ............................................
  flirty.dialogue.conversations.deflect.intimate/2
    en  Ask me somewhere more private, some evening you've properly earned. Not yet — but oh, maybe.
    >>  ............................................
    pt  Me pergunte num lugar mais reservado, numa noite que você tenha merecido direito. Ainda não — mas ah, quem sabe.
    >>  ............................................
  friendly.dialogue.conversations.deflect.intimate/1
    en  That's a story for old friends by a low fire, %1$s. We're getting there. Keep coming around, I mean it.
    >>  ............................................
    pt  Essa é história pra amigo antigo perto do fogo baixo, %1$s. A gente está chegando lá. Continue aparecendo, eu falo sério.
    >>  ............................................
  friendly.dialogue.conversations.deflect.intimate/2
    en  Ask me when you've a key to my kitchen. We're close — not quite THAT close yet.
    >>  ............................................
    pt  Me pergunte quando você tiver a chave da minha cozinha. A gente é próximo — mas ainda não TÃO próximo.
    >>  ............................................
  gloomy.dialogue.conversations.deflect.intimate/1
    en  You want the deep water? You haven't even gotten your boots wet, %1$s.
    >>  ............................................
    pt  Você quer a água funda? Você nem molhou as botas ainda, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.deflect.intimate/2
    en  There's nothing down there worth the swim, %1$s. Take my word for it and ask me something else.
    >>  ............................................
    pt  Não tem nada lá no fundo que valha o mergulho, %1$s. Acredita em mim e me pergunta outra coisa.
    >>  ............................................
  greedy.dialogue.conversations.deflect.intimate/1
    en  Ha — that one's in the locked chest under the locked chest. Keep investing, %1$s. You're the rare account I hope matures.
    >>  ............................................
    pt  Ha — essa está no baú trancado embaixo do baú trancado. Continue investindo, %1$s. Você é a conta rara que eu espero que amadureça.
    >>  ............................................
  greedy.dialogue.conversations.deflect.intimate/2
    en  Some things aren't stock, they're collateral. I don't put those on the counter, %1$s.
    >>  ............................................
    pt  Algumas coisas não são estoque, são garantia. Dessas eu não ponho no balcão, %1$s.
    >>  ............................................
  grumpy.dialogue.conversations.deflect.intimate/1
    en  Absolutely not. ...Ask me again in a year and I might have softened. Might.
    >>  ............................................
    pt  Absolutamente não. ...Me pergunte daqui a um ano e talvez eu tenha amolecido. Talvez.
    >>  ............................................
  grumpy.dialogue.conversations.deflect.intimate/2
    en  You're pushing it, %1$s. I'll allow it — but I'm still not answering.
    >>  ............................................
    pt  Você está forçando, %1$s. Vou permitir — mas ainda não vou responder.
    >>  ............................................
  introverted.dialogue.conversations.deflect.intimate/1
    en  That's the deepest room in the house, %1$s, and I don't open it often. Be patient with me and I might.
    >>  ............................................
    pt  Esse é o cômodo mais fundo da casa, %1$s, e eu não abro com frequência. Tenha paciência comigo e talvez eu abra.
    >>  ............................................
  introverted.dialogue.conversations.deflect.intimate/2
    en  You're asking for something I've shown almost no one. Someday. Not today.
    >>  ............................................
    pt  Você está pedindo algo que eu mostrei a quase ninguém. Um dia. Hoje não.
    >>  ............................................
  lazy.dialogue.conversations.deflect.intimate/1
    en  That's deep, and I don't do deep quickly. Give it a season or two, %1$s.
    >>  ............................................
    pt  Essa é funda, e eu não faço fundo com pressa. Dá uma estação ou duas, %1$s.
    >>  ............................................
  lazy.dialogue.conversations.deflect.intimate/2
    en  You'll get there. Just not at this speed. Ask me again sometime.
    >>  ............................................
    pt  Você chega lá. Só não nessa velocidade. Me pergunte de novo qualquer hora.
    >>  ............................................
  odd.dialogue.conversations.deflect.intimate/1
    en  That one I've only ever told the well, and the well earned it over years. Keep coming around, %1$s. You're getting well-shaped.
    >>  ............................................
    pt  Essa eu só contei pro poço, e o poço levou anos pra merecer. Continue aparecendo, %1$s. Você está ficando com formato de poço.
    >>  ............................................
  odd.dialogue.conversations.deflect.intimate/2
    en  The bees know a piece of that one. You'll have to work up through the bees first, %1$s.
    >>  ............................................
    pt  As abelhas sabem um pedaço dessa. Você vai ter que subir pelas abelhas primeiro, %1$s.
    >>  ............................................
  peaceful.dialogue.conversations.deflect.intimate/1
    en  That's a still-water question, and I'd want to answer it well. Give it time and you'll have it.
    >>  ............................................
    pt  Essa é uma pergunta de água parada, e eu ia querer responder bem. Dê tempo e você terá.
    >>  ............................................
  peaceful.dialogue.conversations.deflect.intimate/2
    en  You're asking gently, and I appreciate that. I'm still not ready. Soon, perhaps.
    >>  ............................................
    pt  Você está perguntando com delicadeza, e eu agradeço. Ainda não estou pronto. Em breve, quem sabe.
    >>  ............................................
  peppy.dialogue.conversations.deflect.intimate/1
    en  That one lives on the bottom shelf of the heart! We're still up top — great shelf! — but keep climbing, %1$s!
    >>  ............................................
    pt  Essa mora na prateleira de baixo do coração! A gente ainda está lá em cima — prateleira ótima! — mas continua escalando, %1$s!
    >>  ............................................
  peppy.dialogue.conversations.deflect.intimate/2
    en  NOPE! Not that one! Ask me anything else and I'll answer it twice, %1$s!
    >>  ............................................
    pt  NEM! Essa não! Me pergunta qualquer outra e eu respondo duas vezes, %1$s!
    >>  ............................................
  playful.dialogue.conversations.deflect.intimate/1
    en  Straight for the deep end! Bold. I'm not diving in yet, but I like that you tried.
    >>  ............................................
    pt  Direto pra parte funda! Ousado. Ainda não vou mergulhar, mas gostei que você tentou.
    >>  ............................................
  playful.dialogue.conversations.deflect.intimate/2
    en  That one's locked up tight. Win it off me sometime and it's yours.
    >>  ............................................
    pt  Essa está bem trancada. Ganha ela de mim qualquer dia e é sua.
    >>  ............................................
  relaxed.dialogue.conversations.deflect.intimate/1
    en  That's deep, and I don't do deep quickly. Give it a season or two, %1$s.
    >>  ............................................
    pt  Essa é funda, e eu não faço fundo com pressa. Dá uma estação ou duas, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.deflect.intimate/2
    en  You'll get there. Just not at this speed. Ask me again sometime.
    >>  ............................................
    pt  Você chega lá. Só não nessa velocidade. Me pergunte de novo qualquer hora.
    >>  ............................................
  sensitive.dialogue.conversations.deflect.intimate/1
    en  Gently, %1$s... not yet. That answer lives somewhere very soft. Keep being how you are, and one day it walks out on its own.
    >>  ............................................
    pt  Com carinho, %1$s... ainda não. Essa resposta mora num lugar muito macio. Continue sendo como você é, e um dia ela sai sozinha.
    >>  ............................................
  sensitive.dialogue.conversations.deflect.intimate/2
    en  I'd cry somewhere in the middle of answering that, %1$s. Let me get steadier about it first.
    >>  ............................................
    pt  Eu ia chorar no meio da resposta, %1$s. Me deixa ficar mais firme sobre isso antes.
    >>  ............................................
  shy.dialogue.conversations.deflect.intimate/1
    en  That's the deepest room in the house, %1$s, and I don't open it often. Be patient with me and I might.
    >>  ............................................
    pt  Esse é o cômodo mais fundo da casa, %1$s, e eu não abro com frequência. Tenha paciência comigo e talvez eu abra.
    >>  ............................................
  shy.dialogue.conversations.deflect.intimate/2
    en  You're asking for something I've shown almost no one. Someday. Not today.
    >>  ............................................
    pt  Você está pedindo algo que eu mostrei a quase ninguém. Um dia. Hoje não.
    >>  ............................................
  upbeat.dialogue.conversations.deflect.intimate/1
    en  That's deep water, %1$s, and I'd want to wade in properly. Give it time. I'm not going anywhere.
    >>  ............................................
    pt  Essa é água funda, %1$s, e eu ia querer entrar direito. Dê tempo. Não vou a lugar nenhum.
    >>  ............................................
  upbeat.dialogue.conversations.deflect.intimate/2
    en  You're asking the real questions now. I like that. I'm just not ready to answer that one — yet.
    >>  ............................................
    pt  Agora você está fazendo as perguntas de verdade. Gosto disso. Só ainda não estou pronto pra essa — ainda.
    >>  ............................................
  witty.dialogue.conversations.deflect.intimate/1
    en  That's deep water, %1$s, and I'd want to wade in properly. Give it time. I'm not going anywhere.
    >>  ............................................
    pt  Essa é água funda, %1$s, e eu ia querer entrar direito. Dê tempo. Não vou a lugar nenhum.
    >>  ............................................
  witty.dialogue.conversations.deflect.intimate/2
    en  You're asking the real questions now. I like that. I'm just not ready to answer that one — yet.
    >>  ............................................
    pt  Agora você está fazendo as perguntas de verdade. Gosto disso. Só ainda não estou pronto pra essa — ainda.
    >>  ............................................
```

</details>


**Outcome 10 of 11** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when `constraints` = "spouse"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.feelings` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when arc `feelings` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `feelings` branch `platonic` budget `relationship`
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.topic.feelings.platonic.respond`
- …where the player's next choices will be: "You matter to me." | "And how do you feel about me?" | "Let's not get sentimental." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.feelings.first
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.topic.feelings.platonic.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.first.to.feelings.platonic`: the villager accepts. Subject `feelings.platonic`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.feelings.first/1   [77 chars]
    en  You're really asking? Fine. You're the part of my day I don't complain about.
    >>  ............................................
    pt  Você está perguntando de verdade? Tudo bem. Você é a parte do meu dia da qual eu não reclamo.
    >>  ............................................
  dialogue.conversations.feelings.first/2   [54 chars]
    en  I notice when you don't come around. That's my answer.
    >>  ............................................
    pt  Eu percebo quando você não aparece. Essa é a minha resposta.
    >>  ............................................
  dialogue.conversations.feelings.first/3   [81 chars]
    en  When you leave, the room gets quieter than it should. Make of that what you will.
    >>  ............................................
    pt  Quando você vai embora, o cômodo fica mais quieto do que deveria. Tire suas conclusões.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.feelings.first/1
    en  You don't make me nervous, %1$s. Do you know how rare that is? I've stopped rehearsing before you arrive.
    >>  ............................................
    pt  Você não me deixa nervoso, %1$s. Você sabe como isso é raro? Parei de ensaiar antes de você chegar.
    >>  ............................................
  anxious.dialogue.conversations.feelings.first/2
    en  I worry less when you're here. That's the plainest way I can put it, and it's a great deal.
    >>  ............................................
    pt  Eu me preocupo menos quando você está aqui. É o jeito mais simples de dizer, e é muita coisa.
    >>  ............................................
  athletic.dialogue.conversations.feelings.first/1
    en  You're the only one who keeps up with me, %1$s. In every way that counts. That's rarer than you think.
    >>  ............................................
    pt  Você é o único que acompanha o meu passo, %1$s. Em todos os sentidos que importam. Isso é mais raro do que você pensa.
    >>  ............................................
  athletic.dialogue.conversations.feelings.first/2
    en  You're the one I'd slow down for. Don't go telling my legs, %1$s.
    >>  ............................................
    pt  Você é a pessoa por quem eu diminuiria o ritmo. Não vai contar pras minhas pernas, %1$s.
    >>  ............................................
  confident.dialogue.conversations.feelings.first/1
    en  I have excellent taste, %1$s, and I've decided you're worth keeping around. Take that as the honor it is.
    >>  ............................................
    pt  Tenho um gosto excelente, %1$s, e decidi que vale a pena manter você por perto. Receba isso como a honra que é.
    >>  ............................................
  confident.dialogue.conversations.feelings.first/2
    en  You don't fawn, and that's exactly why I can't stop looking your way.
    >>  ............................................
    pt  Você não me bajula, e é exatamente por isso que eu não consigo parar de olhar na sua direção.
    >>  ............................................
  crabby.dialogue.conversations.feelings.first/1
    en  I don't do this. ...You don't annoy me, %1$s. From me, that's practically a declaration.
    >>  ............................................
    pt  Eu não faço esse tipo de coisa. ...Você não me irrita, %1$s. Vindo de mim, é praticamente uma declaração.
    >>  ............................................
  crabby.dialogue.conversations.feelings.first/2
    en  I'm short with everyone. I'm less short with you. Work out what that means yourself.
    >>  ............................................
    pt  Sou seco com todo mundo. Sou menos seco com você. Tire suas próprias conclusões.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.first/1
    en  I talk to everyone, %1$s — but I look forward to you. That's the difference, and it's a big one.
    >>  ............................................
    pt  Eu falo com todo mundo, %1$s — mas eu fico ansioso por você. É essa a diferença, e é grande.
    >>  ............................................
  extroverted.dialogue.conversations.feelings.first/2
    en  Of all the people I chase conversation with, you're the one I'd wait for. That's saying a lot.
    >>  ............................................
    pt  De todas as pessoas atrás de quem eu corro pra conversar, você é a que eu esperaria. Isso diz muito.
    >>  ............................................
  flirty.dialogue.conversations.feelings.first/1
    en  Finally, the interesting questions. Let's just say you're not nothing, %1$s. Far from it.
    >>  ............................................
    pt  Enfim, as perguntas interessantes. Digamos que você não é qualquer coisa, %1$s. Longe disso.
    >>  ............................................
  flirty.dialogue.conversations.feelings.first/2
    en  I flirt with everyone, darling. I mean it with you. That's the difference.
    >>  ............................................
    pt  Eu paquero todo mundo, querido. Com você eu falo sério. É essa a diferença.
    >>  ............................................
  friendly.dialogue.conversations.feelings.first/1
    en  Oh, you're family already, %1$s — didn't you know? The door's never locked for you.
    >>  ............................................
    pt  Ah, você já é da família, %1$s — você não sabia? A porta nunca está trancada pra você.
    >>  ............................................
  friendly.dialogue.conversations.feelings.first/2
    en  The day's always brighter with you in it. I've thought so a good while now.
    >>  ............................................
    pt  O dia é sempre mais claro com você nele. Penso isso faz um bom tempo.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.first/1
    en  You make the corridor of Tuesdays feel shorter. Don't make me repeat that.
    >>  ............................................
    pt  Você faz o corredor de terças parecer mais curto. Não me faça repetir isso.
    >>  ............................................
  gloomy.dialogue.conversations.feelings.first/2
    en  I've started looking up when the gate goes. I didn't used to look up, %1$s.
    >>  ............................................
    pt  Comecei a levantar a cabeça quando o portão range. Eu não levantava a cabeça antes, %1$s.
    >>  ............................................
  greedy.dialogue.conversations.feelings.first/1
    en  I ran the numbers, %1$s. Time with you: costly. Returns: unreasonable. You're the one thing I'd overpay for. Don't quote me.
    >>  ............................................
    pt  Fiz as contas, %1$s. Tempo com você: caro. Retorno: absurdo. Você é a única coisa pela qual eu pagaria a mais. Não me cite.
    >>  ............................................
  greedy.dialogue.conversations.feelings.first/2
    en  You've never once asked me what something's worth, %1$s. Do you know how restful that is?
    >>  ............................................
    pt  Você nunca me perguntou quanto vale alguma coisa, %1$s. Sabe como isso é descansado?
    >>  ............................................
  grumpy.dialogue.conversations.feelings.first/1
    en  I don't do this. ...You don't annoy me, %1$s. From me, that's practically a declaration.
    >>  ............................................
    pt  Eu não faço esse tipo de coisa. ...Você não me irrita, %1$s. Vindo de mim, é praticamente uma declaração.
    >>  ............................................
  grumpy.dialogue.conversations.feelings.first/2
    en  I'm short with everyone. I'm less short with you. Work out what that means yourself.
    >>  ............................................
    pt  Sou seco com todo mundo. Sou menos seco com você. Tire suas próprias conclusões.
    >>  ............................................
  introverted.dialogue.conversations.feelings.first/1
    en  You're the one person I don't have to prepare for, %1$s. Given how I'm made, that's a considerable thing to say.
    >>  ............................................
    pt  Você é a única pessoa pra quem eu não preciso me preparar, %1$s. Do jeito que eu sou feito, isso é uma coisa considerável de se dizer.
    >>  ............................................
  introverted.dialogue.conversations.feelings.first/2
    en  I don't need to gather myself before you arrive. I've never managed that with anyone else.
    >>  ............................................
    pt  Eu não preciso me recompor antes de você chegar. Nunca consegui isso com ninguém.
    >>  ............................................
  lazy.dialogue.conversations.feelings.first/1
    en  I like you, %1$s. No great speech about it — I just do, steadily, and that's the kind that lasts.
    >>  ............................................
    pt  Eu gosto de você, %1$s. Sem grande discurso — eu simplesmente gosto, de forma constante, e é esse tipo que dura.
    >>  ............................................
  lazy.dialogue.conversations.feelings.first/2
    en  You're easy company. Coming from me, that's about the highest thing I say about anyone.
    >>  ............................................
    pt  Você é companhia fácil. Vindo de mim, é quase a coisa mais alta que eu digo de alguém.
    >>  ............................................
  odd.dialogue.conversations.feelings.first/1
    en  The candles burn straighter when you're here, %1$s. I checked three times. You make the room make sense. Don't tell the candles.
    >>  ............................................
    pt  As velas queimam mais retas quando você está aqui, %1$s. Conferi três vezes. Você faz o cômodo fazer sentido. Não conta pras velas.
    >>  ............................................
  odd.dialogue.conversations.feelings.first/2
    en  I've stopped needing to count things when you're here, %1$s. I didn't notice until it stopped.
    >>  ............................................
    pt  Eu parei de precisar contar as coisas quando você está aqui, %1$s. Só reparei quando parou.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.first/1
    en  You're easy to be near, %1$s. I don't have to hold anything steady when you're here — it just is.
    >>  ............................................
    pt  Você é fácil de ter por perto, %1$s. Não preciso segurar nada firme quando você está aqui — simplesmente está.
    >>  ............................................
  peaceful.dialogue.conversations.feelings.first/2
    en  There's a quiet I only get in certain company. You're that company.
    >>  ............................................
    pt  Existe um silêncio que eu só tenho em certas companhias. Você é essa companhia.
    >>  ............................................
  peppy.dialogue.conversations.feelings.first/1
    en  You're top three, %1$s! People-wise! Okay, fine, top ONE. Don't make it weird, I have chores!
    >>  ............................................
    pt  Você é top três, %1$s! Em pessoas! Tá bom, top UM. Não deixa esquisito, eu tenho tarefas!
    >>  ............................................
  peppy.dialogue.conversations.feelings.first/2
    en  I save the best bit of every day to tell you about, %1$s. That's all. That's the whole feeling.
    >>  ............................................
    pt  Eu guardo a melhor parte de cada dia pra te contar, %1$s. É só isso. É esse o sentimento inteiro.
    >>  ............................................
  playful.dialogue.conversations.feelings.first/1
    en  You? You're my favourite. You laugh at the right bits and you never tell on me, %1$s.
    >>  ............................................
    pt  Você? Você é o meu favorito. Ri nas partes certas e nunca me entrega, %1$s.
    >>  ............................................
  playful.dialogue.conversations.feelings.first/2
    en  I tease everyone. I only bother teasing you properly. Make of that what you will.
    >>  ............................................
    pt  Eu provoco todo mundo. Só me dou o trabalho de provocar você direito. Tire suas conclusões.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.first/1
    en  I like you, %1$s. No great speech about it — I just do, steadily, and that's the kind that lasts.
    >>  ............................................
    pt  Eu gosto de você, %1$s. Sem grande discurso — eu simplesmente gosto, de forma constante, e é esse tipo que dura.
    >>  ............................................
  relaxed.dialogue.conversations.feelings.first/2
    en  You're easy company. Coming from me, that's about the highest thing I say about anyone.
    >>  ............................................
    pt  Você é companhia fácil. Vindo de mim, é quase a coisa mais alta que eu digo de alguém.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.first/1
    en  You listen with your whole face, %1$s. Do you know how rare that is? Around you I don't have to be the strong one.
    >>  ............................................
    pt  Você escuta com o rosto inteiro, %1$s. Sabe como isso é raro? Perto de você eu não preciso ser o forte.
    >>  ............................................
  sensitive.dialogue.conversations.feelings.first/2
    en  With you I get to set the weight down. You've no idea what that's worth to someone like me.
    >>  ............................................
    pt  Com você eu posso pousar o peso. Você não faz ideia do que isso vale pra alguém como eu.
    >>  ............................................
  shy.dialogue.conversations.feelings.first/1
    en  You're the one person I don't have to prepare for, %1$s. Given how I'm made, that's a considerable thing to say.
    >>  ............................................
    pt  Você é a única pessoa pra quem eu não preciso me preparar, %1$s. Do jeito que eu sou feito, isso é uma coisa considerável de se dizer.
    >>  ............................................
  shy.dialogue.conversations.feelings.first/2
    en  I don't need to gather myself before you arrive. I've never managed that with anyone else.
    >>  ............................................
    pt  Eu não preciso me recompor antes de você chegar. Nunca consegui isso com ninguém.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.first/1
    en  Honestly? You brighten the day when you turn up, %1$s. I don't see the point in pretending otherwise.
    >>  ............................................
    pt  Sinceramente? Você ilumina o dia quando aparece, %1$s. Não vejo sentido em fingir o contrário.
    >>  ............................................
  upbeat.dialogue.conversations.feelings.first/2
    en  I'm always glad to see you. That's the plain truth of it, and I'm happy to say it out loud.
    >>  ............................................
    pt  Fico sempre contente de te ver. É essa a verdade simples, e eu digo com gosto em voz alta.
    >>  ............................................
  witty.dialogue.conversations.feelings.first/1
    en  Honestly? You brighten the day when you turn up, %1$s. I don't see the point in pretending otherwise.
    >>  ............................................
    pt  Sinceramente? Você ilumina o dia quando aparece, %1$s. Não vejo sentido em fingir o contrário.
    >>  ............................................
  witty.dialogue.conversations.feelings.first/2
    en  I'm always glad to see you. That's the plain truth of it, and I'm happy to say it out loud.
    >>  ............................................
    pt  Fico sempre contente de te ver. É essa a verdade simples, e eu digo com gosto em voz alta.
    >>  ............................................
```

</details>


**Outcome 11 of 11** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 6
- Does: remembers `mcaconversations.cooldown.feelings` (this player only) for 48000 ticks
- Then opens: `conversations.feelings`
- …where the player's next choices will be: "I feel the same way." | "I'm not sure how I feel yet." | "Let's talk about something else."

```text
POOL   dialogue key: dialogue.conversations.feelings.first
WHO    VILLAGER — what the player reads after pressing "How do you really feel about me?"
       spoken on: conversations.cat.personal, button `feelings`
       leaves the player on: conversations.feelings
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `feelings.first.to.feelings`: the villager accepts. Subject `feelings`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `feelings`** earlier in this file. Fill it in there, once.


### Button `regrets` — "Do you have any regrets?"

Shown only when MCA's own constraints hold: `"adult"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.regrets` — accepted phrasings: "any regrets"; "do you regret"; "your biggest regret"; "your biggest mistake"
  - the message must contain one of: `regret`, `mistake`, `guilt`
  - scored words: `regret`(1.5), `mistake`(1.0), `guilt`(1.0), `sorry`(0.8), `wrong`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.regrets
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.regrets   [24 chars]
    en  Do you have any regrets?
    >>  ............................................
    pt  Você se arrepende de alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.regrets.the_old_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.regrets.the_old_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 36000 ticks
- Then opens: `conversations.scene.regrets.the_old_one.respond`
- …where the player's next choices will be: "That's a heavy one to carry." | "What would you do differently?" | "Thank you for saying it."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_old_one
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.scene.regrets.the_old_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_old_one.open`: the villager reports. Subject `regrets.long_carried`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_old_one/1   [102 chars]
    en  One conversation, about nine years ago, that I ended too well. Ending it badly would have left a door.
    >>  ............................................
    pt  Uma conversa, uns nove anos atrás, que eu encerrei bem demais. Encerrar mal teria deixado uma porta.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one/2   [91 chars]
    en  I let somebody go and told myself it was respect. It was that I did not want to be told no.
    >>  ............................................
    pt  Deixei alguém ir e disse a mim mesma que era respeito. Era que eu não queria ouvir um não.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_old_one/3   [114 chars]
    en  It is not a thing I did. It is four years of not doing a thing, which is the sort that does not have a date on it.
    >>  ............................................
    pt  Não é uma coisa que eu fiz. São quatro anos sem fazer uma coisa, do tipo que não tem data.
    >>  ............................................
```


**Outcome 2 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.regrets.the_small_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.regrets.the_small_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 36000 ticks
- Then opens: `conversations.scene.regrets.the_small_one.respond`
- …where the player's next choices will be: "Some of that is still fixable." | "Could you mend the friendship?" | "Thank you for saying it."

```text
POOL   dialogue key: dialogue.conversations.scene.regrets.the_small_one
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.scene.regrets.the_small_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.the_small_one.open`: the villager reports. Subject `regrets.ordinary`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:regrets` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.regrets.the_small_one/1   [112 chars]
    en  I should have learned to swim. Everybody in this village can and I made a joke of it at fifteen and here we are.
    >>  ............................................
    pt  Eu deveria ter aprendido a nadar. Todo mundo nesta vila sabe, e eu fiz piada disso aos quinze, e aqui estamos.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one/2   [105 chars]
    en  Selling a good tool to pay for a bad week. I have replaced it twice and neither replacement was the same.
    >>  ............................................
    pt  Vender uma boa ferramenta para pagar uma semana ruim. Já substituí duas vezes e nenhuma substituta foi a mesma coisa.
    >>  ............................................
  dialogue.conversations.scene.regrets.the_small_one/3   [105 chars]
    en  One argument I won that I should have lost on purpose. It cost me a friendship worth more than the point.
    >>  ............................................
    pt  Uma discussão que eu ganhei e deveria ter perdido de propósito. Me custou uma amizade que valia mais que o ponto.
    >>  ............................................
```


**Outcome 3 of 8** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.regrets` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `again` budget `deep`
- Then opens: `conversations.topic.deep.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.regrets.again
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.again.to.deep.again`: the villager accepts. Subject `deep.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.again/1   [58 chars]
    en  We turned those stones over already. Let them lie a while.
    >>  ............................................
    pt  A gente já revirou essas pedras. Deixa elas quietas um tempo.
    >>  ............................................
  dialogue.conversations.regrets.again/2   [71 chars]
    en  One airing per season for those, %1$s. Doctor's orders. I'm the doctor.
    >>  ............................................
    pt  Um arejamento por estação pra esses, %1$s. Ordem médica. O médico sou eu.
    >>  ............................................
  dialogue.conversations.regrets.again/3   [80 chars]
    en  Twice in a day and I'll start thinking it's the most interesting thing about me.
    >>  ............................................
    pt  Duas vezes no mesmo dia e eu vou começar a achar que é a coisa mais interessante sobre mim.
    >>  ............................................
```


**Outcome 4 of 8** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.regrets` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 48000 ticks
- Then opens: `conversations.topic.regrets.guarded.respond`
- …where the player's next choices will be: "Then leave it where it is." | "Tell me something you don't regret, then." | "Lift it for me, just once." | "I'll not dig. Another time."

```text
POOL   dialogue key: dialogue.conversations.deflect.intimate
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.topic.regrets.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   5 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.intimate.to.regrets.guarded`: the villager deflects. Subject `regrets.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / feelings
```

> Written out in full under **`conversations.cat.personal` / button `feelings`** earlier in this file. Fill it in there, once.


**Outcome 5 of 8** — base weight `0`

- Fires when: weighted +100 when arc `regrets` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.regrets` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `resume` budget `deep`
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 48000 ticks
- Then opens: `conversations.arc.regrets.resume.respond`
- …where the player's next choices will be: "Has it sat any easier since?" | "Have you thought any more about putting it right?" | "So about that thing you did." | "I'll leave it be."

```text
POOL   dialogue key: dialogue.conversations.regrets.revisit
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.arc.regrets.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.revisit.opens`: the villager reminisces. Subject `regrets.past`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `regrets:named`, `arc:resumed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.regrets.revisit/1   [54 chars]
    en  That thing I told you about. It's still there, mostly.
    >>  ............................................
    pt  Aquilo que eu te contei. Ainda está lá, na maior parte.
    >>  ............................................
  dialogue.conversations.regrets.revisit/2   [61 chars]
    en  I've been turning it over since we spoke. Not to much effect.
    >>  ............................................
    pt  Venho remoendo desde que conversamos. Sem muito efeito.
    >>  ............................................
  dialogue.conversations.regrets.revisit/3   [48 chars]
    en  You know the one. I don't need to name it twice.
    >>  ............................................
    pt  Você sabe qual. Não preciso nomear duas vezes.
    >>  ............................................
```


**Outcome 6 of 8** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.regrets` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `regrets` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.regrets` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `first` budget `deep`
- Does: remembers `mcaconversations.topic.regrets` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 48000 ticks
- Then opens: `conversations.topic.regrets.respond`
- …where the player's next choices will be: "I'm not going anywhere. Go on." | "Is there anything left to put right?" | "What exactly did you do?" | "I don't know what to say to that." | "That's a lot to carry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.regrets.first
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.topic.regrets.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.first.to.regrets`: the villager accepts. Subject `regrets`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.regrets.first/1   [80 chars]
    en  One or two. There was a door I didn't knock on, years ago. I still walk past it.
    >>  ............................................
    pt  Um ou dois. Teve uma porta em que eu não bati, anos atrás. Ainda passo na frente dela.
    >>  ............................................
  dialogue.conversations.regrets.first/2   [74 chars]
    en  I regret the things I didn't say at a funeral once. Say your things, %1$s.
    >>  ............................................
    pt  Me arrependo do que não disse num enterro, uma vez. Diga as suas coisas, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.first/3   [61 chars]
    en  I traded a friendship for pride once. Worst deal I ever made.
    >>  ............................................
    pt  Troquei uma amizade por orgulho uma vez. Pior negócio que já fiz.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 2 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  flirty.dialogue.conversations.regrets.first/1
    en  A few. Mostly people I let walk away too easily. I'm working on not repeating that.
    >>  ............................................
    pt  Alguns. Quase todos pessoas que eu deixei ir embora fácil demais. Estou trabalhando pra não repetir.
    >>  ............................................
  flirty.dialogue.conversations.regrets.first/2
    en  One or two I let slip out the door. I've learned to hold the good ones tighter, %1$s.
    >>  ............................................
    pt  Um ou dois que eu deixei escapar pela porta. Aprendi a segurar os bons com mais força, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.first/1
    en  Regrets are the only thing I collect. Pick a shelf, any shelf.
    >>  ............................................
    pt  Arrependimento é a única coisa que eu coleciono. Escolhe uma prateleira, qualquer uma.
    >>  ............................................
  gloomy.dialogue.conversations.regrets.first/2
    en  One. There's really only one, and the rest are its children.
    >>  ............................................
    pt  Um. Na verdade só existe um, e os outros são filhos dele.
    >>  ............................................
```

</details>

> Falls back to the base pool above, no voice of its own here: anxious, athletic, confident, crabby, extroverted, friendly, greedy, grumpy, introverted, lazy, odd, peaceful, peppy, playful, relaxed, sensitive, shy, upbeat, witty.


**Outcome 7 of 8** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.regrets` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `acquaintance`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `regrets` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.regrets` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `regrets` branch `again_open` budget `deep`
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 48000 ticks
- Then opens: `conversations.topic.regrets.respond`
- …where the player's next choices will be: "I'm not going anywhere. Go on." | "Is there anything left to put right?" | "What exactly did you do?" | "I don't know what to say to that." | "That's a lot to carry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.regrets.revisit
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.topic.regrets.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.revisit.to.regrets`: the villager accepts. Subject `regrets`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `regrets`** earlier in this file. Fill it in there, once.


**Outcome 8 of 8** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.topic.regrets` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.regrets` (this player only) for 48000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.first
WHO    VILLAGER — what the player reads after pressing "Do you have any regrets?"
       spoken on: conversations.cat.personal, button `regrets`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.first.terminal`: the villager accepts. Subject `regrets.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.personal` / button `regrets`** earlier in this file. Fill it in there, once.


### Button `secret` — "Tell me a secret."

Shown only when MCA's own constraints hold: `"adult"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.secret` — accepted phrasings: "any secrets"; "tell me a secret"; "something hidden"
  - the message must contain one of: `secret`, `hidden`, `confide`, `private`
  - scored words: `secret`(1.5), `hidden`(1.0), `confide`(1.0), `private`(0.8), `hide`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.secret
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.secret   [17 chars]
    en  Tell me a secret.
    >>  ............................................
    pt  Me conta um segredo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.secret.deciding_whether_to_say"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.secret.deciding_whether_to_say", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 36000 ticks
- Then opens: `conversations.scene.secret.deciding_whether_to_say.respond`
- …where the player's next choices will be: "Tell me when you're ready." | "What would saying it cost you?" | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.deciding_whether_to_say
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.scene.secret.deciding_whether_to_say.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.deciding_whether_to_say.open`: the villager reports. Subject `secret.mine`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.deciding_whether_to_say/1   [122 chars]
    en  There is one thing I have never said in this village, and I have been sitting on the edge of saying it to you for a month.
    >>  ............................................
    pt  Existe uma coisa que eu nunca disse nesta vila, e faz um mês que eu estou na beira de dizer a você.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say/2   [116 chars]
    en  It is not shameful. It is just mine, and handing it over changes what I am in a room, and I have been weighing that.
    >>  ............................................
    pt  Não é vergonhoso. É só meu, e entregar muda o que eu sou dentro de uma sala, e eu venho pesando isso.
    >>  ............................................
  dialogue.conversations.scene.secret.deciding_whether_to_say/3   [100 chars]
    en  I will tell you one day and today is probably not the day, and I wanted you to know the door exists.
    >>  ............................................
    pt  Vou te contar um dia e hoje provavelmente não é o dia, e eu queria que você soubesse que a porta existe.
    >>  ............................................
```


**Outcome 2 of 8** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.secret.holding_somebody_elses"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.secret.holding_somebody_elses", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 36000 ticks
- Then opens: `conversations.scene.secret.holding_somebody_elses.respond`
- …where the player's next choices will be: "Good. Keep it." | "Does holding it weigh on you?" | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.secret.holding_somebody_elses
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.scene.secret.holding_somebody_elses.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.holding_somebody_elses.open`: the villager reports. Subject `secret.borrowed`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:secret` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.secret.holding_somebody_elses/1   [144 chars]
    en  I am holding something for somebody and I will not be handing it over, and I am telling you that so you stop wondering why I change the subject.
    >>  ............................................
    pt  Estou guardando algo por alguém e não vou entregar, e digo isso para você parar de se perguntar por que eu mudo de assunto.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses/2   [104 chars]
    en  There is a thing I know about this village that four people would pay to hear and that is going with me.
    >>  ............................................
    pt  Existe uma coisa que eu sei sobre esta vila que quatro pessoas pagariam para ouvir e que vai comigo.
    >>  ............................................
  dialogue.conversations.scene.secret.holding_somebody_elses/3   [128 chars]
    en  Somebody told me something at a bad hour and has since half regretted it, and my whole job now is acting as though they had not.
    >>  ............................................
    pt  Alguém me contou algo numa hora ruim e desde então meio que se arrependeu, e o meu trabalho inteiro agora é agir como se não tivesse contado.
    >>  ............................................
```


**Outcome 3 of 8** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.secret` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `again` budget `deep`
- Then opens: `conversations.topic.deep.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.secret.again
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.again.to.deep.again`: the villager accepts. Subject `deep.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.again/1   [68 chars]
    en  One secret per season, %1$s. Any faster and they stop being secrets.
    >>  ............................................
    pt  Um segredo por estação, %1$s. Mais rápido que isso e eles deixam de ser segredo.
    >>  ............................................
  dialogue.conversations.secret.again/2   [61 chars]
    en  You've had your secret. I'm still nervous about the last one.
    >>  ............................................
    pt  Você já teve o seu segredo. Ainda estou nervoso com o último.
    >>  ............................................
  dialogue.conversations.secret.again/3   [76 chars]
    en  Greedy! Secrets need time to grow back, like carrots. Dark, private carrots.
    >>  ............................................
    pt  Guloso! Segredo precisa de tempo pra crescer de novo, que nem cenoura. Cenoura escura e reservada.
    >>  ............................................
```


**Outcome 4 of 8** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.unlock.confided` (this player only)
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.secret` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 48000 ticks
- Then opens: `conversations.topic.secret.guarded.respond`
- …where the player's next choices will be: "Then keep it until I have." | "Tell me something that isn't buried, then." | "You can tell me. Really." | "Then I'll earn it. Another time."

```text
POOL   dialogue key: dialogue.conversations.deflect.secret
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.topic.secret.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.secret.to.secret.guarded`: the villager deflects. Subject `secret.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.deflect.secret/1   [70 chars]
    en  Secrets are traded, not given. Trust me with something of yours first.
    >>  ............................................
    pt  Segredo se troca, não se dá. Confie um seu a mim primeiro.
    >>  ............................................
  dialogue.conversations.deflect.secret/2   [41 chars]
    en  You first, %1$s. That's how secrets work.
    >>  ............................................
    pt  Você primeiro, %1$s. É assim que segredo funciona.
    >>  ............................................
  dialogue.conversations.deflect.secret/3   [75 chars]
    en  When you've told me something that matters, I'll match it. That's the deal.
    >>  ............................................
    pt  Quando você me contar algo que importa, eu retribuo. Esse é o acordo.
    >>  ............................................
```


**Outcome 5 of 8** — base weight `0`

- Fires when: weighted +100 when arc `secret` is at stage 1..2
- Fires when: RULED OUT when LACKS the memory `mcaconversations.unlock.confided` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.secret` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `resume` budget `deep`
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 48000 ticks
- Then opens: `conversations.arc.secret.resume.respond`
- …where the player's next choices will be: "I've told nobody, you know." | "How are you carrying it?" | "Does anyone else know?" | "I've been thinking of letting it slip." | "It's safe. I'll go."

```text
POOL   dialogue key: dialogue.conversations.secret.revisit
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.arc.secret.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.revisit.opens`: the villager reminisces. Subject `secret.disclosure`, polarity `mixed`, guarded, outcome `None`.
NOTE   this is the line that establishes `secret:heard`, `arc:resumed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.secret.revisit/1   [97 chars]
    en  Still keeping what I told you? ...Good. Then maybe there's another where that came from, someday.
    >>  ............................................
    pt  Ainda está guardando o que eu te contei? ...Ótimo. Então talvez tenha outro de onde veio aquele, um dia.
    >>  ............................................
  dialogue.conversations.secret.revisit/2   [88 chars]
    en  You never breathed a word about last time. I noticed. I don't forget that kind of quiet.
    >>  ............................................
    pt  Você não soltou uma palavra sobre a última vez. Eu reparei. Não esqueço esse tipo de silêncio.
    >>  ............................................
  dialogue.conversations.secret.revisit/3   [107 chars]
    en  Since you kept the last one — there's a second thing, same shape as the first. That's all I'm saying today.
    >>  ............................................
    pt  Já que você guardou a última — tem uma segunda coisa, do mesmo tamanho da primeira. É só isso que eu digo hoje.
    >>  ............................................
```


**Outcome 6 of 8** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.secret` (this player only)
- Fires when: RULED OUT when LACKS the memory `mcaconversations.unlock.confided` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when arc `secret` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.secret` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `first` budget `deep`
- Does: remembers `mcaconversations.topic.secret` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 48000 ticks
- Then opens: `conversations.topic.secret.respond`
- …where the player's next choices will be: "I'll hear it." | "Why me?" | "Don't tell me something you'll regret." | "Keep it, for now."

```text
POOL   dialogue key: dialogue.conversations.secret.first
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.topic.secret.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.first.to.secret`: the villager accepts. Subject `secret`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.secret.first/1   [150 chars]
    en  There's a thing I've never said out loud in this village. I can hand it to you now, or we can talk about the weather and both pretend I never offered.
    >>  ............................................
    pt  Tem uma coisa que eu nunca disse em voz alta nesta vila. Posso te entregar agora, ou a gente fala do tempo e finge que eu nunca ofereci.
    >>  ............................................
  dialogue.conversations.secret.first/2   [143 chars]
    en  Come closer. ...No. I'll say it properly or not at all. Do you want the thing I've been carrying about, %1$s, or shall we leave it where it is?
    >>  ............................................
    pt  Chega mais perto. ...Não. Ou eu digo direito, ou não digo. Você quer a coisa que eu venho carregando, %1$s, ou deixamos onde está?
    >>  ............................................
  dialogue.conversations.secret.first/3   [109 chars]
    en  I've got one. A real one — the kind you don't get back once it's out in the air. Say the word and it's yours.
    >>  ............................................
    pt  Tenho uma. De verdade — dessas que não voltam depois que saem no ar. É só dizer e é sua.
    >>  ............................................
```


**Outcome 7 of 8** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.secret` (this player only)
- Fires when: RULED OUT when LACKS the memory `mcaconversations.unlock.confided` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when arc `secret` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.secret` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `secret` branch `again_open` budget `deep`
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 48000 ticks
- Then opens: `conversations.topic.secret.respond`
- …where the player's next choices will be: "I'll hear it." | "Why me?" | "Don't tell me something you'll regret." | "Keep it, for now."

```text
POOL   dialogue key: dialogue.conversations.secret.revisit
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.topic.secret.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.revisit.to.secret`: the villager accepts. Subject `secret`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `secret`** earlier in this file. Fill it in there, once.


**Outcome 8 of 8** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.topic.secret` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.secret` (this player only) for 48000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.first
WHO    VILLAGER — what the player reads after pressing "Tell me a secret."
       spoken on: conversations.cat.personal, button `secret`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.first.terminal`: the villager accepts. Subject `secret.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.personal` / button `secret`** earlier in this file. Fill it in there, once.


### Button `interests` — "What do you enjoy?"

Shown only when MCA's own constraints hold: `"!toddler,!child"`

```text
POOL   dialogue key: dialogue.conversations.cat.personal.interests
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.interests   [18 chars]
    en  What do you enjoy?
    >>  ............................................
    pt  Do que você gosta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.interests.the_hour_for_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.interests.the_hour_for_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `interests` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.interests` (this player only) for 36000 ticks
- Then opens: `conversations.scene.interests.the_hour_for_it.respond`
- …where the player's next choices will be: "What are the plans?" | "Enjoy the hour." | "Good to know."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_hour_for_it
WHO    VILLAGER — what the player reads after pressing "What do you enjoy?"
       spoken on: conversations.cat.personal, button `interests`
       leaves the player on: conversations.scene.interests.the_hour_for_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_hour_for_it.open`: the villager reports. Subject `interests.tonight`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_hour_for_it/1   [102 chars]
    en  I have an hour after this and I know exactly what I am doing with it, which does not happen most days.
    >>  ............................................
    pt  Tenho uma hora depois disto e sei exatamente o que vou fazer com ela, o que não acontece na maioria dos dias.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it/2   [120 chars]
    en  Everything is finished and the light holds until late, so tonight is mine and I have plans nobody would find impressive.
    >>  ............................................
    pt  Tudo está terminado e a luz aguenta até tarde, então hoje à noite é minha e eu tenho planos que ninguém acharia impressionantes.
    >>  ............................................
  dialogue.conversations.scene.interests.the_hour_for_it/3   [101 chars]
    en  This is the part of the day the rest of the day is for. I would not say that in front of the headman.
    >>  ............................................
    pt  Esta é a parte do dia para a qual o resto do dia serve. Eu não diria isso na frente do chefe da vila.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.interests.the_one_who_shares_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.interests.the_one_who_shares_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `interests` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.interests` (this player only) for 36000 ticks
- Then opens: `conversations.scene.interests.the_one_who_shares_it.respond`
- …where the player's next choices will be: "How did you find out?" | "Two is lucky in a place this size." | "Good to know."

```text
POOL   dialogue key: dialogue.conversations.scene.interests.the_one_who_shares_it
WHO    VILLAGER — what the player reads after pressing "What do you enjoy?"
       spoken on: conversations.cat.personal, button `interests`
       leaves the player on: conversations.scene.interests.the_one_who_shares_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.the_one_who_shares_it.open`: the villager celebrates. Subject `interests.shared`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.interests.the_one_who_shares_it/1   [115 chars]
    en  I found out last month that somebody else here does the same thing, and neither of us had said a word in six years.
    >>  ............................................
    pt  Descobri mês passado que outra pessoa aqui faz a mesma coisa, e nenhuma de nós tinha dito uma palavra em seis anos.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it/2   [88 chars]
    en  There are two of us now. Two is an enormous number when it has been one for a long time.
    >>  ............................................
    pt  Agora somos duas. Duas é um número enorme quando foi uma por muito tempo.
    >>  ............................................
  dialogue.conversations.scene.interests.the_one_who_shares_it/3   [118 chars]
    en  We have started meeting on a Thursday and pretending it is about something else, which fools nobody and suits us both.
    >>  ............................................
    pt  Começamos a nos encontrar às quintas e fingir que é sobre outra coisa, o que não engana ninguém e serve às duas.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.interests.the_hour_for_it"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.interests.the_one_who_shares_it"}  _(chance -5000)_
- Does: session `begin` topic `interests` branch `funnel` budget `quick`
- Then opens: `conversations.topic.interests.open.respond`
- …where the player's next choices will be: "Tell me what the thing is." | "It's good to have something of your own." | "Sounds like a waste of an evening." | "Good to know."

```text
POOL   dialogue key: dialogue.conversations.interests.open
WHO    VILLAGER — what the player reads after pressing "What do you enjoy?"
       spoken on: conversations.cat.personal, button `interests`
       leaves the player on: conversations.topic.interests.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.open`: the villager reports. Subject `interests.what_i_like`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:interests` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.interests.open/1   [90 chars]
    en  There is one thing I would do even if nobody paid me for anything, and it is not my trade.
    >>  ............................................
    pt  Existe uma coisa que eu faria mesmo se ninguém me pagasse por nada, e não é o meu ofício.
    >>  ............................................
  dialogue.conversations.interests.open/2   [97 chars]
    en  I have a thing I am no good at and do anyway, which I have decided is the definition of the word.
    >>  ............................................
    pt  Tenho uma coisa em que eu sou ruim e faço mesmo assim, o que eu decidi que é a definição da palavra.
    >>  ............................................
  dialogue.conversations.interests.open/3   [109 chars]
    en  Small things, mostly. I have got very interested in something nobody else in this village has noticed exists.
    >>  ............................................
    pt  Coisas pequenas, na maioria. Fiquei muito interessada em algo que ninguém mais nesta vila reparou que existe.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.interests.legacy
WHO    VILLAGER — what the player reads after pressing "What do you enjoy?"
       spoken on: conversations.cat.personal, button `interests`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `interests.legacy`: the villager reports. Subject `interests.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.interests.legacy/1   [55 chars]
    en  One or two things. Nothing anybody would put in a song.
    >>  ............................................
    pt  Uma ou duas coisas. Nada que alguém colocasse numa canção.
    >>  ............................................
  dialogue.conversations.interests.legacy/2   [65 chars]
    en  I keep myself occupied. That is about as much as there is to say.
    >>  ............................................
    pt  Eu me mantenho ocupada. É mais ou menos tudo o que há para dizer.
    >>  ............................................
  dialogue.conversations.interests.legacy/3   [57 chars]
    en  Enough to fill an evening, which is all an evening needs.
    >>  ............................................
    pt  O suficiente para encher uma noite, que é tudo de que uma noite precisa.
    >>  ............................................
```


### Button `values` — "What matters to you?"

Shown only when MCA's own constraints hold: `"adult"`

```text
POOL   dialogue key: dialogue.conversations.cat.personal.values
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.values   [20 chars]
    en  What matters to you?
    >>  ............................................
    pt  O que importa para você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.values.what_it_cost_lately"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.values.what_it_cost_lately", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `values` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.values` (this player only) for 36000 ticks
- Then opens: `conversations.scene.values.what_it_cost_lately.respond`
- …where the player's next choices will be: "A rule that costs nothing isn't one." | "Would you do it again?" | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.values.what_it_cost_lately
WHO    VILLAGER — what the player reads after pressing "What matters to you?"
       spoken on: conversations.cat.personal, button `values`
       leaves the player on: conversations.scene.values.what_it_cost_lately.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.what_it_cost_lately.open`: the villager reports. Subject `values.recent_cost`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.what_it_cost_lately/1   [111 chars]
    en  It cost me a customer this season, and I would do it again, and I have been quietly cross about it for a month.
    >>  ............................................
    pt  Me custou um cliente nesta estação, e eu faria de novo, e ando discretamente irritada com isso faz um mês.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately/2   [102 chars]
    en  I lost an afternoon and a friendship's worth of goodwill over four sentences I could have left unsaid.
    >>  ............................................
    pt  Perdi uma tarde e o equivalente a uma amizade em boa vontade por quatro frases que eu podia ter engolido.
    >>  ............................................
  dialogue.conversations.scene.values.what_it_cost_lately/3   [121 chars]
    en  Holding to it was the easy part. Watching somebody else profit from not holding to it is the part nobody warns you about.
    >>  ............................................
    pt  Sustentar foi a parte fácil. Ver outra pessoa lucrar por não sustentar é a parte sobre a qual ninguém avisa.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.values.the_one_i_put_down"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.values.the_one_i_put_down", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `values` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.values` (this player only) for 36000 ticks
- Then opens: `conversations.scene.values.the_one_i_put_down.respond`
- …where the player's next choices will be: "What changed your reckoning?" | "Putting one down takes more than keeping it." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.scene.values.the_one_i_put_down
WHO    VILLAGER — what the player reads after pressing "What matters to you?"
       spoken on: conversations.cat.personal, button `values`
       leaves the player on: conversations.scene.values.the_one_i_put_down.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.the_one_i_put_down.open`: the villager reminisces. Subject `values.abandoned`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.values.the_one_i_put_down/1   [94 chars]
    en  I was raised on a rule I no longer keep, and putting it down took longer than learning it did.
    >>  ............................................
    pt  Fui criada com uma regra que eu não sigo mais, e largar levou mais tempo do que aprender.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down/2   [119 chars]
    en  There is one I held for twenty years and dropped in an afternoon, and I have never told the person who taught it to me.
    >>  ............................................
    pt  Existe uma que eu segui por vinte anos e larguei numa tarde, e nunca contei a quem me ensinou.
    >>  ............................................
  dialogue.conversations.scene.values.the_one_i_put_down/3   [93 chars]
    en  I still catch myself following it. Twenty years of a habit outlives the reason by a long way.
    >>  ............................................
    pt  Ainda me pego seguindo. Vinte anos de hábito sobrevivem ao motivo por muito tempo.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.values.what_it_cost_lately"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.values.the_one_i_put_down"}  _(chance -5000)_
- Does: session `begin` topic `values` branch `funnel` budget `quick`
- Then opens: `conversations.topic.values.open.respond`
- …where the player's next choices will be: "Name the rule." | "A short list is the honest kind." | "Everybody says that until it costs them." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.values.open
WHO    VILLAGER — what the player reads after pressing "What matters to you?"
       spoken on: conversations.cat.personal, button `values`
       leaves the player on: conversations.topic.values.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.open`: the villager reports. Subject `values.what_i_hold`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:values` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.values.open/1   [78 chars]
    en  One rule, really. Everything else I do is that rule with different clothes on.
    >>  ............................................
    pt  Uma regra, na verdade. Todo o resto que eu faço é essa regra com outra roupa.
    >>  ............................................
  dialogue.conversations.values.open/2   [107 chars]
    en  I have a short list and I could recite it, and I would rather show you the list by how I behave for a year.
    >>  ............................................
    pt  Tenho uma lista curta e poderia recitar, e prefiro mostrar a lista pelo meu comportamento por um ano.
    >>  ............................................
  dialogue.conversations.values.open/3   [84 chars]
    en  There is a thing I will not do for money and I have been offered money for it twice.
    >>  ............................................
    pt  Existe uma coisa que eu não faço por dinheiro, e já me ofereceram dinheiro por ela duas vezes.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.values.legacy
WHO    VILLAGER — what the player reads after pressing "What matters to you?"
       spoken on: conversations.cat.personal, button `values`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `values.legacy`: the villager reports. Subject `values.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.values.legacy/1   [79 chars]
    en  I try to be straight with people. It is not much of a creed and it has held up.
    >>  ............................................
    pt  Procuro ser franca com as pessoas. Não é grande credo e tem se sustentado.
    >>  ............................................
  dialogue.conversations.values.legacy/2   [80 chars]
    en  Do the work properly and say what you actually think. The rest sorts itself out.
    >>  ............................................
    pt  Fazer o trabalho direito e dizer o que se pensa de verdade. O resto se resolve.
    >>  ............................................
  dialogue.conversations.values.legacy/3   [76 chars]
    en  Nothing grand. A short list, and I have kept to most of it most of the time.
    >>  ............................................
    pt  Nada grandioso. Uma lista curta, e eu cumpri quase toda ela quase sempre.
    >>  ............................................
```


### Button `player` — "What do you make of me?"

Shown only when MCA's own constraints hold: `"!toddler,!child"`

```text
POOL   dialogue key: dialogue.conversations.cat.personal.player
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.player   [23 chars]
    en  What do you make of me?
    >>  ............................................
    pt  O que você acha de mim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.player.the_day_i_revised_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.player.the_day_i_revised_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `player` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.player` (this player only) for 36000 ticks
- Then opens: `conversations.scene.player.the_day_i_revised_it.respond`
- …where the player's next choices will be: "Which afternoon was it?" | "It means something, hearing that." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it
WHO    VILLAGER — what the player reads after pressing "What do you make of me?"
       spoken on: conversations.cat.personal, button `player`
       leaves the player on: conversations.scene.player.the_day_i_revised_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.the_day_i_revised_it.open`: the villager reminisces. Subject `player.revision`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it/1   [95 chars]
    en  There was one afternoon that did it, and you have no idea which one, and I have never told you.
    >>  ............................................
    pt  Teve uma tarde que resolveu a questão, e você não faz ideia de qual, e eu nunca te contei.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it/2   [106 chars]
    en  I can date the day I stopped being careful around you. You were not doing anything remarkable at the time.
    >>  ............................................
    pt  Eu sei datar o dia em que parei de ser cautelosa perto de você. Você não estava fazendo nada notável na hora.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it/3   [87 chars]
    en  It was not a favour. It was the way you spoke to somebody who could do nothing for you.
    >>  ............................................
    pt  Não foi um favor. Foi o jeito como você falou com alguém que não podia fazer nada por você.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.player.what_i_say_about_you"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.player.what_i_say_about_you", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `player` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.player` (this player only) for 36000 ticks
- Then opens: `conversations.scene.player.what_i_say_about_you.respond`
- …where the player's next choices will be: "What version is going round?" | "You've been defending me, then." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you
WHO    VILLAGER — what the player reads after pressing "What do you make of me?"
       spoken on: conversations.cat.personal, button `player`
       leaves the player on: conversations.scene.player.what_i_say_about_you.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.what_i_say_about_you.open`: the villager reports. Subject `player.reputation_here`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you/1   [99 chars]
    en  You get talked about here, and I am usually the one doing the talking, and you come out of it well.
    >>  ............................................
    pt  Falam de você aqui, e normalmente sou eu que falo, e você sai bem da conversa.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you/2   [99 chars]
    en  Somebody asked me about you last week and I gave them the honest answer rather than the polite one.
    >>  ............................................
    pt  Alguém me perguntou sobre você semana passada e eu dei a resposta honesta em vez da educada.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you/3   [105 chars]
    en  There is a version of you that goes round this village, and I have been quietly correcting it for months.
    >>  ............................................
    pt  Existe uma versão sua circulando por esta vila, e eu venho corrigindo essa versão em silêncio faz meses.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.player.the_day_i_revised_it"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.player.what_i_say_about_you"}  _(chance -5000)_
- Does: session `begin` topic `player` branch `funnel` budget `quick`
- Then opens: `conversations.topic.player.open.respond`
- …where the player's next choices will be: "What shifted your reading?" | "I'd have thought the same of me." | "You judged me early, then." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.player.open
WHO    VILLAGER — what the player reads after pressing "What do you make of me?"
       spoken on: conversations.cat.personal, button `player`
       leaves the player on: conversations.topic.player.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.open`: the villager reminisces. Subject `player.first_impression`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.open/1   [97 chars]
    en  Honestly? I assumed you were passing through. Nearly everybody who arrives with that much kit is.
    >>  ............................................
    pt  Honestamente? Achei que você estava só de passagem. Quase todo mundo que chega com tanto equipamento está.
    >>  ............................................
  dialogue.conversations.player.open/2   [106 chars]
    en  I had you down as trouble for about nine days, and I would rather admit that than pretend I was welcoming.
    >>  ............................................
    pt  Te classifiquei como encrenca por uns nove dias, e prefiro admitir isso a fingir que fui acolhedora.
    >>  ............................................
  dialogue.conversations.player.open/3   [104 chars]
    en  I did not think about you at all for the first while, which is the most honest thing I can say about it.
    >>  ............................................
    pt  Eu não pensei em você de jeito nenhum no começo, que é a coisa mais honesta que posso dizer.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.player.legacy
WHO    VILLAGER — what the player reads after pressing "What do you make of me?"
       spoken on: conversations.cat.personal, button `player`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.legacy`: the villager reports. Subject `player.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.player.legacy/1   [94 chars]
    en  You are all right. I have known worse and I have known better and you are nearer the good end.
    >>  ............................................
    pt  Você é gente boa. Já conheci pior e já conheci melhor, e você está mais perto do lado bom.
    >>  ............................................
  dialogue.conversations.player.legacy/2   [83 chars]
    en  I decided you were harmless in the first week and I have had no cause to revise it.
    >>  ............................................
    pt  Decidi que você era inofensivo na primeira semana e não tive motivo para rever isso.
    >>  ............................................
  dialogue.conversations.player.legacy/3   [112 chars]
    en  You turn up, you do what you said, and you have not asked me for anything unreasonable. That is a decent record.
    >>  ............................................
    pt  Você aparece, faz o que disse e nunca me pediu nada absurdo. É um histórico decente.
    >>  ............................................
```


### Button `origin` — "Where are you from?"

Shown only when MCA's own constraints hold: `"!toddler,!child"`

```text
POOL   dialogue key: dialogue.conversations.cat.personal.origin
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.origin   [19 chars]
    en  Where are you from?
    >>  ............................................
    pt  De onde você é?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.origin.the_season_that_smells_like_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.origin.the_season_that_smells_like_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `origin` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.origin` (this player only) for 36000 ticks
- Then opens: `conversations.scene.origin.the_season_that_smells_like_it.respond`
- …where the player's next choices will be: "What was it like there, this time of year?" | "Some seasons carry more than weather." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.the_season_that_smells_like_it
WHO    VILLAGER — what the player reads after pressing "Where are you from?"
       spoken on: conversations.cat.personal, button `origin`
       leaves the player on: conversations.scene.origin.the_season_that_smells_like_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.the_season_that_smells_like_it.open`: the villager reminisces. Subject `origin.season_memory`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.the_season_that_smells_like_it/1   [90 chars]
    en  This is the season that smells like where I grew up, and it ambushes me every single year.
    >>  ............................................
    pt  Esta é a estação que tem o cheiro de onde eu cresci, e ela me pega de surpresa todo santo ano.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it/2   [89 chars]
    en  The light goes a particular way about now and for about four days I am not entirely here.
    >>  ............................................
    pt  A luz fica de um jeito específico por agora e por uns quatro dias eu não estou inteiramente aqui.
    >>  ............................................
  dialogue.conversations.scene.origin.the_season_that_smells_like_it/3   [95 chars]
    en  Every year at this point I catch myself listening for a bell that has not rung in twenty years.
    >>  ............................................
    pt  Todo ano nesta altura eu me pego escutando um sino que não toca há vinte anos.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.origin.whether_this_is_home"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.origin.whether_this_is_home", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `origin` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.origin` (this player only) for 36000 ticks
- Then opens: `conversations.scene.origin.whether_this_is_home.respond`
- …where the player's next choices will be: "What did you answer?" | "A person can hold two homes." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.origin.whether_this_is_home
WHO    VILLAGER — what the player reads after pressing "Where are you from?"
       spoken on: conversations.cat.personal, button `origin`
       leaves the player on: conversations.scene.origin.whether_this_is_home.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.whether_this_is_home.open`: the villager reports. Subject `origin.is_this_home`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.origin.whether_this_is_home/1   [97 chars]
    en  I have been here longer than I was ever there, and I still say 'back home' about the other place.
    >>  ............................................
    pt  Estou aqui há mais tempo do que estive lá, e ainda digo "lá em casa" falando do outro lugar.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home/2   [106 chars]
    en  Somebody asked me last winter which one was home and I gave an answer I have been arguing with ever since.
    >>  ............................................
    pt  Alguém me perguntou no inverno passado qual dos dois era casa e eu dei uma resposta com a qual discuto até hoje.
    >>  ............................................
  dialogue.conversations.scene.origin.whether_this_is_home/3   [94 chars]
    en  This is where my life is. Whether it is home is a separate question and I have not settled it.
    >>  ............................................
    pt  É aqui que a minha vida está. Se é casa é outra pergunta e eu não resolvi.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.origin.the_season_that_smells_like_it"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.origin.whether_this_is_home"}  _(chance -5000)_
- Does: session `begin` topic `origin` branch `funnel` budget `quick`
- Then opens: `conversations.topic.origin.open.respond`
- …where the player's next choices will be: "What brought you away?" | "That's a long road to walk." | "Everyone came from somewhere." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.origin.open
WHO    VILLAGER — what the player reads after pressing "Where are you from?"
       spoken on: conversations.cat.personal, button `origin`
       leaves the player on: conversations.topic.origin.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.open`: the villager reports. Subject `origin.where_from`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:origin` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.origin.open/1   [102 chars]
    en  Not from here, which everybody worked out in my first fortnight and nobody has ever asked about since.
    >>  ............................................
    pt  Não daqui, o que todo mundo deduziu na minha primeira quinzena e ninguém nunca perguntou depois.
    >>  ............................................
  dialogue.conversations.origin.open/2   [92 chars]
    en  A long way off, and I can still draw the road, and I have not walked it in a very long time.
    >>  ............................................
    pt  Bem longe, e eu ainda sei desenhar a estrada, e não a percorro há muitíssimo tempo.
    >>  ............................................
  dialogue.conversations.origin.open/3   [104 chars]
    en  I was born somewhere with a different word for supper. That is the shortest way to explain the distance.
    >>  ............................................
    pt  Nasci num lugar com outra palavra para a ceia. É o jeito mais curto de explicar a distância.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.origin.legacy
WHO    VILLAGER — what the player reads after pressing "Where are you from?"
       spoken on: conversations.cat.personal, button `origin`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `origin.legacy`: the villager reports. Subject `origin.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.origin.legacy/1   [88 chars]
    en  Somewhere flatter than this, and colder, and a good deal further off than people assume.
    >>  ............................................
    pt  Um lugar mais plano que este, e mais frio, e bem mais longe do que as pessoas supõem.
    >>  ............................................
  dialogue.conversations.origin.legacy/2   [94 chars]
    en  Not here. I came over as a young thing and stayed by accident, which is how most of us arrive.
    >>  ............................................
    pt  Não daqui. Cheguei ainda jovem e fiquei por acidente, que é como a maioria de nós chega.
    >>  ............................................
  dialogue.conversations.origin.legacy/3   [78 chars]
    en  A smaller place than this one, if you can credit it. Four families and a well.
    >>  ............................................
    pt  Um lugar menor que este, se der para acreditar. Quatro famílias e um poço.
    >>  ............................................
```


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.personal.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.back   [15 chars]
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


## `conversations.cat.profession`

**Reached from 667 route(s):** `conversations.arc.work.resume.followup` / `hold_you_to_it`; `conversations.arc.work.resume.followup` / `no_hurry`; `conversations.arc.work.resume.followup` / `leave`; `conversations.arc.work.resume.respond` / `leave`; `conversations.cat.profession` / `work_offer`; `conversations.cat.profession` / `work_offer`; `conversations` / `profession`; `conversations.scene.work.adventurer.bad_route.active.respond` / `leave`; `conversations.scene.work.adventurer.bad_route.succeeded.respond` / `leave`; `conversations.scene.work.adventurer.followup` / `leave`; `conversations.scene.work.adventurer.souvenir.succeeded.respond` / `leave`; `conversations.scene.work.adventurer.unfinished_delve.active.respond` / `leave` …and 655 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.quest.none` — e.g. "Nothing needs doing right now, %1$s. But I appreciate you asking."
- `conversations.quest.offer` — e.g. "Since you ask, %1$s — aye, there's a thing or two. Let me show you what I need."
- `conversations.work.again.apologize` — e.g. "No harm. I like talking about it more than I let on."
- `conversations.work.again.leave` — e.g. "Aye. Come back when I've something new to show you."
- `conversations.work.again.press` — e.g. "It's the same trade it was an hour ago."
- `conversations.work.followup.belittle` — e.g. "Anyone could. Nobody does. There's a difference, %1$s."
- `conversations.work.followup.hear_burnout` — e.g. "...It is. Huh. Everyone else asks when it'll be finished. Right — where were we."
- `conversations.work.followup.joke` — e.g. "Ha. Don't tell the others. They think it holds itself up out of respect."
- `conversations.work.followup.offer_idea` — e.g. "...Huh. That'd work. Where were you three winters ago?"
- `conversations.work.generic` — e.g. "Being a %2$s isn't glamorous, but it's mine, and I'm better at it than most."
- `conversations.work.hate` — e.g. "Between us? Some mornings I stare at the ceiling and think about just... walking."
- `conversations.work.hate.leave` — e.g. "Aye. Off you go."
- `conversations.work.hear_burnout.crit` — e.g. "...It is. Nobody asks the second question, and you just did. Sit down, I've things to say."
- `conversations.work.hear_burnout.partial` — e.g. "Everything wears somebody down. That's not an insight, that's a job."
- …and 167 more pools


```text
POOL   dialogue key: dialogue.conversations.cat.profession
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.profession
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.profession   [23 chars]
    en  My work? What about it?
    >>  ............................................
    pt  Meu trabalho? O que tem ele?
    >>  ............................................
```


### Button `work` — "Do you actually like your work?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `profession.work` — accepted phrasings: "do you like your work"; "how is work going"; "tell me about your work"; "what is your job"; "what do you do"; "do for a living"
  - the message must contain one of: `work`, `do`
  - scored words: `work`(1.5), `busy`(0.6), `task`(0.6), `make`(0.6), `build`(0.6), `do`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.profession.work
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.profession
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.profession.work   [31 chars]
    en  Do you actually like your work?
    >>  ............................................
    pt  Você gosta mesmo do seu trabalho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.misfire.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.misfire.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.misfire.blocked.respond`
- …where the player's next choices will be: "Why stop instead of trying again?" | "I'll bring you lapis for the work." | "Halting there was the right call." | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.blocked`: the villager reports. Subject `work.shady_wizard.a_misfire`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked/1   [92 chars]
    en  %2$s went wrong on Tuesday and left me %3$s, and I have stopped work until I understand why.
    >>  ............................................
    pt  %2$s deu errado na terça e me deixou %3$s, e eu parei o trabalho até entender por quê.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked/2   [119 chars]
    en  %3$s. That is what %2$s did instead of what it was supposed to do, and the gap between those two is the entire problem.
    >>  ............................................
    pt  %3$s. Foi isso que %2$s fez em vez do que deveria, e a distância entre as duas coisas é o problema inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked/3   [104 chars]
    en  I have written down everything I did before %2$s, in order, twice, and the two lists do not quite agree.
    >>  ............................................
    pt  Anotei tudo o que fiz antes de %2$s, em ordem, duas vezes, e as duas listas não batem exatamente.
    >>  ............................................
```


**Outcome 2 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.misfire.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.misfire.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.misfire.succeeded.respond`
- …where the player's next choices will be: "What goes in your notes?" | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.misfire.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.succeeded`: the villager reports. Subject `work.shady_wizard.a_misfire`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded/1   [97 chars]
    en  Four quarter-strength runs and the third one told me. %2$s was fine; the bench under it was damp.
    >>  ............................................
    pt  Quatro execuções a um quarto da força e a terceira me disse. %2$s estava bem; a bancada embaixo estava úmida.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded/2   [119 chars]
    en  It was the material all along. I had been suspecting my own hands for eleven days, which is its own kind of exhausting.
    >>  ............................................
    pt  Era o material o tempo todo. Eu vinha desconfiando das minhas próprias mãos por onze dias, o que é um cansaço à parte.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded/3   [144 chars]
    en  %2$s runs clean now, and I have added a line to the notes about checking the bench, which nobody taught me and which everybody apparently knows.
    >>  ............................................
    pt  %2$s roda limpo agora, e acrescentei uma linha às notas sobre conferir a bancada, coisa que ninguém me ensinou e que aparentemente todo mundo sabe.
    >>  ............................................
```


**Outcome 3 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.the_name_they_use.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.the_name_they_use.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.the_name_they_use.active.respond`
- …where the player's next choices will be: "Where did the word come from?" | "Show somebody the notebook." | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.the_name_they_use.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.the_name_they_use.active`: the villager reports. Subject `work.shady_wizard.customers`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active/1   [104 chars]
    en  They call me shady at %2$s, and they buy from me anyway, and both of those happen in the same afternoon.
    >>  ............................................
    pt  Me chamam de suspeita em %2$s, e compram de mim assim mesmo, e as duas coisas acontecem na mesma tarde.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active/2   [130 chars]
    en  %2$s has decided what I am. The word arrived before I did and I have spent four years failing to be interesting enough to keep it.
    >>  ............................................
    pt  %2$s já decidiu o que eu sou. A palavra chegou antes de mim e passei quatro anos falhando em ser interessante o bastante para merecê-la.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active/3   [130 chars]
    en  The people who come to the shed at night are the ones who called me that at %2$s in the morning. I sell to them at the same price.
    >>  ............................................
    pt  Quem aparece no galpão à noite é quem me chamou disso em %2$s de manhã. Vendo pelo mesmo preço.
    >>  ............................................
```


**Outcome 4 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.the_name_they_use.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.the_name_they_use.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond`
- …where the player's next choices will be: "Careful is the right word for you." | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.the_name_they_use.succeeded`: the villager reports. Subject `work.shady_wizard.customers`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded/1   [107 chars]
    en  The cleric read the notebook and said one sentence about it at the well, and %2$s has been different since.
    >>  ............................................
    pt  A clériga leu o caderno e disse uma frase sobre isso no poço, e %2$s está diferente desde então.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded/2   [99 chars]
    en  Not fixed. Different. Two households ask me questions now instead of asking somebody else about me.
    >>  ............................................
    pt  Não resolvido. Diferente. Duas casas agora me fazem perguntas em vez de perguntar sobre mim a outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded/3   [115 chars]
    en  Somebody at %2$s called me careful last week. I would like that word on my door and I am not going to put it there.
    >>  ............................................
    pt  Alguém em %2$s me chamou de cuidadosa semana passada. Eu queria essa palavra na minha porta e não vou colocar lá.
    >>  ............................................
```


**Outcome 5 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.inherited_page.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.inherited_page.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.inherited_page.active.respond`
- …where the player's next choices will be: "What do you think it says?" | "Find somebody who reads that hand." | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.inherited_page.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.inherited_page.active`: the villager reports. Subject `work.shady_wizard.notes`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active/1   [105 chars]
    en  I have %2$s that came with the shed and I have been looking at it for two years without getting anywhere.
    >>  ............................................
    pt  Tenho %2$s que veio junto com o galpão e faz dois anos que eu olho aquilo sem chegar a lugar nenhum.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active/2   [119 chars]
    en  %2$s. Somebody knew something and wrote it down badly, and now it is mine and it is useless and I cannot throw it away.
    >>  ............................................
    pt  %2$s. Alguém sabia alguma coisa e anotou mal, e agora é minha, é inútil e eu não consigo jogar fora.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active/3   [136 chars]
    en  The tempting thing about %2$s is to try what I think it says. I have not, and the not-trying is the whole of my professional discipline.
    >>  ............................................
    pt  O tentador em %2$s é testar o que eu acho que diz. Eu não testei, e esse não-testar é a minha disciplina profissional inteira.
    >>  ............................................
```


**Outcome 6 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.shady_wizard.inherited_page.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.shady_wizard.inherited_page.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.shady_wizard.inherited_page.succeeded.respond`
- …where the player's next choices will be: "You held off for two whole years." | "I'll let you get back to your workings."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.inherited_page.succeeded`: the villager reports. Subject `work.shady_wizard.notes`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded/1   [97 chars]
    en  A copyist read %2$s in an afternoon. It is a shopping list. Two years, and it is a shopping list.
    >>  ............................................
    pt  Um copista leu %2$s numa tarde. É uma lista de compras. Dois anos, e é uma lista de compras.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded/2   [113 chars]
    en  %2$s turned out to be notes on where to buy good glass, in an old hand, from somebody who was as careful as I am.
    >>  ............................................
    pt  %2$s acabou sendo anotações sobre onde comprar bom vidro, em letra antiga, de alguém tão cuidadoso quanto eu.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded/3   [117 chars]
    en  It is read. I have kept it, framed, over the bench, as a permanent reminder of what two years of assuming looks like.
    >>  ............................................
    pt  Está lida. Guardei, emoldurada, sobre a bancada, como lembrete permanente do que dois anos de suposição parecem.
    >>  ............................................
```


**Outcome 7 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.short_feast.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.short_feast.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.short_feast.blocked.respond`
- …where the player's next choices will be: "How do you stretch a pot?" | "I'll bring carrots for the pot." | "Tell them it's a lean year." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.blocked`: the villager reports. Subject `work.delightchef.ingredients`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked/1   [85 chars]
    en  %2$s is on Sunday and I have %3$s, and forty people are expecting to be fed properly.
    >>  ............................................
    pt  %2$s é domingo e eu tenho %3$s, e quarenta pessoas esperam comer direito.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked/2   [99 chars]
    en  %3$s. I can stretch it, and stretched food tastes like being apologised to, and everybody knows it.
    >>  ............................................
    pt  %3$s. Dá para esticar, e comida esticada tem gosto de pedido de desculpa, e todo mundo percebe.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked/3   [131 chars]
    en  I have counted four times. %2$s needs eleven more portions than I can make, and eleven is exactly the number that cannot be hidden.
    >>  ............................................
    pt  Contei quatro vezes. %2$s precisa de onze porções a mais do que eu consigo fazer, e onze é exatamente o número que não dá para esconder.
    >>  ............................................
```


**Outcome 8 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.short_feast.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.short_feast.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.short_feast.succeeded.respond`
- …where the player's next choices will be: "Who got the spare portion?" | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.short_feast.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.succeeded`: the villager reports. Subject `work.delightchef.ingredients`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded/1   [98 chars]
    en  %2$s went well. Forty-one fed, one spare portion, and I have not slept that well since the spring.
    >>  ............................................
    pt  %2$s correu bem. Quarenta e uma pessoas alimentadas, uma porção sobrando, e eu não dormia tão bem desde a primavera.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded/2   [125 chars]
    en  I said it was a lean year at the start and then nobody thought about it again, which is exactly what I was told would happen.
    >>  ............................................
    pt  Eu disse que era um ano magro no começo e depois ninguém pensou mais nisso, que é exatamente o que me disseram que aconteceria.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded/3   [112 chars]
    en  There was nothing left. Not a scrap wasted and nobody hungry, and that is the only two-part test this trade has.
    >>  ............................................
    pt  Não sobrou nada. Nem uma migalha desperdiçada e ninguém com fome, e é esse o único teste de duas partes que este ofício tem.
    >>  ............................................
```


**Outcome 9 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.public_failure.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.public_failure.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.public_failure.failed.respond`
- …where the player's next choices will be: "What actually went wrong?" | "One dish isn't your record." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.public_failure.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.public_failure.failed`: the villager reports. Subject `work.delightchef.a_dish_that_failed`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed/1   [119 chars]
    en  I served %2$s in front of thirty people and there is no version of that afternoon where I get to explain the chemistry.
    >>  ............................................
    pt  Servi %2$s diante de trinta pessoas e não existe versão daquela tarde em que eu possa explicar a química.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed/2   [104 chars]
    en  %2$s. Everybody was very kind about it, which is the worst possible response and the only one available.
    >>  ............................................
    pt  %2$s. Todo mundo foi muito gentil a respeito, que é a pior resposta possível e a única disponível.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed/3   [130 chars]
    en  It was %2$s and it was my fault and I knew why before it reached the table, and I served it anyway because there was nothing else.
    >>  ............................................
    pt  Era %2$s e a culpa era minha e eu sabia por quê antes de chegar à mesa, e servi assim mesmo porque não havia outra coisa.
    >>  ............................................
```


**Outcome 10 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.public_failure.remembered"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.public_failure.remembered", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.public_failure.remembered.respond`
- …where the player's next choices will be: "Telling it makes it useful." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.remembered
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.public_failure.remembered.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.public_failure.remembered`: the villager reports. Subject `work.delightchef.a_dish_that_failed`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.remembered/1   [94 chars]
    en  I cook that dish once a season now, deliberately, on an ordinary Tuesday with nobody watching.
    >>  ............................................
    pt  Faço aquele prato uma vez por estação agora, de propósito, numa terça comum sem ninguém olhando.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.remembered/2   [128 chars]
    en  Somebody asked me about it last month and I told the whole story and laughed, and that was the moment it finished being a wound.
    >>  ............................................
    pt  Alguém me perguntou sobre aquilo mês passado e eu contei a história inteira e ri, e foi nesse momento que deixou de ser ferida.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.remembered/3   [118 chars]
    en  Two younger cooks have had me tell them about it. It is more useful to them than any of the afternoons that went well.
    >>  ............................................
    pt  Duas cozinheiras mais novas já me pediram para contar. É mais útil para elas do que qualquer uma das tardes que deram certo.
    >>  ............................................
```


**Outcome 11 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.who_eats_last.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.who_eats_last.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.who_eats_last.active.respond`
- …where the player's next choices will be: "Is it fair?" | "Keep holding the bowl back." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.who_eats_last.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.who_eats_last.active`: the villager reports. Subject `work.delightchef.the_crowd`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active/1   [101 chars]
    en  %2$s eat last at every gathering and nobody decided that, which is exactly why nobody will change it.
    >>  ............................................
    pt  %2$s comem por último em toda reunião e ninguém decidiu isso, e é exatamente por isso que ninguém vai mudar.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active/2   [114 chars]
    en  It is not written anywhere. %2$s simply arrive last, and by the time they arrive the good part of the pot is gone.
    >>  ............................................
    pt  Não está escrito em lugar nenhum. %2$s simplesmente chegam por último, e quando chegam a parte boa da panela já foi.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active/3   [114 chars]
    en  I have started holding a covered bowl back for %2$s. Somebody noticed and asked whether that was fair to the rest.
    >>  ............................................
    pt  Passei a guardar uma tigela tampada para %2$s. Alguém reparou e perguntou se aquilo era justo com o resto.
    >>  ............................................
```


**Outcome 12 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightchef.who_eats_last.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightchef.who_eats_last.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightchef.who_eats_last.succeeded.respond`
- …where the player's next choices will be: "Doing it openly changed it." | "I'll let you get back to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightchef.who_eats_last.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.who_eats_last.succeeded`: the villager reports. Subject `work.delightchef.the_crowd`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded/1   [119 chars]
    en  The bowl sits on the end of the table now, uncovered, with %2$s written on a card, and nobody has said a word about it.
    >>  ............................................
    pt  A tigela fica na ponta da mesa agora, destampada, com %2$s escrito num cartão, e ninguém disse uma palavra.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded/2   [116 chars]
    en  Two households have started arriving later on purpose so that they eat with %2$s, which was not any part of my plan.
    >>  ............................................
    pt  Duas casas passaram a chegar mais tarde de propósito para comer com %2$s, o que não fazia parte nenhuma do meu plano.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded/3   [123 chars]
    en  It stopped being a favour the day it stopped being hidden. I would have got there four years sooner if I had told somebody.
    >>  ............................................
    pt  Deixou de ser favor no dia em que deixou de ser escondido. Eu teria chegado lá quatro anos antes se tivesse contado a alguém.
    >>  ............................................
```


**Outcome 13 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.long_evenings.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.long_evenings.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.long_evenings.blocked.respond`
- …where the player's next choices will be: "What would actually fix it?" | "Cook four dishes instead of seven." | "You look worn out." | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.long_evenings.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.blocked`: the villager reports. Subject `work.delightcook.the_hours`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked/1   [94 chars]
    en  %2$s, and %3$s is now the only part of the day where I am any good, and it is getting shorter.
    >>  ............................................
    pt  %2$s, e %3$s agora é a única parte do dia em que eu presto, e está ficando mais curta.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked/2   [105 chars]
    en  %2$s. I am cooking well and I am cooking angry, and the second one is going to reach the food eventually.
    >>  ............................................
    pt  %2$s. Estou cozinhando bem e cozinhando com raiva, e a segunda coisa vai chegar na comida em algum momento.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.blocked/3   [138 chars]
    en  %3$s used to be when I thought about the food. Now it is when I catch up, and the food is thought about while it cooks, which is too late.
    >>  ............................................
    pt  %3$s costumava ser quando eu pensava na comida. Agora é quando eu corro atrás, e a comida é pensada enquanto cozinha, o que é tarde demais.
    >>  ............................................
```


**Outcome 14 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.long_evenings.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.long_evenings.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.long_evenings.succeeded.respond`
- …where the player's next choices will be: "You'd stopped tasting?" | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.long_evenings.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.long_evenings.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.long_evenings.succeeded`: the villager reports. Subject `work.delightcook.the_hours`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded/1   [129 chars]
    en  Four dishes. %2$s is mine again and I have started tasting things before they go out, which I had stopped doing without noticing.
    >>  ............................................
    pt  Quatro pratos. %2$s voltou a ser minha e eu voltei a provar as coisas antes de saírem, coisa que eu tinha parado de fazer sem perceber.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded/2   [139 chars]
    en  Two people complained and both of them are eating here twice a week now, so I have decided the complaint was about change rather than food.
    >>  ............................................
    pt  Duas pessoas reclamaram e as duas comem aqui duas vezes por semana agora, então decidi que a reclamação era sobre mudança e não sobre comida.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.long_evenings.succeeded/3   [112 chars]
    en  I sleep. That is the whole report. I sleep, and the food is better, and I resent how simple it turned out to be.
    >>  ............................................
    pt  Eu durmo. É o relatório inteiro. Eu durmo, a comida está melhor, e me irrita o quanto isso acabou sendo simples.
    >>  ............................................
```


**Outcome 15 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.bruised_deliveries.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.bruised_deliveries.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.bruised_deliveries.blocked.respond`
- …where the player's next choices will be: "I'll bring you potatoes this week." | "Refuse the next delivery." | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.bruised_deliveries.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.bruised_deliveries.blocked`: the villager reports. Subject `work.delightcook.stores`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked/1   [85 chars]
    en  %2$s, every week for a month, and I trim off a third of it before it reaches the pot.
    >>  ............................................
    pt  %2$s, toda semana faz um mês, e eu descarto um terço antes de chegar na panela.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked/2   [100 chars]
    en  %2$s. I have mentioned it twice and been told twice that it is the season, and it is not the season.
    >>  ............................................
    pt  %2$s. Já mencionei duas vezes e me disseram duas vezes que é a estação, e não é a estação.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.blocked/3   [129 chars]
    en  I pay full price for %2$s. The trimming is my time and the loss is my loss, and the grower has never once had a bad week over it.
    >>  ............................................
    pt  Pago preço cheio por %2$s. A limpeza é meu tempo e a perda é minha, e o produtor nunca teve uma semana ruim por causa disso.
    >>  ............................................
```


**Outcome 16 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.bruised_deliveries.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.bruised_deliveries.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond`
- …where the player's next choices will be: "Two other kitchens followed you." | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.bruised_deliveries.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.bruised_deliveries.succeeded`: the villager reports. Subject `work.delightcook.stores`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded/1   [93 chars]
    en  I refused a load at the door and the next one was the best I have had from him in four years.
    >>  ............................................
    pt  Recusei uma carga na porta e a seguinte foi a melhor que ele me mandou em quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded/2   [120 chars]
    en  He shouted for a minute and then went quiet and then said he had known. That last part is the one I keep thinking about.
    >>  ............................................
    pt  Ele gritou por um minuto, depois ficou quieto, depois disse que sabia. É essa última parte que eu fico remoendo.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.bruised_deliveries.succeeded/3   [124 chars]
    en  It is sorted. And two other kitchens have started refusing as well, which was not my plan and is the best outcome available.
    >>  ............................................
    pt  Está resolvido. E outras duas cozinhas passaram a recusar também, o que não era meu plano e é o melhor desfecho possível.
    >>  ............................................
```


**Outcome 17 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.borrowed_recipe.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.borrowed_recipe.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.borrowed_recipe.active.respond`
- …where the player's next choices will be: "What holds you back from naming it?" | "Ask her daughter what she'd want." | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.borrowed_recipe.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.borrowed_recipe.active`: the villager reports. Subject `work.delightcook.a_recipe_not_mine`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active/1   [116 chars]
    en  A woman gave me %2$s before she died and I have been cooking it and I have never once put a name to it on the board.
    >>  ............................................
    pt  Uma mulher me deu %2$s antes de morrer e eu venho fazendo e nunca pus um nome naquilo no quadro.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active/2   [123 chars]
    en  %2$s is not mine. I make it better than she did, which I would never say to anybody who knew her, and it is still not mine.
    >>  ............................................
    pt  %2$s não é minha. Eu faço melhor do que ela fazia, coisa que eu jamais diria a alguém que a conheceu, e ainda assim não é minha.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.active/3   [139 chars]
    en  People ask me what %2$s is called and I say the name of the dish. They are asking whose it is and I have not worked out how to answer that.
    >>  ............................................
    pt  As pessoas perguntam como %2$s se chama e eu digo o nome do prato. Estão perguntando de quem é, e eu ainda não descobri como responder.
    >>  ............................................
```


**Outcome 18 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.delightcook.borrowed_recipe.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.delightcook.borrowed_recipe.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond`
- …where the player's next choices will be: "Asking gave her something too." | "I'll let you get back to the pass."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.delightcook.borrowed_recipe.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightcook.borrowed_recipe.succeeded`: the villager reports. Subject `work.delightcook.a_recipe_not_mine`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:delightcook` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded/1   [117 chars]
    en  Her daughter cried and then laughed and then told me a detail about %2$s that I had been getting wrong for two years.
    >>  ............................................
    pt  A filha chorou, depois riu, e depois me contou um detalhe de %2$s que eu vinha errando havia dois anos.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded/2   [132 chars]
    en  It has her mother's name on the board now, and underneath, in smaller letters, the daughter's, because she gave me the missing step.
    >>  ............................................
    pt  Está com o nome da mãe dela no quadro agora, e embaixo, em letras menores, o da filha, porque foi ela quem me deu o passo que faltava.
    >>  ............................................
  dialogue.conversations.scene.work.delightcook.borrowed_recipe.succeeded/3   [117 chars]
    en  %2$s is right for the first time. Two years of very slightly wrong soup, ended by a conversation I was frightened of.
    >>  ............................................
    pt  %2$s está certa pela primeira vez. Dois anos de sopa levemente errada, encerrados por uma conversa de que eu tinha medo.
    >>  ............................................
```


**Outcome 19 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.inherited_error.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.inherited_error.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.inherited_error.blocked.respond`
- …where the player's next choices will be: "What are your options?" | "I'll bring you paper for the archive." | "Copy it as it stands and mark it." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.blocked`: the villager reports. Subject `work.scribe.errors`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked/1   [105 chars]
    en  There is %2$s in %3$s, and it has been faithfully copied forward by everybody since, including me, twice.
    >>  ............................................
    pt  Existe %2$s em %3$s, e vem sendo copiado fielmente por todo mundo desde então, inclusive por mim, duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked/2   [97 chars]
    en  %2$s. I found it by holding two copies side by side, which nobody had done in about ninety years.
    >>  ............................................
    pt  %2$s. Descobri comparando duas cópias lado a lado, coisa que ninguém fazia havia uns noventa anos.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked/3   [124 chars]
    en  %3$s is the only record this village has. If I correct %2$s I am changing the record, and if I do not I am passing on a lie.
    >>  ............................................
    pt  %3$s é o único registro que esta vila tem. Se eu corrigir %2$s estou alterando o registro, e se eu não corrigir estou repassando uma mentira.
    >>  ............................................
```


**Outcome 20 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.inherited_error.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.inherited_error.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.inherited_error.succeeded.respond`
- …where the player's next choices will be: "What did they argue?" | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.inherited_error.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.succeeded`: the villager reports. Subject `work.scribe.errors`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded/1   [111 chars]
    en  %2$s carries the note now, bound in, in both books, and the text above it is exactly as wrong as it always was.
    >>  ............................................
    pt  %2$s leva a nota agora, encadernada, nos dois livros, e o texto acima continua exatamente tão errado quanto sempre foi.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded/2   [104 chars]
    en  It is done, and it took four days, and the four days were entirely the writing of one careful paragraph.
    >>  ............................................
    pt  Está feito, levou quatro dias, e os quatro dias foram inteiramente escrever um parágrafo cuidadoso.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded/3   [148 chars]
    en  Somebody read the note last month and came to argue with me about it, which is the single best thing that has happened to that archive in a century.
    >>  ............................................
    pt  Alguém leu a nota mês passado e veio discutir comigo, o que é a melhor coisa que aconteceu àquele arquivo em um século.
    >>  ............................................
```


**Outcome 21 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.failing_eyes.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.failing_eyes.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.failing_eyes.active.respond`
- …where the player's next choices will be: "What's your plan for it?" | "Take an apprentice now." | "Nine hours to three is a real loss." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.active`: the villager reports. Subject `work.scribe.the_page`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active/1   [115 chars]
    en  I have %2$s, and I have been working shorter days and telling nobody, which is a plan with a very short life in it.
    >>  ............................................
    pt  Tenho %2$s, e venho trabalhando dias mais curtos sem contar a ninguém, o que é um plano com prazo de validade muito curto.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active/2   [108 chars]
    en  %2$s. Three good hours a day now, where it used to be nine, and the three are as good as the nine ever were.
    >>  ............................................
    pt  %2$s. Três boas horas por dia agora, onde eram nove, e as três são tão boas quanto as nove sempre foram.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active/3   [111 chars]
    en  %2$s means I will have to stop, at some point, and I would like to choose the point rather than have it chosen.
    >>  ............................................
    pt  %2$s significa que eu vou ter que parar, em algum momento, e eu gostaria de escolher o momento em vez de que ele me escolha.
    >>  ............................................
```


**Outcome 22 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.failing_eyes.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.failing_eyes.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.failing_eyes.succeeded.respond`
- …where the player's next choices will be: "Teaching the note first was clever." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.failing_eyes.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.succeeded`: the villager reports. Subject `work.scribe.the_page`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded/1   [122 chars]
    en  She has copied forty pages and made eleven errors, and she has marked all eleven herself, which is the whole of the trade.
    >>  ............................................
    pt  Ela copiou quarenta páginas e cometeu onze erros, e marcou os onze sozinha, que é o ofício inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded/2   [132 chars]
    en  Three hours of mine and eight of hers. The archive is moving faster than it has in twenty years and I am doing less of it than ever.
    >>  ............................................
    pt  Três horas minhas e oito dela. O arquivo anda mais rápido do que em vinte anos e eu faço menos do que nunca.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded/3   [119 chars]
    en  I taught her the marginal note first, before the letters. She thought that was strange. She does not think so any more.
    >>  ............................................
    pt  Ensinei a nota de margem primeiro, antes das letras. Ela achou estranho. Não acha mais.
    >>  ............................................
```


**Outcome 23 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.text_she_doubts.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.text_she_doubts.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.text_she_doubts.active.respond`
- …where the player's next choices will be: "Where does your doubt go?" | "Copy it faithfully anyway." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.text_she_doubts.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.text_she_doubts.active`: the villager reports. Subject `work.scribe.a_text_i_doubt`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active/1   [89 chars]
    en  I am copying %2$s that I do not believe a word of, and my job is to write it out exactly.
    >>  ............................................
    pt  Estou copiando %2$s em que eu não acredito uma palavra, e o meu trabalho é escrever exatamente.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active/2   [116 chars]
    en  %2$s. The man who wrote it was there and I was not, and that is the only fact I am certain of in the whole document.
    >>  ............................................
    pt  %2$s. O homem que escreveu estava lá e eu não estava, e é o único fato de que eu tenho certeza no documento inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active/3   [148 chars]
    en  It would be very easy to copy %2$s slightly less convincingly. A word here, a hesitation there. That is the temptation nobody warns a copyist about.
    >>  ............................................
    pt  Seria muito fácil copiar %2$s de um jeito um pouco menos convincente. Uma palavra aqui, uma hesitação ali. É a tentação sobre a qual ninguém avisa uma copista.
    >>  ............................................
```


**Outcome 24 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.scribe.text_she_doubts.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.scribe.text_she_doubts.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.scribe.text_she_doubts.succeeded.respond`
- …where the player's next choices will be: "A fair disagreement is a rare thing." | "I'll let you get back to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.scribe.text_she_doubts.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.text_she_doubts.succeeded`: the villager reports. Subject `work.scribe.a_text_i_doubt`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded/1   [99 chars]
    en  %2$s is copied, word for word, with four lines of mine in the margin saying what would persuade me.
    >>  ............................................
    pt  %2$s está copiado, palavra por palavra, com quatro linhas minhas na margem dizendo o que me convenceria.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded/2   [144 chars]
    en  I finished it. I still do not believe it and the copy does not show that, and I am prouder of the copy than of almost anything else I have made.
    >>  ............................................
    pt  Terminei. Continuo sem acreditar e a cópia não mostra isso, e tenho mais orgulho dessa cópia do que de quase tudo que já fiz.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded/3   [138 chars]
    en  Somebody read my margin note and told me it was the fairest disagreement they had ever read. I have thought about that more than I should.
    >>  ............................................
    pt  Alguém leu minha nota de margem e me disse que era a discordância mais justa que já tinha lido. Penso nisso mais do que deveria.
    >>  ............................................
```


**Outcome 25 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.unfinished_delve.blocked.respond`
- …where the player's next choices will be: "Any guess what is further in?" | "I'll bring you torches for it." | "Turning back was the right call." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.unfinished_delve.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.blocked`: the villager reports. Subject `work.adventurer.ruin_found`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked/1   [106 chars]
    en  I got as far as %2$s and turned round, because there was %3$s and I would rather be embarrassed than dead.
    >>  ............................................
    pt  Cheguei até %2$s e voltei, porque havia %3$s e eu prefiro passar vergonha a morrer.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked/2   [119 chars]
    en  %2$s stopped me. Not dramatically. I stood there for a while, worked out that %3$s made it a bad idea, and walked home.
    >>  ............................................
    pt  %2$s me barrou. Nada dramático. Fiquei ali um tempo, concluí que %3$s tornava aquilo má ideia, e voltei para casa.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.blocked/3   [84 chars]
    en  There is %3$s past %2$s, and I have been arguing with myself about it for four days.
    >>  ............................................
    pt  Tem %3$s depois de %2$s, e faz quatro dias que eu discuto isso comigo mesma.
    >>  ............................................
```


**Outcome 26 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.unfinished_delve.active.respond`
- …where the player's next choices will be: "Which day do you leave?" | "Come back with all your fingers." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.unfinished_delve.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.active`: the villager reports. Subject `work.adventurer.ruin_found`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active/1   [95 chars]
    en  I am going back to %2$s. Not today. But the deciding part is finished, which was the hard part.
    >>  ............................................
    pt  Vou voltar a %2$s. Não hoje. Mas a parte de decidir acabou, e essa era a parte difícil.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active/2   [90 chars]
    en  %2$s is on my list now rather than on my conscience, and those are very different weights.
    >>  ............................................
    pt  %2$s agora está na minha lista em vez de estar na minha consciência, e são pesos muito diferentes.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.active/3   [103 chars]
    en  I have the light for %2$s. Now I am doing the boring half, which is choosing a morning nobody needs me.
    >>  ............................................
    pt  Tenho luz para %2$s. Agora falta a metade chata, que é escolher uma manhã em que ninguém precise de mim.
    >>  ............................................
```


**Outcome 27 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.unfinished_delve.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.unfinished_delve.succeeded.respond`
- …where the player's next choices will be: "Was it worth the trouble?" | "You finished it. That counts." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.unfinished_delve.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.unfinished_delve.succeeded`: the villager reports. Subject `work.adventurer.ruin_found`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded/1   [93 chars]
    en  I got past %2$s. It took forty minutes and eleven torches and it was, in the end, a corridor.
    >>  ............................................
    pt  Passei de %2$s. Levou quarenta minutos e onze tochas e, no fim, era um corredor.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded/2   [110 chars]
    en  %2$s is behind me now, in both senses. I have been back, I have seen it, and the thing I feared was a draught.
    >>  ............................................
    pt  %2$s ficou para trás agora, nos dois sentidos. Voltei, vi, e a coisa que eu temia era uma corrente de ar.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.unfinished_delve.succeeded/3   [105 chars]
    en  Past %2$s there were three rooms and one of them had been slept in, a long time ago, by somebody careful.
    >>  ............................................
    pt  Depois de %2$s havia três salas, e em uma delas alguém tinha dormido, faz muito tempo, alguém cuidadoso.
    >>  ............................................
```


**Outcome 28 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.bad_route.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.bad_route.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.bad_route.active.respond`
- …where the player's next choices will be: "Is there a better way round?" | "Tell people it's five days." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.bad_route.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.bad_route.active`: the villager reports. Subject `work.adventurer.route`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.active/1   [91 chars]
    en  I was told %2$s was three days. It is five, and the person who told me has never walked it.
    >>  ............................................
    pt  Me disseram que %2$s eram três dias. São cinco, e quem me disse nunca a percorreu.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active/2   [119 chars]
    en  %2$s costs me two extra days every time, and I keep taking it, because the alternative is admitting I was sold a story.
    >>  ............................................
    pt  %2$s me custa dois dias a mais toda vez, e eu continuo pegando, porque a alternativa é admitir que me venderam uma história.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.active/3   [95 chars]
    en  There is a version of %2$s in everybody's head that is shorter and flatter than the actual one.
    >>  ............................................
    pt  Existe uma versão de %2$s na cabeça de todo mundo que é mais curta e mais plana do que a de verdade.
    >>  ............................................
```


**Outcome 29 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.bad_route.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.bad_route.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.bad_route.succeeded.respond`
- …where the player's next choices will be: "That saves someone a bad week." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.bad_route.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.bad_route.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.bad_route.succeeded`: the villager reports. Subject `work.adventurer.route`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded/1   [86 chars]
    en  I said five days about %2$s, out loud, twice, and the second time somebody thanked me.
    >>  ............................................
    pt  Eu disse cinco dias sobre %2$s, em voz alta, duas vezes, e na segunda alguém me agradeceu.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded/2   [86 chars]
    en  %2$s is five days and the village now knows it is five days, and the sky did not fall.
    >>  ............................................
    pt  %2$s são cinco dias e a vila agora sabe que são cinco dias, e o céu não caiu.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.bad_route.succeeded/3   [89 chars]
    en  The map I drew of %2$s is ugly and correct. Two carters have copied it, ugliness and all.
    >>  ............................................
    pt  O mapa que desenhei de %2$s é feio e certo. Dois carroceiros já copiaram, feiura e tudo.
    >>  ............................................
```


**Outcome 30 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.adventurer.souvenir.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.adventurer.souvenir.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.adventurer.souvenir.succeeded.respond`
- …where the player's next choices will be: "What's your best theory?" | "Somebody made that by hand." | "I'll let you pack."

```text
POOL   dialogue key: dialogue.conversations.scene.work.adventurer.souvenir.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.adventurer.souvenir.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.adventurer.souvenir.succeeded`: the villager reports. Subject `work.adventurer.the_one_that_got_away`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:adventurer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded/1   [75 chars]
    en  %2$s. I carried it eleven days and I still cannot tell you what it was for.
    >>  ............................................
    pt  %2$s. Carreguei isso por onze dias e ainda não sei dizer para que servia.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded/2   [85 chars]
    en  I brought back %2$s and no explanation, which is the least satisfying kind of trophy.
    >>  ............................................
    pt  Trouxe %2$s e nenhuma explicação, que é o tipo menos satisfatório de troféu.
    >>  ............................................
  dialogue.conversations.scene.work.adventurer.souvenir.succeeded/3   [134 chars]
    en  %2$s came out of a room that had been shut longer than this village has stood. I have stopped guessing and started just looking at it.
    >>  ............................................
    pt  %2$s saiu de uma sala fechada há mais tempo do que esta vila existe. Parei de adivinhar e passei só a olhar.
    >>  ............................................
```


**Outcome 31 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.arrow_shortage.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.arrow_shortage.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.arrow_shortage.blocked.respond`
- …where the player's next choices will be: "How much difference does that make?" | "I'll bring you feathers." | "Shoot closer until you're resupplied." | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.blocked`: the villager reports. Subject `work.archer.supply`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked/1   [91 chars]
    en  I have %2$s, so I have been shooting at %3$s with arrows I would be ashamed to hand anyone.
    >>  ............................................
    pt  Tenho %2$s, então venho atirando em %3$s com flechas que eu teria vergonha de entregar a alguém.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked/2   [99 chars]
    en  %2$s is the whole problem. A bow is simple. It is the twenty small things behind the bow that fail.
    >>  ............................................
    pt  %2$s é o problema inteiro. Um arco é simples. São as vinte coisinhas atrás do arco que falham.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked/3   [117 chars]
    en  Every arrow I loose at %3$s this week has %2$s, and I can see it in the flight, and I cannot fix it by aiming better.
    >>  ............................................
    pt  Toda flecha que eu solto em %3$s esta semana tem %2$s, e dá para ver no voo, e eu não conserto isso mirando melhor.
    >>  ............................................
```


**Outcome 32 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.arrow_shortage.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.arrow_shortage.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.arrow_shortage.succeeded.respond`
- …where the player's next choices will be: "What does a good group feel like?" | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.arrow_shortage.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.succeeded`: the villager reports. Subject `work.archer.supply`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded/1   [83 chars]
    en  Forty new arrows, all matched, and the group at %2$s tightened the first afternoon.
    >>  ............................................
    pt  Quarenta flechas novas, todas iguais, e o agrupamento em %2$s fechou já na primeira tarde.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded/2   [86 chars]
    en  I fletched until my thumbs were raw and it was the best evening I have had in a month.
    >>  ............................................
    pt  Empenei flechas até meus polegares descascarem, e foi a melhor noite que tive em um mês.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded/3   [88 chars]
    en  %2$s does not lie. Same bow, same arm, better arrows, and suddenly I am not the problem.
    >>  ............................................
    pt  %2$s não mente. Mesmo arco, mesmo braço, flechas melhores, e de repente o problema não sou eu.
    >>  ............................................
```


**Outcome 33 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.missed_shot.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.missed_shot.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.missed_shot.active.respond`
- …where the player's next choices will be: "Do you know what went wrong?" | "One arrow isn't your whole record." | "Then go and shoot it again tomorrow." | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.missed_shot.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.active`: the villager reports. Subject `work.archer.missed_shot`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active/1   [83 chars]
    en  I missed %2$s at forty paces and I have replayed it about nine hundred times since.
    >>  ............................................
    pt  Errei %2$s a quarenta passos e desde então já repassei isso umas novecentas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active/2   [97 chars]
    en  %2$s, clean shot, good light, and I pulled it left. There is no wind to blame and I have checked.
    >>  ............................................
    pt  %2$s, tiro limpo, boa luz, e eu puxei para a esquerda. Não tem vento para culpar, e eu conferi.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active/3   [94 chars]
    en  The frustrating part about %2$s is that I knew it was wrong while the string was still moving.
    >>  ............................................
    pt  O irritante de %2$s é que eu soube que estava errado enquanto a corda ainda se movia.
    >>  ............................................
```


**Outcome 34 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.missed_shot.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.missed_shot.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.missed_shot.succeeded.respond`
- …where the player's next choices will be: "That took discipline." | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.missed_shot.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.succeeded`: the villager reports. Subject `work.archer.missed_shot`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.succeeded/1   [99 chars]
    en  I went back out and put six through the same mark. %2$s is no longer a thing that lives in my head.
    >>  ............................................
    pt  Voltei ao campo e acertei seis na mesma marca. %2$s deixou de ser uma coisa que mora na minha cabeça.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.succeeded/2   [106 chars]
    en  It is finished. Not avenged — finished. I shot until it was boring and then I stopped thinking about %2$s.
    >>  ............................................
    pt  Acabou. Não vingado — acabado. Atirei até virar chato e aí parei de pensar em %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.succeeded/3   [111 chars]
    en  The miss is still in the tally. It just is not in the front of my mind any more, which is all I was asking for.
    >>  ............................................
    pt  O erro continua na conta. Só não fica mais na frente da minha cabeça, que era tudo o que eu pedia.
    >>  ............................................
```


**Outcome 35 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.teaching_a_child.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.teaching_a_child.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.teaching_a_child.active.respond`
- …where the player's next choices will be: "How are you teaching it?" | "Give them a whole season before you judge it." | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.teaching_a_child.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.teaching_a_child.active`: the villager reports. Subject `work.archer.teaching`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active/1   [100 chars]
    en  One of the children wants to learn and has %2$s, and correcting it wrong will put them off for life.
    >>  ............................................
    pt  Uma das crianças quer aprender e tem %2$s, e corrigir do jeito errado afasta a pessoa para sempre.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active/2   [121 chars]
    en  %2$s. That is the whole lesson at the moment, and the difficulty is saying it forty times without sounding tired of them.
    >>  ............................................
    pt  %2$s. É a aula inteira no momento, e a dificuldade é dizer isso quarenta vezes sem soar cansada dela.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active/3   [107 chars]
    en  I am teaching, badly, because %2$s is easy to see and very hard to describe to somebody standing inside it.
    >>  ............................................
    pt  Estou ensinando, mal, porque %2$s é fácil de ver e muito difícil de descrever para quem está dentro disso.
    >>  ............................................
```


**Outcome 36 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.archer.teaching_a_child.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.archer.teaching_a_child.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.archer.teaching_a_child.succeeded.respond`
- …where the player's next choices will be: "You taught them well." | "I'll let you get back to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.archer.teaching_a_child.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.teaching_a_child.succeeded`: the villager reports. Subject `work.archer.teaching`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded/1   [85 chars]
    en  They hit the mark three times running last week and looked at me as if I had done it.
    >>  ............................................
    pt  Ela acertou o alvo três vezes seguidas semana passada e olhou para mim como se eu tivesse feito aquilo.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded/2   [85 chars]
    en  The flinch went. I did not see it go. One afternoon it was simply not there any more.
    >>  ............................................
    pt  O tremor sumiu. Eu não vi sumir. Numa tarde simplesmente não estava mais lá.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded/3   [131 chars]
    en  They have started correcting themselves out loud before I say anything, which is the whole point and I did not expect it this year.
    >>  ............................................
    pt  Ela passou a se corrigir em voz alta antes de eu falar, que é o objetivo inteiro, e eu não esperava isso este ano.
    >>  ............................................
```


**Outcome 37 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cultist.village_suspicion.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cultist.village_suspicion.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cultist.village_suspicion.blocked.respond`
- …where the player's next choices will be: "What do they think you're doing?" | "That's unfair to you." | "Explain it to them openly." | "I'll leave you to your reading."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.blocked`: the villager reports. Subject `work.cultist.suspicion`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked/1   [82 chars]
    en  There was %3$s again. At %2$s, in daylight, where everybody could watch it happen.
    >>  ............................................
    pt  Teve %3$s de novo. Em %2$s, em plena luz, onde todo mundo pôde ver.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked/2   [135 chars]
    en  %2$s is the worst of it. I go for water like anyone and I get %3$s, and then I carry the bucket home slowly so nobody thinks I hurried.
    >>  ............................................
    pt  %2$s é o pior. Vou buscar água como qualquer um e recebo %3$s, e aí levo o balde para casa devagar para ninguém achar que corri.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked/3   [87 chars]
    en  %3$s. Four times this month. I have counted, which is itself a sign of how it is going.
    >>  ............................................
    pt  %3$s. Quatro vezes este mês. Eu contei, o que já diz bastante sobre como as coisas vão.
    >>  ............................................
```


**Outcome 38 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cultist.village_suspicion.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cultist.village_suspicion.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cultist.village_suspicion.succeeded.respond`
- …where the player's next choices will be: "What changed their minds?" | "I'll leave you to your reading."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cultist.village_suspicion.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.succeeded`: the villager reports. Subject `work.cultist.suspicion`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded/1   [109 chars]
    en  Somebody spoke to me at %2$s last week. About the weather. It was the best conversation I have had in a year.
    >>  ............................................
    pt  Alguém falou comigo em %2$s semana passada. Sobre o tempo. Foi a melhor conversa que tive em um ano.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded/2   [121 chars]
    en  It has eased. Not solved. Two households have decided I am dull rather than dangerous, and dull is an enormous promotion.
    >>  ............................................
    pt  Melhorou. Não resolveu. Duas casas decidiram que sou chata em vez de perigosa, e chata é uma promoção enorme.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded/3   [106 chars]
    en  %2$s is bearable now. I still go early out of habit, and I have started staying a little longer each time.
    >>  ............................................
    pt  %2$s está suportável agora. Ainda vou cedo por hábito, e comecei a ficar um pouco mais a cada vez.
    >>  ............................................
```


**Outcome 39 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cultist.empty_vigil.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cultist.empty_vigil.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cultist.empty_vigil.failed.respond`
- …where the player's next choices will be: "What do you make of that?" | "That sounds lonely." | "I'll leave you to your reading."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cultist.empty_vigil.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.empty_vigil.failed`: the villager reports. Subject `work.cultist.vigil`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed/1   [89 chars]
    en  I kept %2$s and nothing came of it, and I have told exactly one person that, and now two.
    >>  ............................................
    pt  Guardei %2$s e não deu em nada, e contei isso a exatamente uma pessoa, e agora a duas.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed/2   [97 chars]
    en  %2$s, from dark to dark, and at the end of it I was cold and I was still myself and that was all.
    >>  ............................................
    pt  %2$s, do escuro ao escuro, e no fim eu estava com frio e continuava eu mesma, e era só isso.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed/3   [116 chars]
    en  I sat out %2$s expecting something to be different by morning. It was not. I have been sitting with that ever since.
    >>  ............................................
    pt  Passei %2$s esperando que algo estivesse diferente de manhã. Não estava. Desde então eu sento com isso.
    >>  ............................................
```


**Outcome 40 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cultist.unanswered_question.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cultist.unanswered_question.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cultist.unanswered_question.active.respond`
- …where the player's next choices will be: "Keep asking it anyway." | "What's the question itself?" | "I'll leave you to your reading."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cultist.unanswered_question.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.unanswered_question.active`: the villager reports. Subject `work.cultist.doubt`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active/1   [84 chars]
    en  There is %2$s in the text and when I raise it, the room changes subject. Every time.
    >>  ............................................
    pt  Existe %2$s no texto, e quando eu levanto isso a sala muda de assunto. Toda vez.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active/2   [96 chars]
    en  %2$s. I have asked three people who should know and been given three different kinds of silence.
    >>  ............................................
    pt  %2$s. Perguntei a três pessoas que deveriam saber e recebi três tipos diferentes de silêncio.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active/3   [115 chars]
    en  I keep coming back to %2$s. Either it is nothing and I look foolish, or it is the only honest question in the book.
    >>  ............................................
    pt  Eu volto sempre a %2$s. Ou não é nada e eu pareço tola, ou é a única pergunta honesta do livro.
    >>  ............................................
```


**Outcome 41 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cultist.unanswered_question.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cultist.unanswered_question.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cultist.unanswered_question.succeeded.respond`
- …where the player's next choices will be: "Eleven times is persistence." | "I'll leave you to your reading."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cultist.unanswered_question.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.unanswered_question.succeeded`: the villager reports. Subject `work.cultist.doubt`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded/1   [109 chars]
    en  Somebody finally answered it. Badly, and out of irritation, and it was still the most useful hour of my year.
    >>  ............................................
    pt  Alguém finalmente respondeu. Mal, e por irritação, e ainda assim foi a hora mais útil do meu ano.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded/2   [111 chars]
    en  I asked it eleven times. On the eleventh, an old man told me the page had been removed on purpose, and by whom.
    >>  ............................................
    pt  Perguntei onze vezes. Na décima primeira, um senhor me disse que a página tinha sido retirada de propósito, e por quem.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded/3   [141 chars]
    en  It is answered. I am not going to pretend the answer was comfortable, but I would take an uncomfortable answer over a polite silence any day.
    >>  ............................................
    pt  Está respondida. Não vou fingir que a resposta foi confortável, mas prefiro uma resposta incômoda a um silêncio educado qualquer dia.
    >>  ............................................
```


**Outcome 42 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.weak_point.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.weak_point.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.weak_point.blocked.respond`
- …where the player's next choices will be: "What happens if nobody fixes it?" | "Bring me the planks and I'll help you fix it." | "Stop asking. Call it a danger and make them hear it." | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.blocked`: the villager reports. Subject `work.guard.weak_points`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked/1   [91 chars]
    en  There is %3$s at %2$s. I have reported it twice and been told twice that it is on the list.
    >>  ............................................
    pt  Tem %3$s em %2$s. Já relatei duas vezes e me disseram duas vezes que está na lista.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked/2   [109 chars]
    en  Walk %2$s with me some evening and you will see %3$s before I have to point at it. That is how obvious it is.
    >>  ............................................
    pt  Ande comigo por %2$s alguma noite e você vai ver %3$s antes de eu precisar apontar. É assim de óbvio.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked/3   [70 chars]
    en  %2$s has %3$s and I have run out of ways to say so that sound patient.
    >>  ............................................
    pt  %2$s tem %3$s, e eu já esgotei os jeitos de dizer isso que ainda soam pacientes.
    >>  ............................................
```


**Outcome 43 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.weak_point.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.weak_point.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.weak_point.succeeded.respond`
- …where the player's next choices will be: "You kept asking. That's why it got done." | "Where's the next weak spot?" | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.weak_point.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.succeeded`: the villager reports. Subject `work.guard.weak_points`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded/1   [108 chars]
    en  %2$s is sound. I walked it twice last night for no reason except that I could walk past it without thinking.
    >>  ............................................
    pt  %2$s está firme. Passei por lá duas vezes ontem à noite sem motivo, só porque dava para passar sem pensar.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded/2   [114 chars]
    en  Mended. Four hours and a dozen planks, after a month of asking. I am trying not to be bitter about the arithmetic.
    >>  ............................................
    pt  Consertado. Quatro horas e uma dúzia de tábuas, depois de um mês pedindo. Estou tentando não ficar amarga com a conta.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded/3   [71 chars]
    en  It is done, and the watch feels different. Not safer, exactly. Lighter.
    >>  ............................................
    pt  Está feito, e a ronda parece outra. Não mais segura, exatamente. Mais leve.
    >>  ............................................
```


**Outcome 44 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.weak_point.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.weak_point.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.weak_point.failed.respond`
- …where the player's next choices will be: "You did your part. They didn't do theirs." | "How are you covering it in the meantime?" | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.weak_point.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.failed`: the villager complains. Subject `work.guard.weak_points`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed/1   [112 chars]
    en  I have stopped raising %2$s. %3$s is still there and I have moved my patrol so that I do not have to look at it.
    >>  ............................................
    pt  Parei de falar de %2$s. %3$s continua lá e eu mudei minha ronda para não ter de olhar.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed/2   [140 chars]
    en  It did not get fixed and it is not going to. I have written it in the book so that when it matters, it will at least have been written down.
    >>  ............................................
    pt  Não foi consertado e não vai ser. Registrei no livro para que, quando importar, ao menos tenha ficado escrito.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed/3   [69 chars]
    en  %3$s at %2$s, still. I am not angry any more, which I think is worse.
    >>  ............................................
    pt  %3$s em %2$s, ainda. Já não estou brava, o que acho que é pior.
    >>  ............................................
```


**Outcome 45 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.night_sighting.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.night_sighting.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.night_sighting.blocked.respond`
- …where the player's next choices will be: "How sure are you about what you saw?" | "I'll walk it with you tonight." | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.night_sighting.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.blocked`: the villager reports. Subject `work.guard.recent_threat`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked/1   [111 chars]
    en  %2$s, past %3$s, two nights running. I am not going to tell you what it was, because I do not know what it was.
    >>  ............................................
    pt  %2$s, depois de %3$s, duas noites seguidas. Não vou dizer o que era, porque eu não sei o que era.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked/2   [121 chars]
    en  I saw %2$s near %3$s. I have written down exactly that and nothing more, and half the village has already improved on it.
    >>  ............................................
    pt  Vi %2$s perto de %3$s. Anotei exatamente isso e nada mais, e metade da vila já melhorou a história.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked/3   [111 chars]
    en  There was %2$s at %3$s and I have spent the day refusing to guess out loud, which is harder than the watch was.
    >>  ............................................
    pt  Havia %2$s em %3$s, e passei o dia recusando adivinhar em voz alta, o que foi mais difícil que a ronda.
    >>  ............................................
```


**Outcome 46 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.night_sighting.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.night_sighting.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.night_sighting.succeeded.respond`
- …where the player's next choices will be: "You were careful about it the whole way through." | "Did the story get away from you?" | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.night_sighting.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.succeeded`: the villager reports. Subject `work.guard.recent_threat`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded/1   [119 chars]
    en  %2$s was a stray dog and a badly hung shutter, in that order. I have never been so pleased to have wasted three nights.
    >>  ............................................
    pt  %2$s era um cão perdido e uma janela mal pendurada, nessa ordem. Nunca fiquei tão contente por ter perdido três noites.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded/2   [135 chars]
    en  It resolved. Not dramatically — most things do not — and the village will never hear about it, which is exactly how a watch should end.
    >>  ............................................
    pt  Se resolveu. Sem drama — quase nada tem — e a vila nunca vai saber, que é exatamente como uma vigília deve terminar.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded/3   [117 chars]
    en  Nothing came of %2$s. I have said so at the well twice, because a rumour left alone grows and a rumour answered dies.
    >>  ............................................
    pt  Não deu em nada, %2$s. Já disse isso no poço duas vezes, porque boato deixado em paz cresce e boato respondido morre.
    >>  ............................................
```


**Outcome 47 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.failing_kit.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.failing_kit.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.failing_kit.active.respond`
- …where the player's next choices will be: "Replace it. Don't wait for it to fail." | "What's stopping you asking for a new one?" | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.failing_kit.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.active`: the villager reports. Subject `work.guard.equipment`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active/1   [123 chars]
    en  %2$s is going. Not gone. Going, in the way that means I will find out exactly when at the least convenient possible moment.
    >>  ............................................
    pt  %2$s está indo. Não foi. Está indo, do jeito que significa que vou descobrir exatamente quando no pior momento possível.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active/2   [82 chars]
    en  I have been nursing %2$s along for six weeks with a bit of cord and some optimism.
    >>  ............................................
    pt  Venho remendando %2$s há seis semanas com um pedaço de corda e otimismo.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active/3   [66 chars]
    en  Do not look at %2$s too closely. I know. I have known for a while.
    >>  ............................................
    pt  Não olhe %2$s de muito perto. Eu sei. Sei faz um tempo.
    >>  ............................................
```


**Outcome 48 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.guard.failing_kit.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.guard.failing_kit.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.guard.failing_kit.succeeded.respond`
- …where the player's next choices will be: "Six weeks, and it took four sentences." | "Does the new one suit you?" | "I'll let you get back to the wall."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.guard.failing_kit.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.succeeded`: the villager reports. Subject `work.guard.equipment`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded/1   [123 chars]
    en  %2$s is replaced. It does not fit right yet and it is already better than the thing it replaced, which tells you something.
    >>  ............................................
    pt  %2$s foi trocada. Ainda não assentou direito e já é melhor que a que substituiu, o que diz alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded/2   [118 chars]
    en  Done. Took an hour and a conversation I had been dreading for six weeks, and the conversation was four sentences long.
    >>  ............................................
    pt  Feito. Levou uma hora e uma conversa que eu temia há seis semanas, e a conversa teve quatro frases.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded/3   [87 chars]
    en  Sorted. I kept the old one, which I am aware is exactly the behaviour that got me here.
    >>  ............................................
    pt  Resolvido. Guardei a antiga, o que eu sei ser exatamente o comportamento que me trouxe até aqui.
    >>  ............................................
```


**Outcome 49 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mercenary.unpaid_contract.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mercenary.unpaid_contract.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mercenary.unpaid_contract.blocked.respond`
- …where the player's next choices will be: "What can you actually do about it?" | "I'll cover the emeralds you're owed." | "Take it to the headman." | "I'll let you see to your gear."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mercenary.unpaid_contract.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.blocked`: the villager reports. Subject `work.mercenary.contract`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked/1   [102 chars]
    en  I finished %2$s for %3$s and I have not been paid, and it has been three weeks of very polite letters.
    >>  ............................................
    pt  Terminei %2$s para %3$s e não fui paga, e já são três semanas de cartas muito educadas.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked/2   [104 chars]
    en  %3$s owes me for %2$s. The work was done properly. That is the part that makes it hard to be calm about.
    >>  ............................................
    pt  %3$s me deve por %2$s. O trabalho foi feito direito. É essa a parte que dificulta ficar calma.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.blocked/3   [105 chars]
    en  I did %2$s on a handshake, which was my mistake, and %3$s has remembered the handshake differently to me.
    >>  ............................................
    pt  Fiz %2$s no aperto de mão, o que foi erro meu, e %3$s se lembra do aperto de mão de outro jeito.
    >>  ............................................
```


**Outcome 50 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mercenary.unpaid_contract.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mercenary.unpaid_contract.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mercenary.unpaid_contract.succeeded.respond`
- …where the player's next choices will be: "Written terms from now on, then." | "I'll let you see to your gear."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mercenary.unpaid_contract.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.unpaid_contract.succeeded`: the villager reports. Subject `work.mercenary.contract`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded/1   [84 chars]
    en  Settled. Not generously, not with an apology, but settled, and %2$s is off my books.
    >>  ............................................
    pt  Resolvido. Sem generosidade, sem desculpas, mas resolvido, e %2$s saiu dos meus livros.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded/2   [94 chars]
    en  I was paid in the end. Half of it in goods I did not want, which I have decided to find funny.
    >>  ............................................
    pt  Fui paga no fim. Metade em mercadoria que eu não queria, e decidi achar isso engraçado.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.unpaid_contract.succeeded/3   [113 chars]
    en  It closed. The useful part is that I now write things down, and %2$s was the last job I ever took on a handshake.
    >>  ............................................
    pt  Fechou. A parte útil é que agora eu anoto tudo, e %2$s foi o último serviço que peguei no aperto de mão.
    >>  ............................................
```


**Outcome 51 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mercenary.job_refused.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mercenary.job_refused.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mercenary.job_refused.succeeded.respond`
- …where the player's next choices will be: "Where's your line, exactly?" | "Refusing cost you something." | "I'll let you see to your gear."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.job_refused.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mercenary.job_refused.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.job_refused.succeeded`: the villager reports. Subject `work.mercenary.the_line`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded/1   [96 chars]
    en  I was offered %2$s last month and I said no, and I have thought about the money most days since.
    >>  ............................................
    pt  Me ofereceram %2$s mês passado e eu disse não, e penso no dinheiro quase todo dia desde então.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded/2   [91 chars]
    en  %2$s. Good pay, short work, and I would have had to be somebody I am not for about an hour.
    >>  ............................................
    pt  %2$s. Bom pagamento, trabalho curto, e eu teria que ser alguém que não sou por cerca de uma hora.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.job_refused.succeeded/3   [124 chars]
    en  Somebody wanted %2$s done and came to me because of what I look like. I said no and they found somebody else by the weekend.
    >>  ............................................
    pt  Alguém queria %2$s feito e veio a mim pela minha aparência. Eu disse não e encontraram outra pessoa até o fim de semana.
    >>  ............................................
```


**Outcome 52 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mercenary.reputation.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mercenary.reputation.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mercenary.reputation.active.respond`
- …where the player's next choices will be: "Does it bother you?" | "Keep sitting down there anyway." | "I'll let you see to your gear."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mercenary.reputation.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.reputation.active`: the villager reports. Subject `work.mercenary.reputation`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.active/1   [129 chars]
    en  Conversation at %2$s goes quiet by about a quarter when I sit down. I have measured it, which is a bleak way to spend an evening.
    >>  ............................................
    pt  A conversa em %2$s cai um quarto quando eu sento. Eu medi, o que é um jeito sombrio de passar uma noite.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active/2   [114 chars]
    en  %2$s decided what I am before I had said anything, and being pleasant since has moved it approximately not at all.
    >>  ............................................
    pt  %2$s decidiu o que eu sou antes de eu ter dito qualquer coisa, e ser agradável desde então mudou aproximadamente nada.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.active/3   [111 chars]
    en  I am useful and unwelcome, at %2$s and most other places, and those two are apparently allowed to sit together.
    >>  ............................................
    pt  Sou útil e indesejada, em %2$s e na maioria dos lugares, e aparentemente essas duas coisas podem conviver.
    >>  ............................................
```


**Outcome 53 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.mercenary.reputation.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.mercenary.reputation.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.mercenary.reputation.succeeded.respond`
- …where the player's next choices will be: "You outlasted it." | "I'll let you see to your gear."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mercenary.reputation.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.mercenary.reputation.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mercenary.reputation.succeeded`: the villager reports. Subject `work.mercenary.reputation`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:mercenary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mercenary.reputation.succeeded/1   [101 chars]
    en  Somebody kept a seat for me at %2$s. A seat. I have thought about it more than I would like to admit.
    >>  ............................................
    pt  Alguém guardou um lugar para mim em %2$s. Um lugar. Pensei nisso mais do que gostaria de admitir.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.succeeded/2   [120 chars]
    en  The quiet at %2$s does not happen any more. It stopped happening at some point and I did not notice the week it stopped.
    >>  ............................................
    pt  O silêncio em %2$s não acontece mais. Parou de acontecer em algum momento e eu não percebi a semana em que parou.
    >>  ............................................
  dialogue.conversations.scene.work.mercenary.reputation.succeeded/3   [105 chars]
    en  Four years, near enough. %2$s has decided I am furniture, and furniture is exactly what I was aiming for.
    >>  ............................................
    pt  Quatro anos, quase. %2$s decidiu que eu sou mobília, e mobília era exatamente o meu objetivo.
    >>  ............................................
```


**Outcome 54 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.old_debt.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.old_debt.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.old_debt.blocked.respond`
- …where the player's next choices will be: "Why pay it back at all?" | "I'll put emeralds toward it." | "Go to them yourself." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.old_debt.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.blocked`: the villager reports. Subject `work.outlaw.people_owed`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.blocked/1   [95 chars]
    en  I owe %2$s %3$s and I have saved about a third of it, which after two years is not a good rate.
    >>  ............................................
    pt  Devo %3$s a %2$s e já juntei cerca de um terço, o que depois de dois anos não é bom ritmo.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked/2   [83 chars]
    en  %3$s. That is what %2$s is out, and no amount of my being sorry converts into %3$s.
    >>  ............................................
    pt  %3$s. É isso que %2$s perdeu, e nenhuma quantidade de arrependimento meu vira %3$s.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.blocked/3   [116 chars]
    en  I have been paying %2$s back in instalments through a third party, because turning up in person would frighten them.
    >>  ............................................
    pt  Venho pagando %2$s em parcelas por intermédio de terceiros, porque aparecer em pessoa assustaria eles.
    >>  ............................................
```


**Outcome 55 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.old_debt.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.old_debt.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.old_debt.succeeded.respond`
- …where the player's next choices will be: "What did they send back?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_debt.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.old_debt.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_debt.succeeded`: the villager reports. Subject `work.outlaw.people_owed`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded/1   [99 chars]
    en  Paid. All of it, through the same third party, and %2$s sent back three words and I have kept them.
    >>  ............................................
    pt  Paga. Toda, pelo mesmo intermediário, e %2$s mandou de volta três palavras que eu guardei.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded/2   [103 chars]
    en  It is settled. I expected to feel something and what I felt was that the next one is still outstanding.
    >>  ............................................
    pt  Está quitada. Eu esperava sentir alguma coisa e o que senti foi que a próxima continua em aberto.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_debt.succeeded/3   [110 chars]
    en  %2$s is square with me. Not friendly. Square. Square was the whole objective and I would like that understood.
    >>  ............................................
    pt  %2$s está quite comigo. Não amigável. Quite. Quite era o objetivo inteiro, e gostaria que isso ficasse entendido.
    >>  ............................................
```


**Outcome 56 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.the_name.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.the_name.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.the_name.active.respond`
- …where the player's next choices will be: "Can that ever change?" | "I judge people by what they do now." | "Then be boring for fifteen years." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.the_name.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.active`: the villager reports. Subject `work.outlaw.the_name`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.active/1   [88 chars]
    en  %2$s knows what I was, and there is no version of the next ten years where %2$s forgets.
    >>  ............................................
    pt  %2$s sabe o que eu fui, e não existe versão dos próximos dez anos em que %2$s esqueça.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active/2   [115 chars]
    en  Every time something goes missing, %2$s looks at me first. Twice they have been decent enough to say so to my face.
    >>  ............................................
    pt  Toda vez que some alguma coisa, %2$s olha para mim primeiro. Duas vezes tiveram a decência de dizer na minha cara.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.active/3   [113 chars]
    en  %2$s has been fair and watchful at once, which is exactly what I would do, and it is still tiring to live inside.
    >>  ............................................
    pt  %2$s tem sido justo e vigilante ao mesmo tempo, que é exatamente o que eu faria, e ainda assim é cansativo viver dentro disso.
    >>  ............................................
```


**Outcome 57 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.the_name.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.the_name.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.the_name.succeeded.respond`
- …where the player's next choices will be: "Four years of boring did that." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.the_name.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.the_name.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.the_name.succeeded`: the villager reports. Subject `work.outlaw.the_name`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.the_name.succeeded/1   [87 chars]
    en  %2$s asked me to look at a lock last week. Asked. That is the first time in four years.
    >>  ............................................
    pt  %2$s me pediu para olhar uma fechadura semana passada. Pediu. É a primeira vez em quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.succeeded/2   [113 chars]
    en  Something went missing in the autumn and nobody came to my door, and I only noticed a week later that nobody had.
    >>  ............................................
    pt  Sumiu uma coisa no outono e ninguém bateu na minha porta, e eu só percebi uma semana depois que ninguém tinha vindo.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.the_name.succeeded/3   [114 chars]
    en  It has shifted. Not forgiven — shifted. %2$s treats me as a neighbour with a past rather than a past with a house.
    >>  ............................................
    pt  Mudou. Não perdoado — mudou. %2$s me trata como vizinha com passado, em vez de passado com uma casa.
    >>  ............................................
```


**Outcome 58 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.old_associate.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.old_associate.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.old_associate.blocked.respond`
- …where the player's next choices will be: "What do they want from you?" | "Tell the headman before they ask you." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.old_associate.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_associate.blocked`: the villager reports. Subject `work.outlaw.old_business`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.blocked/1   [66 chars]
    en  %2$s came through on Tuesday and knew exactly which door was mine.
    >>  ............................................
    pt  %2$s passou por aqui na terça e sabia exatamente qual porta era a minha.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked/2   [121 chars]
    en  I gave %2$s a meal and no answers, and %2$s is still in the district, which I know because I have been counting the days.
    >>  ............................................
    pt  Dei uma refeição a %2$s e nenhuma resposta, e %2$s continua na região, o que eu sei porque venho contando os dias.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.blocked/3   [99 chars]
    en  %2$s wants something. Nobody rides four days to say hello to a person who has stopped being useful.
    >>  ............................................
    pt  %2$s quer alguma coisa. Ninguém cavalga quatro dias para dar bom-dia a uma pessoa que parou de ser útil.
    >>  ............................................
```


**Outcome 59 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.outlaw.old_associate.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.outlaw.old_associate.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.outlaw.old_associate.succeeded.respond`
- …where the player's next choices will be: "That cost you something." | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.outlaw.old_associate.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.outlaw.old_associate.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.outlaw.old_associate.succeeded`: the villager reports. Subject `work.outlaw.old_business`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:outlaw` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded/1   [90 chars]
    en  I told the headman and %2$s left the district within two days, and I have not heard since.
    >>  ............................................
    pt  Contei ao chefe e %2$s saiu da região em dois dias, e não tive notícias desde então.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded/2   [110 chars]
    en  It went the way you said. A bad fortnight, and then it was simply a thing the village knew and had dealt with.
    >>  ............................................
    pt  Foi como você disse. Duas semanas ruins, e depois virou simplesmente uma coisa que a vila sabia e tinha resolvido.
    >>  ............................................
  dialogue.conversations.scene.work.outlaw.old_associate.succeeded/3   [132 chars]
    en  %2$s never did ask me. I will never know whether I gave away a friend or headed off a robbery, and I have decided to live with that.
    >>  ............................................
    pt  %2$s nunca chegou a me pedir nada. Nunca vou saber se entreguei uma amiga ou evitei um roubo, e decidi conviver com isso.
    >>  ............................................
```


**Outcome 60 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.armorer.stalled_commission.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.armorer.stalled_commission.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.armorer.stalled_commission.blocked.respond`
- …where the player's next choices will be: "What does stopping cost you?" | "I'll bring you iron." | "Tell the customer before market day." | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.armorer.stalled_commission.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.blocked`: the villager reports. Subject `work.armorer.materials`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked/1   [82 chars]
    en  %2$s is half finished on the bench and I have %3$s, so it will stay half finished.
    >>  ............................................
    pt  %2$s está pela metade na bancada e eu tenho %3$s, então vai continuar pela metade.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked/2   [111 chars]
    en  I promised %2$s by the market day. Then there was %3$s, and now I am the woman who breaks promises about steel.
    >>  ............................................
    pt  Prometi %2$s até o dia de feira. Aí veio %3$s, e agora eu sou a mulher que quebra promessas sobre aço.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.blocked/3   [116 chars]
    en  %3$s. It sounds small. It means %2$s stops, and a stopped piece cools wrong and fights you when you come back to it.
    >>  ............................................
    pt  %3$s. Parece pouco. Significa que %2$s para, e uma peça parada esfria errado e briga com você quando você volta.
    >>  ............................................
```


**Outcome 61 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.armorer.stalled_commission.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.armorer.stalled_commission.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.armorer.stalled_commission.succeeded.respond`
- …where the player's next choices will be: "How do you know when it's right?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.stalled_commission.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.armorer.stalled_commission.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.stalled_commission.succeeded`: the villager reports. Subject `work.armorer.materials`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded/1   [89 chars]
    en  %2$s went out on Thursday and it fits, and I watched him walk in it before I let him pay.
    >>  ............................................
    pt  %2$s saiu na quinta e serve, e eu o vi caminhar com aquilo antes de deixá-lo pagar.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded/2   [97 chars]
    en  It is done. %2$s is not the best thing I have made, and it is the one I am gladdest to be rid of.
    >>  ............................................
    pt  Está pronto. %2$s não é a melhor coisa que fiz, e é a de que estou mais feliz de me livrar.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.stalled_commission.succeeded/3   [110 chars]
    en  Finished. The last quarter of %2$s took one evening once the metal was there, which tells you the whole story.
    >>  ............................................
    pt  Terminado. O último quarto de %2$s levou uma noite depois que o metal chegou, o que já conta a história inteira.
    >>  ............................................
```


**Outcome 62 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.armorer.bad_fit.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.armorer.bad_fit.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.armorer.bad_fit.active.respond`
- …where the player's next choices will be: "What keeps them from returning?" | "Then take the piece to them." | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.armorer.bad_fit.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.bad_fit.active`: the villager reports. Subject `work.armorer.fitting`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.active/1   [111 chars]
    en  %2$s sits wrong on %3$s and they will not come back for a second fitting, and I cannot fix a body I cannot see.
    >>  ............................................
    pt  %2$s assenta errado em %3$s, e não vão voltar para uma segunda prova, e eu não conserto um corpo que não vejo.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active/2   [122 chars]
    en  I know %2$s is out by a finger's width. %3$s told me it was fine. It is not fine and in a month they will blame the steel.
    >>  ............................................
    pt  Eu sei que %2$s está a um dedo de erro. %3$s me disse que estava bom. Não está, e daqui a um mês vão culpar o aço.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.active/3   [106 chars]
    en  %3$s stood for the measuring and then moved the whole time, and now %2$s is a guess wearing a good polish.
    >>  ............................................
    pt  %3$s ficou de pé para a medida e se mexeu o tempo todo, e agora %2$s é um palpite com um bom polimento.
    >>  ............................................
```


**Outcome 63 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.armorer.bad_fit.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.armorer.bad_fit.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.armorer.bad_fit.succeeded.respond`
- …where the player's next choices will be: "That's a better way of working." | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.bad_fit.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.armorer.bad_fit.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.bad_fit.succeeded`: the villager reports. Subject `work.armorer.fitting`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded/1   [88 chars]
    en  I took %2$s to their door and had it right in twenty minutes, standing in their kitchen.
    >>  ............................................
    pt  Levei %2$s até a porta deles e acertei em vinte minutos, de pé na cozinha.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded/2   [86 chars]
    en  %2$s fits now. They were easier at home, which I should have worked out ten years ago.
    >>  ............................................
    pt  %2$s serve agora. Ficaram mais à vontade em casa, coisa que eu deveria ter concluído há dez anos.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.bad_fit.succeeded/3   [124 chars]
    en  Done, and two neighbours have since asked me to come to them instead. I may have accidentally invented a better way to work.
    >>  ............................................
    pt  Feito, e desde então duas vizinhas pediram que eu vá até elas. Talvez eu tenha inventado um jeito melhor de trabalhar sem querer.
    >>  ............................................
```


**Outcome 64 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.armorer.burn.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.armorer.burn.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.armorer.burn.succeeded.respond`
- …where the player's next choices will be: "Does it still trouble you?" | "I'll let you get back to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.armorer.burn.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.armorer.burn.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.armorer.burn.succeeded`: the villager reports. Subject `work.armorer.heat`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:armorer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.armorer.burn.succeeded/1   [106 chars]
    en  %2$s. Six years ago, and it still tells me when the weather is turning, which is more use than most scars.
    >>  ............................................
    pt  %2$s. Seis anos atrás, e ainda me avisa quando o tempo vai virar, o que é mais útil do que a maioria das cicatrizes.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.burn.succeeded/2   [95 chars]
    en  The mark on %2$s is from being tired at the end of a good day, which is when it always happens.
    >>  ............................................
    pt  A marca em %2$s é de estar cansada no fim de um dia bom, que é sempre quando acontece.
    >>  ............................................
  dialogue.conversations.scene.work.armorer.burn.succeeded/3   [123 chars]
    en  I got %2$s reaching for something I should have let fall. Every smith has one of those and every one of them was avoidable.
    >>  ............................................
    pt  Ganhei %2$s tentando pegar algo que eu deveria ter deixado cair. Todo ferreiro tem uma dessas e todas eram evitáveis.
    >>  ............................................
```


**Outcome 65 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.hard_slaughter.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.hard_slaughter.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.hard_slaughter.blocked.respond`
- …where the player's next choices will be: "How do you get yourself to do it?" | "That's a heavy thing to carry." | "Waiting is worse for her." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.hard_slaughter.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.blocked`: the villager reports. Subject `work.butcher.animals`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked/1   [94 chars]
    en  %2$s has to go this week and I have put it off twice, which helps nobody and least of all her.
    >>  ............................................
    pt  %2$s tem que ir esta semana e eu já adiei duas vezes, o que não ajuda ninguém, muito menos ela.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked/2   [106 chars]
    en  I have known %2$s for nine years. That is the whole difficulty and it is not a good enough reason to wait.
    >>  ............................................
    pt  Conheço %2$s há nove anos. É essa a dificuldade inteira, e não é razão boa o bastante para esperar.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.blocked/3   [105 chars]
    en  %2$s is in pain and I am the person who ends that, and I have been finding urgent errands for three days.
    >>  ............................................
    pt  %2$s está com dor e eu sou a pessoa que encerra isso, e faz três dias que eu invento tarefas urgentes.
    >>  ............................................
```


**Outcome 66 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.hard_slaughter.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.hard_slaughter.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.hard_slaughter.succeeded.respond`
- …where the player's next choices will be: "You did right by her." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.hard_slaughter.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.hard_slaughter.succeeded`: the villager reports. Subject `work.butcher.animals`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded/1   [104 chars]
    en  It is done. %2$s went easily, first light, and I sat on the wall afterwards for longer than I needed to.
    >>  ............................................
    pt  Está feito. %2$s foi tranquila, ao amanhecer, e depois eu fiquei sentada no muro mais tempo do que precisava.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded/2   [109 chars]
    en  %2$s is dealt with and nothing was wasted, and that is the closest thing to a good outcome this trade offers.
    >>  ............................................
    pt  %2$s foi resolvida e nada se perdeu, e isso é o mais perto de um bom desfecho que este ofício oferece.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.hard_slaughter.succeeded/3   [133 chars]
    en  Done properly. I will not pretend I feel nothing. I will say that feeling something about it is how I know I am still doing it right.
    >>  ............................................
    pt  Feito direito. Não vou fingir que não sinto nada. Vou dizer que sentir alguma coisa é como eu sei que ainda faço certo.
    >>  ............................................
```


**Outcome 67 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.spoiling_store.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.spoiling_store.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.spoiling_store.blocked.respond`
- …where the player's next choices will be: "How much would be lost?" | "I'll bring you something to cure it." | "Sell it cheap today, then." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.spoiling_store.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.blocked`: the villager reports. Subject `work.butcher.winter_store`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked/1   [88 chars]
    en  There is %3$s hanging and I have %2$s, and I am watching good food turn into an apology.
    >>  ............................................
    pt  Tem %3$s pendurado e eu tenho %2$s, e estou vendo boa comida virar um pedido de desculpas.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked/2   [102 chars]
    en  %2$s. Which means %3$s has about four days, and after that it is dogs' food and a whole animal wasted.
    >>  ............................................
    pt  %2$s. O que significa que %3$s tem uns quatro dias, e depois disso é comida de cachorro e um animal inteiro desperdiçado.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.blocked/3   [151 chars]
    en  I can smell it starting. %3$s is fine today. With %2$s it will not be fine on Friday and everybody will be very understanding about it, which is worse.
    >>  ............................................
    pt  Dá para sentir o cheiro começando. %3$s está bom hoje. Com %2$s não vai estar bom na sexta, e todo mundo vai ser muito compreensivo, o que é pior.
    >>  ............................................
```


**Outcome 68 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.spoiling_store.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.spoiling_store.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.spoiling_store.failed.respond`
- …where the player's next choices will be: "What happens with the farmer?" | "A warm week isn't your fault." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.spoiling_store.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.spoiling_store.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.spoiling_store.failed`: the villager reports. Subject `work.butcher.winter_store`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.spoiling_store.failed/1   [114 chars]
    en  I lost %2$s. Buried it myself so that nobody would be tempted, and I have not told the farmer the whole of it yet.
    >>  ............................................
    pt  Perdi %2$s. Enterrei eu mesma para ninguém se tentar, e ainda não contei tudo ao fazendeiro.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed/2   [111 chars]
    en  %2$s went. I did everything I could think of on the last day and everything I could think of was two days late.
    >>  ............................................
    pt  %2$s se foi. Fiz tudo o que consegui pensar no último dia, e tudo o que consegui pensar chegou dois dias atrasado.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.spoiling_store.failed/3   [111 chars]
    en  It spoiled. I want to say it was the weather. It was the weather and also me not asking for help on the Monday.
    >>  ............................................
    pt  Estragou. Eu queria dizer que foi o tempo. Foi o tempo e também eu não ter pedido ajuda na segunda-feira.
    >>  ............................................
```


**Outcome 69 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.the_hands.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.the_hands.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.the_hands.active.respond`
- …where the player's next choices will be: "Does that wear on you?" | "Somebody has to do it, and well." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.the_hands.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.the_hands.active`: the villager reports. Subject `work.butcher.squeamish_customers`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.active/1   [114 chars]
    en  People buy from me at %2$s and look at my hands the whole time, and then ask for it wrapped so they cannot see it.
    >>  ............................................
    pt  As pessoas compram de mim em %2$s e ficam olhando minhas mãos o tempo todo, e depois pedem embrulhado para não ver.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active/2   [129 chars]
    en  At %2$s I am the only person who has to think about where supper came from, and everybody else gets to be squeamish at me for it.
    >>  ............................................
    pt  Em %2$s eu sou a única pessoa que precisa pensar de onde veio a janta, e todo mundo tem o direito de sentir nojo de mim por isso.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.active/3   [101 chars]
    en  I scrub to the elbow twice a day. It makes no difference at %2$s. It is not dirt they are looking at.
    >>  ............................................
    pt  Eu esfrego até o cotovelo duas vezes por dia. Não faz diferença em %2$s. Não é sujeira que estão olhando.
    >>  ............................................
```


**Outcome 70 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.butcher.the_hands.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.butcher.the_hands.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.butcher.the_hands.succeeded.respond`
- …where the player's next choices will be: "You changed how they see it." | "I'll let you get back to the block."

```text
POOL   dialogue key: dialogue.conversations.scene.work.butcher.the_hands.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.butcher.the_hands.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.butcher.the_hands.succeeded`: the villager reports. Subject `work.butcher.squeamish_customers`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:butcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.butcher.the_hands.succeeded/1   [96 chars]
    en  A child asked me how it works last week, properly and without flinching, and her mother let her.
    >>  ............................................
    pt  Uma criança me perguntou como funciona semana passada, direito e sem se encolher, e a mãe deixou.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.succeeded/2   [103 chars]
    en  Two people have started asking for the cuts by name instead of pointing, which sounds small and is not.
    >>  ............................................
    pt  Duas pessoas começaram a pedir os cortes pelo nome em vez de apontar, o que parece pouco e não é.
    >>  ............................................
  dialogue.conversations.scene.work.butcher.the_hands.succeeded/3   [121 chars]
    en  It has eased. I stopped apologising for the trade about a year ago and, oddly, that is when people stopped needing me to.
    >>  ............................................
    pt  Melhorou. Parei de me desculpar pelo ofício faz um ano e, curiosamente, foi aí que as pessoas pararam de precisar disso.
    >>  ............................................
```


**Outcome 71 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.stalled_survey.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.stalled_survey.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.stalled_survey.blocked.respond`
- …where the player's next choices will be: "Why does waiting make it worse?" | "I'll bring you paper." | "Sketch it rough on anything to hand." | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.stalled_survey.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.blocked`: the villager reports. Subject `work.cartographer.paper`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked/1   [102 chars]
    en  I have walked all of %2$s and I have %3$s, so it exists only in my head and my head is not a document.
    >>  ............................................
    pt  Percorri %2$s inteiro e tenho %3$s, então aquilo só existe na minha cabeça, e minha cabeça não é um documento.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked/2   [109 chars]
    en  %3$s. Four weeks of pacing %2$s is sitting in a notebook getting less trustworthy every day I do not draw it.
    >>  ............................................
    pt  %3$s. Quatro semanas caminhando %2$s estão num caderno ficando menos confiáveis a cada dia que eu não desenho.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.blocked/3   [109 chars]
    en  The measurements for %2$s are good. They are also fading, because %3$s, and a fading measurement is a rumour.
    >>  ............................................
    pt  As medidas de %2$s estão boas. Também estão sumindo, porque %3$s, e medida que some é boato.
    >>  ............................................
```


**Outcome 72 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.stalled_survey.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.stalled_survey.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.stalled_survey.succeeded.respond`
- …where the player's next choices will be: "Why draw the doubtful parts faintly?" | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.stalled_survey.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.stalled_survey.succeeded`: the villager reports. Subject `work.cartographer.paper`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded/1   [102 chars]
    en  %2$s is on paper. Two days of drawing and it came out closer to what I walked than I had dared expect.
    >>  ............................................
    pt  %2$s está no papel. Dois dias desenhando e saiu mais perto do que eu percorri do que eu ousava esperar.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded/2   [126 chars]
    en  It is finished, and I have marked the parts I am unsure of with a lighter hand, which almost nobody does and everybody should.
    >>  ............................................
    pt  Está pronto, e marquei com traço mais leve as partes de que não tenho certeza, coisa que quase ninguém faz e todo mundo deveria.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.stalled_survey.succeeded/3   [127 chars]
    en  The sheet for %2$s is done. A carter has already used it and come back to argue about one bend, which is exactly what I wanted.
    >>  ............................................
    pt  A folha de %2$s está pronta. Um carroceiro já usou e voltou para discutir uma curva, que é exatamente o que eu queria.
    >>  ............................................
```


**Outcome 73 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.wrong_map.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.wrong_map.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.wrong_map.blocked.respond`
- …where the player's next choices will be: "Go and correct all four sheets." | "How did the error get in?" | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.wrong_map.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.wrong_map.blocked`: the villager reports. Subject `work.cartographer.a_wrong_map`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked/1   [105 chars]
    en  There is %2$s on a sheet I sold to four people, and I did not find out until one of them came back cross.
    >>  ............................................
    pt  Tem %2$s numa folha que vendi para quatro pessoas, e eu só descobri quando uma delas voltou irritada.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked/2   [103 chars]
    en  %2$s. My sheet says otherwise, in ink, in my hand, and there are four of those sheets out in the world.
    >>  ............................................
    pt  %2$s. Minha folha diz outra coisa, a tinta, da minha mão, e existem quatro dessas folhas por aí.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.blocked/3   [131 chars]
    en  I have been wrong before and corrected it quietly. This one is %2$s, which is the sort of wrong that gets somebody soaked or worse.
    >>  ............................................
    pt  Já errei antes e corrigi em silêncio. Este erro é %2$s, do tipo que deixa alguém encharcado ou pior.
    >>  ............................................
```


**Outcome 74 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.wrong_map.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.wrong_map.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.wrong_map.succeeded.respond`
- …where the player's next choices will be: "Dating them is a good habit." | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.wrong_map.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.wrong_map.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.wrong_map.succeeded`: the villager reports. Subject `work.cartographer.a_wrong_map`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded/1   [107 chars]
    en  All four are corrected. The fourth took a letter and six weeks and a reply that was kinder than I deserved.
    >>  ............................................
    pt  As quatro foram corrigidas. A quarta levou uma carta, seis semanas e uma resposta mais gentil do que eu merecia.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded/2   [128 chars]
    en  Done. Two of them thanked me. One had already found out the hard way and was gracious about it, which I am still thinking about.
    >>  ............................................
    pt  Feito. Duas pessoas me agradeceram. Uma já tinha descoberto do jeito difícil e foi generosa, e eu ainda penso nisso.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.wrong_map.succeeded/3   [105 chars]
    en  Corrected, and I have started dating every sheet, so that the next person knows how old my confidence is.
    >>  ............................................
    pt  Corrigidas, e passei a datar todas as folhas, para que a próxima pessoa saiba a idade da minha confiança.
    >>  ............................................
```


**Outcome 75 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.unverified_account.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.unverified_account.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.unverified_account.active.respond`
- …where the player's next choices will be: "How do you judge an account?" | "Draw it, but mark it as hearsay." | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.unverified_account.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.unverified_account.active`: the villager reports. Subject `work.cartographer.accounts`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.active/1   [106 chars]
    en  %2$s described a lake three days east and I cannot check it, and a lake is a large thing to take on trust.
    >>  ............................................
    pt  %2$s descreveu um lago a três dias a leste e eu não consigo conferir, e um lago é coisa grande para se aceitar de boa-fé.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active/2   [123 chars]
    en  I have %2$s telling me about country I have never walked. Half of what travellers say is true and I cannot tell which half.
    >>  ............................................
    pt  Tenho %2$s me contando de uma região que eu nunca percorri. Metade do que viajantes dizem é verdade e eu não sei qual metade.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.active/3   [102 chars]
    en  %2$s gave me distances that are too round. Two days, four days, a week. Nobody walks in round numbers.
    >>  ............................................
    pt  %2$s me deu distâncias redondas demais. Dois dias, quatro dias, uma semana. Ninguém caminha em números redondos.
    >>  ............................................
```


**Outcome 76 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cartographer.unverified_account.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cartographer.unverified_account.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cartographer.unverified_account.succeeded.respond`
- …where the player's next choices will be: "Two independent accounts is proper work." | "I'll let you get back to the sheet."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cartographer.unverified_account.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cartographer.unverified_account.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cartographer.unverified_account.succeeded`: the villager reports. Subject `work.cartographer.accounts`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cartographer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded/1   [104 chars]
    en  Somebody else came through and described the same lake, from the other side, without knowing about %2$s.
    >>  ............................................
    pt  Outra pessoa passou por aqui e descreveu o mesmo lago, do outro lado, sem saber de %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded/2   [112 chars]
    en  Two accounts that agree and have never met. It is on the sheet in a firmer hand now, and I have kept both names.
    >>  ............................................
    pt  Dois relatos que concordam e nunca se encontraram. Está na folha com traço mais firme agora, e guardei os dois nomes.
    >>  ............................................
  dialogue.conversations.scene.work.cartographer.unverified_account.succeeded/3   [135 chars]
    en  %2$s was telling the truth. I would like to say I never doubted it. I did doubt it, properly, and that is why the mark can be firm now.
    >>  ............................................
    pt  %2$s estava dizendo a verdade. Eu gostaria de dizer que nunca duvidei. Duvidei, com razão, e é por isso que a marca pode ser firme agora.
    >>  ............................................
```


**Outcome 77 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.closed_door.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.closed_door.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.closed_door.blocked.respond`
- …where the player's next choices will be: "What do they actually need?" | "Ask them what they want instead." | "Turning up counts for something." | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.closed_door.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.blocked`: the villager reports. Subject `work.cleric.small_kindnesses`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.blocked/1   [108 chars]
    en  %2$s needs somebody and I get %3$s every time, and I have started to wonder whether I am the wrong somebody.
    >>  ............................................
    pt  %2$s precisa de alguém e eu recebo %3$s toda vez, e comecei a me perguntar se eu sou o alguém errado.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked/2   [123 chars]
    en  There is %3$s at %2$s. I keep going anyway, on the theory that turning up is most of it, and some weeks the theory is thin.
    >>  ............................................
    pt  Tem %3$s em %2$s. Continuo indo mesmo assim, pela teoria de que aparecer já é quase tudo, e em algumas semanas a teoria fica fina.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.blocked/3   [128 chars]
    en  %3$s. It is not rudeness. It is a household that has decided help is a debt, and I have not found the sentence that undoes that.
    >>  ............................................
    pt  %3$s. Não é grosseria. É uma casa que decidiu que ajuda é dívida, e eu ainda não achei a frase que desfaz isso.
    >>  ............................................
```


**Outcome 78 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.closed_door.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.closed_door.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.closed_door.succeeded.respond`
- …where the player's next choices will be: "What made the difference?" | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.closed_door.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.closed_door.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.closed_door.succeeded`: the villager reports. Subject `work.cleric.small_kindnesses`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.closed_door.succeeded/1   [107 chars]
    en  %2$s asked me in. Not for anything. They wanted somebody to eat with, and I have thought about it all week.
    >>  ............................................
    pt  %2$s me convidou para entrar. Sem motivo. Queriam alguém para comer junto, e eu pensei nisso a semana inteira.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.succeeded/2   [105 chars]
    en  I asked instead of offering and they told me, and what they wanted took an afternoon and a borrowed cart.
    >>  ............................................
    pt  Perguntei em vez de oferecer e me disseram, e o que queriam levou uma tarde e uma carroça emprestada.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.closed_door.succeeded/3   [104 chars]
    en  The door at %2$s is open now, in the ordinary sense of the phrase, which is the only sense that matters.
    >>  ............................................
    pt  A porta de %2$s está aberta agora, no sentido comum da expressão, que é o único sentido que importa.
    >>  ............................................
```


**Outcome 79 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.sitting_up.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.sitting_up.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.sitting_up.active.respond`
- …where the player's next choices will be: "What do you do through those hours?" | "I'll bring bread for the house." | "You need sleep as well." | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.sitting_up.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.active`: the villager reports. Subject `work.cleric.unseen_hours`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.active/1   [88 chars]
    en  I sat up with %2$s through %3$s. There is nothing to report. That is what sitting up is.
    >>  ............................................
    pt  Fiquei acordada com %2$s durante %3$s. Não há nada a relatar. É isso que velar significa.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active/2   [106 chars]
    en  %3$s is the difficult stretch. %2$s is calmer by then and I am not, and there is nobody to sit up with me.
    >>  ............................................
    pt  %3$s é o trecho difícil. %2$s já está mais calmo então, e eu não estou, e não há ninguém para velar comigo.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.active/3   [124 chars]
    en  I have been at %2$s three nights. The family sleep in shifts and I take %3$s, because I am the one who can sleep in the day.
    >>  ............................................
    pt  Estou com %2$s há três noites. A família dorme em turnos e eu fico com %3$s, porque sou quem pode dormir de dia.
    >>  ............................................
```


**Outcome 80 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.sitting_up.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.sitting_up.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.sitting_up.succeeded.respond`
- …where the player's next choices will be: "Nobody sees those hours but they count." | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.sitting_up.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.sitting_up.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.sitting_up.succeeded`: the villager reports. Subject `work.cleric.unseen_hours`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded/1   [103 chars]
    en  %2$s is through it. Sitting up did not cure anything. It meant nobody was alone at four in the morning.
    >>  ............................................
    pt  %2$s superou. Velar não curou nada. Significou que ninguém ficou sozinho às quatro da manhã.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded/2   [105 chars]
    en  It ended well, and I want to be careful about that, because I did not make it end well. I was only there.
    >>  ............................................
    pt  Terminou bem, e quero ser cuidadosa com isso, porque não fui eu que fiz terminar bem. Eu só estava lá.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.sitting_up.succeeded/3   [120 chars]
    en  The family slept a whole night. That is the thing I did, and it is smaller than people assume and larger than it sounds.
    >>  ............................................
    pt  A família dormiu uma noite inteira. Foi isso que eu fiz, e é menor do que as pessoas imaginam e maior do que soa.
    >>  ............................................
```


**Outcome 81 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.no_answer.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.no_answer.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.no_answer.active.respond`
- …where the player's next choices will be: "So what do you say to them?" | "Stay honest about the limits." | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.no_answer.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.no_answer.active`: the villager reports. Subject `work.cleric.doubt`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.active/1   [90 chars]
    en  The family of %2$s asked me why, and I said I did not know, and I watched that land badly.
    >>  ............................................
    pt  A família de %2$s me perguntou por quê, e eu disse que não sabia, e vi aquilo cair mal.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active/2   [132 chars]
    en  People want me to have an answer. I have a chair, a fire and a long attention span, and none of those sound like enough at the time.
    >>  ............................................
    pt  As pessoas querem que eu tenha uma resposta. Eu tenho uma cadeira, um fogo e paciência longa, e nada disso parece bastar na hora.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.active/3   [128 chars]
    en  I could say something comforting to the family of %2$s. It would work for a fortnight and fail them for the rest of their lives.
    >>  ............................................
    pt  Eu poderia dizer algo reconfortante à família de %2$s. Funcionaria por duas semanas e falharia com eles pelo resto da vida.
    >>  ............................................
```


**Outcome 82 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.cleric.no_answer.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.cleric.no_answer.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.cleric.no_answer.succeeded.respond`
- …where the player's next choices will be: "The honesty held up." | "I'll let you get on with your rounds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cleric.no_answer.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.cleric.no_answer.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cleric.no_answer.succeeded`: the villager reports. Subject `work.cleric.doubt`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:cleric` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cleric.no_answer.succeeded/1   [96 chars]
    en  They came to find me last week. Not for comfort. To tell me that not being lied to had mattered.
    >>  ............................................
    pt  Vieram me procurar semana passada. Não por consolo. Para dizer que não terem sido enganados fez diferença.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.succeeded/2   [133 chars]
    en  It took a year. The mother said she had been angry with me for months and had come round, and told me so in the middle of the market.
    >>  ............................................
    pt  Levou um ano. A mãe disse que ficou com raiva de mim por meses e que mudou de ideia, e disse isso no meio da feira.
    >>  ............................................
  dialogue.conversations.scene.work.cleric.no_answer.succeeded/3   [112 chars]
    en  I said I did not know and I kept turning up, and it turns out those two together are a kind of answer after all.
    >>  ............................................
    pt  Eu disse que não sabia e continuei aparecendo, e acontece que essas duas coisas juntas são um tipo de resposta afinal.
    >>  ............................................
```


**Outcome 83 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.crop_failing.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.crop_failing.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.crop_failing.blocked.respond`
- …where the player's next choices will be: "How long has that been happening?" | "I can bring you bone meal for it." | "Let it lie fallow a year. The ground is telling you something." | "Ground is beyond me. I'd be guessing." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.blocked`: the villager reports. Subject `work.farmer.crop_health`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked/1   [106 chars]
    en  %2$s is going yellow at the tips in %3$s and I have known why for four days. %4$s. Knowing has not helped.
    >>  ............................................
    pt  As pontas de %2$s estão amarelando em %3$s e faz quatro dias que eu sei por quê. %4$s. Saber não ajudou.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked/2   [122 chars]
    en  Come and look at %3$s if you like. %2$s is failing and it is %4$s, and I have not worked out what to do that I can afford.
    >>  ............................................
    pt  Venha ver %3$s, se quiser. %2$s está morrendo por causa de %4$s, e ainda não descobri o que fazer que caiba no meu bolso.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked/3   [105 chars]
    en  %4$s in %3$s. %2$s will not come to anything this year unless something changes, and nothing is changing.
    >>  ............................................
    pt  %4$s em %3$s. %2$s não vai dar em nada este ano se nada mudar, e nada está mudando.
    >>  ............................................
```


**Outcome 84 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.crop_failing.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.crop_failing.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.crop_failing.active.respond`
- …where the player's next choices will be: "What are the odds it comes back?" | "I'm glad it's holding." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.crop_failing.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.active`: the villager reports. Subject `work.farmer.crop_health`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active/1   [108 chars]
    en  %2$s in %3$s has stopped getting worse, which is not the same as getting better, but I will take it for now.
    >>  ............................................
    pt  %2$s em %3$s parou de piorar, o que não é a mesma coisa que melhorar, mas por ora eu aceito.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active/2   [103 chars]
    en  I have been out to %3$s twice a day since. %2$s is holding. I keep expecting to be punished for hoping.
    >>  ............................................
    pt  Tenho ido a %3$s duas vezes por dia desde então. %2$s está aguentando. Fico esperando ser castigada por ter esperança.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active/3   [151 chars]
    en  Better. Not good. %2$s has put out new growth at the near end of %3$s and nothing at the far end, and I do not know yet which end is telling the truth.
    >>  ............................................
    pt  Melhor. Não bom. %2$s brotou de novo na ponta de cá de %3$s e nada na ponta de lá, e ainda não sei qual das duas está falando a verdade.
    >>  ............................................
```


**Outcome 85 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.crop_failing.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.crop_failing.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.crop_failing.succeeded.respond`
- …where the player's next choices will be: "You kept it alive. That was you." | "Will you plant it again next year?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.crop_failing.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.succeeded`: the villager reports. Subject `work.farmer.crop_health`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded/1   [106 chars]
    en  %2$s came back. Not the far end of %3$s — that is gone — but the rest of it, and the rest of it is a year.
    >>  ............................................
    pt  %2$s se recuperou. A ponta de lá de %3$s não — essa se foi — mas o resto sim, e o resto é um ano inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded/2   [119 chars]
    en  I cut %2$s yesterday. Two thirds of what %3$s should give, which after this spring I will call a good year and mean it.
    >>  ............................................
    pt  Colhi %2$s ontem. Dois terços do que %3$s deveria dar, o que depois desta primavera eu chamo de bom ano e falo sério.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded/3   [121 chars]
    en  It made it. I stood in %3$s at dawn feeling foolish about how much %2$s had come to mean, and then I got on with the day.
    >>  ............................................
    pt  Vingou. Fiquei em %3$s de madrugada me sentindo boba com o tanto que %2$s tinha passado a significar, e depois toquei o dia.
    >>  ............................................
```


**Outcome 86 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.crop_failing.failed"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.crop_failing.failed", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.crop_failing.failed.respond`
- …where the player's next choices will be: "That's a year's work. I'm sorry." | "What would you do differently?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.crop_failing.failed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.failed`: the villager reminisces. Subject `work.farmer.crop_health`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed/1   [96 chars]
    en  I turned %2$s under last week. There was no point pretending %3$s was going to give me anything.
    >>  ............................................
    pt  Revirei %2$s na semana passada. Não adiantava fingir que %3$s ia me dar alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed/2   [96 chars]
    en  %3$s is bare. I gave %2$s until the ninth day and then I gave it three more, and then I stopped.
    >>  ............................................
    pt  %3$s está pelado. Dei a %2$s até o nono dia, depois dei mais três, e então parei.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed/3   [102 chars]
    en  It is done. I have ploughed %2$s back into %3$s, which at least means next year starts with something.
    >>  ............................................
    pt  Acabou. Arei %2$s de volta em %3$s, o que pelo menos faz o ano que vem começar com alguma coisa.
    >>  ............................................
```


**Outcome 87 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.price_dispute.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.price_dispute.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.price_dispute.blocked.respond`
- …where the player's next choices will be: "Ask for more. The worst they say is no." | "What do you think it's actually worth?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.price_dispute.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.blocked`: the villager complains. Subject `work.farmer.trade_prices`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked/1   [130 chars]
    en  %3$s has offered me the same for %2$s as last year, and last year was not a hard year. I have not said yes and I have not said no.
    >>  ............................................
    pt  %3$s me ofereceu pelo %2$s o mesmo do ano passado, e o ano passado não foi difícil. Não disse sim nem disse não.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked/2   [133 chars]
    en  There is a conversation I have been avoiding with %3$s about what %2$s is worth. Avoiding it is costing me more than having it would.
    >>  ............................................
    pt  Tem uma conversa que venho evitando com %3$s sobre quanto vale %2$s. Evitar está me custando mais do que ter ela custaria.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked/3   [131 chars]
    en  %3$s wants %2$s at last year's price. Everyone's flour costs what it costs; apparently only my end of it is supposed to stay still.
    >>  ............................................
    pt  %3$s quer %2$s pelo preço do ano passado. A farinha de todo mundo custa o que custa; pelo visto só o meu lado é que deve ficar parado.
    >>  ............................................
```


**Outcome 88 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.price_dispute.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.price_dispute.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.price_dispute.succeeded.respond`
- …where the player's next choices will be: "That took more nerve than the asking looked like." | "What did they say when you named it?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.price_dispute.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.succeeded`: the villager reports. Subject `work.farmer.trade_prices`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded/1   [116 chars]
    en  I asked %3$s. I got most of it — not all, but most — and %2$s is sold at a price I can say out loud without wincing.
    >>  ............................................
    pt  Falei com %3$s. Consegui quase tudo — não tudo, mas quase — e %2$s está vendido a um preço que eu digo em voz alta sem encolher.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded/2   [146 chars]
    en  It went better than a fortnight of dreading it suggested. %3$s barely blinked. I have been undercharging for %2$s out of politeness for two years.
    >>  ............................................
    pt  Correu melhor do que quinze dias de pavor sugeriam. %3$s mal piscou. Faz dois anos que eu cobro pouco por %2$s de pura educação.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded/3   [123 chars]
    en  Done. %2$s is spoken for at the new price and %3$s and I are still on speaking terms, which I had genuinely not counted on.
    >>  ............................................
    pt  Feito. %2$s está negociado pelo preço novo e eu e %3$s continuamos nos falando, o que sinceramente eu não esperava.
    >>  ............................................
```


**Outcome 89 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.pest_pressure.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.pest_pressure.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.pest_pressure.active.respond`
- …where the player's next choices will be: "Fence it properly. Once, and be done." | "How much of it can you live with?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.pest_pressure.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.active`: the villager reports. Subject `work.farmer.pests`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active/1   [111 chars]
    en  %2$s in %3$s again. Not enough to ruin me, enough to be there every morning, which is its own kind of grinding.
    >>  ............................................
    pt  %2$s em %3$s de novo. Não o bastante para me arruinar, o bastante para estar lá toda manhã, o que desgasta do seu próprio jeito.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active/2   [145 chars]
    en  I have moved the netting twice and %2$s have found their way round it twice. %3$s is going to have to be fenced properly and I keep not doing it.
    >>  ............................................
    pt  Já mudei a tela duas vezes e %2$s deram a volta nela duas vezes. %3$s vai ter de ser cercado direito e eu continuo não fazendo.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active/3   [100 chars]
    en  You will hear the noise before you see it. %2$s in %3$s, at dawn, every dawn since the seed went in.
    >>  ............................................
    pt  Você ouve o barulho antes de ver. %2$s em %3$s, ao amanhecer, todo amanhecer desde que a semente entrou.
    >>  ............................................
```


**Outcome 90 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.farmer.pest_pressure.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.farmer.pest_pressure.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.farmer.pest_pressure.succeeded.respond`
- …where the player's next choices will be: "Two days, after forty mornings." | "What did the timber cost you?" | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.farmer.pest_pressure.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.succeeded`: the villager reports. Subject `work.farmer.pests`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: humor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded/1   [141 chars]
    en  The fence is up along %3$s. Two days of my life and I have not seen %2$s inside it since, and I am quietly furious about how simple that was.
    >>  ............................................
    pt  A cerca está de pé ao longo de %3$s. Dois dias da minha vida, e não vi %2$s dentro dela desde então, e estou discretamente furiosa com o quanto foi simples.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded/2   [107 chars]
    en  %3$s is fenced. I woke at dawn out of habit, listened, heard nothing, and went back to sleep like a person.
    >>  ............................................
    pt  %3$s está cercado. Acordei ao amanhecer por hábito, escutei, não ouvi nada e voltei a dormir como gente.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded/3   [128 chars]
    en  Done, and done properly. %2$s can have the verge. They have stopped having the crop, which was always the whole of the argument.
    >>  ............................................
    pt  Feito, e feito direito. %2$s podem ficar com a beira. Pararam de ficar com a lavoura, que era a discussão inteira.
    >>  ............................................
```


**Outcome 91 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fisherman.torn_gear.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fisherman.torn_gear.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fisherman.torn_gear.blocked.respond`
- …where the player's next choices will be: "How long does mending take?" | "I'll bring you string for it." | "Borrow a net from somebody." | "I'll let you get back to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.blocked`: the villager reports. Subject `work.fisherman.nets`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked/1   [85 chars]
    en  I have %2$s, so %3$s might as well be dry land for all the good it does me this week.
    >>  ............................................
    pt  Tenho %2$s, então %3$s pode muito bem ser terra seca, do bem que me faz esta semana.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked/2   [96 chars]
    en  %2$s. I can mend it in an evening if I have twine, and I have been saying that for six evenings.
    >>  ............................................
    pt  %2$s. Conserto numa noite se eu tiver linha, e faz seis noites que eu digo isso.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked/3   [110 chars]
    en  The fish are at %3$s and I am sitting on a bank with %2$s across my knees, which is the whole of my complaint.
    >>  ............................................
    pt  O peixe está em %3$s e eu estou sentada na margem com %2$s no colo, e é essa a minha queixa inteira.
    >>  ............................................
```


**Outcome 92 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fisherman.torn_gear.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fisherman.torn_gear.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fisherman.torn_gear.succeeded.respond`
- …where the player's next choices will be: "How was the haul?" | "I'll let you get back to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fisherman.torn_gear.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.succeeded`: the villager reports. Subject `work.fisherman.nets`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded/1   [95 chars]
    en  Mended and back at %2$s before dawn, and the first haul was better than it had any right to be.
    >>  ............................................
    pt  Remendado e de volta a %2$s antes do amanhecer, e a primeira puxada foi melhor do que tinha direito de ser.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded/2   [110 chars]
    en  Two hours with a needle. Six days of waiting for two hours of work, which is the shape of most of my problems.
    >>  ............................................
    pt  Duas horas com uma agulha. Seis dias de espera por duas horas de trabalho, que é o formato da maioria dos meus problemas.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded/3   [114 chars]
    en  It holds. I tested it in the shallows before I trusted it at %2$s, because I have been caught out that way before.
    >>  ............................................
    pt  Aguenta. Testei no raso antes de confiar em %2$s, porque já me pegaram desprevenida assim.
    >>  ............................................
```


**Outcome 93 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fisherman.empty_water.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fisherman.empty_water.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fisherman.empty_water.active.respond`
- …where the player's next choices will be: "What do you think is causing it?" | "Then fish somewhere deeper." | "I'll let you get back to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fisherman.empty_water.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.empty_water.active`: the villager reports. Subject `work.fisherman.the_season`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active/1   [94 chars]
    en  %3$s has given me %2$s, and I have stopped being able to tell whether that is the river or me.
    >>  ............................................
    pt  %3$s me deu %2$s, e eu parei de conseguir dizer se isso é o rio ou sou eu.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active/2   [133 chars]
    en  %2$s. Everybody has a theory and all the theories are about somebody else's fault, which is how I know none of them are measurements.
    >>  ............................................
    pt  %2$s. Todo mundo tem uma teoria e todas as teorias são sobre a culpa de outra pessoa, e é assim que sei que nenhuma é medição.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active/3   [86 chars]
    en  I have fished %3$s for nineteen years and I have never seen %2$s at this time of year.
    >>  ............................................
    pt  Pesco em %3$s há dezenove anos e nunca vi %2$s nesta época.
    >>  ............................................
```


**Outcome 94 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fisherman.empty_water.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fisherman.empty_water.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fisherman.empty_water.succeeded.respond`
- …where the player's next choices will be: "Changing your habit took nerve." | "I'll let you get back to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fisherman.empty_water.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.empty_water.succeeded`: the villager reports. Subject `work.fisherman.the_season`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded/1   [91 chars]
    en  Deeper worked. Nineteen years of fishing one shelf and the fix was forty paces further out.
    >>  ............................................
    pt  O fundo funcionou. Dezenove anos pescando numa laje e a solução estava quarenta passos mais para fora.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded/2   [100 chars]
    en  I moved, and the catch came back inside a week, and I have been quietly annoyed about it ever since.
    >>  ............................................
    pt  Eu mudei, e a pescaria voltou em uma semana, e desde então ando discretamente irritada com isso.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded/3   [141 chars]
    en  It was the water temperature all along. I would like it on record that being right about the river feels worse than being wrong about myself.
    >>  ............................................
    pt  Era a temperatura da água o tempo todo. Quero registrado que estar certa sobre o rio dói mais do que estar errada sobre mim.
    >>  ............................................
```


**Outcome 95 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fisherman.bad_morning.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fisherman.bad_morning.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fisherman.bad_morning.succeeded.respond`
- …where the player's next choices will be: "Do you still go out alone?" | "I'm glad you came home." | "I'll let you get back to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fisherman.bad_morning.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.bad_morning.succeeded`: the villager reports. Subject `work.fisherman.weather`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded/1   [90 chars]
    en  There was %2$s two springs back and I got home, and I have not talked about it much since.
    >>  ............................................
    pt  Teve %2$s duas primaveras atrás e eu voltei para casa, e desde então falo pouco sobre isso.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded/2   [120 chars]
    en  %2$s. Half an hour. I have had worse days that felt like nothing and that half hour still sits somewhere behind my ribs.
    >>  ............................................
    pt  %2$s. Meia hora. Já tive dias piores que não pesaram nada, e aquela meia hora ainda mora atrás das minhas costelas.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded/3   [121 chars]
    en  I came in with %2$s behind me and tied up and went to the market as if it were an ordinary Tuesday, which is what you do.
    >>  ............................................
    pt  Cheguei com %2$s às minhas costas, amarrei o barco e fui à feira como se fosse uma terça comum, que é o que se faz.
    >>  ............................................
```


**Outcome 96 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.crooked_batch.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.crooked_batch.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.crooked_batch.blocked.respond`
- …where the player's next choices will be: "Are they usable at all?" | "I'll bring you sticks for new shafts." | "Destroy the bad ones." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.blocked`: the villager reports. Subject `work.fletcher.batch_work`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked/1   [81 chars]
    en  %2$s has %3$s and I found it at the end, which means I found it forty times over.
    >>  ............................................
    pt  %2$s tem %3$s e eu descobri no fim, o que quer dizer que descobri quarenta vezes.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked/2   [109 chars]
    en  %3$s runs through %2$s. Each one is nearly right, and nearly right is the most expensive thing in this trade.
    >>  ............................................
    pt  %3$s atravessa %2$s. Cada uma está quase certa, e quase certo é a coisa mais cara deste ofício.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked/3   [82 chars]
    en  I have %2$s on the bench with %3$s and a customer who is expecting them on Friday.
    >>  ............................................
    pt  Tenho %2$s na bancada com %3$s e um cliente esperando na sexta.
    >>  ............................................
```


**Outcome 97 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.crooked_batch.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.crooked_batch.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.crooked_batch.succeeded.respond`
- …where the player's next choices will be: "What does spinning one tell you?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.crooked_batch.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.succeeded`: the villager reports. Subject `work.fletcher.batch_work`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded/1   [102 chars]
    en  %2$s went out true. I spun every one on the bench before it left, which took an hour and was worth it.
    >>  ............................................
    pt  %2$s saiu reto. Girei cada uma na bancada antes de sair, o que levou uma hora e valeu a pena.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded/2   [107 chars]
    en  Remade and delivered, and the box of bad ones is ash, and the bench is clear for the first time in a month.
    >>  ............................................
    pt  Refeito e entregue, e a caixa dos ruins virou cinza, e a bancada está limpa pela primeira vez em um mês.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded/3   [110 chars]
    en  It is done. The second attempt took half as long as the first, which is the only consolation the trade offers.
    >>  ............................................
    pt  Está feito. A segunda tentativa levou metade do tempo da primeira, que é o único consolo do ofício.
    >>  ............................................
```


**Outcome 98 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.bad_supplier.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.bad_supplier.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.bad_supplier.active.respond`
- …where the player's next choices will be: "Send the next load back." | "Why does he send you the poor stock?" | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.bad_supplier.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.bad_supplier.active`: the villager reports. Subject `work.fletcher.shafts`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active/1   [75 chars]
    en  Third delivery running with %2$s, and I have paid full price for all three.
    >>  ............................................
    pt  Terceira entrega seguida com %2$s, e eu paguei preço cheio nas três.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active/2   [113 chars]
    en  %2$s. I sort out the good quarter and the rest is firewood, and I am buying firewood at the price of arrow stock.
    >>  ............................................
    pt  %2$s. Separo o quarto que presta e o resto é lenha, e estou comprando lenha a preço de material de flecha.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active/3   [120 chars]
    en  He sends %2$s because he knows I will make it work. Being good at your trade is apparently a reason to be treated worse.
    >>  ............................................
    pt  Ele manda %2$s porque sabe que eu dou um jeito. Ser boa no ofício é, aparentemente, motivo para ser tratada pior.
    >>  ............................................
```


**Outcome 99 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.bad_supplier.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.bad_supplier.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.bad_supplier.succeeded.respond`
- …where the player's next choices will be: "It took nerve to send it back." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.bad_supplier.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.bad_supplier.succeeded`: the villager reports. Subject `work.fletcher.shafts`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded/1   [111 chars]
    en  I sent it back. He came in person, which he has never done, and the next load was the best I have had in years.
    >>  ............................................
    pt  Devolvi. Ele veio em pessoa, coisa que nunca fez, e a carga seguinte foi a melhor que recebi em anos.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded/2   [127 chars]
    en  It was awkward for a fortnight and then it was simply the new arrangement, and I had made the fortnight much larger in my head.
    >>  ............................................
    pt  Foi constrangedor por duas semanas e depois virou simplesmente o novo arranjo, e eu tinha inflado muito essas duas semanas na cabeça.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded/3   [120 chars]
    en  The note was four lines long. Eleven years of bad wood ended in four lines, and I am still cross about the eleven years.
    >>  ............................................
    pt  O bilhete tinha quatro linhas. Onze anos de madeira ruim acabaram em quatro linhas, e eu ainda estou irritada com os onze anos.
    >>  ............................................
```


**Outcome 100 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.who_buys.active"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.who_buys.active", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.who_buys.active.respond`
- …where the player's next choices will be: "Do you ask what they're for?" | "Making them say it out loud is right." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.who_buys.active.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.who_buys.active`: the villager reports. Subject `work.fletcher.the_bow_trade`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active/1   [80 chars]
    en  %2$s came in yesterday wanting three dozen and would say nothing about what for.
    >>  ............................................
    pt  %2$s apareceu ontem querendo três dúzias e não quis dizer para quê.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active/2   [127 chars]
    en  I sold to %2$s last month and I have been turning it over since. An arrow does not decide anything. The person holding it does.
    >>  ............................................
    pt  Vendi para %2$s mês passado e venho remoendo desde então. Uma flecha não decide nada. Quem a segura, decide.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active/3   [107 chars]
    en  %2$s. I asked what the order was for and got a shrug, and a shrug is an answer, just not a comfortable one.
    >>  ............................................
    pt  %2$s. Perguntei para que era a encomenda e recebi um dar de ombros, e dar de ombros é uma resposta, só não uma confortável.
    >>  ............................................
```


**Outcome 101 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.fletcher.who_buys.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.fletcher.who_buys.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.fletcher.who_buys.succeeded.respond`
- …where the player's next choices will be: "A shrug isn't much to build on." | "I'll let you get back to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.fletcher.who_buys.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.who_buys.succeeded`: the villager reports. Subject `work.fletcher.the_bow_trade`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded/1   [105 chars]
    en  He came back and told me. Deer, for a family that had lost a barn, and he had been embarrassed to say so.
    >>  ............................................
    pt  Ele voltou e me contou. Cervo, para uma família que perdeu um celeiro, e ele tinha vergonha de dizer.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded/2   [107 chars]
    en  It was nothing at all. I had built a whole grim story out of a shrug, which is worth remembering next time.
    >>  ............................................
    pt  Não era nada. Eu tinha construído uma história sombria inteira em cima de um dar de ombros, o que vale lembrar da próxima vez.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded/3   [107 chars]
    en  I asked, he would not answer, and then a week later he answered anyway. People do come round, given a week.
    >>  ............................................
    pt  Perguntei, ele não respondeu, e uma semana depois respondeu assim mesmo. As pessoas mudam de ideia, dado uma semana.
    >>  ............................................
```


**Outcome 102 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.leatherworker.stubborn_hide.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.leatherworker.stubborn_hide.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.leatherworker.stubborn_hide.blocked.respond`
- …where the player's next choices will be: "Eleven weeks for one hide?" | "I'll bring you leather to cover it." | "Tell the customer it will be late." | "I'll let you get back to the pits."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.blocked`: the villager reports. Subject `work.leatherworker.hides`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked/1   [89 chars]
    en  %2$s came out with %3$s after eleven weeks, and eleven weeks is not a thing you get back.
    >>  ............................................
    pt  %2$s saiu com %3$s depois de onze semanas, e onze semanas não é coisa que se recupere.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked/2   [85 chars]
    en  %3$s on %2$s. I did everything the same as always, which is the part that worries me.
    >>  ............................................
    pt  %3$s em %2$s. Fiz tudo igual ao de sempre, e é essa a parte que me preocupa.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked/3   [108 chars]
    en  I have %2$s ruined by %3$s and a commission that assumed it would be fine, and both of those are my problem.
    >>  ............................................
    pt  Tenho %2$s arruinado por %3$s e uma encomenda que contava com isso dar certo, e as duas coisas são problema meu.
    >>  ............................................
```


**Outcome 103 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.leatherworker.stubborn_hide.succeeded"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.leatherworker.stubborn_hide.succeeded", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond`
- …where the player's next choices will be: "Why keep a record now?" | "I'll let you get back to the pits."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.succeeded`: the villager reports. Subject `work.leatherworker.hides`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded/1   [129 chars]
    en  The new pit came out clean. I changed one thing — I moved it out of the afternoon sun — and that was apparently the whole answer.
    >>  ............................................
    pt  O tanque novo saiu limpo. Mudei uma coisa — tirei do sol da tarde — e essa era, aparentemente, a resposta inteira.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded/2   [94 chars]
    en  It worked. Eleven weeks of doing nothing carefully, and then a hide you could fold like cloth.
    >>  ............................................
    pt  Deu certo. Onze semanas fazendo nada com cuidado, e depois um couro que se dobra como pano.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded/3   [128 chars]
    en  Good this time. I have written down what I did, which I have never bothered with in twenty years, and I feel foolish about that.
    >>  ............................................
    pt  Bom desta vez. Anotei o que eu fiz, coisa com que nunca me dei ao trabalho em vinte anos, e me sinto tola por isso.
    >>  ............................................
```


**Outcome 104 of 261** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "work.leatherworker.the_complaint.blocked"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "work.leatherworker.the_complaint.blocked", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `episodes` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `work` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.work` (this player only) for 36000 ticks
- Then opens: `conversations.scene.work.leatherworker.the_complaint.blocked.respond`
- …where the player's next choices will be: "Is there anything that would help?" | "Build the hedge and give them a date." | "I'll let you get back to the pits."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked
WHO    VILLAGER — what the player reads after pressing "Do you actually like your work?"
       spoken on: conversations.cat.profession, button `work`
       leaves the player on: conversations.scene.work.leatherworker.the_complaint.blocked.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.the_complaint.blocked`: the villager reports. Subject `work.leatherworker.the_smell`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked/1   [98 chars]
    en  %2$s has complained about the pits again, and they are right, and I have nowhere else to put them.
    >>  ............................................
    pt  %2$s reclamou dos tanques de novo, e têm razão, e eu não tenho outro lugar para pôr.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked/2   [102 chars]
    en  The smell is real. I stopped noticing it in my second year, which does not help %2$s in the slightest.
    >>  ............................................
    pt  O cheiro é real. Parei de sentir no segundo ano, o que não ajuda %2$s em nada.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked/3   [144 chars]
    en  %2$s wants me moved to the far side of the river. That is a mile and a half from the tannery water, and the water is the whole reason I am here.
    >>  ............................................
    pt  %2$s quer que eu vá para o outro lado do rio. São dois quilômetros da água do curtume, e a água é o motivo inteiro de eu estar aqui.
    >>  ............................................
```

