package com.example.sport.services;

import com.example.sport.models.Message;
import com.example.sport.models.MyUser;
import com.example.sport.repositories.MessageRepository;
import com.example.sport.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MyUserRepository myUserRepository;

    //salvez in baza de date sender-ul, receiver-ul (plecand de la id-urile lor)
    //dar si continutul mesajului + data-ora la care a fost apelata fctia
    public void sendMessage(Long senderId, Long receiverId, String content){
        MyUser sender = myUserRepository.findById(senderId).orElseThrow();
        MyUser receiver = myUserRepository.findById(receiverId).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTime(LocalDateTime.now());
        messageRepository.save(message);
    }

    //obtin conversatia dintre doi useri plecand de la id-urile lor
    //(ii caut in repo dupa id-uri)
    public List<Message> getConversation(Long senderId, Long receiverId){
        MyUser sender = myUserRepository.findById(senderId).orElseThrow();
        MyUser receiver = myUserRepository.findById(receiverId).orElseThrow();
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }
}
