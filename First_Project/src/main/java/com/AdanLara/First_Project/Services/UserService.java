package com.AdanLara.First_Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdanLara.First_Project.Models.Order;
import com.AdanLara.First_Project.Models.User;
import com.AdanLara.First_Project.Repositories.RoleRepository;
import com.AdanLara.First_Project.Repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private RoleRepository rRepo;
	
	//add user
	public User saveWithUserRole(User user) {
		user.setRoles(rRepo.findByName("USER"));
	 return this.uRepo.save(user);
	}
	 //add admin
	public User saveWithAdminRole(User user) {
		user.setRoles(rRepo.findByName("ADMIN"));
		return this.uRepo.save(user);
	}
	//add owner
	public User saveWithOwnerRole(User user) {
		user.setRoles(rRepo.findByName("OWNER"));
		return this.uRepo.save(user);
	}	
	//delete user
	public void deleteUserById(Long id) {
		this.uRepo.deleteById(id);
	}
	//updated user
	public User updateUser(User user) {
		
		return this.uRepo.save(user);
	}
	//get single user
	public User findUser(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	//get by email
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	//find all
	public List<User> findAllUsers() {
		return this.uRepo.findAll();
	}
	//login authentication
	public Boolean loginAuthenticator(String email, String password) {
		User user= this.uRepo.findByEmail(email);
		if(user==null) {
			return false;
		}
		if(!password.equals(user.getPassword())) {
			return false;
		}
		else return true;
	}

}
