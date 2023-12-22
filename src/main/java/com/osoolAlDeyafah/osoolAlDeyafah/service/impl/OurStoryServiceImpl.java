package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.NotFoundException;
import com.osoolAlDeyafah.osoolAlDeyafah.mapper.OurStoryMapper;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.OurStory;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.OurStoryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.OurStoryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.OurStoryRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import com.osoolAlDeyafah.osoolAlDeyafah.service.OurStoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OurStoryServiceImpl implements OurStoryService {

    @Autowired
    private OurStoryRepository repository;
    @Autowired
    private OurStoryMapper mapper;
    @Autowired
    private MediaService mediaService;

    @Override
    public OurStoryResponse create(OurStoryRequest request) {
        log.info("(OurStory Service) Creating our story with request: {}", request);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to Story", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to Story",request.getImageId()));
        }
        OurStoryResponse response = this.mapper.ourStoryToOurStoryResponse(this.repository.save(this.mapper.ourStoryRequestToOurStory(request)));
        log.info("Our story created successfully: {}", response);
        return response;
    }

    @Override
    public List<OurStoryResponse> getAll() {
        log.info("(OurStory Service) Fetching all our stories");
        List<OurStoryResponse> responses = this.repository.findAll(WebContentStatus.DELETED)
                .stream()
                .map(x -> this.mapper.ourStoryToOurStoryResponse(x))
                .toList();
        log.info("Fetched {} our stories successfully", responses.size());
        return responses;
    }

    @Override
    public OurStoryResponse getById(String id) {
        log.info("(OurStory Service) Fetching our story by ID: {}", id);
        return this.mapper.ourStoryToOurStoryResponse(findById(id));
    }

    @Override
    public OurStoryResponse updateByID(OurStoryRequest request, String id) {
        log.info("(OurStory Service) Updating our story by ID: {} with request: {}", id, request);
        OurStory ourStory = findById(id);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to story", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to story",request.getImageId()));
        }
        ourStory.setTitle(request.getTitle());
        ourStory.setDescription(request.getDescription());
        ourStory.setImageId(request.getImageId());
        ourStory.setUpdatedAt(LocalDateTime.now());
        OurStoryResponse response = this.mapper.ourStoryToOurStoryResponse(this.repository.update(ourStory));
        log.info("Our story updated successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteById(String id) {
        log.info("(OurStory Service) Deleting our story by ID: {}", id);
        OurStory ourStory = findById(id);
        ourStory.setStatus(WebContentStatus.DELETED);
        ourStory.setDeletedAt(LocalDateTime.now());
        this.repository.deleteById(ourStory);
        this.mediaService.deleteImageByName(ourStory.getImageId());
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Story Is Deleted Successfully", id));
        log.info("Our story deleted successfully: {}", response);
        return response;
    }

    public OurStory findById(String id){
        if (this.repository.findById(id,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findById(id,WebContentStatus.DELETED).get();
        }
        log.error("(OurStory Service) The [{}] Story Not Found",id);
        throw new NotFoundException(String.format("The [%s] Story Not Found",id));
    }

}
