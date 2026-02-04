package com.student.infomation;
import java.io.*;
import java.util.*;

public class EnvLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = new FileInputStream(".env")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load .env file", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

