package com.example.springsecuritytest.repository;

import com.example.springsecuritytest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
