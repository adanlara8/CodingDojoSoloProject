package com.AdanLara.First_Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdanLara.First_Project.Models.Meat;
import com.AdanLara.First_Project.Repositories.MeatRepository;

@Service
public class MeatService {
	@Autowired
	private MeatRepository mRepo;
	
	//get all
	public List<Meat> findAllMeat(){
		return this.mRepo.findAll();
	}
	//find one
	public Meat findMeatById(Long Id) {
		return this.mRepo.findById(Id).orElse(null);
	}
	
	//save
	public Meat saveTypeOfMeat(Meat meat) {
	 return this.mRepo.save(meat);
	}
	//delete
	public void deleteMeatById(Long id) {
		this.mRepo.deleteById(id);
	}
	
}
