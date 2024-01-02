package com.abhijeet.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abhijeet.web.dto.ClubDto;
import com.abhijeet.web.dto.RegistrationDto;
import com.abhijeet.web.models.UserEntity;
import com.abhijeet.web.security.SecurityUtil;
import com.abhijeet.web.service.UserRepository;
import com.abhijeet.web.service.UserService;

import jakarta.validation.Valid;

@Controller

public class AuthController {
    @Autowired
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        int check1 = 0;
        int check2 = 0;
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null
                && !existingUserEmail.getEmail().isEmpty()) {
            check1 = 1;
        }
        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null && existingUsername.getUsername() != null
                && !existingUsername.getUsername().isEmpty()) {
            check2 = 1;
        }
        if (check1 == check2 && check1 > 0) {
            return "redirect:/register?check";
        }
        if (check1 == 1 && check2 == 0) {
            return "redirect:/register?fail";
        }
        if (check2 == 1 && check1 == 0) {
            return "redirect:/register?error";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";

    }

}
