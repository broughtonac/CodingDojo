package com.andrewbroughton.languagesreloaded.services;

import java.util.*;
import com.andrewbroughton.languagesreloaded.models.Language;
import com.andrewbroughton.languagesreloaded.repositories.LanguageRepository;

import org.springframework.stereotype.Service;

@Service
public class LanguageService {
	private LanguageRepository languageRepository;
//	private List<Language> languages = new ArrayList<Language>();
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	public List<Language> getLanguages() {
//		return languages;
		return languageRepository.findAll();
	}
	public Language findLanguage(Long id) {
		return languageRepository.findOne(id);
//		if (i < languages.size()) {
//			return languages.get(i);
//		}
//		else {
//			return null;
//		}
	}
	public void create(Language language) {
//		language.setId(id);
//		languages.add(language);
		languageRepository.save(language);
	}
	public void update(Language language) {
		languageRepository.save(language);
//		if (id < languages.size()) {
//			languages.set(id,  language);
//		}
	}
	public void delete(Long id) {
//		languages.remove(id);
		languageRepository.delete(id);
	}
}
