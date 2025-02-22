package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.models.food.Ingredient;
import com.example.sport.models.food.RecipeResponse;
import com.example.sport.repositories.MyUserRepository;
import com.example.sport.services.MessageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.sport.services.RecipeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/food1")
public class Food1Controller {
    @Setter
    @Getter
    private int flag = -1;

    private final RecipeService recipeService;
    private final MessageService messageService;
    @Autowired
    private MyUserRepository myUserRepository;

    public Food1Controller(RecipeService recipeService, MessageService messageService) {
        this.recipeService = recipeService;
        this.messageService = messageService;
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
        // Pune informațiile utilizatorului în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        return "redirect:/food1";
    }

    @PostMapping("/send-missing-ingredients-to-a-friend/{q}/{label}")
    public String sendMissingIngredientsToAFriends(@RequestParam String friendName, @PathVariable("q") String q, @PathVariable("label") String label, Model model){
        setFlag(2);
        model.addAttribute("flag", flag);
        //obtin utilizatorul conectat si utilizatorul caruia vreau sa-i trimit mesaj
        MyUser me = getAuthenticatedUserOrDefault();
        MyUser user1 = myUserRepository.findByUsername(friendName).orElseThrow();

        //iau ingredientele mele din query si le adaug in model
        String[] myIngredients = q.split("\\W");
        model.addAttribute("my_ingredients", myIngredients);

        //elimin din ingredientele retetei respective ingredientele mele ca sa obtin ingredientele lipsa
        RecipeResponse recipeResponse = recipeService.getRecipes(label);
        List<Ingredient> allIngredients = recipeResponse.getHits().get(0).getRecipe().getIngredients();
        List<Ingredient> missingIngredients = new ArrayList<>(allIngredients);
        for(String word : myIngredients)
            for(int i = 0; i < allIngredients.size(); i++)
                if (allIngredients.get(i).getText().contains(word)) missingIngredients.remove(allIngredients.get(i));
        model.addAttribute("missing_ingredients", missingIngredients);

        //trimit un mesaj standard cu ingredientele lipsa
        String message = "Hi, I was thinking of making " + label + ", but from this recipe, I only have " + Arrays.toString(myIngredients) +
                ". I was wondering if you'd like to come over and bring " + missingIngredients.stream().map(Ingredient::getText).toList();
        messageService.sendMessage(me.getId(), user1.getId(), message);

        // Pune informațiile utilizatorului în model
        model.addAttribute("userId", me.getId());
        model.addAttribute("username", me.getUsername());
        return "redirect:/food1?successMessage=Message sent to " + friendName;
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