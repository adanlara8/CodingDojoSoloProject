package com.AdanLara.First_Project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdanLara.First_Project.Models.Drink;

@Repository
public interface DrinkRepository extends CrudRepository <Drink,Long>{
	
	List<Drink> findAll();
	
	Drink findByName(String name);
}
