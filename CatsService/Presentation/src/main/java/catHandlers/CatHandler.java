package catHandlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import contracts.cats.ICatService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import models.cats.CatColor;
import models.cats.CatDto;
import models.operations.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab3/cats")
@RequiredArgsConstructor
public class CatHandler {
    private final ICatService catService;

    @GetMapping
    public ResponseEntity<List<CatDto>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatDto> getCatById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findCatById(id));
    }

    @SneakyThrows
    @PostMapping("/createCat")
    public ResponseEntity<CatDto> createCat(@RequestBody String catData) {
        ObjectMapper objectMapper = new ObjectMapper();
        CatDto catDto = objectMapper.readValue(catData, CatDto.class);

        return ResponseEntity.ok(catService.createCat(
                catDto.getName(),
                catDto.getBirthDate(),
                catDto.getBreed(),
                catDto.getColor(),
                catDto.getOwnerId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult> deleteCatById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.deleteCat(id));
    }

    @GetMapping("/getByColor/{color}")
    public ResponseEntity<List<CatDto>> getCatsByColor(@PathVariable CatColor color) {
        return ResponseEntity.ok(catService.findCatsByColor(color));
    }

    @PostMapping("/makeFriends/{firstCatId}/{secondCatId}")
    public ResponseEntity<OperationResult> makeFriends(
            @PathVariable Long firstCatId,
            @PathVariable Long secondCatId) {
        return ResponseEntity.ok(catService.makeFriendsCats(firstCatId, secondCatId));
    }
}
