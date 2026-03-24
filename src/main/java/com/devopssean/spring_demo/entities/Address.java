package com.devopssean.spring_demo.entities;

import jakarta.persistence.*;
import lombok.*;

// With DBs we have:
//      Spring Data JPA - Repositories, like UserRepository.java
//      JPA/Hibernate - ORM - like this file / Category.java
//      JDBC - Low level, have to open and close connections
// The higher you go, the more teh abstraction.


// Use the annotations and the builder pattern only where necessary.
// It should not be a default.
// Use the builder pattern if your entity object has a lot of optional fields
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street") // Avoids us having to match the name in here to the one in the DB
    // DO NOT CHANGE column names in the DB because other apps might depend on them use @Column(name="")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // @Joincolumn instead of @Column because it is a foreign key field
    // @JoinColumn signifies that this is the owner of the relationship
    @ToString.Exclude // To avoid stack overflow errors due to loop when reading addresses
    private User user;
}
