package com.matovic.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.matovic.entities.User;
import com.matovic.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userservice;

	@GetMapping("/login")
	public String loginForm(Model model) {
		
		model.addAttribute("user", new User());
		return "views/loginForm";
	}
	
	@PostMapping("/login")
	public String login(User user, Model model) {
		User u = userservice.findOne(user.getEmail()); 
		if ( u != null && u.getPassword().equals(user.getPassword())) {
			System.out.println("Ulogovan!");
			return "views/profile";
		}
		return null;
	}
}
