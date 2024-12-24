package com.example.sport.repositories;

import com.example.sport.models.MyUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional <MyUser> findByUsername(String username);
    @NotNull
    List<MyUser> findAll();
}
