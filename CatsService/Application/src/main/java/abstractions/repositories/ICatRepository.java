package abstractions.repositories;

import models.cats.Cat;
import models.operations.OperationResult;
import models.owners.Owner;

import java.util.Date;
import java.util.List;


public interface ICatRepository {
    Cat createCat(String name, Date birthDate, String breed, String color, Owner owner);
    Cat findCatById(Long id);
    OperationResult makeFriendsCats(Cat firstCat, Cat secondCat);
    List<Cat> findCatsByColor(String color);
    List<Cat> getAllCats();
    OperationResult deleteCatById(Long id);
}
