package com.osoolAlDeyafah.osoolAlDeyafah.mapper;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Slider;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SliderRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.SliderResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SliderMapper {
    public SliderResponse sliderToSliderResponse(Slider slider){
        return SliderResponse.builder()
                .id(slider.getId())
                .imageId(slider.getImageId())
//                .description(slider.getDescription())
                .status(slider.getStatus())
                .createdAt(slider.getCreatedAt())
                .updatedAt(slider.getUpdatedAt())
                .deletedAt(slider.getDeletedAt())
                .build();
    }

    public Slider sliderRequestToSlider(SliderRequest sliderRequest){
        return Slider.builder()
                .imageId(sliderRequest.getImageId())
//                .description(sliderRequest.getDescription())
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
