package com.example.sport.controllers;

import com.example.sport.models.Message;
import com.example.sport.repositories.MyUserRepository;
import com.example.sport.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friends")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired MyUserRepository myUserRepository;

    @PostMapping("/send_message")
    public ResponseEntity<String> sendMessage(@RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String content) {
        messageService.sendMessage(senderId, receiverId, content);
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(@RequestParam Long senderId, @RequestParam Long receiverId) {
        return ResponseEntity.ok(messageService.getConversation(senderId, receiverId));
    }
//    public String getConversation1(Model model){
//        Long senderId = Long.valueOf(2);
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        MyUser receiver = (MyUser) userDetails;
//        Long receiverId = receiver.getId();
//        String receiverName = receiver.getUsername();
//        String senderName = myUserRepository.findById(senderId).orElseThrow().getUsername();
//
//        List<Message> conversation = messageService.getConversation(senderId, receiverId);
//
//        model.addAttribute("receiverName", receiverName);
//        model.addAttribute("senderName", senderName);
//        model.addAttribute("conversation", conversation);
//        return "friends";
//    }
}

