package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDTO {
    private Long userId;
    private String username;
    private String email;
    private int activeDays;
    private int activeHours;
    private String createdAt;
    private int role;
}
