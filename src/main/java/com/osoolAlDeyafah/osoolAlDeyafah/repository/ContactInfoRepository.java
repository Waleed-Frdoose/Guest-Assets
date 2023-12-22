package com.osoolAlDeyafah.osoolAlDeyafah.repository;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.ContactInformation;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;

import java.util.List;
import java.util.Optional;

public interface ContactInfoRepository {
    public ContactInformation save(ContactInformation contactInformation);
    List<ContactInformation> findAll(WebContentStatus status);
    Optional<ContactInformation> findById(String id, WebContentStatus status);
    public ContactInformation update(ContactInformation contactInformation);
    void deleteById(ContactInformation contactInformation);


}
