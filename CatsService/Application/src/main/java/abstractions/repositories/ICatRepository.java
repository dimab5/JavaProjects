package abstractions.repositories;

import models.cats.Cat;
import models.cats.CatColor;
import models.operations.OperationResult;
import models.owners.Owner;

import java.util.Date;
import java.util.List;


public interface ICatRepository {
    Cat createCat(String name, Date birthDate, String breed, CatColor color, Owner owner);
    Cat findCatById(Long id);
    OperationResult makeFriendsCats(Cat firstCat, Cat secondCat);
    List<Cat> findCatsByColor(CatColor color);
    List<Cat> getAllCats();
    OperationResult deleteCatById(Long id);
}
