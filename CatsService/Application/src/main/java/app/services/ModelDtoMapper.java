package app.services;

import models.cats.Cat;
import models.cats.CatDto;
import models.owners.Owner;
import models.owners.OwnerDto;
import org.springframework.stereotype.Component;

@Component
public class ModelDtoMapper implements IModelDtoMapper {
    public OwnerDto ownerModelToDto(Owner owner) {
        OwnerDto dto = new OwnerDto();

        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setBirthDate(owner.getBirthDate());

        return dto;
    }

    public CatDto catModelToDto(Cat cat) {
        CatDto dto = new CatDto();

        dto.setId(cat.getId());
        dto.setName(cat.getName());
        dto.setColor(cat.getColor());
        dto.setBirthDate(cat.getBirthDate());
        dto.setBreed(cat.getBreed());
        dto.setOwnerId(cat.getOwner().getId());

        return dto;
    }
}
