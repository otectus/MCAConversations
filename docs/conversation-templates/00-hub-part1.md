# Hub, greeting and category pages

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.


**Parts of this conversation:** [part 1](00-hub-part1.md) · [part 2](00-hub-part2.md) · [part 3](00-hub-part3.md) · [part 4](00-hub-part4.md)


## Nodes in this file

- [`conversations`](#conversations)
- [`conversations.cat.chitchat`](#conversations-cat-chitchat)
- [`conversations.cat.events`](#conversations-cat-events)
- [`conversations.cat.personal`](#conversations-cat-personal)

---

## `conversations`

**Reached from 7 route(s):** `conversations.cat.chitchat` / `back`; `conversations.cat.events` / `back`; `conversations.cat.personal` / `back`; `conversations.cat.profession` / `back`; `conversations.cat.relationships` / `back`; `conversations.cat.village` / `back`; `main` / `conversations`


```text
POOL   dialogue key: dialogue.conversations
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations
ARGS   %1$s = the player's name
SIZE   5 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations/1   [47 chars]
    en  You want to talk? Alright. What's on your mind?
    >>  ............................................
    pt  Quer conversar? Tudo bem. O que você tem em mente?
    >>  ............................................
  dialogue.conversations/2   [30 chars]
    en  Sure, we can talk. What about?
    >>  ............................................
    pt  Claro, a gente pode conversar. Sobre o quê?
    >>  ............................................
  dialogue.conversations/3   [40 chars]
    en  I'm listening. What do you want to know?
    >>  ............................................
    pt  Estou ouvindo. O que você quer saber?
    >>  ............................................
  dialogue.conversations/4   [48 chars]
    en  Alright, you have my attention. All of it, even.
    >>  ............................................
    pt  Certo, você tem a minha atenção. Toda ela, inclusive.
    >>  ............................................
  dialogue.conversations/5   [42 chars]
    en  Pull up a fence post. What's on your mind?
    >>  ............................................
    pt  Puxa um mourão de cerca aí. O que você tem em mente?
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations/1
    en  A long talk? That's — that's fine. Good, even. Should I be worried? Sorry. Go on, %1$s.
    >>  ............................................
    pt  Uma conversa longa? Tudo — tudo bem. Bom, até. Eu deveria me preocupar? Desculpa. Pode falar, %1$s.
    >>  ............................................
  anxious.dialogue.conversations/2
    en  All right. Just — tell me if I start rambling. I do that when I'm nervous.
    >>  ............................................
    pt  Tudo bem. Só — me avisa se eu começar a divagar. Eu faço isso quando fico nervoso.
    >>  ............................................
  anxious.dialogue.conversations/3
    en  Yes, let's talk. It helps, actually. Keeps my head from running off on its own.
    >>  ............................................
    pt  Sim, vamos conversar. Ajuda, na verdade. Impede minha cabeça de sair correndo sozinha.
    >>  ............................................
  athletic.dialogue.conversations/1
    en  Talk? Sure. Walk with me though — two more laps around the fences before sundown. Keep up, %1$s.
    >>  ............................................
    pt  Conversar? Claro. Mas caminha comigo — mais duas voltas nas cercas antes do pôr do sol. Acompanha, %1$s.
    >>  ............................................
  athletic.dialogue.conversations/2
    en  Sure, but keep it moving. I think clearer with wind in my face, %1$s.
    >>  ............................................
    pt  Claro, mas mantém em movimento. Penso mais claro com vento na cara, %1$s.
    >>  ............................................
  athletic.dialogue.conversations/3
    en  Talk away — I'll stretch, you speak. Efficient.
    >>  ............................................
    pt  Pode falar — eu alongo, você fala. Eficiente.
    >>  ............................................
  confident.dialogue.conversations/1
    en  You've come to the right person, %1$s. Ask away — I give excellent answers.
    >>  ............................................
    pt  Você veio à pessoa certa, %1$s. Pode perguntar — eu dou respostas excelentes.
    >>  ............................................
  confident.dialogue.conversations/2
    en  You chose your company well today. Go on — I don't rattle easy.
    >>  ............................................
    pt  Você escolheu bem a companhia hoje. Pode falar — eu não me abalo fácil.
    >>  ............................................
  confident.dialogue.conversations/3
    en  Speak up. I've an answer for most things and a fine bluff for the rest.
    >>  ............................................
    pt  Fale alto. Tenho resposta pra quase tudo e um belo blefe pro resto.
    >>  ............................................
  crabby.dialogue.conversations/1
    en  A long talk? With me? Your judgement's questionable, %1$s. Sit down anyway.
    >>  ............................................
    pt  Uma conversa longa? Comigo? Seu julgamento é questionável, %1$s. Senta mesmo assim.
    >>  ............................................
  crabby.dialogue.conversations/2
    en  If you must. Don't expect me to enjoy it. ...I probably will. Don't mention it.
    >>  ............................................
    pt  Se for preciso. Não espera que eu goste. ...Provavelmente vou gostar. Não comenta.
    >>  ............................................
  crabby.dialogue.conversations/3
    en  Talk, then. I've been told I'm poor company. I've never disputed it.
    >>  ............................................
    pt  Fala, então. Já me disseram que sou má companhia. Nunca contestei.
    >>  ............................................
  extroverted.dialogue.conversations/1
    en  A proper long talk? %1$s, you've made my whole day. Where shall we start?
    >>  ............................................
    pt  Uma conversa longa de verdade? %1$s, você fez o meu dia inteiro. Por onde a gente começa?
    >>  ............................................
  extroverted.dialogue.conversations/2
    en  Yes! Nothing better. I could do this until the sun goes down and then some.
    >>  ............................................
    pt  Sim! Não tem nada melhor. Eu ficaria nisso até o sol se pôr e mais um pouco.
    >>  ............................................
  extroverted.dialogue.conversations/3
    en  Finally, someone who wants to actually talk. Go on, ask me anything.
    >>  ............................................
    pt  Enfim, alguém que quer conversar de verdade. Vai, pergunta qualquer coisa.
    >>  ............................................
  flirty.dialogue.conversations/1
    en  A private chat? With you? I've had worse afternoons, %1$s.
    >>  ............................................
    pt  Um papo particular? Com você? Já tive tardes piores, %1$s.
    >>  ............................................
  flirty.dialogue.conversations/2
    en  Ooh, I've been HOPING you'd wander over. Make my afternoon interesting.
    >>  ............................................
    pt  Ooh, eu estava TORCENDO pra você dar as caras. Torne a minha tarde interessante.
    >>  ............................................
  flirty.dialogue.conversations/3
    en  Talk away, darling. I do love the sound of your voice.
    >>  ............................................
    pt  Pode falar, querido. Eu adoro o som da sua voz.
    >>  ............................................
  friendly.dialogue.conversations/1
    en  I was hoping you'd stop by! Pull up a chair, %1$s — there's bread if you want it. What's on your mind?
    >>  ............................................
    pt  Eu estava torcendo pra você aparecer! Puxa uma cadeira, %1$s — tem pão se você quiser. O que você tem em mente?
    >>  ............................................
  friendly.dialogue.conversations/2
    en  Of course we can talk — I could go all day! Where shall we start?
    >>  ............................................
    pt  Claro que a gente pode conversar — eu ficaria o dia todo! Por onde começamos?
    >>  ............................................
  friendly.dialogue.conversations/3
    en  You never have to ask twice with me. Sit, sit!
    >>  ............................................
    pt  Comigo você nunca precisa pedir duas vezes. Senta, senta!
    >>  ............................................
  gloomy.dialogue.conversations/1
    en  Talk. Sure. It passes the time until it doesn't.
    >>  ............................................
    pt  Conversar. Claro. Passa o tempo, até parar de passar.
    >>  ............................................
  gloomy.dialogue.conversations/2
    en  You want to talk. Alright. Words fill the quiet, if nothing else.
    >>  ............................................
    pt  Você quer conversar. Tudo bem. Palavras preenchem o silêncio, se nada mais.
    >>  ............................................
  gloomy.dialogue.conversations/3
    en  Sit, then. Talk. It's cheaper than brooding and about as productive.
    >>  ............................................
    pt  Senta, então. Fala. É mais barato que remoer e quase tão produtivo.
    >>  ............................................
  greedy.dialogue.conversations/1
    en  Talk? Fine — first consultation's free, %1$s. After that we discuss rates.
    >>  ............................................
    pt  Conversar? Tudo bem — a primeira consulta é gratuita, %1$s. Depois a gente discute valores.
    >>  ............................................
  greedy.dialogue.conversations/2
    en  Talk's cheap, they say — and I do love a bargain. Go on, %1$s.
    >>  ............................................
    pt  Falar é barato, dizem — e eu adoro uma pechincha. Pode falar, %1$s.
    >>  ............................................
  greedy.dialogue.conversations/3
    en  You've my ear. The rest of me is still working, mind. Time's money.
    >>  ............................................
    pt  Tem o meu ouvido. O resto de mim continua trabalhando, veja bem. Tempo é dinheiro.
    >>  ............................................
  grumpy.dialogue.conversations/1
    en  A long talk? With me? Your judgement's questionable, %1$s. Sit down anyway.
    >>  ............................................
    pt  Uma conversa longa? Comigo? Seu julgamento é questionável, %1$s. Senta mesmo assim.
    >>  ............................................
  grumpy.dialogue.conversations/2
    en  If you must. Don't expect me to enjoy it. ...I probably will. Don't mention it.
    >>  ............................................
    pt  Se for preciso. Não espera que eu goste. ...Provavelmente vou gostar. Não comenta.
    >>  ............................................
  grumpy.dialogue.conversations/3
    en  Talk, then. I've been told I'm poor company. I've never disputed it.
    >>  ............................................
    pt  Fala, então. Já me disseram que sou má companhia. Nunca contestei.
    >>  ............................................
  introverted.dialogue.conversations/1
    en  A proper talk suits me better than a crowd, %1$s. Ask, and I'll answer honestly.
    >>  ............................................
    pt  Uma conversa de verdade me serve melhor que uma multidão, %1$s. Pergunte, e eu respondo com sinceridade.
    >>  ............................................
  introverted.dialogue.conversations/2
    en  I'd like that. One person at a time is how I prefer the world.
    >>  ............................................
    pt  Eu ia gostar disso. Uma pessoa por vez é como eu prefiro o mundo.
    >>  ............................................
  introverted.dialogue.conversations/3
    en  Quietly, then. I've more to say than I usually let on.
    >>  ............................................
    pt  Em silêncio, então. Tenho mais a dizer do que costumo deixar transparecer.
    >>  ............................................
  lazy.dialogue.conversations/1
    en  A long talk? Even better. Those are best taken slowly, %1$s.
    >>  ............................................
    pt  Uma conversa longa? Melhor ainda. Essas são melhores devagar, %1$s.
    >>  ............................................
  lazy.dialogue.conversations/2
    en  Sure. Pull up something to sit on. No sense doing this standing.
    >>  ............................................
    pt  Claro. Puxa alguma coisa pra sentar. Não faz sentido fazer isso em pé.
    >>  ............................................
  lazy.dialogue.conversations/3
    en  Happily. I do my best thinking at this speed anyway.
    >>  ............................................
    pt  Com prazer. Eu penso melhor nessa velocidade mesmo.
    >>  ............................................
  odd.dialogue.conversations/1
    en  You want to talk? The fence and I just finished. Your timing is excellent. Ask anything — I've already told the bucket.
    >>  ............................................
    pt  Quer conversar? A cerca e eu acabamos agora. Seu tempo é excelente. Pergunte qualquer coisa — já contei tudo ao balde.
    >>  ............................................
  odd.dialogue.conversations/2
    en  Ask me things. I answer better out loud — in my head the answers wander off, %1$s.
    >>  ............................................
    pt  Me pergunte coisas. Eu respondo melhor em voz alta — dentro da cabeça as respostas se perdem por aí, %1$s.
    >>  ............................................
  odd.dialogue.conversations/3
    en  The spoons rearranged themselves into a listening shape this morning. They knew. Ask away.
    >>  ............................................
    pt  As colheres se rearranjaram num formato de escuta hoje de manhã. Elas sabiam. Pode perguntar.
    >>  ............................................
  peaceful.dialogue.conversations/1
    en  A real conversation. I'd like that very much, %1$s. Let's take it gently.
    >>  ............................................
    pt  Uma conversa de verdade. Eu ia gostar muito disso, %1$s. Vamos com calma.
    >>  ............................................
  peaceful.dialogue.conversations/2
    en  Of course. There's time enough for anything worth saying.
    >>  ............................................
    pt  Claro. Há tempo de sobra pra qualquer coisa que valha a pena dizer.
    >>  ............................................
  peaceful.dialogue.conversations/3
    en  Gladly. I find these talks settle me as much as they settle anyone.
    >>  ............................................
    pt  Com prazer. Acho que essas conversas me assentam tanto quanto assentam qualquer um.
    >>  ............................................
  peppy.dialogue.conversations/1
    en  A real conversation?! %1$s, this just cracked the top three of my whole day! Okay okay — ask me ANYTHING!
    >>  ............................................
    pt  Uma conversa de verdade?! %1$s, isso acabou de entrar no top três do meu dia inteiro! Tá, tá — pergunta QUALQUER COISA!
    >>  ............................................
  peppy.dialogue.conversations/2
    en  A LONG one?! %1$s, I am going to be unbearable about this. Sit down!
    >>  ............................................
    pt  Uma conversa LONGA?! %1$s, eu vou ficar insuportável com isso. Senta!
    >>  ............................................
  peppy.dialogue.conversations/3
    en  Ooh a chat! Best decision you've made all day and it's not even close! Go!
    >>  ............................................
    pt  Ooh, um papo! Melhor decisão que você tomou hoje e nem é perto! Vai!
    >>  ............................................
  playful.dialogue.conversations/1
    en  A serious talk? From you? Ha! All right, %1$s, I'll behave. Briefly.
    >>  ............................................
    pt  Uma conversa séria? Vinda de você? Ha! Tudo bem, %1$s, vou me comportar. Brevemente.
    >>  ............................................
  playful.dialogue.conversations/2
    en  Ooh, a proper conversation. Let me guess — you want my secrets. Try your luck.
    >>  ............................................
    pt  Ooh, uma conversa de verdade. Deixa eu adivinhar — você quer os meus segredos. Tenta a sorte.
    >>  ............................................
  playful.dialogue.conversations/3
    en  Fine, fine, I'll sit still. But I make no promises past the first question.
    >>  ............................................
    pt  Tá, tá, eu fico quieto. Mas não prometo nada depois da primeira pergunta.
    >>  ............................................
  relaxed.dialogue.conversations/1
    en  A long talk? Even better. Those are best taken slowly, %1$s.
    >>  ............................................
    pt  Uma conversa longa? Melhor ainda. Essas são melhores devagar, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations/2
    en  Sure. Pull up something to sit on. No sense doing this standing.
    >>  ............................................
    pt  Claro. Puxa alguma coisa pra sentar. Não faz sentido fazer isso em pé.
    >>  ............................................
  relaxed.dialogue.conversations/3
    en  Happily. I do my best thinking at this speed anyway.
    >>  ............................................
    pt  Com prazer. Eu penso melhor nessa velocidade mesmo.
    >>  ............................................
  sensitive.dialogue.conversations/1
    en  Of course we can talk. You look like you're carrying something too — we'll trade. What's on your mind?
    >>  ............................................
    pt  Claro que a gente pode conversar. Você também parece estar carregando algo — vamos trocar. O que tem na sua cabeça?
    >>  ............................................
  sensitive.dialogue.conversations/2
    en  Yes, let's talk. Come sit — you carry things quietly, I've noticed. So do I.
    >>  ............................................
    pt  Sim, vamos conversar. Vem sentar — você carrega as coisas em silêncio, eu reparei. Eu também.
    >>  ............................................
  sensitive.dialogue.conversations/3
    en  Always. Whatever it is, I'll hold it gently, %1$s. Go on.
    >>  ............................................
    pt  Sempre. Seja lá o que for, eu vou segurar com cuidado, %1$s. Pode falar.
    >>  ............................................
  shy.dialogue.conversations/1
    en  A proper talk suits me better than a crowd, %1$s. Ask, and I'll answer honestly.
    >>  ............................................
    pt  Uma conversa de verdade me serve melhor que uma multidão, %1$s. Pergunte, e eu respondo com sinceridade.
    >>  ............................................
  shy.dialogue.conversations/2
    en  I'd like that. One person at a time is how I prefer the world.
    >>  ............................................
    pt  Eu ia gostar disso. Uma pessoa por vez é como eu prefiro o mundo.
    >>  ............................................
  shy.dialogue.conversations/3
    en  Quietly, then. I've more to say than I usually let on.
    >>  ............................................
    pt  Em silêncio, então. Tenho mais a dizer do que costumo deixar transparecer.
    >>  ............................................
  upbeat.dialogue.conversations/1
    en  A proper talk? Gladly. Pull up a spot, %1$s, I've got time and good humour.
    >>  ............................................
    pt  Uma conversa de verdade? Com prazer. Arruma um canto aí, %1$s, tenho tempo e bom humor.
    >>  ............................................
  upbeat.dialogue.conversations/2
    en  Oh, I'd like that. There's not much that beats a good conversation, %1$s.
    >>  ............................................
    pt  Ah, eu ia gostar disso. Não tem muita coisa melhor que uma boa conversa, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations/3
    en  Let's talk properly, then. Days go better when somebody asks.
    >>  ............................................
    pt  Vamos conversar direito, então. Os dias ficam melhores quando alguém pergunta.
    >>  ............................................
  witty.dialogue.conversations/1
    en  A proper talk? Gladly. Pull up a spot, %1$s, I've got time and good humour.
    >>  ............................................
    pt  Uma conversa de verdade? Com prazer. Arruma um canto aí, %1$s, tenho tempo e bom humor.
    >>  ............................................
  witty.dialogue.conversations/2
    en  Oh, I'd like that. There's not much that beats a good conversation, %1$s.
    >>  ............................................
    pt  Ah, eu ia gostar disso. Não tem muita coisa melhor que uma boa conversa, %1$s.
    >>  ............................................
  witty.dialogue.conversations/3
    en  Let's talk properly, then. Days go better when somebody asks.
    >>  ............................................
    pt  Vamos conversar direito, então. Os dias ficam melhores quando alguém pergunta.
    >>  ............................................
```

</details>


### Button `chitchat` — "Just making conversation."

Shown only when MCA's own constraints hold: `"!baby"`

```text
POOL   dialogue key: dialogue.conversations.chitchat
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.chitchat   [25 chars]
    en  Just making conversation.
    >>  ............................................
    pt  Só puxando conversa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `profession` — "About your work..."

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

```text
POOL   dialogue key: dialogue.conversations.profession
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.profession   [18 chars]
    en  About your work...
    >>  ............................................
    pt  Sobre o seu trabalho...
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `village` — "About the village..."

Shown only when MCA's own constraints hold: `"!baby"`

```text
POOL   dialogue key: dialogue.conversations.village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.village   [20 chars]
    en  About the village...
    >>  ............................................
    pt  Sobre o vilarejo...
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `events` — "Heard any news?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

```text
POOL   dialogue key: dialogue.conversations.events
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.events   [15 chars]
    en  Heard any news?
    >>  ............................................
    pt  Ouviu alguma novidade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `personal` — "Something more personal."

Shown only when MCA's own constraints hold: `"!baby"`

```text
POOL   dialogue key: dialogue.conversations.personal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.personal   [24 chars]
    en  Something more personal.
    >>  ............................................
    pt  Algo mais pessoal.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `relationships` — "About us. About family."

Shown only when MCA's own constraints hold: `"family,!baby"`

```text
POOL   dialogue key: dialogue.conversations.relationships
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.relationships   [23 chars]
    en  About us. About family.
    >>  ............................................
    pt  Sobre nós. Sobre família.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.relationships`
- …where the player's next choices will be: "Can we talk about us?" | "How's the family?" | "Something else."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


### Button `babble` — "Goo? Ga-goo!"

Shown only when MCA's own constraints hold: `"baby"`

```text
POOL   dialogue key: dialogue.conversations.babble
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.babble   [12 chars]
    en  Goo? Ga-goo!
    >>  ............................................
    pt  Gu? Ga-gu!
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"

```text
POOL   dialogue key: dialogue.conversations.babble.baby
WHO    VILLAGER — what the player reads after pressing "Goo? Ga-goo!"
       spoken on: conversations, button `babble`
       leaves the player on: main
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `babble.baby.terminal`: the villager accepts. Subject `babble.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.babble.baby/1   [13 chars]
    en  Baba! Ah-goo.
    >>  ............................................
    pt  Bába! Ah-gu.
    >>  ............................................
  dialogue.conversations.babble.baby/2   [34 chars]
    en  *grabs at your finger and giggles*
    >>  ............................................
    pt  *agarra o seu dedo e dá risada*
    >>  ............................................
  dialogue.conversations.babble.baby/3   [10 chars]
    en  Pbbbt. Ba.
    >>  ............................................
    pt  Pbbbt. Ba.
    >>  ............................................
```


### Button `back` — "Never mind."

```text
POOL   dialogue key: dialogue.conversations.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.back   [11 chars]
    en  Never mind.
    >>  ............................................
    pt  Deixa pra lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `main`
- …where the player's next choices will be: "Let's talk properly"
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.

---


## `conversations.cat.chitchat`

**Reached from 144 route(s):** `conversations.cat.chitchat` / `day`; `conversations.cat.chitchat` / `day`; `conversations.cat.chitchat` / `food`; `conversations.cat.chitchat` / `weather`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `routine`; `conversations` / `chitchat`; `conversations.scene.day.before_the_light.respond` / `leave`; `conversations.scene.day.end_of_it.respond` / `leave`; `conversations.scene.day.followup` / `leave`; `conversations.scene.food.followup` / `leave`; `conversations.scene.food.the_fourteenth_time.respond` / `leave` …and 132 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.checkin.late.keep_watch` — e.g. "...You'd sit up. With me. For no reason at all. Well. Pull the other stool over, then."
- `conversations.checkin.late.leave` — e.g. "I'll try. Trying is most of what I do at this hour."
- `conversations.checkin.late.send_to_bed` — e.g. "...You're right. It will keep. It always keeps and I never believe it until somebody says so."
- `conversations.checkin.toddler.ask` — e.g. "The best bit was the bit at the start. Before everything else."
- `conversations.checkin.toddler.delight` — e.g. "It IS a good report. I did a good one."
- `conversations.checkin.toddler.leave` — e.g. "Bye! I've got things."
- `conversations.day.again.apologize` — e.g. "Happens. My days blur together too, if I'm honest."
- `conversations.day.again.leave` — e.g. "Aye. Catch me tomorrow."
- `conversations.day.again.press` — e.g. "It's the same day, %1$s. It's been the same day for an hour."
- `conversations.day.good.deflated.apologize` — e.g. "...I will, thank you. It was a small one and I'd like it back."
- `conversations.day.good.deflated.explain` — e.g. "...Ah. Then say that first next time and I'll ask about yours."
- `conversations.day.good.deflated.leave` — e.g. "Just so. Off you go."
- `conversations.day.good.leave` — e.g. "Aye, off you go. Enjoy yours if you get one."
- `conversations.day.good.share_own` — e.g. "Two of us having a good day. The village won't know what to do with itself."
- …and 102 more pools


```text
POOL   dialogue key: dialogue.conversations.cat.chitchat
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.chitchat
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.chitchat   [25 chars]
    en  Happy to chat. What's up?
    >>  ............................................
    pt  Adoro um bate-papo. E aí?
    >>  ............................................
```


### Button `day` — "How's your day actually going?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `chitchat.day` — accepted phrasings: "how * day"; "how are you doing"; "how is it going"; "how is your day"; "what are you doing"; "what are you up to"; "what is up"
  - the message must contain one of: `day`, `today`, `doing`, `going`, `up`
  - scored words: `day`(1.5), `today`(1.2), `going`(1.0), `doing`(1.0), `morning`(0.8), `afternoon`(0.8), `evening`(0.8), `up`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.day   [30 chars]
    en  How's your day actually going?
    >>  ............................................
    pt  Como está sendo o seu dia, na real?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.day.before_the_light"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.day.before_the_light", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 36000 ticks
- Then opens: `conversations.scene.day.before_the_light.respond`
- …where the player's next choices will be: "What do you do with the first hour?" | "Make it a good one." | "Right you are."

```text
POOL   dialogue key: dialogue.conversations.scene.day.before_the_light
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.scene.day.before_the_light.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.before_the_light.open`: the villager reports. Subject `day.early`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.before_the_light/1   [93 chars]
    en  Started before it was properly light, which I say every morning and mean about half the time.
    >>  ............................................
    pt  Comecei antes de clarear direito, coisa que eu digo toda manhã e sinto de verdade umas metade das vezes.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light/2   [93 chars]
    en  The first hour is the only one that belongs to me. After that it belongs to whoever turns up.
    >>  ............................................
    pt  A primeira hora é a única que é minha. Depois disso é de quem aparecer.
    >>  ............................................
  dialogue.conversations.scene.day.before_the_light/3   [78 chars]
    en  Well begun. Ask me again at four and you will get a different answer entirely.
    >>  ............................................
    pt  Bem começado. Me pergunte de novo às quatro e você vai ter uma resposta completamente diferente.
    >>  ............................................
```


**Outcome 2 of 12** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.day.end_of_it"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.day.end_of_it", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 36000 ticks
- Then opens: `conversations.scene.day.end_of_it.respond`
- …where the player's next choices will be: "What was the best part of it?" | "Get some rest." | "Right you are."

```text
POOL   dialogue key: dialogue.conversations.scene.day.end_of_it
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.scene.day.end_of_it.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.end_of_it.open`: the villager reports. Subject `day.late`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:day` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.day.end_of_it/1   [103 chars]
    en  Done, and I could not tell you what happened in the middle of it, which is usually a sign it went well.
    >>  ............................................
    pt  Acabou, e eu não saberia dizer o que aconteceu no meio, o que costuma ser sinal de que foi bom.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it/2   [83 chars]
    en  Long. Not bad. There is a difference and it took me until about thirty to learn it.
    >>  ............................................
    pt  Longo. Não ruim. Existe diferença e eu levei até uns trinta anos para aprender.
    >>  ............................................
  dialogue.conversations.scene.day.end_of_it/3   [87 chars]
    en  I got one thing finished and four started, and at this hour I have decided that counts.
    >>  ............................................
    pt  Terminei uma coisa e comecei quatro, e a esta hora eu decidi que isso conta.
    >>  ............................................
```


**Outcome 3 of 12** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.day` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `again` budget `quick`
- Then opens: `conversations.topic.day.again.respond`
- …where the player's next choices will be: "Sorry — I forgot I'd asked." | "Humour me. How's your day?" | "Fair enough. Never mind."

```text
POOL   dialogue key: dialogue.conversations.day.again
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.again.to.day.again`: the villager accepts. Subject `day.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.again/1   [34 chars]
    en  Same day it was an hour ago, %1$s.
    >>  ............................................
    pt  O mesmo dia de uma hora atrás, %1$s.
    >>  ............................................
  dialogue.conversations.day.again/2   [53 chars]
    en  Still going. Days don't change that fast around here.
    >>  ............................................
    pt  Ainda rolando. Os dias não mudam tão rápido por aqui.
    >>  ............................................
  dialogue.conversations.day.again/3   [52 chars]
    en  The day refuses to get more interesting, I'm afraid.
    >>  ............................................
    pt  O dia se recusa a ficar mais interessante, infelizmente.
    >>  ............................................
```


**Outcome 4 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `toddler` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.toddler.respond`
- …where the player's next choices will be: "That is the best news I've heard all day." | "Tell me more about that." | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.day.toddler
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.toddler.to.day.toddler`: the villager accepts. Subject `day.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.toddler/1   [35 chars]
    en  I stacked mud! It was the best mud.
    >>  ............................................
    pt  Eu empilhei lama! Era a melhor lama.
    >>  ............................................
  dialogue.conversations.day.toddler/2   [35 chars]
    en  Today is SO long. Is it snack time?
    >>  ............................................
    pt  Hoje tá TÃO longo. Já é hora do lanche?
    >>  ............................................
  dialogue.conversations.day.toddler/3   [43 chars]
    en  I chased a butterfly and then it chased ME.
    >>  ............................................
    pt  Corri atrás de uma borboleta e aí ela correu atrás de MIM.
    >>  ............................................
```


**Outcome 5 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `child` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.young.respond`
- …where the player's next choices will be: "Go on, tell me properly." | "That sounds like a good day's work." | "That's not really news." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.day.child
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.child.to.day.young`: the villager accepts. Subject `day.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.child/1   [63 chars]
    en  BEST day. I found a rock shaped like a slightly different rock!
    >>  ............................................
    pt  MELHOR dia. Achei uma pedra com formato de uma pedra levemente diferente!
    >>  ............................................
  dialogue.conversations.day.child/2   [71 chars]
    en  I raced the chickens and only lost twice. They cheat with the flapping.
    >>  ............................................
    pt  Apostei corrida com as galinhas e só perdi duas vezes. Elas trapaceiam com as asas.
    >>  ............................................
```


**Outcome 6 of 12** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `teen` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.young.respond`
- …where the player's next choices will be: "Go on, tell me properly." | "That sounds like a good day's work." | "That's not really news." | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.day.teen
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.teen.to.day.young`: the villager accepts. Subject `day.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.teen/1   [68 chars]
    en  It's fine. It's a day. They keep happening whether I approve or not.
    >>  ............................................
    pt  Tá tudo bem. É um dia. Eles continuam acontecendo, com ou sem a minha aprovação.
    >>  ............................................
  dialogue.conversations.day.teen/2   [72 chars]
    en  Chores, chores, being told I'm 'so grown up now', more chores. Riveting.
    >>  ............................................
    pt  Tarefas, tarefas, me dizerem que eu estou "tão crescido agora", mais tarefas. Fascinante.
    >>  ............................................
```


**Outcome 7 of 12** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `rough` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.rough.respond`
- …where the player's next choices will be: "That sounds like a lot to carry." | "What happened?" | "Everyone has days like that." | "I'll let you get on."

```text
POOL   dialogue key: dialogue.conversations.day.rough
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.rough.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.to.day.rough`: the villager accepts. Subject `day.rough`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.rough/1   [63 chars]
    en  Long. My back aches and the cat knocked the stew off the table.
    >>  ............................................
    pt  Longo. Minhas costas doem e o gato derrubou o ensopado da mesa.
    >>  ............................................
  dialogue.conversations.day.rough/2   [77 chars]
    en  One of those days where the door sticks and everything else follows its lead.
    >>  ............................................
    pt  Um daqueles dias em que a porta emperra e o resto segue o exemplo.
    >>  ............................................
  dialogue.conversations.day.rough/3   [65 chars]
    en  I dropped an egg, then a bucket, then my patience. In that order.
    >>  ............................................
    pt  Derrubei um ovo, depois um balde, depois a minha paciência. Nessa ordem.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.rough/1
    en  I dropped a whole basket and I've been apologising to everyone since. Nobody minded. I still mind.
    >>  ............................................
    pt  Derrubei uma cesta inteira e estou pedindo desculpas pra todo mundo desde então. Ninguém se importou. Eu ainda me importo.
    >>  ............................................
  anxious.dialogue.conversations.day.rough/2
    en  One thing went wrong this morning and I've been braced for the next one all day, %1$s.
    >>  ............................................
    pt  Uma coisa deu errado de manhã e eu passei o dia todo esperando a próxima, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.day.rough/1
    en  Slow day. Tweaked my shoulder hauling water and now everything's uphill. Even the flat parts.
    >>  ............................................
    pt  Dia lento. Torci o ombro carregando água e agora tudo é subida. Até as partes planas.
    >>  ............................................
  athletic.dialogue.conversations.day.rough/2
    en  Rest day, cleric's orders. I'm pacing the yard like a penned horse.
    >>  ............................................
    pt  Dia de descanso, ordem do clérigo. Estou andando de um lado pro outro no quintal que nem cavalo em cercado.
    >>  ............................................
  confident.dialogue.conversations.day.rough/1
    en  The day is misbehaving. It will come around by supper. They always do when I'm involved.
    >>  ............................................
    pt  O dia está se comportando mal. Vai se ajeitar até a janta. Sempre se ajeitam quando eu estou envolvido.
    >>  ............................................
  confident.dialogue.conversations.day.rough/2
    en  Rough edges. Nothing I can't file smooth by dusk. Watch me.
    >>  ............................................
    pt  Umas arestas. Nada que eu não lixe até o anoitecer. Vai por mim.
    >>  ............................................
  crabby.dialogue.conversations.day.rough/1
    en  The bucket broke. Then the handle. Then my temper. In that order, and all before noon.
    >>  ............................................
    pt  O balde quebrou. Depois a alça. Depois a minha paciência. Nessa ordem, e tudo antes do meio-dia.
    >>  ............................................
  crabby.dialogue.conversations.day.rough/2
    en  Everything's conspired against me since dawn. I'd list it, but I'd only get angrier.
    >>  ............................................
    pt  Tudo conspirou contra mim desde o amanhecer. Eu listaria, mas só ficaria mais bravo.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough/1
    en  Nobody about all day. Not one soul at the well. I've been talking to myself since morning, %1$s.
    >>  ............................................
    pt  Ninguém apareceu o dia todo. Nem uma alma no poço. Estou falando sozinho desde de manhã, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.day.rough/2
    en  Empty sort of day. The work was fine — it's the silence that got to me.
    >>  ............................................
    pt  Um dia vazio. O serviço estava bem — foi o silêncio que me pegou.
    >>  ............................................
  flirty.dialogue.conversations.day.rough/1
    en  Terrible, thanks for asking. Stay a while and fix the average.
    >>  ............................................
    pt  Terrível, obrigada por perguntar. Fica um pouco e melhora a média.
    >>  ............................................
  flirty.dialogue.conversations.day.rough/2
    en  Dreadful, till now. You've a talent for turning up exactly when I need distracting.
    >>  ............................................
    pt  Horrível, até agora. Você tem um talento pra aparecer exatamente quando eu preciso de distração.
    >>  ............................................
  friendly.dialogue.conversations.day.rough/1
    en  A stumbly one — burnt the porridge, tripped over the cat. Nothing warm supper and good company won't mend!
    >>  ............................................
    pt  Um dia tropeçado — queimei o mingau, tropecei no gato. Nada que janta quente e boa companhia não consertem!
    >>  ............................................
  friendly.dialogue.conversations.day.rough/2
    en  Not my easiest, but a friendly face mends most things. Perfect timing, %1$s.
    >>  ............................................
    pt  Não foi o meu mais fácil, mas uma cara amiga conserta quase tudo. Momento perfeito, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough/1
    en  The usual. Grey sky outside, greyer one in here.
    >>  ............................................
    pt  O de sempre. Céu cinza lá fora, mais cinza aqui dentro.
    >>  ............................................
  gloomy.dialogue.conversations.day.rough/2
    en  I got to the end of it. That's all a day like this owes anyone.
    >>  ............................................
    pt  Cheguei ao fim dele. É tudo que um dia desses deve a alguém.
    >>  ............................................
  greedy.dialogue.conversations.day.rough/1
    en  A net loss of a day. Dropped an egg — that's one whole egg — and lent the miller my ladder. I'll see neither again.
    >>  ............................................
    pt  Um dia de prejuízo líquido. Derrubei um ovo — um ovo inteiro — e emprestei minha escada ao moleiro. Não verei nenhum dos dois de novo.
    >>  ............................................
  greedy.dialogue.conversations.day.rough/2
    en  I spent an hour arguing over three coppers and won. It cost me an hour. I'm aware of the arithmetic.
    >>  ............................................
    pt  Passei uma hora discutindo por três moedas de cobre e ganhei. Me custou uma hora. Estou ciente da aritmética.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough/1
    en  The bucket broke. Then the handle. Then my temper. In that order, and all before noon.
    >>  ............................................
    pt  O balde quebrou. Depois a alça. Depois a minha paciência. Nessa ordem, e tudo antes do meio-dia.
    >>  ............................................
  grumpy.dialogue.conversations.day.rough/2
    en  Everything's conspired against me since dawn. I'd list it, but I'd only get angrier.
    >>  ............................................
    pt  Tudo conspirou contra mim desde o amanhecer. Eu listaria, mas só ficaria mais bravo.
    >>  ............................................
  introverted.dialogue.conversations.day.rough/1
    en  The square was full and stayed full. I've been rebuilding myself behind the fence ever since.
    >>  ............................................
    pt  A praça estava cheia e continuou cheia. Estou me reconstruindo atrás da cerca desde então.
    >>  ............................................
  introverted.dialogue.conversations.day.rough/2
    en  Too much noise, too many people wanting words. I'm worn thin, %1$s. It'll pass by morning.
    >>  ............................................
    pt  Barulho demais, gente demais querendo palavras. Estou gasto, %1$s. Passa até de manhã.
    >>  ............................................
  lazy.dialogue.conversations.day.rough/1
    en  Everything wanted doing at once today. I did it one at a time anyway. Took longer, but nothing got broken.
    >>  ............................................
    pt  Tudo quis ser feito de uma vez hoje. Eu fiz uma coisa por vez mesmo assim. Demorou mais, mas nada quebrou.
    >>  ............................................
  lazy.dialogue.conversations.day.rough/2
    en  Busy sort of day. I don't much care for busy — it makes people careless.
    >>  ............................................
    pt  Dia meio corrido. Não gosto muito de corrido — deixa as pessoas descuidadas.
    >>  ............................................
  odd.dialogue.conversations.day.rough/1
    en  The bread came out shaped like the mayor. I ate it anyway, but we were both uncomfortable about it.
    >>  ............................................
    pt  O pão saiu com o formato do prefeito. Comi mesmo assim, mas nós dois ficamos desconfortáveis com aquilo.
    >>  ............................................
  odd.dialogue.conversations.day.rough/2
    en  The porridge whispered something rude and I've been off-balance since. Some days start on the wrong omen, %1$s.
    >>  ............................................
    pt  O mingau sussurrou uma grosseria e eu estou desequilibrado desde então. Tem dia que começa no presságio errado, %1$s.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough/1
    en  A difficult one. I've been out by the water since — it puts things back in proportion.
    >>  ............................................
    pt  Um dia difícil. Estive lá perto da água desde então — isso põe as coisas de volta na proporção.
    >>  ............................................
  peaceful.dialogue.conversations.day.rough/2
    en  Not an easy day. But the evening's coming, and evenings are forgiving things.
    >>  ............................................
    pt  Não foi um dia fácil. Mas a noitinha está vindo, e noitinhas são coisas indulgentes.
    >>  ............................................
  peppy.dialogue.conversations.day.rough/1
    en  Honestly? Not great! But a bad morning is just a good afternoon warming up. Probably!
    >>  ............................................
    pt  Sinceramente? Nada bom! Mas manhã ruim é só tarde boa se aquecendo. Provavelmente!
    >>  ............................................
  peppy.dialogue.conversations.day.rough/2
    en  Wobbly! I dropped things! But I dropped them ENTHUSIASTICALLY, which counts for something!
    >>  ............................................
    pt  Cambaleante! Eu derrubei coisas! Mas derrubei com ENTUSIASMO, o que já conta!
    >>  ............................................
  playful.dialogue.conversations.day.rough/1
    en  Dreadful. My best trick backfired and now I'm the one covered in flour. Fair, I suppose.
    >>  ............................................
    pt  Horrível. Minha melhor pegadinha saiu pela culatra e agora sou eu o coberto de farinha. Justo, eu suponho.
    >>  ............................................
  playful.dialogue.conversations.day.rough/2
    en  Everything went sideways. Even the fun bits. I'll get my own back tomorrow.
    >>  ............................................
    pt  Tudo saiu de lado. Até as partes divertidas. Eu me vingo amanhã.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough/1
    en  Everything wanted doing at once today. I did it one at a time anyway. Took longer, but nothing got broken.
    >>  ............................................
    pt  Tudo quis ser feito de uma vez hoje. Eu fiz uma coisa por vez mesmo assim. Demorou mais, mas nada quebrou.
    >>  ............................................
  relaxed.dialogue.conversations.day.rough/2
    en  Busy sort of day. I don't much care for busy — it makes people careless.
    >>  ............................................
    pt  Dia meio corrido. Não gosto muito de corrido — deixa as pessoas descuidadas.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough/1
    en  Hard. I heard the neighbors arguing and carried it all day like it was mine. I do that. Talking helps, so thank you.
    >>  ............................................
    pt  Difícil. Ouvi os vizinhos discutindo e carreguei isso o dia todo como se fosse meu. Eu faço isso. Conversar ajuda, então obrigado.
    >>  ............................................
  sensitive.dialogue.conversations.day.rough/2
    en  I said the wrong thing at the well this morning and I've been rehearsing better versions of it ever since.
    >>  ............................................
    pt  Eu disse a coisa errada no poço hoje de manhã e desde então venho ensaiando versões melhores.
    >>  ............................................
  shy.dialogue.conversations.day.rough/1
    en  The square was full and stayed full. I've been rebuilding myself behind the fence ever since.
    >>  ............................................
    pt  A praça estava cheia e continuou cheia. Estou me reconstruindo atrás da cerca desde então.
    >>  ............................................
  shy.dialogue.conversations.day.rough/2
    en  Too much noise, too many people wanting words. I'm worn thin, %1$s. It'll pass by morning.
    >>  ............................................
    pt  Barulho demais, gente demais querendo palavras. Estou gasto, %1$s. Passa até de manhã.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough/1
    en  Bucket broke, fence sagged, and I lost an hour to both. Still — the evening's coming, and evenings are forgiving.
    >>  ............................................
    pt  O balde quebrou, a cerca cedeu, e eu perdi uma hora com os dois. Mas a noitinha está vindo, e noitinhas são indulgentes.
    >>  ............................................
  upbeat.dialogue.conversations.day.rough/2
    en  Everything's been slightly against me today. But tomorrow's fresh, and I quite like tomorrows.
    >>  ............................................
    pt  Tudo levemente contra mim hoje. Mas amanhã é novo, e eu gosto bastante de amanhãs.
    >>  ............................................
  witty.dialogue.conversations.day.rough/1
    en  Bucket broke, fence sagged, and I lost an hour to both. Still — the evening's coming, and evenings are forgiving.
    >>  ............................................
    pt  O balde quebrou, a cerca cedeu, e eu perdi uma hora com os dois. Mas a noitinha está vindo, e noitinhas são indulgentes.
    >>  ............................................
  witty.dialogue.conversations.day.rough/2
    en  Everything's been slightly against me today. But tomorrow's fresh, and I quite like tomorrows.
    >>  ............................................
    pt  Tudo levemente contra mim hoje. Mas amanhã é novo, e eu gosto bastante de amanhãs.
    >>  ............................................
```

</details>


**Outcome 8 of 12** — base weight `0`

- Fires when: weighted +100 when the mood is `happy`
- Fires when: weighted +100 when the mood is `overjoyed`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `good` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.good.respond`
- …where the player's next choices will be: "You've earned a day like that." | "What made it a good one?" | "Must be nice. Some of us work." | "Glad to hear it. I'll get on."

```text
POOL   dialogue key: dialogue.conversations.day.good
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.good.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.good.to.day.good`: the villager accepts. Subject `day.good`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.good/1   [81 chars]
    en  Good one, actually. The well water was cold and the queue at the smith was short.
    >>  ............................................
    pt  Foi bom, na verdade. A água do poço estava gelada e a fila do ferreiro estava curta.
    >>  ............................................
  dialogue.conversations.day.good/2   [61 chars]
    en  It's the kind of day where the chickens behave. I'll take it.
    >>  ............................................
    pt  É daqueles dias em que até as galinhas se comportam. Tá ótimo pra mim.
    >>  ............................................
  dialogue.conversations.day.good/3   [51 chars]
    en  Sun's out, boots are dry. That's a win around here.
    >>  ............................................
    pt  Sol lá fora, botas secas. Por aqui isso já é vitória.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.day.good/1
    en  It's gone well so far. I'm trying not to say that too loudly in case it hears me.
    >>  ............................................
    pt  Foi bem até agora. Estou tentando não dizer isso muito alto, caso ele escute.
    >>  ............................................
  anxious.dialogue.conversations.day.good/2
    en  Nothing's gone wrong today. I've checked three times. It's honestly a relief.
    >>  ............................................
    pt  Nada deu errado hoje. Conferi três vezes. É um alívio de verdade.
    >>  ............................................
  athletic.dialogue.conversations.day.good/1
    en  Strong day. Up before the rooster, ten trips to the well, still bouncing. Count them — ten.
    >>  ............................................
    pt  Dia forte. De pé antes do galo, dez viagens ao poço, e ainda saltitando. Conta aí — dez.
    >>  ............................................
  athletic.dialogue.conversations.day.good/2
    en  Legs like springs today. Even the hills felt friendly, %1$s.
    >>  ............................................
    pt  Pernas que nem mola hoje. Até as ladeiras pareceram amigáveis, %1$s.
    >>  ............................................
  confident.dialogue.conversations.day.good/1
    en  Exactly as planned. Cold well water, short line at the smith, sun on time. The day knows better than to cross me.
    >>  ............................................
    pt  Exatamente como planejado. Água de poço gelada, fila curta no ferreiro, sol na hora. O dia sabe que não deve me contrariar.
    >>  ............................................
  confident.dialogue.conversations.day.good/2
    en  Three decisions before noon, all correct. A typical morning.
    >>  ............................................
    pt  Três decisões antes do meio-dia, todas corretas. Uma manhã típica.
    >>  ............................................
  crabby.dialogue.conversations.day.good/1
    en  Tolerable. The fence held, the chickens quietened down, and nobody stood in my light. That's a good day.
    >>  ............................................
    pt  Tolerável. A cerca aguentou, as galinhas sossegaram, e ninguém ficou na frente da minha luz. Isso é um bom dia.
    >>  ............................................
  crabby.dialogue.conversations.day.good/2
    en  Fine, actually. Don't look so surprised — it happens.
    >>  ............................................
    pt  Bem, na verdade. Não faz essa cara de surpresa — acontece.
    >>  ............................................
  extroverted.dialogue.conversations.day.good/1
    en  Marvellous day. Three conversations before noon and one of them turned into a proper argument. Wonderful stuff.
    >>  ............................................
    pt  Dia maravilhoso. Três conversas antes do meio-dia e uma delas virou uma discussão de verdade. Coisa esplêndida.
    >>  ............................................
  extroverted.dialogue.conversations.day.good/2
    en  Busy and loud and full of people. Exactly how I like it.
    >>  ............................................
    pt  Corrido, barulhento e cheio de gente. Exatamente como eu gosto.
    >>  ............................................
  flirty.dialogue.conversations.day.good/1
    en  It just got better. Coincidence? I don't think so.
    >>  ............................................
    pt  Acabou de melhorar. Coincidência? Acho que não.
    >>  ............................................
  flirty.dialogue.conversations.day.good/2
    en  Lovely day, lovelier company. And here you are, right on cue.
    >>  ............................................
    pt  Dia adorável, companhia mais adorável ainda. E aqui está você, bem na deixa.
    >>  ............................................
  friendly.dialogue.conversations.day.good/1
    en  Lovely day! Traded eggs for honey at the well, and everyone had a minute to chat. My favorite kind.
    >>  ............................................
    pt  Dia adorável! Troquei ovos por mel no poço, e todo mundo tinha um minutinho pra conversar. Meu tipo favorito.
    >>  ............................................
  friendly.dialogue.conversations.day.good/2
    en  Grand! Waved to half the village and every one waved back. That's a good day, that.
    >>  ............................................
    pt  Ótimo! Acenei pra meio vilarejo e todos acenaram de volta. Isso é que é um bom dia.
    >>  ............................................
  gloomy.dialogue.conversations.day.good/1
    en  Nothing went wrong yet. I'm keeping my expectations where they belong.
    >>  ............................................
    pt  Nada deu errado ainda. Mantenho minhas expectativas onde elas pertencem.
    >>  ............................................
  gloomy.dialogue.conversations.day.good/2
    en  Someone left bread on my step and didn't say who. I've been suspicious about it for hours.
    >>  ............................................
    pt  Alguém deixou pão na minha porta e não disse quem. Estou desconfiado disso há horas.
    >>  ............................................
  greedy.dialogue.conversations.day.good/1
    en  Profitable. Found a nail, sold nothing at a loss, and the well water's still free. They'll fix that oversight someday.
    >>  ............................................
    pt  Lucrativo. Achei um prego, não vendi nada no prejuízo, e a água do poço continua de graça. Um dia vão corrigir esse descuido.
    >>  ............................................
  greedy.dialogue.conversations.day.good/2
    en  Nobody haggled me down once today. Not one. I've been checking my prices in case I set them wrong.
    >>  ............................................
    pt  Ninguém pechinchou comigo hoje. Nenhuma vez. Ando conferindo meus preços, vai ver que eu errei.
    >>  ............................................
  grumpy.dialogue.conversations.day.good/1
    en  Tolerable. The fence held, the chickens quietened down, and nobody stood in my light. That's a good day.
    >>  ............................................
    pt  Tolerável. A cerca aguentou, as galinhas sossegaram, e ninguém ficou na frente da minha luz. Isso é um bom dia.
    >>  ............................................
  grumpy.dialogue.conversations.day.good/2
    en  Fine, actually. Don't look so surprised — it happens.
    >>  ............................................
    pt  Bem, na verdade. Não faz essa cara de surpresa — acontece.
    >>  ............................................
  introverted.dialogue.conversations.day.good/1
    en  A good day. I walked the long way round, where nobody goes, and came back with my thoughts in order.
    >>  ............................................
    pt  Um bom dia. Fiz o caminho mais longo, por onde ninguém vai, e voltei com os pensamentos em ordem.
    >>  ............................................
  introverted.dialogue.conversations.day.good/2
    en  Quiet and useful. The garden asked nothing of me but hands. Those are my favourite days.
    >>  ............................................
    pt  Quieto e útil. A horta não me pediu nada além de mãos. São os meus dias favoritos.
    >>  ............................................
  lazy.dialogue.conversations.day.good/1
    en  Good one. Got the morning's work done by midday and spent the rest of it in the sun, unrepentant.
    >>  ............................................
    pt  Foi bom. Terminei o serviço da manhã até o meio-dia e passei o resto no sol, sem remorso.
    >>  ............................................
  lazy.dialogue.conversations.day.good/2
    en  Fine day. Nothing demanded anything of me twice. I call that a success.
    >>  ............................................
    pt  Belo dia. Nada exigiu nada de mim duas vezes. Chamo isso de sucesso.
    >>  ............................................
  odd.dialogue.conversations.day.good/1
    en  A seventeen-cloud day. Those are the good ones. I told the well this morning and it echoed back, which is a yes.
    >>  ............................................
    pt  Um dia de dezessete nuvens. Esses são os bons. Contei pro poço hoje de manhã e ele ecoou de volta, o que é um sim.
    >>  ............................................
  odd.dialogue.conversations.day.good/2
    en  A twelve-bird morning and a cloud shaped like forgiveness. The signs are all pointing gentle today.
    >>  ............................................
    pt  Uma manhã de doze pássaros e uma nuvem em formato de perdão. Os sinais estão todos apontando pra gentileza hoje.
    >>  ............................................
  peaceful.dialogue.conversations.day.good/1
    en  A gentle day. I watched the light move across the wall for a while and didn't feel guilty about it.
    >>  ............................................
    pt  Um dia gentil. Fiquei um tempo vendo a luz atravessar a parede e não me senti culpado por isso.
    >>  ............................................
  peaceful.dialogue.conversations.day.good/2
    en  Good. The work was quiet and the afternoon was warm. I want nothing more than that.
    >>  ............................................
    pt  Bom. O trabalho foi quieto e a tarde, quente. Não quero nada além disso.
    >>  ............................................
  peppy.dialogue.conversations.day.good/1
    en  Best day! Okay, top three! The well water was SO cold and I only tripped over the bucket once!
    >>  ............................................
    pt  Melhor dia! Tá, top três! A água do poço estava TÃO gelada e eu só tropecei no balde uma vez!
    >>  ............................................
  peppy.dialogue.conversations.day.good/2
    en  I have done SEVEN things and it isn't even noon! Ask me about the seventh one!
    >>  ............................................
    pt  Eu já fiz SETE coisas e nem é meio-dia! Me pergunta da sétima!
    >>  ............................................
  playful.dialogue.conversations.day.good/1
    en  Grand day! I swapped two buckets round and I'm going to watch what happens. Don't tell anyone.
    >>  ............................................
    pt  Dia excelente! Troquei dois baldes de lugar e vou ficar observando o que acontece. Não conta pra ninguém.
    >>  ............................................
  playful.dialogue.conversations.day.good/2
    en  A good one. Sun out, work light, and one very confused chicken that I take full credit for.
    >>  ............................................
    pt  Foi bom. Sol lá fora, serviço leve, e uma galinha muito confusa pela qual eu assumo total responsabilidade.
    >>  ............................................
  relaxed.dialogue.conversations.day.good/1
    en  Good one. Got the morning's work done by midday and spent the rest of it in the sun, unrepentant.
    >>  ............................................
    pt  Foi bom. Terminei o serviço da manhã até o meio-dia e passei o resto no sol, sem remorso.
    >>  ............................................
  relaxed.dialogue.conversations.day.good/2
    en  Fine day. Nothing demanded anything of me twice. I call that a success.
    >>  ............................................
    pt  Belo dia. Nada exigiu nada de mim duas vezes. Chamo isso de sucesso.
    >>  ............................................
  sensitive.dialogue.conversations.day.good/1
    en  A gentle one. Cold well water, and the baker smiled at me. I keep those. You look tired, though — are you sleeping?
    >>  ............................................
    pt  Um dia gentil. Água de poço gelada, e o padeiro sorriu pra mim. Eu guardo essas coisas. Mas você parece cansado — está dormindo?
    >>  ............................................
  sensitive.dialogue.conversations.day.good/2
    en  Nobody was unkind to anybody where I could see it. That's what makes a day good, for me.
    >>  ............................................
    pt  Ninguém foi rude com ninguém onde eu pudesse ver. É isso que faz um dia bom, pra mim.
    >>  ............................................
  shy.dialogue.conversations.day.good/1
    en  A good day. I walked the long way round, where nobody goes, and came back with my thoughts in order.
    >>  ............................................
    pt  Um bom dia. Fiz o caminho mais longo, por onde ninguém vai, e voltei com os pensamentos em ordem.
    >>  ............................................
  shy.dialogue.conversations.day.good/2
    en  Quiet and useful. The garden asked nothing of me but hands. Those are my favourite days.
    >>  ............................................
    pt  Quieto e útil. A horta não me pediu nada além de mãos. São os meus dias favoritos.
    >>  ............................................
  upbeat.dialogue.conversations.day.good/1
    en  Lovely day. The chickens behaved, the sun showed up, and I got the fence finished. Small victories, stacked up.
    >>  ............................................
    pt  Dia adorável. As galinhas se comportaram, o sol apareceu, e eu terminei a cerca. Pequenas vitórias, empilhadas.
    >>  ............................................
  upbeat.dialogue.conversations.day.good/2
    en  A good one! Quiet, warm, everything where I left it. I'll remember this one fondly.
    >>  ............................................
    pt  Foi bom! Quieto, quentinho, tudo onde eu deixei. Vou lembrar desse com carinho.
    >>  ............................................
  witty.dialogue.conversations.day.good/1
    en  Lovely day. The chickens behaved, the sun showed up, and I got the fence finished. Small victories, stacked up.
    >>  ............................................
    pt  Dia adorável. As galinhas se comportaram, o sol apareceu, e eu terminei a cerca. Pequenas vitórias, empilhadas.
    >>  ............................................
  witty.dialogue.conversations.day.good/2
    en  A good one! Quiet, warm, everything where I left it. I'll remember this one fondly.
    >>  ............................................
    pt  Foi bom! Quieto, quentinho, tudo onde eu deixei. Vou lembrar desse com carinho.
    >>  ............................................
```

</details>


**Outcome 9 of 12** — base weight `0`

- Fires when: weighted +100 when `current_chore` = "chop"
- Fires when: weighted +100 when `current_chore` = "harvest"
- Fires when: weighted +100 when `current_chore` = "fish"
- Fires when: weighted +100 when `current_chore` = "hunt"
- Fires when: weighted +100 when `current_chore` = "prospect"
- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `happy`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `overjoyed`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `working` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.ordinary.respond`
- …where the player's next choices will be: "What are you working on?" | "Mine's been much the same." | "Don't let me slow you down, then." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.day.working
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.ordinary.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.working.to.day.ordinary`: the villager accepts. Subject `day.ordinary`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.working/1   [55 chars]
    en  Busy hands today. Ask me again when I've put this down.
    >>  ............................................
    pt  Mãos ocupadas hoje. Me pergunte de novo quando eu largar isso aqui.
    >>  ............................................
  dialogue.conversations.day.working/2   [58 chars]
    en  In the middle of it, as you can see. But it's honest work.
    >>  ............................................
    pt  No meio do serviço, como você está vendo. Mas é trabalho honesto.
    >>  ............................................
  dialogue.conversations.day.working/3   [71 chars]
    en  You've caught me mid-chore. Talk while I work — the wood doesn't judge.
    >>  ............................................
    pt  Você me pegou no meio da tarefa. Fala enquanto eu trabalho — a madeira não julga.
    >>  ............................................
```


**Outcome 10 of 12** — base weight `1`

- Fires when: RULED OUT when the mood is `depressed`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `sad`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `unhappy`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `happy`  _(chance -2000)_
- Fires when: RULED OUT when the mood is `overjoyed`  _(chance -2000)_
- Fires when: RULED OUT when `current_chore` = "chop"  _(chance -2000)_
- Fires when: RULED OUT when `current_chore` = "harvest"  _(chance -2000)_
- Fires when: RULED OUT when `current_chore` = "fish"  _(chance -2000)_
- Fires when: RULED OUT when `current_chore` = "hunt"  _(chance -2000)_
- Fires when: RULED OUT when `current_chore` = "prospect"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `day` branch `plain` budget `quick`
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.topic.day.ordinary.respond`
- …where the player's next choices will be: "What are you working on?" | "Mine's been much the same." | "Don't let me slow you down, then." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.day.plain
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.topic.day.ordinary.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.plain.to.day.ordinary`: the villager accepts. Subject `day.ordinary`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.day.plain/1   [69 chars]
    en  Ordinary. Nothing's broken and nothing's on fire. I'll take ordinary.
    >>  ............................................
    pt  Comum. Nada quebrou e nada pegou fogo. Aceito comum.
    >>  ............................................
  dialogue.conversations.day.plain/2   [47 chars]
    en  It's a Tuesday sort of day. Even when it isn't.
    >>  ............................................
    pt  É um dia com cara de terça. Mesmo quando não é.
    >>  ............................................
  dialogue.conversations.day.plain/3   [62 chars]
    en  Fine. Uneventful. Which around here counts as a small victory.
    >>  ............................................
    pt  Tranquilo. Sem novidade. O que por aqui já conta como vitória.
    >>  ............................................
```


**Outcome 11 of 12** — base weight `0`

- Fires when: weighted +100 when the mood is `depressed`
- Fires when: weighted +100 when the mood is `sad`
- Fires when: weighted +100 when the mood is `unhappy`
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.day` (this player only)  _(chance -1000)_
- Does: **hearts (raw MCA `positive` field)** = 3
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.rough
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.rough.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.chitchat` / button `day`** earlier in this file. Fill it in there, once.


**Outcome 12 of 12** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.cooldown.day` (this player only) for 12000 ticks
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.day.plain
WHO    VILLAGER — what the player reads after pressing "How's your day actually going?"
       spoken on: conversations.cat.chitchat, button `day`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `day.plain.terminal`: the villager accepts. Subject `day.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.chitchat` / button `day`** earlier in this file. Fill it in there, once.


### Button `food` — "What's good to eat around here?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `chitchat.food` — accepted phrasings: "what do you like to eat"; "what food do you like"; "favorite food"; "do you like to cook"; "are you hungry"
  - the message must contain one of: `food`, `eat`, `hungry`, `meal`, `cook`, `dish`
  - scored words: `food`(1.5), `eat`(1.2), `hungry`(1.0), `meal`(1.0), `cook`(0.8), `dish`(0.8), `favorite`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.food
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.food   [31 chars]
    en  What's good to eat around here?
    >>  ............................................
    pt  O que é bom pra comer por aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 14** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.food.whats_on_today"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.food.whats_on_today", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 36000 ticks
- Then opens: `conversations.scene.food.whats_on_today.respond`
- …where the player's next choices will be: "How do you make that?" | "That sounds good." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.food.whats_on_today
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.scene.food.whats_on_today.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.whats_on_today.open`: the villager reports. Subject `food.today`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.whats_on_today/1   [91 chars]
    en  Something with the last of the roots in it, which is a polite way of describing what it is.
    >>  ............................................
    pt  Alguma coisa com o resto das raízes, que é um jeito educado de descrever o que é.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today/2   [78 chars]
    en  Bread and whatever was nearest. I have made peace with the middle of the week.
    >>  ............................................
    pt  Pão e o que estava mais perto. Já fiz as pazes com o meio da semana.
    >>  ............................................
  dialogue.conversations.scene.food.whats_on_today/3   [96 chars]
    en  Good, actually. I got the timing right for once and nobody was waiting, which is half of a meal.
    >>  ............................................
    pt  Boa, na verdade. Acertei o tempo pela primeira vez e ninguém ficou esperando, e isso já é metade de uma refeição.
    >>  ............................................
```


**Outcome 2 of 14** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.food.the_fourteenth_time"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.food.the_fourteenth_time", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 36000 ticks
- Then opens: `conversations.scene.food.the_fourteenth_time.respond`
- …where the player's next choices will be: "How do you keep it interesting?" | "I'll eat with you one evening." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.food.the_fourteenth_time
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.scene.food.the_fourteenth_time.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.the_fourteenth_time.open`: the villager complains. Subject `food.winter_store`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:food` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.food.the_fourteenth_time/1   [93 chars]
    en  The same four things in a different order, which after a while stops being a different order.
    >>  ............................................
    pt  As mesmas quatro coisas em outra ordem, o que depois de um tempo deixa de ser outra ordem.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time/2   [102 chars]
    en  I could tell you exactly what is left in the store and exactly which week it runs out. That is winter.
    >>  ............................................
    pt  Eu poderia dizer exatamente o que resta na despensa e exatamente em que semana acaba. É isso o inverno.
    >>  ............................................
  dialogue.conversations.scene.food.the_fourteenth_time/3   [89 chars]
    en  It is enough and it is dull, and I have started to think the dullness is the harder half.
    >>  ............................................
    pt  É suficiente e é monótono, e comecei a achar que a monotonia é a metade mais difícil.
    >>  ............................................
```


**Outcome 3 of 14** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.food` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `again` budget `quick`
- Then opens: `conversations.topic.food.again.respond`
- …where the player's next choices will be: "Sorry — we covered this." | "Humour me. What are you craving?" | "Fair. Never mind."

```text
POOL   dialogue key: dialogue.conversations.food.again
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.again.to.food.again`: the villager accepts. Subject `food.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.food.again/1   [52 chars]
    en  Still hungry from the last time you asked, honestly.
    >>  ............................................
    pt  Continuo com fome desde a última vez que você perguntou, sinceramente.
    >>  ............................................
  dialogue.conversations.food.again/2   [69 chars]
    en  We just talked food and now I want bread again. See what you've done?
    >>  ............................................
    pt  A gente acabou de falar de comida e agora eu quero pão de novo. Viu o que você fez?
    >>  ............................................
  dialogue.conversations.food.again/3   [79 chars]
    en  My answer's the same, my stomach agrees, and it's nearly mealtime. Coincidence?
    >>  ............................................
    pt  Minha resposta é a mesma, meu estômago concorda, e já é quase hora da refeição. Coincidência?
    >>  ............................................
```


**Outcome 4 of 14** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `toddler` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.toddler.respond`
- …where the player's next choices will be: "That is excellent food." | "Which one is the best one?" | "Off you go and eat, then."

```text
POOL   dialogue key: dialogue.conversations.food.toddler
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.toddler.to.food.toddler`: the villager accepts. Subject `food.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.food.toddler/1   [36 chars]
    en  I like bread. And apples. And bread.
    >>  ............................................
    pt  Eu gosto de pão. E maçã. E pão.
    >>  ............................................
  dialogue.conversations.food.toddler/2   [40 chars]
    en  Sweet berries! But not the squishy ones.
    >>  ............................................
    pt  Frutinha doce! Mas não as molinhas.
    >>  ............................................
  dialogue.conversations.food.toddler/3   [54 chars]
    en  Mama cuts mine into little pieces so it tastes better.
    >>  ............................................
    pt  A mamãe corta a minha em pedacinhos, aí fica com gosto melhor.
    >>  ............................................
```


**Outcome 5 of 14** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `child` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.young.respond`
- …where the player's next choices will be: "That is an excellent choice." | "What else do you like?" | "That's not proper food." | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.food.child
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.child.to.food.young`: the villager accepts. Subject `food.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.food.child/1   [69 chars]
    en  Cookies first, THEN bread. That's the right order and nobody listens.
    >>  ............................................
    pt  Biscoito primeiro, DEPOIS pão. É essa a ordem certa e ninguém escuta.
    >>  ............................................
  dialogue.conversations.food.child/2   [30 chars]
    en  I tried a beetroot once. Once.
    >>  ............................................
    pt  Eu experimentei beterraba uma vez. Uma.
    >>  ............................................
```


**Outcome 6 of 14** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `teen` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.young.respond`
- …where the player's next choices will be: "That is an excellent choice." | "What else do you like?" | "That's not proper food." | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.food.teen
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.teen.to.food.young`: the villager accepts. Subject `food.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.food.teen/1   [47 chars]
    en  Whatever's in the pantry when nobody's looking.
    >>  ............................................
    pt  O que tiver na despensa quando ninguém tá olhando.
    >>  ............................................
  dialogue.conversations.food.teen/2   [53 chars]
    en  Bread's fine. I'm not picky anymore, I'm just hungry.
    >>  ............................................
    pt  Pão tá bom. Não sou mais exigente, só tô com fome.
    >>  ............................................
```


**Outcome 7 of 14** — base weight `0`

- Fires when: weighted +100 when `trait` = "coeliac_disease"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `trait` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.trait.respond`
- …where the player's next choices will be: "Then that's how it is. No fuss from me." | "How do you manage it, day to day?" | "Sounds like an excuse to be difficult." | "Understood. I'll leave it."

```text
POOL   dialogue key: dialogue.conversations.food.trait.coeliac
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.coeliac`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.coeliac/1   [83 chars]
    en  Bread hates me. Actual bread. In a village that's half bakery. The gods have humor.
    >>  ............................................
    pt  O pão me odeia. Pão de verdade. Num vilarejo que é metade padaria. Os deuses têm senso de humor.
    >>  ............................................
  dialogue.conversations.food.trait.coeliac/2   [95 chars]
    en  No wheat for me — one roll and I'm bedridden. I smell the bakery like other folk visit museums.
    >>  ............................................
    pt  Trigo pra mim não — um pãozinho e eu fico de cama. Eu cheiro a padaria como os outros visitam museu.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. In a village that's half bakery, %1$s. I've learned to leave early.
    >>  ............................................
    pt  Pão me odeia. Num vilarejo que é metade padaria, %1$s. Aprendi a sair cedo.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It took a long time to say that out loud without apologising for it.
    >>  ............................................
    pt  Nada de pão. Levou muito tempo pra dizer isso em voz alta sem pedir desculpa.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. It's been that way a long while and I've built the days around it.
    >>  ............................................
    pt  Pão me odeia. É assim faz tempo e eu construí os dias em volta disso.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's not a tragedy; it's a habit now, same as any other.
    >>  ............................................
    pt  Nada de pão. Não é tragédia; é hábito agora, como qualquer outro.
    >>  ............................................
  confident.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, in a village that is half bakery.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, num vilarejo que é metade padaria.
    >>  ............................................
  confident.dialogue.conversations.food.trait.coeliac/2
    en  No bread. Not a slice, not a crust, not to be polite. It costs me two days.
    >>  ............................................
    pt  Nada de pão. Nem uma fatia, nem uma casca, nem por educação. Me custa dois dias.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, in a village that is half bakery.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, num vilarejo que é metade padaria.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.coeliac/2
    en  No bread. Not a slice, not a crust, not to be polite. It costs me two days.
    >>  ............................................
    pt  Nada de pão. Nem uma fatia, nem uma casca, nem por educação. Me custa dois dias.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, %1$s, in a village that's half bakery. Don't be embarrassed for me.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, %1$s, num vilarejo que é metade padaria. Não fique constrangido por mim.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.coeliac/2
    en  No bread. If you're ever cooking for me, that's the whole of what you need to know.
    >>  ............................................
    pt  Nada de pão. Se você um dia cozinhar pra mim, é tudo que você precisa saber.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, %1$s, in a village that's half bakery. Don't be embarrassed for me.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, %1$s, num vilarejo que é metade padaria. Não fique constrangido por mim.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.coeliac/2
    en  No bread. If you're ever cooking for me, that's the whole of what you need to know.
    >>  ............................................
    pt  Nada de pão. Se você um dia cozinhar pra mim, é tudo que você precisa saber.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, %1$s, in a village that's half bakery. Don't be embarrassed for me.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, %1$s, num vilarejo que é metade padaria. Não fique constrangido por mim.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.coeliac/2
    en  No bread. If you're ever cooking for me, that's the whole of what you need to know.
    >>  ............................................
    pt  Nada de pão. Se você um dia cozinhar pra mim, é tudo que você precisa saber.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. In a village that's half bakery, %1$s. I've learned to leave early.
    >>  ............................................
    pt  Pão me odeia. Num vilarejo que é metade padaria, %1$s. Aprendi a sair cedo.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It took a long time to say that out loud without apologising for it.
    >>  ............................................
    pt  Nada de pão. Levou muito tempo pra dizer isso em voz alta sem pedir desculpa.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, in a village that is half bakery.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, num vilarejo que é metade padaria.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.coeliac/2
    en  No bread. Not a slice, not a crust, not to be polite. It costs me two days.
    >>  ............................................
    pt  Nada de pão. Nem uma fatia, nem uma casca, nem por educação. Me custa dois dias.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread, in a village that is half bakery.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade, num vilarejo que é metade padaria.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.coeliac/2
    en  No bread. Not a slice, not a crust, not to be polite. It costs me two days.
    >>  ............................................
    pt  Nada de pão. Nem uma fatia, nem uma casca, nem por educação. Me custa dois dias.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's arithmetic, every meal.
    >>  ............................................
    pt  Nada de pão. É aritmética, em toda refeição.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. It's been that way a long while and I've built the days around it.
    >>  ............................................
    pt  Pão me odeia. É assim faz tempo e eu construí os dias em volta disso.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's not a tragedy; it's a habit now, same as any other.
    >>  ............................................
    pt  Nada de pão. Não é tragédia; é hábito agora, como qualquer outro.
    >>  ............................................
  odd.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade.
    >>  ............................................
  odd.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's arithmetic, every meal.
    >>  ............................................
    pt  Nada de pão. É aritmética, em toda refeição.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. It's been that way a long while and I've built the days around it.
    >>  ............................................
    pt  Pão me odeia. É assim faz tempo e eu construí os dias em volta disso.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's not a tragedy; it's a habit now, same as any other.
    >>  ............................................
    pt  Nada de pão. Não é tragédia; é hábito agora, como qualquer outro.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me! Actual bread. In a village that is half bakery. The gods have humour.
    >>  ............................................
    pt  Pão me odeia! Pão de verdade. Num vilarejo que é metade padaria. Os deuses têm humor.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.coeliac/2
    en  No bread, ever. Do you know how many conversations here begin with bread? All of them.
    >>  ............................................
    pt  Nada de pão, nunca. Sabe quantas conversas aqui começam com pão? Todas.
    >>  ............................................
  playful.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me! Actual bread. In a village that is half bakery. The gods have humour.
    >>  ............................................
    pt  Pão me odeia! Pão de verdade. Num vilarejo que é metade padaria. Os deuses têm humor.
    >>  ............................................
  playful.dialogue.conversations.food.trait.coeliac/2
    en  No bread, ever. Do you know how many conversations here begin with bread? All of them.
    >>  ............................................
    pt  Nada de pão, nunca. Sabe quantas conversas aqui começam com pão? Todas.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. It's been that way a long while and I've built the days around it.
    >>  ............................................
    pt  Pão me odeia. É assim faz tempo e eu construí os dias em volta disso.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's not a tragedy; it's a habit now, same as any other.
    >>  ............................................
    pt  Nada de pão. Não é tragédia; é hábito agora, como qualquer outro.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. In a village that's half bakery, %1$s. I've learned to leave early.
    >>  ............................................
    pt  Pão me odeia. Num vilarejo que é metade padaria, %1$s. Aprendi a sair cedo.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It took a long time to say that out loud without apologising for it.
    >>  ............................................
    pt  Nada de pão. Levou muito tempo pra dizer isso em voz alta sem pedir desculpa.
    >>  ............................................
  shy.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me. Actual bread.
    >>  ............................................
    pt  Pão me odeia. Pão de verdade.
    >>  ............................................
  shy.dialogue.conversations.food.trait.coeliac/2
    en  No bread. It's arithmetic, every meal.
    >>  ............................................
    pt  Nada de pão. É aritmética, em toda refeição.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me! Actual bread. In a village that is half bakery. The gods have humour.
    >>  ............................................
    pt  Pão me odeia! Pão de verdade. Num vilarejo que é metade padaria. Os deuses têm humor.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.coeliac/2
    en  No bread, ever. Do you know how many conversations here begin with bread? All of them.
    >>  ............................................
    pt  Nada de pão, nunca. Sabe quantas conversas aqui começam com pão? Todas.
    >>  ............................................
  witty.dialogue.conversations.food.trait.coeliac/1
    en  Bread hates me! Actual bread. In a village that is half bakery. The gods have humour.
    >>  ............................................
    pt  Pão me odeia! Pão de verdade. Num vilarejo que é metade padaria. Os deuses têm humor.
    >>  ............................................
  witty.dialogue.conversations.food.trait.coeliac/2
    en  No bread, ever. Do you know how many conversations here begin with bread? All of them.
    >>  ............................................
    pt  Nada de pão, nunca. Sabe quantas conversas aqui começam com pão? Todas.
    >>  ............................................
```

</details>


**Outcome 8 of 14** — base weight `0`

- Fires when: weighted +100 when `trait` = "lactose_intolerance"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `trait` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.trait.respond`
- …where the player's next choices will be: "Then that's how it is. No fuss from me." | "How do you manage it, day to day?" | "Sounds like an excuse to be difficult." | "Understood. I'll leave it."

```text
POOL   dialogue key: dialogue.conversations.food.trait.lactose
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.lactose`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.lactose/1   [90 chars]
    en  Keep the milk away from me, %1$s. Learned that lesson in front of the whole market. Twice.
    >>  ............................................
    pt  Mantenha o leite longe de mim, %1$s. Aprendi essa lição na frente da feira inteira. Duas vezes.
    >>  ............................................
  dialogue.conversations.food.trait.lactose/2   [74 chars]
    en  Cheese and I are old enemies. It always wins, and everyone downwind loses.
    >>  ............................................
    pt  O queijo e eu somos inimigos antigos. Ele sempre ganha, e todo mundo a favor do vento perde.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and I still think about it.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e eu ainda penso nisso.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's the being watched that's hard, not the going without.
    >>  ............................................
    pt  Sem leite. O difícil é ser observado, não é ficar sem.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. Learned that long ago and I've arranged my meals since.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi faz tempo e organizei minhas refeições desde então.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's been no milk for years. There's no drama left in it.
    >>  ............................................
    pt  Sem leite. É sem leite há anos. Não sobrou drama nisso.
    >>  ............................................
  confident.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market. Twice.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi isso na frente do mercado inteiro. Duas vezes.
    >>  ............................................
  confident.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. It is not a preference and I have stopped explaining.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Não é preferência e eu parei de explicar.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market. Twice.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi isso na frente do mercado inteiro. Duas vezes.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. It is not a preference and I have stopped explaining.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Não é preferência e eu parei de explicar.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and they still tease me.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e ainda me provocam.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.lactose/2
    en  No milk. If you're cooking, ask me first — I'd rather that than be polite about it afterwards.
    >>  ............................................
    pt  Sem leite. Se você for cozinhar, me pergunte antes — prefiro isso a ser educado depois.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and they still tease me.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e ainda me provocam.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.lactose/2
    en  No milk. If you're cooking, ask me first — I'd rather that than be polite about it afterwards.
    >>  ............................................
    pt  Sem leite. Se você for cozinhar, me pergunte antes — prefiro isso a ser educado depois.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and they still tease me.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e ainda me provocam.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.lactose/2
    en  No milk. If you're cooking, ask me first — I'd rather that than be polite about it afterwards.
    >>  ............................................
    pt  Sem leite. Se você for cozinhar, me pergunte antes — prefiro isso a ser educado depois.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and I still think about it.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e eu ainda penso nisso.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's the being watched that's hard, not the going without.
    >>  ............................................
    pt  Sem leite. O difícil é ser observado, não é ficar sem.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market. Twice.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi isso na frente do mercado inteiro. Duas vezes.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. It is not a preference and I have stopped explaining.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Não é preferência e eu parei de explicar.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market. Twice.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi isso na frente do mercado inteiro. Duas vezes.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. It is not a preference and I have stopped explaining.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Não é preferência e eu parei de explicar.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me.
    >>  ............................................
    pt  Mantenha o leite longe de mim.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.lactose/2
    en  No milk. No cheese. That's all.
    >>  ............................................
    pt  Sem leite. Sem queijo. É só isso.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. Learned that long ago and I've arranged my meals since.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi faz tempo e organizei minhas refeições desde então.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's been no milk for years. There's no drama left in it.
    >>  ............................................
    pt  Sem leite. É sem leite há anos. Não sobrou drama nisso.
    >>  ............................................
  odd.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me.
    >>  ............................................
    pt  Mantenha o leite longe de mim.
    >>  ............................................
  odd.dialogue.conversations.food.trait.lactose/2
    en  No milk. No cheese. That's all.
    >>  ............................................
    pt  Sem leite. Sem queijo. É só isso.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. Learned that long ago and I've arranged my meals since.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi faz tempo e organizei minhas refeições desde então.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's been no milk for years. There's no drama left in it.
    >>  ............................................
    pt  Sem leite. É sem leite há anos. Não sobrou drama nisso.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me, %1$s. Learned that in front of the whole market. TWICE.
    >>  ............................................
    pt  Mantenha o leite longe de mim, %1$s. Aprendi na frente do mercado inteiro. DUAS vezes.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. In a village with a dairy. It's practically a comedy.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Num vilarejo com laticínio. É praticamente uma comédia.
    >>  ............................................
  playful.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me, %1$s. Learned that in front of the whole market. TWICE.
    >>  ............................................
    pt  Mantenha o leite longe de mim, %1$s. Aprendi na frente do mercado inteiro. DUAS vezes.
    >>  ............................................
  playful.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. In a village with a dairy. It's practically a comedy.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Num vilarejo com laticínio. É praticamente uma comédia.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. Learned that long ago and I've arranged my meals since.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi faz tempo e organizei minhas refeições desde então.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's been no milk for years. There's no drama left in it.
    >>  ............................................
    pt  Sem leite. É sem leite há anos. Não sobrou drama nisso.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me. I learned that in front of the whole market, and I still think about it.
    >>  ............................................
    pt  Mantenha o leite longe de mim. Aprendi na frente do mercado inteiro, e eu ainda penso nisso.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.lactose/2
    en  No milk. It's the being watched that's hard, not the going without.
    >>  ............................................
    pt  Sem leite. O difícil é ser observado, não é ficar sem.
    >>  ............................................
  shy.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me.
    >>  ............................................
    pt  Mantenha o leite longe de mim.
    >>  ............................................
  shy.dialogue.conversations.food.trait.lactose/2
    en  No milk. No cheese. That's all.
    >>  ............................................
    pt  Sem leite. Sem queijo. É só isso.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me, %1$s. Learned that in front of the whole market. TWICE.
    >>  ............................................
    pt  Mantenha o leite longe de mim, %1$s. Aprendi na frente do mercado inteiro. DUAS vezes.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. In a village with a dairy. It's practically a comedy.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Num vilarejo com laticínio. É praticamente uma comédia.
    >>  ............................................
  witty.dialogue.conversations.food.trait.lactose/1
    en  Keep the milk away from me, %1$s. Learned that in front of the whole market. TWICE.
    >>  ............................................
    pt  Mantenha o leite longe de mim, %1$s. Aprendi na frente do mercado inteiro. DUAS vezes.
    >>  ............................................
  witty.dialogue.conversations.food.trait.lactose/2
    en  No milk, no cheese, no butter. In a village with a dairy. It's practically a comedy.
    >>  ............................................
    pt  Sem leite, sem queijo, sem manteiga. Num vilarejo com laticínio. É praticamente uma comédia.
    >>  ............................................
```

</details>


**Outcome 9 of 14** — base weight `0`

- Fires when: weighted +100 when `trait` = "diabetes"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `trait` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.trait.respond`
- …where the player's next choices will be: "Then that's how it is. No fuss from me." | "How do you manage it, day to day?" | "Sounds like an excuse to be difficult." | "Understood. I'll leave it."

```text
POOL   dialogue key: dialogue.conversations.food.trait.diabetes
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.diabetes`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.diabetes/1   [78 chars]
    en  I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?
    >>  ............................................
    pt  Preciso maneirar no doce. Um biscoito, diz o clérigo. Quem é que assa UM biscoito?
    >>  ............................................
  dialogue.conversations.food.trait.diabetes/2   [96 chars]
    en  Sugar's rationed for me these days. I trade my desserts for gossip. Better for the heart anyway.
    >>  ............................................
    pt  Açúcar anda racionado pra mim. Troco minhas sobremesas por fofoca. Melhor pro coração de qualquer jeito.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says, and I count them at parties, %1$s.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga, e eu conto nas festas, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.diabetes/2
    en  It's arithmetic, every meal, forever. That's the part nobody thinks about.
    >>  ............................................
    pt  É aritmética, em toda refeição, pra sempre. É a parte em que ninguém pensa.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One a day. It's a rule, and rules get easier with years.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um por dia. É regra, e regras ficam mais fáceis com os anos.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. It's a habit now. Habits weigh less than rules do.
    >>  ............................................
    pt  Doces são contados. É hábito agora. Hábitos pesam menos que regras.
    >>  ............................................
  confident.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  confident.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. Every one, every day. It is arithmetic and it does not stop.
    >>  ............................................
    pt  Doces são contados. Todos, todo dia. É aritmética e não para.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  crabby.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. Every one, every day. It is arithmetic and it does not stop.
    >>  ............................................
    pt  Doces são contados. Todos, todo dia. É aritmética e não para.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Don't stop offering, though.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Mas não pare de oferecer.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. If you're baking, I'll want one and I'll want you to let me decide.
    >>  ............................................
    pt  Doces são contados. Se você assar, eu vou querer um e vou querer que você me deixe decidir.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Don't stop offering, though.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Mas não pare de oferecer.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. If you're baking, I'll want one and I'll want you to let me decide.
    >>  ............................................
    pt  Doces são contados. Se você assar, eu vou querer um e vou querer que você me deixe decidir.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Don't stop offering, though.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Mas não pare de oferecer.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. If you're baking, I'll want one and I'll want you to let me decide.
    >>  ............................................
    pt  Doces são contados. Se você assar, eu vou querer um e vou querer que você me deixe decidir.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says, and I count them at parties, %1$s.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga, e eu conto nas festas, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.diabetes/2
    en  It's arithmetic, every meal, forever. That's the part nobody thinks about.
    >>  ............................................
    pt  É aritmética, em toda refeição, pra sempre. É a parte em que ninguém pensa.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  greedy.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. Every one, every day. It is arithmetic and it does not stop.
    >>  ............................................
    pt  Doces são contados. Todos, todo dia. É aritmética e não para.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One cookie, the cleric says. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. Every one, every day. It is arithmetic and it does not stop.
    >>  ............................................
    pt  Doces são contados. Todos, todo dia. É aritmética e não para.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.diabetes/2
    en  Counted. Every one. Every day.
    >>  ............................................
    pt  Contados. Todos. Todo dia.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One a day. It's a rule, and rules get easier with years.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um por dia. É regra, e regras ficam mais fáceis com os anos.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. It's a habit now. Habits weigh less than rules do.
    >>  ............................................
    pt  Doces são contados. É hábito agora. Hábitos pesam menos que regras.
    >>  ............................................
  odd.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga.
    >>  ............................................
  odd.dialogue.conversations.food.trait.diabetes/2
    en  Counted. Every one. Every day.
    >>  ............................................
    pt  Contados. Todos. Todo dia.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One a day. It's a rule, and rules get easier with years.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um por dia. É regra, e regras ficam mais fáceis com os anos.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. It's a habit now. Habits weigh less than rules do.
    >>  ............................................
    pt  Doces são contados. É hábito agora. Hábitos pesam menos que regras.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets! One cookie, says the cleric. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces! Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  peppy.dialogue.conversations.food.trait.diabetes/2
    en  Every sweet thing is counted. Every single one. It's the dullest sum in the world and it's mine.
    >>  ............................................
    pt  Toda coisa doce é contada. Cada uma. É a conta mais chata do mundo e é minha.
    >>  ............................................
  playful.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets! One cookie, says the cleric. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces! Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  playful.dialogue.conversations.food.trait.diabetes/2
    en  Every sweet thing is counted. Every single one. It's the dullest sum in the world and it's mine.
    >>  ............................................
    pt  Toda coisa doce é contada. Cada uma. É a conta mais chata do mundo e é minha.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One a day. It's a rule, and rules get easier with years.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um por dia. É regra, e regras ficam mais fáceis com os anos.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.diabetes/2
    en  Sweets are counted. It's a habit now. Habits weigh less than rules do.
    >>  ............................................
    pt  Doces são contados. É hábito agora. Hábitos pesam menos que regras.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says, and I count them at parties, %1$s.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga, e eu conto nas festas, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.diabetes/2
    en  It's arithmetic, every meal, forever. That's the part nobody thinks about.
    >>  ............................................
    pt  É aritmética, em toda refeição, pra sempre. É a parte em que ninguém pensa.
    >>  ............................................
  shy.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets. One, the cleric says.
    >>  ............................................
    pt  Tenho que cuidar dos doces. Um, diz a clériga.
    >>  ............................................
  shy.dialogue.conversations.food.trait.diabetes/2
    en  Counted. Every one. Every day.
    >>  ............................................
    pt  Contados. Todos. Todo dia.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets! One cookie, says the cleric. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces! Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.diabetes/2
    en  Every sweet thing is counted. Every single one. It's the dullest sum in the world and it's mine.
    >>  ............................................
    pt  Toda coisa doce é contada. Cada uma. É a conta mais chata do mundo e é minha.
    >>  ............................................
  witty.dialogue.conversations.food.trait.diabetes/1
    en  I have to watch the sweets! One cookie, says the cleric. Who bakes ONE cookie?
    >>  ............................................
    pt  Tenho que cuidar dos doces! Um biscoito, diz a clériga. Quem assa UM biscoito?
    >>  ............................................
  witty.dialogue.conversations.food.trait.diabetes/2
    en  Every sweet thing is counted. Every single one. It's the dullest sum in the world and it's mine.
    >>  ............................................
    pt  Toda coisa doce é contada. Cada uma. É a conta mais chata do mundo e é minha.
    >>  ............................................
```

</details>


**Outcome 10 of 14** — base weight `0`

- Fires when: weighted +100 when `trait` = "vegetarian"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `trait` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.trait.respond`
- …where the player's next choices will be: "Then that's how it is. No fuss from me." | "How do you manage it, day to day?" | "Sounds like an excuse to be difficult." | "Understood. I'll leave it."

```text
POOL   dialogue key: dialogue.conversations.food.trait.vegetarian
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.trait.vegetarian`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.vegetarian/1   [83 chars]
    en  No meat for me — never touch it. The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne pra mim não — nunca encosto. Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  dialogue.conversations.food.trait.vegetarian/2   [86 chars]
    en  I don't eat anything with a face. The garden feeds me fine, and nothing in it screams.
    >>  ............................................
    pt  Não como nada que tenha rosto. A horta me alimenta bem, e nada nela grita.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. I decided that at nine years old and I've never been able to explain it well.
    >>  ............................................
    pt  Carne não. Decidi isso aos nove anos e nunca consegui explicar bem.
    >>  ............................................
  anxious.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. People take it as a judgement on them, and it isn't, and I've stopped saying so.
    >>  ............................................
    pt  Carne não. As pessoas tomam como julgamento, e não é, e eu parei de dizer.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. It's been that way a long while and nobody argues any more.
    >>  ............................................
    pt  Carne não. É assim faz tempo e ninguém mais discute.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. The butcher and I have had twenty years to get used to each other.
    >>  ............................................
    pt  Carne não. O açougueiro e eu tivemos vinte anos pra nos acostumar.
    >>  ............................................
  confident.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it. The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não. Nunca toco. Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  confident.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. It is not a phase and I have stopped defending it.
    >>  ............................................
    pt  Carne não. Não é fase e eu parei de defender.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it. The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não. Nunca toco. Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. It is not a phase and I have stopped defending it.
    >>  ............................................
    pt  Carne não. Não é fase e eu parei de defender.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. The butcher glares across the square and brings me his best vegetables anyway.
    >>  ............................................
    pt  Carne não. O açougueiro me encara da praça e mesmo assim me traz os melhores legumes.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. Ask before you cook and I'll eat anything else you put in front of me, gladly.
    >>  ............................................
    pt  Carne não. Pergunte antes de cozinhar e eu como qualquer outra coisa que você puser, com prazer.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. The butcher glares across the square and brings me his best vegetables anyway.
    >>  ............................................
    pt  Carne não. O açougueiro me encara da praça e mesmo assim me traz os melhores legumes.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. Ask before you cook and I'll eat anything else you put in front of me, gladly.
    >>  ............................................
    pt  Carne não. Pergunte antes de cozinhar e eu como qualquer outra coisa que você puser, com prazer.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. The butcher glares across the square and brings me his best vegetables anyway.
    >>  ............................................
    pt  Carne não. O açougueiro me encara da praça e mesmo assim me traz os melhores legumes.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. Ask before you cook and I'll eat anything else you put in front of me, gladly.
    >>  ............................................
    pt  Carne não. Pergunte antes de cozinhar e eu como qualquer outra coisa que você puser, com prazer.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. I decided that at nine years old and I've never been able to explain it well.
    >>  ............................................
    pt  Carne não. Decidi isso aos nove anos e nunca consegui explicar bem.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. People take it as a judgement on them, and it isn't, and I've stopped saying so.
    >>  ............................................
    pt  Carne não. As pessoas tomam como julgamento, e não é, e eu parei de dizer.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it. The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não. Nunca toco. Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. It is not a phase and I have stopped defending it.
    >>  ............................................
    pt  Carne não. Não é fase e eu parei de defender.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it. The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não. Nunca toco. Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. It is not a phase and I have stopped defending it.
    >>  ............................................
    pt  Carne não. Não é fase e eu parei de defender.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it.
    >>  ............................................
    pt  Carne não. Nunca toco.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. That's all there is to say about it.
    >>  ............................................
    pt  Carne não. É tudo que há pra dizer.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. It's been that way a long while and nobody argues any more.
    >>  ............................................
    pt  Carne não. É assim faz tempo e ninguém mais discute.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. The butcher and I have had twenty years to get used to each other.
    >>  ............................................
    pt  Carne não. O açougueiro e eu tivemos vinte anos pra nos acostumar.
    >>  ............................................
  odd.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it.
    >>  ............................................
    pt  Carne não. Nunca toco.
    >>  ............................................
  odd.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. That's all there is to say about it.
    >>  ............................................
    pt  Carne não. É tudo que há pra dizer.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. It's been that way a long while and nobody argues any more.
    >>  ............................................
    pt  Carne não. É assim faz tempo e ninguém mais discute.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. The butcher and I have had twenty years to get used to each other.
    >>  ............................................
    pt  Carne não. O açougueiro e eu tivemos vinte anos pra nos acostumar.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me — never touch it! The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não — nunca toco! Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. In a village with a butcher. We have a very cordial standoff, he and I.
    >>  ............................................
    pt  Carne não. Num vilarejo com açougueiro. Temos um impasse muito cordial, ele e eu.
    >>  ............................................
  playful.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me — never touch it! The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não — nunca toco! Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  playful.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. In a village with a butcher. We have a very cordial standoff, he and I.
    >>  ............................................
    pt  Carne não. Num vilarejo com açougueiro. Temos um impasse muito cordial, ele e eu.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. It's been that way a long while and nobody argues any more.
    >>  ............................................
    pt  Carne não. É assim faz tempo e ninguém mais discute.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. The butcher and I have had twenty years to get used to each other.
    >>  ............................................
    pt  Carne não. O açougueiro e eu tivemos vinte anos pra nos acostumar.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. I decided that at nine years old and I've never been able to explain it well.
    >>  ............................................
    pt  Carne não. Decidi isso aos nove anos e nunca consegui explicar bem.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. People take it as a judgement on them, and it isn't, and I've stopped saying so.
    >>  ............................................
    pt  Carne não. As pessoas tomam como julgamento, e não é, e eu parei de dizer.
    >>  ............................................
  shy.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me. Never touch it.
    >>  ............................................
    pt  Carne não. Nunca toco.
    >>  ............................................
  shy.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. That's all there is to say about it.
    >>  ............................................
    pt  Carne não. É tudo que há pra dizer.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me — never touch it! The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não — nunca toco! Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. In a village with a butcher. We have a very cordial standoff, he and I.
    >>  ............................................
    pt  Carne não. Num vilarejo com açougueiro. Temos um impasse muito cordial, ele e eu.
    >>  ............................................
  witty.dialogue.conversations.food.trait.vegetarian/1
    en  No meat for me — never touch it! The pigs and I have an arrangement built on trust.
    >>  ............................................
    pt  Carne não — nunca toco! Os porcos e eu temos um acordo baseado em confiança.
    >>  ............................................
  witty.dialogue.conversations.food.trait.vegetarian/2
    en  No meat. In a village with a butcher. We have a very cordial standoff, he and I.
    >>  ............................................
    pt  Carne não. Num vilarejo com açougueiro. Temos um impasse muito cordial, ele e eu.
    >>  ............................................
```

</details>


**Outcome 11 of 14** — base weight `0`

- Fires when: weighted +100 when `trait` = "sirben"
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `trait` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.trait.respond`
- …where the player's next choices will be: "Then that's how it is. No fuss from me." | "How do you manage it, day to day?" | "Sounds like an excuse to be difficult." | "Understood. I'll leave it."

```text
POOL   dialogue key: dialogue.conversations.food.trait.sirben
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.trait.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   1 line in this pool
NOTE   beat `food.trait.sirben`: the villager discloses. Subject `food.dietary_trait`, polarity `mixed`, permits followup, outcome `None`.
NOTE   this is the line that establishes `trait:dietary` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.trait.sirben   [78 chars]
    en  The sirben feast on the night wind, %1$s. We do not speak of the recipe. HRRK.
    >>  ............................................
    pt  Os sirben se banqueteiam com o vento da noite, %1$s. Não falamos da receita. HRRK.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe. Please don't ask again.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita. Por favor não pergunte de novo.
    >>  ............................................
  athletic.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We have never spoken of the recipe and we never will.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Nunca falamos da receita e nunca vamos falar.
    >>  ............................................
  confident.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  crabby.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  extroverted.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind, %1$s. We do not speak of the recipe, but I like that you asked.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno, %1$s. Não falamos da receita, mas gostei que perguntou.
    >>  ............................................
  flirty.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind, %1$s. We do not speak of the recipe, but I like that you asked.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno, %1$s. Não falamos da receita, mas gostei que perguntou.
    >>  ............................................
  friendly.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind, %1$s. We do not speak of the recipe, but I like that you asked.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno, %1$s. Não falamos da receita, mas gostei que perguntou.
    >>  ............................................
  gloomy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe. Please don't ask again.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita. Por favor não pergunte de novo.
    >>  ............................................
  greedy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  grumpy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  introverted.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  lazy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We have never spoken of the recipe and we never will.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Nunca falamos da receita e nunca vamos falar.
    >>  ............................................
  odd.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  peaceful.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We have never spoken of the recipe and we never will.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Nunca falamos da receita e nunca vamos falar.
    >>  ............................................
  peppy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind! We do not speak of the recipe. HRRK.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno! Não falamos da receita. HRRK.
    >>  ............................................
  playful.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind! We do not speak of the recipe. HRRK.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno! Não falamos da receita. HRRK.
    >>  ............................................
  relaxed.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We have never spoken of the recipe and we never will.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Nunca falamos da receita e nunca vamos falar.
    >>  ............................................
  sensitive.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe. Please don't ask again.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita. Por favor não pergunte de novo.
    >>  ............................................
  shy.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind. We do not speak of the recipe.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno. Não falamos da receita.
    >>  ............................................
  upbeat.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind! We do not speak of the recipe. HRRK.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno! Não falamos da receita. HRRK.
    >>  ............................................
  witty.dialogue.conversations.food.trait.sirben
    en  The sirben feast on the night wind! We do not speak of the recipe. HRRK.
    >>  ............................................
    pt  Os sirben se banqueteiam no vento noturno! Não falamos da receita. HRRK.
    >>  ............................................
```

</details>


**Outcome 12 of 14** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.food` (this player only)
- Fires when: RULED OUT when `trait` = "coeliac_disease"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "lactose_intolerance"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "diabetes"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "vegetarian"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "sirben"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `revisit` budget `quick`
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.normal.respond`
- …where the player's next choices will be: "Mine's plainer than that, I'll admit." | "How do you make it?" | "I'd argue with that, honestly." | "Now I'm hungry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.food.revisit
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.normal.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.revisited`: the villager discloses. Subject `food.preference`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `food:dish_named`, `food:asked_before` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, respectful_disagreement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.revisit/1   [89 chars]
    en  I thought about what you asked — I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Pensei no que você perguntou — experimentei aquela coisa de cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  dialogue.conversations.food.revisit/2   [95 chars]
    en  Since you asked last, the baker changed the rye recipe. The village is split. Families divided.
    >>  ............................................
    pt  Desde a última vez que você perguntou, o padeiro mudou a receita do centeio. O vilarejo se dividiu. Famílias separadas.
    >>  ............................................
  dialogue.conversations.food.revisit/3   [77 chars]
    en  Been cooking since we talked. Last night's barley soup was almost deliberate.
    >>  ............................................
    pt  Ando cozinhando desde que a gente falou. A sopa de cevada de ontem quase foi de propósito.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong, and I've been thinking about what else I'm wrong about.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu errei, e venho pensando em que mais eu erro.
    >>  ............................................
  anxious.dialogue.conversations.food.revisit/2
    en  I tried it. I'd been avoiding it for years for a reason that turned out to be nothing.
    >>  ............................................
    pt  Eu provei. Vinha evitando há anos por um motivo que acabou sendo nada.
    >>  ............................................
  anxious.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right, %1$s. It's a small thing and it isn't nothing.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão, %1$s. É uma coisa pequena e não é nada.
    >>  ............................................
  athletic.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms. It only took thirty years.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos. Só levou trinta anos.
    >>  ............................................
  athletic.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. These things come round eventually.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Essas coisas chegam uma hora.
    >>  ............................................
  athletic.dialogue.conversations.food.revisit/3
    en  The mushrooms. Changed my mind, in my own time, which is the only way I change it.
    >>  ............................................
    pt  Os cogumelos. Mudei de ideia, no meu tempo, que é o único jeito que eu mudo.
    >>  ............................................
  confident.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  confident.dialogue.conversations.food.revisit/2
    en  I tried it. You were right and I'll say so once.
    >>  ............................................
    pt  Eu provei. Você tinha razão e eu digo isso uma vez.
    >>  ............................................
  confident.dialogue.conversations.food.revisit/3
    en  The mushrooms. I've changed my position and I'd rather not discuss how completely.
    >>  ............................................
    pt  Os cogumelos. Mudei de posição e prefiro não discutir o quão completamente.
    >>  ............................................
  crabby.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  crabby.dialogue.conversations.food.revisit/2
    en  I tried it. You were right and I'll say so once.
    >>  ............................................
    pt  Eu provei. Você tinha razão e eu digo isso uma vez.
    >>  ............................................
  crabby.dialogue.conversations.food.revisit/3
    en  The mushrooms. I've changed my position and I'd rather not discuss how completely.
    >>  ............................................
    pt  Os cogumelos. Mudei de posição e prefiro não discutir o quão completamente.
    >>  ............................................
  extroverted.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing, %1$s. I was wrong about mushrooms and you should hear me say it.
    >>  ............................................
    pt  Provei a coisa do cogumelo, %1$s. Eu estava errado sobre cogumelos e você merece ouvir.
    >>  ............................................
  extroverted.dialogue.conversations.food.revisit/2
    en  I tried it because you asked me to. That's the only reason, and I'm glad of it.
    >>  ............................................
    pt  Provei porque você pediu. É a única razão, e eu estou contente.
    >>  ............................................
  extroverted.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right. Tell me the next thing I'm wrong about.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão. Me diga a próxima coisa em que eu estou errado.
    >>  ............................................
  flirty.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing, %1$s. I was wrong about mushrooms and you should hear me say it.
    >>  ............................................
    pt  Provei a coisa do cogumelo, %1$s. Eu estava errado sobre cogumelos e você merece ouvir.
    >>  ............................................
  flirty.dialogue.conversations.food.revisit/2
    en  I tried it because you asked me to. That's the only reason, and I'm glad of it.
    >>  ............................................
    pt  Provei porque você pediu. É a única razão, e eu estou contente.
    >>  ............................................
  flirty.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right. Tell me the next thing I'm wrong about.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão. Me diga a próxima coisa em que eu estou errado.
    >>  ............................................
  friendly.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing, %1$s. I was wrong about mushrooms and you should hear me say it.
    >>  ............................................
    pt  Provei a coisa do cogumelo, %1$s. Eu estava errado sobre cogumelos e você merece ouvir.
    >>  ............................................
  friendly.dialogue.conversations.food.revisit/2
    en  I tried it because you asked me to. That's the only reason, and I'm glad of it.
    >>  ............................................
    pt  Provei porque você pediu. É a única razão, e eu estou contente.
    >>  ............................................
  friendly.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right. Tell me the next thing I'm wrong about.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão. Me diga a próxima coisa em que eu estou errado.
    >>  ............................................
  gloomy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong, and I've been thinking about what else I'm wrong about.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu errei, e venho pensando em que mais eu erro.
    >>  ............................................
  gloomy.dialogue.conversations.food.revisit/2
    en  I tried it. I'd been avoiding it for years for a reason that turned out to be nothing.
    >>  ............................................
    pt  Eu provei. Vinha evitando há anos por um motivo que acabou sendo nada.
    >>  ............................................
  gloomy.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right, %1$s. It's a small thing and it isn't nothing.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão, %1$s. É uma coisa pequena e não é nada.
    >>  ............................................
  greedy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  greedy.dialogue.conversations.food.revisit/2
    en  I tried it. You were right and I'll say so once.
    >>  ............................................
    pt  Eu provei. Você tinha razão e eu digo isso uma vez.
    >>  ............................................
  greedy.dialogue.conversations.food.revisit/3
    en  The mushrooms. I've changed my position and I'd rather not discuss how completely.
    >>  ............................................
    pt  Os cogumelos. Mudei de posição e prefiro não discutir o quão completamente.
    >>  ............................................
  grumpy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  grumpy.dialogue.conversations.food.revisit/2
    en  I tried it. You were right and I'll say so once.
    >>  ............................................
    pt  Eu provei. Você tinha razão e eu digo isso uma vez.
    >>  ............................................
  grumpy.dialogue.conversations.food.revisit/3
    en  The mushrooms. I've changed my position and I'd rather not discuss how completely.
    >>  ............................................
    pt  Os cogumelos. Mudei de posição e prefiro não discutir o quão completamente.
    >>  ............................................
  introverted.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  introverted.dialogue.conversations.food.revisit/2
    en  You were right.
    >>  ............................................
    pt  Você tinha razão.
    >>  ............................................
  introverted.dialogue.conversations.food.revisit/3
    en  The mushrooms. Yes. I've changed my mind.
    >>  ............................................
    pt  Os cogumelos. Sim. Eu mudei de ideia.
    >>  ............................................
  lazy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms. It only took thirty years.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos. Só levou trinta anos.
    >>  ............................................
  lazy.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. These things come round eventually.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Essas coisas chegam uma hora.
    >>  ............................................
  lazy.dialogue.conversations.food.revisit/3
    en  The mushrooms. Changed my mind, in my own time, which is the only way I change it.
    >>  ............................................
    pt  Os cogumelos. Mudei de ideia, no meu tempo, que é o único jeito que eu mudo.
    >>  ............................................
  odd.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  odd.dialogue.conversations.food.revisit/2
    en  You were right.
    >>  ............................................
    pt  Você tinha razão.
    >>  ............................................
  odd.dialogue.conversations.food.revisit/3
    en  The mushrooms. Yes. I've changed my mind.
    >>  ............................................
    pt  Os cogumelos. Sim. Eu mudei de ideia.
    >>  ............................................
  peaceful.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms. It only took thirty years.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos. Só levou trinta anos.
    >>  ............................................
  peaceful.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. These things come round eventually.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Essas coisas chegam uma hora.
    >>  ............................................
  peaceful.dialogue.conversations.food.revisit/3
    en  The mushrooms. Changed my mind, in my own time, which is the only way I change it.
    >>  ............................................
    pt  Os cogumelos. Mudei de ideia, no meu tempo, que é o único jeito que eu mudo.
    >>  ............................................
  peppy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing! I was wrong about mushrooms. Deeply, publicly wrong.
    >>  ............................................
    pt  Provei a coisa do cogumelo! Eu estava errado sobre cogumelos. Profunda e publicamente errado.
    >>  ............................................
  peppy.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. I intend never to mention this again after today.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Pretendo nunca mais mencionar isso depois de hoje.
    >>  ............................................
  peppy.dialogue.conversations.food.revisit/3
    en  The mushrooms have won. I've conceded. There is no dignity left in this kitchen.
    >>  ............................................
    pt  Os cogumelos venceram. Eu me rendi. Não sobrou dignidade nesta cozinha.
    >>  ............................................
  playful.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing! I was wrong about mushrooms. Deeply, publicly wrong.
    >>  ............................................
    pt  Provei a coisa do cogumelo! Eu estava errado sobre cogumelos. Profunda e publicamente errado.
    >>  ............................................
  playful.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. I intend never to mention this again after today.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Pretendo nunca mais mencionar isso depois de hoje.
    >>  ............................................
  playful.dialogue.conversations.food.revisit/3
    en  The mushrooms have won. I've conceded. There is no dignity left in this kitchen.
    >>  ............................................
    pt  Os cogumelos venceram. Eu me rendi. Não sobrou dignidade nesta cozinha.
    >>  ............................................
  relaxed.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms. It only took thirty years.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos. Só levou trinta anos.
    >>  ............................................
  relaxed.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. These things come round eventually.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Essas coisas chegam uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.food.revisit/3
    en  The mushrooms. Changed my mind, in my own time, which is the only way I change it.
    >>  ............................................
    pt  Os cogumelos. Mudei de ideia, no meu tempo, que é o único jeito que eu mudo.
    >>  ............................................
  sensitive.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong, and I've been thinking about what else I'm wrong about.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu errei, e venho pensando em que mais eu erro.
    >>  ............................................
  sensitive.dialogue.conversations.food.revisit/2
    en  I tried it. I'd been avoiding it for years for a reason that turned out to be nothing.
    >>  ............................................
    pt  Eu provei. Vinha evitando há anos por um motivo que acabou sendo nada.
    >>  ............................................
  sensitive.dialogue.conversations.food.revisit/3
    en  The mushrooms. You were right, %1$s. It's a small thing and it isn't nothing.
    >>  ............................................
    pt  Os cogumelos. Você tinha razão, %1$s. É uma coisa pequena e não é nada.
    >>  ............................................
  shy.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing. I was wrong about mushrooms.
    >>  ............................................
    pt  Provei a coisa do cogumelo. Eu estava errado sobre cogumelos.
    >>  ............................................
  shy.dialogue.conversations.food.revisit/2
    en  You were right.
    >>  ............................................
    pt  Você tinha razão.
    >>  ............................................
  shy.dialogue.conversations.food.revisit/3
    en  The mushrooms. Yes. I've changed my mind.
    >>  ............................................
    pt  Os cogumelos. Sim. Eu mudei de ideia.
    >>  ............................................
  upbeat.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing! I was wrong about mushrooms. Deeply, publicly wrong.
    >>  ............................................
    pt  Provei a coisa do cogumelo! Eu estava errado sobre cogumelos. Profunda e publicamente errado.
    >>  ............................................
  upbeat.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. I intend never to mention this again after today.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Pretendo nunca mais mencionar isso depois de hoje.
    >>  ............................................
  upbeat.dialogue.conversations.food.revisit/3
    en  The mushrooms have won. I've conceded. There is no dignity left in this kitchen.
    >>  ............................................
    pt  Os cogumelos venceram. Eu me rendi. Não sobrou dignidade nesta cozinha.
    >>  ............................................
  witty.dialogue.conversations.food.revisit/1
    en  I tried the mushroom thing! I was wrong about mushrooms. Deeply, publicly wrong.
    >>  ............................................
    pt  Provei a coisa do cogumelo! Eu estava errado sobre cogumelos. Profunda e publicamente errado.
    >>  ............................................
  witty.dialogue.conversations.food.revisit/2
    en  I tried it. You were right. I intend never to mention this again after today.
    >>  ............................................
    pt  Eu provei. Você tinha razão. Pretendo nunca mais mencionar isso depois de hoje.
    >>  ............................................
  witty.dialogue.conversations.food.revisit/3
    en  The mushrooms have won. I've conceded. There is no dignity left in this kitchen.
    >>  ............................................
    pt  Os cogumelos venceram. Eu me rendi. Não sobrou dignidade nesta cozinha.
    >>  ............................................
```

</details>


**Outcome 13 of 14** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.food` (this player only)
- Fires when: RULED OUT when `trait` = "coeliac_disease"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "lactose_intolerance"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "diabetes"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "vegetarian"  _(chance -2000)_
- Fires when: RULED OUT when `trait` = "sirben"  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.food` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `food` branch `first` budget `quick`
- Does: remembers `mcaconversations.topic.food` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.topic.food.normal.respond`
- …where the player's next choices will be: "Mine's plainer than that, I'll admit." | "How do you make it?" | "I'd argue with that, honestly." | "Now I'm hungry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.food.first
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.topic.food.normal.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.first_choice`: the villager discloses. Subject `food.preference`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `food:dish_named` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, curiosity, respectful_disagreement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.food.first/1   [91 chars]
    en  The baker's rye, fresh, with too much butter. That's the correct answer and I'll die on it.
    >>  ............................................
    pt  O pão de centeio do padeiro, fresquinho, com manteiga demais. É a resposta correta e eu morro por ela.
    >>  ............................................
  dialogue.conversations.food.first/2   [90 chars]
    en  Stew night at the inn. Get there early or fight the blacksmith for the last bowl. He wins.
    >>  ............................................
    pt  Noite de ensopado na estalagem. Chegue cedo ou brigue com o ferreiro pela última tigela. Ele ganha.
    >>  ............................................
  dialogue.conversations.food.first/3   [87 chars]
    en  Fried eggs at midday, when nobody's watching. Not a proud answer. The true one, though.
    >>  ............................................
    pt  Ovo frito ao meio-dia, quando ninguém está olhando. Não é uma resposta digna. Mas é a verdadeira.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.food.first/1
    en  Something plain that I know agrees with me. I don't like surprises at supper, %1$s.
    >>  ............................................
    pt  Algo simples que eu sei que me cai bem. Não gosto de surpresa na janta, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.food.first/2
    en  Bread and broth. Same as yesterday. There's a lot to be said for knowing what's coming.
    >>  ............................................
    pt  Pão e caldo. Igual a ontem. Tem muito a se dizer sobre saber o que vem pela frente.
    >>  ............................................
  anxious.dialogue.conversations.food.first/3
    en  The baker's rye. There were years I couldn't afford it, so it means more than it should.
    >>  ............................................
    pt  O centeio do padeiro. Teve anos em que eu não podia pagar, então significa mais do que deveria.
    >>  ............................................
  athletic.dialogue.conversations.food.first/1
    en  Anything hearty and plenty of it. A body this busy runs on fuel, not frills.
    >>  ............................................
    pt  Qualquer coisa reforçada e em quantidade. Um corpo ocupado desses roda a combustível, não a frescura.
    >>  ............................................
  athletic.dialogue.conversations.food.first/2
    en  Big plates, no fuss. I've earned every bite by noon and then some.
    >>  ............................................
    pt  Prato grande, sem firula. Já mereci cada garfada até o meio-dia e ainda sobra.
    >>  ............................................
  athletic.dialogue.conversations.food.first/3
    en  The baker's rye. Nothing about it needs improving and nothing about it has changed.
    >>  ............................................
    pt  O centeio do padeiro. Nada nele precisa melhorar e nada nele mudou.
    >>  ............................................
  confident.dialogue.conversations.food.first/1
    en  The finest the inn offers. They save the best cut for me; reputation has its perks.
    >>  ............................................
    pt  O melhor que a estalagem oferece. Guardam o corte mais nobre pra mim; reputação tem suas vantagens.
    >>  ............................................
  confident.dialogue.conversations.food.first/2
    en  Whatever's boldest on the board. I've a strong palate, like everything else about me.
    >>  ............................................
    pt  O que houver de mais marcante no cardápio. Tenho um paladar forte, como tudo em mim.
    >>  ............................................
  confident.dialogue.conversations.food.first/3
    en  The baker's rye. I've eaten better elsewhere and I'd still choose it.
    >>  ............................................
    pt  O centeio do padeiro. Já comi melhor em outro lugar e ainda escolheria esse.
    >>  ............................................
  crabby.dialogue.conversations.food.first/1
    en  Something hot that nobody's meddled with. Plain stew. I don't need my food to be interesting.
    >>  ............................................
    pt  Alguma coisa quente em que ninguém mexeu. Ensopado simples. Não preciso que a minha comida seja interessante.
    >>  ............................................
  crabby.dialogue.conversations.food.first/2
    en  Bread. Proper bread, not the fancy sort. And quiet to eat it in.
    >>  ............................................
    pt  Pão. Pão de verdade, não o chique. E silêncio pra comer.
    >>  ............................................
  crabby.dialogue.conversations.food.first/3
    en  The baker's rye. I've eaten better elsewhere and I'd still choose it.
    >>  ............................................
    pt  O centeio do padeiro. Já comi melhor em outro lugar e ainda escolheria esse.
    >>  ............................................
  extroverted.dialogue.conversations.food.first/1
    en  Anything at a crowded table. The food's secondary — it's the noise around it that I'm there for.
    >>  ............................................
    pt  Qualquer coisa numa mesa cheia. A comida é secundária — é o barulho em volta que me interessa.
    >>  ............................................
  extroverted.dialogue.conversations.food.first/2
    en  Whatever's being shared. I've never once enjoyed a meal alone and I don't intend to start.
    >>  ............................................
    pt  O que estiver sendo dividido. Nunca aproveitei uma refeição sozinho e não pretendo começar.
    >>  ............................................
  extroverted.dialogue.conversations.food.first/3
    en  The baker's rye. Half of why it's good is who I usually eat it with.
    >>  ............................................
    pt  O centeio do padeiro. Metade do porquê ser bom é com quem eu costumo comer.
    >>  ............................................
  flirty.dialogue.conversations.food.first/1
    en  Something to share, slowly, by candlelight. Food's just an excuse for good company. Care to test it?
    >>  ............................................
    pt  Algo pra dividir, devagar, à luz de vela. Comida é só desculpa pra boa companhia. Quer testar?
    >>  ............................................
  flirty.dialogue.conversations.food.first/2
    en  Wine and something sweet. I've a weakness for indulgence — you may have noticed.
    >>  ............................................
    pt  Vinho e alguma coisa doce. Tenho fraqueza por indulgência — você deve ter reparado.
    >>  ............................................
  flirty.dialogue.conversations.food.first/3
    en  The baker's rye. Half of why it's good is who I usually eat it with.
    >>  ............................................
    pt  O centeio do padeiro. Metade do porquê ser bom é com quem eu costumo comer.
    >>  ............................................
  friendly.dialogue.conversations.food.first/1
    en  Anything shared! A meal's better with company. Bring your appetite, I'll bring the butter.
    >>  ............................................
    pt  Qualquer coisa dividida! Refeição é melhor com companhia. Traga o apetite que eu trago a manteiga.
    >>  ............................................
  friendly.dialogue.conversations.food.first/2
    en  The inn's stew, at a full table. Food tastes better among laughing folk, don't you think?
    >>  ............................................
    pt  O ensopado da estalagem, com a mesa cheia. Comida tem gosto melhor entre gente rindo, não acha?
    >>  ............................................
  friendly.dialogue.conversations.food.first/3
    en  The baker's rye. Half of why it's good is who I usually eat it with.
    >>  ............................................
    pt  O centeio do padeiro. Metade do porquê ser bom é com quem eu costumo comer.
    >>  ............................................
  gloomy.dialogue.conversations.food.first/1
    en  Whatever's warm. Warmth is the point; the taste is a bonus I've stopped expecting.
    >>  ............................................
    pt  O que estiver quente. O calor é o ponto; o sabor é um bônus que eu parei de esperar.
    >>  ............................................
  gloomy.dialogue.conversations.food.first/2
    en  Bread and broth. Nothing that raises hopes it can't meet. That's my whole philosophy, really.
    >>  ............................................
    pt  Pão e caldo. Nada que levante esperanças que não possa cumprir. É essa a minha filosofia toda, na verdade.
    >>  ............................................
  gloomy.dialogue.conversations.food.first/3
    en  The baker's rye. There were years I couldn't afford it, so it means more than it should.
    >>  ............................................
    pt  O centeio do padeiro. Teve anos em que eu não podia pagar, então significa mais do que deveria.
    >>  ............................................
  greedy.dialogue.conversations.food.first/1
    en  Whatever's cheapest per bite and fills the longest. I didn't get this pantry by paying inn prices.
    >>  ............................................
    pt  O que for mais barato por garfada e sustentar por mais tempo. Não montei essa despensa pagando preço de estalagem.
    >>  ............................................
  greedy.dialogue.conversations.food.first/2
    en  Free is my favorite flavor. The well water, a neighbor's guilt-gift, that sort of thing. Tastes like savings.
    >>  ............................................
    pt  Grátis é o meu sabor favorito. A água do poço, o presente de culpa de um vizinho, esse tipo de coisa. Tem gosto de economia.
    >>  ............................................
  greedy.dialogue.conversations.food.first/3
    en  The baker's rye. I've eaten better elsewhere and I'd still choose it.
    >>  ............................................
    pt  O centeio do padeiro. Já comi melhor em outro lugar e ainda escolheria esse.
    >>  ............................................
  grumpy.dialogue.conversations.food.first/1
    en  Something hot that nobody's meddled with. Plain stew. I don't need my food to be interesting.
    >>  ............................................
    pt  Alguma coisa quente em que ninguém mexeu. Ensopado simples. Não preciso que a minha comida seja interessante.
    >>  ............................................
  grumpy.dialogue.conversations.food.first/2
    en  Bread. Proper bread, not the fancy sort. And quiet to eat it in.
    >>  ............................................
    pt  Pão. Pão de verdade, não o chique. E silêncio pra comer.
    >>  ............................................
  grumpy.dialogue.conversations.food.first/3
    en  The baker's rye. I've eaten better elsewhere and I'd still choose it.
    >>  ............................................
    pt  O centeio do padeiro. Já comi melhor em outro lugar e ainda escolheria esse.
    >>  ............................................
  introverted.dialogue.conversations.food.first/1
    en  Bread and butter by a window, eaten slowly, with no one waiting on me to say something.
    >>  ............................................
    pt  Pão com manteiga na janela, comido devagar, sem ninguém esperando que eu diga alguma coisa.
    >>  ............................................
  introverted.dialogue.conversations.food.first/2
    en  Whatever the inn serves when the inn is empty. The soup tastes better without the crowd.
    >>  ............................................
    pt  O que a estalagem servir quando a estalagem estiver vazia. A sopa tem gosto melhor sem a multidão.
    >>  ............................................
  introverted.dialogue.conversations.food.first/3
    en  The baker's rye. That's the answer and it hasn't changed in years.
    >>  ............................................
    pt  O centeio do padeiro. É a resposta e não muda há anos.
    >>  ............................................
  lazy.dialogue.conversations.food.first/1
    en  Anything eaten sitting down, with time to finish it. Stew, mostly. Stew doesn't rush you.
    >>  ............................................
    pt  Qualquer coisa comida sentado, com tempo pra terminar. Ensopado, quase sempre. Ensopado não te apressa.
    >>  ............................................
  lazy.dialogue.conversations.food.first/2
    en  Bread, cheese, shade. Not complicated. The best things generally aren't.
    >>  ............................................
    pt  Pão, queijo, sombra. Nada complicado. As melhores coisas geralmente não são.
    >>  ............................................
  lazy.dialogue.conversations.food.first/3
    en  The baker's rye. Nothing about it needs improving and nothing about it has changed.
    >>  ............................................
    pt  O centeio do padeiro. Nada nele precisa melhorar e nada nele mudou.
    >>  ............................................
  odd.dialogue.conversations.food.first/1
    en  Whatever the bucket recommends. Today it said turnips, but the bucket has an agenda. I'm having bread instead.
    >>  ............................................
    pt  O que o balde recomendar. Hoje ele disse nabo, mas o balde tem uma agenda própria. Vou de pão mesmo.
    >>  ............................................
  odd.dialogue.conversations.food.first/2
    en  Soup, if it's the right shape in the bowl. Round is calm, oval is ambitious. I eat by the omens, %1$s.
    >>  ............................................
    pt  Sopa, se estiver no formato certo na tigela. Redondo é calmo, oval é ambicioso. Eu como pelos presságios, %1$s.
    >>  ............................................
  odd.dialogue.conversations.food.first/3
    en  The baker's rye. That's the answer and it hasn't changed in years.
    >>  ............................................
    pt  O centeio do padeiro. É a resposta e não muda há anos.
    >>  ............................................
  peaceful.dialogue.conversations.food.first/1
    en  Something simple, eaten slowly, ideally outdoors. Bread and fruit and no particular hurry.
    >>  ............................................
    pt  Algo simples, comido devagar, de preferência ao ar livre. Pão e fruta e nenhuma pressa em particular.
    >>  ............................................
  peaceful.dialogue.conversations.food.first/2
    en  Whatever's in season, prepared plainly. Food doesn't need to be clever to be good.
    >>  ............................................
    pt  O que estiver na estação, preparado com simplicidade. Comida não precisa ser inteligente pra ser boa.
    >>  ............................................
  peaceful.dialogue.conversations.food.first/3
    en  The baker's rye. Nothing about it needs improving and nothing about it has changed.
    >>  ............................................
    pt  O centeio do padeiro. Nada nele precisa melhorar e nada nele mudou.
    >>  ............................................
  peppy.dialogue.conversations.food.first/1
    en  Anything at a big loud table! Food's just an EXCUSE to gather everyone, and gathering's my favorite sport!
    >>  ............................................
    pt  Qualquer coisa numa mesa grande e barulhenta! Comida é só uma DESCULPA pra juntar todo mundo, e juntar é o meu esporte favorito!
    >>  ............................................
  peppy.dialogue.conversations.food.first/2
    en  The inn's honey cakes! I could eat six and talk through all of them! Come sit, I'll save you a seat!
    >>  ............................................
    pt  Os bolinhos de mel da estalagem! Eu comeria seis e falaria durante os seis! Vem sentar, eu guardo um lugar!
    >>  ............................................
  peppy.dialogue.conversations.food.first/3
    en  The baker's rye. Everything else is just food that isn't the baker's rye.
    >>  ............................................
    pt  O centeio do padeiro. Todo o resto é só comida que não é o centeio do padeiro.
    >>  ............................................
  playful.dialogue.conversations.food.first/1
    en  Whatever I've pinched off someone else's plate. It always tastes better that way, %1$s.
    >>  ............................................
    pt  O que eu tiver surrupiado do prato de alguém. Sempre tem gosto melhor assim, %1$s.
    >>  ............................................
  playful.dialogue.conversations.food.first/2
    en  Honeyed anything. And I'm not above trading a favour for the last of it.
    >>  ............................................
    pt  Qualquer coisa com mel. E eu não sou orgulhoso demais pra trocar um favor pelo último pedaço.
    >>  ............................................
  playful.dialogue.conversations.food.first/3
    en  The baker's rye. Everything else is just food that isn't the baker's rye.
    >>  ............................................
    pt  O centeio do padeiro. Todo o resto é só comida que não é o centeio do padeiro.
    >>  ............................................
  relaxed.dialogue.conversations.food.first/1
    en  Anything eaten sitting down, with time to finish it. Stew, mostly. Stew doesn't rush you.
    >>  ............................................
    pt  Qualquer coisa comida sentado, com tempo pra terminar. Ensopado, quase sempre. Ensopado não te apressa.
    >>  ............................................
  relaxed.dialogue.conversations.food.first/2
    en  Bread, cheese, shade. Not complicated. The best things generally aren't.
    >>  ............................................
    pt  Pão, queijo, sombra. Nada complicado. As melhores coisas geralmente não são.
    >>  ............................................
  relaxed.dialogue.conversations.food.first/3
    en  The baker's rye. Nothing about it needs improving and nothing about it has changed.
    >>  ............................................
    pt  O centeio do padeiro. Nada nele precisa melhorar e nada nele mudou.
    >>  ............................................
  sensitive.dialogue.conversations.food.first/1
    en  Something shared, at a warm table. Food's love made edible, %1$s — that's not just a saying to me.
    >>  ............................................
    pt  Algo dividido, numa mesa quente. Comida é amor em forma comestível, %1$s — pra mim isso não é só um dito.
    >>  ............................................
  sensitive.dialogue.conversations.food.first/2
    en  The inn's stew when the whole family's there. It's never really about the food. It's the faces around it.
    >>  ............................................
    pt  O ensopado da estalagem com a família toda lá. Nunca é sobre a comida, na verdade. É sobre os rostos em volta.
    >>  ............................................
  sensitive.dialogue.conversations.food.first/3
    en  The baker's rye. There were years I couldn't afford it, so it means more than it should.
    >>  ............................................
    pt  O centeio do padeiro. Teve anos em que eu não podia pagar, então significa mais do que deveria.
    >>  ............................................
  shy.dialogue.conversations.food.first/1
    en  Bread and butter by a window, eaten slowly, with no one waiting on me to say something.
    >>  ............................................
    pt  Pão com manteiga na janela, comido devagar, sem ninguém esperando que eu diga alguma coisa.
    >>  ............................................
  shy.dialogue.conversations.food.first/2
    en  Whatever the inn serves when the inn is empty. The soup tastes better without the crowd.
    >>  ............................................
    pt  O que a estalagem servir quando a estalagem estiver vazia. A sopa tem gosto melhor sem a multidão.
    >>  ............................................
  shy.dialogue.conversations.food.first/3
    en  The baker's rye. That's the answer and it hasn't changed in years.
    >>  ............................................
    pt  O centeio do padeiro. É a resposta e não muda há anos.
    >>  ............................................
  upbeat.dialogue.conversations.food.first/1
    en  Fresh bread, still warm, with far too much butter. Simple things done well — that's my whole philosophy, really.
    >>  ............................................
    pt  Pão fresco, ainda quente, com manteiga demais. Coisas simples bem feitas — é essa a minha filosofia inteira, na verdade.
    >>  ............................................
  upbeat.dialogue.conversations.food.first/2
    en  Anything shared at a full table. The food matters less than the company, though the baker's rye helps.
    >>  ............................................
    pt  Qualquer coisa dividida numa mesa cheia. A comida importa menos que a companhia, embora o centeio do padeiro ajude.
    >>  ............................................
  upbeat.dialogue.conversations.food.first/3
    en  The baker's rye. Everything else is just food that isn't the baker's rye.
    >>  ............................................
    pt  O centeio do padeiro. Todo o resto é só comida que não é o centeio do padeiro.
    >>  ............................................
  witty.dialogue.conversations.food.first/1
    en  Fresh bread, still warm, with far too much butter. Simple things done well — that's my whole philosophy, really.
    >>  ............................................
    pt  Pão fresco, ainda quente, com manteiga demais. Coisas simples bem feitas — é essa a minha filosofia inteira, na verdade.
    >>  ............................................
  witty.dialogue.conversations.food.first/2
    en  Anything shared at a full table. The food matters less than the company, though the baker's rye helps.
    >>  ............................................
    pt  Qualquer coisa dividida numa mesa cheia. A comida importa menos que a companhia, embora o centeio do padeiro ajude.
    >>  ............................................
  witty.dialogue.conversations.food.first/3
    en  The baker's rye. Everything else is just food that isn't the baker's rye.
    >>  ............................................
    pt  O centeio do padeiro. Todo o resto é só comida que não é o centeio do padeiro.
    >>  ............................................
```

</details>


**Outcome 14 of 14** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.topic.food` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.food` (this player only) for 24000 ticks
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.food.first
WHO    VILLAGER — what the player reads after pressing "What's good to eat around here?"
       spoken on: conversations.cat.chitchat, button `food`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `food.first.terminal`: the villager accepts. Subject `food.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.chitchat` / button `food`** earlier in this file. Fill it in there, once.


### Button `weather` — "What do you make of this weather?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `chitchat.weather` — accepted phrasings: "what is the weather like"; "how is the weather"; "nice weather"; "is it going to rain"
  - the message must contain one of: `weather`, `rain`, `storm`, `sunny`, `snow`, `sky`
  - scored words: `weather`(1.5), `rain`(1.2), `storm`(1.2), `sunny`(1.0), `snow`(1.0), `sky`(0.8), `cold`(0.6), `hot`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.weather
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.weather   [33 chars]
    en  What do you make of this weather?
    >>  ............................................
    pt  O que você acha desse tempo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 7** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.weather.working_in_the_wet"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.weather.working_in_the_wet", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 36000 ticks
- Then opens: `conversations.scene.weather.working_in_the_wet.respond`
- …where the player's next choices will be: "What does it stop you doing?" | "It's been a grim few days." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.scene.weather.working_in_the_wet.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.working_in_the_wet.open`: the villager complains. Subject `weather.working_in_it`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet/1   [79 chars]
    en  Everything takes twice as long in this and I have stopped pretending otherwise.
    >>  ............................................
    pt  Tudo leva o dobro do tempo assim e eu parei de fingir o contrário.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet/2   [80 chars]
    en  It is the third day of it. The first day is weather and the third day is a mood.
    >>  ............................................
    pt  É o terceiro dia disso. O primeiro dia é clima e o terceiro é humor.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet/3   [101 chars]
    en  I do not mind rain. I mind rain on the days I had planned something, which is apparently all of them.
    >>  ............................................
    pt  Não me incomodo com chuva. Me incomodo com chuva nos dias em que eu tinha planejado algo, o que aparentemente é todos.
    >>  ............................................
```


**Outcome 2 of 7** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.weather.the_long_dry"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.weather.the_long_dry", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 36000 ticks
- Then opens: `conversations.scene.weather.the_long_dry.respond`
- …where the player's next choices will be: "Is the well dropping?" | "Take the good days while they're here." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.scene.weather.the_long_dry.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.the_long_dry.open`: the villager reports. Subject `weather.the_dry_spell`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.the_long_dry/1   [87 chars]
    en  Lovely, and everybody says so, and the people who watch water have gone quiet about it.
    >>  ............................................
    pt  Lindo, e todo mundo diz isso, e quem observa a água ficou calado a respeito.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry/2   [118 chars]
    en  Four clear days. On the fourth clear day the well drops a hand's width and nobody notices but the person who draws it.
    >>  ............................................
    pt  Quatro dias limpos. No quarto dia limpo o poço baixa um palmo e ninguém repara, só quem tira a água.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry/3   [98 chars]
    en  I like it and I am counting. Those two things sit together perfectly well once you are old enough.
    >>  ............................................
    pt  Eu gosto e estou contando. As duas coisas convivem perfeitamente bem depois de certa idade.
    >>  ............................................
```


**Outcome 3 of 7** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `toddler` budget `quick`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 6000 ticks
- Then opens: `conversations.topic.weather.toddler.respond`
- …where the player's next choices will be: "You're right — I can see it too." | "What's your favourite kind of sky?" | "It's just clouds." | "Go on, before it rains."

```text
POOL   dialogue key: dialogue.conversations.weather.toddler
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.topic.weather.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.toddler.to.weather.toddler`: the villager accepts. Subject `weather.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.toddler/1   [47 chars]
    en  Sky water! I like jumping in the puddles after.
    >>  ............................................
    pt  Água do céu! Gosto de pular nas poças depois.
    >>  ............................................
  dialogue.conversations.weather.toddler/2   [48 chars]
    en  The clouds look like sheep today. Big sky sheep.
    >>  ............................................
    pt  As nuvens tão parecendo ovelha hoje. Ovelha grande do céu.
    >>  ............................................
  dialogue.conversations.weather.toddler/3   [52 chars]
    en  Is it gonna thunder? I'm not scared. Maybe a little.
    >>  ............................................
    pt  Vai trovejar? Eu não tenho medo. Só um pouquinho.
    >>  ............................................
```


**Outcome 4 of 7** — base weight `0`

- Fires when: weighted +100 when the sky is `storm`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `storm` budget `quick`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 6000 ticks
- Then opens: `conversations.topic.weather.storm.respond`
- …where the player's next choices will be: "Are you alright out in this?" | "Anything need bringing in?" | "It's only a bit of thunder." | "Get inside. I'll go."

```text
POOL   dialogue key: dialogue.conversations.weather.storm
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.topic.weather.storm.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.to.weather.storm`: the villager accepts. Subject `weather.storm`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.storm/1   [96 chars]
    en  Wild sky today. I've got the shutters latched and a candle ready. Best stay in if you can, %1$s.
    >>  ............................................
    pt  Céu bravo hoje. Já tranquei as venezianas e deixei uma vela pronta. Melhor ficar dentro se puder, %1$s.
    >>  ............................................
  dialogue.conversations.weather.storm/2   [93 chars]
    en  Thunder like that, the animals won't settle and neither will I. Mind the lightning out there.
    >>  ............................................
    pt  Com trovão desses, os bichos não sossegam e eu também não. Cuidado com o raio aí fora.
    >>  ............................................
  dialogue.conversations.weather.storm/3   [106 chars]
    en  A proper storm. My gran said these washed the year's bad luck off the roof. I'll believe it if it lets up.
    >>  ............................................
    pt  Uma tempestade de verdade. Minha avó dizia que elas lavavam o azar do ano do telhado. Vou acreditar se ela passar.
    >>  ............................................
```


**Outcome 5 of 7** — base weight `0`

- Fires when: weighted +100 when the sky is `rain`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `rain` budget `quick`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 6000 ticks
- Then opens: `conversations.topic.weather.mild.respond`
- …where the player's next choices will be: "It's a good one, isn't it." | "Is it any use to the fields?" | "I could do with less of it, personally." | "I'll make the most of it. Bye."

```text
POOL   dialogue key: dialogue.conversations.weather.rain
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.topic.weather.mild.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.rain.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.rain/1   [76 chars]
    en  This rain? The crops love it even if my boots don't. We needed it, honestly.
    >>  ............................................
    pt  Essa chuva? A plantação adora, mesmo que minhas botas não. A gente precisava, sinceramente.
    >>  ............................................
  dialogue.conversations.weather.rain/2   [91 chars]
    en  Grey and drizzly. Good sleeping weather, bad laundry weather. I know which I'd rather have.
    >>  ............................................
    pt  Cinzento e garoando. Bom tempo pra dormir, péssimo pra estender roupa. Sei qual eu prefiro.
    >>  ............................................
  dialogue.conversations.weather.rain/3   [96 chars]
    en  Rain again. The well's happy, the road's mud, and the frogs are showing off. Balance, I suppose.
    >>  ............................................
    pt  Chuva de novo. O poço agradece, a estrada virou lama e os sapos estão se exibindo. Equilíbrio, eu suponho.
    >>  ............................................
```


**Outcome 6 of 7** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the sky is `storm`  _(chance -2000)_
- Fires when: RULED OUT when the sky is `rain`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `weather` branch `clear` budget `quick`
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 6000 ticks
- Then opens: `conversations.topic.weather.mild.respond`
- …where the player's next choices will be: "It's a good one, isn't it." | "Is it any use to the fields?" | "I could do with less of it, personally." | "I'll make the most of it. Bye."

```text
POOL   dialogue key: dialogue.conversations.weather.clear
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.topic.weather.mild.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.clear.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.clear/1   [71 chars]
    en  Not a cloud up there. Days like this you forgive the place its winters.
    >>  ............................................
    pt  Nem uma nuvem lá em cima. Em dias assim você perdoa os invernos daqui.
    >>  ............................................
  dialogue.conversations.weather.clear/2   [93 chars]
    en  Clear skies and warm sun — I'd get twice the work done if I didn't keep stopping to enjoy it.
    >>  ............................................
    pt  Céu limpo e sol quente — eu renderia o dobro se não ficasse parando pra aproveitar.
    >>  ............................................
  dialogue.conversations.weather.clear/3   [92 chars]
    en  Good clean sky today. Makes even the cracked bell and the muddy square look almost handsome.
    >>  ............................................
    pt  Céu bem limpo hoje. Faz até o sino rachado e a praça enlameada parecerem quase bonitos.
    >>  ............................................
```


**Outcome 7 of 7** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Does: remembers `mcaconversations.cooldown.weather` (this player only) for 6000 ticks
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.clear
WHO    VILLAGER — what the player reads after pressing "What do you make of this weather?"
       spoken on: conversations.cat.chitchat, button `weather`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.clear.terminal`: the villager accepts. Subject `weather.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.chitchat` / button `weather`** earlier in this file. Fill it in there, once.


### Button `season` — "How's the season treating you?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `chitchat.season` — accepted phrasings: "what season is it"; "time of year"
  - the message must contain one of: `season`, `spring`, `summer`, `autumn`, `winter`, `holiday`, `festival`
  - scored words: `season`(1.5), `spring`(1.2), `summer`(1.2), `autumn`(1.2), `winter`(1.2), `holiday`(1.0), `festival`(1.0)

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.season
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.season   [30 chars]
    en  How's the season treating you?
    >>  ............................................
    pt  Como a estação está te tratando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 14** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.season.the_turn"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.season.the_turn", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 36000 ticks
- Then opens: `conversations.scene.season.the_turn.respond`
- …where the player's next choices will be: "How does it compare to last year?" | "The light is lovely just now." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.scene.season.the_turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.the_turn.open`: the villager reports. Subject `season.autumn`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.the_turn/1   [124 chars]
    en  Early. Everything is a week ahead of last year and I have written it down, because nobody ever remembers what last year did.
    >>  ............................................
    pt  Cedo. Tudo está uma semana adiantado em relação ao ano passado e eu anotei, porque ninguém lembra o que o ano passado fez.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn/2   [98 chars]
    en  It turned in a single night. One evening it was the end of summer and the next morning it was not.
    >>  ............................................
    pt  Virou numa noite só. Numa tarde era o fim do verão e na manhã seguinte não era mais.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn/3   [120 chars]
    en  I like it and I do not trust it. Autumn is the season that decides how the winter goes and it never tells you which way.
    >>  ............................................
    pt  Eu gosto e não confio. O outono é a estação que decide como o inverno vai ser, e nunca avisa para que lado.
    >>  ............................................
```


**Outcome 2 of 14** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.season.deep_winter"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.season.deep_winter", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 36000 ticks
- Then opens: `conversations.scene.season.deep_winter.respond`
- …where the player's next choices will be: "Will the stores hold?" | "Tell me if anybody runs short." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.scene.season.deep_winter.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.deep_winter.open`: the villager reports. Subject `season.winter`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.deep_winter/1   [96 chars]
    en  Halfway. The second half is always shorter than it feels and I say that to myself most mornings.
    >>  ............................................
    pt  Metade. A segunda metade é sempre mais curta do que parece, e eu digo isso a mim mesma quase toda manhã.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter/2   [107 chars]
    en  Cold and steady, which is the good kind. It is the mild winters with three thaws in them that break things.
    >>  ............................................
    pt  Frio e constante, que é o tipo bom. São os invernos amenos com três degelos que estragam as coisas.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter/3   [135 chars]
    en  We are eating what we put by in the autumn, and the autumn version of me did a reasonable job, so I am not going to complain about her.
    >>  ............................................
    pt  Estamos comendo o que guardamos no outono, e a eu do outono fez um trabalho razoável, então não vou reclamar dela.
    >>  ............................................
```


**Outcome 3 of 14** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.season` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `again` budget `quick`
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.again
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.again.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.again/1   [68 chars]
    en  We did the seasons already. The wheel hasn't turned since breakfast.
    >>  ............................................
    pt  A gente já falou das estações. A roda não virou desde o café da manhã.
    >>  ............................................
  dialogue.conversations.season.again/2   [76 chars]
    en  Same time of year as when you last asked, %1$s. It moves slower than you do.
    >>  ............................................
    pt  Mesma época do ano de quando você perguntou, %1$s. Ela anda mais devagar que você.
    >>  ............................................
  dialogue.conversations.season.again/3   [65 chars]
    en  Ask me again at the turn and I'll have something new. Not before.
    >>  ............................................
    pt  Me pergunte de novo na virada e eu terei algo novo. Antes disso, não.
    >>  ............................................
```


**Outcome 4 of 14** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `toddler` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.toddler.respond`
- …where the player's next choices will be: "It's the best one, you're right." | "What do you like about it?" | "They're all much the same." | "Off you go."

```text
POOL   dialogue key: dialogue.conversations.season.toddler
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.toddler.to.season.toddler`: the villager accepts. Subject `season.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.toddler/1   [57 chars]
    en  I like the season where the flowers come. Is it that one?
    >>  ............................................
    pt  Eu gosto da estação em que vêm as flores. É essa?
    >>  ............................................
  dialogue.conversations.season.toddler/2   [33 chars]
    en  Leaves! There are SO many leaves.
    >>  ............................................
    pt  Folha! Tem MUITA folha.
    >>  ............................................
  dialogue.conversations.season.toddler/3   [42 chars]
    en  Every day is puddle season if you believe.
    >>  ............................................
    pt  Todo dia é estação de poça se você acreditar.
    >>  ............................................
```


**Outcome 5 of 14** — base weight `0`

- Fires when: weighted +100 when the festival is `spring_bloom`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `spring_bloom` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.holiday.respond`
- …where the player's next choices will be: "What do you do for it?" | "Save me a place." | "I can't make it, but enjoy it." | "I'll let you get to it."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.spring_bloom
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.holiday.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.spring_bloom.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.spring_bloom/1   [113 chars]
    en  You picked the spring bloom to visit! The whole village is out with flowers in their hair. Even I've got a sprig.
    >>  ............................................
    pt  Você escolheu o florescer da primavera pra visitar! O vilarejo inteiro está na rua com flor no cabelo. Até eu estou com um raminho.
    >>  ............................................
  dialogue.conversations.season.holiday.spring_bloom/2   [101 chars]
    en  It's the bloom, %1$s — first flowers of the year. We hang garlands and pretend winter never happened.
    >>  ............................................
    pt  É o florescer, %1$s — as primeiras flores do ano. A gente pendura guirlanda e finge que o inverno nunca existiu.
    >>  ............................................
  dialogue.conversations.season.holiday.spring_bloom/3   [99 chars]
    en  Spring bloom today! The children have been weaving crowns since dawn. Try one on, you've earned it.
    >>  ............................................
    pt  Florescer da primavera hoje! As crianças estão trançando coroas desde o amanhecer. Experimenta uma, você merece.
    >>  ............................................
```


**Outcome 6 of 14** — base weight `0`

- Fires when: weighted +100 when the festival is `midsummer`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `midsummer` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.holiday.respond`
- …where the player's next choices will be: "What do you do for it?" | "Save me a place." | "I can't make it, but enjoy it." | "I'll let you get to it."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.midsummer
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.holiday.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.midsummer.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.midsummer/1   [103 chars]
    en  Midsummer! Longest day of the year — there'll be a bonfire in the square tonight, and I'll not miss it.
    >>  ............................................
    pt  Solstício de verão! O dia mais longo do ano — vai ter fogueira na praça hoje à noite, e eu não vou perder.
    >>  ............................................
  dialogue.conversations.season.holiday.midsummer/2   [106 chars]
    en  It's the midsummer feast, %1$s. Dance till the short dark and sleep it off tomorrow. That's the tradition.
    >>  ............................................
    pt  É a festa do solstício, %1$s. Dançar até o breve escuro e dormir tudo amanhã. É essa a tradição.
    >>  ............................................
  dialogue.conversations.season.holiday.midsummer/3   [106 chars]
    en  Feel that sun? Midsummer's come round again. We light the fires at dusk to keep the light a little longer.
    >>  ............................................
    pt  Sentiu esse sol? O solstício de verão voltou. A gente acende as fogueiras ao anoitecer pra segurar a luz mais um pouco.
    >>  ............................................
```


**Outcome 7 of 14** — base weight `0`

- Fires when: weighted +100 when the festival is `harvest_festival`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `harvest_festival` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.holiday.respond`
- …where the player's next choices will be: "What do you do for it?" | "Save me a place." | "I can't make it, but enjoy it." | "I'll let you get to it."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.harvest_festival
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.holiday.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.harvest_festival.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.harvest_festival/1   [104 chars]
    en  Harvest festival! Barns full, tables fuller. Sit with us tonight, %1$s — nobody eats alone this evening.
    >>  ............................................
    pt  Festa da colheita! Celeiro cheio, mesa mais cheia ainda. Senta com a gente hoje, %1$s — ninguém come sozinho essa noite.
    >>  ............................................
  dialogue.conversations.season.holiday.harvest_festival/2   [112 chars]
    en  It's harvest home. A year's work's in the store at last, and we thank the fields the only way we know — a feast.
    >>  ............................................
    pt  É o encerramento da colheita. O trabalho de um ano enfim guardado, e a gente agradece aos campos do único jeito que sabe — um banquete.
    >>  ............................................
  dialogue.conversations.season.holiday.harvest_festival/3   [115 chars]
    en  Best day of the autumn, this. The harvest's in, the ale's out, and even I'll crack a smile before the night's done.
    >>  ............................................
    pt  O melhor dia do outono, esse. A colheita recolhida, a cerveja servida, e até eu abro um sorriso antes da noite acabar.
    >>  ............................................
```


**Outcome 8 of 14** — base weight `0`

- Fires when: weighted +100 when the festival is `midwinter`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `midwinter` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.holiday.respond`
- …where the player's next choices will be: "What do you do for it?" | "Save me a place." | "I can't make it, but enjoy it." | "I'll let you get to it."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.midwinter
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.holiday.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.midwinter.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.midwinter/1   [104 chars]
    en  Midwinter's night — the year's darkest. We light every candle we've got and dare the dark to outlast us.
    >>  ............................................
    pt  Noite do solstício de inverno — a mais escura do ano. Acendemos toda vela que temos e desafiamos o escuro a durar mais que a gente.
    >>  ............................................
  dialogue.conversations.season.holiday.midwinter/2   [112 chars]
    en  It's midwinter, %1$s. Cold and long, but the whole village gathers close and shares what little warmth there is.
    >>  ............................................
    pt  É o solstício de inverno, %1$s. Frio e longo, mas o vilarejo inteiro se junta e divide o pouco calor que tem.
    >>  ............................................
  dialogue.conversations.season.holiday.midwinter/3   [111 chars]
    en  Deep midwinter. We tell the old stories tonight and toast to the light coming back. It always does, in the end.
    >>  ............................................
    pt  Pleno solstício de inverno. Hoje a gente conta as histórias antigas e brinda ao retorno da luz. Ela sempre volta, no fim.
    >>  ............................................
```


**Outcome 9 of 14** — base weight `0`

- Fires when: weighted +100 when the season is `spring`
- Fires when: RULED OUT when the festival is `spring_bloom`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midsummer`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midwinter`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `spring` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.spring
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.spring.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.spring/1   [103 chars]
    en  Spring suits me. Everything's waking up — green on the fields, lambs in the pen, mud on every doorstep.
    >>  ............................................
    pt  A primavera combina comigo. Está tudo acordando — verde nos campos, cordeiros no cercado, lama em toda soleira.
    >>  ............................................
  dialogue.conversations.season.spring/2   [93 chars]
    en  You can smell the thaw, %1$s. I've seeds to get in the ground and I'll not waste a day of it.
    >>  ............................................
    pt  Dá pra sentir o cheiro do degelo, %1$s. Tenho semente pra botar no chão e não vou desperdiçar um dia.
    >>  ............................................
  dialogue.conversations.season.spring/3   [77 chars]
    en  New season, new start. The birds are back and so's my good mood, near enough.
    >>  ............................................
    pt  Estação nova, começo novo. Os pássaros voltaram e o meu bom humor também, quase.
    >>  ............................................
```


**Outcome 10 of 14** — base weight `0`

- Fires when: weighted +100 when the season is `summer`
- Fires when: RULED OUT when the festival is `spring_bloom`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midsummer`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midwinter`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `summer` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.summer
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.summer.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.summer/1   [105 chars]
    en  High summer — long days, short tempers by the forge, and cold well-water that's worth its weight in gold.
    >>  ............................................
    pt  Auge do verão — dias longos, pavios curtos perto da forja, e água gelada de poço que vale ouro.
    >>  ............................................
  dialogue.conversations.season.summer/2   [85 chars]
    en  Warm enough to work till the light's gone. I'll be glad of the shade come noon, mind.
    >>  ............................................
    pt  Quente o bastante pra trabalhar até a luz acabar. Mas vou agradecer a sombra lá pelo meio-dia.
    >>  ............................................
  dialogue.conversations.season.summer/3   [108 chars]
    en  The summer's good to us, %1$s. Crops standing tall and the market full. Make the most of it before the turn.
    >>  ............................................
    pt  O verão é bom pra gente, %1$s. Plantação em pé e feira cheia. Aproveita antes da virada.
    >>  ............................................
```


**Outcome 11 of 14** — base weight `0`

- Fires when: weighted +100 when the season is `autumn`
- Fires when: RULED OUT when the festival is `spring_bloom`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midsummer`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midwinter`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `autumn` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.autumn
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.autumn.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.autumn/1   [96 chars]
    en  Autumn already. The leaves are going gold and I've the harvest and the woodpile both on my mind.
    >>  ............................................
    pt  Outono já. As folhas estão dourando e eu tenho a colheita e a lenha na cabeça ao mesmo tempo.
    >>  ............................................
  dialogue.conversations.season.autumn/2   [93 chars]
    en  There's a bite in the mornings now. Time to lay stores by before winter comes knocking, %1$s.
    >>  ............................................
    pt  As manhãs já mordem. Hora de guardar mantimento antes que o inverno bata na porta, %1$s.
    >>  ............................................
  dialogue.conversations.season.autumn/3   [92 chars]
    en  I love the fall, honestly. Everything ripe at once, then the quiet after. Bittersweet, that.
    >>  ............................................
    pt  Eu amo o outono, sinceramente. Tudo maduro de uma vez, e depois o silêncio. Agridoce, isso.
    >>  ............................................
```


**Outcome 12 of 14** — base weight `0`

- Fires when: weighted +100 when the season is `winter`
- Fires when: RULED OUT when the festival is `spring_bloom`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midsummer`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midwinter`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `winter` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.winter
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.winter.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.winter/1   [102 chars]
    en  Winter's here, and it doesn't ask permission. I keep the fire fed and my complaints to myself. Mostly.
    >>  ............................................
    pt  O inverno chegou, e ele não pede licença. Mantenho o fogo alimentado e as reclamações pra mim. Quase todas.
    >>  ............................................
  dialogue.conversations.season.winter/2   [87 chars]
    en  Cold enough to crack stone, %1$s. Wrap up out there — the road ices over past the well.
    >>  ............................................
    pt  Frio de rachar pedra, %1$s. Se agasalha aí fora — a estrada congela depois do poço.
    >>  ............................................
  dialogue.conversations.season.winter/3   [95 chars]
    en  We hunker down this time of year. Stew on the pot, snow on the sill, and a long wait for green.
    >>  ............................................
    pt  A gente se encolhe nessa época. Ensopado na panela, neve no peitoril, e uma longa espera pelo verde.
    >>  ............................................
```


**Outcome 13 of 14** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when the season is `spring`  _(chance -2000)_
- Fires when: RULED OUT when the season is `summer`  _(chance -2000)_
- Fires when: RULED OUT when the season is `autumn`  _(chance -2000)_
- Fires when: RULED OUT when the season is `winter`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `spring_bloom`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midsummer`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `harvest_festival`  _(chance -2000)_
- Fires when: RULED OUT when the festival is `midwinter`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.season` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `season` branch `any` budget `quick`
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.topic.season.turn.respond`
- …where the player's next choices will be: "It suits you, this time of year." | "Which season's yours?" | "I'll be glad when it's over." | "I'll get on before the light goes."

```text
POOL   dialogue key: dialogue.conversations.season.any
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.any.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.any/1   [95 chars]
    en  Season's turning like it always does. I just keep pace with it and try not to grumble too loud.
    >>  ............................................
    pt  A estação vira como sempre vira. Eu só acompanho o passo e tento não resmungar alto demais.
    >>  ............................................
  dialogue.conversations.season.any/2   [93 chars]
    en  Oh, the year rolls on, %1$s. Some days I mark it, most days the work does the marking for me.
    >>  ............................................
    pt  Ah, o ano segue rolando, %1$s. Tem dia que eu marco, mas quase sempre o trabalho marca por mim.
    >>  ............................................
  dialogue.conversations.season.any/3   [94 chars]
    en  Same wheel it always is — sun, rain, harvest, frost. Comforting to know what's coming, I find.
    >>  ............................................
    pt  Mesma roda de sempre — sol, chuva, colheita, geada. Reconforta saber o que vem, eu acho.
    >>  ............................................
```


**Outcome 14 of 14** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 2
- Does: remembers `mcaconversations.cooldown.season` (this player only) for 48000 ticks
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.any
WHO    VILLAGER — what the player reads after pressing "How's the season treating you?"
       spoken on: conversations.cat.chitchat, button `season`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.any.terminal`: the villager accepts. Subject `season.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.chitchat` / button `season`** earlier in this file. Fill it in there, once.


### Button `routine` — "How do your days go?"

Shown only when MCA's own constraints hold: `"!toddler,!child"`

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.routine
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.routine   [20 chars]
    en  How do your days go?
    >>  ............................................
    pt  Como são seus dias?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.routine.the_first_hour"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.routine.the_first_hour", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `routine` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.routine` (this player only) for 36000 ticks
- Then opens: `conversations.scene.routine.the_first_hour.respond`
- …where the player's next choices will be: "What's the plan for today?" | "May it go easily." | "Sounds a full day."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.the_first_hour
WHO    VILLAGER — what the player reads after pressing "How do your days go?"
       spoken on: conversations.cat.chitchat, button `routine`
       leaves the player on: conversations.scene.routine.the_first_hour.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.the_first_hour.open`: the villager reports. Subject `routine.first_hour`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.the_first_hour/1   [98 chars]
    en  You have caught the good hour. Everything I decide before the sun is properly up turns out better.
    >>  ............................................
    pt  Você pegou a hora boa. Tudo que eu decido antes de o sol subir direito sai melhor.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour/2   [90 chars]
    en  This is the hour where the day is still theoretical and I am briefly an optimist about it.
    >>  ............................................
    pt  Esta é a hora em que o dia ainda é teórico e eu sou brevemente otimista sobre ele.
    >>  ............................................
  dialogue.conversations.scene.routine.the_first_hour/3   [90 chars]
    en  I do the thinking now and the doing later. Reverse that and the day goes sideways by noon.
    >>  ............................................
    pt  Penso agora e faço depois. Inverta isso e o dia desanda até o meio-dia.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.routine.after_the_work"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.routine.after_the_work", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `routine` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.routine` (this player only) for 36000 ticks
- Then opens: `conversations.scene.routine.after_the_work.respond`
- …where the player's next choices will be: "Was it a good one?" | "The tidying will keep." | "Sounds a full day."

```text
POOL   dialogue key: dialogue.conversations.scene.routine.after_the_work
WHO    VILLAGER — what the player reads after pressing "How do your days go?"
       spoken on: conversations.cat.chitchat, button `routine`
       leaves the player on: conversations.scene.routine.after_the_work.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.after_the_work.open`: the villager reports. Subject `routine.evening_remainder`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.routine.after_the_work/1   [85 chars]
    en  The work is finished and I have not yet decided whether the day counts as a good one.
    >>  ............................................
    pt  O trabalho acabou e eu ainda não decidi se o dia conta como bom.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work/2   [92 chars]
    en  This is the hour where I go over everything I said and find two sentences I would take back.
    >>  ............................................
    pt  Esta é a hora em que eu repasso tudo que disse e encontro duas frases que eu retiraria.
    >>  ............................................
  dialogue.conversations.scene.routine.after_the_work/3   [85 chars]
    en  The useful part is done. What is left is the part where I pretend I am going to tidy.
    >>  ............................................
    pt  A parte útil acabou. O que sobra é a parte em que finjo que vou arrumar.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.routine.the_first_hour"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.routine.after_the_work"}  _(chance -5000)_
- Does: session `begin` topic `routine` branch `funnel` budget `quick`
- Then opens: `conversations.topic.routine.open.respond`
- …where the player's next choices will be: "Which part is hardest?" | "A steady day is worth having." | "Sounds the same as everyone's." | "Sounds a full day."

```text
POOL   dialogue key: dialogue.conversations.routine.open
WHO    VILLAGER — what the player reads after pressing "How do your days go?"
       spoken on: conversations.cat.chitchat, button `routine`
       leaves the player on: conversations.topic.routine.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.open`: the villager reports. Subject `routine.shape_of_the_day`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:routine` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.routine.open/1   [107 chars]
    en  Three parts. The part that has to happen at dawn, the part that can slide, and the part I keep putting off.
    >>  ............................................
    pt  Três partes. A que precisa ser ao amanhecer, a que pode escorregar e a que eu vivo adiando.
    >>  ............................................
  dialogue.conversations.routine.open/2   [96 chars]
    en  It runs itself by now. I could do the first two hours asleep and some mornings I suspect I have.
    >>  ............................................
    pt  Já corre sozinho. Eu faria as duas primeiras horas dormindo e em algumas manhãs desconfio que fiz.
    >>  ............................................
  dialogue.conversations.routine.open/3   [108 chars]
    en  Busy until the middle of the day, then it goes quiet, and the quiet half is where the actual work gets done.
    >>  ............................................
    pt  Corrido até o meio do dia, depois fica calmo, e a metade calma é onde o trabalho de verdade acontece.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.routine.legacy
WHO    VILLAGER — what the player reads after pressing "How do your days go?"
       spoken on: conversations.cat.chitchat, button `routine`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `routine.legacy`: the villager reports. Subject `routine.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.routine.legacy/1   [74 chars]
    en  Up early, work, eat, sleep. It is not a thrilling account and it suits me.
    >>  ............................................
    pt  Levantar cedo, trabalhar, comer, dormir. Não é um relato empolgante e me serve.
    >>  ............................................
  dialogue.conversations.routine.legacy/2   [80 chars]
    en  Much the same every day, which people say as a complaint and I say as a comfort.
    >>  ............................................
    pt  Quase igual todo dia, o que as pessoas dizem como reclamação e eu digo como conforto.
    >>  ............................................
  dialogue.conversations.routine.legacy/3   [79 chars]
    en  The morning is busy and the afternoon is fiddly. That is about the whole of it.
    >>  ............................................
    pt  A manhã é corrida e a tarde é minuciosa. É basicamente tudo.
    >>  ............................................
```


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.chitchat.back   [15 chars]
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


## `conversations.cat.events`

**Reached from 146 route(s):** `conversations.arc.news.resume.followup` / `thank_you_for_telling`; `conversations.arc.news.resume.followup` / `leave_it_with_you`; `conversations.arc.news.resume.followup` / `leave`; `conversations.arc.news.resume.respond` / `leave`; `conversations.arc.noticed.resume.followup` / `thank_you_for_telling`; `conversations.arc.noticed.resume.followup` / `leave_it_with_you`; `conversations.arc.noticed.resume.followup` / `leave`; `conversations.arc.noticed.resume.respond` / `leave`; `conversations.cat.events` / `news`; `conversations.cat.events` / `noticed`; `conversations.cat.events` / `noticed`; `conversations.cat.events` / `noticed` …and 134 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.gossip.none` — e.g. "Quiet week, honestly. The most exciting thing was a chicken on the chapel roof."
- `conversations.news.callous.apologize` — e.g. "...It was close to it. Go on. I'd rather not look at you just now."
- `conversations.news.deflated.apologize` — e.g. "Thank you. It'll be over soon enough without help."
- `conversations.news.deflated.let_it_be` — e.g. "Good."
- `conversations.news.followup.ask_how_they_are` — e.g. "...Me? I'm — thank you. I'm alright. And you? You get to answer that too, %1$s."
- `conversations.news.followup.keep_quiet` — e.g. "...Thank you. That's worth more than you know in a place this size."
- `conversations.news.followup.leave` — e.g. "True enough. Enough of other people's business."
- `conversations.news.followup.spread` — e.g. "...Should they. It isn't ours to hand round, %1$s."
- `conversations.news.glad.celebrate_too` — e.g. "It is. We're owed one and I intend to spend it badly."
- `conversations.news.glad.leave` — e.g. "Aye! Go and hear the rest from someone livelier."
- `conversations.news.glad.pass_it_on` — e.g. "Do. This is the one kind of news that improves in the telling."
- `conversations.news.grave.offer_help` — e.g. "...Firewood, and nobody to fetch it. That's the honest answer."
- `conversations.news.helped.ask_who` — e.g. "Nobody will say outright. That's how you know it was done properly — quietly."
- `conversations.news.helped.glad` — e.g. "It is. We're not much of a village if we only notice each other when something goes wrong."
- …and 115 more pools


```text
POOL   dialogue key: dialogue.conversations.cat.events
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.events
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.events   [45 chars]
    en  News travels fast around here. What about it?
    >>  ............................................
    pt  Notícia corre rápido por aqui. O que tem?
    >>  ............................................
```


### Button `news` — "Anything happen around here lately?"

Shown only when MCA's own constraints hold: `"!toddler,!baby"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `events.news` — accepted phrasings: "any news"; "what is new"; "anything happening"; "what is going on"
  - the message must contain one of: `news`, `happening`, `latest`, `update`
  - scored words: `news`(1.5), `happening`(1.0), `latest`(1.0), `update`(0.8), `anything`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.events.news
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.events.news   [35 chars]
    en  Anything happen around here lately?
    >>  ............................................
    pt  Aconteceu alguma coisa por aqui ultimamente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.news.while_you_were_away"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.news.while_you_were_away", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.news` (this player only) for 36000 ticks
- Then opens: `conversations.scene.news.while_you_were_away.respond`
- …where the player's next choices will be: "Start with the two worth telling." | "I've been away too long." | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.news.while_you_were_away
WHO    VILLAGER — what the player reads after pressing "Anything happen around here lately?"
       spoken on: conversations.cat.events, button `news`
       leaves the player on: conversations.scene.news.while_you_were_away.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.while_you_were_away.open`: the villager reports. Subject `news.absence`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.while_you_were_away/1   [83 chars]
    en  A fair bit, and none of it enormous, which is the best kind of week to have missed.
    >>  ............................................
    pt  Bastante coisa, e nada enorme, que é o melhor tipo de semana para se ter perdido.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away/2   [109 chars]
    en  You have been gone long enough that I have to think about where to start, and that is itself a piece of news.
    >>  ............................................
    pt  Você ficou fora tempo suficiente para eu ter que pensar por onde começar, e isso já é uma notícia.
    >>  ............................................
  dialogue.conversations.scene.news.while_you_were_away/3   [103 chars]
    en  Two things worth telling and about nine that are only worth telling if you were here for the beginning.
    >>  ............................................
    pt  Duas coisas que valem contar e umas nove que só valem se você estava aqui no começo.
    >>  ............................................
```


**Outcome 2 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.news.quiet_week"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.news.quiet_week", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.news` (this player only) for 36000 ticks
- Then opens: `conversations.scene.news.quiet_week.respond`
- …where the player's next choices will be: "Tell me a small thing, then." | "A dull week is a good week." | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.news.quiet_week
WHO    VILLAGER — what the player reads after pressing "Anything happen around here lately?"
       spoken on: conversations.cat.events, button `news`
       leaves the player on: conversations.scene.news.quiet_week.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.quiet_week.open`: the villager reports. Subject `news.nothing_much`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:news` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.news.quiet_week/1   [91 chars]
    en  Since yesterday? A cat got into the granary and lost the argument. That is the whole of it.
    >>  ............................................
    pt  Desde ontem? Um gato entrou no celeiro e perdeu a discussão. É isso.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week/2   [96 chars]
    en  Nothing, and I want to be clear that nothing is a good report and I am delivering it cheerfully.
    >>  ............................................
    pt  Nada, e quero deixar claro que nada é um bom relatório e eu estou entregando com alegria.
    >>  ............................................
  dialogue.conversations.scene.news.quiet_week/3   [97 chars]
    en  You were here. You know as much as I do, which is a strange and pleasant thing to be able to say.
    >>  ............................................
    pt  Você estava aqui. Sabe tanto quanto eu, o que é uma coisa estranha e agradável de se poder dizer.
    >>  ............................................
```


**Outcome 3 of 11** — base weight `0`

- Fires when: weighted +200 when arc `news` is at stage 1..2
- Fires when: RULED OUT when arc `news` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.news` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.news` (this player only) for 36000 ticks
- Then opens: `conversations.arc.news.resume.respond`
- …where the player's next choices will be: "And how are they now?" | "Is there anything else they need?" | "I'm glad it helped." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.news.resume
WHO    VILLAGER — what the player reads after pressing "Anything happen around here lately?"
       spoken on: conversations.cat.events, button `news`
       leaves the player on: conversations.arc.news.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `news.resume.opener`: the villager reports. Subject `news.aftermath`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, practical_help
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.news.resume/1   [74 chars]
    en  The firewood arrived. I've not said who from and neither has anybody else.
    >>  ............................................
    pt  A lenha chegou. Não disse de quem e ninguém mais disse.
    >>  ............................................
  dialogue.conversations.news.resume/2   [89 chars]
    en  You asked after them and then you did something, which is a rarer order than you'd think.
    >>  ............................................
    pt  Você perguntou por eles e depois fez algo, uma ordem mais rara do que se imagina.
    >>  ............................................
  dialogue.conversations.news.resume/3   [78 chars]
    en  It went the way these things go. Slowly, and then all at once, and then quiet.
    >>  ............................................
    pt  Foi como essas coisas vão. Devagar, e depois de uma vez, e depois silêncio.
    >>  ............................................
```


**Outcome 4 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `young` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.young.<event type>`
- Then opens: `conversations.topic.news.young.respond`
- …where the player's next choices will be: "Thank you for telling me properly." | "How did you find that out?" | "You shouldn't repeat things." | "Off you go, then."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 5 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `teen` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.teen.<event type>`
- Then opens: `conversations.topic.news.teen.respond`
- …where the player's next choices will be: "Thank you for telling me straight." | "How much of that is certain?" | "You're too young to be repeating that." | "I'll let you get on."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 6 of 11** — base weight `0`

- Fires when: weighted +100 when an untold village event exists of type death/divorce
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `sad` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.topic.news.sad.respond`
- …where the player's next choices will be: "That's awful. How is everyone?" | "What happened, exactly?" | "Ha — serves them right." | "I'm sorry. I'll go."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 7 of 11** — base weight `0`

- Fires when: weighted +100 when an untold village event exists of type marriage/birth
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `glad` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.topic.news.glad.respond`
- …where the player's next choices will be: "That's wonderful news." | "Tell me the whole story." | "Won't last, these things." | "Good to hear. I'll get on."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 8 of 11** — base weight `0`

- Fires when: weighted +100 when an untold village event exists of type arrival/departure
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type marriage/birth  _(chance -2000)_
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `news` branch `mixed` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.topic.news.mixed.respond`
- …where the player's next choices will be: "What brought that on?" | "I hope it works out for them." | "There's more to that story." | "Interesting. I'll go."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 9 of 11** — base weight `0`

- Fires when: weighted +100 when an untold village event exists of type quest
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type marriage/birth  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type arrival/departure  _(chance -2000)_
- Fires when: RULED OUT when the `gossip` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Does: session `begin` topic `news` branch `helped` budget `standard`
- Does: tells the next untold village event, from the pool `dialogue.conversations.gossip.<event type>`
- Then opens: `conversations.topic.news.helped.respond`
- …where the player's next choices will be: "Who was it that helped?" | "Good. That's how a village should work." | "That was me, actually." | "Glad to hear it."
- The villager says **no line of its own** here — the destination node's own prompt is what the player reads.


**Outcome 10 of 11** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when an untold village event exists of type death/divorce  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type marriage/birth  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type arrival/departure  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when an untold village event exists of type quest  _(chance -2000)_
- Does: session `begin` topic `news` branch `none` budget `standard`
- Then opens: `conversations.topic.news.none.respond`
- …where the player's next choices will be: "Quiet suits a village." | "I've had a week of it myself." | "Another time, then."

```text
POOL   dialogue key: dialogue.conversations.gossip.none
WHO    VILLAGER — what the player reads after pressing "Anything happen around here lately?"
       spoken on: conversations.cat.events, button `news`
       leaves the player on: conversations.topic.news.none.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `gossip.none.to.news.none`: the villager accepts. Subject `news.none`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.gossip.none/1   [79 chars]
    en  Quiet week, honestly. The most exciting thing was a chicken on the chapel roof.
    >>  ............................................
    pt  Semana quieta, sinceramente. A coisa mais empolgante foi uma galinha no telhado da capela.
    >>  ............................................
  dialogue.conversations.gossip.none/2   [74 chars]
    en  Nothing new. Same faces, same fences. I'd trade an ear for some real news.
    >>  ............................................
    pt  Nada de novo. Mesmas caras, mesmas cercas. Eu trocaria uma orelha por notícia de verdade.
    >>  ............................................
  dialogue.conversations.gossip.none/3   [73 chars]
    en  All quiet. Even the baker's out of scandals, and that's saying something.
    >>  ............................................
    pt  Tudo quieto. Até o padeiro está sem escândalos, e isso já diz muita coisa.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.gossip.none/1
    en  Nothing, thankfully. No news is the best kind, in my experience. Long may it hold.
    >>  ............................................
    pt  Nada, graças a Deus. Nenhuma notícia é o melhor tipo, pela minha experiência. Que dure.
    >>  ............................................
  anxious.dialogue.conversations.gossip.none/2
    en  Nothing's happened. I keep checking, but genuinely — nothing. That's a relief.
    >>  ............................................
    pt  Não aconteceu nada. Eu fico conferindo, mas sinceramente — nada. Que alívio.
    >>  ............................................
  athletic.dialogue.conversations.gossip.none/1
    en  Nothing. Quietest week all season. I raced the miller's dog out of sheer boredom. Twice. Won once.
    >>  ............................................
    pt  Nada. Semana mais quieta da temporada. Apostei corrida com o cachorro do moleiro de puro tédio. Duas vezes. Ganhei uma.
    >>  ............................................
  athletic.dialogue.conversations.gossip.none/2
    en  No news. I'd have outrun it to you if there were, %1$s.
    >>  ............................................
    pt  Nenhuma notícia. Se tivesse, eu teria corrido pra te contar, %1$s.
    >>  ............................................
  confident.dialogue.conversations.gossip.none/1
    en  Nothing worth my attention this week. When something happens, I'll know it first. I always do.
    >>  ............................................
    pt  Nada digno da minha atenção essa semana. Quando algo acontecer, eu saberei primeiro. Sempre sei.
    >>  ............................................
  confident.dialogue.conversations.gossip.none/2
    en  Quiet week. Give it time — the interesting things happen where I'm standing.
    >>  ............................................
    pt  Semana quieta. Dê tempo — as coisas interessantes acontecem onde eu estou.
    >>  ............................................
  crabby.dialogue.conversations.gossip.none/1
    en  Nothing. And good. News in this village is never the sort you want.
    >>  ............................................
    pt  Nada. E ainda bem. Notícia nesse vilarejo nunca é do tipo que você quer.
    >>  ............................................
  crabby.dialogue.conversations.gossip.none/2
    en  Not a thing worth repeating. Quiet week, for once.
    >>  ............................................
    pt  Nem uma coisa que valha repetir. Semana quieta, pra variar.
    >>  ............................................
  extroverted.dialogue.conversations.gossip.none/1
    en  Nothing! Can you believe it? I've asked everyone. Dreadfully uneventful week.
    >>  ............................................
    pt  Nada! Dá pra acreditar? Eu perguntei pra todo mundo. Semana terrivelmente sem acontecimentos.
    >>  ............................................
  extroverted.dialogue.conversations.gossip.none/2
    en  Not a scrap of news, and believe me I've been looking. The village has gone boring on us.
    >>  ............................................
    pt  Nem um farelo de notícia, e olha que eu procurei. O vilarejo ficou sem graça com a gente.
    >>  ............................................
  flirty.dialogue.conversations.gossip.none/1
    en  No news, unless you count me being this charming with no audience.
    >>  ............................................
    pt  Nenhuma novidade, a menos que conte eu estar assim tão charmosa sem plateia.
    >>  ............................................
  flirty.dialogue.conversations.gossip.none/2
    en  Quiet week. Give me something to gossip ABOUT and I'll happily be indiscreet, %1$s.
    >>  ............................................
    pt  Semana quieta. Me dê algo SOBRE o que fofocar e eu serei indiscreta com prazer, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.gossip.none/1
    en  Quiet week! Everyone's fine, fed, and boring — which is the best news a village can have, honestly.
    >>  ............................................
    pt  Semana quieta! Todo mundo bem, alimentado e sem graça — que é a melhor notícia que um vilarejo pode ter, sinceramente.
    >>  ............................................
  friendly.dialogue.conversations.gossip.none/2
    en  Not a peep! Come back tomorrow — someone always does something worth telling.
    >>  ............................................
    pt  Nem um pio! Volte amanhã — sempre tem alguém fazendo algo que valha a pena contar.
    >>  ............................................
  gloomy.dialogue.conversations.gossip.none/1
    en  Nothing happens here. That's the whole tragedy of the place.
    >>  ............................................
    pt  Nada acontece aqui. É essa a tragédia inteira desse lugar.
    >>  ............................................
  gloomy.dialogue.conversations.gossip.none/2
    en  No news. Which people call peace and I call waiting.
    >>  ............................................
    pt  Nenhuma notícia. O que as pessoas chamam de paz e eu chamo de espera.
    >>  ............................................
  greedy.dialogue.conversations.gossip.none/1
    en  Nothing worth trading. A quiet week — bad for gossip, worse for business. Even the chickens are withholding.
    >>  ............................................
    pt  Nada que valha troca. Semana quieta — ruim pra fofoca, pior pros negócios. Até as galinhas estão sonegando.
    >>  ............................................
  greedy.dialogue.conversations.gossip.none/2
    en  Dry as a bone. I've a nose for what's worth repeating and I've smelled nothing all week.
    >>  ............................................
    pt  Seco feito osso. Eu tenho faro para o que vale repetir e não senti cheiro de nada a semana toda.
    >>  ............................................
  grumpy.dialogue.conversations.gossip.none/1
    en  Nothing. And good. News in this village is never the sort you want.
    >>  ............................................
    pt  Nada. E ainda bem. Notícia nesse vilarejo nunca é do tipo que você quer.
    >>  ............................................
  grumpy.dialogue.conversations.gossip.none/2
    en  Not a thing worth repeating. Quiet week, for once.
    >>  ............................................
    pt  Nem uma coisa que valha repetir. Semana quieta, pra variar.
    >>  ............................................
  introverted.dialogue.conversations.gossip.none/1
    en  I hear very little, %1$s. Conversations tend to stop when I come near — not unkindly, they just do.
    >>  ............................................
    pt  Eu ouço muito pouco, %1$s. As conversas costumam parar quando eu chego perto — não por maldade, elas só param.
    >>  ............................................
  introverted.dialogue.conversations.gossip.none/2
    en  Nothing to report. I'm rarely where news is made, and that's mostly on purpose.
    >>  ............................................
    pt  Nada a relatar. Raramente estou onde a notícia é feita, e isso é quase sempre de propósito.
    >>  ............................................
  lazy.dialogue.conversations.gossip.none/1
    en  Nothing worth carrying. Quiet week — and honestly, I don't chase the news down.
    >>  ............................................
    pt  Nada que valha carregar. Semana quieta — e sinceramente, eu não saio caçando notícia.
    >>  ............................................
  lazy.dialogue.conversations.gossip.none/2
    en  No news. It usually finds me a few days late anyway, and none the worse for it.
    >>  ............................................
    pt  Nenhuma novidade. Ela costuma me achar uns dias atrasada de qualquer jeito, e nem por isso pior.
    >>  ............................................
  odd.dialogue.conversations.gossip.none/1
    en  The weathervane hasn't moved in three days, which is either no news or very big news. I'm treating it as no news. Safer.
    >>  ............................................
    pt  O catavento não se mexe há três dias, o que é ou notícia nenhuma ou notícia enorme. Estou tratando como notícia nenhuma. Mais seguro.
    >>  ............................................
  odd.dialogue.conversations.gossip.none/2
    en  None. Though the crows have been arriving early, and crows are usually first with anything.
    >>  ............................................
    pt  Nenhuma. Embora os corvos andem chegando cedo, e corvo costuma ser o primeiro em qualquer coisa.
    >>  ............................................
  peaceful.dialogue.conversations.gossip.none/1
    en  Nothing at all, and I'm glad of it. A week with no news is a week where nobody was hurt.
    >>  ............................................
    pt  Nada mesmo, e eu fico contente com isso. Uma semana sem notícia é uma semana em que ninguém se machucou.
    >>  ............................................
  peaceful.dialogue.conversations.gossip.none/2
    en  No news. I've never much minded a dull week — they're kinder than the interesting ones.
    >>  ............................................
    pt  Nenhuma notícia. Nunca me incomodei com uma semana sem graça — são mais gentis que as interessantes.
    >>  ............................................
  peppy.dialogue.conversations.gossip.none/1
    en  Nothing new! A chicken sat on the fence for a whole HOUR though. I watched all of it. Riveting stuff!
    >>  ............................................
    pt  Nada novo! Mas uma galinha ficou sentada na cerca por uma HORA inteira. Eu assisti tudo. Coisa fascinante!
    >>  ............................................
  peppy.dialogue.conversations.gossip.none/2
    en  Nothing! Well — a duck walked through the market like it OWNED the place. I clapped. Nobody joined me. Their loss!
    >>  ............................................
    pt  Nada! Bom — um pato atravessou a feira como se fosse DONO do lugar. Eu bati palma. Ninguém me acompanhou. Perderam!
    >>  ............................................
  playful.dialogue.conversations.gossip.none/1
    en  Nothing! Terribly dull week. I may have to invent something just to keep everyone entertained.
    >>  ............................................
    pt  Nada! Semana terrivelmente sem graça. Talvez eu tenha que inventar alguma coisa só pra manter todo mundo entretido.
    >>  ............................................
  playful.dialogue.conversations.gossip.none/2
    en  Not a whisper. If it stays this quiet I'll be forced to cause some news myself.
    >>  ............................................
    pt  Nem um sussurro. Se continuar tão quieto, vou ser obrigado a causar uma notícia eu mesmo.
    >>  ............................................
  relaxed.dialogue.conversations.gossip.none/1
    en  Nothing worth carrying. Quiet week — and honestly, I don't chase the news down.
    >>  ............................................
    pt  Nada que valha carregar. Semana quieta — e sinceramente, eu não saio caçando notícia.
    >>  ............................................
  relaxed.dialogue.conversations.gossip.none/2
    en  No news. It usually finds me a few days late anyway, and none the worse for it.
    >>  ............................................
    pt  Nenhuma novidade. Ela costuma me achar uns dias atrasada de qualquer jeito, e nem por isso pior.
    >>  ............................................
  sensitive.dialogue.conversations.gossip.none/1
    en  Nothing spoken, anyway. But the mason's been quieter than usual and I don't like it. I'll bring him bread. That's the news.
    >>  ............................................
    pt  Nada falado, pelo menos. Mas o pedreiro anda mais calado que o normal e eu não gosto disso. Vou levar pão pra ele. É essa a notícia.
    >>  ............................................
  sensitive.dialogue.conversations.gossip.none/2
    en  No news at all, and that's usually when something's being carried quietly. I'll go and look for it.
    >>  ............................................
    pt  Nenhuma notícia, e é justamente quando alguém está carregando algo em silêncio. Vou procurar.
    >>  ............................................
  shy.dialogue.conversations.gossip.none/1
    en  I hear very little, %1$s. Conversations tend to stop when I come near — not unkindly, they just do.
    >>  ............................................
    pt  Eu ouço muito pouco, %1$s. As conversas costumam parar quando eu chego perto — não por maldade, elas só param.
    >>  ............................................
  shy.dialogue.conversations.gossip.none/2
    en  Nothing to report. I'm rarely where news is made, and that's mostly on purpose.
    >>  ............................................
    pt  Nada a relatar. Raramente estou onde a notícia é feita, e isso é quase sempre de propósito.
    >>  ............................................
  upbeat.dialogue.conversations.gossip.none/1
    en  Quiet week, happily! Nothing but good weather and the usual well-side chatter. I'll take a dull week gladly.
    >>  ............................................
    pt  Semana quieta, felizmente! Só bom tempo e a conversa de sempre à beira do poço. Aceito uma semana sem graça com prazer.
    >>  ............................................
  upbeat.dialogue.conversations.gossip.none/2
    en  No news at all, which is its own good news. Everyone's well, everyone's fed. That'll do.
    >>  ............................................
    pt  Notícia nenhuma, o que já é uma boa notícia por si só. Todo mundo bem, todo mundo alimentado. Serve.
    >>  ............................................
  witty.dialogue.conversations.gossip.none/1
    en  Quiet week, happily! Nothing but good weather and the usual well-side chatter. I'll take a dull week gladly.
    >>  ............................................
    pt  Semana quieta, felizmente! Só bom tempo e a conversa de sempre à beira do poço. Aceito uma semana sem graça com prazer.
    >>  ............................................
  witty.dialogue.conversations.gossip.none/2
    en  No news at all, which is its own good news. Everyone's well, everyone's fed. That'll do.
    >>  ............................................
    pt  Notícia nenhuma, o que já é uma boa notícia por si só. Todo mundo bem, todo mundo alimentado. Serve.
    >>  ............................................
```

</details>


**Outcome 11 of 11** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: remembers `mcaconversations.cooldown.news` (this player only) permanently
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.gossip.none
WHO    VILLAGER — what the player reads after pressing "Anything happen around here lately?"
       spoken on: conversations.cat.events, button `news`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `gossip.none.terminal`: the villager accepts. Subject `gossip.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.events` / button `news`** earlier in this file. Fill it in there, once.


### Button `noticed` — "How have you been, in yourself?"

Shown only when MCA's own constraints hold: `"adult"`

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `events.noticed` — accepted phrasings: "noticed anything"; "seen anything strange"; "anything unusual"; "anything different"
  - the message must contain one of: `notice`, `noticed`, `unusual`, `strange`, `seen`
  - scored words: `notice`(1.2), `noticed`(1.2), `unusual`(1.0), `strange`(1.0), `different`(0.8), `seen`(0.8)

```text
POOL   dialogue key: dialogue.conversations.cat.events.noticed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.events.noticed   [31 chars]
    en  How have you been, in yourself?
    >>  ............................................
    pt  Como você tem estado, por dentro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 17** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.noticed.you_look_tired"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.noticed.you_look_tired", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) for 36000 ticks
- Then opens: `conversations.scene.noticed.you_look_tired.respond`
- …where the player's next choices will be: "You read that correctly." | "I would rather leave that where it is." | "Thanks for noticing."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.you_look_tired
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.scene.noticed.you_look_tired.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.you_look_tired.open`: the villager reports. Subject `noticed.the_player`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: self_disclosure, restraint, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.you_look_tired/1   [90 chars]
    en  You have been walking differently. I am not going to make a thing of it, and I did notice.
    >>  ............................................
    pt  Você anda diferente. Não vou fazer disso um caso, e eu reparei.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired/2   [97 chars]
    en  You come in and you look at the door before you look at anybody. You have done it four times now.
    >>  ............................................
    pt  Você entra e olha para a porta antes de olhar para qualquer pessoa. Já fez isso quatro vezes.
    >>  ............................................
  dialogue.conversations.scene.noticed.you_look_tired/3   [124 chars]
    en  Nothing is wrong that I can see. You are just carrying something, and I would rather say so than watch it for another month.
    >>  ............................................
    pt  Nada de errado que eu consiga ver. Você só está carregando alguma coisa, e prefiro dizer a ficar observando por mais um mês.
    >>  ............................................
```


**Outcome 2 of 17** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.noticed.something_about_the_room"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.noticed.something_about_the_room", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) for 36000 ticks
- Then opens: `conversations.scene.noticed.something_about_the_room.respond`
- …where the player's next choices will be: "What do you make of it?" | "You pay close attention." | "Thanks for noticing."

```text
POOL   dialogue key: dialogue.conversations.scene.noticed.something_about_the_room
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.scene.noticed.something_about_the_room.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.something_about_the_room.open`: the villager reports. Subject `noticed.the_village`, polarity `neutral`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:noticed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.noticed.something_about_the_room/1   [119 chars]
    en  The lane has been quieter since the harvest. Not unhappy. Quieter. There is a difference and it takes a season to hear.
    >>  ............................................
    pt  A viela está mais calada desde a colheita. Não infeliz. Mais calada. Existe diferença e leva uma estação para se ouvir.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room/2   [134 chars]
    en  Somebody has been mending the fence at the top and has told nobody, which I find more interesting than most things that get announced.
    >>  ............................................
    pt  Alguém vem consertando a cerca lá em cima e não contou a ninguém, o que eu acho mais interessante que a maioria das coisas anunciadas.
    >>  ............................................
  dialogue.conversations.scene.noticed.something_about_the_room/3   [122 chars]
    en  Two households have started walking the long way round to the well. I have no idea why and I have decided not to find out.
    >>  ............................................
    pt  Duas casas passaram a dar a volta maior até o poço. Não faço ideia por quê e decidi não descobrir.
    >>  ............................................
```


**Outcome 3 of 17** — base weight `0`

- Fires when: weighted +200 when arc `noticed` is at stage 1..2
- Fires when: RULED OUT when arc `noticed` is at stage <= 0  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `baby`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.noticed` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `resume` budget `standard`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) for 36000 ticks
- Then opens: `conversations.arc.noticed.resume.respond`
- …where the player's next choices will be: "I'm sorry I didn't come." | "How is it now?" | "I'll make it a regular thing." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.resume
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.arc.noticed.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.resume.opener`: the villager reports. Subject `noticed.injury`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit, practical_help
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.resume/1   [70 chars]
    en  You came back. I'd bet against it, quietly, and I'm glad to have lost.
    >>  ............................................
    pt  Você voltou. Eu tinha apostado que não, em silêncio, e fico feliz de ter perdido.
    >>  ............................................
  dialogue.conversations.noticed.resume/2   [87 chars]
    en  You said you'd look in. You didn't, and I'd rather say so than pretend I didn't notice.
    >>  ............................................
    pt  Você disse que ia dar uma passada. Não deu, e prefiro dizer a fingir que não notei.
    >>  ............................................
  dialogue.conversations.noticed.resume/3   [77 chars]
    en  It's mending. Slowly, and mending, and you're the only one who's asked twice.
    >>  ............................................
    pt  Está sarando. Devagar, e sarando, e você é o único que perguntou duas vezes.
    >>  ............................................
```


**Outcome 4 of 17** — base weight `200`

- Fires when: RULED OUT when `min_health` = 15  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `hurt` budget `standard`
- Then opens: `conversations.topic.noticed.hurt.respond`
- …where the player's next choices will be: "What happened to you?" | "Has anyone tended it?" | "You should be resting, not standing here." | "It's only a scratch." | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.noticed.hurt
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.hurt.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.hurt.open`: the villager disclose_problems. Subject `noticed.injury`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `noticed:hurt` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.hurt/1   [81 chars]
    en  Been better. I took a bad one two nights back and it hasn't finished with me yet.
    >>  ............................................
    pt  Já estive melhor. Levei uma feia há duas noites e ela ainda não terminou comigo.
    >>  ............................................
  dialogue.conversations.noticed.hurt/2   [75 chars]
    en  Sore, if you want the truth of it. I'm walking it off and it isn't working.
    >>  ............................................
    pt  Dolorido, se quer a verdade. Estou tentando andar até passar e não está passando.
    >>  ............................................
  dialogue.conversations.noticed.hurt/3   [76 chars]
    en  In myself? In myself I'm fine. It's the rest of me that's the problem, %1$s.
    >>  ............................................
    pt  Em mim? Em mim estou bem. É o resto de mim que é o problema, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.hurt/1
    en  Been better, and I'd rather you didn't look at it too long, %1$s.
    >>  ............................................
    pt  Já estive melhor, e prefiro que você não olhe muito tempo, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt/2
    en  Sore. Saying it out loud makes it real, so I've been avoiding the saying.
    >>  ............................................
    pt  Dolorido. Dizer em voz alta torna real, então venho evitando dizer.
    >>  ............................................
  anxious.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. I'd like to keep believing that, if you'll let me.
    >>  ............................................
    pt  Em mim estou bem. Gostaria de continuar acreditando nisso, se você deixar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt/1
    en  Been better. I've had worse and I'll have worse again, but it's a nuisance.
    >>  ............................................
    pt  Já estive melhor. Já tive pior e vou ter pior de novo, mas incomoda.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt/2
    en  Sore. At my age they take the long road out.
    >>  ............................................
    pt  Dolorido. Na minha idade elas saem pelo caminho longo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. The rest of me has been complaining for thirty years.
    >>  ............................................
    pt  Em mim estou bem. O resto de mim reclama há trinta anos.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt/1
    en  Been better. I took a bad one two nights back and it hasn't finished with me yet.
    >>  ............................................
    pt  Já estive melhor. Levei uma feia há duas noites e ela ainda não terminou comigo.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt/2
    en  Sore. I'm walking it off and it isn't working.
    >>  ............................................
    pt  Dolorido. Estou tentando andar até passar e não está passando.
    >>  ............................................
  confident.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me that's the problem.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim que é o problema.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt/1
    en  Been better. I took a bad one two nights back and it hasn't finished with me yet.
    >>  ............................................
    pt  Já estive melhor. Levei uma feia há duas noites e ela ainda não terminou comigo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt/2
    en  Sore. I'm walking it off and it isn't working.
    >>  ............................................
    pt  Dolorido. Estou tentando andar até passar e não está passando.
    >>  ............................................
  crabby.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me that's the problem.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim que é o problema.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt/1
    en  Been better, %1$s. I took a bad one two nights back and I've not shaken it.
    >>  ............................................
    pt  Já estive melhor, %1$s. Levei uma feia há duas noites e não me livrei dela.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt/2
    en  Sore. I'd not have said so to anyone else stood there, mind.
    >>  ............................................
    pt  Dolorido. Não teria dito isso a mais ninguém parado aí, veja bem.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me — and I'm glad it's you asking.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim — e ainda bem que é você perguntando.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt/1
    en  Been better, %1$s. I took a bad one two nights back and I've not shaken it.
    >>  ............................................
    pt  Já estive melhor, %1$s. Levei uma feia há duas noites e não me livrei dela.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt/2
    en  Sore. I'd not have said so to anyone else stood there, mind.
    >>  ............................................
    pt  Dolorido. Não teria dito isso a mais ninguém parado aí, veja bem.
    >>  ............................................
  flirty.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me — and I'm glad it's you asking.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim — e ainda bem que é você perguntando.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt/1
    en  Been better, %1$s. I took a bad one two nights back and I've not shaken it.
    >>  ............................................
    pt  Já estive melhor, %1$s. Levei uma feia há duas noites e não me livrei dela.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt/2
    en  Sore. I'd not have said so to anyone else stood there, mind.
    >>  ............................................
    pt  Dolorido. Não teria dito isso a mais ninguém parado aí, veja bem.
    >>  ............................................
  friendly.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me — and I'm glad it's you asking.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim — e ainda bem que é você perguntando.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt/1
    en  Been better, and I'd rather you didn't look at it too long, %1$s.
    >>  ............................................
    pt  Já estive melhor, e prefiro que você não olhe muito tempo, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt/2
    en  Sore. Saying it out loud makes it real, so I've been avoiding the saying.
    >>  ............................................
    pt  Dolorido. Dizer em voz alta torna real, então venho evitando dizer.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. I'd like to keep believing that, if you'll let me.
    >>  ............................................
    pt  Em mim estou bem. Gostaria de continuar acreditando nisso, se você deixar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt/1
    en  Been better. I took a bad one two nights back and it hasn't finished with me yet.
    >>  ............................................
    pt  Já estive melhor. Levei uma feia há duas noites e ela ainda não terminou comigo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt/2
    en  Sore. I'm walking it off and it isn't working.
    >>  ............................................
    pt  Dolorido. Estou tentando andar até passar e não está passando.
    >>  ............................................
  greedy.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me that's the problem.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim que é o problema.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt/1
    en  Been better. I took a bad one two nights back and it hasn't finished with me yet.
    >>  ............................................
    pt  Já estive melhor. Levei uma feia há duas noites e ela ainda não terminou comigo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt/2
    en  Sore. I'm walking it off and it isn't working.
    >>  ............................................
    pt  Dolorido. Estou tentando andar até passar e não está passando.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. It's the rest of me that's the problem.
    >>  ............................................
    pt  Em mim estou bem. É o resto de mim que é o problema.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt/1
    en  Been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt/2
    en  Sore.
    >>  ............................................
    pt  Dolorido.
    >>  ............................................
  introverted.dialogue.conversations.noticed.hurt/3
    en  In myself, fine. The rest of me, less so.
    >>  ............................................
    pt  Em mim, bem. O resto de mim, menos.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt/1
    en  Been better. I've had worse and I'll have worse again, but it's a nuisance.
    >>  ............................................
    pt  Já estive melhor. Já tive pior e vou ter pior de novo, mas incomoda.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt/2
    en  Sore. At my age they take the long road out.
    >>  ............................................
    pt  Dolorido. Na minha idade elas saem pelo caminho longo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. The rest of me has been complaining for thirty years.
    >>  ............................................
    pt  Em mim estou bem. O resto de mim reclama há trinta anos.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt/1
    en  Been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt/2
    en  Sore.
    >>  ............................................
    pt  Dolorido.
    >>  ............................................
  odd.dialogue.conversations.noticed.hurt/3
    en  In myself, fine. The rest of me, less so.
    >>  ............................................
    pt  Em mim, bem. O resto de mim, menos.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt/1
    en  Been better. I've had worse and I'll have worse again, but it's a nuisance.
    >>  ............................................
    pt  Já estive melhor. Já tive pior e vou ter pior de novo, mas incomoda.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt/2
    en  Sore. At my age they take the long road out.
    >>  ............................................
    pt  Dolorido. Na minha idade elas saem pelo caminho longo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. The rest of me has been complaining for thirty years.
    >>  ............................................
    pt  Em mim estou bem. O resto de mim reclama há trinta anos.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt/1
    en  Been better! Took a bad one two nights back and it's still making its opinions known.
    >>  ............................................
    pt  Já estive melhor! Levei uma feia há duas noites e ela ainda dá opinião.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt/2
    en  Sore, and doing a heroic job of not mentioning it. Until now, obviously.
    >>  ............................................
    pt  Dolorido, e fazendo um trabalho heroico de não mencionar. Até agora, claro.
    >>  ............................................
  peppy.dialogue.conversations.noticed.hurt/3
    en  In myself? Excellent. The rest of me is staging a small rebellion, %1$s.
    >>  ............................................
    pt  Em mim? Excelente. O resto de mim está fazendo uma pequena rebelião, %1$s.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt/1
    en  Been better! Took a bad one two nights back and it's still making its opinions known.
    >>  ............................................
    pt  Já estive melhor! Levei uma feia há duas noites e ela ainda dá opinião.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt/2
    en  Sore, and doing a heroic job of not mentioning it. Until now, obviously.
    >>  ............................................
    pt  Dolorido, e fazendo um trabalho heroico de não mencionar. Até agora, claro.
    >>  ............................................
  playful.dialogue.conversations.noticed.hurt/3
    en  In myself? Excellent. The rest of me is staging a small rebellion, %1$s.
    >>  ............................................
    pt  Em mim? Excelente. O resto de mim está fazendo uma pequena rebelião, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt/1
    en  Been better. I've had worse and I'll have worse again, but it's a nuisance.
    >>  ............................................
    pt  Já estive melhor. Já tive pior e vou ter pior de novo, mas incomoda.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt/2
    en  Sore. At my age they take the long road out.
    >>  ............................................
    pt  Dolorido. Na minha idade elas saem pelo caminho longo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. The rest of me has been complaining for thirty years.
    >>  ............................................
    pt  Em mim estou bem. O resto de mim reclama há trinta anos.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt/1
    en  Been better, and I'd rather you didn't look at it too long, %1$s.
    >>  ............................................
    pt  Já estive melhor, e prefiro que você não olhe muito tempo, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt/2
    en  Sore. Saying it out loud makes it real, so I've been avoiding the saying.
    >>  ............................................
    pt  Dolorido. Dizer em voz alta torna real, então venho evitando dizer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.hurt/3
    en  In myself I'm fine. I'd like to keep believing that, if you'll let me.
    >>  ............................................
    pt  Em mim estou bem. Gostaria de continuar acreditando nisso, se você deixar.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt/1
    en  Been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt/2
    en  Sore.
    >>  ............................................
    pt  Dolorido.
    >>  ............................................
  shy.dialogue.conversations.noticed.hurt/3
    en  In myself, fine. The rest of me, less so.
    >>  ............................................
    pt  Em mim, bem. O resto de mim, menos.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt/1
    en  Been better! Took a bad one two nights back and it's still making its opinions known.
    >>  ............................................
    pt  Já estive melhor! Levei uma feia há duas noites e ela ainda dá opinião.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt/2
    en  Sore, and doing a heroic job of not mentioning it. Until now, obviously.
    >>  ............................................
    pt  Dolorido, e fazendo um trabalho heroico de não mencionar. Até agora, claro.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.hurt/3
    en  In myself? Excellent. The rest of me is staging a small rebellion, %1$s.
    >>  ............................................
    pt  Em mim? Excelente. O resto de mim está fazendo uma pequena rebelião, %1$s.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt/1
    en  Been better! Took a bad one two nights back and it's still making its opinions known.
    >>  ............................................
    pt  Já estive melhor! Levei uma feia há duas noites e ela ainda dá opinião.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt/2
    en  Sore, and doing a heroic job of not mentioning it. Until now, obviously.
    >>  ............................................
    pt  Dolorido, e fazendo um trabalho heroico de não mencionar. Até agora, claro.
    >>  ............................................
  witty.dialogue.conversations.noticed.hurt/3
    en  In myself? Excellent. The rest of me is staging a small rebellion, %1$s.
    >>  ............................................
    pt  Em mim? Excelente. O resto de mim está fazendo uma pequena rebelião, %1$s.
    >>  ............................................
```

</details>


**Outcome 5 of 17** — base weight `0`

- Fires when: weighted +180 when `min_infection_progress` = 1
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `afflicted` budget `standard`
- Then opens: `conversations.topic.noticed.afflicted.respond`
- …where the player's next choices will be: "What is it that frightens you most?" | "How long have you known?" | "I'm not going anywhere." | "Everyone's got something wrong with them." | "I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.afflicted
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.afflicted.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.afflicted.open`: the villager disclose_problems. Subject `noticed.affliction`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `noticed:afflicted` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.afflicted/1   [89 chars]
    en  Frightened, if you want it plainly. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo, se quer sem rodeios. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  dialogue.conversations.noticed.afflicted/2   [81 chars]
    en  I've been better. I've also been less afraid, and I'd take the second back first.
    >>  ............................................
    pt  Já estive melhor. Também já estive menos assustado, e eu escolheria isso de volta primeiro.
    >>  ............................................
  dialogue.conversations.noticed.afflicted/3   [94 chars]
    en  Don't ask me that kindly, %1$s, or I'll tell you the whole of it and neither of us wants that.
    >>  ............................................
    pt  Não me pergunte isso com carinho, %1$s, ou eu conto tudo e nenhum de nós quer isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.afflicted/1
    en  Frightened. I've said it now, and I can't unsay it, and part of me wants to.
    >>  ............................................
    pt  Com medo. Eu disse agora, e não posso desdizer, e parte de mim quer.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted/2
    en  I've been better. Less afraid, too, and I'd trade almost anything for that.
    >>  ............................................
    pt  Já estive melhor. Menos assustado também, e eu trocaria quase tudo por isso.
    >>  ............................................
  anxious.dialogue.conversations.noticed.afflicted/3
    en  Please don't ask me gently. I've been holding this shut all week.
    >>  ............................................
    pt  Por favor não pergunte com doçura. Venho segurando isso fechado a semana toda.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've not been frightened of much in a long life.
    >>  ............................................
    pt  Com medo, e faz tempo que eu não tinha medo de muita coisa.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've buried people who had this, so I know what I'm looking at.
    >>  ............................................
    pt  Já estive melhor. Enterrei gente que teve isso, então sei o que estou vendo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly. I've held together through worse by not being asked kindly.
    >>  ............................................
    pt  Não pergunte com carinho. Aguentei coisa pior justamente por não me perguntarem assim.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted/1
    en  Frightened. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've also been less afraid, and I'd take that back first.
    >>  ............................................
    pt  Já estive melhor. Também já estive menos assustado, e escolheria isso primeiro.
    >>  ............................................
  confident.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly or I'll tell you the whole of it.
    >>  ............................................
    pt  Não me pergunte com carinho ou eu conto tudo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted/1
    en  Frightened. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've also been less afraid, and I'd take that back first.
    >>  ............................................
    pt  Já estive melhor. Também já estive menos assustado, e escolheria isso primeiro.
    >>  ............................................
  crabby.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly or I'll tell you the whole of it.
    >>  ............................................
    pt  Não me pergunte com carinho ou eu conto tudo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted/1
    en  Frightened, %1$s. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo, %1$s. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted/2
    en  I've been better. You're the first person I've said that to without softening it.
    >>  ............................................
    pt  Já estive melhor. Você é a primeira pessoa a quem digo isso sem suavizar.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly, or I'll tell you the whole of it and I'll not be able to stop.
    >>  ............................................
    pt  Não pergunte com carinho, ou eu conto tudo e não vou conseguir parar.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted/1
    en  Frightened, %1$s. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo, %1$s. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted/2
    en  I've been better. You're the first person I've said that to without softening it.
    >>  ............................................
    pt  Já estive melhor. Você é a primeira pessoa a quem digo isso sem suavizar.
    >>  ............................................
  flirty.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly, or I'll tell you the whole of it and I'll not be able to stop.
    >>  ............................................
    pt  Não pergunte com carinho, ou eu conto tudo e não vou conseguir parar.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted/1
    en  Frightened, %1$s. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo, %1$s. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted/2
    en  I've been better. You're the first person I've said that to without softening it.
    >>  ............................................
    pt  Já estive melhor. Você é a primeira pessoa a quem digo isso sem suavizar.
    >>  ............................................
  friendly.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly, or I'll tell you the whole of it and I'll not be able to stop.
    >>  ............................................
    pt  Não pergunte com carinho, ou eu conto tudo e não vou conseguir parar.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted/1
    en  Frightened. I've said it now, and I can't unsay it, and part of me wants to.
    >>  ............................................
    pt  Com medo. Eu disse agora, e não posso desdizer, e parte de mim quer.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. Less afraid, too, and I'd trade almost anything for that.
    >>  ............................................
    pt  Já estive melhor. Menos assustado também, e eu trocaria quase tudo por isso.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.afflicted/3
    en  Please don't ask me gently. I've been holding this shut all week.
    >>  ............................................
    pt  Por favor não pergunte com doçura. Venho segurando isso fechado a semana toda.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted/1
    en  Frightened. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've also been less afraid, and I'd take that back first.
    >>  ............................................
    pt  Já estive melhor. Também já estive menos assustado, e escolheria isso primeiro.
    >>  ............................................
  greedy.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly or I'll tell you the whole of it.
    >>  ............................................
    pt  Não me pergunte com carinho ou eu conto tudo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted/1
    en  Frightened. There's something in me that wasn't there last month.
    >>  ............................................
    pt  Com medo. Tem algo em mim que não estava lá no mês passado.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've also been less afraid, and I'd take that back first.
    >>  ............................................
    pt  Já estive melhor. Também já estive menos assustado, e escolheria isso primeiro.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly or I'll tell you the whole of it.
    >>  ............................................
    pt  Não me pergunte com carinho ou eu conto tudo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted/1
    en  Frightened.
    >>  ............................................
    pt  Com medo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted/2
    en  I've been better. And less afraid.
    >>  ............................................
    pt  Já estive melhor. E menos assustado.
    >>  ............................................
  introverted.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly.
    >>  ............................................
    pt  Não me pergunte com carinho.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've not been frightened of much in a long life.
    >>  ............................................
    pt  Com medo, e faz tempo que eu não tinha medo de muita coisa.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've buried people who had this, so I know what I'm looking at.
    >>  ............................................
    pt  Já estive melhor. Enterrei gente que teve isso, então sei o que estou vendo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly. I've held together through worse by not being asked kindly.
    >>  ............................................
    pt  Não pergunte com carinho. Aguentei coisa pior justamente por não me perguntarem assim.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted/1
    en  Frightened.
    >>  ............................................
    pt  Com medo.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted/2
    en  I've been better. And less afraid.
    >>  ............................................
    pt  Já estive melhor. E menos assustado.
    >>  ............................................
  odd.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly.
    >>  ............................................
    pt  Não me pergunte com carinho.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've not been frightened of much in a long life.
    >>  ............................................
    pt  Com medo, e faz tempo que eu não tinha medo de muita coisa.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've buried people who had this, so I know what I'm looking at.
    >>  ............................................
    pt  Já estive melhor. Enterrei gente que teve isso, então sei o que estou vendo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly. I've held together through worse by not being asked kindly.
    >>  ............................................
    pt  Não pergunte com carinho. Aguentei coisa pior justamente por não me perguntarem assim.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've run out of jokes about it, which tells you where we are.
    >>  ............................................
    pt  Com medo, e já acabaram minhas piadas sobre isso, o que diz onde estamos.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I'd list the ways, but you look like you have somewhere to be.
    >>  ............................................
    pt  Já estive melhor. Eu listaria os jeitos, mas você parece ter aonde ir.
    >>  ............................................
  peppy.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly, %1$s. Cheerfulness is all that's holding this together.
    >>  ............................................
    pt  Não me pergunte com carinho, %1$s. A alegria é tudo que segura isso.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've run out of jokes about it, which tells you where we are.
    >>  ............................................
    pt  Com medo, e já acabaram minhas piadas sobre isso, o que diz onde estamos.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I'd list the ways, but you look like you have somewhere to be.
    >>  ............................................
    pt  Já estive melhor. Eu listaria os jeitos, mas você parece ter aonde ir.
    >>  ............................................
  playful.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly, %1$s. Cheerfulness is all that's holding this together.
    >>  ............................................
    pt  Não me pergunte com carinho, %1$s. A alegria é tudo que segura isso.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've not been frightened of much in a long life.
    >>  ............................................
    pt  Com medo, e faz tempo que eu não tinha medo de muita coisa.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I've buried people who had this, so I know what I'm looking at.
    >>  ............................................
    pt  Já estive melhor. Enterrei gente que teve isso, então sei o que estou vendo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly. I've held together through worse by not being asked kindly.
    >>  ............................................
    pt  Não pergunte com carinho. Aguentei coisa pior justamente por não me perguntarem assim.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted/1
    en  Frightened. I've said it now, and I can't unsay it, and part of me wants to.
    >>  ............................................
    pt  Com medo. Eu disse agora, e não posso desdizer, e parte de mim quer.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted/2
    en  I've been better. Less afraid, too, and I'd trade almost anything for that.
    >>  ............................................
    pt  Já estive melhor. Menos assustado também, e eu trocaria quase tudo por isso.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.afflicted/3
    en  Please don't ask me gently. I've been holding this shut all week.
    >>  ............................................
    pt  Por favor não pergunte com doçura. Venho segurando isso fechado a semana toda.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted/1
    en  Frightened.
    >>  ............................................
    pt  Com medo.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted/2
    en  I've been better. And less afraid.
    >>  ............................................
    pt  Já estive melhor. E menos assustado.
    >>  ............................................
  shy.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me kindly.
    >>  ............................................
    pt  Não me pergunte com carinho.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've run out of jokes about it, which tells you where we are.
    >>  ............................................
    pt  Com medo, e já acabaram minhas piadas sobre isso, o que diz onde estamos.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I'd list the ways, but you look like you have somewhere to be.
    >>  ............................................
    pt  Já estive melhor. Eu listaria os jeitos, mas você parece ter aonde ir.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly, %1$s. Cheerfulness is all that's holding this together.
    >>  ............................................
    pt  Não me pergunte com carinho, %1$s. A alegria é tudo que segura isso.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted/1
    en  Frightened, and I've run out of jokes about it, which tells you where we are.
    >>  ............................................
    pt  Com medo, e já acabaram minhas piadas sobre isso, o que diz onde estamos.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted/2
    en  I've been better. I'd list the ways, but you look like you have somewhere to be.
    >>  ............................................
    pt  Já estive melhor. Eu listaria os jeitos, mas você parece ter aonde ir.
    >>  ............................................
  witty.dialogue.conversations.noticed.afflicted/3
    en  Don't ask me that kindly, %1$s. Cheerfulness is all that's holding this together.
    >>  ............................................
    pt  Não me pergunte com carinho, %1$s. A alegria é tudo que segura isso.
    >>  ............................................
```

</details>


**Outcome 6 of 17** — base weight `0`

- Fires when: weighted +140 when the villager is pregnant
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `expecting` budget `standard`
- Then opens: `conversations.topic.noticed.expecting.respond`
- …where the player's next choices will be: "How are you feeling, really?" | "Is there anything you're short of?" | "Do you feel ready?" | "Women manage it every day." | "I'll leave you in peace."

```text
POOL   dialogue key: dialogue.conversations.noticed.expecting
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.expecting.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.expecting.open`: the villager discloses. Subject `noticed.pregnancy`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `noticed:expecting` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.expecting/1   [79 chars]
    en  Tired in a way I don't mind, which is new. Ask me again at the end of the week.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, o que é novo. Pergunte de novo no fim da semana.
    >>  ............................................
  dialogue.conversations.noticed.expecting/2   [61 chars]
    en  Large, mostly. And hungry at hours that don't have names yet.
    >>  ............................................
    pt  Grande, principalmente. E com fome em horas que ainda não têm nome.
    >>  ............................................
  dialogue.conversations.noticed.expecting/3   [77 chars]
    en  Two of us in here, %1$s, and only one of us sleeps. I'll let you guess which.
    >>  ............................................
    pt  Somos duas aqui dentro, %1$s, e só uma dorme. Adivinhe qual.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, and frightened in a way I do. Both at once.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, e assustada de um jeito que incomoda. As duas coisas.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting/2
    en  Large, and clumsy with it, and I mind that more than I let on.
    >>  ............................................
    pt  Grande, e desajeitada, e isso me incomoda mais do que eu demonstro.
    >>  ............................................
  anxious.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. I talk to her at night when nobody can hear me being foolish.
    >>  ............................................
    pt  Somos duas aqui dentro. Falo com ela à noite, quando ninguém me ouve ser boba.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind. I remember this part; it's the next part that's long.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda. Lembro desta parte; a próxima é que é longa.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting/2
    en  Large, mostly, and hungry at hours I'd forgotten existed.
    >>  ............................................
    pt  Grande, principalmente, e com fome em horas que eu tinha esquecido que existiam.
    >>  ............................................
  athletic.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. It's not my first and it's no less strange for that.
    >>  ............................................
    pt  Somos duas aqui dentro. Não é a primeira e nem por isso é menos estranho.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, which is new. Ask me again at the end of the week.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, o que é novo. Pergunte no fim da semana.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. And hungry at odd hours.
    >>  ............................................
    pt  Grande, principalmente. E com fome em horas estranhas.
    >>  ............................................
  confident.dialogue.conversations.noticed.expecting/3
    en  Two of us in here, and only one of us sleeps.
    >>  ............................................
    pt  Somos duas aqui dentro, e só uma dorme.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, which is new. Ask me again at the end of the week.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, o que é novo. Pergunte no fim da semana.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. And hungry at odd hours.
    >>  ............................................
    pt  Grande, principalmente. E com fome em horas estranhas.
    >>  ............................................
  crabby.dialogue.conversations.noticed.expecting/3
    en  Two of us in here, and only one of us sleeps.
    >>  ............................................
    pt  Somos duas aqui dentro, e só uma dorme.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, %1$s, which is new for me.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, %1$s, o que é novo pra mim.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. You may say so. Everyone else has been carefully not saying so.
    >>  ............................................
    pt  Grande, principalmente. Pode dizer. Todos vêm cuidadosamente não dizendo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now. It still surprises me every morning.
    >>  ............................................
    pt  Somos duas aqui dentro agora. Ainda me surpreende toda manhã.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, %1$s, which is new for me.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, %1$s, o que é novo pra mim.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. You may say so. Everyone else has been carefully not saying so.
    >>  ............................................
    pt  Grande, principalmente. Pode dizer. Todos vêm cuidadosamente não dizendo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now. It still surprises me every morning.
    >>  ............................................
    pt  Somos duas aqui dentro agora. Ainda me surpreende toda manhã.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, %1$s, which is new for me.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, %1$s, o que é novo pra mim.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. You may say so. Everyone else has been carefully not saying so.
    >>  ............................................
    pt  Grande, principalmente. Pode dizer. Todos vêm cuidadosamente não dizendo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now. It still surprises me every morning.
    >>  ............................................
    pt  Somos duas aqui dentro agora. Ainda me surpreende toda manhã.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, and frightened in a way I do. Both at once.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, e assustada de um jeito que incomoda. As duas coisas.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting/2
    en  Large, and clumsy with it, and I mind that more than I let on.
    >>  ............................................
    pt  Grande, e desajeitada, e isso me incomoda mais do que eu demonstro.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. I talk to her at night when nobody can hear me being foolish.
    >>  ............................................
    pt  Somos duas aqui dentro. Falo com ela à noite, quando ninguém me ouve ser boba.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, which is new. Ask me again at the end of the week.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, o que é novo. Pergunte no fim da semana.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. And hungry at odd hours.
    >>  ............................................
    pt  Grande, principalmente. E com fome em horas estranhas.
    >>  ............................................
  greedy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here, and only one of us sleeps.
    >>  ............................................
    pt  Somos duas aqui dentro, e só uma dorme.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, which is new. Ask me again at the end of the week.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, o que é novo. Pergunte no fim da semana.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting/2
    en  Large, mostly. And hungry at odd hours.
    >>  ............................................
    pt  Grande, principalmente. E com fome em horas estranhas.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here, and only one of us sleeps.
    >>  ............................................
    pt  Somos duas aqui dentro, e só uma dorme.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting/1
    en  Tired. Not badly.
    >>  ............................................
    pt  Cansada. Não muito.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting/2
    en  Large. Hungry.
    >>  ............................................
    pt  Grande. Com fome.
    >>  ............................................
  introverted.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now.
    >>  ............................................
    pt  Somos duas aqui dentro agora.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind. I remember this part; it's the next part that's long.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda. Lembro desta parte; a próxima é que é longa.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting/2
    en  Large, mostly, and hungry at hours I'd forgotten existed.
    >>  ............................................
    pt  Grande, principalmente, e com fome em horas que eu tinha esquecido que existiam.
    >>  ............................................
  lazy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. It's not my first and it's no less strange for that.
    >>  ............................................
    pt  Somos duas aqui dentro. Não é a primeira e nem por isso é menos estranho.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting/1
    en  Tired. Not badly.
    >>  ............................................
    pt  Cansada. Não muito.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting/2
    en  Large. Hungry.
    >>  ............................................
    pt  Grande. Com fome.
    >>  ............................................
  odd.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now.
    >>  ............................................
    pt  Somos duas aqui dentro agora.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind. I remember this part; it's the next part that's long.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda. Lembro desta parte; a próxima é que é longa.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting/2
    en  Large, mostly, and hungry at hours I'd forgotten existed.
    >>  ............................................
    pt  Grande, principalmente, e com fome em horas que eu tinha esquecido que existiam.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. It's not my first and it's no less strange for that.
    >>  ............................................
    pt  Somos duas aqui dentro. Não é a primeira e nem por isso é menos estranho.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind! Ask again on Friday and you may get a different answer.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda! Pergunte na sexta e talvez mude.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting/2
    en  Large. Magnificently, inconveniently large, and hungry at hours with no names.
    >>  ............................................
    pt  Grande. Magnificamente, inconvenientemente grande, e com fome em horas sem nome.
    >>  ............................................
  peppy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here and only one of us sleeps. I'll let you guess which, %1$s.
    >>  ............................................
    pt  Somos duas aqui dentro e só uma dorme. Adivinhe qual, %1$s.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind! Ask again on Friday and you may get a different answer.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda! Pergunte na sexta e talvez mude.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting/2
    en  Large. Magnificently, inconveniently large, and hungry at hours with no names.
    >>  ............................................
    pt  Grande. Magnificamente, inconvenientemente grande, e com fome em horas sem nome.
    >>  ............................................
  playful.dialogue.conversations.noticed.expecting/3
    en  Two of us in here and only one of us sleeps. I'll let you guess which, %1$s.
    >>  ............................................
    pt  Somos duas aqui dentro e só uma dorme. Adivinhe qual, %1$s.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind. I remember this part; it's the next part that's long.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda. Lembro desta parte; a próxima é que é longa.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting/2
    en  Large, mostly, and hungry at hours I'd forgotten existed.
    >>  ............................................
    pt  Grande, principalmente, e com fome em horas que eu tinha esquecido que existiam.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. It's not my first and it's no less strange for that.
    >>  ............................................
    pt  Somos duas aqui dentro. Não é a primeira e nem por isso é menos estranho.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind, and frightened in a way I do. Both at once.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda, e assustada de um jeito que incomoda. As duas coisas.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting/2
    en  Large, and clumsy with it, and I mind that more than I let on.
    >>  ............................................
    pt  Grande, e desajeitada, e isso me incomoda mais do que eu demonstro.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.expecting/3
    en  Two of us in here. I talk to her at night when nobody can hear me being foolish.
    >>  ............................................
    pt  Somos duas aqui dentro. Falo com ela à noite, quando ninguém me ouve ser boba.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting/1
    en  Tired. Not badly.
    >>  ............................................
    pt  Cansada. Não muito.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting/2
    en  Large. Hungry.
    >>  ............................................
    pt  Grande. Com fome.
    >>  ............................................
  shy.dialogue.conversations.noticed.expecting/3
    en  Two of us in here now.
    >>  ............................................
    pt  Somos duas aqui dentro agora.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind! Ask again on Friday and you may get a different answer.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda! Pergunte na sexta e talvez mude.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting/2
    en  Large. Magnificently, inconveniently large, and hungry at hours with no names.
    >>  ............................................
    pt  Grande. Magnificamente, inconvenientemente grande, e com fome em horas sem nome.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.expecting/3
    en  Two of us in here and only one of us sleeps. I'll let you guess which, %1$s.
    >>  ............................................
    pt  Somos duas aqui dentro e só uma dorme. Adivinhe qual, %1$s.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting/1
    en  Tired in a way I don't mind! Ask again on Friday and you may get a different answer.
    >>  ............................................
    pt  Cansada de um jeito que não me incomoda! Pergunte na sexta e talvez mude.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting/2
    en  Large. Magnificently, inconveniently large, and hungry at hours with no names.
    >>  ............................................
    pt  Grande. Magnificamente, inconvenientemente grande, e com fome em horas sem nome.
    >>  ............................................
  witty.dialogue.conversations.noticed.expecting/3
    en  Two of us in here and only one of us sleeps. I'll let you guess which, %1$s.
    >>  ............................................
    pt  Somos duas aqui dentro e só uma dorme. Adivinhe qual, %1$s.
    >>  ............................................
```

</details>


**Outcome 7 of 17** — base weight `0`

- Fires when: weighted +120 when has the memory `mcaconversations.state.proud` (this player only)
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.annoyed` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `proud` budget `standard`
- Then opens: `conversations.topic.noticed.proud.respond`
- …where the player's next choices will be: "What have I done, exactly?" | "Who have you been telling?" | "I don't know what to do with that." | "It was nothing. Forget it." | "Right, then."

```text
POOL   dialogue key: dialogue.conversations.noticed.proud
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.proud.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.proud.open`: the villager celebrates. Subject `noticed.pride`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `noticed:proud` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.proud/1   [77 chars]
    en  Better than I've been in a while, and you're a good part of the reason, %1$s.
    >>  ............................................
    pt  Melhor do que estive em muito tempo, e você é boa parte do motivo, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.proud/2   [83 chars]
    en  Pleased. Quietly, and about something you did, which I'd rather say now than never.
    >>  ............................................
    pt  Contente. Em silêncio, e por algo que você fez, o que prefiro dizer agora a nunca.
    >>  ............................................
  dialogue.conversations.noticed.proud/3   [86 chars]
    en  Good. I've been telling people about that thing you finished, and I've not been brief.
    >>  ............................................
    pt  Bem. Ando falando às pessoas daquilo que você terminou, e não fui breve.
    >>  ............................................
```


**Outcome 8 of 17** — base weight `0`

- Fires when: weighted +110 when the mood is `depressed`
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.annoyed` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.elated` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `worn` budget `standard`
- Then opens: `conversations.topic.noticed.worn.respond`
- …where the player's next choices will be: "How long has it been like this?" | "Is there something I could take off you?" | "You don't have to have a reason for it." | "Everyone's tired. Get on with it." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.noticed.worn
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.worn.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.worn.open`: the villager disclose_problems. Subject `noticed.exhaustion`, polarity `negative`, invites followup, outcome `None`.
NOTE   this is the line that establishes `noticed:worn` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, curiosity, practical_help, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.worn/1   [55 chars]
    en  Tired. Not the kind that sleep fixes, before you offer.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, antes que você ofereça.
    >>  ............................................
  dialogue.conversations.noticed.worn/2   [76 chars]
    en  Worn through, if I'm honest. Nothing's wrong. I'm just at the end of myself.
    >>  ............................................
    pt  Gasto, se for sincero. Nada está errado. Eu só cheguei ao fim de mim.
    >>  ............................................
  dialogue.conversations.noticed.worn/3   [76 chars]
    en  I've been better, %1$s, and I couldn't point at one thing and say that's it.
    >>  ............................................
    pt  Já estive melhor, %1$s, e não conseguiria apontar uma coisa e dizer que é isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. I've stopped hoping the mornings will help.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Parei de esperar que as manhãs ajudem.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn/2
    en  Worn through, and nothing's wrong, and that's somehow harder to say than a reason.
    >>  ............................................
    pt  Gasto, e nada está errado, e de algum modo é mais difícil dizer isso que um motivo.
    >>  ............................................
  anxious.dialogue.conversations.noticed.worn/3
    en  I've been better. I can't point at one thing, and I've been looking for months.
    >>  ............................................
    pt  Já estive melhor. Não consigo apontar uma coisa, e venho procurando há meses.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. That kind arrives somewhere past fifty.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Esse tipo chega passados os cinquenta.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. There doesn't have to be, at my age.
    >>  ............................................
    pt  Gasto. Nada está errado. Na minha idade, não precisa estar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.worn/3
    en  I've been better. I've also been worse, and both passed, which is worth knowing.
    >>  ............................................
    pt  Já estive melhor. Também já estive pior, e ambos passaram, o que vale saber.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind that sleep fixes, before you offer.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, antes que você ofereça.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. I'm just at the end of myself.
    >>  ............................................
    pt  Gasto. Nada está errado. Eu só cheguei ao fim de mim.
    >>  ............................................
  confident.dialogue.conversations.noticed.worn/3
    en  I've been better, and I couldn't point at one thing and say that's it.
    >>  ............................................
    pt  Já estive melhor, e não conseguiria apontar uma coisa e dizer que é isso.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind that sleep fixes, before you offer.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, antes que você ofereça.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. I'm just at the end of myself.
    >>  ............................................
    pt  Gasto. Nada está errado. Eu só cheguei ao fim de mim.
    >>  ............................................
  crabby.dialogue.conversations.noticed.worn/3
    en  I've been better, and I couldn't point at one thing and say that's it.
    >>  ............................................
    pt  Já estive melhor, e não conseguiria apontar uma coisa e dizer que é isso.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn/1
    en  Tired, %1$s. Not the kind sleep fixes, so don't offer me an early night.
    >>  ............................................
    pt  Cansado, %1$s. Não do tipo que o sono resolve, então não me ofereça dormir cedo.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn/2
    en  Worn through. I'd not have said that to the last three people who asked.
    >>  ............................................
    pt  Gasto. Não teria dito isso às últimas três pessoas que perguntaram.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.worn/3
    en  I've been better. You'll be wanting a reason and I haven't got one.
    >>  ............................................
    pt  Já estive melhor. Você vai querer um motivo e eu não tenho.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn/1
    en  Tired, %1$s. Not the kind sleep fixes, so don't offer me an early night.
    >>  ............................................
    pt  Cansado, %1$s. Não do tipo que o sono resolve, então não me ofereça dormir cedo.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn/2
    en  Worn through. I'd not have said that to the last three people who asked.
    >>  ............................................
    pt  Gasto. Não teria dito isso às últimas três pessoas que perguntaram.
    >>  ............................................
  flirty.dialogue.conversations.noticed.worn/3
    en  I've been better. You'll be wanting a reason and I haven't got one.
    >>  ............................................
    pt  Já estive melhor. Você vai querer um motivo e eu não tenho.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn/1
    en  Tired, %1$s. Not the kind sleep fixes, so don't offer me an early night.
    >>  ............................................
    pt  Cansado, %1$s. Não do tipo que o sono resolve, então não me ofereça dormir cedo.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn/2
    en  Worn through. I'd not have said that to the last three people who asked.
    >>  ............................................
    pt  Gasto. Não teria dito isso às últimas três pessoas que perguntaram.
    >>  ............................................
  friendly.dialogue.conversations.noticed.worn/3
    en  I've been better. You'll be wanting a reason and I haven't got one.
    >>  ............................................
    pt  Já estive melhor. Você vai querer um motivo e eu não tenho.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. I've stopped hoping the mornings will help.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Parei de esperar que as manhãs ajudem.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn/2
    en  Worn through, and nothing's wrong, and that's somehow harder to say than a reason.
    >>  ............................................
    pt  Gasto, e nada está errado, e de algum modo é mais difícil dizer isso que um motivo.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.worn/3
    en  I've been better. I can't point at one thing, and I've been looking for months.
    >>  ............................................
    pt  Já estive melhor. Não consigo apontar uma coisa, e venho procurando há meses.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind that sleep fixes, before you offer.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, antes que você ofereça.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. I'm just at the end of myself.
    >>  ............................................
    pt  Gasto. Nada está errado. Eu só cheguei ao fim de mim.
    >>  ............................................
  greedy.dialogue.conversations.noticed.worn/3
    en  I've been better, and I couldn't point at one thing and say that's it.
    >>  ............................................
    pt  Já estive melhor, e não conseguiria apontar uma coisa e dizer que é isso.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind that sleep fixes, before you offer.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, antes que você ofereça.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. I'm just at the end of myself.
    >>  ............................................
    pt  Gasto. Nada está errado. Eu só cheguei ao fim de mim.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.worn/3
    en  I've been better, and I couldn't point at one thing and say that's it.
    >>  ............................................
    pt  Já estive melhor, e não conseguiria apontar uma coisa e dizer que é isso.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn/1
    en  Tired. Not that kind.
    >>  ............................................
    pt  Cansado. Não desse tipo.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn/2
    en  Worn through.
    >>  ............................................
    pt  Gasto.
    >>  ............................................
  introverted.dialogue.conversations.noticed.worn/3
    en  I've been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. That kind arrives somewhere past fifty.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Esse tipo chega passados os cinquenta.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. There doesn't have to be, at my age.
    >>  ............................................
    pt  Gasto. Nada está errado. Na minha idade, não precisa estar.
    >>  ............................................
  lazy.dialogue.conversations.noticed.worn/3
    en  I've been better. I've also been worse, and both passed, which is worth knowing.
    >>  ............................................
    pt  Já estive melhor. Também já estive pior, e ambos passaram, o que vale saber.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn/1
    en  Tired. Not that kind.
    >>  ............................................
    pt  Cansado. Não desse tipo.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn/2
    en  Worn through.
    >>  ............................................
    pt  Gasto.
    >>  ............................................
  odd.dialogue.conversations.noticed.worn/3
    en  I've been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. That kind arrives somewhere past fifty.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Esse tipo chega passados os cinquenta.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. There doesn't have to be, at my age.
    >>  ............................................
    pt  Gasto. Nada está errado. Na minha idade, não precisa estar.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.worn/3
    en  I've been better. I've also been worse, and both passed, which is worth knowing.
    >>  ............................................
    pt  Já estive melhor. Também já estive pior, e ambos passaram, o que vale saber.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn/1
    en  Tired. Not the sleep-fixes-it kind, so don't waste a good suggestion on me.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, então não gaste uma boa sugestão comigo.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn/2
    en  Worn through! Nothing's wrong, which is somehow the annoying part.
    >>  ............................................
    pt  Gasto! Nada está errado, o que de alguma forma é a parte irritante.
    >>  ............................................
  peppy.dialogue.conversations.noticed.worn/3
    en  I've been better, and I can't point at one thing, which spoils the story entirely.
    >>  ............................................
    pt  Já estive melhor, e não consigo apontar uma coisa, o que estraga a história.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn/1
    en  Tired. Not the sleep-fixes-it kind, so don't waste a good suggestion on me.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, então não gaste uma boa sugestão comigo.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn/2
    en  Worn through! Nothing's wrong, which is somehow the annoying part.
    >>  ............................................
    pt  Gasto! Nada está errado, o que de alguma forma é a parte irritante.
    >>  ............................................
  playful.dialogue.conversations.noticed.worn/3
    en  I've been better, and I can't point at one thing, which spoils the story entirely.
    >>  ............................................
    pt  Já estive melhor, e não consigo apontar uma coisa, o que estraga a história.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. That kind arrives somewhere past fifty.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Esse tipo chega passados os cinquenta.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn/2
    en  Worn through. Nothing's wrong. There doesn't have to be, at my age.
    >>  ............................................
    pt  Gasto. Nada está errado. Na minha idade, não precisa estar.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.worn/3
    en  I've been better. I've also been worse, and both passed, which is worth knowing.
    >>  ............................................
    pt  Já estive melhor. Também já estive pior, e ambos passaram, o que vale saber.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn/1
    en  Tired. Not the kind sleep fixes. I've stopped hoping the mornings will help.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve. Parei de esperar que as manhãs ajudem.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn/2
    en  Worn through, and nothing's wrong, and that's somehow harder to say than a reason.
    >>  ............................................
    pt  Gasto, e nada está errado, e de algum modo é mais difícil dizer isso que um motivo.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.worn/3
    en  I've been better. I can't point at one thing, and I've been looking for months.
    >>  ............................................
    pt  Já estive melhor. Não consigo apontar uma coisa, e venho procurando há meses.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn/1
    en  Tired. Not that kind.
    >>  ............................................
    pt  Cansado. Não desse tipo.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn/2
    en  Worn through.
    >>  ............................................
    pt  Gasto.
    >>  ............................................
  shy.dialogue.conversations.noticed.worn/3
    en  I've been better.
    >>  ............................................
    pt  Já estive melhor.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn/1
    en  Tired. Not the sleep-fixes-it kind, so don't waste a good suggestion on me.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, então não gaste uma boa sugestão comigo.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn/2
    en  Worn through! Nothing's wrong, which is somehow the annoying part.
    >>  ............................................
    pt  Gasto! Nada está errado, o que de alguma forma é a parte irritante.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.worn/3
    en  I've been better, and I can't point at one thing, which spoils the story entirely.
    >>  ............................................
    pt  Já estive melhor, e não consigo apontar uma coisa, o que estraga a história.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn/1
    en  Tired. Not the sleep-fixes-it kind, so don't waste a good suggestion on me.
    >>  ............................................
    pt  Cansado. Não do tipo que o sono resolve, então não gaste uma boa sugestão comigo.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn/2
    en  Worn through! Nothing's wrong, which is somehow the annoying part.
    >>  ............................................
    pt  Gasto! Nada está errado, o que de alguma forma é a parte irritante.
    >>  ............................................
  witty.dialogue.conversations.noticed.worn/3
    en  I've been better, and I can't point at one thing, which spoils the story entirely.
    >>  ............................................
    pt  Já estive melhor, e não consigo apontar uma coisa, o que estraga a história.
    >>  ............................................
```

</details>


**Outcome 9 of 17** — base weight `0`

- Fires when: weighted +90 when the relationship band is one of `stranger`, `tense`, `hostile`
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.annoyed` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.elated` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.proud` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `guarded` budget `standard`
- Then opens: `conversations.topic.noticed.guarded.respond`
- …where the player's next choices will be: "I won't push. I only wondered." | "Ask me something easier, then." | "I'll go first, if that helps." | "Come on. What's really going on with you?" | "Of course. Good day."

```text
POOL   dialogue key: dialogue.conversations.noticed.guarded
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.guarded.open`: the villager deflects. Subject `noticed.guarded`, polarity `neutral`, guarded, outcome `None`.
NOTE   this is the line that establishes `noticed:guarded` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: restraint, curiosity, candor, self_disclosure, boundary_push, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.guarded/1   [37 chars]
    en  Well enough, thank you. And yourself?
    >>  ............................................
    pt  Bem o bastante, obrigado. E você?
    >>  ............................................
  dialogue.conversations.noticed.guarded/2   [55 chars]
    en  Fine. It's not a question I'd usually answer at length.
    >>  ............................................
    pt  Bem. Não é uma pergunta que eu costume responder demoradamente.
    >>  ............................................
  dialogue.conversations.noticed.guarded/3   [75 chars]
    en  Same as ever. That's most of what I give out to people I've not known long.
    >>  ............................................
    pt  Como sempre. É quase tudo que eu dou a quem conheço há pouco.
    >>  ............................................
```


**Outcome 10 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.grieving` (villager-wide)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `grieving` budget `standard`
- Then opens: `conversations.topic.noticed.grieving.respond`
- …where the player's next choices will be: "You're allowed to feel that." | "I'll not make you talk about it." | "People die. That's life." | "I'm sorry. I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.grieving.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.open`: the villager discloses. Subject `noticed.grief`, polarity `acute`, guarded, outcome `None`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, restraint, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.grieving/1   [108 chars]
    en  Ah. You heard, then. We lost one of our own. The whole village feels the gap. ...Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Então você soube. Perdemos um dos nossos. O vilarejo inteiro sente a falta. ...Obrigado por perguntar, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.grieving/2   [103 chars]
    en  It's been a mournful few days here. Someone's chair sits empty. I keep expecting them round the corner.
    >>  ............................................
    pt  Foram uns dias de luto por aqui. A cadeira de alguém está vazia. Fico esperando ver a pessoa virar a esquina.
    >>  ............................................
  dialogue.conversations.noticed.grieving/3   [104 chars]
    en  There's grief in the streets just now. We hold each other up when it comes. Kind of you to notice, %1$s.
    >>  ............................................
    pt  Tem luto nas ruas agora. A gente se ampara quando isso acontece. Gentileza sua reparar, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard. We lost one of our own, %1$s. Thank you for asking.
    >>  ............................................
    pt  Ah. Você soube. Perdemos um dos nossos, %1$s. Obrigado por perguntar.
    >>  ............................................
  anxious.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. I've said that sentence eleven times this week and it hasn't got easier.
    >>  ............................................
    pt  Perdemos alguém. Já disse essa frase onze vezes esta semana e não ficou mais fácil.
    >>  ............................................
  anxious.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you. Most people cross the lane rather than ask.
    >>  ............................................
    pt  Você soube. Obrigado. A maioria atravessa a rua em vez de perguntar.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. It'll be a long while.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Vai ser longo.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. These things take the years they take and this one has only started.
    >>  ............................................
    pt  Perdemos alguém. Essas coisas levam os anos que levam e essa mal começou.
    >>  ............................................
  athletic.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking. There's not much else to be done but ask.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar. Não há muito mais a fazer além de perguntar.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving/1
    en  You heard, then. We lost one of our own. Thank you for asking.
    >>  ............................................
    pt  Você soube, então. Perdemos um dos nossos. Obrigado por perguntar.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The whole village feels the gap. That's the whole of it.
    >>  ............................................
    pt  Perdemos alguém. O vilarejo inteiro sente a falta. É tudo.
    >>  ............................................
  confident.dialogue.conversations.noticed.grieving/3
    en  Aye, you heard. One of ours. I'll not say more than that today.
    >>  ............................................
    pt  É, você soube. Um dos nossos. Não vou dizer mais que isso hoje.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving/1
    en  You heard, then. We lost one of our own. Thank you for asking.
    >>  ............................................
    pt  Você soube, então. Perdemos um dos nossos. Obrigado por perguntar.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The whole village feels the gap. That's the whole of it.
    >>  ............................................
    pt  Perdemos alguém. O vilarejo inteiro sente a falta. É tudo.
    >>  ............................................
  crabby.dialogue.conversations.noticed.grieving/3
    en  Aye, you heard. One of ours. I'll not say more than that today.
    >>  ............................................
    pt  É, você soube. Um dos nossos. Não vou dizer mais que isso hoje.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. The whole village feels the gap. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. O vilarejo inteiro sente a falta. Obrigado por perguntar, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving/2
    en  You heard. Sit with me a moment. It's better than standing about with it.
    >>  ............................................
    pt  Você soube. Sente comigo um momento. É melhor que ficar de pé com isso.
    >>  ............................................
  extroverted.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. I'm glad it's you who came and asked, and I'd not have said that a month ago.
    >>  ............................................
    pt  Perdemos alguém. Fico contente que tenha sido você a vir perguntar, e eu não teria dito isso um mês atrás.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. The whole village feels the gap. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. O vilarejo inteiro sente a falta. Obrigado por perguntar, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving/2
    en  You heard. Sit with me a moment. It's better than standing about with it.
    >>  ............................................
    pt  Você soube. Sente comigo um momento. É melhor que ficar de pé com isso.
    >>  ............................................
  flirty.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. I'm glad it's you who came and asked, and I'd not have said that a month ago.
    >>  ............................................
    pt  Perdemos alguém. Fico contente que tenha sido você a vir perguntar, e eu não teria dito isso um mês atrás.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. The whole village feels the gap. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. O vilarejo inteiro sente a falta. Obrigado por perguntar, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving/2
    en  You heard. Sit with me a moment. It's better than standing about with it.
    >>  ............................................
    pt  Você soube. Sente comigo um momento. É melhor que ficar de pé com isso.
    >>  ............................................
  friendly.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. I'm glad it's you who came and asked, and I'd not have said that a month ago.
    >>  ............................................
    pt  Perdemos alguém. Fico contente que tenha sido você a vir perguntar, e eu não teria dito isso um mês atrás.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard. We lost one of our own, %1$s. Thank you for asking.
    >>  ............................................
    pt  Ah. Você soube. Perdemos um dos nossos, %1$s. Obrigado por perguntar.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. I've said that sentence eleven times this week and it hasn't got easier.
    >>  ............................................
    pt  Perdemos alguém. Já disse essa frase onze vezes esta semana e não ficou mais fácil.
    >>  ............................................
  gloomy.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you. Most people cross the lane rather than ask.
    >>  ............................................
    pt  Você soube. Obrigado. A maioria atravessa a rua em vez de perguntar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving/1
    en  You heard, then. We lost one of our own. Thank you for asking.
    >>  ............................................
    pt  Você soube, então. Perdemos um dos nossos. Obrigado por perguntar.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The whole village feels the gap. That's the whole of it.
    >>  ............................................
    pt  Perdemos alguém. O vilarejo inteiro sente a falta. É tudo.
    >>  ............................................
  greedy.dialogue.conversations.noticed.grieving/3
    en  Aye, you heard. One of ours. I'll not say more than that today.
    >>  ............................................
    pt  É, você soube. Um dos nossos. Não vou dizer mais que isso hoje.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving/1
    en  You heard, then. We lost one of our own. Thank you for asking.
    >>  ............................................
    pt  Você soube, então. Perdemos um dos nossos. Obrigado por perguntar.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The whole village feels the gap. That's the whole of it.
    >>  ............................................
    pt  Perdemos alguém. O vilarejo inteiro sente a falta. É tudo.
    >>  ............................................
  grumpy.dialogue.conversations.noticed.grieving/3
    en  Aye, you heard. One of ours. I'll not say more than that today.
    >>  ............................................
    pt  É, você soube. Um dos nossos. Não vou dizer mais que isso hoje.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The gap is everywhere.
    >>  ............................................
    pt  Perdemos alguém. A falta está em todo lugar.
    >>  ............................................
  introverted.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. It'll be a long while.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Vai ser longo.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. These things take the years they take and this one has only started.
    >>  ............................................
    pt  Perdemos alguém. Essas coisas levam os anos que levam e essa mal começou.
    >>  ............................................
  lazy.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking. There's not much else to be done but ask.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar. Não há muito mais a fazer além de perguntar.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The gap is everywhere.
    >>  ............................................
    pt  Perdemos alguém. A falta está em todo lugar.
    >>  ............................................
  odd.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. It'll be a long while.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Vai ser longo.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. These things take the years they take and this one has only started.
    >>  ............................................
    pt  Perdemos alguém. Essas coisas levam os anos que levam e essa mal começou.
    >>  ............................................
  peaceful.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking. There's not much else to be done but ask.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar. Não há muito mais a fazer além de perguntar.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Obrigado por perguntar, %1$s.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving/2
    en  You heard. One of ours. There's no light way to say that and I've stopped looking for one.
    >>  ............................................
    pt  Você soube. Um dos nossos. Não há jeito leve de dizer e eu parei de procurar um.
    >>  ............................................
  peppy.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. The gap is the size of the whole village. Thank you for coming by.
    >>  ............................................
    pt  Perdemos alguém. A falta é do tamanho do vilarejo inteiro. Obrigado por vir.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Obrigado por perguntar, %1$s.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving/2
    en  You heard. One of ours. There's no light way to say that and I've stopped looking for one.
    >>  ............................................
    pt  Você soube. Um dos nossos. Não há jeito leve de dizer e eu parei de procurar um.
    >>  ............................................
  playful.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. The gap is the size of the whole village. Thank you for coming by.
    >>  ............................................
    pt  Perdemos alguém. A falta é do tamanho do vilarejo inteiro. Obrigado por vir.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. It'll be a long while.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Vai ser longo.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. These things take the years they take and this one has only started.
    >>  ............................................
    pt  Perdemos alguém. Essas coisas levam os anos que levam e essa mal começou.
    >>  ............................................
  relaxed.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking. There's not much else to be done but ask.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar. Não há muito mais a fazer além de perguntar.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard. We lost one of our own, %1$s. Thank you for asking.
    >>  ............................................
    pt  Ah. Você soube. Perdemos um dos nossos, %1$s. Obrigado por perguntar.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. I've said that sentence eleven times this week and it hasn't got easier.
    >>  ............................................
    pt  Perdemos alguém. Já disse essa frase onze vezes esta semana e não ficou mais fácil.
    >>  ............................................
  sensitive.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you. Most people cross the lane rather than ask.
    >>  ............................................
    pt  Você soube. Obrigado. A maioria atravessa a rua em vez de perguntar.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving/2
    en  We lost somebody. The gap is everywhere.
    >>  ............................................
    pt  Perdemos alguém. A falta está em todo lugar.
    >>  ............................................
  shy.dialogue.conversations.noticed.grieving/3
    en  You heard. Thank you for asking.
    >>  ............................................
    pt  Você soube. Obrigado por perguntar.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Obrigado por perguntar, %1$s.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving/2
    en  You heard. One of ours. There's no light way to say that and I've stopped looking for one.
    >>  ............................................
    pt  Você soube. Um dos nossos. Não há jeito leve de dizer e eu parei de procurar um.
    >>  ............................................
  upbeat.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. The gap is the size of the whole village. Thank you for coming by.
    >>  ............................................
    pt  Perdemos alguém. A falta é do tamanho do vilarejo inteiro. Obrigado por vir.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving/1
    en  Ah. You heard, then. We lost one of our own. Thank you for asking, %1$s.
    >>  ............................................
    pt  Ah. Você soube, então. Perdemos um dos nossos. Obrigado por perguntar, %1$s.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving/2
    en  You heard. One of ours. There's no light way to say that and I've stopped looking for one.
    >>  ............................................
    pt  Você soube. Um dos nossos. Não há jeito leve de dizer e eu parei de procurar um.
    >>  ............................................
  witty.dialogue.conversations.noticed.grieving/3
    en  We lost somebody. The gap is the size of the whole village. Thank you for coming by.
    >>  ............................................
    pt  Perdemos alguém. A falta é do tamanho do vilarejo inteiro. Obrigado por vir.
    >>  ............................................
```

</details>


**Outcome 11 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.annoyed` (this player only)
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `annoyed` budget `standard`
- Then opens: `conversations.topic.noticed.annoyed.respond`
- …where the player's next choices will be: "I'm sorry for what I did." | "Let me explain myself." | "You're still on about that?" | "I'll give you room."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.annoyed.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.open`: the villager complains. Subject `noticed.player_conflict`, polarity `negative`, permits followup, outcome `None`.
NOTE   this is the line that establishes `state:annoyed`, `cause:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, dismissal, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.annoyed/1   [38 chars]
    en  You. Right. What is it you want, %1$s?
    >>  ............................................
    pt  Você. Certo. O que você quer, %1$s?
    >>  ............................................
  dialogue.conversations.noticed.annoyed/2   [42 chars]
    en  I'm still sore about it, if you must know.
    >>  ............................................
    pt  Ainda estou magoado com aquilo, se quer saber.
    >>  ............................................
  dialogue.conversations.noticed.annoyed/3   [53 chars]
    en  In myself? Irritated. Mostly at you, since you asked.
    >>  ............................................
    pt  Comigo? Irritado. Principalmente com você, já que perguntou.
    >>  ............................................
```


**Outcome 12 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.elated` (villager-wide)
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.annoyed` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `elated` budget `standard`
- Then opens: `conversations.topic.noticed.elated.respond`
- …where the player's next choices will be: "It's good to see you like this." | "What's brought this on?" | "Don't get carried away." | "Enjoy it. I'll go."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.elated.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.open`: the villager celebrates. Subject `noticed.elation`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `state:elated` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, dismissal, humor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.elated/1   [97 chars]
    en  Oh, it's a good few days! There's been happy news in the village — you can feel it in the square.
    >>  ............................................
    pt  Ah, são uns dias bons! Teve notícia feliz no vilarejo — dá pra sentir na praça.
    >>  ............................................
  dialogue.conversations.noticed.elated/2   [97 chars]
    en  Everyone's lighter this week, %1$s. Good things have happened close to home, and the joy spreads.
    >>  ............................................
    pt  Todo mundo está mais leve essa semana, %1$s. Aconteceram coisas boas aqui perto, e a alegria se espalha.
    >>  ............................................
  dialogue.conversations.noticed.elated/3   [110 chars]
    en  Can't you feel it? The whole village is grinning about something. I've caught it too, and I'm not fighting it.
    >>  ............................................
    pt  Não dá pra sentir? O vilarejo inteiro está sorrindo por causa de alguma coisa. Eu peguei também, e não estou resistindo.
    >>  ............................................
```


**Outcome 13 of 17** — base weight `0`

- Fires when: weighted +1 when the relationship band is one of 
- Fires when: RULED OUT when has the memory `mcaconversations.state.grieving` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.annoyed` (this player only)  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.state.elated` (villager-wide)  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `noticed` branch `fine` budget `standard`
- Then opens: `conversations.topic.noticed.fine.respond`
- …where the player's next choices will be: "Steady's not nothing." | "Really, though?" | "Not much of an answer." | "You're not fine and we both know it." | "Fair. I'll get on."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.topic.noticed.fine.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.open`: the villager reports. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `None`.
NOTE   this is the line that establishes `state:steady` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: encouragement, curiosity, dismissal, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.noticed.fine/1   [82 chars]
    en  Same as ever, thanks for asking. Ups and downs, mostly evens. That's village life.
    >>  ............................................
    pt  Como sempre, obrigado por perguntar. Altos e baixos, quase tudo no meio. É a vida de vilarejo.
    >>  ............................................
  dialogue.conversations.noticed.fine/2   [75 chars]
    en  Steady enough. No news worth the word, which is its own kind of good, %1$s.
    >>  ............................................
    pt  Bem estável. Nenhuma notícia que valha a palavra, o que já é um tipo de coisa boa, %1$s.
    >>  ............................................
  dialogue.conversations.noticed.fine/3   [93 chars]
    en  Oh, plodding along. The bell rings, the bread bakes, I complain, I sleep. Comforting, really.
    >>  ............................................
    pt  Ah, levando. O sino toca, o pão assa, eu reclamo, eu durmo. Reconfortante, na verdade.
    >>  ............................................
```


**Outcome 14 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.grieving` (villager-wide)
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) permanently
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.grieving
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.grieving.open.legacy`: the villager discloses. Subject `noticed.grief`, polarity `acute`, guarded, outcome `None`.
NOTE   this is the line that establishes `state:grieving`, `loss:recent` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.cat.events` / button `noticed`** earlier in this file. Fill it in there, once.


