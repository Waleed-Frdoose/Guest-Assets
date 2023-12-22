package com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Menu;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepositoryMongo extends MongoRepository<Menu,String> {
    List<Menu> findByStatusNot(WebContentStatus status);
    Optional<Menu> findByImageIdAndStatusNot(String imageId, WebContentStatus status);
    Optional<Menu> findByIdAndStatusNot(String imageId, WebContentStatus status);
}
