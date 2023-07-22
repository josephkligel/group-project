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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="journals")
public class Journal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotEmpty(message="Journal title is required!")
    private String journalTitle;
	
	@NotEmpty(message="Entry is required!")
    @Size(min=10, message="Entry must be at least 10 characters long")
    private String entry;
	
	@NotNull(message="Privacy selection is required")
    @Column(name="privacy", columnDefinition="TINYINT(1)")
    private Integer privacy;
	
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="journal_id")
    private User journaler;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_journals",
			joinColumns = @JoinColumn(name = "journal_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "journals_comments",
			joinColumns = @JoinColumn(name = "journal_id"),
			inverseJoinColumns = @JoinColumn(name = "comment_id")
	)
    private List<Comment> comments;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="journaler", fetch = FetchType.LAZY)
    private List<Comment> commentsReceived;
    
    public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Comment> getCommentsReceived() {
		return commentsReceived;
	}
	public void setCommentsReceived(List<Comment> commentsReceived) {
		this.commentsReceived = commentsReceived;
	}
	public Journal() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJournalTitle() {
		return journalTitle;
	}
	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public Integer getPrivacy() {
		return privacy;
	}
	public void setPrivacy(Integer privacy) {
		this.privacy = privacy;
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
	public User getJournaler() {
		return journaler;
	}
	public void setJournaler(User journaler) {
		this.journaler = journaler;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	  
}
