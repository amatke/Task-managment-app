package com.matovic.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matovic.entities.Task;
import com.matovic.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	Set<Task> findByUser(User user);

}
