package com.example.imageboard.controller;

import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostDeleteController {

    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    ReplyRepository replyRepository;
    @PostMapping("/m/delete/thread/{id}")
    private ResponseEntity<String> deleteThreadAction(@PathVariable("id") int id){
        threadRepository.deleteById(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/m/delete/reply/{id}")
    private ResponseEntity<String> deleteReplyAction(@PathVariable("id") int id){
        replyRepository.deleteById(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
