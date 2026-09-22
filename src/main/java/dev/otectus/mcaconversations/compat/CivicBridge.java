package dev.otectus.mcaconversations.compat;

import dev.otectus.mcaconversations.McaConversations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Reflection-only access to Ultima Kingdoms' requester-scoped civic contact API. */
public final class CivicBridge {

    private static final String MOD_ID = "ultima_kingdoms";
    private static final String API = "com.ultimakingdoms.api.civic.CivicNetworkApi";
    /** Conversations-owned reason for a request that never reached Ultima. */
    static final String UNAVAILABLE_KEY = "mcaconversations.civic.unavailable";
    private static volatile Access binding;
    private static volatile boolean attempted;
    private static volatile Access testAccess;
    private static boolean failureReported;

    public enum Action {
        INTRODUCTION,
        COMMISSIONS;

        public static Action parse(String value) {
            return switch (value) {
                case "introduction" -> INTRODUCTION;
                case "commissions" -> COMMISSIONS;
                default -> throw new IllegalArgumentException(
                        "conversations_civic must be 'introduction' or 'commissions'");
            };
        }
    }

    /** Flattened public context; no hidden settlement or provider type crosses this boundary. */
    public record Contact(UUID npc, ResourceLocation organization, String nameKey, String role,
                          boolean servicesAvailable, boolean introductionQualified,
                          boolean commissionQualified, List<String> reasons,
                          List<String> commissionReasons, long stateRevision, long policyRevision) {
        public Contact {
            reasons = List.copyOf(reasons);
            commissionReasons = List.copyOf(commissionReasons);
        }
    }

    /** Requester-only result. Settlement identity deliberately remains inside Ultima's own response. */
    public record Reply(boolean success, String reason) {
    }

    interface Access {
        Optional<?> context(ServerPlayer player, Entity speaker) throws Throwable;

        Object request(Action action, ServerPlayer player, Entity speaker) throws Throwable;
    }

    private CivicBridge() {
    }

    public static boolean isAvailable() {
        return access() != null;
    }

    public static Optional<Contact> speakerContext(ServerPlayer player, Entity speaker) {
        Access live = access();
        if (live == null || player == null || speaker == null) return Optional.empty();
        try {
            Optional<?> raw = live.context(player, speaker);
            if (raw == null || raw.isEmpty()) return Optional.empty();
            Contact decoded = decode(raw.get());
            return decoded.npc().equals(speaker.getUUID()) ? Optional.of(decoded) : Optional.empty();
        } catch (Throwable failure) {
            report(failure);
            return Optional.empty();
        }
    }

    public static Reply request(Action action, ServerPlayer player, Entity speaker) {
        Access live = access();
        if (live == null || action == null || player == null || speaker == null) {
            // Ultima's own reason keys only exist while Ultima is installed; this one is ours, so a
            // pack that authors the action on an ungated route never shows the player a raw key.
            return new Reply(false, UNAVAILABLE_KEY);
        }
        try {
            Object raw = live.request(action, player, speaker);
            return raw == null ? new Reply(false, "civic.institution_unavailable") : decodeReply(raw);
        } catch (Throwable failure) {
            report(failure);
            return new Reply(false, "civic.institution_unavailable");
        }
    }

    private static Contact decode(Object raw) throws Throwable {
        UUID npc = (UUID) accessor(raw, "npc").invoke(raw);
        String organization = boundedResourceId(
                (String) accessor(raw, "organization").invoke(raw), "organization");
        ResourceLocation organizationId = ResourceLocation.tryParse(organization);
        if (npc == null || organizationId == null || !organizationId.toString().equals(organization)) {
            throw new IllegalArgumentException("invalid civic contact identity");
        }
        String nameKey = boundedKey((String) accessor(raw, "nameKey").invoke(raw), "name key");
        String role = boundedToken((String) accessor(raw, "role").invoke(raw), "role");
        boolean services = (boolean) accessor(raw, "servicesAvailable").invoke(raw);
        boolean introductions = (boolean) accessor(raw, "introductionQualified").invoke(raw);
        boolean commissions = (boolean) accessor(raw, "commissionQualified").invoke(raw);
        List<String> reasons = reasons(accessor(raw, "reasons").invoke(raw));
        List<String> commissionReasons = reasons(accessor(raw, "commissionReasons").invoke(raw));
        long stateRevision = (long) accessor(raw, "stateRevision").invoke(raw);
        long policyRevision = (long) accessor(raw, "policyRevision").invoke(raw);
        if (stateRevision < 0L || policyRevision < 0L) throw new IllegalArgumentException("negative civic revision");
        return new Contact(npc, organizationId, nameKey, role, services, introductions, commissions,
                reasons, commissionReasons, stateRevision, policyRevision);
    }

