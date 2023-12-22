package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response;

import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponse {
    private String id;
    private String title;
    private String imageId;
    private WebContentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
