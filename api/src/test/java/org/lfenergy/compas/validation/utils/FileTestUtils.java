package org.lfenergy.compas.validation.utils;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileTestUtils {

    private FileTestUtils() {
        // Utility class - prevent instantiation
    }

    public static String stringifyFile(String filePath) throws IOException {
        try (InputStream inputStream = FileTestUtils.class.getResourceAsStream(filePath)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
