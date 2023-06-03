package com.example.imageboard.controller;

import com.example.imageboard.model.Reply;
import com.example.imageboard.model.Thread;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import com.example.imageboard.service.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;


@Controller
public class ThreadPostController {

    private final ThreadRepository threadRepository;
    private final ReplyRepository replyRepository;
    private final FileStorageService fileStorageService;

    public ThreadPostController(ThreadRepository threadRepository, ReplyRepository replyRepository, FileStorageService fileStorageService) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
        this.fileStorageService = fileStorageService;
    }

    record ReplyData(String description, String name, String title, MultipartFile imgFile) {
    }

    @PostMapping(value = "/m/posted")
    private String addThread(ReplyData replyData) {
        ZonedDateTime date = ZonedDateTime.now();
        Thread thread = new Thread();
        thread.setTitle(replyData.title);
        thread.setBumpedAt(date);
        threadRepository.saveAndFlush(thread);

        Reply reply = new Reply();
        reply.setDescription(replyData.description);
        if (replyData.name == null || replyData.name.equals("")) {
            reply.setName("Anonymous");
        } else {
            reply.setName(replyData.name);
        }

        if(!replyData.imgFile.isEmpty()) {
            reply.setImgURL(
                    fileStorageService.getFileURL(
                            fileStorageService.storeFile(replyData.imgFile)
                    )
            );
        }
        reply.setDate(date);
        reply.setThread(thread);
        replyRepository.saveAndFlush(reply);

        return "redirect:/m/thread/" + thread.getId();
    }

}
