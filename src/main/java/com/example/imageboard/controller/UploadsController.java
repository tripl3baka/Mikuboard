package com.example.imageboard.controller;

import com.example.imageboard.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UploadsController {

    private final FileStorageService fileStorageService;

    public UploadsController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @GetMapping("/uploads/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = fileStorageService.openFile(filename);
        return ResponseEntity.ok().body(file);
    }

}
