package com.nnk.springboot.config;

import com.nnk.springboot.domain.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * this class is used to check if a password fulfill the required conditions such
 * as minimum length, using digit and special characters, if not, the password is
 * not valid
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_DIGITS = 1;
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-+.";

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
        int lowerCaseChar = 0;

        for (char c : rawPassword.toString().toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (SPECIAL_CHARACTERS.indexOf(c) != -1) {
                specialCharCount++;
            }

            if(Character.isLowerCase(c)){
                lowerCaseChar++;
            }
        }

        if (lowerCaseChar == 0) {
            return false;
        }
        return digitCount >= MIN_DIGITS && specialCharCount > 0;
    }
}
