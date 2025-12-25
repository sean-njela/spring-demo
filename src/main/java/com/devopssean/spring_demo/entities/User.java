package com.devopssean.spring_demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// It's not mandatory to specify the nullable directive in @Column calls
// because we are using the DB first design approach,
// where we design the DB first then write the models.
// So the nullable directives are specified in the DB schema.
// The model first approach is more cluttered as we will need to
// define a lot of directives alongside the DB fields.
// DB first => Right click > New > JPA Entities from DB
// Model first => Write models + directives > Mouse hover > Create flyway diff changes
// or right click on db.migration directory > new > flyway migration
// Use one technique, if writing models is easier to describe entitites, do so then
// apply the flyway migration then cleanup the directives.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users") // The table name in our DB
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    // mappedBy is the owner of the relationship (the private variable name in the Entity class of the table where the relationship is explicitly stated)
    // Since we only stated the foreign key relationship in addresses table, addresses.user becomes the owner
    @Builder.Default
    @ToString.Exclude // Because builder doesn't initialize lists and other objects, causing errors on runtime
    private List<Address> addresses = new ArrayList<Address>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            // The annotation is specific to a many-to-many relationship
            // It is used instead of the @JoinColumn which is for one to many
            name = "user_tags", // Table name
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key column
            inverseJoinColumns = @JoinColumn(name = "tag_id") // Other Foreign key column
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName) {
        var tag = new Tag(tagName);
        tags.add(tag); // Now user knows about the tag
        tag.getUsers().add(this);// Now tag knows about the user
    }

    public void addProfile(Profile profile) {
        this.setProfile(profile);
        profile.setUser(this);
    }
}
