package com.AdanLara.First_Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdanLara.First_Project.Models.Drink;
import com.AdanLara.First_Project.Models.Meat;
import com.AdanLara.First_Project.Repositories.DrinkRepository;
import com.AdanLara.First_Project.Repositories.MeatRepository;

@Service
public class DrinkService {
	
	@Autowired
	private DrinkRepository dRepo;
	
	//get all
	public List<Drink> findAllDrinks(){
		return this.dRepo.findAll();
	}
	//find one
	public Drink findDrinktById(Long Id) {
		return this.dRepo.findById(Id).orElse(null);
	}
	
	//save
	public Drink saveDrink(Drink drink) {
	 return this.dRepo.save(drink);
	}
	//delete
	public void deleteDrinkById(Long id) {
		this.dRepo.deleteById(id);
	}
	//fing by name for order
	public Drink findByName(String name) {
		return this.dRepo.findByName(name);
	}
		
}
