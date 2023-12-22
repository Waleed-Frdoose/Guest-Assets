package com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.ContactInformation;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInfoRepositoryMongo extends MongoRepository<ContactInformation,String> {
    List<ContactInformation> findByStatusNot(WebContentStatus status);
    Optional<ContactInformation> findByIdAndStatusNot(String id, WebContentStatus status);

}
