package com.AdanLara.First_Project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdanLara.First_Project.Models.Meat;

@Repository
public interface MeatRepository extends CrudRepository <Meat,Long> {
	
	List<Meat> findAll();
	
}
