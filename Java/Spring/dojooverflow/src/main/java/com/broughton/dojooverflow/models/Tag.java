package com.broughton.dojooverflow.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="tags")
public class Tag {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String subject;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="tags_questions",
    		joinColumns=@JoinColumn(name="tag_id"),
    		inverseJoinColumns=@JoinColumn(name="question_id"))
    private List<Question> questions;
    public Tag() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
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
