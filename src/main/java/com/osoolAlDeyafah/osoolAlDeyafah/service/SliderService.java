package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SliderRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.SliderResponse;

import java.util.List;

public interface SliderService {
    public SliderResponse create(SliderRequest request);
    public List<SliderResponse> getAll();
    public SliderResponse updateById(SliderRequest request, String id);
    public SliderResponse updateByImageId(SliderRequest request, String imageId);
    public MessageResponse deleteByImageId(String imageId);
    public MessageResponse deleteById(String id);
}