**Outcome 15 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.annoyed` (this player only)
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) permanently
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.annoyed
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.annoyed.open.legacy`: the villager complains. Subject `noticed.player_conflict`, polarity `negative`, permits followup, outcome `None`.
NOTE   this is the line that establishes `state:annoyed`, `cause:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.cat.events` / button `noticed`** earlier in this file. Fill it in there, once.


**Outcome 16 of 17** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.state.elated` (villager-wide)
- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) permanently
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.elated
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.elated.open.legacy`: the villager celebrates. Subject `noticed.elation`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `state:elated` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.cat.events` / button `noticed`** earlier in this file. Fill it in there, once.


**Outcome 17 of 17** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 1
- Does: session `turn`
- Does: remembers `mcaconversations.cooldown.noticed` (this player only) permanently
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.noticed.fine
WHO    VILLAGER — what the player reads after pressing "How have you been, in yourself?"
       spoken on: conversations.cat.events, button `noticed`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `noticed.fine.open.legacy`: the villager reports. Subject `noticed.wellbeing`, polarity `neutral`, permits followup, outcome `None`.
NOTE   this is the line that establishes `state:steady` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

> Written out in full under **`conversations.cat.events` / button `noticed`** earlier in this file. Fill it in there, once.


### Button `shared_history` — "What have we been through?"

Shown only when MCA's own constraints hold: `"adult"`

```text
POOL   dialogue key: dialogue.conversations.cat.events.shared_history
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.events.shared_history   [26 chars]
    en  What have we been through?
    >>  ............................................
    pt  Pelo que já passamos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.shared_history.the_thing_we_avoid"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.shared_history.the_thing_we_avoid", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `shared_history` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.shared_history` (this player only) for 36000 ticks
- Then opens: `conversations.scene.shared_history.the_thing_we_avoid.respond`
- …where the player's next choices will be: "Say it. I'd rather hear it." | "We can leave that one buried." | "So we have."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_thing_we_avoid
WHO    VILLAGER — what the player reads after pressing "What have we been through?"
       spoken on: conversations.cat.events, button `shared_history`
       leaves the player on: conversations.scene.shared_history.the_thing_we_avoid.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_thing_we_avoid.open`: the villager reports. Subject `shared_history.unmentioned`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_thing_we_avoid/1   [111 chars]
    en  There is one thing between us that we have both agreed to walk past, and neither of us ever agreed to it aloud.
    >>  ............................................
    pt  Existe uma coisa entre nós pela qual nós dois concordamos em passar reto, e nenhum de nós concordou em voz alta.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid/2   [106 chars]
    en  We have never gone back over that afternoon, and I have decided that is a choice rather than an oversight.
    >>  ............................................
    pt  Nunca revisitamos aquela tarde, e eu decidi que isso é uma escolha e não um descuido.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_thing_we_avoid/3   [103 chars]
    en  I am going to say the thing we do not say, and then I am going to let you decide whether we keep going.
    >>  ............................................
    pt  Vou dizer a coisa que a gente não diz, e depois vou deixar você decidir se continuamos.
    >>  ............................................
