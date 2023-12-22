package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl.DayOfWeekValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DayOfWeekValidator.class)
@ReportAsSingleViolation
public @interface ValidDayOfWeek {
    String message() default "Invalid dayOfWeek value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
