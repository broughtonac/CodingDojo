package com.andrewbroughton.thecode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TheCodeController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/code")
	public String code() {
		return "code.jsp";
	}
	@RequestMapping(path="/process", method=RequestMethod.POST)
	public String process(
			@RequestParam(value="passcode") String passcode,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (passcode.equals("bushido")) {
			return "redirect:/code";
		}
		else {
			redirectAttributes.addFlashAttribute("myErrors", "you must train harder");
			return "redirect:/";
		}
	}
}
