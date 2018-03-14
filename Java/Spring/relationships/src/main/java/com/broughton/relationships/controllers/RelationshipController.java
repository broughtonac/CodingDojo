package com.broughton.relationships.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.broughton.relationships.models.License;
import com.broughton.relationships.models.Person;
import com.broughton.relationships.services.RelationshipService;

@Controller
public class RelationshipController {
	private RelationshipService relationshipService;
	public RelationshipController(RelationshipService relationshipService) {
		this.relationshipService = relationshipService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/persons/new")
	public String renderPerson(Model model) {
		model.addAttribute("person", new Person());
		return "person.jsp";
	}
	@RequestMapping(path="/createPerson", method=RequestMethod.POST)
	public String createPerson(
			@ModelAttribute("person") Person person,
			BindingResult result) {
		relationshipService.createPerson(person);
		return "redirect:/persons/new";
	}
	@RequestMapping("/persons/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Person person = relationshipService.getPerson(id);
		model.addAttribute("person", person);
		model.addAttribute("license", person.getLicense());
		return "view.jsp";
	}
	@RequestMapping("/licenses/new")
	public String renderLicense(Model model) {
		model.addAttribute("license", new License());
		model.addAttribute("person", new Person());
		model.addAttribute("persons", relationshipService.findAll());
		return "license.jsp";
	}
	@RequestMapping(path="/createLicense", method=RequestMethod.POST)
	public String createLicense(
			@ModelAttribute("license") License license,
			BindingResult result,
			@RequestParam(value="expiration_date") String expiration_date) throws ParseException {
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
		license.setExpiration_date(simpleDateFormat.parse(expiration_date));
		Person person = license.getPerson();
		relationshipService.createLicense(person, license);
		return "redirect:/licenses/new";
	}
}
