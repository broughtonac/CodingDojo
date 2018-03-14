package com.broughton.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;
import com.broughton.beltexam.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
}
