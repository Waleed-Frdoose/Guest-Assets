package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.GalleryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GalleryService {
    public GalleryResponse create(GalleryRequest request);
    public List<GalleryResponse> getAllImage();
    public Page<GalleryProjection> getAllImage(int page, int size);
    public List<GalleryResponse> getAllVideo();
    public Page<GalleryProjection> getAllVideo(int page, int size);
    public GalleryResponse getById(String id);
    public GalleryResponse updateByID(GalleryRequest request, String id);
    MessageResponse deleteById(String id);
}
