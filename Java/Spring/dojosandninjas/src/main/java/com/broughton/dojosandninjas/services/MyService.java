package com.broughton.dojosandninjas.services;

import org.springframework.stereotype.Service;
import java.util.*;
import com.broughton.dojosandninjas.models.*;
import com.broughton.dojosandninjas.repositories.*;

@Service
public class MyService {
	private DojoRepository dojoRepository;
    private NinjaRepository ninjaRepository;
	public MyService(
        DojoRepository dojoRepository,
        NinjaRepository ninjaRepository) {
		this.dojoRepository = dojoRepository;
        this.ninjaRepository = ninjaRepository;
	}
	public void createDojo(Dojo dojo) {
		dojoRepository.save(dojo);
	}
	public Dojo findDojo(Long id) {
		return dojoRepository.findOne(id);
	}
	public List<Dojo> findAllDojos() {
		return (List<Dojo>) dojoRepository.findAll();
	}
	public void createNinja(Dojo dojo, Ninja ninja) {
		if (ninjaRepository.findOne(ninja.getId()) == null) {
			ninja.setDojo(dojo);
			List<Ninja> ninjas = dojo.getNinjas();
			ninjas.add(ninja);
			dojo.setNinjas(ninjas);
			dojoRepository.save(dojo);
			ninjaRepository.save(ninja);
		}
	}
}
