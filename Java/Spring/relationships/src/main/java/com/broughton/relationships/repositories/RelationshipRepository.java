package com.broughton.relationships.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.broughton.relationships.models.Person;

@Repository
public interface RelationshipRepository extends CrudRepository<Person,Long> {
	@Modifying
	@Query(value="update licenses set number = :number where licenses.id = :licenseId", nativeQuery=true)
	void setNumber(@Param("licenseId") Long licenseId, @Param("number") String number);
}
