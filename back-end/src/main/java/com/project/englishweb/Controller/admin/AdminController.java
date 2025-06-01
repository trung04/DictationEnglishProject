package com.project.englishweb.Controller.admin;

import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

  
    @GetMapping("/index")
    public String home(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("admin");
        if (admin == null || admin.getRole() != 1) {
            return "redirect:/login";
        }
        if (admin != null) {
            model.addAttribute("user", admin);
        }
        return "index";
    }


    @GetMapping("/layout")
    public String layout(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("admin");
        if (admin != null) {
            model.addAttribute("user", admin);
        }
        return "layout/layout";
    }

   
    @GetMapping("/login")
    public String login(HttpSession session,@RequestParam(value = "error", required = false) String error, Model model) {
        User admin = (User) session.getAttribute("admin");
        if(admin!=null && admin.getRole()==1) {
            model.addAttribute("user", admin);

            return "redirect:/index";
        }
        if (error != null) {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng.");
        }
        return "admin/login";
    }


    @PostMapping("/handleLogin")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "redirect:/login?error=true";
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/login?error=true";
        }

        if (user.getRole() != 1) {
            model.addAttribute("error", "Tài khoản không có quyền truy cập admin.");
            return "admin/login";
        }

        session.setAttribute("admin", user);
        return "redirect:/index";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
