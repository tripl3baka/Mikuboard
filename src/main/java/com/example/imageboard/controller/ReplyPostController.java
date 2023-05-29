package com.example.imageboard.controller;

import com.example.imageboard.model.Reply;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.imageboard.model.Thread;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ReplyPostController {

    private final ReplyRepository replyRepository;
    private final ThreadRepository threadRepository;

    public ReplyPostController(ReplyRepository replyRepository, ThreadRepository threadRepository) {
        this.replyRepository = replyRepository;
        this.threadRepository = threadRepository;
    }

    record ReplyData(String description, String name, String imgURL){
    }

    @PostMapping("/submit/{id}")
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
        reply.setImgURL(replyData.imgURL);
        reply.setThread(thread.get());
        replyRepository.saveAndFlush(reply);

        return"redirect:/thread/" + id;
    }
}


