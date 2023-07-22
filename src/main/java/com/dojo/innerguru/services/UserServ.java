package com.dojo.innerguru.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dojo.innerguru.models.LoginUser;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.repositories.UserRepo;

@Service
public class UserServ {
	@Autowired
	UserRepo userRepo;
	
	public User register(User newUser, BindingResult result) {
		
		Optional<User> incomingUser = userRepo.findByEmail(newUser.getEmail());
    	
    	if(incomingUser.isPresent()) {
    		result.rejectValue("email", "Matches", "Email is already in use");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "Passwords do no match");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}
    
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepo.save(newUser);
    	
	}
	
	public User login(LoginUser newLogin, BindingResult result) {

		Optional<User> incomingUser = userRepo.findByEmail(newLogin.getEmail());
        
    	if(!incomingUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User does not exist");
    		return null;
    	}
    	
    	User userFromDb = incomingUser.get();
        
    	if(!BCrypt.checkpw(newLogin.getPassword(), userFromDb.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}
    	
        return userFromDb;
        
    }
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public User findById(Long id) {
		Optional<User> incomingUser = userRepo.findById(id);
		if(incomingUser.isPresent()) {
			return incomingUser.get();
		}else {
			return null;
		}
	}
}
