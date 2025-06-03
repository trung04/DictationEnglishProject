package com.project.englishweb.Service;

import com.project.englishweb.DTO.UserDTO;
import com.project.englishweb.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    Optional<User> getUserById(Long id);
    void createUser(UserDTO userDTO);
    void updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    public int addTime(Long id,int second);
    public long getTime(Long id);
    List<User> getAllUsers();

}
