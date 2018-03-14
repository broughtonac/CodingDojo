package com.broughton.beltreviewer.repositories;

import java.math.BigInteger;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.broughton.beltreviewer.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
	
}
