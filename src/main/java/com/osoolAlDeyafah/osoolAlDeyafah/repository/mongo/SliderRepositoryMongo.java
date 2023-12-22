package com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Slider;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SliderRepositoryMongo extends MongoRepository<Slider,String> {
    List<Slider> findByStatusNot(WebContentStatus status);
    Optional<Slider> findByImageIdAndStatusNot(String imageId, WebContentStatus status);
    Optional<Slider> findByIdAndStatusNot(String imageId, WebContentStatus status);

}
