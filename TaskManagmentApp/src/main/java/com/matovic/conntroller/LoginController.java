package com.matovic.conntroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	public String showloginForm(Model model) {
		
		return "views/loginForm";
	}
}
