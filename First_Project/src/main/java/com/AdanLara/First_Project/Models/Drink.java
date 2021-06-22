package com.AdanLara.First_Project.Models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="drinks")
public class Drink {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull(message="Must Have Price")
	private float price;
	
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyy-MM-DD HH:mm:ss")
	private Date createdAt;
	
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt= new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt= new Date();
	}
	//cont

	public Drink() {
	}
	//GNS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

}
