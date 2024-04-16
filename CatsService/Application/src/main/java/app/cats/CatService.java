package app.cats;

import abstractions.repositories.ICatRepository;
import abstractions.repositories.IOwnerRepository;
import app.services.IModelDtoMapper;
import contracts.cats.ICatService;
import lombok.AllArgsConstructor;
import models.cats.Cat;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;
import models.owners.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CatService implements ICatService {
    private final ICatRepository catRepository;
    private final IOwnerRepository ownerRepository;
    private final IModelDtoMapper mapper;

    @Override
    public CatDto createCat(String name, Date birthDate, String breed, String color, Long ownerId) {
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = ownerRepository.findOwnerByName(authentication.getName());

        return catRepository
                .getAllCats()
                .stream()
                .filter(cat -> owner.getRole() == "admin" || cat.getOwner().getName().equals(authentication.getName()))
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CatDto findCatById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cat cat = catRepository.findCatById(id);
        Owner owner = ownerRepository.findOwnerByName(authentication.getName());

        return (cat != null && (owner.getRole() == "admin" || cat.getOwner().getName().equals(authentication.getName())))
                ? mapper.catModelToDto(cat) : null;
    }

    @Override
    public List<CatDto> findCatsByColor(String color) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = ownerRepository.findOwnerByName(authentication.getName());

        return catRepository
                .findCatsByColor(color)
                .stream()
                .filter(cat -> owner.getRole() == "admin" || cat.getOwner().getName().equals(authentication.getName()))
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResult deleteCat(Long catId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cat cat = catRepository.findCatById(catId);
        Owner owner = ownerRepository.findOwnerByName(authentication.getName());

        if (cat != null && (owner.getRole() == "admin" || cat.getOwner().getName().equals(authentication.getName()))) {
            return catRepository.deleteCatById(cat.getId());
        } else {
            return OperationResult.FAIL;
        }
    }
}
