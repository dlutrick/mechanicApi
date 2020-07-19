package com.promineotech.mechanicApi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Order {
	
	private Long Id;
	private User user;
	private List<Job> services;
	private List<Product> products;	
	private LocalDate datePurchased;
	private Float totalPrice;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}

	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="service_order",
	joinColumns = @JoinColumn(name="orderId", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="serviceId", referencedColumnName="id"))
	public List<Job> getServices() {
		return services;
	}

	public void setServices(List<Job> services) {
		this.services = services;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="product_order",
	joinColumns = @JoinColumn(name="orderId", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="productId", referencedColumnName="id"))
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public LocalDate getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(LocalDate datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	

}
