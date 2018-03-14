package com.andrewbroughton.learningplatform.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LearningPlatformController {
	public Map<String,String> initializeMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("0733", "set up lesson placeholder");
		map.put("0345", "forms assignment placeholder");
		map.put("0342", "cards lesson placeholder");
		map.put("0348", "advanced lesson placeholder");
		map.put("2342", "binary assignment placeholder");
		return map;
	}
	public Map<String,String> placeholders = initializeMap();
	@RequestMapping("/")
	public String index() {
		return "lesson.jsp";
	}
	@RequestMapping("/m/{chapter}/0553/{lessonNumber}")
	public String lesson(@PathVariable("lessonNumber") String lessonNumber, Model model) {
		model.addAttribute("placeholder", placeholders.get(lessonNumber));
		return "lesson.jsp";
	}
	@RequestMapping("/m/{chapter}/0483/{assignmentNumber}")
	public String assignment(@PathVariable("assignmentNumber") String assignmentNumber, Model model) {
		model.addAttribute("placeholder", placeholders.get(assignmentNumber));
		return "assignment.jsp";
	}
}
