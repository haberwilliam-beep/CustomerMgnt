package com.cms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class EncryptionUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    private EncryptionUtil() {}

    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
