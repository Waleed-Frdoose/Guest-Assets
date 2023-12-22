package com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.MediaTypeConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MediaTypeValidator implements ConstraintValidator<MediaTypeConstraint, String> {
    @Override
    public boolean isValid(String mediaType, ConstraintValidatorContext constraintValidatorContext) {
        return mediaType != null && (mediaType.equals("IMAGE") || mediaType.equals("VIDEO"));
    }
}
