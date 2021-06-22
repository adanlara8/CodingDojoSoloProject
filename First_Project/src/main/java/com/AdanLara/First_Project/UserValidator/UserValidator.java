package com.AdanLara.First_Project.UserValidator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.AdanLara.First_Project.Models.User;
import com.AdanLara.First_Project.Repositories.UserRepository;



@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository uRepo;
	
	  @Override
	public boolean supports(Class<?>clazz) {
		return User.class.equals(clazz);	
		}
	  
	  @Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "PasswordMatch","Passwords Must Match");
		}
		if(this.uRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "CheckEmail","Email is Already Registered");
		}
	}

}
