package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPublicProfileDTO {
    private Long userId;
    private String username;
    private String email;
    private int activeDays;
    private int activeHours;
}
