package com.codingdojo.beltboilerplateone.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.beltboilerplateone.models.Idea;
import com.codingdojo.beltboilerplateone.models.User;
import com.codingdojo.beltboilerplateone.services.UserService;
import com.codingdojo.beltboilerplateone.validator.UserValidator;
import com.codingdojo.beltboilerplateone.services.IdeaService;

@Controller
public class Users{
	private static final long serialVersionUID = 7425721442160028116L;
	private final UserService userService;
    private final UserValidator userValidator;
    private final IdeaService ideaService;
    
    public Users(UserService userService, UserValidator userValidator, IdeaService ideaService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ideaService = ideaService;
    }
    
    
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "logreg.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
//fetches all users
//    	 List<User> users = userService.findAllUsers();
//    	 if(users.contains(user.getEmail())) {
//    		 
//    	 }
    	//doesn't allow duplicate emails
    	User us = userService.findByEmail(user.getEmail());
    	if(us != null) {
    		model.addAttribute("error", "Invalid Credentials. Please try again.");
    		return "logreg.jsp";
    	}
   	 userValidator.validate(user, result);
   // if result has errors, return the registration page (don't worry about validations just now)
   	 if(result.hasErrors()) {
   		 return "logreg.jsp";
   	 } 
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
   	 User u = userService.registerUser(user);
   	 session.setAttribute("userId", u.getId());
   	 return "redirect:/ideas";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
     //conditions for if email is empty
    	 if(user.getEmail() == "") {
    		 user.setPassword("password");
    		 model.addAttribute("error", "Invalid Credentials. Please try again.");
    		 return "logreg.jsp";
    	 }
    	// if the user is authenticated, save their user id in session
    	 System.out.println("I'm before boolean isAuthenticated");
   	 boolean isAuthenticated = userService.authenticateUser(email, password);
   	 System.out.println("I'm AFTER boolean isAuthenticated");
   	 if(isAuthenticated) {
   		 System.out.println("I'm inside if(isAuthenticated)");
   		 User u = userService.findByEmail(email);
   		 System.out.println("I'm after findByEmail");
   		 session.setAttribute("userId", u.getId());
   		 System.out.println("I'm after session.setAttribute");
   		 model.addAttribute("user", u);
   		 System.out.println("I'm after model.addAttribute");
   		 return "redirect:/ideas";
   	 }
        // else, add error messages and return the login page
   	 else {
   		 model.addAttribute("error", "Invalid Credentials. Please try again.");
   		 return "logreg.jsp";
   	 }
    }
    
    @RequestMapping("/ideas")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
//   	 Long userId = (Long)session.getAttribute("userId");
//   	 User u = userService.findUserById(userId);
//   	 model.addAttribute("user", u);
	 List<Idea> ideas = ideaService.allIdeas();
//		first is the model object that's being sent for the view, second is query variable languages
	System.out.println(ideaService.allIdeas());
	model.addAttribute("ideas", ideas); 
   	 return "dashboard.jsp";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
   	 session.invalidate();
        // redirect to login page
   	 return "redirect:/";
    }
    
    @RequestMapping("/ideas/new")
    public String getnewidea(HttpSession session, Model model, @Valid @ModelAttribute("newidea") Idea newidea) {
    	model.addAttribute("newidea", newidea);
//   	 Long userId = (Long) session.getAttribute("userId");
//   	 User u = userService.findUserById(userId);
//   	 model.addAttribute("user", u);
    	return "new.jsp";
    } 
   
    @PostMapping("/newideaprocess")
	public String createidea(@Valid @ModelAttribute("newidea") Idea newidea, BindingResult result, Model model, HttpSession session, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
		return "redirect:/ideas/new";
		} else {
//		   	 Long userId = (Long) session.getAttribute("userId");
//		   	 User u = userService.findUserById(userId);
//		   	 model.addAttribute("user", u);
            ideaService.createidea(newidea);
            return "redirect:/ideas";
        }
    } 
    
    @RequestMapping("/ideas/{id}")
	public String findIdea(@PathVariable("id") Long id, Model model) {
		Idea idea = ideaService.findIdeaById(id);
		model.addAttribute("idea", idea);
		return "details.jsp";
	}
    

    
    @RequestMapping(value="/ideas/delete/{id}")
    public String destroy(@PathVariable("id") Long id) {
        ideaService.deleteIdea(id);
        return "redirect:/ideas";
    }
    
    @RequestMapping("/ideas/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Idea idea = ideaService.findIdea(id);
        model.addAttribute("idea", idea);
    		return "edit.jsp";
    }
    
    @RequestMapping(value="/ideas/{id}/edit", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("idea") String idea, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
        		System.out.println("I'm in edit in result.hasErrors");
            return "edit.jsp";
        } else {
//          	Long userId = (Long)session.getAttribute("userId");
//           	User u = userService.findUserById(userId);
//           	model.addAttribute("user", u);
        		Idea ideaone = new Idea();
        		ideaone.setIdea(idea);
            ideaService.updateIdea(ideaone);
            System.out.println("Hello");
            return "redirect:/ideas";
        }
    }
    
}
    

    