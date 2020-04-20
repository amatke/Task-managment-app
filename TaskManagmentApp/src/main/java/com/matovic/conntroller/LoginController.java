package com.matovic.conntroller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matovic.entities.User;
import com.matovic.repositories.UserRepository;
import com.matovic.services.TaskService;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/login")
	public String loginForm(Model model) {

		model.addAttribute("user", new User());
		return "views/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Valid User user, BindingResult bindingResult, Model model, HttpSession session, RedirectAttributes redirAttr) {
			
		if(bindingResult.hasErrors()) {
			for (ObjectError e : bindingResult.getFieldErrors()) {
				if(!e.getCodes()[0].endsWith("name")) {
					return "views/loginForm";
				}
			}
		}
		
		Optional<User> u = userRepository.findById(user.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
						
		if ( u.isPresent()  && encoder.matches(user.getPassword(), u.get().getPassword())) {
			System.out.println("Ulogovan!");
			model.addAttribute("user", u.get());
			session.setAttribute("User", u.get());
	        session.setAttribute("btnLogOut", true);
			//redirAttr.addFlashAttribute("btnLogOut", true);
			return "views/profile";
		}
		model.addAttribute("wrongPass", true);
		return "views/loginForm";
	}
	
	
	
	@GetMapping("/logOut")
	public String loginOut(HttpSession session) {
		session.removeAttribute("btnLogOut");
		session.removeAttribute("User");
		return "index";
	}
	
	
	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		User user = (User) session.getAttribute("User");
		model.addAttribute("tasks", taskService.findUserTask(user));
		return "views/profile";
	}
}
