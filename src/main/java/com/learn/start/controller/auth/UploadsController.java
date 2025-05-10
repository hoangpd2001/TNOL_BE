package com.learn.start.controller.auth;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/auth/uploads")
public class UploadsController {

    @GetMapping("/giaovien/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
        Path imagePath = Paths.get("uploads/giaovien").resolve(filename);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(imagePath.toUri());
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    @GetMapping("/cauhoiques/{filename:.+}")
    public ResponseEntity<Resource> getImageCauHoiQue(@PathVariable String filename) throws MalformedURLException {
        Path imagePath = Paths.get("uploads/question/question").resolve(filename);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(imagePath.toUri());
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    @GetMapping("/cauhoians/{filename:.+}")
    public ResponseEntity<Resource> getImageCauHoiAns(@PathVariable String filename) throws MalformedURLException {
        Path imagePath = Paths.get("uploads/question/answer").resolve(filename);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(imagePath.toUri());
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
