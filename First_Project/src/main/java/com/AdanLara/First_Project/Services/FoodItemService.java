package com.AdanLara.First_Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdanLara.First_Project.Models.FoodItem;
import com.AdanLara.First_Project.Repositories.FoodItemRepository;

@Service
public class FoodItemService {
	@Autowired
	private FoodItemRepository fRepo;
	
	//get all
	public List<FoodItem> findAllFoodItems(){
		return this.fRepo.findAll();
	}
	//find one
	public FoodItem findFoodItemById(Long Id) {
		return this.fRepo.findById(Id).orElse(null);
	}
	
	//save
	public FoodItem saveFoodItem(FoodItem foodItem) {
	 return this.fRepo.save(foodItem);
	}
	//delete
	public void deleteFoodItemById(Long id) {
		this.fRepo.deleteById(id);
	}
	//find string for order
	public FoodItem findItembyName(String name) {
		return this.fRepo.findByName(name);
	}
}