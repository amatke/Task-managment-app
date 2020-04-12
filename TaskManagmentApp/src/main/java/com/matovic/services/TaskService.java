package com.matovic.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matovic.entities.Task;
import com.matovic.entities.User;
import com.matovic.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public Set<Task> findUserTask(User user){
		return taskRepository.findByUser(user);
	}
}
