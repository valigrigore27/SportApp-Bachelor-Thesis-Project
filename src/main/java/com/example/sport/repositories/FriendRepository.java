package com.example.sport.repositories;

import com.example.sport.models.Friend;
import com.example.sport.models.MyUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByUser1AndAccepted(MyUser user1, boolean accepted);
    List<Friend> findByUser2AndAccepted(MyUser user2, boolean accepted);

    Optional<Friend> findByUser1AndUser2AndAccepted(MyUser user1, MyUser user2, boolean accepted);

    void delete(@NotNull Friend friendRequest);

    @Override
    boolean existsById(Long id);
    boolean existsByUser1AndUser2AndAccepted(MyUser user1, MyUser user2, boolean accepted);

}
