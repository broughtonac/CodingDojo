package com.andrewbroughton.displaydate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DisplayDateController {
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String date(Model model) {
		Date raw = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		String date = simpleDateFormat.format(raw);
		model.addAttribute("currentDate", date);
		return "date.jsp";
	}
	@RequestMapping("/time")
	public String time(Model model) {
		Date raw = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:m a");
		String time = simpleDateFormat.format(raw);
		model.addAttribute("currentTime", time);
		return "time.jsp";
	}
}
