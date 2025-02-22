package com.example.sport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private RestTemplate restTemplate;

    public List getRecommendations(int exerciseId, int topN) {
        String apiUrl = "http://127.0.0.1:5000/recommend?exercise_id=" + exerciseId + "&top_n=" + topN;
        ResponseEntity<List> response = restTemplate.getForEntity(apiUrl, List.class);
        return response.getBody();
    }

        //DE CE NU MERGE FACUT ASA CA LA RecipeService??? (.getForObject vs .getForEntity)
    //ADICA SA FOLOSESC CLASA ExerciseRecommendation
//        public ExerciseRecommendation getRecommendations(int exerciseId, int topN) {
//            String apiUrl = "http://127.0.0.1:5000/recommend?exercise_id=" + exerciseId + "&top_n=" + topN;
//            System.out.println(restTemplate.getForObject(apiUrl, ExerciseRecommendation.class));
//            return restTemplate.getForObject(apiUrl, ExerciseRecommendation.class);
//}

    public List getAllExercises(){
        String apiUrl = "http://127.0.0.1:5000/all_exercises";
        ResponseEntity<List> response = restTemplate.getForEntity(apiUrl, List.class);
        return response.getBody();
    }
}
