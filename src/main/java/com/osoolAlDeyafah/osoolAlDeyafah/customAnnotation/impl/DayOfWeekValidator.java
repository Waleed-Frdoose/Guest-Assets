package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.ValidDayOfWeek;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DayOfWeekValidator implements ConstraintValidator<ValidDayOfWeek, String> {
    private static final Set<String> VALID_DAYS = new HashSet<>(
            Arrays.asList("WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY", "MONDAY", "TUESDAY")
    );
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && VALID_DAYS.contains(value.trim().toUpperCase());
    }
}
