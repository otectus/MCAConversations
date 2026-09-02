# Topic: deep

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.topic.deep.again.respond`](#conversations-topic-deep-again-respond)

---

## `conversations.topic.deep.again.respond`

**Reached from 5 route(s):** `conversations.cat.personal` / `life`; `conversations.cat.personal` / `dreams`; `conversations.cat.personal` / `hopes`; `conversations.cat.personal` / `regrets`; `conversations.cat.personal` / `secret`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.again` — e.g. "You know my dream already. Saying it twice won't make it come faster."
- `conversations.hopes.again` — e.g. "You already asked what I'm hoping for, %1$s. My wishes haven't changed since breakfast."
- `conversations.life.again` — e.g. "I just told you my whole story. Let it breathe a little."
- `conversations.regrets.again` — e.g. "We turned those stones over already. Let them lie a while."
- `conversations.secret.again` — e.g. "One secret per season, %1$s. Any faster and they stop being secrets."


```text
POOL   dialogue key: dialogue.conversations.topic.deep.again.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.deep.again.respond   [18 chars]
    en  We were just here.
    >>  ............................................
    pt  A gente acabou de falar disso.
    >>  ............................................
```


### Button `apologize` — "Sorry — I've asked already."

*stance family `candor` · tone `gentle` · answers the beat(s) `dreams.again.to.deep.again`, `hopes.again.to.deep.again`, `life.again.to.deep.again`, `regrets.again.to.deep.again`, `secret.again.to.deep.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `deep.again.apologize` — accepted phrasings: "sorry, i have asked already"; "sorry about the story"; "my mistake, asked already"; "sorry about the dream"; "sorry about the hope"; "sorry about the regret"
  - the message must contain one of: `sorry`, `mistake`, `already`
  - scored words: `already`(1.5), `sorry`(1.2), `story`(0.8), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.deep.again.respond.apologize
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.deep.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.deep.again.respond.apologize   [27 chars]
    en  Sorry — I've asked already.
    >>  ............................................
    pt  Desculpa — já perguntei.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `life`
- Does: disposition — tension -2  _(recorded under topic `life.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.apologize.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.again.apologize/1   [87 chars]
    en  It's alright. It's a long story and I'd only have started it in the wrong place anyway.
    >>  ............................................
    pt  Tudo bem. É uma história longa e eu teria começado no lugar errado de qualquer jeito.
    >>  ............................................
  dialogue.conversations.life.again.apologize/2   [80 chars]
    en  No harm, %1$s. It's not as though the past has gone anywhere since this morning.
    >>  ............................................
    pt  Sem problema, %1$s. Não é como se o passado tivesse ido a algum lugar desde de manhã.
    >>  ............................................
  dialogue.conversations.life.again.apologize/3   [82 chars]
    en  Happens. I've told bits of it to so many people I lose track of who has which bit.
    >>  ............................................
    pt  Acontece. Já contei pedaços para tanta gente que perco a conta de quem tem qual pedaço.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `dreams`
