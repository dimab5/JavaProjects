package models.cats;

import lombok.Data;
import lombok.EqualsAndHashCode;
import models.owners.Owner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cats")
@EqualsAndHashCode
@Data
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private String breed;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Owner owner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "first_id"),
            inverseJoinColumns = @JoinColumn(name = "second_id"))
    private List<Cat> friends = new ArrayList<>();

    public void addFriend(Cat cat) {
        friends.add(cat);
    }
}
