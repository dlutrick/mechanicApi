package com.promineotech.mechanicApi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
	
	private Long id;
	private User user;
	private String make;
	private String model;
	private String year;
	private String currentMiles;
	private String licensePlate;
	private String vehiclePictureUrl;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCurrentMiles() {
		return currentMiles;
	}

	public void setCurrentMiles(String currentMiles) {
		this.currentMiles = currentMiles;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getVehiclePictureUrl() {
		return vehiclePictureUrl;
	}

	public void setVehiclePictureUrl(String vehiclePictureUrl) {
		this.vehiclePictureUrl = vehiclePictureUrl;
	}

}
