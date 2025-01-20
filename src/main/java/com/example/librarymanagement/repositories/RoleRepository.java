package com.example.librarymanagement.repositories;

import com.example.librarymanagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByName(String name);
}
