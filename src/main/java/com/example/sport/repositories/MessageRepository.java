package com.example.sport.repositories;

import com.example.sport.models.Message;
import com.example.sport.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(MyUser sender, MyUser receiver);
}
