package com.matovic.conntroller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.matovic.entities.Task;
import com.matovic.entities.User;
import com.matovic.services.TaskService;
import com.matovic.services.UserService;

@Controller
public class TaskController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private TaskService taskService;
	
	/*
	 * @GetMapping("/users") public String showIndexPage(Model model) {
	 * 
	 * model.addAttribute("users", userService.findAll()); return "views/users"; }
	 */
	
	@GetMapping("/users")
	public String searchUser(Model model, @RequestParam(defaultValue="")  String searchName) {
		model.addAttribute("users", userService.findByName(searchName));
		return "views/users";
	}
	
	
	@GetMapping("/addTask")
	public String taskForm(String email, Model model, HttpSession session) {
		
		session.setAttribute("email", email);
		model.addAttribute("task", new Task());
		return "views/taskForm";
	}
	
	
	@PostMapping("/addTask")
	public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
		
		if(bindingResult.hasErrors()) {
			System.err.println("Task greske");
			return "views/taskForm";
		}
		
		String email = (String) session.getAttribute("email");
		User user = userService.findOne(email);
		
		taskService.addTask(task, user);
		
		return "views/users";
	}
}
