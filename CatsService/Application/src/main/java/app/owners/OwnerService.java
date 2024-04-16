package app.owners;

import abstractions.repositories.IOwnerRepository;
import app.services.IModelDtoMapper;
import contracts.owners.IOwnerService;
import lombok.AllArgsConstructor;
import models.cats.Cat;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;
import models.owners.OwnerDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OwnerService implements IOwnerService {
    private IOwnerRepository ownerRepository;
    private IModelDtoMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public OwnerDto createOwner(OwnerDto owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        return mapper.ownerModelToDto(ownerRepository.createOwner(
                owner.getName(),
                owner.getBirthDate(),
                owner.getPassword(),
                owner.getRole()));
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
