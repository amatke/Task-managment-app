package com.matovic.conntroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.matovic.entities.User;
import com.matovic.repositories.UserRepository;
import com.matovic.services.UserService;

@Controller
public class RegisterController {
		
	@Autowired
	public UserService userService;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		
		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	
	@PostMapping("/register")
	public String register(User user, Model model, BindingResult bindingResult) {
		System.out.println(user.getEmail() + user.getPassword() );
		model.addAttribute("exist", false);
		
		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if (userService.isPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "views/registerForm";
		}
		
		userService.createUser(user);
		return "views/loginForm";
	}
}
