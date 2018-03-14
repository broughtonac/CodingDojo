package com.broughton.beltreviewer.models;

import java.util.*;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String name;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date date;
	@NotBlank
	private String city;
	private String state;
    @ManyToMany(fetch=FetchType.LAZY) 
    @JoinTable(
    		name="events_users",
    		joinColumns=@JoinColumn(name="event_id"),
    		inverseJoinColumns=@JoinColumn(name="user_id"))
    private Set<User> users = new HashSet<User>();
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="host_id")
    private User host;
	@OneToMany(mappedBy="event",fetch=FetchType.LAZY)
	private List<Message> messages;
    private Date createdAt;
    private Date updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
