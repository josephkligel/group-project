package com.dojo.innerguru.services;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.innerguru.models.Comment;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.repositories.CommentRepo;

@Service
public class CommentServ {
	private final CommentRepo commentRepo;
	
	public CommentServ(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public List<Comment> allComments(){
		return commentRepo.findAll();
	}
	
	public Comment updateComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	
	
	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	public void deleteComment(Comment comment) {
		commentRepo.delete(comment);
	}
	
	public Comment findById(Long id) {
		Optional<Comment> optionalComment = commentRepo.findById(id);
		if(optionalComment.isPresent()) {
			return optionalComment.get();
		}else {
			return null;
		}
	}

}
