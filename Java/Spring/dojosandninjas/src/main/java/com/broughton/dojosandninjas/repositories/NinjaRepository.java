package com.broughton.dojosandninjas.repositories;

import org.springframework.data.repository.CrudRepository;
import com.broughton.dojosandninjas.models.Ninja;

public interface NinjaRepository extends CrudRepository<Ninja,Long> {}
