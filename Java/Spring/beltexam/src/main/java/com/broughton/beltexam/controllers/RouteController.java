package com.broughton.beltexam.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*") // wildcard for all routes
public class RouteController {
	public RouteController() {}
	@RequestMapping("")
	public String redirect(
			HttpServletRequest requset,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		String url = requset.getRequestURI().toString();
		redirectAttributes.addAttribute("url", url);
		if (session.getAttribute("id") == null) {
			return "redirect:/";
		}
		return "redirect:/{url}";
	}
}
