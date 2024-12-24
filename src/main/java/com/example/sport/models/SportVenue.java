package com.example.sport.models;

import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSportVenueName() {
        return sportVenueName;
    }

    public void setSportVenueName(String sportVenueName) {
        this.sportVenueName = sportVenueName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
