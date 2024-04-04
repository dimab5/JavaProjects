package ownerHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import contracts.owners.IOwnerService;
import lombok.SneakyThrows;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab3/owners")
public class OwnerHandler {
    private final IOwnerService ownerService;

    @Autowired
    public OwnerHandler(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findOwnerById(id));
    }

    @GetMapping("catList/{id}")
    public ResponseEntity<List<CatDto>> getOwnerCats(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getCatListById(id));
    }

    @SneakyThrows
    @PostMapping("/createOwner")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody String ownerData) {
        ObjectMapper objectMapper = new ObjectMapper();
        OwnerDto ownerDto = objectMapper.readValue(ownerData, OwnerDto.class);

        return ResponseEntity.ok(ownerService.createOwner(
                ownerDto.getName(),
                ownerDto.getBirthDate()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationResult> deleteOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.deleteOwner(id));
    }
}