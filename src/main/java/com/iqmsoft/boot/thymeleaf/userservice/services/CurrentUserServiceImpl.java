package com.iqmsoft.boot.thymeleaf.userservice.services;

import org.springframework.stereotype.Service;

import com.iqmsoft.boot.thymeleaf.userservice.services.dto.CurrentUser;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canReadUserDetails(CurrentUser currentUser, Long userId) {
        return currentUser != null && hasReadPermission(currentUser, userId);
    }

    @Override
    public boolean canWriteUserDetails(CurrentUser currentUser, Long userId) {
        return currentUser != null && hasWritePermission(currentUser, userId);
    }

    private boolean hasReadPermission(CurrentUser currentUser, Long userId) {
        return currentUser.isAdmin() || currentUser.isSuperAdmin() || isUsersOwnData(currentUser, userId);
    }

    private boolean hasWritePermission(CurrentUser currentUser, Long userId) {
        return currentUser.isSuperAdmin() || isUsersOwnData(currentUser, userId);
    }

    private boolean isUsersOwnData(CurrentUser currentUser, Long userId) {
        return currentUser.getId().equals(userId);
    }
}
