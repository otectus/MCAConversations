# Executive Summary

MCA’s dialogue system in 1.20.1 is a JSON‐driven “dialogue tree” with statically authored questions and answers.  Conversations are triggered by player‐NPC interactions, routed via custom network messages (`InteractionDialogueInitMessage`, `InteractionDialogueMessage`, etc.), and rendered by the client GUI (`InteractScreen.java`).  The data format is a set of JSON **questions** and **answers** with conditions (via *InteractionPredicates*) and weighted results.  NPCs track simple **relationship memories** (hearts) with each player in their brain (e.g. `getMemoriesForPlayer(player).modHearts(…)`) but do not currently “remember” conversation topics across sessions.  This report reviews MCA’s existing conversation code and data structures, identifies where an add-on can hook in, surveys lightweight conversation techniques, and proposes 4–6 implementation options for a “MCA: Real Talk” add-on.  We compare each option on complexity, memory cost, and feature richness, and recommend how to test and roll it out.  

 

## Existing MCA Conversation System

### Dialogue Data & Format  
MCA stores dialogue in JSON under its data files.  A `Dialogues` loader (`net.mca.resources.Dialogues`) reads files defining **Question** objects, each with multiple **Answer** options and **Result** actions.  Each `Question` has an ID string and may be marked “auto” (no player choice).  Each `Answer` has conditions (via `InteractionPredicate`) and may adjust hearts or trigger gifts. The code example below (simplified) shows a question with two answers, one conditional on player trust: 

```json
{
  "id": "greeting",
  "text": "dialogue.greeting.text",
  "answers": [
    { "id": "hi", "text": "dialogue.greeting.hi", "chance": 1.0,
      "results": [{ "doAction": "sayNice"}]
    },
    { "id": "bye", "text": "dialogue.greeting.bye", "chance": 1.0,
      "requirements": { "minHearts": 5 },
      "results": [{ "doAction": "giveGift"}]
    }
  ]
}
```

At runtime, when a dialogue is initiated, MCA selects valid answers by checking each `Answer`’s predicates (using `question.getValidAnswers(player, villager)`) and then sends the client the chosen Question ID and available answer IDs.  The client looks up the display text via localization keys (`Question.getTranslationKey`) and shows buttons for each answer.

### Conversation Flow & Triggers  

In code, the conversation starts when the player interacts with an MCA NPC.  On the server, this is handled by messaging: e.g. a client may send an `InteractionDialogueInitMessage(villagerUUID)` when the player opens the “Interact” GUI with a villager.  The server handler for this message looks up the root question (`Dialogues.getInstance().getQuestion("root")`).  If the root question is auto-triggered (no choices), it calls `selectAnswer` immediately; otherwise it packages an `InteractionDialogueResponse(question, player, villager)` back to the client.  

On the client, receiving `InteractionDialogueResponse` invokes `ClientProxy.getNetworkHandler().handleDialogueResponse(this)`, which in turn calls `InteractScreen` to render the question text and answer choices.  When the user clicks an answer button, the client sends back `InteractionDialogueMessage(villagerUUID, questionId, answerId)` to the server.  The server’s handler calls `Dialogues.selectAnswer(villager, player, questionId, answerId)`, which executes the answer’s results (e.g. changing hearts, running commands) and then either chooses the next question (if any) or ends the conversation.  (Internally, `selectAnswer` may send another `InteractionDialogueResponse` if the conversation continues.)

Thus, MCA’s conversation is a dialogue tree: player choices lead to new questions or end-of-conversation.  All prompts and text are authored, and the system has no persistent “dialogue memory” beyond relationship stats (hearts) and one‐off flags already in place (e.g. “got married” or “divorced”).

### NPC State, Memory & Scheduling  

MCA tracks simple **player‐NPC relationship** in a memory object.  Each villager’s brain has a `Memories` entry per player (accessible via `getVillagerBrain().getMemoriesForPlayer(player)`).  This stores **hearts (affection/trust)** and other stats.  Certain interactions modify hearts (e.g. gifting or hitting the villager).  These hearts influence some conditional dialogues (via `minHearts` in JSON or trust checks in code).  Beyond hearts, villagers remember some player-specific facts (like “married”, “is parent of child”, etc.), but these are mostly binary flags used in quests or spawning.  Notably, MCA does **not** currently persist a list of which questions or topics the player has already discussed; every conversation starts from “root” each time by default.

