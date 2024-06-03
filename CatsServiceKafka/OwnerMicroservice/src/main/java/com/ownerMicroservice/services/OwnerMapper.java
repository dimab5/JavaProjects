package com.ownerMicroservice.services;

import com.entities.owners.Owner;
import com.entities.owners.OwnerDto;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapper {
    public Owner dtoToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();

        owner.setRole(ownerDto.getRole());
        owner.setName(ownerDto.getName());
        owner.setPassword(ownerDto.getPassword());
        owner.setBirthdate(ownerDto.getBirthdate());

        return owner;
    }

    public OwnerDto ownerToDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setName(owner.getName());
        ownerDto.setRole(owner.getRole());
        ownerDto.setPassword(owner.getPassword());
        ownerDto.setBirthdate(owner.getBirthdate());

        return ownerDto;
    }
}
