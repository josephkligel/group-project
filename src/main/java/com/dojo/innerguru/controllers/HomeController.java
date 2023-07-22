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
			
			@Autowired
			private CommentServ commentServ;
			
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
					goal.setUsers(thisGoal.getUsers());
					goal.setAspirer(user);
					goalServ.updateGoal(goal);
					return "redirect:/home";
				}
			}
			
			@RequestMapping("/goal/delete/{id}")
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
			
			@GetMapping("/journals/new")
			public String newJournal(@ModelAttribute("journal") Journal journal, HttpSession session, Model model) {
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				
				model.addAttribute("userId", userId);
				return "newjournal.jsp";
			}
			
			@PostMapping("/journals/new")
			public String addNewJournal(@Valid @ModelAttribute(name= "journal") Journal journal, BindingResult result, HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				if(result.hasErrors()) {
					return "newjournal.jsp";
				}
				
				else {
					journalServ.addJournal(journal);
					
					Long userId = (Long) session.getAttribute("userId");
					User user = userServ.findById(userId);
					user.getJournals().add(journal);
					userServ.updateUser(user);
					return "redirect:/home";
				}
			}
			
			@GetMapping("/journal/edit/{id}")
			public String editJournal(@PathVariable("id") Long id, HttpSession session, Model model) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				Journal journal= journalServ.findById(id);
				model.addAttribute("journal", journal);
				return "editjournal.jsp";
			}
			
			@PutMapping("/journal/edit/{id}")
			public String editJournal(
					@PathVariable("id") Long id, 
					@Valid @ModelAttribute("journal") Journal journal, 
					BindingResult result, 
					HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				
				User user = userServ.findById(userId);
				
				if(result.hasErrors()) {
					return "editjournal.jsp";
				}
				
				else {
					Journal thisJournal= journalServ.findById(id);
					journal.setUsers(thisJournal.getUsers());
					journal.setJournaler(user);
					journalServ.updateJournal(journal);
					return "redirect:/home";
				}
			}
			
			
			@RequestMapping("/journal/delete/{id}")
			public String deleteJournal(@PathVariable("id") Long id, HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				Journal journal= journalServ.findById(id);
				journalServ.deleteJournal(journal);
				
				return "redirect:/home";
			}
			
			@GetMapping("/journals/{id}")
			public String viewJournal(@PathVariable("id") Long id, HttpSession session, Model model) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				Journal journal = journalServ.findById(id);
				Comment comment= commentServ.findById(id);
				model.addAttribute("journal", journal);
				model.addAttribute("assignedComments", comment);
				return "viewjournal.jsp";
			}
			
			@GetMapping("/comments/new")
			public String newComment(@ModelAttribute("comment") Comment comment, HttpSession session, Model model) {
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				
				model.addAttribute("userId", userId);
				return "newcomment.jsp";
			}
			
			@PostMapping("/comments/new")
			public String addNewComment(@Valid @ModelAttribute(name= "comment") Comment comment, BindingResult result, HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				if(result.hasErrors()) {
					return "newcomment.jsp";
				}
				
				else {
					commentServ.addComment(comment);
					
					Long userId = (Long) session.getAttribute("userId");
					User user = userServ.findById(userId);
					user.getComments().add(comment);
					userServ.updateUser(user);
					return "redirect:/communityposts";
				}
			}
			
			@GetMapping("/editcomment/{id}")
			public String editComment(@PathVariable("id") Long id, HttpSession session, Model model) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				Comment comment= commentServ.findById(id);
				model.addAttribute("comment", comment);
				return "editcomment.jsp";
			}
			
			@PostMapping("/editcomment/{id}")
			public String editComment(
					@PathVariable("id") Long id, 
					@Valid @ModelAttribute("comment") Comment comment, 
					BindingResult result, 
					HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				
				User user = userServ.findById(userId);
				
				if(result.hasErrors()) {
					return "editcomment.jsp";
				}
				
				else {
					Comment thisComment= commentServ.findById(id);
					comment.setUsers(thisComment.getUsers());
					comment.setCommenter(user);
					commentServ.updateComment(comment);
					return "redirect:/communityposts";
				}
			}
			
			
			@RequestMapping("/comment/delete/{id}")
			public String deleteComment(@PathVariable("id") Long id, HttpSession session) {
				
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				
				Comment comment= commentServ.findById(id);
				commentServ.deleteComment(comment);
				
				return "redirect:/communityposts";
			}
			
			
			@GetMapping("/communityposts")
			public String community(HttpSession session, Model model) {
			 
				if(session.getAttribute("userId") == null) {
					return "redirect:/logout";
				}
				Long userId = (Long) session.getAttribute("userId");
				User user = userServ.findById(userId);
				
				model.addAttribute("user", user);
				model.addAttribute("allJournals", journalServ.allJournals());
				 
				return "community.jsp";
			}
			
		}


