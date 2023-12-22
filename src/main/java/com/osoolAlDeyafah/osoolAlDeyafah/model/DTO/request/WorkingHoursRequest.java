package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.TrimmedString;
import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.ValidDayOfWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingHoursRequest {
    @TrimmedString(message = "dayOfWeek must not have leading or trailing spaces")
    @NotNull(message = "dayOfWeek is required")
    @NotEmpty(message = "dayOfWeek should not be empty")
    @NotBlank(message = "dayOfWeek should not be blank")
    @ValidDayOfWeek
    private String dayOfWeek;
    @TrimmedString(message = "openingTime must not have leading or trailing spaces")
    @NotNull(message = "openingTime is required")
    @NotEmpty(message = "openingTime should not be empty")
    @NotBlank(message = "openingTime should not be blank")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "openingTime should be in HH:mm format and the hh between 00 - 23, the mm between 00 - 59")
    private String openingTime;
    @TrimmedString(message = "closingTime must not have leading or trailing spaces")
    @NotNull(message = "closingTime is required")
    @NotEmpty(message = "closingTime should not be empty")
    @NotBlank(message = "closingTime should not be blank")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "closingTime should be in HH:mm format and the hh between 00 - 23, the mm between 00 - 59")
    private String closingTime;
}
