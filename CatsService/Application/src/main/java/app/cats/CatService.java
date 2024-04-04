package app.cats;

import abstractions.repositories.ICatRepository;
import abstractions.repositories.IOwnerRepository;
import app.services.IModelDtoMapper;
import contracts.cats.ICatService;
import models.cats.Cat;
import models.cats.CatColor;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CatService implements ICatService {
    private final ICatRepository catRepository;
    private final IOwnerRepository ownerRepository;
    private final IModelDtoMapper mapper;

    @Autowired
    public CatService(
            IModelDtoMapper mapper,
            IOwnerRepository ownerRepository,
            ICatRepository catRepository) {
        this.mapper = mapper;
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public CatDto createCat(String name, Date birthDate, String breed, CatColor color, Long ownerId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);

        return mapper.catModelToDto(catRepository.createCat(name, birthDate, breed, color, owner));
    }

    @Override
    public OperationResult makeFriendsCats(Long firstCatId, Long secondCatId) {
        Cat firstCat = catRepository.findCatById(firstCatId);
        Cat secondCat = catRepository.findCatById(secondCatId);

        return catRepository.makeFriendsCats(firstCat, secondCat);
    }

    @Override
    public List<CatDto> getAllCats() {
        return catRepository
                .getAllCats()
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CatDto findCatById(Long id) {
        return mapper.catModelToDto(catRepository.findCatById(id));
    }

    @Override
    public List<CatDto> findCatsByColor(CatColor color) {
        return catRepository
                .findCatsByColor(color)
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResult deleteCat(Long catId) {
        Cat cat = catRepository.findCatById(catId);

        return catRepository.deleteCatById(cat.getId());
    }
}
