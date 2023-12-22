package com.osoolAlDeyafah.osoolAlDeyafah.model.document;


import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "sliderImage")
public class Slider {
    @Id
    private String id;
    private String imageId;
//    private String description;
    private WebContentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
