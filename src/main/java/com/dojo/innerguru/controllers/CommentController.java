package com.dojo.innerguru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dojo.innerguru.models.Comment;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.services.CommentServ;
import com.dojo.innerguru.services.JournalServ;
import com.dojo.innerguru.services.UserServ;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CommentController {
	
	@Autowired
	private CommentServ commentServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private JournalServ journalServ;
	
	@GetMapping("/comments/new")
	public String newComment(@ModelAttribute("comment") Comment comment, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		Long journalId = (Long) session.getAttribute("journalId");
		
		model.addAttribute("userId", userId);
		model.addAttribute("journalId", journalId);
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
			comment.setUser(thisComment.getUser());
			comment.setUser(user);
			commentServ.updateComment(comment);
			return "redirect:/communityposts";
		}
	}
	
	
	@GetMapping("/comment/delete/{id}")
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