Villager AI (via the Minecraft `Brain` system) uses **memory modules** for simple behaviors (e.g. village home location, guard bounties).  However, there is no built-in advanced conversation history.  Villagers do have schedules (they go to work, home, gather at square, etc.) but this mainly affects pathfinding and activities, not dialogue.  No event hooks exist for conversation beyond the network messages and the hook on right‐click interaction (Forge’s `PlayerInteractEntityEvent` or mixins around `interactMob` in `VillagerEntityMCA`).

### Localization  

Dialogue lines use Minecraft’s translation system.  Each `Question` and `Answer` references translation keys (e.g. `dialogue.greeting.text`).  The `InteractScreen` calls `Text.translatable(Question.getTranslationKey(questionId, answerId))` for each answer.  The actual text entries live in language files (e.g. `assets/mca/lang/en_us.json`), which must be updated for any new dialogue keys.  Localization is therefore straightforward: add entries to the existing lang files or a new language file, keyed by the JSON IDs.

### Persistence and Hooks  

NPC memory (hearts, flags) is automatically saved in the world’s entity NBT.  If we add new memory (e.g. “topics discussed” flags), we must ensure they are saved too.  Options include: extending `VillagerBrain` or attaching a custom Forge capability to the villager.  Forging integration points: MCA is a Forge 1.20.1 mod, so we can hook into Forge events (e.g. `WorldTickEvent` for scheduling, `EntityJoinWorldEvent` to add data to new villagers, or `PlayerInteractEvent.EntityInteract` to initiate conversation).  MCA’s own networking and message handlers (in `net.mca.network`) are the primary API for conversation – an add-on can reuse those or send its own messages.  For example, an add-on could call `NetworkHandler.sendToPlayer(new InteractionDialogueResponse(...))` to inject a question at any time.

  

## Survey of Lightweight Dialogue Systems

In place of heavy AI, RPGs use simpler scripted systems.  Here are some common patterns:

- **Branching Dialogue Trees** (Static Choice Trees):  Each conversation is a tree of fixed questions/answers (as MCA currently does).  *Pros:* Easy for writers, fully controllable.  *Cons:* Explodes combinatorially with depth, hard to maintain global consistency.  No implicit memory beyond flags.  Used in many RPGs (e.g. classic BioWare games).

- **Finite State Machines (FSMs)**:  Dialogue is modeled as states (e.g. “greeting”, “quest offered”, “completed”) with transitions triggered by events or choices.  Similar to trees but emphasizes stateful context.  *Pros:* Naturally handles linear progressions and state-dependent dialogue.  *Cons:* Can still branch heavily; need extra bookkeeping for states per NPC.  More code complexity to implement.

- **Flag/Variable Conditionals**:  Choices set boolean or numeric flags, and later dialogue queries those flags.  Essentially what MCA does with predicates (`minHearts`, `marriageStatus`, etc.).  *Pros:* Very low overhead (a few flags), easy to add new conditions.  *Cons:* Limited expressiveness; can become tangled if too many flags.  Doesn’t manage multi-turn memory beyond yes/no facts.

- **Topic/Keyword Maps**:  Conversations based on topics (e.g. an NPC has a “gossip network” of known topics).  Player selects a topic (politics, family, war) and NPC has canned responses per topic, possibly with subtopics.  *Pros:* Feels like open conversation, good for variety.  *Cons:* Needs design of topic hierarchy; still essentially static.  Slightly more dynamic than simple tree.

- **Procedural/Template Generation**:  NPC lines assembled from templates or filler (e.g. “I heard about {event} from {source}”).  *Pros:* Low memory usage, can produce varied lines.  *Cons:* May seem generic or repetitive; still needs underlying story data.  Requires code to generate text.  

- **Dialogue with Variables**:  In-tree systems that interpolate game variables (player name, quest status) into lines.  *Pros:* More personal feel.  *Cons:* Limited if logic needs to branch deeply on those variables; essentially adds conditional phrases in a static tree.

- **Social Memory Graphs**:  A lightweight model where NPCs “remember” events (e.g. who betrayed them, who gave gifts) and reference them in dialogue later.  *Pros:* Increases consistency and realism.  *Cons:* Requires tracking extra data per NPC (who did what to them) – moderate overhead.  Still scripted references in dialogue.

In sum, truly **non‐AI** methods rely on static scripts + small memory.  MCA already uses a form of branching tree with flag-based conditions (via predicates on hearts, race, etc.).  Adding depth without heavy AI means enriching this model: e.g. more flags (topics), state machines, or limited procedural templates.  

**Pros/Cons summary:**

