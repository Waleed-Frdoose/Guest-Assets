package com.osoolAlDeyafah.osoolAlDeyafah.mapper;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class OurStoryMapper {
    public OurStoryResponse ourStoryToOurStoryResponse(OurStory ourStory){
        return OurStoryResponse.builder()
                .id(ourStory.getId())
                .title(ourStory.getTitle())
                .description(ourStory.getDescription())
                .imageId(ourStory.getImageId())
                .status(ourStory.getStatus())
                .createdAt(ourStory.getCreatedAt())
                .updatedAt(ourStory.getUpdatedAt())
                .deletedAt(ourStory.getDeletedAt())
                .build();
    }

    public OurStory ourStoryRequestToOurStory(OurStoryRequest ourStoryRequest){
        return OurStory.builder()
                .title(ourStoryRequest.getTitle())
                .description(ourStoryRequest.getDescription())
                .imageId(ourStoryRequest.getImageId())
                .status(WebContentStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public MessageResponse toMessageResponse(String msg){
        return MessageResponse.builder()
                .msg(msg)
                .build();
    }
}
