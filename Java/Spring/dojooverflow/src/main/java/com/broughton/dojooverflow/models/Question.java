package com.broughton.dojooverflow.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=1,message="question shouldn't be blank")
	private String content;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy="question", fetch=FetchType.LAZY)
    private List<Answer> answers;
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="tags_questions",
    		joinColumns = @JoinColumn(name="question_id"),
    		inverseJoinColumns = @JoinColumn(name="tag_id"))
    private List<Tag> tags;
    public Question() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