```


**Outcome 2 of 4** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.shared_history.the_date_nobody_marks"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.shared_history.the_date_nobody_marks", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `shared_history` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.shared_history` (this player only) for 36000 ticks
- Then opens: `conversations.scene.shared_history.the_date_nobody_marks.respond`
- …where the player's next choices will be: "How long has it been?" | "That deserves marking." | "So we have."

```text
POOL   dialogue key: dialogue.conversations.scene.shared_history.the_date_nobody_marks
WHO    VILLAGER — what the player reads after pressing "What have we been through?"
       spoken on: conversations.cat.events, button `shared_history`
       leaves the player on: conversations.scene.shared_history.the_date_nobody_marks.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.the_date_nobody_marks.open`: the villager reminisces. Subject `shared_history.unmarked_date`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.shared_history.the_date_nobody_marks/1   [103 chars]
    en  It has been long enough now that there is a date I quietly count from, and nobody else knows it exists.
    >>  ............................................
    pt  Já faz tempo o bastante para existir uma data da qual eu conto em silêncio, e ninguém mais sabe que ela existe.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks/2   [97 chars]
    en  I know how long you have been coming here to the day, and I have never once mentioned the number.
    >>  ............................................
    pt  Eu sei há quantos dias exatos você vem aqui, e nunca mencionei o número nenhuma vez.
    >>  ............................................
  dialogue.conversations.scene.shared_history.the_date_nobody_marks/3   [108 chars]
    en  Villages mark harvests and funerals. It has occurred to no one to mark the day a stranger stopped being one.
    >>  ............................................
    pt  As vilas marcam colheitas e enterros. Ninguém nunca pensou em marcar o dia em que um estranho deixou de ser.
    >>  ............................................
