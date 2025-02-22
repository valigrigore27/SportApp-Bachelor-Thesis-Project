package com.example.sport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

@Setter
@Getter
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

    @Column(name = "photo_path")
    private String photoPath;

    public MyUser() {}

    public MyUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
                ", likedVenues='" + likedVenues + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}

