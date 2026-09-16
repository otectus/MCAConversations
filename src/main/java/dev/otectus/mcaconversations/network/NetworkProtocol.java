package dev.otectus.mcaconversations.network;

import dev.otectus.mcaconversations.McaConversations;

import java.io.InputStream;
import java.util.Properties;

/**
 * Reads the channel protocol version out of the resource {@code processResources} expands it into.
 *
 * <p>Version strings live in {@code gradle.properties} in this project and nowhere else; the channel
 * version is one, so it is declared there as {@code network_protocol} and read back here rather than
 * being spelled out in Java. Nothing else in the mod may hard-code it.
 */
final class NetworkProtocol {

    private static final String RESOURCE = "/mcaconversations-network.properties";
    /** Only reached when the resource is missing from the classpath; keep in step with gradle.properties. */
    private static final String FALLBACK = "3";

    private NetworkProtocol() {
    }

    static String version() {
        try (InputStream in = NetworkProtocol.class.getResourceAsStream(RESOURCE)) {
            if (in != null) {
                Properties properties = new Properties();
                properties.load(in);
                String value = properties.getProperty("protocol");
                if (value != null && !value.isBlank()) {
                    return value.trim();
                }
            }
        } catch (Throwable t) {
            McaConversations.LOGGER.warn("could not read the network protocol resource; using {}", FALLBACK, t);
        }
        return FALLBACK;
    }
}
