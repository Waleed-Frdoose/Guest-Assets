package com.osoolAlDeyafah.osoolAlDeyafah.controller;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.OurStoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/ourStories")
public class OurStoryController {

    @Autowired
    private OurStoryService service;

    @PostMapping
    public ResponseEntity<OurStoryResponse> create(@Valid @RequestBody OurStoryRequest request){
        log.info("(OurStory Controller) Creating our story with request: {}", request);
        return ResponseEntity.ok(this.service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<OurStoryResponse>> getAll(){
        log.info("(OurStory Controller) Fetching all our stories");
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OurStoryResponse> getById(@PathVariable String id){
        log.info("(OurStory Controller) Fetching our story by ID: {}", id);
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OurStoryResponse> updateById(@Valid @RequestBody OurStoryRequest request, @PathVariable String id){
        log.info("(OurStory Controller) Updating our story by ID: {} with request: {}", id, request);
        return ResponseEntity.ok(this.service.updateByID(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable String id){
        log.info("(OurStory Controller) Deleting our story by ID: {}", id);
        return ResponseEntity.ok(this.service.deleteById(id));
    }

}
