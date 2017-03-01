package com.iqmsoft.boot.thymeleaf.userservice.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iqmsoft.boot.thymeleaf.userservice.services.dto.UserDto;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (user.getAge() != null && user.getAge() < 18 && user.isAdmin()) {
            errors.rejectValue("admin", "error.admin.young");
        }
    }
}
