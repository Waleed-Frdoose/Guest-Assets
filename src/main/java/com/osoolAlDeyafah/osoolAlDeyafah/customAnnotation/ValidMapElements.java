package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl.MapElementsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MapElementsValidator.class)
@Documented
public @interface ValidMapElements {
    String message() default "Map elements must not be empty, null, blank, or have leading or trailing spaces, and must contain 'en' and 'ar' keys";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
