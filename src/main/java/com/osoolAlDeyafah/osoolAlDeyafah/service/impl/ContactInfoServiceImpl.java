package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.NotFoundException;
import com.osoolAlDeyafah.osoolAlDeyafah.mapper.ContactInfoMapper;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.ContactInformation;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.ContactInfoRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SendEmailRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.ContactInfoResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.ContactInfoRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.service.ContactInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    @Autowired
    private ContactInfoRepository repository;
    @Autowired
    private ContactInfoMapper mapper;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ContactInfoResponse create(ContactInfoRequest request) {
        log.info("(ContactInfo Service) Creating contact with request: {}", request);
        ContactInfoResponse response = this.mapper.contactInfoToContactInfoResponse(this.repository.save(this.mapper.contactInfoRequestToContactInfo(request)));
        log.info("Contact created successfully: {}", response);
        return response;
    }

    @Override
    public List<ContactInfoResponse> getAll() {
        log.info("(ContactInfo Service) Fetching all contacts");
        List<ContactInfoResponse> responses = this.repository.findAll(WebContentStatus.DELETED).stream()
                .map(x -> this.mapper.contactInfoToContactInfoResponse(x))
                .toList();
        log.info("Fetched {} contacts successfully", responses.size());
        return responses;
    }

    @Override
    public ContactInfoResponse getById(String id) {
        log.info("(ContactInfo Service) Fetching contact by ID: {}", id);
        ContactInfoResponse response = this.mapper.contactInfoToContactInfoResponse(findById(id));
        log.info("Fetched contact by ID successfully: {}", response);
        return response;
    }

    @Override
    public ContactInfoResponse updateByID(ContactInfoRequest updateRequest, String id) {
        log.info("(ContactInfo Service) Updating contact by ID: {} with request: {}", id, updateRequest);
        ContactInformation contactInformation = findById(id);
        contactInformation.setAddress(updateRequest.getAddress());
        contactInformation.setEmail(updateRequest.getEmail());
        contactInformation.setPhone(updateRequest.getPhone());
        contactInformation.setWorkingHours(this.mapper.workingHoursRequestToWorkingHours(updateRequest.getWorkingHours()));
        contactInformation.setUpdatedAt(LocalDateTime.now());
        ContactInfoResponse response = this.mapper.contactInfoToContactInfoResponse(this.repository.update(contactInformation));
        log.info("Contact updated successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteById(String id) {
        log.info("(ContactInfo Service) Deleting contact by ID: {}", id);
        ContactInformation contactInformation = findById(id);
        contactInformation.setStatus(WebContentStatus.DELETED);
        contactInformation.setDeletedAt(LocalDateTime.now());
        this.repository.deleteById(contactInformation);
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Contact Is Deleted Successfully", id));
        log.info("Contact deleted successfully: {}", response);
        return response;
    }

    public ContactInformation findById(String id){
        if (this.repository.findById(id,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findById(id,WebContentStatus.DELETED).get();
        }
        log.error("(ContactInfo Service) The [{}] Contact Not Found",id);
        throw new NotFoundException(String.format("The [%s] Contact Not Found",id));
    }

    @Override
    public MessageResponse sendSimpleEmail(SendEmailRequest emailRequest) {
        log.info("(ContactInfo Service) Sending simple email with request: {}", emailRequest);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("m.frdoose@xprism.net");
        message.setSubject("Feedback from "+ emailRequest.getFromEmail());
        message.setText(String.format("Hello, You have a new feedback from: \n\n"
                + "Name: %s\n"
                + "Email: %s\n"
                + "Phone: %s\n\n"
                + "Message:\n%s", emailRequest.getName(),
                emailRequest.getFromEmail(), emailRequest.getPhone(), emailRequest.getBody()));
        javaMailSender.send(message);
        MessageResponse response = this.mapper.toMessageResponse("Send Successfully");
        log.info("Email sent successfully: {}", response);
        return response;
    }

}
