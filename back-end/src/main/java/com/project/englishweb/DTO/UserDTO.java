package com.project.englishweb.DTO;

import lombok.Data;

@Data
public class UserDTO {
    
	private Long userId;
    private String username;
    private String email;
    private String password;
    private int role;
}
