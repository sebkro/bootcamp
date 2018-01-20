package com.cgi.bootcamp.survey.ui.models.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = EmailsConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailsConstraint {
    String message() default "Bitte gebe nur gültige E-Mail-Adressen an.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}