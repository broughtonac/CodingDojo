package com.andrewbroughton.languagesreloaded.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.andrewbroughton.languagesreloaded.models.Language;
import java.util.*;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
	List<Language> findAll();
}
