package contracts.cats;

import models.cats.Cat;
import models.cats.CatColor;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.Owner;

import java.util.Date;
import java.util.List;

public interface ICatService {
    CatDto createCat(String name, Date birthDate, String breed, CatColor color, Long ownerId);
    OperationResult makeFriendsCats(Long firstCatId, Long secondCatId);
    List<CatDto> getAllCats();
    CatDto findCatById(Long id);
    List<CatDto> findCatsByColor(CatColor color);
    OperationResult deleteCat(Long catId);
}
