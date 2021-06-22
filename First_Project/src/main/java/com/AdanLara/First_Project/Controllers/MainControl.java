package com.AdanLara.First_Project.Controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AdanLara.First_Project.Models.Drink;
import com.AdanLara.First_Project.Models.FoodItem;
import com.AdanLara.First_Project.Models.Meat;
import com.AdanLara.First_Project.Models.Order;
import com.AdanLara.First_Project.Models.User;
import com.AdanLara.First_Project.Services.DrinkService;
import com.AdanLara.First_Project.Services.FoodItemService;
import com.AdanLara.First_Project.Services.MeatService;
import com.AdanLara.First_Project.Services.OrderService;
import com.AdanLara.First_Project.Services.UserService;
import com.AdanLara.First_Project.UserValidator.UserValidator;

@Controller
public class MainControl {
	@Autowired
	private UserService uServ;
	@Autowired
	private MeatService mServ;
	@Autowired
	private FoodItemService fServ;
	@Autowired
	private UserValidator uValid;
	@Autowired
	private OrderService oServ;
	@Autowired
	private DrinkService dServ;
	
	//login
	@GetMapping("/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@GetMapping("/home")
	public String mainPage() {		
		return "home.jsp";
	}
	
	@GetMapping("/home/{id}")
	public String userPage(@PathVariable("id") Long id, Model viewModel,HttpSession session) {
		
		viewModel.addAttribute("user",this.uServ.findUser(id));
		Long currentUserId=(Long) session.getAttribute("user__id");
		User currentUser =this.uServ.findUser(currentUserId);
		User accessUser= this.uServ.findUser(id);
		
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		
		else if( currentUser!=accessUser) {
			return "redirect:/home/"+ currentUserId;
		}
		
		return "home.jsp";
	}
	
	
	@PostMapping("/login")
	public String loginPage(@RequestParam("loginEmail") String email, @RequestParam("loginPassword") String password, RedirectAttributes redAtt, HttpSession session) {
		if(!this.uServ.loginAuthenticator(email, password)) {
			redAtt.addFlashAttribute("error", "Invalid Credentails");
		return "redirect:/login";
		}
		User user = this.uServ.getByEmail(email);
		session.setAttribute("user__id", user.getId());
		Long id = user.getId();
		
		return "redirect:/home/"+id;
	}
	
	//register
	@GetMapping("/register")
	public String registerPage(@ModelAttribute("newUser") User newUser) {
		return "register.jsp";
	}
	//save new user
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result) {
		this.uValid.validate(newUser, result);
		if(result.hasErrors()) {
			return "register.jsp";
		}
		Long checkDB =(long) 1;
		if(this.uServ.findUser(checkDB)==null) {
			this.uServ.saveWithOwnerRole(newUser);
		}
		else {this.uServ.saveWithUserRole(newUser);
		}
		return "redirect:/register";
	}
	//view menu
	@GetMapping("/menu")
	public String viewMenu(Model viewModel) {

		viewModel.addAttribute("dishes", this.fServ.findAllFoodItems());
		viewModel.addAttribute("meats",this.mServ.findAllMeat());
		viewModel.addAttribute("drinks",this.dServ.findAllDrinks());

		return "menu.jsp";
	}
	
	//edit User Info
	@GetMapping("/{id}/edit")
	public String accountInfo(@PathVariable("id") Long id, Model viewModel, @ModelAttribute ("user") User user, HttpSession session) {		
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		viewModel.addAttribute("user", this.uServ.findUser(id));
		Long currentUserId=(Long) session.getAttribute("user__id");
		User currentUser =this.uServ.findUser(currentUserId);
		User accessUser= this.uServ.findUser(id);

		if( currentUser!=accessUser) {
			return "redirect:/home/"+ currentUserId;
		}
		return "account.jsp";
	}
	//post user updated info
	@PostMapping("/{id}/edit")
	public String updateAccountInfo(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") Long id, HttpSession session) {		
		
		User currentUser= this.uServ.findUser(id);
		
		user.setPassword(currentUser.getPassword());
		String confirmPassword=currentUser.getPassword();
		user.setConfirmPassword(confirmPassword);
		 if(currentUser.getId()==1) {
			 this.uServ.saveWithOwnerRole(user);
		 }
		 else {this.uServ.saveWithUserRole(user);
		 }
		 
		return "redirect:/"+id+"/edit";
		
	}
	
