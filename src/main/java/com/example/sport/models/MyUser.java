package com.example.sport.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

@Entity
@Table(name = "my_user")
//implementam UserDetails pentru a permite accesul la informatiile utilizatorului autentificat
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name = "liked_exercises")
    private String likedExercises;

    @Column(name = "liked_recipes")
    private String likedRecipes;

    @Column(name = "liked_venues")
    private String likedVenues;

    public MyUser() {}

    public MyUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLikedExercises() {
        return likedExercises;
    }

    public void setLikedExercises(String likedExercises) {
        this.likedExercises = likedExercises;
    }

    public String getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(String likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    public String getLikedVenues() {
        return likedVenues;
    }

    public void setLikedVenues(String likedVenues) {
        this.likedVenues = likedVenues;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", likedExercises='" + likedExercises + '\'' +
                ", likedRecipes='" + likedRecipes + '\'' +
                '}';
    }
}

