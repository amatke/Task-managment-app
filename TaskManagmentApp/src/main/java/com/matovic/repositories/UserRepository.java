package com.matovic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matovic.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByNameLike(String name);
}
