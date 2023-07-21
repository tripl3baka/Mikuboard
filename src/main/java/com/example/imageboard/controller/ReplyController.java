package com.example.imageboard.controller;

import com.example.imageboard.model.Reply;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import com.example.imageboard.service.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.imageboard.model.Thread;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final ThreadRepository threadRepository;
    private final FileStorageService fileStorageService;

    public ReplyController(ReplyRepository replyRepository, ThreadRepository threadRepository, FileStorageService fileStorageService) {
        this.replyRepository = replyRepository;
        this.threadRepository = threadRepository;
        this.fileStorageService = fileStorageService;
    }

    record ReplyData(String description, String name, MultipartFile imgFile){
    }

    @PostMapping("/m/submit/{id}")
    private String addReply(@PathVariable("id") int id, ReplyData replyData, Model model){
        Optional<Thread> thread = threadRepository.findById(id);
        if(thread.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND,"Thread not found (Could be deleted)");
        }
        Reply reply = new Reply();

        model.addAttribute("thread", thread.get());
        if(replyData.name == null || replyData.name.equals("")) {
            reply.setName("Anonymous");
        }
        else{
            reply.setName(replyData.name);
        }
        reply.setDescription(replyData.description);
        if(!replyData.imgFile.isEmpty()) {
            reply.setImgURL(
                    fileStorageService.getFileURL(
                            fileStorageService.storeFile(replyData.imgFile)
                    )
            );
        }
        reply.setThread(thread.get());

        ZonedDateTime date = ZonedDateTime.now();
        reply.setDate(date);
        reply.getThread().setBumpedAt(date);
        threadRepository.saveAndFlush(reply.getThread());
        replyRepository.saveAndFlush(reply);

        return"redirect:/m/thread/" + id;
    }
}


