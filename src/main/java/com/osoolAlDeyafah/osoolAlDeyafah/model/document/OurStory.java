package com.osoolAlDeyafah.osoolAlDeyafah.model.document;

import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ourStory")
public class OurStory {
    @Id
    private String id;
    private String title;
    private String description;
    private String imageId;
    private WebContentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
