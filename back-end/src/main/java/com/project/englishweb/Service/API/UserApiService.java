package com.project.englishweb.Service.API;

import com.project.englishweb.DTO.ChangeEmailRequest;
import com.project.englishweb.DTO.ChangePasswordRequest;
import com.project.englishweb.DTO.UserAccountDTO;
import com.project.englishweb.DTO.UserPublicProfileDTO;


public interface UserApiService {
    UserPublicProfileDTO getPublicProfile(String username);
    UserAccountDTO getAccountInfo(String username);
    void changePassword(String username, ChangePasswordRequest request);
    void changeEmail(String username, ChangeEmailRequest request);
}

