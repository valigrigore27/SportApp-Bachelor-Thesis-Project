package com.example.sport.models.food;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RecipeResponse {
    //nr total de retete care corespund cautarii
    private Integer count;
    private List<Hit> hits;

}
