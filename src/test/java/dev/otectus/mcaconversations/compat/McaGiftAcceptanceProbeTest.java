package dev.otectus.mcaconversations.compat;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import static org.junit.jupiter.api.Assertions.*;

/** Pins the real MCA transfer instruction and the mixin's accepted-only injection point. */
class McaGiftAcceptanceProbeTest {
    @Test
    void eachMcaBuildTransfersExactlyOneItemAtTheHook() throws Exception {
        String jars = System.getProperty("mcaconversations.probe.jars", "");
        assertFalse(jars.isBlank(), "the binary gift probe requires the configured MCA artifacts");
        for (String path : jars.split(File.pathSeparator)) {
            try (JarFile jar = new JarFile(path)) {
                var entry = jar.stream().filter(e -> e.getName().endsWith(
                        "/entity/ai/BreedableRelationship.class")
                        && (e.getName().startsWith("forge/") || e.getName().startsWith("net/")))
                        .findFirst().orElseThrow();
                List<Integer> transferCounts = new ArrayList<>();
                new ClassReader(jar.getInputStream(entry)).accept(new ClassVisitor(Opcodes.ASM9) {
                    @Override public MethodVisitor visitMethod(int access, String name, String descriptor,
                                                               String signature, String[] exceptions) {
                        if (!name.equals("acceptGift")) return null;
                        return new MethodVisitor(Opcodes.ASM9) {
                            int lastConstant = -1;
                            @Override public void visitInsn(int opcode) { lastConstant = opcode; }
                            @Override public void visitMethodInsn(int opcode, String owner, String name,
                                                                  String descriptor, boolean itf) {
                                if (owner.equals("net/minecraft/world/item/ItemStack")
                                        && (name.equals("split") || name.equals("m_41620_"))
                                        && descriptor.equals("(I)Lnet/minecraft/world/item/ItemStack;")) {
                                    transferCounts.add(lastConstant);
                                }
                            }
                        };
                    }
                }, 0);
                assertEquals(List.of(Opcodes.ICONST_1), transferCounts, path);
            }
        }
    }

    @Test
    void theGiftHookRunsAtTheTransferInsteadOfBeforeAcceptanceChecks() throws Exception {
        List<String> anchors = new ArrayList<>();
        try (var stream = getClass().getResourceAsStream(
                "/dev/otectus/mcaconversations/mixin/BreedableRelationshipMixin.class")) {
            assertNotNull(stream);
            new ClassReader(stream).accept(new ClassVisitor(Opcodes.ASM9) {
                @Override public MethodVisitor visitMethod(int access, String name, String descriptor,
                                                           String signature, String[] exceptions) {
                    if (!name.equals("mcaconversations$onAcceptGift")) return null;
                    return new MethodVisitor(Opcodes.ASM9) {
                        @Override public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                            return annotations(anchors);
                        }
                    };
                }
            }, ClassReader.SKIP_CODE);
        }
        assertTrue(anchors.contains("value=INVOKE"));
        assertTrue(anchors.contains("shift=BEFORE"));
        assertTrue(anchors.contains("target=Lnet/minecraft/world/item/ItemStack;split(I)Lnet/minecraft/world/item/ItemStack;"));
        assertFalse(anchors.contains("value=HEAD"));
    }

    private static AnnotationVisitor annotations(List<String> values) {
        return new AnnotationVisitor(Opcodes.ASM9) {
            @Override public void visit(String name, Object value) { values.add(name + "=" + value); }
            @Override public void visitEnum(String name, String descriptor, String value) { values.add(name + "=" + value); }
            @Override public AnnotationVisitor visitArray(String name) { return this; }
            @Override public AnnotationVisitor visitAnnotation(String name, String descriptor) { return this; }
        };
    }
}
