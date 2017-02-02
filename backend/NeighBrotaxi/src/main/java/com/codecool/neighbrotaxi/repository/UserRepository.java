package com.codecool.neighbrotaxi.repository;

import com.codecool.neighbrotaxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Interface for handling user table.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
