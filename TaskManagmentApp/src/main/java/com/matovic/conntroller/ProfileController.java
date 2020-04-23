package com.matovic.conntroller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.matovic.entities.User;
import com.matovic.services.TaskService;
import com.matovic.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal, HttpSession session) {
		
		String email = principal.getName();
		User user = userService.findOne(email);
		
		model.addAttribute("tasks", taskService.findUserTask(user));
		session.setAttribute("User", user);
		session.setAttribute("btnLogOut", true); 
		 
		return "views/profile";
	}
}
