package com.broughton.loginandregistration.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.broughton.loginandregistration.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
}
