package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.models.food.Ingredient;
import com.example.sport.models.food.RecipeResponse;
import com.example.sport.repositories.MyUserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.sport.services.RecipeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/food1")
public class Food1Controller {
    @Setter
    @Getter
    private int flag = 0;

    private final RecipeService recipeService;

    @Autowired
    private MyUserRepository myUserRepository;

    public Food1Controller(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    // `q` este parametrul din query, ex.: "/food?q=onion+garlic+salt+cucumber"
    public String getRecipes(@RequestParam(required = false) String q, Model model) {
        // Obține utilizatorul autentificat sau unul implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Pune informațiile utilizatorului în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        setFlag(0);
        model.addAttribute("flag", flag);
        RecipeResponse response = recipeService.getRecipes(q);

        // Pune în model rețetele găsite (hits) și query-ul introdus
        model.addAttribute("hits", response.getHits());
        model.addAttribute("q", q);
        return "food1";
    }

    @GetMapping("/{label}")
    public String likeARecipe(@PathVariable("label") String label, Model model) {
        setFlag(1);
        model.addAttribute("flag", flag);

        // Obține utilizatorul autentificat sau unul implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Adaugă rețeta în lista de rețete apreciate
        String likedRecipes = user.getLikedRecipes();
        if (likedRecipes == null || likedRecipes.isEmpty()) {
            likedRecipes = label;
        } else {
            List<String> recipesList = new ArrayList<>(Arrays.asList(likedRecipes.split(",")));
            if (!recipesList.contains(label)) {
                recipesList.add(label);
                likedRecipes = String.join(",", recipesList);
            }
        }
        user.setLikedRecipes(likedRecipes);
        myUserRepository.save(user);

        model.addAttribute("label", label);
        return "redirect:/food1";
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
