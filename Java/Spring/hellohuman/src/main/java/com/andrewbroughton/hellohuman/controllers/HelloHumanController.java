package com.andrewbroughton.hellohuman.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloHumanController {
	@RequestMapping("/")
	public String index(@RequestParam(value="name", defaultValue="human") String name, HttpSession session) {
		session.setAttribute("name", name);
		return "index.jsp";
	}
}
