package contracts.owners;

import models.cats.Cat;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;
import models.owners.OwnerDto;

import java.util.Date;
import java.util.List;

public interface IOwnerService {
    OwnerDto createOwner(String name, Date birthDate);
    OwnerDto findOwnerById(Long id);
    List<CatDto> getCatListById(Long id);
    OperationResult becomeOwner(Owner owner, Cat cat);
    OperationResult deleteOwner(Long ownerId);
    List<OwnerDto> getAllOwners();
}
