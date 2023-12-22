package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request;

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
public class SliderRequest {
    @TrimmedString(message = "ImageId must not have leading or trailing spaces")
    @NotNull(message = "ImageId is required")
    @NotEmpty(message = "ImageId should not be empty")
    @NotBlank(message = "ImageId should not be blank")
    private String imageId;
//    @TrimmedString(message = "description must not have leading or trailing spaces")
//    @NotNull(message = "description is required")
//    @NotEmpty(message = "description should not be empty")
//    @NotBlank(message = "description should not be blank")
//    private String description;
}
