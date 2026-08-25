# MCA: Conversations — 1.21.1 / NeoForge port evidence

Phase 0 record. Every mixin target and MCA API used by this mod, read from the **resolved
binary** rather than from upstream source. The jar is the authority; the GitHub branch is
supporting evidence only.

## Target binary

| | |
|---|---|
| Artifact | `net.conczin.mca:mca-neoforge:7.7.36-beta.3+1.21.1` |
| Maven | `https://maven.conczin.net/Artifacts` |
| Inspected file | `mca-neoforge-7.7.36-beta.3+1.21.1.jar` (12,141,510 bytes) |
| SHA-256 | `de4763d34a41cb84ffa392b87cdb23191beddda2323b56552a1a2fcd7c436fc3` |
| Mappings | Mojang official (mojmap) — no SRG, no refmap needed |
| Extras | `META-INF/accesstransformer.cfg`, jarJar'd `mixinextras-neoforge-0.5.4.jar` |

`7.7.36+1.21.1` (without the `-beta.3` qualifier) **does not exist** on the Conczin Maven.
Published 1.21.1 NeoForge versions run `7.7.13` … `7.7.33` … `7.7.34-beta.3`,
`7.7.35-beta.3`, `7.7.36-beta.3`. The latest non-prerelease is `7.7.33+1.21.1`.

Classes live directly at `net/conczin/mca/**`. The 1.20.1 line's Forgix-merged universal jar
(with `forge/`, `fabric/`, `quilt/` roots) is gone; 1.21.1 publishes per-loader jars.

The POM declares transitive **runtime** dependencies on `maven.modrinth:sodium` and
`maven.modrinth:ferrite-core`, which do not resolve without the Modrinth Maven. The build
therefore declares the dependency `{ transitive = false }`. MixinExtras is already bundled
inside the jar, so nothing needed is lost.

## Mixin targets

Inspected with JDK 21 `javap -p -s -c`
(`C:\Users\crims\.gradle\jdks\eclipse_adoptium-21-amd64-windows.2`).

### `net.conczin.mca.entity.ai.BreedableRelationship` — `BreedableRelationshipMixin`

```
private void acceptGift(ItemStack, GiftType, ServerPlayer, Memories)
descriptor: (Lnet/minecraft/world/item/ItemStack;Lnet/conczin/mca/entity/interaction/gifts/GiftType;Lnet/minecraft/server/level/ServerPlayer;Lnet/conczin/mca/entity/ai/Memories;)V
```

The method is **private**. Its bytecode contains exactly one `ItemStack.split(I)` call:

```
277: invokevirtual VillagerEntityMCA.getInventory:()Lnet/minecraft/world/SimpleContainer;
282: invokevirtual net/minecraft/world/item/ItemStack.split:(I)Lnet/minecraft/world/item/ItemStack;
285: invokevirtual net/minecraft/world/SimpleContainer.addItem:(...)Lnet/minecraft/world/item/ItemStack;
```

Offset 282 sits in the `else` branch, after the inventory-full, `Response.FAIL` and
saturation-`FAIL` early returns. Injecting `BEFORE` that single `INVOKE` is unambiguous and is
the only position that records exactly the gifts MCA actually accepts. The 1.20.1 hook injected
at `HEAD`, which is why rejected, inventory-full and desaturated gifts were recorded as accepted.

Villager resolution: `Relationship.getWorld()` → `ServerLevel`, `Relationship.getUUID()` → `UUID`.

### `net.conczin.mca.network.Network` — `McaNetworkMixin`

```
public static void sendToPlayer(HandleablePayload, ServerPlayer)
descriptor: (Lnet/conczin/mca/network/HandleablePayload;Lnet/minecraft/server/level/ServerPlayer;)V
```

`HandleablePayload extends CustomPacketPayload` with `default void handle(Player)` and
`default void handleServer(ServerPlayer)`. The 1.20.1 `cobalt.network.NetworkHandler` /
`Message` pair no longer exists.

Payloads this hook inspects, all records — read via accessors, never fields:

