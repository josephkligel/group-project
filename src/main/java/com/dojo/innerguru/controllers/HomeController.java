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

import com.dojo.innerguru.models.Comment;
import com.dojo.innerguru.models.Goal;
import com.dojo.innerguru.models.Journal;
import com.dojo.innerguru.models.LoginUser;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.services.CommentServ;
import com.dojo.innerguru.services.GoalServ;
import com.dojo.innerguru.services.JournalServ;
import com.dojo.innerguru.services.UserServ;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
			@Autowired
			private UserServ userServ;
			
			@Autowired
			private GoalServ goalServ;
			
			@Autowired
			private JournalServ journalServ;
			
			@GetMapping("/")
			public String index(Model model) {
			    model.addAttribute("newUser", new User());
			    model.addAttribute("newLogin", new LoginUser());
			    return "index.jsp";
			}
			
			@PostMapping("/register")
			public String register(@Valid @ModelAttribute(name = "newUser") User newUser, 
					BindingResult result, Model model, HttpSession session) {

			    User user = userServ.register(newUser, result);
			     
			    if(result.hasErrors()) {
			        model.addAttribute("newLogin", new LoginUser());
			        return "index.jsp";
			    }
			    session.setAttribute("userId", user.getId());
			 
			    return "redirect:/home";
			}
			
			@PostMapping("/login")
			public String login(@Valid @ModelAttribute(name= "newLogin") LoginUser newLogin, 
					BindingResult result, Model model, HttpSession session) {
			     
				User user = userServ.login(newLogin, result);
			 
			    if(result.hasErrors()) {
			        model.addAttribute("newUser", new User());
			        return "index.jsp";
			    }
			     
			    session.setAttribute("userId", user.getId());
			 
			    return "redirect:/home";
			}
			
			@GetMapping("/home")
			public String home(HttpSession session, Model model) {
			 
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				User user = userServ.findById(userId);
				
				model.addAttribute("user", user);
				model.addAttribute("assignedGoals", goalServ.getAssignedGoals(user));
				model.addAttribute("assignedJournals", journalServ.getAssignedJournals(user));
				return "home.jsp";
			}
			
}
			




