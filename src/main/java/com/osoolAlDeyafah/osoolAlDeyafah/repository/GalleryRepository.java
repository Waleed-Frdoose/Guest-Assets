package com.osoolAlDeyafah.osoolAlDeyafah.repository;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Gallery;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository {
    public Gallery save(Gallery gallery);
    public List<Gallery> findByMediaType(MediaType mediaType, WebContentStatus status);
    Page<GalleryProjection> findByMediaType(MediaType mediaType, WebContentStatus status, PageRequest pageRequest);
    public Optional<Gallery> findById(String id, WebContentStatus status);
}
