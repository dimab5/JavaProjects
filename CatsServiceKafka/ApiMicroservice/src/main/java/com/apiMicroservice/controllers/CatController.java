package com.apiMicroservice.controllers;

import com.apiMicroservice.kafka.KafkaConsumer;
import com.apiMicroservice.kafka.KafkaProducer;
import com.entities.cats.CatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/lab5/cats")
@RequiredArgsConstructor
public class CatController {
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;

    @GetMapping
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<List<CatDto>> getAllCats(Principal principal) {
        kafkaProducer.getAllCats(principal.getName());
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getAllCats().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(catDtos.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<CatDto> getCatById(@PathVariable Long id) {
        kafkaProducer.getCatById(id);
        Optional<CatDto> catDto;
        try {
            catDto = kafkaConsumer.getCatById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(catDto.get());
    }

    /*
    {
     "name": "catName",
     "birthdate": "2022-12-12",
     "breed": "breed",
     "color": "color",
     "ownerId": 7
    }
    */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto) {
        kafkaProducer.createCat(catDto);

        return ResponseEntity.ok(catDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<CatDto> deleteCatById(@PathVariable Long id) {
        kafkaProducer.deleteCatById(id);
        Optional<CatDto> catDto;
        try {
            catDto = kafkaConsumer.deleteCatById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(catDto.get());
    }

    @GetMapping("getByColor/{color}")
    @PreAuthorize("hasAuthority('admin') || hasAuthority('user')")
    public ResponseEntity<List<CatDto>> getCatsByColor(@PathVariable String color) {
        kafkaProducer.getCatsByColor(color);
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getCatsByColor().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(catDtos.get());
    }

    @GetMapping("catList/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<CatDto>> getOwnerCats(@PathVariable Long id) {
        kafkaProducer.getOwnerCats(id);
        Optional<List<CatDto>> catDtos;
        try {
            catDtos = kafkaConsumer.getOwnerCats().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(catDtos.get());
    }
}
