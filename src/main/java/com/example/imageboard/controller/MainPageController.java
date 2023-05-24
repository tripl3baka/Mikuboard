package com.example.imageboard.controller;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private final ThreadRepository threadRepository;
    public MainPageController(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @GetMapping("/hello")
    public String showPage (Model model){
        model.addAttribute("threads", threadRepository.findAll());
        return "index";
    }

}
