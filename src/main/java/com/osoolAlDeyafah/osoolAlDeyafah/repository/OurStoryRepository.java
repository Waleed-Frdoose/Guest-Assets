package com.osoolAlDeyafah.osoolAlDeyafah.repository;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;

import java.util.List;
import java.util.Optional;

public interface OurStoryRepository {
    public OurStory save(OurStory ourStory);
    List<OurStory> findAll(WebContentStatus status);
    Optional<OurStory> findById(String id, WebContentStatus status);
    public OurStory update(OurStory ourStory);
    void deleteById(OurStory ourStory);


}
