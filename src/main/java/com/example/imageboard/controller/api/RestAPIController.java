//package com.example.imageboard.controller.api;
//import com.example.imageboard.model.Thread;
//import com.example.imageboard.repository.ThreadRepository;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RestAPIController {
//    private final ThreadRepository threadRepository;
//
//    public RestAPIController(ThreadRepository threadRepository) {
//        this.threadRepository = threadRepository;
//    }
//
//    record ThreadData(String title, String description, String imgURL){
//    }
//
//
//    //TODO
//    @PostMapping("/post")
//    private void addThread(@RequestBody ThreadData threadData){
//        Thread thread = new Thread();
//        thread.setTitle(threadData.title);
//        threadRepository.save(thread);
//    }
//
//}