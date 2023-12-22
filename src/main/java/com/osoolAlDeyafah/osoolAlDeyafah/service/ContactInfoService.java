package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.ContactInfoRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SendEmailRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.ContactInfoResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;

import java.util.List;

public interface ContactInfoService {
    public ContactInfoResponse create(ContactInfoRequest request);
    public List<ContactInfoResponse> getAll();
    public ContactInfoResponse getById(String id);
    public ContactInfoResponse updateByID(ContactInfoRequest updateRequest, String id);
    public MessageResponse deleteById(String id);
    public MessageResponse sendSimpleEmail(SendEmailRequest emailRequest);
}
