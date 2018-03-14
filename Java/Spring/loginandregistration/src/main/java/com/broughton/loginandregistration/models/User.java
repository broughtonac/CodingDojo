package com.broughton.loginandregistration.models;

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
	@Size(min=1,max=255,message="first name must not be blank")
	private String firstName;
	@Size(min=1,max=255,message="last name must not be blank")
	private String lastName;
	@Email(message="invalid email")
	private String email;
	@Size(min=8,max=255,message="password must be at least 8 characters")
	private String password;
	@Transient
	@Size(min=8,max=255,message="password confirmation must be at least 8 characters")
	private String confirm;
	private Boolean admin;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	public User() {}
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
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Boolean isAdmin() {
		return admin;
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
