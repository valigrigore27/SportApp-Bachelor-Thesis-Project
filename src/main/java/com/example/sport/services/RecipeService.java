package com.example.sport.services;
import com.example.sport.models.food.ErrorResponse;
import com.example.sport.models.food.Ingredient;
import com.example.sport.models.food.RecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
//EDAMAM - SITE DE UNDE IAU RETETELE
@Service
public class RecipeService {

    //folosesc @Autowired pentru a injecta bean-ul din RestTemplateConfig
    //ca sa nu mai instantiez din nou si din nou RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    @Value("${edamam.api.key}")
    private String appKey;

    @Value("${edamam.api.id}")
    private String appId;

    //metoda care preia retetele de pe edamam
    public RecipeResponse getRecipes(String q){
        String url = "https://api.edamam.com/api/recipes/v2" + "?type=public&q=" + q + "&app_id=" + appId + "&app_key=" + appKey;
        try {
            //prin restTemplate imi iau obiectul de pe edamam.com
            // cu metoda .getForObject (ce primeste url-ul + tipul raspunsului de tip clasa)
            return restTemplate.getForObject(url, RecipeResponse.class);
            //in cazul in care nu se poate returna un astfel de raspuns cu retete
            //arunc exceptie returnand un obiect de tipul ErrorResponse (din models)
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = restTemplate.getForObject(url, ErrorResponse.class);
            assert errorResponse != null;
            throw new RuntimeException(errorResponse.getMessage());
        }
    }

    //ingredientele de care mai am nevoie pentru prima reteta din lista (dar trebuie pt o reteta anume facut!!)
    public void missingIngredients(String q){
        RecipeResponse recipeResponse = getRecipes(q);
        String[] words = q.split("\\W");
        List<Ingredient> allIngredients = recipeResponse.getHits().get(0).getRecipe().getIngredients();
        List<Ingredient> missingIngredients = new ArrayList<>(allIngredients);
        for(String word : words)
            for (Ingredient allIngredient : allIngredients)
                if (allIngredient.getText().contains(word)) missingIngredients.remove(allIngredient);
        System.out.println(missingIngredients);
    }
}
