package com.osoolAlDeyafah.osoolAlDeyafah.repository;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Slider;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;

import java.util.List;
import java.util.Optional;

public interface SliderRepository {
    public Slider save(Slider slider);
    List<Slider> findAll(WebContentStatus status);
    Optional<Slider> findByImageId(String imageId, WebContentStatus status);
    Optional<Slider> findById(String id, WebContentStatus status);
    void deleteByImageId(Slider slider);
    void deleteById(Slider slider);

}
