package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.models.food.Ingredient;
import com.example.sport.models.food.RecipeResponse;
import com.example.sport.services.RecipeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food-details1")
public class FoodDetails1Controller {

    private final RecipeService recipeService;

    public FoodDetails1Controller(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public String getDashboard(Model model) {
        // Obținere utilizator curent sau setare utilizator implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Adăugare informații utilizator în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());


        return "food-details1";
    }


    private MyUser getAuthenticatedUserOrDefault() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (MyUser) principal; // Conversie la MyUser dacă utilizatorul este autentificat
        } else {
            // Creează un utilizator implicit pentru testare
            MyUser testUser = new MyUser();
            testUser.setId(6L); // ID implicit
            testUser.setUsername("Test User"); // Nume implicit
            testUser.setLikedRecipes(""); // Rețete implicite
            testUser.setLikedExercises(""); // Exerciții implicite
            return testUser;
        }
    }

}
