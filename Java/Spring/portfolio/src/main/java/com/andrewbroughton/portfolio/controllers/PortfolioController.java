package com.andrewbroughton.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortfolioController {
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	@RequestMapping("/me")
	public String me() {
		return "aboutme.html";
	}
	@RequestMapping("/projects")
	public String projects() {
		return "myprojects.html";
	}
}
