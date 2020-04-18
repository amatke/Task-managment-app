package com.matovic.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.matovic.entities.User;
import com.matovic.repositories.UserRepository;

@Controller
public class RegisterController {
	
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		
		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	
	@PostMapping("/register")
	public String register(User user, Model model) {
		
		System.out.println(user.getEmail() + user.getPassword() );
		userRepository.save(user);
		return "views/loginForm";
	}
}
