package com.broughton.employeesandmanagers.models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
	// if id == manager_id, employee is a manager and has a list of employees
	// if id != manager_id, employee is not a manager and has a manager with id manager_id
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
    @OneToMany(mappedBy="manager",fetch=FetchType.LAZY)
    private List<Employee> employees;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee manager;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    public Employee() {}
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
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
