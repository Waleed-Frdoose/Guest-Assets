package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl.MediaTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MediaTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MediaTypeConstraint {
    String message() default "Invalid Media Type. Allowed values are IMAGE or VIDEO.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
