package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.ValidMapElements;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;
import java.util.Map;

public class MapElementsValidator implements ConstraintValidator<ValidMapElements, Map<Locale, String>> {
    @Override
    public void initialize(ValidMapElements constraintAnnotation) {
    }

    @Override
    public boolean isValid(Map<Locale, String> map, ConstraintValidatorContext context) {
        if (map == null || map.isEmpty()) {
            return false;
        }

        for (Map.Entry<Locale, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null ||
                    entry.getValue().isEmpty() || entry.getValue().isBlank() ||
                    !entry.getValue().equals(entry.getValue().trim())) {
                return false;
            }
        }


        if (!map.containsKey(new Locale("en")) || !map.containsKey(new Locale("ar"))){
            return false;
        }

        String enValue = map.get(new Locale("en"));
        String arValue = map.get(new Locale("ar"));
        if (!isValidStringValue(enValue) && !isValidStringValue(arValue)){
            return false;
        }

        return true;
    }

    private boolean isValidStringValue(String value) {
        return value != null && !value.isEmpty() && !value.isBlank() && value.equals(value.trim());
    }
}
