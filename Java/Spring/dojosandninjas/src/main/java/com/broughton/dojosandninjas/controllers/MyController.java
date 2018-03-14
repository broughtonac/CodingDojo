package com.broughton.dojosandninjas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.broughton.dojosandninjas.models.*;
import com.broughton.dojosandninjas.services.MyService;

@Controller
public class MyController {
	private MyService myService;
	public MyController(MyService myService) {
		this.myService = myService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/dojos/new")
	public String renderDojos(Model model) {
		model.addAttribute("dojo", new Dojo());
		return "dojos.jsp";
	}
	@RequestMapping(path="/createDojo", method=RequestMethod.POST)
	public String createDojo(
			@ModelAttribute("dojo") Dojo dojo,
			BindingResult result) {
		myService.createDojo(dojo);
		return "redirect:/dojos/new";
	}
	@RequestMapping("/ninjas/new")
	public String renderNinjas(Model model) {
		model.addAttribute("ninja", new Ninja());
		model.addAttribute("dojos", myService.findAllDojos());
		return "ninjas.jsp";
	}
	@RequestMapping(path="/createNinja", method=RequestMethod.POST)
	public String createNinja(
			@ModelAttribute("ninja") Ninja ninja,
			BindingResult result) {
		Dojo dojo = ninja.getDojo();
		myService.createNinja(dojo, ninja);
		return "redirect:/ninjas/new";
	}
	@RequestMapping("/dojos/{id}")
	public String renderView(@PathVariable("id") Long id, Model model) {
		Dojo dojo = myService.findDojo(id);
		model.addAttribute("dojo", dojo);
		model.addAttribute("ninjas", dojo.getNinjas());
		return "view.jsp";
	}
}
