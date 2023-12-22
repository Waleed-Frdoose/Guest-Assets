package com.osoolAlDeyafah.osoolAlDeyafah.controller;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.MenuRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SliderRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MenuResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.SliderResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.SliderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/sliders")
public class SliderController {

    @Autowired
    private SliderService service;

    @PostMapping
    public ResponseEntity<SliderResponse> create(@Valid @RequestBody SliderRequest request){
        log.info("(Slider Controller) Creating slider with request: {}", request);
        return ResponseEntity.ok(this.service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SliderResponse>> getAll(){
        log.info("(Slider Controller) Fetching all sliders");
        return ResponseEntity.ok(this.service.getAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<SliderResponse> updateById(@Valid @RequestBody SliderRequest request, @PathVariable String id){
        log.info("(Slider Controller) Updating slider by ID: {} with request: {}", id, request);
        return ResponseEntity.ok(this.service.updateById(request,id));
    }

    @PutMapping("/image-id/{imageId:.+}")
    public ResponseEntity<SliderResponse> updateByImageId(@Valid @RequestBody SliderRequest request, @PathVariable String imageId){
        log.info("(Slider Controller) Updating slider by image ID: {} with request: {}", imageId, request);
        return ResponseEntity.ok(this.service.updateByImageId(request,imageId));
    }

    @DeleteMapping("image-name/{imageId:.+}")
    public ResponseEntity<MessageResponse> deleteByImageId(@PathVariable String imageId){
        log.info("(Slider Controller) Deleting slider by image ID: {}", imageId);
        return ResponseEntity.ok(this.service.deleteByImageId(imageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable String id){
        log.info("(Slider Controller) Deleting slider by ID: {}", id);
        return ResponseEntity.ok(this.service.deleteById(id));
    }

}
