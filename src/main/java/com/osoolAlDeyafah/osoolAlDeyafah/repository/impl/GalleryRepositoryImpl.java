package com.osoolAlDeyafah.osoolAlDeyafah.repository.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Gallery;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.GalleryRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.GalleryRepositoryMongo;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.OurStoryRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GalleryRepositoryImpl implements GalleryRepository {

    @Autowired
    private GalleryRepositoryMongo repositoryMongo;

    @Override
    public Gallery save(Gallery gallery) {
        return this.repositoryMongo.save(gallery);
    }

    @Override
    public List<Gallery> findByMediaType(MediaType mediaType, WebContentStatus status) {
        return this.repositoryMongo.findByMediaTypeAndStatusNot(mediaType, status);
    }

    @Override
    public Page<GalleryProjection> findByMediaType(MediaType mediaType, WebContentStatus status, PageRequest pageRequest) {
        return this.repositoryMongo.findByMediaTypeAndStatusNot(mediaType, status, pageRequest);
    }

    @Override
    public Optional<Gallery> findById(String id, WebContentStatus status) {
        return this.repositoryMongo.findByIdAndStatusNot(id, status);
    }
}
