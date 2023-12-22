package com.osoolAlDeyafah.osoolAlDeyafah.mapper;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.ContactInformation;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.WorkingHours;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.ContactInfoRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.WorkingHoursRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.ContactInfoResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class ContactInfoMapper {
    public ContactInfoResponse contactInfoToContactInfoResponse(ContactInformation contactInformation){
        return ContactInfoResponse.builder()
                .id(contactInformation.getId())
                .address(contactInformation.getAddress())
                .email(contactInformation.getEmail())
                .phone(contactInformation.getPhone())
                .workingHours(contactInformation.getWorkingHours())
                .status(contactInformation.getStatus())
                .createdAt(contactInformation.getCreatedAt())
                .updatedAt(contactInformation.getUpdatedAt())
                .deletedAt(contactInformation.getDeletedAt())
                .build();
    }

    public ContactInformation contactInfoRequestToContactInfo(ContactInfoRequest contactInfoRequest){
        return ContactInformation.builder()
                .address(contactInfoRequest.getAddress())
                .email(contactInfoRequest.getEmail())
                .phone(contactInfoRequest.getPhone())
                .workingHours(workingHoursRequestToWorkingHours(contactInfoRequest.getWorkingHours()))
                .status(WebContentStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public List<WorkingHours> workingHoursRequestToWorkingHours(List<WorkingHoursRequest> workingHoursRequest){

        return workingHoursRequest.stream().map(x -> WorkingHours.builder()
                .dayOfWeek(DayOfWeek.valueOf(x.getDayOfWeek().toUpperCase()))
                .openingTime(LocalTime.parse(x.getOpeningTime()))
                .closingTime(LocalTime.parse(x.getClosingTime()))
                .build()).toList();

    }

    public MessageResponse toMessageResponse(String msg){
        return MessageResponse.builder()
                .msg(msg)
                .build();
    }
}
