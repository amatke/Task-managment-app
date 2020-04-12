package com.matovic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matovic.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
