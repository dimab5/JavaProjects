package com.apiMicroservice.controllers;

import com.apiMicroservice.kafka.KafkaConsumer;
import com.apiMicroservice.kafka.KafkaProducer;
import com.entities.cats.CatDto;
import com.entities.owners.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("lab5/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final KafkaConsumer kafkaConsumer;
    private final KafkaProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;

    /*{
            "name": "dima",
            "birthdate": "2004-12-12",
            "password": "123",
            "role": "admin"
    }*/
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        kafkaProducer.createOwner(owner);

        return ResponseEntity.ok(owner);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        kafkaProducer.getAllOwners();
        Optional<List<OwnerDto>> ownersDto;
        try {
            ownersDto = kafkaConsumer.getAllOwners().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }
        return ResponseEntity.ok(ownersDto.get());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        kafkaProducer.getOwnerById(id);
        Optional<OwnerDto> ownerDto;
        try {
            ownerDto = kafkaConsumer.getOwnerById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(ownerDto.get());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OwnerDto> deleteOwnerById(@PathVariable Long id) {
        kafkaProducer.deleteOwnerById(id);
        Optional<OwnerDto> ownerDto;
        try {
            ownerDto = kafkaConsumer.deleteOwnerById().get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Failed to retrieve owner information", e);
        }

        return ResponseEntity.ok(ownerDto.get());
    }
}
