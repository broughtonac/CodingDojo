package com.broughton.beltreviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.broughton.beltreviewer.models.*;
import com.broughton.beltreviewer.services.BeltReviewerService;

@Controller
public class BeltReviewerController {
	private BeltReviewerService service;
	public BeltReviewerController(BeltReviewerService service) {
		this.service = service;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/home")
	public String renderHome(
			@ModelAttribute("user") User user,
			HttpSession session) {
		if (session.isNew()) {
			session.setAttribute("id", null);
		}
		return "home.jsp";
	}
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (email == "") {
			redirectAttributes.addFlashAttribute("loginErrors", "enter your email address");
			return "redirect:/home";
		}
		User user = service.findByEmail(email);
		if (user == null) {
			redirectAttributes.addFlashAttribute("loginErrors", "email address not found");
			return "redirect:/home";
		}
		if (service.isMatch(password, user.getPassword())) {
			session.setAttribute("id", user.getId());
			return "redirect:/events";
		}
		else {
			redirectAttributes.addFlashAttribute("loginErrors", "incorrect password");
			return "redirect:/home";
		}
	}
	@RequestMapping(path="/register",method=RequestMethod.POST)
	public String register(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("registrationErrors", result.getAllErrors());
			if (service.emailExists(user)) {
				redirectAttributes.addFlashAttribute("emailExistsError", "that email already exists");
				return "redirect:/home";
			}
			return "redirect:/home";
		}
		service.createUser(user);
		session.setAttribute("id", user.getId());
		System.out.println(user.getId());
		return "redirect:/events";
	}
	@RequestMapping("/events")
	public String renderDashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(userId);
		model.addAttribute("thisUser", thisUser);
		model.addAttribute("eventsAttendingInState", service.getEventsAttendingInState(thisUser, thisUser.getState()));
		model.addAttribute("eventsNotAttendingInState", service.getEventsNotAttendingInState(thisUser, thisUser.getState()));
		model.addAttribute("eventsAttendingNotInState", service.getEventsAttendingNotInState(thisUser, thisUser.getState()));
		model.addAttribute("eventsNotAttendingNotInState", service.getEventsNotAttendingNotInState(thisUser, thisUser.getState()));
		model.addAttribute("event", new Event());
		System.out.println(userId);
		return "dashboard.jsp";
	}
	@RequestMapping("/attend/{id}")
	public String attend(@PathVariable("id") Long eventId, HttpSession session) {
		Event event = service.findEventById(eventId);
		Long userId = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(userId);
		service.addAttendee(event, thisUser);
		return "redirect:/events";
	}
	@RequestMapping("/unattend/{id}")
	public String unattend(@PathVariable("id") Long eventId, HttpSession session) {
		Event event = service.findEventById(eventId);
		Long userId = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(userId);
		service.removeAtendee(event, thisUser);
		return "redirect:/events";
	}
	@RequestMapping("/events/{id}/edit")
	public String renderEdit(
			@PathVariable("id") Long eventId,
			Model model,
			HttpSession session) {
		if (service.findEventById(eventId).getHost().getId().equals(session.getAttribute("id"))) {
			model.addAttribute("event", service.findEventById(eventId));
			model.addAttribute("newEvent", new Event());
			return "edit.jsp";
		}
		return "redirect:/events";
	}
	@RequestMapping(path="/update/event/{id}",method=RequestMethod.POST)
	public String update(
			@PathVariable("id") Long eventId,
			@Valid @ModelAttribute("newEvent") Event newEvent,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("updateErrors", result.getAllErrors());
			redirectAttributes.addAttribute("id", eventId);
			return "redirect:/events/{id}";
		}
		service.updateEvent(service.findEventById(eventId), newEvent);
		return "redirect:/events";
	}
	@RequestMapping("/events/{id}/delete")
	public String delete(
			@PathVariable("id") Long eventId) {
		service.destroyEvent(service.findEventById(eventId));
		return "redirect:/events";
	}
	@RequestMapping(path="/create/event",method=RequestMethod.POST)
	public String createEvent(
			@Valid @ModelAttribute("event") Event event,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("createEventErrors", result.getAllErrors());
			return "redirect:/events";
		}
		Long userId = (Long) session.getAttribute("id");
		User thisUser = service.findUserById(userId);
		service.createEvent(event, thisUser);
		return "redirect:/events/";
	}
	@RequestMapping("/events/{id}")
	public String renderEvent(
			@PathVariable("id") Long eventId,
			@Valid @ModelAttribute("message") Message message,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		Event event = service.findEventById(eventId);
		model.addAttribute("event", event);
		return "event.jsp";
	}
	@RequestMapping(path="/create/message/{id}",method=RequestMethod.POST)
	public String createMessage(
			@PathVariable("id") Long eventId,
			@Valid @ModelAttribute("message") Message message,
			BindingResult result,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("id", eventId);
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("createMessageErrors", result.getAllErrors());
			return "redirect:/events/{id}";
		}
		Long userId = (Long) session.getAttribute("id");
		System.out.println(userId);
		User thisUser = service.findUserById(userId);
		message.setUser(thisUser);
		message.setEvent(service.findEventById(eventId));
		service.createMessage(message);
		return "redirect:/events/{id}";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("id", null);
		return "redirect:/home";
	}
}
