package com.broughton.beltexam.services;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;
import com.broughton.beltexam.models.*;
import com.broughton.beltexam.repositories.*;

@Service
public class BeltExamService {
	private PlanRepository planRepository;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bcrypt;
	public BeltExamService(
			PlanRepository planRepository,
			UserRepository userRepository) {
		this.planRepository = planRepository;
		this.userRepository = userRepository;
		this.bcrypt = new BCryptPasswordEncoder();
	}
	// PLAN SERVICES
	public void createPlan(Plan plan) {
		planRepository.save(plan);
	}
	public Plan findPlanById(Long id) {
		return planRepository.findOne(id);
	}
	public List<Plan> findAllPlans() {
		return (ArrayList<Plan>) planRepository.findAll();
	}
	public void destroyPlan(Plan plan) {
		planRepository.delete(plan);
	}
	public void activate(Plan plan) {
		plan.setAvailable("available");
		planRepository.save(plan);
	}
	public void deactivate(Plan plan) {
		plan.setAvailable("unavailable");
		planRepository.save(plan);
	}
	public int subscribers(Plan plan) {
		return plan.getUsers().size();
	}
	public List<Integer> getDays() {
		List<Integer> days = new ArrayList<Integer>();
		for (int i = 1; i < 32; i++) {
			days.add(Integer.valueOf(i));
		}
		return days;
	}
	public Date getNextDueDate(Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (day < calendar.get(Calendar.DAY_OF_MONTH)) {
			calendar.set(Calendar.DAY_OF_MONTH, day);
			calendar.add(Calendar.MONTH, 1);
		}
		else if (day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		else {
			calendar.set(Calendar.DAY_OF_MONTH, day);
		}
		return calendar.getTime();
	}
	public List<Plan> findActivePlans() {
		List<Plan> activePlans = (ArrayList<Plan>) new ArrayList<Plan>();
		for (BigInteger id : planRepository.queryActivePlans()) {
			activePlans.add(planRepository.findOne(id.longValue()));
		}
		return activePlans;
	}
	// USER SERVICES
	public void createUser(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setAdmin(false);
		userRepository.save(user);
	}
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public List<User> findAllUsers() {
		return (ArrayList<User>) userRepository.findAll();
	}
	public Boolean emailExists(String email) {
		return this.findByEmail(email) != null;
	}
	public boolean isMatch(String password, String hashed) {
		return (bcrypt.matches(password, hashed));
	}
	public void createAdmin(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setAdmin(true);
		userRepository.save(user);
	}
	public void bindPlan(Plan plan, User user, Date dueDate) {
		user.setDueDate(dueDate);
		user.setPlan(plan);
		userRepository.save(user);
	}
}
