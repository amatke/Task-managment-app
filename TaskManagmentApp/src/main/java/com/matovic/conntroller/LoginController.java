package com.matovic.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
						
		if ( u != null && encoder.matches(user.getPassword(), u.getPassword())) {
			System.out.println("Ulogovan!");
			model.addAttribute("user", u);
			return "views/profile";
		}
		model.addAttribute("wrongPass", true);
		return "views/loginForm";
	}
}
