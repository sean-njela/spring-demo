package com.devopssean.spring_demo.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "street")
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
