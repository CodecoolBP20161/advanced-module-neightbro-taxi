package com.codecool.neighbrotaxi.repository;

import com.codecool.neighbrotaxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Interface for handling user table.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByUsername(String userName);
}
