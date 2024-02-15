package com.evntmgmt.EventManagement.repository;

import com.evntmgmt.EventManagement.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, String> {
    Optional<Users> findByUserName(String username);
    Boolean existsbyUsername(String username);
    Boolean existsByEmail(String email);
}
