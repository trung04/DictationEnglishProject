package com.project.englishweb.Controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class
AdminController {
    @GetMapping("/index")
    public String home() {
        return "index";
    }
    @GetMapping("/layout")
    public String layout(){
        return "layout/layout";
    }
}