- Trees/Graphs (branching): Easy to author; complexity and content size grow exponentially if naively extended. Maintains creative control but lacks emergent behavior.  
- State Machines: Enforces context (e.g. after gifting enter “grateful” state); good for linear quest dialogues. But multi-quest NPC needs many states and flags.  
- Flags/Variables: Very low cost (booleans, counters). Flexible for implementing conditions. But “state” is implicit; a flag often just disables/enables branches. Easily becomes unmaintainable if flags proliferate.  
- Topic Maps/Gossip: Can make dialogue seem more dynamic, but really still a preset network; risk of incoherence. Moderate complexity to implement a topic graph and update it.  
- Procedural Templates: Low CPU/MT usage (string operations), high reuse of content templates. However, writing good templates is nontrivial, and subtlety is limited.  
- Social Memory: Enriches NPC consistency (e.g. reference past favors), but requires storing relationships/events per NPC. If done simply (e.g. a list of last N interactions), cost is moderate.

 

## Integration Points and Modding APIs

For a Forge 1.20.1 add-on, integration can use both MCA’s APIs and vanilla Forge hooks.  Key points:

- **Network Hooks:** Use MCA’s `NetworkHandler` to send `InteractionDialogueInitMessage`, `InteractionDialogueMessage`, or even custom messages (e.g. an `InteractionDialogueQuestionResponse`) to simulate conversation.  These messages already exist in `net.mca.network`, so the mod can reuse them to drive the `InteractScreen`.  

- **Entity Events:** Listen to `PlayerInteractEntityEvent` (right-click). When the clicked entity is an `Entity` with `instanceof VillagerEntityMCA`, cancel default action and begin dialogue (send `InteractionDialogueInitMessage`).  Or hook into MCA’s own right-click handling (via mixin on `VillagerEntityMCA.interactMob`).  

- **Villager Capability or Data:** Attach new persistent data to villagers (e.g. a `Capability<YourMemory>` or extend their `Brain`).  For example, a Forge `ICapabilityProvider` on `VillagerEntity` storing “topicsAsked” or “moodState”.  Mark it `@SaveNBT` so it persists with the world.  

- **Scheduling:** If dialogues depend on time-of-day or routines, use Forge’s `WorldTickEvent` or `VillagerTickEvent` to update NPC states.  MCA villagers follow schedules internally, but our add-on may override or add new “get up at dusk to gossip” behaviors by checking time and villager schedule.

- **Forge Config/Localization:** Define any new localization keys or config options in the mod.  If we add flags like “persistent gossip”, allow toggling it in config.  Use `@Mod.EventBusSubscriber` to register commands or GUI hooks if needed. 

Overall, MCA already handles the heavy lifting of player<–>NPC communication and dialogue UI.  The add-on should focus on *what* dialogue to send *when* (i.e. improving depth) rather than rewriting the GUI or networking.  

 

## Proposed Implementation Options

Below are 6 candidate approaches, from minimal to richer, each with design outline, data structures, and a snippet of how it would integrate.  We assume adding *not* AI/text-generation, but extended scripting and memory.

