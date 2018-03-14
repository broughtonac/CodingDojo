package com.andrewbroughton.grouplanguages.services;

import java.util.*;
import com.andrewbroughton.grouplanguages.models.Language;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
	private List<Language> languages = new ArrayList<Language>();
	public List<Language> getLanguages() {
		return languages;
	}
	public Language findLanguage(int i) {
		if (i < languages.size()) {
			return languages.get(i);
		}
		else {
			return null;
		}
	}
	public void create(Language language, int id) {
		language.setId(id);
		languages.add(language);
	}
	public void update(int id, Language language) {
		if (id < languages.size()) {
			languages.set(id,  language);
			System.out.println(language);
		}
	}
	public void delete(int id) {
		languages.remove(id);
	}
}
