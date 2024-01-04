package com.nnk.springboot.domain.annotation;

import com.nnk.springboot.config.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must have minimum 8 char, 1 digit and 1 special char (!@#$%^&*()-+.)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
