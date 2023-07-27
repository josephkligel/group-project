package com.dojo.innerguru.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.innerguru.models.Goal;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.repositories.GoalRepo;

@Service
public class GoalServ {
	
	private final GoalRepo goalRepo;
	
		public GoalServ(GoalRepo goalRepo) {
			this.goalRepo = goalRepo;
		}
		
		public List<Goal> allGoals(){
			return goalRepo.findAll();
		}
		
		public Goal updateGoal(Goal goal) {
			return goalRepo.save(goal);
		}
		
		public List<Goal> getAssignedGoals(User user){
			return goalRepo.findAllByUser(user);
		}
		
		public Goal addGoal(Goal goal) {
			return goalRepo.save(goal);
		}
		
		public void deleteGoal(Goal goal) {
			goalRepo.delete(goal);
		}
		
		public Goal findById(Long id) {
			Optional<Goal> optionalGoal = goalRepo.findById(id);
			if(optionalGoal.isPresent()) {
				return optionalGoal.get();
			}else {
				return null;
			}
		}
}
