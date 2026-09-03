package dev.otectus.mcaconversations.compat.mca;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Guards {@link McaBinding#erase} against the one mistake it exists to prevent.
 *
 * <p>MCA's {@code getTranslatable(Player, String, Object...)} is varargs, so the bound handle's last
 * parameter is the argument array itself and {@code McaHandles.translatable} passes it whole. Dropping
 * the {@code asFixedArity()} from {@link McaBinding#erase} does not fail to bind, does not throw, and
 * does not log: {@code asType} quietly re-collects, MCA receives a one-element array holding our array,
 * and every dialogue line with a substitution renders {@code [Ljava.lang.Object;@1a2b3c} where the name
 * or number should be. That reached players, so it is asserted rather than commented.
 *
 * <p>MCA is not needed here — {@code erase} is pure {@code java.lang.invoke}, and a local varargs method
 * has the identical shape.
 */
class McaBindingErasureTest {

    /** Stands in for {@code Messenger#getTranslatable}: two fixed parameters, then a varargs tail. */
    public static final class Speaker {
        public Object[] getTranslatable(Object player, String key, Object... params) {
            return params;
        }

        public String getName(Object player) {
            return "name:" + player;
        }
    }

    /** {@code Member#erasedType()} for a virtual member: receiver first, then every parameter erased. */
    private static MethodType erased(int parameters) {
        return MethodType.methodType(Object.class, Collections.nCopies(parameters + 1, Object.class));
    }

    @Test
    void varargsTailReceivesTheArgumentArrayItself() throws Throwable {
        MethodHandle bound = McaBinding.erase(
                MethodHandles.lookup().unreflect(
                        Speaker.class.getMethod("getTranslatable", Object.class, String.class, Object[].class)),
                erased(3));

        Object[] args = {"Jelena", 3};
        Object seen = bound.invoke(new Speaker(), "player", "dialogue.conversations.quest.accepted", args);

        assertArrayEquals(args, (Object[]) seen,
                "the varargs tail must receive our argument array itself; a one-element array holding it "
                        + "means asFixedArity() was dropped and every %2$s renders as [Ljava.lang.Object;@…");
    }

    @Test
    void emptyArgumentsStayEmpty() throws Throwable {
        MethodHandle bound = McaBinding.erase(
                MethodHandles.lookup().unreflect(
                        Speaker.class.getMethod("getTranslatable", Object.class, String.class, Object[].class)),
                erased(3));

        Object seen = bound.invoke(new Speaker(), "player", "dialogue.conversations.us.worries", new Object[0]);

        assertEquals(0, ((Object[]) seen).length,
                "a line with no substitutions must reach MCA with no arguments at all");
    }

    @Test
    void ordinaryMembersAreUnaffected() throws Throwable {
        MethodHandle bound = McaBinding.erase(
                MethodHandles.lookup().unreflect(Speaker.class.getMethod("getName", Object.class)),
                erased(1));

        assertEquals("name:Jelena", bound.invoke(new Speaker(), "Jelena"),
                "erasure of a non-varargs member must be unchanged by the fixed-arity pin");
    }
}