- Does: disposition — tension -2  _(recorded under topic `dreams.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.again.apologize.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.again.apologize/1   [78 chars]
    en  It's alright. Saying it twice in a day makes it sound less likely, that's all.
    >>  ............................................
    pt  Tudo bem. Dizer duas vezes no mesmo dia só faz parecer menos provável.
    >>  ............................................
  dialogue.conversations.dreams.again.apologize/2   [81 chars]
    en  No harm, %1$s. It hasn't moved on since breakfast, but I appreciate the interest.
    >>  ............................................
    pt  Sem problema, %1$s. Não andou nada desde o café, mas agradeço o interesse.
    >>  ............................................
  dialogue.conversations.dreams.again.apologize/3   [66 chars]
    en  Happens. I'd rather be asked twice than not at all, if I'm honest.
    >>  ............................................
    pt  Acontece. Prefiro ser perguntado duas vezes a não ser perguntado, para ser honesto.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `hopes`
- Does: disposition — tension -2  _(recorded under topic `hopes.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.again.apologize.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.again.apologize/1   [80 chars]
    en  It's alright. Hopes don't change between morning and afternoon, more's the pity.
    >>  ............................................
    pt  Tudo bem. Esperança não muda entre a manhã e a tarde, infelizmente.
    >>  ............................................
  dialogue.conversations.hopes.again.apologize/2   [60 chars]
    en  No harm, %1$s. Ask me tomorrow and there might even be news.
    >>  ............................................
    pt  Sem problema, %1$s. Me pergunte amanhã e pode até ter novidade.
    >>  ............................................
  dialogue.conversations.hopes.again.apologize/3   [57 chars]
    en  Happens. It's a nice thing to be asked about, even twice.
    >>  ............................................
    pt  Acontece. É bom ser perguntado sobre isso, mesmo duas vezes.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `regrets`
- Does: disposition — tension -2  _(recorded under topic `regrets.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.again.apologize.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.again.apologize/1   [80 chars]
    en  It's alright. It isn't the sort of thing that gets lighter for a second telling.
    >>  ............................................
    pt  Tudo bem. Não é o tipo de coisa que fica mais leve na segunda vez.
    >>  ............................................
  dialogue.conversations.regrets.again.apologize/2   [64 chars]
    en  No harm, %1$s. Once a day is already more than I usually manage.
    >>  ............................................
    pt  Sem problema, %1$s. Uma vez por dia já é mais do que eu costumo aguentar.
    >>  ............................................
  dialogue.conversations.regrets.again.apologize/3   [85 chars]
    en  Happens. I'd sooner you forgot you asked than remembered what I answered, truthfully.
    >>  ............................................
    pt  Acontece. Preferia que você esquecesse a pergunta a lembrar a resposta, na verdade.
    >>  ............................................
```


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `secret`
- Does: disposition — tension -2  _(recorded under topic `secret.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.again.apologize.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.again.apologize/1   [69 chars]
    en  It's alright. It's not the sort of thing that improves by repetition.
    >>  ............................................
    pt  Tudo bem. Não é o tipo de coisa que melhora com repetição.
    >>  ............................................
  dialogue.conversations.secret.again.apologize/2   [36 chars]
    en  No harm, %1$s. Ask me in a few days.
    >>  ............................................
    pt  Sem problema, %1$s. Pergunte em uns dias.
    >>  ............................................
  dialogue.conversations.secret.again.apologize/3   [53 chars]
    en  Happens. I lose track of what I've said to whom, too.
    >>  ............................................
    pt  Acontece. Eu também perco a conta do que falei para quem.
    >>  ............................................
```


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the live session is in topic `life`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `dreams`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `hopes`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `regrets`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `secret`  _(chance -2000)_
- Does: disposition — tension -2  _(recorded under topic `life.again.apologize`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.apologize
WHO    VILLAGER — what the player reads after pressing "Sorry — I've asked already."
       spoken on: conversations.topic.deep.again.respond, button `apologize`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.apologize.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.topic.deep.again.respond` / button `apologize`** earlier in this file. Fill it in there, once.


### Button `press` — "Tell me again anyway."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `dreams.again.to.deep.again`, `hopes.again.to.deep.again`, `life.again.to.deep.again`, `regrets.again.to.deep.again`, `secret.again.to.deep.again`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `deep.again.press` — accepted phrasings: "tell me again anyway"; "the story again, please"; "go on, again"; "the dream again, please"; "the hope again, please"; "the regret again, please"
  - the message must contain one of: `again`, `anyway`, `humour`
  - scored words: `again`(1.5), `anyway`(1.2), `story`(0.8), `dream`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.deep.again.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.deep.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.deep.again.respond.press   [21 chars]
    en  Tell me again anyway.
    >>  ............................................
    pt  Me conta de novo mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `life`
- Does: **hearts -1** — decision id `life.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `life.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.press.terminal`: the villager resists. Subject `life.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.again.press/1   [31 chars]
    en  Same answer, shorter this time.
    >>  ............................................
    pt  Mesma resposta, mais curta desta vez.
    >>  ............................................
  dialogue.conversations.life.again.press/2   [49 chars]
    en  Twice in a day is a lot to ask of a person, %1$s.
    >>  ............................................
    pt  Duas vezes num dia é muito para pedir a uma pessoa, %1$s.
    >>  ............................................
  dialogue.conversations.life.again.press/3   [45 chars]
    en  ...Fine. But you'll get the abridged version.
    >>  ............................................
    pt  ...Tá. Mas você vai receber a versão resumida.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `dreams`
- Does: **hearts -1** — decision id `dreams.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `dreams.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.again.press.terminal`: the villager resists. Subject `dreams.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.again.press/1   [85 chars]
    en  Same one as this morning. It's not the sort of thing that gets a new version by noon.
    >>  ............................................
    pt  O mesmo de manhã. Não é coisa que ganhe versão nova até o meio-dia.
    >>  ............................................
  dialogue.conversations.dreams.again.press/2   [89 chars]
    en  You'll get the same answer with less enthusiasm, %1$s. That's what asking twice buys you.
    >>  ............................................
    pt  Você vai ouvir a mesma resposta com menos entusiasmo, %1$s. É o que perguntar duas vezes compra.
    >>  ............................................
  dialogue.conversations.dreams.again.press/3   [72 chars]
    en  ...Fine. But if it sounds smaller this time that's your doing, not mine.
    >>  ............................................
    pt  ...Tudo bem. Mas se soar menor desta vez, a culpa é sua, não minha.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `hopes`
- Does: **hearts -1** — decision id `hopes.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `hopes.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.again.press.terminal`: the villager resists. Subject `hopes.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.again.press/1   [59 chars]
    en  Same hope. Hopes are slow. That's rather the point of them.
    >>  ............................................
    pt  A mesma esperança. Esperança é lenta. É meio que o ponto dela.
    >>  ............................................
  dialogue.conversations.hopes.again.press/2   [85 chars]
    en  Nothing's changed since you last asked, %1$s. That's not a complaint, it's a harvest.
    >>  ............................................
    pt  Nada mudou desde que você perguntou, %1$s. Não é reclamação, é colheita.
    >>  ............................................
  dialogue.conversations.hopes.again.press/3   [73 chars]
    en  ...Fine. Still hoping. There, you've had it twice and it's twice as true.
    >>  ............................................
    pt  ...Tudo bem. Ainda esperando. Pronto, ouviu duas vezes e é duas vezes verdade.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `regrets`
- Does: **hearts -1** — decision id `regrets.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `regrets.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.again.press.terminal`: the villager resists. Subject `regrets.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.again.press/1   [80 chars]
    en  Same one. There's only ever been the one, which is either lucky or very unlucky.
    >>  ............................................
    pt  O mesmo. Só houve um, o que é sorte ou muito azar.
    >>  ............................................
  dialogue.conversations.regrets.again.press/2   [64 chars]
    en  Twice in a day is a lot to ask of that particular subject, %1$s.
    >>  ............................................
    pt  Duas vezes no mesmo dia é muito para esse assunto em particular, %1$s.
    >>  ............................................
  dialogue.conversations.regrets.again.press/3   [66 chars]
    en  ...Fine. The short version, and then we're done with it for today.
    >>  ............................................
    pt  ...Tudo bem. A versão curta, e depois encerramos por hoje.
    >>  ............................................
```


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `secret`
- Does: **hearts -1** — decision id `secret.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `secret.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.again.press.terminal`: the villager resists. Subject `secret.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.again.press/1   [75 chars]
    en  Same answer, and quieter this time, because the walls haven't moved either.
    >>  ............................................
    pt  A mesma resposta, e mais baixo desta vez, porque as paredes também não se mudaram.
    >>  ............................................
  dialogue.conversations.secret.again.press/2   [84 chars]
    en  One a season, I said. Asking twice in a day is how people end up with nothing, %1$s.
    >>  ............................................
    pt  Um por estação, eu disse. Perguntar duas vezes no dia é como se acaba sem nenhum, %1$s.
    >>  ............................................
  dialogue.conversations.secret.again.press/3   [86 chars]
    en  ...Fine. But you're getting the small one, and you're not getting another for a while.
    >>  ............................................
    pt  ...Tudo bem. Mas você leva o pequeno, e não leva outro tão cedo.
    >>  ............................................
```


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the live session is in topic `life`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `dreams`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `hopes`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `regrets`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `secret`  _(chance -2000)_
- Does: **hearts -1** — decision id `life.again.press`, budget `deep`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `life.again.press`)_
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.press
WHO    VILLAGER — what the player reads after pressing "Tell me again anyway."
       spoken on: conversations.topic.deep.again.respond, button `press`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.press.terminal`: the villager resists. Subject `life.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.topic.deep.again.respond` / button `press`** earlier in this file. Fill it in there, once.


### Button `leave` — "Fair. Another day."

*stance family `exit` · tone `plain` · answers the beat(s) `dreams.again.to.deep.again`, `hopes.again.to.deep.again`, `life.again.to.deep.again`, `regrets.again.to.deep.again`, `secret.again.to.deep.again` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.deep.again.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.deep.again.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.deep.again.respond.leave   [18 chars]
    en  Fair. Another day.
    >>  ............................................
    pt  Justo. Outro dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `life`
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.life.again.leave/1   [32 chars]
    en  Aye. Catch me later in the week.
    >>  ............................................
    pt  Tá. Me pega no fim da semana.
    >>  ............................................
  dialogue.conversations.life.again.leave/2   [22 chars]
    en  Take care of yourself.
    >>  ............................................
    pt  Se cuide.
    >>  ............................................
  dialogue.conversations.life.again.leave/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```


**Outcome 2 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `dreams`
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.dreams.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.again.leave.terminal`: the villager accepts. Subject `dreams.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.dreams.again.leave/1   [32 chars]
    en  Aye. Catch me later in the week.
    >>  ............................................
    pt  Tá. Me pega no fim da semana.
    >>  ............................................
  dialogue.conversations.dreams.again.leave/2   [9 chars]
    en  Quite so.
    >>  ............................................
    pt  Isso mesmo.
    >>  ............................................
  dialogue.conversations.dreams.again.leave/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```


**Outcome 3 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `hopes`
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.hopes.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `hopes.again.leave.terminal`: the villager accepts. Subject `hopes.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.hopes.again.leave/1   [32 chars]
    en  Aye. Catch me later in the week.
    >>  ............................................
    pt  Tá. Me pega no fim da semana.
    >>  ............................................
  dialogue.conversations.hopes.again.leave/2   [10 chars]
    en  Go safely.
    >>  ............................................
    pt  Vá com cuidado.
    >>  ............................................
  dialogue.conversations.hopes.again.leave/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```


**Outcome 4 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `regrets`
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.regrets.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `regrets.again.leave.terminal`: the villager accepts. Subject `regrets.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.regrets.again.leave/1   [32 chars]
    en  Aye. Catch me later in the week.
    >>  ............................................
    pt  Tá. Me pega no fim da semana.
    >>  ............................................
  dialogue.conversations.regrets.again.leave/2   [43 chars]
    en  There's nothing more to say about it today.
    >>  ............................................
    pt  Não há mais nada a dizer sobre isso hoje.
    >>  ............................................
  dialogue.conversations.regrets.again.leave/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```


**Outcome 5 of 6** — base weight `0`

- Fires when: weighted +100 when the live session is in topic `secret`
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.secret.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `secret.again.leave.terminal`: the villager accepts. Subject `secret.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.secret.again.leave/1   [32 chars]
    en  Aye. Catch me later in the week.
    >>  ............................................
    pt  Tá. Me pega no fim da semana.
    >>  ............................................
  dialogue.conversations.secret.again.leave/2   [19 chars]
    en  I'll see you about.
    >>  ............................................
    pt  A gente se vê por aí.
    >>  ............................................
  dialogue.conversations.secret.again.leave/3   [12 chars]
    en  Go on, then.
    >>  ............................................
    pt  Pode ir, então.
    >>  ............................................
```


**Outcome 6 of 6** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the live session is in topic `life`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `dreams`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `hopes`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `regrets`  _(chance -2000)_
- Fires when: RULED OUT when the live session is in topic `secret`  _(chance -2000)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.again.leave
WHO    VILLAGER — what the player reads after pressing "Fair. Another day."
       spoken on: conversations.topic.deep.again.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.leave.terminal`: the villager accepts. Subject `life.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.topic.deep.again.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

