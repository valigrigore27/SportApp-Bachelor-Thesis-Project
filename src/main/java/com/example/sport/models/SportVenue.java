package com.example.sport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "sports_venues")
@Entity
public class SportVenue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String sportVenueName;

    @Column(name = "type")
    private String type;

    @Column(name = "location")
    private String location;

    @Column(name = "phone")
    private String phone;

    public SportVenue(){}
    public SportVenue(Long id, String sportVenueName, String type, String location, String phone) {
        this.id = id;
        this.sportVenueName = sportVenueName;
        this.type = type;
        this.location = location;
        this.phone = phone;
    }

}
