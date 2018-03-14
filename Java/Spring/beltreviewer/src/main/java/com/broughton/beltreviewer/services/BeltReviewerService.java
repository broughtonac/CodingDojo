package com.broughton.beltreviewer.services;

import static org.mockito.Matchers.longThat;

import java.math.BigInteger;
import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.broughton.beltreviewer.models.*;
import com.broughton.beltreviewer.repositories.*;

@Service
public class BeltReviewerService {
	private UserRepository userRepository;
	private EventRepository eventRepository;
	private MessageRepository messageRepository;
	private BCryptPasswordEncoder bcrypt;
	public BeltReviewerService(
			UserRepository userRepository,
			EventRepository eventRepository,
			MessageRepository messageRepository) {
		this.userRepository = userRepository;
		this.eventRepository = eventRepository;
		this.messageRepository = messageRepository;
		this.bcrypt = new BCryptPasswordEncoder();
	}
	// user services
	public void createUser(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		userRepository.save(user);
	}
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public boolean isMatch(String password, String hashed) {
		return bcrypt.matches(password, hashed);
	}
	public boolean emailExists(User user) {
		String email = user.getEmail();
		return this.findByEmail(email) != null;
	}
    public List<Event> getEventsAttendingInState(User user, String state) {
		List<BigInteger> eventIds = userRepository.queryEventsAttendingInState(user.getId(), user.getState());
		List<Event> eventsAttendingInState = new ArrayList<Event>();
		for (BigInteger id : eventIds) {
			Event event = this.findEventById(id.longValue());
			eventsAttendingInState.add(event);
		}
		return eventsAttendingInState;
	}
	public List<Event> getEventsNotAttendingInState(User user, String state) {
		List<BigInteger> eventIds = userRepository.queryEventsNotAttendingInState(user.getId(), user.getState());
		List<Event> eventsNotAttendingInState = new ArrayList<Event>();
		for (BigInteger id : eventIds) {
			Event event = this.findEventById(id.longValue());
			eventsNotAttendingInState.add(event);
		}
		return eventsNotAttendingInState;
	}
	public List<Event> getEventsAttendingNotInState(User user, String state) {
		List<BigInteger> eventIds = userRepository.queryEventsAttendingNotInState(user.getId(), user.getState());
		List<Event> eventsAttendingNotInState = new ArrayList<Event>();
		for (BigInteger id : eventIds) {
			Event event = this.findEventById(id.longValue());
			eventsAttendingNotInState.add(event);
		}
		return eventsAttendingNotInState;
	}
	public List<Event> getEventsNotAttendingNotInState(User user, String state) {
		List<BigInteger> eventIds = userRepository.queryEventsNotAttendingNotInState(user.getId(), user.getState());
		List<Event> eventsNotAttendingNotInState = new ArrayList<Event>();
		for (BigInteger id : eventIds) {
			Event event = this.findEventById(id.longValue());
			eventsNotAttendingNotInState.add(event);
		}
		return eventsNotAttendingNotInState;
	}
	// event services
	public void createEvent(Event event, User host) {
		event.setHost(host);
//		event.getUsers().add(host);
		eventRepository.save(event);
	}
	public void updateEvent(Event oldEvent, Event newEvent) {
		oldEvent.setName(newEvent.getName());
		oldEvent.setDate(newEvent.getDate());
		oldEvent.setCity(newEvent.getCity());
		oldEvent.setState(newEvent.getState());
		eventRepository.save(oldEvent);
	}
	public void addAttendee(Event event, User user) {
		event.getUsers().add(user);
		eventRepository.save(event);
	}
	public void removeAtendee(Event event, User user) {
		event.getUsers().remove(user);
		eventRepository.save(event);
	}
	public Event findEventById(Long id) {
		return eventRepository.findOne(id);
	}
	public void destroyEvent(Event event) {
		eventRepository.delete(event);
	}
	// message services
	public void createMessage(Message message) {
		messageRepository.save(message);
	}
}
