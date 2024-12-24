package com.example.sport.models;

import jakarta.persistence.*;

//asociata cu tabela Friend din BD se refera la un request mai mult
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyUser getUser1() {
        return user1;
    }

    public void setUser1(MyUser user1) {
        this.user1 = user1;
    }

    public MyUser getUser2() {
        return user2;
    }

    public void setUser2(MyUser user2) {
        this.user2 = user2;
    }
}
