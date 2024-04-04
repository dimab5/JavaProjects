package abstractions.repositories;

import models.cats.Cat;
import models.operations.OperationResult;
import models.owners.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;


public interface IOwnerRepository {
    Owner createOwner(String name, Date birthDate);
    Owner findOwnerById(Long id);
    List<Owner> getAllOwners();
    OperationResult becomeOwner(Owner owner, Cat cat);
    OperationResult deleteOwnerById(Long id);
    List<Cat> getCatListById(Long id);
}
