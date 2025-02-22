package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

@Controller
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "my_registration";
    }

    @PostMapping("/register")
    //punem ModelAttribute ca sa putem trimite datele din frontend pe backend cu SpringBoot
    public String createUser(@ModelAttribute MyUser user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    myUserRepository.save(user);

    // redirect la login
    return "redirect:/login";
}


}