```


**Outcome 3 of 4** — base weight `800`

- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.shared_history.the_thing_we_avoid"}  _(chance -5000)_
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.shared_history.the_date_nobody_marks"}  _(chance -5000)_
- Does: session `begin` topic `shared_history` branch `funnel` budget `quick`
- Then opens: `conversations.topic.shared_history.open.respond`
- …where the player's next choices will be: "What's on the list?" | "It's been good, on balance." | "Keeping score isn't for me." | "So we have."

```text
POOL   dialogue key: dialogue.conversations.shared_history.open
WHO    VILLAGER — what the player reads after pressing "What have we been through?"
       spoken on: conversations.cat.events, button `shared_history`
       leaves the player on: conversations.topic.shared_history.open.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.open`: the villager reports. Subject `shared_history.the_ledger`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:shared_history` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, empathy, dismissal, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.shared_history.open/1   [100 chars]
    en  I keep a rough account of it in my head, and there are more entries on your side of it than on mine.
    >>  ............................................
    pt  Guardo uma conta aproximada disso na cabeça, e tem mais entradas do seu lado do que do meu.
    >>  ............................................
  dialogue.conversations.shared_history.open/2   [111 chars]
    en  Two bad weeks, one very good afternoon, and a great many days where nothing happened and you were there anyway.
    >>  ............................................
    pt  Duas semanas ruins, uma tarde muito boa, e um monte de dias em que nada aconteceu e você estava lá do mesmo jeito.
    >>  ............................................
  dialogue.conversations.shared_history.open/3   [98 chars]
    en  It is a longer list than either of us would say out loud, and most of it is the unremarkable kind.
    >>  ............................................
    pt  É uma lista mais longa do que qualquer um de nós diria em voz alta, e quase tudo é do tipo banal.
    >>  ............................................
