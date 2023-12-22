package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.TrimmedString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInfoRequest {
    @TrimmedString(message = "address must not have leading or trailing spaces")
    @NotNull(message = "address is required")
    @NotEmpty(message = "address should not be empty")
    @NotBlank(message = "address should not be blank")
    private String address;
    @TrimmedString(message = "email must not have leading or trailing spaces")
    @NotNull(message = "email is required")
    @NotEmpty(message = "email should not be empty")
    @NotBlank(message = "email should not be blank")
    @Pattern(regexp = ".*@.*", message = "Email must contain the @ symbol")
    private String email;
    @TrimmedString(message = "phone must not have leading or trailing spaces")
    @NotNull(message = "phone is required")
    @NotEmpty(message = "phone should not be empty")
    @NotBlank(message = "phone should not be blank")
    @Pattern(regexp = "^(\\+?962|0)7[789]\\d{7}$", message = "Invalid Jordanian phone number format.")
    private String phone;
    @NotNull(message = "workingHours is required")
    @NotEmpty(message = "workingHours should not be empty")
    @Valid
    private List<WorkingHoursRequest> workingHours;
}
