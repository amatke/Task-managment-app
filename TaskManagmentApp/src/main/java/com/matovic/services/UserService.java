package com.matovic.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matovic.entities.Role;
import com.matovic.entities.User;
import com.matovic.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role("USER"));
		user.setRoles(roles);
		
		userRepository.save(user);		// CascadeType.Persist nad private Set<Role> roles omogucava implicitno cuvanje roles
	}
	
	public void createAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role("ADMIN"));
		user.setRoles(roles);
		
		userRepository.save(user);		// CascadeType.Persist nad private Set<Role> roles omogucava implicitno cuvanje roles
	}
	
	public User findOne(User user) {
		return userRepository.findById(user.getEmail()).get();
	}
}
