package app.owners;

import abstractions.repositories.IOwnerRepository;
import app.services.IModelDtoMapper;
import contracts.owners.IOwnerService;
import models.cats.Cat;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;
import models.owners.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OwnerService implements IOwnerService {
    private final IOwnerRepository ownerRepository;
    private final IModelDtoMapper mapper;

    @Autowired
    public OwnerService(IOwnerRepository ownerRepository, IModelDtoMapper mapper) {
        this.ownerRepository = ownerRepository;
        this.mapper = mapper;
    }

    @Override
    public OwnerDto createOwner(String name, Date birthDate) {
        return mapper.ownerModelToDto(ownerRepository.createOwner(name, birthDate));
    }

    @Override
    public OwnerDto findOwnerById(Long id) {
        return mapper.ownerModelToDto(ownerRepository.findOwnerById(id));
    }

    @Override
    public List<CatDto> getCatListById(Long id) {
        return ownerRepository
                .getCatListById(id)
                .stream()
                .map(mapper::catModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OperationResult becomeOwner(Owner owner, Cat cat) {
        return ownerRepository.becomeOwner(owner, cat);
    }

    @Override
    public OperationResult deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findOwnerById(ownerId);

        return ownerRepository.deleteOwnerById(owner.getId());
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return ownerRepository
                .getAllOwners()
                .stream()
                .map(mapper::ownerModelToDto)
                .collect(Collectors.toList());
    }
}
