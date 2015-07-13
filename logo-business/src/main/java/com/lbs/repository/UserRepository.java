package com.lbs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lbs.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	
	List<User> findUserByFirstnameAndLastname(String firstname,String lastname);
	
	
	//PageRequest pageRequest = new PageRequest(page, size)
//	List<User> findUserByFirstname(String firstname, Pageable pageable);

	
	// Custom query
//	@Query("select * from User where firstname = :name1")
//	List<User> findByfirstAndLast(@Param(value = "name1") String name1,@Param(value = "name2") String name2);
}
