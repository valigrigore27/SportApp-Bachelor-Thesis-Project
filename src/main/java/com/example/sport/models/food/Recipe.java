package com.example.sport.models.food;

import java.util.List;

public class Recipe {
    private String label;
    private String image;
    private List<String> healthLabels;
    private int yield;
    private List <Ingredient> ingredients;
    private double calories;
    private Nutrients totalNutrients;
    private String url;

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Nutrients getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(Nutrients totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }
}
