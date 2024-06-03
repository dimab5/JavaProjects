package com.entities.owners;

import com.entities.cats.Cat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "owners")
@EqualsAndHashCode
@Data
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Date birthdate;

    private String password;
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats = new ArrayList<>();
}

