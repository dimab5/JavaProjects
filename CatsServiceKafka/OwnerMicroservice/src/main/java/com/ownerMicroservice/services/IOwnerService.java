package com.ownerMicroservice.services;

import com.entities.owners.OwnerDto;

import java.util.List;

public interface IOwnerService {
    OwnerDto createOwner(OwnerDto ownerDto);
    OwnerDto findOwnerById(Long id);
    void deleteOwner(Long ownerId);
    List<OwnerDto> getAllOwners();
}
