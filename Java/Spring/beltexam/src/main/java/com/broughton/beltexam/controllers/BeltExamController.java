package com.broughton.beltexam.controllers;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.broughton.beltexam.models.*;
import com.broughton.beltexam.services.BeltExamService;

@Controller
public class BeltExamController {
	private BeltExamService service;
	public BeltExamController(BeltExamService service) {
		this.service = service;
	}
	// LOGIN AND REGISTRATION CONTROLS
	@RequestMapping("/")
	public String index(
			Model model,
			HttpSession session) {
		session.setAttribute("id", null);
		model.addAttribute("user", new User());
		session.setAttribute("id", null);
		return "index.jsp"; // index.jsp = login and registration page
	}
	@PostMapping("/register")
	public String createUser(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("registrationErrors", result.getAllErrors());
			return "redirect:/";
		}
		if (service.emailExists(user.getEmail())) {
			redirectAttributes.addFlashAttribute("emailExistsError", "that email already exists");
			return "reidrect:/";
		}
		if (service.findAllUsers().isEmpty()) {
			service.createAdmin(user);
			session.setAttribute("id", user.getId());
			return "redirect:/admin";
		}
		service.createUser(user);
		session.setAttribute("id", user.getId());
		return "redirect:/selection";
	}
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		User user = service.findByEmail(email);
		if (user == null) {
			redirectAttributes.addFlashAttribute("loginErrors", "email not found");
			return "redirect:/";
		}
		if (service.isMatch(password, user.getPassword())) {
			session.setAttribute("id", user.getId());
			if (user.isAdmin()) {
				return "redirect:/admin";
			}
			if (user.getPlan() == null) {
				return "redirect:/selection";
			}
			return "redirect:/profile";
		}
		redirectAttributes.addFlashAttribute("loginErrors", "incorrect password");
		return "redirect:/";
	}
	// ADMIN CONTROLS
	@RequestMapping("/admin")
	public String renderDashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		User thisAdmin = service.findUserById(id);
		List<User> customers = service.findAllUsers();
		customers.remove(thisAdmin);
		model.addAttribute("customers", customers);
		model.addAttribute("plans", service.findAllPlans());
		model.addAttribute("plan", new Plan());
		return "dashboard.jsp";
	}
	@PostMapping("/plans/create")
	public String createPlan(
			@Valid @ModelAttribute("plan") Plan plan,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("createPlanErrors", result.getAllErrors());
		}
		else {
			service.createPlan(plan);
		}
		return "redirect:/admin";
	}
	@RequestMapping("/plans/activate/{id}")
	public String activate(@PathVariable("id") Long id) {
		service.activate(service.findPlanById(id));
		return "redirect:/admin";
	}
	@RequestMapping("/plans/deactivate/{id}")
	public String deactivate(@PathVariable("id") Long id) {
		service.deactivate(service.findPlanById(id));
		return "redirect:/admin";
	}
	@RequestMapping("/plans/delete/{id}")
	public String deletePlan(@PathVariable("id") Long id) {
		service.destroyPlan(service.findPlanById(id));
		return "redirect:/admin";
	}
	// CUSTOMER CONTROLS
	@RequestMapping("/selection")
	public String renderSelection(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(id);
		model.addAttribute("thisUser", thisUser);
		model.addAttribute("plans", service.findActivePlans());
		model.addAttribute("days", service.getDays());
		return "selection.jsp";
	}
	@PostMapping("plans/bind")
	public String bindPlan(
			@RequestParam("dueDay") Integer dueDay,
			@RequestParam("planId") Long planId,
			HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(id);
		Plan plan = service.findPlanById(planId);
		Date dueDate = service.getNextDueDate(dueDay);
		service.bindPlan(plan, thisUser, dueDate);
		return "redirect:/profile";
	}
	@RequestMapping("/profile")
	public String renderProfile(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(id);
		model.addAttribute("thisUser", thisUser);
		return "profile.jsp";
	}
	// LOGOUT CONTROLS
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("id", null);
		return "redirect:/";
	}
}
