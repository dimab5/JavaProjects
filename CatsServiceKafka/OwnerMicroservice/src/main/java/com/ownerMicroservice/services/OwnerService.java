package com.ownerMicroservice.services;

import com.entities.owners.Owner;
import com.entities.owners.OwnerDto;
import com.ownerMicroservice.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService implements IOwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {
        ownerRepository.save(ownerMapper.dtoToOwner(ownerDto));

        return ownerDto;
    }

    @Override
    public OwnerDto findOwnerById(Long id) {
        return ownerMapper.ownerToDto(ownerRepository.findOwnerById(id));
    }

    @Override
    public void deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);
        ownerRepository.delete(owner);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerRepository
                .findAll()
                .stream()
                .map(ownerMapper::ownerToDto)
                .collect(Collectors.toList());

    }
}
