package com.example.imageboard.controller;
import com.example.imageboard.model.Reply;
import com.example.imageboard.model.Thread;
import com.example.imageboard.repository.ReplyRepository;
import com.example.imageboard.repository.ThreadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ThreadPostController {

    private final ThreadRepository threadRepository;
    private final ReplyRepository replyRepository;
    public ThreadPostController(ThreadRepository threadRepository, ReplyRepository replyRepository) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
    }

    record ReplyData(String description, String name, String title, String imgURL){
    }

    @PostMapping(value = "/posted")
    private String addThread(ReplyData replyData){
        Thread thread = new Thread();
        thread.setTitle(replyData.title);
        Reply reply = new Reply();
        reply.setDescription(replyData.description);
        if(replyData.name == null || replyData.name.equals("")) {
            reply.setName("Anonymous");
        }
        else{
            reply.setName(replyData.name);
        }
        reply.setThread(thread);
        threadRepository.saveAndFlush(thread);
        replyRepository.saveAndFlush(reply);

        return "redirect:thread/" + thread.getId();
    }

}
