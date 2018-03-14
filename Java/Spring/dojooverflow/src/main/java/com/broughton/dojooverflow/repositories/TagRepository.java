package com.broughton.dojooverflow.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.broughton.dojooverflow.models.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
	@Query(value=
			"SELECT \n" + 
			"    COUNT(tags.id)\n" + 
			"FROM\n" + 
			"    tags\n" + 
			"WHERE\n" + 
			"    tags.subject = :subject", nativeQuery=true)
	int countTagsWithName(@Param("subject") String subject);
	@Query(value=
			"SELECT \n" + 
			"    tags.id\n" + 
			"FROM\n" + 
			"    tags\n" + 
			"WHERE\n" + 
			"    subject = :subject", nativeQuery=true)
	Long getTagIdBySubject(@Param("subject") String subject);
}
