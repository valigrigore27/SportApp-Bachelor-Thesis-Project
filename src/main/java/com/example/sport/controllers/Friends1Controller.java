package com.example.sport.controllers;

import com.example.sport.models.Friend;
import com.example.sport.models.Message;
import com.example.sport.models.MyUser;
import com.example.sport.repositories.FriendRepository;
import com.example.sport.repositories.MyUserRepository;
import com.example.sport.services.FriendService;
import com.example.sport.services.MessageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/friends1")
public class Friends1Controller {
    @Setter
    @Getter
    private int flag = 0;

    @Autowired
    private MessageService messageService;
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private FriendService friendService;

//sa fac o metoda care sa imi ia userul (id + nume)
    //ca sa nu mai fac asta tot timpul:
    //MyUser user = getAuthenticatedUserOrDefault();
    //        Long userId = user.getId();
    //        String userName = user.getUsername();

    @GetMapping
    public String getFriendsPage(Model model) {
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId = user.getId();
        String userName = user.getUsername();

        model.addAttribute("userId", userId);
        model.addAttribute("username", userName);
        setFlag(0);
        model.addAttribute("flag", flag);
        return "friends1";
    }

    @GetMapping("/my_friends")
    public String myFriends(Model model){
        // Obține utilizatorul autentificat sau creează unul fictiv
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId = user.getId();
        String username = user.getUsername();

        List<MyUser> allMyFriends = friendService.getFriends(userId);
        model.addAttribute("allMyFriends", allMyFriends);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        setFlag(1);
        model.addAttribute("flag", flag);
        return "friends1";
    }

    @GetMapping("/maybe_you_know_them")
    public String maybeYouKnowThem(Model model){
        // Obține utilizatorul autentificat sau creează unul fictiv
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId = user.getId();
        String username = user.getUsername();

        //toti oamenii din baza de date
        List<MyUser> allPeople = myUserRepository.findAll();
        //toti prietenii mei
        List<MyUser> allMyFriends = friendService.getFriends(userId);
        //id-urile prietenilor mei
        List<Long> friendIds = allMyFriends.stream().map(MyUser::getId).toList();

        //toti cei carora le-am dat cerere si nu mi-au acceptat-o
        List<MyUser> allMyPendingFriends = friendService.getPendingFriends(userId);
        //id-urile acestora
        List<Long> pendingFriendIds = allMyPendingFriends.stream().map(MyUser::getId).toList();

        //cei pe care ii pot adauga la prieteni sunt toti din bd minus cei pe care
        //ii am la prieteni minus cei carora le-am dat cerere - eu

        List<MyUser> notMyFriends = allPeople
                .stream()
                .filter(user1 -> !friendIds.contains(user1.getId()) && !pendingFriendIds.contains(user1.getId()) && user1.getId()!=user.getId())
                .toList();

        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("notMyFriends", notMyFriends);
        setFlag(2);
        model.addAttribute("flag", flag);
        return "friends1";
    }

    @GetMapping("/my_pending_friends")
    public String myPendingFriends(Model model){
        // Obține utilizatorul autentificat sau creează unul fictiv
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId = user.getId();
        String username = user.getUsername();
        List<MyUser> allMyPendingFriends = friendService.getPendingFriends(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("allMyPendingFriends", allMyPendingFriends);
        setFlag(3);
        model.addAttribute("flag", flag);
        return "friends1";
    }

    @GetMapping("/my_friend_requests")
    public String myFriendRequests(Model model){
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId = user.getId();
        String userName = user.getUsername();

        List<MyUser> myFriendRequests = friendService.getMyFriendRequests(user);
        model.addAttribute("myFriendRequests", myFriendRequests);
        model.addAttribute("userId", userId);
        model.addAttribute("username", userName);
        setFlag(4);
        model.addAttribute("flag", flag);
        return "friends1";
    }

    @GetMapping("/get-conversation/{userId2}")
    public String getConversationWithAFriend(@PathVariable("userId2") Long userId2, Model model) {
        // Obține utilizatorul autentificat sau creează unul fictiv
        MyUser user1 = getAuthenticatedUserOrDefault();

        Long userId1 = user1.getId();
        String username1 = user1.getUsername();

        // Obține informațiile despre prieten (sender)
        String username2 = myUserRepository.findById(userId2).orElseThrow().getUsername();

        // iau conversația(care de fapt este o lista de mesaje, fiecare continand sender, receiver, content, time)
        // dintre user1 si user 2 dar si invers si le concatenez
        List<Message> conversation1 = messageService.getConversation(userId2, userId1);
        List<Message> conversation2 = messageService.getConversation(userId1, userId2);

        List<Message> fullConversation = Stream.concat(conversation1.stream(), conversation2.stream())
                .sorted(Comparator.comparing(Message::getTime))
                .toList();
        model.addAttribute("fullConversation", fullConversation);

        setFlag(5);
        model.addAttribute("flag", flag);
        model.addAttribute("userId1", userId1);
        model.addAttribute("userId2", userId2);
        model.addAttribute("username1", username1);
        model.addAttribute("username2", username2);

        //asta ca sa se potriveasca cu denumirile din navbarul de la flagul 0
        model.addAttribute("receiverId", userId1);
        model.addAttribute("receiverName", username1);

        model.addAttribute("conversation1", conversation1);
        model.addAttribute("conversation2", conversation2);
        return "friends1";
    }

    @PostMapping("/get-conversation/send-message/{userId2}")
    public String sendMessage(@PathVariable Long userId2, @RequestParam String content){
        MyUser user1 = getAuthenticatedUserOrDefault();
        Long userId1 = user1.getId();
        //apelez functia din service care imi va salva in baza de date ce am trimis eu (cel logat) unui user2
        messageService.sendMessage(userId1, userId2, content);
        return "redirect:/friends1/get-conversation/{userId2}";
    }

    @PostMapping("/send-friend-request/{userId2}")
    public String sendFriendRequest(@PathVariable Long userId2){
        MyUser user = getAuthenticatedUserOrDefault();
        Long userId1 = user.getId();
        friendService.sendFriendRequest(userId1, userId2);
        return "redirect:/friends1";
    }

    @PostMapping("/delete-request/{userId2}")
    public String deleteFriendRequest(@PathVariable Long userId2){
        MyUser user1 = getAuthenticatedUserOrDefault();
        MyUser user2 = myUserRepository.findById(userId2).orElseThrow();

        Friend friendRequest = friendRepository.findByUser1AndUser2AndAccepted(user1, user2, false).orElseThrow();

        friendRepository.delete(friendRequest);
        return "redirect:/friends1";
    }

    @PostMapping("accept-friend-request/{userId2}")
    public String acceptFriendRequest(@PathVariable Long userId2){
        MyUser user1 = getAuthenticatedUserOrDefault();
        MyUser user2 = myUserRepository.findById(userId2).orElseThrow();
        Friend myRequest = friendRepository.findByUser1AndUser2AndAccepted(user2, user1, false).orElseThrow();
        friendService.acceptFriendRequest(myRequest.getId());
        return "redirect:/friends1";
    }

    private MyUser getAuthenticatedUserOrDefault() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (MyUser) principal;
        } else {
            MyUser testUser = new MyUser();
            testUser.setId(6L);
            testUser.setUsername("Test User");
            testUser.setLikedRecipes("");
            testUser.setLikedExercises("");
            return testUser;
        }
    }
}
