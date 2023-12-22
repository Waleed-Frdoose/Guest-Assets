package com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Gallery;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepositoryMongo extends MongoRepository<Gallery,String> {
    List<Gallery> findByStatusNot(WebContentStatus status);
    public List<Gallery> findByMediaTypeAndStatusNot(MediaType mediaType, WebContentStatus status);
    Page<GalleryProjection> findByMediaTypeAndStatusNot(MediaType mediaType, WebContentStatus status, PageRequest pageRequest);
    Optional<Gallery> findByIdAndStatusNot(String id, WebContentStatus status);
}