```


**Outcome 4 of 4** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: nothing — this reply neither costs nor pays anything
- Then opens: `conversations.cat.events`
- …where the player's next choices will be: "Anything happen around here lately?" | "How have you been, in yourself?" | "What have we been through?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.shared_history.legacy
WHO    VILLAGER — what the player reads after pressing "What have we been through?"
       spoken on: conversations.cat.events, button `shared_history`
       leaves the player on: conversations.cat.events
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `shared_history.legacy`: the villager reports. Subject `shared_history.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.shared_history.legacy/1   [79 chars]
    en  A fair amount, by now. More than I would have guessed when you first turned up.
    >>  ............................................
    pt  Bastante coisa, a esta altura. Mais do que eu teria imaginado quando você apareceu.
    >>  ............................................
  dialogue.conversations.shared_history.legacy/2   [106 chars]
    en  We have had a couple of bad days and rather more ordinary ones, which is what a record ought to look like.
    >>  ............................................
    pt  Tivemos alguns dias ruins e bem mais dias comuns, que é como um histórico deve ser.
    >>  ............................................
  dialogue.conversations.shared_history.legacy/3   [81 chars]
    en  Enough that I would notice the gap if you went. That is the honest measure of it.
    >>  ............................................
    pt  O bastante para eu notar a falta se você fosse embora. É a medida honesta disso.
    >>  ............................................
