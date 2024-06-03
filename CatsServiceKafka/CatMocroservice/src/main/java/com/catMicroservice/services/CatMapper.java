package com.catMicroservice.services;

import com.catMicroservice.repositories.OwnerRepository;
import com.entities.cats.Cat;
import com.entities.cats.CatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatMapper {
    private final OwnerRepository ownerRepository;

    public Cat dtoToCat(CatDto catDto) {
        Cat cat = new Cat();

        cat.setName(catDto.getName());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwner(ownerRepository.findOwnerById(catDto.getOwnerId()));
        cat.setBirthdate(catDto.getBirthdate());

        return cat;
    }

    public CatDto catToDto(Cat cat) {
        CatDto catDto = new CatDto();

        catDto.setName(cat.getName());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        catDto.setBirthdate(cat.getBirthdate());
        catDto.setOwnerId(cat.getOwner().getId());

        return catDto;
    }
}
