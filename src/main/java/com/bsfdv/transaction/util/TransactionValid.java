package com.bsfdv.transaction.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TransactionTypeValidator.class})
public @interface TransactionValid {

    String message() default "Incorrect Transaction parties.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}