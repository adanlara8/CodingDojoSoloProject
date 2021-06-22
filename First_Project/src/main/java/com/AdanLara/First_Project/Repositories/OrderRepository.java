package com.AdanLara.First_Project.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AdanLara.First_Project.Models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
	List<Order> findAll();

}
