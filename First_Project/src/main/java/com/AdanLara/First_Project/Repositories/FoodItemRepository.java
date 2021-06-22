package com.AdanLara.First_Project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdanLara.First_Project.Models.FoodItem;

@Repository
public interface FoodItemRepository extends CrudRepository <FoodItem, Long> {
	
	List<FoodItem> findAll();
	
	FoodItem findByName(String name);
}
