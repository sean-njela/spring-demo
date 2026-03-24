package com.devopssean.spring_demo.repositories;

import com.devopssean.spring_demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


//We mainly use JPARepository(more complex work) or CrudRepository (default choice)
// Args:
    // 1 - Entity we are creating this for
    // 2 - Data type of the primary key ID
public interface UserRepository extends CrudRepository<User, Long> {

}
