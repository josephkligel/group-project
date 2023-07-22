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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First Name is required!")
    @Size(min=2, max=25, message="First Name must be between 2 and 25 characters")
    private String firstName;
    
    @NotEmpty(message="Last Name is required!")
    @Size(min=2, max=25, message="Last Name must be between  and 25 characters")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Enter a valid email")
    private String email;
    @NotEmpty(message="Password is required!")
    @Size(min=10, max=200, message="Password must be between 10 and 200 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=10, max=200, message="Password must be between 10 and 200 characters")
    private String confirm;
    
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
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_journals",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "journal_id")
	)
    private List<Journal> journals;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="journaler", fetch = FetchType.LAZY)
    private List<Journal> journalsWritten;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_goals",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "goal_id")
	)
    private List<Goal> goals;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="aspirer", fetch = FetchType.LAZY)
    private List<Goal> goalsMade;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_comments",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "comment_id")
	)
    private List<Comment> comments;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="commenter", fetch = FetchType.LAZY)
    private List<Comment> commentsMade;

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
	public List<Journal> getJournals() {
		return journals;
	}
	public void setJournals(List<Journal> journals) {
		this.journals = journals;
	}
	public List<Journal> getJournalsWritten() {
		return journalsWritten;
	}
	public void setJournalsWritten(List<Journal> journalsWritten) {
		this.journalsWritten = journalsWritten;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	public List<Goal> getGoalsMade() {
		return goalsMade;
	}
	public void setGoalsMade(List<Goal> goalsMade) {
		this.goalsMade = goalsMade;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Comment> getCommentsMade() {
		return commentsMade;
	}
	public void setCommentsMade(List<Comment> commentsMade) {
		this.commentsMade = commentsMade;
	}
}