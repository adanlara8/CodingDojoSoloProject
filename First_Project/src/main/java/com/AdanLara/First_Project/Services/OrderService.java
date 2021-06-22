package com.AdanLara.First_Project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdanLara.First_Project.Models.Order;
import com.AdanLara.First_Project.Models.User;
import com.AdanLara.First_Project.Repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository oRepo;
	
	
	public List<Order> findAll() {
		return this.oRepo.findAll();
	}
	
	public Order saveOrder(Order order) {
		
		return this.oRepo.save(order);
	}
	
	public void deleteOrder(Long id) {
		this.oRepo.deleteById(id);
	}
	
	public Order getById(Long id) {
		return this.oRepo.findById(id).orElse(null);
	}
	//like
	public void likeOrder(Order order, User user) {
		order.setLiker(user);
		this.oRepo.save(order);
		
	}
	
	//unlike
	public void unlikeOrder(Order order, User user) {
		order.setLiker(null);
		this.oRepo.save(order);
		
	}
}
