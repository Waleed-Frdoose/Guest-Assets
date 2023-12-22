package com.osoolAlDeyafah.osoolAlDeyafah.controller;


import com.osoolAlDeyafah.osoolAlDeyafah.model.DTO.response.MessageResponse;
import com.osoolAlDeyafah.osoolAlDeyafah.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("api/v1/medias")
public class MediaController {

    @Autowired
    private MediaService service;

    @PostMapping("/upload-image")
    public ResponseEntity<MessageResponse> uploadImage(@RequestPart("file") MultipartFile file) {
        log.info("(Media Controller) Received request to upload image: {}", file.getOriginalFilename());
        return ResponseEntity.ok(this.service.uploadImage(file));
    }

    @PostMapping("/upload-video")
    public ResponseEntity<MessageResponse> uploadVideo(@RequestPart("file") MultipartFile file) {
        log.info("(Media Controller) Received request to upload video: {}", file.getOriginalFilename());
        return ResponseEntity.ok(this.service.uploadVideo(file));
    }

//    @GetMapping("/uploads/image/{filename:.+}")
//    public ResponseEntity<byte[]> getImageByName(@PathVariable String filename) {
//        log.info("Received request to get image by name (Controller): {}", filename);
//        byte[] imageData = this.service.getImageByName(filename);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//    }
//
//    @GetMapping("/uploads/video/{filename:.+}")
//    public ResponseEntity<byte[]> getVideoByName(@PathVariable String filename) {
//        log.info("Received request to get video by name (Controller): {}", filename);
//        byte[] imageData = this.service.getVideoByName(filename);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf("video/mp4"))
//                .body(imageData);
//    }

    @DeleteMapping("/image/{filename:.+}")
    public ResponseEntity<MessageResponse> deleteImageByName(@PathVariable String filename) {
        log.info("(Media Controller) Received request to delete image by name: {}", filename);
        return ResponseEntity.ok(this.service.deleteImageByName(filename));
    }

    @DeleteMapping("/video/{filename:.+}")
    public ResponseEntity<MessageResponse> deleteVideoByName(@PathVariable String filename) {
        log.info("(Media Controller) Received request to delete video by name: {}", filename);
        return ResponseEntity.ok(this.service.deleteVideoByName(filename));
    }

}
