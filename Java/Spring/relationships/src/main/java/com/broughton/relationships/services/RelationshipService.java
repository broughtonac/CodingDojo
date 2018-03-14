package com.broughton.relationships.services;

import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

import com.broughton.relationships.models.License;
import com.broughton.relationships.models.Person;
import com.broughton.relationships.repositories.RelationshipRepository;

@Service
public class RelationshipService {
	private RelationshipRepository relationshipRepository;
	public RelationshipService(RelationshipRepository relationshipRepository) {
		this.relationshipRepository = relationshipRepository;
	}
	public void createPerson(Person person) {
		relationshipRepository.save(person);
	}
	public Person getPerson(Long id) {
		return relationshipRepository.findOne(id);
	}
	public List<Person> findAll() {
		return (List<Person>) relationshipRepository.findAll();
	}
	@Transactional
	public void createLicense(Person person, License license) {
		Long person_id = license.getPerson().getId();
		if (relationshipRepository.findOne(person_id).getLicense() == null) {
			person.setLicense(license);
			license.setPerson(person);
			relationshipRepository.save(person);
			Long licenseId = relationshipRepository.findOne(person_id).getLicense().getId();
			String idString = Long.toString(licenseId);
			String zeros = "";
			for (int i = 0; i < 6 - idString.length(); i++) { zeros = zeros + "0"; }
			String number = zeros + idString;
			relationshipRepository.setNumber(licenseId, number);
		}
	}
	public License getLicense(Long id) {
		return relationshipRepository.findOne(id).getLicense();
	}
}
