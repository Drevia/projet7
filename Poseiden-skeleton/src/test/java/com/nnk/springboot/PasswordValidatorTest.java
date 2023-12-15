package com.nnk.springboot;

import com.nnk.springboot.config.PasswordValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {

    @InjectMocks
    private PasswordValidator passwordValidator;

    @Test
    void testPasswordValidityOk() {
        String password = "abcdefgh1!";

        boolean isValid = passwordValidator.isValid(password, null);
        assertTrue(isValid);
    }

}
