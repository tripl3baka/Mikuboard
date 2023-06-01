package com.example.imageboard.controller;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import com.example.imageboard.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.imageboard.model.Thread;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
public class MainPageController {
    private final ThreadRepository threadRepository;
    private final ReplyRepository replyRepository;
    private final FileStorageService fileStorageService;

    public MainPageController(ThreadRepository threadRepository, ReplyRepository replyRepository, FileStorageService fileStorageService) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/m")
    public String redirect(Model model){
        return mainPage(1, model);
    }

    @GetMapping("/m/{id}")
    public String mainPage (@PathVariable("id") int id, Model model){
        Pageable page = PageRequest.of(id-1,10, Sort.by("bumpedAt").descending());
        model.addAttribute("threads", threadRepository.findAll(page));
        model.addAttribute("threadCount", threadRepository.count());
        return "index";
    }

    @GetMapping("/m/newthread")
    public String newThread(){
        return "newThread";
    }

    @GetMapping("/m/thread/{id}")
    public String threadPage(@PathVariable("id") int id, Model model){

        Optional<Thread> thread = threadRepository.findById(id);
        if(thread.isEmpty()) {
        throw new ResponseStatusException(NOT_FOUND,"Thread not found");
        }
        model.addAttribute("thread", thread.get());

        return "threadPage";
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = fileStorageService.openFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
