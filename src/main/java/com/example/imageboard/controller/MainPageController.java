package com.example.imageboard.controller;
import com.example.imageboard.repository.ThreadRepository;
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
    public MainPageController(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @GetMapping("/hello")
    public String mainPage (Model model){
        model.addAttribute("threads", threadRepository.findAll());
        return "index";
    }

    @GetMapping("/newthread")
    public String newThread(Model model){
        model.addAttribute("thread", new Thread());
        return "newThread";
    }

    @GetMapping("/thread/{id}")
    public String threadPage(@PathVariable("id") int id, Model model){

        Optional<Thread> thread = threadRepository.findById(id);
        if(thread.isEmpty()) {
        throw new ResponseStatusException(NOT_FOUND,"Thread not found");
        }
        model.addAttribute("thread", thread.get());

        return "threadPage";
    }

}
