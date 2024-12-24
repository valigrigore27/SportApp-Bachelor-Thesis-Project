package com.example.sport.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Table(name = "sport")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "sport_name")
    private String sportName;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "estimated_training_time")
    private int estimatedTrainingTime;

    @Column(name = "training_description")
    private String trainingDescription;

    @Column(name = "muscles_worked")
    private String musclesWorked;

    @Column(name = "famous_athletes")
    private String famousAthletes;

    @Column(name = "image")
    private String image;

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public int getEstimatedTrainingTime() {
        return estimatedTrainingTime;
    }

    public void setEstimatedTrainingTime(int estimatedTrainingTime) {
        this.estimatedTrainingTime = estimatedTrainingTime;
    }

    public String getFamousAthletes() {
        return famousAthletes;
    }

    public void setFamousAthletes(String famousAthletes) {
        this.famousAthletes = famousAthletes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusclesWorked() {
        return musclesWorked;
    }

    public void setMusclesWorked(String musclesWorked) {
        this.musclesWorked = musclesWorked;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    public String getImage(){return image;}

    public void setImage(String image){
        this.image= image;
    }
}
