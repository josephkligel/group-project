package com.dojo.innerguru.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.innerguru.models.Comment;
import com.dojo.innerguru.models.User;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
		List<Comment> findAll();
		Comment findByIdIs(Long id);
		List<Comment> findAllByUsers(User user);
		List<Comment> findByUsersNotContains(User user);
	}