    private static Reply decodeReply(Object raw) throws Throwable {
        boolean success = (boolean) accessor(raw, "success").invoke(raw);
        String reason = boundedKey((String) accessor(raw, "reason").invoke(raw), "action reason");
        // Deliberately do not invoke settlement(): destinations remain inside Ultima's requester-only
        // presentation and cannot leak into MCA dialogue state, chat, or generated text.
        return new Reply(success, reason);
    }

    static Contact decodeContextForTest(Object raw) throws Throwable {
        return decode(raw);
    }

    static Reply decodeReplyForTest(Object raw) throws Throwable {
        return decodeReply(raw);
    }

    private static List<String> reasons(Object raw) {
        if (!(raw instanceof List<?> values) || values.size() > 8) {
            throw new IllegalArgumentException("invalid civic reasons");
        }
        List<String> result = new ArrayList<>();
        for (Object value : values) {
            if (!(value instanceof String reason)) throw new IllegalArgumentException("invalid civic reason");
            result.add(boundedKey(reason, "reason"));
        }
        return List.copyOf(result);
    }

    private static String boundedKey(String value, String what) {
        if (value == null || value.isBlank() || value.length() > 256
                || !value.matches("[a-z0-9_.:/-]+")) {
            throw new IllegalArgumentException("invalid civic " + what);
        }
        return value;
    }

    private static String boundedResourceId(String value, String what) {
        if (value == null || value.length() > 256) {
            throw new IllegalArgumentException("invalid civic " + what);
        }
        ResourceLocation parsed = ResourceLocation.tryParse(value);
        if (parsed == null || !parsed.toString().equals(value)) {
            throw new IllegalArgumentException("invalid civic " + what);
        }
        return value;
    }

    private static String boundedToken(String value, String what) {
        if (value == null || value.isBlank() || value.length() > 64
                || !value.matches("[a-z0-9_.-]+")) {
            throw new IllegalArgumentException("invalid civic " + what);
        }
        return value;
    }

    private static Method accessor(Object owner, String name) throws NoSuchMethodException {
        return owner.getClass().getMethod(name);
    }

    private static Access access() {
        if (testAccess != null) return testAccess;
        if (binding != null) return binding;
        if (attempted) return null;
        try {
            if (!ModList.get().isLoaded(MOD_ID)) {
                attempted = true;
                return null;
            }
        } catch (Throwable unavailableForge) {
            attempted = true;
            return null;
        }
        synchronized (CivicBridge.class) {
            if (binding != null) return binding;
            if (attempted) return null;
            attempted = true;
            try {
                Class<?> api = Class.forName(API, false, CivicBridge.class.getClassLoader());
                Method context = api.getMethod("speakerContext", ServerPlayer.class, Entity.class);
                Method introduction = api.getMethod("requestIntroduction", ServerPlayer.class, Entity.class);
                Method commissions = api.getMethod("requestCommissions", ServerPlayer.class, Entity.class);
                binding = new ReflectiveAccess(context, introduction, commissions);
                return binding;
            } catch (Throwable failure) {
                report(failure);
                return null;
            }
        }
    }

    static void setAccessForTest(Access access) {
        testAccess = access;
    }

    static void resetForTest() {
        testAccess = null;
    }

    private static void report(Throwable failure) {
        if (failureReported) return;
        failureReported = true;
        Throwable cause = failure instanceof InvocationTargetException invocation
                && invocation.getCause() != null ? invocation.getCause() : failure;
        McaConversations.LOGGER.warn("Ultima civic context/action bridge failed closed", cause);
    }

    private record ReflectiveAccess(Method context, Method introduction, Method commissions) implements Access {
        @Override
        @SuppressWarnings("unchecked")
        public Optional<?> context(ServerPlayer player, Entity speaker) throws Throwable {
            return (Optional<?>) context.invoke(null, player, speaker);
        }

        @Override
        public Object request(Action action, ServerPlayer player, Entity speaker) throws Throwable {
            return (action == Action.INTRODUCTION ? introduction : commissions).invoke(null, player, speaker);
        }
    }
}
