package com.andrewbroughton.lookify.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import com.andrewbroughton.lookify.models.Song;
import com.andrewbroughton.lookify.services.SongService;

@Controller
public class SongController {
	private SongService songService;
	public SongController(SongService songService) {
		this.songService = songService;
	}
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("artist", null);
		List<Song> songs = songService.findAll();
		model.addAttribute("songs", songs);
		return "dashboard.jsp";
	}
	@RequestMapping("/songs/new")
	public String renderCreate(Model model) {
		model.addAttribute("song", new Song());
		return "create.jsp";
	}
	@RequestMapping(path="/create", method=RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("song") Song song,
			BindingResult result) {
		if (result.hasErrors()) {
			return "dashboard.jsp";
		}
		else {
			songService.create(song);
			return "redirect:/dashboard";
		}
	}
	@RequestMapping("/destroy/{id}")
	public String destory(@PathVariable("id") Long id) {
		songService.destroy(id);
		return "redirect:/dashboard";
	}
	@RequestMapping(path="/search", method=RequestMethod.POST)
	public String search(
			@RequestParam(value="artist") String artist,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("artist", artist);
		return "redirect:/search/{artist}";

	}
	@RequestMapping("search/{artist}")
	public String renderSearch(
			@PathVariable("artist") String artist,
			Model model) {
		List<Song> results = songService.search(artist);
		model.addAttribute("results", results);
		return "search.jsp";
	}
	@RequestMapping("/search/topTen")
	public String topTen(Model model) {
		List<Song> topTen = songService.topTen();
		model.addAttribute("topTen", topTen);
		return "topTen.jsp";
	}
	@RequestMapping("/songs/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Song song = songService.read(id);
		model.addAttribute("song", song);
		return "view.jsp";
	}
}
