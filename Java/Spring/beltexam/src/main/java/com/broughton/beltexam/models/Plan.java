package com.broughton.beltexam.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="plans")
public class Plan {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message="type must not be blank")
	private String type;
	@NotNull(message="cost must not be blank")
	private Double cost;
	private String available;
	@OneToMany(mappedBy="plan",fetch=FetchType.LAZY)
	private List<User> users;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	public Plan() {
		this.available = "available";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Boolean isAvailable() {
		if (this.available.equals("available")) {
			return true;
		}
		return false;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
