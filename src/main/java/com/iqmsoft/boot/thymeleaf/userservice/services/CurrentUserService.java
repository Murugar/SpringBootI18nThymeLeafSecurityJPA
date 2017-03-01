package com.iqmsoft.boot.thymeleaf.userservice.services;

import com.iqmsoft.boot.thymeleaf.userservice.services.dto.CurrentUser;

public interface CurrentUserService {

    boolean canReadUserDetails(CurrentUser currentUser, Long userId);

    boolean canWriteUserDetails(CurrentUser currentUser, Long userId);
}
