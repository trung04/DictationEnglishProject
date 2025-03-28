package com.project.englishweb.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/index")
    public String home() {
        return "admin/home"; // Trả về templates/admin/home.html
    }

    @GetMapping("/layout")
    public String layout() {
        return "layout/layout"; // Trả về templates/layout/layout.html
    }
}
