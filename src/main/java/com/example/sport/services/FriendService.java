package com.example.sport.services;

import com.example.sport.models.Friend;
import com.example.sport.models.MyUser;
import com.example.sport.repositories.FriendRepository;
import com.example.sport.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MyUserRepository myUserRepository;

    //pentru 2 id-uri de user anume creez o cerere de prietenie
    //pe care o voi salva in baza de date daca nu exista deja
    public void sendFriendRequest(Long userId1, Long userId2){
        MyUser user1 = myUserRepository.findById(userId1).orElseThrow();
        MyUser user2 = myUserRepository.findById(userId2).orElseThrow();
        //creez pur si simplu o cerere de prietenie
        Friend request = new Friend();
        request.setUser1(user1);
        request.setUser2(user2);
        request.setAccepted(false);
        if(!(friendRepository.existsByUser1AndUser2AndAccepted(user1, user2, false))
                && !(friendRepository.existsByUser1AndUser2AndAccepted(user1, user2, true))) {
            friendRepository.save(request);
            System.out.println("Friend request sent");
        }
        else {
            System.out.println("Friend request already exists");
        }
    }

    //pentru o cerere anume, dupa ce al doilea user va apasa pe butonul de accept
    //se va apela functia asta care seteaza cererea la "acceptata" pe
    //partea de backend
    public void acceptFriendRequest(Long requestId){
        Friend request = friendRepository.findById(requestId).orElseThrow();
        request.setAccepted(true);
        friendRepository.save(request);

        //automat daca accept devin si eu prietenul lui
        Friend accept = new Friend();
        MyUser userCopy = request.getUser1();
        accept.setUser1(request.getUser2());
        accept.setUser2(userCopy);
        accept.setAccepted(true);
        friendRepository.save(accept);

    }

    //pentru un anume id al unui user returnez toti prietenii lui
    public List<MyUser> getFriends(Long userId){
        MyUser user = myUserRepository.findById(userId).orElseThrow();
        List<Friend> friends = friendRepository.findByUser1AndAccepted(user, true);
        return friends.stream().map(Friend::getUser2).collect(Collectors.toList());
    }

    //pentru un anume id al unui user returnez pe toti carora le-a dat cerere, dar inca nu a fost acceptata
    public List<MyUser> getPendingFriends(Long userId){
        MyUser user = myUserRepository.findById(userId).orElseThrow();
        List<Friend> friends = friendRepository.findByUser1AndAccepted(user, false);
        return friends.stream().map(Friend::getUser2).collect(Collectors.toList());
    }

    public List<MyUser> getMyFriendRequests(MyUser user){
        List<Friend> friends = friendRepository.findByUser2AndAccepted(user, false);
        return friends.stream().map(Friend::getUser1).collect(Collectors.toList());
    }

}
