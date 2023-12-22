package com.osoolAlDeyafah.osoolAlDeyafah.controller;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.MenuRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MenuResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MenuService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/menus")
public class MenuController {

    @Autowired
    private MenuService service;

    @PostMapping
    public ResponseEntity<MenuResponse> create(@Valid @RequestBody MenuRequest request){
        log.info("(Menu Controller) Creating menu with request: {}", request);
        return ResponseEntity.ok(this.service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAll(){
        log.info("(Menu Controller) Fetching all menus");
        return ResponseEntity.ok(this.service.getAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuResponse> updateById(@Valid @RequestBody MenuRequest request, @PathVariable String id){
        log.info("(Menu Controller) Updating menu by ID: {} with request: {}", id, request);
        return ResponseEntity.ok(this.service.updateById(request,id));
    }

    @PutMapping("/image-id/{imageId:.+}")
    public ResponseEntity<MenuResponse> updateByImageId(@Valid @RequestBody MenuRequest request, @PathVariable String imageId){
        log.info("(Menu Controller) Updating menu by image ID: {} with request: {}", imageId, request);
        return ResponseEntity.ok(this.service.updateByImageId(request,imageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable String id){
        log.info("(Menu Controller) Deleting menu by ID: {}", id);
        return ResponseEntity.ok(this.service.deleteById(id));
    }

    @DeleteMapping("/image-id/{imageId:.+}")
    public ResponseEntity<MessageResponse> deleteByImageId(@PathVariable String imageId){
        log.info("(Menu Controller) Deleting menu by image ID: {}", imageId);
        return ResponseEntity.ok(this.service.deleteByImageId(imageId));
    }

}
