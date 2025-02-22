package com.example.sport.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseRecommendation {
    @JsonProperty("ID")
    private int id;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("BodyPart")
    private String bodyPart;

    @JsonProperty("Equipment")
    private String equipment;

    @JsonProperty("Rating")
    private double rating;

    public ExerciseRecommendation(int id, String title, String bodyPart, String equipment, double rating) {
        this.bodyPart = bodyPart;
        this.equipment = equipment;
        this.id = id;
        this.rating = rating;
        this.title = title;
    }

}
