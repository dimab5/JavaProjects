package ownerControllers;

import contracts.owners.IOwnerService;
import lombok.AllArgsConstructor;
import models.cats.CatDto;
import models.operations.OperationResult;
import models.owners.OwnerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lab3/owners")
public class OwnerController {
    private final IOwnerService ownerService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findOwnerById(id));
    }

    @GetMapping("catList/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<CatDto>> getOwnerCats(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getCatListById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto owner) {
        return ResponseEntity.ok(ownerService.createOwner(owner));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OperationResult> deleteOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.deleteOwner(id));
    }
}