package com.AdanLara.First_Project.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="neworders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank
	private String item;
	@NotBlank
	private String meat;
	@NotNull
	private Long quantity;

	@NotNull
	private float itemPrice;
	
	private String drink;
	
	private float drinkPrice;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyy-MM-DD HH:mm:ss")
	private Date createdAt;
	
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User customer;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="liker_id")
	private User liker;
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt= new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt= new Date();
	}
	//conts

	public Order() {
	}
	//GNS

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMeat() {
		return meat;
	}

	public void setMeat(String meat) {
		this.meat = meat;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public float getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(float drinkPrice) {
		this.drinkPrice = drinkPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getLiker() {
		return liker;
	}

	public void setLiker(User liker) {
		this.liker = liker;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
}
