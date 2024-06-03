package com.catMicroservice.services;

import com.entities.cats.CatDto;

import java.util.List;

public interface ICatService {
    CatDto createCat(CatDto catDto);
    void makeFriendsCats(Long firstCatId, Long secondCatId);
    List<CatDto> getAllCats();
    CatDto findCatById(Long id);
    List<CatDto> findCatsByColor(String color);
    void deleteCat(Long catId);
    List<CatDto> getCatListById(Long id);
}