```


### Button `back` — "Something else."

```text
POOL   dialogue key: dialogue.conversations.cat.events.back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.events
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.events.back   [15 chars]
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


## `conversations.cat.personal`

**Reached from 297 route(s):** `conversations.arc.dreams.resume.followup` / `leave`; `conversations.arc.dreams.resume.respond` / `doubt`; `conversations.arc.dreams.resume.respond` / `leave`; `conversations.arc.fears.followthrough.followup` / `leave`; `conversations.arc.fears.followthrough.respond` / `recall_promise`; `conversations.arc.fears.followthrough.respond` / `recall_promise`; `conversations.arc.fears.followthrough.respond` / `recall_promise`; `conversations.arc.fears.followthrough.respond` / `leave`; `conversations.arc.fears.plan.followup` / `leave`; `conversations.arc.fears.plan.respond` / `leave`; `conversations.arc.feelings.resume.followup` / `leave`; `conversations.arc.feelings.resume.respond` / `leave` …and 285 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.dreams.again.apologize` — e.g. "It's alright. Saying it twice in a day makes it sound less likely, that's all."
- `conversations.dreams.again.leave` — e.g. "Aye. Catch me later in the week."
- `conversations.dreams.again.press` — e.g. "Same one as this morning. It's not the sort of thing that gets a new version by noon."
- `conversations.dreams.close.confide` — e.g. "...Then we're two fools with plans, and that's a better number than one."
- `conversations.dreams.close.honest.confide` — e.g. "...Then we're two fools with plans and no obligations to each other. Perfect arrangement."
- `conversations.dreams.close.honest.leave` — e.g. "So do I. Off you go, and thank you for the honest version."
- `conversations.dreams.close.honest.say_means` — e.g. "...It did. More so knowing you'd not pretend to be more use than you are."
- `conversations.dreams.close.honest.thank` — e.g. "You're welcome. And you didn't dress it up as help you weren't going to give, which I'll remember."
- `conversations.dreams.close.leave` — e.g. "Aye. Go on, and thank you."
- `conversations.dreams.close.say_means` — e.g. "...It did. Wanting a thing out loud is how people find out you might fail at it."
- `conversations.dreams.close.thank` — e.g. "Don't thank me yet. Thank me if it ever turns into anything."
- `conversations.dreams.encourage.doubt` — e.g. "That's kind. But dreams cost coin, and mine's mostly spoken for."
- `conversations.dreams.encourage.glad` — e.g. "You think so? Then maybe I'll actually do it. Watch me, %1$s."
- `conversations.dreams.guarded.ask_safer` — e.g. "Ask me what I did today. That one's free and it's mostly fence posts."
- …and 238 more pools


```text
POOL   dialogue key: dialogue.conversations.cat.personal
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.cat.personal
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.cat.personal   [38 chars]
    en  Getting personal, are we? Go on, then.
    >>  ............................................
    pt  Ficando pessoal, é? Pode falar, então.
    >>  ............................................
