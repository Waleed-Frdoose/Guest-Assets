package com.osoolAlDeyafah.osoolAlDeyafah.controller;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.GalleryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.GalleryService;
import com.osoolAlDeyafah.osoolAlDeyafah.service.OurStoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/galleries")
public class GalleryController {
    @Autowired
    private GalleryService service;

    @PostMapping
    public ResponseEntity<GalleryResponse> create(@Valid @RequestBody GalleryRequest request){
        log.info("(Gallery Controller) Creating gallery with request: {}", request);
        return ResponseEntity.ok(this.service.create(request));
    }

    @GetMapping("/image")
    public ResponseEntity<List<GalleryResponse>> getAllImage(){
        log.info("(Gallery Controller) Fetching all image galleries");
        return ResponseEntity.ok(this.service.getAllImage());
    }

    @GetMapping("/video")
    public ResponseEntity<List<GalleryResponse>> getAllVideo(){
        log.info("(Gallery Controller) Fetching all video galleries");
        return ResponseEntity.ok(this.service.getAllVideo());
    }

    @PostMapping("/image/paginated")
    public ResponseEntity<Page<GalleryProjection>> getAllImagePaginated(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size
    ){
        log.info("(Gallery Controller) Fetching paginated image galleries. Page: {}, Size: {}", page, size);
        return ResponseEntity.ok(this.service.getAllImage(page,size));
    }

    @PostMapping("/video/paginated")
    public ResponseEntity<Page<GalleryProjection>> getAllVideoPaginated(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size
    ){
        log.info("(Gallery Controller) Fetching paginated video galleries. Page: {}, Size: {}", page, size);
        return ResponseEntity.ok(this.service.getAllVideo(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalleryResponse> getById(@PathVariable String id){
        log.info("(Gallery Controller) Fetching gallery by ID: {}", id);
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GalleryResponse> updateById(@Valid @RequestBody GalleryRequest request, @PathVariable String id){
        log.info("(Gallery Controller) Updating gallery by ID: {} with request: {}", id, request);
        return ResponseEntity.ok(this.service.updateByID(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable String id){
        log.info("(Gallery Controller) Deleting gallery by ID: {}", id);
        return ResponseEntity.ok(this.service.deleteById(id));
    }

}
