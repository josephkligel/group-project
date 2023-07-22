package com.dojo.innerguru.services;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.innerguru.models.Journal;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.repositories.JournalRepo;

@Service
public class JournalServ {
	private final JournalRepo journalRepo;
	
		public JournalServ(JournalRepo journalRepo) {
			this.journalRepo = journalRepo;
		}
		
		public List<Journal> allJournals(){
			return journalRepo.findAll();
		}
		
		public Journal updateJournal(Journal journal) {
			return journalRepo.save(journal);
		}
		
		public List<Journal> getAssignedJournals(User user){
			return journalRepo.findAllByUsers(user);
		}
		
		public List<Journal> getUnassignedJournals(User user){
			return journalRepo.findByUsersNotContains(user);
		}
		
		public Journal addJournal(Journal journal) {
			return journalRepo.save(journal);
		}
		
		public void deleteJournal(Journal journal) {
			journalRepo.delete(journal);
		}
		
		public Journal findById(Long id) {
			Optional<Journal> optionalJournal = journalRepo.findById(id);
			if(optionalJournal.isPresent()) {
				return optionalJournal.get();
			}else {
				return null;
			}
		}
}
