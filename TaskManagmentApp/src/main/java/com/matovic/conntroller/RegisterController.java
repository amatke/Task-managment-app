package com.matovic.conntroller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.matovic.entities.User;
import com.matovic.services.UserService;

@Controller
public class RegisterController {

	@Autowired
	public UserService userService;
	
	@GetMapping("/registerF")
	public String registerForm(Model model, HttpSession session) {
				
		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	
	@PostMapping("/REGISTER")
	public String register(@Valid User user, BindingResult bindingResult, Model model) {		//BindingResult mora da bude odmah posle bean-a user

		//model.addAttribute("exist", false);
		
		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if (userService.isPresent(user.getEmail())) {
			model.addAttribute("exist", true);
			return "views/registerForm";
		}
		
		model.addAttribute("created", true);
		model.addAttribute("user", new User());
		userService.createUser(user);
		return "views/loginForm";
	}
}
