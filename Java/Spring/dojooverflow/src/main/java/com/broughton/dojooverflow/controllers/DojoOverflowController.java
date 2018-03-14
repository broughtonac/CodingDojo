package com.broughton.dojooverflow.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.broughton.dojooverflow.models.*;
import com.broughton.dojooverflow.services.DojoOverflowService;

@Controller
public class DojoOverflowController {
	private DojoOverflowService dojoOverflowService;
	public DojoOverflowController(DojoOverflowService dojoOverflowService) {
		this.dojoOverflowService = dojoOverflowService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/questions")
	public String renderQuestionsDashboard(Model model) {
		model.addAttribute("questions", dojoOverflowService.findAllQuestions());
		return "questionsdashboard.jsp";
	}
	@RequestMapping("/questions/new")
	public String renderNewQuestion(Model model) {
		model.addAttribute("question", new Question());
		return "newquestion.jsp";
	}
	@RequestMapping(path="/create/question", method=RequestMethod.POST)
	public String createQuestion(
			@Valid @ModelAttribute("question") Question question,
			BindingResult result,
			@RequestParam(value="questionTags") String questionTags,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
		}
		else {
			dojoOverflowService.createQuestion(question);
			dojoOverflowService.createAndBindTags(question, questionTags);
		}
		return "redirect:/questions/new";
	}
	@RequestMapping("/questions/{id}")
	public String renderViewQuestion(@PathVariable("id") Long id, Model model) {
		model.addAttribute("question", dojoOverflowService.findQuestion(id));
		model.addAttribute("answer", new Answer());
		return "viewquestion.jsp";
	}
	@RequestMapping(path="/create/answer/{id}", method=RequestMethod.POST)
	public String createAnswer(
			@Valid @ModelAttribute("answer") Answer answer,
			BindingResult result,
			@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
		}
		else {
			Question question = dojoOverflowService.findQuestion(id);
			dojoOverflowService.createAndBindAnswer(question, answer);
		}
		return "redirect:/questions/{id}";
	}
}
