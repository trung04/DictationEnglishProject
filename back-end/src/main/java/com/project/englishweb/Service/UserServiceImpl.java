package com.project.englishweb.Service;

import com.project.englishweb.DTO.UserDTO;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.Service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Nếu thay đổi username/email thì kiểm tra trùng
            if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
                throw new RuntimeException("Email đã tồn tại");
            }
            if (!user.getUsername().equals(userDTO.getUsername()) && userRepository.existsByUsername(userDTO.getUsername())) {
                throw new RuntimeException("Username đã tồn tại");
            }

            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(userDTO.getPassword());
            }

            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
