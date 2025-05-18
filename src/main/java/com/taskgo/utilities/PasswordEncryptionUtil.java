package com.taskgo.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j2
public class PasswordEncryptionUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * @param rawPassword The plain-text password
     * @return The encrypted password
     */
    public static String encodePassword(String rawPassword) {
        log.info("Encrypting password: {}", rawPassword);
        return encoder.encode(rawPassword);
    }

    /**
     * @param rawPassword     A plain-text password
     * @param encodedPassword An encrypted password
     * @return Whether the plain-text password matches the encrypted password or not
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        log.info("Matching plain-text password: {} with an encrypted-password: {}", rawPassword, encodedPassword);
        return encoder.matches(rawPassword, encodedPassword);
    }
}