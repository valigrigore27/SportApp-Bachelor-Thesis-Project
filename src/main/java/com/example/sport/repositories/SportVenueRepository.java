package com.example.sport.repositories;

import com.example.sport.models.SportVenue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportVenueRepository extends JpaRepository <SportVenue, Long> {

    @NotNull
    Optional <SportVenue> findById(Long id);

    List <SportVenue> findBySportVenueName(String name);
}
