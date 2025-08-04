package com.taskgo.utilities;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomsGeneratorUtil {
    private static final SecureRandom random = new SecureRandom();
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "@$!%*?&#";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIALS;
    private static final int PASSWORD_LENGTH = 8;

    /**
     * Generates a random 4-digit numeric code.
     *
     * @return a 4-digit code as a string
     */
    public static String generateFourDigitCode() {
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

    /**
     * Generates a secure random password of fixed length.
     * The password will contain at least one uppercase letter, one lowercase letter,
     * one digit, and one special character.
     *
     * @return a secure password string
     */
    public static String generateSecurePassword() {
        List<Character> passwordChars = new ArrayList<>();
        passwordChars.add(UPPER.charAt(random.nextInt(UPPER.length())));
        passwordChars.add(LOWER.charAt(random.nextInt(LOWER.length())));
        passwordChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        passwordChars.add(SPECIALS.charAt(random.nextInt(SPECIALS.length())));

        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            passwordChars.add(ALL.charAt(random.nextInt(ALL.length())));
        }

        Collections.shuffle(passwordChars, random);

        StringBuilder password = new StringBuilder();
        for (char ch : passwordChars) {
            password.append(ch);
        }

        return password.toString();
    }
}
