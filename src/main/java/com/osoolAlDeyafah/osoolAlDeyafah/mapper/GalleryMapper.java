package com.osoolAlDeyafah.osoolAlDeyafah.mapper;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.GalleryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Gallery;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class GalleryMapper {
    public GalleryResponse galleryToGalleryResponse(Gallery gallery){
        return GalleryResponse.builder()
                .id(gallery.getId())
                .title(gallery.getTitle())
                .description(gallery.getDescription())
                .mediaId(gallery.getMediaId())
                .mediaType(gallery.getMediaType())
                .status(gallery.getStatus())
                .createdAt(gallery.getCreatedAt())
                .updatedAt(gallery.getUpdatedAt())
                .deletedAt(gallery.getDeletedAt())
                .build();
    }

    public Gallery galleryRequestToGallery(GalleryRequest galleryRequest){
        return Gallery.builder()
                .title(galleryRequest.getTitle())
                .description(galleryRequest.getDescription())
                .mediaId(galleryRequest.getMediaId())
                .mediaType(MediaType.valueOf(galleryRequest.getMediaType()))
                .status(WebContentStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public MessageResponse toMessageResponse(String msg){
        return MessageResponse.builder()
                .msg(msg)
                .build();
    }
}
