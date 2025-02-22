package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Setter
@Getter
@Controller
@RequestMapping("/dashboard1")
public class Dashboard1Controller {
    private int flag = 0;

    @GetMapping()
    public String getDashboard(Model model) {
        // Obținere utilizator curent sau setare utilizator implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Adăugare informații utilizator în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        // Setare flag pentru vizualizarea cardurilor albe
        setFlag(0);
        model.addAttribute("flag", flag);
        return "dashboard1";
    }

    @GetMapping("/my-exercises")
    public String myExercises(Model model) {
        // Setare flag pentru vizualizarea exercițiilor utilizatorului
        setFlag(1);
        model.addAttribute("flag", flag);

        // Obținere utilizator curent sau utilizator implicit
        MyUser user = getAuthenticatedUserOrDefault();
        String likedExercises = user.getLikedExercises();
        String[] myExercises = likedExercises != null ? likedExercises.split(",") : new String[]{};

        // Adăugare informații utilizator și exerciții în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("my_exercises", myExercises);
        return "dashboard1";
    }

    @GetMapping("/my-recipes")
    public String myRecipes(Model model) {
        // Setare flag pentru vizualizarea rețetelor utilizatorului
        setFlag(2);
        model.addAttribute("flag", flag);

        MyUser user = getAuthenticatedUserOrDefault();
        String likedRecipes = user.getLikedRecipes();
        String[] myRecipes = likedRecipes != null ? likedRecipes.split(",") : new String[]{};

        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("my_recipes", myRecipes);
        return "dashboard1";
    }

    @GetMapping("my-venues")
    public String myVenues(Model model){
        // Setare flag pentru vizualizarea rețetelor utilizatorului
        setFlag(3);
        model.addAttribute("flag", flag);

        // Obținere utilizator curent sau utilizator implicit
        MyUser user = getAuthenticatedUserOrDefault();
        String likedVenues = user.getLikedVenues();
        String[] myVenues = likedVenues != null ? likedVenues.split(",") : new String[]{};

        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("my_venues", myVenues);
        return "dashboard1";
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