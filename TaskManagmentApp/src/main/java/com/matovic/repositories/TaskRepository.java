package com.matovic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matovic.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
