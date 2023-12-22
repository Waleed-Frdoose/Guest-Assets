package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.NotFoundException;
import com.osoolAlDeyafah.osoolAlDeyafah.mapper.GalleryMapper;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.GalleryRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryProjection;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.GalleryResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Gallery;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.MediaType;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.GalleryRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.service.GalleryService;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryRepository repository;
    @Autowired
    private GalleryMapper mapper;
    @Autowired
    private MediaService mediaService;

    @Override
    public GalleryResponse create(GalleryRequest request) {
        log.info("(Gallery Service) Creating gallery with request: {}", request);
        if(MediaType.valueOf(request.getMediaType()) == MediaType.IMAGE){
            if (!this.mediaService.findImageByName(request.getMediaId())){
                log.warn("The [{}] Image not found in the server, so cannot add to Gallery", request.getMediaId());
                throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to Gallery",request.getMediaId()));
            }
        }else {
            if (!this.mediaService.findVideoByName(request.getMediaId())){
                log.warn("The [{}] Video not found in the server, so cannot add to Gallery", request.getMediaId());
                throw new NotFoundException(String.format("The [%s] Video not found in server so can not add to Gallery",request.getMediaId()));
            }
        }
        GalleryResponse response = this.mapper.galleryToGalleryResponse(this.repository.save(this.mapper.galleryRequestToGallery(request)));
        log.info("Gallery created successfully: {}", response);
        return response;
    }

    @Override
    public List<GalleryResponse> getAllImage() {
        log.info("(Gallery Service) Fetching all image galleries");
        List<GalleryResponse> responses = this.repository.findByMediaType(MediaType.IMAGE, WebContentStatus.DELETED)
                .stream()
                .map(x -> this.mapper.galleryToGalleryResponse(x))
                .toList();
        log.info("Fetched {} image galleries successfully", responses.size());
        return responses;
    }

    @Override
    public Page<GalleryProjection> getAllImage(int page, int size) {
        log.info("(Gallery Service) Fetching paginated image galleries. Page: {}, Size: {}", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.repository.findByMediaType(MediaType.IMAGE, WebContentStatus.DELETED,pageRequest);
    }

    @Override
    public List<GalleryResponse> getAllVideo() {
        log.info("(Gallery Service) Fetching all video galleries");
        List<GalleryResponse> responses = this.repository.findByMediaType(MediaType.VIDEO, WebContentStatus.DELETED)
                .stream()
                .map(x -> this.mapper.galleryToGalleryResponse(x))
                .toList();
        log.info("Fetched {} video galleries successfully", responses.size());
        return responses;
    }

    @Override
    public Page<GalleryProjection> getAllVideo(int page, int size) {
        log.info("(Gallery Service) Fetching paginated video galleries. Page: {}, Size: {}", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.repository.findByMediaType(MediaType.VIDEO, WebContentStatus.DELETED,pageRequest);
    }

    @Override
    public GalleryResponse getById(String id) {
        log.info("(Gallery Service) Fetching gallery by ID: {}", id);
        return this.mapper.galleryToGalleryResponse(findById(id));
    }

    @Override
    public GalleryResponse updateByID(GalleryRequest request, String id) {
        log.info("(Gallery Service) Updating gallery by ID: {} with request: {}", id, request);
        Gallery gallery = findById(id);
        if(MediaType.valueOf(request.getMediaType()) == MediaType.IMAGE){
            if (!this.mediaService.findImageByName(request.getMediaId())){
                log.warn("The [{}] Image not found in the server, so cannot add to Gallery", request.getMediaId());
                throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to Gallery",request.getMediaId()));
            }
        }else {
            if (!this.mediaService.findVideoByName(request.getMediaId())){
                log.warn("The [{}] Video not found in the server, so cannot add to Gallery", request.getMediaId());
                throw new NotFoundException(String.format("The [%s] Video not found in server so can not add to Gallery",request.getMediaId()));
            }
        }
        gallery.setTitle(request.getTitle());
        gallery.setDescription(request.getDescription());
        gallery.setMediaId(request.getMediaId());
        gallery.setUpdatedAt(LocalDateTime.now());
        GalleryResponse response = this.mapper.galleryToGalleryResponse(this.repository.save(gallery));
        log.info("Gallery updated successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteById(String id) {
        log.info("(Gallery Service) Deleting gallery by ID: {}", id);
        Gallery gallery = findById(id);
        gallery.setStatus(WebContentStatus.DELETED);
        gallery.setDeletedAt(LocalDateTime.now());
        this.repository.save(gallery);
        if(gallery.getMediaType() == MediaType.IMAGE){
            this.mediaService.deleteImageByName(gallery.getMediaId());
        }else {
            this.mediaService.deleteVideoByName(gallery.getMediaId());
        }
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Gallery Is Deleted Successfully", id));
        log.info("Gallery deleted successfully: {}", response);
        return response;
    }

    public Gallery findById(String id){
        if (this.repository.findById(id,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findById(id,WebContentStatus.DELETED).get();
        }
        log.error("(Gallery Service) The [{}] Gallery Not Found",id);
        throw new NotFoundException(String.format("The [%s] Gallery Not Found",id));
    }

}
