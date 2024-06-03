package com.catMicroservice.repositories;

import com.entities.cats.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    Cat findCatById(Long id);
    Cat findCatByIdAndOwnerName(Long id, String ownerName);
    List<Cat> findCatsByColor(String color);
    List<Cat> findCatsByColorAndOwnerName(String color, String ownerName);
    List<Cat> findCatsByOwnerId(Long id);
    List<Cat> findCatsByOwnerName(String name);
}
