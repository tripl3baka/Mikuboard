package com.example.imageboard.controller;

import com.example.imageboard.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveController {

    @Autowired
    ThreadRepository threadRepository;

    @GetMapping("/m/archive")
    public String archivePageDisplay(Model model){
        model.addAttribute("threadsArchived", threadRepository.findThreadsArchived());
        return "archive";
    }
}
