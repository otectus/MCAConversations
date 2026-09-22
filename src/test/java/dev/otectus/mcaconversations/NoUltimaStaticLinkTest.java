package dev.otectus.mcaconversations;

import dev.otectus.mcaconversations.support.TestPaths;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Proves the optional Ultima bridge contains no JVM type reference that would make it mandatory. */
class NoUltimaStaticLinkTest {

    private static final byte[] NEEDLE = "com/ultimakingdoms/".getBytes(StandardCharsets.UTF_8);

    @Test
    void noCompiledClassStaticallyLinksUltima() throws IOException {
        Path classes = TestPaths.of("build/classes/java/main");
        List<String> violations = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(classes)) {
            for (Path path : paths.filter(file -> file.toString().endsWith(".class")).toList()) {
                if (contains(Files.readAllBytes(path), NEEDLE)) {
                    violations.add(classes.relativize(path).toString().replace('\\', '/'));
                }
            }
        }
        assertTrue(violations.isEmpty(), "Ultima is optional, but these classes statically link it: " + violations);
    }

    private static boolean contains(byte[] haystack, byte[] needle) {
        outer:
        for (int i = 0; i <= haystack.length - needle.length; i++) {
            for (int j = 0; j < needle.length; j++) {
                if (haystack[i + j] != needle[j]) continue outer;
            }
            return true;
        }
        return false;
    }
}
