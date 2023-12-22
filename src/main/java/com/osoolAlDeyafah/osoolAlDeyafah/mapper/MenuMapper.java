package com.osoolAlDeyafah.osoolAlDeyafah.mapper;

import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Menu;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.MenuRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MenuResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MenuMapper {
    public MenuResponse menuToMenuResponse(Menu menu){
        return MenuResponse.builder()
                .id(menu.getId())
                .title(menu.getTitle())
                .imageId(menu.getImageId())
                .status(menu.getStatus())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .deletedAt(menu.getDeletedAt())
                .build();
    }

    public Menu menuRequestToMenu(MenuRequest menuRequest){
        return Menu.builder()
                .title(menuRequest.getTitle())
                .imageId(menuRequest.getImageId())
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
