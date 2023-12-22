package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.MediaTypeConstraint;
import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.TrimmedString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryRequest {
    @TrimmedString(message = "title must not have leading or trailing spaces")
    @NotNull(message = "title is required")
    @NotEmpty(message = "title should not be empty")
    @NotBlank(message = "title should not be blank")
    private String title;
    @TrimmedString(message = "description must not have leading or trailing spaces")
    @NotNull(message = "description is required")
    @NotEmpty(message = "description should not be empty")
    @NotBlank(message = "description should not be blank")
    private String description;
    @TrimmedString(message = "mediaId must not have leading or trailing spaces")
    @NotNull(message = "mediaId is required")
    @NotEmpty(message = "mediaId should not be empty")
    @NotBlank(message = "mediaId should not be blank")
    private String mediaId;
    @TrimmedString(message = "mediaType must not have leading or trailing spaces")
    @NotNull(message = "mediaType is required")
    @NotEmpty(message = "mediaType should not be empty")
    @NotBlank(message = "mediaType should not be blank")
    @MediaTypeConstraint
    private String mediaType;
}
