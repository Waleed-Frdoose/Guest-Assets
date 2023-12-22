package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request;

import com.osoolAlDeyafah.osoolAlDeyafah.customAnnotation.TrimmedString;
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
public class SendEmailRequest {
    @TrimmedString(message = "name must not have leading or trailing spaces")
    @NotNull(message = "name is required")
    @NotEmpty(message = "name should not be empty")
    @NotBlank(message = "name should not be blank")
    private String name;
//    @TrimmedString(message = "lastname must not have leading or trailing spaces")
//    @NotNull(message = "lastname is required")
//    @NotEmpty(message = "lastname should not be empty")
//    @NotBlank(message = "lastname should not be blank")
//    private String lastname;
    @TrimmedString(message = "email must not have leading or trailing spaces")
    @NotNull(message = "email is required")
    @NotEmpty(message = "email should not be empty")
    @NotBlank(message = "email should not be blank")
    @Pattern(regexp = ".*@.*", message = "Email must contain the @ symbol")
    private String fromEmail;
    @TrimmedString(message = "phone must not have leading or trailing spaces")
    @NotNull(message = "phone is required")
    @NotEmpty(message = "phone should not be empty")
    @NotBlank(message = "phone should not be blank")
    @Pattern(regexp = "^(\\+?962|0)7[789]\\d{7}$", message = "Invalid Jordanian phone number format.")
    private String phone;
//    @TrimmedString(message = "body must not have leading or trailing spaces")
    @NotNull(message = "body is required")
    @NotEmpty(message = "body should not be empty")
    @NotBlank(message = "body should not be blank")
    private String body;
}
