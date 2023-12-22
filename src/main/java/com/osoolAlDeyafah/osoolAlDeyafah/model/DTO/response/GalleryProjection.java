package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response;

import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;

import java.time.LocalDateTime;

public interface GalleryProjection {
    String getId();
    String getTitle();
    String getDescription();
    String getMediaId();
    MediaType getMediaType();
    WebContentStatus getStatus();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    LocalDateTime getDeletedAt();
}
