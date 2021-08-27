package belt.spring.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import belt.spring.models.User;
import belt.spring.services.RatingService;
import belt.spring.services.ShowService;
import belt.spring.services.UserService;
import belt.spring.validator.UserValidator;
import belt.spring.models.Rating;
import belt.spring.models.Show;

@Controller
public class MainController {

	// private final
		private final UserService userService;
		private final ShowService showService;
		private final RatingService ratingService;
		
		// validations
		private final UserValidator userValidator;
		
		public MainController(UserService userService, UserValidator userValidator, ShowService showService,RatingService ratingService) {
			this.userService = userService;
			this.showService = showService;
			this.userValidator = userValidator;
			this.ratingService=ratingService;
		}
		
		// 'USER controller'
		
		// welcome, login and registration page
		@GetMapping("/")
		public String welcome(@ModelAttribute("user") User user) {
			return "index.jsp";
		}
		
		// POST, register user
		@PostMapping("/registration")
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	    	userValidator.validate(user, result);
	    	if(result.hasErrors()) {
	    		return "index.jsp";
	    	} else {
	    		User u = userService.registerUser(user);
	    		session.setAttribute("u_id", u.getId());
	    		return "redirect:/shows";
	    	}
	        // if result has errors, return the registration page (don't worry about validations just now)
	        // else, save the user in the database, save the user id in session, and redirect them to the /home route
	    }
		
		// POST, login user
		@PostMapping("/login")
	    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
	    	boolean isAuthenticated = userService.authenticateUser(email, password);
			if(isAuthenticated) {
				User u = userService.findByEmail(email);
				session.setAttribute("u_id", u.getId());
				model.addAttribute("name",u.getName());
				return "redirect:/shows";
			}
			else {
				model.addAttribute("error", "invalid credentials");
				return "index.jsp";	
			}
	        
	    }
		
		// logout user
	    @RequestMapping("/logout")
	    public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
	        // invalidate session
	        // redirect to login page
	    }
	    
	 /*
	  * SPACE
	  */
	    
	   // Shows CONTROLLER
	   
	 // show dash-board
	    @GetMapping("/shows")
	    public String dashboard(HttpSession session, Model model) {
	    	
	    	// get user in session by id
	    	// cast
	    	Long u_id = (Long) session.getAttribute("u_id");
	    	User u = userService.findUserById(u_id);
	    	model.addAttribute("user", u);
	    	
	    	// display all shows 
	    	List<Show> allShows = showService.findAll();
	    	model.addAttribute("allShows", allShows);
	    	
	    	return "dashboard.jsp";
	    }
	    
	  // new show page, renders page
	    @GetMapping("/shows/new")
	    public String newShowPage(@Valid @ModelAttribute("newShow") Show newShow) {
	    	return "new.jsp";
	    }
	    
	    // creation of new show,  POST
	    @PostMapping("/shows/new")
	    public String newShow(@Valid @ModelAttribute("newShow") Show newShow,
	    		BindingResult result,HttpSession session, Model model, 
				RedirectAttributes flash) {
	    	// get user in session by id
	    	
	    	
	    	// create the show 
	    	if(result.hasErrors()) {
	    		
	    		flash.addFlashAttribute("errors", "No puedes dejar en blanco este espacio");
	    		return "redirect:/shows/new";
	    	} else {
	    		Long u_id = (Long) session.getAttribute("u_id");
		    	User u = userService.findUserById(u_id);
		    	model.addAttribute("user", u);
	    		// attaching user to show 
	    		newShow.setUser(u);
	    		showService.createShow(newShow);
	    		
	    		return "redirect:/shows";
	    	}
	    }
	    
	    
	    // edit show page,
	    @GetMapping("/shows/{id}/edit")
	    public String editShowPage(@PathVariable("id") Long id, Model model,
	    		@Valid @ModelAttribute("updateShow") Show updateShow, HttpSession session) {
	    	
	    	// get show by id
	    	Show show = showService.getShowById(id);
	    	model.addAttribute("show", show);
	    	
	    	// get user in session 
	    	Long u_id = (Long) session.getAttribute("u_id");
	    	
	    	// verification between user in session/ brute force and the actual creator of the show
	    	if(show.getUser().getId().equals(u_id)) {
	    		return "edit.jsp";
	    	} else {
	    		
	        	return "redirect:/shows";    		
	    	}
	    } 
	    
	    
	    // edit an show, POST, update information identified by id
	    @PostMapping("/shows/{id}/edit")
	    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("updateShow") Show updateShow, BindingResult result, HttpSession session) {
	    	
	    	if(result.hasErrors()) {
	    		return "redirect:/shows/"+id+"/edit";
	    	} else {
	    		// get user in session
	        	Long u_id = (Long) session.getAttribute("u_id");
	        	User u = userService.findUserById(u_id);
	        	
	        	// update the show
	        	updateShow.setUser(u);
	        	showService.updateShow(updateShow);
	        	
	        	return "redirect:/shows";
	    	}
	    }
	    
	    // show show page
	    @GetMapping("shows/{id}")
	    public String showShowPage(@PathVariable("id") Long id, Model model) {
	    	
	    	// get show by id
	    	Show show = showService.getShowById(id);
	    	model.addAttribute("show", show);
	    	//model.addAttribute(")
	    	 
	    	
	    	//model.addAttribute("rating",ratingService.buscarshow(id));
	    	model.addAttribute("rating",ratingService.buscaTodos());
	    	return "show.jsp";
	    }
	    
	    // delete an show
	    @PostMapping("/shows/{id}/delete")
	    public String deleteShow(@PathVariable("id") Long id) {
	    	showService.delete(id);
	    	return "redirect:/shows";   
	    }
	    
	    @PostMapping("/shows/{id}/rate")
	    public String rate(@PathVariable("id") Long id,
	    		HttpSession session,Model model,@RequestParam("num") String num) {
	    	 
	    	Long u_id = (Long) session.getAttribute("u_id");
	    	User u = userService.findUserById(u_id);
	    	Show s=showService.findShowById(id);
	    	double r = Double.parseDouble(num);
	    	
	    	
	    	
	    	//double total=ratingService.findAll().size();

	    	/*double sum = 0.0;
	    	
	    	for(int i=0;i<total;i++) {
	    		sum +=r;
	    	}
			s.setRating((int) sum);
			System.out.println(sum);*/
			

	    	Rating rating=new Rating();
	    
	    	
	    	
	    	rating.setShow(s);
	    	rating.setUser(u);
	    	rating.setNumber(r);
	    	ratingService.saveRating(rating);
	    	model.addAttribute("rating",ratingService.findAll());

	    	return "redirect:/shows";
	    }
	    
	  
	   
}
