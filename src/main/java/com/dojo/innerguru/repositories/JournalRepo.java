package com.dojo.innerguru.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dojo.innerguru.models.Journal;
import com.dojo.innerguru.models.User;

@Repository
public interface JournalRepo extends CrudRepository<Journal, Long> {
		List<Journal> findAll();
		Journal findByIdIs(Long id);
		List<Journal> findAllByUser(User user);
		}