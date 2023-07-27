package com.dojo.innerguru.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.innerguru.models.Goal;
import com.dojo.innerguru.models.User;

@Repository
public interface GoalRepo extends CrudRepository<Goal, Long> {
		List<Goal> findAll();
		Goal findByIdIs(Long id);
		List<Goal> findAllByUser(User user);
	}