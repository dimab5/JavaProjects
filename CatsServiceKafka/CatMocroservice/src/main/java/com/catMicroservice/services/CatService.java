package com.catMicroservice.services;

import com.catMicroservice.repositories.CatRepository;
import com.entities.cats.Cat;
import com.entities.cats.CatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatService implements ICatService {
    private final CatRepository catRepository;
    private final CatMapper catMapper;

    @Override
    public CatDto createCat(CatDto catDto) {
        catRepository.save(catMapper.dtoToCat(catDto));

        return catDto;
    }

    @Override
    public void makeFriendsCats(Long firstCatId, Long secondCatId) {
        Cat firstCat = catRepository.findCatById(firstCatId);
        Cat secondCat = catRepository.findCatById(secondCatId);

        firstCat.addFriend(secondCat);
        secondCat.addFriend(firstCat);

        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }

    @Override
    public List<CatDto> getAllCats() {
        return catRepository
                .findAll()
                .stream()
                .map(catMapper::catToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CatDto findCatById(Long id) {
        return catMapper.catToDto(catRepository.findCatById(id));
    }

    @Override
    public List<CatDto> findCatsByColor(String color) {
        return catRepository
                .findCatsByColor(color)
                .stream()
                .map(catMapper::catToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCat(Long catId) {
        Cat cat = catRepository.findCatById(catId);
        catRepository.delete(cat);
    }

    @Override
    public List<CatDto> getCatListById(Long id) {
        return catRepository
                .findCatsByOwnerId(id)
                .stream()
                .map(catMapper::catToDto)
                .collect(Collectors.toList());
    }
}
