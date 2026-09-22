package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import dev.otectus.mcaconversations.conversation.KingdomGateSpec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

/**
 * Optional, reflection-only boundary to Ultima Kingdoms and Ultima Factions.
 *
 * <p>Every method fails closed for authored restrictions. Ungated content never calls this class,
 * and the one compatibility escape hatch is {@code when_unknown: allow} on an inline predicate.
 */
public final class KingdomBridge {

    private static final String MOD_ID = "ultima_kingdoms";
    private static final String GATE_API = "com.ultimakingdoms.api.gating.KingdomGateApi";
    private static final String FACTIONS_API = "com.ultimakingdoms.api.factions.UltimaFactionsApi";
    private static final String STANDING_SCOPE = "com.ultimakingdoms.api.factions.StandingScope";

    private static volatile Binding binding;
    private static volatile boolean attempted;
    private static volatile boolean bindFailureReported;
    private static volatile boolean runtimeFailureReported;

    private KingdomBridge() {
    }

    /** True when the authored kingdom predicate and optional standing clause both pass. */
    public static boolean allows(KingdomGateSpec gate, ServerPlayer player, Entity giver) {
        if (gate == null) return true;
        Binding live = binding();
        if (live == null || player == null || giver == null) {
            return gate.allowsWhenUnavailable();
        }
        try {
            boolean kingdom = gate.namedGate().isPresent()
                    ? (boolean) live.testNamed().invoke(null, player, giver, gate.namedGate().get(),
                            gate.explicitSettlementId())
                    : (boolean) live.testJson().invoke(null, player, giver, gate.predicateJson().toString(),
                            gate.explicitSettlementId());
            if (!kingdom) return false;
            if (gate.standing().isEmpty()) return true;
            return matchesStanding(live, gate, gate.standing().get(), player, giver);
        } catch (Throwable throwable) {
            reportRuntimeFailure(throwable);
            return false;
        }
    }

    private static boolean matchesStanding(Binding live, KingdomGateSpec gate,
                                           KingdomGateSpec.StandingGate standing,
                                           ServerPlayer player, Entity giver) throws Throwable {
        ResourceLocation kingdom = standing.kingdom().orElseGet(() -> resolveKingdom(live, gate, player, giver)
                .orElse(null));
        if (kingdom == null || live.factionsGet() == null || live.factionsMatches() == null) return false;
        Object server = player.getServer();
        if (server == null) return false;
        Object service = live.factionsGet().invoke(null, server);
        Object scope = enumConstant(live.standingScope(), standing.scope().name());
        OptionalInt local = standing.scope() == KingdomGateSpec.StandingScope.FACTION
                ? OptionalInt.empty() : localStanding(live, service, player, giver);
        return (boolean) live.factionsMatches().invoke(service, scope, player.getUUID(), kingdom, local,
                standing.minimum(), standing.maximum());
    }

    /** Prefer Ultima's public provider facade; older Ultima builds fall back to Conversations' bridge. */
    private static OptionalInt localStanding(Binding live, Object service,
                                             ServerPlayer player, Entity giver) {
        if (live.factionsLocalStanding() == null || live.communityConstructor() == null) {
            return ReputationBridge.localScore(player, giver);
        }
        try {
            OptionalInt village = McaCompat.getHomeVillageId(giver);
            if (village.isEmpty()) return OptionalInt.empty();
            ResourceLocation dimension = giver.level().dimension().location();
            Object community = live.communityConstructor().newInstance(dimension, village.getAsInt());
            Object score = live.factionsLocalStanding().invoke(service, player.getUUID(), community);
            return score instanceof OptionalInt value ? value : OptionalInt.empty();
        } catch (Throwable unavailable) {
            McaConversations.LOGGER.debug("[MCA: Conversations] Ultima local-standing provider unavailable", 
                    unwrap(unavailable));
            return OptionalInt.empty();
        }
    }

