package com.osoolAlDeyafah.osoolAlDeyafah.service.impl;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.*;
import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private Path uploadImageDirectory;
    @Autowired
    private Path uploadVideoDirectory;

    @Value("${spring.servlet.multipart.max-file-size}")
    private long maxFileSize;

    @Override
    public MessageResponse uploadImage(MultipartFile file)  {
        log.info("(Media Service) Received request to upload image: {}", file.getOriginalFilename());
        if (!file.isEmpty()) {
            if (file.getSize() > maxFileSize) {
                log.error("File size exceeds the allowed limit: {}", file.getSize());
                throw new SizeLimitExceededException("File size exceeds the allowed limit.");
            }
            String contentType = file.getContentType();
            if (contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/webp"))) {
                String originalFilename = UUID.randomUUID() + "_" +  file.getOriginalFilename();
                Path filePath = Paths.get(uploadImageDirectory.toString(), originalFilename);
                try {
                    Files.write(filePath, file.getBytes());
                } catch (IOException e) {
                    log.error("Failed to write the file: {}", e.getMessage(), e);
                    throw new FileWriteException("Failed to write the file: " + e.getMessage());
                }
                log.info("The image is uploaded successfully: {}", file.getOriginalFilename());
                return new MessageResponse(originalFilename);
            }
            log.error("Unsupported file type: {}", contentType);
            throw new UnsupportedMediaTypeException("Unsupported file type");
        }
        log.error("The file is empty");
        throw new FileEmptyException("The file is empty");
    }

    @Override
    public MessageResponse uploadVideo(MultipartFile file)  {
        log.info("(Media Service) Received request to upload video: {}", file.getOriginalFilename());
        if (!file.isEmpty()) {
            if (file.getSize() > maxFileSize) {
                log.error("File size exceeds the allowed limit: {}", file.getSize());
                throw new SizeLimitExceededException("File size exceeds the allowed limit.");
            }
            String contentType = file.getContentType();
            if (contentType != null && (contentType.startsWith("video/"))) {
                String originalFilename = UUID.randomUUID() + "_" +  file.getOriginalFilename();
                Path filePath = Paths.get(uploadVideoDirectory.toString(), originalFilename);
                try {
                    Files.write(filePath, file.getBytes());
                } catch (IOException e) {
                    log.error("Failed to write the file: {}", e.getMessage(), e);
                    throw new FileWriteException("Failed to write the file: " + e.getMessage());
                }
                log.info("The video is uploaded successfully: {}", file.getOriginalFilename());
                return new MessageResponse(originalFilename);
            }
            log.error("Unsupported file type: {}", contentType);
            throw new UnsupportedMediaTypeException("Unsupported file type");
        }
        log.error("The file is empty");
        throw new FileEmptyException("The file is empty");
    }

//    @Override
//    public Resource download(String name) {
//        Resource fileResource = new FileSystemResource(uploadPhotoDirectory.resolve(name));
//
//        if (fileResource.exists() && fileResource.isReadable()) {
//            return fileResource;
//        } else {
//            throw new NotFoundException("File not found");
//        }
//    }

//    @Override
//    public byte[] getImageByName(String name) {
//        log.info("Received request to get image by name (Service): {}", name);
//        Resource fileResource = new FileSystemResource(uploadImageDirectory.resolve(name));
//        if (fileResource.exists() && fileResource.isReadable()) {
//            try {
//                return Files.readAllBytes(new File(fileResource.getURI()).toPath());
//            }catch (IOException e){
//                log.error("Failed to read the file: {}", e.getMessage(), e);
//                throw new FileReadException("Failed to read the file: " + e.getMessage());
//            }
//        } else {
//            log.error("The file is not found: {}", name);
//            throw new NotFoundException("File not found");
//        }
//    }
//    @Override
//    public byte[] getVideoByName(String name) {
//        log.info("Received request to get video by name (Service): {}", name);
//        Resource fileResource = new FileSystemResource(uploadVideoDirectory.resolve(name));
//        if (fileResource.exists() && fileResource.isReadable()) {
//            try {
//                return Files.readAllBytes(new File(fileResource.getURI()).toPath());
//            }catch (IOException e){
//                log.error("Failed to read the file: {}", e.getMessage(), e);
//                throw new FileReadException("Failed to read the file: " + e.getMessage());
//            }
//        } else {
//            log.error("The file is not found: {}", name);
//            throw new NotFoundException("File not found");
//        }
//    }

    @Override
    public MessageResponse deleteImageByName(String name) {
        log.info("(Media Service) Received request to delete image by name: {}", name);
        Resource fileResource = new FileSystemResource(uploadImageDirectory.resolve(name));
        if (fileResource.exists() && fileResource.isReadable()) {
            try {
                Files.delete(new File(fileResource.getURI()).toPath());
                log.info("The Image Is Deleted From Server Successfully: {}", name);
                return new MessageResponse("The Image Is Deleted From Server Successfully");
            }catch (IOException e){
                log.error("Failed to delete the file: {}", e.getMessage(), e);
                throw new FileReadException("Failed to delete the file: " + e.getMessage());
            }
        } else {
            log.error("The file is not found: {}", name);
            throw new NotFoundException("File not found");
        }
    }

    @Override
    public MessageResponse deleteVideoByName(String name) {
        log.info("(Media Service) Received request to delete video by name: {}", name);
        Resource fileResource = new FileSystemResource(uploadVideoDirectory.resolve(name));
        if (fileResource.exists() && fileResource.isReadable()) {
            try {
                Files.delete(new File(fileResource.getURI()).toPath());
                log.info("The Video Is Deleted From Server Successfully: {}", name);
                return new MessageResponse("The Video Is Deleted From Server Successfully");
            }catch (IOException e){
                log.error("Failed to delete the file: {}", e.getMessage(), e);
                throw new FileReadException("Failed to delete the file: " + e.getMessage());
            }
        } else {
            log.error("The file is not found: {}", name);
            throw new NotFoundException("File not found");
        }
    }

    @Override
    public boolean findImageByName(String name){
        log.info("(Media Service) Received request to find image by name: {}", name);
        Resource fileResource = new FileSystemResource(uploadImageDirectory.resolve(name));
        return fileResource.exists() && fileResource.isReadable();
    }

    @Override
    public boolean findVideoByName(String name){
        log.info("(Media Service) Received request to find video by name: {}", name);
        Resource fileResource = new FileSystemResource(uploadVideoDirectory.resolve(name));
        return fileResource.exists() && fileResource.isReadable();
    }

}
