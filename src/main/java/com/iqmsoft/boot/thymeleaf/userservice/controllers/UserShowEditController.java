package com.iqmsoft.boot.thymeleaf.userservice.controllers;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.iqmsoft.boot.thymeleaf.userservice.services.UserService;
import com.iqmsoft.boot.thymeleaf.userservice.services.dto.UserDto;
import com.iqmsoft.boot.thymeleaf.userservice.util.UserValidator;

@Slf4j
@Controller
@RequestMapping("/user")
@SessionAttributes("existingUser")
public class UserShowEditController {

    @Autowired
    private UserService userService;

    @PostAuthorize("@currentUserServiceImpl.canReadUserDetails(principal, #id)")
    @GetMapping(value = "/{id}")
    public String showUser(@PathVariable Long id, Model model, @RequestParam(required = false) boolean saved) {
        log.info("GET: Show data for user with id = {}", id);
        UserDto user = userService.find(id);

        model.addAttribute("existingUser", user);
        model.addAttribute("saved", saved);
        return "user";
    }

    @PostAuthorize("@currentUserServiceImpl.canWriteUserDetails(principal, #user.id)")
    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("existingUser") @Valid UserDto user, BindingResult result,
                           @RequestParam(required = false) String state, SessionStatus sessionStatus) {
        log.info("POST: Edit user");
        new UserValidator().validate(user, result);

        if (state != null && state.equals("init")) {
            log.info("Show user data to edit: {}", user);
            return "user_edit";
        }
        if (result.hasErrors()) {
            log.error("Invalid user edit data: {}", user);
            return "user_edit";
        }

        userService.update(user);
        sessionStatus.setComplete();
        log.info("User data updated successfully! {}", user);
        return String.format("redirect:/user/%d?saved=true", user.getId());
    }
}