```


### Button `life` — "Tell me about your life."

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.life` — accepted phrasings: "tell me about yourself"; "tell me about your life"; "your life"; "what is your story"; "tell me your story"
  - the message must contain one of: `life`, `yourself`, `story`, `background`
  - scored words: `life`(1.2), `yourself`(1.0), `story`(0.8), `background`(0.8), `living`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.life
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.life   [24 chars]
    en  Tell me about your life.
    >>  ............................................
    pt  Me conta sobre a sua vida.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.life.the_chapter_im_in"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.life.the_chapter_im_in", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 36000 ticks
- Then opens: `conversations.scene.life.the_chapter_im_in.respond`
- …where the player's next choices will be: "What changed in four years?" | "Steady sounds like an achievement." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.life.the_chapter_im_in
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.scene.life.the_chapter_im_in.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.the_chapter_im_in.open`: the villager reports. Subject `life.now`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.the_chapter_im_in/1   [114 chars]
    en  I am in the middle of something and I will not know what it was until it is over. That is most of a life, I think.
    >>  ............................................
    pt  Estou no meio de alguma coisa e só vou saber o que foi quando acabar. Acho que uma vida é quase toda assim.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in/2   [131 chars]
    en  Steady. I used to think steady was what happened to people who had given up, and now I know it is what happens to people who chose.
    >>  ............................................
    pt  Estável. Eu achava que estável era o que acontecia com quem desistiu, e agora sei que é o que acontece com quem escolheu.
    >>  ............................................
  dialogue.conversations.scene.life.the_chapter_im_in/3   [103 chars]
    en  Better than four years ago and less interesting, and I would take this trade every time you offered it.
    >>  ............................................
    pt  Melhor que quatro anos atrás e menos interessante, e eu aceitaria essa troca todas as vezes que você oferecesse.
    >>  ............................................
```


**Outcome 2 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.life.how_i_came_here"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.life.how_i_came_here", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 36000 ticks
- Then opens: `conversations.scene.life.how_i_came_here.respond`
- …where the player's next choices will be: "Where were you before?" | "This place is better for you being here." | "Thank you for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.life.how_i_came_here
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.scene.life.how_i_came_here.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.how_i_came_here.open`: the villager reminisces. Subject `life.origin`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:life` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.life.how_i_came_here/1   [83 chars]
    en  I did not choose this village. I chose to stop, and this is where I was when I did.
    >>  ............................................
    pt  Eu não escolhi esta vila. Escolhi parar, e era aqui que eu estava quando parei.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here/2   [90 chars]
    en  There was a road and then there was not, and I have been here ever since and mean to stay.
    >>  ............................................
    pt  Havia uma estrada e depois não havia mais, e estou aqui desde então e pretendo ficar.
    >>  ............................................
  dialogue.conversations.scene.life.how_i_came_here/3   [90 chars]
    en  It was a decision made by somebody else that I have spent a long time turning into my own.
    >>  ............................................
    pt  Foi uma decisão de outra pessoa que eu passei muito tempo transformando na minha.
    >>  ............................................
```


**Outcome 3 of 11** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.life` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `again` budget `deep`
- Then opens: `conversations.topic.deep.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.life.again
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.again.to.deep.again`: the villager accepts. Subject `deep.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.again/1   [56 chars]
    en  I just told you my whole story. Let it breathe a little.
    >>  ............................................
    pt  Acabei de te contar a minha história inteira. Deixa ela respirar um pouco.
    >>  ............................................
  dialogue.conversations.life.again/2   [66 chars]
    en  My whole biography twice in one week? Wait for the second edition.
    >>  ............................................
    pt  Minha biografia inteira duas vezes na mesma semana? Espera a segunda edição.
    >>  ............................................
  dialogue.conversations.life.again/3   [68 chars]
    en  You know the story now, %1$s. Nothing's happened since except lunch.
    >>  ............................................
    pt  Agora você sabe a história, %1$s. Não aconteceu nada desde então além do almoço.
    >>  ............................................
```


**Outcome 4 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `toddler` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.toddler.respond`
- …where the player's next choices will be: "That sounds like a very good life." | "What's the best part of a day?" | "Off you go, then."

```text
POOL   dialogue key: dialogue.conversations.life.toddler
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.toddler.to.life.toddler`: the villager accepts. Subject `life.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.toddler/1   [62 chars]
    en  I'm little! I do puddles and snacks and naps. It's a full day.
    >>  ............................................
    pt  Eu sou pequeno! Eu faço poça e lanche e soneca. É um dia cheio.
    >>  ............................................
  dialogue.conversations.life.toddler/2   [40 chars]
    en  My life is good. Yesterday I saw a frog.
    >>  ............................................
    pt  Minha vida é boa. Ontem eu vi um sapo.
    >>  ............................................
  dialogue.conversations.life.toddler/3   [51 chars]
    en  When I'm big I'll reach the table. The WHOLE table.
    >>  ............................................
    pt  Quando eu for grande vou alcançar a mesa. A mesa INTEIRA.
    >>  ............................................
```


**Outcome 5 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `child` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.young.respond`
- …where the player's next choices will be: "Go on, tell me the long version." | "That's a life worth having." | "You'll see it differently when you're older." | "That'll do for now. Off you go."

```text
POOL   dialogue key: dialogue.conversations.life.child
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.child.to.life.young`: the villager accepts. Subject `life.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.child/1   [48 chars]
    en  School, chores, and expert-level puddle finding.
    >>  ............................................
    pt  Escola, tarefa, e caça a poças em nível avançado.
    >>  ............................................
  dialogue.conversations.life.child/2   [72 chars]
    en  I'm learning to whittle! I've made three sticks into... pointier sticks.
    >>  ............................................
    pt  Tô aprendendo a entalhar! Já transformei três gravetos em... gravetos mais pontudos.
    >>  ............................................
```


**Outcome 6 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `teen` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.young.respond`
- …where the player's next choices will be: "Go on, tell me the long version." | "That's a life worth having." | "You'll see it differently when you're older." | "That'll do for now. Off you go."

```text
POOL   dialogue key: dialogue.conversations.life.teen
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.teen.to.life.young`: the villager accepts. Subject `life.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.teen/1   [45 chars]
    en  Chores, mostly. Apparently that's 'life' now.
    >>  ............................................
    pt  Tarefa, quase só. Pelo visto isso agora é "a vida".
    >>  ............................................
  dialogue.conversations.life.teen/2   [47 chars]
    en  I'm figuring things out. Slowly. Don't rush me.
    >>  ............................................
    pt  Tô me achando. Devagar. Não me apressa.
    >>  ............................................
```


**Outcome 7 of 11** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `tense`, `hostile`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.guarded.respond`
- …where the player's next choices will be: "I'll wait for the rest." | "Tell me a recent bit, then." | "Skip ahead for me." | "I'll come back for the next page."

```text
POOL   dialogue key: dialogue.conversations.deflect.personal
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   6 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.personal.to.life.guarded`: the villager deflects. Subject `life.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / dreams; conversations.cat.personal / fears; conversations.cat.personal / hopes
```

