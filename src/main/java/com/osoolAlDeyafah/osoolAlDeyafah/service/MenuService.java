package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.MenuRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MenuResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;

import java.util.List;

public interface MenuService {
    public MenuResponse create(MenuRequest request);
    public List<MenuResponse> getAll();
    public MenuResponse updateById(MenuRequest request, String id);
    public MenuResponse updateByImageId(MenuRequest request, String imageId);
    public MessageResponse deleteByImageId(String imageId);
    public MessageResponse deleteById(String id);
}