```
InteractionDialogueQuestionResponse(Component questionText, boolean silent)
InteractionDialogueResponse(String question, List<String> answers)
AnalysisResults(net.conczin.mca.resources.data.Analysis analysis)
```

`InteractionDialogueResponse` has a second constructor
`(Question, ServerPlayer, VillagerEntityMCA)`; the mixin only reads accessors, so this does not
matter, but it rules out matching on a single constructor descriptor.

### `net.conczin.mca.network.c2s.InteractionDialogueMessage` — `InteractionDialogueMessageMixin`

```
public record InteractionDialogueMessage(UUID villagerUUID, String question, String answer)
public void handleServer(ServerPlayer)
descriptor: (Lnet/minecraft/server/level/ServerPlayer;)V
```

The 1.20.1 target `receive` is gone. Record components are private final fields with accessors
`villagerUUID()`, `question()`, `answer()` — shadowing record fields is avoided by casting
`this` and calling the accessors.

### `net.conczin.mca.resources.data.dialogue.Question` — `QuestionMixin`

```
public List<String> getValidAnswers(ServerPlayer, VillagerEntityMCA)
descriptor: (Lnet/minecraft/server/level/ServerPlayer;Lnet/conczin/mca/entity/VillagerEntityMCA;)Ljava/util/List;
```

Both parameters must precede `CallbackInfoReturnable<List<String>>` in the handler.

### `net.conczin.mca.resources.Dialogues` — `DialoguesMixin`

```
private final Map<String, Question> questions;   // descriptor Ljava/util/Map;
public Question getQuestion(String)              // (Ljava/lang/String;)Lnet/conczin/mca/resources/data/dialogue/Question;
public void selectAnswer(VillagerEntityMCA, ServerPlayer, String, String)
```

### `net.conczin.mca.MCAClient` — `client.MCAClientMixin`

```
public static boolean useExpandedPersonalityTranslations()   // ()Z
```

Its body: `!isTTSPackActive && languageCode.equals("en_us") && !Config.getInstance().enableOnlineTTS`,
where `isTTSPackActive` scans loaded packs for an id containing `MCAVoices`. So the native gate
allows **`en_us` only** — the 1.20.1 comment claiming `en_us`/`ru_ru` is stale. Both upstream
guards are retained; the mixin only widens the locale test.

### Deleted: `client.VillagerMessageMixin`

```
public record VillagerMessage(Component prefix, Component message, UUID uuid)
```

The payload now carries `Component` objects directly. The 1.20.1 JSON-reparse / repeated-random-draw
bug the mixin worked around no longer exists, and the constructors and getters it targeted are gone.

## Extension seams (unchanged)

```
GiftPredicate.register(String, BiFunction<JsonElement,String,T>, GiftPredicate.Factory<T>)
Actions.register(String, BiFunction<JsonElement,String,T>, Actions.Factory<T>)
```

Both still public static generics with the same shape, so all 18 conditions and 9 actions
register unchanged.

## Personality roster

`net.conczin.mca.entity.ai.relationship.Personality` is an extensible registry class. Built-ins:

`unassigned` (sentinel, excluded from `getRandom`), then the 14 rollable ids in registration
order: `friendly`, `flirty`, `playful`, `gloomy`, `sensitive`, `greedy`, `odd`, `crabby`,
`extroverted`, `introverted`, `relaxed`, `anxious`, `peaceful`, `upbeat`.

`confident` and `peppy` — present in the 1.20.1-era 7.7 beta — are **not registered** here.
They move to legacy-only alongside `athletic`; their lang overlays stay shipped as compatibility
assets.

Stable accessors: `Personality.getId()` → `ResourceLocation`, `getDialoguePrefix()` →
`ExtensibleTypeRegistry.translationSuffix(id)` (namespace stripped), and
`VillagerBrain.getPersonalityId()` → `ResourceLocation`. These replace the 1.20.1
`Personality.toString()` cross-version workaround.

## Access transformers

The MCA jar ships `META-INF/accesstransformer.cfg`. A dependency's AT is **not** applied to this
mod's compilation. If any mixin or compat call turns out to need an AT-widened Minecraft member,
Conversations must declare its own `accessTransformers` entry rather than relying on MCA's.
