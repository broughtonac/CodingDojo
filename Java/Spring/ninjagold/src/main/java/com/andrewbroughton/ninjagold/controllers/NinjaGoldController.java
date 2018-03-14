package com.andrewbroughton.ninjagold.controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NinjaGoldController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
			session.setAttribute("activity", new ArrayList<String>());
			session.setAttribute("nets", new ArrayList<String>());
			session.setAttribute("times", new ArrayList<String>());
		}
		return "index.jsp";
	}
	@RequestMapping(path="/process", method=RequestMethod.POST)
	public String process(@RequestParam(value="building") String building, HttpSession session) {
		Random r = new Random();
		int currentGold = (int) session.getAttribute("gold");
		ArrayList<String> currentActivity = (ArrayList<String>) session.getAttribute("activity");
		ArrayList<String> currentNet = (ArrayList<String>) session.getAttribute("nets");
		ArrayList<String> currentTimes = (ArrayList<String>) session.getAttribute("times");
		currentTimes.add(new Date().toString());
		if (building.equals("farm")) {
			int amount = r.nextInt(10) + 10;
			session.setAttribute("gold", currentGold + amount);
			currentActivity.add("got " + Integer.toString(amount) + " from farm");
			currentNet.add("positive");
		}
		else if (building.equals("cave")) {
			int amount = r.nextInt(5) + 5;
			session.setAttribute("gold", currentGold + amount);
			currentActivity.add("got " + Integer.toString(amount) + " from cave");
			currentNet.add("positive");
		}
		else if (building.equals("house")) {
			int amount = r.nextInt(3) + 2;
			session.setAttribute("gold", currentGold + amount);
			currentActivity.add("got " + Integer.toString(amount) + " from house");
			currentNet.add("positive");
		}
		else if (building.equals("casino")) {
			int amount = r.nextInt(50) * (r.nextBoolean() ? 1 : -1);
			session.setAttribute("gold", currentGold + amount);
			if (amount > 0) {
				currentActivity.add("got " + Integer.toString(amount) + " from casino");
				currentNet.add("positive");
			}
			else {
				currentActivity.add("lost " + Integer.toString(amount) + " from casino");
				currentNet.add("negative");
			}
		}
		return "redirect:/";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("gold", null);
		session.setAttribute("activity", null);
		session.setAttribute("nets", null);
		return "redirect:/";
	}
}
