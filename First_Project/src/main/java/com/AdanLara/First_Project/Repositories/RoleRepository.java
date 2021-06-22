package com.AdanLara.First_Project.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.AdanLara.First_Project.Models.Role;



public interface RoleRepository extends CrudRepository <Role, Long> {
	
	List<Role> findAll();
	
	List<Role> findByName(String name);
	
	Optional<Role> findById(Long id);
}


