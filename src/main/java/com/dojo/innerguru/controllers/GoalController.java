package com.dojo.innerguru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dojo.innerguru.models.Goal;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.services.GoalServ;
import com.dojo.innerguru.services.UserServ;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GoalController {
	@Autowired
	private GoalServ goalServ;
	
	@Autowired
	private UserServ userServ;
	
	@GetMapping("/goals/{id}")
	public String viewGoal(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Goal goal = goalServ.findById(id);
		model.addAttribute("goal", goal);
		return "viewgoal.jsp";
	}
	
	@GetMapping("/goal/edit/{id}")
	public String editGoal(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Goal goal= goalServ.findById(id);
		model.addAttribute("goal", goal);
		return "editgoal.jsp";
	}
	
	@PutMapping("/goal/edit/{id}")
	public String editGoal(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("goal") Goal goal, 
			BindingResult result, 
			HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		User user = userServ.findById(userId);
		
		if(result.hasErrors()) {
			return "editgoal.jsp";
		}
		
		else {
			Goal thisGoal= goalServ.findById(id);
			goal.setUser(thisGoal.getUser());
			goal.setUser(user);
			goalServ.updateGoal(goal);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/goal/delete/{id}")
	public String deleteGoal(@PathVariable("id") Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Goal goal= goalServ.findById(id);
		goalServ.deleteGoal(goal);
		
		return "redirect:/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null); 
	    return "redirect:/";
	}
	
	@GetMapping("/goals/new")
	public String newGoal(@ModelAttribute("goal") Goal goal, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		model.addAttribute("userId", userId);
		return "newgoal.jsp";
	}
	
	@PostMapping("/goals/new")
	public String addNewGoal(@Valid @ModelAttribute(name= "goal") Goal goal, BindingResult result, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		if(result.hasErrors()) {
			return "newgoal.jsp";
		}
		
		else {
			goalServ.addGoal(goal);
			
			Long userId = (Long) session.getAttribute("userId");
			User user = userServ.findById(userId);
			user.getGoals().add(goal);
			userServ.updateUser(user);
			return "redirect:/home";
		}
	}
}
