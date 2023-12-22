package com.osoolAlDeyafah.osoolAlDeyafah.controller;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.ContactInfoRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SendEmailRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.ContactInfoResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.ContactInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/contacts")
public class ContactInfoController {

    @Autowired
    private ContactInfoService service;

    @PostMapping
    public ResponseEntity<ContactInfoResponse> create(@Valid @RequestBody ContactInfoRequest request){
        log.info("(ContactInfo Controller) Creating contact with request: {}", request);
        return ResponseEntity.ok(this.service.create(request));
    }

    @PostMapping("/send-email")
    public ResponseEntity<MessageResponse> sendEmail(@Valid @RequestBody SendEmailRequest request){
        log.info("(ContactInfo Controller) Sending email with request: {}", request);
        return ResponseEntity.ok(this.service.sendSimpleEmail(request));
    }

    @GetMapping
    public ResponseEntity<List<ContactInfoResponse>> getAll(){
        log.info("(ContactInfo Controller) Fetching all contacts");
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfoResponse> getById(@PathVariable String id){
        log.info("(ContactInfo Controller) Fetching contact by ID: {}", id);
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfoResponse> updateById(@Valid @RequestBody ContactInfoRequest request, @PathVariable String id){
        log.info("(ContactInfo Controller) Updating contact by ID: {} with request: {}", id, request);
        return ResponseEntity.ok(this.service.updateByID(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable String id){
        log.info("(ContactInfo Controller) Deleting contact by ID: {}", id);
        return ResponseEntity.ok(this.service.deleteById(id));
    }

}
