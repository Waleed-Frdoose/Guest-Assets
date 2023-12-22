package com.osoolAlDeyafah.osoolAlDeyafah.repository;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Menu;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    public Menu save(Menu menu);
    List<Menu> findAll(WebContentStatus status);
    Optional<Menu> findByImageId(String imageId, WebContentStatus status);
    Optional<Menu> findById(String id, WebContentStatus status);
    void deleteByImageId(Menu menu);
    void deleteById(Menu menu);



}
