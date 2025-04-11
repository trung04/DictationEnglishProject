package com.project.englishweb.Controller.user;

import com.project.englishweb.DTO.UserDTO;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //  Hiển thị danh sách + Form tạo/sửa người dùng
    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) Long id,
                            Model model,
                            @RequestParam(required = false) String errorMessage) {
        Page<User> userPage = userService.getAllUsers(PageRequest.of(page, 5));
        model.addAttribute("users", userPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());

        if (id != null) {
            userService.getUserById(id).ifPresent(user -> {
                UserDTO dto = new UserDTO();
                dto.setUsername(user.getUsername());
                dto.setEmail(user.getEmail());
                model.addAttribute("userDTO", dto);
                model.addAttribute("userId", user.getUserId());
            });
        } else {
            model.addAttribute("userDTO", new UserDTO()); // form trống khi tạo mới
        }

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "user/manage";
    }

    //  Tạo người dùng mới (hiển thị lỗi nếu bị trùng)
    @PostMapping
    public String createUser(@ModelAttribute("userDTO") UserDTO userDTO,
                             Model model,
                             @RequestParam(defaultValue = "0") int page) {
        try {
            userService.createUser(userDTO);
            return "redirect:/admin/users";
        } catch (RuntimeException e) {
            // Load lại danh sách và trả về form với dữ liệu cũ + lỗi
            Page<User> userPage = userService.getAllUsers(PageRequest.of(page, 5));
            model.addAttribute("users", userPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", userPage.getTotalPages());

            model.addAttribute("userDTO", userDTO);
            model.addAttribute("errorMessage", e.getMessage());
            return "user/manage";
        }
    }

    //  Cập nhật người dùng
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("userDTO") UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return "redirect:/admin/users";
    }

    //  Xóa người dùng
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
