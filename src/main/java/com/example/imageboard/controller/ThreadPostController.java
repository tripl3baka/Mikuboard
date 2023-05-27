package com.example.imageboard.controller;
import com.example.imageboard.model.Thread;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThreadPostController {

    private final ThreadRepository threadRepository;
    public ThreadPostController(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    record ThreadData(String title, String description, String imgURL){
    }

    @RequestMapping(value = "/posted", method = RequestMethod.POST)
    private String addThread(ThreadData threadData){
        Thread thread = new Thread();
        thread.setTitle(threadData.title);
        thread.setDescription(threadData.description);
        threadRepository.save(thread);
        return "redirect:hello";
    }
}
