package com.osoolAlDeyafah.osoolAlDeyafah.repository.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Slider;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.SliderRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.SliderRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SliderRepositoryImpl implements SliderRepository {

    @Autowired
    private SliderRepositoryMongo repositoryMongo;

    @Override
    public Slider save(Slider slider) {
        return this.repositoryMongo.save(slider);
    }

    @Override
    public List<Slider> findAll(WebContentStatus status) {
        return this.repositoryMongo.findByStatusNot(status);
    }

    @Override
    public Optional<Slider> findByImageId(String imageId, WebContentStatus status) {
        return this.repositoryMongo.findByImageIdAndStatusNot(imageId, status);
    }

    @Override
    public Optional<Slider> findById(String id, WebContentStatus status) {
        return this.repositoryMongo.findByIdAndStatusNot(id, status);
    }

    @Override
    public void deleteByImageId(Slider slider) {
        this.repositoryMongo.save(slider);
    }

    @Override
    public void deleteById(Slider slider) {
        this.repositoryMongo.save(slider);
    }
}