	//view form to order
	@GetMapping("/order")
	public String addNewOrder(@ModelAttribute("order") Order order, HttpSession session, Model viewModel) {
		
		Long currentUserId = (Long) session.getAttribute("user__id");
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}

		viewModel.addAttribute("dishes", this.fServ.findAllFoodItems());
		viewModel.addAttribute("meats",this.mServ.findAllMeat());
		viewModel.addAttribute("drinks",this.dServ.findAllDrinks());
		viewModel.addAttribute("user",this.uServ.findUser(currentUserId));
		
		return "order.jsp";
	}
	
	//Post an order
	@PostMapping("/order")
	public String postNewOrder(@Valid @ModelAttribute("order") Order order, BindingResult result,HttpSession session, Model viewModel) {
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		if(result.hasErrors()) {
			return "order.jsp";
		}
		
		String itemName= order.getItem();
		FoodItem item=this.fServ.findItembyName(itemName);
		order.setItemPrice(item.getPrice() * order.getQuantity());
		
		String drinkName=order.getDrink();
		Drink drink= this.dServ.findByName(drinkName);	
		order.setDrinkPrice(drink.getPrice());
		
		Long currentUserId = (Long) session.getAttribute("user__id");
		
		order.setCustomer(this.uServ.findUser(currentUserId));
		this.oServ.saveOrder(order);

		return "redirect:/order";
	}
	//view past orders
	@GetMapping("/pastOrders/{id}")
	public String pastOrders(@PathVariable("id") Long id, Model viewModel,HttpSession session) {
		
		Long currentUserId = (Long) session.getAttribute("user__id");
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		
		viewModel.addAttribute("user",this.uServ.findUser(id));
		
		User currentUser =this.uServ.findUser(currentUserId);
		User accessUser= this.uServ.findUser(id);
		
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		
		else if( currentUser!=accessUser) {
			return "redirect:/home/"+ currentUserId;
		}
		viewModel.addAttribute("past_orders",currentUser.getOrders());
		viewModel.addAttribute("user",this.uServ.findUser(currentUserId));
		viewModel.addAttribute("user_favorites",currentUser.getFavorites());
		System.out.println(currentUser.getFavorites().size());
		return "pastOrders.jsp";
	}
	//like
	@GetMapping("/pastOrders/{userId}/like/{id}")
	public String likeOrder(@PathVariable("userId") Long userId,@PathVariable("id") Long id ,HttpSession session) {
		
	
		Long currentUserId=(Long) session.getAttribute("user__id");
		User currentUser =this.uServ.findUser(currentUserId);
		
		User accessUser= this.uServ.findUser(userId);
		
		
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}
		
		else if( currentUser!=accessUser) {
			return "redirect:/home/"+ currentUserId;
		}
		
		Order order= this.oServ.getById(id);
		
		this.oServ.likeOrder(order, currentUser);
		return "redirect:/pastOrders/"+currentUserId;
	}
	
	//unlike
		@GetMapping("/pastOrders/{userId}/unlike/{id}")
		public String unlikeOrder(@PathVariable("userId") Long userId,@PathVariable("id") Long id ,HttpSession session) {
			
		
			Long currentUserId=(Long) session.getAttribute("user__id");
			User currentUser =this.uServ.findUser(currentUserId);
			
			User accessUser= this.uServ.findUser(userId);
			
			
			if(session.getAttribute("user__id")==null) {
				return "redirect:/home"; 
			}
			
			else if( currentUser!=accessUser) {
				return "redirect:/home/"+ currentUserId;
			}
			
			Order order= this.oServ.getById(id);
			
			this.oServ.unlikeOrder(order, currentUser);
			return "redirect:/pastOrders/"+currentUserId;
		}
	// OWNER AND ADMIN ONLY      OWNER AND ADMIN ONLY     OWNER ADMIN ONLY
	
	//get all users
	@GetMapping("/allUsers")
	public String viewAllUsers(Model viewModel,HttpSession session) {
		
		Long currentUserId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		
		User neededUser= this.uServ.findUser((long) 1);
		viewModel.addAttribute("user",currentUser);
		
	
		
		if(session.getAttribute("user__id")==null) {
			return "redirect:/home"; 
		}		
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+currentUserId; 
		}
		
		else {		
			List <User> allUsers =this.uServ.findAllUsers();
			viewModel.addAttribute("users", allUsers);
			return "displayUsers.jsp";}
	}
				
		/*if(currentUser.getRoles().contains("OWNER")|currentUser.getRoles().contains("ADMIN")) {
		
		List <User> allUsers =this.uServ.findAllUsers();
		viewModel.addAttribute("users", allUsers);
		return "displayUsers.jsp";
		}
		else if(currentUser.getRoles().contains("USER")){
		return "redirect:/home/"+userId;*/
	
	//logout
	@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/login";
		}
	
	// display forms to add to menu
	@GetMapping("/addFoodItem")
	public String AddToMenu( @ModelAttribute("foodItem") FoodItem item,@ModelAttribute("drink") Drink drink, @ModelAttribute("meat") Meat meat,HttpSession session, Model viewModel) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		Long currentUserId=(Long) session.getAttribute("user__id");
		viewModel.addAttribute("user",this.uServ.findUser(currentUserId));
		viewModel.addAttribute("drinks",this.dServ.findAllDrinks());
		viewModel.addAttribute("meats",this.mServ.findAllMeat());
		viewModel.addAttribute("dishes",this.fServ.findAllFoodItems());
		
		return "foodItem.jsp";
	}
	
	//add dish to menu
	@PostMapping("/addDish")
	public String AddDish(@Valid @ModelAttribute("foodItem")FoodItem item, BindingResult result, HttpSession session) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		
		this.fServ.saveFoodItem(item);
		return "redirect:/addFoodItem";
	}
	//add Meat to menu
		@PostMapping("/addMeat")
		public String AddMeat(@Valid @ModelAttribute("meat") Meat meat, BindingResult result, HttpSession session, Model viewModel) {
			Long userId=(Long) session.getAttribute("user__id");
			User currentUser=this.uServ.findUser(userId);
			User neededUser= this.uServ.findUser((long) 1);
			
			if(result.hasErrors()) {
				return "meatOption.jsp";
			}
			if(session.getAttribute("user__id")==null){
					return "redirect:/home";
				}
				
			else if(currentUser!=neededUser) {
				return "redirect:/home/"+userId; 
				}
			
			this.mServ.saveTypeOfMeat(meat);
			
			return "redirect:/addFoodItem";
		}
	//add drink to menu
		@PostMapping("/addDrink")
		public String AddMeat(@Valid @ModelAttribute("drink") Drink drink, BindingResult result, HttpSession session, Model viewModel) {
			Long userId=(Long) session.getAttribute("user__id");
			User currentUser=this.uServ.findUser(userId);
			User neededUser= this.uServ.findUser((long) 1);
			
			if(result.hasErrors()) {
				return "meatOption.jsp";
			}
			if(session.getAttribute("user__id")==null){
					return "redirect:/home";
				}
				
			else if(currentUser!=neededUser) {
				return "redirect:/home/"+userId; 
				}
			
			this.dServ.saveDrink(drink);
			
			return "redirect:/addFoodItem";
		}
		
	//display dish
	@GetMapping("/editDish/{userId}/{id}")
	public String editDish(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @ModelAttribute ("foodItem") FoodItem foodItem, HttpSession session, Model viewModel) {		
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
			viewModel.addAttribute("user", this.uServ.findUser(currentUserId));
			viewModel.addAttribute("dish", this.fServ.findFoodItemById(id));
			viewModel.addAttribute("meats",this.mServ.findAllMeat());
			viewModel.addAttribute("dishes",this.fServ.findAllFoodItems());
			viewModel.addAttribute("drinks",this.dServ.findAllDrinks());
			return "editFoodItem.jsp";
		}
	}
	
	//display drink
	@GetMapping("/editDrink/{userId}/{id}")
	public String editDrink(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @ModelAttribute ("drink") Drink drink, HttpSession session, Model viewModel) {		
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
			viewModel.addAttribute("user", this.uServ.findUser(currentUserId));
			viewModel.addAttribute("drink", this.dServ.findDrinktById(id));
			viewModel.addAttribute("meats",this.mServ.findAllMeat());
			viewModel.addAttribute("dishes",this.fServ.findAllFoodItems());
			viewModel.addAttribute("drinks",this.dServ.findAllDrinks());
			return "editDrinkItem.jsp";
		}
	}
	
	// display meat option
	@GetMapping("/editMeat/{userId}/{id}")
	public String editMeatOptions(@PathVariable("userId") Long userId,@PathVariable("id") Long id, @ModelAttribute ("meat") Meat meat, HttpSession session,Model viewModel) {		
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
			viewModel.addAttribute("user", this.uServ.findUser(currentUserId));
			viewModel.addAttribute("meat", this.mServ.findMeatById(id));
			viewModel.addAttribute("meats",this.mServ.findAllMeat());
			viewModel.addAttribute("dishes",this.fServ.findAllFoodItems());
			viewModel.addAttribute("drinks",this.dServ.findAllDrinks());
		return "editMeatOptions.jsp";
		}
	}
	
	//update dish
	@PostMapping("/editDish/{userId}/{id}")
	public String updateDish(@Valid @ModelAttribute("foodItem") FoodItem foodItem, BindingResult result,@PathVariable("userId") Long userId,@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			return "editFoodItem.jsp";
		}
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
		viewModel.addAttribute("dish",this.fServ.findFoodItemById(id));
		this.fServ.saveFoodItem(foodItem);
		return "redirect:/addFoodItem";
		}
	}

	//update meat
	@PostMapping("/editMeat/{userId}/{id}")
	public String updateMeatOptions(@Valid @ModelAttribute("meat") Meat meat,BindingResult result,@PathVariable("userId") Long userId,@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			return "editMeatOptions.jsp";
		}
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
		viewModel.addAttribute("meat",this.mServ.findMeatById(id));
		this.mServ.saveTypeOfMeat(meat);
		return "redirect:/addFoodItem";
		}
	}
	//update drink
	@PostMapping("/editDrink/{userId}/{id}")
	public String updateDrink(@Valid @ModelAttribute("drink") Drink drink,BindingResult result,@PathVariable("userId") Long userId,@PathVariable("id") Long id, HttpSession session, Model viewModel) {
		if(result.hasErrors()) {
			return "editDrinkItem.jsp";
		}
		Long currentUserId= (Long)session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(currentUserId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		else {
		viewModel.addAttribute("drink",this.dServ.findDrinktById(id));
		this.dServ.saveDrink(drink);
		return "redirect:/addFoodItem";
		}
	}		
	//delete user
	@GetMapping("/{id}/deleteUser")
	public String deleteUser(@PathVariable("id") Long id, HttpSession session) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		this.uServ.deleteUserById(id);
		return "redirect:/allUsers";
	}
	//delete food item
	@GetMapping("/deleteFoodItem/{id}")
	public String deleteFoodItem(@PathVariable("id") Long id, HttpSession session) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
		this.fServ.deleteFoodItemById(id);
		return "redirect:/addFoodItem";
		
	}
	
	//delete meat option
	@GetMapping("/deleteMeatOption/{id}")
	public String deleteMeatOption(@PathVariable("id") Long id, HttpSession session) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
			this.mServ.deleteMeatById(id);
		return "redirect:/addFoodItem";
		
	}
	//delete drink
	@GetMapping("/deleteDrink/{id}")
	public String deleteDrink(@PathVariable("id") Long id, HttpSession session) {
		Long userId=(Long) session.getAttribute("user__id");
		User currentUser=this.uServ.findUser(userId);
		User neededUser= this.uServ.findUser((long) 1);
		
		if(session.getAttribute("user__id")==null){
			return "redirect:/home";
		}
		
		else if(currentUser!=neededUser) {
			return "redirect:/home/"+userId; 
		}
			this.dServ.deleteDrinkById(id);
		return "redirect:/addFoodItem";	
	}
}
