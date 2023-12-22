package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;

import java.util.List;

public interface OurStoryService {
    public OurStoryResponse create(OurStoryRequest request);
    public List<OurStoryResponse> getAll();
    public OurStoryResponse getById(String id);
    public OurStoryResponse updateByID(OurStoryRequest updateRequest, String id);
    public MessageResponse deleteById(String id);
}
