package com.example.sport.controllers;

import com.example.sport.models.MyUser;
import com.example.sport.repositories.MyUserRepository;
import com.example.sport.services.GoogleSearchService;
import com.example.sport.services.TrainingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/training1")
public class Training1Controller {

    @Setter
    @Getter
    private int flag = 0;

    private final TrainingService trainingService;
    private final GoogleSearchService googleSearchService;

    @Autowired
    private MyUserRepository myUserRepository;

    public Training1Controller(TrainingService trainingService, GoogleSearchService googleSearchService) {
        this.trainingService = trainingService;
        this.googleSearchService = googleSearchService;
    }

    @GetMapping()
    public String getTrainingTable(Model model) {
        // Obținere utilizator curent sau utilizator implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Adăugare informații utilizator în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        setFlag(0);
        model.addAttribute("flag", flag);
        model.addAttribute("all_exercises", trainingService.getAllExercises());

        return "training1";
    }

    @GetMapping("/get-recommendations-and-like-exercise/{exerciseId}/{exerciseTitle}")
    public String getRecommendationsAndLikeExercise(@PathVariable("exerciseId") Integer exerciseId, @PathVariable("exerciseTitle") String exerciseTitle, Model model) {
        MyUser user = getAuthenticatedUserOrDefault();
        // Gestionare adăugare exercițiu în lista de exerciții apreciate
        String likedExercises = user.getLikedExercises();
        if (likedExercises == null || likedExercises.isEmpty()) {
            likedExercises = exerciseTitle;
        } else {
            //aici iau string-ul care contine cuvinte separate orin virgula si il sparg intr-o lista de cuvinte
            List<String> exercisesList = new ArrayList<>(Arrays.asList(likedExercises.split(",")));
            if (!exercisesList.contains(exerciseTitle)) {
                //acum iau titlul la care am dat like si il pun in acea lista
                exercisesList.add(exerciseTitle);
                //aici iau lista toata cu tot cu ultimul cuvant bagat si creez iar stringul separat prin virgule
                likedExercises = String.join(",", exercisesList);
            }
        }
        //actualizez lista atribuita userului respectiv
        user.setLikedExercises(likedExercises);
        //salvez user-ul din bd cu actualizarile facute
        myUserRepository.save(user);

        List recommendations = trainingService.getRecommendations(exerciseId, 3);
        Object findExerciseById = trainingService.getAllExercises().get(exerciseId);
        // Adăugare informații utilizator și exerciții în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        //flag=1 ca sa vad partea de recomandari + il adaug in model
        setFlag(1);
        model.addAttribute("flag", flag);
        model.addAttribute("exercise_title", exerciseTitle);
        model.addAttribute("find_exercise_by_id", findExerciseById);
        model.addAttribute("recommendations", recommendations);
        return "training1";
    }

    @GetMapping("/view-gif/{exerciseName}")
    public String getExerciseImage(@PathVariable("exerciseName") String exerciseName, Model model) {
        // caut imagine pentru exercitiul respectiv
        String imageUrl = googleSearchService.getFirstImageUrl(exerciseName);
        System.out.println("Image URL: " + imageUrl);

        // adaug imaginea + numele ex. în model
        model.addAttribute("exerciseImage", imageUrl);
        model.addAttribute("exerciseName", exerciseName);

        setFlag(2);
        model.addAttribute("flag", flag);
        return "training1";
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