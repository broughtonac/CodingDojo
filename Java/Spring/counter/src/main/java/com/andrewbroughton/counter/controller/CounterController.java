package com.andrewbroughton.counter.controller;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CounterController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("count") == null) {
			session.setAttribute("count", 1);
		}
		else {
			int visits = (int) session.getAttribute("count");
			session.setAttribute("count", visits + 1);
		}
		return "index.jsp";
	}
	@RequestMapping("/counter")
	public String counter() {
		return "counter.jsp";
	}
	@RequestMapping("/clear")
	public String clear(HttpSession session) {
		if (session.getAttribute("count") != null) {
			session.removeAttribute("count");
		}
		return "redirect:/";
	}
}
