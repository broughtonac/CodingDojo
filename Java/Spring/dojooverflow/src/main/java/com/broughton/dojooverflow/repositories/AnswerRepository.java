package com.broughton.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.broughton.dojooverflow.models.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {

}
