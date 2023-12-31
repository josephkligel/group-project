package com.dojo.innerguru.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="goals")
public class Goal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotEmpty(message="Goal name is required!")
    private String goalName;
	
	@NotEmpty(message="Goal length is required!")
    private String shortOrLong;
	
	@NotEmpty(message="Description is required!")
    @Size(min=3, message="Description must be at least 3 characters long")
    private String description;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
        
    public Goal() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortOrLong() {
		return shortOrLong;
	}
	public void setShortOrLong(String shortOrLong) {
		this.shortOrLong = shortOrLong;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
