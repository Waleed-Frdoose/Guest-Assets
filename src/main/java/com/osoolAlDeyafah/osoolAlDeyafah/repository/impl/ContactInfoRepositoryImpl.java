package com.osoolAlDeyafah.osoolAlDeyafah.repository.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.ContactInformation;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.ContactInfoRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.mongo.ContactInfoRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactInfoRepositoryImpl implements ContactInfoRepository {

    @Autowired
    private ContactInfoRepositoryMongo repositoryMongo;

    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        return this.repositoryMongo.save(contactInformation);
    }

    @Override
    public List<ContactInformation> findAll(WebContentStatus status) {
        return this.repositoryMongo.findByStatusNot(status);
    }

    @Override
    public Optional<ContactInformation> findById(String id, WebContentStatus status) {
        return this.repositoryMongo.findByIdAndStatusNot(id, status);
    }

    @Override
    public ContactInformation update(ContactInformation contactInformation) {
        return this.repositoryMongo.save(contactInformation);
    }

    @Override
    public void deleteById(ContactInformation contactInformation) {
        this.repositoryMongo.save(contactInformation);
    }
}
