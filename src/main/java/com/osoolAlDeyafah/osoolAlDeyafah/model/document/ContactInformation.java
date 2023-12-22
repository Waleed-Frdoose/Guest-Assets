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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "contactInformation")
public class ContactInformation {
    @Id
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