```text
  dialogue.conversations.deflect.personal/1   [60 chars]
    en  That's... a bit close to the bone for someone I barely know.
    >>  ............................................
    pt  Isso é... um pouco íntimo demais pra alguém que eu mal conheço.
    >>  ............................................
  dialogue.conversations.deflect.personal/2   [69 chars]
    en  Maybe when I know you better, %1$s. Words like that need trust first.
    >>  ............................................
    pt  Talvez quando eu te conhecer melhor, %1$s. Palavras assim precisam de confiança primeiro.
    >>  ............................................
  dialogue.conversations.deflect.personal/3   [66 chars]
    en  Buy me a sweetroll first. Then we'll see about the deep questions.
    >>  ............................................
    pt  Me paga um pão doce primeiro. Aí a gente vê essas perguntas profundas.
    >>  ............................................
  dialogue.conversations.deflect.personal/4   [74 chars]
    en  I keep that shelf locked. Come back when we're friends, not acquaintances.
    >>  ............................................
    pt  Essa prateleira eu mantenho trancada. Volte quando a gente for amigo, não conhecido.
    >>  ............................................
  dialogue.conversations.deflect.personal/5   [67 chars]
    en  You get the weather and the crops, %1$s. The rest costs friendship.
    >>  ............................................
    pt  Você leva o tempo e a lavoura, %1$s. O resto custa amizade.
    >>  ............................................
  dialogue.conversations.deflect.personal/6   [91 chars]
    en  My mother said never hand a stranger your heart or your ladder. I lost the ladder that way.
    >>  ............................................
    pt  Minha mãe dizia pra nunca entregar a um estranho o seu coração nem a sua escada. Perdi a escada assim.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.deflect.personal/1
    en  I — not yet. Sorry. It's not you, I promise. I just need to be surer first, %1$s.
    >>  ............................................
    pt  Eu — ainda não. Desculpa. Não é você, eu prometo. Só preciso ter mais certeza antes, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.deflect.personal/2
    en  Can I not answer that one? I will. Just not while I'm still working it out myself.
    >>  ............................................
    pt  Posso não responder essa? Eu respondo. Só não enquanto eu ainda estou entendendo por mim mesmo.
    >>  ............................................
  athletic.dialogue.conversations.deflect.personal/1
    en  Whoa — steep hill for a first run. Train with me a while and we'll climb it together, %1$s.
    >>  ............................................
    pt  Opa — ladeira íngreme pra uma primeira corrida. Treina comigo um tempo e a gente sobe juntos, %1$s.
    >>  ............................................
  athletic.dialogue.conversations.deflect.personal/2
    en  That's a cool-down talk, and we've just started warming up. Keep coming round.
    >>  ............................................
    pt  Essa é conversa de desaquecimento, e a gente mal começou a aquecer. Continue aparecendo.
    >>  ............................................
  confident.dialogue.conversations.deflect.personal/1
    en  That answer is reserved for my inner circle. Small circle. Excellent view. Earn your seat.
    >>  ............................................
    pt  Essa resposta é reservada ao meu círculo íntimo. Círculo pequeno. Vista excelente. Conquiste o seu lugar.
    >>  ............................................
  confident.dialogue.conversations.deflect.personal/2
    en  I'm an open book about my triumphs. The rest has a cover charge. Keep visiting.
    >>  ............................................
    pt  Sou um livro aberto sobre os meus triunfos. O resto tem taxa de entrada. Continue aparecendo.
    >>  ............................................
  crabby.dialogue.conversations.deflect.personal/1
    en  No. Not yet. Don't take it personally — I don't tell anyone that, %1$s.
    >>  ............................................
    pt  Não. Ainda não. Não leve pro pessoal — eu não conto isso pra ninguém, %1$s.
    >>  ............................................
  crabby.dialogue.conversations.deflect.personal/2
    en  That's mine. Ask something else, and I'll answer it properly.
    >>  ............................................
    pt  Essa é minha. Pergunta outra coisa que eu respondo direito.
    >>  ............................................
  extroverted.dialogue.conversations.deflect.personal/1
    en  Ha! I'll tell you everything about everyone else, but that one stays mine a while longer, %1$s.
    >>  ............................................
    pt  Ha! Eu te conto tudo sobre todo mundo, mas essa continua sendo minha mais um tempo, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.deflect.personal/2
    en  I'm an open book with a couple of pages stuck together. That's one of them. For now.
    >>  ............................................
    pt  Sou um livro aberto com umas duas páginas grudadas. Essa é uma delas. Por enquanto.
    >>  ............................................
  flirty.dialogue.conversations.deflect.personal/1
    en  So forward! I like it. But you'll have to work for that one, %1$s.
    >>  ............................................
    pt  Que atrevimento! Eu gosto. Mas você vai ter que trabalhar por essa, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.deflect.personal/2
    en  Buy me a drink before the big questions. Then we'll see how loose my tongue gets.
    >>  ............................................
    pt  Me pague uma bebida antes das perguntas grandes. Aí a gente vê o quanto a minha língua solta.
    >>  ............................................
  friendly.dialogue.conversations.deflect.personal/1
    en  Ah, that's kitchen-table talk, and we're still at the front gate! Come by more — the kettle's always on.
    >>  ............................................
    pt  Ah, essa é conversa de mesa de cozinha, e a gente ainda está no portão! Apareça mais — a chaleira está sempre no fogo.
    >>  ............................................
  friendly.dialogue.conversations.deflect.personal/2
    en  I'll tell you anything once we're proper friends — and we're nearly there!
    >>  ............................................
    pt  Eu te conto qualquer coisa quando a gente for amigo de verdade — e estamos quase lá!
    >>  ............................................
  gloomy.dialogue.conversations.deflect.personal/1
    en  No. Some cellars stay shut, especially for strangers.
    >>  ............................................
    pt  Não. Alguns porões ficam fechados, principalmente pra estranhos.
    >>  ............................................
  gloomy.dialogue.conversations.deflect.personal/2
    en  Ask me the small things. I've plenty of those and they cost me nothing.
    >>  ............................................
    pt  Me pergunte as coisas pequenas. Tenho muitas dessas e não me custam nada.
    >>  ............................................
  greedy.dialogue.conversations.deflect.personal/1
    en  That information isn't for sale yet — and I sell almost everything. Build some credit with me first. You're good for it, I think.
    >>  ............................................
    pt  Essa informação ainda não está à venda — e eu vendo quase tudo. Construa algum crédito comigo primeiro. Você é bom pagador, eu acho.
    >>  ............................................
  greedy.dialogue.conversations.deflect.personal/2
    en  I'll itemise my whole life for you eventually. Consider this the free sample ending.
    >>  ............................................
    pt  Eu vou detalhar a minha vida inteira pra você um dia. Considere isto o fim da amostra grátis.
    >>  ............................................
  grumpy.dialogue.conversations.deflect.personal/1
    en  No. Not yet. Don't take it personally — I don't tell anyone that, %1$s.
    >>  ............................................
    pt  Não. Ainda não. Não leve pro pessoal — eu não conto isso pra ninguém, %1$s.
    >>  ............................................
  grumpy.dialogue.conversations.deflect.personal/2
    en  That's mine. Ask something else, and I'll answer it properly.
    >>  ............................................
    pt  Essa é minha. Pergunta outra coisa que eu respondo direito.
    >>  ............................................
  introverted.dialogue.conversations.deflect.personal/1
    en  That's further in than I go with most people, %1$s. Not never — just not yet. Keep coming by.
    >>  ............................................
    pt  Essa é mais funda do que eu vou com a maioria das pessoas, %1$s. Não é nunca — só ainda não. Continue aparecendo.
    >>  ............................................
  introverted.dialogue.conversations.deflect.personal/2
    en  I keep that one to myself for now. Ask again when we've known each other longer.
    >>  ............................................
    pt  Essa eu guardo pra mim por enquanto. Me pergunte de novo quando a gente se conhecer há mais tempo.
    >>  ............................................
  lazy.dialogue.conversations.deflect.personal/1
    en  That's a question for a longer afternoon than this one, %1$s. Come back when we've got the time.
    >>  ............................................
    pt  Essa é pergunta pra uma tarde mais longa que essa, %1$s. Volta quando a gente tiver tempo.
    >>  ............................................
  lazy.dialogue.conversations.deflect.personal/2
    en  Not today. Nothing against you — I just get round to things when I get round to them.
    >>  ............................................
    pt  Hoje não. Nada contra você — eu só chego nas coisas quando chego.
    >>  ............................................
  odd.dialogue.conversations.deflect.personal/1
    en  That answer lives under the third floorboard, and the floorboard doesn't know you yet. Come by more. Floorboards warm up. So do I.
    >>  ............................................
    pt  Essa resposta mora embaixo da terceira tábua do assoalho, e a tábua ainda não te conhece. Apareça mais. Tábuas esquentam. Eu também.
    >>  ............................................
  odd.dialogue.conversations.deflect.personal/2
    en  That one's filed somewhere I'd have to move furniture to reach. Come back when you've been about longer and I'll shift the wardrobe.
    >>  ............................................
    pt  Essa está guardada num lugar em que eu teria que mover móveis para alcançar. Volte quando tiver ficado mais tempo por aqui e eu empurro o armário.
    >>  ............................................
  peaceful.dialogue.conversations.deflect.personal/1
    en  That deserves a fuller answer than I can give today. Ask me again when we've more time, %1$s.
    >>  ............................................
    pt  Isso merece uma resposta mais completa do que eu consigo dar hoje. Me pergunte de novo quando a gente tiver mais tempo, %1$s.
    >>  ............................................
  peaceful.dialogue.conversations.deflect.personal/2
    en  Not yet — not out of secrecy. Some things want the right moment, and this isn't it.
    >>  ............................................
    pt  Ainda não — e não por segredo. Algumas coisas pedem o momento certo, e este não é.
    >>  ............................................
  peppy.dialogue.conversations.deflect.personal/1
    en  Ooh, big question! Too big for now! But keep coming around and I will absolutely overshare someday, promise!
    >>  ............................................
    pt  Ooh, pergunta grande! Grande demais por enquanto! Mas continua aparecendo que um dia eu vou falar demais, prometo!
    >>  ............................................
  peppy.dialogue.conversations.deflect.personal/2
    en  Not yet not yet not yet! I have RULES. Two of them! That's one of them!
    >>  ............................................
    pt  Ainda não, ainda não, ainda não! Eu tenho REGRAS. Duas! Essa é uma delas!
    >>  ............................................
  playful.dialogue.conversations.deflect.personal/1
    en  Ooh, sneaky. Nice try, %1$s. You'll have to be much cleverer than that to get it out of me.
    >>  ............................................
    pt  Ooh, sorrateiro. Boa tentativa, %1$s. Você vai ter que ser bem mais esperto pra arrancar isso de mim.
    >>  ............................................
  playful.dialogue.conversations.deflect.personal/2
    en  Not telling! Ask me three more times and I might slip. That's the game.
    >>  ............................................
    pt  Não conto! Pergunta mais três vezes que eu talvez escorregue. É esse o jogo.
    >>  ............................................
  relaxed.dialogue.conversations.deflect.personal/1
    en  That's a question for a longer afternoon than this one, %1$s. Come back when we've got the time.
    >>  ............................................
    pt  Essa é pergunta pra uma tarde mais longa que essa, %1$s. Volta quando a gente tiver tempo.
    >>  ............................................
  relaxed.dialogue.conversations.deflect.personal/2
    en  Not today. Nothing against you — I just get round to things when I get round to them.
    >>  ............................................
    pt  Hoje não. Nada contra você — eu só chego nas coisas quando chego.
    >>  ............................................
  sensitive.dialogue.conversations.deflect.personal/1
    en  You're kind to ask — I felt that. But that story still bruises, and I'd want to trust the hands I put it in. Give us time?
    >>  ............................................
    pt  Foi gentil da sua parte perguntar — eu senti isso. Mas essa história ainda machuca, e eu ia querer confiar nas mãos onde eu a coloco. Me dá tempo?
    >>  ............................................
  sensitive.dialogue.conversations.deflect.personal/2
    en  I've told that one twice and regretted it twice. The third time I'd like to be sure.
    >>  ............................................
    pt  Eu já contei essa duas vezes e me arrependi das duas. Na terceira eu gostaria de ter certeza.
    >>  ............................................
  shy.dialogue.conversations.deflect.personal/1
    en  That's further in than I go with most people, %1$s. Not never — just not yet. Keep coming by.
    >>  ............................................
    pt  Essa é mais funda do que eu vou com a maioria das pessoas, %1$s. Não é nunca — só ainda não. Continue aparecendo.
    >>  ............................................
  shy.dialogue.conversations.deflect.personal/2
    en  I keep that one to myself for now. Ask again when we've known each other longer.
    >>  ............................................
    pt  Essa eu guardo pra mim por enquanto. Me pergunte de novo quando a gente se conhecer há mais tempo.
    >>  ............................................
  upbeat.dialogue.conversations.deflect.personal/1
    en  Ah — that's a bigger question than we've earned yet, %1$s. Keep visiting, though. I'm not hard to open up.
    >>  ............................................
    pt  Ah — essa é uma pergunta maior do que a gente merece ainda, %1$s. Mas continue visitando. Eu não sou difícil de abrir.
    >>  ............................................
  upbeat.dialogue.conversations.deflect.personal/2
    en  Not quite yet. Ask me again when we've a few more good afternoons behind us — I'd like that.
    >>  ............................................
    pt  Ainda não. Me pergunte de novo quando a gente tiver mais algumas boas tardes acumuladas — eu ia gostar.
    >>  ............................................
  witty.dialogue.conversations.deflect.personal/1
    en  Ah — that's a bigger question than we've earned yet, %1$s. Keep visiting, though. I'm not hard to open up.
    >>  ............................................
    pt  Ah — essa é uma pergunta maior do que a gente merece ainda, %1$s. Mas continue visitando. Eu não sou difícil de abrir.
    >>  ............................................
  witty.dialogue.conversations.deflect.personal/2
    en  Not quite yet. Ask me again when we've a few more good afternoons behind us — I'd like that.
    >>  ............................................
    pt  Ainda não. Me pergunte de novo quando a gente tiver mais algumas boas tardes acumuladas — eu ia gostar.
    >>  ............................................
```

</details>


**Outcome 8 of 11** — base weight `0`

- Fires when: weighted +100 when arc `life` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `resume` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.arc.life.resume.respond`
- …where the player's next choices will be: "You never finished that story." | "And where does that leave you now?" | "It was a long time ago." | "Another time."

```text
POOL   dialogue key: dialogue.conversations.life.revisit
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.arc.life.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.revisit.opens`: the villager reminisces. Subject `life.history`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `life:chapter_told`, `arc:resumed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.life.revisit/1   [74 chars]
    en  You asked me about my life once. I've been thinking about what I left out.
    >>  ............................................
    pt  Você me perguntou sobre a minha vida uma vez. Fiquei pensando no que deixei de fora.
    >>  ............................................
  dialogue.conversations.life.revisit/2   [82 chars]
    en  Remember when you asked about my life? I remembered the good parts after you left.
    >>  ............................................
    pt  Lembra quando você perguntou da minha vida? Lembrei das partes boas depois que você foi embora.
    >>  ............................................
  dialogue.conversations.life.revisit/3   [87 chars]
    en  Been sorting through the years since we talked. Some of them were better than I let on.
    >>  ............................................
    pt  Andei revisando os anos desde a nossa conversa. Alguns foram melhores do que eu deixei transparecer.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.revisit
    en  You asked about my life once and I've replayed my answer roughly four hundred times since.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez e eu já revi a resposta umas quatrocentas vezes.
    >>  ............................................
  athletic.dialogue.conversations.life.revisit
    en  You asked me about my life. I've been chewing on it since, mostly on long runs.
    >>  ............................................
    pt  Você perguntou da minha vida. Venho mastigando isso desde então, quase sempre correndo.
    >>  ............................................
  confident.dialogue.conversations.life.revisit
    en  I told you about my life. I've decided you get the rest of it, and I don't decide that often.
    >>  ............................................
    pt  Eu te contei da minha vida. Decidi que você fica com o resto, e eu não decido isso sempre.
    >>  ............................................
  crabby.dialogue.conversations.life.revisit
    en  You asked me about my life once. I've been irritated about how much I left out ever since.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Desde então eu ando irritado com o quanto deixei de fora.
    >>  ............................................
  extroverted.dialogue.conversations.life.revisit
    en  I told you about my life and then talked about it to four other people. You started something.
    >>  ............................................
    pt  Eu te contei da minha vida e depois falei disso com mais quatro pessoas. Você começou algo.
    >>  ............................................
  flirty.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been deciding which parts to tell you ever since.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Desde então eu venho escolhendo o que te contar.
    >>  ............................................
  friendly.dialogue.conversations.life.revisit
    en  You asked about my life once and it stayed with me all week. Here's the rest, if you want it.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez e ficou comigo a semana toda. Aqui vai o resto, se quiser.
    >>  ............................................
  gloomy.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been going over the parts I left out. There are a lot of them.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Venho revendo as partes que deixei de fora. São muitas.
    >>  ............................................
  greedy.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been totting up what I didn't tell you. It's a fair sum.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Venho somando o que não te contei. Dá uma boa quantia.
    >>  ............................................
  grumpy.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been annoyed about the bits I skipped ever since.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Desde então ando irritado com o que eu pulei.
    >>  ............................................
  introverted.dialogue.conversations.life.revisit
    en  You asked about my life once, and I've been assembling the rest of it quietly since then.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez, e eu venho montando o resto em silêncio desde então.
    >>  ............................................
  lazy.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been meaning to finish the answer for days now.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Venho pretendendo terminar a resposta faz dias.
    >>  ............................................
  odd.dialogue.conversations.life.revisit
    en  You asked about my life once and I've since remembered eleven things I forgot to mention.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez e desde então eu lembrei de onze coisas que esqueci de contar.
    >>  ............................................
  peaceful.dialogue.conversations.life.revisit
    en  You asked about my life once. It's been settling since, the way water does.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Desde então tem ido assentando, como a água faz.
    >>  ............................................
  peppy.dialogue.conversations.life.revisit
    en  You asked me about my life and I've thought of SO many more bits since. Sit down.
    >>  ............................................
    pt  Você perguntou da minha vida e eu pensei em MUITO mais pedaços desde então. Senta.
    >>  ............................................
  playful.dialogue.conversations.life.revisit
    en  You asked about my life once and I've been saving up the good bits ever since. Ready?
    >>  ............................................
    pt  Você perguntou da minha vida uma vez e eu venho guardando as partes boas desde então. Pronto?
    >>  ............................................
  relaxed.dialogue.conversations.life.revisit
    en  You asked about my life once. It's been drifting back to me in pieces since.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Vem voltando em pedaços desde então.
    >>  ............................................
  sensitive.dialogue.conversations.life.revisit
    en  You asked about my life once and I've thought about it every night since. Is that strange?
    >>  ............................................
    pt  Você perguntou da minha vida uma vez e eu penso nisso toda noite desde então. Isso é estranho?
    >>  ............................................
  shy.dialogue.conversations.life.revisit
    en  You asked about my life once. I've been practising the rest of the answer, actually.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Na verdade eu venho ensaiando o resto da resposta.
    >>  ............................................
  upbeat.dialogue.conversations.life.revisit
    en  You asked about my life once — and I've had the loveliest time remembering the rest.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez — e eu tive o maior prazer em lembrar o resto.
    >>  ............................................
  witty.dialogue.conversations.life.revisit
    en  You asked about my life once. I've since edited it considerably. This draft is better.
    >>  ............................................
    pt  Você perguntou da minha vida uma vez. Desde então eu editei bastante. Esta versão é melhor.
    >>  ............................................
```

</details>


**Outcome 9 of 11** — base weight `0`

- Fires when: weighted +100 when LACKS the memory `mcaconversations.topic.life` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `life` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `first` budget `deep`
- Does: remembers `mcaconversations.topic.life` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.respond`
- …where the player's next choices will be: "Which part matters most to you?" | "That can't have been easy." | "You could have done better than that." | "I don't know what to say to that." | "I've kept you long enough."

```text
POOL   dialogue key: dialogue.conversations.life.first
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.first.to.life`: the villager accepts. Subject `life`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.life.first/1   [88 chars]
    en  My life? Hm. Give me a second... it's a long story with a lot of turns badly signposted.
    >>  ............................................
    pt  Minha vida? Hm. Me dá um segundo... é uma história longa e mal sinalizada.
    >>  ............................................
  dialogue.conversations.life.first/2   [101 chars]
    en  Where do I even start? Born here, scraped knees here, buried my father behind the chapel. Still here.
    >>  ............................................
    pt  Por onde eu começo? Nasci aqui, ralei o joelho aqui, enterrei meu pai atrás da capela. E continuo aqui.
    >>  ............................................
  dialogue.conversations.life.first/3   [82 chars]
    en  It's been quiet mostly. One flood, one bad winter, one summer I still think about.
    >>  ............................................
    pt  Foi quieta, na maior parte. Uma enchente, um inverno ruim, e um verão em que eu ainda penso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.life.first/1
    en  All of it? That's — that's a lot to get right. Give me a moment and I'll try to tell it properly, %1$s.
    >>  ............................................
    pt  Tudo? Isso é — isso é muita coisa pra acertar. Me dá um instante que eu tento contar direito, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.life.first/2
    en  It's mostly quiet, with a few bad years I still think about more than I should.
    >>  ............................................
    pt  É quase toda quieta, com alguns anos ruins nos quais eu ainda penso mais do que deveria.
    >>  ............................................
  athletic.dialogue.conversations.life.first/1
    en  My story? Born fast, stayed fast. Raced everything — kids, chickens, one very smug goat. Still owe that goat a rematch.
    >>  ............................................
    pt  Minha história? Nasci rápido, continuei rápido. Apostei corrida com tudo — crianças, galinhas, uma cabra muito convencida. Ainda devo revanche pra essa cabra.
    >>  ............................................
  athletic.dialogue.conversations.life.first/2
    en  All motion, my life. The still bits I forget; the finish lines I never do.
    >>  ............................................
    pt  Só movimento, a minha vida. As partes paradas eu esqueço; as linhas de chegada, nunca.
    >>  ............................................
  confident.dialogue.conversations.life.first/1
    en  A fine story, saved for the right audience. It begins with a stubborn child who was right about nearly everything.
    >>  ............................................
    pt  Uma bela história, reservada para o público certo. Começa com uma criança teimosa que estava certa sobre quase tudo.
    >>  ............................................
  confident.dialogue.conversations.life.first/2
    en  I never once doubted I'd amount to something. I was right. I usually am.
    >>  ............................................
    pt  Nunca duvidei nem uma vez de que eu seria alguém. Eu estava certo. Costumo estar.
    >>  ............................................
  crabby.dialogue.conversations.life.first/1
    en  My life story? It's long and it's mostly grievances, %1$s. You asked, so I'll trim it to the decent parts.
    >>  ............................................
    pt  A história da minha vida? É longa e quase toda de queixas, %1$s. Você perguntou, então eu corto pras partes decentes.
    >>  ............................................
  crabby.dialogue.conversations.life.first/2
    en  Nothing remarkable. Some hard years, a few good ones. I remember the hard ones better.
    >>  ............................................
    pt  Nada notável. Uns anos duros, alguns bons. Lembro melhor dos duros.
    >>  ............................................
  extroverted.dialogue.conversations.life.first/1
    en  Oh, you'll want to sit down for this one, %1$s — it's long and there are a great many people in it.
    >>  ............................................
    pt  Ah, você vai querer sentar pra essa, %1$s — é longa e tem uma quantidade enorme de gente nela.
    >>  ............................................
  extroverted.dialogue.conversations.life.first/2
    en  Where do I begin? Everyone I've ever met is in this story somewhere. Get comfortable.
    >>  ............................................
    pt  Por onde eu começo? Todo mundo que eu já conheci está nessa história em algum lugar. Se acomoda.
    >>  ............................................
  flirty.dialogue.conversations.life.first/1
    en  My life story? Buy me a drink first. Fine, fine — chapter one...
    >>  ............................................
    pt  A história da minha vida? Me paga uma bebida primeiro. Tá, tá — capítulo um...
    >>  ............................................
  flirty.dialogue.conversations.life.first/2
    en  A story with a lot of dancing and a few names I won't mention. Sit close for the good parts.
    >>  ............................................
    pt  Uma história com muita dança e alguns nomes que eu não vou mencionar. Senta pertinho pras partes boas.
    >>  ............................................
  friendly.dialogue.conversations.life.first/1
    en  Nobody's asked in ages! Get comfortable — it's a long one, with three good harvests and a lot of shared suppers in it.
    >>  ............................................
    pt  Faz séculos que ninguém pergunta! Fica à vontade — é longa, com três boas colheitas e muita janta dividida no meio.
    >>  ............................................
  friendly.dialogue.conversations.life.first/2
    en  A warm sort of story, full of neighbors and open doors. I remember every one fondly.
    >>  ............................................
    pt  Uma história do tipo quentinho, cheia de vizinhos e portas abertas. Lembro de cada um com carinho.
    >>  ............................................
  gloomy.dialogue.conversations.life.first/1
    en  My life is a long corridor of Tuesdays, %1$s. But there were a few Sundays. I'll tell you about those.
    >>  ............................................
    pt  Minha vida é um corredor longo de terças-feiras, %1$s. Mas teve alguns domingos. Vou te contar desses.
    >>  ............................................
  gloomy.dialogue.conversations.life.first/2
    en  Born here, stayed here, buried three people I liked. That's the spine of it, %1$s. The rest is weather.
    >>  ............................................
    pt  Nasci aqui, fiquei aqui, enterrei três pessoas de quem eu gostava. É essa a espinha, %1$s. O resto é clima.
    >>  ............................................
  greedy.dialogue.conversations.life.first/1
    en  The whole story? Premium inventory, %1$s. But you pay attention, and that's the rarest coin. Fine — chapter one, free of charge.
    >>  ............................................
    pt  A história toda? Estoque premium, %1$s. Mas você presta atenção, e essa é a moeda mais rara. Tudo bem — capítulo um, sem cobrança.
    >>  ............................................
  greedy.dialogue.conversations.life.first/2
    en  Born owing, spent thirty years getting even, and I'm about level now. That's the shape of it.
    >>  ............................................
    pt  Nasci devendo, passei trinta anos ficando quite, e agora estou mais ou menos zerado. É essa a forma da coisa.
    >>  ............................................
  grumpy.dialogue.conversations.life.first/1
    en  My life story? It's long and it's mostly grievances, %1$s. You asked, so I'll trim it to the decent parts.
    >>  ............................................
    pt  A história da minha vida? É longa e quase toda de queixas, %1$s. Você perguntou, então eu corto pras partes decentes.
    >>  ............................................
  grumpy.dialogue.conversations.life.first/2
    en  Nothing remarkable. Some hard years, a few good ones. I remember the hard ones better.
    >>  ............................................
    pt  Nada notável. Uns anos duros, alguns bons. Lembro melhor dos duros.
    >>  ............................................
  introverted.dialogue.conversations.life.first/1
    en  It's a small, quiet story, %1$s, and I've never minded that. Sit down — I'll tell it properly, since you asked.
    >>  ............................................
    pt  É uma história pequena e quieta, %1$s, e eu nunca me incomodei com isso. Senta — eu conto direito, já que você perguntou.
    >>  ............................................
  introverted.dialogue.conversations.life.first/2
    en  Not much happens in it, and what does happens inwardly. I prefer it that way.
    >>  ............................................
    pt  Não acontece muita coisa nela, e o que acontece, acontece por dentro. Prefiro assim.
    >>  ............................................
  lazy.dialogue.conversations.life.first/1
    en  It's a long story and I tell it slowly, %1$s. Settle in — there's no short version worth having.
    >>  ............................................
    pt  É uma história longa e eu conto devagar, %1$s. Se acomoda — não tem versão curta que preste.
    >>  ............................................
  lazy.dialogue.conversations.life.first/2
    en  Ordinary enough, and I've enjoyed most of it. That's more than plenty of people can say.
    >>  ............................................
    pt  Comum o bastante, e eu curti a maior parte. É mais do que muita gente pode dizer.
    >>  ............................................
  odd.dialogue.conversations.life.first/1
    en  Born on a Tuesday, which explains a lot. A flood, a goose I still owe an apology, one summer I keep in a jar. I'll start with the goose.
    >>  ............................................
    pt  Nasci numa terça, o que explica muita coisa. Uma enchente, um ganso a quem ainda devo desculpas, um verão que eu guardo num pote. Vou começar pelo ganso.
    >>  ............................................
  odd.dialogue.conversations.life.first/2
    en  I have lived here always, except for the year I didn't, which nobody remembers, including me. Ask me about the jar instead.
    >>  ............................................
    pt  Eu sempre morei aqui, tirando o ano em que não morei, que ninguém lembra, inclusive eu. Me pergunte do pote.
    >>  ............................................
  peaceful.dialogue.conversations.life.first/1
    en  It's not a dramatic story, %1$s, but it's been a good one. I'll tell it gladly if you've the time.
    >>  ............................................
    pt  Não é uma história dramática, %1$s, mas foi boa. Conto com gosto se você tiver tempo.
    >>  ............................................
  peaceful.dialogue.conversations.life.first/2
    en  Some hard years early on, and a long calm since. I'm grateful for the calm.
    >>  ............................................
    pt  Alguns anos duros no começo, e uma longa calmaria desde então. Sou grato pela calmaria.
    >>  ............................................
  peppy.dialogue.conversations.life.first/1
    en  Ooh, story time! Born here, loved it, scraped every knee twice, named the well bucket — where do I even START, %1$s?
    >>  ............................................
    pt  Ooh, hora da história! Nasci aqui, amei, ralei cada joelho duas vezes, dei nome ao balde do poço — por onde eu COMEÇO, %1$s?
    >>  ............................................
  peppy.dialogue.conversations.life.first/2
    en  It's mostly other people! I remember my life by who was in it. That's a LOT of names, so settle in!
    >>  ............................................
    pt  É quase toda gente! Eu lembro da minha vida por quem estava nela. É MUITO nome, então se acomoda!
    >>  ............................................
  playful.dialogue.conversations.life.first/1
    en  My story? It's mostly narrow escapes and things I shouldn't have done. Ready? You'll enjoy this one, %1$s.
    >>  ............................................
    pt  Minha história? É quase toda fuga por pouco e coisa que eu não devia ter feito. Pronto? Você vai gostar dessa, %1$s.
    >>  ............................................
  playful.dialogue.conversations.life.first/2
    en  Long, silly, and full of people chasing me. I regret almost none of it.
    >>  ............................................
    pt  Longa, boba e cheia de gente correndo atrás de mim. Não me arrependo de quase nada.
    >>  ............................................
  relaxed.dialogue.conversations.life.first/1
    en  It's a long story and I tell it slowly, %1$s. Settle in — there's no short version worth having.
    >>  ............................................
    pt  É uma história longa e eu conto devagar, %1$s. Se acomoda — não tem versão curta que preste.
    >>  ............................................
  relaxed.dialogue.conversations.life.first/2
    en  Ordinary enough, and I've enjoyed most of it. That's more than plenty of people can say.
    >>  ............................................
    pt  Comum o bastante, e eu curti a maior parte. É mais do que muita gente pode dizer.
    >>  ............................................
  sensitive.dialogue.conversations.life.first/1
    en  No one's asked so gently before. It's an ordinary story — but there's a winter in it my eyes still sting over. Sit close.
    >>  ............................................
    pt  Ninguém nunca perguntou com tanta delicadeza. É uma história comum — mas tem um inverno nela que ainda arde nos meus olhos. Senta pertinho.
    >>  ............................................
  sensitive.dialogue.conversations.life.first/2
    en  Ordinary from the outside. From in here it's a great many small kindnesses and two things I've never said aloud.
    >>  ............................................
    pt  Comum vista de fora. Daqui de dentro são muitas bondades pequenas e duas coisas que eu nunca disse em voz alta.
    >>  ............................................
  shy.dialogue.conversations.life.first/1
    en  It's a small, quiet story, %1$s, and I've never minded that. Sit down — I'll tell it properly, since you asked.
    >>  ............................................
    pt  É uma história pequena e quieta, %1$s, e eu nunca me incomodei com isso. Senta — eu conto direito, já que você perguntou.
    >>  ............................................
  shy.dialogue.conversations.life.first/2
    en  Not much happens in it, and what does happens inwardly. I prefer it that way.
    >>  ............................................
    pt  Não acontece muita coisa nela, e o que acontece, acontece por dentro. Prefiro assim.
    >>  ............................................
  upbeat.dialogue.conversations.life.first/1
    en  My whole story? It's a good one, %1$s — not grand, but good. Plenty of bright spots, and I remember those best.
    >>  ............................................
    pt  Minha história inteira? É boa, %1$s — não grandiosa, mas boa. Muitos pontos luminosos, e eu lembro melhor deles.
    >>  ............................................
  upbeat.dialogue.conversations.life.first/2
    en  Ups and downs like anyone's, but I've been lucky more often than not. Sit down and I'll tell you the good parts.
    >>  ............................................
    pt  Altos e baixos como a de qualquer um, mas eu tive sorte mais vezes que não. Senta que eu conto as partes boas.
    >>  ............................................
  witty.dialogue.conversations.life.first/1
    en  My whole story? It's a good one, %1$s — not grand, but good. Plenty of bright spots, and I remember those best.
    >>  ............................................
    pt  Minha história inteira? É boa, %1$s — não grandiosa, mas boa. Muitos pontos luminosos, e eu lembro melhor deles.
    >>  ............................................
  witty.dialogue.conversations.life.first/2
    en  Ups and downs like anyone's, but I've been lucky more often than not. Sit down and I'll tell you the good parts.
    >>  ............................................
    pt  Altos e baixos como a de qualquer um, mas eu tive sorte mais vezes que não. Senta que eu conto as partes boas.
    >>  ............................................
```

</details>


**Outcome 10 of 11** — base weight `0`

- Fires when: weighted +100 when has the memory `mcaconversations.topic.life` (this player only)
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when arc `life` is at stage 1..2  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.life` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `life` branch `again_open` budget `deep`
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.topic.life.respond`
- …where the player's next choices will be: "Which part matters most to you?" | "That can't have been easy." | "You could have done better than that." | "I don't know what to say to that." | "I've kept you long enough."

```text
POOL   dialogue key: dialogue.conversations.life.revisit
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.topic.life.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.revisit.to.life`: the villager accepts. Subject `life`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

> Written out in full under **`conversations.cat.personal` / button `life`** earlier in this file. Fill it in there, once.


**Outcome 11 of 11** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the `branching` feature is ON  _(chance -2000)_
- Does: **hearts (raw MCA `positive` field)** = 4
- Does: remembers `mcaconversations.topic.life` (this player only) permanently
- Does: remembers `mcaconversations.cooldown.life` (this player only) for 48000 ticks
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.life.first
WHO    VILLAGER — what the player reads after pressing "Tell me about your life."
       spoken on: conversations.cat.personal, button `life`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `life.first.terminal`: the villager accepts. Subject `life.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

> Written out in full under **`conversations.cat.personal` / button `life`** earlier in this file. Fill it in there, once.


### Button `dreams` — "What do you dream about?"

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `personal.dreams` — accepted phrasings: "what are your dreams"; "your ambitions"; "do you dream"
  - the message must contain one of: `dream`, `ambition`, `aspire`, `goal`
  - scored words: `dream`(1.5), `ambition`(1.0), `aspire`(1.0), `goal`(1.0), `wish`(0.6)

```text
POOL   dialogue key: dialogue.conversations.cat.personal.dreams
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.cat.personal.dreams   [24 chars]
    en  What do you dream about?
    >>  ............................................
    pt  Com o que você sonha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.dreams.the_named_one"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.dreams.the_named_one", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 36000 ticks
- Then opens: `conversations.scene.dreams.the_named_one.respond`
- …where the player's next choices will be: "What's the next step?" | "Go and ask them this week." | "I'm glad you said it out loud." | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_named_one
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.scene.dreams.the_named_one.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_named_one.open`: the villager reports. Subject `dreams.ambition`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, restraint, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_named_one/1   [110 chars]
    en  There is a thing I want and I have said it out loud twice, counting now, and both times to somebody I trusted.
    >>  ............................................
    pt  Existe uma coisa que eu quero e já disse em voz alta duas vezes, contando agora, e as duas para alguém em quem confio.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one/2   [128 chars]
    en  I want to be the person other people come to for one particular thing. That is smaller than it sounds and harder than it sounds.
    >>  ............................................
    pt  Quero ser a pessoa que os outros procuram para uma coisa específica. É menor do que parece e mais difícil do que parece.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_named_one/3   [109 chars]
    en  It takes about six years and I have done two of them, and the two have been better than the four before them.
    >>  ............................................
    pt  Leva uns seis anos e eu já fiz dois, e esses dois foram melhores que os quatro anteriores.
    >>  ............................................
```


**Outcome 2 of 11** — base weight `0`

- Fires when: weighted +900 when `conversations_scene` = {"is": "topic.dreams.the_small_version"}
- Fires when: RULED OUT when `conversations_scene` = {"is": "topic.dreams.the_small_version", "not": true}  _(chance -5000)_
- Fires when: RULED OUT when the `dynamic` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `scene` budget `standard`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 36000 ticks
- Then opens: `conversations.scene.dreams.the_small_version.respond`
- …where the player's next choices will be: "Which two things?" | "I hope you get both." | "Thanks for telling me."

```text
POOL   dialogue key: dialogue.conversations.scene.dreams.the_small_version
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.scene.dreams.the_small_version.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.the_small_version.open`: the villager reports. Subject `dreams.modest`, polarity `positive`, invites followup, outcome `None`.
NOTE   this is the line that establishes `topic:dreams` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.dreams.the_small_version/1   [109 chars]
    en  A better roof and a quieter winter. That is what I tell people and it happens to be true, just not all of it.
    >>  ............................................
    pt  Um telhado melhor e um inverno mais calmo. É o que eu digo às pessoas e por acaso é verdade, só não é tudo.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version/2   [86 chars]
    en  Nothing grand. I would like the next year to look like this one with two things fixed.
    >>  ............................................
    pt  Nada grandioso. Eu gostaria que o ano que vem fosse igual a este com duas coisas consertadas.
    >>  ............................................
  dialogue.conversations.scene.dreams.the_small_version/3   [105 chars]
    en  I want to finish something I started. I am not going to say what, because saying what makes it a promise.
    >>  ............................................
    pt  Quero terminar algo que comecei. Não vou dizer o quê, porque dizer o quê transforma em promessa.
    >>  ............................................
```


**Outcome 3 of 11** — base weight `0`

- Fires when: weighted +1000 when has the memory `mcaconversations.cooldown.dreams` (this player only)
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `again` budget `deep`
- Then opens: `conversations.topic.deep.again.respond`
- …where the player's next choices will be: "Sorry — I've asked already." | "Tell me again anyway." | "Fair. Another day."

```text
POOL   dialogue key: dialogue.conversations.dreams.again
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.deep.again.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.again.to.deep.again`: the villager accepts. Subject `deep.again`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.again/1   [69 chars]
    en  You know my dream already. Saying it twice won't make it come faster.
    >>  ............................................
    pt  Você já sabe do meu sonho. Falar duas vezes não faz ele chegar mais rápido.
    >>  ............................................
  dialogue.conversations.dreams.again/2   [77 chars]
    en  My dream hasn't changed since this morning, %1$s. They move slower than that.
    >>  ............................................
    pt  Meu sonho não mudou desde hoje de manhã, %1$s. Eles andam mais devagar que isso.
    >>  ............................................
  dialogue.conversations.dreams.again/3   [45 chars]
    en  Still the same dream. Still saving up for it.
    >>  ............................................
    pt  Continua o mesmo sonho. Continuo juntando dinheiro pra ele.
    >>  ............................................
```


**Outcome 4 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `toddler`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `toddler` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.toddler.respond`
- …where the player's next choices will be: "I believe you." | "How will you do it?" | "Off you go and practise."

```text
POOL   dialogue key: dialogue.conversations.dreams.toddler
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.toddler.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.toddler.to.dreams.toddler`: the villager accepts. Subject `dreams.toddler`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.toddler/1   [16 chars]
    en  I wanna be TALL.
    >>  ............................................
    pt  Eu quero ser ALTO.
    >>  ............................................
  dialogue.conversations.dreams.toddler/2   [32 chars]
    en  A pony! Or a dog. Or a pony-dog.
    >>  ............................................
    pt  Um pônei! Ou um cachorro. Ou um pônei-cachorro.
    >>  ............................................
  dialogue.conversations.dreams.toddler/3   [47 chars]
    en  I dreamed I was a bee last night. It was great.
    >>  ............................................
    pt  Ontem eu sonhei que era uma abelha. Foi ótimo.
    >>  ............................................
```


**Outcome 5 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `child`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `child` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.young.respond`
- …where the player's next choices will be: "Go on, tell me the whole plan." | "You could do that, you know." | "You'll grow out of that one." | "Off you go and plan, then."

```text
POOL   dialogue key: dialogue.conversations.dreams.child
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.child.to.dreams.young`: the villager accepts. Subject `dreams.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.child/1   [86 chars]
    en  I'm gonna be a knight AND a baker. Sword in one hand, cake in the other. It's decided.
    >>  ............................................
    pt  Vou ser cavaleiro E padeiro. Espada numa mão, bolo na outra. Já está decidido.
    >>  ............................................
  dialogue.conversations.dreams.child/2   [63 chars]
    en  I want a pet slime! A LITTLE one. It can sleep in the boot box.
    >>  ............................................
    pt  Quero um slime de estimação! Um PEQUENININHO. Ele pode dormir na caixa de botas.
    >>  ............................................
```


**Outcome 6 of 11** — base weight `0`

- Fires when: weighted +100 when the villager is a `teen`
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `teen` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.young.respond`
- …where the player's next choices will be: "Go on, tell me the whole plan." | "You could do that, you know." | "You'll grow out of that one." | "Off you go and plan, then."

```text
POOL   dialogue key: dialogue.conversations.dreams.teen
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.young.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.teen.to.dreams.young`: the villager accepts. Subject `dreams.young`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.dreams.teen/1   [93 chars]
    en  Somewhere that isn't here, doing something nobody assigned me. That's the whole dream so far.
    >>  ............................................
    pt  Algum lugar que não seja aqui, fazendo algo que ninguém me mandou. É esse o sonho todo até agora.
    >>  ............................................
  dialogue.conversations.dreams.teen/2   [84 chars]
    en  Don't laugh — I've been practicing the bow when nobody's watching. I'm getting good.
    >>  ............................................
    pt  Não ria — ando treinando com o arco quando ninguém está olhando. Estou ficando bom.
    >>  ............................................
```


**Outcome 7 of 11** — base weight `0`

- Fires when: weighted +100 when the relationship band is one of `stranger`, `tense`, `hostile`
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `guarded` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.topic.dreams.guarded.respond`
- …where the player's next choices will be: "Then build it in peace." | "Tell me a smaller one, then." | "Let me hear it anyway." | "Another time, when it's further on."

```text
POOL   dialogue key: dialogue.conversations.deflect.personal
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.topic.dreams.guarded.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   6 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `deflect.personal.to.dreams.guarded`: the villager deflects. Subject `dreams.guarded`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   the same pool is also spoken at: conversations.cat.personal / life; conversations.cat.personal / fears; conversations.cat.personal / hopes
```

> Written out in full under **`conversations.cat.personal` / button `life`** earlier in this file. Fill it in there, once.


**Outcome 8 of 11** — base weight `0`

- Fires when: weighted +100 when arc `dreams` is at stage 1..2
- Fires when: RULED OUT when the relationship band is one of `stranger`, `tense`, `hostile`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `toddler`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `child`  _(chance -2000)_
- Fires when: RULED OUT when the villager is a `teen`  _(chance -2000)_
- Fires when: RULED OUT when has the memory `mcaconversations.cooldown.dreams` (this player only)  _(chance -1000)_
- Fires when: RULED OUT when the `branching` feature is OFF  _(chance -2000)_
- Fires when: RULED OUT when the `topics` feature is OFF  _(chance -2000)_
- Does: session `begin` topic `dreams` branch `resume` budget `deep`
- Does: remembers `mcaconversations.cooldown.dreams` (this player only) for 48000 ticks
- Then opens: `conversations.arc.dreams.resume.respond`
- …where the player's next choices will be: "Any closer to it?" | "What's the first step? I'll help with that." | "Still on about that?" | "Keep at it."

```text
POOL   dialogue key: dialogue.conversations.dreams.revisit
WHO    VILLAGER — what the player reads after pressing "What do you dream about?"
       spoken on: conversations.cat.personal, button `dreams`
       leaves the player on: conversations.arc.dreams.resume.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `dreams.revisit.opens`: the villager reminisces. Subject `dreams.ambition`, polarity `mixed`, invites followup, outcome `None`.
NOTE   this is the line that establishes `dreams:named`, `arc:resumed` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.dreams.revisit/1   [63 chars]
    en  Still dreaming about what I told you. It hasn't gotten smaller.
    >>  ............................................
    pt  Ainda sonhando com aquilo que te contei. Não diminuiu nem um pouco.
    >>  ............................................
  dialogue.conversations.dreams.revisit/2   [76 chars]
    en  You remembered my dream? Careful, %1$s, I might start believing in it again.
    >>  ............................................
    pt  Você lembrou do meu sonho? Cuidado, %1$s, eu posso voltar a acreditar nele.
    >>  ............................................
  dialogue.conversations.dreams.revisit/3   [79 chars]
    en  Since you asked last time, I've saved four emeralds toward it. Four! It begins.
    >>  ............................................
    pt  Desde que você perguntou da última vez, já guardei quatro esmeraldas pra ele. Quatro! Começou.
    >>  ............................................
```

