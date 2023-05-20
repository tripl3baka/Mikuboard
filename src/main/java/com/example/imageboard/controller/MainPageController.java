package com.example.imageboard.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    @GetMapping("/hello")
    public String showPage (Model model){
        return "index";
    }

}
