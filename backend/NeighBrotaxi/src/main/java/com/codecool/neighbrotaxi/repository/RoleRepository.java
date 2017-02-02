package com.codecool.neighbrotaxi.repository;

import com.codecool.neighbrotaxi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by cave on 2017.02.02..
 */
@RepositoryRestResource(collectionResourceRel = "user-roles", path = "user-roles")
public interface RoleRepository extends JpaRepository<Role, String>{
}
