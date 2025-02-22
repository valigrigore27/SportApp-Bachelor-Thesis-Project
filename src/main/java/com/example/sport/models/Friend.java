package com.example.sport.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//asociata cu tabela Friend din BD se refera la un request mai mult
@Setter
@Getter
@Entity
@Table(name = "Friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //doi useri care vor fi prieteni sau nu
    @ManyToOne
    private MyUser user1;

    @ManyToOne
    private MyUser user2;

    private boolean accepted; //daca is prieteni sau nu

    public Friend(){

    }

    public Friend(Long id, MyUser user1, MyUser user2, boolean accepted) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.accepted = accepted;
    }

}
