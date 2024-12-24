package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    // Controller pentru index înainte de logare
    @GetMapping("/")
    public String handleWelcome() {
        return "index";
    }

    // Controller pentru index după logare
    @GetMapping("/user/index")
    public String handleUserHome(Model model) {
        MyUser user = getAuthenticatedUserOrDefault();

        // Adăugare informații utilizator în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        return "user_index";
    }

    // Controller pentru pagina de login
    @GetMapping("/login")
    public String handleLogin() {
        return "my_login";
    }


    private MyUser getAuthenticatedUserOrDefault() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (MyUser) principal;
        } else {
            MyUser testUser = new MyUser();
            testUser.setId(6L);
            testUser.setUsername("Test User");
            testUser.setLikedRecipes("");
            testUser.setLikedExercises("");
            return testUser;
        }
    }
}