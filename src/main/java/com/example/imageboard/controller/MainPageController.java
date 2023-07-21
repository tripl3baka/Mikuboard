package com.example.imageboard.controller;
import com.example.imageboard.repository.LoginRepository;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import com.example.imageboard.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.imageboard.model.Thread;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class MainPageController {
    private final ThreadRepository threadRepository;

    public MainPageController(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @GetMapping("/m")
    public String redirect(Model model){
        return mainPage(1, model);
    }

    @GetMapping("/m/page/{id}")
    public String mainPage (@PathVariable("id") int id, Model model){
        Pageable page = PageRequest.of(id-1,10, Sort.by("bumpedAt").descending());
        model.addAttribute("threads", threadRepository.findThreadsNotArchived(page));
        model.addAttribute("threadCount", threadRepository.CountNotArchivedThreads());
        return "index";
    }

    @GetMapping("/m/catalog")
    public String catalog(Model model){
        model.addAttribute("threads", threadRepository.findThreadsNotArchived());
        return "catalog";
    }

}
