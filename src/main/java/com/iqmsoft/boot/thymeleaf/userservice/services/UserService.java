package com.iqmsoft.boot.thymeleaf.userservice.services;

import org.springframework.data.domain.Page;

import com.iqmsoft.boot.thymeleaf.userservice.entities.User;
import com.iqmsoft.boot.thymeleaf.userservice.services.dto.CurrentUser;
import com.iqmsoft.boot.thymeleaf.userservice.services.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long getNum();

    Page<UserDto> getPage(Integer pageNumber);

    void delete(Long id);

    UserDto find(Long id);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    Optional<User> findByEmail(String email);

    List<UserDto> findByName(String name);
}
