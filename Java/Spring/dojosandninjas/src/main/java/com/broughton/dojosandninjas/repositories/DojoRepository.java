package com.broughton.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.broughton.dojosandninjas.models.Dojo;

@Repository
public interface DojoRepository extends CrudRepository<Dojo,Long> {}
