package com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.WorkingHours;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoResponse {
    private String id;
    private String address;
    private String email;
    private String phone;
    private List<WorkingHours> workingHours;
    private WebContentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
