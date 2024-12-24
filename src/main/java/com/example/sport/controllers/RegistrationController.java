package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user){
        //criptez parola pe care acesta a pus-o
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //salvez user-ul in repo
        return myUserRepository.save(user);
    }
}
