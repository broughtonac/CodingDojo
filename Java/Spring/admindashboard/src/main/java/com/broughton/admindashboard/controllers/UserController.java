package com.broughton.admindashboard.controllers;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.broughton.admindashboard.services.UserService;
import com.broughton.admindashboard.models.User;

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
	@RequestMapping("/home")
	public String register(
			@ModelAttribute("user") User user,
			HttpSession session) {
		session.setAttribute("id", null);
		return "home.jsp";
	}
	@PostMapping("/register")
	public String create(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("registrationErrors", result.getAllErrors());
			return "redirect:/home";
		}
		else {
			if (userService.emailExists(user) == true) {
				redirectAttributes.addFlashAttribute("emailExistsError", "that email already exists");
				return "redirect:/home";
			}
			if (userService.all().isEmpty()) {
				userService.createAdmin(user);
				session.setAttribute("id", user.getId());
				return "redirect:/admin";
			}
			else {
				userService.create(user);
				session.setAttribute("id", user.getId());
				return "redirect:/dashboard";
			}
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
			return "redirect:/home";
		}
		else {
			if (userService.isMatch(password, user.getPassword())) {
				session.setAttribute("id", user.getId());
				if (user.isAdmin()) {
					return "redirect:/admin";
				}
				else {
					return "redirect:/dashboard";
				}
			}
			else {
				redirectAttributes.addFlashAttribute("loginErrors", "incorrect password");
				return "redirect:/home";
			}
		}
	}
	@RequestMapping("/admin")
	public String renderAdminDashboard(Model model, HttpSession session) {
		Long sessionId = (Long) session.getAttribute("id");
		if (sessionId != null) {
			User thisUser = userService.findById(sessionId);
			model.addAttribute("thisUser", thisUser);
			List<User> users = userService.all();
			model.addAttribute("users", users);
			return "admindashboard.jsp";
		}
		else {
			return "redirect:/home";
		}
	}
	@RequestMapping("/admin/{id}")
	public String makeAdmin(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		userService.createAdmin(user);
		return "redirect:/admin";
	}
	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		userService.destroy(user);
		return "redirect:/admin";
	}
	@RequestMapping("/dashboard")
	public String renderDashboard(Model model, HttpSession session) {
		Long sessionId = (Long) session.getAttribute("id");
		if (sessionId != null) {
			User thisUser = userService.findById(sessionId);
			model.addAttribute("thisUser", thisUser);
			return "dashboard.jsp";
		}
		else {
			return "redirect:/home";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("id", null);
		return "redirect:/home";
	}
}
