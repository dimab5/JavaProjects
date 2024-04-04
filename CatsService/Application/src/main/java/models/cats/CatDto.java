package models.cats;

import lombok.Data;
import models.owners.Owner;

import java.util.Date;

@Data
public class CatDto {
    private Long id;
    private String name;
    private Date birthDate;
    private String breed;
    private CatColor color;
    private Long ownerId;
}
