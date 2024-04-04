package app.services;

import models.cats.Cat;
import models.cats.CatDto;
import models.owners.Owner;
import models.owners.OwnerDto;

public interface IModelDtoMapper {
    public CatDto catModelToDto(Cat cat);
    public OwnerDto ownerModelToDto(Owner owner);
}
