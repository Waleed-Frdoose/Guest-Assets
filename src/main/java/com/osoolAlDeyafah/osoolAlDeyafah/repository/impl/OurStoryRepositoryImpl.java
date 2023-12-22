package com.osoolAlDeyafah.osoolAlDeyafah.repository.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.OurStoryRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.OurStoryRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OurStoryRepositoryImpl implements OurStoryRepository {

    @Autowired
    private OurStoryRepositoryMongo repositoryMongo;

    @Override
    public OurStory save(OurStory ourStory) {
        return this.repositoryMongo.save(ourStory);
    }

    @Override
    public List<OurStory> findAll(WebContentStatus status) {
        return this.repositoryMongo.findByStatusNot(status);
    }

    @Override
    public Optional<OurStory> findById(String id, WebContentStatus status) {
        return this.repositoryMongo.findByIdAndStatusNot(id, status);
    }

    @Override
    public OurStory update(OurStory ourStory) {
        return this.repositoryMongo.save(ourStory);
    }

    @Override
    public void deleteById(OurStory ourStory) {
        this.repositoryMongo.save(ourStory);
    }
}
