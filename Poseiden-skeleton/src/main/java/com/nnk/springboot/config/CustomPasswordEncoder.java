package com.nnk.springboot.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_DIGITS = 1;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //On laisseBCryptEncoder s'occuper de l'encodage sans regles particuliere
    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }

        int digitCount = 0;
        int specialCharCount = 0;

        for (char c : rawPassword.toString().toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (SPECIAL_CHARACTERS.indexOf(c) != -1) {
                specialCharCount++;
            }
        }

        return digitCount >= MIN_DIGITS && specialCharCount > 0 && encoder.matches(rawPassword, encodedPassword);
    }
}
