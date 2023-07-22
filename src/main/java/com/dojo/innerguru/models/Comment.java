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
@Table(name="comments")
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	
    @NotEmpty(message="Comment is required!")
    @Size(min=3, message="Comment must be at least 3 characters long")
    private String commentPost;
    
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
    @JoinColumn(name="comment_id")
    private User commenter;
    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_comments",
			joinColumns = @JoinColumn(name = "comment_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="journal_id")
    private Journal journaler;
    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "journals_comments",
			joinColumns = @JoinColumn(name = "comment_id"),
			inverseJoinColumns = @JoinColumn(name = "journal_id")
	)
    private List<Journal> journals;
    
    public Comment() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public User getCommenter() {
		return commenter;
	}
	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
    
	 public List<Journal> getJournals() {
			return journals;
		}
	 public void setJournals(List<Journal> journals) {
			this.journals = journals;
		}
	 public String getCommentPost() {
			return commentPost;
		}
		public void setCommentPost(String commentPost) {
			this.commentPost = commentPost;
		}
		public Journal getJournaler() {
			return journaler;
		}
		public void setJournaler(Journal journaler) {
			this.journaler = journaler;
		}


}
