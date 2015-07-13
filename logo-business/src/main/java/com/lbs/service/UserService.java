package com.lbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.model.User;
import com.lbs.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public void saveUser(User user){
		userRepository.save(user);
	}

	public User findUser(Long userId) {
		return userRepository.findOne(userId);
	}
} 
