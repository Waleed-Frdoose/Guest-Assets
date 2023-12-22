package com.osoolAlDeyafah.osoolAlDeyafah.config;

import com.osoolAlDeyafah.osoolAlDeyafah.exceptionHandel.UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class DirectoryConfig {

    @Bean
    public Path uploadImageDirectory() {
        log.info("Create Directory For uploads/images");
        Path directoryPath = Paths.get("uploads/images");
        if (Files.notExists(directoryPath)) {
            log.info("uploads/images File not exist");
            try {
                log.info("Try to create uploads/images File");
                Files.createDirectories(directoryPath);
                log.info("Create uploads/images File Successfully");
            } catch (Exception e) {
                log.error("Failed to create the upload/Images directory");
                throw new UploadException("Failed to create the upload/Images directory.");
            }
        }
        return directoryPath;
    }

    @Bean
    public Path uploadVideoDirectory() {
        log.info("Create Directory For uploads/videos");
        Path directoryPath = Paths.get("uploads/videos");
        if (Files.notExists(directoryPath)) {
            log.info("uploads/videos File not exist");
            try {
                log.info("Try to create uploads/videos File");
                Files.createDirectories(directoryPath);
                log.info("Create uploads/videos File Successfully");
            } catch (Exception e) {
                log.error("Failed to create the upload/videos directory");
                throw new UploadException("Failed to create the upload/videos directory.");
            }
        }
        return directoryPath;
    }
}
