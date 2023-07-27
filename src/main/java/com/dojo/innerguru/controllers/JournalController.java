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

import com.dojo.innerguru.models.Comment;
import com.dojo.innerguru.models.Journal;
import com.dojo.innerguru.models.User;
import com.dojo.innerguru.services.CommentServ;
import com.dojo.innerguru.services.JournalServ;
import com.dojo.innerguru.services.UserServ;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class JournalController {
	
	@Autowired
	private JournalServ journalServ;
	
	@Autowired 
	private UserServ userServ;
	
	@Autowired
	private CommentServ commentServ;
	
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
		
		if(result.hasErrors()) {
			return "editjournal.jsp";
		}
		
		else {
			Journal thisJournal= journalServ.findById(id);
			journal.setUser(thisJournal.getUser());
			journalServ.updateJournal(journal);
			return "redirect:/home";
		}
	}
	
	
	@GetMapping("/journal/delete/{id}")
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
		
		session.setAttribute("journalId", id);
		
		Journal journal = journalServ.findById(id);
		Comment comment= commentServ.findById(id);
		model.addAttribute("journal", journal);
		model.addAttribute("assignedComments", comment);
		return "viewjournal.jsp";
	}
	

}
