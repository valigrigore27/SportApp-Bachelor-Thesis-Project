package com.example.sport.controllers;
import com.example.sport.models.MyUser;
import com.example.sport.models.SportVenue;
import com.example.sport.repositories.MyUserRepository;
import com.example.sport.repositories.SportVenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/maps1")
public class Maps1Controller {

    @Autowired
    SportVenueRepository sportVenueRepository;
    @Autowired
    MyUserRepository myUserRepository;

    @GetMapping
    public String getMapsPage(Model model) {
        // Obține utilizatorul autentificat sau creează unul fictiv
        MyUser user = getAuthenticatedUserOrDefault();
        // Pune informațiile utilizatorului în model
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        return "maps1";
    }

    @GetMapping("/search-by-name")
    private String searchByNameALocation(@RequestParam String sportVenueName, Model model){

        List<SportVenue> locatii = sportVenueRepository.findBySportVenueName(sportVenueName);
        MyUser user = getAuthenticatedUserOrDefault();

        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("locatii", locatii);
        if (!locatii.isEmpty()) {
            SportVenue firstLocation = locatii.get(0);
            model.addAttribute("locationAddress", firstLocation.getLocation());
//            System.out.println(firstLocation.getLocation());
        }

        return "maps1";
    }

    @GetMapping("/like-a-venue/{sportVenueName}")
    public String likeAVenue(@PathVariable("sportVenueName") String sportVenueName, Model model) {
        // Obține utilizatorul autentificat sau unul implicit
        MyUser user = getAuthenticatedUserOrDefault();

        // Adaugă rețeta în lista de rețete apreciate
        String likedVenues = user.getLikedVenues();
        if (likedVenues == null || likedVenues.isEmpty()) {
            likedVenues = sportVenueName;
        } else {
            List<String> venuesList = new ArrayList<>(Arrays.asList(likedVenues.split(",")));
            if (!venuesList.contains(sportVenueName)) {
                venuesList.add(sportVenueName);
                likedVenues = String.join(",", venuesList);
            }
        }
        user.setLikedVenues(likedVenues);
        myUserRepository.save(user);

        model.addAttribute("sportVenueName", sportVenueName);
        return "redirect:/maps1";
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
