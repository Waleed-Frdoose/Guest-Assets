package com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OurStoryRepositoryMongo extends MongoRepository<OurStory,String> {
    List<OurStory> findByStatusNot(WebContentStatus status);
    Optional<OurStory> findByIdAndStatusNot(String id, WebContentStatus status);

}
