package com.osoolAlDeyafah.osoolAlDeyafah.repository.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Menu;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.MenuRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.MenuRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    private MenuRepositoryMongo repositoryMongo;

    @Override
    public Menu save(Menu menu) {
        return this.repositoryMongo.save(menu);
    }

    @Override
    public List<Menu> findAll(WebContentStatus status) {
        return this.repositoryMongo.findByStatusNot(status);
    }

    @Override
    public Optional<Menu> findByImageId(String imageId, WebContentStatus status) {
        return this.repositoryMongo.findByImageIdAndStatusNot(imageId, status);
    }

    @Override
    public Optional<Menu> findById(String id, WebContentStatus status) {
        return this.repositoryMongo.findByIdAndStatusNot(id, status);
    }

    @Override
    public void deleteByImageId(Menu menu) {
        this.repositoryMongo.save(menu);
    }

    @Override
    public void deleteById(Menu menu) {
        this.repositoryMongo.save(menu);
    }

}
