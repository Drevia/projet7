package com.nnk.springboot.config;

import com.nnk.springboot.domain.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_DIGITS = 1;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String rawPassword, ConstraintValidatorContext constraintValidatorContext) {
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
        return digitCount >= MIN_DIGITS && specialCharCount > 0;
    }
}
