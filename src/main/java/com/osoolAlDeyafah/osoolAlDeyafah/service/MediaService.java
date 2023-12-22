package com.osoolAlDeyafah.osoolAlDeyafah.service;

import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    public MessageResponse uploadImage(MultipartFile file);
    public MessageResponse uploadVideo(MultipartFile file);
//    public byte[] getImageByName(String name);
//    public byte[] getVideoByName(String name);
//    public Resource download(String name);
    public MessageResponse deleteImageByName(String name);
    public MessageResponse deleteVideoByName(String name);
    public boolean findImageByName(String name);

    boolean findVideoByName(String name);
}
