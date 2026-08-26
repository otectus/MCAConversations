package dev.otectus.mcaconversations.support;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Reads a {@code static final int} constant straight out of a class file in a jar, without loading
 * the class.
 *
 * <p>Reflection cannot do this job here. {@code Field#getInt} forces the declaring class to
 * initialise, and a published Forge mod references Minecraft members by their SRG names
 * ({@code f_256913_}) while a dev-mapped test JVM has the official ones — so initialising any class
 * of a production mod jar that touches Minecraft state throws {@code NoSuchFieldError}. A constant is
 * stored in the field's {@code ConstantValue} attribute, which needs no linkage at all to read.
 *
 * <p>Deliberately dependency-free, in the same spirit as the byte-scanning static-link tripwires:
 * this parses only as much of the class file as it takes to walk the constant pool and the field
 * table, so it adds no bytecode library to the test classpath and runs on any JDK.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html">JVMS §4, ClassFile</a>
 */
public final class ClassFileConstants {

    private ClassFileConstants() {
    }

    /**
     * The compile-time value of {@code binaryClassName.fieldName}, or {@code null} when the jar has no
     * such class, the class has no such {@code int} field, or the field is not a constant.
     */
    @Nullable
    public static Integer staticFinalInt(Path jar, String binaryClassName, String fieldName)
            throws IOException {
        String entryName = binaryClassName.replace('.', '/') + ".class";
        try (ZipFile zip = new ZipFile(jar.toFile())) {
            ZipEntry entry = zip.getEntry(entryName);
            if (entry == null) {
                return null;
            }
            try (InputStream in = zip.getInputStream(entry)) {
                return readConstant(ByteBuffer.wrap(in.readAllBytes()), fieldName);
            }
        }
    }

    @Nullable
    private static Integer readConstant(ByteBuffer buf, String fieldName) {
        if (buf.remaining() < 10 || buf.getInt() != 0xCAFEBABE) {
            return null;
        }
        buf.getShort(); // minor version
        buf.getShort(); // major version

        int poolCount = u2(buf);
        Map<Integer, String> utf8 = new HashMap<>();
        Map<Integer, Integer> integers = new HashMap<>();
        for (int index = 1; index < poolCount; index++) {
            int tag = u1(buf);
            switch (tag) {
                case 1 -> { // Utf8
                    byte[] bytes = new byte[u2(buf)];
                    buf.get(bytes);
                    utf8.put(index, new String(bytes, java.nio.charset.StandardCharsets.UTF_8));
                }
                case 3 -> integers.put(index, buf.getInt());            // Integer
                case 4 -> buf.getInt();                                  // Float
                case 5, 6 -> {                                           // Long, Double
                    buf.getLong();
                    index++; // these occupy two pool slots (JVMS 4.4.5)
                }
                case 7, 8, 16, 19, 20 -> buf.getShort();                 // *_index forms
                case 15 -> {                                             // MethodHandle
                    u1(buf);
                    buf.getShort();
                }
                case 9, 10, 11, 12, 17, 18 -> buf.getInt();              // pairs of indices
                default -> {
                    return null; // an unknown tag means we cannot trust our position any more
                }
            }
        }

        buf.getShort(); // access flags
        buf.getShort(); // this class
        buf.getShort(); // super class
        int interfaces = u2(buf);
        buf.position(buf.position() + interfaces * 2);

        int fields = u2(buf);
        for (int i = 0; i < fields; i++) {
            buf.getShort(); // access flags
            String name = utf8.get(u2(buf));
            String descriptor = utf8.get(u2(buf));
            int attributes = u2(buf);
            Integer value = null;
            for (int a = 0; a < attributes; a++) {
                String attributeName = utf8.get(u2(buf));
                int length = buf.getInt();
                int end = buf.position() + length;
                if ("ConstantValue".equals(attributeName) && length == 2) {
                    value = integers.get(u2(buf));
                }
                buf.position(end);
            }
            if (fieldName.equals(name) && "I".equals(descriptor) && value != null) {
                return value;
            }
        }
        return null;
    }

    private static int u1(ByteBuffer buf) {
        return buf.get() & 0xFF;
    }

    private static int u2(ByteBuffer buf) {
        return buf.getShort() & 0xFFFF;
    }
}
