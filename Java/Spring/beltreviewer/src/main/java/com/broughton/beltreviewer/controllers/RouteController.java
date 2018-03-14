package com.broughton.beltreviewer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*") // wildcard for all routes
public class RouteController {
	public RouteController() {}
	@RequestMapping("")
	public String redirect(HttpServletRequest requset, HttpSession session) {
		if (session.getAttribute("id") != null) {
			return "redirect:/events";
		}
		return "redirect:/home";
	}
}
