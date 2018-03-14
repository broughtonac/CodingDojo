package com.broughton.beltreviewer.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message="first name must not be blank")
	private String firstName;
	@NotBlank(message="last name must not be blank")
	private String lastName;
	@Email(message="you must enter a valid email address")
	private String email;
	@NotBlank(message="city must not be blank")
	private String city;
	private String state;
	@Size(min=8,message="password must be at least 8 characters long")
	private String password;
	@Transient
	@NotBlank(message="password confirmation required")
	private String confirm;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="events_users",
    		joinColumns=@JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="event_id"))
    private Set<Event> events = new HashSet<Event>();
    @OneToMany(mappedBy="host",fetch=FetchType.LAZY)
    private List<Event> eventsHosting;
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<Message> messages;
    private Date createdAt;
    private Date updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public List<Event> getEventsHosting() {
		return eventsHosting;
	}
	public void setEventsHosting(List<Event> eventsHosting) {
		this.eventsHosting = eventsHosting;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
