package com.andrewbroughton.languagesreloaded.controllers;

import java.util.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.andrewbroughton.languagesreloaded.models.Language;
import com.andrewbroughton.languagesreloaded.services.LanguageService;

@Controller
public class Languages {
//	private int count = 0;
	private LanguageService languageService;
	public Languages(LanguageService languageService) {
		this.languageService = languageService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/languages")
	public String languages(Model model) {
		List<Language> languages = languageService.getLanguages();
		model.addAttribute("languages", languages);
		model.addAttribute("language", new Language());
		return "languages.jsp";
	}
	@RequestMapping("/languages/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		model.addAttribute("language", language);
		return "view.jsp";
	}
	@RequestMapping(path="/create", method=RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("language") Language language,
			BindingResult result) {
		if (result.hasErrors()) {
			return "languages.jsp";
		}
		else {
			languageService.create(language);
//			this.count++;
			return "redirect:/languages";
		}
	}
	@RequestMapping(path="/languages/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Language language = languageService.findLanguage(id);
		if (language != null) {
			model.addAttribute("language", language);
			return "edit.jsp";
		}
		else {
			return "redirect:/languages";
		}
	}
	@RequestMapping(path="/languages/edit/{id}", method=RequestMethod.POST)
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("language") Language language, BindingResult result) {
		if (result.hasErrors()) {
			return "edit.jsp";
		}
		else {
			languageService.update(language);
			return "redirect:/languages";
		}
	}
	@RequestMapping("/languages/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		languageService.delete(id);
		return "redirect:/languages";
	}
}