    private static Optional<ResourceLocation> resolveKingdom(Binding live, KingdomGateSpec gate,
                                                              ServerPlayer player, Entity giver) {
        try {
            Object value = live.resolveKingdomId().invoke(null, player, giver, gate.subject(),
                    gate.explicitSettlementId());
            if (!(value instanceof Optional<?> optional) || optional.isEmpty()
                    || !(optional.get() instanceof ResourceLocation id)) {
                return Optional.empty();
            }
            return Optional.of(id);
        } catch (Throwable throwable) {
            reportRuntimeFailure(throwable);
            return Optional.empty();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object enumConstant(Class<?> type, String name) {
        return Enum.valueOf((Class<? extends Enum>) type.asSubclass(Enum.class), name);
    }

    private static Binding binding() {
        Binding cached = binding;
        if (cached != null) return cached;
        if (attempted || !ModList.get().isLoaded(MOD_ID)) return null;
        synchronized (KingdomBridge.class) {
            if (binding != null) return binding;
            if (attempted) return null;
            attempted = true;
            try {
                ClassLoader loader = KingdomBridge.class.getClassLoader();
                Class<?> gateApi = Class.forName(GATE_API, false, loader);
                Method testJson = gateApi.getMethod("testJson", ServerPlayer.class, Entity.class,
                        String.class, Optional.class);
                Method testNamed = gateApi.getMethod("testNamed", ServerPlayer.class, Entity.class,
                        ResourceLocation.class, Optional.class);
                Method resolve = gateApi.getMethod("resolveKingdomId", ServerPlayer.class, Entity.class,
                        String.class, Optional.class);

                Method factionsGet = null;
                Method factionsMatches = null;
                Method factionsLocalStanding = null;
                Constructor<?> communityConstructor = null;
                Class<?> standingScope = null;
                try {
                    Class<?> factionsApi = Class.forName(FACTIONS_API, false, loader);
                    standingScope = Class.forName(STANDING_SCOPE, false, loader);
                    factionsGet = factionsApi.getMethod("get", net.minecraft.server.MinecraftServer.class);
                    Class<?> service = factionsGet.getReturnType();
                    factionsMatches = service.getMethod("matches", standingScope, UUID.class,
                            ResourceLocation.class, OptionalInt.class, int.class, int.class);
                    try {
                        Class<?> community = Class.forName(
                                "com.ultimakingdoms.api.McaCommunityRef", false, loader);
                        communityConstructor = community.getConstructor(ResourceLocation.class, int.class);
                        factionsLocalStanding = service.getMethod("getLocalStanding", UUID.class, community);
                    } catch (ClassNotFoundException | NoSuchMethodException olderFacade) {
                        // Additive capability. ReputationBridge supplies the same tri-state local input
                        // for older Ultima builds; absence is still OptionalInt.empty(), never zero.
                    }
                } catch (ClassNotFoundException | NoSuchMethodException unavailable) {
                    McaConversations.LOGGER.info("[MCA: Conversations] Ultima Kingdoms has kingdom gates but "
                            + "no compatible Ultima Factions facade; standing clauses remain unavailable.");
                }
                binding = new Binding(testJson, testNamed, resolve, factionsGet, factionsMatches,
                        factionsLocalStanding, communityConstructor, standingScope);
                McaConversations.LOGGER.info("[MCA: Conversations] Ultima Kingdoms kingdom gates detected{}.",
                        factionsMatches == null ? "" : " with faction standing");
                return binding;
            } catch (Throwable throwable) {
                if (!bindFailureReported) {
                    bindFailureReported = true;
                    McaConversations.LOGGER.error("[MCA: Conversations] Ultima Kingdoms is installed but its "
                            + "kingdom gate API did not bind; authored kingdom restrictions fail closed.",
                            unwrap(throwable));
                }
                return null;
            }
        }
    }

    private static void reportRuntimeFailure(Throwable throwable) {
        if (!runtimeFailureReported) {
            runtimeFailureReported = true;
            McaConversations.LOGGER.error("[MCA: Conversations] Ultima kingdom/faction gate failed closed.",
                    unwrap(throwable));
        }
    }

    private static Throwable unwrap(Throwable throwable) {
        return throwable instanceof InvocationTargetException invocation && invocation.getCause() != null
                ? invocation.getCause() : throwable;
    }

    private record Binding(Method testJson, Method testNamed, Method resolveKingdomId,
                           Method factionsGet, Method factionsMatches, Method factionsLocalStanding,
                           Constructor<?> communityConstructor, Class<?> standingScope) {
    }
}
