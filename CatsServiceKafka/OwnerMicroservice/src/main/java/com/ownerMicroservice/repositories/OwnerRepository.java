package com.ownerMicroservice.repositories;

import com.entities.owners.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findOwnerById(Long id);
    Owner findOwnerByName(String name);
}
