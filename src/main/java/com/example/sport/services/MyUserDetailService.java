package com.example.sport.services;

import com.example.sport.models.MyUser;
import com.example.sport.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

//LA CE IMI TREBUIE ACEASTA CLASA SERVICE CAND POT FOLOSI DIRECT REPO-RUL MyUserRepository

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<MyUser> user = repository.findByUsername(username);
//        if(user.isPresent()){
//            var userObj = user.get();
//            return userObj;
//        }else {
//            throw new UsernameNotFoundException(username);
//        }
        //SAU
        MyUser user = repository.findByUsername(username).orElseThrow();
        return user;
    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException{
        MyUser user = repository.findById(userId).orElseThrow();
        return user;
    }
}
