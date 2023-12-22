package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.ConflictException;
import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.LimitSizeImageException;
import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.NotFoundException;
import com.osoolAlDeyafah.osoolAlDeyafah.mapper.MenuMapper;
import com.osoolAlDeyafah.osoolAlDeyafah.model.document.Menu;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.request.MenuRequest;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MenuResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.model.enums.WebContentStatus;
import com.osoolAlDeyafah.osoolAlDeyafah.repository.MenuRepository;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;
    @Autowired
    private MenuMapper mapper;
    @Autowired
    private MediaService mediaService;

    @Override
    public MenuResponse create(MenuRequest request) {
        log.info("(Menu Service) Creating menu with request: {}", request);
        if (getAll().size() >= 4){
            log.warn("Cannot add image because the number of images is 4, and only 4 images can be added.");
            throw new LimitSizeImageException("You can not add image because number of images 4 and can add 4 images just.");
        }
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to menu", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to menu",request.getImageId()));
        }
        if (this.repository.findByImageId(request.getImageId(), WebContentStatus.DELETED).isPresent()){
            log.warn("ImageId [{}] already exists, cannot add duplicate image.", request.getImageId());
            throw new ConflictException("The ImageId is Already Exist.");
        }
        Menu menu = this.mapper.menuRequestToMenu(request);
        MenuResponse response = this.mapper.menuToMenuResponse(this.repository.save(menu));
        log.info("Menu created successfully: {}", response);
        return response;
    }

    @Override
    public List<MenuResponse> getAll() {
        log.info("(Menu Service) Fetching all menus");
        List<MenuResponse> responses = this.repository.findAll(WebContentStatus.DELETED)
                .stream()
                .map(x -> this.mapper.menuToMenuResponse(x))
                .toList();
        log.info("Fetched {} menus successfully", responses.size());
        return responses;
    }

    @Override
    public MenuResponse updateById(MenuRequest request, String id) {
        log.info("(Menu Service) Updating menu by ID: {} with request: {}", id, request);
        Menu menu = findById(id);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to menu", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to slider",request.getImageId()));
        }
        menu.setTitle(request.getTitle());
        menu.setImageId(request.getImageId());
        menu.setUpdatedAt(LocalDateTime.now());
        MenuResponse response = this.mapper.menuToMenuResponse(this.repository.save(menu));
        log.info("Menu updated successfully: {}", response);
        return response;
    }

    @Override
    public MenuResponse updateByImageId(MenuRequest request, String imageId) {
        log.info("(Menu Service) Updating menu by image ID: {} with request: {}", imageId, request);
        Menu menu = findByImageId(imageId);
        if (!this.mediaService.findImageByName(request.getImageId())){
            log.warn("The [{}] Image not found in the server, so cannot add to menu", request.getImageId());
            throw new NotFoundException(String.format("The [%s] Image not found in server so can not add to slider",request.getImageId()));
        }
        menu.setTitle(request.getTitle());
        menu.setImageId(request.getImageId());
        menu.setUpdatedAt(LocalDateTime.now());
        MenuResponse response = this.mapper.menuToMenuResponse(this.repository.save(menu));
        log.info("Menu updated successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteByImageId(String imageId) {
        log.info("(Menu Service) Deleting menu by image ID: {}", imageId);
        Menu menu = findByImageId(imageId);
        menu.setStatus(WebContentStatus.DELETED);
        menu.setDeletedAt(LocalDateTime.now());
        this.repository.deleteByImageId(menu);
        this.mediaService.deleteImageByName(menu.getImageId());
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Image deleted from menu and server successfully", imageId));
        log.info("Menu deleted successfully: {}", response);
        return response;
    }

    @Override
    public MessageResponse deleteById(String id) {
        log.info("(Menu Service) Deleting menu by ID: {}", id);
        Menu menu = findById(id);
        menu.setStatus(WebContentStatus.DELETED);
        menu.setDeletedAt(LocalDateTime.now());
        this.repository.deleteById(menu);
        this.mediaService.deleteImageByName(menu.getImageId());
        MessageResponse response = this.mapper.toMessageResponse(String.format("The [%s] Image deleted from menu and server successfully", id));
        log.info("Menu deleted successfully: {}", response);
        return response;
    }

    public Menu findByImageId(String imageId){
        if (this.repository.findByImageId(imageId,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findByImageId(imageId,WebContentStatus.DELETED).get();
        }
        log.error("(Menu Service) The [{}] Image Not Found",imageId);
        throw new NotFoundException(String.format("The [%s] Image Not Found",imageId));
    }

    public Menu findById(String id){
        if (this.repository.findById(id,WebContentStatus.DELETED).isPresent()) {
            return this.repository.findById(id,WebContentStatus.DELETED).get();
        }
        log.error("(Menu Service) The [{}] Image Not Found",id);
        throw new NotFoundException(String.format("The [%s] Image Not Found",id));
    }

}
