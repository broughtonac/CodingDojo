package com.broughton.loginandregistration.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.broughton.loginandregistration.models.User;
import com.broughton.loginandregistration.services.UserService;

@Controller
public class UserController {
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/register")
	public String register(
			@ModelAttribute("user") User user,
			HttpSession session) {
		session.setAttribute("id", null);
		return "register.jsp";
	}
	@PostMapping("/register")
	public String create(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("registrationErrors", result.getAllErrors());
			return "redirect:/register";
		}
		else {
			userService.create(user);
			session.setAttribute("id", user.getId());
			return "redirect:/dashboard";
		}
	}
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		User user = userService.findByEmail(email);
		if (user == null) {
			redirectAttributes.addFlashAttribute("loginErrors", "email was blank");
			return "redirect:/register";
		}
		else {
			if (userService.isMatch(password, user.getPassword())) {
				session.setAttribute("id", user.getId());
				return "redirect:/dashboard";
			}
			else {
				redirectAttributes.addFlashAttribute("loginErrors", "incorrect password");
				return "redirect:/register";
			}
		}
	}
	@RequestMapping("/dashboard")
	public String renderDashboard(HttpSession session) {
		if (session.getAttribute("id") != null) {
			return "dashboard.jsp";
		}
		else {
			return "redirect:/register";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("id", null);
		return "redirect:/register";
	}
}
