package com.broughton.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import com.broughton.dojooverflow.models.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {

}