| Option | Description | Data/Storage | Rough Pseudocode | Perf/Complexity |
| --- | --- | --- | --- | --- |
| **1. “Don’t Repeat Yourself” (Minimal)** – Track recently asked Q’s and skip repeats. For example, mark a question ID in villager memory after it’s asked once, so it won’t appear again (or gets deprioritized) on future talks. This adds “memory” of conversation topics. | Store a `Set<String> askedQuestions` in villager’s persistent NBT. On `InteractionDialogueInit`, clear or keep from last talk. In `Dialogues.selectAnswer`, before sending next question, check `if (villagerMemory.askedQuestions.contains(qId))` and pick a different branch or end. | *Extended Villager NBT:* e.g.: `<data><asked><value id="greeting"/><value id="questOffer"/></asked></data>`. | ```java
// Example: before sending question:
Question q = Dialogues.getInstance().getQuestion(nextId);
if (villagerMemory.hasAsked(q.getName())) {
    // either end or pick alternative
    q = Dialogues.getInstance().getRandomQuestionNotAsked(villager);
}
sendDialogueResponse(q);
villagerMemory.markAsked(q.getName());
``` | **Perf:** negligible (few string flags) **Effort:** Low (hook into Dialogues) **Feature:** low (prevents repetition, slightly deeper feel) |
| **2. State‐Driven Conversations** – Assign a simple state machine or “conversation mood” per NPC. E.g., states like *Neutral/Greeting*, *QuestOffered*, *QuestGiven*, *QuestDone*. Dialogue trees in JSON have branches keyed to these states (using predicates). The add-on updates the villager’s `state` flag (persistent) when events happen (gift given, quest started/completed). | *Villager Memory:* an enum or int `stateId` per villager. Dialogue JSON uses a requirement `"state":"questOffered"`, etc. | ```java
// On gift:
if (receivedHeartGift) villagerMemory.state = State.GRATEFUL;
// On quest complete:
if (quest.isComplete()) villagerMemory.state = State.JOYFUL;

// In dialogue select:
Dialogues.selectAnswer(villager, player, qId, aId);
// then maybe send next question with check
```
*(Dialogue JSON example)*  
```json
"requirements": { "villagerState": "GRATEFUL" }
``` | **Perf:** Low (one extra enum check per dialogue) **Effort:** Medium (add state field + adjust JSON) **Feature:** Medium (persistent mood, branching by quest progress) |
| **3. Topic Flags & References** – Enable NPC to remember **topics** of conversation. For each NPC-player pair, track booleans like `toldAboutVillage`, `toldChildName`, `toldGossip`. Use these to unlock new dialogue or prevent repeating the same info. For instance, after the player asks “Tell me about your village” once, set `toldVillage=true` so future asks get a “we already talked” response. | *Memory:* several boolean flags per villager per player (could use a BitSet or small JSON in capability). Dialogue JSON uses `requires: { "flagNotToldVillage": true }`. | ```java
// On answering "Tell me about village":
villagerMemory.flags.put("toldVillage", true);

// In JSON predicate:
"requirements": { "flag": "toldVillage", "value": false }
```  
On subsequent asks, the “already told” branch requires toldVillage=true. | **Perf:** Low (check a flag map) **Effort:** Medium (add flag storage, tag JSON) **Feature:** Medium (topic depth, consistency) |
| **4. Event‐Triggered Lines (Calendar/Time)** – Make dialogue vary by time or events. E.g. villager mentions *today’s season*, *time of day*, or recent in-game events (weather, festival).  Data needed: none extra (use world time/day), plus new dialogue JSON conditioned on these. Could also trigger unique dialogues on birthdays or holidays. | Use Minecraft time or world data in conditions. Dialogue JSON has fields like `"requirements": { "timeOfDay": "night" }` or `"month": "December"`.  No extra NBT needed.  | ```java
long dayTime = world.getDayTime() % 24000;
boolean isNight = (dayTime > 13000);
if (isNight) {
   // Only present nighttime questions
}
```  
(*In Dialogues.getValidAnswers*, check such conditions.) | **Perf:** None (just reading world time) **Effort:** Low (few JSON conditions) **Feature:** Low-Medium (adds variety by schedule) |
| **5. Social/Gossip Memory** – NPCs remember gossip about other characters and can share or react to it. For example, if NPC A hears from player about NPC B’s event, A might later mention it to the player. Implement by recording events in a lightweight global memory and showing relevant lines. | *Memory:* A global or village-level log of “recent events” (e.g. map of NPC IDs to recent events). Each villager has access to subset. Dialogue JSON conditioned on `lastHeardEvent==X`. For simplicity, one could use villagers’ `gossip` like a boolean `heardX`. | ```java
// On villager B event (e.g. marriage):
WorldState.events.put("B_married", true);
// On villager A interact:
if (WorldState.events.contains("B_married") && !villagerMemory.sharedGossip) {
    // trigger gossip line
    villagerMemory.sharedGossip = true;
}
``` | **Perf:** Low-medium (store few flags globally) **Effort:** High (new data structure, JSON tags) **Feature:** High (NPCs feel aware of world) |
| **6. Querying Simple Database / Template** – For some dynamic info, store it in villager data and let them use it in sentences. Example: villager remembers player’s gift (“I remember you brought me a *flower* last week.”). On gift, store item type in villager memory; in dialogue, include `${lastGiftItem}`.  | *Memory:* store last gift item name (string) and timestamp. Dialogue text uses template: `"text": "dialogue.giftResponse",` where `dialogue.giftResponse=${playerName}, thanks for the ${lastGiftItem}!"`. Implementation can use basic string replacement. | ```java
// On gift (server side):
villagerMemory.lastGift = giftItem.getName().getString();
villagerMemory.giftTime = world.getTime();

// In InteractScreen rendering:
String template = translate("dialogue.giftResponse");
String text = template.replace("${lastGiftItem}", villagerMemory.lastGift);
```
(Requires customizing translation or GUI rendering.) | **Perf:** Very low (string ops) **Effort:** Medium (need text templates, translation entries) **Feature:** Medium (personalized references) |

Each option above can be layered: e.g. combining state flags with topic memory and time conditions.  A *minimal-change* path (Option 1) might be easiest: simply avoid repeating lines and add new ones conditionally.  A *richer* approach (Options 3–6) gives more immersion but requires more coding and data.

