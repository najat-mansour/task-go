package com.taskgo.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     *
     * @param rawPassword The plain-text password
     * @return The encrypted password
     */
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     *
     * @param rawPassword A plain-text password
     * @param encodedPassword A encrypted password
     * @return Whether the plain-text password matches the encrypted password or not
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
