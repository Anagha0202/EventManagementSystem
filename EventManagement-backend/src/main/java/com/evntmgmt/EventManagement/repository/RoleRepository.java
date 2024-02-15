package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.ERole;
import com.evntmgmt.EventManagement.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