### Code Snippets

Below is a pseudo-code sketch for Option 3 (topic flags). It shows extending a villager’s NBT and using predicates in `Dialogues`:

```java
// Define a capability on VillagerEntityMCA
public class TopicMemory {
    private final Set<String> flags = new HashSet<>();
    public boolean has(String f) { return flags.contains(f); }
    public void set(String f) { flags.add(f); }
}

// During conversation (server side):
// e.g., when handling InteractionDialogueMessage:
VillagerEntityMCA villager = (VillagerEntityMCA)v;
TopicMemory mem = villager.getCapability(TopicMemoryProvider.TOPIC_CAP).orElse(null);
if (questionId.equals("ask_about_village") && answerId.equals("yes")) {
    // After answering, mark that topic
    mem.set("askedVillage");
}

// In JSON dialogues, use custom predicate:
"requirements": {
    "predicate": "topic_not_asked",
    "topic": "askedVillage"
}
```

And on startup of the mod, one could register `topic_not_asked` so that `InteractionPredicateTypeAdapter` checks `!villager.getCapability(...).has(topic)` before showing that answer.  

### Option Comparison

| Option                     | Complexity | Perf Impact | Feature Richness    | Dev Effort  |
|----------------------------|------------|-------------|---------------------|------------|
| 1. No Repeat (flags)       | Low        | Negligible  | Low (avoids duplicates) | Low        |
| 2. FSM (state field)       | Medium     | Negligible  | Medium (contextual flow) | Medium     |
| 3. Topic Flags (per-topic)| Medium     | Low         | Medium-High (dynamic topics) | Medium    |
| 4. Time/Event-specific     | Low        | Negligible  | Low-Medium (variety by schedule) | Low    |
| 5. Gossip (shared memory)  | High       | Low         | High (social context)    | High      |
| 6. Template variables      | Medium     | Low         | Medium (personalized lines) | Medium   |

Option 1 is minimal (“fast hack”): almost no runtime cost beyond a few boolean checks, and easy to revert. Option 5 (gossip) is richest but also most work and a bit riskier for mod compatibility (needs careful sync of world events).  Combining several options (e.g. 2+3+4) can yield a deep, consistent system without any heavy AI.

 

## Testing and Deployment

To ensure performance stays low:

- **Profiling:** Use tools like Spark or VisualVM on a test server with many NPCs. Profile the CPU/memory cost of conversation ticks and new data structures (especially if storing per-NPC data). Measure how `Dialogues.selectAnswer` and our added checks impact tick time.  
- **Metrics:** Track average time to process a talk (should be sub-ms) and memory overhead per NPC (e.g. a few bytes per flag). Ensure any added capability or map scales linearly with number of players/NPCs.  
- **Incremental Rollout:** Start by enabling one feature at a time (e.g. Option 1 alone) and test with existing dialogues. Use a staging server with representative player interaction. Check that old dialogue lines still show correctly (no regression). Then add Option 2, test again, etc.  
- **Edge Cases:** Test saving/loading worlds to confirm all new NBT or capability data persists.  Try multi-player – ensure synchronization (though most data is per-NPC, not per-client).  

Continuous testing on both single-player and dedicated server setups is needed. Profiling should focus on any new loops (e.g. iterating asked questions or gossip flags). Keep data structures minimal (use simple sets, booleans, not heavy collections).  

 

## Follow-Up Questions

To refine the design further, the following should be clarified:

- **Environment:** Will “MCA: Real Talk” run on servers only, or also single-player? (Performance budgets differ; server might have many NPCs.)
- **NPC Scale:** How many MCA NPCs are typically present? (Affects memory/cpu headroom.)
- **Persistence Scope:** Should conversation memory persist across world saves forever, or only for the session? (Likely permanent per NPC.)
- **Localization Needs:** Any new text for dialogues must be localized. Which languages need support?  
- **Mod Interactions:** Will this add-on interact with other mods (e.g. using items from other mods as gifts to trigger new topics)?  
- **Gameplay Goals:** Is the aim to add specific features (like relationship dialogue, family topics) or just general “make chatting less repetitive”?  

With these answers, we can fine-tune which options are most valuable and how to implement them in MCA’s modding framework.

**Sources:** We examined MCA 1.20.1 code on GitHub (dialogue loading in `Dialogues.java`, network messages in `InteractionDialogueResponse.java`, memory use in `VillagerEntityMCA.java`) and general design discussions of dialogue systems to inform these options. All proposed approaches avoid heavy AI, focusing on state/flag logic within Forge/MC conventions.