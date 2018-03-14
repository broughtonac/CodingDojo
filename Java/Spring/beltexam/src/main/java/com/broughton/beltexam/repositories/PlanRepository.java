package com.broughton.beltexam.repositories;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.broughton.beltexam.models.Plan;

public interface PlanRepository extends CrudRepository<Plan,Long> {
	@Query(value=
			"SELECT \n" + 
			"    id\n" + 
			"FROM\n" + 
			"    plans\n" + 
			"WHERE\n" + 
			"    available = 'available'",nativeQuery=true)
	List<BigInteger> queryActivePlans();
}
