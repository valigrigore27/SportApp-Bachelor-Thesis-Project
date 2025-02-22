package com.example.sport.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Mesajul in sine de la sender la receiver se gaseste in content
@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MyUser sender;

    @ManyToOne
    private MyUser receiver;

    //mesajul nenul
    @Column(nullable = false)
    private String content;

    private LocalDateTime time;

    public Message(){

    }

    public Message(Long id, MyUser sender, MyUser receiver, String content, LocalDateTime time) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyUser getSender() {
        return sender;
    }

    public void setSender(MyUser sender) {
        this.sender = sender;
    }

    public MyUser getReceiver() {
        return receiver;
    }

    public void setReceiver(MyUser receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
