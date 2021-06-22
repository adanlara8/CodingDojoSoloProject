package com.AdanLara.First_Project.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
//att
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@Size(min=3, max=15)
	@NotBlank
	private String firstName;
	
	@Size(min=2,max=15)
	@NotBlank
	private String lastName;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id")
			)
	private List<Role> roles;
	
	@OneToMany(mappedBy="customer",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List<Order> orders;
	
	@OneToMany(mappedBy="liker",
			cascade=CascadeType.ALL,
			fetch=FetchType.LAZY)
	private List<Order> favorites;
	
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
//const
	public User() {
		super();
	}
	
	//gns
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Order> favorites) {
		this.favorites = favorites;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}	

}
