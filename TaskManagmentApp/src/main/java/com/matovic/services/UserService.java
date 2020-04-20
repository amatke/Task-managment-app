package com.matovic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public void createAdmin(User user) {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);   // CascadeType.Persist nad private List<Role> roles omogucava implicitno cuvanje roles
	}
	
	public User findOne(String email) {
	  return userRepository.findById(email).get();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
		return  userRepository.findByNameLike("%"+name+"%");
	}

	public boolean isPresent(String email){
		Optional<User> u=userRepository.findById(email);
		if(u.isPresent()) {
			System.out.println("User vec postoji " + u );
			return true;
		}
		return false;
	}

}