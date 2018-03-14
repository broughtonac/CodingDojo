package com.broughton.beltexam.models;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plan_id")
	private Plan plan;
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dueDate;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	public User() {
	}
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
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
