package com.broughton.beltreviewer.repositories;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.broughton.beltreviewer.models.*;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByEmail(String email);
	@Query(
			value="SELECT \n" + 
					"    events_users.event_id\n" + 
					"FROM\n" + 
					"    events\n" + 
					"        LEFT JOIN\n" + 
					"    events_users ON events_users.event_id = events.id\n" + 
					"WHERE\n" + 
					"    events_users.user_id = :userId\n" + 
					"        AND events.state = :state",
			nativeQuery=true)
	List<BigInteger> queryEventsAttendingInState(@Param("userId") Long userId, @Param("state") String state);
	@Query(
			value="SELECT \n" + 
					"    events.id\n" + 
					"FROM\n" + 
					"    events\n" + 
					"        LEFT JOIN\n" + 
					"    events_users ON events_users.event_id = events.id\n" + 
					"WHERE\n" + 
					"    user_id != :userId \n" + 
					"        AND events.state = :state",
			nativeQuery=true)
	List<BigInteger> queryEventsNotAttendingInState(@Param("userId") Long userId, @Param("state") String state);
	@Query(
			value="SELECT \n" + 
					"    events_users.event_id\n" + 
					"FROM\n" + 
					"    events\n" + 
					"        LEFT JOIN\n" + 
					"    events_users ON events_users.event_id = events.id\n" + 
					"WHERE\n" + 
					"    events_users.user_id = :userId\n" + 
					"        AND events.state != :state",
			nativeQuery=true)
	List<BigInteger> queryEventsAttendingNotInState(@Param("userId") Long userId, @Param("state") String state);
	@Query(
			value="SELECT \n" + 
					"    events.id\n" + 
					"FROM\n" + 
					"    events\n" + 
					"        LEFT JOIN\n" + 
					"    events_users ON events_users.event_id = events.id\n" + 
					"WHERE\n" + 
					"    user_id != :userId \n" + 
					"        AND events.state != :state",
			nativeQuery=true)
	List<BigInteger> queryEventsNotAttendingNotInState(@Param("userId") Long userId, @Param("state") String state);
}