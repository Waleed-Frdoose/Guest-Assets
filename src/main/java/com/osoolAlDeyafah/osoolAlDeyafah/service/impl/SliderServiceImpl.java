package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.ConflictException;
import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.NotFoundException;
import com.osoolAlDeyafah.osoolAlDeyafah.mapper.SliderMapper;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Slider;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.SliderRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.SliderResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.SliderRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import com.osoolAlDeyafah.osoolAlDeyafah.service.SliderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SliderServiceImpl implements SliderService {

    @Autowired
    private SliderRepository repository;
    @Autowired
    private SliderMapper mapper;
    @Autowired
    private MediaService mediaService;

    @Override
    public SliderResponse create(SliderRequest request) {
        log.info("(Slider Service) Creating slider with request: {}", request);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to slider", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to slider",request.getImageId()));
        }
        if (this.repository.findByImageId(request.getImageId(), WebContentStatus.DELETED).isPresent()){
            log.error("Conflict: The ImageId {} is already in use.", request.getImageId());
            throw new ConflictException("The ImageId is Already Exist.");
        }
        Slider slider = this.mapper.sliderRequestToSlider(request);
        SliderResponse response = this.mapper.sliderToSliderResponse(this.repository.save(slider));

        log.info("Slider created successfully: {}", response);
        return response;
    }

    @Override
    public List<SliderResponse> getAll() {
        log.info("(Slider Service) Fetching all sliders");
        List<SliderResponse> responses = this.repository.findAll(WebContentStatus.DELETED)
                .stream()
                .map(x -> this.mapper.sliderToSliderResponse(x))
                .toList();
        log.info("Fetched {} sliders successfully", responses.size());
        return responses;
    }

    @Override
    public SliderResponse updateById(SliderRequest request, String id) {
        log.info("(Slider Service) Updating slider by ID: {} with request: {}", id, request);
        Slider slider = findById(id);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to slider", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to slider",request.getImageId()));
        }
        slider.setImageId(request.getImageId());
//        slider.setDescription(request.getDescription());
        slider.setUpdatedAt(LocalDateTime.now());
        SliderResponse response = this.mapper.sliderToSliderResponse(this.repository.save(slider));

        log.info("Slider updated successfully: {}", response);
        return response;
    }

    @Override
    public SliderResponse updateByImageId(SliderRequest request, String imageId) {
        log.info("(Slider Service) Updating slider by image ID: {} with request: {}", imageId, request);
        Slider slider = findByImageId(imageId);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to slider", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to slider",request.getImageId()));
        }
        slider.setImageId(request.getImageId());
//        slider.setDescription(request.getDescription());
        slider.setUpdatedAt(LocalDateTime.now());
        SliderResponse response = this.mapper.sliderToSliderResponse(this.repository.save(slider));

        log.info("Slider updated successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteByImageId(String imageId) {
        log.info("(Slider Service) Deleting slider by image ID: {}", imageId);
        Slider slider = findByImageId(imageId);
        slider.setStatus(WebContentStatus.DELETED);
        slider.setDeletedAt(LocalDateTime.now());
        this.repository.deleteByImageId(slider);
        this.mediaService.deleteImageByName(slider.getImageId());
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Image deleted from slider and server successfully", imageId));

        log.info("Slider deleted successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteById(String id) {
        log.info("(Slider Service) Deleting slider by ID: {}", id);
        Slider slider = findById(id);
        slider.setStatus(WebContentStatus.DELETED);
        slider.setDeletedAt(LocalDateTime.now());
        this.repository.deleteById(slider);
        this.mediaService.deleteImageByName(slider.getImageId());
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Image deleted from slider and server successfully", id));

        log.info("Slider deleted successfully: {}", response);
        return response;
    }

    public Slider findByImageId(String imageId){
        if (this.repository.findByImageId(imageId, WebContentStatus.DELETED).isPresent()) {
            return this.repository.findByImageId(imageId,WebContentStatus.DELETED).get();
        }
        log.error("(Slider Service) The [{}] Image Not Found",imageId);
        throw new NotFoundException(String.format("The [%s] Image Not Found",imageId));
    }

    public Slider findById(String id){
        if (this.repository.findById(id,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findById(id,WebContentStatus.DELETED).get();
        }
        log.error("(Slider Service) The [{}] Image Not Found",id);
        throw new NotFoundException(String.format("The [%s] Image Not Found",id));
    }

}
