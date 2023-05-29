package com.example.imageboard.controller;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.imageboard.model.Thread;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
public class MainPageController {
    private final ThreadRepository threadRepository;
    private final ReplyRepository replyRepository;

    public MainPageController(ThreadRepository threadRepository, ReplyRepository replyRepository) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
    }

    @GetMapping("/m")
    public String redirect(){
        return "redirect:/m/0";
    }

    @GetMapping("/m/{id}")
    public String mainPage (@PathVariable("id") int id, Model model){

        Pageable page = PageRequest.of(id,10, Sort.by("id").descending());

        model.addAttribute("threads", threadRepository.findAll(page));
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


}
