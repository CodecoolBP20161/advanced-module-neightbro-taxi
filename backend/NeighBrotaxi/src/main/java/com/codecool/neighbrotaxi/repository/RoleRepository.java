package com.codecool.neighbrotaxi.repository;

import com.codecool.neighbrotaxi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Interface for handling role table.
 */
@RepositoryRestResource(collectionResourceRel = "userRoles", path = "user-roles")
public interface RoleRepository extends JpaRepository<Role, String>{
}
