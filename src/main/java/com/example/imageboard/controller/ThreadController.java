package com.example.imageboard.controller;

import com.example.imageboard.model.Reply;
import com.example.imageboard.model.Thread;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import com.example.imageboard.service.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
public class ThreadController {

    private final ThreadRepository threadRepository;
    private final ReplyRepository replyRepository;
    private final FileStorageService fileStorageService;

    public ThreadController(ThreadRepository threadRepository, ReplyRepository replyRepository, FileStorageService fileStorageService) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/m/newthread")
    public String createAction(){
        return "newThread";
    }

    @GetMapping("/m/thread/{id}")
    public String threadPage(@PathVariable("id") int id, Model model){

        Optional<Thread> thread = threadRepository.findById(id);
        if(thread.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND,"Thread not found");
        }
        model.addAttribute("thread", thread.get());
        model.addAttribute("pageNumber",
                ((threadRepository.CountThreadsWithIdHigherThan(
                        thread.get().getId()))/10)+1);
        return "threadPage";
    }

    record ReplyData(String description, String name, String title, MultipartFile imgFile) {
    }

    @PostMapping(value = "/m/posted")
    private String storeAction(ReplyData replyData) {
        ZonedDateTime date = ZonedDateTime.now();
        Thread thread = new Thread();
        thread.setTitle(replyData.title);
        thread.setBumpedAt(date);
        thread.setArchived(false);
        threadRepository.saveAndFlush(thread);

            Optional<Thread> threadToArchive = threadRepository.findById(thread.getId()-101);
            if(threadToArchive.isPresent()) {
                threadToArchive.get().setArchived(true);
                threadRepository.saveAndFlush(threadToArchive.get());
            }

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
