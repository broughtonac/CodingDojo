package com.broughton.beltreviewer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.broughton.beltreviewer.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {

}
