package com.matovic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matovic.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
