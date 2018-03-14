package com.broughton.admindashboard.services;

import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.broughton.admindashboard.models.User;
import com.broughton.admindashboard.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private BCryptPasswordEncoder bcrypt;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.bcrypt = new BCryptPasswordEncoder();
	}
	public void create(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		userRepository.save(user);
	}
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
	public ArrayList<User> all() {
		return (ArrayList<User>) userRepository.findAll();
	}
	public void update(User user) {
		create(user);
	}
	public void destroy(User user) {
		userRepository.delete(user);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public boolean isMatch(String password, String hashed) {
		if (bcrypt.matches(password, hashed)) {
			return true;
		}
		else {
			return false;
		}
	}
	public void createAdmin(User user) {
		if (this.all().isEmpty()) {
			user.setPassword(bcrypt.encode(user.getPassword()));
		}
		user.setAdmin(true);
		userRepository.save(user);
	}
	public Boolean emailExists(User user) {
		String email = user.getEmail();
		if (this.findByEmail(email) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
