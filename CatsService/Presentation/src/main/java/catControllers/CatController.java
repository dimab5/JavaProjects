package catControllers;


import contracts.cats.ICatService;
import lombok.AllArgsConstructor;
import models.cats.CatDto;
import models.operations.OperationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab3/cats")
@AllArgsConstructor
public class CatController {
    private ICatService catService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<List<CatDto>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<CatDto> getCatById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.findCatById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto) {
        return ResponseEntity.ok(catService.createCat(
                catDto.getName(),
                catDto.getBirthDate(),
                catDto.getBreed(),
                catDto.getColor(),
                catDto.getOwnerId()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<OperationResult> deleteCatById(@PathVariable Long id) {
        return ResponseEntity.ok(catService.deleteCat(id));
    }

    @GetMapping("/getByColor/{color}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<List<CatDto>> getCatsByColor(@PathVariable String color) {
        return ResponseEntity.ok(catService.findCatsByColor(color));
    }

    @PostMapping("/makeFriends/{firstCatId}/{secondCatId}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<OperationResult> makeFriends(
            @PathVariable Long firstCatId,
            @PathVariable Long secondCatId) {
        return ResponseEntity.ok(catService.makeFriendsCats(firstCatId, secondCatId));
    }
}
